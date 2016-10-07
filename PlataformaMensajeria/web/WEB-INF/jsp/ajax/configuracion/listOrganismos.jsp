<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>

<div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>

<!-- ************************** -->
<!-- Criterios de la busqueda   -->
<!-- ************************** -->
<div id="contenedor_principal">
	<s:form id="frmBuscarOrganismos" method="POST" action="buscarOrganismo"
		validate="false" theme="css_xhtml">
		<fieldset>
			<table summary="Tabla formulario criterios de búsqueda" cellpadding="0" cellspacing="4">
				<thead>
					<tr>
						<th headers="header0" colspan="2" align="left">
							<div class="titulobloque">
								<h2><s:text name="panels.template.organismos.search" /></h2>
							</div>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<!-- Nombre -->
						<td headers="header0"><s:textfield
							name="organismo.nombre" id="organismo.nombre"
							theme="xhtml" 
							key="field.general.nombre" labelposition="left"
							required="false" size="30" maxlength="255"
							cssClass="input_tablas_registro" /></td>
					</tr>
				</tbody>
			</table>
		</fieldset>
		
		<br>
		<!-- ****************************************************************************** -->
		<!-- Botones																		-->
		<!-- ****************************************************************************** -->
	
		&nbsp;
		<div>
			<s:submit theme="simple" value="%{getText('buttons.text.search')}" cssClass="boton_template"/>
		</div>

	</s:form>

	<!-- ********************************************************************* -->
	<!-- Tabla de resultados                                                   -->
	<!-- ********************************************************************* -->
	<div class="tabla_BD" title="Tabla resultados">
		<s:set name="listaOrganismos" value="%{listaOrganismos}" />

		<s:if test="#listaOrganismos !=null">

			<display:table 
				id="tableId"
				summary="Tabla de resultados de búsqueda de organismos"
				name="listaOrganismos" 
				class="contenidonivel3 BD" 
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
				<%-- id --%>
				<display:column property="id" titleKey="field.organismo.idOrganismo" sortable="true"
					headerClass="BD" class="BD" />
				
				<%-- nombre --%>
				<display:column property="nombre" titleKey="field.organismo.nombreOrganismo" sortable="true"
					headerClass="BD" class="BD" />
				
				<%-- rol --%>
				<display:column property="rol" titleKey="field.organismo.rol" sortable="true"
					headerClass="BD" class="BD" />
				
				<%-- Organismo Padre --%>
				<display:column property="organismoPadre" titleKey="field.organismo.organismoPadre" 
					sortable="true" headerClass="BD" class="BD" />
				
				<%-- acciones --%>
				<display:column property="organismoAction" titleKey="panels.urls" 
					headerClass="BD" class="BD" media="html" />
			</display:table>

		</s:if>

	</div>
	
</div>
