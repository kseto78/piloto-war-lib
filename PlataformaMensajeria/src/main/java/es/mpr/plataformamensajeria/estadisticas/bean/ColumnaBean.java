package es.mpr.plataformamensajeria.estadisticas.bean;

import com.map.j2ee.auditoria.ifaces.Audit;

public class ColumnaBean implements Audit {

    private String titulo;
    private String valor;
    private String sortable;
	private String format;
		public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

		public String getSortable() {
		return sortable;
	}

	public void setSortable(String sortable) {
		this.sortable = sortable;
	}

		public ColumnaBean(){
    	super();
    	reset();
    }
    
	public void reset(){
		this.titulo = null;
		this.valor = null;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String obtenerXML() {
		// TODO Auto-generated method stub
		return null;
	}

}
