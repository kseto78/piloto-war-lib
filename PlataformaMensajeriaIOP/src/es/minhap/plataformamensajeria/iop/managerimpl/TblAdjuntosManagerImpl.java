package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.beans.entity.AuditoriaBean;
import es.minhap.plataformamensajeria.iop.manager.TblAdjuntosManager;
import es.minhap.plataformamensajeria.iop.manager.TblAuditoriaManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesAdjuntosManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.ViewMensajeAdjuntosDetalladaManager;
import es.minhap.plataformamensajeria.iop.manager.ViewMensajesDetalladaManager;
import es.minhap.plataformamensajeria.iop.util.MensajesAuditoria;
import es.minhap.plataformamensajeria.iop.util.Utils;
import es.minhap.sim.dao.TblAdjuntosDAO;
import es.minhap.sim.model.TblAdjuntos;
import es.minhap.sim.model.TblMensajesAdjuntos;
import es.minhap.sim.query.ViewMensajeAdjuntosDetalladaQuery;
import es.minhap.sim.query.ViewMensajesDetalladaQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblAdjuntosManagerImpl")
public class TblAdjuntosManagerImpl implements TblAdjuntosManager {

	@Autowired
	private TblAdjuntosDAO adjuntosDAO;

	@Autowired
	private ViewMensajesDetalladaManager viewMensajesDetalladaDAO;

	@Autowired
	private TblAuditoriaManager auditoriaManager;

	@Autowired
	private TblMensajesAdjuntosManager mensajesAdjuntosManager;

	@Autowired
	private TblMensajesManager mensajesManager;

	@Autowired
	private ViewMensajeAdjuntosDetalladaManager viewMensajeAdjuntosDetalladaDAO;

	/**
	 * @see es.minhap.TblLotesEnviosManager.insertarLote
	 */
	@Override
	@Transactional
	public Integer insertarAdjunto(Long mensajeId, String nombre, byte[] contenido, String usuario, String password) {

		// comprobamos el mensaje
		Integer count = comprobarMensaje(mensajeId, usuario, password);

		// Auditamos con error -1
		if (null == count || count.intValue() != 1) {
			auditarMensaje(null, mensajeId, usuario, password, MensajesAuditoria.ERROR_NO_EXISTE_MENSAJE,
					MensajesAuditoria.OPERACION_NUEVO_ANEXO, MensajesAuditoria.COD_ERROR_1);
			return MensajesAuditoria.COD_ERROR_1.intValue();
		}

		// insertamos adjunto
		TblAdjuntos adjunto = new TblAdjuntos();
		adjunto.setNombre(nombre);
		adjunto.setImagen(0L);
		adjunto.setContenido(contenido);
		adjunto.setFechacreacion(new Date());
		adjunto.setCreadopor(usuario);

		Long adjuntoId = getAdjuntosDAO().insert(adjunto);

		if (null == adjuntoId) {
			auditarMensaje(null, mensajeId, usuario, password, MensajesAuditoria.ERROR_BBDD,
					MensajesAuditoria.OPERACION_NUEVO_ANEXO, MensajesAuditoria.COD_ERROR_BBDD);
			return MensajesAuditoria.COD_ERROR_BBDD.intValue();
		}

		Long mensajesAdjuntosId = insertarTablaMensajesAdjuntos(mensajeId, adjuntoId);

		if (null == mensajesAdjuntosId) {
			auditarMensaje(null, mensajeId, usuario, password, MensajesAuditoria.ERROR_BBDD,
					MensajesAuditoria.OPERACION_NUEVO_ANEXO, MensajesAuditoria.COD_ERROR_BBDD);
			return MensajesAuditoria.COD_ERROR_BBDD.intValue();
		}

		auditarMensaje(null, mensajeId, usuario, password, MensajesAuditoria.ANEXO_CREADO,
				MensajesAuditoria.OPERACION_NUEVO_ANEXO, mensajeId);

		return adjuntoId.intValue();

	}

	

	@Override
	@Transactional
	public Integer asociarAnexo(Long mensajeId, Long idAdjunto, String usuario, String password) {
		Integer res = 0;
		// comprobamos el mensaje
		Integer count = comprobarMensaje(mensajeId, usuario, password);

		// Auditamos con error -1
		if (null == count || count.intValue() != 1) {
			auditarMensaje(null, mensajeId, usuario, password, MensajesAuditoria.ERROR_NO_EXISTE_MENSAJE,
					MensajesAuditoria.OPERACION_NUEVO_ANEXO, MensajesAuditoria.COD_ERROR_1);
			return MensajesAuditoria.COD_ERROR_1.intValue();
		}

		// comprobamos el anexo
		count = comprobarAnexo(idAdjunto, usuario, password);

		// Auditamos con error -2
		if (null == count || count.intValue() != 1) {
			auditarMensaje(null, mensajeId, usuario, password, MensajesAuditoria.ERROR_NO_EXISTE_ANEXO,
					MensajesAuditoria.OPERACION_ASOCIAR_ANEXO, MensajesAuditoria.COD_ERROR_2);
			return MensajesAuditoria.COD_ERROR_2.intValue();
		}
		
		// ASOCIAR ANEXO AL EMAIL EN LA TABLA "TBL_EMAILSADJUNTOS"
		Long mensajesAdjuntosId = insertarTablaMensajesAdjuntos(mensajeId, idAdjunto);

		if (null == mensajesAdjuntosId) {
			auditarMensaje(null, mensajeId, usuario, password, MensajesAuditoria.ERROR_BBDD,
					MensajesAuditoria.OPERACION_ASOCIAR_ANEXO, MensajesAuditoria.COD_ERROR_BBDD);
			return MensajesAuditoria.COD_ERROR_BBDD.intValue();
		}

		auditarMensaje(null, mensajeId, usuario, password, MensajesAuditoria.ANEXO_ASOCIADO,
				MensajesAuditoria.OPERACION_ASOCIAR_ANEXO, null);

		return res;
	}
	
