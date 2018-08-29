package es.mpr.plataformamensajeria.estadisticas.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * The Class FilaEstadisticaBean.
 */
public class FilaEstadisticaBean implements Audit {

    /**  nombre grupo. */
    private String nombreGrupo;
    
    /**  nombre columnas. */
    private List<String> nombreColumnas;
    
    /**  lista columnas map. */
    //private List<HashMap<String,String>> listaColumnasMap;
    private List<ColumnaBean> listaColumnasMap = null;
 	
	 /**  valor columna. */
	 private HashMap<String, String> valorColumna;
	
	/**  valor count. */
	private int valorCount;
	
	/**  valor total count. */
	private int valorTotalCount;
	
	/**  columna count. */
	private int columnaCount;
	
	/**  nombre columna grupo. */
	private String nombreColumnaGrupo;
	
		/**
		 * Constructor de fila estadistica bean.
		 */
		public FilaEstadisticaBean(){
    	super();
    	reset();
    }
    
	/**
	 * Reset.
	 */
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
	 * Añade los valores de una columna.
	 *
	 * @param columna the columna
	 * @param valor the valor
	 */
	public void addValorColumna(String columna,String valor){
		valorColumna.put(columna, valor);
	}
	
	/**
	 * Registra el nombre de una columna para luego iterarla y obtener los valores.
	 *
	 * @param nombreColumna the nombre columna
	 */
	public void addNombreColumna(String nombreColumna){
		nombreColumnas.add(nombreColumna);
	}
	
	/**
	 * Añade la definicion de una columna.
	 *
	 * @param columnaMap the columna map
	 */
	public void addColumnaMap(ColumnaBean columnaMap){
		listaColumnasMap.add(columnaMap);
	}
	
	
	
	/**
	 * Obtener titulo.
	 *
	 * @return titulo
	 */
	public String getTitulo(){
		String column = nombreColumnas.get(columnaCount);
		columnaCount++;
		return column;
	}
	
	/**
	 * Obtener valor.
	 *
	 * @return valor
	 */
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
	
	/**
	 * Obtener valor total.
	 *
	 * @return valor total
	 */
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
	
	
	
	/**
	 * Obtener lista columnas map.
	 *
	 * @return lista columnas map
	 */
	public List<ColumnaBean> getListaColumnasMap() {
		return new ArrayList<ColumnaBean>(listaColumnasMap);
	}

	/**
	 * Modificar lista columnas map.
	 *
	 * @param listaColumnasMap new lista columnas map
	 */
	public void setListaColumnasMap(List<ColumnaBean> listaColumnasMap) {
		this.listaColumnasMap = new ArrayList<ColumnaBean>(listaColumnasMap);
	}

	/**
	 * Obtener nombre grupo.
	 *
	 * @return nombre grupo
	 */
	public String getNombreGrupo() {
		return nombreGrupo;
	}

	/**
	 * Modificar nombre grupo.
	 *
	 * @param nombreGrupo new nombre grupo
	 */
	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}

	/**
	 * Obtener nombre columnas.
	 *
	 * @return nombre columnas
	 */
	public List<String> getNombreColumnas() {
		return new ArrayList<String>(nombreColumnas);
	}

	/**
	 * Modificar nombre columnas.
	 *
	 * @param nombreColumnas new nombre columnas
	 */
	public void setNombreColumnas(List<String> nombreColumnas) {
		this.nombreColumnas = new ArrayList<String>(nombreColumnas);
	}

	/**
	 * Obtener valor columna.
	 *
	 * @return valor columna
	 */
	public HashMap<String, String> getValorColumna() {
		return valorColumna;
	}

	/**
	 * Modificar valor columna.
	 *
	 * @param valorColumna the valor columna
	 */
	public void setValorColumna(HashMap<String, String> valorColumna) {
		this.valorColumna = valorColumna;
	}

	/**
	 * Obtener valor count.
	 *
	 * @return valor count
	 */
	public int getValorCount() {
		return valorCount;
	}

	/**
	 * Modificar valor count.
	 *
	 * @param valorCount new valor count
	 */
	public void setValorCount(int valorCount) {
		this.valorCount = valorCount;
	}

	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Modificar nombre columna grupo.
	 *
	 * @param columna new nombre columna grupo
	 */
	public void setNombreColumnaGrupo(String columna) {
		this.nombreColumnaGrupo = columna;
		
	}
	
	/**
	 * Obtener nombre columna grupo.
	 *
	 * @return nombre columna grupo
	 */
	public String getNombreColumnaGrupo(){
		return this.nombreColumnaGrupo;
	}


	/**
	 * Obtener valor total count.
	 *
	 * @return valor total count
	 */
	public int getValorTotalCount() {
		return valorTotalCount;
	}

	/**
	 * Modificar valor total count.
	 *
	 * @param valorTotalCount new valor total count
	 */
	public void setValorTotalCount(int valorTotalCount) {
		this.valorTotalCount = valorTotalCount;
	}

}
