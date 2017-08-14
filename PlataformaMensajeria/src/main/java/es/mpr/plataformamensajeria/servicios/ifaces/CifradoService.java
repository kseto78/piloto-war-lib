package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.util.KeyValueObject;

import es.mpr.plataformamensajeria.beans.CertificadoBean;

/**
 * Servicio para cifrar/descifrar un documento XML de comunicación SOAP según el
 * mecanismo de cifrado siguiente :<br>
 * <ul>
 * <li>1) El organismo que deba cifrar la información, generará una clave
 * simétrica utilizando el algoritmo AES 128. (Las implementaciones deberán
 * soportar longitudes mayores para cuando se recomiende el cambio por motivos
 * de seguridad, por ejemplo AES 256) <br/>
 * Algorithm= http://www.w3.org/2001/04/xmlenc#aes128-cbc</li>
 * <li>2) Se cifrará el contenido del nodo datos específicos en el caso de
 * peticiones síncronas. En la peticiones/respuestas asíncronas (usadas
 * generalmente para una petición con múltiples solicitudes) se cifrará el nodo
 * Solicitudes/Transmisiones.</li>
 * <li>3) El organismo cifrará la clave simétrica anteriormente generada,
 * utilizando el algoritmo asimétrico rsa, para garantizar la confidencialidad
 * hacia el solicitante de la información. Para ello utilizará la clave pública
 * extraída del certificado con el que se firmó la petición. No se soportará
 * enviar otra información adicional no relacionada con el requirente. <br/>
 * El algoritmo utilizado sera: Algorithm=
 * http://www.w3.org/2001/04/xmlenc#rsa-1_5</li>
 * <li>4) Se compondrán los tokens de seguridad necesarios y el mensaje según el
 * formato acordado como se describe a continuación.</li>
 * <li>5) Una vez cifrado el mensaje, es cuándo se procede a la firma del mismo
 * (y no antes). De esta forma se garantiza la integridad de la transmisión y la
 * confidencialidad extremo a extremo de la misma.</li>
 * </ul>
 * 
 * @author ludarcos
 * 
 */
public interface CifradoService {

	/**
	 * Descifra el documento. El documento original no se modificará, se
	 * devolverá una nueva instancia de documento descifrado
	 * 
	 * @param documento
	 *            Documento XML que descifrar
	 * @param keyStoreType
	 *            Tipo de keystore
	 * @param keyStorePassword
	 *            Contraseña de keystore
	 * @param keyStoreAlias
	 *            Alias de keystore
	 * @param aliasPassword
	 *            Contraseña de keystore
	 * @param keyStoreFile
	 *            Ruta del keystore
	 * @return El documento descifrado
	 * @throws Exception
	 */
	Document descifrar(final Document documento, final String keyStoreType,
			final String keyStorePassword, final String keyStoreAlias,
			final String aliasPassword, final String keyStoreFile)
			throws Exception;

	/**
	 * Descifra la clave pública.
	 * 
	 * @param documento
	 *            Documento XML que descifrar
	 * @param keyStoreType
	 *            Tipo de keystore
	 * @param keyStorePassword
	 *            Contraseña de keystore
	 * @param keyStoreAlias
	 *            Alias de keystore
	 * @param aliasPassword
	 *            Contraseña de keystore
	 * @param keyStoreFile
	 *            Ruta del keystore
	 * @return
	 * @throws Exception
	 */
	Document descifrarKey(final Document documento, final String keyStoreType,
			final String keyStorePassword, final String keyStoreAlias,
			final String aliasPassword, final String keyStoreFile)
			throws Exception;
	
	/**
	 * Cifra el documento. El documento original no se modificará, se devolverá
	 * una nueva instancia de documento cifrado
	 * 
	 * @param documento
	 *            Documento XML que cifrar
	 * @param keyStoreType
	 *            Tipo de keystore
	 * @param keyStorePassword
	 *            Contraseña de keystore
	 * @param keyStoreAlias
	 *            Alias de keystore
	 * @param aliasPassword
	 *            Contraseña del alias
	 * @param keyStoreFile
	 *            Ruta del keystore
	 * @param nodosAFirmar
	 *            Lista de nodos que cifrar
	 * @return Un documento cuyos nodos espcificado han sido cifrados
	 */
	Document cifrar(final Document documento, final String keyStoreType,
			final String keyStorePassword, final String keyStoreAlias,
			final String aliasPassword, final String keyStoreFile,
			final List<Node> nodosAFirmar) throws Exception;
	
	
	public List<KeyValueObject> getCertificados() throws BusinessException;
	
}
