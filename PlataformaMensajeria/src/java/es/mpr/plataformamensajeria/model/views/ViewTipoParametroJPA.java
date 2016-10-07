package es.mpr.plataformamensajeria.model.views;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.map.j2ee.auditoria.ifaces.Audit;
import com.map.j2ee.base.jpa.AbstractBaseJPAEntity;

/**
 * <p>Clase que representa un tipo parametro para la capa de presentaci&oacute;n
 * 
 * @author 
 *
 */
@Entity
@Table(name = "VIEW_TIPOSPARAMETROS")
public class ViewTipoParametroJPA extends AbstractBaseJPAEntity implements Serializable {
	
	
		private static final long serialVersionUID = 1L;
		@Id
		@SequenceGenerator(name="tipoParametro", sequenceName="TIPOPARAMETROID_SEC", allocationSize=1,initialValue=1)
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tipoParametro")
		@Column(name="TIPOPARAMETROID")
		protected Integer tipoParametroId;
		@Column(name="NOMBRE")
		protected String nombre;
		@Column(name="DESCRIPCION")
		protected String descripcion;
		@Column(name="TAGS")
		protected String tag;
		@Column(name="TIPO")
		protected Integer tipo;
		
		@Column(name="TIPOCAMPO")
		protected String tipoCampo;
		public ViewTipoParametroJPA() {
			super();
			this.tipoParametroId=null;
			this.nombre = null;
			this.descripcion = null;
			this.tag = null;
			this.tipo = null;
		}
		public Object getId() {
			return this.tipoParametroId;
		}
		public void setId(Object id){
			this.tipoParametroId =(Integer)id;
		}
		public Integer getTipoParametroId() {
			return tipoParametroId;
		}

		public void setTipoParametroId(Integer tipoParametroId) {
			this.tipoParametroId = tipoParametroId;
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

		public String getTag() {
			return tag;
		}

		public void setTag(String tag) {
			this.tag = tag;
		}

		public Integer getTipo() {
			return tipo;
		}

		public void setTipo(Integer tipo) {
			this.tipo = tipo;
		}
		public void setTipoCampo(String tipoCampo) {
			this.tipoCampo = tipoCampo;
		}
		public String getTipoCampo() {
			return tipoCampo;
		}
}
