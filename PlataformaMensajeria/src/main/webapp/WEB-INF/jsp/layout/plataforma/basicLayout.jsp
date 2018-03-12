<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<%@page import="es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta content="/img/Login/icoAppLogin.jpg" itemprop="image">
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Cache-Control" content="no-cache" />
<tiles:importAttribute name="title"/>
<title><s:text name="%{#attr['title']}"/></title>
<%-- Tenplate Basico
  Template en el que sea crea una cabecera, un menu a la izquierda, el
  cuerpo y un pie

  @param title Titulo de la pagina
  @param header Cabecera
  @param menu Menu
  @param body Body
  @param footer Pie
--%>

 


<!-- 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.4.2.min.js" ></script>
-->

<sj:head jqueryui="true" jquerytheme="black-tie" compressed="false" /> 
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.8.custom.min.js" ></script>   --%>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.min.js" ></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-sliderAccess.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-timepicker-addon.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-timepicker-es.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/plataformaMensajeria.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/autocompleter.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/custom-theme/jquery-ui-1.8.custom.css"/> 
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/custom-theme/jquery-ui-timepicker-addon.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/aplicacionesExternas.css" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/reset.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/messagingApp.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/grids.css"/>
<!--[if lte IE 7]>
     <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/messagingAppIE7.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/gridsIE7.css"/>
<![endif]-->
<script type="text/javascript">

