package es.mpr.plataformamensajeria.web.action.home;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

	protected static final String INFOUSER = "infoUser";

	protected static final String PLATAFORMAMENSA = "PlataformaMensajeriaUtil.ID_ROL_USUARIO_PLATAFORMA";

	protected static final String DOT = "###,###.##";

	protected static final String PASS = "password";

	protected static final String PLATAFORMAMENSA0 = "PlataformaMensajeriaUtil.ID_USUARIO_LOGUEADO";

	protected static final String MOCKUSER = "mockUser";

	protected static final String PLATAFORMAMENSA1 = "PlataformaMensajeriaUtil.USERNAME";

	protected static final String PLATAFORMAMENSA2 = "PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA";

	protected static final String PRUEBAS = "pruebas";

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  logger. */
	private static Logger logger = Logger.getLogger(HomeAction.class);
	
	/**  servicios listado home. */
	@Resource(name = "servicioListadosHomeImpl")
	private ServicioListadosHome serviciosListadoHome;
	
	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;

	/**  filtro anyo. */
	// Parametros Filtro Uso servidores
	private String filtroAnyo;
	
	/**  filtro mes. */
	private String filtroMes;
	
	/**  filtro anyo lotes. */
	// Parametros Filtro Estados Lotes
	private String filtroAnyoLotes;
	
	/**  filtro mes lotes. */
	private String filtroMesLotes;

	/**  combo anyos. */
	// Combos
	List<KeyValueObject> comboAnyos = new ArrayList<>();

	/**  lista envios pendientes canal. */
	// Listados
	public List<EnviosPendientesCanalBean> listaEnviosPendientesCanal;
	
	/**  lista uso. */
	public List<UsoServidoresBean> listaUso;
	
	/**  lista uso servidores. */
	public List<UsoServidoresBean> listaUsoServidores;
	
	/**  lista uso proveedores. */
	public List<UsoServidoresBean> listaUsoProveedores;
	
	/**  lista uso receptores. */
	public List<UsoServidoresBean> listaUsoReceptores;
	
	/**  lista uso servidores push. */
	public List<UsoServidoresBean> listaUsoServidoresPush;
	
	/**  listado estados lotes envios. */
	public List<EstadoLotesEnviosBean> listadoEstadosLotesEnvios;

	/**  totales email. */
	public Integer totalesEmail = 0;
	
	/**  totales SMS. */
	public Integer totalesSMS = 0;
	
	/**  totales recepcion SMS. */
	public Integer totalesRecepcionSMS = 0;
	
	/**  totales push. */
	public Integer totalesPush = 0;
	
/**
 * Info home.
 *
 * @return the string
 * @throws BaseException the base exception
 */
