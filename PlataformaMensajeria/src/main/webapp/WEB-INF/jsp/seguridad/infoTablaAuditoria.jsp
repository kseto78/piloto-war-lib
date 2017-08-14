<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>

<div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>

<!-- ************************** -->
<!-- Criterios de la busqueda   -->
<!-- ************************** -->
<div id="contenedor_principal">
	<s:form id="frmBuscarRegistrosAuditoria" method="POST" action="buscarAuditoria"
		validate="false" theme="css_xhtml">
	<fieldset>
			<table summary="Tabla formulario criterios de búsqueda" cellpadding="0" cellspacing="4">
			<thead>
				<tr>
					<th headers="header0" colspan="2" align="left">
						<div class="titulobloque">
							<h2><s:text name="menu.auditoria" /></h2>
						</div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td headers="header0">
						<s:text name="pages.Auditoria.manage.title"></s:text>
					</td>
				</tr>
			</tbody>
			</table>
	</fieldset>

	<br>

	<!-- ****************************************************************************** -->
	<!-- Botones																		-->
	<!-- ****************************************************************************** -->

	&nbsp;
    <div><s:submit theme="simple" cssClass="boton_template" value="%{getText('buttons.text.search')}"/></div>

	</s:form>

	<!-- ********************************************************************* -->
	<!-- Tabla de resultados                                                   -->
	<!-- ********************************************************************* -->
	<div class="tabla_BD" title="Tabla resultados">
	<s:set name="listaRegistrosAuditoria" value="%{listaRegistrosAuditoria}" />
	<s:if test="#listaRegistrosAuditoria !=null && #listaRegistrosAuditoria.size()>0">
		<display:table summary="Tabla de resultados de búsqueda de registros Auditoria"
		name="listaRegistrosAuditoria" class="contenidonivel3 BD" pagesize="2"
		requestURI="" defaultsort="1" defaultorder="ascending" sort="list"
		export="true" cellpadding="0" cellspacing="0">
			<%-- id --%>
			<display:column property="id" titleKey="field.auditoria.idRegistro" 
				sortable="true"	headerClass="BD" class="BD" />
	
			<%-- operacion --%>
			<display:column property="operacion" titleKey="field.auditoria.operacion" sortable="true"
				headerClass="BD" class="BD" />
	
			<%-- usuario --%>
			<display:column property="usuario" titleKey="field.auditoria.usuario" sortable="true"
				headerClass="BD" class="BD" />
				
			<%-- Datos Entrada --%>
			<display:column property="datosEntrada" titleKey="field.auditoria.datosEntrada" sortable="true"
				headerClass="BD" class="BD" />
				
			<%-- Datos Respuesta --%>
			<display:column property="datosRepuesta" titleKey="field.auditoria.datosRespuesta" sortable="true"
				headerClass="BD" class="BD" />
				
			<%-- Resultado --%>
			<display:column property="resultado" titleKey="field.auditoria.resultado" sortable="true"
				headerClass="BD" class="BD" />
	
			<%-- Resultado --%>
			<display:column property="fecha" titleKey="field.auditoria.fecha" sortable="true"
				headerClass="BD" class="BD" />
       	</display:table>
	</s:if>
	</div>
</div>
