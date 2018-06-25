package es.minhap.misim.bus.webapp.serviceprovider;

import javax.annotation.Resource;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Provider;
import javax.xml.ws.WebServiceContext;

import misim.bus.common.bean.SoapPayload;

import org.mule.api.MuleContext;
import org.mule.api.context.MuleContextAware;
import org.mule.module.client.MuleClient;
import org.springframework.context.annotation.Scope;
/**
 * Clase que implementa la logica del servicio WSProvider
 * @author ralberoc
 *
 */
@Scope(value="request")
public class WSProvider implements Provider<SOAPMessage>,
		MuleContextAware {

	protected static final String SOAP_ACTION = "SOAPAction";

	private MuleContext muleContext;
	private MuleClient muleClient;

	@Resource
	private WebServiceContext context;

	private String receptQueue = null;
	private String soapAction = null;
	private SoapPayload<?> payload=null;
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SOAPMessage invoke(final SOAPMessage request) {


		SOAPMessage response = null;

		return response;
	}

	/**
	 * Modifica el contexto de mule
	 */
	@Override
	public void setMuleContext(final MuleContext context) {
		this.muleContext = context;
	}

	/**
	 * obtiene el objeto WebServiceContext
	 * @return
	 */
	public WebServiceContext getContext() {
		return context;
	}

	/**
	 * Obtiene la cadena receptQueue
	 * @return
	 */
	public String getReceptQueue() {
		return receptQueue;
	}

	/**
	 * Obtiene la cadena soapAction
	 * @return
	 */
	public String getSoapAction() {
		return soapAction;
	}

	/**
	 * Modifica el objeto WebServiceContext
	 * @param context
	 */
	public void setContext(WebServiceContext context) {
		this.context = context;
	}

	/**
	 * Obtiene la cadena receptQueue
	 * @param receptQueue
	 */
	public void setReceptQueue(String receptQueue) {
		this.receptQueue = receptQueue;
	}

	/**
	 * Modifica la cadena soapAction
	 * @param soapAction
	 */
	public void setSoapAction(String soapAction) {
		this.soapAction = soapAction;
	}

	/**
	 * Obtiene el objeto SoapPayload
	 * @return
	 */
	public SoapPayload<?> getPayload() {
		return payload;
	}

	/**
	 * Modifica el objeto SoapPayload
	 * @param payload
	 */
	public void setPayload(SoapPayload<?> payload) {
		this.payload = payload;
	}

	/**
	 * obtiene el objeto MuleContext
	 * @return
	 */
	public MuleContext getMuleContext() {
		return muleContext;
	}

	/**
	 * Modifica el objeto MuleClient
	 * @return
	 */
	public MuleClient getMuleClient() {
		return muleClient;
	}

	/**
	 * Modifica el objeto MuleClient
	 * @param muleClient
	 */
	public void setMuleClient(MuleClient muleClient) {
		this.muleClient = muleClient;
	}

}
