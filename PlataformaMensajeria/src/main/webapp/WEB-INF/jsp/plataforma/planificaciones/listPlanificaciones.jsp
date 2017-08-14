<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true" redirectTo="permisoDenegado"  allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href="permisoDenegado.action";
	</script>
</plataforma:securityRedirect>

<script>
function setValue(obj){
	var value = obj.value;
	document.getElementById('planificacion.servicioId').value=value;
}
function makeRequest(){
	document.getElementById('planificacion.servicioId').value='';
	  $('#sid option').each(function() {
		        $(this).remove();
		});
	$.ajax({
        type: "POST",
        url: "ajaxLoadServicios.action",
        data: {idAplicacion:document.getElementById('planificacion.aplicacionId').value}, // serializes the form's elements.
        success: function(data)
        {
     	  var items = data.items;
     	  $('#sid').append($('<option>', { 
     	        value: '',
     	        text : 'Todos' 
     	    }));
     	 $.each(items, function (i, item) {
     		$('#sid').append($('<option>', { 
     	        value: item.value,
     	        text : item.text 
     	    }));
     	});
        },
        error: function(data)
        {
     	   alert("error..."); 
        }
      });
}
function checkServicio(){
	document.getElementById('planificacion.servicioId').value=document.getElementById('sid').value;
	return true;
}
</script/>
<!-- <div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>-->

