package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.minhap.sim.model.TblOrganismos;
import es.minhap.sim.model.TblOrganismosPdp;
/**
 * 
 * @author everis
 *
 */
public interface QueryExecutorOrganismosPdp {

	
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
	public List<TblOrganismosPdp> getOrganismosPdpPaginado(int start, int size, String order, String column,
			es.minhap.plataformamensajeria.iop.beans.OrganismoPdpBean ob);
	
	/**
	 * Obtiene el total según ob
	 * 
	 * @param ob
	 * @return
	 */
	public Integer countOrganismosPdpPaginado(es.minhap.plataformamensajeria.iop.beans.OrganismoPdpBean ob);
	
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
