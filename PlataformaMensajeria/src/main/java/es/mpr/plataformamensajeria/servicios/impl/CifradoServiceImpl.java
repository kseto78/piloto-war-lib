package es.mpr.plataformamensajeria.servicios.impl;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.ws.security.WSEncryptionPart;
import org.apache.ws.security.components.crypto.Crypto;
import org.apache.ws.security.components.crypto.CryptoFactory;
import org.apache.ws.security.message.WSSecEncrypt;
import org.apache.ws.security.message.WSSecHeader;
import org.apache.xml.security.Init;
import org.apache.xml.security.algorithms.JCEMapper;
import org.apache.xml.security.encryption.XMLCipher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.util.KeyValueObject;

import es.mpr.plataformamensajeria.servicios.ifaces.CifradoService;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.SeguridadUtil;

/**
 * Clase CifradoServiceImpl.
 */
@Service(value = "cifradoService")
public class CifradoServiceImpl implements CifradoService {

	protected static final String ERROR_DE_SISTEM = "Error de sistema Descifrado";

	protected static final String CIFRADOSERVICEI = "[CifradoServiceImpl] Descifrado: Error de sistema Descifrado";

	protected static final String SUNJCE = "SunJCE";

	protected static final String HTTPWWWDOTW3DOT = "http://www.w3.org/2001/04/xmlenc#aes128-cbc";

	protected static final String HTTPWWWDOTW3DOT0 = "http://www.w3.org/2001/04/xmlenc#";

	protected static final String KEYSTORE_UTILIZ = "KeyStore utilizado: ";

	protected static final String CIPHERVALUE = "CipherValue";

	/** Constante SEPARADOR_CERTIFICADOS_VALUES. */
	private static final String SEPARADOR_CERTIFICADOS_VALUES = "#";

	/** Constante SEPARADOR_CERTIFICADOS. */
	private static final String SEPARADOR_CERTIFICADOS = "&&";

	/**
	 * Logger slf4j. Para mayor modularidad, se aplica en la interfaz de la API
	 */
	private static final Logger LOG = LoggerFactory.getLogger(CifradoService.class);
	
	/**  props. */
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties props;


	@Override
	public Document descifrar(final Document documento,
			final String keyStoreType, final String keyStorePassword,
			final String keyStoreAlias, final String aliasPassword,
			final String keyStoreFile) throws Exception{
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("Inicio descifrar....");
			LOG.debug(KEYSTORE_UTILIZ + keyStoreFile);
		}
		
