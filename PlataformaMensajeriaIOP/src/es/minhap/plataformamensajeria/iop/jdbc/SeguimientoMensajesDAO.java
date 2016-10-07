package es.minhap.plataformamensajeria.iop.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;

import es.minhap.plataformamensajeria.iop.beans.respuestaSeguimiento.SeguimientoMensaje;
import es.minhap.plataformamensajeria.iop.beans.respuestaSeguimiento.Registro;
/**
 * 
 * @author i-nercya
 *
 */
public class SeguimientoMensajesDAO extends PlataformaMensajeriaIOPDAO {
	private static final String PROCEDIMIENTO_CONSULTAR_ESTADO = "{call PKG_SEGUI_MENSAJES.CONSULTARESTADO(?,?,?,?,?,?,?,?,?,?,?,?)}";
	private static final String PROCEDIMIENTO_CONSULTAR_ESTADO_MULTIDEST = "{call PKG_SEGUI_MENSAJES.CONSULTARESTADOMULTIDEST(?,?,?,?,?,?,?,?,?,?,?,?)}";
	private static final String PROCEDIMIENTO_CONSULTAR_HISTORIAL = "{call PKG_SEGUI_MENSAJES.CONSULTARHISTORIAL(?,?,?,?,?)}";
	private static final String PROCEDIMIENTO_CONSULTAR_HISTORIAL_MULTIDEST = "{call PKG_SEGUI_MENSAJES.CONSULTARHISTORIALMULTIDEST(?,?,?,?,?)}";
	private static final String GET_ES_MULTIDESTINATARIO_BY_MENSAJE = "SELECT lt.MULTIDESTINATARIO FROM TBL_LOTESENVIOS lt inner join "
			+ "TBL_MENSAJES m on m.LOTEENVIOID = lt.LOTEENVIOID where m.MENSAJEID = ? AND lt.MULTIDESTINATARIO = 1";
	private static final String GET_ES_MULTIDESTINATARIO_BY_LOTE = "SELECT MULTIDESTINATARIO FROM TBL_LOTESENVIOS where LOTEENVIOID = ?";
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
	public ArrayList<SeguimientoMensaje> consultarEstado(Integer servicioId, Integer canalId, Integer aplicacionId, Integer loteId, Integer mensajeId,
			String codExterno, Integer estadoId, Date fechaDesde, Date fechaHasta, String usuario, String password){
		ResultSet rs = null;
		CallableStatement call = null;
		ArrayList<SeguimientoMensaje> resultado = new ArrayList<SeguimientoMensaje>();
		try{
		
			call = this.getConn().prepareCall(PROCEDIMIENTO_CONSULTAR_ESTADO);
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
				SeguimientoMensaje estado = new SeguimientoMensaje();
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
				Integer reintentos = rs.getInt("NUMEROENVIOS");
				Integer idEstado = rs.getInt("ESTADOID");
				estado.setIdServicio(idServicio);
				estado.setServicio(servicio);
				estado.setIdCanal(idCanal);
				estado.setCanal(canal);
				estado.setIdAplicacion(idAplicacion);
				estado.setAplicacion(aplicacion);
				estado.setIdLote(idLote);
				estado.setIdMensaje(idEnvio);
				estado.setIdExterno(idExterno);
				if (null != idEstado) {
					estado.setIdEstado(idEstado);
				}
				estado.setEstado(estadoE);
				if (null != reintentos) {
					estado.setReintentos(reintentos);
				}
				estado.setFecha(toXMLGregorianCalendar(ultimoEnvio));
				
				resultado.add(estado);
			}
			
		}catch (SQLException e) {
			logger.error("[SeguimientoMensajesDAO.consultarEstado] - Error en bbdd",e);
			
		}finally{
			this.closeSQLObject(rs);
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
	public ArrayList<Registro> consultarHistorial(Integer idMensaje, String idExterno, String usuario, String password){
		ArrayList<Registro> resultado =  new ArrayList<Registro>();
		CallableStatement call = null;
		ResultSet rs = null;
		try{

			call = this.getConn().prepareCall(PROCEDIMIENTO_CONSULTAR_HISTORIAL);
			if (null != idMensaje){
				if (isMultidestinatario(idMensaje))
					call = this.getConn().prepareCall(PROCEDIMIENTO_CONSULTAR_HISTORIAL_MULTIDEST);
				else
					call = this.getConn().prepareCall(PROCEDIMIENTO_CONSULTAR_HISTORIAL);
			}else{
				call = this.getConn().prepareCall(PROCEDIMIENTO_CONSULTAR_HISTORIAL);
			}
			
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
				Registro historico = new Registro();
				Integer mensajeId = rs.getInt("MENSAJEID");
				String externalId = rs.getString("CODIGOEXTERNO");
				Integer servidorId = rs.getInt("SERVIDORID");
				String servidor = rs.getString("SERVIDOR");
				Date fecha = rs.getDate("FECHA");
				String estado = rs.getString("ESTADO");
				historico.setIdMensaje(mensajeId);
				historico.setEstado(estado);
				historico.setFecha(toXMLGregorianCalendar(fecha));
				
				historico.setIdExterno(externalId);
				historico.setIdServidor(servidorId);
				historico.setServidor(servidor);
				resultado.add(historico);
			}
		}catch(SQLException e){
			logger.debug("[SeguimientoMensajesDAO.consultarHistorial] - Error en bbdd",e);
		}finally{
			this.closeSQLObject(call);
			this.closeSQLObject(rs);
		}
		return resultado;
	}
	
	
	public static Date toDate(XMLGregorianCalendar calendar){
        if(calendar == null) {
            return null;
        }
        return calendar.toGregorianCalendar().getTime();
    }
	
	public static XMLGregorianCalendar toXMLGregorianCalendar(Date date){
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(date);
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
        } catch (DatatypeConfigurationException ex) {
            
        }
        return xmlCalendar;
    }
	
	private boolean isMultidestinatarioLote(Integer idLote) {
		boolean res = false;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String s;
		try {
			conn = this.getConn();
			s = GET_ES_MULTIDESTINATARIO_BY_LOTE;
			pst = conn.prepareStatement(s);
			pst.setInt(1, idLote);
			rs = pst.executeQuery();

			if (rs.next()){
					return true;
			}

		} catch (SQLException e) {
			logger.debug(
					"[OperacionesMensajesDAO.isMultidestinatarioLote] - Error en bbdd obteniendo si es Multidestinatario",e);
			res = false;
		} catch (NumberFormatException e) {
			logger.debug(
					"[OperacionesMensajesDAO.isMultidestinatario] - La loteId introducido no es correcto", e);
			res = false;
		}finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(pst);
		}
		return res;
	}
	private boolean isMultidestinatario(Integer idMensaje) {
		boolean res = false;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String s;
		try {
			conn = this.getConn();
			s = GET_ES_MULTIDESTINATARIO_BY_MENSAJE;
			pst = conn.prepareStatement(s);
			pst.setInt(1, idMensaje);
			rs = pst.executeQuery();

			if (rs.next()){
					return true;
			}

		} catch (SQLException e) {
			logger.debug(
					"[OperacionesMensajesDAO.isMultidestinatario] - Error en bbdd obteniendo si es Multidestinatario",e);
			res = false;
		} catch (NumberFormatException e) {
			logger.debug(
					"[OperacionesMensajesDAO.isMultidestinatario] - La mensajeId introducido no es correcto", e);
			res = false;
		}finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(pst);
		}
		return res;
	}
}
