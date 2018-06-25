package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblTiposParametros;


public interface TblTiposParametrosManager {

	/**
	 * Obtiene la lista de tipos parametros por tipo y activo
	 * 
	 * @param tipo
	 * @return 
	 */
	public List<TblTiposParametros> listaTiposParametrosPorTipo(Integer tipo);
	
	/**
	 * Obtiene el tipoParametro por su id
	 * 
	 * @param tipoParametroId
	 * @return 
	 */
	public TblTiposParametros getTipoParametroById(Long tipoParametroId);
}
