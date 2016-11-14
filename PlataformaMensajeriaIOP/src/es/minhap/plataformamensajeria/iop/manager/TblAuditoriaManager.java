package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.plataformamensajeria.iop.beans.entity.AuditoriaBean;

public interface TblAuditoriaManager {

	/**
	 * Inserta auditoria con los datos necesarios
	 * 
	 * @param auditoriaBean
	 * @return
	 */
	Integer insertarAuditoria(AuditoriaBean auditoria);

}
