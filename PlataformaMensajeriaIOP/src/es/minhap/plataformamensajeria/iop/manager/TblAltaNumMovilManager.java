package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblAltaNumMovil;
import es.minhap.sim.query.TblAltaNumMovilQuery;

public interface TblAltaNumMovilManager {

	/**
	 * Obtiene el elemento de la tabla
	 * 
	 * @param query
	 * @return TblAltaNumMovil
	 */
	TblAltaNumMovil getTblAltaNumMovilByQuery(TblAltaNumMovilQuery query);

	/**
	 * ACtualiza el registro
	 * 
	 * @param tblAltaNumMovil
	 */
	void actualizar(TblAltaNumMovil tblAltaNumMovil);

	/**
	 * Inserta el registro
	 * 
	 * @param tblAltaNumMovil
	 * @return
	 */
	Long insertar(TblAltaNumMovil alta);

	/**
	 * Elimina el registro
	 * 
	 * @param idAltaNumMovil
	 */
	void eliminar(Long idAltaNumMovil);

	/**
	 * Listado entradas que cumples la caracteristica
	 * 
	 * @param query
	 * @return
	 */
	List<Long> getListaIdTblAltaNumMovilByQuery(TblAltaNumMovilQuery query);
	
	

}
