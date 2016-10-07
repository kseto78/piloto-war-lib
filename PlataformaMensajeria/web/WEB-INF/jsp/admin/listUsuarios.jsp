<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>


<div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>

<!-- ************************** -->
<!-- Criterios de la busqueda   -->
<!-- ************************** -->
<div id="contenedor_principal">
	<s:form id="frmBuscarUsuarios" method="GET" action="adminBuscarUsuarios"
		 theme="css_xhtml" validate="false">
	<fieldset>
			<table summary="Tabla formulario criterios de búsqueda" cellpadding="0" cellspacing="4">
			<thead>
				<tr>
					<th headers="header0" colspan="2" align="left">
						<div class="titulobloque">
							<h2><s:text name="panels.template.usuarios.search" /></h2>
						</div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<!-- Id Usuario -->
					<td headers="header0"><s:textfield
						name="form.username" id="form.username"
						theme="xhtml" 
						key="field.usuario.username" labelposition="left"
						required="false" size="10" maxlength="10"
						cssClass="input_tablas_registro" /></td>
				</tr>
				<tr>
					<!-- Nombre -->
					<td headers="header0"><s:textfield
						name="form.nombre" theme="xhtml" 
						key="field.usuario.nombre" labelposition="left"
						required="false" size="20" maxlength="15"
						cssClass="input_tablas_registro" /></td>
				</tr>
				<tr>
					<!-- Apellidos -->
					<td headers="header0"><s:textfield
						name="form.apellidos" theme="xhtml" 
						key="field.usuario.apellidos" labelposition="left"
						required="false" size="20" maxlength="20"
						cssClass="input_tablas_registro" /></td>
				</tr>
				<tr>
					<!-- Descripcion -->
					<td headers="header0"><s:textfield
						name="form.description" theme="xhtml" 
						key="field.usuario.descripcion" labelposition="left"
						required="false" size="45" maxlength="45"
						cssClass="input_tablas_registro" /></td>
				</tr>
				<tr>
					<!-- Tipo Usuario -->
					<td headers="header0"><s:select name="form.tipoUsuario"
						id="form.tipoUsuario" 
						required="true" emptyOption="false"
						theme="xhtml" 
						key="usuario.tipoUsuario" labelposition="left"
						list="contextUsuarios" listKey="codigo"
						listValue="descripcion" cssClass="input_tablas_registro" /></td>
				</tr>
			</tbody>
			</table>
	</fieldset>
	
	<br>

	<!-- ********************************************************************* -->
	<!-- Botones 															   -->
	<!-- ********************************************************************* -->

	&nbsp;
    <%--
    <s:a href="javascript:enviar('frmBuscarUsuarios');" labelposition="left"
	cssClass="boton_template" >
	<s:text name="buttons.text.search" /></s:a>
    --%>
 <div>   <s:submit theme="simple" value="%{getText('buttons.text.search')}" cssClass="boton_template" /> </div>

	</s:form>
	<!-- ********************************************************************* -->
	<!-- Tabla de resultados                                                   -->
	<!-- ********************************************************************* -->
	<div class="tabla_BD" title="Tabla resultados">
	<s:if test="listaUsuarios != null">
		<display:table summary="Tabla de resultados de búsqueda de usuarios"
			name="listaUsuarios" class="contenidonivel3 BD" pagesize="5"
			requestURI="" defaultsort="1" defaultorder="ascending" sort="list"
			export="true" cellpadding="0" cellspacing="0" frame="_blank" 
			decorator="es.mpr.template.web.decorators.TableWrapper">
	
			<%-- username --%>
			<display:column property="username" titleKey="field.usuario.username"
				sortable="true" headerClass="BD" class="BD" />
	
			<%-- nombre --%>
			<display:column property="nombre" titleKey="field.usuario.nombre" sortable="true"
				headerClass="BD" class="BD" />
	
			<%-- apellidos --%>
			<display:column property="apellidos" titleKey="field.usuario.apellidos"
				sortable="true" headerClass="BD" class="BD" />
	
			<%-- descripcion --%>
			<display:column property="description" titleKey="field.usuario.descripcion"
				sortable="true" headerClass="BD" class="BD" />
	
			<%-- acciones --%>
			<display:column property="userAction" titleKey="panels.urls"
				headerClass="BD" class="BD" media="html" />
	
		</display:table>
	</s:if>
	</div>
</div>