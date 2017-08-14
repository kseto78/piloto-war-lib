<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>

<div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>
<script language="javascript" src="struts/js/fwk-ajax.js"></script>
<script type="text/javascript" >
			
	function loadDiv(data)
	{		
		if (data==null)
			return;
		
		var result = data.JSON;
		if (result == "success"){
				
		var catalogList = data.catalogList;
		var lista = '<ul>';
		for (var i = 0; i < catalogList.length; i++){
              	lista += '<li>' + catalogList[i].descripcion + '</li>';
        }
		 
		lista +="</ul>";
		 	
        $("div#result").html(lista);
		}
	}
	
	function errorDiv(data){
		
		$("div#result").html("Hay un error");
	}
	
	$(document).ready(function() {
		
	    $(function() {
	        $("a#get").click(function() {
	        	
	          var params = "catalogName=lenguajes";
	          var url = "catalogJSON.action?" + params;
	         	          
	          //ajaxFwk.get(url,loadDiv);
	          
	          var ajax = new AjaxEngine();
	          ajax.setURL(url);
	          ajax.setSuccessCall(loadDiv);
	          ajax.setErrorCall(errorDiv);
	          ajax.setType("GET");
	          ajax.sendRequest();
	          
	        })
	        
	        $("a#post").click(function() {
	        	
	          var params = "catalogName=comunidades";
	          var url = "catalogJSON.action";
	         		          
	          //ajaxFwk.post(url,loadDiv,params);
	          
	          var ajax = new AjaxEngine();
	          ajax.setURL(url);
	          ajax.setSuccessCall(loadDiv);
	          ajax.setErrorCall(errorDiv);
	          ajax.setType("POST");
	          ajax.setData(params);
	          ajax.sendRequest();
	        })
	        
	         $("#submit").click(function() {
	        	
	          var url = "catalogJSON.action";
	         
	          var ajax = new AjaxEngine();
	          ajax.setURL(url);
	          ajax.setSuccessCall(loadDiv);
	          ajax.setErrorCall(errorDiv);
	          ajax.setType("POST");
	          //ajax.setData(params);
	          ajax.submitForm("formAjax");
	          
	          return;
	          //ajaxFwk.submit(url,"result",$("#formAjax"));
	        })
	    })
	});	
</script>
<div id="contenedor_principal">
	<s:url id="remoteurl" action="catalogJSON" />
	
		<fieldset>
			<table summary="Tabla formulario" cellpadding="0" cellspacing="4">
			<thead>
				<tr>
					<th headers="header0" align="left">
						<div class="titulobloque">
							<h2><s:text name="pages.ajax.enlaces.title" /></h2>
						</div>
					</th>
					<td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<a href="#lenguajes" id="get">Lenguajes - Ajax GET</a>
					</td>
				</tr>
				<tr>
					<td>
						<a href="#comunidades" id="post">Comunidades - Ajax POST</a>
					</td>
				</tr>
			</tbody>
			</table>
		</fieldset>
		<br/>
	<div id="result">
	</div>
</div>