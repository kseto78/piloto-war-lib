package es.mpr.plataformamensajeria.estadisticas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import oracle.jdbc.OracleTypes;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.util.KeyValueObject;

import es.mpr.plataformamensajeria.estadisticas.bean.ColumnaBean;
import es.mpr.plataformamensajeria.estadisticas.bean.EstadisticasBean;
import es.mpr.plataformamensajeria.estadisticas.bean.FilaEstadisticaBean;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.plataformamensajeria.util.TituloEstadisticasParser;
/**
 * Clase encargada de la generación de las estadísticas.
 * @author i-nercya
 *
 */
public class EstadisticasPlataforma {
	
	private EstadisticasBean estadistica = null;
	/**
	 * Constante para el tipo de vista en años
	 */
	public static final String VISTA_ANNOS="1";
	/**
	 * Constante para el tipo de vista en años
	 */

	public static final String VISTA_MESES="2";
	/**
	 * Constante para el tipo de vista en años
	 */

	public static final String VISTA_DIAS="3";
	
	
	/**
	 * Constantes para el tipo de agrupaciones
	 */
	public static final String AGRUPAR_APLICACIONES="1";
	/**
	 * Constantes para el tipo de agrupaciones
	 */
	public static final String AGRUPAR_SERVIDOR="2";
	/**
	 * Constantes para el tipo de agrupaciones
	 */
	public static final String AGRUPAR_SERVICIO="3";
	/**
	 * Constantes para el tipo de agrupaciones
	 */
	public static final String AGRUPAR_CANAL="4";
	/**
	 * Constantes para el tipo de agrupaciones
	 */
	public static final String AGRUPAR_ESTADO="5";
	
	private static final  HashMap<String,String> vista = new HashMap<String,String>();
 	static
 	{
 		vista.put(VISTA_ANNOS, "Años");
 		vista.put(VISTA_MESES, "Meses");
 		vista.put(VISTA_DIAS, "Días");
 	}	

	private static final HashMap<String,String> agrupaciones = new HashMap<String,String>();
 	static
 	{
 		agrupaciones.put(AGRUPAR_APLICACIONES, "Aplicaciones");
 		agrupaciones.put(AGRUPAR_SERVIDOR, "Servidor");
 		agrupaciones.put(AGRUPAR_SERVICIO, "Servicio");
 		agrupaciones.put(AGRUPAR_CANAL, "Canal");
 		agrupaciones.put(AGRUPAR_ESTADO, "Estado");
 	}
 	
	private static final String PROCEDIMIENTO_CONSULTA = "{call PROC_GENERARESTADISTICA(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	
	private HashMap<Integer,Integer> mapPermisosUsuario = null;
	private String rolUsuario = null;
	/**
	 * 
	 * @return
	 */
	public String getRolUsuario() {
		return rolUsuario;
	}

	public void setRolUsuario(String rolUsuario) {
		this.rolUsuario = rolUsuario;
	}
	/**
	 * 
	 * @return
	 */
	public HashMap<Integer, Integer> getMapPermisosUsuario() {
		return mapPermisosUsuario;
	}

	public void setMapPermisosUsuario(HashMap<Integer, Integer> mapPermisosUsuario) {
		this.mapPermisosUsuario = mapPermisosUsuario;
	}

