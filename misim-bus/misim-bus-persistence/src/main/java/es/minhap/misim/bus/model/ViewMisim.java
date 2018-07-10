package es.minhap.misim.bus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

	@Entity
	@Table(name = "VIEW_MISIM")
	public class ViewMisim implements java.io.Serializable {
		
		private static final long serialVersionUID = 1L;

		private Long idAuditoria;
		private Date fechaCreacion;
		private Long idLote;
		private String proveedorProducto;
		private Long idPeticion;
		
		@Id
		@Column(name = "ID_AUDITORIA", unique = true, nullable = false, precision = 20, scale = 0)
		public Long getIdAuditoria() {
			return this.idAuditoria;
		}

		public void setIdAuditoria(Long idAuditoria) {
			this.idAuditoria = idAuditoria;
		}
		
		@Column(name = "FECHA_CREACION",  length = 7)
		public Date getFechaCreacion() {
			return this.fechaCreacion;
		}

		public void setFechaCreacion(Date fechaCreacion) {
			this.fechaCreacion = fechaCreacion;
		}

		@Column(name = "ID_LOTE", precision = 20, scale = 0)
		public Long getIdLote() {
			return this.idLote;
		}

		public void setIdLote(Long idLote) {
			this.idLote = idLote;
		}

		@Column(name = "PROVEEDOR_PRODUCTO", nullable = false, length = 40)
		public String getProveedorProducto() {
			return this.proveedorProducto;
		}

		public void setProveedorProducto(String proveedorProducto) {
			this.proveedorProducto = proveedorProducto;
		}

		@Column(name = "ID_PETICION", unique = true, nullable = false, precision = 20, scale = 0)
		public Long getIdPeticion() {
			return this.idPeticion;
		}

		public void setIdPeticion(Long idPeticion) {
			this.idPeticion = idPeticion;
		}
		
		public ViewMisim() {
		}
	}
