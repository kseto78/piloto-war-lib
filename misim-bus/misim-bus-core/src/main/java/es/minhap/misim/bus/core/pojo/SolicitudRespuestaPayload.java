package es.minhap.misim.bus.core.pojo;

import java.util.List;

import misim.bus.common.bean.SoapPayload;
import es.redsara.intermediacion.scsp.esquemas.v3.solicitudrespuesta.SolicitudRespuesta;

public class SolicitudRespuestaPayload extends SoapPayload<SolicitudRespuesta> {

	/**
	 * Serialization
	 */
	private static final long serialVersionUID = -3381119453685860404L;

	@Override
	public List<SoapPayload<SolicitudRespuesta>> getSubQueries(final String encoding) throws Exception {
		return null;
	}

}
