package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.dao.DAOException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.beans.ServidoresOrganismosBean;
import es.mpr.plataformamensajeria.model.PlanificacionJPA;
import es.mpr.plataformamensajeria.model.ServidoresJPA;
import es.mpr.plataformamensajeria.model.ServidoresOrganismosJPA;
import es.mpr.plataformamensajeria.model.views.ViewServidoresJPA;
import es.mpr.plataformamensajeria.model.views.ViewServidoresOrganismosJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.template.web.action.admin.UsuariosForm;

/**
 * <p>
 * Maneja la persistencia y b&uacute;squeda de servidores a traves de JPA.
 * 
 * @author
 * 
 */
public class ServicioServidorImpl implements ServicioServidor {

	private IPaginationJPADAO jpa;
	protected EntityManager em;

	public EntityManager getEntityManager() {
		return em;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
	}

	private static final Integer TIPO_SERVIDOR = new Integer(2);
	private static final Integer POR_DEFECTO = new Integer(1);
	private static final String COMBO_SERVIDORES_BY_PROPIETARIO = "select distinct servidor.servidorId, servidor.nombre from tbl_servidores servidor, TBL_SERVIDORES_SERVICIOS ss , tbl_servicios s where  servidor.servidorId = ss.servidorId and s.servicioId = ss.servicioId and s.aplicacionId in(select ua.aplicacionid from tbl_usuarios_aplicaciones ua where ua.usuarioid=?)  and (servidor.eliminado is null or servidor.eliminado = 'N') order by servidor.nombre";
	private static final String COMBO_SERVIDORES_ALL = "select servidor.servidorId, servidor.nombre from tbl_servidores servidor where (servidor.eliminado is null or servidor.eliminado = 'N') order by servidor.nombre";

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

