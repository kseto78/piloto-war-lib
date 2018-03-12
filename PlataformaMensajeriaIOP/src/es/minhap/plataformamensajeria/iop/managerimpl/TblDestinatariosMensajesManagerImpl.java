package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.entity.TextComparator;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorDestinatariosMensajes;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblEstadosManager;
import es.minhap.plataformamensajeria.iop.manager.TblHistoricosManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.sim.dao.TblDestinatariosMensajesDAO;
import es.minhap.sim.model.TblDestinatariosMensajes;
import es.minhap.sim.query.TblDestinatariosMensajesQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblDestinatariosMensajesManagerImpl")
public class TblDestinatariosMensajesManagerImpl implements TblDestinatariosMensajesManager {
	
	@Resource 
	private TblDestinatariosMensajesDAO destinatariosMensajesDAO;
	
	@Resource 
	private TblEstadosManager estadosManager;
	
	@Resource
	private TblHistoricosManager historicosManager;
	
	@Resource
	private TblMensajesManager mensajesManager;
	
	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	
	@Autowired
	private QueryExecutorDestinatariosMensajes queryExecutorDestinatariosMensajes;
	/**
	 * @see es.minhap.TblDestinatariosMensajesManager.insertarDestinatarioMensaje
	 */
	@Override
	@Transactional
	public Long insertarDestinatarioMensaje(String mensajeId,String destinatario, String idExterno, String usuario){
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		
		//insertamos el destinatarioMensaje
		TblDestinatariosMensajes destinatariosMensajes = getDestinatariosMensajes(idExterno, destinatario,
				usuario, ps, Long.parseLong(mensajeId));

		// insertamos destinatario del mensaje
		return insertar(destinatariosMensajes);
	}

	/**
	 * @see es.minhap.TblDestinatariosMensajesManager.insertarDestinatarioMensajeEmail
	 */
	@Override
	@Transactional
	public List<Long> insertarDestinatarioMensajeEmail(String mensajeId, List<Long> destinatarios, String idExterno,
			String usuario, Long estadoId) {
		List<Long> res = new ArrayList<>();
		for (Long d : destinatarios) {
			res.add(insertarDestinatarioMensaje(mensajeId, d.toString(), idExterno, usuario));
			
		}
		return res;
	}


	@Override
	@Transactional
	public List<TblDestinatariosMensajes> getDestinatarioMensajes(Long mensajeId) {
		TblDestinatariosMensajesQuery query = new TblDestinatariosMensajesQuery();
		query.setMensajeid(mensajeId);
		return destinatariosMensajesDAO.search(query).getResults();
	}
	
	@Override
	@Transactional
	public List<TblDestinatariosMensajes> getDestinatarioMensajesPendientes(Long mensajeid, String estadoPendiente) {
		TblDestinatariosMensajesQuery query = new TblDestinatariosMensajesQuery();
		query.setMensajeid(mensajeid);
		query.setEstado(estadoPendiente);
		return destinatariosMensajesDAO.search(query).getResults();
	}

	@Override
	@Transactional
	public TblDestinatariosMensajes getDestinatarioMensaje(Long destinatarioMensajeId) {
		return destinatariosMensajesDAO.get(destinatarioMensajeId);
	}

	@Override
	@Transactional
	public Integer checkIdExternoExists(String idExterno) {
		TblDestinatariosMensajesQuery query = new TblDestinatariosMensajesQuery();
		query.setCodigoexterno(idExterno);
		return (destinatariosMensajesDAO.count(query) == 1) ? 1 :0;
	}

	@Override
	@Transactional
	public Long insertar(TblDestinatariosMensajes destinatariosMensajes) {
		return destinatariosMensajesDAO.insert(destinatariosMensajes);
		
	}

	@Override
	@Transactional
	public Integer contarEstadosDestinatariosMensajes(Long mensajeId) {
		return queryExecutorDestinatariosMensajes.countDistinctStatus(mensajeId);
	}

	@Override
	@Transactional
	public String getIdExterno(Long mensajeId) {
		TblDestinatariosMensajesQuery query = new TblDestinatariosMensajesQuery();
		query.setMensajeid(mensajeId);
		TblDestinatariosMensajes  tblDestinatariosMensajes = destinatariosMensajesDAO.searchUnique(query);
		return tblDestinatariosMensajes.getCodigoexterno();
	}
	
	@Override
	@Transactional
	public String getUim(Long destinatariosMensaje) {
		return destinatariosMensajesDAO.get(destinatariosMensaje).getUim();
	}

	@Override
	@Transactional
	public void update(TblDestinatariosMensajes destinatarioMensaje) {
		destinatariosMensajesDAO.update(destinatarioMensaje);
	}
	
	
	@Override
	@Transactional
	public TblDestinatariosMensajes getDestinatariosMensajes(String idExterno, String destinatario, String usuario,
			PropertiesServices ps, Long mensajeId) {
		TblDestinatariosMensajes destinatariosMensajes = new TblDestinatariosMensajes();
		destinatariosMensajes.setMensajeid(mensajeId);
		destinatariosMensajes.setEstado(ps.getMessage("constantes.ESTADO_PENDIENTE",null));
		destinatariosMensajes.setDestinatario(destinatario);
		destinatariosMensajes.setCodigoexterno(idExterno);
		destinatariosMensajes.setCreadopor(usuario);
		destinatariosMensajes.setModificadopor(usuario);
		destinatariosMensajes.setFechacreacion(new Date());
		destinatariosMensajes.setFechamodificacion(new Date());
		destinatariosMensajes.setUltimoenvio(new Date());
		return destinatariosMensajes;
	}

