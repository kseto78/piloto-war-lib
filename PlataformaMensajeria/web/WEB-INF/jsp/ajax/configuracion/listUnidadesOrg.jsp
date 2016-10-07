<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>

<div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>

<!-- ************************** -->
<!-- Criterios de la busqueda   -->
<!-- ************************** -->
<div id="contenedor_principal">
	<s:form id="frmBuscarUnidadOrg" method="POST" action="buscarUnidadOrg" validate="false"
	theme="css_xhtml">
	<fieldset>
	<table summary="Tabla formulario criterios de búsqueda" cellpadding="0"
		cellspacing="4">
		<thead>
			<tr>
				<th headers="header0" colspan="2" align="left">
				<div class="titulobloque">
				<h2><s:text name="panels.template.unidades.search" /></h2>
				</div>
				</th>
			</tr>
		</thead>
		<tbody>


			<tr>
				<!-- Nombre -->
				<td headers="header0"><s:textfield
					name="unidadOrganizacional.nombre" id="unidadOrganizacional.nombre"
					theme="xhtml" key="field.general.nombre" labelposition="left"
					required="false" size="30" maxlength="255"
					cssClass="input_tablas_registro" /></td>
			</tr>
			<tr>
				<!-- Localidad -->
				<td headers="header0"><s:textfield
					name="unidadOrganizacional.localidad"
					id="unidadOrganizacional.localidad" theme="xhtml"
					key="field.configuracion.unidadesorg.localidad"
					labelposition="left" required="false" size="30" maxlength="255"
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
<div>    <s:submit theme="simple" value="%{getText('buttons.text.search')}" cssClass="boton_template" /> </div>
</s:form> 
		<!-- ********************************************************************* -->
		<!-- Tabla de resultados                                                   -->
		<!-- ********************************************************************* -->
		<div class="tabla_BD" title="Tabla resultados">
			<s:set name="listaUnidadesOrganizacionales" value="%{listaUnidadesOrganizacionales}" />
			<s:if test="#listaUnidadesOrganizacionales !=null">
			<display:table summary="Tabla de resultados de búsqueda de unidades organizacionales"
			name="listaUnidadesOrganizacionales" class="contenidonivel3 BD" pagesize="5"
			requestURI="" defaultsort="1" defaultorder="ascending" sort="list"
			export="true" cellpadding="0" cellspacing="0"
			decorator="es.mpr.template.web.decorators.TableWrapper">
			
				<%-- id --%>
				<display:column property="id" titleKey="field.organismo.idUnidadOrganizacional" 
					sortable="true"	headerClass="BD" class="BD" />

				<%-- nombre --%>

				<display:column property="nombre" titleKey="field.general.nombre" sortable="true"
					headerClass="BD" class="BD" />

				<%-- comunidad --%>

				<display:column property="comunidad" titleKey="field.test.ca" sortable="true"
					headerClass="BD" class="BD" />
					
				<%-- provincia --%>

				<display:column property="provincia" titleKey="field.test.prov" sortable="true"
					headerClass="BD" class="BD" />
					
				<%-- localidad --%>

				<display:column property="localidad" titleKey="field.configuracion.unidadesorg.localidad" 
					sortable="true"	headerClass="BD" class="BD" />
					
				 <%-- acciones --%>
				 <display:column property="unidadOrgAction" titleKey="panels.urls" 
				 	headerClass="BD" class="BD" media="html" />

          
				
			</display:table>
			</s:if>
	</div>
</div>

