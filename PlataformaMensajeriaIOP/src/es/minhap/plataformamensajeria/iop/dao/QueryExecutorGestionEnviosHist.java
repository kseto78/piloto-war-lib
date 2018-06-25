package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import es.minhap.sim.model.TblGestionEnviosHist;
import es.minhap.sim.model.ViewGestionEnviosDestHistId;

/**
 * 
 * 
 * @author everis
 *
 */
public interface QueryExecutorGestionEnviosHist {
	
		
	/**
	 * Obtiene el listado según parametros
	 * 
	 * @param servicioId
	 * @param year
	 * @param month
	 * @param columna
	 * @return
	 */
	public List<TblGestionEnviosHist> getInformesServiciosBy(Long servicioId, Integer year, Integer month, String columna);
	
	/**
	 * Obtiene lista de TblGestionEnviosHist a partir de la lista de mensajes en TblGestionEnvios
	 * @param subList
	 * @return 
	 */
	public List<TblGestionEnviosHist> convertGestionEnviosTOGestionEnviosHist(List<Long> subList);

	/**
	 * Obtiene lista de TblGestionEnviosHist a partir de la lista de mensajes
	 * @param listaMensajes
	 * @return 
	 */
	public List<TblGestionEnviosHist> getTodosGestionEnviosCons(List<Long> listaMensajes);

	/**
	 * Obtiene el total según eg
	 * 
	 * @param servicioId
	 * @return
	 */
	public Integer countGestionEnviosHistoricoLotes(
			es.minhap.plataformamensajeria.iop.beans.GestionEnvioHistoricoBean eg);

	/**
	 * Obtiene el total según eg
	 * 
	 * @param eg
	 * @return
	 */
	public Integer countGestionEnviosHistorico(es.minhap.plataformamensajeria.iop.beans.GestionEnvioHistoricoBean eg);

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
	public List<TblGestionEnviosHist> getGestionEnvioLotesPaginado(int inicio, Integer pagesize, String order,
			String column, es.minhap.plataformamensajeria.iop.beans.GestionEnvioHistoricoBean eg);

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
	public List<TblGestionEnviosHist> getGestionEnvioMensajesPaginado(int inicio, Integer pagesize, String order,
			String column, es.minhap.plataformamensajeria.iop.beans.GestionEnvioHistoricoBean eg);

	/**
	 * Obtiene el total según eg
	 * 
	 * @param eg
	 * @return
	 */
	public Integer countGestionEnviosHistDestinatarios(
			es.minhap.plataformamensajeria.iop.beans.GestionEnvioHistoricoBean eg);

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
	public List<ViewGestionEnviosDestHistId> getGestionEnvioDestinatariosHistPaginado(int inicio, Integer pagesize,
			String order, String column, es.minhap.plataformamensajeria.iop.beans.GestionEnvioHistoricoBean eg);
}
