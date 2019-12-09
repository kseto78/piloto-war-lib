package es.mpr.plataformamensajeria.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.locale.LocaleBeanUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.security.perm.model.UserVO;

import es.minhap.misim.bus.model.Peticion;
import es.minhap.misim.bus.query.PeticionQuery;
import es.minhap.plataformamensajeria.iop.misim.manager.PeticionManager;
import es.minhap.plataformamensajeria.iop.util.Utils;
import es.mpr.plataformamensajeria.beans.PeticionBean;
import es.mpr.plataformamensajeria.beans.UsuarioAplicacionBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuario;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuarioAplicacion;
import es.mpr.template.web.action.admin.UsuariosForm;

/**
 * The Class PlataformaMensajeriaUtil.
 */
public class PlataformaMensajeriaUtil {

	protected static final String PLATAFORMAMENSA = "PlataformaMensajeriaUtil - getUsuarioLogueado Authority():";

	protected static final String R_CONST_REF = "\\";

	protected static final String DD20_4D250_5DOT = "([01]?\\\\d\\\\d?|2[0-4]\\\\d|25[0-5])\\\\.";

	protected static final String DOT = ".";

	protected static final String CONTENT_DISPOSI = "Content-Disposition";

	protected static final String ATTACHMENT_FILE = "attachment; filename=\\";

	protected static final String PLATAFORMAMENSA0 = "PlataformaMensajeriaUtil - getUsuarioLogueado ";

	/**  logger. */
	private static Logger logger = Logger.getLogger(PlataformaMensajeriaUtil.class);
	
	/** Constante ROL_USUARIO_PLATAFORMA. */
	public static final String ROL_USUARIO_PLATAFORMA = "ROL_USUARIO_PLATAFORMA";
	
	/** Constante ID_ROL_USUARIO_PLATAFORMA. */
	public static final String ID_ROL_USUARIO_PLATAFORMA = "ID_ROL_USUARIO_PLATAFORMA";
	
	/** Constante MAP_PERMISOS_APLICACIONES. */
	public static final String MAP_PERMISOS_APLICACIONES = "MAP_PERMISOS_APLICACIONES";
	
	/** Constante ROL_ADMINISTRADOR. */
	public static final String ROL_ADMINISTRADOR="ROL_ADMINISTRADOR";
	
	/* INIT srealarq  nº478	09-07-2019*/
	/** Constante ROL_CAID. */
	public static final String ROL_CAID="ROL_CAID";
	/* END srealarq	09-07-2019*/
	
	/** Constante ROL_PROPIETARIO. */
	public static final String ROL_PROPIETARIO="ROL_PROPIETARIO";
	
	/** Constante ID_USUARIO_LOGUEADO. */
	public static final String ID_USUARIO_LOGUEADO = "ID_USUARIO_LOGUEADO";
	
	/** Constante USERNAME. */
	public static final String USERNAME = "USERNAME";
	
	/** Constante ROLES. */
	public static final HashMap<String,Integer> ROLES = new HashMap<>();
    
    /** Constante IPADDRESS_PATTERN. */
    private static final String IPADDRESS_PATTERN = 
		"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		DD20_4D250_5DOT +
		DD20_4D250_5DOT +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	/**  pattern. */
	private static Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
	
	/**  matcher. */
	private static Matcher matcher;

	 
	static {
		ROLES.put(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR,1 );
		ROLES.put(PlataformaMensajeriaUtil.ROL_PROPIETARIO,2);
		ROLES.put(PlataformaMensajeriaUtil.ROL_CAID,3);
 		
 	}
	

	/**
	 * Obtiene la información del usuario Logueado en la aplicación.
	 *
	 * @param user the user
	 * @return usuario logueado
	 */
	
	
	public static UsuariosForm getUsuarioLogueado(Object user){
		
		MapUser detallesUsuario = (MapUser)	user;
		
		UsuariosForm formUsuario = new UsuariosForm();
		if (null != detallesUsuario){
			UserVO tmpUserVO = (UserVO) detallesUsuario.getUserInfo();
			Collection<GrantedAuthority> roles=detallesUsuario.getAuthorities();
		     for (GrantedAuthority g : roles) {
		    	 logger.info(PLATAFORMAMENSA +g.getAuthority());
			}
			try {
				LocaleBeanUtils.copyProperties(formUsuario,tmpUserVO);
			} catch (IllegalAccessException | InvocationTargetException e) {
				logger.info(PLATAFORMAMENSA0 + e);
			} 
		}
		return formUsuario;
	}
	
	
	/**
	 * Obtener usuario logueado.
	 *
	 * @return usuario logueado
	 */
	
