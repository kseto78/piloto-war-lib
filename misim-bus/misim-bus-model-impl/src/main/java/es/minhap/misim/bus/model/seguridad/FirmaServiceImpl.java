package es.minhap.misim.bus.model.seguridad;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.Provider;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Properties;

import javax.annotation.Resource;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import misim.bus.common.exceptions.ApplicationException;
import misim.bus.common.util.XMLUtils;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import es.minhap.misim.bus.model.exception.ModelException;

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

	
	@Resource(name = "cifradoPrivadoProperties")
	Properties props;
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
			
			
				LOG.info("REQUEST ORIGINAL FIRMADA: " + XMLUtils.dom2xml(documento));
			
			
			final XMLSignatureFactory signatureFactory = XMLSignatureFactory
					.getInstance("DOM", (Provider) Class.forName("org.apache.jcp.xml.dsig.internal.dom.XMLDSigRI").newInstance());
			
			
			
			
			// Se recupera el certificado de la firma WS-Security
			NodeList nodeSecurityWSSecurity = documento.getElementsByTagNameNS(
								"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd",
								"BinarySecurityToken");
	
			// Se recuperan todos los nodos Signature del XML
			final NodeList signatureL = documento.getElementsByTagNameNS(
					"http://www.w3.org/2000/09/xmldsig#", "Signature");

			// Si no hay o si no hay uno único, la firma no es válida
//			if ((signatureL == null) || (signatureL.getLength() != 1)) {
//				LOG.error("Validar Firma: No se ha encontrado el nodo firma");
//				throw new ModelException("No se ha encontrado el nodo firma", 307);
//			}

			// Se recupera el único elemento Signature
			final Node sigNode = signatureL.item(0);

			Node binarySecurityToken = null;

			// Se recuperan todos los nodos wsse:Security que no tienen "actor"
			final NodeList listaNodosSeguridad = WSSecurityUtil.getSecurityHeader(documento, StringUtils.EMPTY).getChildNodes();

//			// Si hay al menos un nodo XML Security
			if (listaNodosSeguridad != null) {
				// Se busca el BinarySecurityToken
				for (int i = 0; i < listaNodosSeguridad.getLength(); i++) {
					if (listaNodosSeguridad.item(i).getNodeName().contains("BinarySecurityToken")) {
						binarySecurityToken = listaNodosSeguridad.item(i).getFirstChild();
						break;
					}
				}
			}
			
			byte[] bytesCertificado = null;
			bytesCertificado= nodeSecurityWSSecurity.item(0).getTextContent().getBytes();

			if(bytesCertificado==null){
				LOG.error("Validar Firma: No se ha encontrado el certificado firmante en el documento XML");
				throw new ModelException("No se ha encontrado el certificado firmante en el documento XML", 311);	
			}
			
			File file = new File(props.getProperty(ModelTestUtil.MODEL_PETICION));
			
	
			// Recuperamos el documento SOAP sin firmar
			final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			final DocumentBuilder db = dbf.newDocumentBuilder();
			final Document docOrginal2 = db.parse(file);
			


			// Invocamos al servicio de firma
			final Document docFirmado = firmarWSSecurity(
					docOrginal2,
					props.getProperty(ModelTestUtil.KEY_STORE_TYPE),
					props.getProperty(ModelTestUtil.KEY_STORE_PASSWORD),
					props.getProperty(ModelTestUtil.KEY_STORE_ALIAS),
					props.getProperty(ModelTestUtil.ALIAS_PASSWORD),
					props.getProperty(ModelTestUtil.KEY_STORE_FILE));
			
			
			NodeList nodeSecurityWSSecurity2 = docFirmado.getElementsByTagNameNS(
					"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd",
					"BinarySecurityToken");


			byte[] bytesCertificado2 = null;
			
			bytesCertificado2= nodeSecurityWSSecurity2.item(0).getTextContent().getBytes();
						
			boolean firmaValida = false;
			
			if (compararCertificados(bytesCertificado, bytesCertificado2)){
				firmaValida = true;
			}else{
				firmaValida = false;
			}
			

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
