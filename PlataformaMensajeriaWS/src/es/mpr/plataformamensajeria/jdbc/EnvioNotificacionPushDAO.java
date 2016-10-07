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
public class EnvioNotificacionPushDAO extends PlataformaWSDAO {
	static Logger logger = Logger.getLogger(EnvioNotificacionPushDAO.class);
	private static final String PROCEDIMIENTO_CREAR_LOTE = "{call PKG_ENVIOS_GENERAL.CREARLOTE(?,?,?,?,?)}";
	private static final String PROCEDIMIENTO_CREAR_MENSAJE_NOTIFICACION_PUSH=
			"{call PKG_ENVIOS_NOTIFICACIONES_PUSH.CREARMENSAJENOTIFICACIONPUSH(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	/**
	 *  
	 * @param servicioId
	 * @param nombreLote
	 * @param usuario
	 * @param password
	 * @return
	 */
	public Integer crearLote(String servicioId, String nombreLote, String usuario, String password){
		BigDecimal idLote = null;
		CallableStatement call = null;
		try{
			
			call = getConn().prepareCall(PROCEDIMIENTO_CREAR_LOTE);
			if(servicioId!=null){
				call.setInt(1,new Integer(servicioId));
			}else{
				call.setNull(1,java.sql.Types.INTEGER);
			}
			
			call.setString(2, nombreLote);
			call.setString(3, usuario);
			call.setString(4, password);
			call.registerOutParameter(5, OracleTypes.NUMBER);
			call.executeUpdate();
			idLote =  (BigDecimal) call.getObject(5);
			
		}catch (SQLException e) {
			logger.debug("[crearLote] - Error en bbdd", e);
			
			idLote=new BigDecimal(-10);
		}finally{
			this.closeSQLObject(call);
		}
		return idLote.intValue();
	}
	
	/**
	 * @param idLote
	 * @param titulo
	 * @param cuerpo
	 * @param docUsuario
	 * @param codSIA
	 * @param codOrganismo
	 * @param icono
	 * @param sonido
	 * @param idExterno
	 * @param nombreUsuarioDestinatario
	 * @param usuario
	 * @param password
	 * @return
	 */
	public Integer crearMensajeNotificacionPush(Integer idLote, String titulo, String cuerpo, String docUsuario, String codSIA, 
			String codOrganismo, String icono, String sonido, String idExterno, String nombreUsuarioDestinatario, String usuario, String password){
		BigDecimal idMensajeNotificacionPush = null;
		CallableStatement call = null;
		
		try{
			
			call = getConn().prepareCall(PROCEDIMIENTO_CREAR_MENSAJE_NOTIFICACION_PUSH);
			if(idLote!=null){
				call.setInt(1,new Integer(idLote));
			}else{
				call.setNull(1,java.sql.Types.INTEGER);
			}
			call.setString(2, titulo);
			call.setString(3, cuerpo);
			call.setString(4, docUsuario);
			call.setString(5, codSIA);
			call.setString(6, codOrganismo);
		    call.setString(7, idExterno);
		    call.setString(8, nombreUsuarioDestinatario);
		    call.setString(9, icono);
		    call.setString(10, sonido);
		    call.setString(11, usuario);
		    call.setString(12, password);
		    call.registerOutParameter(13, OracleTypes.NUMBER);
			call.executeUpdate();
			idMensajeNotificacionPush = (BigDecimal) call.getObject(13);
			
		}catch(SQLException ec){
			logger.debug("[crearSMS] crearMensajeNotificacionPush - Error en bbdd", ec);
			ec.printStackTrace();
			idMensajeNotificacionPush = new BigDecimal(-10); //error base de datos
		}finally{
			this.closeSQLObject(call);
		}	
		
		return idMensajeNotificacionPush.intValue();
	}
	
}
