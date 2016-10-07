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
 *  Representa la vista de informes de servicios agrupados por codigo organismo pagador
 *  
 *  @author jgonzvil
 */
@Entity
@Table(name = "VIEW_INFORMES_SERVICIOS_CODCOP")
@NamedQueries({         
	@NamedQuery(name = "selectViewInformesServiciosCodOrgPagadorJPA", query = "SELECT e FROM ViewInformesServiciosCodOrgPagadorJPA e")})

public class ViewInformesServiciosCodOrgPagadorJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewInformesServiciosCodOrgPagadorJPA() {
		this.servicioId=null;
		this.anno=null;
		this.mes=null;
		this.codOrganismoPagador=null;
		this.numTotal=null;
	}
	
	@Id
	@Column(name="SERVICIOID")
	private Integer servicioId;
	@Column(name="ANNO")
	private Integer anno;
	@Column(name="MES")
	private Integer mes;
	@Column(name="CODORGANISMOPAGADOR")
	private String codOrganismoPagador;
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

	public String getCorOrganismoPagador() {
		return codOrganismoPagador;
	}

	public void setCorOrganismoPagador(String codOrganismoPagador) {
		this.codOrganismoPagador = codOrganismoPagador;
	}

	public Integer getNumTotal() {
		return numTotal;
	}

	public void setNumTotal(Integer numTotal) {
		this.numTotal = numTotal;
	}
	
	
}
