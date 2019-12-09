<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<sj:dialog  id="dialogEnviosNotificacionPush" title="DETALLE NOTIFICACION PUSH" cssStyle="min-height:90px" autoOpen="false">
<div id="notificacion" class="ui-dialog-content ui-widget-content" display: block; min-height: 0px; height: auto;">
	<div class="editContainer">
        <div class="nameDescription" style="width: 135px">
            <label>Datos Generales</label>
        </div>
        <div class="editContent" style="width:770px;">
            <p class="criteria">
                <label style="width: 90px;" class="fieldText">ID Mensaje:</label>
                <strong><s:label value="%{detalleEmail.mensajeId}"/></strong>
            </p>         
            <p class="criteria">
            	<span style="width: 280px;">
                    <label style="width: 90px;" class="fieldText">Aplicación:</label>
                    <strong><s:label value="%{detalleEmail.nombreAplicacion}"/></strong>
                </span>
            	<span>
                    <label style="width: 70px;" class="fieldText">Servicio:</label>
                    <strong><s:label value="%{detalleEmail.nombreServicio}"/></strong>
	            </span>
            </p>
            <p class="criteria">
                <label style="width: 90px;" class="fieldText">Lote Envío:</label>
                <strong><s:label value="%{detalleEmail.nombreLoteEnvio}"/></strong>
            </p>
            <p class="criteria">
                <label style="width: 90px;" class="fieldText">ID Lote Envío:</label>
                <strong><s:label value="%{detalleEmail.idLote}"/></strong>
            </p>
            <p class="criteria">
            	<span style="width: 280px;">
                    <label style="width: 90px;" class="fieldText">Usuario:</label>
                    <strong><s:label value="%{detalleEmail.docUsuario}"/></strong>
                </span>
            	<span>
                    <label style="width: 140px;" class="fieldText">Procedimiento/Servicio:</label>
                    <strong><s:label value="%{detalleEmail.codSIA}"/></strong>
	            </span>
            </p>
            <p class="criteria">
            	<span style="width: 280px;">
                    <label style="width: 90px;" class="fieldText">Organismo:</label>
                    <strong><s:label value="%{detalleEmail.codOrganismo}"/></strong>
                </span>
            </p>
        </div>
    </div>
    <div class="editContainer">
        <div class="nameDescription" style="width: 135px">
            <label>Notificación Push</label>
        </div>
        <div class="editContent" style="width:770px;">
            <p class="criteria">
                <label style="width: 130px;" class="fieldText">Título:</label>
                <strong><s:label value="%{detalleEmail.asunto}"/></strong>
            </p>
            <p class="criteria">
                <label style="width: 130px;" class="fieldText">Mensaje:</label>
                <strong><s:label value="%{detalleEmail.cuerpo}"/></strong>
            </p>
            <p class="criteria">
                <label style="width: 130px;" class="fieldText">Identificador Usuario:</label>
                <strong><s:label value="%{detalleEmail.nombreUsuario}"/></strong>
            </p>
            <p class="criteria">
                <label style="width: 130px;" class="fieldText">Icono:</label>
                <strong><s:label value="%{detalleEmail.icono}"/></strong>
            </p>
            <p class="criteria">
                <label style="width: 130px;" class="fieldText">Sonido:</label>
                <strong><s:label value="%{detalleEmail.sonido}"/></strong>
            </p>
        </div>        
    </div>
     </div>
       <div class="editContainer">
       <img src="/sim/img/ajax-loader.gif" id=loading alt="Processing" height="50" width="50" style="
			    position: absolute;
			    left: 50%; visibility:hidden;
			    bottom: 45%;
			">
        <div style="width: 135px" class="nameDescription">
            <label>Destinatarios</label>
        </div>
         
   <div style="width: 770px;overflow: auto;" class="editContent">
    <s:set var="total">${resultCount}</s:set>
     <h4 class="titular">Encontradas ${resultCount} entradas</h4>
    
	    <display:table 
					id="tableMensajesId"
					name="listaGestionEnviosDestinatariosMensaje" 
					pagesize="${pageSize}"
					requestURI="viewMensajeNotificacionPush.action" 
					defaultsort="1" 
					defaultorder="ascending" 
					sort="external"
					export="true" 
					cellpadding="0" 
					cellspacing="0" 
					partialList="true"
					requestURIcontext="false"
					size='<%=request.getAttribute("totalSize")%>'
					decorator="es.mpr.template.web.decorators.TableWrapper">
					<display:setProperty name="css.tr.even" value="null" />
					<display:setProperty name="css.tr.odd"  value="odd" />
					
					<display:column property="fechaFormateada" titleKey="plataforma.gestionViewLotes.fecha" 
						headerClass="TH110 separator center"  class="TH110 separator center" />
					
					<display:column property="estado" titleKey="plataforma.gestionViewLotes.estado" 
						headerClass="TH110 separator center" class="TH110 separator center" />
					
					<display:column property="destinatario" titleKey="plataforma.gestionViewLotes.destinatario" 
					headerClass="TH110 separator center" class="TH110 separator center" />
					
					
					<%-- acciones --%>
				<display:column property="historicoDestinatarioAction" titleKey="plataforma.gestionViewLotes.blanco"
					headerClass="TH20 separator " class="TH20 separator center" media="html" />			
			</display:table>
		</div>
	</div>
</div>
</sj:dialog>
<script>
$(function(){
    $('a').each(function() {
        if(this.href.includes("viewMensajeNotificacionPush.action?")){
	        	var enl = this.href.split('viewMensajeNotificacionPush.action')[1];
	        	
	            $(this).attr('href', 'javascript:verMensajesPaginar("viewMensajeNotificacionPush.action' + enl + '")');
            }
        
    });
});
</script>