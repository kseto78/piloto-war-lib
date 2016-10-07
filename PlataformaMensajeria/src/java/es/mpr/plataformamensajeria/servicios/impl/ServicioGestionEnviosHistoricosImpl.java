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
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.dao.DAOException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.mpr.plataformamensajeria.beans.AdjuntoEmailHistoricosBean;
import es.mpr.plataformamensajeria.beans.CanalBean;
import es.mpr.plataformamensajeria.beans.DestinatariosMensajesHistoricosBean;
import es.mpr.plataformamensajeria.beans.DetalleEnvioHistBean;
import es.mpr.plataformamensajeria.beans.DetalleLoteBean;
import es.mpr.plataformamensajeria.beans.GestionEnvioBean;
import es.mpr.plataformamensajeria.beans.GestionEnvioHistoricoBean;
import es.mpr.plataformamensajeria.beans.HistoricoBean;
import es.mpr.plataformamensajeria.beans.HistoricoHistBean;
import es.mpr.plataformamensajeria.beans.MensajeHistoricosBean;
import es.mpr.plataformamensajeria.model.DestinatarioHistoricosJPA;
import es.mpr.plataformamensajeria.model.DestinatarioJPA;
import es.mpr.plataformamensajeria.model.DestinatariosMensajesHistoricosJPA;
import es.mpr.plataformamensajeria.model.GestionEnviosHistoricosJPA;
import es.mpr.plataformamensajeria.model.MensajesHistoricosJPA;
import es.mpr.plataformamensajeria.model.MensajesJPA;
import es.mpr.plataformamensajeria.model.UsuariosPushJPA;
import es.mpr.plataformamensajeria.model.views.ViewAdjuntoEmailHistJPA;
import es.mpr.plataformamensajeria.model.views.ViewCanalJPA;
import es.mpr.plataformamensajeria.model.views.ViewGestionEnviosHistJPA;
import es.mpr.plataformamensajeria.model.views.ViewHistoricoHistJPA;
import es.mpr.plataformamensajeria.model.views.ViewHistoricoSMSJPA;
import es.mpr.plataformamensajeria.model.views.ViewLotesEnviosDetalladaHistJPA;
import es.mpr.plataformamensajeria.model.views.ViewMensajesHistJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioGestionEnviosHistoricos;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>
 * Maneja la persistencia y busqueda de canales a traves de JPA.
 * 
 * @author
 * 
 */
