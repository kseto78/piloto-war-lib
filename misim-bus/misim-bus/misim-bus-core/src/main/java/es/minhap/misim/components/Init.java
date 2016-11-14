package es.minhap.misim.components;

import java.nio.charset.Charset;

import misim.bus.common.util.XMLUtils;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;

public class Init implements Callable {

	@Override
	public Object onCall(final MuleEventContext eventContext) throws Exception {
		final String xml = eventContext.getMessageAsString();
		eventContext.getMessage().setPayload(XMLUtils.xml2doc(xml, Charset.forName("UTF-8")));
		return eventContext.getMessage();
	}

}
