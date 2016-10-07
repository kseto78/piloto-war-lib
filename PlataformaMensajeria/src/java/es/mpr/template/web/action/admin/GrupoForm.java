package es.mpr.template.web.action.admin;

import java.io.Serializable;

/**
 * 
 * Clase que contiene los atributos del formulario de los tipos de seguro
 */
public class GrupoForm implements Serializable {
    private static final long serialVersionUID = 1;

    protected String id ;

    protected String name ;

    protected String description ;
    

    /**
     * Constructor
     */
    public GrupoForm() {
        super();
    }

    /**
     * Gets the id
     * 
     * @return Returns a String
     */
	public String getId() {
        return this.id;
    }

    /**
     * Sets the id
     * 
     * @param id
     *            The id to set
     */
	public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the name
     * 
     * @return Returns a String
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name
     * 
     * @param name
     *            The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the description
     * 
     * @return Returns a String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description
     * 
     * @param description
     *            The description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }



}