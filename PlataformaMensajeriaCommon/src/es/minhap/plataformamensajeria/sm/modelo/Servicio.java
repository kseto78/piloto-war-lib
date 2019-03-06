package es.minhap.plataformamensajeria.sm.modelo;


import java.util.Date;

/**
 * <p>Clase que representa un servicio para la capa de presentaci&oacute;n
 * 
 * @author Altran
 *
 */
public class Servicio {
	
	
		private static final long serialVersionUID = 1L;

		public Servicio() {
			super();
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
			this.externalId = null;
			this.nmaxenvios = null;
			this.headerSMS = null;
			this.canalNombre = null;
			this.aplicacionNombre = null;
			this.historificacion = null;
			this.motivoHistorificacion = null;
			this.conservacion = null;
			this.motivoConservacion = null;
			this.pendienteAprobacion = null;
			this.nombreLoteEnvio = null;
			this.badge = null;
			this.fcmProjectKey = null;
			this.apnsRutaCertificado = null;
			this.apnsPasswordCertificado = null;
			this.androidPlataforma = null;
			this.iosPlataforma = null;
			this.isAndroidPlataforma = null;
			this.isIosPlataforma = null;
			this.endPoint = null;
			this.informesActivo = null;
			this.isInformesActivo = null;
			this.agrupacionEstado = null;
			this.isAgrupacionEstado = null;
			this.agrupacionCodOrg = null;
			this.isAgrupacionCodOrg = null;
			this.agrupacionCodSia = null;
			this.isAgrupacionCodSia = null;
			this.agrupacionCodOrgPagador = null;
			this.isAgrupacionCodOrgPagador = null;
			this.informesDestinatarios = null;
			
			this.responsableFuncionalNombre = null;
			this.responsableFuncionalEmail = null;
			this.responsableTecnicoNombre = null;
			this.responsableTecnicoEmail = null;
			
		}
		
		protected Integer servicioId;
		protected String nombre = null;
		protected String descripcion = null;
		protected Integer activo = null;
		protected Integer canalId = null;
		protected Integer aplicacionId = null;
		protected Date fechaCreacion = null;
		protected String creadoPor = null;
		protected Date fechaModificacion = null;
		protected String modificadoPor = null;
		protected String  externalId = null;
		protected Integer nmaxenvios = null;
		protected String headerSMS = null;
		protected String isActivo = null; 
		protected String canalNombre = null;
		protected String aplicacionNombre = null;
		protected String fromMail = null;
		protected String fromMailName = null;
		protected Integer historificacion = null;
		protected Integer historificacionInput = null;
		protected String motivoHistorificacion = null;
		protected Integer conservacion = null;
		protected Integer conservacionInput = null;
		protected String motivoConservacion = null;
		protected Integer pendienteAprobacion = null;
		protected String isPendienteAprobacion = null;
		protected String nombreLoteEnvio = null;
		protected Integer badge = null;
		protected String fcmProjectKey = null;
		protected String apnsRutaCertificado = null;
		protected String apnsPasswordCertificado = null;
		protected Integer androidPlataforma = null;
		protected Integer iosPlataforma = null;
		protected String isAndroidPlataforma = null; 
		protected String isIosPlataforma = null; 
		protected String endPoint = null; 
		protected Integer informesActivo = null;
		protected String isInformesActivo = null;
		protected Integer agrupacionEstado = null;
		protected String isAgrupacionEstado = null;
		protected Integer agrupacionCodOrg = null;
		protected String isAgrupacionCodOrg = null;
		protected Integer agrupacionCodSia = null;
		protected String isAgrupacionCodSia = null;
		protected Integer agrupacionCodOrgPagador = null;
		protected String isAgrupacionCodOrgPagador = null;
		protected String informesDestinatarios = null;
		
		protected String responsableFuncionalNombre = null;
		protected String responsableFuncionalEmail = null;
		protected String responsableTecnicoNombre = null;
		protected String responsableTecnicoEmail = null;
		
		protected String proveedorUsuarioSMS = null;
		protected String proveedorPassSMS = null;
		
		
		public void setFromMail(String fromMail){
			this.fromMail = fromMail;
		}
		public String getFromMail(){
			return this.fromMail;
		}
		
		public void setFromMailName(String fromMailName){
			this.fromMailName = fromMailName;
		}
		
		public String getFromMailName(){
			return this.fromMailName;
		}

