package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import es.minhap.plataformamensajeria.iop.beans.GestionEnvioBean;
import es.minhap.sim.model.TblGestionEnvios;
import es.minhap.sim.model.ViewEstadoLotesEnvios;
import es.minhap.sim.model.ViewGestionEnviosDestId;

/**
 * 
 * 
 * @author everis
 *
 */
public interface QueryExecutorGestionEnvios {
	
	/**
	 * Obtiene el estado de los lotes de envíos
	 * 
	 * @return
	 */
	public List<ViewEstadoLotesEnvios> getUltimosEstadoEnviosLotes();
	
	/**
	 * Obtiene el listado según parametros
	 * 
	 * @param servicioId
	 * @param year
	 * @param month
	 * @param columna
	 * @return
	 */
	public List<TblGestionEnvios> getInformesServiciosBy(Long servicioId, Integer year, Integer month, String columna);

	/**
	 * Obtiene el total según eg
	 * 
	 * @param servicioId
	 * @return
	 */
	public Integer countGestionEnviosLotes(es.minhap.plataformamensajeria.iop.beans.GestionEnvioBean eg);
	
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
	public List<TblGestionEnvios> getGestionEnvioLotesPaginado(Integer inicio, Integer pagesize, String order, String column,
			es.minhap.plataformamensajeria.iop.beans.GestionEnvioBean criterio);

	/**
	 * Obtiene el total según eg
	 * 
	 * @param eg
	 * @return
	 */
	public Integer countGestionEnviosMensajes(es.minhap.plataformamensajeria.iop.beans.GestionEnvioBean eg);

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
	public List<TblGestionEnvios> getGestionEnvioMensajesPaginado(int inicio, Integer pagesize, String order,
			String column, es.minhap.plataformamensajeria.iop.beans.GestionEnvioBean criterio);

	
	/**
	 * Obtiene el total según eg
	 * 
	 * @param eg
	 * @return
	 */
	Integer countGestionEnviosDestinatarios(GestionEnvioBean eg);

	
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
	List<ViewGestionEnviosDestId> getGestionEnvioDestinatariosPaginado(Integer inicio, Integer pagesize, String order,
			String column, GestionEnvioBean criterio);
}
