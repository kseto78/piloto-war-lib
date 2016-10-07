<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>

<div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>
<script language="javascript" src="struts/js/fwk-ajax.js"></script>
<script type="text/javascript" >
			
	function loadProvincias(data)
	{		
		 var options = '';
		 var result = data.JSON;
		 if (result == "success"){
				
			var catalogList = data.catalogList;
			if (catalogList!=null && catalogList.length>0)
			{				
				for (var i = 0; i < catalogList.length; i++){
	               	options += '<option value="' + catalogList[i].codigo + '">' + catalogList[i].descripcion + '</option>';
            	}
				
				$("select#provincias").html(options);
			}         
         
         $("select#provincias").change();
		}
	}
	
	function loadLocalidades(data)
	{		
		 var options = '';
		 var result = data.JSON;
		 if (result == "success"){
				
			var catalogList = data.catalogList;
			if (catalogList!=null && catalogList.length>0)
			{
				for (var i = 0; i < catalogList.length; i++){
	               	options += '<option value="' + catalogList[i].codigo + '">' + catalogList[i].descripcion + '</option>';
            	}
			
         	$("select#localidad").html(options);
			}
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
	<s:url id="remoteurl" action="combosJSON" />
	<s:form id="formAjax" action="%{remoteurl}" validate="false" theme="css_xhtml">
		<fieldset>
			<table summary="Tabla formulario" cellpadding="0" cellspacing="4" width="100%">
			<thead>
				<tr>
					<th headers="header0" align="left">
						<div class="titulobloque">
							<h2><s:text name="pages.ajax.combos.title2" /></h2>
						</div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>											
						<s:url id="catalogurl" action="catalogJSON?catalogName=comunidades"/>
						<sj:select 
						href="%{catalogurl}" 
						id="comunidades" 
						formIds="formAjax" 
						name="comunidades" 
						list="catalogList" 
						listKey="codigo"
						listValue="descripcion"
						emptyOption="true"
						key="field.test.ca"
						labelposition="left" />
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
						key="field.test.prov" 
						labelposition="left" />
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
						key="field.configuracion.unidadesorg.localidad" 
						labelposition="left" />
						<br>
					</td>
				</tr>
				</tbody>
			</table>
		</fieldset>
	</s:form>
</div>