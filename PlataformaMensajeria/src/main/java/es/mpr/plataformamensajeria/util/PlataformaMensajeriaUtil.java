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

public class PlataformaMensajeriaUtil {

	private static Logger logger = Logger.getLogger(PlataformaMensajeriaUtil.class);
	
	public static final String ROL_USUARIO_PLATAFORMA = "ROL_USUARIO_PLATAFORMA";
	public static final String ID_ROL_USUARIO_PLATAFORMA = "ID_ROL_USUARIO_PLATAFORMA";
	public static final String MAP_PERMISOS_APLICACIONES = "MAP_PERMISOS_APLICACIONES";
	public static final String ROL_ADMINISTRADOR="ROL_ADMINISTRADOR";
	public static final String ROL_PROPIETARIO="ROL_PROPIETARIO";
	public static final String ID_USUARIO_LOGUEADO = "ID_USUARIO_LOGUEADO";
	public static final String USERNAME = "USERNAME";
	public static final HashMap<String,Integer> ROLES = new HashMap<String,Integer>();
    private static final String IPADDRESS_PATTERN = 
		"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
		"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	private static Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
	private static Matcher matcher;

	 
	static
 	{
		ROLES.put(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR,1 );
		ROLES.put(PlataformaMensajeriaUtil.ROL_PROPIETARIO,2);
 		
 	}
	

	/**
	 * Obtiene la información del usuario Logueado en la aplicación
	 * @return
	 */
	
	////MIGRADO
	public static UsuariosForm getUsuarioLogueado(Object user){
		
		MapUser detallesUsuario = (MapUser)	user;
		
		UsuariosForm formUsuario = new UsuariosForm();
		if (null != detallesUsuario){
			UserVO tmpUserVO = (UserVO) detallesUsuario.getUserInfo();
			Collection<GrantedAuthority> roles=detallesUsuario.getAuthorities();
		     for (GrantedAuthority g : roles) {
		    	 logger.info("PlataformaMensajeriaUtil - getUsuarioLogueado Authority():" +g.getAuthority());
			}
			try {
				LocaleBeanUtils.copyProperties(formUsuario,tmpUserVO);
			} catch (IllegalAccessException | InvocationTargetException e) {
				logger.info("PlataformaMensajeriaUtil - getUsuarioLogueado " + e);
			} 
		}
		return formUsuario;
	}
	
	
	///MIGRADO
	public static UsuariosForm getUsuarioLogueado(){
		MapUser detallesUsuario = (MapUser)	SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		UsuariosForm formUsuario = new UsuariosForm();
		 Collection<GrantedAuthority> roles=detallesUsuario.getAuthorities();
	     for (GrantedAuthority g : roles) {
	    	 logger.info("PlataformaMensajeriaUtil - getUsuarioLogueado Authority():" +g.getAuthority());
		}
		UserVO tmpUserVO = (UserVO) detallesUsuario.getUserInfo();
		try {
			LocaleBeanUtils.copyProperties(formUsuario,tmpUserVO);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.info("PlataformaMensajeriaUtil - getUsuarioLogueado " +e);
		} 
		return formUsuario;
	}
	
