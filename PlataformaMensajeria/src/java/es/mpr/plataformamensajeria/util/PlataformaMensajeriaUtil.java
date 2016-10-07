package es.mpr.plataformamensajeria.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.locale.LocaleBeanUtils;
import org.springframework.security.context.SecurityContextHolder;

import com.map.j2ee.security.auth.MapUser;
import com.map.j2ee.security.perm.model.UserVO;

import es.mpr.plataformamensajeria.beans.AdjuntoEmailBean;
import es.mpr.template.web.action.admin.UsuariosForm;

public class PlataformaMensajeriaUtil {

	private static final String SELECT_ROL_BY_USERNAME="Select usr.rolId from TBL_USUARIOS usr where usr.login=?";
	private static final String SELECT_ID_USUARIO_BY_USERNAME="Select usr.usuarioId from TBL_USUARIOS usr where usr.login=?";
	private static final String SELECT_PERMISOS_APLICACIONES_BY_USERNAME="SELECT usrapp.modo, usrapp.aplicacionid from VIEW_USUARIOS_APLICACIONES usrapp where usrapp.usuarioid=(select usuarioid usr from tbl_usuarios usr where usr.login=?)"; 
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
    private static final String MOBILE_NUMBER_PATTERN="^\\d$";

	private static Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);;
	private static Matcher matcher;

	 
	static
 	{
		ROLES.put(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR,1 );
		ROLES.put(PlataformaMensajeriaUtil.ROL_PROPIETARIO,2);
 		
 	}
	private static String getStringFromInputStream(InputStream is) {
		 
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
 
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
 
		return sb.toString();
 
	}
	/**
	 * Obtiene la información del usuario Logueado en la aplicación
	 * @return
	 */
	public static UsuariosForm getUsuarioLogueado(Object user){
		
		MapUser detallesUsuario = (MapUser)	user;
		
		UsuariosForm formUsuario = new UsuariosForm();
		
		UserVO tmpUserVO = (UserVO) detallesUsuario.getUserInfo();
		try {
			LocaleBeanUtils.copyProperties(formUsuario,tmpUserVO);
		} catch (IllegalAccessException e) {
		  e.printStackTrace();
		} catch (InvocationTargetException e) {
		  e.printStackTrace();
		}
		return formUsuario;
	}
	public static UsuariosForm getUsuarioLogueado(){
		MapUser detallesUsuario = (MapUser)	SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		UsuariosForm formUsuario = new UsuariosForm();
		/*GrantedAuthority[] roles=detallesUsuario.getAuthorities();
		for (int i = 0; i < roles.length; i++) {
			GrantedAuthority grantedAuthority = roles[i];
		    System.out.print(grantedAuthority.getAuthority());
		}*/
		UserVO tmpUserVO = (UserVO) detallesUsuario.getUserInfo();
		try {
			LocaleBeanUtils.copyProperties(formUsuario,tmpUserVO);
		} catch (IllegalAccessException e) {
		  e.printStackTrace();
		} catch (InvocationTargetException e) {
		  e.printStackTrace();
		}
		return formUsuario;
	}
	/**
	 * Comprueba si una cadena está vacía o es nula
	 * @param value
	 * @return
	 */
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
	public static boolean isEmptyNumber(Integer value){
		if(value==null||(value!=null&&(value.intValue()==0||value.intValue()==-1))){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * Estable una conexión JDBC con la base de datos y devuelve el objeto
	 * La configuración para establecer la conexión con la BBDD se encuentra en es.mpr.plataformamensajeria.util.conf.properties (Por defecto se utiliza la de desarrollo)
	 * 
	 * @return
	 */
	public static Connection getConexion(){
		PlataformaMensajeriaProperties props = new PlataformaMensajeriaProperties();
		Connection con = null;
		try {
			Class.forName(props.getProperty("db.driver", "oracle.jdbc.driver.OracleDriver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url=props.getProperty("db.url", "jdbc:oracle:thin:@//mapdes03b.inter060.es:1521/mptapd2.inter060.es");
		String userDB = props.getProperty("db.user", "USR_MSGPLT");
		String passDB = props.getProperty("db.pass", "5ETMNLPQ02JD");
		try {
			con =  DriverManager.getConnection(url, userDB, passDB);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	/**
	 * Valida el formato de las horas introducidas para las planificaciones a través de una expresión regular.
	 * @param hora
	 * @return
	 */
	public static boolean validaFormatoHora(String hora){
		ValidaFormatoHora formato24Horas=new ValidaFormatoHora();
		return formato24Horas.validate(hora);
	}
	/**
	 * Devuelve el rol del usuario (login) que se pasa como parámetro [java.lang.String]
	 * @param userName
	 * @return Rol Id
	 */
	public static Integer getRolUsuarioByUsername(String userName){
		Integer rolId = new Integer(-1);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = getConexion();
			pstmt = con.prepareStatement(SELECT_ROL_BY_USERNAME);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();
			if(rs!=null&&rs.next()){
				rolId = rs.getInt(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeSQLStatements(con, pstmt, rs,null);		
		}
		return rolId;
		
	}
	/**
	 * Devuelve un HashMap con los permisos asociados a cada aplicación asignada al usuario
	 * @param username
	 * @return
	 */
	public static HashMap<Integer, Integer> getMapPermisosAplicaciones(String userName) {
		HashMap<Integer,Integer> mapPermisosAplicaciones = new HashMap<Integer,Integer>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con= getConexion();
			pstmt = con.prepareStatement(SELECT_PERMISOS_APLICACIONES_BY_USERNAME);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				Integer modo = rs.getInt(1);
				Integer aplicacion = rs.getInt(2);
				mapPermisosAplicaciones.put(aplicacion, modo);
			}
		}catch(SQLException exc){
			exc.printStackTrace();
		}finally{
			closeSQLStatements(con, pstmt, rs,null);
		}
		
		
		
		return mapPermisosAplicaciones;
	}
	/**
	 * Devuelve el Identificador del usuario (BBDD Plataforma Mensajeria)
	 * @param userName
	 * @return
	 */
	public static Integer getIdByUsername(String userName) {
		Integer idUsuario = new Integer(-1);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = getConexion();
			pstmt = con.prepareStatement(SELECT_ID_USUARIO_BY_USERNAME);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();
			if(rs!=null&&rs.next()){
				idUsuario = rs.getInt(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeSQLStatements(con, pstmt, rs,null);		
		}
		return idUsuario;
	}	
	/**
	 * Extrae de la session el rolId (Integer) del usuario logueado
	 * @param request
	 * @return
	 */
	public static Integer getRolIdFromSession(HttpServletRequest request){
		Integer sessionRolIdUsuario = (Integer)request.getSession().getAttribute(ID_ROL_USUARIO_PLATAFORMA);
		return sessionRolIdUsuario;
	}
	
	/**
	 * Extrae de la session el rol (String) del usuario logueado
	 * @param request
	 * @return
	 */
	public static String getRolFromSession(HttpServletRequest request){
		String sessionRolUsuario = (String)request.getSession().getAttribute(ROL_USUARIO_PLATAFORMA);
		return sessionRolUsuario;
	}
	/**
	 * Extrae de la session el identificador del usuario logueado
	 * @param request
	 * @return
	 */
	public static Integer getIdUsuarioFromSession(HttpServletRequest request){
		Integer sessionIdUsuario = (Integer)request.getSession().getAttribute(ID_USUARIO_LOGUEADO);
		return sessionIdUsuario;

	}	
	public static void closeSQLStatements(Connection con,PreparedStatement pstmt, ResultSet rs, CallableStatement call){
			try {
				if(rs!=null){
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(pstmt!=null){
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(call!=null){
					call.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}						
			try {
				if(con!=null){
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
			
		
	}
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
	 * 
	 * @param adjunto
	 * @return
	 */
	public static String existeFichero(AdjuntoEmailBean adjunto) {
		String rutaCompleta = creaRutaAdjunto(adjunto,true);
		File fichero = new File(rutaCompleta);
		if(fichero.exists()){
			return fichero.getPath();
		}
		return null;
	}
	/**
	 * 
	 * @param adjunto
	 * @return
	 */
	public static String creaRutaAdjunto(AdjuntoEmailBean adjunto,boolean conNombre) {
		PlataformaMensajeriaProperties props = new PlataformaMensajeriaProperties();
		String rutaAdjunto="";
		String rutaInicial = props.getProperty("mails.adjuntos.dir", "");
		
		rutaAdjunto=rutaInicial+adjunto.getEmailId()+File.separator+adjunto.getAdjuntoId()+File.separator+((conNombre)?adjunto.getNombre():"");
		return rutaAdjunto;
	}
	/**
	 * M�todo para identificar si la petici�n es para exportar una lista o para mostrar simplemente un listado. 
	 * @param request
	 * @return
	 */
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
    * Validate ip address with regular expression
    * @param ip ip address for validation
    * @return true valid ip address, false invalid ip address
    */
    public static boolean validate(final String ip){		  
      pattern = Pattern.compile(IPADDRESS_PATTERN);
	  matcher = pattern.matcher(ip);
	  return matcher.matches();	    	    
    }
    /**
     * Valida que el n� de movil introducido es valido.
     * @param mobileNumber
     * @return
     */
    public static boolean validateMobileNumber(String mobileNumber){
    	Pattern pattern = Pattern.compile(MOBILE_NUMBER_PATTERN);
    	matcher = pattern.matcher(mobileNumber);
    	return matcher.matches();
    }
    /**
     * 
     * @param nmaxenvios
     * @return
     */
	public static boolean isMobileNumber(String nmaxenvios) {
		if (nmaxenvios == null || nmaxenvios.toString().length() == 0 || !(nmaxenvios.toString().length()==9)){
            return false;
		}else{
	        for (int i = 0; i < nmaxenvios.toString().length(); i++) {
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
	 * 
	 * @param nombreCriterio
	 * @return
	 */
	public static String parseTextCriteria(String nombreCriterio) {
		if(isEmpty(nombreCriterio)){
			return "%";
		}else{
			if(nombreCriterio.indexOf("*")!=-1){
				return nombreCriterio.replaceAll("\\*","%");
			}else{
				return "%"+nombreCriterio+"%";
			}
			
		}
	}
	/**
	 * Obligatorio pasar un a�o de inicio. Formato YYYY (2009)
	 * @param startYear
	 * @return
	 */
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
	public static boolean validateEmail(String email){
		String EMAIL_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
}

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
	  public boolean validate(final String time){

		  matcher = pattern.matcher(time);
		  return matcher.matches();

	  }
	  
	  
	  
}