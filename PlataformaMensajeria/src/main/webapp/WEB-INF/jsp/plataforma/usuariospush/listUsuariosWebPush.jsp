<%@page import="org.displaytag.decorator.MultilevelTotalTableDecorator"%>
<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>


<!-- <div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>-->

<!-- ************************** -->
<!-- Criterios de la busqueda   -->
<!-- ************************** -->
<script>
function setValue(obj){
	var value = obj.value;
	document.getElementById('usuariosWebPush.servicioId').value=value;
}
function makeRequest(){
	document.getElementById('usuariosWebPush.servicioId').value='';
	  $('#sid option').each(function() {
		        $(this).remove();
		});
	$.ajax({
        type: "POST",
        url: "ajaxLoadServiciosCanal.action",
        data: {idAplicacion:document.getElementById('usuariosWebPush.aplicacionId').value,idCanal:5}, // serializes the form's elements.
        success: function(data)
        {
     	  var items = data.items;
     	  $('#sid').append($('<option>', { 
     	        value: '',
     	        text : 'Todos' 
     	    }));
     	 $.each(items, function (i, item) {
     		$('#sid').append($('<option>', { 
     	        value: item.value,
     	        text : item.text 
     	    }));
     	});
        },
        error: function(data)
        {
     	   alert("error..."); 
        }
      });
}
</script>
<div class="mainContent">
    <h3 class="pageNameButtons">
    	<span class="floatRight"></span>
	    <label>Usuarios Web Push</label>
    </h3>
	<%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp"%>
	<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp"%>
	<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp"%>
	<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp"%>
	<div class="criteria">
		<s:form id="frmBuscarUsuariosWebPush" method="POST"
			action="listUsuariosWebPush" validate="false" theme="css_xhtml">
			<p class="criteria">
                    	<label class="fieldText" style="width: 100px;">Id:</label>
                    	<s:textfield
							name="usuariosWebPush.usuarioId" id="usuariosWebPush.usuarioId"
							theme="simple" 
							key="field.general.id" labelposition="left"
							size="9" maxlength="60" 
							cssClass="input_tablas_registro" />
               	   </p>
			<p class="criteria">			
				<span> <label style="width: 100px;" class="fieldText">Aplicacion:</label>
					<s:select id="usuariosWebPush.aplicacionId"
						name="usuariosWebPush.aplicacionId" emptyOption="false"
						theme="simple" onchange="makeRequest()" labelposition="left" 
						list="comboAplicaciones" listKey="codigo" headerKey="" 
						headerValue="Todas" listValue="descripcion" cssClass="W240" 
						value="%{usuariosWebPush.aplicacionId}" disabled="false" />
				</span> 
				<span> <label style="width: 80px;" class="fieldText">Usuario:</label>
					<s:textfield
							name="usuariosWebPush.nombreUsuario" id="usuariosWebPush.nombreUsuario"
							theme="simple" 
							labelposition="left"
							size="9" maxlength="40" 
							cssClass="input_tablas_registro" />
				</span> 
				
			</p>
			<p class="criteria">
				<span> <label style="width: 100px;" class="fieldText">Servicio:</label>
					<s:select id="sid"
						name="sid" emptyOption="false"
						theme="simple" onchange="setValue(this)" labelposition="left" 
						list="comboServicios" listKey="codigo" headerKey="" 
						headerValue="Todos" listValue="descripcion" cssClass="W240" 
						value="%{usuariosWebPush.servicioId}" disabled="false" />
				</span> 
				<span> <label class="fieldText" style="width: 100px;">
						Fecha: </label>Desde: <s:textfield name="usuariosWebPush.fechaDesde"
						value="%{usuariosWebPush.fechaDesde}"
						onchange="validateDates('usuariosWebPush.fechaDesde','usuariosWebPush.fechaHasta',this)"
						id="usuariosWebPush.fechaDesde" theme="simple" format="dd/MM/yyyy"
						style="width:60px;" size="10" maxlength="10" cssClass="datepickerEstadisticas">
						<s:param name="value">
    							<s:date name="usuariosWebPush.fechaDesde" format="dd/MM/yyyy"/>
  						</s:param>
						</s:textfield>
					Hasta: <s:textfield name="usuariosWebPush.fechaHasta"
						value="%{usuariosWebPush.fechaHasta}"
						id="usuariosWebPush.fechaHasta" theme="simple"
						onchange="validateDates('usuariosWebPush.fechaDesde','usuariosWebPush.fechaHasta',this)"
						style="width:60px;" size="10" maxlength="10" cssClass="datepickerEstadisticas">
						<s:param name="value">
    							<s:date name="usuariosWebPush.fechaHasta" format="dd/MM/yyyy"/>
  						</s:param>
						</s:textfield>
				</span> 

			</p>
			<div class="footerCriteria">
				<span class="leftSide"></span> <span class="rightSide"> <s:submit
						theme="simple" value="%{getText('buttons.text.search')}"
						cssClass="button" />
				</span>
			</div>
	</div>
	<s:hidden id="usuariosWebPush.servicioId" name="usuariosWebPush.servicioId" value="%{usuariosWebPush.servicioId}"/>
	<s:hidden id="pageSizeSelected" name="pageSize" value="%{pageSize}"/> 
	</s:form>
	<h4 class="titular">Encontradas ${resultCount} entradas</h4>
	<table class="tablaHeader">
		<thead>
			<tr>
				<th class="superHeader" colspan="5">
					<div class="floatLeft"></div>
					<div class="floatRight">
						<s:a cssClass="button" onclick="makeExcell(this)">Exportar Excel</s:a>
					</div>
				</th>
			</tr>
		</thead>
	</table>
	<display:table id="tableId"
		summary="Tabla de resultados de b�squeda de usuarios Push"
		name="listaUsuariosWebPush" pagesize="${pageSize}" requestURI=""
		defaultsort="1" defaultorder="ascending" sort="external" export="true"
		cellpadding="0" cellspacing="0" partialList="true"
		size='<%=request.getAttribute("totalSize")%>'
		decorator="es.mpr.template.web.decorators.TableWrapper">
		<display:setProperty name="css.tr.even" value="null" />
		<display:setProperty name="css.tr.odd" value="odd" />
		<%-- Id --%>
		<display:column property="usuarioId" titleKey="plataforma.usuariospush.usuarioid"
			sortable="true" headerClass="separator center" class="center" />
		<%-- nombre usuario --%>
		<display:column property="nombreUsuario"
			titleKey="plataforma.usuariospush.username" sortable="true"
			headerClass="separator center" class="center" />
		<%-- aplicacion --%>
		<display:column property="aplicacion"
			titleKey="plataforma.usuariospush.aplicacionid" sortable="false"
			headerClass="separator center" class="center" />
		<%-- servicio --%>
		<display:column property="servicio"
			titleKey="plataforma.usuariospush.servicioid" sortable="true"
			headerClass="separator center" class="center" />
		
	 	<%-- fecha --%>
		<display:column property="fechaCreacion"
			titleKey="plataforma.auditoria.fecha"
			format="{0,date,dd/MM/yyyy HH:mm}" sortable="true"
			headerClass="separator center" class="center" /> 
	</display:table>
	<br />
	
	<s:form id="insertarUsuarioWebPush" name="insertarUsuarioWebPush"
			action="insertarUsuarioWebPush" onsubmit="return false;" theme="simple">
			<div class="HomeContent">