	@Override
	@Transactional
	public TblDestinatariosMensajes getDestinatarioMensajeByUim(String uim) {
		TblDestinatariosMensajesQuery query = new TblDestinatariosMensajesQuery();
		query.setUimComparator(TextComparator.EQUALS);
		query.setUim(uim);

		return destinatariosMensajesDAO.searchUnique(query);
	}

	@Override
	@Transactional
	public List<TblDestinatariosMensajes> getDestinatarioMensajesUsuarios(Long mensajeId, List<String> listaUsuarios,
			String estadoInicial) {
		TblDestinatariosMensajesQuery query = new TblDestinatariosMensajesQuery();
		for(String s : listaUsuarios) 
			query.addDestinatarioIn(s);
		query.setMensajeid(mensajeId);
		if (null != estadoInicial)
			query.setEstado(estadoInicial);
		return destinatariosMensajesDAO.search(query).getResults(); //ver que pasa si no hay
	}

	@Override
	@Transactional
	public Integer updateNumIntentosEncolar(Long destinatarioMensajeId) {
		TblDestinatariosMensajes destinatario = destinatariosMensajesDAO.get(destinatarioMensajeId);
		Integer n = (null != destinatario.getNumintentosencolar())? destinatario.getNumintentosencolar() + 1 : 1; 
		destinatario.setNumintentosencolar(n);
		destinatariosMensajesDAO.update(destinatario);
		return n;
	}
	
	
	@Override
	@Transactional
	public List<Long> getIdMensajeByIdExterno(String idExterno) {
		List<Long> res = new ArrayList<>();
		TblDestinatariosMensajesQuery query = new TblDestinatariosMensajesQuery();
		
		query.setCodigoexterno(idExterno);
		query.setCodigoexternoComparator(TextComparator.EQUALS);
		List<TblDestinatariosMensajes> lista = destinatariosMensajesDAO.search(query).getResults();
		for (TblDestinatariosMensajes dm : lista) {
			res.add(dm.getMensajeid());
		}
		return res;
	}
	
	@Override
	@Transactional
	public void delete(Long destinatarioMensajeId) {
		destinatariosMensajesDAO.delete(destinatarioMensajeId);
		
	}
	
	
	@Override
	@Transactional
	public List<TblDestinatariosMensajes> getDestinatarioMensajesByQuery(TblDestinatariosMensajesQuery query) {
		return destinatariosMensajesDAO.search(query).getResults();
	}
	
	/**
	 * @return the destinatariosMensajesDAO
	 */
	public TblDestinatariosMensajesDAO getDestinatariosMensajesDAO() {
		return destinatariosMensajesDAO;
	}

	/**
	 * @param destinatariosMensajesDAO the destinatariosMensajesDAO to set
	 */
	public void setDestinatariosMensajesDAO(TblDestinatariosMensajesDAO destinatariosMensajesDAO) {
		this.destinatariosMensajesDAO = destinatariosMensajesDAO;
	}

	/**
	 * @return the estadosManager
	 */
	public TblEstadosManager getEstadosManager() {
		return estadosManager;
	}

	/**
	 * @param estadosManager the estadosManager to set
	 */
	public void setEstadosManager(TblEstadosManager estadosManager) {
		this.estadosManager = estadosManager;
	}

	/**
	 * @return the historicosManager
	 */
	public TblHistoricosManager getHistoricosManager() {
		return historicosManager;
	}

	/**
	 * @param historicosManager the historicosManager to set
	 */
	public void setHistoricosManager(TblHistoricosManager historicosManager) {
		this.historicosManager = historicosManager;
	}

	/**
	 * @return the mensajesManager
	 */
	public TblMensajesManager getMensajesManager() {
		return mensajesManager;
	}

	/**
	 * @param mensajesManager the mensajesManager to set
	 */
	public void setMensajesManager(TblMensajesManager mensajesManager) {
		this.mensajesManager = mensajesManager;
	}

	/**
	 * @return the reloadableResourceBundleMessageSource
	 */
	public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
		return reloadableResourceBundleMessageSource;
	}

	/**
	 * @param reloadableResourceBundleMessageSource the reloadableResourceBundleMessageSource to set
	 */
	public void setReloadableResourceBundleMessageSource(
			ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
		this.reloadableResourceBundleMessageSource = reloadableResourceBundleMessageSource;
	}

	/**
	 * @return the queryExecutorDestinatariosMensajes
	 */
	public QueryExecutorDestinatariosMensajes getQueryExecutorDestinatariosMensajes() {
		return queryExecutorDestinatariosMensajes;
	}

	/**
	 * @param queryExecutorDestinatariosMensajes the queryExecutorDestinatariosMensajes to set
	 */
	public void setQueryExecutorDestinatariosMensajes(QueryExecutorDestinatariosMensajes queryExecutorDestinatariosMensajes) {
		this.queryExecutorDestinatariosMensajes = queryExecutorDestinatariosMensajes;
	}

	
}
