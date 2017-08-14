<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>

<div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>
<script language="javascript" src="struts/js/fwk-ajax.js"></script>
<script type="text/javascript" >
	
	/*function getJSON(catalogName,lookup){
		
		var url_catalog = "catalogJSON.action";
		
		$.getJSON(url_catalog,{catalogName: catalogName,lookUpValue: lookup}, loadSelect);
	}*/
		
	function loadProvincias(data)
	{		
		 var options = '';
		 var result = data.JSON;
		 if (result == "success"){
				
			var catalogList = data.catalogList;
			for (var i = 0; i < catalogList.length; i++){
               	options += '<option value="' + catalogList[i].codigo + '">' + catalogList[i].descripcion + '</option>';
            }
			
         $("select#provincias").html(options);
         
         $("select#provincias").change();
		}
	}
	
	function loadLocalidades(data)
	{		
		 var options = '';
		 var result = data.JSON;
		 if (result == "success"){
				
			var catalogList = data.catalogList;
			for (var i = 0; i < catalogList.length; i++){
               	options += '<option value="' + catalogList[i].codigo + '">' + catalogList[i].descripcion + '</option>';
            }
			
         $("select#localidad").html(options);
		}
	}

	$(document).ready(function() {
	    $(function() {
	        $("select#comunidades").change(function() {
	        	
	          var params = "catalogName=provinciasPorCA&lookUpValue=" + $(this).val();
	          var url = "catalogJSON.action";
	          
	          var ajax = new AjaxEngine();
	          ajax.setURL(url);
	          ajax.setSuccessCall(loadProvincias);
	          //ajax.setErrorCall(errorDiv);
	          ajax.setType("POST");
	          ajax.setData(params);
	          ajax.sendRequest();
	        })
	        
	        $("select#provincias").change(function() {
	        	
	          var params = "catalogName=localidadesPorProvincia&lookUpValue=" + $(this).val();
	          var url = "catalogJSON.action";
	          
	          var ajax = new AjaxEngine();
	          ajax.setURL(url);
	          ajax.setSuccessCall(loadLocalidades);
	          ajax.setType("POST");
	          ajax.setData(params);
	          ajax.sendRequest();
	        })
	    })
	});

	function setParams(select){
		
		var select_provincias = $('#provincias');
		
		var options = {};
		options.href= "/PlataformaMensajeria/catalogJSON.action?catalogName=" + select.name + "&lookUpValue=" + select.value;
	}
	
	$.subscribe('setParams', function (event, element){
		
		var selectedValue = event.originalEvent.currentTarget.value;
		
		$('#catalogName').attr('value',"provinciasPorCA");
		$('#lookUpValue').attr('value',selectedValue);
	})
	
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
							<h2><s:text name="pages.ajax.title" /></h2>
						</div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<sj:select 
							href="%{remoteurl}" 
							id="language" 
							onChangeTopics="recargaFramework" 
							name="language"
							list="languageObjList"
							listKey="codigo"
							listValue="descripcion"
							emptyOption="true" 
							headerKey="-1" 
							headerValue="Elija un lenguaje"/>
						<br>
					</td>
				</tr>
				<tr>
					<td>
						<sj:select 
							href="%{remoteurl}" 
							id="framework" 
							formIds="formAjax" 
							onChangeTopics="recargaVersion"
							reloadTopics="recargaFramework" 
							name="framework" 
							list="frameworkList" 
							listKey="codigo"
							listValue="descripcion"
							emptyOption="true" 
							headerKey="-1" 
							headerValue="Elija un framework"/>
						<br>
					</td>
				</tr>
				<tr>
					<td>
						<sj:select 
							href="%{remoteurl}" 
							id="version" 
							formIds="formAjax" 
							reloadTopics="recargaVersion" 
							name="version" 
							list="versionList" 
							emptyOption="true" 
							headerKey="-1" 
							headerValue="Elija una version"/>
						<br>
					</td>
				</tr>
				<tr>
					<td>
						<s:param name="catalogName" value="comunidades" />
											
						<s:url id="catalogurl" action="catalogJSON?catalogName=comunidades">
							<s:param name="catalogName" value="%{catalogName}" />
						</s:url>
						<sj:select 
						href="%{catalogurl}" 
						id="comunidades" 
						formIds="formAjax" 
						name="comunidades" 
						list="catalogList" 
						listKey="codigo"
						listValue="descripcion"
						emptyOption="true" 
						headerKey="-1" 
						headerValue="Comunidades"/>
						<br>
					</td>
				</tr>
				<tr>
					<td>
						<s:url id="catalogurl" action="catalogJSON"/>
						<sj:select 
						href="%{catalogurl}" 
						id="provincias" 
						formIds="formAjax" 
						name="provincias" 
						list="catalogList" 
						listKey="codigo"
						listValue="descripcion"
						emptyOption="true" 
						headerKey="-1" 
						headerValue="Provincias"/>
						<br>
					</td>
				</tr>
				<tr>
					<td>
						<s:url id="catalogurl" action="catalogJSON"/>
						<sj:select 
						href="%{catalogurl}" 
						id="localidad" 
						formIds="formAjax" 
						name="localidad" 
						list="catalogList" 
						listKey="codigo"
						listValue="descripcion"
						emptyOption="true" 
						headerKey="-1" 
						headerValue="Localidad"/>
						<br>
					</td>
				</tr>
			</tbody>
			</table>
		</fieldset>
	</s:form>
</div>