<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>

<div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>
<script language="javascript" src="struts/js/fwk-ajax.js"></script>
<script type="text/javascript" >
	$(document).ready( function() {
			$.subscribe('loadResult', function(event, data) {
		
				if (event.originalEvent.data!=null)
				{
					var result = event.originalEvent.data.JSON;
					if (result!=null && result=="success")
					{
						var nombre = event.originalEvent.data.nombre;
						var apellidos = event.originalEvent.data.apellidos;
						var situacion = event.originalEvent.data.situacion;
						var opinion = event.originalEvent.data.opinion;
						var condiciones = event.originalEvent.data.condiciones;
						
						var list = "";
						list += "nombre: " + nombre + "\n";
						list += "apellidos: " + apellidos + "\n";
						list += "situacion: " + situacion + "\n";
						list += "opinion: " + opinion + "\n";
						
						if (condiciones==null)
							condiciones = false;
						
						list += "condiciones: " + condiciones;
			
						$("#resultado").val(list);
					}
				}
			});
			
			$.subscribe('errorState', function(event, data) {
				
				var status = event.originalEvent.request.status;
				var statusText = event.originalEvent.request.statusText;
				
				if (statusText!=null)
				{
					var list = "";
					list += statusText + " - " + status;
					
					$("#resultado").val(list);
				}
				
			});
	});
</script>
<div id="contenedor_principal">
	<s:url id="remoteurl" action="formsJSON" />
	<s:form id="formAjax" action="%{remoteurl}" validate="false" theme="css_xhtml">
		<fieldset>
			<table summary="Tabla formulario" cellpadding="0" cellspacing="4">
			<thead>
				<tr>
					<th headers="header0" align="left">
						<div class="titulobloque">
							<h2><s:text name="pages.ajax.forms.title" /></h2>
						</div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
					<s:textfield 
						id="nombre" 
						name="nombre"
						key="field.usuario.nombre"
						labelposition="left"/>
					</td>
				</tr>
				<tr>
					<td>
					<s:textfield 
						id="apellidos" 
						name="apellidos"
						key="field.usuario.apellidos"
						labelposition="left" />
					</td>
				</tr>
				<tr>
					<td>
					<s:radio 
						id="situacion" 
						name="situacion"
						list="situacionList"
						key="pages.ajax.forms.situacion"
						listKey="codigo"
						listValue="descripcion"
						labelposition="left" />
					</td>
				</tr>
				<tr>
					<td>
					<s:textarea 
						id="opinion"
						name="opinion"
						cols="40" 
						rows="10"
						key="pages.ajax.forms.opinion" /> 
					</td>
				</tr>
				<tr>
					<td>
					<s:checkbox 
						id="condiciones" 
						name="condiciones"
						key="pages.ajax.forms.condiciones"
						listKey="codigo"
						listValue="descripcion"
						labelposition="left" />
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>
					<sj:submit id="submit" formIds="formAjax"
								href="%{remoteurl}"
								dataType="json"
								onSuccessTopics="loadResult"
								onErrorTopics="errorState"
								button="true"
								indicator="indicator"/>
						<img id="indicator" src="images/indicator.gif" alt="%{getText('pages.ajax.forms.loading')}" style="display:none"/>
					</td>
				</tr>
				<tr>
				<td>
					<textarea id="resultado" rows="10" cols="40" name="resultado"></textarea>
				</td>
				</tr>
			</tbody>
			</table>
		</fieldset>
	</s:form>
	<br/><br/>
</div>