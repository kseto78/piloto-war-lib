<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true"
	redirectTo="permisoDenegado" allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href = "permisoDenegado.action";
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
		<span class="floatRight"></span> <label>Contactos</label>
	</h3>
	<%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp"%>
	<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp"%>
	<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp"%>
	<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp"%>


	<!-- 		          Cuadro de Busqueda del JSP    -->
		<div class="criteria">
	 		<s:form id="frmBuscarContactos" method="POST" action="listarContactos" 
	 			validate="false" theme="css_xhtml"> 
				<p class="criteria">
					<label class="fieldText" style="width: 100px;">Nombre:</label>
	  				<s:textfield name="contacto.nombre" id="contacto.nombre"  
	 					theme="simple" key="field.general.nombre" labelposition="left" 
	  					size="100" cssStyle="width:530px;" maxlength="255" 
						cssClass="input_tablas_registro" />  
				</p>
				<p class="criteria">
					<label class="fieldText" style="width: 100px;">Apellidos:</label>
	  				<s:textfield name="contacto.apellidos" id="contacto.apellidos"  
	 					theme="simple" key="field.general.apellidos" labelposition="left" 
	  					size="100" cssStyle="width:530px;" maxlength="255" 
						cssClass="input_tablas_registro" />  
				</p> 
				<p class="criteria"> 
					<span> <label style="width: 100px;" class="fieldText">Aplicacion:</label> 
	  					<s:select cssStyle="width:200px;" id="contacto.aplicacionid" 
	 						name="contacto.aplicacionid" emptyOption="false" theme="simple" 
	 						labelposition="left" list="comboAplicaciones" listKey="codigo" 
	  						listValue="descripcion" cssClass="" headerKey=""  
	  						headerValue="Todos" value="%{contacto.aplicacionid}"  
	 						disabled="false" /> 
	 				</span>
	 				  
	 			<div class="footerCriteria"> 
	 				<span class="leftSide"></span> <span class="rightSide"> <s:submit 
	  						theme="simple" value="%{getText('buttons.text.search')}"  
	 						cssClass="button" /> 
					</span> 
	 			</div> 
	  		</s:form>  
	  	</div>  

	<!-- 	Lista de objetos recuperado del action -->
	<s:set name="listaContactos" value="%{listaContactos}" />

	<s:form id="frmEliminarContactosSeleccionados" method="POST"
		onsubmit="return confirmDeleteSelected();"
		action="deleteContactosSeleccionados">
		<h4 class="titular">
			Encontradas
			<%=request.getAttribute("totalSize")%>
			entradas
		</h4>
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
			function checkBotonEliminarSeleccionados() {
				var listaChecks = document
						.getElementById('frmEliminarContactosSeleccionados').checkDelList;

				var botonEliminarSeleccionados = document
						.getElementById('eliminaSeleccionados');
				var enable = false;
				if (listaChecks.checked) {
					enable = true;
				}
				for (i = 0; lcheck = listaChecks[i]; i++) {
					if (lcheck.checked) {
						enable = true;
					}
				}
				if (enable) {
					botonEliminarSeleccionados.disabled = "";
				} else {
					botonEliminarSeleccionados.disabled = "disabled";
				}
			}
		</script>
		<!-- 		TABLA DE CONTENIDO DE LA BUSQUEDA -->
		<display:table id="tableId"
			summary="Tabla de resultados de búsqueda de contactos"
			name="listaContactos" class="" pagesize="${pageSize}" requestURI=""
			defaultsort="1" defaultorder="ascending" sort="external"
			export="true" cellpadding="0" cellspacing="0" partialList="true"
			size='<%=request.getAttribute("totalSize")%>'
			decorator="es.mpr.template.web.decorators.TableWrapper">
			<display:setProperty name="css.tr.even" value="null" />
			<display:setProperty name="css.tr.odd" value="odd" />
			<display:setProperty name="basic.empty.showtable" value="true" />
			<display:column class="darkTD TH15">
				<center>

					<input type="checkbox" onclick="checkBotonEliminarSeleccionados()"
						id="checkDelList" name="checkDelList"
						value="${tableId.contactoId }" />
				</center>
				<input type="hidden" idd="__checkbox_checkDelList"
					name="__checkbox_checkDelList" />

			</display:column>
			<%-- organismo--%>
			<display:column property="organismo"
				titleKey="field.contacto.organismo" sortable="false"
				headerClass="TH30 separator center" class="center" />

			<%-- nombre aplicacion--%>
			<display:column property="aplicacion"
				titleKey="field.contacto.nombreAplicacion" sortable="true"
				headerClass="separator center" class="" />

			<%-- nombre servicio--%>
			<display:column property="servicio"
				titleKey="field.contacto.nombreServicio" sortable="true"
				headerClass="separator center" class="" />

			<%-- nombre --%>
			<display:column property="nombre" titleKey="field.contacto.nombre"
				sortable="true" headerClass="separator center" class="" />

			<%-- apellidos --%>
			<display:column property="apellidos"
				titleKey="field.contacto.apellidos" sortable="true"
				headerClass="separator center" class="" />

			<%-- correo --%>
			<display:column property="email" titleKey="field.contacto.email"
				sortable="true" headerClass="separator center" class="" />

			<%-- telefono --%>
			<display:column property="telefono"
				titleKey="field.contacto.telefono" sortable="false"
				headerClass="separator center" class="" />

			<%-- acciones --%>
						<display:column property="contactosAction"
			 				headerClass="TH45 separator center" class="" media="html" /> 
		</display:table>
		<table>
			<tfoot>
				<tr>
					<td colspan="7"><span class="leftSide"> <s:submit
								id="eliminaSeleccionados" theme="simple" disabled="true"
								value="%{getText('button.plataforma.eliminarseleccionados')}"
								cssClass="button" />
					</span> <span class="rightSide"> <input type="button"
							value="Nueva Entrada" class="button"
							onclick='javascript:location.href="nuevoContacto.action"' />
					</span></td>
				</tr>
			</tfoot>
		</table>
	</s:form>
</div>