package es.minhap.misim.bus.model.seguridad;

import org.w3c.dom.Document;

import es.minhap.misim.bus.model.exception.ModelException;

/**
 * API FirmaService : Proporciona los métodos necesarios a la gestión de la
 * firma en el MICC
 * 
 * @author ludarcos
 * 
 */
public interface FirmaService {

	static final String ELEMENT_ID = "Id";
	static final String WSSECURITY_XSD = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";
	static final String WSS_CANONICALIZATION = "http://www.w3.org/2001/10/xml-exc-c14n#";
	

	/**
	 * Firma un documento XML contra WS-Security. Los algoritmos son los
	 * siguientes : <br/>
	 * <ul>
	 * <li>DigestMethod = http://www.w3.org/2000/09/xmldsig#sha1</li>
	 * <li>SignatureMethod = http://www.w3.org/2000/09/xmldsig#rsa-sha1</li>
	 * <li>CanonicalizationMethod = http://www.w3.org/2001/10/xml-exc-c14n#</li>
	 * </ul>
	 * 
	 * @param documento
	 *            - Documento sin firmar
	 * @param keyStoreType
	 *            - Tipo de keyStore
	 * @param keyStorePassword
	 *            - Contraseña del keyStore
	 * @param keyStoreAlias
	 *            - Alias del keyStore
	 * @param aliasPassword
	 *            - Contraseña del alias
	 * @param keyStoreFile
	 *            - Ruta del keyStore
	 * @return Un {@link Document} firmado
	 * @throws ModelException
	 */
	public Document firmarWSSecurity(Document documento, String keyStoreType,
			String keyStorePassword, String keyStoreAlias,
			String aliasPassword, String keyStoreFile) throws ModelException;

	/**
	 * Firma un documento XML contra XML Digital Signature. Los esquemas y
	 * algoritmos son los siguientes : <br/>
	 * <ul>
	 * <li>XMLDSig = http://www.w3.org/TR/xmldsig-core/</li>
	 * <li>Transformación = http://www.w3.org/2001/10/xml-exc-c14n</li>
	 * <li>CanonicalizationMethod = http://www.w3.org/2001/10/xml-exc-c14n</li>
	 * </ul>
	 * 
	 * @param documento
	 *            - Documento sin firmar
	 * @param keyStoreType
	 *            - Tipo de keyStore
	 * @param keyStorePassword
	 *            - Contraseña del keyStore
	 * @param keyStoreAlias
	 *            - Alias del keyStore
	 * @param aliasPassword
	 *            - Contraseña del alias
	 * @param keyStoreFile
	 *            - Ruta del keyStore
	 * @return Un {@link Document} firmado
	 * @throws ModelException
	 */
	public Document firmarXMLDSign(Document documento, String keyStoreType,
			String keyStorePassword, String keyStoreAlias,
			String aliasPassword, String keyStoreFile) throws ModelException;

	/**
	 * Valida la firma de un documento XML contra WS-Security - Los algoritmos
	 * son los siguientes : <br/>
	 * <ul>
	 * <li>DigestMethod = http://www.w3.org/2000/09/xmldsig#sha1</li>
	 * <li>SignatureMethod = http://www.w3.org/2000/09/xmldsig#rsa-sha1</li>
	 * <li>CanonicalizationMethod = http://www.w3.org/2001/10/xml-exc-c14n#</li>
	 * </ul>
	 * 
	 * @param documento
	 *            Documento XML firmado contra WS-Security
	 * @return true si se verifica el documento contra firma, false en cualquier
	 *         otro caso
	 * @throws ModelException
	 */
	public boolean validarFirmaWSSecurity(Document documento)
			throws ModelException;

	/**
	 * Valida la firma de un documento XML contra XML Digital Signature - Los
	 * esquemas y algoritmos son los siguientes : <br/>
	 * <ul>
	 * <li>XMLDSig = http://www.w3.org/TR/xmldsig-core/</li>
	 * <li>Transformación = http://www.w3.org/2001/10/xml-exc-c14n</li>
	 * <li>CanonicalizationMethod = http://www.w3.org/2001/10/xml-exc-c14n</li>
	 * </ul>
	 * 
	 * @param documento
	 *            Documento XML firmado contra XML Digital Signature
	 * @return true si se verifica el documento contra firma, false en cualquier
	 *         otro caso
	 * @throws ModelException
	 */
	public boolean validarFirmaXMLDSign(Document documento)
			throws ModelException;
	
	/**
	 * Valida si dos certificados son iguales
	 * 
	 * @param certificado1
	 * @param certificado2
	 * @return true si se verifica que son iguales, 
	 * 		   false en cualquier otro caso
	 * @throws ModelException
	 */
	public boolean compararCertificados(byte[] certificado1, byte[] certificado2)
			throws ModelException;
}
