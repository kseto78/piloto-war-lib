package es.mpr.plataformamensajeria.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.map.j2ee.base.jpa.AbstractBaseJPAEntity;

/**
 * <p>Clase que representa un servidor para la capa de presentaci&oacute;n
 * 
 * @author Altran
 *
 */
@Entity
@Table(name = "TBL_TIPOPLANIFICACIONES")
public class TipoPlanificacionJPA extends AbstractBaseJPAEntity implements Serializable {
	
	
		private static final long serialVersionUID = 1L;

		public TipoPlanificacionJPA() {
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

		public Integer getTipoPlanificacionId() {
			return tipoPlanificacionId;
		}
		public void setTipoPlanificacionId(Integer tipoPlanificacionId) {
			this.tipoPlanificacionId = tipoPlanificacionId;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		
		@Id
		@SequenceGenerator(name="tipoPlanificacion", sequenceName="TIPOPLANIFICACIONID_SEC", allocationSize=1,initialValue=1)
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tipoPlanificacion")
		@Column(name="TIPOPLANIFICACIONID")
		protected Integer tipoPlanificacionId;
		@Column(name="NOMBRE")
		protected String nombre;
		@Column(name="DESCRIPCION")
		protected String descripcion;
		@Column(name="ACTIVO")
		protected Integer activo = null;
		@Column(name="FECHACREACION")
		protected Date fechaCreacion = null;
		@Column(name="CREADOPOR")
		protected String creadoPor = null;
		@Column(name="FECHAMODIFICACION")
		protected Date fechaModificacion = null;
		@Column(name="MODIFICADOPOR")
		protected String modificadoPor = null;
		
		public Object getId() {
			return this.tipoPlanificacionId;
		}
		public void setId(Object id){
			this.tipoPlanificacionId =(Integer)id;
		}
	
		public Integer getActivo() {
			return activo;
		}
		public void setActivo(Integer activo) {
			this.activo = activo;
		}
		public Date getFechaCreacion() {
			return fechaCreacion;
		}
		public void setFechaCreacion(Date fechaCreacion) {
			this.fechaCreacion = fechaCreacion;
		}
		public String getCreadoPor() {
			return creadoPor;
		}
		public void setCreadoPor(String creadoPor) {
			this.creadoPor = creadoPor;
		}
		public Date getFechaModificacion() {
			return fechaModificacion;
		}
		public void setFechaModificacion(Date fechaModificacion) {
			this.fechaModificacion = fechaModificacion;
		}
		public String getModificadoPor() {
			return modificadoPor;
		}
		public void setModificadoPor(String modificadoPor) {
			this.modificadoPor = modificadoPor;
		}

	
}
