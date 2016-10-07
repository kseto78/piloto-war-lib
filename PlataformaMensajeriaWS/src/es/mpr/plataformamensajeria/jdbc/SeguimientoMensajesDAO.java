package es.mpr.plataformamensajeria.jdbc;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import oracle.jdbc.OracleTypes;
import es.mpr.plataformamensajeria.beans.ConsultaEstadoBean;
import es.mpr.plataformamensajeria.beans.ConsultaHistoricoBean;

/**
 * 
 * @author i-nercya
 *
 */
public class SeguimientoMensajesDAO extends PlataformaWSDAO {
	private static final String PROCEDIMIENTO_CONSULTAR_ESTADO = "{call PKG_SEGUI_MENSAJES.CONSULTARESTADO(?,?,?,?,?,?,?,?,?,?,?,?)}";
	private static final String PROCEDIMIENTO_CONSULTAR_HISTORIAL = "{call PKG_SEGUI_MENSAJES.CONSULTARHISTORIAL(?,?,?,?,?)}";
	static Logger logger = Logger.getLogger(SeguimientoMensajesDAO.class);
	/**
	 * 
	 * @param servicioId
	 * @param canalId
	 * @param aplicacionId
	 * @param loteId
	 * @param mensajeId
	 * @param codExterno
	 * @param estadoId
	 * @param fechaDesde
	 * @param fechaHasta
	 * @param usuario
	 * @param password
	 * @return
	 */
	public ArrayList<ConsultaEstadoBean> consultarEstado(Integer servicioId, Integer canalId, Integer aplicacionId, Integer loteId, Integer mensajeId,
			String codExterno, Integer estadoId, Date fechaDesde, Date fechaHasta, String usuario, String password){
		ResultSet rs = null;
		CallableStatement call = null;
		ArrayList<ConsultaEstadoBean> resultado = new ArrayList<ConsultaEstadoBean>();
		try{
			//beginTransaction();
			call = getConn().prepareCall(PROCEDIMIENTO_CONSULTAR_ESTADO);
			if(servicioId!=null){
				call.setInt(1, new Integer(servicioId));
			}else{
				call.setNull(1, java.sql.Types.INTEGER);
			}
			if(canalId!=null){
				call.setInt(2, canalId);
			}else{
				call.setNull(2, java.sql.Types.INTEGER);
			}
			if(aplicacionId!=null){
				call.setInt(3, aplicacionId);
			}else{
				call.setNull(3, java.sql.Types.INTEGER);
			}
			if(loteId!=null){
				call.setInt(4, loteId);
			}else{
				call.setNull(4, java.sql.Types.INTEGER);
			}
			if(mensajeId!=null){
				call.setInt(5, mensajeId);
			}else{
				call.setNull(5, java.sql.Types.INTEGER);
			}			
			
			call.setString(6,codExterno);
			if(estadoId!=null){
				call.setInt(7, estadoId);
			}else{
				call.setNull(7, java.sql.Types.INTEGER);
			}	
			
			if(fechaDesde!=null){
				call.setDate(8, new java.sql.Date(fechaDesde.getTime()));
			}else{
				call.setDate(8, null);
			}
			if(fechaHasta!=null){
				call.setDate(9, new java.sql.Date(fechaHasta.getTime()));
			}else{
				call.setDate(9,null);
			}
			
			call.setString(10, usuario);
			call.setString(11, password);
			call.registerOutParameter(12, OracleTypes.CURSOR);
			call.executeUpdate();
			rs =  (ResultSet) call.getObject(12);
			// ESCA-JAVA0283:
			while(rs!=null&&rs.next()){
				ConsultaEstadoBean estado = new ConsultaEstadoBean();
				String aplicacion = rs.getString("APLICACION");
				String servicio = rs.getString("SERVICIO");
				String estadoE = rs.getString("ESTADO");
				Integer idCanal = rs.getInt("CANALID");
				Integer idAplicacion = rs.getInt("APLICACIONID");
				Integer idServicio = rs.getInt("SERVICIOID"); 
				Integer idEnvio = rs.getInt("MENSAJEID");
				Date ultimoEnvio = rs.getDate("ULTIMOENVIO");
				Integer idLote = rs.getInt("LOTEENVIOID");
				String idExterno = rs.getString("IDEXTERNO");
				String canal = rs.getString("CANAL");
				estado.setIdServicio(idServicio.toString());
				estado.setServicio(servicio);
				estado.setIdCanal(idCanal.toString());
				estado.setCanal(canal);
				estado.setIdAplicacion(idAplicacion.toString());
				estado.setAplicacion(aplicacion);
				estado.setIdLote(idLote.toString());
				estado.setIdMensaje(idEnvio.toString());
				estado.setIdExterno(idExterno);
				estado.setEstado(estadoE);
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				if(ultimoEnvio!=null){
					estado.setFecha(sdf.format(ultimoEnvio));
				} 
				resultado.add(estado);
			}
			//endTransaction(true);
		}catch (SQLException e) {
			logger.debug("[consultarEstado] - Error en bbdd",e);
			
		}finally{
			this.closeSQLObject(call);
		}
		return resultado;
	}
	/**
	 * 
	 * @param idMensaje
	 * @param idExterno
	 * @param usuario
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<ConsultaHistoricoBean> consultarHistorial(Integer idMensaje, String idExterno, String usuario, String password){
		ArrayList<ConsultaHistoricoBean> resultado =  new ArrayList<ConsultaHistoricoBean>();
		CallableStatement call = null;
		ResultSet rs = null;
		try{
			call = getConn().prepareCall(PROCEDIMIENTO_CONSULTAR_HISTORIAL);
			if(idMensaje!=null){
				call.setInt(1, idMensaje);
			}else{
				call.setNull(1, java.sql.Types.INTEGER);
			}
			call.setString(2, idExterno);
			call.setString(3, usuario);
			call.setString(4, password);
			call.registerOutParameter(5, OracleTypes.CURSOR);
			call.executeUpdate();
			rs =  (ResultSet) call.getObject(5);
			while(rs!=null&&rs.next()){
				ConsultaHistoricoBean historico = new ConsultaHistoricoBean();
				Integer mensajeId = rs.getInt("MENSAJEID");
				String externalId = rs.getString("CODIGOEXTERNO");
				Integer servidorId = rs.getInt("SERVIDORID");
				String servidor = rs.getString("SERVIDOR");
				Date fecha = rs.getDate("FECHA");
				String estado = rs.getString("ESTADO");
				historico.setIdMensaje(mensajeId.toString());
				historico.setEstado(estado);
				if(fecha!=null){
					SimpleDateFormat sdf = new SimpleDateFormat();
					historico.setFecha(sdf.format(fecha));
				}
				historico.setIdExterno(externalId);
				historico.setIdServidor(servidorId.toString());
				historico.setServidor(servidor);
				resultado.add(historico);
			}
		}catch(SQLException e){
			logger.debug("[consultarHistorial] - Error en bbdd",e);
		}finally{
			this.closeSQLObject(call);
		}
		return resultado;
	}
}
