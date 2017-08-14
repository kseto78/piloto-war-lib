<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>

<div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>

<!-- ****************************************************************************** -->
<!-- Formulario                                                                     -->
<!-- ****************************************************************************** -->

<div id="contenedor_principal">
<s:form id="frmConsUsuario" method="POST" action="adminEditarUsuario"
	validate="false"  theme="css_xhtml">
	<s:hidden name="form.uid" value="%{form.uid}" />
    <s:hidden name="form.id" value="%{form.id}" />
<fieldset>
		<table summary="Tabla formulario criterios de búsqueda" cellpadding="0" cellspacing="4">
		<thead>
			<tr>
				<th headers="header0" colspan="3" align="left">
					<div class="titulobloque">
						<h2><s:text name="panels.datosGenerales" /></h2>
					</div>
				</th>
			</tr>
		</thead>

		<tbody>
			<tr>
				<!-- tipoUsuario -->
				<td headers="header0"><s:select
					id="sTipoUsuario" name="sTipoUsuario" 
					emptyOption="false" theme="css_xhtml" 
					key="usuario.tipoUsuario" labelposition="left"
					list="contextUsuarios" listKey="codigo"
					listValue="descripcion" cssClass="input_tablas_registro" 
					value="%{form.tipoUsuario}" disabled="true" />
					<s:hidden name="form.tipoUsuario" 
					value="%{form.tipoUsuario}" />
				</td>
				<!-- username -->
				<td headers="header0"><s:label
					key="field.usuario.username" theme="css_xhtml" 
					value="%{form.username}" />
					<s:hidden name="form.username" 
					value="%{form.username}" />
				</td>
				<td headers="header0">&nbsp;</td>
			</tr>
			<tr>
				<!-- nombre -->
				<td headers="header0"><s:label
					key="field.usuario.nombre" theme="css_xhtml" 
					value="%{form.nombre}" />
				<!-- apellidos -->
				<td headers="header0"><s:label
					key="field.usuario.apellidos" theme="css_xhtml" 
					value="%{form.apellidos}" />
				</td>
				<td headers="header0">&nbsp;</td>
			</tr>
			<tr>
				<!-- descripcion -->
				<td headers="header0" colspan="2" align="left">
					<s:label 
					key="field.usuario.descripcion" theme="css_xhtml" 
					value="%{form.description}" /></td>
				<td headers="header0">&nbsp;</td>
			</tr>
			<tr>
				<!-- Organismo -->
				<td headers="header0"><framework:catalog
					id="organismos" emptyOption="true" catalogname="organismos"
					name="form.organismo" value="%{form.organismo}"
					key="field.usuario.organismo" labelposition="left"
					cssClass="input_tablas_registro" theme="css_xhtml" 
					disabled="true" /></td>
				<!-- Unidad Organizacional -->
				<td headers="header0"><framework:catalog
					id="unidadesOrganizacionales" emptyOption="true" catalogname="unidadesOrganizacionales"
					name="form.unidadOrganizacional" value="%{form.unidadOrganizacional}"
					key="field.usuario.unidadOrganizacional" labelposition="left"
					cssClass="input_tablas_registro" theme="css_xhtml" 
					disabled="true" /></td>
				<!-- eliminado -->
				<!--<td headers="header0"><s:checkbox
					name="form.eliminado" theme="css_xhtml"
					key="field.usuario.eliminado" labelposition="left"
					cssClass="input_tablas_registro" fieldValue="true" 
					disabled="true" /></td>	-->
			</tr>
			<!-- password -->
			<tr>
				<!-- telefono1 -->
				<td headers="header0"><s:label 
					key="field.usuario.telefono"
					theme="css_xhtml" labelposition="top"
					value="%{form.telefono}"
					 /></td>
				<!-- telefono2 -->
				<td headers="header0"><s:label 
					key="field.usuario.telefono2" 
					theme="css_xhtml" labelposition="top"
					value="%{form.telefono2}"
					 /></td>
				<!-- movil -->
				<td headers="header0"><s:label
					key="field.usuario.movil"
					theme="css_xhtml" labelposition="top"
					value="%{form.movil}" 
					 /></td>
			</tr>
			<tr>
				<!-- direCalle -->
				<td headers="header0"><s:label
					key="field.usuario.direccion"
					theme="css_xhtml" labelposition="top"
					value="%{form.direccion}" 
					 /></td>
				<!-- codPostal -->
				<td headers="header0"><s:label
					key="field.usuario.codpostal"
					theme="css_xhtml" labelposition="top"
					value="%{form.codigoPostal}" 
					 /></td>
			</tr><!--
			<tr>
				 direNum 
				<td headers="header0"><s:label
					key="field.usuario.numero"
					theme="css_xhtml" labelposition="top"
					value="%{form.numero}" 
					 /></td>
				 direPiso 
				<td headers="header0"><s:label 
					key="field.usuario.piso"
					theme="css_xhtml" labelposition="top"
					value="%{form.piso}" 
					 /></td>
				 direPuerta 
				<td headers="header0"><s:label
					key="field.usuario.puerta"
					theme="css_xhtml" labelposition="top"
					value="%{form.puerta}" 
					 /></td>
				 direEscalera 
				<td headers="header0"><s:label
					key="field.usuario.escalera"
					theme="css_xhtml" labelposition="top"
					value="%{form.escalera}" 
					 /></td>
			</tr>
			--><tr>
				<!-- direCiudad -->
				<td headers="header0"><s:label
					key="field.usuario.ciudad"
					theme="css_xhtml" labelposition="top"
					value="%{form.ciudad}" 
					 /></td>
				<!-- provincia -->
				<td headers="header0"><s:label
					key="field.usuario.provincia"
					theme="css_xhtml" labelposition="top"
					value="%{form.provincia}" 
					 /></td>
				<!-- pais --><!--
				<td headers="header0"><s:label
					key="field.usuario.pais"
					theme="css_xhtml" labelposition="top"
					value="%{form.pais}" 
					 /></td>
			--></tr>
			<tr>
				<!-- nif -->
				<td headers="header0"><s:label 
					key="field.usuario.nif"
					theme="css_xhtml" labelposition="top"
					value="%{form.nif}" 
					 /></td>
				<!-- cif -->
				<td headers="header0"><s:label 
					key="field.usuario.cif"
					theme="css_xhtml" labelposition="top"
					value="%{form.cif}"  
					 /></td>
				<!-- email -->
				<td headers="header0"><s:label
					key="field.usuario.email"
					theme="css_xhtml" labelposition="top"
					value="%{form.email}"  
					 /></td>
			</tr>
			<tr>
				<!-- fax -->
				<td headers="header0"><s:label 
					key="field.usuario.fax"
					theme="css_xhtml" labelposition="top"
					value="%{form.fax}" 
					 /></td>
				<!-- despacho -->
				<td headers="header0"><s:label
					key="field.usuario.despacho"
					theme="css_xhtml" labelposition="top"
					value="%{form.despacho}" 
					 /></td>
				<!-- puntosContacto --><!--
				<td headers="header0"><s:label
					key="field.usuario.puntosContacto"
					theme="css_xhtml" labelposition="top"
					value="%{form.puntosContacto}" 
					 /></td>
			--></tr><!--
			<tr>
				 email2 
				<td headers="header0"><s:label
					key="field.usuario.email2"
					theme="css_xhtml" labelposition="top"
					value="%{form.email2}" 
					 /></td>
				 mailNoticias 
				<td headers="header0"><s:label
					key="field.usuario.mailNoticias"
					theme="css_xhtml" labelposition="top"
					value="%{form.mailNoticias}" 
					 /></td>
				 mailNovedades 
				<td headers="header0"><s:label
					key="field.usuario.mailNovedades"
					theme="css_xhtml" labelposition="top"
					value="%{form.mailNovedades}" 
					 /></td>
			</tr>
			<tr>
				 mailContenidosInf 
				<td headers="header0"><s:label
					key="field.usuario.mailContenidosInf"
					theme="css_xhtml" labelposition="top"
					value="%{form.mailContenidos}"  
					 /></td>
				 mailServicios 
				<td headers="header0"><s:label
					key="field.usuario.mailServicios"
					theme="css_xhtml" labelposition="top"
					value="%{form.mailServicios}" 
					 /></td>
				 mailCampanas 
				<td headers="header0"><s:label
					key="field.usuario.mailCampanas"
					theme="css_xhtml" labelposition="top"
					value="%{form.mailCampanias}" 
					 /></td>
			</tr>
			--></tbody>
		</table>
	</fieldset>
	
	<br>

	<fieldset>
	<!-- DIV GRUPOS SCROLL -->
		<table summary="Tabla con los grupos del usuario" cellpadding="0" cellspacing="4">
		<thead>
			<tr>
				<th headers="header0" colspan="2" align="left">
					<div class="titulobloque">
						<h2><s:text name="panels.Grupos" /></h2>
					</div>
				</th>				
			</tr>
		</thead>

		<tbody>
			<tr>
				<td headers="header0">
					<s:checkboxlist name="form.checkBoxGruposSeleccionados" 
					cssStyle="input_tablas_registro" theme="css_xhtml" 
					list="form.checkBoxGrupos" disabled="true" />
				</td>
			</tr>
		</tbody>
		</table>
	<!-- DIV GRUPOS Scroll -->
	</fieldset>

	<br>

	<!-- ****************************************************************************** -->
	<!-- Botones																		-->
	<!-- ****************************************************************************** -->

<div>    <s:submit theme="simple" value="%{getText('buttons.text.editar')}" cssClass="boton_template" /> </div>
	&nbsp;
    <div >
	<s:url id="url" action="adminUsuarios"/>
	<s:a href="%{url}"  labelposition="left" >
	<s:text name="buttons.text.volver" /></s:a>
    </div>
	
</s:form>
</div>