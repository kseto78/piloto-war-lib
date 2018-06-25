package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.plataformamensajeria.iop.beans.OrganismosServicioBean;
import es.minhap.sim.model.TblOrganismosServicio;
import es.minhap.sim.query.TblOrganismosServicioQuery;

public interface TblOrganismosServicioManager {

	/**
	 * Obtiene los organismos pertenecientes por query
	 * 
	 * @param query
	 * @return lista de organismosServiciosBean
	 */
	public List<OrganismosServicioBean> getOrganismosServicioByQuery(TblOrganismosServicioQuery query);

	/**
	 * inserta un nuevo ServicioOrganismo
	 * 
	 * @param servicioOrganismosTO
	 * @param source
	 * @param accion
	 * @param accionId
	 * @param descripcion
	 * @return id
	 */
	public Long insert(TblOrganismosServicio servicioOrganismosTO, String source, String accion, Long accionId, String descripcion);

	/**
	 * obtiene el organismoServicio por Id
	 * 
	 * @param servicioOrganismoId
	 * @return TblOrganismosServicio
	 */
	public OrganismosServicioBean getOrganismoServicioById(Integer servicioOrganismoId);

	/**
	 * Elimina el organismoServicio por Id
	 * 
	 * @param servicioOrganismoId
	 */
	public void delete(Integer servicioOrganismoId, String source, String accion, Long accionId, String descripcion);


	
}