	/**
	 * Comprueba si una cadena está vacía o es nula
	 * @param value
	 * @return
	 */
	////MIGRADO
	public static boolean isEmpty(String value){
		if(value==null||(value!=null&&value.equals(""))){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * Comprueba si un numero es igual a 0 o es nulo
	 * @param value
	 * @return
	 */
	///MIGRADO
	public static boolean isEmptyNumber(Integer value){
		if(value==null||(value!=null&&(value.intValue()==0||value.intValue()==-1))){
			return true;
		}else{
			return false;
		}
	}
		
	///MIGRADO
	public static boolean isEmptyNumberLong(Long value){
		if(value==null||(value!=null&&(value.intValue()==0||value.intValue()==-1))){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Valida el formato de las horas introducidas para las planificaciones a través de una expresión regular.
	 * @param hora
	 * @return
	 */
	////MIGRADO
	public static boolean validaFormatoHora(String hora){
		ValidaFormatoHora formato24Horas=new ValidaFormatoHora();
		return formato24Horas.validate(hora);
	}
	/**
	 * Devuelve el rol del usuario (login) que se pasa como parámetro [java.lang.String]
	 * @param userName
	 * @param servicioUsuario 
	 * @return Rol Id
	 */
	////MIGRADO
	public static Integer getRolUsuarioByUsername(String userName, ServicioUsuario servicioUsuario){
		Integer rolId = servicioUsuario.getRolIdByUsername(userName);
		if (null == rolId){
			rolId = -1;
		}
		return rolId;
		
	}
	/**
	 * Devuelve un HashMap con los permisos asociados a cada aplicación asignada al usuario
	 * @param username
	 * @return
	 */
	/////MIGRADO
	public static HashMap<Integer, Integer> getMapPermisosAplicaciones(String userName, ServicioUsuarioAplicacion servicioUsuarioAplicacion) {
		HashMap<Integer,Integer> mapPermisosAplicaciones = new HashMap<Integer,Integer>();
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
	 * Extrae de la session el rolId (Integer) del usuario logueado
	 * @param request
	 * @return
	 */
	////MIGRADO
	public static Integer getRolIdFromSession(HttpServletRequest request){
		Integer sessionRolIdUsuario = (Integer)request.getSession().getAttribute(ID_ROL_USUARIO_PLATAFORMA);
		return sessionRolIdUsuario;
	}
	
	/**
	 * Extrae de la session el rol (String) del usuario logueado
	 * @param request
	 * @return
	 */
	////MIGRADO
	public static String getRolFromSession(HttpServletRequest request){
		String sessionRolUsuario = (String)request.getSession().getAttribute(ROL_USUARIO_PLATAFORMA);
		return sessionRolUsuario;
	}
	/**
	 * Extrae de la session el identificador del usuario logueado
	 * @param request
	 * @return
	 */
	////MIGRADO
	public static Integer getIdUsuarioFromSession(HttpServletRequest request){
		Integer sessionIdUsuario = (Integer)request.getSession().getAttribute(ID_USUARIO_LOGUEADO);
		return sessionIdUsuario;

	}	
	
	
	
	////MIGRADO
	public static void changeSessionRol(Integer rolId, HttpSession httpSession) {
    	if(rolId!=null&&rolId == 1){
    		httpSession.setAttribute(PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA, PlataformaMensajeriaUtil.ROL_ADMINISTRADOR);
    	}else if(rolId!=null&&rolId == 2){
    		httpSession.setAttribute(PlataformaMensajeriaUtil.ROL_USUARIO_PLATAFORMA, PlataformaMensajeriaUtil.ROL_PROPIETARIO);
    	}
		
	}
	public static void main(String[] args){
		
	}

	/**
	 * M�todo para identificar si la petici�n es para exportar una lista o para mostrar simplemente un listado. 
	 * @param request
	 * @return
	 */
	///MIGRADO
	public static boolean isExport(HttpServletRequest request) {
		boolean sw =false;
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
     * 
     * @param nmaxenvios
     * @return
     */
	////MIGRADO
	public static boolean isMobileNumber(String nmaxenvios) {
		if (nmaxenvios == null || nmaxenvios.toString().length() == 0 || !(nmaxenvios.toString().length()==9)){
            return false;
		}else{
	        for (int i = 0; i < nmaxenvios.toString().length() - 1; i++) {
	            if (!Character.isDigit(nmaxenvios.toString().charAt(i))){
	            	return false;
	            }
	        }
		}
        return true;
	}
    
    /**
     * 
     * @param nmaxenvios
     * @return
     */
	////MIGRADO
	public static boolean isNumber(Integer nmaxenvios) {
		if (nmaxenvios == null || nmaxenvios.toString().length() == 0)
            return false;
        for (int i = 0; i < nmaxenvios.toString().length(); i++) {
            if (!Character.isDigit(nmaxenvios.toString().charAt(i)))
                return false;
        }
        return true;
	}
	
	/**
	 * Obligatorio pasar un a�o de inicio. Formato YYYY (2009)
	 * @param startYear
	 * @return
	 */
	////MIGRADO
	public static ArrayList<String> getYears(String startYear,String endYear){
		ArrayList<String> listadoAnyos = new ArrayList<String>();
		try{
			int start = Integer.valueOf(startYear);
			int lastYear = 0;
			if(endYear!=null&&!endYear.isEmpty()){
				lastYear = Integer.valueOf(endYear);
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
			exc.printStackTrace();
		}
		return listadoAnyos;
	}
	/**
	 * Comprueba si un email tiene formato correcto
	 * @param email
	 * @return
	 */
	
	////MIGRADO
	public static boolean validateEmail(String email){
		String EMAIL_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
	

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
	
	public static void descargaFicheroXml(HttpServletResponse response, String nombreFichero, 
			String contenidoFichero, String tipoFichero) {

		logger.info("[PlataformaMensajeriaUtil] - descargaFicheroXml - inicio");
		
		String contenidoFicheroUTF8 = Utils.convertToUTF8(contenidoFichero);
		
		String contentDisposition = "attachment; filename=\"" + nombreFichero + "." + tipoFichero + "\"";

		response.setHeader("Content-Disposition", contentDisposition);

		response.setContentType("text/xml");

			byte bFichero[] = contenidoFicheroUTF8.getBytes();
            ServletOutputStream sOutStream;
			try {
				sOutStream = response.getOutputStream();
				sOutStream.write(bFichero);
				response.flushBuffer();
				
			} catch (IOException e) {
				logger.error("[PlataformaMensajeriaUtil] - descargaFicheroXml - ERROR: Se ha producido al descargar el fichero");
				logger.error("[PlataformaMensajeriaUtil] - descargaFicheroXml - ERROR: " + e.toString(), e);
			}
	        
			logger.info("[PlataformaMensajeriaUtil] - descargaFicheroXml - fin");
	}
	
	public static void descargaFicheroPkPass(HttpServletResponse response, String nombreFichero, 
			String contenidoFichero, String tipoFichero) {

		logger.info("[PlataformaMensajeriaUtil] - descargaFicheroPkpass - inicio");
		//Path pathFile = Paths.get(contenidoFichero);
		String old = contenidoFichero.substring(contenidoFichero.lastIndexOf('/') + 1);
		String f = contenidoFichero;
		f = f.replace(old, nombreFichero) + "." + tipoFichero;
		Path pathFile = Paths.get(contenidoFichero);
		Path destinationPath = Paths.get(f);
					
            ServletOutputStream sOutStream;
			try {
				Files.move(pathFile, destinationPath, StandardCopyOption.REPLACE_EXISTING);
				byte bFichero[] = Files.readAllBytes(destinationPath);
				
				String contentDisposition = "attachment; filename=\"" + nombreFichero + "." + tipoFichero + "\"";

				response.setHeader("Content-Disposition", contentDisposition);

				response.setContentType("application/vnd-com.apple.pkpass");
				
				sOutStream = response.getOutputStream();
				sOutStream.write(bFichero);
				response.flushBuffer();
				sOutStream.close();
				
				Files.delete(destinationPath);
			} catch (IOException e) {
				logger.error("[PlataformaMensajeriaUtil] - descargaFicheroPkpass - ERROR: Se ha producido al descargar el fichero");
				logger.error("[PlataformaMensajeriaUtil] - descargaFicheroPkpass - ERROR: " + e.toString(), e);
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