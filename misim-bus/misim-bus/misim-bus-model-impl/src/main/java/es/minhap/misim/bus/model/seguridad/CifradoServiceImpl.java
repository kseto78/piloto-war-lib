package es.minhap.misim.bus.model.seguridad;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

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

import es.minhap.misim.bus.model.exception.ModelException;

@Service(value = "cifradoService")
public class CifradoServiceImpl implements CifradoService {

	/**
	 * Logger slf4j. Para mayor modularidad, se aplica en la interfaz de la API
	 */
	private static final Logger LOG = LoggerFactory.getLogger(CifradoService.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Document cifrar(final Document documento, final String keyStoreType,
			final String keyStorePassword, final String keyStoreAlias,
			final String aliasPassword, final String keyStoreFile,
			final List<Node> nodosAFirmar) throws ModelException {
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("Inicio cifrar....");
			LOG.debug("KeyStore utilizado: " + keyStoreFile);
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
			builder.setSymmetricEncAlgorithm("http://www.w3.org/2001/04/xmlenc#aes128-cbc");
			JCEMapper.registerDefaultAlgorithms();

			final WSSecHeader secHeader = new WSSecHeader();
			secHeader.insertSecurityHeader(documento);
			builder.prepare(documento, crypto);

			final List<WSEncryptionPart> parts = new ArrayList<WSEncryptionPart>();
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
			LOG.error("Cifrado: Error de sistema Cifrado", e);
			throw new ModelException("Error de sistema Cifrado", 502);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Document descifrar(final Document documento,
			final String keyStoreType, final String keyStorePassword,
			final String keyStoreAlias, final String aliasPassword,
			final String keyStoreFile) throws ModelException{
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("Inicio descifrar....");
			LOG.debug("KeyStore utilizado: " + keyStoreFile);
		}
		
		try {
			
			Init.init();
			final Node key = documento.getElementsByTagNameNS(
					"http://www.w3.org/2001/04/xmlenc#", 
					"EncryptedKey").item(0);
			
			if (key == null) {
				return documento;
			}
			final Node keyData = ((Element) key).getElementsByTagNameNS(
					"http://www.w3.org/2001/04/xmlenc#", 
					"CipherData").item(0);

			final Node keyValue = ((Element) keyData).getElementsByTagNameNS(
					"http://www.w3.org/2001/04/xmlenc#", 
					"CipherValue").item(0);

			final String symetricKey = ((Element) keyValue).getTextContent();

			final SecretKeySpec keySpec = new SecretKeySpec(
					Base64.decodeBase64(symetricKey), "AES");

			final Element encryptedDataElement = (Element) documento.getElementsByTagNameNS(
					"http://www.w3.org/2001/04/xmlenc#",
					"EncryptedData").item(0);

			final XMLCipher xmlCipher = XMLCipher.getProviderInstance(
					"http://www.w3.org/2001/04/xmlenc#aes128-cbc", 
					"SunJCE");
			
			xmlCipher.init(2, keySpec);
			xmlCipher.doFinal(documento, encryptedDataElement);

			if (LOG.isDebugEnabled()) {
				LOG.debug("Fin descifrar");
			}
			
			return documento;
			
		} catch (final Exception e) {
			LOG.error("Descifrado: Error de sistema Descifrado", e);
			throw new ModelException("Error de sistema Descifrado", 502);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Document descifrarKey(final Document documento,
			final String keyStoreType, final String keyStorePassword,
			final String keyStoreAlias, final String aliasPassword,
			final String keyStoreFile) throws ModelException {
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("Inicio descifrarKey...");
			LOG.debug("Key store utilizado: " + keyStoreFile);
		}
		
		try {
			
			final NodeList nl = documento.getElementsByTagNameNS(
					"http://www.w3.org/2001/04/xmlenc#", 
					"CipherValue");

			final Node encriptedKey = nl.item(0);

			JCEMapper.registerDefaultAlgorithms();
			
			KeyStore ks;
			if ("PKCS12".equals(keyStoreType)) {
				ks = KeyStore.getInstance(keyStoreType, "SunJSSE");
			} else {
				ks = KeyStore.getInstance(keyStoreType, "SUN");
			}
			
			ks.load(new FileInputStream(keyStoreFile),keyStorePassword.toCharArray());
			
			final KeyStore.PrivateKeyEntry keyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(
					keyStoreAlias, 
					new KeyStore.PasswordProtection(aliasPassword.toCharArray()));

			final PrivateKey privateKey = keyEntry.getPrivateKey();

			final Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding","SunJCE");
			cipher.init(2, privateKey);
			final byte[] encryptedKeyBA = Base64.decodeBase64(encriptedKey.getTextContent());

			final String symetricKey = Base64.encodeBase64String(cipher.doFinal(encryptedKeyBA));
			encriptedKey.setTextContent(symetricKey);
			
			if (LOG.isDebugEnabled()) {
				LOG.debug("Fin descifrarKey...");
			}
			return documento;
			
		} catch (final Throwable e) {
			LOG.error("Descifrado: Error de sistema Descifrado", e);
			throw new ModelException("Error de sistema Descifrado", 502);
		}
	}

}