function showRepPass(){
	document.getElementById('repPassLabel').style.display="display";
	return true;
}

	$(function(){
		// Accordion
		$("#accordion").accordion({ header: "h3" });

		// Tabs
		$('#tabs').tabs();

		// Dialog			
		$('#dialog').dialog({
			autoOpen: false,
			width: 600,
			show: 'blind',
			hide: 'blind',
			
			modal: true
		});
		// Dialog Link
		$('.btnView').click(function(){
			$('.viewDialog').dialog('open');
			return false;
		});		
		$('.viewDialog').dialog({
			autoOpen: false,
			width: 800,
			position: ["center", 100],
			modal: true,
			show: 'blind',
			hide: 'blind',
		});

		// Datepicker
		$('.datepicker').datetimepicker();

		// Datepicker
		$('.datepickerEstadisticas').datepicker($.datepicker.regional['es2']);
		
		//hover states on the static widgets
		$('.dialog_link, ul#icons li').hover(
			function() { $(this).addClass('ui-state-hover'); }, 
			function() { $(this).removeClass('ui-state-hover'); }
		);
		$('#gestionEnvioBean.aplicacionid').change(function(){
			alert('peticion post');
			$.ajax({
		           type: "POST",
		           url: "ajaxLoadServicios.action",
		           data: {idAplicacion:$('#gestionEnvioBean.aplicacionid').val()}, // serializes the form's elements.
		           success: function(data)
		           {
		        	   var items = data.items;
		        	   $('#gestionEnvioBean.servicioid option').each(function() {
		        		        $(this).remove();
		        		});
		        	  $('#gestionEnvioBean.servicioid').append($('<option>', { 
		        	        value: '',
		        	        text : '' 
		        	    }));
		        	 $.each(items, function (i, item) {
		        	    $('#gestionEnvioBean.servicioid').append($('<option>', { 
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
		});

		$('#gestionEnvioHistoricoBean.aplicacionId').change(function(){
			alert('peticion post');
			$.ajax({
		           type: "POST",
		           url: "ajaxLoadServicios.action",
		           data: {idAplicacion:$('#gestionEnvioHistoricoBean.aplicacionId').val()}, // serializes the form's elements.
		           success: function(data)
		           {
		        	   var items = data.items;
		        	   $('#gestionEnvioHistoricoBean.servicioId option').each(function() {
		        		        $(this).remove();
		        		});
		        	  $('#gestionEnvioHistoricoBean.servicioId').append($('<option>', { 
		        	        value: '',
		        	        text : '' 
		        	    }));
		        	 $.each(items, function (i, item) {
		        	    $('#gestionEnvioHistoricoBean.servicioId').append($('<option>', { 
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
		});
				
	//	$ajaxStart(function() {
	//		
	//	});
	//	$ajaxStop(function() {
	//		
	//	});
	
	    
	});
	function checkItem(formName,isPlan,prefix){
		var valid=true;
		var frm = document.getElementById(formName);
		var i=0;
		var msg = "Atenci�n\n";
		if(!isPlan){
			if (prefix == "1" || prefix == "4" || prefix == "5"){
				for(i=0;i<2;i++){
					var obj = frm.elements[i];
					if(obj.value==""){
						valid=false;
						msg+="Seleccione un valor para '"+obj.title+"'\n";
					}
				}
			}
			else if (prefix == "2"){
				for(i=0;i<frm.elements.length-1;i++){
					var obj = frm.elements[i];
					if(obj.value==""){
						valid=false;
						msg+="Seleccione un valor para '"+obj.title+"'\n";
					}
				}
			} else if (prefix == "3"){
				for(i=0;i<frm.elements.length-3;i++){
					var obj = frm.elements[i];
					if(obj.value==""){
						valid=false;
						msg+="Seleccione un valor para '"+obj.title+"'\n";
					}
				}
			}
			else{
				for(i=0;i<3;i++){
					var obj = frm.elements[i];
					if(obj.value==""){
						valid=false;
						msg+="Seleccione un valor para '"+obj.title+"'\n";
					}
				}
				
			}
		}else{
			var lunes,martes,miercoles,jueves,viernes,sabado,domingo;
			lunes = document.getElementById(prefix+".lunes");
			martes = document.getElementById(prefix+".martes");
			miercoles = document.getElementById(prefix+".miercoles");
			jueves = document.getElementById(prefix+".jueves");
			viernes = document.getElementById(prefix+".viernes");
			sabado = document.getElementById(prefix+".sabado");
			domingo= document.getElementById(prefix+".domingo");
			var horaDesde,horaHasta;
			horaDesde = document.getElementById(prefix+".horaDesde");
			horaHasta = document.getElementById(prefix+".horaHasta");
			var servidorId = document.getElementById(prefix+".servidorId");
			if(lunes.checked||martes.checked||miercoles.checked||jueves.checked||viernes.checked||sabado.checked||domingo.checked){
				
			}else{
				valid=false;
				msg+="Debe seleccionar al menos un d�a de la semana\n";
			}
			if(horaDesde.value==horaHasta.value){
				valid=false;
				msg+="Debe seleccionar Horas Inicio y Fin diferentes\n";
			}else{
				var horaInicio = horaDesde.value.split(":")[0];
				var minInicio = horaDesde.value.split(":")[1];
				
				var horaFin = horaHasta.value.split(":")[0];
				var minFin = horaHasta.value.split(":")[1];
				var desde = new Date(horaInicio,minInicio,0);
				var hasta = new Date(horaFin,minFin,0);
				if(desde>hasta){
					valid=false;
					msg+="Hora Inicio no puede ser mayor que Hora Fin";
				}
				
			}
			/*if(servidorId!=null&&servidorId!="undefined"){
				if(servidorId.value==""){
					valid=false;
					msg+="Debe seleccionar un Servidor / Proveedor para crear la planificaci�n";
				}
			}*/
			
		}
		if(!valid){
			alert(msg);
			return false;
		}else{
			frm.submit();
			return true;
		}
	}
	function loadEnvio(enlace,envioId,tipoEnvio) {
		var auxHTML = $('div#ajaxloader_'+enlace.id).html();
		$('div#ajaxloader_'+enlace.id).html("<p><img src=\"<%=request.getContextPath()%>/img/ajax-loader.gif\" width=\"auto\" height=\"auto\"/></p>");
		var arrayEnvioId = envioId.split("_");
		var t="";
	   	if(arrayEnvioId[0] == '2'){
	   		t="SMS";
	   		
	   	}else if(arrayEnvioId[0] == '1'){
	   		t="Email";
	   		
	   	}else {
	   		t="NotificacionPush";
		}

		var aux ="";

		if(arrayEnvioId[0] == '2'){
	   		aux="SMS";
	   		
	   	}else if(arrayEnvioId[0] == '4'){
	   		aux="NotificacionPush";
	   	}
		
		var url =tipoEnvio+aux;
	   	url += ".action?idEnvio="+envioId;
	   
	    var dialogoEnvios = $('#dialogEnvios'+t);
	    dialogoEnvios.load(
        		url,
        		{
   	    			autoOpen: true,
   	    			width: 810,
   	    			modal: true,
   	    			show: 'blind',
   	    			hide: 'blind'
   	    		},
                function(responseText, textStatus, XMLHttpRequest) {
   	    			dialogoEnvios.dialog({
		           	 			autoOpen: true,
		           				width: 950,
		           				modal: true,
		           				show: 'blind',
		           				hide: 'blind',
		           			});
		           	    		$('div#ajaxloader_'+enlace.id).html(auxHTML);
                }

        );
        return false;	    
	           
	}

	function loadLote(enlace,loteId,tipoEnvio) {
		var auxHTML = $('div#ajaxloader_'+enlace.id).html();
		$('div#ajaxloader_'+enlace.id).html("<p><img src=\"<%=request.getContextPath()%>/img/ajax-loader.gif\" width=\"auto\" height=\"auto\"/></p>");
		var url =tipoEnvio;
	   	url += ".action?idLote="+loteId;
	   
	    var dialogoEnvios = $('#dialogLotes');
	    dialogoEnvios.load(
        		url,
        		{
   	    			autoOpen: true,
   	    			width: 810,
   	    			modal: true,
   	    			show: 'blind',
   	    			hide: 'blind'
   	    		},
                function(responseText, textStatus, XMLHttpRequest) {
   	    			dialogoEnvios.dialog({
		           	 			autoOpen: true,
		           				width: 950,
		           				modal: true,
		           				show: 'blind',
		           				hide: 'blind',
		           			});
		           	    		$('div#ajaxloader_'+enlace.id).html(auxHTML);
                }

        );
        return false;	    
	           
	}

	function loadMisim(enlace,idLote,tipoEnvio, idMensaje) {
		var auxHTML = $('div#ajaxloader_'+enlace.id).html();
		$('div#ajaxloader_'+enlace.id).html("<p><img src=\"<%=request.getContextPath()%>/img/ajax-loader.gif\" width=\"auto\" height=\"auto\"/></p>");
		var url =tipoEnvio;
// 	   	url += ".action?idLote="+idLote;
	   	url += ".action?idLote="+idLote+"&idMensaje="+idMensaje;

	    var dialogoMisim = $('#dialogMisim');
	    dialogoMisim.load(
        		url,
        		{
   	    			autoOpen: true,
   	    			width: 810,
   	    			modal: true,
   	    			show: 'blind',
   	    			hide: 'blind'
   	    		},
                function(responseText, textStatus, XMLHttpRequest) {
   	    			dialogoMisim.dialog({
		           	 			autoOpen: true,
		           				width: 950,
		           				modal: true,
		           				show: 'blind',
		           				hide: 'blind',
		           			});
		           	    		$('div#ajaxloader_'+enlace.id).html(auxHTML);
                }

        );
        return false;	    
	           
	}

	function loadPassbook(enlace,idServicioOrganismo,idServicio,idOrganismo) {
		var auxHTML = $('div#ajaxloader_'+enlace.id).html();
		$('div#ajaxloader_'+enlace.id).html("<p><img src=\"<%=request.getContextPath()%>/img/ajax-loader.gif\" width=\"auto\" height=\"auto\"/></p>");
		var url = "viewPassbook.action?idServicioOrganismo="+idServicioOrganismo+"&idServicio="+idServicio+"&idOrganismo="+idOrganismo;

	    var dialogoPassbook = $('#dialogPassbook');
	    dialogoPassbook.load(
        		url,
        		{
   	    			autoOpen: true,
   	    			width: 810,
   	    			modal: true,
   	    			show: 'blind',
   	    			hide: 'blind'
   	    		},
                function(responseText, textStatus, XMLHttpRequest) {
   	    			dialogoPassbook.dialog({
		           	 			autoOpen: true,
		           				width: 810,
		           				modal: true,
		           				show: 'blind',
		           				hide: 'blind',
		           			});
		           	    		$('div#ajaxloader_'+enlace.id).html(auxHTML);
		           	    		$('#formSaveViewPassbook').on('submit', function(e){
			           	 			e.preventDefault();

									var formData = new FormData($('#formSaveViewPassbook')[0]);
			           	 			
			           	 			$.ajax({
				           	 			type: 'post',
				           	 			url: 'saveViewPassbook.action',
				           	 			data: formData,
				           	 			processData: false,
				           	 			contentType: false,
				           	 			success: function () {
				           	 				loadPassbook(enlace,idServicioOrganismo,idServicio,idOrganismo);
					           	 		}
				           	 		});
			           	 		});
                }

        );
        return false;	    
	           
	}

	function deleteImagenLogo(enlace,idServicioOrganismo,idServicio,idOrganismo) {
			var auxHTML = $('div#ajaxloader_'+enlace.id).html();
			$('div#ajaxloader_'+enlace.id).html("<p><img src=\"<%=request.getContextPath()%>/img/ajax-loader.gif\" width=\"auto\" height=\"auto\"/></p>");
			var url = "deleteImagenLogo.action?idServicioOrganismo="+idServicioOrganismo+"&idServicio="+idServicio+"&idOrganismo="+idOrganismo;
	
		       	var dialogoPassbook = $('#dialogPassbook');
		       	    	dialogoPassbook.load(
		       	    			url,
		               		{
		          	    			autoOpen: true,
		          	    			width: 810,
		          	    			modal: true,
		          	    			show: 'blind',
		          	    			hide: 'blind'
		          	    		},
		                       function(responseText, textStatus, XMLHttpRequest) {
		          	    			dialogoPassbook.dialog({
		       		           	 			autoOpen: true,
		       		           				width: 810,
		       		           				modal: true,
		       		           				show: 'blind',
		       		           				hide: 'blind',
		       		           			});
		       		           	    		$('div#ajaxloader_'+enlace.id).html(auxHTML);
		    		           	    		$('#formSaveViewPassbook').on('submit', function(e){
		    			           	 			e.preventDefault();

		    									var formData = new FormData($('#formSaveViewPassbook')[0]);
		    			           	 			
		    			           	 			$.ajax({
		    				           	 			type: 'post',
		    				           	 			url: 'saveViewPassbook.action',
		    				           	 			data: formData,
		    				           	 			processData: false,
		    				           	 			contentType: false,
		    				           	 			success: function () {
		    				           	 				loadPassbook(enlace,idServicioOrganismo,idServicio,idOrganismo);
		    					           	 		}
		    				           	 		});
		    			           	 		});
		                       }
	
		               );
	        return false;	    
// 		}
	}

	function deleteImagenBackground(enlace,idServicioOrganismo,idServicio,idOrganismo) {
		var auxHTML = $('div#ajaxloader_'+enlace.id).html();
		$('div#ajaxloader_'+enlace.id).html("<p><img src=\"<%=request.getContextPath()%>/img/ajax-loader.gif\" width=\"auto\" height=\"auto\"/></p>");
		var url = "deleteImagenBackground.action?idServicioOrganismo="+idServicioOrganismo+"&idServicio="+idServicio+"&idOrganismo="+idOrganismo;

	       	var dialogoPassbook = $('#dialogPassbook');
	       	    	dialogoPassbook.load(
	       	    			url,
	               		{
	          	    			autoOpen: true,
	          	    			width: 810,
	          	    			modal: true,
	          	    			show: 'blind',
	          	    			hide: 'blind'
	          	    		},
	                       function(responseText, textStatus, XMLHttpRequest) {
	          	    			dialogoPassbook.dialog({
	       		           	 			autoOpen: true,
	       		           				width: 810,
	       		           				modal: true,
	       		           				show: 'blind',
	       		           				hide: 'blind',
	       		           			});
	       		           	    		$('div#ajaxloader_'+enlace.id).html(auxHTML);
	    		           	    		$('#formSaveViewPassbook').on('submit', function(e){
	    			           	 			e.preventDefault();

	    									var formData = new FormData($('#formSaveViewPassbook')[0]);
	    			           	 			
	    			           	 			$.ajax({
	    				           	 			type: 'post',
	    				           	 			url: 'saveViewPassbook.action',
	    				           	 			data: formData,
	    				           	 			processData: false,
	    				           	 			contentType: false,
	    				           	 			success: function () {
	    				           	 				loadPassbook(enlace,idServicioOrganismo,idServicio,idOrganismo);
	    					           	 		}
	    				           	 		});
	    			           	 		});
	                       }

	               );
        return false;	    
	}

	function deleteImagenIcon(enlace,idServicioOrganismo,idServicio,idOrganismo) {
		var auxHTML = $('div#ajaxloader_'+enlace.id).html();
		$('div#ajaxloader_'+enlace.id).html("<p><img src=\"<%=request.getContextPath()%>/img/ajax-loader.gif\" width=\"auto\" height=\"auto\"/></p>");
		var url = "deleteImagenIcon.action?idServicioOrganismo="+idServicioOrganismo+"&idServicio="+idServicio+"&idOrganismo="+idOrganismo;

	       	var dialogoPassbook = $('#dialogPassbook');
	       	    	dialogoPassbook.load(
	       	    			url,
	               		{
	          	    			autoOpen: true,
	          	    			width: 810,
	          	    			modal: true,
	          	    			show: 'blind',
	          	    			hide: 'blind'
	          	    		},
	                       function(responseText, textStatus, XMLHttpRequest) {
	          	    			dialogoPassbook.dialog({
	       		           	 			autoOpen: true,
	       		           				width: 810,
	       		           				modal: true,
	       		           				show: 'blind',
	       		           				hide: 'blind',
	       		           			});
	       		           	    		$('div#ajaxloader_'+enlace.id).html(auxHTML);
	    		           	    		$('#formSaveViewPassbook').on('submit', function(e){
	    			           	 			e.preventDefault();

	    									var formData = new FormData($('#formSaveViewPassbook')[0]);
	    			           	 			
	    			           	 			$.ajax({
	    				           	 			type: 'post',
	    				           	 			url: 'saveViewPassbook.action',
	    				           	 			data: formData,
	    				           	 			processData: false,
	    				           	 			contentType: false,
	    				           	 			success: function () {
	    				           	 				loadPassbook(enlace,idServicioOrganismo,idServicio,idOrganismo);
	    					           	 		}
	    				           	 		});
	    			           	 		});
	                       }

	               );
        return false;	    
	}

	function previsualizar(enlace,idServicioOrganismo,idServicio,idOrganismo) {
		var auxHTML = $('div#ajaxloader_'+enlace.id).html();
		$('div#ajaxloader_'+enlace.id).html("<p><img src=\"<%=request.getContextPath()%>/img/ajax-loader.gif\" width=\"auto\" height=\"auto\"/></p>");
		var url = "previsualizar.action?idServicioOrganismo="+idServicioOrganismo+"&idServicio="+idServicio+"&idOrganismo="+idOrganismo;

		$.ajax({
	           type: "POST",
	           url: url,
	           error: function(data)
	           {
  	 				loadPassbook(enlace,idServicioOrganismo,idServicio,idOrganismo);
	           }
	         });
        return false;	    
	}

	function generarClaves() {
		var vapidPublicKey =  document.getElementById('servicio.vapidPublicKey').value;
		var vapidPrivateKey =  document.getElementById('servicio.vapidPrivateKey').value;
		if(null != vapidPublicKey && null != vapidPrivateKey && vapidPublicKey.length > 0 && vapidPrivateKey.length > 0){

			if (confirm('Si modifica las claves los usuarios registrados con la clave anterior dejar�n de recibir notificaciones. Adem�s se deber�n realizar modificaciones en el c�digo de la acplicaci�n cliente. �Desea Continuar?')) {
				$.ajax({
			           type: "POST",
			           url: "generarClavesServicio.action",
			           dataType : "json",
			           success: function(data)
				        {	
			        	    json = JSON.parse(data);
					        
				     	 	$("input[id='servicio.vapidPublicKey']").val(json.PublicKey);
				     		$("input[id='servicio.vapidPrivateKey']").val(json.PrivateKey);
				     		$("input[id='vapidPublicKey']").val(json.PublicKey);
				     		$("input[id='vapidPrivateKey']").val(json.PrivateKey);
				     		
				        },
				        error: function(data)
				        {
				     	   	alert("error..."); 
				        }
				      });
			} else {
			    // Do nothing!
			}
		}else{
			$.ajax({
		           type: "POST",
		           url: "generarClavesServicio.action",
		           dataType : "json",
		           success: function(data)
			        {	
		        	    json = JSON.parse(data);
				        
		        	    $("input[id='servicio.vapidPublicKey']").val(json.PublicKey);
			     		$("input[id='servicio.vapidPrivateKey']").val(json.PrivateKey);
			     		$("input[id='vapidPublicKey']").val(json.PublicKey);
			     		$("input[id='vapidPrivateKey']").val(json.PrivateKey);
			     		
			        },
			        error: function(data)
			        {
			     	   	alert("Se ha producido un error durante la generaci�n. Int�ntelo m�s tarde"); 
			        }
			      });
			}
	}

	function loadLoteHist(enlace,loteId,tipoEnvio) {
		var auxHTML = $('div#ajaxloader_'+enlace.id).html();
		$('div#ajaxloader_'+enlace.id).html("<p><img src=\"<%=request.getContextPath()%>/img/ajax-loader.gif\" width=\"auto\" height=\"auto\"/></p>");
		var url =tipoEnvio;
	   	url += ".action?idLote="+loteId;
	   
	    var dialogoEnvios = $('#dialogLotes');
	    dialogoEnvios.load(
        		url,
        		{
   	    			autoOpen: true,
   	    			width: 810,
   	    			modal: true,
   	    			show: 'blind',
   	    			hide: 'blind'
   	    		},
                function(responseText, textStatus, XMLHttpRequest) {
   	    			dialogoEnvios.dialog({
		           	 			autoOpen: true,
		           				width: 950,
		           				modal: true,
		           				show: 'blind',
		           				hide: 'blind',
		           			});
		           	    		$('div#ajaxloader_'+enlace.id).html(auxHTML);
                }

        );
        return false;	    
	           
	}


	function loadMensaje(enlace,mensajeId,tipoEnvio,tipomensaje,idLote) {
		var auxHTML = $('div#ajaxloader_'+enlace.id).html();
		$('div#ajaxloader_'+enlace.id).html("<p><img src=\"<%=request.getContextPath()%>/img/ajax-loader.gif\" width=\"auto\" height=\"auto\"/></p>");
		
		var aux ="";
		var t = "";
	   	if(tipomensaje == 'SMS'){
	   		aux="SMS";
	   		t = "SMS";
	   	}else if(tipomensaje == 'EMAIL'){
	   		aux="";
	   		t="Email";
	   	}else if(tipomensaje == 'RECEPCION SMS'){
	   		aux="SMS";
	   		t = "SMS";
	   	}else {
	   		aux="NotificacionPush";
	   		t= "NotificacionPush";
		}
		
		var url =tipoEnvio+aux;
	   	url += ".action?idMensaje="+mensajeId+"&idLote="+idLote;
	   
	   	var dialogoEnvios = $('#dialogEnvios'+t);
	    dialogoEnvios.load(
        		url,
        		{
   	    			autoOpen: true,
   	    			width: 810,
   	    			modal: true,
   	    			show: 'blind',
   	    			hide: 'blind'
   	    		},
                function(responseText, textStatus, XMLHttpRequest) {
   	    			dialogoEnvios.dialog({
		           	 			autoOpen: true,
		           				width: 950,
		           				modal: true,
		           				show: 'blind',
		           				hide: 'blind',
		           			});
		           	    		$('div#ajaxloader_'+enlace.id).html(auxHTML);
                }

        );
        return false;	    
	           
	}

	function loadMensajeHist(enlace,mensajeId,tipoEnvio,tipomensaje,idLote) {
		var auxHTML = $('div#ajaxloader_'+enlace.id).html();
		$('div#ajaxloader_'+enlace.id).html("<p><img src=\"<%=request.getContextPath()%>/img/ajax-loader.gif\" width=\"auto\" height=\"auto\"/></p>");
		
		var aux ="";
		var t = "";
	   	if(tipomensaje == 'SMS'){
	   		aux="SMS";
	   		t = "SMS";
	   	}else if(tipomensaje == 'EMAIL'){
	   		aux="";
	   		t="Email";
	   	}else if(tipomensaje == 'RECEPCION SMS'){
	   		aux="SMS";
	   		t = "SMS";
	   	}else {
	   		aux="NotificacionPush";
	   		t= "NotificacionPush";
		}
		
		var url =tipoEnvio+aux+"Historico";
	   	url += ".action?idMensaje="+mensajeId+"&idLote="+idLote;
	   
	   	var dialogoEnvios = $('#dialogEnvios'+t);
	    dialogoEnvios.load(
        		url,
        		{
   	    			autoOpen: true,
   	    			width: 810,
   	    			modal: true,
   	    			show: 'blind',
   	    			hide: 'blind'
   	    		},
                function(responseText, textStatus, XMLHttpRequest) {
   	    			dialogoEnvios.dialog({
		           	 			autoOpen: true,
		           				width: 950,
		           				modal: true,
		           				show: 'blind',
		           				hide: 'blind',
		           			});
		           	    		$('div#ajaxloader_'+enlace.id).html(auxHTML);
                }

        );
        return false;	    
	           
	}
	

	function loadMensajeHistorico(enlace,mensajeId,tipoEnvio,destinatarioMensaje,tipoMensaje) {
		var auxHTML = $('div#ajaxloader_'+enlace.id).html();
		$('div#ajaxloader_'+enlace.id).html("<p><img src=\"<%=request.getContextPath()%>/img/ajax-loader.gif\" width=\"auto\" height=\"auto\"/></p>");
		
		
		var url =tipoEnvio;
	   	url += ".action?idMensaje="+mensajeId+"&idDestinatariosMensajes="+destinatarioMensaje;
	   
	   	var dialogoEnvios = $('#dialogHistoricoEnvio');
	    dialogoEnvios.load(
        		url,
        		{
   	    			autoOpen: true,
   	    			width: 810,
   	    			modal: true,
   	    			show: 'blind',
   	    			hide: 'blind'
   	    		},
                function(responseText, textStatus, XMLHttpRequest) {
   	    			dialogoEnvios.dialog({
		           	 			autoOpen: true,
		           				width: 950,
		           				modal: true,
		           				show: 'blind',
		           				hide: 'blind',
		           			});
		           	    		$('div#ajaxloader_'+enlace.id).html(auxHTML);
                }

        );
        return false;	    
	           
	}
	
	function loadEnvioHistorico(enlace,envioId,tipoEnvio) {
		var auxHTML = $('div#ajaxloader_'+enlace.id).html();
		$('div#ajaxloader_'+enlace.id).html("<p><img src=\"<%=request.getContextPath()%>/img/ajax-loader.gif\" width=\"auto\" height=\"auto\"/></p>");
		var arrayEnvioId = envioId.split("_");
		var t="";
		if(arrayEnvioId[0] == '2'){
	   		t="SMS";
	   		
	   	}else if(arrayEnvioId[0] == '1'){
	   		t="Email";
	   		
	   	}else {
	   		t="NotificacionPush";
		}

		var aux ="";

		if(arrayEnvioId[0] == '2'){
	   		aux="SMS";
	   		
	   	}else if(arrayEnvioId[0] == '4'){
	   		aux="NotificacionPush";
	   	}
		
		var url =tipoEnvio+aux;
	   	url += "Historico.action?idEnvio="+envioId;
	   
	    var dialogoEnvios = $('#dialogEnvios'+t);
	    dialogoEnvios.load(
        		url,
        		{
   	    			autoOpen: true,
   	    			width: 810,
   	    			modal: true,
   	    			show: 'blind',
   	    			hide: 'blind'
   	    		},
                function(responseText, textStatus, XMLHttpRequest) {
   	    			dialogoEnvios.dialog({
		           	 			autoOpen: true,
		           				width: 950,
		           				modal: true,
		           				show: 'blind',
		           				hide: 'blind',
		           			});
		           	    		$('div#ajaxloader_'+enlace.id).html(auxHTML);
                }

        );
        return false;	    
	           
	}
	function loadPlan(enlace,idplanificacion,idRetorno,varRetorno,actionRetorno) {
		var auxHTML = $('div#ajaxloader_'+enlace.id).html();
		$('div#ajaxloader_'+enlace.id).html("<p><img src=\"<%=request.getContextPath()%>/img/ajax-loader.gif\" width=\"auto\" height=\"auto\"/></p>");
		var height="height:125px";
		var heightN=125;
		var url ="";
	   if(varRetorno=="idAplicacion"){
        	url = "loadPlanificacionesApp.action?idPlanificacion="+idplanificacion+"&idAplicacion="+idRetorno;
        	height="height:150px";heightN=150;
	   }else if(varRetorno=="idServicio"){
       		url = "loadPlanificacionesServ.action?idPlanificacion="+idplanificacion+"&idServicio="+idRetorno;
       		height="height:150px";heightN=150;
	   }else if(varRetorno=="idServidor"){
       		url = "loadPlanificacionesServer.action?idPlanificacion="+idplanificacion+"&idServidor="+idRetorno;
	   }else if(varRetorno=="idProveedorSMS"){
		   url = "loadPlanificacionesProveedorSMS.action?idPlanificacion="+idplanificacion+"&idProveedorSMS="+idRetorno;
	   }else if(varRetorno=="idReceptorSMS"){
		   url = "loadPlanificacionesReceptorSMS.action?idPlanificacion="+idplanificacion+"&idReceptorSMS="+idRetorno;
	   }else if(varRetorno=="idServidorPush"){
		   url = "loadPlanificacionesServidorPush.action?idPlanificacion="+idplanificacion+"&idServidorPush="+idRetorno;
	   }else if(varRetorno=="idServidorWebPush"){
		   url = "loadPlanificacionesServidorWebPush.action?idPlanificacion="+idplanificacion+"&idServidorWebPush="+idRetorno;
	   }else if(varRetorno=="idOrganismo"){
		   url = "loadPlanificacionesOrganismo.action?idPlanificacion="+idplanificacion+"&idOrganismo="+idRetorno;
		   height="height:150px";heightN=150;
	   }
	   

        var dialogoPlanificacion = $('#dialogPlanifications');
        
        
        
        dialogoPlanificacion.load(
        		url,
        		{
   	    			autoOpen: true,
   	    			width: 810,
   	    			modal: true,
   	    			show: 'blind',
   	    			hide: 'blind'
   	    		},
                function(responseText, textStatus, XMLHttpRequest) {
   	    			dialogoPlanificacion.attr('style',height);
		           	    		dialogoPlanificacion.dialog({
		           	 			autoOpen: true,
		           				width: 800,
		           				height:heightN,
		           				modal: true,
		           				show: 'blind',
		           				hide: 'blind',
		           				style: height,
		           			});
		           	    		$('div#ajaxloader_'+enlace.id).html(auxHTML);
                }

        );
        return false;
    }
    
	function connectingModal(){
		 var dialogConnecting = $('#dialogConnecting');
		 
		 dialogConnecting.dialog({
			 autoOpen: true,
		 	modal: true,
				width: 100,
   				height:90,
		 	 show: 'blind',
		 	 hide: 'blind',
		 });
		 $('#dialogConnecting').parent().find('a.ui-dialog-titlebar-close').hide();
	}
	function newPlan(enlace,idAplicacion,idServicio) {
		var auxHTML = $('div#ajaxloader_'+enlace.id).html();
		 $('div#ajaxloader_'+enlace.id).html('<p><img src="<%=request.getContextPath()%>/img/ajax-loader.gif" width="auto" height="auto" /></p>');
		var url = "nuevaPlanificacionApp.action?idAplicacion="+idAplicacion+"&idServicio="+idServicio;
	   
        var dialogoPlanificacion = $('#dialogPlanifications');
        
        
        dialogoPlanificacion.load(
        		url,
        		{
   	    			autoOpen: true,
   	    			width: 810,
   	    			modal: true,
   	    			show: 'blind',
   	    			hide: 'blind'
   	    		},
                function(responseText, textStatus, XMLHttpRequest) {
   	    			dialogoPlanificacion.attr('style','min-height:150px');
		           	    		dialogoPlanificacion.dialog({
		           	 			autoOpen: true,
		           				width: 800,
		           				height:150,
		           				modal: true,
		           				show: 'blind',
		           				hide: 'blind',
		           				style: 'min-height:150px',
		           			});
		           	    	 $('div#ajaxloader_'+enlace.id).html(auxHTML);
                }

        );
        return false;
    }	
	
</script>

<script type="text/javascript">
	var imgsrc="<%=request.getContextPath()%>/images/layouts/";
</script>
<s:head/>
</head>

<body>

<div id="generalContent">
	<tiles:insertAttribute name='header'/>

	<!-- <div id="contenedor_principal"> -->

	<div id="mainContainer">
	<h2 class="breadCrumb">
            <span>Est�s en..</span>
            <framework:crumb /> <s:if test='detalleAplicacion != null'><a href="viewAplicacion.action?idAplicacion=${detalleAplicacion.aplicacionId}">${detalleAplicacion.nombre}</a></s:if>
           <!-- <a href="#">CONFIGURACI�N DE APLICACIONES</a><a href="#">Aplicaciones</a></h2>-->
    </h2>
		
	<tiles:insertAttribute name='menu'/>
		
		
			<tiles:insertAttribute name='body'/>			
		
	</div>
	<!-- </div>-->
	
</div>

</body>

</html>
