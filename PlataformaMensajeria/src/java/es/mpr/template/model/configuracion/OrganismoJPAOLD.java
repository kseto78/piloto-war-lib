package es.mpr.template.model.configuracion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.map.j2ee.auditoria.ifaces.Audit;
import com.map.j2ee.base.jpa.AbstractBaseJPAEntity;

/*
 * Diferentes implementaciones de la JPA pueden requerir diferencias en las NamedQuerys.
 * Por ejemplo la siguiente Namedquery debe ser expresada diferente si nuestra implementaci�n es openjpa o hibernate:
 * Con Open JPA -> @NamedQuery(name = "selectOrganismoJPA", query = "SELECT m FROM OrganismoJPA m WHERE upper (m.nombre) like :nombre")
 * Con Hibernate -> @NamedQuery(name = "selectOrganismoJPA", query = "SELECT m FROM OrganismoJPA m WHERE upper (m.nombre) like ?")
 */

/**
 *  <p>Clase de entidad con las anotaciones JPA necesarias.
 *  
 *  <p>
 *  Representa la tabla ORGANISMOS de la base de datos
 *  
 *  @author Altran
 */
@Entity
@Table(name = "TEMP_ORGANISMO")

@NamedQueries({
@NamedQuery(name = "selectOrganismo_orderbyId_ASC2", query = "SELECT e FROM OrganismoJPAOLD e order by e.id ASC"),
@NamedQuery(name = "selectOrganismo_orderbyNombre_ASC2", query = "SELECT e FROM OrganismoJPAOLD e order by e.nombre ASC"),
@NamedQuery(name = "selectOrganismo_orderbyRol_ASC2", query = "SELECT e FROM OrganismoJPAOLD e order by e.rol ASC"),
@NamedQuery(name = "selectOrganismo_orderbyPadre_ASC2", query = "SELECT e FROM OrganismoJPAOLD e order by e.organismoPadre ASC"),
@NamedQuery(name = "selectOrganismo_orderbyId_DESC2", query = "SELECT e FROM OrganismoJPAOLD e order by e.id DESC"),
@NamedQuery(name = "selectOrganismo_orderbyNombre_DESC2", query = "SELECT e FROM OrganismoJPAOLD e order by e.nombre DESC"),
@NamedQuery(name = "selectOrganismo_orderbyRol_DESC2", query = "SELECT e FROM OrganismoJPAOLD e order by e.rol DESC"),
@NamedQuery(name = "selectOrganismo_orderbyPadre_DESC2", query = "SELECT e FROM OrganismoJPAOLD e order by e.organismoPadre DESC"),
@NamedQuery(name = "selectOrganismoByName_orderbyId_ASC2", 
			query = "SELECT e FROM OrganismoJPAOLD e WHERE upper (e.nombre) like ? order by e.id ASC"),
@NamedQuery(name = "selectOrganismoByName_orderbyNombre_ASC2", 
			query = "SELECT e FROM OrganismoJPAOLD e WHERE upper (e.nombre) like ? order by e.nombre ASC"),
@NamedQuery(name = "selectOrganismoByName_orderbyRol_ASC2", 
			query = "SELECT e FROM OrganismoJPAOLD e WHERE upper (e.nombre) like ? order by e.rol ASC"),
@NamedQuery(name = "selectOrganismoByName_orderbyPadre_ASC2", 
			query = "SELECT e FROM OrganismoJPAOLD e WHERE upper (e.nombre) like ? order by e.organismoPadre ASC"),
@NamedQuery(name = "selectOrganismoByName_orderbyId_DESC2", 
			query = "SELECT e FROM OrganismoJPAOLD e WHERE upper (e.nombre) like ? order by e.id DESC"),
@NamedQuery(name = "selectOrganismoByName_orderbyNombre_DESC2", 
			query = "SELECT e FROM OrganismoJPAOLD e WHERE upper (e.nombre) like ? order by e.nombre DESC"),
@NamedQuery(name = "selectOrganismoByName_orderbyRol_DESC2", 
			query = "SELECT e FROM OrganismoJPAOLD e WHERE upper (e.nombre) like ? order by e.rol DESC"),
@NamedQuery(name = "selectOrganismoByName_orderbyPadre_DESC2", 
			query = "SELECT e FROM OrganismoJPAOLD e WHERE upper (e.nombre) like ? order by e.organismoPadre DESC"),
@NamedQuery(name = "selectOrganismoJPA2", query = "SELECT m FROM OrganismoJPAOLD m WHERE upper (m.nombre) like ?"),
@NamedQuery(name = "selectOrganismo_count2", query = "SELECT count(e) FROM OrganismoJPAOLD e"),
@NamedQuery(name = "selectOrganismoByName_count2", query = "SELECT count(e) FROM OrganismoJPAOLD e WHERE upper (e.nombre) like ?")
})
public class OrganismoJPAOLD extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@SequenceGenerator(name="organismo", sequenceName="ORGANISMO_SEQ", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="organismo")
	protected Long id;

	
	@Column(name = "NOMBRE", nullable = false)
	protected String nombre = null;
	@Column(name = "ROL", nullable = true)
	protected String rol = null;
	@Column(name = "organismoPadre", nullable = true)
	protected Long organismoPadre = null;

	public OrganismoJPAOLD() {
		super();
		this.id = null;
		this.nombre = null;
		this.rol = null;
		this.organismoPadre = null;
	}

	/**
	 *
	 * @return Nombre del organismo
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * @return Rol del organismo
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * 
	 * @return Id del organismo padre
	 */
	public Long getOrganismoPadre() {
		return organismoPadre;
	}

	/**
	 * Establece la propiedad nombre
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Establece la propiedad rol
	 * 
	 * @param rol
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	/**
	 * Establece la propiedad organismoPadre
	 * 
	 * @param organismoPadre
	 */
	public void setOrganismoPadre(Long organismoPadre) {
		this.organismoPadre = organismoPadre;
	}

	/**
	 * Devuelve el id del organismo
	 * 
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el id del organismo
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	

	
}
