package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.minhap.sim.model.TblOrganismos;
import es.minhap.sim.query.TblOrganismosQuery;
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
	@Transactional boolean organismoActivoEnServicio(Integer servicioId,String organismoPagador);
	
	/**
	 * Comprueba si para ese servicio esta activa la aplicacion
	 * @param servicioId
	 * @return
	 */
	@Transactional Integer checkActiveApplication(Integer servicioId);

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
	List<TblOrganismos> getOrganismosPaginado(int start, int size, String order, String column,
			es.minhap.plataformamensajeria.iop.beans.OrganismoBean ob);
	
	/**
	 * Obtiene el total según ob
	 * 
	 * @param ob
	 * @return
	 */
	Integer countOrganismosPaginado(es.minhap.plataformamensajeria.iop.beans.OrganismoBean ob);
	
	/**
	 * Obtiene el listado
	 * 
	 * @param term
	 * @return List<String>
	 */
	List<String> getListAutocomplete(String term);

	/**
	 * Obtiene id
	 * 
	 * @param search
	 * @return Integer
	 */
	Integer getOrganismoIdByDir3(String search);

	List<TblOrganismos> getOrganismosHijos(String idOrganismo);

	Integer getOrganismoIdByDir3SoloEliminado(String search);

	List<TblOrganismos> getOrganismosByPdp(long idPdpDiputaciones);
	
	Integer countServidoresPaginadoOrganismos(TblOrganismosQuery queryOrg);

}
