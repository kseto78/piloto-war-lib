package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.minhap.sim.model.TblPdpDiputaciones;




/**
 * 
 * @author everis
 *
 */
public interface QueryExecutorPdpDiputaciones {

	
	/**
	 * Obtiene el listado según parametros
	 * 
	 * @param inicio
	 * @param pagesize
	 * @param order
	 * @param column
	 * @param criterio
	 * @return
	 */
	public List<TblPdpDiputaciones> getPdpDiputacionesPaginado(int start, int size, String order, String column,
			es.minhap.plataformamensajeria.iop.beans.PdpDiputacionesBean ob);
	
	/**
	 * Obtiene el total según ob
	 * 
	 * @param ob
	 * @return
	 */
	public Integer countPdpDiputacionesPaginado(es.minhap.plataformamensajeria.iop.beans.PdpDiputacionesBean ob);
	
	/**
	 * Obtiene el listado
	 * 
	 * @param term
	 * @return List<String>
	 */
	public List<String> getListAutocomplete(String term);

	/**
	 * Obtiene id
	 * 
	 * @param search
	 * @return Integer
	 */

	public List<String> getOrganismosHijos(String idOrganismo);

}
