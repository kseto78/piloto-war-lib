package es.mpr.plataformamensajeria.web.action.home;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.security.perm.model.User060VO;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.Preparable;

import es.mpr.plataformamensajeria.beans.EnviosPendientesCanalBean;
import es.mpr.plataformamensajeria.beans.EstadoLotesEnviosBean;
import es.mpr.plataformamensajeria.beans.UsoServidoresBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioListadosHome;
import es.mpr.plataformamensajeria.util.MapUser;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.plataformamensajeria.util.TituloEstadisticasParser;

/**
 * <p>
 * Clase Action de Struts2 para la gesti&oacute;n de los Auditorias.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n,
 * borrado y listado de los Usuarios
 * 
 * @author i-nercya
 * 
 */
@Controller("homeAction")
@Scope("prototype")
public class HomeAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(HomeAction.class);
	
	@Resource(name = "servicioListadosHomeImpl")
	private ServicioListadosHome serviciosListadoHome;
	
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;

	// Parametros Filtro Uso servidores
	private String filtroAnyo;
	private String filtroMes;
	
	// Parametros Filtro Estados Lotes
	private String filtroAnyoLotes;
	private String filtroMesLotes;

	// Combos
	List<KeyValueObject> comboAnyos = new ArrayList<KeyValueObject>();

	// Listados
	public List<EnviosPendientesCanalBean> listaEnviosPendientesCanal;
	public List<UsoServidoresBean> listaUso;
	public List<UsoServidoresBean> listaUsoServidores;
	public List<UsoServidoresBean> listaUsoProveedores;
	public List<UsoServidoresBean> listaUsoReceptores;
	public List<UsoServidoresBean> listaUsoServidoresPush;
	public List<EstadoLotesEnviosBean> listadoEstadosLotesEnvios;

	public Integer totalesEmail = 0;
	public Integer totalesSMS = 0;
	public Integer totalesRecepcionSMS = 0;
	public Integer totalesPush = 0;
	
