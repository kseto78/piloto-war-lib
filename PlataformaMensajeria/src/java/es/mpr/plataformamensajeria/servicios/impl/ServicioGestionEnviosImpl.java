package es.mpr.plataformamensajeria.servicios.impl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import oracle.jdbc.OracleTypes;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.dao.DAOException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.mpr.plataformamensajeria.beans.AdjuntoEmailBean;
import es.mpr.plataformamensajeria.beans.CanalBean;
import es.mpr.plataformamensajeria.beans.DestinatariosMensajesBean;
import es.mpr.plataformamensajeria.beans.DetalleEnvioBean;
import es.mpr.plataformamensajeria.beans.DetalleLoteBean;
import es.mpr.plataformamensajeria.beans.GestionEnvioBean;
import es.mpr.plataformamensajeria.beans.HistoricoBean;
import es.mpr.plataformamensajeria.beans.MensajeBean;
import es.mpr.plataformamensajeria.model.DestinatarioJPA;
import es.mpr.plataformamensajeria.model.DestinatariosMensajesJPA;
import es.mpr.plataformamensajeria.model.GestionEnviosHistoricosJPA;
import es.mpr.plataformamensajeria.model.GestionEnviosJPA;
import es.mpr.plataformamensajeria.model.MensajesJPA;
import es.mpr.plataformamensajeria.model.views.ViewAdjuntoEmailJPA;
import es.mpr.plataformamensajeria.model.views.ViewCanalJPA;
import es.mpr.plataformamensajeria.model.views.ViewGestionEnviosJPA;
import es.mpr.plataformamensajeria.model.views.ViewHistoricoJPA;
import es.mpr.plataformamensajeria.model.views.ViewHistoricoSMSJPA;
import es.mpr.plataformamensajeria.model.views.ViewLotesEnviosDetalladaJPA;
import es.mpr.plataformamensajeria.model.views.ViewMensajesJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnvios;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>
 * Maneja la persistencia y b&uacute;squeda de canales a traves de JPA.
 * 
 * @author
 * 
 */
public class ServicioGestionEnviosImpl implements ServicioGestionEnvios {
	Logger logger = Logger.getLogger(ServicioGestionEnviosImpl.class);

	private static String MENSAJESMS = "SMS";
	private static String MENSAJEEMAIL = "EMAIL";
	private static String MENSAJENOTIFICACION = "NOTIFICACION PUSH";
	private static String MENSAJERECEPCION = "RECEPCION SMS";
	private IPaginationJPADAO jpa;
	protected EntityManager em;

	public EntityManager getEntityManager() {
		return em;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
	}

	/**
	 * /**
	 * 
	 * @return Objeto BaseJPADao
	 */
	public IPaginationJPADAO getJpa() {
		return jpa;
	}

	/**
	 * Establece la propiedad jpa
	 * 
	 * @param jpa
	 */
	public void setJpa(IPaginationJPADAO jpa) {
		this.jpa = jpa;
	}

	static HashMap<Integer, Integer> mapPermisosUsuarioAplicacion = null;
	static String rolUsuario = null;