	public static UsuariosForm getUsuarioLogueado(){
		MapUser detallesUsuario = (MapUser)	SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		UsuariosForm formUsuario = new UsuariosForm();
		 Collection<GrantedAuthority> roles=detallesUsuario.getAuthorities();
	     for (GrantedAuthority g : roles) {
	    	 logger.info(PLATAFORMAMENSA +g.getAuthority());
		}
		UserVO tmpUserVO = (UserVO) detallesUsuario.getUserInfo();
		try {
			LocaleBeanUtils.copyProperties(formUsuario,tmpUserVO);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.info(PLATAFORMAMENSA0 +e);
		} 
		return formUsuario;
	}
	
	/**
	 * Comprueba si una cadena está vacía o es nula.
	 *
	 * @param value the value
	 * @return true, si es empty
	 */
	
	public static boolean isEmpty(String value){
		return value==null||(value!=null&&"".equals(value));
	}
	
	/**
	 * Comprueba si un numero es igual a 0 o es nulo.
	 *
	 * @param value the value
	 * @return true, si es empty number
	 */
	
	public static boolean isEmptyNumber(Integer value){
		return value==null||(value!=null&&(value.intValue()==0||value.intValue()==-1));
	}
		
	/**
	 * Comprueba empty number long.
	 *
	 * @param value the value
	 * @return true, si es empty number long
	 */
	
	public static boolean isEmptyNumberLong(Long value){
		return value==null||(value!=null&&(value.intValue()==0||value.intValue()==-1));
	}
	
	/**
	 * Valida el formato de las horas introducidas para las planificaciones a través de una expresión regular.
	 *
	 * @param hora the hora
	 * @return true, if successful
	 */
	
	public static boolean validaFormatoHora(String hora){
		ValidaFormatoHora formato24Horas=new ValidaFormatoHora();
		return formato24Horas.validate(hora);
	}
	
	/**
	 * Devuelve el rol del usuario (login) que se pasa como parámetro [java.lang.String]
	 *
	 * @param userName the user name
	 * @param servicioUsuario the servicio usuario
	 * @return Rol Id
	 */
	
	public static Integer getRolUsuarioByUsername(String userName, ServicioUsuario servicioUsuario){
		Integer rolId = servicioUsuario.getRolIdByUsername(userName);
		if (null == rolId){
			rolId = -1;
		}
		return rolId;
		
	}
	
	/**
	 * Devuelve un HashMap con los permisos asociados a cada aplicación asignada al usuario.
	 *
	 * @param userName the user name
	 * @param servicioUsuarioAplicacion the servicio usuario aplicacion
	 * @return map permisos aplicaciones
	 */
	
	public static HashMap<Integer, Integer> getMapPermisosAplicaciones(String userName, ServicioUsuarioAplicacion servicioUsuarioAplicacion) {
		HashMap<Integer,Integer> mapPermisosAplicaciones = new HashMap<>();
		List<UsuarioAplicacionBean> lista = servicioUsuarioAplicacion.getListPermisosAplicacionesUsuario(userName);
		for (UsuarioAplicacionBean uab : lista) {
			Integer aplicacion = 0;
			Integer modo = 0;
			if (null != uab.getAplicacionId()){
				aplicacion = uab.getAplicacionId().intValue();
			}
			if (null != uab.getModo()){
				modo = uab.getModo();
			}
			mapPermisosAplicaciones.put(aplicacion, modo);
		}		
		return mapPermisosAplicaciones;
	}
	
	
	/**
	 * Extrae de la session el rolId (Integer) del usuario logueado.
	 *
	 * @param request the request
	 * @return rol id from session
	 */

	public static Integer getRolIdFromSession(HttpServletRequest request){
		return (Integer)request.getSession().getAttribute(ID_ROL_USUARIO_PLATAFORMA);
	}
	
	/**
	 * Extrae de la session el rol (String) del usuario logueado.
	 *
	 * @param request the request
	 * @return rol from session
	 */
	
	public static String getRolFromSession(HttpServletRequest request){
		return (String)request.getSession().getAttribute(ROL_USUARIO_PLATAFORMA);
	}
	
	/**
	 * Extrae de la session el identificador del usuario logueado.
	 *
	 * @param request the request
	 * @return id usuario from session
	 */
	
	public static Integer getIdUsuarioFromSession(HttpServletRequest request){
		return (Integer)request.getSession().getAttribute(ID_USUARIO_LOGUEADO);

	}	
	
	
	
	/**
	 * Change session rol.
	 *
	 * @param rolId the rol id
	 * @param httpSession the http session
	 */