	public EstadisticasPlataforma(){

	}
	public EstadisticasPlataforma(Integer aplicacionId,Integer servidorId,Integer canalId,Integer servicioId,Integer estadoId,
			Integer vistaId,java.util.Date fechaDesde,java.util.Date fechaHasta,Integer agruparId){
		if(estadistica!=null){
			estadistica.reset();
		}else{
			estadistica = new EstadisticasBean();
		}
		estadistica.setAplicacionId(aplicacionId);
		estadistica.setServidorId(servidorId);
		estadistica.setCanalId(canalId);
		estadistica.setServicioId(servicioId);
		estadistica.setEstadoId(estadoId);
		estadistica.setVistaId(vistaId);
		estadistica.setFechaDesde(fechaDesde);
		estadistica.setFechaHasta(fechaHasta);
		estadistica.setAgruparId(agruparId);
	}
	/**
	 * 
	 * @param estadisticaBean
	 */
	public EstadisticasPlataforma(EstadisticasBean estadisticaBean){
		this.estadistica = estadisticaBean;
	}
	/**
	 * @param reverse indica la orientaci�n de la tabla
	 * @return 
	 */
	public List<FilaEstadisticaBean> getEstadisticas(boolean reverse) throws BusinessException{
		Connection con = null;
		CallableStatement call = null;
		ResultSet rs = null;
		List<FilaEstadisticaBean> listaFilasEstadisticas = new ArrayList<FilaEstadisticaBean>();
		
		try{
			if(estadistica!=null){
				con = PlataformaMensajeriaUtil.getConexion();
				call = con.prepareCall(PROCEDIMIENTO_CONSULTA);
				call.setInt(1, estadistica.getAplicacionId());//aplicacionId
				call.setInt(2, estadistica.getServidorId());//servidorId;
				call.setInt(3, estadistica.getCanalId());//canalId;
				call.setInt(4, estadistica.getServicioId() != null ? estadistica.getServicioId() : 0);//servicioId;
				call.setInt(5, estadistica.getEstadoId());//estadoId;
				call.setInt(6, estadistica.getVistaId()); //vistaId;
				java.sql.Date fechaDesde = new Date(estadistica.getFechaDesde().getTime());
				java.sql.Date fechaHasta = new Date(estadistica.getFechaHasta().getTime());
				call.setDate(7,fechaDesde );
				call.setDate(8,fechaHasta );
				call.setInt(9,estadistica.getAgruparId());//agruparId
				//TODO: APLICAR FILTRO SEGUIRDAD (SÓLO APLICACIONES ASIGNAS AAL USUARIO).
				StringBuffer sbf = new StringBuffer();
				if(mapPermisosUsuario!=null&&(!PlataformaMensajeriaUtil.isEmpty(rolUsuario)
						&&!rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR))){
					Set<Integer> idAplicaciones = mapPermisosUsuario.keySet();
					Iterator<Integer> itAplicaciones = idAplicaciones.iterator();
					boolean first=true;
					while(itAplicaciones.hasNext()){
					Integer idAplicacion = itAplicaciones.next();
					if(!first){
						sbf.append(",");
					}
					sbf.append(idAplicacion);
					first=false;
					}
					call.setString(10, sbf.toString());
				}else{
					call.setString(10, null);
				}
				call.setString(11, (!PlataformaMensajeriaUtil.isEmpty(estadistica.getDocUsuario())?estadistica.getDocUsuario():null));
				call.setString(12, (!PlataformaMensajeriaUtil.isEmpty(estadistica.getCodSIA())?estadistica.getCodSIA():null));
				call.setString(13, (!PlataformaMensajeriaUtil.isEmpty(estadistica.getCodOrganismo())?estadistica.getCodOrganismo():null));
				call.setString(14, (!PlataformaMensajeriaUtil.isEmpty(estadistica.getCodOrganismoPagador())?estadistica.getCodOrganismoPagador():null));
				call.registerOutParameter(15, OracleTypes.CURSOR);
				call.executeUpdate();
				rs =  (ResultSet)call.getObject(15);//Obtenemos el cursor
				ResultSetMetaData rsmd = rs.getMetaData();
				List<String> nombreColumnas = new ArrayList<String>();
				int numColumns = rsmd.getColumnCount();
				for (int i=1; i<numColumns+1; i++) {
					if (i != 2 && i != 3){
						String columnName = rsmd.getColumnName(i);
						nombreColumnas.add(columnName);
					}
			    }
		
				while(rs.next()){//FILA
					Iterator<String> it = nombreColumnas.iterator();
					FilaEstadisticaBean fila = new FilaEstadisticaBean();
					boolean isGroupColumn = true;
					while(it.hasNext()){//Columnas
						String columna = it.next();
	
						if(isGroupColumn){
				/*@*/		fila.setNombreGrupo(rs.getString(columna));
							fila.setNombreColumnaGrupo(columna);
							isGroupColumn=false;
						}else{
							/**
							 * Definimos las propiedades de la columna
							 */
							//HashMap<String,String> colMap = new HashMap<String,String>();
							ColumnaBean colMap = new ColumnaBean();
							String valor = rs.getString(columna);
							String colParsed = "";
							if(estadistica!=null&&estadistica.getVistaId()!=null&&estadistica.getVistaId()==2){
								colParsed = TituloEstadisticasParser.parseMesAnno(columna);
							}else if(estadistica!=null&&estadistica.getVistaId()!=null&&estadistica.getVistaId()==3){
								colParsed = TituloEstadisticasParser.parseDiaMesAnno(columna);
							}else{
								colParsed = columna;
							}
								
							colMap.setTitulo(colParsed);
							colMap.setValor(valor);
							
							/**
							 * Obtenemos el valor de la columna
							 */
							
							if(valor==null){
								valor = "0";
							}
							fila.addColumnaMap(colMap);					
							fila.addValorColumna(colParsed, valor);
							fila.addNombreColumna(colParsed);
						}
					}
					listaFilasEstadisticas.add(fila);					
				}
			}
		} catch (SQLException e) {
			throw new BusinessException("Error en estadisticas");
		}finally{
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e) {
					throw new BusinessException("Error en estadisticas");
				}
			}
			if(call!=null){
				try {
					call.close();
				} catch (SQLException e) {
					throw new BusinessException("Error en estadisticas");
				}
			}
			if(con!=null){
				try {
					con.clearWarnings();
				} catch (SQLException e) {
					throw new BusinessException("Error en estadisticas");
				}
			}
		}
		if(reverse){
			listaFilasEstadisticas = reverseEstadistica(listaFilasEstadisticas);
		}
		return listaFilasEstadisticas;
	}
	
	public String getVista(String id){
		return vista.get(id);
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public String getAgrupacion(String id){
		return agrupaciones.get(id);
	}	
	/**
	 * 
	 * @return
	 */
	public EstadisticasBean getEstadistica() {
		return estadistica;
	}
	
	public static List<KeyValueObject> getComboVista(){
		Set<String>listaClaves= vista.keySet();
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        Iterator<String> itClaves = listaClaves.iterator();
        while(itClaves.hasNext()){
        	String clave = itClaves.next();
        	option = new KeyValueObject();
    		option.setCodigo(clave);
    		option.setDescripcion(vista.get(clave));
    		result.add(option);
        }
        return result;
	}
	/**
	 * Inverte el orden de las tablas
	 * @param listaEstadistica
	 * @return
	 */
	public List<FilaEstadisticaBean> reverseEstadistica(List<FilaEstadisticaBean> listaEstadistica){
		ArrayList<FilaEstadisticaBean> listaRevertida = new ArrayList<FilaEstadisticaBean>();
		ArrayList<String> nombreColumnasNuevo = new ArrayList<String>();
		List<ColumnaBean> listadoColumnasAFilas = new ArrayList<ColumnaBean>();
		for (FilaEstadisticaBean filaEstadisticaBean : listaEstadistica) {
			nombreColumnasNuevo.add(filaEstadisticaBean.getNombreGrupo());
			if(listadoColumnasAFilas.size()==0){
				listadoColumnasAFilas = filaEstadisticaBean.getListaColumnasMap();
			}
		}
		
		for (ColumnaBean colNueva : listadoColumnasAFilas) {
			FilaEstadisticaBean filaEstadistica = new FilaEstadisticaBean();
			filaEstadistica.setNombreColumnas(nombreColumnasNuevo);
			filaEstadistica.setNombreGrupo(colNueva.getTitulo());
			for (FilaEstadisticaBean filaEstadisticaBean : listaEstadistica) {
				filaEstadistica.addValorColumna(filaEstadisticaBean.getNombreGrupo(), filaEstadisticaBean.getValorColumna().get(colNueva.getTitulo()));
				ColumnaBean col = new ColumnaBean();
				col.setTitulo(filaEstadisticaBean.getNombreGrupo());
				filaEstadistica.addColumnaMap(col);
			}
			listaRevertida.add(filaEstadistica);
		}
		return listaRevertida;
	}
	/**
	 * 
	 * @return
	 */
	public static List<KeyValueObject> getComboAgrupaciones(){
		Set<String>listaClaves= agrupaciones.keySet();
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        Iterator<String> itClaves = listaClaves.iterator();
        while(itClaves.hasNext()){
        	String clave = itClaves.next();
        	option = new KeyValueObject();
    		option.setCodigo(clave);
    		option.setDescripcion(agrupaciones.get(clave));
    		result.add(option);
        }
        return result;
	}


	public void setEstadistica(EstadisticasBean estadistica) {
		this.estadistica = estadistica;
	}
	
}
