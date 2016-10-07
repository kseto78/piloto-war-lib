package es.mpr.plataformamensajeria.model.views;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.map.j2ee.base.jpa.AbstractBaseJPAEntity;

/**
 *  <p>Clase de entidad con las anotaciones JPA necesarias.
 *  
 *  <p>
 *  Representa la vista de informes de servicios agrupados por codigo SIA
 *  
 *  @author jgonzvil
 */
@Entity
@Table(name = "VIEW_INFORMES_SERVICIOS_CODSIA")
@NamedQueries({         
	@NamedQuery(name = "selectViewInformesServiciosCodSiaJPA", query = "SELECT e FROM ViewInformesServiciosCodSiaJPA e")})

public class ViewInformesServiciosCodSiaJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewInformesServiciosCodSiaJPA() {
		this.servicioId=null;
		this.anno=null;
		this.mes=null;
		this.codSia=null;
		this.numTotal=null;
	}
	
	@Id
	@Column(name="SERVICIOID")
	private Integer servicioId;
	@Column(name="ANNO")
	private Integer anno;
	@Column(name="MES")
	private Integer mes;
	@Column(name="CODSIA")
	private String codSia;
	@Column(name="NUMTOTAL")
	private Integer numTotal;
	
	@Override
	public Object getId() {
		return servicioId;
	}
	
	public void setId(Integer id){
		this.servicioId=id;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public String getCodSia() {
		return codSia;
	}

	public void setCodSia(String codSia) {
		this.codSia = codSia;
	}

	public Integer getNumTotal() {
		return numTotal;
	}

	public void setNumTotal(Integer numTotal) {
		this.numTotal = numTotal;
	}
	
	
}
