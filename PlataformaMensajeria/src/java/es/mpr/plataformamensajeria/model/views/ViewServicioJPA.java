package es.mpr.plataformamensajeria.model.views;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.map.j2ee.base.jpa.AbstractBaseJPAEntity;

/**
 * <p>Clase que representa un servicio para la capa de presentaci&oacute;n
 * 
 * @author Altran
 *
 */
@Entity
@Table(name = "VIEW_SERVICIOS")
@NamedQueries({
	@NamedQuery(name="selectViewServicioJPA", 
			query="SELECT e FROM ViewServicioJPA e WHERE (e.eliminado is null or e.eliminado = 'N')"),
	@NamedQuery(name="selectViewServicioJPAByIdAplicacion", 
			query="SELECT e FROM ViewServicioJPA e WHERE to_char(e.aplicacionId) = ? and (e.eliminado is null or e.eliminado = 'N') order by e.nombre ASC"),
	@NamedQuery(name="selectViewServiciosAll", 
	query="SELECT e FROM ViewServicioJPA e WHERE (e.eliminado is null or e.eliminado = 'N')"),
	@NamedQuery(name="selectViewServiciosAllNombreAplicacion_ASC", 
	query="SELECT e FROM ViewServicioJPA e WHERE (e.eliminado is null or e.eliminado = 'N') order by e.aplicacionNombre ASC"),
	@NamedQuery(name="selectViewServiciosAllNombreAplicacion_DESC", 
	query="SELECT e FROM ViewServicioJPA e WHERE (e.eliminado is null or e.eliminado = 'N') order by e.aplicacionNombre DESC"),
	@NamedQuery(name="selectViewServiciosAllNombreServicio_ASC", 
	query="SELECT e FROM ViewServicioJPA e WHERE (e.eliminado is null or e.eliminado = 'N') order by e.nombre ASC"),
	@NamedQuery(name="selectViewServiciosAllNombreServicio_DESC", 
	query="SELECT e FROM ViewServicioJPA e WHERE (e.eliminado is null or e.eliminado = 'N') order by e.nombre DESC"),

	@NamedQuery(name="selectViewServiciosAllCount",
query="SELECT count(e) FROM ViewServicioJPA e WHERE (e.eliminado is null or e.eliminado = 'N') "),
	@NamedQuery(name="selectViewServiciosByNombreAndAplicacion_orderByNombreAplicacion_ASC", 
	query="SELECT e FROM ViewServicioJPA e WHERE to_char(e.aplicacionId)= :aplicacionId and (e.eliminado is null or e.eliminado = 'N') " +
			"and upper (e.nombre) like upper(:nombre) and (e.eliminado is null or e.eliminado = 'N') order by e.aplicacionNombre ASC"),
	@NamedQuery(name="selectViewServiciosByNombreAndAplicacion_orderByNombreAplicacion_DESC", 
	query="SELECT e FROM ViewServicioJPA e WHERE to_char(e.aplicacionId)= :aplicacionId " +
			"and upper (e.nombre) like upper(:nombre) and (e.eliminado is null or e.eliminado = 'N') order by e.aplicacionNombre DESC"),
	@NamedQuery(name="selectViewServiciosByNombreAndAplicacion_orderByNombreServicio_ASC", 
	query="SELECT e FROM ViewServicioJPA e WHERE to_char(e.aplicacionId)= :aplicacionId " +
			"and upper (e.nombre) like upper(:nombre) and (e.eliminado is null or e.eliminado = 'N') order by e.nombre ASC"),
	@NamedQuery(name="selectViewServiciosByNombreAndAplicacion_orderByNombreServicio_DESC", 
	query="SELECT e FROM ViewServicioJPA e WHERE to_char(e.aplicacionId)= :aplicacionId " +
			"and upper (e.nombre) like upper(:nombre) and (e.eliminado is null or e.eliminado = 'N') order by e.nombre DESC"),
	@NamedQuery(name="selectViewServiciosByNombreAndAplicacionCount", 
	query="SELECT count(e) FROM ViewServicioJPA e WHERE to_char(e.aplicacionId)= :aplicacionId " +
			"and upper (e.nombre) like upper(:nombre) and (e.eliminado is null or e.eliminado = 'N')"),


@NamedQuery(name="selectViewServiciosByNombre_orderByNombreServicio_ASC", 
query="SELECT e FROM ViewServicioJPA e WHERE upper (e.nombre) like " +
		"upper(:nombre) and (e.eliminado is null or e.eliminado = 'N') order by e.nombre ASC"),
@NamedQuery(name="selectViewServiciosByNombre_orderByNombreServicio_DESC", 
query="SELECT e FROM ViewServicioJPA e WHERE upper (e.nombre) like " +
		"upper(:nombre) and (e.eliminado is null or e.eliminado = 'N') order by e.nombre DESC"),
@NamedQuery(name="selectViewServiciosByNombre_orderByNombreAplicacion_ASC", 
query="SELECT e FROM ViewServicioJPA e WHERE upper (e.nombre) like " +
		"upper(:nombre) and (e.eliminado is null or e.eliminado = 'N') order by e.aplicacionNombre ASC"),
@NamedQuery(name="selectViewServiciosByNombre_orderByNombreAplicacion_DESC",
query="SELECT e FROM ViewServicioJPA e WHERE upper (e.nombre) like " +
		"upper(:nombre) and (e.eliminado is null or e.eliminado = 'N') order by e.aplicacionNombre DESC"),
@NamedQuery(name="selectViewServiciosByNombreCount", 
query="SELECT count(e) FROM ViewServicioJPA e WHERE upper (e.nombre) " +
		"like upper(:nombre) and (e.eliminado is null or e.eliminado = 'N')"),

@NamedQuery(name="selectViewServiciosByAplicacionCount", 
query="SELECT count(e) FROM ViewServicioJPA e " +
		"WHERE to_char(e.aplicacionId)= :aplicacionId and (e.eliminado is null or e.eliminado = 'N')"),

@NamedQuery(name="selectViewServiciosByAplicacion_orderByNombreServicio_ASC", 
query="SELECT e FROM ViewServicioJPA e WHERE " +
		"to_char(e.aplicacionId)= :aplicacionId and (e.eliminado is null or e.eliminado = 'N') order by e.nombre ASC"),
@NamedQuery(name="selectViewServiciosByAplicacion_orderByNombreServicio_DESC", 
query="SELECT e FROM ViewServicioJPA e WHERE " +
		"to_char(e.aplicacionId)= :aplicacionId and (e.eliminado is null or e.eliminado = 'N') order by e.nombre DESC"),
@NamedQuery(name="selectViewServiciosByAplicacion_orderByNombreAplicacion_ASC", 
query="SELECT e FROM ViewServicioJPA e WHERE " +
		"to_char(e.aplicacionId)= :aplicacionId and (e.eliminado is null or e.eliminado = 'N') order by e.aplicacionNombre ASC"),
@NamedQuery(name="selectViewServiciosByAplicacion_orderByNombreAplicacion_DESC", 
query="SELECT e FROM ViewServicioJPA e WHERE " +
		"to_char(e.aplicacionId)= :aplicacionId and (e.eliminado is null or e.eliminado = 'N') order by e.aplicacionNombre DESC")})
