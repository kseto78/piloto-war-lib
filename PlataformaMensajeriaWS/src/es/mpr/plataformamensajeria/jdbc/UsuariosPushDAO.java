package es.mpr.plataformamensajeria.jdbc;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;

/**
 * 
 * @author jgonzvil
 *
 */
public class UsuariosPushDAO extends PlataformaWSDAO {
	static Logger logger = Logger.getLogger(UsuariosPushDAO.class);
	private static final String PROCEDIMIENTO_ALTA_USUARIO_PUSH = "{call PKG_USUARIOS_PUSH.ALTAUSUARIO(?,?,?,?,?,?,?,?)}";
	
	/**
	 *  
	 * @param recipiente
	 * @return 
	 */
	public Integer altaUsuario(String nombreUsuario, String servicioId, String usuario, String password,
			String plataformaId, String tokenUsuario, String dispositivoId){
		BigDecimal result = null;
		CallableStatement call = null;
		try{
			call = getConn().prepareCall(PROCEDIMIENTO_ALTA_USUARIO_PUSH);
			
			call.setString(1, nombreUsuario);
			call.setString(2, servicioId);
			call.setString(3, usuario);
			call.setString(4, password);
			call.setString(5, plataformaId);
			call.setString(6, tokenUsuario);
			call.setString(7, dispositivoId);
			call.registerOutParameter(8, OracleTypes.NUMBER);
			call.executeUpdate();
			
			result = (BigDecimal) call.getObject(8);
			
		}catch (SQLException e) {
			logger.debug("[altaUsuario] - Error en bbdd", e);
			result = null;
		}finally{
			this.closeSQLObject(call);
		}
		
		if(null!=result){
			return result.intValue();
		} else {
			return Integer.valueOf(-1);
		}
		
	}
	
}
