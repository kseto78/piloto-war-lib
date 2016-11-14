package es.minhap.misim.bus.model.seguridad;

import java.io.ByteArrayInputStream;
import java.security.Provider;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;

import misim.bus.common.exceptions.ApplicationException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.ws.security.WSSConfig;
import org.apache.ws.security.WSSecurityEngine;
import org.apache.ws.security.components.crypto.Crypto;
import org.apache.ws.security.components.crypto.CryptoFactory;
import org.apache.ws.security.message.WSSecHeader;
import org.apache.ws.security.message.WSSecSignature;
import org.apache.ws.security.util.WSSecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.misim.bus.model.util.X509KeySelectorPublicKey;

/**
 * Implementación para la API {@link FirmaService}
 * 
 * @author ludarcos
 * 
 */
@Service(value = "firmaServiceImpl")
public class FirmaServiceImpl implements FirmaService {

	/**
	 * Logger slf4j. Para mayor modularidad, se aplica en la interfaz de la API
	 */
	private static final Logger LOG = LoggerFactory.getLogger(FirmaService.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Document firmarWSSecurity(final Document documento,
			final String keyStoreType, final String keyStorePassword,
			final String keyStoreAlias, final String aliasPassword,
			final String keyStoreFile) throws ModelException {

		Document documentoFirmado = null;

		LOG.debug("Inicio firmarWSSecurity...");

		try {
			// Initialización de la factory
			final Crypto crypto = CryptoFactory.getInstance(
					SeguridadUtil.initializateCryptoProperties(keyStoreType,
															   keyStorePassword, 
															   keyStoreAlias, 
															   aliasPassword,
															   keyStoreFile));

			// Inicialización de Builder de firma
			final WSSecSignature builder = new WSSecSignature();
			builder.setUserInfo(keyStoreAlias, aliasPassword);
			builder.setKeyIdentifierType(1);
			builder.setSigCanonicalization(WSS_CANONICALIZATION);
			final WSSecurityEngine newEngine = new WSSecurityEngine();
			final WSSConfig config = WSSConfig.getNewInstance();
			config.setAllowNamespaceQualifiedPasswordTypes(true);
			config.setWsiBSPCompliant(false);
			newEngine.setWssConfig(config);
			builder.setWsConfig(config);

			// Creación de la cabecera
			final WSSecHeader secHeader = new WSSecHeader();
			secHeader.insertSecurityHeader(documento);

			// Creación del dolcumento firmado
			documentoFirmado = builder.build(documento, crypto, secHeader);
			documentoFirmado.setXmlStandalone(true);

			if (LOG.isDebugEnabled()) {
				LOG.debug("Fin firmarWSSecurity");
			}
			return documentoFirmado;
			
		} catch (final Throwable e) {
			LOG.error("Firma: Error de sistema Firma", e);
			throw new ModelException("Error de sistema Firma", 502);
		}
	}

	@Override
	public Document firmarXMLDSign(final Document documento,
			final String keyStoreType, final String keyStorePassword,
			final String keyStoreAlias, final String aliasPassword,
			final String keyStoreFile) throws ModelException {
		// TODO implementar la firma con XML Digital Signature
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validarFirmaWSSecurity(final Document documento)	throws ModelException {

		LOG.debug("Inicio validarFirmaWSSecurity...");

		try {
			
			final XMLSignatureFactory signatureFactory = XMLSignatureFactory
					.getInstance("DOM", (Provider) Class.forName("org.apache.jcp.xml.dsig.internal.dom.XMLDSigRI").newInstance());

			// Se recuperan todos los nodos Signature del XML
			final NodeList signatureL = documento.getElementsByTagNameNS(
					"http://www.w3.org/2000/09/xmldsig#", "Signature");

			// Si no hay o si no hay uno único, la firma no es válida
			if ((signatureL == null) || (signatureL.getLength() != 1)) {
				LOG.error("Validar Firma: No se ha encontrado el nodo firma");
				throw new ModelException("No se ha encontrado el nodo firma", 307);
			}

			// Se recupera el único elemento Signature
			final Node sigNode = signatureL.item(0);

			Node binarySecurityToken = null;

			// Se recuperan todos los nodos wsse:Security que no tienen "actor"
			final NodeList listaNodosSeguridad = WSSecurityUtil.getSecurityHeader(documento, StringUtils.EMPTY).getChildNodes();

			// Si hay al menos un nodo XML Security
			if (listaNodosSeguridad != null) {
				// Se busca el BinarySecurityToken
				for (int i = 0; i < listaNodosSeguridad.getLength(); i++) {
					if (listaNodosSeguridad.item(i).getNodeName().contains("BinarySecurityToken")) {
						binarySecurityToken = listaNodosSeguridad.item(i).getFirstChild();
						break;
					}
				}
			}

			// Si no existe ningún BinarySecurityToken, no se valida la firma
			byte[] bytesCertificado = null;
			if (binarySecurityToken != null) {
				bytesCertificado=binarySecurityToken.getTextContent().getBytes();
			}
			
			if(bytesCertificado==null){
				LOG.error("Validar Firma: No se ha encontrado el certificado firmante en el documento XML");
				throw new ModelException("No se ha encontrado el certificado firmante en el documento XML", 311);	
			}

			// Se reconstituye el Certificado (público) contenido en la firma
			final X509Certificate certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(
							new ByteArrayInputStream(Base64.decodeBase64(bytesCertificado)));

			// Se recupera la clave pública desde el certificado
			final PublicKey pk = certificate.getPublicKey();

			// Se recupera el Body
			final Element body = WSSecurityUtil.findBodyElement(documento);

			// Se inicializa el contexto de validación DOM
			final DOMValidateContext valContext = new DOMValidateContext(new X509KeySelectorPublicKey(pk), sigNode);
			
			if (StringUtils.isNotBlank(body.getAttributeNS(WSSECURITY_XSD,ELEMENT_ID))) {
				valContext.setIdAttributeNS(body, WSSECURITY_XSD, ELEMENT_ID);
			} else {
				valContext.setIdAttributeNS(body, null, ELEMENT_ID);
			}

			// Se recupera la firma
			final XMLSignature signature = signatureFactory.unmarshalXMLSignature(valContext);

			// Se verifica la validación
			final boolean firmaValida = signature.validate(valContext);

			if (LOG.isDebugEnabled()) {
				LOG.debug("Fin validarFirmaWSSecurity");
			}

			return firmaValida;

		} catch (final Throwable e) {
			LOG.error("Validar Firma: Error de sistema Validar Firma", e);
			throw new ModelException("Error de sistema Validar Firma", 502);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validarFirmaXMLDSign(final Document documento)
			throws ModelException {
		// TODO Implementar la validación de firma por XML Digital Signature
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean compararCertificados(byte[] certificado1, byte[] certificado2) throws ModelException{
		
		boolean certificadosIguales=false;
		
		try{
			final X509Certificate certificate1 = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(Base64.decodeBase64(certificado1)));
			final X509Certificate certificate2 = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(Base64.decodeBase64(certificado2)));
			
			certificadosIguales = certificate1.equals(certificate2);
			
		} catch (final Throwable e) {
			LOG.error("Error desconocido al comparar los certificados: ", e);
			throw new ApplicationException(e);
		}
		return certificadosIguales;
	}

}
