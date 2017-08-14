<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="contenedor_principal">

<fieldset>
		<table summary="Tabla Errores" cellpadding="0" cellspacing="4">
		<thead>
			<tr>
				<th headers="header0">
					<div class="titulobloque">
						<h2><s:text name="errors.j2ee.generic.messageerrorpage" /></h2>
					</div>
				</th>
			</tr>
		</thead>
		<tbody>
		<tr>
			<td headers="header0"><s:text name="errors.j2ee.generic.messageerrorpage2" /></td>
		</tr>
		<tr>
			<td headers="header0"><s:actionerror/></td>
		</tr>
		</tbody>
	</table>
	<% //Eliminamos los posibles mensajes obtenidos de alguna operación cuando surja un error no controlado
	request.getSession().setAttribute("MSGPLT",null); //Mensajes OK
	request.getSession().setAttribute("MSGPLT_ERROR",null); //Mensajes errores controlados
	request.getSession().setAttribute("MSGPLT_FIELD_ERROR",null); //Mensajes error validacion campos
	%>
</fieldset>