	@Override
	public List<ServidorBean> getServidores(ServidorBean criterio) throws BusinessException {
		List<ServidoresJPA> listJPA = null;

		try {

			if (criterio == null || criterio.getNombre() == null || criterio.getNombre().equals("")) {

				ServidoresJPA criterioJPA = new ServidoresJPA();
				criterioJPA.setServidorId(criterio.getServidorId());
				criterioJPA.setNombre(criterio.getNombre());

				listJPA = jpa.readAll(0, 0, criterioJPA);
			} else {
				String[] param = new String[] { "%" + criterio.getNombre().toUpperCase() + "%" };
				listJPA = jpa.executeQuery("selectServidorJPA", param);
			}

			List<ServidorBean> result = getListServidorBean(listJPA);

			return result;

		} catch (DAOException e) {
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}

	@Override
	public List<ServidorBean> getServidores() throws BusinessException {
		List<ViewServidoresJPA> listJPA = null;

		try {
			ViewServidoresJPA criterioJPA = new ViewServidoresJPA();
			listJPA = jpa.executeQuery("selectServidores", null);
			List<ServidorBean> result = getListViewServidorBean(listJPA);

			return result;

		} catch (DAOException e) {
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}

	@Override
	public List<ServidorBean> getServidoresYProveedores() throws BusinessException {
		List<ViewServidoresJPA> listJPA = null;

		try {
			ViewServidoresJPA criterioJPA = new ViewServidoresJPA();
			criterioJPA.setTipo(new Integer(3));// COGEMOS TODOS
			listJPA = jpa.readAll(0, 0, criterioJPA);
			List<ServidorBean> result = getListViewServidorBean(listJPA);

			return result;

		} catch (DAOException e) {
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}

	@Override
	public List<ServidorBean> getServidoresYProveedores(String tipoServidor) throws BusinessException {
		List<ViewServidoresJPA> listJPA = null;

		try {
			String[] param = new String[] { tipoServidor };
			listJPA = jpa.executeQuery("selectServidoresByTipo2", param);

			List<ServidorBean> result = getListViewServidorBean(listJPA);

			return result;

		} catch (DAOException e) {
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}

	@Override
	public List<ServidorBean> getServidoresYProveedores(String rolUsuario, Integer idUsuario) throws BusinessException {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = PlataformaMensajeriaUtil.getConexion();
		List<ServidorBean> listBean = new ArrayList<ServidorBean>();
		try {
			if (rolUsuario != null && rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR)) {
				pstmt = con.prepareStatement(COMBO_SERVIDORES_ALL);
			} else {
				pstmt = con.prepareStatement(COMBO_SERVIDORES_BY_PROPIETARIO);
				pstmt.setInt(1, idUsuario);
			}
			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
				String idServidor = rs.getString(1);
				String nombre = rs.getString(2);
				ServidorBean servidor = new ServidorBean();
				servidor.setServidorId(new Long(idServidor));
				servidor.setNombre(nombre);
				listBean.add(servidor);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listBean;
	}

	//
	@Override
	public List<ServidorBean> getServidoresByTipoPlanificacion(String tipo) throws BusinessException {
		List<ServidorBean> listAllServidores = null;
		// EntityManagerFactory emf=Persistence.createEntityManagerFactory("plataformamensajeriaapp2");
		// EntityManager em=emf.createEntityManager();
		Query query = em.createNamedQuery("selectServidoresByTipo");
		System.out.println("@@@@@@ Using Entity Manager");
		query.setParameter("tipo", new Integer(tipo));
		List<ViewServidoresJPA> listJPA = query.getResultList();
		listAllServidores = getListViewServidorBean(listJPA);
		// try{
		// if(entityManager!=null&&entityManager.isOpen()){
		// entityManager.close();
		// entityManager.close();
		// }
		// }catch(Exception e){
		// System.err.println("[PLATAFORMA-BBDD] Error cerrando la conexión con la base de datos.");
		// }
		return listAllServidores;
	}

	@Override
	public List<ServidorBean> getServidoresNoAsignados(Integer idServicio) throws BusinessException {
		ArrayList<ServidorBean> listaServidores = new ArrayList<ServidorBean>();
		Connection con = PlataformaMensajeriaUtil.getConexion();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("select s.servidorid, s.nombre from tbl_servidores s where s.servidorid not in (select servidorid from tbl_servidores_servicios ss where ss.servicioid=" + idServicio + ") and s.tipo=2" + " and (s.eliminado is null or s.eliminado = 'N')");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ServidorBean servidorBean = new ServidorBean();
				Integer idServidor = rs.getInt(1);
				String nombreServidor = rs.getString(2);
				servidorBean.setServidorId(idServidor.longValue());
				servidorBean.setNombre(nombreServidor);
				listaServidores.add(servidorBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			PlataformaMensajeriaUtil.closeSQLStatements(con, pstmt, rs, null);
		}

		return listaServidores;
	}

	@Override
	public List<ServidorBean> getAllServidores() throws BusinessException {
		List<ServidorBean> listAllServidores = null;
		// EntityManagerFactory emf=Persistence.createEntityManagerFactory("plataformamensajeriaapp2");
		// EntityManager em=emf.createEntityManager();
		Query query = em.createNamedQuery("selectAllServidores");
		List<ViewServidoresJPA> listJPA = query.getResultList();
		listAllServidores = getListViewServidorBean(listJPA);
		// try{
		// if(em!=null&&em.isOpen()){
		// em.close();
		// emf.close();
		// }
		// }catch(Exception e){
		// System.err.println("[PLATAFORMA-BBDD] Error cerrando la conexión con la base de datos.");
		// }
		return listAllServidores;
	}

	@Override
	public PaginatedList<ServidorBean> getServidores(int start, int size, String order, String columnSort, ServidorBean criterio) throws BusinessException {
		List<ViewServidoresJPA> listJPA = null;

		try {

			StringBuffer namedQuery = null;

			// Columna para ordenar
			Hashtable<String, String> columns = new Hashtable<String, String>();
			columns.put("1", "Nombre");
			columns.put("2", "Descripcion");
			columns.put("3", "Activo");
			if (columnSort == null)
				columnSort = "1"; // Id

			String column = columns.get(columnSort);
			if (column == null)
				column = "Nombre";

			String[] param = {};
			// EntityManagerFactory emf=Persistence.createEntityManagerFactory("plataformamensajeriaapp2");
			// EntityManager em=emf.createEntityManager();
			//
			// EntityTransaction entr=em.getTransaction();
			// Query query=em.createNamedQuery("studentRecords");
			// query.setParameter(1, "vinod");
			// query.setParameter(2, "PHD");
			// List stList=query.getResultList();
			// Iterator stIterator=stList.iterator();
			String nombreCriterio = null;

			// Determinamos la NamedQuery
			if (criterio == null || criterio.getNombre() == null || criterio.getNombre().equals("")) {

				// Sin criterio
				nombreCriterio = "";

				namedQuery = new StringBuffer("selectViewServidorByName_orderby");

			} else {
				System.out.println("[CRITERIO] Nombre: " + criterio.getNombre());
				nombreCriterio = criterio.getNombre();
				namedQuery = new StringBuffer("selectViewServidorByName_orderby");
			}

			namedQuery.append(column);

			// Orden ascendente o descendente
			if (order == null || order.equals("1")) // ASC
			{
				namedQuery.append("_ASC");
			} else if (order != null && order.equals("2")) // DESC
			{
				namedQuery.append("_DESC");
			}
			// Lista parcial

			// listJPA = jpa.executeQuery(namedQuery.toString(), param, start, size);
			Query query = em.createNamedQuery(namedQuery.toString());
			nombreCriterio = PlataformaMensajeriaUtil.parseTextCriteria(nombreCriterio);
			query.setParameter("nombre", nombreCriterio);
			query.setParameter("tipo", TIPO_SERVIDOR);
			if (size > 0) {
				query.setFirstResult(start);
				query.setMaxResults(size);
			}
			listJPA = (List<ViewServidoresJPA>) query.getResultList();
			List<ServidorBean> pageList = getListViewServidorBean(listJPA);

			// Total de organismos
			Integer rowcount = getTotalServidores(criterio, em);

			PaginatedList<ServidorBean> result = new PaginatedList<ServidorBean>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);
			// try{
			// if(em!=null&&em.isOpen()){
			// em.close();
			// emf.close();
			// }
			// }catch(Exception e){
			// System.err.println("[PLATAFORMA-BBDD] Error cerrando la conexión con la base de datos.");
			// }
			return result;
		} catch (Exception e) {
			e.printStackTrace(System.out);
			throw new BusinessException(e, "errors.organismo.getOrganismos");

		}
	}

	@Override
	public Integer getTotalServidores(ServidorBean criterio, EntityManager em) throws BusinessException {
		try {
			if (criterio == null || criterio.getNombre() == null || criterio.getNombre().equals("")) {
				Query rowCountQuery = em.createNamedQuery("selectViewServidor_count");
				rowCountQuery.setParameter("nombre", PlataformaMensajeriaUtil.parseTextCriteria(""));
				rowCountQuery.setParameter("tipo", TIPO_SERVIDOR);
				Long rowcount = (Long) rowCountQuery.getSingleResult();
				return (rowcount != null) ? rowcount.intValue() : 0;
			} else {
				Query rowCountQuery = em.createNamedQuery("selectViewServidor_count");
				String nombreCriterio = criterio.getNombre();
				rowCountQuery.setParameter("nombre", PlataformaMensajeriaUtil.parseTextCriteria(nombreCriterio));
				rowCountQuery.setParameter("tipo", TIPO_SERVIDOR);
				Long rowcount = (Long) rowCountQuery.getSingleResult();
				return (rowcount != null) ? rowcount.intValue() : 0;
			}
		} catch (Exception e) {
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}

	@Override
	// @Auditable(operacion=Constants.AUDITORIA_OPERACION_ALTA)
	@Transactional
	public Long newServidor(ServidorBean servidor) throws BusinessException {
		try {
			ServidoresJPA servidorJPA = getServidorJPA(servidor);
			servidorJPA.setTipo(TIPO_SERVIDOR);
			servidorJPA.setFechaCreacion(new Date());
			// TODO: Hacer utilidad para obtener el usuario logueado
			UsuariosForm usuarioLogueado = PlataformaMensajeriaUtil.getUsuarioLogueado();
			servidorJPA.setCreadoPor(usuarioLogueado.getNombre() + " " + usuarioLogueado.getApellidos());
			// servidorJPA.setPorDefecto(POR_DEFECTO);
			servidorJPA.setServidorId(null);
			jpa.insert(servidorJPA);

			servidor.setServidorId((Long) servidorJPA.getId());
			servidor.setNombre(servidorJPA.getNombre());
			servidor.setDescripcion(servidorJPA.getDescripcion());
			servidor.setActivo(servidorJPA.getActivo());
			servidor.setFechaCreacion(servidorJPA.getFechaCreacion());
			servidor.setCreadoPor(servidorJPA.getCreadoPor());
			return servidor.getServidorId();
		} catch (DAOException e) {
			BusinessException exc = new BusinessException(e, "errors.organismo.newOrganismo");
			exc.mRechargeInput();
			throw exc;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateServidor(ServidorBean servidor) throws BusinessException {
		try {
			ServidoresJPA servidorJPA = getServidorJPA(servidor);
			servidorJPA.setFechaModificacion(new Date());
			servidorJPA.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			servidorJPA.setFechaCreacion(servidor.getFechaCreacion());
			jpa.update(servidorJPA);
			return;
		} catch (DAOException e) {
			throw new BusinessException(e, "errors.organismo.updateOrganismo");
		}

	}

	@Override
	// @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	@Transactional
	public ServidorBean loadServidor(ServidorBean servidor) throws BusinessException {
		try {
			ServidoresJPA servidorJPA = getServidorJPA(servidor);

			return getServidorBean((ServidoresJPA) jpa.read(servidorJPA));
		} catch (DAOException e) {
			throw new BusinessException(e, "errors.organismo.loadOrganismo");
		}
	}

	@Override
	@Transactional
	public void deleteServidor(ServidorBean servidor) throws BusinessException {
		try {
			ServidorBean servidorBBDD = loadServidor(servidor);
			ServidoresJPA servidorJPA = getServidorJPA(servidorBBDD);
			String[] paramServidor = new String[] { servidorJPA.getServidorId().toString() };
			List<Object> listaPlanificaciones = jpa.executeQuery("selectPlanificacionByServidorIdJPA", paramServidor);
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			for (Object object : listaPlanificaciones) {
				if (object instanceof PlanificacionJPA) {
					PlanificacionJPA planificacion = (PlanificacionJPA) object;
					planificacion.setModificadoPor(modificador);
					planificacion.setFechaModificacion(new Date());
					planificacion.setEliminado("S");
					jpa.update(planificacion);
				}

			}
			servidorJPA.setEliminado("S");
			servidorJPA.setModificadoPor(modificador);
			servidorJPA.setFechaModificacion(new Date());
			jpa.update(servidorJPA);
			// jpa.executeQuery("deleteServidor", params);
			// jpa.delete(servidorJPA);
			return;

		} catch (Exception e) {
			throw new BusinessException(e, "errors.organismo.deleteOrganismo");
		}
	}

	/**
	 * <p>
	 * Obtenemos un objeto OrganismoJPA a partir de un objeto OrganismoBean
	 * </p>
	 * 
	 * @param organismoBean
	 * 
	 * @return objeto OrganismoJPA
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	protected ServidoresJPA getServidorJPA(ServidorBean servidor) {

		ServidoresJPA servidorJPA = new ServidoresJPA();

		try {
			BeanUtils.copyProperties(servidorJPA, servidor);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		/*
		 * servidorJPA.setId(servidor.getId()); servidorJPA.setServidorId(servidorJPA.getServidorId()); servidorJPA.setNombre(servidor.getNombre()); servidorJPA.setDescripcion(servidor.getDescripcion()); System.out.println("[PLATAFORMA-FORM-ACTIVO]: Valor: " +
		 * servidor.getActivo()); servidorJPA.setActivo(servidor.getActivo());
		 */
		return servidorJPA;
	}

	/**
	 * <p>
	 * Convertirmos una lista de ServidoresJPA a una lista de ServidoresBean
	 * </p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	protected List<ServidorBean> getListServidorBean(List<ServidoresJPA> listJPA) throws BusinessException {
		List<ServidorBean> result = null;

		if (listJPA != null && !listJPA.isEmpty()) {
			result = new ArrayList<ServidorBean>();

			for (int indice = 0; indice < listJPA.size(); indice++) {

				ServidoresJPA servidorJPA = listJPA.get(indice);
				ServidorBean servidor = new ServidorBean();

				try {
					BeanUtils.copyProperties(servidor, servidorJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}

				result.add(servidor);
			}
		}

		return result;
	}

	/**
	 * <p>
	 * Obtenemos un objeto ServidorBean a partir de un objeto ServidoresJPA
	 * </p>
	 * 
	 * @param servidorJPA
	 * 
	 * @return objeto ServidorBean
	 */
	protected ServidorBean getServidorBean(ServidoresJPA servidorJPA) throws BusinessException {
		ServidorBean servidor = new ServidorBean();

		try {
			BeanUtils.copyProperties(servidor, servidorJPA);
		} catch (IllegalAccessException e) {
			throw new BusinessException(e);
		} catch (InvocationTargetException e) {
			throw new BusinessException(e);
		}

		return servidor;
	}

	/**
	 * <p>
	 * Convertirmos una lista de ViewServidoresJPA a una lista de ServidoresBean
	 * </p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	protected List<ServidorBean> getListViewServidorBean(List<ViewServidoresJPA> listJPA) throws BusinessException {
		List<ServidorBean> result = null;

		if (listJPA != null && !listJPA.isEmpty()) {
			result = new ArrayList<ServidorBean>();

			for (int indice = 0; indice < listJPA.size(); indice++) {

				ViewServidoresJPA servidorJPA = listJPA.get(indice);
				ServidorBean servidor = new ServidorBean();

				try {

					Date defaultValue = null;
					DateConverter converter = new DateConverter(defaultValue);
					ConvertUtils.register(converter, java.util.Date.class);
					BeanUtils.copyProperties(servidor, servidorJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}

				result.add(servidor);
			}
		}

		return result;
	}

	@Override
	public List<ServidorBean> getAllServidoresBBDD() throws BusinessException {

		List<ServidorBean> listAllServidores = new ArrayList<ServidorBean>();

		Query query = em.createNamedQuery("selectAllServidorJPA");
		List<ServidoresJPA> listJPA = query.getResultList();
		if (null != listJPA && !listJPA.isEmpty()) {
			for (ServidoresJPA servidor : listJPA) {
				ServidorBean servidorBean = new ServidorBean();
				servidorBean.setServidorId(servidor.getServidorId());
				servidorBean.setNombre(servidor.getNombre());

				listAllServidores.add(servidorBean);
			}
		}

		return listAllServidores;
	}

	@Override
	public List<ServidoresOrganismosBean> getServidorOrganismo(String idOrganismo) throws BusinessException {
		List<ViewServidoresOrganismosJPA> listJPA = null;
		String[] params = new String[] { idOrganismo };
		try {
			listJPA = jpa.executeQuery("ViewSelectServidorOrganismoJPA", params);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<ServidoresOrganismosBean> result = getListViewServidorOrganismosBean(listJPA);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ViewServidoresOrganismosJPA> getServidorOrganismo(List<Integer> listaOrganismos) throws BusinessException {
		List<ViewServidoresOrganismosJPA> listJPA = null;

		try {

			Query q = em.createNamedQuery("listViewServidoresPorOrganismos");
			q.setParameter("listaOrganismos", listaOrganismos);
			listJPA = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listJPA;
	}

	protected List<ServidoresOrganismosBean> getListViewServidorOrganismosBean(List<ViewServidoresOrganismosJPA> listJPA) throws BusinessException {
		List<ServidoresOrganismosBean> result = null;

		if (listJPA != null && !listJPA.isEmpty()) {
			result = new ArrayList<ServidoresOrganismosBean>();

			for (int indice = 0; indice < listJPA.size(); indice++) {

				ViewServidoresOrganismosJPA servidorOrganismoJPA = listJPA.get(indice);
				ServidoresOrganismosBean servidorOrganismo = new ServidoresOrganismosBean();

				try {

					Date defaultValue = null;
					DateConverter converter = new DateConverter(defaultValue);
					ConvertUtils.register(converter, java.util.Date.class);
					BeanUtils.copyProperties(servidorOrganismo, servidorOrganismoJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}

				result.add(servidorOrganismo);
			}
		}

		return result;
	}

	@Override
	@Transactional(noRollbackFor = Exception.class)
	public void newServidoresOrganismo(ServidoresOrganismosBean servidorOrganismo) {
		ServidoresOrganismosJPA servidoresorganismoJPA = getServidoresOrganismoJPA(servidorOrganismo);
		try {
			if (servidoresorganismoJPA != null) {
				servidoresorganismoJPA.setServidorOrganismoId(null);
			}
			jpa.insert(servidoresorganismoJPA);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected ServidoresOrganismosJPA getServidoresOrganismoJPA(ServidoresOrganismosBean servidorOrganismo) {

		ServidoresOrganismosJPA servidorOrganismoJPA = new ServidoresOrganismosJPA();

		try {
			BeanUtils.copyProperties(servidorOrganismoJPA, servidorOrganismo);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		/*
		 * servicioJPA.setId(servidor.getId()); servicioJPA.setServicioId(servicioJPA.getServidorId()); servicioJPA.setNombre(servidor.getNombre()); servicioJPA.setDescripcion(servidor.getDescripcion()); System.out.println("[PLATAFORMA-FORM-ACTIVO]: Valor: " +
		 * servidor.getActivo()); servicioJPA.setActivo(servidor.getActivo());
		 */
		return servidorOrganismoJPA;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteServidorOrganismos(ServidoresOrganismosBean servidorOrganismo) throws BusinessException {
		try {
			ServidoresOrganismosJPA servidorOrganismoBBDD = loadServidorOrganismoJPA(servidorOrganismo);
			jpa.delete(servidorOrganismoBBDD);

		} catch (Exception e) {
			throw new BusinessException(e, "errors.organismo.deleteOrganismo");
		}
	}

	@Override
	public ServidoresOrganismosJPA loadServidorOrganismoJPA(ServidoresOrganismosBean servidorOrganismo) throws BusinessException {
		try {
			ServidoresOrganismosJPA servidorOrganismoJPA = getServidoresOrganismoJPA(servidorOrganismo);

			return (ServidoresOrganismosJPA) jpa.read(servidorOrganismoJPA);
		} catch (Exception e) {
			throw new BusinessException(e, "errors.organismo.loadOrganismo");
		}
	}

	@Override
	public ServidoresOrganismosBean loadServidorOrganismoBean(ServidoresOrganismosBean servidorOrganismo) throws BusinessException {
		try {
			ServidoresOrganismosJPA servidorOrganismoJPA = getServidoresOrganismoJPA(servidorOrganismo);

			return getServidorOrganismoBean((ServidoresOrganismosJPA) jpa.read(servidorOrganismoJPA));

		} catch (Exception e) {
			throw new BusinessException(e, "errors.organismo.loadOrganismo");
		}
	}

	protected ServidoresOrganismosBean getServidorOrganismoBean(ServidoresOrganismosJPA servidorOrganismoJPA) throws BusinessException {
		ServidoresOrganismosBean so = new ServidoresOrganismosBean();

		try {
			if(servidorOrganismoJPA != null){
				BeanUtils.copyProperties(so, servidorOrganismoJPA);
			}else
				return null;
		} catch (IllegalAccessException e) {
			throw new BusinessException(e);
		} catch (InvocationTargetException e) {
			throw new BusinessException(e);
		}

		return so;
	}

}
