package es.minhap.plataformamensajeria.iop.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles.ServicioMovil;
/**
 * 
 * @author everis
 *
 */
public class ServiciosDisponiblesDAO extends PlataformaMensajeriaIOPDAO {
	static Logger logger = Logger.getLogger(ServiciosDisponiblesDAO.class);

	/**
	 * 
	 * @param idDispositivo
	 * @return
	 */
	public List<ServicioMovil> consultarServiciosDisponibles(String idDispositivo){
		List<ServicioMovil> serviciosMoviles = new ArrayList<>();
		Map<String, ServicioMovil> serviciosMovilesUsuario = new HashMap<>();
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		conn = this.getConn();
		try {
			if (null != idDispositivo) {
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = stmt
						.executeQuery("SELECT S.SERVICIOSMOVILESID as id ,S.NOMBRE as nombre, S.DESCRIPCION as descripcion,S.ICONO as icono, S.URL_SERVICIO as url, S.TIPO as tipo, S.IMAGEN as imagen, S.ESTADO as estado from TBL_SERVICIOSMOVILES S WHERE S.ESTADO = 1");
				while (rs.next()) {
					String id = rs.getString("id");
					String servicio = rs.getString("nombre");
					String descServicio = rs.getString("descripcion");
					String icono = rs.getString("icono");
					String url = rs.getString("url");
					String tipo = rs.getString("tipo");
					String imagen = rs.getString("imagen");
					String estado = rs.getString("estado");
					ServicioMovil servicioMovil = new ServicioMovil();
					servicioMovil.setMobileService(servicio);
					servicioMovil.setDescMobileService(descServicio);
					servicioMovil.setUrlService(url);
					servicioMovil.setIdService(id);
					servicioMovil.setIcon(icono);
					servicioMovil.setTipo(tipo);
					servicioMovil.setImagen(imagen);
					servicioMovil.setEstado(estado);
					serviciosMovilesUsuario.put(id, servicioMovil);
				}
				rs.close();
				stmt.close();
				if (!serviciosMovilesUsuario.isEmpty()) {

					Set<String> keys = serviciosMovilesUsuario.keySet();
					for (String key : keys) {
						ServicioMovil servMovil = serviciosMovilesUsuario.get(key);
						conn = this.getConn();
						stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
						rs = stmt
								.executeQuery("SELECT UP.USUARIOID as ID from TBL_USUARIOS_PUSH UP, TBL_USUARIOS_SERVICIOSMOVILES US, TBL_SERVICIOSMOVILES SERV where UP.USUARIOID = US.USUARIOSID AND up.USUARIOID IN (SELECT  DISTINCT USUARIOID FROM TBL_USUARIOS_PUSH WHERE DISPOSITIVOID='"
										+ idDispositivo
										+ "') AND US.SERVICIOSMOVILESID = SERV.SERVICIOSMOVILESID AND US.ESTADOSUSCRIPCION= 1 AND SERV.SERVICIOSMOVILESID = "
										+ servMovil.getIdService() + "");
						servMovil.setEstado((rs.next()) ? "1" : "0");
						serviciosMovilesUsuario.put(key, servMovil);
						stmt.close();
						rs.close();
					}
					Collection<ServicioMovil> servMovilCollection = serviciosMovilesUsuario.values();
					serviciosMoviles = new ArrayList<>(servMovilCollection);
				}
			}
		} catch (SQLException e) {
			logger.debug("[ServiciosDisponiblesDAO.consultarServiciosDisponibles] - Error en bbdd",e);
		} finally {
			this.closeSQLObject(rs);
			this.closeSQLObject(stmt);
		}	
		return serviciosMoviles;
	}
}
