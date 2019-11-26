<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<sj:dialog id="dialogOrganismosProveedores" title="Lista de Organismos en Proveedor SMS"
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
		 <s:set var="total">${resultCountOrganismos}</s:set>
		
		 <h4 class="titular">Encontradas ${resultCountOrganismos} entradas 
		 
         <s:a cssClass="button" onclick="makeExcellP(this)" style="position: absolute;right: 24px;">Exportar Excel</s:a>
          </h4>
          		
			<display:table 
				id="tableIdOrganismos"
				summary="Tabla de resultados de búsqueda de organismos"
				name="listaOrganismos" 
				
				class="" 
				pagesize="${pageSizeOrganismos}"
				requestURI="viewOrganismosProveedores.action" 
				defaultsort="1" 
				defaultorder="ascending" 
				sort="external"
				export="true" 
				cellpadding="0" 
				cellspacing="0" 
				partialList="true"
				size='<%=request.getAttribute("totalSizeOrganismos")%>'
				decorator="es.mpr.template.web.decorators.TableWrapper">
				<display:setProperty name="css.tr.even" value="null" />
				<display:setProperty name="css.tr.odd"  value="odd" />
				<%-- nombre --%>
				<display:column property="organismoId" titleKey="field.organismo.id" sortable="false"
					headerClass="TH30 separator center" class="center" />	
				<display:column property="organismoDir3" titleKey="field.organismo.DIR3" sortable="false"
					headerClass="TH100 separator center" class="" media="html"  />
				<display:column property="nombre" titleKey="field.organismo.nombre" sortable="false"
					headerClass="TH170 separator center" class="" />
				
				<%-- descripcion --%>
				<display:column property="estado" titleKey="field.organismo.estado" sortable="false"
					headerClass="TH70 separator center" class="center" />
				<display:column property="fechaEstadoStr" titleKey="field.organismo.fechaestado" sortable="false"
					headerClass="TH80 separator center" class="center" />
				<display:column property="codUnidadRaiz" titleKey="field.organismo.padre" sortable="false"
					headerClass="TH100 separator center" class="center" /> 
				<display:column property="isActivo" titleKey="field.organismo.activo" sortable="false"
					headerClass="TH50 separator center" class="center" /> 
				
			</display:table>
		</div>
	</div>

	</div>
</sj:dialog>
<script>
$(function(){
    $('a').each(function() {
		
        if(this.href.includes("viewOrganismosProveedores.action?")){
	        	var enl = this.href.split('viewOrganismosProveedores.action')[1];
	        	
	            $(this).attr('href', 'javascript:verMensajesPaginar("viewOrganismosProveedores.action' + enl + '")');
            }
        
    });
});

</script>