	public static void changeSessionRol(Integer rolId, HttpSession httpSession) {
    	if(rolId!=null&&rolId == 1){
    		httpSession.setAttribute(PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA, PlataformaMensajeriaUtil.ROL_ADMINISTRADOR);
    	}else if(rolId!=null&&rolId == 2){
    		httpSession.setAttribute(PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA, PlataformaMensajeriaUtil.ROL_PROPIETARIO);
    	}else if(rolId!=null&&rolId == 3){
    		httpSession.setAttribute(PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA, PlataformaMensajeriaUtil.ROL_CAID);
    	}
		
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		// This method has to be empty.
		
	}

	/**
	 * M�todo para identificar si la petici�n es para exportar una lista o para mostrar simplemente un listado. 
	 *
	 * @param request the request
	 * @return true, si es export
	 */
	///MIGRADO
	public static boolean isExport(HttpServletRequest request) {
		boolean sw =false;
		@SuppressWarnings("unchecked")
		Set<String> listaParametros = request.getParameterMap().keySet();
		Iterator<String> paramIterator = listaParametros.iterator();
		while(paramIterator.hasNext()){
			String param = paramIterator.next();
			if(param!=null&&param.indexOf("d-")!=-1&&param.indexOf("-e")!=-1){
				sw=true;
			}
			
		}
		return sw;
	}
	
    /**
     * Comprueba mobile number.
     *
     * @param nmaxenvios the nmaxenvios
     * @return true, si es mobile number
     */
	////MIGRADO
	public static boolean isMobileNumber(String nmaxenvios) {
		if (nmaxenvios == null || nmaxenvios.isEmpty() || nmaxenvios.length() != 9){
            return false;
		}else{
	        for (int i = 0; i < nmaxenvios.length() - 1; i++) {
	            if (!Character.isDigit(nmaxenvios.charAt(i))){
	            	return false;
	            }
	        }
		}
        return true;
	}
    
    /**
     * Comprueba number.
     *
     * @param nmaxenvios the nmaxenvios
     * @return true, si es number
     */
	////MIGRADO
	public static boolean isNumber(Integer nmaxenvios) {
		if (nmaxenvios == null || nmaxenvios.toString().isEmpty()) {
			return false;
		}
        for (int i = 0; i < nmaxenvios.toString().length(); i++) {
            if (!Character.isDigit(nmaxenvios.toString().charAt(i))) {
				return false;
			}
        }
        return true;
	}
	
	/**
	 * Obligatorio pasar un a�o de inicio. Formato YYYY (2009)
	 *
	 * @param startYear the start year
	 * @param endYear the end year
	 * @return years
	 */
	////MIGRADO
	public static ArrayList<String> getYears(String startYear,String endYear){
		ArrayList<String> listadoAnyos = new ArrayList<>();
		try{
			int start = Integer.parseInt(startYear);
			int lastYear = 0;
			if(endYear!=null&&!endYear.isEmpty()){
				lastYear = Integer.parseInt(endYear);
			}else{
				lastYear = Calendar.getInstance().get(Calendar.YEAR);
			}
			if(lastYear<start){
				return listadoAnyos;
			}else{
				for(int i=start;i<=lastYear;i++){
					listadoAnyos.add(String.valueOf(i));
				}
			}
		}catch(NumberFormatException exc){
		}
		return listadoAnyos;
	}
	
	/**
	 * Comprueba si un email tiene formato correcto.
	 *
	 * @param email the email
	 * @return true, if successful
	 */
	