<!-- ************************** -->
<!-- Criterios de la busqueda   -->
<!-- ************************** -->
        <div class="mainContent">
        <%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp" %>
            <div class="criteria">
		<s:form id="frmBuscarPlanificaciones" method="POST" action="buscarPlanificaciones"
		validate="false" theme="css_xhtml">            	
                   <p class="criteria">
                     <span>
                       <label style="width: 120px;" class="fieldText">Aplicaciones:</label>
                       <s:select cssStyle="width:200px;"
						id="planificacion.aplicacionId" name="planificacion.aplicacionId" onchange="makeRequest()" 
						emptyOption="false" theme="simple" 
						labelposition="left"
						list="comboAplicaciones" listKey="codigo"
						listValue="descripcion" cssClass=""  headerKey="" headerValue="Todas"
						value="%{planificacion.aplicacionId}" disabled="false" />
           	      	</span>
           	      	<span>
                       <label style="width: 120px;" class="fieldText">Servicios:</label>
                       <s:select cssStyle="width:200px;"
						id="sid" name="sid" onchange="setValue(this)"
						emptyOption="false" theme="simple" 
						labelposition="left"
						list="comboServicios" listKey="codigo"
						listValue="descripcion" cssClass="" headerKey="" headerValue="Todos"
						value="%{planificacion.servicioId}" disabled="false" />           	      	
           	      	</span>
           	      </p> 	
	               	 <p class="criteria">
	                     	<span>
	                            <label class="fieldText" style="width: 120px;">Días de la semana:</label>
	                            L
	                            <s:checkbox id="planificacion.lunes" name="planificacion.lunes" value="%{planificacion.lunes}" theme="simple"/>
	                            M
	                            <s:checkbox id="planificacion.martes" name="planificacion.martes" value="%{planificacion.martes}" theme="simple"/>                          
	                            X
	                            <s:checkbox id="planificacion.miercoles" name="planificacion.miercoles" value="%{planificacion.miercoles}" theme="simple"/>                         
	                            J
	                            <s:checkbox id="planificacion.jueves" name="planificacion.jueves" value="%{planificacion.jueves}" theme="simple"/>                        
	                            V
	                            <s:checkbox id="planificacion.viernes" name="planificacion.viernes" value="%{planificacion.viernes}" theme="simple"/>  
	                            S
	                            <s:checkbox id="planificacion.sabado" name="planificacion.sabado" value="%{planificacion.sabado}" theme="simple"/>   
	                            D
	                            <s:checkbox id="planificacion.domingo" name="planificacion.domingo" value="%{planificacion.domingo}" theme="simple"/>              
							</span>
	                   <p class="criteria">
	                 	<span>
	                    	<label class="fieldText" style="width: 120px;">Hora Inicio:</label>
					                   Desde	 <s:select 
												id="planificacion.horaDesde" name="planificacion.horaDesde" 
												emptyOption="false" theme="simple" 
												labelposition="left"
												headerKey="" headerValue=""
												list="comboHorasInicio" listKey="codigo"
												listValue="descripcion" cssClass="W65" 
												value="%{planificacion.horaDesde}" disabled="false" />
					                    Hasta <s:select 
												id="planificacion.horaHasta" name="planificacion.horaHasta" 
												emptyOption="false" theme="simple" 
												labelposition="left"
												headerKey="" headerValue=""
												list="comboHorasFin" listKey="codigo"
												listValue="descripcion" cssClass="W65" 
												value="%{planificacion.horaHasta}" disabled="false" />
	                    </span>
	                    <span>
	                    	<label class="fieldText" style="width: 120px;">Hora Fin:</label>
	                        Desde	 <s:select 
												id="planificacion.horaDesdeFin" name="planificacion.horaDesdeFin" 
												emptyOption="false" theme="simple" 
												labelposition="left"
												list="comboHorasInicio" listKey="codigo"
												headerKey="" headerValue=""
												listValue="descripcion" cssClass="W65" 
												value="%{planificacion.horaDesdeFin}" disabled="false" />
					                    Hasta <s:select 
												id="planificacion.horaHastaFin" name="planificacion.horaHastaFin" 
												emptyOption="false" theme="simple"
												headerKey="" headerValue="" 
												labelposition="left"
												list="comboHorasFin" listKey="codigo"
												listValue="descripcion" cssClass="W65" 
												value="%{planificacion.horaHastaFin}" disabled="false" />
	                    </span>
	           	      </p> 
                   <p class="criteria">
                     <span>
                       <label style="width: 120px;" class="fieldText">Canal:</label>
                       <s:select cssStyle="width:200px;"
						id="planificacion.canalId" name="planificacion.canalId" 
						emptyOption="false" theme="simple" 
						labelposition="left"
						list="comboCanales" listKey="codigo"
						listValue="descripcion" cssClass=""  headerKey="" headerValue="Todos"
						value="%{planificacion.canalId}" disabled="false" />
           	      	</span>
           	      	<span>
                       <label style="width: 120px;" class="fieldText">Servidor:</label>
                       <s:select cssStyle="width:200px;"
						id="planificacion.servidorId" name="planificacion.servidorId" 
						emptyOption="false" theme="simple" 
						labelposition="left"
						list="comboBusquedaServidores" listKey="codigo"
						listValue="descripcion" cssClass="" headerKey="" headerValue="Todos"
						value="%{planificacion.servidorId}" disabled="false" />           	      	
           	      	</span>
           	      </p> 	                
                <div class="footerCriteria">
                    <span class="leftSide"></span>
                    <span class="rightSide">
                       <s:submit  theme="simple" value="%{getText('buttons.text.search')}" cssClass="button"/>
                    </span>
                </div>
            </div>
            <s:hidden id="planificacion.servicioId" name="planificacion.servicioId" value="%{planificacion.servicioId}"/>
           </s:form>
 
 		<s:set name="listaPlanificaciones" value="%{listaPlanificaciones}" />

		<s:form id="frmEliminarPlanificacionesSeleccionadas" method="POST" action="deletePlanificacionesSeleccionadas" onsubmit="return confirmDeleteSelected();">
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
               		var listaChecks = document.getElementById('frmEliminarPlanificacionesSeleccionadas').checkDelList;

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
				summary="Tabla de resultados de búsqueda de planificaciones"
				name="listaPlanificaciones" 
				
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
				<display:setProperty name="basic.empty.showtable" value="true"/>
				<display:column class="darkTD TH15" media="html">
					<center>
					
					<input type="checkbox" onclick="checkBotonEliminarSeleccionados()" id="checkDelList" name="checkDelList" value="${tableId.planificacionId }"/></center> 
					<input type="hidden" idd="__checkbox_checkDelList" name="__checkbox_checkDelList"/>
 				
				</display:column>
				
				<display:column property="planificacionId" titleKey="field.planificacion.id" sortable="false"
					headerClass="TH30 separator center" class="center" />				
				<%-- nombre aplicacion--%>
				<display:column property="nombreAplicacion" titleKey="field.planificacion.nombreAplicacion" sortable="true"
					headerClass="TH170 separator center" class="" />
				
				<%-- nombre servicio --%>
				<display:column property="nombreServicio" titleKey="field.planificacion.nombreServicio" sortable="true"
					headerClass="separator center" class="" />
				<%-- nombre descripcion --%>
				<display:column property="dias" titleKey="field.planificacion.diasSemana" sortable="false"
					headerClass="separator center" class="" />					
				<display:column property="horaDesde" titleKey="field.planificacion.horaDesde" sortable="false"
					headerClass="separator center" class="center" />
				<display:column property="horaHasta" titleKey="field.planificacion.horaHasta" sortable="false"
					headerClass="separator center" class="center" />
				<display:column property="isActivo" titleKey="field.planificacion.activo" sortable="false"
					headerClass="separator center" class="center" />
				<%-- acciones --%>
				<display:column property="planificacionAction"  
					headerClass="TH45 separator" class="" media="html" />
			</display:table>
			<table>
			<tfoot>
				<tr>
                    	<td colspan="5">
                        	<span class="leftSide">
                            	<!-- <input type="button" class="button" value="Eliminar Seleccionados">-->
                            	<s:submit  id="eliminaSeleccionados" theme="simple" disabled="true"  value="%{getText('button.plataforma.eliminarseleccionados')}" cssClass="button"/>
                            </span>
		                    <span class="rightSide">
		                    </span>
                        </td>
                    </tr>
               </tfoot>
                   </table>
            </s:form>
