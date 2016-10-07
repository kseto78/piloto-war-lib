package es.mpr.plataformamensajeria.web.action.servidores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.security.context.SecurityContextHolder;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.Preparable;

import es.mpr.plataformamensajeria.beans.ParametroServidorBean;
import es.mpr.plataformamensajeria.beans.PlanificacionBean;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.beans.TipoParametroBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioParametroServidor;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPlanificacion;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioTipoParametro;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.plataformamensajeria.util.SMTPConnectionTest;

/**
 * <p>
 * Clase Action de Struts2 para la gesti&oacute;n de los organismos.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n,
 * borrado y listado de los Organismos
 * 
 * @author Altran
 * 
 */

public class ServidoresAction extends PlataformaPaginationAction implements
		ServletRequestAware, Preparable {

	private static final long serialVersionUID = 1L;

	private static final String REQUEST_ATTRIBUTE_TOTALSIZE = "totalSize";
	private static final String REQUEST_ATTRIBUTE_PAGESIZE = "pageSize";

	private static final Integer PAGESIZE = new Integer(20); // Elementos por
																// pagina
	List<KeyValueObject> comboTipoParametros = new ArrayList<KeyValueObject>();
	public List<ServidorBean> listaServidores = null;
	private ServicioServidor servicioServidor;
	private ServicioTipoParametro servicioTipoParametro;
	private ServicioParametroServidor servicioParametroServidor;
	private ServicioPlanificacion servicioPlanificacion;

	private String tipoParametroId;

	private String idServidor;
	private String resultCount;
	private String[] checkDelList;
	private ParametroServidorBean parametroServidor;
	private ServidorBean servidor;
	private PlanificacionBean planificacionServidor;

	private String parametroServidorId;
	private String planificacionId;

	public String getPlanificacionId() {
		return planificacionId;
	}

	public void setPlanificacionId(String planificacionId) {
		this.planificacionId = planificacionId;
	}

	private List<ParametroServidorBean> listaParametrosServidor = null;
	private List<PlanificacionBean> listaPlanificacionesServidor = null;

	public String newSearch() throws BaseException {
		return SUCCESS;
	}

	public String search() throws BaseException {
		
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		
		int page = getPage("tableId"); // Pagina a mostrar
		String order = getOrder("tableId"); // Ordenar de modo ascendente o
											// descendente
		String columnSort = getColumnSort("tableId"); // Columna usada para
														// ordenar

		if (servidor != null)
			if (servidor.getNombre() != null
					&& servidor.getNombre().length() <= 0)
				servidor.setNombre(null);

		int inicio = (page - 1) * PAGESIZE;
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ServidorBean> result = servicioServidor.getServidores(
				inicio, (export)?-1:PAGESIZE, order, columnSort, servidor);
		Integer totalSize = result.getTotalList();

		listaServidores = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);
		
		if (!export) {
			getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, PAGESIZE);
		} else {
			getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, totalSize);
		}

		if (listaServidores != null && !listaServidores.isEmpty()) {
			for (int indice = 0; indice < listaServidores.size(); indice++) {

				ServidorBean servidor = listaServidores.get(indice);
				servidor.setNombre(StringEscapeUtils.escapeHtml(servidor
						.getNombre()));
				servidor.setDescripcion(StringEscapeUtils.escapeHtml(servidor
						.getDescripcion()));
			}
		}

		return SUCCESS;
	}

	public String execute() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		int page = getPage("tableId"); // Pagina a mostrar
		String order = getOrder("tableId"); // Ordenar de modo ascendente o
											// descendente
		String columnSort = getColumnSort("tableId"); // Columna usada para
														// ordenar

		if (servidor != null)
			if (servidor.getNombre() != null
					&& servidor.getNombre().length() <= 0)
				servidor.setNombre(null);

		int inicio = (page - 1) * PAGESIZE;
		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
		PaginatedList<ServidorBean> result = servicioServidor.getServidores(
				inicio, (export)?-1:PAGESIZE, order, columnSort, servidor);
		Integer totalSize = result.getTotalList();

		listaServidores = result.getPageList();

		// Atributos de request
		getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);
		
		if (!export) {
			getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, PAGESIZE);
		} else {
			getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, totalSize);
		}
		// getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, PAGESIZE);

		if (listaServidores != null && !listaServidores.isEmpty()) {
			for (int indice = 0; indice < listaServidores.size(); indice++) {

				ServidorBean servidor = listaServidores.get(indice);
				servidor.setNombre(StringEscapeUtils.escapeHtml(servidor
						.getNombre()));
				servidor.setDescripcion(StringEscapeUtils.escapeHtml(servidor
						.getDescripcion()));
			}
		}

		return SUCCESS;
	}

	public String create() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		if (servidor != null) { 
			// throw new BusinessException("EL servidor recibido es nulo");
			if (servidor.getIsActivo() != null
					&& servidor.getIsActivo().indexOf("'activo'") != -1) {
				servidor.setActivo(new Integer(1));
			} else {
				servidor.setActivo(new Integer(0));
			}
			if (validaServidor(servidor)) {
				Long idServidor = servicioServidor.newServidor(servidor);
				this.idServidor = idServidor.toString();
				addActionMessageSession(this
						.getText("plataforma.servidores.create.ok"));
			} else {
				return ERROR;
			}
		} else {
			addActionErrorSession(this
					.getText("plataforma.servidores.create.error"));
			return ERROR;
		}
		return SUCCESS;
	}

	private boolean validaServidor(ServidorBean servidor) {
		boolean sw = true;
		if (PlataformaMensajeriaUtil.isEmpty(servidor.getNombre())) {
			addActionErrorSession(this
					.getText("plataforma.servidores.field.nombre.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(servidor.getDescripcion())) {
			addActionErrorSession(this
					.getText("plataforma.servidores.field.descripcion.error"));
			sw = false;
		}
		return sw;
	}

	public String update() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		ServidorBean servidorBBDD = null;
		if (servidor == null) {
			// throw new BusinessException("EL servidor recibido es nulo");
			addActionErrorSession(this
					.getText("plataforma.servidores.update.error"));
		} else {
			System.out.println("[ServidoresAction - Idservidor] valor == "
					+ servidor.getServidorId());
			if (servidor.getServidorId() == null) {
				if (idServidor != null) {
					servidor.setServidorId(new Long(idServidor));
					servidorBBDD = servicioServidor.loadServidor(servidor);
				} else {
					String idServidor = (String) request
							.getAttribute("idServidor");
					System.out
							.println("[ServidoresAction - request.getAttribute('idServidor)' == "
									+ idServidor);
					if (idServidor != null) {
						servidor.setId(new Long(idServidor));
						servidorBBDD = servicioServidor.loadServidor(servidor);
					}
				}

				System.out
						.println("[ServidoresAction - Idservidor despues de setear idServidor] valor == "
								+ servidor.getServidorId());
			} else {
				servidorBBDD = servicioServidor.loadServidor(servidor);

			}
			if (servidorBBDD != null) {
				servidorBBDD.setNombre(servidor.getNombre());
				servidorBBDD.setDescripcion(servidor.getDescripcion());
				servidorBBDD.setActivo(servidor.getActivo());
				servidorBBDD.setPorDefecto(servidor.getPorDefecto());
			}
			if (validaServidor(servidorBBDD)) {
				
				servicioServidor.updateServidor(servidorBBDD);
				addActionMessageSession(this
						.getText("plataforma.servidores.update.ok"));
			}
		}
		// addActionError("Errores en el update de servidores");
		return SUCCESS;

	}

	public String load() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		if (idServidor == null)
			throw new BusinessException("EL idServidor recibido es nulo");
		try {
			servidor = new ServidorBean();
			servidor.setServidorId(new Long(idServidor));
			servidor = servicioServidor.loadServidor(servidor);
			return SUCCESS;
		} catch (NumberFormatException e) {
			String mensg = this.getText(
					"errors.action.organismo.loadOrganismo",
					new String[] { servidor.getServidorId().toString() });
			throw new BusinessException(mensg);
		} catch (BusinessException e) {
			String mensg = this.getText(
					"errors.action.organismo.loadOrganismo",
					new String[] { servidor.getServidorId().toString() });
			throw new BusinessException(mensg);
		}

	}

	public String deleteParametroServidor() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		if (parametroServidorId == null) {
			addActionErrorSession(this
					.getText("plataforma.servidores.parametro.delete.error"));
		} else {
			parametroServidor = new ParametroServidorBean();
			parametroServidor.setParametroServidorId(new Integer(
					parametroServidorId));
			servidor = new ServidorBean();
			servidor.setServidorId(new Long(idServidor));
			servidor = servicioServidor.loadServidor(servidor);
			servicioServidor.updateServidor(servidor);
			servicioParametroServidor
					.deleteParametroServidor(parametroServidor);
			addActionMessageSession(this
					.getText("plataforma.servidores.parametro.delete.ok"));
		}
		return SUCCESS;
	}

	public String deletePlanificacionServidor() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		if (planificacionId == null) {
			addActionErrorSession(this
					.getText("plataforma.servidores.planificacion.delete.error"));
		} else {
			planificacionServidor = new PlanificacionBean();
			planificacionServidor.setPlanificacionId(new Integer(
					planificacionId));
			servicioPlanificacion.deletePlanificacion(planificacionServidor);
			addActionMessageSession(this
					.getText("plataforma.servidores.planificacion.delete.ok"));
		}
		return SUCCESS;
	}

	public String delete() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		if (idServidor == null) {
			addActionErrorSession(this
					.getText("plataforma.servidores.delete.error"));
		} else {
			servidor = new ServidorBean();
			servidor.setServidorId(new Long(idServidor));
			servicioServidor.deleteServidor(servidor);
			addActionMessageSession(this
					.getText("plataforma.servidores.delete.ok"));
		}
		return SUCCESS;

	}

	public String deleteSelected() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		if (checkDelList == null) {
			// throw new
			// BusinessException("No se ha seleccionado ningún servidor para eliminar");
			addActionErrorSession(this
					.getText("plataforma.servidores.deleteSelected.error"));
		} else {
			for (String idServidor : checkDelList) {
				servidor = new ServidorBean();
				servidor.setServidorId(new Long(idServidor));
				servicioServidor.deleteServidor(servidor);
			}
			addActionMessageSession(this
					.getText("plataforma.servidores.deleteSelected.ok"));
		}
		return SUCCESS;

	}

	private boolean validaParametro(ParametroServidorBean parametroServidor) {
		boolean sw = true;
		if (PlataformaMensajeriaUtil.isEmptyNumber(parametroServidor
				.getTipoParametroId())) {
			addActionErrorSession(this
					.getText("plataforma.servidores.parametro.add.parametroid.error"));
			sw = false;
		}
		if (PlataformaMensajeriaUtil.isEmpty(parametroServidor.getValor())) {
			addActionErrorSession(this
					.getText("plataforma.servidores.parametro.add.valor.error"));
			sw = false;
		}
		// IP TIPO 1
		// CAMBIO: 2012-08-29 a petici�n del MAP - No validar IP, porque pueden introducir DNS
//		if (parametroServidor.getTipoParametroId() == 1
//				&& !PlataformaMensajeriaUtil.validate(parametroServidor
//						.getValor())) {
//			addActionErrorSession(this
//					.getText("plataforma.servidores.parametro.ip.formato.error"));
//			sw = false;
//		}
		try {
			if (servicioParametroServidor
					.existeParametroServidor(parametroServidor)) {
				addActionErrorSession(this
						.getText("plataforma.servidores.parametro.add.existe"));
				sw = false;
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sw;
	}

	public String checkSMTPConnection() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		// Obtener los par�metros configurados para el servidor y realizar una
		// validaci�n para comprobar
		// que se han configurado todos
		String ip = null, user = null, password = null, secure = null, port = null, reqAuth= null;
		String msgError = this.getText("plataforma.servidores.checkparameters");
		List<ParametroServidorBean> listaParametrosServidor = servicioParametroServidor
				.getParametroServidorByServidorId(new Integer(idServidor)); 
		List<TipoParametroBean> listaTiposParametrosServidor = servicioTipoParametro
				.getTipoParametrosServidor();
		// Validar el n� de par�metros configurados en el servidor
		// y n� de tipos de par�metros
		int paramServ = listaParametrosServidor.size();
		int paramTipo = listaTiposParametrosServidor.size();
		HashMap<Integer, String> mapaParametrosServidor = new HashMap<Integer, String>();
		for (ParametroServidorBean parametroServidor : listaParametrosServidor) {
			mapaParametrosServidor.put(parametroServidor.getTipoParametroId(),
					parametroServidor.getValor());
		}
		boolean sw = true;
		for (TipoParametroBean tipoParametroBean : listaTiposParametrosServidor) {
			if (tipoParametroBean.getTipoParametroId()!=ParametroServidorBean.CONEXION_SEGURA&&
				tipoParametroBean.getTipoParametroId()!=ParametroServidorBean.REQ_AUTH&&
				tipoParametroBean.getTipoParametroId()!=ParametroServidorBean.USUARIO&&
				tipoParametroBean.getTipoParametroId()!=ParametroServidorBean.PASSWORD&&
					!mapaParametrosServidor.containsKey(tipoParametroBean
					.getTipoParametroId())) {
					sw = false;
					msgError += "<br/>- " + tipoParametroBean.getNombre();
			} else {
				if (ParametroServidorBean.IP == tipoParametroBean
						.getTipoParametroId()) {
					ip = mapaParametrosServidor.get(tipoParametroBean
							.getTipoParametroId());
				}
				if (ParametroServidorBean.USUARIO == tipoParametroBean
						.getTipoParametroId()) {
					user = mapaParametrosServidor.get(tipoParametroBean
							.getTipoParametroId());
				}
				if (ParametroServidorBean.PASSWORD == tipoParametroBean
						.getTipoParametroId()) {
					password = mapaParametrosServidor.get(tipoParametroBean
							.getTipoParametroId());
				}
				if (ParametroServidorBean.CONEXION_SEGURA == tipoParametroBean
						.getTipoParametroId()) {
					secure = mapaParametrosServidor.get(tipoParametroBean
							.getTipoParametroId());
				}
				if (ParametroServidorBean.PUERTO == tipoParametroBean
						.getTipoParametroId()) {
					port = mapaParametrosServidor.get(tipoParametroBean
							.getTipoParametroId());
				}
				if (ParametroServidorBean.REQ_AUTH == tipoParametroBean
						.getTipoParametroId()){
					reqAuth = mapaParametrosServidor.get(tipoParametroBean
							.getTipoParametroId());
				}
			}
		}
		if (sw) {
			// Validar conexi�n
			SMTPConnectionTest smtpTestConn = new SMTPConnectionTest();
			String validSMTPParams = smtpTestConn.checkConnectionClassic(
					ip, user, password, secure, port,reqAuth);
			// SMTPConnectionTest.checkConnection(ip, user, password, secure,
			// port);

			if (validSMTPParams == null) {
				addActionMessageSession(this
						.getText("plataforma.servidores.checkparameters.ok"));
			} else {
				addActionErrorSession(this
						.getText("plataforma.servidores.checkparameters.error")
						+ ":<br/><br/>" + validSMTPParams);
			}

		} else {
			addActionErrorSession(msgError);
		}

		return SUCCESS;
	}

	public String addParametroServidor() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		if (parametroServidor != null) {
			if (!validaParametro(parametroServidor)) {
				return ERROR;
			} else {
				servicioParametroServidor
						.newParametroServidor(parametroServidor);
				// addActionMessage(this.getText("plataforma.mensaje.paramtetroservidorok"));
				addActionMessageSession(this
						.getText("plataforma.servidores.parametro.add.ok"));
			}
		} else {
			addActionErrorSession(this
					.getText("plataforma.servidores.parametro.add.error"));
			return ERROR;
		}
		return SUCCESS;
	}

	public String addPlanificacion() throws BaseException {
		if(getRequest().getSession().getAttribute("infoUser")==null) return "noUser"; 
		if (planificacionServidor != null
				&& !PlataformaMensajeriaUtil
						.isEmptyNumber(planificacionServidor.getServidorId())) {
			if (planificacionValida(planificacionServidor)) {
				planificacionServidor.setActivo(new Integer(1));
				planificacionServidor.setTipoPlanificacionId(new Integer(1));
				
				int valido = servicioPlanificacion.validaPlanificacionServidor(planificacionId, planificacionServidor.getServidorId(),
						planificacionServidor.getLunes(),planificacionServidor.getMartes(),planificacionServidor.getMiercoles(),
						planificacionServidor.getJueves(),planificacionServidor.getViernes(),planificacionServidor.getSabado(),
						planificacionServidor.getDomingo(),planificacionServidor.getHoraHasta(),planificacionServidor.getHoraDesde());
				
				if(valido == 1){
					servicioPlanificacion.newPlanificacion(planificacionServidor);
					addActionMessageSession(this
							.getText("plataforma.servidores.planificacion.add.ok"));

				}else if (valido == 0)
				{
			    	   addActionErrorSession("No se ha a&ntilde;adido la planificaci&oacute;n. La planificaci&oacute;n introducida se solapa con otras planificaciones");
			    	   return ERROR;
				}
			} else {
				return ERROR;
			}
		} else {
			addActionErrorSession(this
					.getText("plataforma.servidores.planificacion.add.error"));
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * Verifica que se ha introducido por lo menos un día de la semana y las
	 * horas de inicio y fin
	 * 
	 * @param planificacionServidor
	 */
	private boolean planificacionValida(PlanificacionBean planificacionServidor) {
		boolean sw = true;
		if (planificacionServidor != null) {
			if (PlataformaMensajeriaUtil.isEmpty(planificacionServidor
					.getHoraDesde())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor
							.getHoraHasta())) {
				addFieldErrorSession(this
						.getText("plataforma.servidores.planificacion.horas.error"));
				sw = false;

			}
			if (!PlataformaMensajeriaUtil.isEmpty(planificacionServidor
					.getHoraDesde())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor
							.getHoraHasta())) {
				addFieldErrorSession(this
						.getText("plataforma.servidores.planificacion.horaHasta.error"));
				sw = false;
			}
			if (PlataformaMensajeriaUtil.isEmpty(planificacionServidor
					.getHoraDesde())
					&& !PlataformaMensajeriaUtil.isEmpty(planificacionServidor
							.getHoraHasta())) {
				addFieldErrorSession(this
						.getText("plataforma.servidores.planificacion.horaDesde.error"));
				sw = false;
			}
			if (sw) {
				if (!validoFormatoHora(planificacionServidor.getHoraDesde())) {
					addFieldErrorSession(this
							.getText("plataforma.servidores.planificacion.horaDesde.formato.error"));
					sw = false;
				}
				if (!validoFormatoHora(planificacionServidor.getHoraHasta())) {
					addFieldErrorSession(this
							.getText("plataforma.servidores.planificacion.horaHasta.formato.error"));
					sw = false;
				}
				if (sw) {
					if (!validoHoras(planificacionServidor.getHoraDesde(),
							planificacionServidor.getHoraHasta())) {
						sw = false;
					}
				}
			}
			if (PlataformaMensajeriaUtil.isEmpty(planificacionServidor
					.getLunes())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor
							.getMartes())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor
							.getMiercoles())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor
							.getJueves())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor
							.getViernes())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor
							.getSabado())
					&& PlataformaMensajeriaUtil.isEmpty(planificacionServidor
							.getDomingo())) {
				addFieldErrorSession(this
						.getText("plataforma.servidores.planificacion.dias.error"));
				sw = false;
			}

		}
		return sw;
	}

	private boolean validoHoras(String horaDesde, String horaHasta) {
		boolean sw = true;
		String[] horaDesdeArray = horaDesde.split(":");
		String[] horaHastaArray = horaHasta.split(":");
		int hDesde = new Integer(horaDesdeArray[0]);
		int mDesde = new Integer(horaDesdeArray[1]);
		int hHasta = new Integer(horaHastaArray[0]);
		int mHasta = new Integer(horaHastaArray[1]);
		if (hDesde > hHasta) {
			addFieldErrorSession(this
					.getText("plataforma.servidores.planificacion.horaDesde.menor.error"));
			sw = false;
		} else if (hDesde == hHasta && mDesde > mHasta) {
			addFieldErrorSession(this
					.getText("plataforma.servidores.planificacion.horaDesde.menor.error"));
			sw = false;
		} else if (hDesde == hHasta && mDesde == mHasta) {
			addFieldErrorSession(this
					.getText("plataforma.servidores.planificacion.horas.iguales.error"));
			sw = false;
		}
		return sw;
	}

	private boolean validoFormatoHora(String hora) {
		boolean sw = true;
		if (!PlataformaMensajeriaUtil.isEmpty(hora)) {
			if (!PlataformaMensajeriaUtil.validaFormatoHora(hora)) {
				sw = false;
			}
		}
		return sw;
	}
	ArrayList<TipoParametroBean> tiposParametros = new ArrayList<TipoParametroBean>();
	@Override
	public void prepare() throws Exception {
		// contextUsuarios = getComboValues();
		if (idServidor != null) {
			tiposParametros = (ArrayList<TipoParametroBean>) servicioTipoParametro
					.getTipoParametrosServidor();
			comboTipoParametros = getComboValues();
			listaParametrosServidor = getParametrosServidor();
			listaPlanificacionesServidor = getLoadPlanificacionesServidor();
		}
	}

	public ArrayList<TipoParametroBean> getTiposParametros() {
		return tiposParametros;
	}

	public void setTiposParametros(ArrayList<TipoParametroBean> tiposParametros) {
		this.tiposParametros = tiposParametros;
	}

	private List<PlanificacionBean> getLoadPlanificacionesServidor() {
		List<PlanificacionBean> lista = null;
		if (idServidor != null && idServidor.length() > 0) {
			try {
				lista = servicioPlanificacion
						.getPlanificacionesByServidorId(new Integer(idServidor));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (servidor != null && servidor.getServidorId() != null) {
			try {
				lista = servicioPlanificacion
						.getPlanificacionesByServidorId(new Integer(idServidor));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lista;
	}

	private List<ParametroServidorBean> getParametrosServidor() {
		List<ParametroServidorBean> lista = null;
		if (idServidor != null && idServidor.length() > 0) {
			try {
				lista = servicioParametroServidor
						.getParametroServidorByServidorId(new Integer(
								idServidor));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (servidor != null && servidor.getServidorId() != null) {
			try {
				lista = servicioParametroServidor
						.getParametroServidorByServidorId(servidor
								.getServidorId().intValue());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lista;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<KeyValueObject> getComboValues() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();

		KeyValueObject option = null;
		// TODO: DEVOLVER SOLO LOS PARAMETROS ACTIVOS!!!!
		ArrayList<TipoParametroBean> keys = null;
		try {
			keys = (ArrayList<TipoParametroBean>) servicioTipoParametro
					.getTipoParametrosServidor();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (keys != null && keys.size() > 0)
			for (TipoParametroBean key : keys) {

				option = new KeyValueObject();
				option.setCodigo(key.getTipoParametroId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		return result;
	}

	public List<KeyValueObject> getComboTipoParametros() {
		return comboTipoParametros;
	}

	public void setComboTipoParametros(List<KeyValueObject> comboTipoParametros) {
		this.comboTipoParametros = comboTipoParametros;
	}

	public List<ServidorBean> getListaServidores() {
		return listaServidores;
	}

	public void setListaServidores(List<ServidorBean> listaServidores) {
		this.listaServidores = listaServidores;
	}

	public ServicioServidor getServicioServidor() {
		return servicioServidor;
	}

	public void setServicioServidor(ServicioServidor servicioServidor) {
		this.servicioServidor = servicioServidor;
	}

	public ServicioTipoParametro getServicioTipoParametro() {
		return servicioTipoParametro;
	}

	public void setServicioTipoParametro(
			ServicioTipoParametro servicioTipoParametro) {
		this.servicioTipoParametro = servicioTipoParametro;
	}

	public ServicioParametroServidor getServicioParametroServidor() {
		return servicioParametroServidor;
	}

	public void setServicioParametroServidor(
			ServicioParametroServidor servicioParametroServidor) {
		this.servicioParametroServidor = servicioParametroServidor;
	}

	public String getTipoParametroId() {
		return tipoParametroId;
	}

	public void setTipoParametroId(String tipoParametroId) {
		this.tipoParametroId = tipoParametroId;
	}

	public ServidorBean getServidor() {
		return servidor;
	}

	public void setServidor(ServidorBean servidor) {
		this.servidor = servidor;
	}

	public String getIdServidor() {
		return idServidor;
	}

	public void setIdServidor(String idServidor) {
		this.idServidor = idServidor;
	}

	public String getResultCount() {
		return resultCount;
	}

	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}

	public String[] getCheckDelList() {
		return checkDelList;
	}

	public void setCheckDelList(String[] checkDelList) {
		this.checkDelList = checkDelList;
	}

	public ParametroServidorBean getParametroServidor() {
		return parametroServidor;
	}

	public void setParametroServidor(ParametroServidorBean parametroServidor) {
		this.parametroServidor = parametroServidor;
	}

	public List<ParametroServidorBean> getListaParametrosServidor() {
		return listaParametrosServidor;
	}

	public void setListaParametrosServidor(
			List<ParametroServidorBean> listaParametrosServidor) {
		this.listaParametrosServidor = listaParametrosServidor;
	}

	public void setParametroServidorId(String parametroServidorId) {
		this.parametroServidorId = parametroServidorId;
	}

	public PlanificacionBean getPlanificacionServidor() {
		return planificacionServidor;
	}

	public void setPlanificacionServidor(PlanificacionBean planificacionServidor) {
		this.planificacionServidor = planificacionServidor;
	}

	public String getParametroServidorId() {
		if (parametroServidor != null
				&& parametroServidor.getParametroServidorId() != null) {
			return parametroServidor.getParametroServidorId().toString();
		} else {
			return parametroServidorId;
		}

	}

	public ServicioPlanificacion getServicioPlanificacion() {
		return servicioPlanificacion;
	}

	public void setServicioPlanificacion(
			ServicioPlanificacion servicioPlanificacion) {
		this.servicioPlanificacion = servicioPlanificacion;
	}

	public List<PlanificacionBean> getListaPlanificacionesServidor() {
		return listaPlanificacionesServidor;
	}

	public void setListaPlanificacionesServidor(
			List<PlanificacionBean> listaPlanificacionesServidor) {
		this.listaPlanificacionesServidor = listaPlanificacionesServidor;
	}

	public boolean isEmpty(String value) {
		if (value == null || (value != null && value.equals(""))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Método que resuelve el lugar donde tiene que volver
	 */
	public String getVolver() {
		String volver = "buscarServidores.action";
		if (!PlataformaMensajeriaUtil.isEmpty(from)
				&& !PlataformaMensajeriaUtil.isEmpty(idFrom)) {
			volver = from + "?" + var + "=" + idFrom;
		}
		return volver;
	}

}