	////MIGRADO
	public static boolean validateEmail(String email){
		String emailPattern = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		pattern = Pattern.compile(emailPattern);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
	

	/**
	 * Load xml peticion.
	 *
	 * @param idPeticion the id peticion
	 * @param peticionManager the peticion manager
	 * @return the peticion bean
	 * @throws BusinessException the business exception
	 */
	public static PeticionBean loadXmlPeticion(String idPeticion, PeticionManager peticionManager) throws BusinessException {
		PeticionBean peticionBean = new PeticionBean();
	
		PeticionQuery query = new PeticionQuery();
		query.setIdPeticion(Long.parseLong(idPeticion));
		
		try {
			Peticion peticion = peticionManager.getPeticionByQuery(query);
			String xmlPeticion = peticion.getMensajePeticion();
			peticionBean.setMensajePeticion(xmlPeticion);
			peticionBean.setIdPeticion(Long.parseLong(idPeticion));
			
		} catch (Exception e) {
			logger.error("PlataformaMensajeriaUtil - loadXmlPeticion:" + e);
			throw new BusinessException(e);
		}
		return peticionBean;
	}
	
	
	/**
	 * Load xml respuesta.
	 *
	 * @param idPeticion the id peticion
	 * @param peticionManager the peticion manager
	 * @return the peticion bean
	 * @throws BusinessException the business exception
	 */
	public static PeticionBean loadXmlRespuesta(String idPeticion, PeticionManager peticionManager) throws BusinessException {
		PeticionBean peticionBean = new PeticionBean();
	
		PeticionQuery query = new PeticionQuery();
		query.setIdPeticion(Long.parseLong(idPeticion));
		
		try {
			Peticion peticion = peticionManager.getPeticionByQuery(query);
			String xmlRespuesta = peticion.getMensajeRespuesta();
			peticionBean.setMensajeRespuesta(xmlRespuesta);
			peticionBean.setIdPeticion(Long.parseLong(idPeticion));
			
		} catch (Exception e) {
			logger.error("PlataformaMensajeriaUtil - loadXmlRespuesta:" + e);
			throw new BusinessException(e);
		}
		return peticionBean;
	}
	
	/**
	 * Descarga fichero xml.
	 *
	 * @param response the response
	 * @param nombreFichero the nombre fichero
	 * @param contenidoFichero the contenido fichero
	 * @param tipoFichero the tipo fichero
	 */
	public static void descargaFicheroXml(HttpServletResponse response, String nombreFichero, 
			String contenidoFichero, String tipoFichero) {

		logger.info("[PlataformaMensajeriaUtil] - descargaFicheroXml - inicio");
		
		String contenidoFicheroUTF8 = Utils.convertToUTF8(contenidoFichero);
		
		String contentDisposition = ATTACHMENT_FILE + nombreFichero + DOT + tipoFichero + R_CONST_REF;

		response.setHeader(CONTENT_DISPOSI, contentDisposition);

		response.setContentType("text/xml");

			byte bFichero[] = contenidoFicheroUTF8.getBytes();
            ServletOutputStream sOutStream;
			try {
				sOutStream = response.getOutputStream();
				sOutStream.write(bFichero);
				response.flushBuffer();
				
			} catch (IOException e) {
				logger.error("[PlataformaMensajeriaUtil] - descargaFicheroXml - ERROR: Se ha producido al descargar el fichero");
				logger.error("[PlataformaMensajeriaUtil] - descargaFicheroXml - ERROR: " + e, e);
			}
	        
			logger.info("[PlataformaMensajeriaUtil] - descargaFicheroXml - fin");
	}
	
	/**
	 * Descarga fichero pk pass.
	 *
	 * @param response the response
	 * @param nombreFichero the nombre fichero
	 * @param contenidoFichero the contenido fichero
	 * @param tipoFichero the tipo fichero
	 */
	public static void descargaFicheroPkPass(HttpServletResponse response, String nombreFichero, 
			String contenidoFichero, String tipoFichero) {

		logger.info("[PlataformaMensajeriaUtil] - descargaFicheroPkpass - inicio");
		String old = contenidoFichero.substring(contenidoFichero.lastIndexOf('/') + 1);
		String f = contenidoFichero;
		f = f.replace(old, nombreFichero) + DOT + tipoFichero;
		Path pathFile = Paths.get(contenidoFichero);
		Path destinationPath = Paths.get(f);
					
            ServletOutputStream sOutStream;
			try {
				Files.move(pathFile, destinationPath, StandardCopyOption.REPLACE_EXISTING);
				byte bFichero[] = Files.readAllBytes(destinationPath);
				
				String contentDisposition = ATTACHMENT_FILE + nombreFichero + DOT + tipoFichero + R_CONST_REF;

				response.setHeader(CONTENT_DISPOSI, contentDisposition);

				response.setContentType("application/vnd-com.apple.pkpass");
				
				sOutStream = response.getOutputStream();
				sOutStream.write(bFichero);
				response.flushBuffer();
				sOutStream.close();
				
				Files.delete(destinationPath);
			} catch (IOException e) {
				logger.error("[PlataformaMensajeriaUtil] - descargaFicheroPkpass - ERROR: Se ha producido al descargar el fichero");
				logger.error("[PlataformaMensajeriaUtil] - descargaFicheroPkpass - ERROR: " + e, e);
			}
	        
			logger.info("[PlataformaMensajeriaUtil] - descargaFicheroPkpass - fin");
	}
	
}


///MIGRADO
class ValidaFormatoHora{
	 
	  private Pattern pattern;
	  private Matcher matcher;

	  private static final String TIME24HOURS_PATTERN = 
               "([01]?[0-9]|2[0-4]):[0-5][0-9]";

	  public ValidaFormatoHora(){
		  pattern = Pattern.compile(TIME24HOURS_PATTERN);
	  }

	  /**
	   * Validate time in 24 hours format with regular expression
	   * @param time time address for validation
	   * @return true valid time fromat, false invalid time format
	   */
	  ////MIGRADO
	  public boolean validate(final String time){

		  matcher = pattern.matcher(time);
		  return matcher.matches();

	  }
	  
	  
	  
}