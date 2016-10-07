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
        <%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp" %>	
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp" %>
            <div class="criteria">
		<s:form id="frmBuscarUsuarios" method="POST" action="buscarUsuarios"
		validate="false" theme="css_xhtml"> 
		          <p class="criteria">
                    <label class="fieldText" style="width: 100px;">Nombre:</label>
                    <s:textfield
							name="usuario.nombre" id="usuario.nombre"
							theme="simple" 
							key="field.general.nombre" labelposition="left"
							required="false" size="100" cssStyle="width:530px;" maxlength="255"
							cssClass="input_tablas_registro" />
                </p>           	
                   <p class="criteria">
                     <span>
                       <label style="width: 100px;" class="fieldText">Aplicacion:</label>
                       <s:select cssStyle="width:200px;"
						id="usuario.aplicacionId" name="usuario.aplicacionId" 
						emptyOption="false" theme="simple" 
						labelposition="left"
						list="comboAplicaciones" listKey="codigo"
						listValue="descripcion" cssClass="" headerKey="-1" headerValue="Todos"
						value="%{usuario.aplicacionId}" disabled="false" />
           	      	</span>
           	      	<span>
                       <label style="width: 100px;" class="fieldText">Rol:</label>
                       <s:select cssStyle="width:150px;"
						id="usuario.rolId" name="usuario.rolId" 
						emptyOption="false" theme="simple" 
						labelposition="left"
						list="comboRoles" listKey="codigo"
						listValue="descripcion" cssClass="" headerKey="-1" headerValue="Todos"
						value="%{usuario.rolId}" disabled="false" />           	      	
           	      	</span>
                <div class="footerCriteria">
                    <span class="leftSide"></span>
                    <span class="rightSide">
                       <s:submit  theme="simple" value="%{getText('buttons.text.search')}" cssClass="button"/>
                    </span>
                </div>
            </div>
           </s:form>
 
 		<s:set name="listaUsuarios" value="%{listaUsuarios}" />

		<s:form id="frmEliminarUsuariosSeleccionados" method="POST" onsubmit="return confirmDeleteSelected();" action="deleteUsuariosSeleccionados">
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
               		var listaChecks = document.getElementById('frmEliminarUsuariosSeleccionados').checkDelList;

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
				summary="Tabla de resultados de búsqueda de usuarios"
				name="listaUsuarios" 
				
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
				<display:column class="darkTD TH15">
					<center>
					
					<input type="checkbox" onclick="checkBotonEliminarSeleccionados()" id="checkDelList" name="checkDelList" value="${tableId.usuarioId }"/></center> 
					<input type="hidden" idd="__checkbox_checkDelList" name="__checkbox_checkDelList"/>
 				
				</display:column>
				<display:column property="usuarioId" titleKey="field.usuario.id" sortable="false"
					headerClass="TH30 separator center" class="center" />				
				<%-- nombre usuario--%>
				<display:column property="nombre" titleKey="field.usuario.usuario" sortable="true"
					headerClass="separator center" class="" />
				
				<%-- nombre rol --%>
				<display:column property="rolUsuario" titleKey="field.usuario.rolUsuario" sortable="true"
					headerClass="TH170 separator center" class="" />
				<%--><display:column property="nombreAplicacion" titleKey="field.usuario.nombreAplicacion" sortable="false"
					headerClass="separator" class="" />
				--%>					
				<%-- acciones --%>
				<display:column property="usuarioAction"  
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
                            	<input  type="button" value="Nueva Entrada" class="button" onclick='javascript:location.href="nuevoUsuario.action"'/>
                            </span>
                        </td>
                    </tr>
               </tfoot>
                   </table>
            </s:form>
