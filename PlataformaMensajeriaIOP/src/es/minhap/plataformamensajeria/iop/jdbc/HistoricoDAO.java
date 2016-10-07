package es.minhap.plataformamensajeria.iop.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import es.minhap.plataformamensajeria.iop.beans.HistoricoBean;

/**
 * 
 * @author jgonzvil
 * 
 */
public class HistoricoDAO extends PlataformaMensajeriaIOPDAO {
	static Logger log = Logger.getLogger(HistoricoDAO.class);

	public boolean setHistorico(HistoricoBean hist){

		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		String s;
		Integer estadoMensaje = null;
		
		conn = this.getConn();
		boolean resultado = true;
		try{
//			actualizarEstadoLote(hist.getMensajeid().intValue());
			estadoMensaje = getEstadoMensaje(hist.getMensajeid().intValue());
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			s = "INSERT INTO tbl_historicos (historicoid,fecha,mensajeid,estadoid,subestadoid) ";
			s += "VALUES(HISTORICOID_SEC.NEXTVAL,sysdate," + hist.getMensajeid()+ "," + estadoMensaje
					+ "," + hist.getSubestadoid() + ")";
			
			rs = stmt.executeQuery(s);
		} catch (Exception e) {
			log.debug("[HistoricoDAO.setHistorico] - Error en bbdd", e);
			resultado =false;
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}	
		return resultado;
	}

	public boolean setHistoricoMult(HistoricoBean hist) {
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		String s;
		conn = this.getConn();
		boolean resultado = true;
		Integer estadoMensaje = null;
		
		try{
				
			estadoMensaje = getEstadoMensaje(hist.getMensajeid().intValue());
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			s = "INSERT INTO tbl_historicos (historicoid,fecha,mensajeid,estadoid,descripcion,subestadoid,destinatariosmensajes) ";
			s += "VALUES(HISTORICOID_SEC.NEXTVAL,sysdate," + hist.getMensajeid() + "," + estadoMensaje
					+ ",'"+ hist.getDescripcion() +"'," + hist.getSubestadoid() + "," + hist.getDestinatariosMensajes()+")";
			
			rs = stmt.executeQuery(s);
		} catch (Exception e) {
			log.debug("[HistoricoDAO.setHistorico] - Error en bbdd", e);
			resultado =false;
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}	
		return resultado;
	}

	private Integer getEstadoMensaje(int mensajeId) {
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		String s;
		conn = this.getConn();
		Integer res = null;
		
		try{
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			s = "Select ESTADOID from TBL_ESTADOS where NOMBRE = (SELECT ESTADOACTUAL from TBL_MENSAJES where  MENSAJEID = " + mensajeId +")";
			rs = stmt.executeQuery(s);
			
			if (rs.next()){
				res = rs.getInt("ESTADOID");
			}	
		
		} catch (Exception e) {
			log.debug("[HistoricoDAO.setHistorico] - Error en bbdd", e);
			
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}	
		return res;
	}

	public void actualizarEstadoLote(Integer mensajeId) {
		Statement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		String s;
		conn = this.getConn();
		String estado_desc = "";
		Map<String, Integer> map = new HashMap<String,Integer>();
		try{
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			s = "SELECT ESTADOACTUAL, count(*) as TOTAL from TBL_MENSAJES where "
					+ "LOTEENVIOID = (Select LOTEENVIOID FROM TBL_MENSAJES WHERE MENSAJEID = " + mensajeId +") group by ESTADOACTUAL";
			rs = stmt.executeQuery(s);
			
			while (rs.next()){
				Integer total = rs.getInt("TOTAL");
				String key = rs.getString("ESTADOACTUAL");
				map.put(key, total);
			}
			if (map.size()==1)
				estado_desc = map.entrySet().iterator().next().getKey();
			else
				estado_desc = "INCIDENCIA";
		
			stmt.close();
			rs.close();
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			s = "UPDATE tbl_lotesEnvios set estadoenvioid = (select ESTADOID from TBL_ESTADOS where "
					+ "NOMBRE like '"+estado_desc+"%') where LOTEENVIOID = (select loteenvioid "
					+ "from tbl_mensajes where mensajeid = "
					+ mensajeId
					+ ")";
			
			rs = stmt.executeQuery(s);
			
			stmt.close();
			rs.close();
			
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			s = "UPDATE TBL_GESTIONENVIOS set ESTADOLOTE = '"+estado_desc+"' where LOTEENVIOID = (select loteenvioid "
					+ "from tbl_mensajes where mensajeid = "
					+ mensajeId
					+ ")";
			
			rs = stmt.executeQuery(s);
			
		} catch (Exception e) {
			log.debug("[HistoricoDAO.setHistorico] - Error en bbdd", e);
			
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}	
	}
	
}
