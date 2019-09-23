<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<sj:dialog id="dialogMensajesReenvios" title="Mensajes Reenvios Job"
	cssStyle="height:auto;padding:0;" autoOpen="false">
	<div id="Email" class="ui-dialog-content ui-widget-content"
		style="display: block; min-height: 0px; height: auto;">		
	</div>
	<div id="loading" style="visibility:hidden">		   
		   <img src="/sim/img/ajax-loader.gif" alt="Processing" height="50" width="50" style="
			    position: absolute;
			    left: 50%;
			    bottom: 47%;
			">
    </div>
	<div class="editContainer">
		
		<div style="width: 935px" class="editContent">
		 <s:set var="total">${resultCount}</s:set>     
        <h4 class="titular">Encontradas ${resultCount} entradas</h4>
    	
			
			<display:table 
				id="tableId"
				summary="Tabla de resultados de búsqueda de logs"
				name="listaGestionEnvios" 
				pagesize="${pageSize}"
				requestURI="viewMensajeReenvios.action"				
				defaultsort="1" 
				defaultorder="ascending" 
				sort="external"
				export="true" 
				cellpadding="0" 
				cellspacing="0" 
				partialList="true"
				
				
				size='<%=request.getAttribute("totalSize")%>'
				>
<%-- 				<display:setProperty name="css.tr.even" value="null" /> --%>
<%-- 				<display:setProperty name="css.tr.odd"  value="odd" /> --%>
<%-- 				<display:column class="darkTD TH17"> --%>
<!-- 					<center>					 -->
<%-- 					<input type="checkbox" onclick="checkBotonAccionSeleccionados()" id="checkDelList" name="checkDelList" value="${tableId.envioId}"/></center> --%>
					
<!-- 					<input type="hidden" idd="__checkbox_checkDelList" name="__checkbox_checkDelList"/> -->
<!-- 					</center> -->
<%-- 				</display:column>				 --%>
				<%-- operacion --%>
				<display:column property="aplicacion" titleKey="plataforma.gestionenvios.aplicacion" sortable="true"
					headerClass="TH130 separator center" class="gestionEnviosColumn" />
				<%-- entidad --%>
				<display:column property="servicio" titleKey="plataforma.gestionenvios.servicio" sortable="true"
					headerClass="TH110 separator center" class="gestionEnviosColumn" media="html"  />
				<%-- Id --%>
				<display:column property="loteEnvio"  titleKey="plataforma.gestionenvios.loteenvio" 
					sortable="true"	headerClass="TH130 separator center" class="gestionEnviosColumn"
					media="html" />	
				
					<display:column  property="idLote" titleKey="plataforma.gestionenvios.idLote" sortable="true" 
					headerClass="TH55 separator center" class="center" />
					<display:column  property="mensajeId" titleKey="plataforma.gestionenvios.idMensaje" sortable="true" 
					headerClass="TH55 separator center" class="center" />
					<display:column property="ultimoEnvioStr" titleKey="plataforma.gestionenvios.fecha" sortable="true"
					headerClass="TH100 separator center" class="TH100 center" />
					<display:column property="estado" titleKey="plataforma.gestionenvios.estado" sortable="true"
					headerClass="TH120 separator center" class="center" />
					<display:column property="destinatario"  titleKey="plataforma.gestionenvios.destinatario" 
					sortable="true"	headerClass="TH160 separator center" class="gestionEnviosColumn"
					media="html" />			

<%-- 				<display:column property="gestionEnviosAction"   --%>
<%-- 					headerClass="TH20 separator center" class="" media="html" /> --%>
			<plataforma:securityadmin usuarioLogueado="true" showIfGranted="true">
<%-- 				<display:column property="verMisimAction"   --%>
<%-- 					headerClass="TH35 separator center" class="" media="html" /> --%>
			</plataforma:securityadmin>
			</display:table>
		</div>
	</div>

	</div>
</sj:dialog>
<script>
$(function(){
    $('a').each(function() {
        if(this.href.includes("viewMensajeReenvios.action?")){
	        	var enl = this.href.split('viewMensajeReenvios.action')[1];
	        	
	            $(this).attr('href', 'javascript:verMensajesPaginar("viewMensajeReenvios.action' + enl + '")');
            }
        
    });
});
</script>