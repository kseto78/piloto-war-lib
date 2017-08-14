package es.mpr.plataformamensajeria.estadisticas.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.map.j2ee.auditoria.ifaces.Audit;

public class FilaEstadisticaBean implements Audit {

    private String nombreGrupo;
    private List<String> nombreColumnas;
    //private List<HashMap<String,String>> listaColumnasMap;
    private List<ColumnaBean> listaColumnasMap = null;
 	private HashMap<String, String> valorColumna;
	private int valorCount;
	private int valorTotalCount;
	private int columnaCount;
	private String nombreColumnaGrupo;
	
		public FilaEstadisticaBean(){
    	super();
    	reset();
    }
    
	public void reset(){
		this.nombreGrupo = null;
		this.nombreColumnas = new ArrayList<String>();
		this.valorColumna = new HashMap<String,String>();
		this.listaColumnasMap = new ArrayList<ColumnaBean>();
		this.valorCount = 0;
		this.columnaCount =0;
		this.nombreColumnaGrupo = null;
	}
	/**
	 * Añade los valores de una columna
	 * @param columna
	 * @param valor
	 */
	public void addValorColumna(String columna,String valor){
		valorColumna.put(columna, valor);
	}
	/**
	 * Registra el nombre de una columna para luego iterarla y obtener los valores
	 * @param nombreColumna
	 */
	public void addNombreColumna(String nombreColumna){
		nombreColumnas.add(nombreColumna);
	}
	/**
	 * Añade la definicion de una columna
	 * @param columnaMap
	 */
	public void addColumnaMap(ColumnaBean columnaMap){
		listaColumnasMap.add(columnaMap);
	}
	
	
	
	public String getTitulo(){
		String column = nombreColumnas.get(columnaCount);
		columnaCount++;
		return column;
	}
	public Object getValor(){
		Integer intResult = new Integer(0);
		//int numColumnas=nombreColumnas.size()-1;
		
		String column = nombreColumnas.get(valorCount);
		String valor = valorColumna.get(column);
		valorCount++;
		if(valorCount==nombreColumnas.size()){
			valorCount=0;
		}
		try{
			intResult = Integer.parseInt(valor);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return intResult;
		
		
	}
	public Object getValorTotal(){
		Long parcial = new Long(0);
		//int numColumnas=nombreColumnas.size()-1;
		Iterator<String> it = nombreColumnas.iterator();
		while(it.hasNext()){
			String val = it.next();
			String valor = valorColumna.get(val);
			valorTotalCount++;
			long vLong = Long.parseLong(valor);
			parcial+=vLong;
		}

		try{
			//intResult = Integer.parseInt(valor);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return parcial;
		
		
	}
	
	
	
	public List<ColumnaBean> getListaColumnasMap() {
		return new ArrayList<ColumnaBean>(listaColumnasMap);
	}

	public void setListaColumnasMap(List<ColumnaBean> listaColumnasMap) {
		this.listaColumnasMap = new ArrayList<ColumnaBean>(listaColumnasMap);
	}

	public String getNombreGrupo() {
		return nombreGrupo;
	}

	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}

	public List<String> getNombreColumnas() {
		return new ArrayList<String>(nombreColumnas);
	}

	public void setNombreColumnas(List<String> nombreColumnas) {
		this.nombreColumnas = new ArrayList<String>(nombreColumnas);
	}

	public HashMap<String, String> getValorColumna() {
		return valorColumna;
	}

	public void setValorColumna(HashMap<String, String> valorColumna) {
		this.valorColumna = valorColumna;
	}

	public int getValorCount() {
		return valorCount;
	}

	public void setValorCount(int valorCount) {
		this.valorCount = valorCount;
	}

	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void setNombreColumnaGrupo(String columna) {
		this.nombreColumnaGrupo = columna;
		
	}
	public String getNombreColumnaGrupo(){
		return this.nombreColumnaGrupo;
	}

}