public class ServicioGestionEnviosHistoricosImpl implements ServicioGestionEnviosHistoricos {
	Logger logger = Logger.getLogger(ServicioGestionEnviosHistoricosImpl.class);

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
	 * Convertirmos una lista de ViewGestionEnviosHistJPA a una lista de GestionEnvioHistorifricadoBean
	 * </p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos GestionEnvioHistoricoBean
	 */
	protected List<GestionEnvioHistoricoBean> getListGestionEnvioHistoricoBean(List<ViewGestionEnviosHistJPA> listJPA) throws BusinessException {
		List<GestionEnvioHistoricoBean> result = null;

		if (listJPA != null && !listJPA.isEmpty()) {
			result = new ArrayList<GestionEnvioHistoricoBean>();

			for (int indice = 0; indice < listJPA.size(); indice++) {

				ViewGestionEnviosHistJPA gestionEnvioHistJPA = listJPA.get(indice);
				GestionEnvioHistoricoBean gestionEnvioHist = new GestionEnvioHistoricoBean();

				try {
					Date defaultValue = null;
					DateConverter converter = new DateConverter(defaultValue);
					ConvertUtils.register(converter, java.util.Date.class);
					BeanUtils.copyProperties(gestionEnvioHist, gestionEnvioHistJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}

				result.add(gestionEnvioHist);
			}
		}

		return result;
	}

	@Override
	public List<GestionEnvioHistoricoBean> getGestionDeEnviosHistoricos(GestionEnvioHistoricoBean criterio) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	Connection con;

	@SuppressWarnings("unchecked")
	@Override
	public PaginatedList<GestionEnvioHistoricoBean> getGestionDeEnviosHistoricos(int inicio, Integer pagesize, String order, String columnSort, GestionEnvioHistoricoBean criterio, HttpServletRequest request, boolean porLotes) throws BusinessException {
		// Columna para ordenar
		List<GestionEnvioHistoricoBean> listaAuditoriaBean = new ArrayList<GestionEnvioHistoricoBean>();
		// listaAuditoriaBean
		mapPermisosUsuarioAplicacion = (HashMap<Integer, Integer>) request.getSession().getAttribute(PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES);
		rolUsuario = (String) request.getSession().getAttribute(PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA);
		Hashtable<String, String> columns = new Hashtable<String, String>();
		String column = null;
		if (porLotes) {
			columns.put("1", "APLICACION");
			columns.put("2", "SERVICIO");
			columns.put("3", "LOTEENVIO");
			columns.put("4", "LOTEENVIOID");
			columns.put("5", "ULTIMOENVIO");
			columns.put("6", "ESTADOLOTE");
			if (columnSort == null) {
				columnSort = "4"; // LOTEID
				column = columns.get(columnSort);
			} else if (columnSort.equals("5")) {
				if (order == null || order.equals("1"))
					column = "ULTIMOENVIO ";
				else
					column = "LOTEENVIOID desc, ULTIMOENVIO ";

			} else {
				column = columns.get(columnSort);
			}
		} else {
			columns.put("1", "APLICACION");
			columns.put("2", "SERVICIO");
			columns.put("3", "LOTEENVIO");
			columns.put("4", "LOTEENVIOID");
			columns.put("5", "MENSAJEID");
			columns.put("6", "ULTIMOENVIO");
			columns.put("7", "ESTADO");
			if (columnSort == null) {
				columnSort = "6"; // Fecha
			}

			column = columns.get(columnSort);
		}

		if (column == null) {
			column = "LOTEENVIOID desc, ULTIMOENVIO";
		}
		if (order == null) {
			order = "2";
		}

		PaginatedList<GestionEnvioHistoricoBean> result = null;
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
				cstmt = con.prepareCall("{call PROC_GESTIONENVIOS_LOTES_HIST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				;
			} else {
				cstmt = con.prepareCall("{call PROC_GESTIONENVIOS_HIST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			}

			cstmt.setInt(1, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getAplicacionId()) ? criterio.getAplicacionId() : 0));
			cstmt.setInt(2, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getServidorId()) ? criterio.getServidorId() : 0));
			cstmt.setInt(3, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getCanalId()) ? criterio.getCanalId() : 0));
			cstmt.setInt(4, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getServicioId()) ? criterio.getServicioId() : 0));
			cstmt.setInt(5, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getEstadoId()) ? criterio.getEstadoId() : 0));

			cstmt.setInt(6, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getIdLote()) ? criterio.getIdLote() : 0));
			cstmt.setString(7, (criterio != null && !PlataformaMensajeriaUtil.isEmpty(criterio.getDestinatario()) ? criterio.getDestinatario() : null));
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
			cstmt.setInt(19, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getMensajeId()) ? criterio.getMensajeId() : 0));
			cstmt.registerOutParameter(20, OracleTypes.CURSOR);
			cstmt.executeUpdate();

			rs = (ResultSet) cstmt.getObject(20);
			while (rs != null && rs.next()) {

				GestionEnvioHistoricoBean envio = new GestionEnvioHistoricoBean();
				// APLICACION, SERVICIO, LOTEENVIO, ESTADO, DESTINATARIO, CANALID, APLICACIONID,
				// SERVICIOID, EMAILID, ULTIMOENVIO, ESTADOID, LOTEENVIOID, SERVIDORID, IDEXTERNO, SMSID, ENVIOID
				// DOCUSUARIO, CODSIA, CODORGANISMO, CODORGANISMOPAGADOR
				String aplicacion = rs.getString("APLICACION");

				String servicio = rs.getString("SERVICIO");
				String loteEnvio = rs.getString("LOTEENVIO");

				Integer canalId = rs.getInt("CANALID");
				Integer aplicacionId = rs.getInt("APLICACIONID");
				Integer servicioId = rs.getInt("SERVICIOID");

				String ultimoEnvio = rs.getString("ULTIMOENVIO");
				Integer loteEnvioId = rs.getInt("LOTEENVIOID");

				String codOrganismo = rs.getString("CODORGANISMO");
				String codOrganismoPagador = rs.getString("CODORGANISMOPAGADOR");
				String nombreUsuario = rs.getString("NOMBREUSUARIO");

				Integer multidestinatario = rs.getInt("MULTIDESTINATARIO");

				String destinatario = "";
				Integer mensId = 0;
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
					mensId = rs.getInt("MENSAJEID");
					envioId = rs.getString("ENVIOID");
					idExterno = rs.getString("IDEXTERNO");
					docUsuario = rs.getString("DOCUSUARIO");
					codSIA = rs.getString("CODSIA");
					icono = rs.getString("ICONO");
					sonido = rs.getString("SONIDO");

					estadoId = rs.getInt("ESTADOID");
					servidorId = rs.getInt("SERVIDORID");
				} else {
					estadoId = rs.getInt("ESTADOID");
					servidorId = rs.getInt("SERVIDORID");
				}

				envio.setAplicacion(aplicacion);
				envio.setServicio(servicio);
				envio.setLoteEnvio(loteEnvio);
				envio.setEstado(estado);
				envio.setDestinatario(destinatario);
				envio.setCanalId(canalId);
				envio.setAplicacionId(aplicacionId);
				envio.setServicioId(servicioId);
				envio.setMensajeId(mensId);
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
		} finally {
			PlataformaMensajeriaUtil.closeSQLStatements(con, null, rs, cstmt);
		}

		result = new PaginatedList<GestionEnvioHistoricoBean>();
		result.setPageList(listaAuditoriaBean);
		result.setTotalList(rowcount);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginatedList<GestionEnvioHistoricoBean> getGestionDeEnviosDestinatariosHistoricos(int inicio, Integer pagesize, String order, String columnSort, GestionEnvioHistoricoBean criterio, HttpServletRequest request) throws BusinessException {
		List<GestionEnvioHistoricoBean> listaAuditoriaBean = new ArrayList<GestionEnvioHistoricoBean>();
		// listaAuditoriaBean
		mapPermisosUsuarioAplicacion = (HashMap<Integer, Integer>) request.getSession().getAttribute(PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES);
		rolUsuario = (String) request.getSession().getAttribute(PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA);
		Hashtable<String, String> columns = new Hashtable<String, String>();
		columns.put("1", "APLICACION");
		columns.put("2", "SERVICIO");
		columns.put("3", "LOTEENVIO");
		columns.put("4", "LOTEENVIOID");
		columns.put("5", "MENSAJEID");
		columns.put("6", "ULTIMOENVIO");
		columns.put("7", "ESTADO");
		columns.put("8", "DESTINATARIO");

		if (columnSort == null) {
			columnSort = "4"; //LOTEENVIOID 
		}
		String column = columns.get(columnSort);
		if (column == null) {
			column = "MENSAJEID";
		}
		if (order == null) {
			order = "2";
		}

		PaginatedList<GestionEnvioHistoricoBean> result = null;
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
		Integer rowcount = getTotalGestionEnvioDestinatariosHistoricos(con, criterio, request);

		ResultSet rs = null;
		CallableStatement cstmt = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			cstmt = con.prepareCall("{call PROC_GESTIONENVIOS_DEST_HIST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
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
			cstmt.setInt(19, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getMensajeId()) ? criterio.getMensajeId() : 0));
			cstmt.registerOutParameter(20, OracleTypes.CURSOR);
			cstmt.executeUpdate();

			rs = (ResultSet) cstmt.getObject(20);
			while (rs != null && rs.next()) {

				GestionEnvioHistoricoBean envio = new GestionEnvioHistoricoBean();

				String aplicacion = rs.getString("APLICACION");
				String servicio = rs.getString("SERVICIO");
				String loteEnvio = rs.getString("LOTEENVIO");
				String estado = rs.getString("ESTADO");
				String destinatario = rs.getString("DESTINATARIO");
				Integer canalId = rs.getInt("CANALID");
				Integer aplicacionId = rs.getInt("APLICACIONID");
				Integer servicioId = rs.getInt("SERVICIOID");
				Integer mensajeId = rs.getInt("MENSAJEID");
				// Cambio Ra�l 2013-10-16
				String ultimoEnvio = rs.getString("ULTIMOENVIO");

				Integer loteEnvioId = rs.getInt("LOTEENVIOID");

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
				envio.setIdLote(loteEnvioId);

				envio.setVistaEnviosId(3);
				envio.setDestinatariosMensajes(destinatariosMensajes);

				listaAuditoriaBean.add(envio);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			PlataformaMensajeriaUtil.closeSQLStatements(con, null, rs, cstmt);
		}

		result = new PaginatedList<GestionEnvioHistoricoBean>();
		result.setPageList(listaAuditoriaBean);
		result.setTotalList(rowcount);
		return result;

	}

	public Integer getTotalGestionEnvioDestinatariosHistoricos(Connection con, GestionEnvioHistoricoBean criterio, HttpServletRequest request) {
		mapPermisosUsuarioAplicacion = (HashMap<Integer, Integer>) request.getSession().getAttribute(PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES);
		ResultSet rs = null;
		Integer i = new Integer(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		CallableStatement cstmt = null;
		try {
			cstmt = con.prepareCall("{call PROC_GESTIONENVIOS_CON_DEST_HI(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

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

	public Integer getTotalGestionEnvio(Connection con, GestionEnvioHistoricoBean criterio, HttpServletRequest request) {
		mapPermisosUsuarioAplicacion = (HashMap<Integer, Integer>) request.getSession().getAttribute(PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES);
		ResultSet rs = null;
		Integer i = new Integer(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		CallableStatement cstmt = null;
		try {
			cstmt = con.prepareCall("{call PROC_GESTIONENVIOS_CONT_HIST(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getAplicacionId()) ? criterio.getAplicacionId() : 0));
			cstmt.setInt(2, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getServidorId()) ? criterio.getServidorId() : 0));
			cstmt.setInt(3, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getCanalId()) ? criterio.getCanalId() : 0));
			cstmt.setInt(4, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getServicioId()) ? criterio.getServicioId() : 0));
			cstmt.setInt(5, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getEstadoId()) ? criterio.getEstadoId() : 0));
			cstmt.setInt(6, (criterio != null && !PlataformaMensajeriaUtil.isEmptyNumber(criterio.getIdLote()) ? criterio.getIdLote() : 0));
			cstmt.setString(7, (criterio != null && !PlataformaMensajeriaUtil.isEmpty(criterio.getDestinatario()) ? criterio.getDestinatario() : null));
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

	public Integer getTotalLotesGestionEnvio(Connection con, GestionEnvioHistoricoBean criterio, HttpServletRequest request) {
		mapPermisosUsuarioAplicacion = (HashMap<Integer, Integer>) request.getSession().getAttribute(PlataformaMensajeriaUtil.MAP_PERMISOS_APLICACIONES);
		ResultSet rs = null;
		Integer i = new Integer(0);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		CallableStatement cstmt = null;
		try {
			cstmt = con.prepareCall("{call PROC_GESTIONENVIOS_LOT_HIST_CO(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

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
	public DetalleEnvioHistBean loadMensaje(String idMensaje) throws BusinessException {
		DetalleEnvioHistBean detalleEmail = new DetalleEnvioHistBean();

		ViewMensajesHistJPA mensaje = new ViewMensajesHistJPA();
		mensaje.setMensajeId(Integer.parseInt(idMensaje));
		try {
			mensaje = (ViewMensajesHistJPA) jpa.read(mensaje);

			// Obtenemos los adjuntos del Email
			Query query = em.createNamedQuery("selectViewAdjuntoEmailByEmailIdHistJPA");
			query.setParameter("emailId", Integer.parseInt(idMensaje));
			List<AdjuntoEmailHistoricosBean> listaAdjuntos = getListAdjuntoEmailBean((List<ViewAdjuntoEmailHistJPA>) query.getResultList());
			Query queryImagenes = em.createNamedQuery("selectViewImagenEmailByEmailIdHistJPA");
			queryImagenes.setParameter("emailId", Integer.parseInt(idMensaje));
			List<AdjuntoEmailHistoricosBean> listaImagenes = getListAdjuntoEmailBean((List<ViewAdjuntoEmailHistJPA>) queryImagenes.getResultList());

			// Obtenemos los datos generales del envio
			query = em.createNamedQuery("selectViewGestionEnviosByIdHistJPA");
			query.setParameter("mensajeId", Integer.parseInt(idMensaje));
			ViewGestionEnviosHistJPA gestionEnvioHist = (ViewGestionEnviosHistJPA) query.getSingleResult();

			detalleEmail.setNombreAplicacion(gestionEnvioHist.getAplicacion());
			detalleEmail.setNombreServicio(gestionEnvioHist.getServicio());
			detalleEmail.setNombreLoteEnvio(gestionEnvioHist.getLoteEnvio());
			detalleEmail.setIdExterno(gestionEnvioHist.getIdExterno());
			detalleEmail.setIdLote(gestionEnvioHist.getIdLote());
			detalleEmail.setMensajeId(Integer.parseInt(idMensaje));
			query = em.createNamedQuery("selectMensajeHistoricosJPA");
			query.setParameter("mensajeId", Integer.parseInt(idMensaje));
			MensajesHistoricosJPA emailJPA = (MensajesHistoricosJPA) query.getSingleResult();
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

			detalleEmail.setFechaHistorificacion(mensaje.getFechaHistorificacion());
			detalleEmail.setDocUsuario(emailJPA.getDocUsuario());
			detalleEmail.setCodSIA(emailJPA.getCodSIA());
			detalleEmail.setCodOrganismo(emailJPA.getCodOrganismo());
			detalleEmail.setCodOrganismoPagador(emailJPA.getCodOrganismoPagador());
			detalleEmail.setNombreUsuario(emailJPA.getNombreUsuario());
			detalleEmail.setIcono(emailJPA.getIcono());
			detalleEmail.setSonido(emailJPA.getSonido());
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return detalleEmail;
	}

	/**
	 * <p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos
	 */
	@Transactional
	protected List<AdjuntoEmailHistoricosBean> getListAdjuntoEmailBean(List<ViewAdjuntoEmailHistJPA> listJPA) throws BusinessException {
		List<AdjuntoEmailHistoricosBean> result = null;

		if (listJPA != null && !listJPA.isEmpty()) {
			result = new ArrayList<AdjuntoEmailHistoricosBean>();

			for (int indice = 0; indice < listJPA.size(); indice++) {

				ViewAdjuntoEmailHistJPA adjuntoJPA = listJPA.get(indice);
				AdjuntoEmailHistoricosBean adjunto = new AdjuntoEmailHistoricosBean();
				adjunto.setAdjuntoId(adjuntoJPA.getAdjuntoId());
				adjunto.setCreadoPor(adjuntoJPA.getCreadoPor());
				adjunto.setEmailId(adjuntoJPA.getEmailId());
				adjunto.setFechaCreacion(adjuntoJPA.getFechaCreacion());
				adjunto.setFechaModificacion(adjuntoJPA.getFechaModificacion());
				adjunto.setModificadoPor(adjuntoJPA.getModificadoPor());
				adjunto.setNombre(adjuntoJPA.getNombre());
				adjunto.setFechaHistorificacion(adjuntoJPA.getFechaHistorificacion());
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
	protected List<HistoricoHistBean> getListHistoricoBean(List<ViewHistoricoHistJPA> listJPA) throws BusinessException {
		List<HistoricoHistBean> result = null;

		if (listJPA != null && !listJPA.isEmpty()) {
			result = new ArrayList<HistoricoHistBean>();

			for (int indice = 0; indice < listJPA.size(); indice++) {

				ViewHistoricoHistJPA historicoJPA = listJPA.get(indice);
				HistoricoHistBean historico = new HistoricoHistBean();

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

	@Override
	public ViewGestionEnviosHistJPA getEnvioHistorico(String idEnvio) throws BusinessException {
		try {
			ViewGestionEnviosHistJPA gestionEnvioHistJPA = new ViewGestionEnviosHistJPA();
			gestionEnvioHistJPA.setId(idEnvio);
			return (ViewGestionEnviosHistJPA) jpa.read(gestionEnvioHistJPA);
		} catch (DAOException e) {
			throw new BusinessException(e, "errors.organismo.loadOrganismo");
		}
	}

	@Override
	public AdjuntoEmailHistoricosBean loadAdjunto(String idAdjunto, String idEmail) {
		// logger.debug("Cargando adjunto = " + idAdjunto + " | idEmail = "+idEmail);
		AdjuntoEmailHistoricosBean adjunto = new AdjuntoEmailHistoricosBean();
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT nombre, contenido,emailId,adjuntoId from view_adjuntos_hist where adjuntoId=" + idAdjunto + " and emailId=" + idEmail);
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
	public AdjuntoEmailHistoricosBean loadAdjuntoBean(String idAdjunto, String idEmail) {
		ViewAdjuntoEmailHistJPA adjuntoJPA = new ViewAdjuntoEmailHistJPA();
		adjuntoJPA.setAdjuntoId(new Integer(idAdjunto));
		adjuntoJPA.setEmailId(new Integer(idEmail));
		try {
			String[] params = new String[] { idAdjunto, idEmail };
			Object obj = (Object) jpa.executeQuery("selectViewAdjuntoEmailByAdjuntoIdEmailIdHistJPA", params);
			if (obj instanceof ArrayList) {
				ArrayList<ViewAdjuntoEmailHistJPA> listado = (ArrayList<ViewAdjuntoEmailHistJPA>) obj;
				if (listado != null && listado.size() > 0) {
					if (listado.get(0) instanceof ViewAdjuntoEmailHistJPA) {
						adjuntoJPA = (ViewAdjuntoEmailHistJPA) listado.get(0);
					}
				}
			} else {
				if (obj instanceof ViewAdjuntoEmailHistJPA) {
					adjuntoJPA = (ViewAdjuntoEmailHistJPA) obj;
				}
			}

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AdjuntoEmailHistoricosBean adjunto = new AdjuntoEmailHistoricosBean();
		adjunto.setNombre(adjuntoJPA.getNombre());
		adjunto.setAdjuntoId(adjuntoJPA.getAdjuntoId());
		adjunto.setEmailId(adjuntoJPA.getEmailId());
		return adjunto;
	}

	@Override
	@Transactional
	public List<GestionEnvioHistoricoBean> getTodosGestionEnviosCons(String listIdsMensajes, Integer servicioId) throws BusinessException {

		List<GestionEnvioHistoricoBean> result = new ArrayList<GestionEnvioHistoricoBean>();

		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT * FROM TBL_GESTIONENVIOS_HIST WHERE MENSAJEID IN ( " + listIdsMensajes + ")");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				GestionEnvioHistoricoBean gestionEnvio = new GestionEnvioHistoricoBean();
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
				gestionEnvio.setFechaHistorificacion(rs.getTimestamp("FECHAHISTORIFICACION"));

				result.add(gestionEnvio);
			}

		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.cons.getGestionEnvios" + servicioId);
		} finally {
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return result;
	}

	@Override
	@Transactional
	public List<GestionEnviosHistoricosJPA> getTodosGestionEnviosJPACons(Integer servicioId, Integer loteID) throws BusinessException {

		List<GestionEnviosHistoricosJPA> result = new ArrayList<GestionEnviosHistoricosJPA>();

		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con
					.prepareStatement("SELECT * FROM TBL_GESTIONENVIOS_HIST GEH INNER JOIN" + " TBL_MENSAJES_HIST MH ON GEH.MENSAJEID = MH.MENSAJEID INNER JOIN" + " TBL_LOTESENVIOS_HIST LEH ON LEH.LOTEENVIOID = MH.LOTEENVIOID" + " WHERE LEH.SERVICIOID=" + servicioId + " AND MH.LOTEENVIOID=" + loteID + "");

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
				gestionEnvio.setFechaHistorificacion(rs.getTimestamp("FECHAHISTORIFICACION"));
				gestionEnvio.setDocUsuario(rs.getString("DOCUSUARIO"));
				gestionEnvio.setCodSIA(rs.getString("CODSIA"));
				gestionEnvio.setCodOrganismo(rs.getString("CODORGANISMO"));
				gestionEnvio.setCodOrganismoPagador(rs.getString("CODORGANISMOPAGADOR"));

				result.add(gestionEnvio);
			}

		} catch (SQLException e) {
			throw new BusinessException(e, "errors.job.cons.getGestionEnvios" + servicioId);
		} finally {
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DetalleLoteBean loadLote(String idLote) throws BusinessException {
		DetalleLoteBean detalleLote = new DetalleLoteBean();

		ViewLotesEnviosDetalladaHistJPA lote = new ViewLotesEnviosDetalladaHistJPA();
		lote.setLoteEnvioId(Integer.parseInt(idLote));
		try {
			lote = (ViewLotesEnviosDetalladaHistJPA) jpa.read(lote);

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

	@SuppressWarnings("unchecked")
	@Override
	public PaginatedList<MensajeHistoricosBean> getMensajesLotes(int start, int size, String order, String columnSort, String criterio) throws BusinessException {
		List<MensajesHistoricosJPA> listJPA = null;

		try {

			StringBuffer namedQuery = null;

			String column = "MENSAJEID";

			String namedQueryCount = null;

			// Determinamos la NamedQuery

			namedQuery = new StringBuffer("selectMensajesHistoricosJPAPorLotes");

			Query query = em.createNamedQuery(namedQuery.toString());
			query.setParameter("loteId", criterio);
			if (size > 0) {
				query.setFirstResult(start);
				query.setMaxResults(size);
			}
			namedQueryCount = "selectMensajesLoteHistoricosCount";

			ArrayList<MensajesHistoricosJPA> pageList = (ArrayList<MensajesHistoricosJPA>) query.getResultList();
			ArrayList<MensajeHistoricosBean> listaMensajes = getListMensajesLoteBean(pageList);

			Integer rowcount = getTotalMensajesLote(criterio, em, namedQueryCount);

			PaginatedList<MensajeHistoricosBean> result = new PaginatedList<MensajeHistoricosBean>();
			result.setPageList(listaMensajes);
			result.setTotalList(rowcount);

			return result;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw new BusinessException(e, "errors.organismo.getOrganismos");

		}
	}

	protected ArrayList<MensajeHistoricosBean> getListMensajesLoteBean(List<MensajesHistoricosJPA> listJPA) throws BusinessException {
		ArrayList<MensajeHistoricosBean> result = null;

		if (listJPA != null && !listJPA.isEmpty()) {
			result = new ArrayList<MensajeHistoricosBean>();

			for (int indice = 0; indice < listJPA.size(); indice++) {

				MensajesHistoricosJPA mensajeJPA = listJPA.get(indice);
				MensajeHistoricosBean mensajeBean = new MensajeHistoricosBean();

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
	public boolean isMultidestinatario(Integer idLote) throws BusinessException {
		boolean res = false;
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("SELECT MULTIDESTINATARIO from TBL_LOTESENVIOS_HIST where LOTEENVIOID=" + idLote);
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
	public PaginatedList<DestinatariosMensajesHistoricosBean> getDestinatariosMensajesMultidestinatario(int start, int size, String order, String columnSort, String idMensaje) throws BusinessException {
		PaginatedList<DestinatariosMensajesHistoricosBean> result = new PaginatedList<DestinatariosMensajesHistoricosBean>();
		MensajesJPA ms = new MensajesJPA();
		ArrayList<DestinatariosMensajesHistoricosBean> listaDestinatariosMensajes = new ArrayList<DestinatariosMensajesHistoricosBean>();
		try {
			// traemos el mensaje
			ms.setMensajeId(Integer.parseInt(idMensaje));

			ms = (MensajesJPA) jpa.read(ms);
			Query query = em.createNamedQuery("selectDestinatariosMensajeHistoricosJPA");
			query.setParameter("mensajeId", idMensaje);
			ArrayList<DestinatariosMensajesHistoricosJPA> listaDestinatariosJPA = (ArrayList<DestinatariosMensajesHistoricosJPA>) query.getResultList();
			listaDestinatariosMensajes = getListDestinatariosMensajesHistoricosBean(listaDestinatariosJPA);

			if (ms.getTipoMensaje().equals(MENSAJENOTIFICACION)) {
				for (DestinatariosMensajesHistoricosBean dmBean : listaDestinatariosMensajes) {
					query = em.createNamedQuery("selectUsuariosPushByIdJPA");
					query.setParameter("usuarioId", dmBean.getDestinatario());
					UsuariosPushJPA usuario = (UsuariosPushJPA) query.getSingleResult();
					dmBean.setDestinatario(usuario.getNombreUsuario());
				}
			} else if (ms.getTipoMensaje().equals(MENSAJEEMAIL)) {
				for (DestinatariosMensajesHistoricosBean dmBean : listaDestinatariosMensajes) {
					query = em.createNamedQuery("selectDestinatarioHistoricosJPA");
					query.setParameter("destinatarioId", dmBean.getDestinatario());
					DestinatarioJPA destinatario = (DestinatarioJPA) query.getSingleResult();
					dmBean.setDestinatario(destinatario.getEmail());
				}
			}

			String namedQueryCount = "selectCountDestinatariosMensajeHistoricosJPA";
			Integer rowcount = getTotalDestinatariosMensaje(idMensaje, em, namedQueryCount);

			result.setPageList(listaDestinatariosMensajes);
			result.setTotalList(rowcount);

			return result;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw new BusinessException(e, "errors.organismo.getOrganismos");

		}

	}

	protected ArrayList<DestinatariosMensajesHistoricosBean> getListDestinatariosMensajesHistoricosBean(ArrayList<DestinatariosMensajesHistoricosJPA> listJPA) throws BusinessException {
		ArrayList<DestinatariosMensajesHistoricosBean> result = null;

		if (listJPA != null && !listJPA.isEmpty()) {
			result = new ArrayList<DestinatariosMensajesHistoricosBean>();

			for (int indice = 0; indice < listJPA.size(); indice++) {

				DestinatariosMensajesHistoricosJPA dmJPA = listJPA.get(indice);
				DestinatariosMensajesHistoricosBean dm = new DestinatariosMensajesHistoricosBean();

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
			rowCountQuery.setParameter("idMensaje", idMensaje);

			Long rowcount = (Long) rowCountQuery.getSingleResult();
			return (rowcount != null) ? rowcount.intValue() : 0;

		} catch (Exception e) {
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public PaginatedList<DestinatariosMensajesHistoricosBean> getDestinatariosMensajes(int start, int size, String order, String columnSort, String idMensaje) throws BusinessException {
		PaginatedList<DestinatariosMensajesHistoricosBean> result = new PaginatedList<DestinatariosMensajesHistoricosBean>();
		MensajesHistoricosJPA ms = new MensajesHistoricosJPA();
		ArrayList<DestinatariosMensajesHistoricosBean> listaDestinatariosMensajes = new ArrayList<DestinatariosMensajesHistoricosBean>();
		try {
			// traemos el mensaje
			ms.setMensajeId(Integer.parseInt(idMensaje));

			ms = (MensajesHistoricosJPA) jpa.read(ms);

			if (ms.getTipoMensaje().equals(MENSAJESMS)) {
				DestinatariosMensajesHistoricosBean dmBean = loadDestinatarioMensaje(ms);
				dmBean.setDestinatario(ms.getTelefono());
				listaDestinatariosMensajes.add(dmBean);
			} else if (ms.getTipoMensaje().equals(MENSAJENOTIFICACION)) {
				DestinatariosMensajesHistoricosBean dmBean = loadDestinatarioMensaje(ms);
				dmBean.setDestinatario(ms.getNombreUsuario());
				listaDestinatariosMensajes.add(dmBean);
			} else if (ms.getTipoMensaje().equals(MENSAJERECEPCION)) {
				DestinatariosMensajesHistoricosBean dmBean = loadDestinatarioMensaje(ms);
				dmBean.setDestinatario(ms.getTelefono());
				listaDestinatariosMensajes.add(dmBean);
			} else {
				Query query = em.createNamedQuery("selectDestinatarioByMensajeHistoricosJPA");
				query.setParameter("mensajeId", idMensaje);
				List<DestinatarioHistoricosJPA> listaDestinatarios = query.getResultList();
				for (DestinatarioHistoricosJPA dJPA : listaDestinatarios) {
					DestinatariosMensajesHistoricosBean dmBean = loadDestinatarioMensaje(ms);
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

	private DestinatariosMensajesHistoricosBean loadDestinatarioMensaje(MensajesHistoricosJPA ms) {
		DestinatariosMensajesHistoricosBean dmBean = new DestinatariosMensajesHistoricosBean();
		dmBean.setCodigoExterno(ms.getCodigoExterno());
		dmBean.setCreadoPor(ms.getCreadoPor());
		dmBean.setEstado(ms.getEstadoActual());
		dmBean.setFechaCreacion(ms.getFechaCreacion());
		dmBean.setFechaModificacion(ms.getFechaModificacion());
		dmBean.setMensajeId(ms.getMensajeId());
		dmBean.setModificadoPor(ms.getModificadoPor());
		dmBean.setUim(ms.getUim());
		dmBean.setTipoMensaje(ms.getTipoMensaje());
		dmBean.setUltimoEnvio(ms.getUltimoEnvio());
		return dmBean;
	}

	@Override
	public DestinatariosMensajesHistoricosBean getDestinatariosMensajesHistoricos(String idDestinatariosMensajes) throws BusinessException {
		DestinatariosMensajesHistoricosBean res = new DestinatariosMensajesHistoricosBean();

		DestinatariosMensajesHistoricosJPA dmJPA = new DestinatariosMensajesHistoricosJPA();

		try {

			dmJPA.setDestinatariosMensajes(Integer.parseInt(idDestinatariosMensajes));

			dmJPA = (DestinatariosMensajesHistoricosJPA) jpa.read(dmJPA);
			if (null != dmJPA)
				BeanUtils.copyProperties(res, dmJPA);
		} catch (IllegalAccessException e) {
			throw new BusinessException(e);
		} catch (InvocationTargetException e) {
			throw new BusinessException(e);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return res;
	}

	@Override
	public GestionEnvioHistoricoBean getMensajeHistorico(String idMensaje) throws BusinessException {
		GestionEnvioHistoricoBean geBean = new GestionEnvioHistoricoBean();

		try {
			// Obtenemos los datos generales del envio
			Query query = em.createNamedQuery("selectViewGestionEnviosByIdHistJPA");
			query.setParameter("mensajeId", Integer.parseInt(idMensaje));
			ViewGestionEnviosHistJPA gestionEnvio = (ViewGestionEnviosHistJPA) query.getSingleResult();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<HistoricoHistBean> getHistoricosHistMensaje(String idMensaje, String idDestinatariosMensajes) throws BusinessException {
		List<HistoricoHistBean> result = new ArrayList<HistoricoHistBean>();

		Query query = null;

		try {
			if (null == idDestinatariosMensajes) {
				query = em.createNamedQuery("selectViewHistoricoByMensajeIdHistJPA");
				query.setParameter("mensajeId", Integer.parseInt(idMensaje));
				result = getListHistoricoBean((List<ViewHistoricoHistJPA>) query.getResultList());

			} else {
				query = em.createNamedQuery("selectViewHistoricoByMensajeDestinatarioIdHistJPA");
				query.setParameter("mensajeId", Integer.parseInt(idMensaje));
				query.setParameter("destinatariosMensajesId", Integer.parseInt(idDestinatariosMensajes));
				result = getListHistoricoBean((List<ViewHistoricoHistJPA>) query.getResultList());
			}

			return result;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw new BusinessException(e, "errors.organismo.getOrganismos");

		}
	}
}
