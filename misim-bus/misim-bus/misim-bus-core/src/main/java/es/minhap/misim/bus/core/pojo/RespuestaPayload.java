package es.minhap.misim.bus.core.pojo;

import java.util.List;

import es.redsara.intermediacion.scsp.esquemas.v3.respuesta.Respuesta;
import misim.bus.common.bean.SoapPayload;

public class RespuestaPayload extends SoapPayload<Respuesta> {

	/**
	 * Serializable
	 */
	private static final long serialVersionUID = 1763889570646191759L;

	@Override
	public List<SoapPayload<Respuesta>> getSubQueries(String encoding)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