		try {
			
			Init.init();
			final Node key = documento.getElementsByTagNameNS(
					HTTPWWWDOTW3DOT0, 
					"EncryptedKey").item(0);
			
			if (key == null) {
				return documento;
			}
			final Node keyData = ((Element) key).getElementsByTagNameNS(
					HTTPWWWDOTW3DOT0, 
					"CipherData").item(0);

			final Node keyValue = ((Element) keyData).getElementsByTagNameNS(
					HTTPWWWDOTW3DOT0, 
					CIPHERVALUE).item(0);

			final String symetricKey = ((Element) keyValue).getFirstChild().getNodeValue();

			final SecretKeySpec keySpec = new SecretKeySpec(
					Base64.decodeBase64(symetricKey), "AES");

			final Element encryptedDataElement = (Element) documento.getElementsByTagNameNS(
					HTTPWWWDOTW3DOT0,
					"EncryptedData").item(0);

			final XMLCipher xmlCipher = XMLCipher.getProviderInstance(
					HTTPWWWDOTW3DOT, 
					SUNJCE);
			
			xmlCipher.init(2, keySpec);
			xmlCipher.doFinal(documento, encryptedDataElement);

			if (LOG.isDebugEnabled()) {
				LOG.debug("Fin descifrar");
			}
			
			return documento;
			
		} catch (final Exception e) {
			LOG.error(CIFRADOSERVICEI, e);
			throw new Exception(ERROR_DE_SISTEM);
		}
	}

	@Override
	public Document descifrarKey(final Document documento,
			final String keyStoreType, final String keyStorePassword,
			final String keyStoreAlias, final String aliasPassword,
			final String keyStoreFile) throws Exception {
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("Inicio descifrarKey...");
			LOG.debug("Key store utilizado: " + keyStoreFile);
		}
		
		try (FileInputStream file = new FileInputStream(keyStoreFile)){
			
			final NodeList nl = documento.getElementsByTagNameNS(
					HTTPWWWDOTW3DOT0, 
					CIPHERVALUE);

			final Node encriptedKey = nl.item(0);

			if (encriptedKey == null) {
				return documento;
				}
			
			JCEMapper.registerDefaultAlgorithms();
			
			KeyStore ks;
			if ("PKCS12".equals(keyStoreType)) {
				ks = KeyStore.getInstance(keyStoreType, "SunJSSE");
			} else {
				ks = KeyStore.getInstance(keyStoreType, "SUN");
			}
			
			ks.load(file,keyStorePassword.toCharArray());
						
			final KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(
					keyStoreAlias, 
					new KeyStore.PasswordProtection(aliasPassword.toCharArray()));

			final PrivateKey privateKey = keyEntry.getPrivateKey();

			final Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding",SUNJCE);
			
			cipher.init(2, privateKey);
			final byte[] encryptedKeyBA = Base64.decodeBase64(encriptedKey.getFirstChild().getNodeValue());

			final String symetricKey = Base64.encodeBase64String(cipher.doFinal(encryptedKeyBA));
			encriptedKey.getFirstChild().setNodeValue(symetricKey);
			
			if (LOG.isDebugEnabled()) {
				LOG.debug("Fin descifrarKey...");
			}
			return documento;
			
		} catch (final Throwable e) {
			LOG.error(CIFRADOSERVICEI, e);
			throw new Exception(ERROR_DE_SISTEM);
		}
	}
	
	@Override
	public Document cifrar(final Document documento, final String keyStoreType,
			final String keyStorePassword, final String keyStoreAlias,
			final String aliasPassword, final String keyStoreFile,
			final List<Node> nodosAFirmar) throws Exception {
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("Inicio cifrar....");
			LOG.debug(KEYSTORE_UTILIZ + keyStoreFile);
		}
		
		try {
			
			final Crypto crypto = CryptoFactory.getInstance(
					SeguridadUtil.initializateCryptoProperties(keyStoreType,
															   keyStorePassword, 
															   keyStoreAlias, 
															   aliasPassword,
															   keyStoreFile));

			final WSSecEncrypt builder = new WSSecEncrypt();
			builder.setUserInfo(keyStoreAlias, keyStorePassword);
			builder.setKeyEnc("http://www.w3.org/2001/04/xmlenc#rsa-1_5");
			builder.setKeyIdentifierType(1);
			builder.setSymmetricEncAlgorithm(HTTPWWWDOTW3DOT);
			JCEMapper.registerDefaultAlgorithms();

			final WSSecHeader secHeader = new WSSecHeader();
			secHeader.insertSecurityHeader(documento);
			builder.prepare(documento, crypto);

			final List<WSEncryptionPart> parts = new ArrayList<>();
			for (final Node node : nodosAFirmar) {
				final WSEncryptionPart encP = new WSEncryptionPart(node.getLocalName(), node.getNamespaceURI(), "Content");
				parts.add(encP);
			}
			final Element refs = builder.encryptForRef(null, parts);

			builder.addInternalRefElement(refs);
			builder.prependToHeader(secHeader);
			builder.prependBSTElementToHeader(secHeader);
			
			if (LOG.isDebugEnabled()) {
				LOG.debug("Fin cifrar");
			}
			return documento;
			
		} catch (final Throwable e) {
			LOG.error("[CifradoServiceImpl] Cifrado: Error de sistema Cifrado", e);
			throw new Exception("Error de sistema Cifrado");
		}
	}
	
	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.CifradoService#getCertificados()
	 */
	public List<KeyValueObject> getCertificados() throws BusinessException {
		
		try {
			String certificados = props.getProperty("decode.keystore.certificados", null);
			
			String[] combos = certificados.split(SEPARADOR_CERTIFICADOS);
			List<KeyValueObject> result = new ArrayList<>();

			for (String combo : combos) {
				KeyValueObject option =  new KeyValueObject();
				String [] valores = combo.split(SEPARADOR_CERTIFICADOS_VALUES);
				option.setDescripcion(valores[0]);
				option.setCodigo(valores[1]);
				result.add(option);
			}
			
			return result;
		} catch (Exception e){
			LOG.error("[CifradoServiceImpl] - getCertificados:" + e);
			throw new BusinessException(e,"errors.decode.getCertificados");	
		}
	}
}