		public void setActivado(String activado){
			if(activado!=null&&activado.equals("true")){
				this.activo = Integer.valueOf(1);
			}else{
				this.activo = Integer.valueOf(0);
			}
		}
		public String getActivado(){
			if(activo!=null&&activo.intValue()==1){
				return "true";
			}else{
				return "false";
			}
			
		}
		public String getIsActivo() {
			if(activo!=null&&activo.intValue()==1){
				return "<span class='activo'></span>";
			}else{
				return "<span class='inactivo'></span>";
			}
			
		}


		public void setIsActivo(String isActivo) {
			if(isActivo!=null&&isActivo.equals("true")){
				this.activo = Integer.valueOf(1);
			}else{
				this.activo = Integer.valueOf(0);
			}
			this.isActivo = isActivo;
		}


	
		public Object getId() {
			return servicioId;
		}


		public void setId(Object servicioId) {
			this.servicioId = (Integer)servicioId;
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


		public String getExternalId() {
			return externalId;
		}


		public void setExternalId(String externalId) {
			this.externalId = externalId;
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
		public Integer getHistorificacion() {
			return historificacion;
		}
		public void setHistorificacion(Integer historificacion) {
			this.historificacion = historificacion;
		}
		public String getMotivoHistorificacion() {
			return motivoHistorificacion;
		}
		public void setMotivoHistorificacion(String motivoHistorificacion) {
			this.motivoHistorificacion = motivoHistorificacion;
		}
		public Integer getConservacion() {
			return conservacion;
		}
		public void setConservacion(Integer conservacion) {
			this.conservacion = conservacion;
		}
		public String getMotivoConservacion() {
			return motivoConservacion;
		}
		public void setMotivoConservacion(String motivoConservacion) {
			this.motivoConservacion = motivoConservacion;
		}
		public Integer getHistorificacionInput() {
			return historificacionInput;
		}
		public void setHistorificacionInput(Integer historificacionInput) {
			this.historificacionInput = historificacionInput;
		}
		public Integer getConservacionInput() {
			return conservacionInput;
		}
		public void setConservacionInput(Integer conservacionInput) {
			this.conservacionInput = conservacionInput;
		}
		public Integer getPendienteAprobacion() {
			return pendienteAprobacion;
		}
		public void setPendienteAprobacion(Integer pendienteAprobacion) {
			this.pendienteAprobacion = pendienteAprobacion;
		}
		
		public String getIsPendienteAprobacion() {
			if(pendienteAprobacion!=null && pendienteAprobacion.intValue()==1){
				return "<span class='activo'></span>";
			} else {
				return "<span class='inactivo'></span>";
			}
		}

		public void setIsPendienteAprobacion(String isPendienteAprobacion) {
			if(isPendienteAprobacion!=null && isPendienteAprobacion.equals("true")){
				this.pendienteAprobacion = Integer.valueOf(1);
			} else {
				this.pendienteAprobacion = Integer.valueOf(0);
			}
			this.isPendienteAprobacion = isPendienteAprobacion; //TODO
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
		public String getFcmProjectKey() {
			return fcmProjectKey;
		}
		public void setFcmProjectKey(String fcmProjectKey) {
			this.fcmProjectKey = fcmProjectKey;
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
			return this.androidPlataforma;
		}
		public void setAndroidPlataforma(Integer androidPlataforma) {
			this.androidPlataforma = androidPlataforma;
		}
		public Integer getIosPlataforma() {
			return this.iosPlataforma;
		}
		public void setIosPlataforma(Integer iosPlataforma) {
			this.iosPlataforma = iosPlataforma;
		}
		public String getIsAndroidPlataforma() {
			if(this.androidPlataforma!=null&&this.androidPlataforma.intValue()==1){
				return "true";
			}else{
				return "false";
			}
		}
		public void setIsAndroidPlataforma(String androidPlataforma) {
			this.isAndroidPlataforma = androidPlataforma;
			if(androidPlataforma!=null&&androidPlataforma.equals("true")){
				this.androidPlataforma = Integer.valueOf(1);
			}else{
				this.androidPlataforma = Integer.valueOf(0);
			}
			
		}
		public String getIsIosPlataforma() {
			if(this.iosPlataforma!=null&&this.iosPlataforma.intValue()==1){
				return "true";
			}else{
				return "false";
			}
		}
		public void setIsIosPlataforma(String iosPlataforma) {
			this.isIosPlataforma = iosPlataforma;
			if(iosPlataforma!=null&&iosPlataforma.equals("true")){
				this.iosPlataforma = Integer.valueOf(1);
			}else{
				this.iosPlataforma = Integer.valueOf(0);
			}
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
		public void setInformesActivado(String informesActivado){
			if(informesActivado!=null&&informesActivado.equals("true")){
				this.informesActivo = Integer.valueOf(1);
			}else{
				this.informesActivo = Integer.valueOf(0);
			}
		}
		public String getInformesActivado(){
			if(informesActivo!=null&&informesActivo.intValue()==1){
				return "true";
			}else{
				return "false";
			}
			
		}
		public String getIsInformesActivo() {
			if(informesActivo!=null&&informesActivo.intValue()==1){
				return "true";
			}else{
				return "false";
			}
		}
		public void setIsInformesActivo(String isInformesActivo) {
			if(isInformesActivo!=null&&isInformesActivo.equals("true")){
				this.informesActivo = Integer.valueOf(1);
			}else{
				this.informesActivo = Integer.valueOf(0);
			}
			this.isInformesActivo = isInformesActivo;
		}
		public Integer getAgrupacionEstado() {
			return agrupacionEstado;
		}
		public void setAgrupacionEstado(Integer agrupacionEstado) {
			this.agrupacionEstado = agrupacionEstado;
		}
		public void setAgrupacionEstadoActivado(String agrupacionEstadoActivado){
			if(agrupacionEstadoActivado!=null&&agrupacionEstadoActivado.equals("true")){
				this.agrupacionEstado = Integer.valueOf(1);
			}else{
				this.agrupacionEstado = Integer.valueOf(0);
			}
			this.isAgrupacionEstado = isInformesActivo;
		}
		public String getAgrupacionEstadoActivado(){
			if(agrupacionEstado!=null&&agrupacionEstado.intValue()==1){
				return "true";
			}else{
				return "false";
			}
			
		}
		public String getIsAgrupacionEstado() {
			if(agrupacionEstado!=null&&agrupacionEstado.intValue()==1){
				return "true";
			}else{
				return "false";
			}
		}
		public void setIsAgrupacionEstado(String isAgrupacionEstado) {
			if(isAgrupacionEstado!=null&&isAgrupacionEstado.equals("true")){
				this.agrupacionEstado = Integer.valueOf(1);
			}else{
				this.agrupacionEstado = Integer.valueOf(0);
			}
			this.isAgrupacionEstado = isAgrupacionEstado;
		}
		public Integer getAgrupacionCodOrg() {
			return agrupacionCodOrg;
		}
		public void setAgrupacionCodOrg(Integer agrupacionCodOrg) {
			this.agrupacionCodOrg = agrupacionCodOrg;
		}
		public void setAgrupacionCodOrgActivado(String agrupacionCodOrgActivado){
			if(agrupacionCodOrgActivado!=null&&agrupacionCodOrgActivado.equals("true")){
				this.agrupacionCodOrg = Integer.valueOf(1);
			}else{
				this.agrupacionCodOrg = Integer.valueOf(0);
			}
		}
		public String getgrupacionCodOrgActivado(){
			if(agrupacionCodOrg!=null&&agrupacionCodOrg.intValue()==1){
				return "true";
			}else{
				return "false";
			}
			
		}
		public String getIsAgrupacionCodOrg() {
			if(agrupacionCodOrg!=null&&agrupacionCodOrg.intValue()==1){
				return "true";
			}else{
				return "false";
			}
		}
		public void setIsAgrupacionCodOrg(String isAgrupacionCodOrg) {
			if(isAgrupacionCodOrg!=null&&isAgrupacionCodOrg.equals("true")){
				this.agrupacionCodOrg = Integer.valueOf(1);
			}else{
				this.agrupacionCodOrg = Integer.valueOf(0);
			}
			this.isAgrupacionCodOrg = isAgrupacionCodOrg;
		}
		public Integer getAgrupacionCodSia() {
			return agrupacionCodSia;
		}
		public void setAgrupacionCodSia(Integer agrupacionCodSia) {
			this.agrupacionCodSia = agrupacionCodSia;
		}
		public void setAgrupacionCodSiaActivado(String agrupacionCodSiaActivado){
			if(agrupacionCodSiaActivado!=null&&agrupacionCodSiaActivado.equals("true")){
				this.agrupacionCodSia = Integer.valueOf(1);
			}else{
				this.agrupacionCodSia = Integer.valueOf(0);
			}
		}
		public String getAgrupacionCodOrgActivado(){
			if(agrupacionCodSia!=null&&agrupacionCodSia.intValue()==1){
				return "true";
			}else{
				return "false";
			}
			
		}
		public String getIsAgrupacionCodSia() {
			if(agrupacionCodSia!=null&&agrupacionCodSia.intValue()==1){
				return "true";
			}else{
				return "false";
			}
		}
		public void setIsAgrupacionCodSia(String isAgrupacionCodSia) {
			if(isAgrupacionCodSia!=null&&isAgrupacionCodSia.equals("true")){
				this.agrupacionCodSia = Integer.valueOf(1);
			}else{
				this.agrupacionCodSia = Integer.valueOf(0);
			}
			this.isAgrupacionCodSia = isAgrupacionCodSia;
		}
		public Integer getAgrupacionCodOrgPagador() {
			return agrupacionCodOrgPagador;
		}
		public void setAgrupacionCodOrgPagador(Integer agrupacionCodOrgPagador) {
			this.agrupacionCodOrgPagador = agrupacionCodOrgPagador;
		}
		public void setAgrupacionCodOrgPagadorActivado(String agrupacionCodOrgPagadorActivado){
			if(agrupacionCodOrgPagadorActivado!=null&&agrupacionCodOrgPagadorActivado.equals("true")){
				this.agrupacionCodOrgPagador = Integer.valueOf(1);
			}else{
				this.agrupacionCodOrgPagador = Integer.valueOf(0);
			}
		}
		public String getAgrupacionCodOrgPagadorActivado(){
			if(agrupacionCodOrgPagador!=null&&agrupacionCodOrgPagador.intValue()==1){
				return "true";
			}else{
				return "false";
			}
			
		}
		public String getIsAgrupacionCodOrgPagador() {
			if(agrupacionCodOrgPagador!=null&&agrupacionCodOrgPagador.intValue()==1){
				return "true";
			}else{
				return "false";
			}
		}
		public void setIsAgrupacionCodOrgPagador(String isAgrupacionCodOrgPagador) {
			if(isAgrupacionCodOrgPagador!=null&&isAgrupacionCodOrgPagador.equals("true")){
				this.agrupacionCodOrgPagador = Integer.valueOf(1);
			}else{
				this.agrupacionCodOrgPagador = Integer.valueOf(0);
			}
			this.isAgrupacionCodOrgPagador = isAgrupacionCodOrgPagador;
		}
		public String getInformesDestinatarios() {
			return informesDestinatarios;
		}
		public void setInformesDestinatarios(String informesDestinatarios) {
			this.informesDestinatarios = informesDestinatarios;
		}
		public String getResponsableFuncionalNombre() {
			return responsableFuncionalNombre;
		}
		public void setResponsableFuncionalNombre(String responsableFuncionalNombre) {
			this.responsableFuncionalNombre = responsableFuncionalNombre;
		}
		public String getResponsableFuncionalEmail() {
			return responsableFuncionalEmail;
		}
		public void setResponsableFuncionalEmail(String responsableFuncionalEmail) {
			this.responsableFuncionalEmail = responsableFuncionalEmail;
		}
		public String getResponsableTecnicoNombre() {
			return responsableTecnicoNombre;
		}
		public void setResponsableTecnicoNombre(String responsableTecnicoNombre) {
			this.responsableTecnicoNombre = responsableTecnicoNombre;
		}
		public String getResponsableTecnicoEmail() {
			return responsableTecnicoEmail;
		}
		public void setResponsableTecnicoEmail(String responsableTecnicoEmail) {
			this.responsableTecnicoEmail = responsableTecnicoEmail;
		}
		public String getProveedorUsuarioSMS() {
			return proveedorUsuarioSMS;
		}
		public void setProveedorUsuarioSMS(String proveedorUsuarioSMS) {
			this.proveedorUsuarioSMS = proveedorUsuarioSMS;
		}
		public String getProveedorPassSMS() {
			return proveedorPassSMS;
		}
		public void setProveedorPassSMS(String proveedorPassSMS) {
			this.proveedorPassSMS = proveedorPassSMS;
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
