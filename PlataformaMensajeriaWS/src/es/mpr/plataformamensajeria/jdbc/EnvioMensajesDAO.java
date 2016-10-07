package es.mpr.plataformamensajeria.jdbc;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import oracle.jdbc.OracleTypes;
import oracle.sql.BLOB;
import oracle.sql.CLOB;

/**
 * 
 * @author i-nercya
 *
 */
public class EnvioMensajesDAO extends PlataformaWSDAO {
	static Logger logger = Logger.getLogger(EnvioMensajesDAO.class);
	private static final String PROCEDIMIENTO_CREAR_LOTE = "{call PKG_ENVIOS_GENERAL.CREARLOTE(?,?,?,?,?)}";
	private static final String PROCEDIMIENTO_ELIMINAR_LOTE = "{call PKG_ENVIOS_GENERAL.ELIMINARLOTE(?,?,?,?)}";
	private static final String PROCEDIMIENTO_CREAR_MENSAJE = "{call PKG_ENVIOS_EMAIL.CREARMENSAJEINDICADORES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	private static final String PROCEDIMIENTO_ELIMINAR_MENSAJE = "{call PKG_ENVIOS_EMAIL.ELIMINARMENSAJE(?,?,?,?)}";
	private static final String PROCEDIMIENTO_NUEVO_ANEXO = "{call PKG_ENVIOS_EMAIL.NUEVOANEXO (?,?,?,?,?,?)}";
	private static final String PROCEDIMIENTO_ASOCIAR_ANEXO = "{call PKG_ENVIOS_EMAIL.ASOCIARANEXO (?,?,?,?,?)}";
	private static final String PROCEDIMIENTO_CREAR_MENSAJE_SMS="{call PKG_ENVIOS_SMS.CREARMENSAJESMS(?,?,?,?,?,?,?,?,?,?,?)}";
	private static final String PROCEDIMIENTO_ELIMINAR_MENSAJE_SMS="{call PKG_ENVIOS_SMS.ELIMINARMENSAJESMS(?,?,?,?,?,?,?)}";
	private static final String PROCEDIMIENTO_COMPROBAR_ENVIO = "{call PKG_ENVIOS_EMAIL.COMPROBAREMAIL(?,?,?)}";
	//Actualizaciones bug numero caracteres
	private static final String PROCEDIMIENTO_CREAR_MENSAJE_SINCUERPO = "{call PKG_ENVIOS_EMAIL.CREARMENSAJESINCUERPO(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
	
	//Nuevos procedimientos para a�adir imagenes y asociarlas a un mensaje.
	/**
	 * PROCEDURE NUEVAIMAGEN (paramEmailId in number,
                        paramCID in varchar2,
                        paramAnexo in blob,
                        paramUsuario in varchar2,
                        paramPassword in varchar2, outConfirmacion out number);

PROCEDURE ASOCIARIMAGEN (paramEmailId in number,
                        paramAdjuntoId in number,
                        paramUsuario in varchar2,
                        paramPassword in varchar2, outConfirmacion out number);
	 */
	
	private static final String PROCEDIMIENTO_NUEVA_IMAGEN = "{call PKG_ENVIOS_EMAIL.NUEVAIMAGEN(?,?,?,?,?,?)}";
	private static final String PROCEDIMIENTO_ASOCIAR_IMAGEN = "{call PKG_ENVIOS_EMAIL.ASOCIARIMAGEN(?,?,?,?,?,?)}";
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
			//beginTransaction();
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
			
