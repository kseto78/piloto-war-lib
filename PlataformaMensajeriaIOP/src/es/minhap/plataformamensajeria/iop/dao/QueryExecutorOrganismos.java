package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.minhap.sim.model.TblOrganismos;
/**
 * 
 * @author everis
 *
 */
public interface QueryExecutorOrganismos {

	/**
	 * Obtiene si el organismo esta activo en el servicio
	 * @param servicioId
	 * @param organismoPagador
	 * @return
	 */
	@Transactional
	public boolean organismoActivoEnServicio(Integer servicioId,String organismoPagador);
	
	/**
	 * Comprueba si para ese servicio esta activa la aplicacion
	 * @param servicioId
	 * @return
	 */
	@Transactional
	public Integer checkActiveApplication(Integer servicioId);

	/**
	 * Obtiene si el organismo esta asociado al servicio
	 * @param servicioId
	 * @param organismoPagador
	 * @return
	 */
	boolean asociadoOrganismoServicio(Integer servicioId, String organismoPagador);

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
	public List<TblOrganismos> getOrganismosPaginado(int start, int size, String order, String column,
			es.minhap.plataformamensajeria.iop.beans.OrganismoBean ob);
	
	/**
	 * Obtiene el total según ob
	 * 
	 * @param ob
	 * @return
	 */
	public Integer countOrganismosPaginado(es.minhap.plataformamensajeria.iop.beans.OrganismoBean ob);
	
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
	public Integer getOrganismoIdByDir3(String search);

	public List<TblOrganismos> getOrganismosHijos(String idOrganismo);

	public Integer getOrganismoIdByDir3SoloEliminado(String search);

	public List<TblOrganismos> getOrganismosByPdp(long idPdpDiputaciones);

}
