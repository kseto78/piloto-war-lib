package es.minhap.misim.bus.model.seguridad;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Properties;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import misim.bus.common.exceptions.ApplicationException;
import misim.bus.common.util.KeyStoreUtils;
import misim.bus.common.util.XMLUtils;

import org.apache.commons.codec.binary.Base64;
import org.apache.ws.security.WSSConfig;
import org.apache.ws.security.WSSecurityEngine;
import org.apache.ws.security.components.crypto.Crypto;
import org.apache.ws.security.components.crypto.CryptoFactory;
import org.apache.ws.security.message.WSSecHeader;
import org.apache.ws.security.message.WSSecSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.misim.bus.model.exception.ModelException;
import es.minhap.plataformamensajeria.iop.beans.lotes.PeticionXMLBean;
import es.minhap.plataformamensajeria.iop.manager.TblCertificadosManager;
import es.minhap.sim.model.TblCertificados;

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
	
	@Resource(name="TblCertificadosManagerImpl") 
	private TblCertificadosManager tblCertificadosManager;
	
	@Resource(name = "cifradoPrivadoWSSProperties")
	Properties props;
	
	@Resource(name = "certificadoPublicoProperties")
	Properties propsCertificado;
	
	@Autowired
	CifradoService cifradoService;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Document firmarWSSecurity(final Document documento,
			final String keyStoreType, final String keyStorePassword,
			final String keyStoreAlias, final String aliasPassword,
			final String keyStoreFile, PropertiesServices ps) throws ModelException {

		Document documentoFirmado = null;
		
		LOG.info("En firmarWSSecurity.");

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
			final WSSecurityEngine newEngine = new WSSecurityEngine();
			final WSSConfig config = WSSConfig.getNewInstance();
			config.setAllowNamespaceQualifiedPasswordTypes(true);
			config.setWsiBSPCompliant(false);
			newEngine.setWssConfig(config);
			final WSSecSignature builder = new WSSecSignature();
			builder.setUserInfo(keyStoreAlias, aliasPassword);
			builder.setKeyIdentifierType(1);
			builder.setSigCanonicalization(WSS_CANONICALIZATION);
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
			
			LOG.info("Fin firmarWSSecurity.");
			return documentoFirmado;
			
		} catch (final Throwable e) {
			LOG.error("Firma: Error de sistema Firma", e);
			throw new ModelException("Error de sistema Firma", Integer.parseInt(ps.getMessage("plataformaErrores.ValidarFirma.COD_ERROR_GENERAL", null)));
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
	public boolean validarFirmaWSSecurity(final Document documento, PropertiesServices ps)	throws ModelException {
		

		LOG.debug("Inicio validarFirmaWSSecurity...");
		
		LOG.info("En validarFirmaWSSecurity.");

		try {			
			
			LOG.info("REQUEST ORIGINAL FIRMADA Y ENCRIPTADA: " + XMLUtils.dom2xml(documento));

			// Invocamos al servicio de descifrado
			Document docDescifrado = cifradoService.descifrarKey(
					documento,
					props.getProperty(KeyStoreUtils.KEY_STORE_TYPE),
					props.getProperty(KeyStoreUtils.KEY_STORE_PASSWORD),
					props.getProperty(KeyStoreUtils.KEY_STORE_ALIAS),
					props.getProperty(KeyStoreUtils.ALIAS_PASSWORD),
					props.getProperty(KeyStoreUtils.KEY_STORE_FILE));
			
			docDescifrado = cifradoService.descifrar(docDescifrado,
				props.getProperty(KeyStoreUtils.KEY_STORE_TYPE),
				props.getProperty(KeyStoreUtils.KEY_STORE_PASSWORD),
				props.getProperty(KeyStoreUtils.KEY_STORE_ALIAS),
				props.getProperty(KeyStoreUtils.ALIAS_PASSWORD),
				props.getProperty(KeyStoreUtils.KEY_STORE_FILE));	
			
			LOG.info("REQUEST ORIGINAL FIRMADA Y DESENCRIPTADA: " + XMLUtils.dom2xml(docDescifrado));
			
			// Obtenemos el id del servicio de la peticion para poder buscar en la BBDD el
			// certificado correspondiente solo si el tipo de la peticion es de EnvioMensajesServiceWSS,
			// si no encontramos el idServicio, la peticion es de tipo ActualizarPassword
			NodeList peticion = docDescifrado.getElementsByTagNameNS("http://misim.redsara.es/misim-bus-webapp/peticion", "Peticion");
			String idServicio = null;
			String xmlPeticion = "";
			boolean isActualizarPassword = false;
			if(peticion.item(0)!=null){
				xmlPeticion = XMLUtils.nodeToString(peticion.item(0));
				PeticionXMLBean peticionXML = new PeticionXMLBean();
				peticionXML.loadObjectFromXML(xmlPeticion);
				idServicio = peticionXML.getServicio();		
				if(idServicio == null){
					throw new ModelException("Falta el id de servicio para comprobar la firma", Integer.parseInt(ps.getMessage("plataformaErrores.ValidarFirma.COD_ERROR_GENERAL", null)));
				}
			}
			else {
				peticion = docDescifrado.getElementsByTagNameNS("http://misim.redsara.es/misim-bus-webapp/peticion", "ActualizarPasswordPeticion");
				xmlPeticion = XMLUtils.nodeToString(peticion.item(0));
				isActualizarPassword = true;
			}	
			// Se recuperan todos los nodos Signature del XML
			final NodeList signatureL = docDescifrado.getElementsByTagNameNS(
					"http://www.w3.org/2000/09/xmldsig#", "Signature");

			// Si no hay o si no hay uno único, la firma no es válida
			if ((signatureL == null) || (signatureL.getLength() != 1)) {
				LOG.error("Validar Firma: No se ha encontrado el nodo firma");
				throw new ModelException("No se ha encontrado el nodo firma", 307);
			}

			// Se recupera el único elemento Signature
			final Node sigNode = signatureL.item(0);
			
			boolean validarFirmaServidor = false;
			
			if(isActualizarPassword){ //Comprobacion de firma para tipo ActualizarPassword
				if (comprobarFirmaServerCertificadoPublico(docDescifrado, propsCertificado.getProperty(ModelTestUtilTest.KEY_STORE_ALIAS), 
						propsCertificado.getProperty(ModelTestUtilTest.KEY_STORE_PASSWORD), 
						propsCertificado.getProperty(ModelTestUtilTest.KEY_STORE_FILE) )){
					validarFirmaServidor = true;
					LOG.info("comprobarFirmaServer: "+validarFirmaServidor);
				}else{
					validarFirmaServidor = false;
					LOG.info("comprobarFirmaServer: "+validarFirmaServidor);
				}

				LOG.info("comprobarFirmaServer: "+validarFirmaServidor);
			}else{ //Comprobacion de firma para peticion de tipo EnvioMensajesServiceWSS
				if (comprobarFirmaServer(sigNode, idServicio, ps)){
					validarFirmaServidor = true;
					LOG.info("comprobarFirmaServer: "+validarFirmaServidor);
				}else{
					validarFirmaServidor = false;
					LOG.info("comprobarFirmaServer: "+validarFirmaServidor);
				}
				LOG.info("comprobarFirmaServer: "+validarFirmaServidor);
			}

			if (LOG.isDebugEnabled()) {
				LOG.debug("Fin validarFirmaWSSecurity");
			}
			
			LOG.info("Fin validarFirmaWSSecurity: "+validarFirmaServidor);
			
			return validarFirmaServidor;

		} catch (final ModelException e) {
			LOG.error(e.getMensaje(), e);
			throw new ModelException(e.getMensaje(), Integer.parseInt(ps.getMessage("plataformaErrores.ValidarFirma.COD_ERROR_GENERAL", null)));
		} catch (final Throwable e) {
			LOG.error("Validar Firma: Error de sistema Validar Firma", e);
			throw new ModelException("Error de sistema Validar Firma", Integer.parseInt(ps.getMessage("plataformaErrores.ValidarFirma.COD_ERROR_GENERAL", null)));
		}

	}

	/**
	 * Comprueba la firma mandada en la peticion con la clave publica del certificado del servidor
	 * 
	 * @param sigNode
	 * @param bytesCertificadoServidor
	 * @return
	 * @throws CertificateException
	 */
	private boolean comprobarFirmaServer(Node sigNode, String idServicio, PropertiesServices ps) throws ModelException {

		String valSerial=null, valName=null;
		
		LOG.info("En comprobarFirmaServer ");
		
		for (int i=0;i<sigNode.getChildNodes().getLength();i++){
			if(sigNode.getChildNodes().item(i).getNodeName().contains("KeyInfo")){
				Node keyInfo = sigNode.getChildNodes().item(i);
				for(int j=0;j<keyInfo.getChildNodes().getLength();j++){
					if(keyInfo.getChildNodes().item(j).getNodeName().contains("SecurityTokenReference")){
						Node securityTokenReference = keyInfo.getChildNodes().item(j);
						for(int k=0;k<securityTokenReference.getChildNodes().getLength();k++){
							if(securityTokenReference.getChildNodes().item(k).getNodeName().contains("X509Data")){
								Node x509Data = securityTokenReference.getChildNodes().item(k);
								for(int l=0;l<x509Data.getChildNodes().getLength();l++){
									if(x509Data.getChildNodes().item(l).getNodeName().contains("X509IssuerSerial")){
										Node x509IssuerSerial = x509Data.getChildNodes().item(l);
										for(int m=0; m<x509IssuerSerial.getChildNodes().getLength();m++){
											if(x509IssuerSerial.getChildNodes().item(m).getNodeName().contains("X509IssuerName")){
												valName = x509IssuerSerial.getChildNodes().item(m).getTextContent();
											}
											if(x509IssuerSerial.getChildNodes().item(m).getNodeName().contains("X509SerialNumber")){
												valSerial = x509IssuerSerial.getChildNodes().item(m).getTextContent();
												break;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
				
		try {
			String valSerialServidor = "";
			String valNameServidor = "";
			TblCertificados certificado = tblCertificadosManager.getCertificadosByServicio(Long.valueOf(idServicio));
			valSerialServidor = certificado.getSerial();
			valNameServidor = certificado.getIssuer();

			//Se comprueba
			LOG.info("[FirmaServiceImpl] la firma contiene el serial number " + valSerial + " y el IssuerDN:"+ valName);
			if(valSerialServidor.equals((valSerial)) && 
				valNameServidor.replaceAll(" ","").equals(valName.replaceAll(" ",""))){
				return true;
			}
		}catch (NullPointerException e){
			LOG.error("No se encuentra el certificado o no se encuentra activo en la base de datos");
			throw new ModelException("No se encuentra el certificado o no se encuentra activo en la base de datos.", 502);		
		}catch (Exception e) {
			LOG.error("Ha ocurrido un error al validar la firma.", e);
			throw new ModelException("Ha ocurrido un error al validar la firma.", Integer.parseInt(ps.getMessage("plataformaErrores.ValidarFirma.COD_ERROR_GENERAL", null)));
		} 
		return false;
	}

	/**
	 * Comprueba la firma mandada en la peticion con un certificado publico
	 * 
	 * @param documento
	 * @param bytesCertificadoServidor
	 * @return
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * @throws KeyStoreException 
	 * @throws NoSuchAlgorithmException 
	 * @throws CertificateException
	 * @throws UnrecoverableKeyException 
	 */
	private boolean comprobarFirmaServerCertificadoPublico(Document documento, String aliasKeyStore, String passwordKeyStore, String rutaKeyStore) throws UnsupportedEncodingException, IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
		 
		try(FileInputStream is = new FileInputStream(rutaKeyStore)){
			
			String contenidoCertificadoPublicoServidor = null;		
		    KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
		        
		    char[] passwd = passwordKeyStore.toCharArray();
		    keystore.load(is, passwd);
		        
		    Certificate cert = keystore.getCertificate(aliasKeyStore);
	    	Base64 encoder = new Base64(64);
		        
		    byte[] derCert = cert.getEncoded();
		    contenidoCertificadoPublicoServidor = new String(encoder.encode(derCert));
			
			NodeList nodes = documento.getElementsByTagName("wsse:KeyIdentifier");
			String certificadoPeticion = nodes.item(0).getFirstChild().getTextContent();
			 
			contenidoCertificadoPublicoServidor = contenidoCertificadoPublicoServidor.replace("\n", "").replace("\r", "");
			return certificadoPeticion.equals(contenidoCertificadoPublicoServidor);
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
		
		LOG.info("En compararCertificados.");
		
		try{
			final X509Certificate certificate1 = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(Base64.decodeBase64(certificado1)));
			final X509Certificate certificate2 = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(Base64.decodeBase64(certificado2)));
			LOG.info("certificado 1: "+certificate1);
			LOG.info("certificado 2: "+certificate2);
				
			certificadosIguales = certificate1.equals(certificate2);
			
			LOG.info("Fin compararCertificados, certificados iguales: "+certificadosIguales);
			
		} catch (final Throwable e) {
			LOG.error("Error desconocido al comparar los certificados: ", e);
			throw new ApplicationException(e);
		}
		return certificadosIguales;
	}

}
