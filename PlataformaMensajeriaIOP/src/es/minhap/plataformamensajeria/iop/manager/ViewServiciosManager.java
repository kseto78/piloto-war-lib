package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.ViewServicios;
import es.minhap.sim.query.ViewServiciosQuery;

public interface ViewServiciosManager {

	/**
	 * Se obtiene la aplicacion de la vista
	 * 
	 * @param ViewServiciosQuery
	 * @return ViewServicios
	 */
	public ViewServicios getAplicacionId(ViewServiciosQuery query);
	
	
	public List<ViewServicios> getServicios();
	
	/**
	 * Se obtiene la lista de servicios por aplicacion
	 * 
	 * @param aplicacionId
	 * @return
	 */
	public List<ViewServicios> getServiciosByAplicacion(Long aplicacionId);
	
	/**
	 * la lista con los servicios
	 * 
	 * @param start
	 * @param size
	 * @param order
	 * @param columnSort
	 * @param nombreServicio
	 * @param aplicacionId
	 * @return
	 */
	public List<ViewServicios> getServiciosPaginado(int start, int size, String order, String columnSort, String nombreServicio, Long aplicacionId, int servicioId, boolean count);

	/**
	 * la lista con los canales
	 * 
	 * idServicio 
	 * @return
	 */
	public List<ViewServicios> getCanalesByServicioId(String idServicio);

	public List<ViewServicios> getServiciosPorCanal(String idAplicacion, String idCanal);

	
}
