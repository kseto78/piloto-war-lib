package es.mpr.plataformamensajeria.estadisticas.bean;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * Clase ColumnaBean.
 */
public class ColumnaBean implements Audit {

    /**  titulo. */
    private String titulo;
    
    /**  valor. */
    private String valor;
    
    /**  sortable. */
    private String sortable;
	
	/**  format. */
	private String format;
		
		/**
		 * Obtener format.
		 *
		 * @return format
		 */
		public String getFormat() {
		return format;
	}

	/**
	 * Modificar format.
	 *
	 * @param format new format
	 */
	public void setFormat(String format) {
		this.format = format;
	}

		/**
		 * Obtener sortable.
		 *
		 * @return sortable
		 */
		public String getSortable() {
		return sortable;
	}

	/**
	 * Modificar sortable.
	 *
	 * @param sortable new sortable
	 */
	public void setSortable(String sortable) {
		this.sortable = sortable;
	}

		/**
		 * Constructor de columna bean.
		 */
		public ColumnaBean(){
    	super();
    	reset();
    }
    
	/**
	 * Reset.
	 */
	public void reset(){
		this.titulo = null;
		this.valor = null;
	}

	/**
	 * Obtener titulo.
	 *
	 * @return titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Modificar titulo.
	 *
	 * @param titulo new titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Obtener valor.
	 *
	 * @return valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Modificar valor.
	 *
	 * @param valor new valor
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

	/* (non-Javadoc)
	 * @see com.map.j2ee.auditoria.ifaces.Audit#obtenerXML()
	 */
	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

}
