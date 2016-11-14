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
	
	public WSProvider(){}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SOAPMessage invoke(final SOAPMessage request) {


		SOAPMessage response = null;

		return response;
	}

	@Override
	public void setMuleContext(final MuleContext context) {
		this.muleContext = context;
	}


	public WebServiceContext getContext() {
		return context;
	}

	public String getReceptQueue() {
		return receptQueue;
	}

	public String getSoapAction() {
		return soapAction;
	}

	public void setContext(WebServiceContext context) {
		this.context = context;
	}

	public void setReceptQueue(String receptQueue) {
		this.receptQueue = receptQueue;
	}

	public void setSoapAction(String soapAction) {
		this.soapAction = soapAction;
	}

	public SoapPayload<?> getPayload() {
		return payload;
	}

	public void setPayload(SoapPayload<?> payload) {
		this.payload = payload;
	}

	public MuleContext getMuleContext() {
		return muleContext;
	}

	public MuleClient getMuleClient() {
		return muleClient;
	}

	public void setMuleClient(MuleClient muleClient) {
		this.muleClient = muleClient;
	}

}
