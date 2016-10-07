<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>


<div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>

<!-- ****************************************************************************** -->
<!-- Formulario                                                                     -->
<!-- ****************************************************************************** -->

<div id="contenedor_principal">

	<fieldset>
	
			<table summary="Tabla formulario criterios de búsqueda" cellpadding="0" cellspacing="4">
			<thead>
				<tr>
					<th headers="header0" colspan="2" align="left">
						<div class="titulobloque">
							<h2><s:text name="menu.usuarioLogeado" /></h2>
						</div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td headers="header0">
						<s:text name="pages.infousuariologado.paginapublica"/>
						<br/>
						<br/>
						<sec:authorize ifNotGranted="ROLE_GRUPOFWK">
							<s:text name="pages.infousuariologado.textousuarioapp"/>
						</sec:authorize>
						
						<sec:authorize ifAllGranted="ROLE_ADMINFWK">
							<s:text name="pages.infousuariologado.textousuarioadmapp"/>
						</sec:authorize>
					</td>
				</tr>
			</tbody>
			</table>
	
	</fieldset>

<sec:authorize ifAnyGranted="ROLE_ADMINFWK,ROLE_GRUPOFWK">

	<br/>
	<s:text name="pages.infousuariologado.pulse"/>
	<br/><br/>

    <s:form method="POST" action="detallesUsuarioLogado" validate="false" cssClass="bloqueizq_contenido" >
        <s:submit theme="simple" action="detallesUsuarioLogado" value="%{getText('buttons.text.enviar')}" cssClass="input_boton_buscador" />
    </s:form>

    <s:if test="form!=null">
        <br/><br/>
		<s:text name="pages.infousuariologado.detalles"/>
		<br/>
        <s:text name="usuario.tipoUsuario" />:&nbsp;<s:label name="form.tipoUsuario" /><br/>
        <s:text name="field.usuario.username" />:&nbsp;<s:label name="form.username" /><br/>
        <s:text name="field.usuario.nombre" />:&nbsp;<s:label name="form.nombre" /><br/>
        <s:text name="field.usuario.apellidos" />:&nbsp;<s:label name="form.apellidos " /><br/>
        <s:text name="field.usuario.descripcion" />:&nbsp;<s:label name="form.description" /><br/>
        <s:text name="field.usuario.telefono" />:&nbsp;<s:label name="form.telefono" /><br/>
        <s:text name="field.usuario.telefono2" />:&nbsp;<s:label name="form.telefono2" /><br/>
        <s:text name="field.usuario.movil" />:&nbsp;<s:label name="form.movil" /><br/>
        <s:text name="field.usuario.direccion" />:&nbsp;<s:label name="form.direccion" /><br/>
        <s:text name="field.usuario.numero" />:&nbsp;<s:label name="form.numero" /><br/>
        <s:text name="field.usuario.piso" />:&nbsp;<s:label name="form.piso" /><br/>
        <s:text name="field.usuario.puerta" />:&nbsp;<s:label name="form.puerta" /><br/>
        <s:text name="field.usuario.escalera" />:&nbsp;<s:label name="form.escalera" /><br/>
        <s:text name="field.usuario.codpostal" />:&nbsp;<s:label name="form.codigoPostal" /><br/>
        <s:text name="field.usuario.ciudad" />:&nbsp;<s:label name="form.ciudad" /><br/>
        <s:text name="field.usuario.provincia" />:&nbsp;<s:label name="form.provincia" /><br/>
        <s:text name="field.usuario.pais" />:&nbsp;<s:label name="form.pais" /><br/>
        <s:text name="field.usuario.nif" />:&nbsp;<s:label name="form.nif" /><br/>
        <s:text name="field.usuario.cif" />:&nbsp;<s:label name="form.cif" /><br/>
        <s:text name="field.usuario.email" />:&nbsp;<s:label name="form.email" /><br/>
        <s:text name="field.usuario.mailNoticias" />:&nbsp;<s:label name="form.mailNoticias" /><br/>
        <s:text name="field.usuario.mailNovedades" />:&nbsp;<s:label name="form.mailNovedades" /><br/>
        <s:text name="field.usuario.mailContenidosInf" />:&nbsp;<s:label name="form.mailContenidos" /><br/>
        <s:text name="field.usuario.mailCampanas" />:&nbsp;<s:label name="form.mailCampanias" /><br/>
        <s:text name="field.usuario.fax" />:&nbsp;<s:label name="form.fax" /><br/>
        <s:text name="field.usuario.puntosContacto" />:&nbsp;<s:label name="form.puntosContacto" /><br/>
        <s:text name="field.usuario.despacho" />:&nbsp;<s:label name="form.despacho" /><br/>
    </s:if>
</sec:authorize>

</div>