			//endTransaction(true);
		}catch (SQLException e) {
			logger.debug("[crearLote] - Error en bbdd", e);
			
			idLote=new BigDecimal(-10);
		}finally{
			this.closeSQLObject(call);
		}
		return idLote.intValue();
	}
	/**
	 * 
	 * @param idLote
	 * @param usuario
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public BigDecimal eliminarLote(String idLote, String usuario, String password){
		BigDecimal confirmacion = null;
		CallableStatement call = null;
		try{
			//beginTransaction();
			 call = getConn().prepareCall(PROCEDIMIENTO_ELIMINAR_LOTE);
			if(idLote!=null){
				call.setInt(1,new Integer(idLote));
			}else{
				call.setNull(1,java.sql.Types.INTEGER);
			}
			call.setString(2, usuario);
			call.setString(3, password);
			call.registerOutParameter(4, OracleTypes.NUMBER);
			call.executeUpdate();
			confirmacion =  (BigDecimal) call.getObject(4);
			
			//endTransaction(true);
		}catch(SQLException e){
			logger.debug("[eliminarLote] - Error en bbdd", e);
			confirmacion = new BigDecimal(-10);
			//throw new DatabaseException("Se ha producido un error interno eliminando el lote con id: " +idLote);
		}finally{
			this.closeSQLObject(call);
		}
		return confirmacion;
	}
	/**
	 * 
	 * @param idLote
	 * @param asunto
	 * @param cuerpo
	 * @param docUsuario
	 * @param codSIA
	 * @param codOrganismo
	 * @param codExterno
	 * @param cc
	 * @param bcc
	 * @param to
	 * @param usuario
	 * @param password
	 * @return
	 * paramLoteId in number,
                     paramCabecera in varchar,
                     paramCuerpo in clob,
                     paramCodExterno in varchar,
                     paramCC in varchar2,
                     paramBCC in varchar2,
                     paramTO in varchar2,
                     paramTipoCuerpo in varchar2,
                     paramTipoCodificacion in varchar2,  
                     paramPrioridad in number,
                     paramUsuario in varchar2,
                     paramPassword in varchar2, outEmailId out number)
	 */
	public Integer crearEmail(Integer idLote, String asunto, String cuerpo, String docUsuario, String codSIA, String codOrganismo, 
			String codExterno, String formatoCuerpo, String codificacion, Integer prioridad, String cc, String bcc,
			String to, String usuario, String password){
		BigDecimal idMensaje = null;
		CallableStatement call = null;
		try{
			//beginTransaction();
			call = getConn().prepareCall(PROCEDIMIENTO_CREAR_MENSAJE);
			if(idLote!=null){
				call.setInt(1,new Integer(idLote));
			}else{
				call.setNull(1,java.sql.Types.INTEGER);
			}
			call.setString(2, asunto);
			CLOB tempClob = CLOB.createTemporary(getConn(), true, CLOB.DURATION_SESSION);
			  tempClob.open(CLOB.MODE_READWRITE);
			  Writer tempClobWriter = tempClob.getCharacterOutputStream();
			  tempClobWriter.write(cuerpo);
			  tempClobWriter.flush();
			  tempClobWriter.close(); 
			  tempClob.close();
			call.setClob(3, tempClob);
			//call.setString(3, cuerpo);
			call.setString(4,docUsuario);
			call.setString(5,codSIA);
			call.setString(6,codOrganismo);
			call.setString(7,codExterno);
			call.setString(8,cc);
			call.setString(9,bcc);
			call.setString(10,to);
			
			call.setString(11,(formatoCuerpo!=null)?formatoCuerpo:"");
			call.setString(12,(codificacion!=null)?codificacion:"");
			call.setInt(13,(prioridad!=null)?prioridad:0);
			call.setString(14,usuario);
			call.setString(15,password);
			call.registerOutParameter(16, OracleTypes.NUMBER);
			call.executeUpdate();
			idMensaje =  (BigDecimal) call.getObject(16);
			
			//endTransaction(true);
		}catch(SQLException ec){
			logger.debug("[crearEmail] - Error en bbdd", ec);
			
			idMensaje=new BigDecimal(-10);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("[crearEmail] - Error I/O", e);
			idMensaje=new BigDecimal(-10);
			e.printStackTrace();
		}finally{
			this.closeSQLObject(call);
		}
		return idMensaje.intValue();
	}
	/**
	 * 
	 * @param idMensaje
	 * @param usuario
	 * @param password
	 * @return
	 */
	public Integer eliminarEmail(Integer idMensaje, String usuario, String password){
		BigDecimal confirmacion = null;
		CallableStatement call = null;
		try{
			//beginTransaction();
			call = getConn().prepareCall(PROCEDIMIENTO_ELIMINAR_MENSAJE);
			if(idMensaje!=null){
				call.setInt(1,new Integer(idMensaje));
			}else{
				call.setNull(1,java.sql.Types.INTEGER);
			}
			call.setString(2,usuario);
			call.setString(3,password);
			call.registerOutParameter(4, OracleTypes.NUMBER);
			call.executeUpdate();
			confirmacion =  (BigDecimal) call.getObject(4);
			
			//endTransaction(true);
		}catch(SQLException ec){
			confirmacion=new BigDecimal(-10);
			logger.debug("[eliminarEmail] - Error en bbdd", ec);
			
		}finally{
			this.closeSQLObject(call);
		}
		return confirmacion.intValue();
	}
	/**
	 * 
	 * @param idMensaje
	 * @param CID Imagen
	 * @param contenido
	 * @param usuario
	 * @param password
	 * @return
	 */
	public Integer nuevaImagen(Integer idMensaje,String cid, byte[] contenido,
			String usuario, String password){
		BigDecimal idAnexo = null;
		CallableStatement call = null;
		try{
			//beginTransaction();
			call = getConn().prepareCall(PROCEDIMIENTO_NUEVA_IMAGEN);
			if(idMensaje!=null){
				call.setInt(1,new Integer(idMensaje));
			}else{
				call.setNull(1,java.sql.Types.INTEGER);
			}
			call.setString(2,cid);
			BLOB blob = BLOB.createTemporary(getConn(), true, BLOB.DURATION_SESSION);
		    OutputStream blob_os = blob.getBinaryOutputStream();
		    if(contenido!=null){
			    blob_os.write(contenido);
			    blob_os.flush();
				call.setBlob(3, blob);
			}else{
				blob=null;
				call.setBlob(3, blob);
			}
		    call.setString(4,usuario);
		    call.setString(5, password);
		    call.registerOutParameter(6, OracleTypes.NUMBER);
			call.executeUpdate();
			idAnexo =  (BigDecimal) call.getObject(6);
			//endTransaction(true);
		}catch(SQLException ec){
			logger.debug("[nuevaImagen] - Error en bbdd", ec);
			idAnexo=new BigDecimal(-10); //error base de datos
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeSQLObject(call);
		}
		return idAnexo.intValue();
	}
	
	/**
	 * 
	 * @param idMensaje
	 * @param idAdjunto
	 * @param usuario
	 * @param password
	 * @return
	 */
	public Integer asociarImagen(Integer idMensaje, Integer idImagen, String usuario, String password){
		BigDecimal confirmacion = null;
		CallableStatement call = null;
		try{
			//beginTransaction();
			
			call = getConn().prepareCall(PROCEDIMIENTO_ASOCIAR_IMAGEN);
			if(idMensaje!=null){
				call.setInt(1,new Integer(idMensaje));
			}else{
				call.setNull(1,java.sql.Types.INTEGER);
			}
			call.setInt(2, idImagen);
		    call.setString(3,usuario);
		    call.setString(4, password);
		    call.registerOutParameter(5, OracleTypes.NUMBER);
			call.executeUpdate();
			confirmacion =  (BigDecimal) call.getObject(5);
			//endTransaction(true);
		}catch(SQLException ec){
			logger.debug("[asociaImagen] - Error en bbdd", ec);
			confirmacion= new BigDecimal(-10); //error base de datos
		}finally{
			this.closeSQLObject(call);
		}
		return confirmacion.intValue();
	}
	/**
	 * 
	 * @param idMensaje
	 * @param nombreAnexo
	 * @param contenido
	 * @param usuario
	 * @param password
	 * @return
	 */
	public Integer nuevoAnexo(Integer idMensaje,String nombreAnexo, byte[] contenido,
			String usuario, String password){
		BigDecimal idAnexo = null;
		CallableStatement call = null;
		try{
			//beginTransaction();
			call = getConn().prepareCall(PROCEDIMIENTO_NUEVO_ANEXO);
			if(idMensaje!=null){
				call.setInt(1,new Integer(idMensaje));
			}else{
				call.setNull(1,java.sql.Types.INTEGER);
			}
			call.setString(2,nombreAnexo);
			BLOB blob = BLOB.createTemporary(getConn(), true, BLOB.DURATION_SESSION);
		    OutputStream blob_os = blob.getBinaryOutputStream();
		    if(contenido!=null){
			    blob_os.write(contenido);
			    blob_os.flush();
				call.setBlob(3, blob);
			}else{
				blob=null;
				call.setBlob(3, blob);
			}
		    call.setString(4,usuario);
		    call.setString(5, password);
		    call.registerOutParameter(6, OracleTypes.NUMBER);
			call.executeUpdate();
			idAnexo =  (BigDecimal) call.getObject(6);
			//endTransaction(true);
		}catch(SQLException ec){
			logger.debug("[nuevoAnexo] - Error en bbdd", ec);
			idAnexo=new BigDecimal(-10); //error base de datos
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeSQLObject(call);
		}
		return idAnexo.intValue();
	}
	/**
	 * 
	 * @param idMensaje
	 * @param idAdjunto
	 * @param usuario
	 * @param password
	 * @return
	 */
	public Integer asociarAnexo(Integer idMensaje, Integer idAdjunto, String usuario, String password){
		BigDecimal confirmacion = null;
		CallableStatement call = null;
		try{
			//beginTransaction();
			call = getConn().prepareCall(PROCEDIMIENTO_ASOCIAR_ANEXO);
			if(idMensaje!=null){
				call.setInt(1,new Integer(idMensaje));
			}else{
				call.setNull(1,java.sql.Types.INTEGER);
			}
			call.setInt(2, idAdjunto);
		    call.setString(3,usuario);
		    call.setString(4, password);
		    call.registerOutParameter(5, OracleTypes.NUMBER);
			call.executeUpdate();
			confirmacion =  (BigDecimal) call.getObject(5);
			//endTransaction(true);
		}catch(SQLException ec){
			logger.debug("[asociaAnexo] - Error en bbdd", ec);
			confirmacion= new BigDecimal(-10); //error base de datos
		}finally{
			this.closeSQLObject(call);
		}
		return confirmacion.intValue();
	}
	/**
	 * Crea un nuevo mensaje asociado al idLote correspondiente y devuelve el identificador del SMS creado
	 * @param idLote - Identificador del lote al que se asociar� el SMS
	 * @param cuerpo
	 * @param docUsuario
	 * @param codSIA
	 * @param codOrganismo
	 * @param codOrganismoPagador
	 * @param idExterno
	 * @param destinatario
	 * @param usuario
	 * @param password
	 * @return
	 */
	public Integer crearSMS(Integer idLote, String cuerpo, String docUsuario, String codSIA, 
			String codOrganismo, String codOrganismoPagador, String idExterno, String destinatario, String usuario,String password){
		BigDecimal idSMS = null;
		CallableStatement call = null;
		try{
			//beginTransaction();
			call = getConn().prepareCall(PROCEDIMIENTO_CREAR_MENSAJE_SMS);
			if(idLote!=null){
				call.setInt(1,new Integer(idLote));
			}else{
				call.setNull(1,java.sql.Types.INTEGER);
			}
			call.setString(2, cuerpo);
			call.setString(3, docUsuario);
			call.setString(4, codSIA);
			call.setString(5, codOrganismo);
			call.setString(6, codOrganismoPagador);
		    call.setString(7, idExterno);
		    call.setString(8, destinatario);
		    call.setString(9, usuario);
		    call.setString(10, password);
		    call.registerOutParameter(11, OracleTypes.NUMBER);
			call.executeUpdate();
			idSMS =  (BigDecimal) call.getObject(11);
			
			//endTransaction(true);
		}catch(SQLException ec){
			logger.debug("[crearSMS] crearSMS - Error en bbdd", ec);
			ec.printStackTrace();
			idSMS=new BigDecimal(-10); //error base de datos
		}finally{
			this.closeSQLObject(call);
		}	
		return idSMS.intValue();
	}
	/**
	 * Elimina un mensaje y devuelve el c�digo de confimraci�n (Ver documentaci�n para identificar los posibles c�digos de confirmaci�n
	 * @param idSMS
	 * @param usuario
	 * @param password
	 * @return
	 */
	public Integer eliminarSMS(Integer idSMS, String usuario, String password){
		BigDecimal confirmacion = null;
		CallableStatement call =null;
		try{
			//beginTransaction(); 
			call = getConn().prepareCall(PROCEDIMIENTO_ELIMINAR_MENSAJE_SMS);
			if(idSMS!=null){
				call.setInt(1,new Integer(idSMS));
			}else{
				call.setNull(1,java.sql.Types.INTEGER);
			}
			call.setString(2,usuario);
		    call.setString(3, password);
		    call.registerOutParameter(4, OracleTypes.NUMBER);
			call.executeUpdate();
			confirmacion =  (BigDecimal) call.getObject(4);
			
			//endTransaction(true);
		}catch(SQLException ec){
			logger.debug("[eliminarSMS] - Error en bbdd", ec);
			confirmacion=new BigDecimal(-10); //error base de datos
		}finally{
			this.closeSQLObject(call);
		}	
		return confirmacion.intValue(); 
	}
	
	public Integer comprobarEnvioEmail(Integer idMensaje, String usuario, String password){
		BigDecimal confirmacion = new BigDecimal(1);
		CallableStatement call =null;
		try{ 
			//beginTransaction() ;
			call = getConn().prepareCall(PROCEDIMIENTO_COMPROBAR_ENVIO);
			if(idMensaje!=null){
				call.setInt(1,new Integer(idMensaje));
			}else{
				return 0;
			}
			call.setString(2,usuario);
		    call.setString(3, password);
		  // call.registerOutParameter(4, OracleTypes.NUMBER);
			call.executeUpdate();
			//confirmacion =  (BigDecimal) call.getObject(4);
			
			//endTransaction(true);
		}catch(SQLException ec){
			logger.debug("[comprobarEnvioEmail] - Error en bbdd", ec);
			confirmacion=new BigDecimal(-10); //error base de datos
		}finally{
			this.closeSQLObject(call);
		}	
		return confirmacion.intValue();
	}
}