<!-- 				<div class="alerts"> -->
				<div>
					<p>
						<button disabled
							class="js-push-btn mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">
							Enable Push Messaging
						</button>
					</p>
				
<!-- 					<input id="subscription" value="" /> -->

					<p class="criteria">
						
					<s:textfield name="endpoint" value="%{endpoint}"
							id="endpoint" theme="simple"
							cssStyle="visibility:hidden;display:none;" labelposition="left"
							size="70" maxlength="255" cssClass="" />
					</p>
					
					<p class="criteria">
						
					<s:textfield name="pdh" value="%{pdh}"
							id="pdh" theme="simple"
							cssStyle="visibility:hidden;display:none;" labelposition="left"
							size="70" maxlength="255" cssClass="" />
					</p>
					
					<p class="criteria">
						
					<s:textfield name="auth" value="%{auth}"
							id="auth" theme="simple"
							cssStyle="visibility:hidden;display:none;" labelposition="left"
							size="70" maxlength="255" cssClass="" />
					</p>
					
					<p class="criteria">

					<s:textfield name="idUsuario" value="%{idUsuario}"
							id="idUsuario" theme="simple"
							cssStyle="visibility:hidden;display:none;" labelposition="left"
							size="70" maxlength="255" cssClass="" />
					</p>
					
					<p class="criteria">

					<s:textfield name="subscription" value="%{subscription}"
							id="subscription" theme="simple"
							cssStyle="visibility:hidden;display:none;" labelposition="left"
							size="70" maxlength="255" cssClass="" />
					</p>
					
					<s:textfield name="accion" value="%{accion}"
							id="accion" theme="simple"
							cssStyle="visibility:hidden;display:none;" labelposition="left"
							size="70" maxlength="255" cssClass="" />
						
					<script>
					const publicKeyConstant = "${publicKey}";
					</script>
					<script type="text/javascript" src="pushMod.js"></script>

				</div>
			</div>
		</s:form>

</div>


