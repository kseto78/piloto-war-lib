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
	            <label>Proveedores MISIM</label>
          	</h3> 	
		<%@include file="/WEB-INF/jsp/plataforma/validation/validadoresMisimJS.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp" %>
            <div class="criteria">
<s:form id="frmBuscarProveedoresMisim" method="POST" action="buscarProveedoresMisim"
		validate="false" theme="css_xhtml">            	
            	<p class="criteria">
                    <label class="fieldText" style="width: 100px;">Nombre:</label>
                    <s:textfield
							name="proveedorMisim.nombre" id="proveedorMisim.nombre"
							theme="simple" 
							key="field.general.nombre" cssStyle="width:500px;" labelposition="left"
							size="60" maxlength="255" cssClass="input_tablas_registro" />
                </p>
                <div class="footerCriteria">
                    <span class="leftSide"></span>
                    <span class="rightSide">
                       <s:submit  theme="simple" value="%{getText('buttons.text.search')}" cssClass="button"/>
                    </span>
                </div>
                   </s:form>
            </div>
 
 
 		<s:set name="listaProveedoresMisim" value="%{listaProveedoresMisim}" />


		<s:form id="frmEliminarProveedoresMisimSeleccionados" method="POST" onsubmit="return confirmDeleteSelected();" action="deleteProveedoresMisimSeleccionados">
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
               		var listaChecks = document.getElementById('frmEliminarProveedoresMisimSeleccionados').checkDelList;

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
				summary="Tabla de resultados de búsqueda de proveedores de Misim"
				name="listaProveedoresMisim" 
				
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
					
					<input type="checkbox" onclick="checkBotonEliminarSeleccionados()" id="checkDelList" name="checkDelList" value="${tableId.idProveedor }"/></center> 
					<input type="hidden" idd="__checkbox_checkDelList" name="__checkbox_checkDelList"/>
 				
				</display:column>
				<%-- nombre --%>
				<display:column property="idProveedor" titleKey="field.proveedorMisim.id" sortable="false"
					headerClass="TH30 separator center" class="center" />			
				<display:column property="nombre" titleKey="field.proveedorMisim.nombre" sortable="true"
					headerClass="TH300 separator center" class="center" />
				
				<display:column property="producto.nombre" titleKey="field.proveedorMisim.producto" sortable="false"
 					headerClass="separator center" class="center" />

				<%-- acciones --%>
				<display:column property="proveedorMisimAction" titleKey="field.proveedorMisim.proveedorMisimAction"
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
                            	<input  type="button" value="Nueva Entrada" class="button" onclick='javascript:location.href="nuevoProveedorMisim.action"'/>
                            </span>
                        </td>
                    </tr>
               </tfoot>
                   </table>
            </s:form>
