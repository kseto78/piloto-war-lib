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
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.map.j2ee.dao.DAOException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.pagination.dao.IPaginationJPADAO;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServicioOrganismosBean;
import es.mpr.plataformamensajeria.beans.ServidoresServiciosBean;
import es.mpr.plataformamensajeria.model.OrganismosServicioJPA;
import es.mpr.plataformamensajeria.model.PlanificacionJPA;
import es.mpr.plataformamensajeria.model.ServicioJPA;
import es.mpr.plataformamensajeria.model.ServidoresServiciosJPA;
import es.mpr.plataformamensajeria.model.views.ViewServicioJPA;
import es.mpr.plataformamensajeria.model.views.ViewServicioOrganismosJPA;
import es.mpr.plataformamensajeria.model.views.ViewServidoresServiciosJPA;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.template.web.action.admin.UsuariosForm;

/**
 * <p>
 * Maneja la persistencia y b&uacute;squeda de servicios a traves de JPA.
 * 
 * @author
 * 
 */
public class ServicioServicioImpl implements ServicioServicio {

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
	private static final String COMBO_SERVICIOS_BY_PROPIETARIO = "select distinct servicio.servicioId,servicio.nombre from TBL_SERVIDORES_SERVICIOS ss , tbl_servicios servicio where  servicio.servicioId = ss.servicioId (+) and servicio.aplicacionId" + " in(select ua.aplicacionid from tbl_usuarios_aplicaciones ua where ua.usuarioid=?) and (servicio.eliminado is null or servicio.eliminado = 'N') order by servicio.nombre";
	private static final String COMBO_SERVICIOS_ALL = "select servicio.servicioId,servicio.nombre from VIEW_SERVICIOS servicio where (servicio.eliminado is null or servicio.eliminado = 'N') order by servicio.nombre";

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
	public List<ServicioBean> getServicios(ServicioBean criterio) throws BusinessException {
		List<ServicioJPA> listJPA = null;

		try {

			if (criterio == null || criterio.getNombre() == null || criterio.getNombre().equals("")) {

				ServicioJPA criterioJPA = new ServicioJPA();
				criterioJPA.setServicioId(criterio.getServicioId());
				criterioJPA.setNombre(criterio.getNombre());

				listJPA = jpa.readAll(0, 0, criterioJPA);
			} else {
				String[] param = new String[] { "%" + criterio.getNombre().toUpperCase() + "%" };
				listJPA = jpa.executeQuery("selectServidorJPA", param);
			}

			List<ServicioBean> result = getListServicioBean(listJPA);

			return result;

		} catch (DAOException e) {
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}

	@Override
	public List<ServicioBean> getServicios() throws BusinessException {
		List<ViewServicioJPA> listJPA = null;

		try {

			listJPA = jpa.executeQuery("selectViewServicioJPA", null);

			List<ServicioBean> result = getListViewServicioBean(listJPA);

			return result;

		} catch (DAOException e) {
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}

	@Override
	public List<ServicioBean> getServiciosMultiorganismo() throws BusinessException {
		List<ServicioJPA> listJPA = null;

		try {

			listJPA = jpa.executeQuery("selectServicioMultiOrganismoJPA", null);

			List<ServicioBean> result = getListServicioBean(listJPA);

			return result;

		} catch (DAOException e) {
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}
	
	

	@Override
	public List<ServicioBean> getServicios(String rolUsuario, Integer idUsuario) throws BusinessException {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = PlataformaMensajeriaUtil.getConexion();
		List<ServicioBean> listBean = new ArrayList<ServicioBean>();
		try {
			if (rolUsuario != null && rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR)) {
				pstmt = con.prepareStatement(COMBO_SERVICIOS_ALL);
			} else {
				pstmt = con.prepareStatement(COMBO_SERVICIOS_BY_PROPIETARIO);
				pstmt.setInt(1, idUsuario);
			}
			rs = pstmt.executeQuery();
			while (rs != null && rs.next()) {
				String idServicio = rs.getString(1);
				String nombre = rs.getString(2);
				ServicioBean servicio = new ServicioBean();
				servicio.setServicioId(new Integer(idServicio));
				servicio.setNombre(nombre);
				listBean.add(servicio);
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

	@Override
	public PaginatedList<ServicioBean> getServicios(int start, int size, String order, String columnSort, ServicioBean criterio) throws BusinessException {
		List<ViewServicioJPA> listJPA = null;

		try {

			StringBuffer namedQuery = null;

			// Columna para ordenar
			Hashtable<String, String> columns = new Hashtable<String, String>();
			columns.put("1", "NombreAplicacion");
			columns.put("2", "NombreServicio");
			columns.put("3", "Activo");
			if (columnSort == null)
				columnSort = "1"; // Id

			String column = columns.get(columnSort);
			if (column == null)
				column = "NombreAplicacion";

			String[] param = {};
			// EntityManagerFactory emf=Persistence.createEntityManagerFactory("plataformamensajeriaapp2");
			// EntityManager em=emf.createEntityManager();

			// EntityTransaction entr=em.getTransaction();
			// Query query=em.createNamedQuery("studentRecords");
			// query.setParameter(1, "vinod");
			// query.setParameter(2, "PHD");
			// List stList=query.getResultList();
			// Iterator stIterator=stList.iterator();
			String nombreCriterio = null;
			String namedQueryCount = null;
			int tipoConsulta = 0;
			// Determinamos la NamedQuery

			if (criterio == null) {
				namedQuery = new StringBuffer("selectViewServiciosAll");
				namedQueryCount = "selectViewServiciosAllCount";
			} else if (criterio.getNombre() != null && criterio.getNombre().length() > 0 && criterio.getAplicacionId() != null && criterio.getAplicacionId().intValue() != 0) {
				namedQuery = new StringBuffer("selectViewServiciosByNombreAndAplicacion_orderBy");
				tipoConsulta = 1;
				namedQueryCount = "selectViewServiciosByNombreAndAplicacionCount";
			} else if (criterio.getNombre() != null && criterio.getNombre().length() > 0 && (criterio.getAplicacionId() == null || (criterio.getAplicacionId() != null && criterio.getAplicacionId() == 0))) {
				namedQuery = new StringBuffer("selectViewServiciosByNombre_orderBy");
				tipoConsulta = 2;
				namedQueryCount = "selectViewServiciosByNombreCount";
			} else if ((criterio.getNombre() == null || (criterio.getNombre() != null && criterio.getNombre().equals(""))) && criterio.getAplicacionId() != null && criterio.getAplicacionId() != 0) {
				namedQuery = new StringBuffer("selectViewServiciosByAplicacion_orderBy");
				tipoConsulta = 3;
				namedQueryCount = "selectViewServiciosByAplicacionCount";
			}
			if (namedQuery == null) {
				namedQuery = new StringBuffer("selectViewServiciosAll");
				namedQueryCount = "selectViewServiciosAllCount";
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
			query = setParams(query, criterio, tipoConsulta);
			if (size > 0) {
				query.setFirstResult(start);
				query.setMaxResults(size);
			}
			listJPA = (List<ViewServicioJPA>) query.getResultList();
			List<ServicioBean> pageList = getListViewServicioBean(listJPA);

			// Total de organismos
			Integer rowcount = getTotalServicios(criterio, em, namedQueryCount, tipoConsulta);

			PaginatedList<ServicioBean> result = new PaginatedList<ServicioBean>();
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

	/**
	 * Setea los parámetros en función de la información introducida en el formulario de búsqueda
	 * 
	 * @param query
	 * @param criterio
	 * @param tipoConsulta
	 * @return
	 */
	public Query setParams(Query query, ServicioBean criterio, int tipoConsulta) {
		if (tipoConsulta == 0) {
			return query;
		} else if (tipoConsulta == 1) {
			String nombreCriterio = criterio.getNombre();
			nombreCriterio = PlataformaMensajeriaUtil.parseTextCriteria(nombreCriterio);
			query.setParameter("nombre", nombreCriterio);
			query.setParameter("aplicacionId", criterio.getAplicacionId().toString());

		} else if (tipoConsulta == 2) {
			String nombreCriterio = criterio.getNombre();
			nombreCriterio = PlataformaMensajeriaUtil.parseTextCriteria(nombreCriterio);
			query.setParameter("nombre", nombreCriterio);
		} else {
			query.setParameter("aplicacionId", criterio.getAplicacionId().toString());
		}
		return query;
	}

	@Override
	public Integer getTotalServicios(ServicioBean criterio, EntityManager em, String namedQuery, int tipoConsulta) throws BusinessException {
		try {
			Query rowCountQuery = em.createNamedQuery(namedQuery);
			rowCountQuery = setParams(rowCountQuery, criterio, tipoConsulta);
			Long rowcount = (Long) rowCountQuery.getSingleResult();
			return (rowcount != null) ? rowcount.intValue() : 0;

		} catch (Exception e) {
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}
	}

	@Override
	// @Auditable(operacion=Constants.AUDITORIA_OPERACION_ALTA)
	@Transactional
	public Integer newServicio(ServicioBean servicio) throws BusinessException {
		try {
			ServicioJPA servicioJPA = getServicioJPA(servicio);

			servicioJPA.setFechaCreacion(new Date());
			// TODO: Hacer utilidad para obtener el usuario logueado
			UsuariosForm usuarioLogueado = PlataformaMensajeriaUtil.getUsuarioLogueado();
			servicioJPA.setCreadoPor(usuarioLogueado.getNombre() + " " + usuarioLogueado.getApellidos());

			servicioJPA.setServicioId(null);

			jpa.insert(servicioJPA);

			servicio.setServicioId((Integer) servicioJPA.getId());
			servicio.setNombre(servicioJPA.getNombre());
			servicio.setDescripcion(servicioJPA.getDescripcion());
			servicio.setActivo(servicioJPA.getActivo());
			servicio.setFechaCreacion(servicioJPA.getFechaCreacion());
			servicio.setCreadoPor(servicioJPA.getCreadoPor());
			return servicio.getServicioId();
		} catch (DAOException e) {
			BusinessException exc = new BusinessException(e, "errors.organismo.newOrganismo");
			exc.mRechargeInput();
			throw exc;
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateServicio(ServicioBean servicio) throws BusinessException {
		try {
			ServicioJPA servicioJPA = getServicioJPA(servicio);
			servicioJPA.setFechaModificacion(new Date());
			servicioJPA.setModificadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			servicioJPA.setFechaCreacion(servicio.getFechaCreacion());
			
			jpa.update(servicioJPA);
			
		} catch (DAOException e) {
			throw new BusinessException(e, "errors.organismo.updateOrganismo");
		}

	}

	@Override
	// @Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	@Transactional
	public ServicioBean loadServicio(ServicioBean servicio) throws BusinessException {
		try {
			ServicioJPA servicioJPA = getServicioJPA(servicio);

			return getServicioBean((ServicioJPA) jpa.read(servicioJPA));
		} catch (DAOException e) {
			throw new BusinessException(e, "errors.organismo.loadOrganismo");
		}
	}

	@Override
	@Transactional
	public void deleteServicio(ServicioBean servicio) throws BusinessException {
		try {
			ServicioBean servicioBBDD = loadServicio(servicio);
			ServicioJPA servicioJPA = getServicioJPA(servicioBBDD);
			String[] paramServicio = new String[] { servicioJPA.getservicioId().toString() };
			List<Object> listaPlanificacionesServicio = jpa.executeQuery("selectPlanificacionByServicioIdJPA", paramServicio);
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			for (Object object : listaPlanificacionesServicio) {
				if (object instanceof PlanificacionJPA) {
					PlanificacionJPA planificacion = (PlanificacionJPA) object;
					planificacion.setModificadoPor(modificador);
					planificacion.setFechaModificacion(new Date());
					planificacion.setEliminado("S");
					jpa.update(planificacion);
				}
			}
			List<Object> listaServidoresServicios = jpa.executeQuery("selectServidoresServiciosByServicioIdJPA", paramServicio);
			for (Object object : listaServidoresServicios) {
				if (object instanceof ServidoresServiciosJPA) {
					ServidoresServiciosJPA servidorServicio = (ServidoresServiciosJPA) object;
					jpa.delete(servidorServicio);
				}
			}
			servicioJPA.setEliminado("S");
			servicioJPA.setModificadoPor(modificador);
			servicioJPA.setFechaModificacion(new Date());
			jpa.update(servicioJPA);
			return;

		} catch (DAOException e) {
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
	public ServicioJPA getServicioJPA(ServicioBean servicio) {

		ServicioJPA servicioJPA = new ServicioJPA();

		try {
			Integer defaultValue = null;
			IntegerConverter converter = new IntegerConverter(defaultValue);
			ConvertUtils.register(converter, java.lang.Integer.class);
			BeanUtils.copyProperties(servicioJPA, servicio);
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
		return servicioJPA;
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
	protected ServidoresServiciosJPA getServidoresServicioJPA(ServidoresServiciosBean servidoresServicios) {

		ServidoresServiciosJPA servidoresServiciosJPA = new ServidoresServiciosJPA();

		try {
			BeanUtils.copyProperties(servidoresServiciosJPA, servidoresServicios);
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
		return servidoresServiciosJPA;
	}

	protected OrganismosServicioJPA getServicioOrganismosJPA(ServicioOrganismosBean servicioOrganismo) {

		OrganismosServicioJPA organismoServicioJPA = new OrganismosServicioJPA();

		try {
			BeanUtils.copyProperties(organismoServicioJPA, servicioOrganismo);
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
		return organismoServicioJPA;
	}

	/**
	 * <p>
	 * Convertirmos una lista de ServicioJPA a una lista de ServidoresBean
	 * </p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	protected List<ServicioBean> getListServicioBean(List<ServicioJPA> listJPA) throws BusinessException {
		List<ServicioBean> result = null;

		if (listJPA != null && !listJPA.isEmpty()) {
			result = new ArrayList<ServicioBean>();

			for (int indice = 0; indice < listJPA.size(); indice++) {

				ServicioJPA servicioJPA = listJPA.get(indice);
				if (null==servicioJPA.getFechaCreacion()|| servicioJPA.getFechaCreacion().equals("")){
					servicioJPA.setFechaCreacion(new Date());
				}
				if (null==servicioJPA.getFechaModificacion() || servicioJPA.getFechaModificacion().equals("")){
					servicioJPA.setFechaModificacion(new Date());
				}
				ServicioBean servicio = new ServicioBean();

				try {
					BeanUtils.copyProperties(servicio, servicioJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				} catch (ConversionException e) {
					throw new BusinessException(e);
				}

				result.add(servicio);
			}
		}

		return result;
	}

	/**
	 * <p>
	 * Obtenemos un objeto ServicioBean a partir de un objeto ServicioJPA
	 * </p>
	 * 
	 * @param servicioJPA
	 * 
	 * @return objeto ServicioBean
	 */
	protected ServicioBean getServicioBean(ServicioJPA servicioJPA) throws BusinessException {
		ServicioBean servicio = new ServicioBean();

		try {
			BeanUtils.copyProperties(servicio, servicioJPA);
		} catch (IllegalAccessException e) {
			throw new BusinessException(e);
		} catch (InvocationTargetException e) {
			throw new BusinessException(e);
		}

		return servicio;
	}

	/**
	 * <p>
	 * Obtenemos un objeto ServicioBean a partir de un objeto ServicioJPA
	 * </p>
	 * 
	 * @param servicioJPA
	 * 
	 * @return objeto ServicioBean
	 */
	protected ServidoresServiciosBean getServidoresServiciosBean(ServidoresServiciosJPA servidoresServiciosJPA) throws BusinessException {
		ServidoresServiciosBean servidorServicio = new ServidoresServiciosBean();

		try {
			BeanUtils.copyProperties(servidorServicio, servidoresServiciosJPA);
		} catch (IllegalAccessException e) {
			throw new BusinessException(e);
		} catch (InvocationTargetException e) {
			throw new BusinessException(e);
		}

		return servidorServicio;
	}

	/**
	 * <p>
	 * Convertirmos una lista de ViewServicioJPA a una lista de ServidoresBean
	 * </p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos OrganismoBean
	 */
	protected List<ServicioBean> getListViewServicioBean(List<ViewServicioJPA> listJPA) throws BusinessException {
		List<ServicioBean> result = null;

		if (listJPA != null && !listJPA.isEmpty()) {
			result = new ArrayList<ServicioBean>();

			for (int indice = 0; indice < listJPA.size(); indice++) {

				ViewServicioJPA servicioJPA = listJPA.get(indice);
				ServicioBean servicio = new ServicioBean();

				try {

					Date defaultValue = null;
					DateConverter converter = new DateConverter(defaultValue);
					ConvertUtils.register(converter, java.util.Date.class);
					BeanUtils.copyProperties(servicio, servicioJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}

				result.add(servicio);
			}
		}

		return result;
	}

	/**
	 * <p>
	 * Convertirmos una lista de ViewServidoresServiciosJPA a una lista de ServidoresServiciosBean
	 * </p>
	 * 
	 * @param listJPA
	 * 
	 * @return Lista de objetos ServidoresServiciosBean
	 */
	protected List<ServidoresServiciosBean> getListViewServidoresServiciosBean(List<ViewServidoresServiciosJPA> listJPA) throws BusinessException {
		List<ServidoresServiciosBean> result = null;

		if (listJPA != null && !listJPA.isEmpty()) {
			result = new ArrayList<ServidoresServiciosBean>();

			for (int indice = 0; indice < listJPA.size(); indice++) {

				ViewServidoresServiciosJPA servidorServicioJPA = listJPA.get(indice);
				ServidoresServiciosBean servidorServicio = new ServidoresServiciosBean();

				try {

					Date defaultValue = null;
					DateConverter converter = new DateConverter(defaultValue);
					ConvertUtils.register(converter, java.util.Date.class);
					BeanUtils.copyProperties(servidorServicio, servidorServicioJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}

				result.add(servidorServicio);
			}
		}

		return result;
	}

	@Override
	public List<ServicioBean> getServiciosByAplicacionId(Integer aplicacionId) throws BusinessException {
		List<ViewServicioJPA> listJPA = null;
		String[] params = new String[] { aplicacionId.toString() };
		try {
			listJPA = jpa.executeQuery("selectViewServicioJPAByIdAplicacion", params);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ServicioBean> result = getListViewServicioBean(listJPA);
		return result;
	}

	@Override
	public List<ServicioBean> getServiciosByOrganismoId(Integer organismoId) throws BusinessException {
		List<ServicioJPA> listJPA = null;
		String[] params = new String[] { organismoId.toString() };
		try {
			listJPA = jpa.executeQuery("selectOrganismosServicioJPA", params);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ServicioBean> result = getListServicioBean(listJPA);
		return result;
	}

	@Override
	public ServidoresServiciosBean loadServidorServicio(ServidoresServiciosBean servidorServicio) throws BusinessException {
		try {
			ServidoresServiciosJPA servicioJPA = getServidoresServicioJPA(servidorServicio);

			return getServidoresServiciosBean((ServidoresServiciosJPA) jpa.read(servicioJPA));
		} catch (Exception e) {
			throw new BusinessException(e, "errors.organismo.loadOrganismo");
		}
	}

	@Override
	public ServidoresServiciosJPA loadServidorServicioJPA(ServidoresServiciosBean servidorServicio) throws BusinessException {
		try {
			ServidoresServiciosJPA servicioJPA = getServidoresServicioJPA(servidorServicio);

			return (ServidoresServiciosJPA) jpa.read(servicioJPA);
		} catch (Exception e) {
			throw new BusinessException(e, "errors.organismo.loadOrganismo");
		}
	}

	@Override
	@Transactional
	public void deleteServidoresServicios(ServidoresServiciosBean servidorServicio) throws BusinessException {
		try {
			ServidoresServiciosJPA servidorBBDD = loadServidorServicioJPA(servidorServicio);
			jpa.delete(servidorBBDD);
			String a = "";
			return;

		} catch (Exception e) {
			throw new BusinessException(e, "errors.organismo.deleteOrganismo");
		}
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void deleteServicioOrganismos(ServicioOrganismosBean servicioOrganismo) throws BusinessException {
		try {
			OrganismosServicioJPA organismoServicioBBDD = loadOrganismoServicioJPA(servicioOrganismo);
			jpa.delete(organismoServicioBBDD);
			
		} catch (Exception e) {
			throw new BusinessException(e, "errors.organismo.deleteOrganismo");
		}
	}
	
	@Override
	public OrganismosServicioJPA loadOrganismoServicioJPA(ServicioOrganismosBean organismoServicio) throws BusinessException {
		try {
			OrganismosServicioJPA OrganismoServicioJPA = getServicioOrganismosJPA(organismoServicio);

			return (OrganismosServicioJPA) jpa.read(OrganismoServicioJPA);
		} catch (Exception e) {
			throw new BusinessException(e, "errors.organismo.loadOrganismo");
		}
	}

	@Override
	@Transactional
	public void newServidoresServicio(ServidoresServiciosBean servidorServicio) {
		ServidoresServiciosJPA servidoresServiciosJPA = getServidoresServicioJPA(servidorServicio);
		try {
			if (servidoresServiciosJPA != null) {
				servidoresServiciosJPA.setServidorServicioId(null);
			}
			jpa.insert(servidoresServiciosJPA);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void newServicioOrganismo(ServicioOrganismosBean servicioOrganismo) {
		OrganismosServicioJPA servicioOrganismosJPA = getServicioOrganismosJPA(servicioOrganismo);

		try {
			if (servicioOrganismosJPA != null) {
				servicioOrganismosJPA.setServicioOrganismoId(null);
			}
			servicioOrganismosJPA.setCreadoPor(PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto());
			servicioOrganismosJPA.setFechaCreacion(new Date());
			jpa.insert(servicioOrganismosJPA);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<ServidoresServiciosBean> getServidoresServicios(String idServicio) throws BusinessException {
		List<ViewServidoresServiciosJPA> listJPA = null;
		String[] params = new String[] { idServicio };
		try {
			listJPA = jpa.executeQuery("selectViewServidoresServiciosByServicio", params);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ServidoresServiciosBean> result = getListViewServidoresServiciosBean(listJPA);
		return result;

	}

	@Override
	public List<ServicioOrganismosBean> getServicioOrganismo(String idServicio) throws BusinessException {
		List<ViewServicioOrganismosJPA> listJPA = null;
		String[] params = new String[] { idServicio };
		try {
			listJPA = jpa.executeQuery("ViewSelectServicioOrganismosJPA", params);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ServicioOrganismosBean> result = getListViewServicioOrganismosBean(listJPA);
		return result;

	}

	@Override
	public List<ServicioOrganismosBean> getOrganismoServicio(String idOrganismo) throws BusinessException {
		List<ViewServicioOrganismosJPA> listJPA = null;
		String[] params = new String[] { idOrganismo };
		try {
			listJPA = jpa.executeQuery("ViewSelectOrganismosServicioJPA", params);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ServicioOrganismosBean> result = getListViewServicioOrganismosBean(listJPA);
		return result;

	}

	@Override
	@Transactional
	public List<ServicioBean> getServiciosHistorico() throws BusinessException {
		List<ServicioJPA> listJPA = null;

		try {

			listJPA = jpa.executeQuery("selectServicioHistJPA", null);

			List<ServicioBean> result = getListServicioBean(listJPA);

			return result;

		} catch (DAOException e) {
			throw new BusinessException(e, "errors.job.historico.getServicios");
		}
	}

	@Override
	@Transactional
	public List<ServicioBean> getServiciosCons() throws BusinessException {
		List<ServicioJPA> listJPA = null;

		try {

			listJPA = jpa.executeQuery("selectServicioConsJPA", null);

			List<ServicioBean> result = getListServicioBean(listJPA);

			return result;

		} catch (DAOException e) {
			throw new BusinessException(e, "errors.job.cons.getServicios");
		}
	}

	@Override
	@Transactional
	public List<ServicioBean> getServiciosInformes() throws BusinessException {
		List<ServicioJPA> listJPA = null;

		try {

			listJPA = jpa.executeQuery("selectServicioInformesJPA", null);

			List<ServicioBean> result = getListServicioBean(listJPA);

			return result;

		} catch (DAOException e) {
			throw new BusinessException(e, "errors.job.informesServicios.getServicios");
		}
	}
	
	@Override
	public boolean actualizarCheckMultiorganismo(boolean isMultiorganismo, Integer idServicio) throws BusinessException {
		boolean res = true;
		Integer activo = 0;
		try {
			if (isMultiorganismo)
				activo = 1;
			else
				activo = 0;
//			jpa.executeQuery("actualizarCheckMultiorganismo", null);
			Query query = em.createNamedQuery("actualizarCheckMultiorganismo");
			query = query.setParameter("activo", activo);
			query = query.setParameter("idServicio", idServicio);
			res = (Boolean) query.getSingleResult();
			
		} catch (Exception e) {
			res = false;
			throw new BusinessException(e, "errors.job.informesServicios.getServicios");
			
		}
		return res;
	}

	protected List<ServicioOrganismosBean> getListViewServicioOrganismosBean(List<ViewServicioOrganismosJPA> listJPA) throws BusinessException {
		List<ServicioOrganismosBean> result = null;

		if (listJPA != null && !listJPA.isEmpty()) {
			result = new ArrayList<ServicioOrganismosBean>();

			for (int indice = 0; indice < listJPA.size(); indice++) {

				ViewServicioOrganismosJPA servicioOrganismoJPA = listJPA.get(indice);
				ServicioOrganismosBean servicioOrganismo = new ServicioOrganismosBean();

				try {

					Date defaultValue = null;
					DateConverter converter = new DateConverter(defaultValue);
					ConvertUtils.register(converter, java.util.Date.class);
					BeanUtils.copyProperties(servicioOrganismo, servicioOrganismoJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}

				result.add(servicioOrganismo);
			}
		}

		return result;
	}

	

}
