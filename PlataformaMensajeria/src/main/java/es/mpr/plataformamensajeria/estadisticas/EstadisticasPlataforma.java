package es.mpr.plataformamensajeria.estadisticas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.util.KeyValueObject;

import es.minhap.plataformamensajeria.iop.dao.QueryExecutorEstadisticas;
import es.mpr.plataformamensajeria.estadisticas.bean.ColumnaBean;
import es.mpr.plataformamensajeria.estadisticas.bean.EstadisticasBean;
import es.mpr.plataformamensajeria.estadisticas.bean.FilaEstadisticaBean;
import es.mpr.plataformamensajeria.servicios.impl.ServicioParametroServidorImpl;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.plataformamensajeria.util.TituloEstadisticasParser;
/**
 * Clase encargada de la generación de las estadísticas.
 * @author i-nercya
 *
 */
@Service("estadisticasPlataforma")
public class EstadisticasPlataforma {
	
	private static Logger logger = Logger.getLogger(ServicioParametroServidorImpl.class);
	
	private EstadisticasBean estadistica = null;
	
	@Resource(name="QueryExecutorEstadisticasImpl")
	private QueryExecutorEstadisticas queryExecutorEstadisticas;
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
 	
	private HashMap<Integer,Integer> mapPermisosUsuario = null;
	private String rolUsuario = null;
	public EstadisticasPlataforma(){}
	
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
	 * @param estadisticaBean 
	 * @param reverse indica la orientaci�n de la tabla
	 * @return 
	 */
	///MIGRADO
	public List<FilaEstadisticaBean> getEstadisticas(EstadisticasBean estadisticaBean, boolean reverse) throws BusinessException{
		List<FilaEstadisticaBean> listaFilasEstadisticas = new ArrayList<FilaEstadisticaBean>();
		
		if(estadisticaBean != null){
			
			StringBuffer aplicaciones = new StringBuffer();
			if(mapPermisosUsuario!=null&&(!PlataformaMensajeriaUtil.isEmpty(rolUsuario)
					&&!rolUsuario.equals(PlataformaMensajeriaUtil.ROL_ADMINISTRADOR))){
				Set<Integer> idAplicaciones = mapPermisosUsuario.keySet();
				Iterator<Integer> itAplicaciones = idAplicaciones.iterator();
				boolean first=true;
				while(itAplicaciones.hasNext()){
				Integer idAplicacion = itAplicaciones.next();
				if(!first){
					aplicaciones.append(",");
				}
				aplicaciones.append(idAplicacion);
				first=false;
				}
			}
			
			es.minhap.plataformamensajeria.iop.beans.EstadisticasBean est = new es.minhap.plataformamensajeria.iop.beans.EstadisticasBean(); 
			est = getEstadisticasBean(estadisticaBean,est);
			
			List<Object[]> rows = queryExecutorEstadisticas.getEstadisticas(est, aplicaciones);
			
			List<String> nombreColumnas = new ArrayList<String>();
			
			nombreColumnas.add(null);
			
			Calendar fechaDesde = Calendar.getInstance();
			Calendar fechaHasta = Calendar.getInstance();
			
			fechaDesde.setTime(estadisticaBean.getFechaDesde());
			fechaHasta.setTime(estadisticaBean.getFechaHasta());
			fechaHasta.add(Calendar.DAY_OF_MONTH, 1);
			
			//Se genera el listado de las columnas.
			if(estadisticaBean.getVistaId() == 1){
				while (fechaDesde.getTime().before(fechaHasta.getTime())){
					Integer agno = fechaDesde.get(Calendar.YEAR);
		        	fechaDesde.add(Calendar.YEAR, 1);
		        	nombreColumnas.add(agno.toString());
		        }
			} else if(estadisticaBean.getVistaId() == 2){
				while (fechaDesde.getTime().before(fechaHasta.getTime())){
					String columnName = (fechaDesde.get(Calendar.MONTH)+1)
		            		+ "_"
		            		+ fechaDesde.get(Calendar.YEAR);
		        	fechaDesde.add(Calendar.MONTH, 1);
		        	nombreColumnas.add(columnName);
		        }
			}else if(estadisticaBean.getVistaId() == 3){
				while (fechaDesde.getTime().before(fechaHasta.getTime())){
					String columnName = fechaDesde.get(Calendar.DAY_OF_MONTH)
		            		+ "_"
		            		+ (fechaDesde.get(Calendar.MONTH)+1)
		            		+ "_"
		            		+ fechaDesde.get(Calendar.YEAR);
		        	fechaDesde.add(Calendar.DAY_OF_YEAR, 1);
		        	nombreColumnas.add(columnName);
		        }
			}
			
			LinkedHashMap<String, List<ColumnaBean>> valores = new LinkedHashMap<String, List<ColumnaBean>>();
			
			for(Object[] obj: rows){
				//En el caso de que no se haya añadido ya una fila con un nombre concreto de servicio.
				if(!valores.containsKey((String) obj[0])){
					Iterator<String> it = nombreColumnas.iterator();
					List<ColumnaBean> dias = new ArrayList<ColumnaBean>();
					ColumnaBean colMap;
					while(it.hasNext()){//COLUMNA
						String columna = it.next();
						String valor;
						if(columna!=null){
							colMap = new ColumnaBean();
							if(obj[4] != null){
								String[] columnaS = null;
								String[] objColumnaS = null;
								if(estadisticaBean.getVistaId() != 1){
									columnaS = columna.split("_");
									objColumnaS = ((String) obj[3]).split("_");
								}
								if(estadisticaBean.getVistaId() == 1){
									if(columna.equals(((BigDecimal) obj[3]).toString())){
										valor = ((BigDecimal) obj[4]).toString();
									} else {
										valor = "0";
									}
								} else if(estadisticaBean.getVistaId() == 2){
									if(columnaS[0].equals(objColumnaS[0]) && columnaS[1].equals(objColumnaS[1])){
										valor = ((BigDecimal) obj[4]).toString();
									} else {
										valor = "0";
									}
								} else if(estadisticaBean.getVistaId() == 3){
									if(columnaS[0].equals(objColumnaS[0]) && columnaS[1].equals(objColumnaS[1]) 
											&& columnaS[2].equals(objColumnaS[2])){
										valor = ((BigDecimal) obj[4]).toString();
									} else {
										valor = "0";
									}
								} else {
									valor = "0";
								}
									
							} else {
								valor = "0";
							}
												
							colMap.setTitulo(columna);
							colMap.setValor(valor);
							
							dias.add(colMap);
						}
					}
					
					valores.put((String) obj[0], dias);
				} else {
					//Si el servicio ya ha sido añadido en la lista anteriormente.
					List<ColumnaBean> diasRec = valores.get((String) obj[0]);
					
					for(int i=0;i<diasRec.size();i++){
//						if(diasRec.get(i).getValor().equals("0")){
							String columnaRec = diasRec.get(i).getTitulo();
							String fecha = (String) obj[3];
							if(fecha!=null && columnaRec != null){
								if(obj[4] != null){
									String[] columnaS = null;
									String[] objColumnaS = null;
									if(estadisticaBean.getVistaId() != 1){
										columnaS = columnaRec.split("_");
										objColumnaS = fecha.split("_");
									}
									if(estadisticaBean.getVistaId() == 1){
										if(fecha.equals(((BigDecimal) obj[3]).toString())){
											Integer v = Integer.parseInt(diasRec.get(i).getValor());
											diasRec.get(i).setValor(String.valueOf(((BigDecimal) obj[4]).intValue() + v));
										} 
									} else if(estadisticaBean.getVistaId() == 2){
										if(columnaS[0].equals(objColumnaS[0]) && columnaS[1].equals(objColumnaS[1])){
											Integer v = Integer.parseInt(diasRec.get(i).getValor());
											diasRec.get(i).setValor(String.valueOf(((BigDecimal) obj[4]).intValue() + v));
										} 
									} else if(estadisticaBean.getVistaId() == 3){
										if(columnaS[0].equals(objColumnaS[0]) && columnaS[1].equals(objColumnaS[1]) 
												&& columnaS[2].equals(objColumnaS[2])){
											Integer v = Integer.parseInt(diasRec.get(i).getValor());
											diasRec.get(i).setValor(String.valueOf(((BigDecimal) obj[4]).intValue() + v));
										} 
									} 
								}
							}
//						}
					}
					
					valores.put((String) obj[0], diasRec);
				}
			}
			
			//Se genera la lista final de datos para mostrar en la vista
			for(Entry<String, List<ColumnaBean>> valor: valores.entrySet()){
				FilaEstadisticaBean fila = new FilaEstadisticaBean();
				boolean isGroupColumn = true;
				String colParsed = "";
				
				for(ColumnaBean columna: valor.getValue()){
					
					if(isGroupColumn){
						colParsed = getColParsed(columna, estadisticaBean);
						fila.setNombreGrupo(valor.getKey());
						isGroupColumn=false;
					}
					
					fila.addColumnaMap(columna);
					
					if(reverse){
						colParsed = columna.getTitulo();
					} else {
						colParsed = getColParsed(columna, estadisticaBean);
					}
					
					fila.addValorColumna(colParsed, columna.getValor());
					fila.addNombreColumna(colParsed);
				}
				listaFilasEstadisticas.add(fila);
			}
			
		}
		
		if(reverse){
			return reverseEstadistica(listaFilasEstadisticas, estadisticaBean);
		} else {
			return listaFilasEstadisticas;
		}
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
	public List<FilaEstadisticaBean> reverseEstadistica(List<FilaEstadisticaBean> listaEstadistica,
			EstadisticasBean estadisticaBean){
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
			filaEstadistica.setNombreGrupo(getColParsed(colNueva, estadisticaBean));
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

	es.minhap.plataformamensajeria.iop.beans.EstadisticasBean getEstadisticasBean(EstadisticasBean estadistica,
			es.minhap.plataformamensajeria.iop.beans.EstadisticasBean est) throws BusinessException{

		est.setAgruparId(estadistica.getAgruparId());
		est.setVistaId(estadistica.getVistaId());
		est.setFechaDesde(estadistica.getFechaDesde());
		est.setFechaHasta(estadistica.getFechaHasta());
		est.setAplicacionId(estadistica.getAplicacionId());
		est.setServidorId(estadistica.getServidorId());
		est.setServicioId(estadistica.getServicioId());
		est.setCanalId(estadistica.getCanalId());
		est.setEstadoId(estadistica.getEstadoId());
		est.setDocUsuario(estadistica.getDocUsuario());
		est.setCodSIA(estadistica.getCodSIA());
		est.setCodOrganismo(estadistica.getCodOrganismo());
		est.setCodOrganismoPagador(estadistica.getCodOrganismoPagador());
		
		return est;
	}

	public String getColParsed(ColumnaBean columna, EstadisticasBean estadisticaBean){
		String colParsed = "";
		
		if(estadisticaBean!=null&&estadisticaBean.getVistaId()!=null&&estadisticaBean.getVistaId()==2){
			colParsed = TituloEstadisticasParser.parseMesAnno(columna.getTitulo());
		}else if(estadisticaBean!=null&&estadisticaBean.getVistaId()!=null&&estadisticaBean.getVistaId()==3){
			colParsed = TituloEstadisticasParser.parseDiaMesAnno(columna.getTitulo());
		}else{
			colParsed = columna.getTitulo();
		}
		
		return colParsed;
	}
	
	public void setEstadistica(EstadisticasBean estadistica) {
		this.estadistica = estadistica;
	}
	
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
}
