<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true" redirectTo="permisoDenegado"  allowedTo="ROL_ADMINISTRADOR">
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
	            <label>Servicios</label>
          	</h3>  
        <%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp" %>	
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp" %>
            
        <s:form id="frmBuscarServicios" method="POST" action="buscarServicios" validate="false" theme="css_xhtml">            	
            <div class="criteria">
            		<p class="criteria">
                    	<label class="fieldText" style="width: 150px;">Identificador del servicio:</label>
                    	<s:textfield
							name="servicio.servicioId" id="servicio.servicioId"
							theme="simple" 
							key="field.general.id" labelposition="left"
							size="9" maxlength="60"
							cssClass="input_tablas_registro" />
               	   </p>
                   <p class="criteria">
                       <label style="width: 150px;" class="fieldText">Aplicaciones:</label>
                       <s:select
						id="servicio.aplicacionid" name="servicio.aplicacionid" 
						emptyOption="false" theme="simple" 
						labelposition="left"
						list="comboAplicaciones" listKey="codigo"
						listValue="descripcion" cssClass="" headerKey="" headerValue="Todas"
						value="%{servicio.aplicacionid}" disabled="false" />
           	      </p> 
            	<p class="criteria">
                    <label class="fieldText" style="width: 150px;">Nombre:</label>
                    <s:textfield
							name="servicio.nombre" id="servicio.nombre"
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
 
 		<s:set name="listaServicios" value="%{listaServicios}" />

		
		<s:form id="frmEliminarServiciosSeleccionados" method="POST" onsubmit="return confirmDeleteSelected();" action="deleteServiciosSeleccionados">
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
               		var listaChecks = document.getElementById('frmEliminarServiciosSeleccionados').checkDelList;

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
				summary="Tabla de resultados de búsqueda de servicios"
				name="listaServicios" 
				
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
					
					<input type="checkbox" onclick="checkBotonEliminarSeleccionados()" id="checkDelList" name="checkDelList" value="${tableId.servicioId }"/></center> 
					<input type="hidden" id="__checkbox_checkDelList" name="__checkbox_checkDelList"/>
 				
				</display:column>
				<display:column property="servicioId" titleKey="field.servicio.id" sortable="false"
					headerClass="TH30 separator center" class="center" />
				<%-- nombre aplicacion--%>
				<display:column property="aplicacionnombre" titleKey="field.servicio.aplicacionNombre" sortable="true"
					headerClass="TH170 separator center" class="" />
				
				<%-- nombre servicio --%>
				<display:column property="nombre" titleKey="field.servicio.nombre" sortable="true"
					headerClass="separator center" class="" />
				<%-- nombre descripcion --%>
				<display:column property="descripcion" titleKey="field.servicio.descripcion" sortable="false"
					headerClass="separator center" class="" />	
				<%-- activo --%>				
				<display:column property="isActivo" titleKey="field.servicio.activo" sortable="false"
					headerClass="TH50 separator center" class="center" />
				<%-- pendiente aprobacion --%>	
				<display:column property="isPendienteAprobacion" titleKey="field.servicio.pendienteAprobacion" sortable="false"
					headerClass="TH50 separator center" class="center" />
				<%-- acciones --%>
				<display:column property="servicioAction"  
					headerClass="TH45 separator center" class="" media="html" />
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
                            	<input  type="button" value="Nueva Entrada" class="button" onclick='javascript:location.href="nuevoServicio.action"'/>
                            </span>
                        </td>
                    </tr>
               </tfoot>
                   </table>
            </s:form>
		
