package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblCertificados;

public interface TblCertificadosManager {


	/**
	 * Comprueba si existe el certificado por el servicio
	 * 
	 * @param query
	 * @return 
	 */
	
	TblCertificados getCertificadosByServicio(Long servicioId);

		
}
