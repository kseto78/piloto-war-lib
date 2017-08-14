<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>

<div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>

<div id="contenedor_principal">

	<fieldset>
	
			<table summary="Tabla formulario criterios de búsqueda" cellpadding="0" cellspacing="4">
			<thead>
				<tr>
					<th headers="header0" colspan="2" align="left">
						<div class="titulobloque">
							<h2><s:text name="menu.configuracion.pool" /></h2>
						</div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td headers="header0">
						<% 
						if (request.getAttribute("pool")!= null) {
								%>
						<s:text name="pages.pool.objetoRecuperado" />
						
						<%= request.getAttribute("pool") %>
						
						<% }
						else { %>
							<input type="submit" value="Comprobar Pool">
						<% }
						%>
					</td>
				</tr>
			</tbody>
			</table>
	
	</fieldset>

</div>