	/**
	 * @param mensajeId
	 * @param adjuntoId
	 * @return
	 */
	private Long insertarTablaMensajesAdjuntos(Long mensajeId, Long adjuntoId) {
		// insertamos tabla mensajes adjuntos
		TblMensajesAdjuntos ma = new TblMensajesAdjuntos();
		ma.setTblAdjuntos(getAdjuntosDAO().get(adjuntoId));
		ma.setTblMensajes(getMensajesManager().getMensaje(mensajeId));

		return getMensajesAdjuntosManager().insertarMensajesAdjuntos(ma);
	}

	/**
	 * @param idAdjunto
	 * @param usuario
	 * @param password
	 * @return
	 */
	private Integer comprobarAnexo(Long idAdjunto, String usuario, String password) {
		Integer count;
		ViewMensajeAdjuntosDetalladaQuery query = new ViewMensajeAdjuntosDetalladaQuery();
		query.setAdjuntoid(idAdjunto);
		query.setUsuario(usuario);
		query.setPassword(Utils.encode64(password));

		count = getViewMensajeAdjuntosDetalladaDAO().countViewMensajeAdjuntosDetallada(query);
		return count;
	}

	/**
	 * @param mensajeId
	 * @param usuario
	 * @param password
	 * @return
	 */
	private Integer comprobarMensaje(Long mensajeId, String usuario, String password) {
		ViewMensajesDetalladaQuery query = new ViewMensajesDetalladaQuery();
		query.setUsuario(usuario);
		query.setPassword(Utils.encode64(password));
		query.setMensajeid(mensajeId);
		

		return getViewMensajesDetalladaDAO().countViewMensajeDetallada(query);
	}

	private void auditarMensaje(Long idLote, Long mensajeid, String usuario, String password, String textoMensaje,
			String textoOperacion, Long codigoOperacion) {
		AuditoriaBean auditoria = new AuditoriaBean(textoOperacion, new Date(), idLote, null, mensajeid, null, usuario,
				password, codigoOperacion, textoMensaje);
		getAuditoriaManager().insertarAuditoria(auditoria);
	}



	/**
	 * @return the adjuntosDAO
	 */
	public TblAdjuntosDAO getAdjuntosDAO() {
		return adjuntosDAO;
	}



	/**
	 * @param adjuntosDAO the adjuntosDAO to set
	 */
	public void setAdjuntosDAO(TblAdjuntosDAO adjuntosDAO) {
		this.adjuntosDAO = adjuntosDAO;
	}



	/**
	 * @return the viewMensajesDetalladaDAO
	 */
	public ViewMensajesDetalladaManager getViewMensajesDetalladaDAO() {
		return viewMensajesDetalladaDAO;
	}



	/**
	 * @param viewMensajesDetalladaDAO the viewMensajesDetalladaDAO to set
	 */
	public void setViewMensajesDetalladaDAO(ViewMensajesDetalladaManager viewMensajesDetalladaDAO) {
		this.viewMensajesDetalladaDAO = viewMensajesDetalladaDAO;
	}



	/**
	 * @return the auditoriaManager
	 */
	public TblAuditoriaManager getAuditoriaManager() {
		return auditoriaManager;
	}



	/**
	 * @param auditoriaManager the auditoriaManager to set
	 */
	public void setAuditoriaManager(TblAuditoriaManager auditoriaManager) {
		this.auditoriaManager = auditoriaManager;
	}



	/**
	 * @return the mensajesAdjuntosManager
	 */
	public TblMensajesAdjuntosManager getMensajesAdjuntosManager() {
		return mensajesAdjuntosManager;
	}



	/**
	 * @param mensajesAdjuntosManager the mensajesAdjuntosManager to set
	 */
	public void setMensajesAdjuntosManager(TblMensajesAdjuntosManager mensajesAdjuntosManager) {
		this.mensajesAdjuntosManager = mensajesAdjuntosManager;
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
	 * @return the viewMensajeAdjuntosDetalladaDAO
	 */
	public ViewMensajeAdjuntosDetalladaManager getViewMensajeAdjuntosDetalladaDAO() {
		return viewMensajeAdjuntosDetalladaDAO;
	}



	/**
	 * @param viewMensajeAdjuntosDetalladaDAO the viewMensajeAdjuntosDetalladaDAO to set
	 */
	public void setViewMensajeAdjuntosDetalladaDAO(ViewMensajeAdjuntosDetalladaManager viewMensajeAdjuntosDetalladaDAO) {
		this.viewMensajeAdjuntosDetalladaDAO = viewMensajeAdjuntosDetalladaDAO;
	}

}
