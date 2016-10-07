package es.mpr.template.beans;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * <p>Clase que representa un organismo para la capa de presentaci&oacute;n
 * 
 * @author Altran
 *
 */
public class UnidadOrganizacionalBean {

	/**
	 * ID
	 */
	@Id
	@SequenceGenerator(name="unidad", sequenceName="UNIDADORG_SEQ", allocationSize=1,initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="unidad")
	
	protected Long id;
	
	/**
     * Nombre de la Unidad
     */
	@Column(name="NOMBRE", nullable=false)
	protected String nombre;

    /**
     * Comunidad Aut&oacute;noma
     */
	@Column(name="COMUNIDAD", nullable=false)
	protected String comunidad;

    /**
     * Provincia de la Unidad Organizacional
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
    public UnidadOrganizacionalBean() {
    }

    /**
     * @return Nombre de la unidad organizacional
     * 
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return Comunidad aut&oacute;noma
     */
    public String getComunidad() {
        return comunidad;
    }

    /**
     * @return Devuelve la provincia
     * 
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     *
     * @return Localidad
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * @param Nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece la propiedad localidad
     * 
     * @param Localidad
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * Establece la comunidad aut&oacute;noma
     * 
     * @param Comunidad aut&oacute;noma
     */
    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    /**
     * Establece la propiedad provincia
     * 
     * @param Provincia
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * 
     * @return Identificador
     */
	public Long getId() {
		return id;
	}

	/**
	 * Identificador
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}


}