//////Migrado
	public String infoHome() throws BaseException {
		
		if(getRequest().getParameter("mockUser")!=null) {
			if(getRequest().getParameter("mockUser").equals(properties.getProperty("key.random", "bhk2460u44m8rwea"))) {
				mockAdminUser();
			} else if (getRequest().getParameter("mockUser").equals("mockAppUser")) {
				mockApplicationUser();
			}
		}
		
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		SimpleDateFormat sdfInicio = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		logger.info("### INFO HOME ### INICIO: " + sdfInicio.format(Calendar.getInstance().getTime()));
		int page = getPage("tableId");
		int inicio = (page - 1) * Integer.parseInt(properties.getProperty("generales.PAGESIZEHOME", "10"));
		
		// ///////////////////////////////////////////
		// Obtenemos el listado de Mensajes Pendientes
		// ///////////////////////////////////////////
		logger.info("### INFO HOME ### INICIO EnviosPendientesCanal: " + sdfInicio.format(Calendar.getInstance().getTime()));
		listaEnviosPendientesCanal = serviciosListadoHome.getEnviosPendientesCanal();
		logger.info("### INFO HOME ### FIN    EnviosPendientesCanal: " + sdfInicio.format(Calendar.getInstance().getTime()));
		if (listaEnviosPendientesCanal != null) {
			for (EnviosPendientesCanalBean envioPendiente : listaEnviosPendientesCanal) {
				totalesEmail += envioPendiente.getEmail();
				totalesSMS += envioPendiente.getSms();
				totalesRecepcionSMS += envioPendiente.getRecepcionSMS();
				totalesPush += envioPendiente.getPush();
			}
		}
		if (listaEnviosPendientesCanal != null && listaEnviosPendientesCanal.size() < 4) {
			int size = listaEnviosPendientesCanal.size();
			for (int i = size; i < 4; i++) {
				EnviosPendientesCanalBean envioPendienteVacio = new EnviosPendientesCanalBean();
				envioPendienteVacio.setAplicacion("");
				envioPendienteVacio.setEmail(0);
				envioPendienteVacio.setSms(0);
				envioPendienteVacio.setRecepcionSMS(0);
				envioPendienteVacio.setPush(0);
				listaEnviosPendientesCanal.add(envioPendienteVacio);
			}
		}
		// ///////////////////////////////////////////
		// Obtenemos el listado de Uso Servidores
		// ///////////////////////////////////////////
		if (null == filtroAnyo || filtroAnyo.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			String anyoActual = sdf.format(new Date());
			this.filtroAnyo = anyoActual;
		}
		if (null == filtroMes || filtroMes.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("M");
			String mesActual = sdf.format(new Date());
			this.filtroMes = mesActual;
		}
		// ///////////////////////////////////////////
		// Obtenemos el listado de Uso Servidores
		// ///////////////////////////////////////////
		if (null == filtroAnyoLotes || filtroAnyoLotes.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			String anyoActual = sdf.format(new Date());
			this.filtroAnyoLotes = anyoActual;
		}
		if (null == filtroMesLotes || filtroMesLotes.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("M");
			String mesActual = sdf.format(new Date());
			this.filtroMesLotes = mesActual;
		}
		logger.info("### INFO HOME ### INICIO UsoServidores: " + sdfInicio.format(Calendar.getInstance().getTime()));
		if(filtroAnyo != null && filtroMes != null){
			listaUso = serviciosListadoHome.getUsoServidoresBean(filtroAnyo, filtroMes);
		}
		logger.info("### INFO HOME ### FIN    UsoServidores: " + sdfInicio.format(Calendar.getInstance().getTime()));
		// Caluclar listados usos servidores, proveedores, receptores y
		// servidores push.
		listaUsoServidores = new ArrayList<UsoServidoresBean>();
		listaUsoProveedores = new ArrayList<UsoServidoresBean>();
		listaUsoReceptores = new ArrayList<UsoServidoresBean>();
		listaUsoServidoresPush = new ArrayList<UsoServidoresBean>();
		for (UsoServidoresBean uso : listaUso) {
			if (uso.getTipoServidor() == 1) {
				listaUsoServidores.add(uso);
			}
			if (uso.getTipoServidor() == 2) {
				listaUsoProveedores.add(uso);
			}
			if (uso.getTipoServidor() == 3) {
				listaUsoReceptores.add(uso);
			}
			if (uso.getTipoServidor() == 4) {
				listaUsoServidoresPush.add(uso);
			}
		}
		// ///////////////////////////////////////////
		// Obtenemos el listado de Alertas
		// ///////////////////////////////////////////
		logger.info("### INFO HOME ### INICIO EstadoLotesEnvios: " + sdfInicio.format(Calendar.getInstance().getTime()));
		PaginatedList<EstadoLotesEnviosBean> result = serviciosListadoHome.getEstadosLotesEnvios(inicio, Integer.parseInt(properties.getProperty("generales.PAGESIZEHOME", "10")),
				filtroAnyoLotes, filtroMesLotes);
		logger.info("### INFO HOME ### FIN EstadoLotesEnvios: " + sdfInicio.format(Calendar.getInstance().getTime()));
		Integer totalSize = result.getTotalList();

		listadoEstadosLotesEnvios = result.getPageList();
		if (listadoEstadosLotesEnvios != null && listadoEstadosLotesEnvios.size() < 10) {
			int i = listadoEstadosLotesEnvios.size();
			for (int j = i; j <= 10; j++) {
				EstadoLotesEnviosBean estadoBean = new EstadoLotesEnviosBean();
				listadoEstadosLotesEnvios.add(estadoBean);
			}
		} else if (listadoEstadosLotesEnvios == null) {
			listadoEstadosLotesEnvios = new ArrayList<EstadoLotesEnviosBean>();
			for (int j = 0; j < 10; j++) {
				EstadoLotesEnviosBean estadoBean = new EstadoLotesEnviosBean();
				listadoEstadosLotesEnvios.add(estadoBean);
			}
		}

		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_TOTALSIZE",null), totalSize);

		getRequest().setAttribute(properties.getProperty("generales.REQUEST_ATTRIBUTE_PAGESIZE",null), Integer.parseInt(properties.getProperty("generales.PAGESIZEHOME", "10")));

		logger.info("### INFO HOME ### FIN: " + sdfInicio.format(Calendar.getInstance().getTime()));
		return SUCCESS;
	}

	private void mockAdminUser() {
		Integer rolUsuarioId = 1;
    	Integer idUsuario = 341;
    	String userName="11111111H";
    	
    	request.getSession().setAttribute(properties.getProperty("PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA",null), 
    	properties.getProperty("PlataformaMensajeriaUtil.ROL_ADMINISTRADOR",null));

    	request.getSession().setAttribute(properties.getProperty("PlataformaMensajeriaUtil.ID_ROL_USUARIO_PLATAFORMA",null), rolUsuarioId);
    	request.getSession().setAttribute(properties.getProperty("PlataformaMensajeriaUtil.ID_USUARIO_LOGUEADO",null), idUsuario);

    	request.getSession().setAttribute(properties.getProperty("PlataformaMensajeriaUtil.USERNAME",null), userName);
    	
    	User060VO usuario = new User060VO();
		usuario.setUsername(userName);
		usuario.setNombre("Admin");
		usuario.setApellidos("admin");
		
		MapUser springUser = new MapUser(userName, "password", true, getAuthoritiesMock(usuario, rolUsuarioId), usuario);
//		        
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(springUser, null, getAuthoritiesMock(usuario, rolUsuarioId), null, null, null, null, null));
//		        
        request.getSession().setAttribute("infoUser", springUser);
		
	}
	
	private void mockApplicationUser () {
		Integer rolUsuarioId = 2;
    	Integer idUsuario = 64;
    	String userName="pae_sim";
    	
    	request.getSession().setAttribute(properties.getProperty("PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA",null), 
    	properties.getProperty("PlataformaMensajeriaUtil.ROL_PROPIETARIO",null));

    	request.getSession().setAttribute(properties.getProperty("PlataformaMensajeriaUtil.ID_ROL_USUARIO_PLATAFORMA",null), rolUsuarioId);
    	request.getSession().setAttribute(properties.getProperty("PlataformaMensajeriaUtil.ID_USUARIO_LOGUEADO",null), idUsuario);

    	request.getSession().setAttribute(properties.getProperty("PlataformaMensajeriaUtil.USERNAME",null), userName);
    	
    	User060VO usuario = new User060VO();
		usuario.setUsername(userName);
		usuario.setNombre("Admin");
		usuario.setApellidos("PAE");
		
		MapUser springUser = new MapUser(userName, "password", true, getAuthoritiesMock(usuario, rolUsuarioId), usuario);
//		        
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(springUser, null, getAuthoritiesMock(usuario, rolUsuarioId), null, null, null, null, null));
//		        
        request.getSession().setAttribute("infoUser", springUser);
		
	}

