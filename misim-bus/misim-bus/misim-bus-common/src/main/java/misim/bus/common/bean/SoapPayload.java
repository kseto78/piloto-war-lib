package misim.bus.common.bean;

import java.io.Serializable;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class SoapPayload<P> implements Serializable {

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = 1680035718626095801L;

	private String soapAction;
	private Document soapMessage;
	private Boolean encrypted = Boolean.FALSE;

	/**
	 * Cuenta el número de elementos
	 * 
	 * @param namespaceURI
	 *            URI del namespace del elemento
	 * @param localName
	 *            nombre local (sin namespace) de los elementos que contar
	 * @return el número de elementos encontrados
	 */
	public int countElementsByName(final String namespaceURI,
			final String localName) {
		int numElements = 0;
		final NodeList elements = soapMessage.getElementsByTagNameNS(
				namespaceURI, localName);
		if (elements != null) {
			numElements = elements.getLength();
		}
		return numElements;
	}

	public Boolean getEncrypted() {
		return encrypted;
	}

	public String getSoapAction() {
		return soapAction;
	}

	public Document getSoapMessage() {
		return soapMessage;
	}

	public List<SoapPayload<P>> getSubQueries(final String encoding)
			throws Exception {
		return null;
	}

	public void setSoapAction(final String soapAction) {
		this.soapAction = soapAction;
	}

	public void setSoapMessage(final Document soapMessage) {
		this.soapMessage = soapMessage;

		final NodeList cipherValue = soapMessage.getElementsByTagNameNS(
				"http://www.w3.org/2001/04/xmlenc#", "CipherValue");
		if ((cipherValue != null) && (cipherValue.getLength() > 0)) {
			encrypted = Boolean.TRUE;
		}
	}
}
