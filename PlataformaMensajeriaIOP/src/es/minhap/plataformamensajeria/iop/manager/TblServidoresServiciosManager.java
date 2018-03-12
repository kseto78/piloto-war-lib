package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.plataformamensajeria.iop.beans.TblServidoresServiciosBean;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.model.TblServidoresServicios;
import es.minhap.sim.query.TblServidoresServiciosQuery;

public interface TblServidoresServiciosManager {

	
	/**
	 * recupera el el valos del contador según datos pasados
	 * 
	 * @param TblServidoresServiciosQuery
	 * @return Integer
	 */
	public Integer countServidoresServicios(TblServidoresServiciosQuery query);

	/**
	 * 
	 * @param servicioid
	 * @return
	 */
	public TblServidoresServicios getServidoresServicio(TblServicios servicio);
	
	/**
	 * 
	 * @param servicioId
	 * @return
	 */
	public List<Long> getServidoresFromServidoresServiciosByServicio(Long servicioId);

	
	/**
	 * Obtiene los servidores segun query
	 * 
	 * @param query
	 * @return lista de servidoresServiciosBean
	 */
	public List<TblServidoresServiciosBean> getServidoresServicioByQuery(TblServidoresServiciosQuery query);

	
	/**
	 * Inserta en la tabla servidores servicios
	 * @param servidorServicio
	 * @param source
	 * @param accion
	 * @param accionId
	 * @param descricpion
	 * @return 
	 */
	public void insert(TblServidoresServicios servidorServicio, String source, String accion, Long accionId,
			String descripcion);


	/**
	 * borra en la tabla parametrosServidor
	 * @param servidorServicioId
	 * @param source
	 * @param accion
	 * @param accionId
	 * @param descripcion
	 */
	public void delete(Long servidorServicioId, String source, String accion, Long accionId, String descripcion);
}
