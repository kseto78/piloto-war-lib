package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblServidoresOrganismos;
import es.minhap.sim.query.TblServidoresOrganismosQuery;

public interface TblServidoresOrganismosManager {

	/**
	 * Obtiene los Servidores-Organismos by query
	 * 
	 * @param query
	 * @return lista de TblServidoresOrganismos
	 */
	List<TblServidoresOrganismos> getServidoresOrganismosByQuery(TblServidoresOrganismosQuery query);

	/**
	 * Obtiene el servidor organismo por Id
	 * 
	 * @param servidorOrganismoId
	 * @return TblServidoresOrganismos
	 */
	TblServidoresOrganismos getServidoresOrganismosById(Long servidorOrganismoId);

	/**
	 * Insertamos un nuevo servicio
	 *
	 * @param soTO
	 * @param source
	 * @param accion
	 * @param accionId
	 * @param descripcion
	 * @return Long
	 */
	Long insert(TblServidoresOrganismos soTO, String source, String accion, Long accionId, String descripcion);

	/**
	 * Insertamos un nuevo servicio
	 *
	 * @param servidorOrganismoId
	 * @param source
	 * @param accion
	 * @param accionId
	 * @param descripcion
	 */
	void delete(Long servidorOrganismoId, String source, String accion, Long accionId, String descripcion);

	
}