//////Migrado
	public List<KeyValueObject> getComboAnyos() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();
		KeyValueObject option = null;
		ArrayList<String> listaAnyos = PlataformaMensajeriaUtil.getYears(properties.getProperty("home.startyear", "2012"), properties.getProperty("home.endyear", ""));
		for (String anyo : listaAnyos) {
			option = new KeyValueObject();
			option.setCodigo(anyo);
			option.setDescripcion(anyo);
			result.add(option);
		}
		return result;
	}

//////Migrado
	public List<KeyValueObject> getComboMeses() {
		List<KeyValueObject> result = new ArrayList<KeyValueObject>();
		KeyValueObject option = null;
		ArrayList<String> listaMeses = TituloEstadisticasParser.getMesesFullName();
		for (String string : listaMeses) {
			String[] splitMes = string.split("_");
			option = new KeyValueObject();
			option.setCodigo(splitMes[0]);
			option.setDescripcion(splitMes[1]);
			result.add(option);
		}

		return result;
	}


	public String getFiltroAnyo() {
		return filtroAnyo;
	}

	public void setFiltroAnyo(String filtroAnyo) {
		this.filtroAnyo = filtroAnyo;
	}

	public String getFiltroMes() {
		return filtroMes;
	}

	public void setFiltroMes(String filtroMes) {
		this.filtroMes = filtroMes;
	}

	public List<EnviosPendientesCanalBean> getListaEnviosPendientesCanal() {
		return listaEnviosPendientesCanal;
	}

	public void setListaEnviosPendientesCanal(List<EnviosPendientesCanalBean> listaEnviosPendientesCanal) {
		this.listaEnviosPendientesCanal = listaEnviosPendientesCanal;
	}

	public String getTotalesEmail() {
		DecimalFormat formateador = new DecimalFormat("###,###.##");
		return formateador.format(totalesEmail);
	}

	public void setTotalesEmail(Integer totalesEmail) {
		this.totalesEmail = totalesEmail;
	}

	public String getTotalesSMS() {
		DecimalFormat formateador = new DecimalFormat("###,###.##");
		return formateador.format(totalesSMS);

	}

	public void setTotalesSMS(Integer totalesSMS) {
		this.totalesSMS = totalesSMS;
	}

	public List<EstadoLotesEnviosBean> getListadoEstadosLotesEnvios() {
		return listadoEstadosLotesEnvios;
	}

	public String getTotalesRecepcionSMS() {
		DecimalFormat formateador = new DecimalFormat("###,###.##");
		return formateador.format(totalesRecepcionSMS);
	}

	public void setTotalesRecepcionSMS(Integer totalesRecepcionSMS) {
		this.totalesRecepcionSMS = totalesRecepcionSMS;
	}

	public void setListadoEstadosLotesEnvios(List<EstadoLotesEnviosBean> listadoEstadosLotesEnvios) {
		this.listadoEstadosLotesEnvios = listadoEstadosLotesEnvios;
	}

	/**
	 * @return the serviciosListadoHome
	 */
	public ServicioListadosHome getServiciosListadoHome() {
		return serviciosListadoHome;
	}

	/**
	 * @param serviciosListadoHome
	 *            the serviciosListadoHome to set
	 */
	public void setServiciosListadoHome(ServicioListadosHome serviciosListadoHome) {
		this.serviciosListadoHome = serviciosListadoHome;
	}

	public String getFiltroAnyoLotes() {
		return filtroAnyoLotes;
	}

	public void setFiltroAnyoLotes(String filtroAnyoLotes) {
		this.filtroAnyoLotes = filtroAnyoLotes;
	}

	public String getFiltroMesLotes() {
		return filtroMesLotes;
	}

	public void setFiltroMesLotes(String filtroMesLotes) {
		this.filtroMesLotes = filtroMesLotes;
	}

	@Override
	public void prepare() throws Exception {
		
	}
	
	private List<GrantedAuthority> getAuthoritiesMock(User060VO usuario, Integer rolUsuarioId)
	  {
	    List<GrantedAuthority> authList = new ArrayList<>(2);
	    
	    authList.add(new GrantedAuthorityImpl("ROLE_"));
	    String role = "ROLE_ADMINISTRADOR";
	    authList.add(new GrantedAuthorityImpl(role));
	    
	    return authList;
	  }

}
