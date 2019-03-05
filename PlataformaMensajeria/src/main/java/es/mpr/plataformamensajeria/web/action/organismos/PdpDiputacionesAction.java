package es.mpr.plataformamensajeria.web.action.organismos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

import es.minhap.plataformamensajeria.iop.beans.PdpDiputacionesBean;
import es.minhap.sim.model.TblOrganismos;
import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.DetalleAplicacionBean;
import es.mpr.plataformamensajeria.beans.OrganismoBean;
import es.mpr.plataformamensajeria.beans.PlanificacionBean;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServicioOrganismosBean;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.beans.ServidoresOrganismosBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPdpDiputaciones;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuarioAplicacion;
import es.mpr.plataformamensajeria.util.KeyValueObjects;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>
 * Clase Action de Struts2 para la gesti&oacute;n de los organismos.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n, borrado y listado de los Organismos
 * 
 * @author Altran
 * 
 */
@Controller("pdpDiputacionesAction")
@Scope("prototype")
public class PdpDiputacionesAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  logger. */
	private static Logger logger = Logger.getLogger(PdpDiputacionesAction.class);
	
	/**  servicio organismo pdp. */
	@Resource(name="servicioOrganismoImpl")
	private transient ServicioOrganismo servicioOrganismo;
	
	/**  servicio organismo pdp. */
	@Resource(name="servicioPdpDiputacionesImpl")
	private transient ServicioPdpDiputaciones servicioPdpDiputaciones;
	
	/**  servicio servicio. */
	@Resource(name="servicioServicioImpl")
	private transient ServicioServicio servicioServicio;
	
	/**  servicio usuario aplicacion. */
	@Resource(name="servicioUsuarioAplicacionImpl")
	private transient ServicioUsuarioAplicacion servicioUsuarioAplicacion;
	
	/**  servicio servidor. */
	@Resource(name="servicioServidorImpl")
	private transient ServicioServidor servicioServidor;
	
	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private transient PlataformaMensajeriaProperties properties;
	
	/**  organismo pdp. */
	transient PdpDiputacionesBean organismo; 
	
	/** organismo */
	private OrganismoBean organismoArbol;
	
	/**  servidor organismo. */
	private ServidoresOrganismosBean servidorOrganismo;
	
	/**  aplicacion. */
	private AplicacionBean aplicacion;
	
	/**  servicio organismos. */
	transient ServicioOrganismosBean servicioOrganismos;
	
	/**  servicio. */
	private ServicioBean servicio;
	
	/**  servidor. */
	private ServidorBean servidor;
	
	/**  planificacion organismo. */
	private PlanificacionBean planificacionOrganismo;
	
	/**  lista organismos. */
	transient List<PdpDiputacionesBean> listaOrganismosPdp = null;
	
	/**  lista servicio organismos. */
	transient List<ServicioOrganismosBean> listaServicioOrganismos = null;
	
	/**  lista servidores organismos. */
	transient List<ServidoresOrganismosBean> listaServidoresOrganismos = null;
	
	/**  lista planificaciones servicio. */
	transient List<PlanificacionBean> listaPlanificacionesServicio = null;
	
	/**  combo servicio organismos. */
	transient List<KeyValueObject> comboServicioOrganismos = new ArrayList<>();
	
	/**  combo organismos hijos */
	transient List<KeyValueObjects> comboOrganismosHijos = new ArrayList<>();
	
	/**  combo servidores organismos. */
	transient List<KeyValueObject> comboServidoresOrganismos = new ArrayList<>();
	
	/**  combo servidores plan. */
	transient List<KeyValueObject> comboServidoresPlan = new ArrayList<>();
	
	/**  combo servicios plan. */
	transient List<KeyValueObject> comboServiciosPlan = new ArrayList<>();
	
	/**  combo servidores. */
	transient List<KeyValueObject> comboServidores = new ArrayList<>();
	
	/**  combo tipos estados. */
	transient List<KeyValueObject> comboTiposEstados = new ArrayList<>();
	
	/**  check del list. */
	private String[] checkDelList;
	
	/**  check del list organismos servicios. */
	private String[] checkDelListOrganismosServicios;
	
	/**  check del list servidor organismos. */
	private String[] checkDelListServidorOrganismos;
	
	/**  check del list planificaciones organismos. */
	private String[] checkDelListPlanificacionesOrganismos;
	
	/**  id organismo. */
	private String pdpDiputacionesId;
	
	/**  id servicio organismo. */
	private String idServicioOrganismo;
	
	/**  servidor organismo id. */
	private String servidorOrganismoId;
	
	/**  id planificacion. */
	private String idPlanificacion;
	
	/**  id servicio. */
	private String idServicio;
	
	/**  id servidor. */
	private String idServidor;
	
	/**  id proveedor SMS. */
	private String idProveedorSMS;
	
	/**  id receptor SMS. */
	private String idReceptorSMS;
	
	/**  id servidor push. */
	private String idServidorPush;
	
	/**  result count. */
	private String resultCount;
	
	/**  check password. */
	private String checkPassword;
	
	/**  recovery. */
	private String recovery = "";
	
	private boolean replicarHijosServicios;
	
	private boolean seleccionarHijosServicios;
	
	private boolean replicarHijosServidores;
	
	private boolean seleccionarHijosServidores;
	
	private String serviciosHijosSeleccionados;
	
	private String servidorHijosSeleccionados;
	
	/** datos servicios */
	private String datosServicios;
	
	/** datos servidor */
	private String datosServidor;
	
	/**  session. */
	@SuppressWarnings("rawtypes")
	transient Map session;
	
	/** Constante INFO_USER. */
	private static final String INFO_USER = "infoUser";

	/** Constante NO_USER. */
	private static final String NO_USER = "noUser";

	/** Constante TABLE_ID. */
	private static final String TABLE_ID = "tableId";

	/** Constante GENERALES_REQUEST_ATTRIBUTE_TOTALSIZE. */
	private static final String GENERALES_REQUEST_ATTRIBUTE_TOTALSIZE = "generales.REQUEST_ATTRIBUTE_TOTALSIZE";

	/** Constante GENERALES_REQUEST_ATTRIBUTE_PAGESIZE. */
	private static final String GENERALES_REQUEST_ATTRIBUTE_PAGESIZE = "generales.REQUEST_ATTRIBUTE_PAGESIZE";

	/** Constante LOG_ACCIONID_ACTUALIZAR. */
	private static final String LOG_ACCIONID_ACTUALIZAR = "log.ACCIONID_ACTUALIZAR";

	/** Constante LOG_ACCION_ACTUALIZAR. */
	private static final String LOG_ACCION_ACTUALIZAR = "log.ACCION_ACTUALIZAR";

	/** Constante GENERALES_PAGESIZE. */
	private static final String GENERALES_PAGESIZE = "generales.PAGESIZE";

	/** Constante LOG_ACCIONID_ELIMINAR. */
	private static final String LOG_ACCIONID_ELIMINAR = "log.ACCIONID_ELIMINAR";

	/** Constante LOG_ACCION_ELIMINAR. */
	private static final String LOG_ACCION_ELIMINAR = "log.ACCION_ELIMINAR";

	/** Constante LOG_SOURCE_ORGANISMOS. */
	private static final String LOG_SOURCE_ORGANISMOSPDP = "log.SOURCE_ORGANISMOSPDP";
	
	/** Constante RECOVERY. */
	private static final String RECOVERY = "recovery";


	/**
	 * New search.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String newSearch() {
		if (getRequest().getSession().getAttribute(PdpDiputacionesAction.INFO_USER) == null)
			return PdpDiputacionesAction.NO_USER;
		
		organismo = (PdpDiputacionesBean) getRequest().getSession().getAttribute("organismoPdp");
		
		
		Integer totalSize = 0;
		getRequest().setAttribute(properties.getProperty(PdpDiputacionesAction.GENERALES_REQUEST_ATTRIBUTE_TOTALSIZE, null), totalSize);
		listaOrganismosPdp =new ArrayList<>();
		
		return SUCCESS;
	}
	
	/**
	 * Arbol Organismos.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String arbolOrganismos() throws BaseException {
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error("OrganismosAction - load:" + e);
			return PdpDiputacionesAction.NO_USER;
		}
		if (pdpDiputacionesId == null)
			throw new BusinessException("EL idOrganismo recibido es nulo");
		try {		
			organismo = new PdpDiputacionesBean();
			organismo.setPdpDiputacionesId(new Integer(pdpDiputacionesId));
			organismo = servicioPdpDiputaciones.loadOrganismoPdp(organismo);
			comboOrganismosHijos = cargarComboOrganismosHijosPdp();

			return SUCCESS;
		} catch (NumberFormatException e) {
			String mensg = this.getText("errors.action.organismoPdp.loadOrganismo", new String[] { organismo.getPdpDiputacionesId().toString() });
			logger.error("OrganismosAction - load:" + e);
			throw new BusinessException(mensg);
		} 
	}

	/**
	 * Search.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String search() throws BaseException {

		if (getRequest().getSession().getAttribute(PdpDiputacionesAction.INFO_USER) == null)
			return PdpDiputacionesAction.NO_USER;

		int page = getPage(PdpDiputacionesAction.TABLE_ID); // Pagina a mostrar
		String order = getOrder(PdpDiputacionesAction.TABLE_ID); // Ordenar de modo ascendente o descendente
		String columnSort = getColumnSort(PdpDiputacionesAction.TABLE_ID); // Columna usada para ordenar

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty(PdpDiputacionesAction.GENERALES_PAGESIZE, "20"));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<PdpDiputacionesBean> result = servicioPdpDiputaciones.getOrganismosPdpDiputaciones(inicio, export ? -1 : Integer.parseInt(properties.getProperty(PdpDiputacionesAction.GENERALES_PAGESIZE, "20")), order, columnSort, organismo);
		Integer totalSize = result.getTotalList();

		listaOrganismosPdp = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(properties.getProperty(PdpDiputacionesAction.GENERALES_REQUEST_ATTRIBUTE_TOTALSIZE, null), totalSize);
		if (!export) {
			getRequest().setAttribute(properties.getProperty(PdpDiputacionesAction.GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null), 
					Integer.parseInt(properties.getProperty(PdpDiputacionesAction.GENERALES_PAGESIZE, "20")));
		} else {
			getRequest().setAttribute(properties.getProperty(PdpDiputacionesAction.GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null), 
					totalSize);
		}

		//guardamos el organismo para que al volver tengamos la ultima busqueda
		getRequest().getSession().setAttribute("organismoPdp", organismo);

		return SUCCESS;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	///MIGRADO
	@Override
	public String execute() throws BaseException {

		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return PdpDiputacionesAction.NO_USER;
		}
		int page = getPage(PdpDiputacionesAction.TABLE_ID); // Pagina a mostrar
		String order = getOrder(PdpDiputacionesAction.TABLE_ID); // Ordenar de modo ascendente o descendente
		String columnSort = getColumnSort(PdpDiputacionesAction.TABLE_ID); // Columna usada para ordenar

		if (organismo != null && organismo.getNombre() != null && organismo.getNombre().length() <= 0)
				organismo.setNombre(null);

		int inicio = (page - 1) * Integer.parseInt(properties.getProperty(PdpDiputacionesAction.GENERALES_PAGESIZE, "20"));
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<PdpDiputacionesBean> result = servicioPdpDiputaciones.getOrganismosPdpDiputaciones(inicio, (export) ? -1 : Integer.parseInt(properties.getProperty(PdpDiputacionesAction.GENERALES_PAGESIZE, "20")), order, columnSort, organismo);
		Integer totalSize = result.getTotalList();

		listaOrganismosPdp = result.getPageList();

		getRequest().setAttribute(properties.getProperty(PdpDiputacionesAction.GENERALES_REQUEST_ATTRIBUTE_TOTALSIZE, null), totalSize);

		if (!export) {
			getRequest().setAttribute(properties.getProperty(PdpDiputacionesAction.GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null),
					Integer.parseInt(properties.getProperty(PdpDiputacionesAction.GENERALES_PAGESIZE, "20")));
		} else {
			getRequest().setAttribute(properties.getProperty(PdpDiputacionesAction.GENERALES_REQUEST_ATTRIBUTE_PAGESIZE, null), totalSize);
		}
		if (listaOrganismosPdp != null && !listaOrganismosPdp.isEmpty()) {
			for (int indice = 0; indice < listaOrganismosPdp.size(); indice++) {

				PdpDiputacionesBean organismoPdp = listaOrganismosPdp.get(indice);
				organismoPdp.setNombre(StringEscapeUtils.escapeHtml(organismoPdp.getNombre()));
				organismoPdp.setDescripcion(StringEscapeUtils.escapeHtml(organismoPdp.getDescripcion()));
			}
		}

		return SUCCESS;
	}

	/**
	 * Creates the.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String create() throws BaseException {
		String accion = properties.getProperty("log.ACCION_INSERTAR", null);
		Long accionId = Long.parseLong(properties.getProperty("log.ACCIONID_INSERTAR", null));
		String source = properties.getProperty(PdpDiputacionesAction.LOG_SOURCE_ORGANISMOSPDP, null);
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error("OrganismosAction - create:" + e);
			return PdpDiputacionesAction.NO_USER;
		}
		if (organismo != null) {
			
			if (!validaObligatorios(organismo)) {
				return ERROR;
			}
			boolean existeOrganismo = servicioPdpDiputaciones.existeOrganimo(organismo);
			
			if(!existeOrganismo){
				Integer id = servicioPdpDiputaciones.newOrganismoPdpDiputacion(organismo, source, accion, accionId);
				this.pdpDiputacionesId = id.toString();
				addActionMessageSession(this.getText("plataforma.organismopdp.create.ok"));
			}else{
				addActionErrorSession(this.getText("plataforma.organismopdp.create.organismoExiste"));
				return ERROR;
			}
			

			
		} else {
			addActionErrorSession(this.getText("plataforma.organismopdp.create.error"));
		}

		return SUCCESS;

	}
	
	/**
	 * Valida obligatorios.
	 *
	 * @param aplicacion2 the aplicacion 2
	 * @param isUpdate the is update
	 * @return true, if successful
	 */
	////MIGRADO
	private boolean validaObligatorios(PdpDiputacionesBean aplicacion2) {
		boolean sw = true;		
		if (PlataformaMensajeriaUtil.isEmpty(aplicacion2.getNombre())) {
			addActionErrorSession(this.getText("plataforma.organismopdp.field.nombre.error"));
			sw = false;
		}
		
		return sw;
	}

	/**
	 * Update.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String update() throws BaseException {
		String accion = properties.getProperty(PdpDiputacionesAction.LOG_ACCION_ACTUALIZAR, null);
		Long accionId = Long.parseLong(properties.getProperty(PdpDiputacionesAction.LOG_ACCIONID_ACTUALIZAR, null));
		String source = properties.getProperty(PdpDiputacionesAction.LOG_SOURCE_ORGANISMOSPDP, null);
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error("OrganismosAction - update:" + e);
			return PdpDiputacionesAction.NO_USER;
		}
		PdpDiputacionesBean organismoPdpBBDD = null;
		if (organismo == null) {
			addActionErrorSession(this.getText("plataforma.organismopdp.update.error"));

		} else {
			
			if (organismo.getPdpDiputacionesId() == null) {
				if (pdpDiputacionesId != null) {
					organismo.setPdpDiputacionesId(new Integer(pdpDiputacionesId));
					organismoPdpBBDD = servicioPdpDiputaciones.loadOrganismoPdp(organismo);
				} else {
					String id = (String) request.getAttribute("idOrganismo");
					
					if (id != null) {
						aplicacion.setId(new Long(id));
						organismoPdpBBDD = servicioPdpDiputaciones.loadOrganismoPdp(organismo);
					}
				}
			} else {
				organismoPdpBBDD = servicioPdpDiputaciones.loadOrganismoPdp(organismo);

			}
			if (organismoPdpBBDD != null) {		
				organismoPdpBBDD.setNombre(organismo.getNombre());
				organismoPdpBBDD.setDescripcion(organismo.getDescripcion());
				if(!organismo.getActivo() && organismoPdpBBDD.getActivo()){
					List<TblOrganismos> organismosHijos = servicioOrganismo.getOrganismosByPdp(organismoPdpBBDD.getPdpDiputacionesId().longValue());
					if(!organismosHijos.isEmpty()){
						addActionWarningMessageSession(this.getText("plataforma.organismopdp.update.desactivado"));
					}
				}
				organismoPdpBBDD.setActivo(organismo.getActivo());
				
				if (validaObligatorios(organismoPdpBBDD)) {
					if(replicarHijosServicios){
						List<TblOrganismos> organismosHijos = servicioOrganismo.getOrganismosByPdp(organismoPdpBBDD.getPdpDiputacionesId().longValue());
						if(!organismosHijos.isEmpty()){
							if(!datosServicios.isEmpty()){
								actualizarServiciosOrganismosHijos(organismosHijos);
							}
							
						}else{
							addActionErrorSession(this.getText("plataforma.organismopdp.update.service.error"));
							return SUCCESS;
						}
					}
					if(seleccionarHijosServicios){						
						List<String> listaServiciosHijosSeleccionados = Arrays.asList(serviciosHijosSeleccionados.split(","));  
						if(!listaServiciosHijosSeleccionados.isEmpty()){
							List<TblOrganismos> organismosHijos = new ArrayList<>(); 
							for(String hijoSeleccionado:listaServiciosHijosSeleccionados){								 
								TblOrganismos orgHijo = new TblOrganismos();
								orgHijo.setOrganismoid(Long.valueOf(hijoSeleccionado));
								organismosHijos.add(orgHijo);
							}
							if(!datosServicios.isEmpty()){
								actualizarServiciosOrganismosHijos(organismosHijos);
							}							
						}						
					}
					if(replicarHijosServidores){
						List<TblOrganismos> organismosHijos = servicioOrganismo.getOrganismosByPdp(organismoPdpBBDD.getPdpDiputacionesId().longValue());
						if(!organismosHijos.isEmpty()){
							if(!datosServidor.isEmpty()){
								actualizarServidorOrganismosHijos(organismosHijos);
							}							
						}else{
							addActionErrorSession(this.getText("plataforma.organismopdp.update.server.error"));
							return SUCCESS;
						}
						
					}
					if(seleccionarHijosServidores){						
						List<String> listaServidorHijosSeleccionados = Arrays.asList(servidorHijosSeleccionados.split(","));  
						if(!listaServidorHijosSeleccionados.isEmpty()){
							List<TblOrganismos> organismosHijos = new ArrayList<>(); 
							for(String hijoSeleccionado:listaServidorHijosSeleccionados){								 
								TblOrganismos orgHijo = new TblOrganismos();
								orgHijo.setOrganismoid(Long.valueOf(hijoSeleccionado));
								organismosHijos.add(orgHijo);
							}
							if(!datosServidor.isEmpty()){
								actualizarServidorOrganismosHijos(organismosHijos);
							}							
						}						
					}
					servicioPdpDiputaciones.updateOrganismoPdp(organismoPdpBBDD, source, accion, accionId);
					addActionMessageSession(this.getText("plataforma.organismopdp.update.ok"));
				}
			}
		}
		return SUCCESS;
	}

	private void actualizarServidorOrganismosHijos(List<TblOrganismos> organismosHijos) throws BusinessException {
		String accion = properties.getProperty(PdpDiputacionesAction.LOG_ACCION_ACTUALIZAR, null);
		Long accionId = Long.parseLong(properties.getProperty(PdpDiputacionesAction.LOG_ACCIONID_ACTUALIZAR, null));
		String source = properties.getProperty(PdpDiputacionesAction.LOG_SOURCE_ORGANISMOSPDP, null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_SERVIDOR", null);
		String[] infoServidor = null; 
		infoServidor = datosServidor.split(",");
		ServidoresOrganismosBean servidorOrg = new ServidoresOrganismosBean();			
		servidorOrg.setServidorId(Long.valueOf(infoServidor[0]));
		servidorOrg.setHeaderSMS(infoServidor[1]);
		servidorOrg.setProveedorUsuarioSMS(infoServidor[2]);
		servidorOrg.setProveedorPasswordSMS(infoServidor[3]);
		
		for(TblOrganismos orga:organismosHijos){			
			servidorOrg.setOrganismoId(orga.getOrganismoid());
			List<ServidoresOrganismosBean> listaServidoresOrganismosHijo = servicioServidor.getServidorOrganismo(String.valueOf(orga.getOrganismoid()));
			if(!listaServidoresOrganismosHijo.isEmpty()){
				for(ServidoresOrganismosBean servOrg: listaServidoresOrganismosHijo){
					deleteServidorOrganismo(servOrg.getServidorOrganismoId());
				}
			}			
			servicioServidor.newServidoresOrganismo(servidorOrg, source, accion, accionId, descripcion);
			addActionMessageSession(this.getText("plataforma.organismopdp.update.server"));
		}			
	}

	private void actualizarServiciosOrganismosHijos(List<TblOrganismos> organismosHijos) throws BusinessException {
		String accion = properties.getProperty(PdpDiputacionesAction.LOG_ACCION_ACTUALIZAR, null);
		Long accionId = Long.parseLong(properties.getProperty(PdpDiputacionesAction.LOG_ACCIONID_ACTUALIZAR, null));
		String source = properties.getProperty(PdpDiputacionesAction.LOG_SOURCE_ORGANISMOSPDP, null);
		
		
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_SERVICIO", null);
				
		String[] idServicios = datosServicios.split(",");
		List<String> listaServicios = Arrays.asList(idServicios); 
		
		for(TblOrganismos orga:organismosHijos){
			for(String serv:listaServicios){
				// Comprobamos que en los organismos hijos no tiene ya el servicio añadido
				if(!servicioOrganismo.existeOrganismoServicio(orga.getOrganismoid(), Long.valueOf(serv))){
					//Añadimos el organismo y su servicio a la tabla Tbl_Organismos_servicio
					ServicioOrganismosBean servOrg = new ServicioOrganismosBean();
					servOrg.setOrganismoId(orga.getOrganismoid().intValue());
					servOrg.setServicioId(Integer.valueOf(serv));
					servicioServicio.newServicioOrganismo(servOrg, source, accion, accionId, descripcion);
					addActionMessageSession(this.getText("plataforma.organismopdp.update.service"));
				}
			}
			
		}
	}

	/**
	 * Load.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String load() throws BaseException {
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error("OrganismosPdpAction - load:" + e);
			return PdpDiputacionesAction.NO_USER;
		}
		if (pdpDiputacionesId == null)
			throw new BusinessException("EL idPdpDiputaciones recibido es nulo");
		try {
			organismo = new PdpDiputacionesBean();
			organismo.setPdpDiputacionesId(new Integer(pdpDiputacionesId));
			organismo = servicioPdpDiputaciones.loadOrganismoPdp(organismo);
			comboOrganismosHijos = cargarComboOrganismosHijosPdp();

			return SUCCESS;
		} catch (NumberFormatException | BusinessException e) {
			String mensg = this.getText("errors.action.organismopdp.loadOrganismo", new String[] { organismo.getPdpDiputacionesId().toString() });
			logger.error("OrganismosPdpAction - load:" + e);
			throw new BusinessException(mensg);
		} 

	}
	
	/**
	 * Delete.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String delete() throws BaseException {	
		String accion = properties.getProperty(PdpDiputacionesAction.LOG_ACCION_ELIMINAR, null);
		Long accionId = Long.parseLong(properties.getProperty(PdpDiputacionesAction.LOG_ACCIONID_ELIMINAR, null));
		String source = properties.getProperty(PdpDiputacionesAction.LOG_SOURCE_ORGANISMOSPDP, null);
				
		if (getRequest().getSession().getAttribute(PdpDiputacionesAction.INFO_USER) == null)
			return PdpDiputacionesAction.NO_USER;
		if (pdpDiputacionesId == null) {
			addActionErrorSession(this.getText("plataforma.organismopdp.delete.error"));

		} else {
			//Metodo comprobacion si esta asignado a un organismo normal
			List<TblOrganismos> listOrgHijos = servicioOrganismo.getOrganismosByPdp(Long.parseLong(pdpDiputacionesId));
			if(listOrgHijos.isEmpty()){
				servicioPdpDiputaciones.deleteOrganismoPdp(Long.parseLong(pdpDiputacionesId), source, accion, accionId);
				addActionMessageSession(this.getText("plataforma.organismopdp.delete.ok"));
			}else{
				for(TblOrganismos org:listOrgHijos){
					servicioOrganismo.deleteOrganismoPdp(org, source, accion, accionId);
				}
				servicioPdpDiputaciones.deleteOrganismoPdp(Long.parseLong(pdpDiputacionesId), source, accion, accionId);
				addActionMessageSession(this.getText("plataforma.organismopdp.delete.ok"));
			}
			
		}
		return SUCCESS;
	}

	/**
	 * Delete selected.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	///MIGRADO
	public String deleteSelected() throws BaseException {		
		String accion = properties.getProperty(PdpDiputacionesAction.LOG_ACCION_ELIMINAR, null);
		Long accionId = Long.parseLong(properties.getProperty(PdpDiputacionesAction.LOG_ACCIONID_ELIMINAR, null));
		String source = properties.getProperty(PdpDiputacionesAction.LOG_SOURCE_ORGANISMOSPDP, null);
		StringBuilder listaOrg = new StringBuilder("");
		
		if (getRequest().getSession().getAttribute(PdpDiputacionesAction.INFO_USER) == null)
			return PdpDiputacionesAction.NO_USER;
		if (checkDelList == null) {
			addActionErrorSession(this.getText("plataforma.organimopdp.deleteSelected.error"));

		} else {
			for (String idOrganismoPdpCheck : checkDelList) {				
				//Metodo comprobacion si esta asignado a un organismo normal
				List<TblOrganismos> listOrgHijos = servicioOrganismo.getOrganismosByPdp(Long.parseLong(idOrganismoPdpCheck));
				if(listOrgHijos.isEmpty()){
					servicioPdpDiputaciones.deleteOrganismoPdp(Long.parseLong(idOrganismoPdpCheck), source, accion, accionId);					
				}else{
					PdpDiputacionesBean orgPdp = new PdpDiputacionesBean();
					orgPdp.setPdpDiputacionesId(Integer.valueOf(idOrganismoPdpCheck));
					orgPdp = servicioPdpDiputaciones.loadOrganismoPdp(orgPdp);
					listaOrg.append("[" + orgPdp.getNombre() + "]" + "\t");					
				}				
			}			
			if(!listaOrg.toString().equals("")){
				addActionErrorSession(this.getText("plataforma.organismopdp.deleteSelectedHijosActivos.error") + " " + listaOrg);
			}else{
				addActionMessageSession(this.getText("plataforma.organismopdp.deleteSelected.ok"));
			}
		}
		return SUCCESS;

	}
	
    /* (non-Javadoc)
     * @see es.mpr.plataformamensajeria.impl.PlataformaPaginationAction#prepare()
     */
    ///MIGRADO
	@SuppressWarnings("rawtypes")
	@Override
	public void prepare() throws Exception {
		session = (Map) ActionContext.getContext().get("session");
		recovery = (String) session.get(RECOVERY);
	

		if (pdpDiputacionesId != null) {			
			if (organismo == null)
				load();
			comboServicioOrganismos = cargarComboServicioOrganismos();
			comboServidoresOrganismos = cargarComboServidoresOrganismos();
		}
	}

	/**
	 * Cargar combo servicio organismos.
	 *
	 * @return the list
	 */
	///MIGRADO
	private List<KeyValueObject> cargarComboServicioOrganismos() {
		List<KeyValueObject> result = new ArrayList<>();

		KeyValueObject option;
		String listaServ = properties.getProperty("altasmasivas.comboservicios.serviciosAeatGiss", null);
		List<String> serviciosAeatGiss = new ArrayList<String>(Arrays.asList(listaServ.split(",")));
		ArrayList<ServicioBean> keys = null;
		try {
			keys = (ArrayList<ServicioBean>) servicioServicio.getServiciosMultiorganismo();
		} catch (BusinessException e) {
			logger.error("OrganismosAction - cargarComboServicioOrganismos:" + e);
		}

		if (keys != null && !keys.isEmpty())
			for (ServicioBean key : keys) {
				for(String idServ : serviciosAeatGiss){
					if(idServ.equals(key.getServicioId().toString())){
						option = new KeyValueObject();
						option.setCodigo(key.getServicioId().toString());
						option.setDescripcion(key.getNombre());
						result.add(option);
					}
				}
			}
		return result;
	}

	/**
	 * Cargar combo servidores organismos.
	 *
	 * @return the list
	 */
	///MIGRADO
	private List<KeyValueObject> cargarComboServidoresOrganismos() {
		List<KeyValueObject> result = new ArrayList<>();

		KeyValueObject option;

		ArrayList<ServidorBean> keys = null;
		try {
			keys = (ArrayList<ServidorBean>) servicioServidor.getServidoresYProveedores(properties.getProperty("generales.TIPO_SERVIDOR_SMS", null));
		} catch (BusinessException e) {
			logger.error("OrganismosAction - cargarComboServicioOrganismos:" + e);
		}

		if (keys != null && !keys.isEmpty())
			for (ServidorBean key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getServidorid().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		return result;
	}

	/**
	 * Cargar combo organismos hijos.
	 * @param  
	 *
	 * @return the list
	 */
	///MIGRADO
	private List<KeyValueObjects> cargarComboOrganismosHijosPdp() {
		List<KeyValueObjects> result = new ArrayList<>();

		KeyValueObjects option;

		ArrayList<TblOrganismos> keys = null;
		keys = (ArrayList<TblOrganismos>) servicioOrganismo.getOrganismosByPdp(Long.valueOf(pdpDiputacionesId));

		if (keys != null && !keys.isEmpty())
			for (TblOrganismos key : keys) {
				option = new KeyValueObjects();
				option.setCodigo(key.getDir3());
				option.setNombre(key.getNombre());
				option.setDescripcion(String.valueOf(key.getOrganismoid()));
				result.add(option);
			}
		return result;
	}

	/**
	 * Delete servidor organismo.
	 *
	 * @param servidorOrganismoId the servidor organismo id
	 * @throws BusinessException the business exception
	 */
	///MIGRADO
	private void deleteServidorOrganismo(Long servidorOrganismoId) throws BusinessException {
		String accion = properties.getProperty(PdpDiputacionesAction.LOG_ACCION_ELIMINAR, null);
		Long accionId = Long.parseLong(properties.getProperty(PdpDiputacionesAction.LOG_ACCIONID_ELIMINAR, null));
		String source = properties.getProperty(PdpDiputacionesAction.LOG_SOURCE_ORGANISMOSPDP, null);
		String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ELIMINAR_SERVIDOR_ORGANISMO", null);
		ServidoresOrganismosBean servidorOrganismos = new ServidoresOrganismosBean();
		servidorOrganismos.setServidorOrganismoId(servidorOrganismoId);
		servicioServidor.deleteServidorOrganismos(servidorOrganismos, source, accion, accionId, descripcion);
	}


	/**
	 * Obtener volver.
	 *
	 * @return volver
	 */
	@SuppressWarnings("unchecked")
	public String getVolver() {
		String volver = "listarPdpDiputaciones.action";
		if (!PlataformaMensajeriaUtil.isEmpty(from) && !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volver = from + "?" + var + "=" + idFrom;
			session.put(RECOVERY, volver);
		} else if (session.get(RECOVERY) != null) {
			volver = (String) session.get(RECOVERY);
			session.put(RECOVERY, null);
		}
		return volver;
	}
	
	
	/**
	 * Modificar servidor.
	 *
	 * @param aplicacion new servidor
	 */
	public void setServidor(AplicacionBean aplicacion) {
		this.aplicacion = aplicacion;
	}

	/**
	 * Obtener id servidor.
	 *
	 * @return id servidor
	 */
	public String getIdServidor() {
		return idServidor;
	}

	/**
	 * Obtener result count.
	 *
	 * @return result count
	 */
	public String getResultCount() {
		return resultCount;
	}

	/**
	 * Modificar result count.
	 *
	 * @param resultCount new result count
	 */
	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}

	/**
	 * Obtener check del list.
	 *
	 * @return check del list
	 */
	public String[] getCheckDelList() {
		return checkDelList;
	}

	/**
	 * Modificar check del list.
	 *
	 * @param checkDelList new check del list
	 */
	public void setCheckDelList(String[] checkDelList) {
		this.checkDelList = checkDelList;
	}

	/**
	 * Obtener servicio usuario aplicacion.
	 *
	 * @return servicio usuario aplicacion
	 */
	public ServicioUsuarioAplicacion getServicioUsuarioAplicacion() {
		return servicioUsuarioAplicacion;
	}

	/**
	 * Modificar servicio usuario aplicacion.
	 *
	 * @param servicioUsuarioAplicacion new servicio usuario aplicacion
	 */
	public void setServicioUsuarioAplicacion(ServicioUsuarioAplicacion servicioUsuarioAplicacion) {
		this.servicioUsuarioAplicacion = servicioUsuarioAplicacion;
	}

	/**  detalle aplicacion. */
	transient DetalleAplicacionBean detalleAplicacion;

	/**
	 * Obtener detalle aplicacion.
	 *
	 * @return detalle aplicacion
	 */
	public DetalleAplicacionBean getDetalleAplicacion() {
		return detalleAplicacion;
	}

	/**
	 * Modificar detalle aplicacion.
	 *
	 * @param detalleAplicacion new detalle aplicacion
	 */
	public void setDetalleAplicacion(DetalleAplicacionBean detalleAplicacion) {
		this.detalleAplicacion = detalleAplicacion;
	}

	/**
	 * Obtener servicio servicio.
	 *
	 * @return servicio servicio
	 */
	public ServicioServicio getServicioServicio() {
		return servicioServicio;
	}

	/**
	 * Modificar servicio servicio.
	 *
	 * @param servicioServicio new servicio servicio
	 */
	public void setServicioServicio(ServicioServicio servicioServicio) {
		this.servicioServicio = servicioServicio;
	}

	/**
	 * Modificar servicio servidor.
	 *
	 * @param servicioServidor new servicio servidor
	 */
	public void setServicioServidor(ServicioServidor servicioServidor) {
		this.servicioServidor = servicioServidor;
	}

	/**
	 * Obtener servicio servidor.
	 *
	 * @return servicio servidor
	 */
	public ServicioServidor getServicioServidor() {
		return servicioServidor;
	}

	/**
	 * Obtener id servidor push.
	 *
	 * @return id servidor push
	 */
	public String getIdServidorPush() {
		return idServidorPush;
	}

	/**
	 * Modificar id servidor push.
	 *
	 * @param idServidorPush new id servidor push
	 */
	public void setIdServidorPush(String idServidorPush) {
		this.idServidorPush = idServidorPush;
	}

	/**
	 * Obtener servicio organismo.
	 *
	 * @return servicio organismo
	 */
	public ServicioPdpDiputaciones getServicioOrganismoPdp() {
		return servicioPdpDiputaciones;
	}

	/**
	 * Modificar servicio organismo.
	 *
	 * @param servicioOrganismo new servicio organismo
	 */
	public void setServicioOrganismoPdp(ServicioPdpDiputaciones servicioOrganismoPdp) {
		this.servicioPdpDiputaciones = servicioOrganismoPdp;
	}

	/**
	 * Obtener lista servicio organismos.
	 *
	 * @return lista servicio organismos
	 */
	public List<ServicioOrganismosBean> getListaServicioOrganismos() {
		return listaServicioOrganismos;
	}

	/**
	 * Modificar lista servicio organismos.
	 *
	 * @param listaServicioOrganismos new lista servicio organismos
	 */
	public void setListaServicioOrganismos(List<ServicioOrganismosBean> listaServicioOrganismos) {
		this.listaServicioOrganismos = listaServicioOrganismos;
	}

	/**
	 * Obtener servicio organismos.
	 *
	 * @return servicio organismos
	 */
	public ServicioOrganismosBean getServicioOrganismos() {
		return servicioOrganismos;
	}

	/**
	 * Modificar servicio organismos.
	 *
	 * @param servicioOrganismos new servicio organismos
	 */
	public void setServicioOrganismos(ServicioOrganismosBean servicioOrganismos) {
		this.servicioOrganismos = servicioOrganismos;
	}

	/**
	 * Obtener combo servicio organismos.
	 *
	 * @return combo servicio organismos
	 */
	public List<KeyValueObject> getComboServicioOrganismos() {
		return comboServicioOrganismos;
	}

	/**
	 * Modificar combo servicio organismos.
	 *
	 * @param comboServicioOrganismos new combo servicio organismos
	 */
	public void setComboServicioOrganismos(List<KeyValueObject> comboServicioOrganismos) {
		this.comboServicioOrganismos = comboServicioOrganismos;
	}

	/**
	 * Obtener servicio.
	 *
	 * @return servicio
	 */
	public ServicioBean getServicio() {
		return servicio;
	}

	/**
	 * Modificar servicio.
	 *
	 * @param servicio new servicio
	 */
	public void setServicio(ServicioBean servicio) {
		this.servicio = servicio;
	}

	/**
	 * Modificar combo servidores.
	 *
	 * @param comboServidores new combo servidores
	 */
	public void setComboServidores(List<KeyValueObject> comboServidores) {
		this.comboServidores = comboServidores;
	}

	/**
	 * Obtener id pdpDiputacionesId.
	 *
	 * @return id pdpDiputacionesId
	 */
	public String getPdpDiputacionesId() {
		return pdpDiputacionesId;
	}

	/**
	 * Modificar id pdpDiputacionesId.
	 *
	 * @param idOrganismo new id pdpDiputacionesId
	 */
	public void setPdpDiputacionesId(String pdpDiputacionesId) {
		this.pdpDiputacionesId = pdpDiputacionesId;
	}

	/**
	 * Obtener id servicio organismo.
	 *
	 * @return id servicio organismo
	 */
	public String getIdServicioOrganismo() {
		return idServicioOrganismo;
	}

	/**
	 * Modificar id servicio organismo.
	 *
	 * @param idServicioOrganismo new id servicio organismo
	 */
	public void setIdServicioOrganismo(String idServicioOrganismo) {
		this.idServicioOrganismo = idServicioOrganismo;
	}

	/**
	 * Obtener servidor organismo id.
	 *
	 * @return servidor organismo id
	 */
	public String getServidorOrganismoId() {
		return servidorOrganismoId;
	}

	/**
	 * Modificar servidor organismo id.
	 *
	 * @param servidorOrganismoId new servidor organismo id
	 */
	public void setServidorOrganismoId(String servidorOrganismoId) {
		this.servidorOrganismoId = servidorOrganismoId;
	}

	/**
	 * Obtener servidor organismo.
	 *
	 * @return servidor organismo
	 */
	public ServidoresOrganismosBean getServidorOrganismo() {
		return servidorOrganismo;
	}

	/**
	 * Modificar servidor organismo.
	 *
	 * @param servidorOrganismo new servidor organismo
	 */
	public void setServidorOrganismo(ServidoresOrganismosBean servidorOrganismo) {
		this.servidorOrganismo = servidorOrganismo;
	}

	/**
	 * Obtener lista servidores organismos.
	 *
	 * @return lista servidores organismos
	 */
	public List<ServidoresOrganismosBean> getListaServidoresOrganismos() {
		return listaServidoresOrganismos;
	}

	/**
	 * Modificar lista servidores organismos.
	 *
	 * @param listaServidoresOrganismos new lista servidores organismos
	 */
	public void setListaServidoresOrganismos(List<ServidoresOrganismosBean> listaServidoresOrganismos) {
		this.listaServidoresOrganismos = listaServidoresOrganismos;
	}

	/**
	 * Obtener combo servidores organismos.
	 *
	 * @return combo servidores organismos
	 */
	public List<KeyValueObject> getComboServidoresOrganismos() {
		return comboServidoresOrganismos;
	}

	/**
	 * Modificar combo servidores organismos.
	 *
	 * @param comboServidoresOrganismos new combo servidores organismos
	 */
	public void setComboServidoresOrganismos(List<KeyValueObject> comboServidoresOrganismos) {
		this.comboServidoresOrganismos = comboServidoresOrganismos;
	}

	/**
	 * Obtener lista planificaciones servicio.
	 *
	 * @return lista planificaciones servicio
	 */
	public List<PlanificacionBean> getListaPlanificacionesServicio() {
		return listaPlanificacionesServicio;
	}

	/**
	 * Modificar lista planificaciones servicio.
	 *
	 * @param listaPlanificacionesServicio new lista planificaciones servicio
	 */
	public void setListaPlanificacionesServicio(List<PlanificacionBean> listaPlanificacionesServicio) {
		this.listaPlanificacionesServicio = listaPlanificacionesServicio;
	}

	/**
	 * Obtener combo servidores plan.
	 *
	 * @return combo servidores plan
	 */
	public List<KeyValueObject> getComboServidoresPlan() {
		return comboServidoresPlan;
	}

	/**
	 * Modificar combo servidores plan.
	 *
	 * @param comboServidoresPlan new combo servidores plan
	 */
	public void setComboServidoresPlan(List<KeyValueObject> comboServidoresPlan) {
		this.comboServidoresPlan = comboServidoresPlan;
	}

	/**
	 * Obtener combo servicios plan.
	 *
	 * @return combo servicios plan
	 */
	public List<KeyValueObject> getComboServiciosPlan() {
		return comboServiciosPlan;
	}

	/**
	 * Modificar combo servicios plan.
	 *
	 * @param comboServiciosPlan new combo servicios plan
	 */
	public void setComboServiciosPlan(List<KeyValueObject> comboServiciosPlan) {
		this.comboServiciosPlan = comboServiciosPlan;
	}

	/**
	 * Obtener servidor.
	 *
	 * @return servidor
	 */
	public ServidorBean getServidor() {
		return servidor;
	}

	/**
	 * Modificar servidor.
	 *
	 * @param servidor new servidor
	 */
	public void setServidor(ServidorBean servidor) {
		this.servidor = servidor;
	}

	/**
	 * Obtener planificacion organismo.
	 *
	 * @return planificacion organismo
	 */
	public PlanificacionBean getPlanificacionOrganismo() {
		return planificacionOrganismo;
	}

	/**
	 * Modificar planificacion organismo.
	 *
	 * @param planificacionOrganismo new planificacion organismo
	 */
	public void setPlanificacionOrganismo(PlanificacionBean planificacionOrganismo) {
		this.planificacionOrganismo = planificacionOrganismo;
	}

	/**
	 * Obtener check del list organismos servicios.
	 *
	 * @return check del list organismos servicios
	 */
	public String[] getCheckDelListOrganismosServicios() {
		return checkDelListOrganismosServicios;
	}

	/**
	 * Modificar check del list organismos servicios.
	 *
	 * @param checkDelListOrganismosServicios new check del list organismos servicios
	 */
	public void setCheckDelListOrganismosServicios(String[] checkDelListOrganismosServicios) {
		this.checkDelListOrganismosServicios = checkDelListOrganismosServicios;
	}

	/**
	 * Obtener check del list servidor organismos.
	 *
	 * @return check del list servidor organismos
	 */
	public String[] getCheckDelListServidorOrganismos() {
		return checkDelListServidorOrganismos;
	}

	/**
	 * Modificar check del list servidor organismos.
	 *
	 * @param checkDelListServidorOrganismos new check del list servidor organismos
	 */
	public void setCheckDelListServidorOrganismos(String[] checkDelListServidorOrganismos) {
		this.checkDelListServidorOrganismos = checkDelListServidorOrganismos;
	}

	/**
	 * Obtener check del list planificaciones organismos.
	 *
	 * @return check del list planificaciones organismos
	 */
	public String[] getCheckDelListPlanificacionesOrganismos() {
		return checkDelListPlanificacionesOrganismos;
	}

	/**
	 * Modificar check del list planificaciones organismos.
	 *
	 * @param checkDelListPlanificacionesOrganismos new check del list planificaciones organismos
	 */
	public void setCheckDelListPlanificacionesOrganismos(String[] checkDelListPlanificacionesOrganismos) {
		this.checkDelListPlanificacionesOrganismos = checkDelListPlanificacionesOrganismos;
	}
	
	/**
	 * Obtener check password.
	 *
	 * @return check password
	 */
	public String getCheckPassword() {
		return checkPassword;
	}

	/**
	 * Modificar check password.
	 *
	 * @param checkPassword new check password
	 */
	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}
	
	/**
	 * Obtener id proveedor SMS.
	 *
	 * @return id proveedor SMS
	 */
	public String getIdProveedorSMS() {
		return idProveedorSMS;
	}

	/**
	 * Modificar id proveedor SMS.
	 *
	 * @param idProveedorSMS new id proveedor SMS
	 */
	public void setIdProveedorSMS(String idProveedorSMS) {
		this.idProveedorSMS = idProveedorSMS;
	}

	/**
	 * Obtener id receptor SMS.
	 *
	 * @return id receptor SMS
	 */
	public String getIdReceptorSMS() {
		return idReceptorSMS;
	}

	/**
	 * Modificar id receptor SMS.
	 *
	 * @param idReceptorSMS new id receptor SMS
	 */
	public void setIdReceptorSMS(String idReceptorSMS) {
		this.idReceptorSMS = idReceptorSMS;
	}

	/**
	 * Modificar id servidor.
	 *
	 * @param idServidor new id servidor
	 */
	public void setIdServidor(String idServidor) {
		this.idServidor = idServidor;
	}

	/**
	 * Obtener id servicio.
	 *
	 * @return id servicio
	 */
	public String getIdServicio() {
		return idServicio;
	}

	/**
	 * Modificar id servicio.
	 *
	 * @param idServicio new id servicio
	 */
	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}

	/**
	 * Obtener lista organismos.
	 *
	 * @return lista organismos
	 */
	public List<PdpDiputacionesBean> getListaOrganismos() {
		return listaOrganismosPdp;
	}

	/**
	 * Modificar lista organismos.
	 *
	 * @param listaOrganismos new lista organismos
	 */
	public void setListaOrganismos(List<PdpDiputacionesBean> listaOrganismosPdp) {
		this.listaOrganismosPdp = listaOrganismosPdp;
	}

	/**
	 * Obtener aplicacion.
	 *
	 * @return aplicacion
	 */
	public AplicacionBean getAplicacion() {
		return aplicacion;
	}

	/**
	 * Modificar aplicacion.
	 *
	 * @param aplicacion new aplicacion
	 */
	public void setAplicacion(AplicacionBean aplicacion) {
		this.aplicacion = aplicacion;
	}
	
	/**
	 * Obtener combo servidores.
	 *
	 * @return combo servidores
	 */
	public List<KeyValueObject> getComboServidores() {
		return comboServidores;
	}

	/**
	 * Obtener id planificacion.
	 *
	 * @return id planificacion
	 */
	public String getIdPlanificacion() {
		return idPlanificacion;
	}

	/**
	 * Modificar id planificacion.
	 *
	 * @param idPlanificacion new id planificacion
	 */
	public void setIdPlanificacion(String idPlanificacion) {
		this.idPlanificacion = idPlanificacion;
	}

	/**
	 * Obtener combo tipos estados.
	 *
	 * @return the comboTiposEstados
	 */
	public List<KeyValueObject> getComboTiposEstados() {
		return comboTiposEstados;
	}

	/**
	 * Modificar combo tipos estados.
	 *
	 * @param comboTiposEstados the comboTiposEstados to set
	 */
	public void setComboTiposEstados(List<KeyValueObject> comboTiposEstados) {
		this.comboTiposEstados = comboTiposEstados;
	}


	/**
	 * Obtener recovery.
	 *
	 * @return recovery
	 */
	public String getRecovery() {
		return recovery;
	}


	/**
	 * Modificar recovery.
	 *
	 * @param recovery new recovery
	 */
	public void setRecovery(String recovery) {
		this.recovery = recovery;
	}	

	/**
	 * @return the comboOrganismosHijos
	 */
	public List<KeyValueObjects> getComboOrganismosHijos() {
		return comboOrganismosHijos;
	}

	/**
	 * @param comboOrganismosHijos the comboOrganismosHijos to set
	 */
	public void setComboOrganismosHijos(List<KeyValueObjects> comboOrganismosHijos) {
		this.comboOrganismosHijos = comboOrganismosHijos;
	}

	/**
	 * @return the organismo
	 */
	public PdpDiputacionesBean getOrganismo() {
		return organismo;
	}

	/**
	 * @param organismo the organismo to set
	 */
	public void setOrganismo(PdpDiputacionesBean organismo) {
		this.organismo = organismo;
	}

	/**
	 * @return the replicarHijosServicios
	 */
	public boolean isReplicarHijosServicios() {
		return replicarHijosServicios;
	}

	/**
	 * @param replicarHijosServicios the replicarHijosServicios to set
	 */
	public void setReplicarHijosServicios(boolean replicarHijosServicios) {
		this.replicarHijosServicios = replicarHijosServicios;
	}

	/**
	 * @return the seleccionarHijosServicios
	 */
	public boolean isSeleccionarHijosServicios() {
		return seleccionarHijosServicios;
	}

	/**
	 * @param seleccionarHijosServicios the seleccionarHijosServicios to set
	 */
	public void setSeleccionarHijosServicios(boolean seleccionarHijosServicios) {
		this.seleccionarHijosServicios = seleccionarHijosServicios;
	}

	/**
	 * @return the replicarHijosServidores
	 */
	public boolean isReplicarHijosServidores() {
		return replicarHijosServidores;
	}

	/**
	 * @param replicarHijosServidores the replicarHijosServidores to set
	 */
	public void setReplicarHijosServidores(boolean replicarHijosServidores) {
		this.replicarHijosServidores = replicarHijosServidores;
	}

	/**
	 * @return the seleccionarHijosServidores
	 */
	public boolean isSeleccionarHijosServidores() {
		return seleccionarHijosServidores;
	}

	/**
	 * @param seleccionarHijosServidores the seleccionarHijosServidores to set
	 */
	public void setSeleccionarHijosServidores(boolean seleccionarHijosServidores) {
		this.seleccionarHijosServidores = seleccionarHijosServidores;
	}

	/**
	 * @return the servicioOrganismo
	 */
	public ServicioOrganismo getServicioOrganismo() {
		return servicioOrganismo;
	}

	/**
	 * @param servicioOrganismo the servicioOrganismo to set
	 */
	public void setServicioOrganismo(ServicioOrganismo servicioOrganismo) {
		this.servicioOrganismo = servicioOrganismo;
	}

	/**
	 * @return the datosServicios
	 */
	public String getDatosServicios() {
		return datosServicios;
	}

	/**
	 * @param datosServicios the datosServicios to set
	 */
	public void setDatosServicios(String datosServicios) {
		this.datosServicios = datosServicios;
	}

	/**
	 * @return the datosServidor
	 */
	public String getDatosServidor() {
		return datosServidor;
	}

	/**
	 * @param datosServidor the datosServidor to set
	 */
	public void setDatosServidor(String datosServidor) {
		this.datosServidor = datosServidor;
	}

	/**
	 * @return the organismoArbol
	 */
	public OrganismoBean getOrganismoArbol() {
		return organismoArbol;
	}

	/**
	 * @param organismoArbol the organismoArbol to set
	 */
	public void setOrganismoArbol(OrganismoBean organismoArbol) {
		this.organismoArbol = organismoArbol;
	}

	/**
	 * @return the serviciosHijosSeleccionados
	 */
	public String getServiciosHijosSeleccionados() {
		return serviciosHijosSeleccionados;
	}

	/**
	 * @param serviciosHijosSeleccionados the serviciosHijosSeleccionados to set
	 */
	public void setServiciosHijosSeleccionados(String serviciosHijosSeleccionados) {
		this.serviciosHijosSeleccionados = serviciosHijosSeleccionados;
	}

	/**
	 * @return the servidorHijosSeleccionados
	 */
	public String getServidorHijosSeleccionados() {
		return servidorHijosSeleccionados;
	}

	/**
	 * @param servidorHijosSeleccionados the servidorHijosSeleccionados to set
	 */
	public void setServidorHijosSeleccionados(String servidorHijosSeleccionados) {
		this.servidorHijosSeleccionados = servidorHijosSeleccionados;
	}
	
	
}