//////Migrado
	public String infoHome() throws BaseException {
		
		if(getRequest().getParameter(MOCKUSER)!=null) {
			if(getRequest().getParameter(MOCKUSER).equals(properties.getProperty("key.random", "bhk2460u44m8rwea"))) {
				mockAdminUser();
			} else if ("mockAppUser".equals(getRequest().getParameter(MOCKUSER))) {
				mockApplicationUser();
			}
		}
		
		//Se comenta hasta reestructurar la Home
		if (getRequest().getSession().getAttribute(INFOUSER) == null) {
			return "noUser";
		}
		SimpleDateFormat sdfInicio = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		logger.info("### INFO HOME ### INICIO: " + sdfInicio.format(Calendar.getInstance().getTime()));
//		
//		// ///////////////////////////////////////////
//		// Obtenemos el listado de Mensajes Pendientes
//		// ///////////////////////////////////////////
//		// ///////////////////////////////////////////
//		// Obtenemos el listado de Uso Servidores
//		// ///////////////////////////////////////////
//		// ///////////////////////////////////////////
//		// Obtenemos el listado de Uso Servidores
//		// ///////////////////////////////////////////
//		// Caluclar listados usos servidores, proveedores, receptores y
//		// servidores push.
//		// ///////////////////////////////////////////
//		// Obtenemos el listado de Alertas
//		// ///////////////////////////////////////////
//		PaginatedList<EstadoLotesEnviosBean> result = serviciosListadoHome.getEstadosLotesEnvios(inicio, Integer.parseInt(properties.getProperty("generales.PAGESIZEHOME", "10")),
//



		logger.info("### INFO HOME ### FIN: " + sdfInicio.format(Calendar.getInstance().getTime()));
		return SUCCESS;
	}

	/**
	 * Mock admin user.
	 */
	private void mockAdminUser() {
		Integer rolUsuarioId = 1;
    	Integer idUsuario = 341;
    	String userName="11111111H";
    	
    	request.getSession().setAttribute(properties.getProperty(PLATAFORMAMENSA2,null), 
    	properties.getProperty("PlataformaMensajeriaUtil.ROL_ADMINISTRADOR",null));

    	request.getSession().setAttribute(properties.getProperty(PLATAFORMAMENSA,null), rolUsuarioId);
    	request.getSession().setAttribute(properties.getProperty(PLATAFORMAMENSA0,null), idUsuario);

    	request.getSession().setAttribute(properties.getProperty(PLATAFORMAMENSA1,null), userName);
    	
    	User060VO usuario = new User060VO();
		usuario.setUsername(userName);
		usuario.setNombre("Juan");
		usuario.setApellidos("Ejemplo");
		
		MapUser springUser = new MapUser(userName, PASS, true, getAuthoritiesMock(usuario, rolUsuarioId), usuario);
//		        
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(springUser, null, getAuthoritiesMock(usuario, rolUsuarioId), null, null, null, null, null));
//		        
        request.getSession().setAttribute(INFOUSER, springUser);
		
	}
	
	/**
	 * Mock application user.
	 */
	private void mockApplicationUser () {
		Integer rolUsuarioId = 2;
    	Integer idUsuario = 321;
    	String userName=PRUEBAS;
    	
    	request.getSession().setAttribute(properties.getProperty(PLATAFORMAMENSA2,null), 
    	properties.getProperty("PlataformaMensajeriaUtil.ROL_PROPIETARIO",null));

    	request.getSession().setAttribute(properties.getProperty(PLATAFORMAMENSA,null), rolUsuarioId);
    	request.getSession().setAttribute(properties.getProperty(PLATAFORMAMENSA0,null), idUsuario);

    	request.getSession().setAttribute(properties.getProperty(PLATAFORMAMENSA1,null), userName);
    	
    	User060VO usuario = new User060VO();
		usuario.setUsername(userName);
		usuario.setNombre(PRUEBAS);
		usuario.setApellidos("PRUEBAS");
		
		MapUser springUser = new MapUser(userName, PASS, true, getAuthoritiesMock(usuario, rolUsuarioId), usuario);
//		        
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(springUser, null, getAuthoritiesMock(usuario, rolUsuarioId), null, null, null, null, null));
//		        
        request.getSession().setAttribute(INFOUSER, springUser);
        
          
        List<String> arrayOrganismos = new ArrayList<>();
    	arrayOrganismos.add("L01471868");   			    			
		request.getSession().setAttribute(properties.getProperty("PlataformaMensajeriaUtil.ARRAY_ORGANISMOS",null), arrayOrganismos);
		
		request.getSession().setAttribute(properties.getProperty("PlataformaMensajeriaUtil.ES_AEAT",null), "OK");
		
	}

/**
 * Obtener combo anyos.
 *
 * @return combo anyos
 */
