package es.mpr.template.model.configuracion;

import com.map.j2ee.base.jpa.AbstractBaseJPAEntity;
import java.io.Serializable;

import javax.persistence.*;

/**
 * @author juanantonio.caro
 * Clase anotada bajo la especificación JPA
 * Representa a la tabla TEMP_UnidadOrganizacional
 */


@Entity
//@Table(name="TEMP_UNIDADORGANIZACIONAL", schema="FW3")
@Table(name="TEMP_UNIDADORGANIZACIONAL")

/*
 * Diferentes implementaciones de la JPA pueden requerir diferencias en las NamedQuerys.
 * Por ejemplo la siguiente Namedquery debe ser expresada diferente si nuestra implementaci�n es openjpa o hibernate:
 * Con Open JPA -> @NamedQuery(name = "selectUnidadOrgz", query = "SELECT m FROM UnidadOrganizacionalJPA m WHERE upper (m.nombre) like :nombre ")
 * Con Hibernate -> @NamedQuery(name = "selectUnidadOrgz", query = "SELECT m FROM UnidadOrganizacionalJPA m WHERE upper (m.nombre) like ? ")
 */

@NamedQuery(name = "selectUnidadOrgz", query = "SELECT m FROM UnidadOrganizacionalJPA m WHERE upper (m.nombre) like :nombre ")
public class UnidadOrganizacionalJPA extends AbstractBaseJPAEntity implements Serializable{

	
	private static final long serialVersionUID = 8878168880518544021L;
	/**
	 * ID
	 */
	@Id
	//@SequenceGenerator(name="unidad", sequenceName="FW3.UNIDADORG_SEQ", allocationSize=1,initialValue=1)
	@SequenceGenerator(name="unidad", sequenceName="UNIDADORG_SEQ", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="unidad")
	protected Long id;
	/**
     * Nombre de la Unidad
     */
	@Column(name="NOMBRE", nullable=false)
	protected String nombre;

    /**
     * Codigo de Comunidad Autonoma
     */
	@Column(name="COMUNIDAD", nullable=false)
	protected String comunidad;

    /**
     * Codigo de la Provincia de la Unidad
     */
	@Column(name="PROVINCIA", nullable=false)
	protected String provincia;

    /**
     * Localidad de la unidad
     */
	@Column(name="LOCALIDAD", nullable=false)
	protected String localidad;

    /**
     * Constructor por defecto
     */
    public UnidadOrganizacionalJPA() {
    }

    /**
     * @return Returns the nombre.
     * @hibernate.property not-null="true"
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return Returns the comunidad.
     * @hibernate.property not-null="true" length = "5"
     */
    public String getComunidad() {
        return comunidad;
    }

    /**
     * @return Returns the provincia.
     * @hibernate.property not-null="true" length = "5"
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     *
     * @return Returns the localidad.
     * @hibernate.property not-null="true"
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * @param nombre The nombre to set.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param localidad The localidad to set.
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * @param comunidad The comunidad to set.
     */
    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    /**
     * @param provincia The provincia to set.
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
