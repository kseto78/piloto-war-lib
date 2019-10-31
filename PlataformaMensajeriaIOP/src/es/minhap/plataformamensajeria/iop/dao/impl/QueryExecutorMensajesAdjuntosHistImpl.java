package es.minhap.plataformamensajeria.iop.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;

import es.minhap.common.exception.ApplicationException;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorMensajesAdjuntosHist;
import es.minhap.sim.model.TblAdjuntos;
import es.minhap.sim.model.TblAdjuntosHist;
import es.minhap.sim.model.TblLotesEnviosHist;
import es.minhap.sim.model.TblMensajes;
import es.minhap.sim.model.TblMensajesAdjuntos;
import es.minhap.sim.model.TblMensajesAdjuntosHist;
import es.minhap.sim.model.TblMensajesHist;

/**
 * Query Executor encargado de lanzar consultas con relaciones con otras tabas a partir de la tabla TBL_SUBESTADO
 * 
 * @author everis
 *
 */
@Service("QueryExecutorMensajesAdjuntosHistImpl")
public class QueryExecutorMensajesAdjuntosHistImpl extends HibernateDaoSupport implements QueryExecutorMensajesAdjuntosHist {

	protected static final String R_CONST_1 = "lista";

	protected static final String R_CONST_2 = "unchecked";

	private static final Logger LOG = LoggerFactory.getLogger(QueryExecutorMensajesAdjuntosHistImpl.class);
	
	private static final String LOG_START = "search - start";
	
	private static final String HAS_ERROR = "Se ha producido un error ";
	
	@Autowired
	@Qualifier(value = "sessionFactorySIMApp")
	public void setSessionFactoryApp(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	
	@SuppressWarnings(R_CONST_2)
	public List<TblMensajesAdjuntosHist> convertAdjuntosTOAdjuntosHist(List<Long> subList, TblLotesEnviosHist loteHistorico, Integer max, Integer firstResult) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select m from TblMensajesAdjuntos m where m.tblMensajes.mensajeid in (:lista) ";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			
			query.setParameterList(R_CONST_1, subList);
			query.setMaxResults(max);
			query.setFirstResult(firstResult);
			return convertTOHistAdjuntos(query.list(), loteHistorico);
			 			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	@Override
	public Integer countConvertAdjuntosTOAdjuntosHist(List<Long> subList, TblLotesEnviosHist loteHistorico) {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select count(m) from TblMensajesAdjuntos m where m.tblMensajes.mensajeid in (:lista) ";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			
			query.setParameterList(R_CONST_1, subList);
			return ((Long)query.uniqueResult()).intValue();
			 			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	@SuppressWarnings(R_CONST_2)
	@Override
	public List<Long> getIdMensajesAdjuntosCons(List<Long> listaMensajes) {
		try {
			List<Long> res;
			if (LOG.isDebugEnabled()) {
				LOG.debug(LOG_START);
			}
			String sql = "select ma.mensajeadjuntoid from TblMensajesAdjuntosHist ma where ma.tblMensajesHist.mensajeid in (:lista) ";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			query.setParameterList(R_CONST_1, listaMensajes);
			res = query.list();
			if (null == res){
				return new ArrayList<>();
			}else{
				return res;
			}
			
		} catch (Exception e) {
			LOG.error(HAS_ERROR, e);
			throw new ApplicationException(e);
		}
	}
	
	private List<TblMensajesAdjuntosHist> convertTOHistAdjuntos(List<TblMensajesAdjuntos> list, TblLotesEnviosHist loteHistorico) {
		List<TblMensajesAdjuntosHist> res = new ArrayList<>();
		if (null != list){
			for (TblMensajesAdjuntos mensajeAdjunto : list) {
				res.add(convertTOHist(mensajeAdjunto, loteHistorico));
			}
		}
		return res;
	}

	private TblMensajesAdjuntosHist convertTOHist(TblMensajesAdjuntos mensajeAdjunto, TblLotesEnviosHist loteHistorico) {
		TblMensajesAdjuntosHist mah = null;
		if (null != mensajeAdjunto){
			mah = new TblMensajesAdjuntosHist();
			mah.setTblMensajesHist(convertTOHist(mensajeAdjunto.getTblMensajes(),loteHistorico));
			mah.setTblAdjuntosHist(converTOAdjuntoHist(mensajeAdjunto.getTblAdjuntos()));
			mah.setMensajeadjuntoid(mensajeAdjunto.getMensajeadjuntoid());
		}
		return mah;
	}
	
	private TblMensajesHist convertTOHist(TblMensajes mensaje, TblLotesEnviosHist loteHistorico) {
		TblMensajesHist m = null;
		if (null != mensaje){
			m = new TblMensajesHist();
			m.setCabecera(mensaje.getCabecera());
			m.setCodigoexterno(mensaje.getCodigoexterno());
			m.setCodorganismo(mensaje.getCodorganismo());
			m.setCodorganismopagador(mensaje.getCodorganismopagador());
			m.setCodsia(mensaje.getCodsia());
			m.setCreadopor(mensaje.getCreadopor());
			m.setCuerpo(mensaje.getCuerpo());
			m.setCuerpo_clob(mensaje.getCuerpo_clob());
			m.setCuerpofile(mensaje.getCuerpofile());
			m.setDocusuario(mensaje.getDocusuario());
			m.setEstadoactual(mensaje.getEstadoactual());
			m.setFechacreacion(mensaje.getFechacreacion());
			m.setFechamodificacion(mensaje.getFechamodificacion());
			m.setIcono(mensaje.getIcono());
			m.setIdenviossms(mensaje.getIdenviossms());
			m.setMensajeid(mensaje.getMensajeid());
			m.setModificadopor(mensaje.getModificadopor());
			m.setNombreusuario(mensaje.getNombreusuario());
			m.setNumeroenvios(mensaje.getNumeroenvios());
			m.setPrioridad(mensaje.getPrioridad());
			m.setSonido(mensaje.getSonido());
			m.setTblEstados(mensaje.getTblEstados());
			m.setTblLotesEnviosHist(loteHistorico);
			m.setTelefono(mensaje.getTelefono());
			m.setTipocodificacion(mensaje.getTipocodificacion());
			m.setTipocuerpo(mensaje.getTipocuerpo());
			m.setTipomensaje(mensaje.getTipomensaje());
			m.setUim(mensaje.getUim());
			m.setUltimoenvio(mensaje.getUltimoenvio());
			m.setUltimoidhistorico(mensaje.getUltimoidhistorico());
	
			
		}
		return m;
	}
	
	private TblAdjuntosHist converTOAdjuntoHist(TblAdjuntos adjunto) {
		TblAdjuntosHist a = null;
		if (null != adjunto){
			a = new TblAdjuntosHist();
			a.setAdjuntoid(adjunto.getAdjuntoid());
			a.setContenido(adjunto.getContenido());
			a.setContenidofile(adjunto.getContenidofile());
			a.setCreadopor(adjunto.getCreadopor());
			a.setFechacreacion(adjunto.getFechacreacion());
			a.setFechamodificacion(adjunto.getFechamodificacion());
			a.setImagen(adjunto.getImagen());
			a.setModificadopor(adjunto.getModificadopor());
			a.setNombre(adjunto.getNombre());
			
		}
		return a;
	}

}