public class ViewServicioJPA extends AbstractBaseJPAEntity implements Serializable {
	
	
		private static final long serialVersionUID = 1L;

		public ViewServicioJPA() {
			this.servicioId = null;
			this.nombre = null;
			this.descripcion = null;
			this.activo = null;
			this.canalId = null;
			this.aplicacionId = null;
			this.fechaCreacion = null;
			this.creadoPor = null;
			this.fechaModificacion = null;
			this.modificadoPor = null;
			this.nmaxenvios = null;
			this.headerSMS = null;
			this.canalNombre = null;
			this.aplicacionNombre = null;
			this.historificacion = null;
			this.conservacion = null;
			this.pendienteAprobacion = null;
			this.endPoint = null;
			this.informesActivo = null;
			this.agrupacionEstado = null;
			this.agrupacionCodOrg = null;
			this.agrupacionCodSia = null;
			this.agrupacionCodOrgPagador = null;
			this.informesDestinatarios = null;
		}

		@Id
		@SequenceGenerator(name="servicio", sequenceName="SERVICIOID_SEC", allocationSize=1,initialValue=1)
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="servicio")
		@Column(name="SERVICIOID")		
		protected Integer servicioId;
		@Column(name="NOMBRE")
		protected String nombre = null;
		@Column(name="DESCRIPCION")
		protected String descripcion = null;
		@Column(name="ACTIVO")
		protected Integer activo = null;
		@Column(name="CANALID")
		protected Integer canalId = null;
		@Column(name="APLICACIONID")
		protected Integer aplicacionId = null;
		@Column(name="FECHACREACION")
		protected Date fechaCreacion = null;
		@Column(name="CREADOPOR")
		protected String creadoPor = null;
		@Column(name="FECHAMODIFICACION")
		protected Date fechaModificacion = null;
		@Column(name="MODIFICADOPOR")
		protected String modificadoPor = null;
		@Column(name="NMAXENVIOS")
		protected Integer nmaxenvios = null;
		@Column(name="HEADERSMS")
		protected String headerSMS = null;
		@Column(name="CANALNOMBRE")
		protected String canalNombre = null;
		@Column(name="APLICACIONNOMBRE")
		protected String aplicacionNombre = null;		
		@Column (name="ELIMINADO")
		protected String eliminado = null;
		@Column (name="CUENTAENVIO")
		protected String fromMail = null;
		@Column(name="NOMBRECUENTAENVIO")
		protected String fromMailName = null;
		@Column(name="HISTORIFICACION")
		protected Integer historificacion = null;
		@Column(name="CONSERVACION")
		protected Integer conservacion = null;
		@Column(name="PENDIENTEAPROBACION")
		protected Integer pendienteAprobacion = null;
		@Column(name="NOMBRELOTEENVIO")
		protected String nombreLoteEnvio = null;
		@Column(name="BADGE")
		protected Integer badge = null;
		@Column(name="GCMPROJECTKEY")
		protected String gcmProjectKey = null;
		@Column(name="APNSRUTACERTIFICADO")
		protected String apnsRutaCertificado = null;
		@Column(name="APNSPASSWORDCERTIFICADO")
		protected String apnsPasswordCertificado = null;
		@Column(name="ANDROIDPLATAFORMA")
		protected Integer androidPlataforma = null;
		@Column(name="IOSPLATAFORMA")
		protected Integer iosPlataforma = null;
		@Column(name="ENDPOINT")
		protected String endPoint = null;
		@Column(name="INFORMESACTIVO")
		protected Integer informesActivo = null;
		@Column(name="AGRUPACIONESTADO")
		protected Integer agrupacionEstado = null;
		@Column(name="AGRUPACIONCODORG")
		protected Integer agrupacionCodOrg = null;
		@Column(name="AGRUPACIONCODSIA")
		protected Integer agrupacionCodSia = null;
		@Column(name="AGRUPACIONCODORGPAGADOR")
		protected Integer agrupacionCodOrgPagador = null;
		@Column(name="INFORMESDESTINATARIOS")
		protected String informesDestinatarios = null;

		public String getFromMail() {
			return fromMail;
		}
		public void setFromMail(String fromMail) {
			this.fromMail = fromMail;
		}
		public String getFromMailName() {
			return fromMailName;
		}
		public void setFromMailName(String fromMailName) {
			this.fromMailName = fromMailName;
		}
		public String getEliminado() {
			return eliminado;
		}
		public void setEliminado(String eliminado) {
			this.eliminado = eliminado;
		}
		public String getCanalNombre() {
			return canalNombre;
		}
		public void setCanalNombre(String canalNombre) {
			this.canalNombre = canalNombre;
		}
		public String getAplicacionNombre() {
			return aplicacionNombre;
		}
		public void setAplicacionNombre(String aplicacionNombre) {
			this.aplicacionNombre = aplicacionNombre;
		}
		public void setActivado(String activado){
			if(activado!=null&&activado.equals("true")){
				this.activo = new Integer(1);
			}else{
				this.activo = new Integer(0);
			}
		}
		public String getActivado(){
			if(activo!=null&&activo.intValue()==1){
				return "true";
			}else{
				return "false";
			}
			
		}


		public Integer getservicioId() {
			return servicioId;
		}


		public void setservicioId(Integer servicioId) {
			this.servicioId = servicioId;
		}

		public Integer getId() {
			return servicioId;
		}


		public void setId(Integer servicioId) {
			this.servicioId = servicioId;
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
	
		public Integer getServicioId() {
			return servicioId;
		}
		public void setServicioId(Integer servicioId) {
			this.servicioId = servicioId;
		}
		public Integer getCanalId() {
			return canalId;
		}
		public void setCanalId(Integer canalId) {
			this.canalId = canalId;
		}
		public Integer getAplicacionId() {
			return aplicacionId;
		}
		public void setAplicacionId(Integer aplicacionId) {
			this.aplicacionId = aplicacionId;
		}
		public Integer getNmaxenvios() {
			return nmaxenvios;
		}
		public void setNmaxenvios(Integer nmaxenvios) {
			this.nmaxenvios = nmaxenvios;
		}
		public String getHeaderSMS() {
			return headerSMS;
		}
		public void setHeaderSMS(String headerSMS) {
			this.headerSMS = headerSMS;
		}
		public Integer getHistorificacion() {
			return historificacion;
		}
		public void setHistorificacion(Integer historificacion) {
			this.historificacion = historificacion;
		}
		public Integer getConservacion() {
			return conservacion;
		}
		public void setConservacion(Integer conservacion) {
			this.conservacion = conservacion;
		}
		public Integer getPendienteAprobacion() {
			return pendienteAprobacion;
		}
		public void setPendienteAprobacion(Integer pendienteAprobacion) {
			this.pendienteAprobacion = pendienteAprobacion;
		}
		public String getNombreLoteEnvio() {
			return nombreLoteEnvio;
		}
		public void setNombreLoteEnvio(String nombreLoteEnvio) {
			this.nombreLoteEnvio = nombreLoteEnvio;
		}
		public Integer getBadge() {
			return badge;
		}
		public void setBadge(Integer badge) {
			this.badge = badge;
		}
		public String getGcmProjectKey() {
			return gcmProjectKey;
		}
		public void setGcmProjectKey(String gcmProjectKey) {
			this.gcmProjectKey = gcmProjectKey;
		}
		public String getApnsRutaCertificado() {
			return apnsRutaCertificado;
		}
		public void setApnsRutaCertificado(String apnsRutaCertificado) {
			this.apnsRutaCertificado = apnsRutaCertificado;
		}
		public String getApnsPasswordCertificado() {
			return apnsPasswordCertificado;
		}
		public void setApnsPasswordCertificado(String apnsPasswordCertificado) {
			this.apnsPasswordCertificado = apnsPasswordCertificado;
		}
		public Integer getAndroidPlataforma() {
			return androidPlataforma;
		}
		public void setAndroidPlataforma(Integer androidPlataforma) {
			this.androidPlataforma = androidPlataforma;
		}
		public Integer getIosPlataforma() {
			return iosPlataforma;
		}
		public void setIosPlataforma(Integer iosPlataforma) {
			this.iosPlataforma = iosPlataforma;
		}
		public String getEndPoint() {
			return endPoint;
		}
		public void setEndPoint(String endPoint) {
			this.endPoint = endPoint;
		}
		public Integer getInformesActivo() {
			return informesActivo;
		}
		public void setInformesActivo(Integer informesActivo) {
			this.informesActivo = informesActivo;
		}
		public Integer getAgrupacionEstado() {
			return agrupacionEstado;
		}
		public void setAgrupacionEstado(Integer agrupacionEstado) {
			this.agrupacionEstado = agrupacionEstado;
		}
		public Integer getAgrupacionCodOrg() {
			return agrupacionCodOrg;
		}
		public void setAgrupacionCodOrg(Integer agrupacionCodOrg) {
			this.agrupacionCodOrg = agrupacionCodOrg;
		}
		public Integer getAgrupacionCodSia() {
			return agrupacionCodSia;
		}
		public void setAgrupacionCodSia(Integer agrupacionCodSia) {
			this.agrupacionCodSia = agrupacionCodSia;
		}
		public Integer getAgrupacionCodOrgPagador() {
			return agrupacionCodOrgPagador;
		}
		public void setAgrupacionCodOrgPagador(Integer agrupacionCodOrgPagador) {
			this.agrupacionCodOrgPagador = agrupacionCodOrgPagador;
		}
		public String getInformesDestinatarios() {
			return informesDestinatarios;
		}
		public void setInformesDestinatarios(String informesDestinatarios) {
			this.informesDestinatarios = informesDestinatarios;
		}


                
                /*
	 * Devuelve el objeto como un XML
	 * 
	 
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
*/
}
