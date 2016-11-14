package es.minhap.misim.bus.model.util;

import java.security.Key;
import java.security.PublicKey;

import javax.xml.crypto.AlgorithmMethod;
import javax.xml.crypto.KeySelector;
import javax.xml.crypto.KeySelectorException;
import javax.xml.crypto.KeySelectorResult;
import javax.xml.crypto.XMLCryptoContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;

public class X509KeySelectorPublicKey extends KeySelector {

	PublicKey clavePublica;

	/**
	 * Constructor
	 * 
	 * @param clavePublica
	 *            Clave Pública
	 */
	public X509KeySelectorPublicKey(final PublicKey clavePublica) {
		this.clavePublica = clavePublica;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public KeySelectorResult select(KeyInfo keyInfo, Purpose purpose,
			AlgorithmMethod method, XMLCryptoContext context)
			throws KeySelectorException {
		return new KeySelectorResult() {

			@Override
			public Key getKey() {
				return X509KeySelectorPublicKey.this.clavePublica;
			}
		};
	}

}
