package es.mpr.plataformamensajeria.beans;

import java.io.Serializable;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>
 * Representa los envios de tipo email
 * <p>
 * 
 * 
 * @author i-nercya
 */
public class DetalleLoteBean implements Audit, Serializable {

	private static final long serialVersionUID = -7720405961205898287L;



	public DetalleLoteBean() {
		this.nombreAplicacion = null;
		this.nombreServicio = null;
		this.nombreLoteEnvio = null;
		this.idLoteEnvio = null;
		
	}

	private String nombreAplicacion;

	private String nombreServicio;

	private String nombreLoteEnvio;

	private Integer idLoteEnvio;
	


	@Override
	public String obtenerXML() {
		return null;
	}

	public String getNombreAplicacion() {
		return nombreAplicacion;
	}

	public void setNombreAplicacion(String nombreAplicacion) {
		this.nombreAplicacion = nombreAplicacion;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public String getNombreLoteEnvio() {
		return nombreLoteEnvio;
	}

	public void setNombreLoteEnvio(String nombreLoteEnvio) {
		this.nombreLoteEnvio = nombreLoteEnvio;
	}

	public Integer getIdLoteEnvio() {
		return idLoteEnvio;
	}

	public void setIdLoteEnvio(Integer idLoteEnvio) {
		this.idLoteEnvio = idLoteEnvio;
	}

}