	/**
	 * <p>
	 * Convertirmos una lista de ViewCanalJPA a una lista de CanalBean
	 * </p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos CanalBean
	 */
	protected List<CanalBean> getListViewCanalBean(List<ViewCanalJPA> listJPA) throws BusinessException {
		List<CanalBean> result = null;

		if (listJPA != null && !listJPA.isEmpty()) {
			result = new ArrayList<CanalBean>();

			for (int indice = 0; indice < listJPA.size(); indice++) {

				ViewCanalJPA canalJPA = listJPA.get(indice);
				CanalBean canal = new CanalBean();

				try {

					Date defaultValue = null;
					DateConverter converter = new DateConverter(defaultValue);
					ConvertUtils.register(converter, java.util.Date.class);
					BeanUtils.copyProperties(canal, canalJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}

				result.add(canal);
			}
		}

		return result;
	}

	/**
	 * <p>
	 * Convertirmos una lista de ViewGestionEnviosJPA a una lista de GestionEnvioBean
	 * </p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos GestionBean
	 */
	protected List<GestionEnvioBean> getListViewGestionEnvioBean(List<ViewGestionEnviosJPA> listJPA) throws BusinessException {
		List<GestionEnvioBean> result = null;

		if (listJPA != null && !listJPA.isEmpty()) {
			result = new ArrayList<GestionEnvioBean>();

			for (int indice = 0; indice < listJPA.size(); indice++) {

				ViewGestionEnviosJPA gestionEnvioJPA = listJPA.get(indice);
				GestionEnvioBean gestionEnvio = new GestionEnvioBean();

				try {
					Date defaultValue = null;
					DateConverter converter = new DateConverter(defaultValue);
					ConvertUtils.register(converter, java.util.Date.class);
					BeanUtils.copyProperties(gestionEnvio, gestionEnvioJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}

				result.add(gestionEnvio);
			}
		}

		return result;
	}

	@Override
	public List<GestionEnvioBean> getGestionDeEnvios(GestionEnvioBean criterio) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	Connection con;

	@SuppressWarnings("unchecked")
	@Override
	public PaginatedList<GestionEnvioBean> getGestionDeEnvios(int inicio, Integer pagesize, String order, String columnSort, GestionEnvioBean criterio, HttpServletRequest request, boolean porLotes) throws BusinessException {
		// System.out.println("@@@ Lanzando consulta");
		// Columna para ordenar
		List<GestionEnvioBean> listaAuditoriaBean = new ArrayList<GestionEnvioBean>();

		// listaAuditoriaBean
		mapPermisosUsuarioAplicacion = (HashMap<Integer, Integer>) request.getSession().getAttribute(PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES);
		rolUsuario = (String) request.getSession().getAttribute(PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA);
		Hashtable<String, String> columns = new Hashtable<String, String>();
		String column = null;
		if (porLotes){
			columns.put("1", "APLICACION");
			columns.put("2", "SERVICIO");
			columns.put("3", "LOTEENVIO");
			columns.put("4", "LOTEENVIOID");
			columns.put("5", "ULTIMOENVIO");
			columns.put("6", "ESTADOLOTE");
			if (null != columnSort && columnSort.equals("5")){
				if (null != order && order.equals("2"))
					column = "ULTIMOENVIO desc, LOTEENVIOID ";
				else if (order.equals("1"))
					column = "ULTIMOENVIO asc, LOTEENVIOID ";
			}else if (null != columnSort){
				column = columns.get(columnSort);
			}else
				column = "ULTIMOENVIO desc, LOTEENVIOID ";
				
		}else{
			columns.put("1", "APLICACION");
			columns.put("2", "SERVICIO");
			columns.put("3", "LOTEENVIO");
			columns.put("4", "LOTEENVIOID");
			columns.put("5", "MENSAJEID");
			columns.put("6", "ULTIMOENVIO");
			columns.put("7", "ESTADO");
			if (null != columnSort && columnSort.equals("6")){
				if (null != order && order.equals("2"))
					column = "ULTIMOENVIO desc, MENSAJEID ";
				else if (order.equals("1"))
					column = "ULTIMOENVIO asc, MENSAJEID ";
			}else if (null != columnSort){
				column = columns.get(columnSort);
			}else
				column = "ULTIMOENVIO desc, MENSAJEID ";
		}
		
		if (column == null) {
			column = "LOTEENVIOID desc, ULTIMOENVIO";
		}
		if (order == null) {
			order = "2";
		}
		
		PaginatedList<GestionEnvioBean> result = null;
		if (con == null) {
			con = PlataformaMensajeriaUtil.getConexion();
		} else {
			try {
				if (con.isClosed()) {
					con = PlataformaMensajeriaUtil.getConexion();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Integer rowcount = 0;
		if (porLotes) {
			rowcount = getTotalLotesGestionEnvio(con, criterio, request);
		} else {
			rowcount = getTotalGestionEnvio(con, criterio, request);
		}

		ResultSet rs = null;
		CallableStatement cstmt = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			if (porLotes) {
				cstmt = con.prepareCall("{call PROC_GESTIONENVIOS_LOTES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			} else {
				cstmt = con.prepareCall("{call PROC_GESTIONENVIOS(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			}
			cstmt.setInt(1, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getAplicacionId()) ? criterio.getAplicacionId() : 0));
			cstmt.setInt(2, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getServidorId()) ? criterio.getServidorId() : 0));
			cstmt.setInt(3, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getCanalId()) ? criterio.getCanalId() : 0));
			cstmt.setInt(4, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getServicioId()) ? criterio.getServicioId() : 0));
			cstmt.setInt(5, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getEstadoId()) ? criterio.getEstadoId() : 0));

			cstmt.setInt(6, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getIdLote()) ? criterio.getIdLote() : 0));
			cstmt.setString(7, (criterio != null && !PlataformaMensajeriaUtil.isEmpty(criterio.getDestinatario()) ? criterio.getDestinatario() : null));
			// cstmt.setString(8, (criterio!=null&&!PlataformaMensajeriaUtil.isEmpty(criterio.getTelefonoMovil())?criterio.getTelefonoMovil():null));
			String desde = null;
			String hasta = null;
			if (criterio != null && criterio.getFechaDesde() != null) {
				desde = sdf.format(criterio.getFechaDesde());
			}
			if (criterio != null && criterio.getFechaHasta() != null) {
				hasta = sdf.format(criterio.getFechaHasta());
			}

			cstmt.setString(8, desde);
			cstmt.setString(9, hasta);

			int i = inicio + 1;
			if (pagesize > 0) {

				cstmt.setInt(10, i);
				cstmt.setInt(11, i + (pagesize) - 1);
			} else {
				cstmt.setInt(10, 0);
				// System.out.println("@@@ Setting RowCount = "+rowcount);
				cstmt.setInt(11, rowcount);
			}

			StringBuffer sbf = new StringBuffer();
			if (mapPermisosUsuarioAplicacion != null && (!PlataformaMensajeriaUtil.isEmpty(rolUsuario) && !rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR))) {
				Set<Integer> idAplicaciones = mapPermisosUsuarioAplicacion.keySet();
				Iterator<Integer> itAplicaciones = idAplicaciones.iterator();

				boolean first = true;
				while (itAplicaciones.hasNext()) {
					Integer idAplicacion = itAplicaciones.next();
					if (!first) {
						sbf.append(",");
					}
					sbf.append(idAplicacion);
					first = false;
				}
			}
			cstmt.setString(12, (sbf.toString().length() > 0) ? sbf.toString() : null);
			cstmt.setString(13, column);
			if (order != null && order.equals("1")) {
				cstmt.setString(14, "ASC");
			} else if (order != null && order.equals("2")) {
				cstmt.setString(14, "DESC");
			}

			cstmt.setString(15, (criterio != null && !PlataformaMensajeriaUtil.isEmpty(criterio.getDocUsuario()) ? criterio.getDocUsuario() : null));
			cstmt.setString(16, (criterio != null && !PlataformaMensajeriaUtil.isEmpty(criterio.getCodSIA()) ? criterio.getCodSIA() : null));
			cstmt.setString(17, (criterio != null && !PlataformaMensajeriaUtil.isEmpty(criterio.getCodOrganismo()) ? criterio.getCodOrganismo() : null));
			cstmt.setString(18, (criterio != null && !PlataformaMensajeriaUtil.isEmpty(criterio.getCodOrganismoPagador()) ? criterio.getCodOrganismoPagador() : null));
			cstmt.setInt(19, (criterio!=null&&!PlataformaMensajeriaUtil.isEmptyNumber(criterio.getMensajeId())?criterio.getMensajeId():0));
			cstmt.registerOutParameter(20, OracleTypes.CURSOR);
			cstmt.executeUpdate();

			rs = (ResultSet) cstmt.getObject(20);
			while (rs != null && rs.next()) {

				GestionEnvioBean envio = new GestionEnvioBean();
				/*
				 * APLICACION, SERVICIO, LOTEENVIO, CANALID, APLICACIONID, SERVICIOID,
              ULTIMOENVIO,  LOTEENVIOID, 
                    CODORGANISMO, CODORGANISMOPAGADOR, NOMBREUSUARIO, MULTIDESTINATARIO, ESTADOLOTE
				 * */
				// APLICACION, SERVICIO, LOTEENVIO, ESTADO, DESTINATARIO, CANALID, APLICACIONID,
				// SERVICIOID, EMAILID, ULTIMOENVIO, ESTADOID, LOTEENVIOID, SERVIDORID, IDEXTERNO, SMSID, ENVIOID
				// DOCUSUARIO, CODSIA, CODORGANISMO, CODORGANISMOPAGADOR, NOMBREUSUARIO, ICONO, SONIDO
				String aplicacion = rs.getString("APLICACION");

				String servicio = rs.getString("SERVICIO");
				String loteEnvio = rs.getString("LOTEENVIO");
				

				Integer canalId = rs.getInt("CANALID");
				Integer aplicacionId = rs.getInt("APLICACIONID");
				Integer servicioId = rs.getInt("SERVICIOID");

				// Cambio Ra�l 2013-10-16
				String ultimoEnvio = rs.getString("ULTIMOENVIO");
				
				Integer loteEnvioId = rs.getInt("LOTEENVIOID");
				

				String codOrganismo = rs.getString("CODORGANISMO");
				String codOrganismoPagador = rs.getString("CODORGANISMOPAGADOR");
				String nombreUsuario = rs.getString("NOMBREUSUARIO");

				Integer multidestinatario = rs.getInt("MULTIDESTINATARIO");

				String destinatario = "";
				Integer mensajeId = 0;
				String envioId = "";
				String idExterno = "";
				String docUsuario = "";
				String codSIA = "";
				String icono = "";
				String sonido = "";
				String estado = "";
				Integer estadoId = 0;
				Integer servidorId = 0;
				if (!porLotes) {
					estado = rs.getString("ESTADO");
					destinatario = rs.getString("DESTINATARIO");
					mensajeId = rs.getInt("MENSAJEID");
					envioId = rs.getString("ENVIOID");
					idExterno = rs.getString("IDEXTERNO");
					docUsuario = rs.getString("DOCUSUARIO");
					codSIA = rs.getString("CODSIA");
					icono = rs.getString("ICONO");
					sonido = rs.getString("SONIDO");
					estadoId = rs.getInt("ESTADOID");
					servidorId = rs.getInt("SERVIDORID");
				}else{
					estado = rs.getString("ESTADOLOTE");
				}

				envio.setAplicacion(aplicacion);
				envio.setServicio(servicio);
				envio.setLoteEnvio(loteEnvio);
				envio.setEstado(estado);
				envio.setDestinatario(destinatario);
				envio.setCanalId(canalId);
				envio.setAplicacionId(aplicacionId);
				envio.setServicioId(servicioId);
				envio.setMensajeId(mensajeId);
				envio.setUltimoEnvioStr(ultimoEnvio);
				envio.setEstadoId(estadoId);
				envio.setServidorId(servidorId);
				envio.setIdExterno(idExterno);
				envio.setEnvioId(envioId);
				envio.setIdLote(loteEnvioId);
				envio.setId(envioId);
				envio.setDocUsuario(docUsuario);
				envio.setCodSIA(codSIA);
				envio.setCodOrganismo(codOrganismo);
				envio.setCodOrganismoPagador(codOrganismoPagador);
				envio.setNombreUsuario(nombreUsuario);
				envio.setIcono(icono);
				envio.setSonido(sonido);
				envio.setMultidestinatario(multidestinatario);

				listaAuditoriaBean.add(envio);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			PlataformaMensajeriaUtil.closeSQLStatements(con, null, rs, cstmt);
		}

		result = new PaginatedList<GestionEnvioBean>();
		result.setPageList(listaAuditoriaBean);
		result.setTotalList(rowcount);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PaginatedList<GestionEnvioBean> getGestionDeEnviosDestinatarios(int inicio, Integer pagesize, String order, String columnSort, GestionEnvioBean criterio, HttpServletRequest request) throws BusinessException {
		List<GestionEnvioBean> listaAuditoriaBean= new ArrayList<GestionEnvioBean>();
		//listaAuditoriaBean 
		mapPermisosUsuarioAplicacion = (HashMap<Integer, Integer>)request.getSession().
				getAttribute(PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES);
		rolUsuario = (String)request.getSession().getAttribute(PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA);
		Hashtable<String, String> columns = new Hashtable<String,String>();	
		columns.put("1","APLICACION");
		columns.put("2", "SERVICIO");
		columns.put("3", "LOTEENVIO");
		columns.put("4", "MENSAJEID");
		columns.put("5", "LOTEENVIOID");
		columns.put("6", "ULTIMOENVIO");
		columns.put("7", "ESTADO");
		columns.put("8", "DESTINATARIO");
		columns.put("9", "FECHA");
		
		if (columnSort==null){
			columnSort = "9"; //FECHA
		}
		String column = columns.get(columnSort);
		if(order==null){
			order = "2";
		}
		 
		PaginatedList<GestionEnvioBean> result = null; 
		if(con==null){
			con = PlataformaMensajeriaUtil.getConexion();
		}else{
			try{
				if(con.isClosed()){
					con = PlataformaMensajeriaUtil.getConexion();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		Integer rowcount = getTotalGestionEnvioDestinatarios(con,criterio,request);
		
		ResultSet rs = null;
		CallableStatement cstmt=null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			cstmt = con.prepareCall("{call PROC_GESTIONENVIOS_DEST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, (criterio!=null&&!PlataformaMensajeriaUtil.isEmptyNumber(criterio.getAplicacionId())?criterio.getAplicacionId():0));
			cstmt.setInt(2, (criterio!=null&&!PlataformaMensajeriaUtil.isEmptyNumber(criterio.getServidorId())?criterio.getServidorId():0));
			cstmt.setInt(3, (criterio!=null&&!PlataformaMensajeriaUtil.isEmptyNumber(criterio.getCanalId())?criterio.getCanalId():0));
			cstmt.setInt(4, (criterio!=null&&!PlataformaMensajeriaUtil.isEmptyNumber(criterio.getServicioId())?criterio.getServicioId():0));
			cstmt.setInt(5, (criterio!=null&&!PlataformaMensajeriaUtil.isEmptyNumber(criterio.getEstadoId())?criterio.getEstadoId():0));
			
			cstmt.setInt(6, (criterio!=null&&!PlataformaMensajeriaUtil.isEmptyNumber(criterio.getIdLote())?criterio.getIdLote():0));
			cstmt.setString(7, (criterio!=null&&!PlataformaMensajeriaUtil.isEmpty(criterio.getDestinatario())?criterio.getDestinatario():null));
			//cstmt.setString(8, (criterio!=null&&!PlataformaMensajeriaUtil.isEmpty(criterio.getTelefonoMovil())?criterio.getTelefonoMovil():null));
			String desde=null;
			String hasta = null;
			if(criterio!=null&&criterio.getFechaDesde()!=null){
				desde = sdf.format(criterio.getFechaDesde());
			}
			if(criterio!=null&&criterio.getFechaHasta()!=null){
				hasta = sdf.format(criterio.getFechaHasta());
			}
			
			cstmt.setString(8, desde);
			cstmt.setString(9, hasta);
			
			int i = inicio+1;
			if(pagesize>0){
				
				cstmt.setInt(10,i);
				cstmt.setInt(11,i+(pagesize)-1);
			}else{
				cstmt.setInt(10,0);
				//System.out.println("@@@ Setting RowCount = "+rowcount);
				cstmt.setInt(11,rowcount);
			}
			
			StringBuffer sbf = new StringBuffer();
			if(mapPermisosUsuarioAplicacion!=null&&(!PlataformaMensajeriaUtil.isEmpty(rolUsuario)
					&&!rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR))){
				Set<Integer> idAplicaciones = mapPermisosUsuarioAplicacion.keySet();
				Iterator<Integer> itAplicaciones = idAplicaciones.iterator();
				
				boolean first=true;
				while(itAplicaciones.hasNext()){
					Integer idAplicacion = itAplicaciones.next();
					if(!first){
						sbf.append(",");
					}
					sbf.append(idAplicacion);
					first=false;
				}
			}
			cstmt.setString(12,(sbf.toString().length()>0)?sbf.toString():null);
			cstmt.setString(13, column);
			if(order!=null&&order.equals("1")){
				cstmt.setString(14, "ASC");
			}else if(order!=null&&order.equals("2")){
				cstmt.setString(14, "DESC");
			}
			
			cstmt.setString(15, (criterio!=null&&!PlataformaMensajeriaUtil.isEmpty(criterio.getDocUsuario())?criterio.getDocUsuario():null));
			cstmt.setString(16, (criterio!=null&&!PlataformaMensajeriaUtil.isEmpty(criterio.getCodSIA())?criterio.getCodSIA():null));
			cstmt.setString(17, (criterio!=null&&!PlataformaMensajeriaUtil.isEmpty(criterio.getCodOrganismo())?criterio.getCodOrganismo():null));
			cstmt.setString(18, (criterio!=null&&!PlataformaMensajeriaUtil.isEmpty(criterio.getCodOrganismoPagador())?criterio.getCodOrganismoPagador():null));
			cstmt.setInt(19, (criterio!=null&&!PlataformaMensajeriaUtil.isEmptyNumber(criterio.getMensajeId())?criterio.getMensajeId():0));
			cstmt.registerOutParameter(20, OracleTypes.CURSOR);
			cstmt.executeUpdate();
			 
			rs = (ResultSet) cstmt.getObject(20);
			while(rs!=null&&rs.next()){
			
				GestionEnvioBean envio = new GestionEnvioBean();
				//APLICACION, SERVICIO, LOTEENVIO, ESTADO, DESTINATARIO, CANALID, APLICACIONID, 
				//SERVICIOID, EMAILID, ULTIMOENVIO, ESTADOID, LOTEENVIOID, SERVIDORID, IDEXTERNO, SMSID, ENVIOID
				//DOCUSUARIO, CODSIA, CODORGANISMO, CODORGANISMOPAGADOR, NOMBREUSUARIO, ICONO, SONIDO
				String aplicacion = rs.getString("APLICACION");
				
				String servicio = rs.getString("SERVICIO");
				String loteEnvio = rs.getString("LOTEENVIO");
				String estado = rs.getString("ESTADO");
				String destinatario = rs.getString("DESTINATARIO");
				Integer canalId = rs.getInt("CANALID");
				Integer aplicacionId = rs.getInt("APLICACIONID");
				Integer servicioId = rs.getInt("SERVICIOID");
				Integer mensajeId = rs.getInt("MENSAJEID");
				//Cambio Ra�l 2013-10-16
				String ultimoEnvio = rs.getString("ULTIMOENVIO");
				//Date ultimoEnvio = rs.getTimestamp("ULTIMOENVIO");
//				Integer estadoId = rs.getInt("ESTADOID");
				Integer loteEnvioId = rs.getInt("LOTEENVIOID");
//				Integer servidorId = rs.getInt("SERVIDORID");
//				String idExterno = rs.getString("IDEXTERNO");
				//String smsId = rs.getString("SMSID");
				String envioId = rs.getString("ENVIOID");
//				String docUsuario = rs.getString("DOCUSUARIO");
//				String codSIA = rs.getString("CODSIA");
//				String codOrganismo = rs.getString("CODORGANISMO");
//				String codOrganismoPagador = rs.getString("CODORGANISMOPAGADOR");
//				String nombreUsuario = rs.getString("NOMBREUSUARIO");
//				String icono = rs.getString("ICONO");
//				String sonido = rs.getString("SONIDO");
				Integer destinatariosMensajes = rs.getInt("DESTINATARIOSMENSAJES");
				
				envio.setAplicacion(aplicacion);
				envio.setServicio(servicio);
				envio.setLoteEnvio(loteEnvio);
				envio.setEstado(estado);
				envio.setDestinatario(destinatario);
				envio.setCanalId(canalId);
				envio.setAplicacionId(aplicacionId);
				envio.setServicioId(servicioId);
				envio.setMensajeId(mensajeId);
				envio.setUltimoEnvioStr(ultimoEnvio);
//				envio.setEstadoId(estadoId);
//				envio.setServidorId(servidorId);
//				envio.setIdExterno(idExterno);
				envio.setEnvioId(envioId);
				envio.setIdLote(loteEnvioId);
//				envio.setId(envioId);
//				envio.setLoteEnvio(loteEnvioId.toString());
//				envio.setDocUsuario(docUsuario);
//				envio.setCodSIA(codSIA);
//				envio.setCodOrganismo(codOrganismo);
//				envio.setCodOrganismoPagador(codOrganismoPagador);
//				envio.setNombreUsuario(nombreUsuario);
//				envio.setIcono(icono);
//				envio.setSonido(sonido);
				envio.setVistaEnviosId(3);
				envio.setDestinatariosMensajes(destinatariosMensajes);
								
				listaAuditoriaBean.add(envio);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			PlataformaMensajeriaUtil.closeSQLStatements(con, null, rs, cstmt);
		}
		
		
		result = new PaginatedList<GestionEnvioBean>();
		result.setPageList(listaAuditoriaBean);
		result.setTotalList(rowcount);
		return result;	
	
	}

	public Integer getTotalGestionEnvio(Connection con, GestionEnvioBean criterio, HttpServletRequest request) {
		mapPermisosUsuarioAplicacion = (HashMap<Integer, Integer>) request.getSession().getAttribute(PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES);
		ResultSet rs = null;
		Integer i = new Integer(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		CallableStatement cstmt = null;
		try {
			cstmt = con.prepareCall("{call PROC_GESTIONENVIOS_CONT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getAplicacionId()) ? criterio.getAplicacionId() : 0));
			cstmt.setInt(2, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getServidorId()) ? criterio.getServidorId() : 0));
			cstmt.setInt(3, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getCanalId()) ? criterio.getCanalId() : 0));
			cstmt.setInt(4, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getServicioId()) ? criterio.getServicioId() : 0));
			cstmt.setInt(5, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getEstadoId()) ? criterio.getEstadoId() : 0));
			cstmt.setInt(6, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getIdLote()) ? criterio.getIdLote() : 0));
			cstmt.setString(7, (criterio != null && !PlataformaMensajeriaUtil.isEmpty(criterio.getDestinatario()) ? criterio.getDestinatario() : null));
			// cstmt.setString(8, (criterio!=null&&!PlataformaMensajeriaUtil.isEmpty(criterio.getTelefonoMovil())?criterio.getTelefonoMovil():null));
			String desde = null;
			String hasta = null;
			if (criterio != null && criterio.getFechaDesde() != null) {
				desde = sdf.format(criterio.getFechaDesde());
			}
			if (criterio != null && criterio.getFechaHasta() != null) {
				hasta = sdf.format(criterio.getFechaHasta());
			}
			cstmt.setString(8, desde);
			cstmt.setString(9, hasta);
			StringBuffer sbf = new StringBuffer();
			if (mapPermisosUsuarioAplicacion != null && (!PlataformaMensajeriaUtil.isEmpty(rolUsuario) && !rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR))) {

				Set<Integer> idAplicaciones = mapPermisosUsuarioAplicacion.keySet();
				Iterator<Integer> itAplicaciones = idAplicaciones.iterator();

				boolean first = true;
				while (itAplicaciones.hasNext()) {
					Integer idAplicacion = itAplicaciones.next();
					// System.out.println("@@@ ITERANDO IDS DE APLICACIONES "+ idAplicacion);

					if (!first) {
						sbf.append(",");
					}
					sbf.append(idAplicacion);
					first = false;
				}
			}

			cstmt.setString(10, (sbf.toString().length() > 0) ? sbf.toString() : null);

			cstmt.setString(11, (criterio != null && !PlataformaMensajeriaUtil.isEmpty(criterio.getDocUsuario()) ? criterio.getDocUsuario() : null));
			cstmt.setString(12, (criterio != null && !PlataformaMensajeriaUtil.isEmpty(criterio.getCodSIA()) ? criterio.getCodSIA() : null));
			cstmt.setString(13, (criterio != null && !PlataformaMensajeriaUtil.isEmpty(criterio.getCodOrganismo()) ? criterio.getCodOrganismo() : null));
			cstmt.setString(14, (criterio != null && !PlataformaMensajeriaUtil.isEmpty(criterio.getCodOrganismoPagador()) ? criterio.getCodOrganismoPagador() : null));
			cstmt.setInt(15, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getMensajeId()) ? criterio.getMensajeId() : 0));
			cstmt.registerOutParameter(16, OracleTypes.CURSOR);
			cstmt.executeUpdate();

			// get cursor and cast it to ResultSet
			rs = (ResultSet) cstmt.getObject(16);
			while (rs != null && rs.next()) {
				i = rs.getInt(1);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (cstmt != null) {
					cstmt.close();
					cstmt = null;
				}

			} catch (SQLException e) {
			}
			try {

				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {

			}
		}
		return i;
	}

	public Integer getTotalGestionEnvioDestinatarios(Connection con, GestionEnvioBean criterio, HttpServletRequest request) {
		mapPermisosUsuarioAplicacion = (HashMap<Integer, Integer>) request.getSession().getAttribute(PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES);
		ResultSet rs = null;
		Integer i = new Integer(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		CallableStatement cstmt = null;
		try {
			cstmt = con.prepareCall("{call PROC_GESTIONENVIOS_CONT_DEST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getAplicacionId()) ? criterio.getAplicacionId() : 0));
			cstmt.setInt(2, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getServidorId()) ? criterio.getServidorId() : 0));
			cstmt.setInt(3, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getCanalId()) ? criterio.getCanalId() : 0));
			cstmt.setInt(4, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getServicioId()) ? criterio.getServicioId() : 0));
			cstmt.setInt(5, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getEstadoId()) ? criterio.getEstadoId() : 0));
			cstmt.setInt(6, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getIdLote()) ? criterio.getIdLote() : 0));
			cstmt.setString(7, (criterio != null && !PlataformaMensajeriaUtil.isEmpty(criterio.getDestinatario()) ? criterio.getDestinatario() : null));
			// cstmt.setString(8, (criterio!=null&&!PlataformaMensajeriaUtil.isEmpty(criterio.getTelefonoMovil())?criterio.getTelefonoMovil():null));
			String desde = null;
			String hasta = null;
			if (criterio != null && criterio.getFechaDesde() != null) {
				desde = sdf.format(criterio.getFechaDesde());
			}
			if (criterio != null && criterio.getFechaHasta() != null) {
				hasta = sdf.format(criterio.getFechaHasta());
			}
			cstmt.setString(8, desde);
			cstmt.setString(9, hasta);
			StringBuffer sbf = new StringBuffer();
			if (mapPermisosUsuarioAplicacion != null && (!PlataformaMensajeriaUtil.isEmpty(rolUsuario) && !rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR))) {

				Set<Integer> idAplicaciones = mapPermisosUsuarioAplicacion.keySet();
				Iterator<Integer> itAplicaciones = idAplicaciones.iterator();

				boolean first = true;
				while (itAplicaciones.hasNext()) {
					Integer idAplicacion = itAplicaciones.next();
					// System.out.println("@@@ ITERANDO IDS DE APLICACIONES "+ idAplicacion);

					if (!first) {
						sbf.append(",");
					}
					sbf.append(idAplicacion);
					first = false;
				}
			}

			cstmt.setString(10, (sbf.toString().length() > 0) ? sbf.toString() : null);

			cstmt.setString(11, (criterio != null && !PlataformaMensajeriaUtil.isEmpty(criterio.getDocUsuario()) ? criterio.getDocUsuario() : null));
			cstmt.setString(12, (criterio != null && !PlataformaMensajeriaUtil.isEmpty(criterio.getCodSIA()) ? criterio.getCodSIA() : null));
			cstmt.setString(13, (criterio != null && !PlataformaMensajeriaUtil.isEmpty(criterio.getCodOrganismo()) ? criterio.getCodOrganismo() : null));
			cstmt.setString(14, (criterio != null && !PlataformaMensajeriaUtil.isEmpty(criterio.getCodOrganismoPagador()) ? criterio.getCodOrganismoPagador() : null));
			cstmt.setInt(15, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getMensajeId()) ? criterio.getMensajeId() : 0));
			cstmt.registerOutParameter(16, OracleTypes.CURSOR);
			cstmt.executeUpdate();

			// get cursor and cast it to ResultSet
			rs = (ResultSet) cstmt.getObject(16);
			while (rs != null && rs.next()) {
				i = rs.getInt(1);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (cstmt != null) {
					cstmt.close();
					cstmt = null;
				}

			} catch (SQLException e) {
			}
			try {

				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {

			}
		}
		return i;
	}
	
	public Integer getTotalLotesGestionEnvio(Connection con, GestionEnvioBean criterio, HttpServletRequest request) {
		mapPermisosUsuarioAplicacion = (HashMap<Integer, Integer>) request.getSession().getAttribute(PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES);
		ResultSet rs = null;
		Integer i = new Integer(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		CallableStatement cstmt = null;
		try {
			cstmt = con.prepareCall("{call PROC_GESTIONENVIOS_LOTES_CONT(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getAplicacionId()) ? criterio.getAplicacionId() : 0));
			cstmt.setInt(2, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getServidorId()) ? criterio.getServidorId() : 0));
			cstmt.setInt(3, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getCanalId()) ? criterio.getCanalId() : 0));
			cstmt.setInt(4, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getServicioId()) ? criterio.getServicioId() : 0));
			cstmt.setInt(5, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getEstadoId()) ? criterio.getEstadoId() : 0));
			cstmt.setInt(6, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getIdLote()) ? criterio.getIdLote() : 0));
			cstmt.setString(7, (criterio != null && !PlataformaMensajeriaUtil.isEmpty(criterio.getDestinatario()) ? criterio.getDestinatario() : null));
			// cstmt.setString(8, (criterio!=null&&!PlataformaMensajeriaUtil.isEmpty(criterio.getTelefonoMovil())?criterio.getTelefonoMovil():null));
			String desde = null;
			String hasta = null;
			if (criterio != null && criterio.getFechaDesde() != null) {
				desde = sdf.format(criterio.getFechaDesde());
			}
			if (criterio != null && criterio.getFechaHasta() != null) {
				hasta = sdf.format(criterio.getFechaHasta());
			}
			cstmt.setString(8, desde);
			cstmt.setString(9, hasta);
			StringBuffer sbf = new StringBuffer();
			if (mapPermisosUsuarioAplicacion != null && (!PlataformaMensajeriaUtil.isEmpty(rolUsuario) && !rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR))) {

				Set<Integer> idAplicaciones = mapPermisosUsuarioAplicacion.keySet();
				Iterator<Integer> itAplicaciones = idAplicaciones.iterator();

				boolean first = true;
				while (itAplicaciones.hasNext()) {
					Integer idAplicacion = itAplicaciones.next();
					// System.out.println("@@@ ITERANDO IDS DE APLICACIONES "+ idAplicacion);

					if (!first) {
						sbf.append(",");
					}
					sbf.append(idAplicacion);
					first = false;
				}
			}

			cstmt.setString(10, (sbf.toString().length() > 0) ? sbf.toString() : null);

			cstmt.setString(11, (criterio != null && !PlataformaMensajeriaUtil.isEmpty(criterio.getDocUsuario()) ? criterio.getDocUsuario() : null));
			cstmt.setString(12, (criterio != null && !PlataformaMensajeriaUtil.isEmpty(criterio.getCodSIA()) ? criterio.getCodSIA() : null));
			cstmt.setString(13, (criterio != null && !PlataformaMensajeriaUtil.isEmpty(criterio.getCodOrganismo()) ? criterio.getCodOrganismo() : null));
			cstmt.setString(14, (criterio != null && !PlataformaMensajeriaUtil.isEmpty(criterio.getCodOrganismoPagador()) ? criterio.getCodOrganismoPagador() : null));
			cstmt.setInt(15, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getMensajeId()) ? criterio.getMensajeId() : 0));
			cstmt.registerOutParameter(16, OracleTypes.CURSOR);
			cstmt.executeUpdate();

			// get cursor and cast it to ResultSet
			rs = (ResultSet) cstmt.getObject(16);
			while (rs != null && rs.next()) {
				i = rs.getInt(1);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (cstmt != null) {
					cstmt.close();
					cstmt = null;
				}

			} catch (SQLException e) {
			}
			try {

				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {

			}
		}
		return i;
	}

	@Override
	public DetalleEnvioBean loadMensaje(String idEmail) throws BusinessException {
		DetalleEnvioBean detalleEmail = new DetalleEnvioBean();

		// Obtener los datos del Email
		// String[] arrayEnvioId = idEnvio.split("_");
		// Integer idEmail = new Integer(arrayEnvioId[1]);
		ViewMensajesJPA mensaje = new ViewMensajesJPA();
		mensaje.setMensajeId(Integer.parseInt(idEmail));
		try {
			mensaje = (ViewMensajesJPA) jpa.read(mensaje);

			// Obtenemos los adjuntos del Email
			Query query = em.createNamedQuery("selectViewAdjuntoEmailByEmailIdJPA");
			query.setParameter("emailId", Integer.parseInt(idEmail));
			List<AdjuntoEmailBean> listaAdjuntos = getListAdjuntoEmailBean((List<ViewAdjuntoEmailJPA>) query.getResultList());
			Query queryImagenes = em.createNamedQuery("selectViewImagenEmailByEmailIdJPA");
			queryImagenes.setParameter("emailId", Integer.parseInt(idEmail));
			List<AdjuntoEmailBean> listaImagenes = getListAdjuntoEmailBean((List<ViewAdjuntoEmailJPA>) queryImagenes.getResultList());

			// //Obtenemos los destinatarios (Todos: BCC, CC, TO)
			// query = em.createNamedQuery("selectViewDestinatarioByEmailJPA");
			// query.setParameter("emailId",idEmail.toString());
			// List<ViewDestinatarioJPA> listaDestinatarios = query.getResultList();
			// // ESCA-JAVA0283:
			// for(int i=0;listaDestinatarios!=null&&i<listaDestinatarios.size();i++){
			// ViewDestinatarioJPA destinatario = listaDestinatarios.get(i);
			// if(destinatario!=null&&destinatario.getTipoDestinatario()!=null&&destinatario.getTipoDestinatario().equals("TO")){
			// detalleEmail.addDestino(destinatario.getEmail());
			// }
			// if(destinatario!=null&&destinatario.getTipoDestinatario()!=null&&destinatario.getTipoDestinatario().equals("BCC")){
			// detalleEmail.addBcc(destinatario.getEmail());
			// }
			// if(destinatario!=null&&destinatario.getTipoDestinatario()!=null&&destinatario.getTipoDestinatario().equals("CC")){
			// detalleEmail.addCc(destinatario.getEmail());
			// }
			// }

			// //Obtenemos los historicos del Email
			// query = em.createNamedQuery("selectViewHistoricoByMensajeIdJPA");
			// query.setParameter("mensajeId", idEmail);
			// List<HistoricoBean> listaHistorico = getListHistoricoBean((List<ViewHistoricoJPA>) query.getResultList());

			// Obtenemos los datos generales del envio
			query = em.createNamedQuery("selectViewGestionEnviosByIdJPA");
			query.setParameter("mensajeId", Integer.parseInt(idEmail));
			ViewGestionEnviosJPA gestionEnvio = (ViewGestionEnviosJPA) query.getSingleResult();

			detalleEmail.setNombreAplicacion(gestionEnvio.getAplicacion());
			detalleEmail.setNombreServicio(gestionEnvio.getServicio());
			detalleEmail.setNombreLoteEnvio(gestionEnvio.getLoteEnvio());
			detalleEmail.setIdExterno(gestionEnvio.getIdExterno());
			detalleEmail.setIdLote(gestionEnvio.getIdLote());
			detalleEmail.setMensajeId(Integer.parseInt(idEmail));
			query = em.createNamedQuery("selectMensajeJPA");
			query.setParameter("mensajeId", Integer.parseInt(idEmail));
			MensajesJPA emailJPA = (MensajesJPA) query.getSingleResult();
			detalleEmail.setTipoContenido(emailJPA.getTipoCuerpo());
			detalleEmail.setCodificacion(emailJPA.getTipoCodificacion());
			detalleEmail.setPrioridad(emailJPA.getPrioridad());
			detalleEmail.setAsunto(mensaje.getCabecera());
			detalleEmail.setCuerpo(mensaje.getCuerpo());
			detalleEmail.setTelefono(mensaje.getTelefono());
			detalleEmail.setTipoMensaje(mensaje.getTipoMensaje());
			if (listaAdjuntos != null) {
				detalleEmail.setListadoAdjuntos(listaAdjuntos);
			}
			if (listaImagenes != null) {
				detalleEmail.setListadoImagenes(listaImagenes);
			}
			// if(listaHistorico!=null){
			// detalleEmail.setListadoHistorico(listaHistorico);
			// }
			detalleEmail.setDocUsuario(emailJPA.getDocUsuario());
			detalleEmail.setCodSIA(emailJPA.getCodSIA());
			detalleEmail.setCodOrganismo(emailJPA.getCodOrganismo());
			detalleEmail.setCodOrganismoPagador(emailJPA.getCodOrganismoPagador());
			detalleEmail.setIcono(emailJPA.getIcono());
			detalleEmail.setSonido(emailJPA.getSonido());
			detalleEmail.setNombreUsuario(emailJPA.getNombreUsuario());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return detalleEmail;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DetalleLoteBean loadLote(String idLote) throws BusinessException {
		DetalleLoteBean detalleLote = new DetalleLoteBean();

		ViewLotesEnviosDetalladaJPA lote = new ViewLotesEnviosDetalladaJPA();
		lote.setLoteEnvioId(Integer.parseInt(idLote));
		try {
			lote = (ViewLotesEnviosDetalladaJPA) jpa.read(lote);

			detalleLote.setIdLoteEnvio(lote.getLoteEnvioId());
			detalleLote.setNombreAplicacion(lote.getNombreAplicacion());
			detalleLote.setNombreLoteEnvio(lote.getNombreLote());
			detalleLote.setNombreServicio(lote.getNombreServicio());
		} catch (DAOException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		return detalleLote;
	}

	protected ArrayList<MensajeBean> getListMensajesLoteBean(List<MensajesJPA> listJPA) throws BusinessException {
		ArrayList<MensajeBean> result = null;

		if (listJPA != null && !listJPA.isEmpty()) {
			result = new ArrayList<MensajeBean>();

			for (int indice = 0; indice < listJPA.size(); indice++) {

				MensajesJPA mensajeJPA = listJPA.get(indice);
				MensajeBean mensajeBean = new MensajeBean();

				try {
					BeanUtils.copyProperties(mensajeBean, mensajeJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}

				result.add(mensajeBean);
			}
		}

		return result;
	}

	@Override
	@Transactional
	public void reenviarEnvio(String tipoEnvio, int idEnvio) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session sess = sessionFactory.getCurrentSession();
		
		if (null != sess) {
			try {
				sess.beginTransaction();
				
				if (isMultidestinatarioPorMensaje(idEnvio)){
					org.hibernate.Query query = sess.getNamedQuery("reenviaEnvioMult");
					query.setParameter(0, Integer.valueOf(idEnvio));
					// query.setParameter(1, tipoEnvio);
		
					query.executeUpdate();
				}else{
					org.hibernate.Query query = sess.getNamedQuery("reenviaEnvio");
					query.setParameter(0, Integer.valueOf(idEnvio));
					// query.setParameter(1, tipoEnvio);
		
					query.executeUpdate();
				}

			} catch (Exception e) {
				System.err.println("[PLATAFORMA-BBDD] Error cerrando la conexión con la base de datos.");
			}finally{
				if (sess.isOpen()) {
					sess.close();
					sessionFactory.close();
				}
			}
		}
		return;
	}

	@Override
	@Transactional
	public void anulaEnvio(String tipoEnvio, int idEnvio) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session sess = sessionFactory.getCurrentSession();
		if (null != sess) {
			try {
				sess.beginTransaction();
				if (isMultidestinatarioPorMensaje(idEnvio)) {
					org.hibernate.Query query = sess.getNamedQuery("anulaEnvioMult");
					query.setParameter(0, Integer.valueOf(idEnvio));
					// query.setParameter(1, tipoEnvio);

					query.executeUpdate();
				} else {
					org.hibernate.Query query = sess.getNamedQuery("anulaEnvio");
					query.setParameter(0, Integer.valueOf(idEnvio));
					// query.setParameter(1, tipoEnvio);

					query.executeUpdate();
				}

			} catch (Exception e) {
				System.err.println("[PLATAFORMA-BBDD] Error cerrando la conexión con la base de datos.");
			} finally {
				if (sess.isOpen()) {
					sess.close();
					sessionFactory.close();
				}
			}
		}
		return;
	
	}

	@Override
	public void anulaEnvioLote(String idLote) {
		for (Integer idMensaje : recuperarMensajesPorLote(Integer.parseInt(idLote))) {
			anulaEnvio(null, idMensaje);
		}
	}

	@Override
	public void reenviarEnvioLote(String idLote) {
		for (Integer idMensaje : recuperarMensajesPorLote(Integer.parseInt(idLote))) {
			reenviarEnvio(null, idMensaje);
		}
	}
	
	private List<Integer> recuperarMensajesPorLote(Integer idLote) {
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> listaMensajes = new ArrayList<Integer>();
		try {
			pstmt = con.prepareStatement("SELECT MENSAJEID FROM TBL_MENSAJES WHERE LOTEENVIOID = " + idLote);
			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
				listaMensajes.add(rs.getInt("MENSAJEID"));
			}

		} catch (Exception e) {
			System.err.println("[PLATAFORMA-BBDD] Error cerrando la conexión con la base de datos.");
		} finally {
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return listaMensajes;

	}
	/**
	 * <p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos
	 */
	@Transactional
	protected List<AdjuntoEmailBean> getListAdjuntoEmailBean(List<ViewAdjuntoEmailJPA> listJPA) throws BusinessException {
		List<AdjuntoEmailBean> result = null;

		if (listJPA != null && !listJPA.isEmpty()) {
			result = new ArrayList<AdjuntoEmailBean>();

			for (int indice = 0; indice < listJPA.size(); indice++) {

				ViewAdjuntoEmailJPA adjuntoJPA = listJPA.get(indice);
				AdjuntoEmailBean adjunto = new AdjuntoEmailBean();
				adjunto.setAdjuntoId(adjuntoJPA.getAdjuntoId());
				// adjunto.setContenido(adjuntoJPA.getContenido());
				adjunto.setCreadoPor(adjuntoJPA.getCreadoPor());
				adjunto.setEmailId(adjuntoJPA.getEmailId());
				adjunto.setFechaCreacion(adjuntoJPA.getFechaCreacion());
				adjunto.setFechaModificacion(adjuntoJPA.getFechaModificacion());
				adjunto.setModificadoPor(adjuntoJPA.getModificadoPor());
				adjunto.setNombre(adjuntoJPA.getNombre());
				// adjunto.setColumnaTest(adjuntoJPA.getColumnaTest());
				result.add(adjunto);
			}
		}

		return result;
	}

	/**
	 * <p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos
	 */
	protected List<HistoricoBean> getListHistoricoBeanFromSMSJPA(List<ViewHistoricoSMSJPA> listJPA) throws BusinessException {
		List<HistoricoBean> result = null;

		if (listJPA != null && !listJPA.isEmpty()) {
			result = new ArrayList<HistoricoBean>();

			for (int indice = 0; indice < listJPA.size(); indice++) {

				ViewHistoricoSMSJPA historicoJPA = listJPA.get(indice);
				HistoricoBean historico = new HistoricoBean();

				try {
					Date defaultValue = null;
					DateConverter converter = new DateConverter(defaultValue);
					ConvertUtils.register(converter, java.util.Date.class);
					BeanUtils.copyProperties(historico, historicoJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}

				result.add(historico);
			}
		}

		return result;
	}

	/**
	 * <p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos
	 */
	protected List<HistoricoBean> getListHistoricoBean(List<ViewHistoricoJPA> listJPA) throws BusinessException {
		List<HistoricoBean> result = null;

		if (listJPA != null && !listJPA.isEmpty()) {
			result = new ArrayList<HistoricoBean>();

			for (int indice = 0; indice < listJPA.size(); indice++) {

				ViewHistoricoJPA historicoJPA = listJPA.get(indice);
				HistoricoBean historico = new HistoricoBean();

				try {
					Date defaultValue = null;
					DateConverter converter = new DateConverter(defaultValue);
					ConvertUtils.register(converter, java.util.Date.class);
					BeanUtils.copyProperties(historico, historicoJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}

				result.add(historico);
			}
		}

		return result;
	}

	private List<HistoricoBean> getListaHistoricoBean(List<ViewHistoricoJPA> resultList) {

		return null;
	}

	@Override
	public ViewGestionEnviosJPA getEnvio(String idEnvio) throws BusinessException {
		try {
			ViewGestionEnviosJPA gestionEnvioJPA = new ViewGestionEnviosJPA();
			gestionEnvioJPA.setId(idEnvio);
			return (ViewGestionEnviosJPA) jpa.read(gestionEnvioJPA);
		} catch (DAOException e) {
			throw new BusinessException(e, "errors.organismo.loadOrganismo");
		}
	}

@Override
public List<ViewGestionEnviosJPA> getEnviosLote(String idLote) throws BusinessException {
	try {
		List<ViewGestionEnviosJPA> res = null;
		
		ViewGestionEnviosJPA gestionEnvioJPA = new ViewGestionEnviosJPA();
		gestionEnvioJPA.setIdLote(Integer.parseInt(idLote));
		res = jpa.readAll(0, 0, gestionEnvioJPA);
		return res;
	} catch (DAOException e) {
		throw new BusinessException(e, "errors.organismo.loadOrganismo");
	}
}

	@Override
	public AdjuntoEmailBean loadAdjunto(String idAdjunto, String idEmail) {
		// logger.debug("Cargando adjunto = " + idAdjunto + " | idEmail = "+idEmail);
		AdjuntoEmailBean adjunto = new AdjuntoEmailBean();
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT nombre, contenido,emailId,adjuntoId from view_adjuntos where adjuntoId=" + idAdjunto + " and emailId=" + idEmail);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				adjunto.setNombre(rs.getString(1));
				Blob blob = rs.getBlob(2);
				InputStream is = blob.getBinaryStream();
				// logger.debug("Tama�o blob = "+blob.length());
				adjunto.setEmailId(rs.getInt(3));
				adjunto.setAdjuntoId(rs.getInt(4));
				adjunto.setContenido(org.apache.commons.io.IOUtils.toByteArray(is));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return adjunto;
	}

	@Override
	public AdjuntoEmailBean loadAdjuntoBean(String idAdjunto, String idEmail) {
		ViewAdjuntoEmailJPA adjuntoJPA = new ViewAdjuntoEmailJPA();
		adjuntoJPA.setAdjuntoId(new Integer(idAdjunto));
		adjuntoJPA.setEmailId(new Integer(idEmail));
		try {
			String[] params = new String[] { idAdjunto, idEmail };
			Object obj = (Object) jpa.executeQuery("selectViewAdjuntoEmailByAdjuntoIdEmailIdJPA", params);
			if (obj instanceof ArrayList) {
				ArrayList<ViewAdjuntoEmailJPA> listado = (ArrayList<ViewAdjuntoEmailJPA>) obj;
				if (listado != null && listado.size() > 0) {
					if (listado.get(0) instanceof ViewAdjuntoEmailJPA) {
						adjuntoJPA = (ViewAdjuntoEmailJPA) listado.get(0);
					}
				}
			} else {
				if (obj instanceof ViewAdjuntoEmailJPA) {
					adjuntoJPA = (ViewAdjuntoEmailJPA) obj;
				}
			}

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AdjuntoEmailBean adjunto = new AdjuntoEmailBean();
		adjunto.setNombre(adjuntoJPA.getNombre());
		adjunto.setAdjuntoId(adjuntoJPA.getAdjuntoId());
		adjunto.setEmailId(adjuntoJPA.getEmailId());
		return adjunto;
	}

	@Override
	@Transactional
	public List<GestionEnvioBean> getGestionDeEnviosHist(GestionEnvioBean criterio) throws BusinessException {

		List<GestionEnviosJPA> listJPA = null;

		try {
			if (null != criterio) {

				GestionEnviosJPA criterioJPA = new GestionEnviosJPA();
				if (null != criterio) {
					criterioJPA.setMensajeId(criterio.getMensajeId());
				}

				listJPA = jpa.readAll(0, 0, criterioJPA);

			}

			List<GestionEnvioBean> result = getListGestionEnviosBean(listJPA);

			return result;

		} catch (DAOException e) {
			throw new BusinessException(e, "errors.organismo.getUnidadesOrganizacionales");
		}

	}

	/**
	 * <p>
	 * Convertirmos una lista de GestionEnviosJPA a una lista de GestionEnvioBean
	 * </p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos GestionBean
	 */
	protected List<GestionEnvioBean> getListGestionEnviosBean(List<GestionEnviosJPA> listJPA) throws BusinessException {
		List<GestionEnvioBean> result = null;

		if (listJPA != null && !listJPA.isEmpty()) {
			result = new ArrayList<GestionEnvioBean>();

			for (int indice = 0; indice < listJPA.size(); indice++) {

				GestionEnviosJPA gestionEnvioJPA = listJPA.get(indice);
				GestionEnvioBean gestionEnvio = new GestionEnvioBean();

				try {
					Date defaultValue = null;
					DateConverter converter = new DateConverter(defaultValue);
					ConvertUtils.register(converter, java.util.Date.class);
					BeanUtils.copyProperties(gestionEnvio, gestionEnvioJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}

				result.add(gestionEnvio);
			}
		}

		return result;
	}

	@Override
	@Transactional
	public List<GestionEnvioBean> getTodosGestionEnviosHist(String listIdsMensajes, Integer servicioId) throws BusinessException {

		List<GestionEnvioBean> result = new ArrayList<GestionEnvioBean>();

		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT * FROM TBL_GESTIONENVIOS WHERE MENSAJEID IN ( " + listIdsMensajes + ")");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				GestionEnvioBean gestionEnvio = new GestionEnvioBean();
				gestionEnvio.setAplicacion(rs.getString("APLICACION"));
				gestionEnvio.setCanal(rs.getString("CANAL"));
				gestionEnvio.setServicio(rs.getString("SERVICIO"));
				gestionEnvio.setEstado(rs.getString("ESTADO"));
				gestionEnvio.setDestinatario(rs.getString("DESTINATARIO"));
				gestionEnvio.setAplicacionId(rs.getInt("APLICACIONID"));
				gestionEnvio.setServicioId(rs.getInt("SERVICIOID"));
				gestionEnvio.setCanalId(rs.getInt("CANALID"));
				gestionEnvio.setIdLote(rs.getInt("LOTEENVIOID"));
				gestionEnvio.setNombre(rs.getString("NOMBRE"));
				gestionEnvio.setMensajeId(rs.getInt("MENSAJEID"));
				gestionEnvio.setUltimoEnvio(rs.getTimestamp("ULTIMOENVIO"));
				gestionEnvio.setEstadoId(rs.getInt("ESTADOID"));
				gestionEnvio.setServidorId(rs.getInt("SERVIDORID"));
				gestionEnvio.setCodigoExterno(rs.getString("CODIGOEXTERNO"));
				gestionEnvio.setAnio(rs.getInt("ANIO"));
				gestionEnvio.setMes(rs.getInt("MES"));

				result.add(gestionEnvio);
			}

		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.historico.getGestionEnvios" + servicioId);
		} finally {
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return result;
	}

	@Override
	@Transactional
	public List<GestionEnviosHistoricosJPA> getTodosGestionEnviosJPAHist(Integer servicioId, Integer loteID) throws BusinessException {

		List<GestionEnviosHistoricosJPA> result = new ArrayList<GestionEnviosHistoricosJPA>();

		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con
					.prepareStatement("SELECT * FROM TBL_GESTIONENVIOS GE INNER JOIN" + " TBL_MENSAJES M ON GE.MENSAJEID = M.MENSAJEID INNER JOIN" + " TBL_LOTESENVIOS LE ON LE.LOTEENVIOID = M.LOTEENVIOID" + " WHERE LE.SERVICIOID=" + servicioId + " AND M.LOTEENVIOID=" + loteID + "");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				GestionEnviosHistoricosJPA gestionEnvio = new GestionEnviosHistoricosJPA();
				gestionEnvio.setAplicacion(rs.getString("APLICACION"));
				gestionEnvio.setCanal(rs.getString("CANAL"));
				gestionEnvio.setServicio(rs.getString("SERVICIO"));
				gestionEnvio.setEstado(rs.getString("ESTADO"));
				gestionEnvio.setDestinatario(rs.getString("DESTINATARIO"));
				gestionEnvio.setAplicacionId(rs.getInt("APLICACIONID"));
				gestionEnvio.setServicioId(rs.getInt("SERVICIOID"));
				gestionEnvio.setCanalId(rs.getInt("CANALID"));
				gestionEnvio.setLoteEnvioId(rs.getInt("LOTEENVIOID"));
				gestionEnvio.setNombre(rs.getString("NOMBRE"));
				gestionEnvio.setMensajeId(rs.getInt("MENSAJEID"));
				gestionEnvio.setUltimoEnvio(rs.getTimestamp("ULTIMOENVIO"));
				gestionEnvio.setEstadoId(rs.getInt("ESTADOID"));
				gestionEnvio.setServidorId(rs.getInt("SERVIDORID"));
				gestionEnvio.setCodigoExterno(rs.getString("CODIGOEXTERNO"));
				gestionEnvio.setAnio(rs.getInt("ANIO"));
				gestionEnvio.setMes(rs.getInt("MES"));
				gestionEnvio.setDocUsuario(rs.getString("DOCUSUARIO"));
				gestionEnvio.setCodSIA(rs.getString("CODSIA"));
				gestionEnvio.setCodOrganismo(rs.getString("CODORGANISMO"));
				gestionEnvio.setCodOrganismoPagador(rs.getString("CODORGANISMOPAGADOR"));
				gestionEnvio.setNombreUsuario(rs.getString("NOMBREUSUARIO"));
				gestionEnvio.setIcono(rs.getString("ICONO"));
				gestionEnvio.setSonido(rs.getString("SONIDO"));
				gestionEnvio.setEstadoLote(rs.getString("ESTADOLOTE"));

				result.add(gestionEnvio);
			}

		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.historico.getGestionEnvios" + servicioId);
		} finally {
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginatedList<MensajeBean> getMensajesLotes(int start, int size, String order, String columnSort, String criterio) throws BusinessException {
		List<MensajesJPA> listJPA = null;

		try {

			StringBuffer namedQuery = null;

			String column = "MENSAJEID";

			String namedQueryCount = null;

			// Determinamos la NamedQuery

			namedQuery = new StringBuffer("selectMensajesJPAPorLotes");

			// listJPA = jpa.executeQuery(namedQuery.toString(), param, start, size);
			Query query = em.createNamedQuery(namedQuery.toString());
			query.setParameter("loteId", criterio);
			if (size > 0) {
				query.setFirstResult(start);
				query.setMaxResults(size);
			}
			namedQueryCount = "selectMensajesLoteCount";

			ArrayList<MensajesJPA> pageList = (ArrayList<MensajesJPA>) query.getResultList();
			ArrayList<MensajeBean> listaMensajes = getListMensajesLoteBean(pageList);

			Integer rowcount = getTotalMensajesLote(criterio, em, namedQueryCount);

			PaginatedList<MensajeBean> result = new PaginatedList<MensajeBean>();
			result.setPageList(listaMensajes);
			result.setTotalList(rowcount);

			return result;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw new BusinessException(e, "errors.organismo.getOrganismos");

		}
	}

	private Integer getTotalMensajesLote(String criterio, EntityManager em, String namedQuery) throws BusinessException {
		try {
			Query rowCountQuery = em.createNamedQuery(namedQuery);
			rowCountQuery.setParameter("loteId", criterio);

			Long rowcount = (Long) rowCountQuery.getSingleResult();
			return (rowcount != null) ? rowcount.intValue() : 0;

		} catch (Exception e) {
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}

	@Override
	public GestionEnvioBean getMensaje(String idMensaje) throws BusinessException {
		GestionEnvioBean geBean = new GestionEnvioBean();

		try {
			// Obtenemos los datos generales del envio
			Query query = em.createNamedQuery("selectViewGestionEnviosByIdJPA");
			query.setParameter("mensajeId", Integer.parseInt(idMensaje));
			ViewGestionEnviosJPA gestionEnvio = (ViewGestionEnviosJPA) query.getSingleResult();
			geBean.setAplicacion(gestionEnvio.getAplicacion());
			geBean.setServicio(gestionEnvio.getServicio());
			geBean.setLoteEnvio(gestionEnvio.getLoteEnvio());
			geBean.setIdExterno(gestionEnvio.getIdExterno());
			geBean.setIdLote(gestionEnvio.getIdLote());
			geBean.setMensajeId(Integer.parseInt(idMensaje));
			geBean.setDocUsuario(gestionEnvio.getDocUsuario());
			geBean.setCodSIA(gestionEnvio.getCodSia());
			geBean.setCodOrganismo(gestionEnvio.getCodOrganismo());
			geBean.setCodOrganismoPagador(gestionEnvio.getCodOrganismoPagador());

		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e);
		}
		return geBean;
	}

	@Override
	public boolean isMultidestinatario(Integer idLote) throws BusinessException {
		boolean res = false;
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT MULTIDESTINATARIO from TBL_LOTESENVIOS where LOTEENVIOID=" + idLote);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				if (rs.getInt("MULTIDESTINATARIO") == 1)
					res = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return res;
	}
	
	private boolean isMultidestinatarioPorMensaje(Integer idMensaje) throws BusinessException {
		boolean res = false;
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT MULTIDESTINATARIO from TBL_LOTESENVIOS where LOTEENVIOID= "
					+ "(SELECT LOTEENVIOID FROM TBL_MENSAJES WHERE MENSAJEID = "+idMensaje+")");
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				if (rs.getInt("MULTIDESTINATARIO") == 1)
					res = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginatedList<DestinatariosMensajesBean> getDestinatariosMensajes(int start, int size, String order, String columnSort, String idMensaje) throws BusinessException {
		PaginatedList<DestinatariosMensajesBean> result = new PaginatedList<DestinatariosMensajesBean>();
		MensajesJPA ms = new MensajesJPA();
		ArrayList<DestinatariosMensajesBean> listaDestinatariosMensajes = new ArrayList<DestinatariosMensajesBean>();
		try {
			// traemos el mensaje
			ms.setMensajeId(Integer.parseInt(idMensaje));

			ms = (MensajesJPA) jpa.read(ms);

			if (ms.getTipoMensaje().equals(MENSAJESMS)) {
				DestinatariosMensajesBean dmBean = loadDestinatarioMensaje(ms);
				dmBean.setDestinatario(ms.getTelefono());
				listaDestinatariosMensajes.add(dmBean);
			} else if (ms.getTipoMensaje().equals(MENSAJENOTIFICACION)) {
				DestinatariosMensajesBean dmBean = loadDestinatarioMensaje(ms);
				dmBean.setDestinatario(ms.getNombreUsuario());
				listaDestinatariosMensajes.add(dmBean);
			} else if (ms.getTipoMensaje().equals(MENSAJERECEPCION)) {
				DestinatariosMensajesBean dmBean = loadDestinatarioMensaje(ms);
				dmBean.setDestinatario(ms.getTelefono());
				listaDestinatariosMensajes.add(dmBean);
			} else {
				Query query = em.createNamedQuery("selectDestinatarioByMensajeJPA");
				query.setParameter("mensajeId", idMensaje);
				List<DestinatarioJPA> listaDestinatarios = query.getResultList();
				for (DestinatarioJPA dJPA : listaDestinatarios) {
					DestinatariosMensajesBean dmBean = loadDestinatarioMensaje(ms);
					dmBean.setDestinatario(dJPA.getEmail());
					listaDestinatariosMensajes.add(dmBean);
				}
			}

			// solo hay un destinatario mpor mensaje
			Integer rowcount = 1;

			result.setPageList(listaDestinatariosMensajes);
			result.setTotalList(rowcount);

			return result;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw new BusinessException(e, "errors.organismo.getOrganismos");

		}
	}

	private DestinatariosMensajesBean loadDestinatarioMensaje(MensajesJPA ms) {
		DestinatariosMensajesBean dmBean = new DestinatariosMensajesBean();
		dmBean.setCodigoExterno(ms.getCodigoExterno());
		dmBean.setCreadoPor(ms.getCreadoPor());
		dmBean.setEstado(ms.getEstadoActual());
		dmBean.setFechaCreacion(ms.getFechaCreacion());
		dmBean.setFechaModificacion(ms.getFechaModificacion());
		dmBean.setMensajeId(ms.getMensajeId());
		dmBean.setModificadoPor(ms.getModificadoPor());
		dmBean.setNodo(ms.getNodo());
		dmBean.setUim(ms.getUim());
		dmBean.setTipoMensaje(ms.getTipoMensaje());
		dmBean.setUltimoEnvio(ms.getUltimoEnvio());
		return dmBean;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginatedList<DestinatariosMensajesBean> getDestinatariosMensajesMultidestinatario(int start, int size, String order, String columnSort, String idMensaje) throws BusinessException {
		PaginatedList<DestinatariosMensajesBean> result = new PaginatedList<DestinatariosMensajesBean>();
		MensajesJPA ms = new MensajesJPA();
		ArrayList<DestinatariosMensajesBean> listaDestinatariosMensajes = new ArrayList<DestinatariosMensajesBean>();
		try {
			// traemos el mensaje
			ms.setMensajeId(Integer.parseInt(idMensaje));

			ms = (MensajesJPA) jpa.read(ms);
			Query query = em.createNamedQuery("selectDestinatariosMensajeJPA");
			query.setParameter("mensajeId", idMensaje);
			ArrayList<DestinatariosMensajesJPA> listaDestinatariosJPA = (ArrayList<DestinatariosMensajesJPA>) query.getResultList();
			listaDestinatariosMensajes = getListDestinatariosMensajesBean(listaDestinatariosJPA);

			if (ms.getTipoMensaje().equals(MENSAJENOTIFICACION)) {
				for (DestinatariosMensajesBean dmBean : listaDestinatariosMensajes) {
					query = em.createNamedQuery("selectUsuariosPushByIdJPA");
					query.setParameter("usuarioId", dmBean.getDestinatario());
					String usuario = (String) query.getSingleResult();
					dmBean.setDestinatario(usuario);
				}
			} else if (ms.getTipoMensaje().equals(MENSAJEEMAIL)){
				for (DestinatariosMensajesBean dmBean : listaDestinatariosMensajes) {
					query = em.createNamedQuery("selectDestinatarioJPA");
					query.setParameter("destinatarioId", dmBean.getDestinatario());
					DestinatarioJPA destinatario = (DestinatarioJPA) query.getSingleResult();
					dmBean.setDestinatario(destinatario.getEmail());
				}
			}

			String namedQueryCount = "selectCountDestinatariosMensajeJPA";
			Integer rowcount = getTotalDestinatariosMensaje(idMensaje, em, namedQueryCount);

			result.setPageList(listaDestinatariosMensajes);
			result.setTotalList(rowcount);

			return result;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw new BusinessException(e, "errors.organismo.getOrganismos");

		}
	}

	protected ArrayList<DestinatariosMensajesBean> getListDestinatariosMensajesBean(ArrayList<DestinatariosMensajesJPA> listJPA) throws BusinessException {
		ArrayList<DestinatariosMensajesBean> result = null;

		if (listJPA != null && !listJPA.isEmpty()) {
			result = new ArrayList<DestinatariosMensajesBean>();

			for (int indice = 0; indice < listJPA.size(); indice++) {

				DestinatariosMensajesJPA dmJPA = listJPA.get(indice);
				DestinatariosMensajesBean dm = new DestinatariosMensajesBean();

				try {
					BeanUtils.copyProperties(dm, dmJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}

				result.add(dm);
			}
		}

		return result;
	}

	private Integer getTotalDestinatariosMensaje(String idMensaje, EntityManager em, String namedQuery) throws BusinessException {
		try {
			Query rowCountQuery = em.createNamedQuery(namedQuery);
			rowCountQuery.setParameter("mensajeId", idMensaje);

			Long rowcount = (Long) rowCountQuery.getSingleResult();
			return (rowcount != null) ? rowcount.intValue() : 0;

		} catch (Exception e) {
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}

	@Override
	public DestinatariosMensajesBean getDestinatariosMensajes(String idDestinatariosMensajes) throws BusinessException {
		DestinatariosMensajesBean res = new DestinatariosMensajesBean();
		
		DestinatariosMensajesJPA dmJPA = new DestinatariosMensajesJPA();
		
		try {
			
			dmJPA.setDestinatariosMensajes(Integer.parseInt(idDestinatariosMensajes));

			dmJPA = (DestinatariosMensajesJPA) jpa.read(dmJPA);
			if (null != dmJPA)
				BeanUtils.copyProperties(res, dmJPA);
		} catch (IllegalAccessException e) {
			throw new BusinessException(e);
		} catch (InvocationTargetException e) {
			throw new BusinessException(e);
		}catch (Exception e) {
			throw new BusinessException(e);
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HistoricoBean> getHistoricosMensaje(String idMensaje, String idDestinatariosMensajes) throws BusinessException {
		List<HistoricoBean> result = new ArrayList<HistoricoBean>();
	
		Query query = null;
	
		try {
			if (null == idDestinatariosMensajes){
				query = em.createNamedQuery("selectViewHistoricoByMensajeIdJPA");
				query.setParameter("mensajeId", Integer.parseInt(idMensaje));
				result = getListHistoricoBean((List<ViewHistoricoJPA>) query.getResultList());
				
			}else{
				 query = em.createNamedQuery("selectViewHistoricoByMensajeDestinatarioIdJPA");
				query.setParameter("mensajeId", Integer.parseInt(idMensaje));
				query.setParameter("destinatariosMensajesId", Integer.parseInt(idDestinatariosMensajes));
				result = getListHistoricoBean((List<ViewHistoricoJPA>) query.getResultList());
			}

			return result;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw new BusinessException(e, "errors.organismo.getOrganismos");

		}
	}



	
}
