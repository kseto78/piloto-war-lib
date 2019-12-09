package es.mpr.plataformamensajeria.beans;

import java.util.Date;

import com.map.j2ee.auditoria.ifaces.Audit;

/**
 * <p>Clase que representa un servidor para la capa de presentaci&oacute;n.
 *
 * @author Altran
 */
public class TipoPlanificacionBean implements Audit{
	

		/**  tipo planificacion id. */
	protected Integer tipoPlanificacionId;

		/**  nombre. */
		protected String nombre;

		/**  descripcion. */
		protected String descripcion;

		/**  activo. */
		protected Integer activo = null;

		/**  fecha creacion. */
		protected Date fechaCreacion = null;

		/**  creado por. */
		protected String creadoPor = null;

		/**  fecha modificacion. */
		protected Date fechaModificacion = null;

		/**  modificado por. */
		protected String modificadoPor = null;

		/**
		 * Constructor de tipo planificacion bean.
		 */
		public TipoPlanificacionBean() {
			super();
			this.tipoPlanificacionId=null;
			this.nombre=null;
			this.descripcion=null;
			this.activo = null;
			this.fechaCreacion = null;
			this.creadoPor = null;
			this.fechaModificacion = null;
			this.modificadoPor = null;
		}

		/**
		 * Obtener tipo planificacion id.
		 *
		 * @return tipo planificacion id
		 */
		public Integer getTipoPlanificacionId() {
			return tipoPlanificacionId;
		}
		
		/**
		 * Modificar tipo planificacion id.
		 *
		 * @param tipoPlanificacionId new tipo planificacion id
		 */
		public void setTipoPlanificacionId(Integer tipoPlanificacionId) {
			this.tipoPlanificacionId = tipoPlanificacionId;
		}
		
		/**
		 * Obtener nombre.
		 *
		 * @return nombre
		 */
		public String getNombre() {
			return nombre;
		}
		
		/**
		 * Modificar nombre.
		 *
		 * @param nombre new nombre
		 */
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		
		/**
		 * Obtener descripcion.
		 *
		 * @return descripcion
		 */
		public String getDescripcion() {
			return descripcion;
		}
		
		/**
		 * Modificar descripcion.
		 *
		 * @param descripcion new descripcion
		 */
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		/**
		 * Obtener id.
		 *
		 * @return id
		 */
		public Object getId() {
			return this.tipoPlanificacionId;
		}
		
		/**
		 * Modificar id.
		 *
		 * @param id new id
		 */
		public void setId(Object id){
			this.tipoPlanificacionId =(Integer)id;
		}
	
		/**
		 * Obtener activo.
		 *
		 * @return activo
		 */
		public Integer getActivo() {
			return activo;
		}
		
		/**
		 * Modificar activo.
		 *
		 * @param activo new activo
		 */
		public void setActivo(Integer activo) {
			this.activo = activo;
		}
		
		/**
		 * Obtener fecha creacion.
		 *
		 * @return fecha creacion
		 */
		public Date getFechaCreacion() {
			return fechaCreacion;
		}
		
		/**
		 * Modificar fecha creacion.
		 *
		 * @param fechaCreacion new fecha creacion
		 */
		public void setFechaCreacion(Date fechaCreacion) {
			this.fechaCreacion = fechaCreacion;
		}
		
		/**
		 * Obtener creado por.
		 *
		 * @return creado por
		 */
		public String getCreadoPor() {
			return creadoPor;
		}
		
		/**
		 * Modificar creado por.
		 *
		 * @param creadoPor new creado por
		 */
		public void setCreadoPor(String creadoPor) {
			this.creadoPor = creadoPor;
		}
		
		/**
		 * Obtener fecha modificacion.
		 *
		 * @return fecha modificacion
		 */
		public Date getFechaModificacion() {
			return fechaModificacion;
		}
		
		/**
		 * Modificar fecha modificacion.
		 *
		 * @param fechaModificacion new fecha modificacion
		 */
		public void setFechaModificacion(Date fechaModificacion) {
			this.fechaModificacion = fechaModificacion;
		}
		
		/**
		 * Obtener modificado por.
		 *
		 * @return modificado por
		 */
		public String getModificadoPor() {
			return modificadoPor;
		}
		
		/**
		 * Modificar modificado por.
		 *
		 * @param modificadoPor new modificado por
		 */
		public void setModificadoPor(String modificadoPor) {
			this.modificadoPor = modificadoPor;
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
