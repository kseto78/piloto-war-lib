package es.mpr.template.beans;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>Clase que representa un organismo para la capa de presentaci&oacute;n
 * 
 * @author Altran
 *
 */
public class OrganismoBeanOLD implements Audit{
	
	
		private static final long serialVersionUID = 1L;

		protected Long id;
	
		protected String nombre = null;
		
		protected String rol = null;
		
		protected Long organismoPadre = null;

		/**
		 * Constructor
		 */
		public OrganismoBeanOLD()
		{
			
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
                
                /**
	 * Devuelve el objeto como un XML
	 * 
	 */
	public String obtenerXML() {
		StringBuffer sb = new StringBuffer("<objeto>OrganimoJPA</objeto>");
		if(id != null)
			sb.append("<id>"  + id +"</id>" );
		if(nombre != null)
			sb.append("<nombre>"  + nombre +"</nombre>" );
		if(rol != null)
			sb.append("<rol>"  + rol +"</rol>" );
		if(organismoPadre != null)
			sb.append("<organismoPadre>"  + organismoPadre +"</organismoPadre>" );
		if(sb.length()==0)
			return null;
		else
			return sb.toString();
	}

}
