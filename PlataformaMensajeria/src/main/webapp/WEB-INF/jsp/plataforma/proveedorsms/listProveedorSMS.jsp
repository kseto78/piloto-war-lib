<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true" redirectTo="permisoDenegado"  allowedTo="ROL_ADMINISTRADOR,ROL_CAID">
	<script>
		document.location.href="permisoDenegado.action";
	</script>
</plataforma:securityRedirect>
<!-- <div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>-->

<!-- ************************** -->
<!-- Criterios de la busqueda   -->
<!-- ************************** -->
<div class="mainContent">
            <h3 class="pageNameButtons">
    			<span class="floatRight"></span>
	            <label>Proveedores SMS</label>
          	</h3>
	<%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp" %>
	<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp" %>
	<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp" %>
	<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp" %>	
	<sj:dialog  id="dialogOrganismosProveedores" title="LISTA ORGANISMOS PROVEEDORES" 	href="viewOrganismosProveedores.action" 
				cssStyle="display:none" autoOpen="false" modal="true"
				width="950"></sj:dialog>
            <div class="criteria">
		<s:form id="frmBuscarProveedorSMS" method="POST" action="buscarProveedorSMS"
		validate="false" theme="css_xhtml">            	
            	<p class="criteria">
                    <label class="fieldText" style="width: 100px;">Nombre:</label>
                    <s:textfield
							name="proveedorSMS.nombre" id="proveedorSMS.nombre"
							theme="simple" 
							key="field.general.nombre" cssStyle="width:500px;" labelposition="left"
							size="60" maxlength="255"
							cssClass="input_tablas_registro" />
                </p>
                <div class="footerCriteria">
                    <span class="leftSide"></span>
                    <span class="rightSide">
                       <s:submit  theme="simple" value="%{getText('buttons.text.search')}" cssClass="button"/>
                    </span>
                </div>
            </div>
           </s:form>
 
 		<s:set name="listaProveedoresSMS" value="%{listaProveedoresSMS}" />

		<s:form id="frmEliminarProveedoresSeleccionados" method="POST" onsubmit="return confirmDeleteSelected();" action="deleteProveedoresSMSSeleccionados">
		<h4 class="titular">Encontradas <%=request.getAttribute("totalSize")%> entradas</h4>
		   <table class="tablaHeader">
			<thead>
                	<tr> 
                    	<th class="superHeader" colspan="5">
                        	<div class="floatLeft"></div>
                            <div class="floatRight">
                            	<s:a cssClass="button" onclick="makeExcell(this)">Exportar Excel</s:a>
                            	<!-- <input type="button" class="button" value="Exportar Excel">-->
                            </div>
                        </th>
                    </tr>
                </thead>
               </table>
               <script>
               	function checkBotonEliminarSeleccionados(){
               		var listaChecks = document.getElementById('frmEliminarProveedoresSeleccionados').checkDelList;

               		var botonEliminarSeleccionados = document.getElementById('eliminaSeleccionados');
               		var enable=false;
               		if(listaChecks.checked){
               			enable=true;
               		}
               		for (i = 0; lcheck = listaChecks[i]; i++) {
               	        if (lcheck.checked) {
               	            enable=true;   
               	        }
               	    }
               		if(enable){
               			botonEliminarSeleccionados.disabled="";
               		}else{
               			botonEliminarSeleccionados.disabled="disabled";
               		}
               	}
               </script>
			<display:table 
				id="tableId"
				summary="Tabla de resultados de búsqueda de proveedores SMS"
				name="listaProveedoresSMS" 
				
				class="" 
				pagesize="${pageSize}"
				requestURI="" 
				defaultsort="1" 
				defaultorder="ascending" 
				sort="external"
				export="true" 
				cellpadding="0" 
				cellspacing="0" 
				partialList="true"
				size='<%=request.getAttribute("totalSize")%>'
				decorator="es.mpr.template.web.decorators.TableWrapper">
				<display:setProperty name="css.tr.even" value="null" />
				<display:setProperty name="css.tr.odd"  value="odd" />
				<display:column class="darkTD TH15">
					<center>
					
					<input type="checkbox" onclick="checkBotonEliminarSeleccionados()" id="checkDelList" name="checkDelList" value="${tableId.proveedorSMSId }"/></center> 
					<input type="hidden" idd="__checkbox_checkDelList" name="__checkbox_checkDelList"/>
 				
				</display:column>
				<display:column property="proveedorSMSId" titleKey="field.proveedorSMS.id" sortable="false"
					headerClass="TH30 separator center" class="center" /> 
				<%-- nombre --%>
				<display:column property="nombre" titleKey="field.proveedorSMS.nombre" sortable="true"
					headerClass="TH170 separator center" class="" />
				
				<%-- descripcion --%>
				<display:column property="descripcion" titleKey="field.proveedorSMS.descripcion" sortable="false"
					headerClass="separator center" class="" />
				<display:column property="isDefecto" titleKey="field.proveedorSMS.porDefecto" sortable="false"
					headerClass="TH50 separator center" class="center" />					
				<display:column property="isActivo" titleKey="field.proveedorSMS.activo" sortable="false"
					headerClass="TH50 separator center" class="center" />
				<%-- acciones --%>
				<display:column property="proveedorSMSAction"  
					headerClass="TH80 separator center" class="" media="html" />
			</display:table>
			<table>
			<tfoot>
				<tr>
                    	<td colspan="5">
                        	<span class="leftSide">
                            	<!-- <input type="button" class="button" value="Eliminar Seleccionados">-->
                            	<s:submit  id="eliminaSeleccionados" theme="simple" disabled="true" value="%{getText('button.plataforma.eliminarseleccionados')}" cssClass="button"/>
                            </span>
		                    <span class="rightSide">
                            	
                            	<input  type="button" value="Nueva Entrada" class="button" onclick='javascript:location.href="nuevoProveedorSMS.action"'/>
                            </span>
                        </td>
                    </tr>
               </tfoot>
                   </table>
            </s:form>
 <script>
 function loadOrganismo(idProveedorSMS){

	 var auxHTML = $('div#ajaxloader_ajax_'+idProveedorSMS).html();
		$('div#ajaxloader_ajax_'+idProveedorSMS).html("<p><img src=\"/sim/img/ajax-loader.gif\" width=\"auto\" height=\"auto\"/></p>");

		var url = "viewOrganismosProveedores.action?idProveedorSMS=" + idProveedorSMS;
	 	var dialogOrganismosProveedores = $('#dialogOrganismosProveedores');
	 	dialogOrganismosProveedores.load(
	    		url,
	    		{
		    			autoOpen: true,
		    			width: 510,
		    			modal: true,
		    			show: 'blind',
		    			hide: 'blind'
		    		},
	            function(responseText, textStatus, XMLHttpRequest) {
		    			dialogOrganismosProveedores.dialog({
		           	 			autoOpen: true,
		           				width: 950,
		           				modal: true,
		           				show: 'blind',
		           				hide: 'blind',
		           			});
		    			$('div#ajaxloader_ajax_'+idProveedorSMS).html(auxHTML);        	    		
	            }

	    );
	    return false;	
	 }
 function verMensajesPaginar(url){
		document.getElementById('loading').style.visibility="";
		$('td').find('a').each(function(){
			 $(this).addClass('disabled-link');
			});

			$('.disabled-link').on('click', false);
		var dialogOrganismosProveedores = $('#dialogOrganismosProveedores');
		dialogOrganismosProveedores.load(
	    		url,
	    		{
		    			
		    		},
	            function(responseText, textStatus, XMLHttpRequest) {
		    			dialogOrganismosProveedores.dialog({
		           	 			autoOpen: true,
		           				width: 950,
		           				modal: true,
		           				show: 'blind',
		           				hide: 'blind',
		           			});	    			       	    		
	            }

	    );
	    return false;	
	}
 function makeExcellP(enlace){
	 	var elms = document.querySelectorAll("[id='exportOptions']");
	 
		var div = elms[1];

		var en = div.getElementsByTagName('a')[0].getAttribute('href');
		var split = div.getElementsByTagName('a')[0].getAttribute('href').split('"');
		div.getElementsByTagName('a')[0].setAttribute('href',split[1]);		
		
		var elms = div.getElementsByTagName("a");
		var maxI=0;
		for(var i = 0, maxI = elms.length; i < maxI; ++i) {
			var a = elms[i];
			enlace.href=a.href;
		}
	}
 </script>
