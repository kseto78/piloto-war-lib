package es.minhap.plataformamensajeria.iop.manager;

import es.minhap.sim.model.TblProcesoInformesServicios;

public interface TblProcesoInformesServiciosManager {

	/**
	 * Inserta auditoria con los datos necesarios
	 * 
	 * @param pis
	 * @return
	 */
	Long insertar(TblProcesoInformesServicios pis);

}
