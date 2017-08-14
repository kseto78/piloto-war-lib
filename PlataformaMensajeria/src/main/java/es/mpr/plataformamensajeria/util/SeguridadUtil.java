package es.mpr.plataformamensajeria.util;

import java.util.Properties;

public class SeguridadUtil {
	
	/**
	 * Initialize las propiedades del sistema criptográfico
	 * 
	 * @param keyStoreType
	 *            Tipo de keyStore
	 * @param keyStorePassword
	 *            Contraseña del keyStore
	 * @param keyStoreAlias
	 *            Alias del keyStore
	 * @param aliasPassword
	 *            Contraseña del alias del keyStore
	 * @param keyStoreFile
	 *            Ruta del fichero de keyStore
	 * @return un objeto {@link Properties} initializado para WS-Security y
	 *         Apache WSS4J Merlin
	 */
	public static Properties initializateCryptoProperties(
			final String keyStoreType, final String keyStorePassword,
			final String keyStoreAlias, final String aliasPassword,
			final String keyStoreFile) {
		Properties props = new Properties();
		props.setProperty("org.apache.ws.security.crypto.provider",
				"org.apache.ws.security.components.crypto.Merlin");
		props.setProperty("org.apache.ws.security.crypto.merlin.keystore.type",
				keyStoreType);
		props.setProperty(
				"org.apache.ws.security.crypto.merlin.keystore.password",
				keyStorePassword);
		props.setProperty(
				"org.apache.ws.security.crypto.merlin.keystore.alias",
				keyStoreAlias);
		props.setProperty(
				"org.apache.ws.security.crypto.merlin.keystore.private.password",
				aliasPassword);
		props.setProperty(
				"org.apache.ws.security.crypto.merlin.keystore.provider", "SUN");
		props.setProperty("org.apache.ws.security.crypto.merlin.file",
				keyStoreFile);
		return props;
	}
}
