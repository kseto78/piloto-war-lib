<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>

<div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>

<!-- ************************** -->
<!-- Criterios de la busqueda   -->
<!-- ************************** -->
<div id="contenedor_principal">
	<s:form id="frmBuscarGrupos" method="GET" action="adminBuscarGrupos"
		validate="false" theme="css_xhtml"
		>
	<fieldset>
			<table summary="Tabla formulario criterios de búsqueda" cellpadding="0" cellspacing="4">
			<thead>
				<tr>
					<th headers="header0" colspan="2" align="left">
						<div class="titulobloque">
							<h2><s:text name="panels.template.grupos.search" /></h2>
						</div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<!-- Nombre -->
					<td headers="header0"><s:textfield
						name="form.name" theme="xhtml" 
						key="field.grupo.nombreGrupo" labelposition="left"
						required="false" size="20" maxlength="15"
						cssClass="input_tablas_registro" /></td>
				</tr>
				<tr>
					<!-- Descripcion -->
					<td headers="header0"><s:textfield
						name="form.description" theme="xhtml" 
						key="field.grupo.descripcion" labelposition="left"
						required="false" size="45" maxlength="45"
						cssClass="input_tablas_registro" /></td>
				</tr>
			</tbody>
			</table>
	</fieldset>
	
	<br>

	<!-- ********************************************************************* -->
	<!-- Botones 															   -->
	<!-- ********************************************************************* -->

&nbsp;
<div>    <s:submit theme="simple" value="%{getText('buttons.text.search')}" cssClass="boton_template" /> </div>

	</s:form>
	<!-- ********************************************************************* -->
	<!-- Tabla de resultados                                                   -->
	<!-- ********************************************************************* -->
	<div class="tabla_BD" title="Tabla resultados">
	<s:if test="#request.listaGrupos != null">
		<display:table summary="Tabla de resultados de búsqueda de grupos"
			name="listaGrupos" class="contenidonivel3 BD" pagesize="5"
			requestURI="" defaultsort="1" defaultorder="ascending" sort="list"
			export="true" cellpadding="0" cellspacing="0" frame="_blank" 
			decorator="es.mpr.template.web.decorators.TableWrapper">
	
			<%-- nombre --%>
			<display:column property="name" titleKey="field.grupo.nombreGrupo" sortable="true"
				headerClass="BD" class="BD" />
	
			<%-- descripcion --%>
			<display:column property="description" titleKey="field.grupo.descripcion"
				sortable="true" headerClass="BD" class="BD" />
	
			<%-- acciones --%>
			<display:column property="grupoAction" titleKey="panels.urls"
				headerClass="BD" class="BD" media="html"/>
	
		</display:table>
	</s:if>
	</div>
</div>