//////Migrado
	public List<KeyValueObject> getComboAnyos() {
		List<KeyValueObject> result = new ArrayList<>();
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

/**
 * Obtener combo meses.
 *
 * @return combo meses
 */
//////Migrado
	public List<KeyValueObject> getComboMeses() {
		List<KeyValueObject> result = new ArrayList<>();
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


	/**
	 * Obtener filtro anyo.
	 *
	 * @return filtro anyo
	 */
	public String getFiltroAnyo() {
		return filtroAnyo;
	}

	/**
	 * Modificar filtro anyo.
	 *
	 * @param filtroAnyo new filtro anyo
	 */
	public void setFiltroAnyo(String filtroAnyo) {
		this.filtroAnyo = filtroAnyo;
	}

	/**
	 * Obtener filtro mes.
	 *
	 * @return filtro mes
	 */
	public String getFiltroMes() {
		return filtroMes;
	}

	/**
	 * Modificar filtro mes.
	 *
	 * @param filtroMes new filtro mes
	 */
	public void setFiltroMes(String filtroMes) {
		this.filtroMes = filtroMes;
	}

	/**
	 * Obtener lista envios pendientes canal.
	 *
	 * @return lista envios pendientes canal
	 */
	public List<EnviosPendientesCanalBean> getListaEnviosPendientesCanal() {
		return listaEnviosPendientesCanal;
	}

	/**
	 * Modificar lista envios pendientes canal.
	 *
	 * @param listaEnviosPendientesCanal new lista envios pendientes canal
	 */
	public void setListaEnviosPendientesCanal(List<EnviosPendientesCanalBean> listaEnviosPendientesCanal) {
		this.listaEnviosPendientesCanal = listaEnviosPendientesCanal;
	}

	/**
	 * Obtener totales email.
	 *
	 * @return totales email
	 */
	public String getTotalesEmail() {
		DecimalFormat formateador = new DecimalFormat(DOT);
		return formateador.format(totalesEmail);
	}

	/**
	 * Modificar totales email.
	 *
	 * @param totalesEmail new totales email
	 */
	public void setTotalesEmail(Integer totalesEmail) {
		this.totalesEmail = totalesEmail;
	}

	/**
	 * Obtener totales SMS.
	 *
	 * @return totales SMS
	 */
	public String getTotalesSMS() {
		DecimalFormat formateador = new DecimalFormat(DOT);
		return formateador.format(totalesSMS);

	}

	/**
	 * Modificar totales SMS.
	 *
	 * @param totalesSMS new totales SMS
	 */
	public void setTotalesSMS(Integer totalesSMS) {
		this.totalesSMS = totalesSMS;
	}

	/**
	 * Obtener listado estados lotes envios.
	 *
	 * @return listado estados lotes envios
	 */
	public List<EstadoLotesEnviosBean> getListadoEstadosLotesEnvios() {
		return listadoEstadosLotesEnvios;
	}

	/**
	 * Obtener totales recepcion SMS.
	 *
	 * @return totales recepcion SMS
	 */
	public String getTotalesRecepcionSMS() {
		DecimalFormat formateador = new DecimalFormat(DOT);
		return formateador.format(totalesRecepcionSMS);
	}

	/**
	 * Modificar totales recepcion SMS.
	 *
	 * @param totalesRecepcionSMS new totales recepcion SMS
	 */
	public void setTotalesRecepcionSMS(Integer totalesRecepcionSMS) {
		this.totalesRecepcionSMS = totalesRecepcionSMS;
	}

	/**
	 * Modificar listado estados lotes envios.
	 *
	 * @param listadoEstadosLotesEnvios new listado estados lotes envios
	 */
	public void setListadoEstadosLotesEnvios(List<EstadoLotesEnviosBean> listadoEstadosLotesEnvios) {
		this.listadoEstadosLotesEnvios = listadoEstadosLotesEnvios;
	}

	/**
	 * Obtener servicios listado home.
	 *
	 * @return the serviciosListadoHome
	 */
	public ServicioListadosHome getServiciosListadoHome() {
		return serviciosListadoHome;
	}

	/**
	 * Modificar servicios listado home.
	 *
	 * @param serviciosListadoHome            the serviciosListadoHome to set
	 */
	public void setServiciosListadoHome(ServicioListadosHome serviciosListadoHome) {
		this.serviciosListadoHome = serviciosListadoHome;
	}

	/**
	 * Obtener filtro anyo lotes.
	 *
	 * @return filtro anyo lotes
	 */
	public String getFiltroAnyoLotes() {
		return filtroAnyoLotes;
	}

	/**
	 * Modificar filtro anyo lotes.
	 *
	 * @param filtroAnyoLotes new filtro anyo lotes
	 */
	public void setFiltroAnyoLotes(String filtroAnyoLotes) {
		this.filtroAnyoLotes = filtroAnyoLotes;
	}

	/**
	 * Obtener filtro mes lotes.
	 *
	 * @return filtro mes lotes
	 */
	public String getFiltroMesLotes() {
		return filtroMesLotes;
	}

	/**
	 * Modificar filtro mes lotes.
	 *
	 * @param filtroMesLotes new filtro mes lotes
	 */
	public void setFiltroMesLotes(String filtroMesLotes) {
		this.filtroMesLotes = filtroMesLotes;
	}

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.impl.PlataformaPaginationAction#prepare()
	 */
	@Override
	public void prepare() throws Exception {
		// This method has to be empty.
		
	}
	
	/**
	 * Obtener authorities mock.
	 *
	 * @param usuario the usuario
	 * @param rolUsuarioId the rol usuario id
	 * @return authorities mock
	 */
	private List<GrantedAuthority> getAuthoritiesMock(User060VO usuario, Integer rolUsuarioId) {
	    List<GrantedAuthority> authList = new ArrayList<>(2);
	    
	    authList.add(new GrantedAuthorityImpl("ROLE_"));
	    String role = "ROLE_ADMINISTRADOR";
	    authList.add(new GrantedAuthorityImpl(role));
	    
	    return authList;
	  }

}
