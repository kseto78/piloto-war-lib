<%@ taglib prefix="s" uri="/struts-tags" %>
<script>
	Date.prototype.toLocaleFormat = Date.prototype.toLocaleFormat || function(pattern) {
		var month = this.getMonth() +1;
		var day = this.getDate();
		if(month<10){
			month="0"+month;
		}
		if(day<10){
			day="0"+day;
		}
	    return pattern.replace(/%Y/g, this.getFullYear()).replace(/%m/g, month).replace(/%d/g, day);
	};
	var formatDates='%d/%m/%Y';
	function validateDates(id1,id2,obj){
		var desde = document.getElementById(id1);
		var hasta = document.getElementById(id2);
		var hoy = new Date();
		var from = null; 
		var to = null;
		try{
			from = desde.value.split("/");
		}catch(e){
			desde.value=hoy.toLocaleFormat(formatDates);;
		}
		try{
			to = hasta.value.split("/");
		}catch(e){
			hasta.value=hoy.toLocaleFormat(formatDates);;
		}
		
		var fechaDesde = new Date(from[2],from[1]-1,from[0]);
		var fechaHasta = new Date(to[2],to[1]-1,to[0]);
		hoy.setHours(0,0,0,0);
		if(fechaDesde.getTime()>hoy.getTime()){
			desde.value=hoy.toLocaleFormat(formatDates);
			hasta.value=hoy.toLocaleFormat(formatDates);
		}else if(fechaHasta.getTime()>hoy.getTime()){
			hasta.value=hoy.toLocaleFormat(formatDates);
		}else if(fechaDesde.getTime()>fechaHasta.getTime()){
			if(obj.id==desde.id){
				hasta.value=fechaDesde.toLocaleFormat(formatDates);
			}else{
				desde.value=fechaHasta.toLocaleFormat(formatDates);
			}
		}
	}
	function confirmDelete(n){
		var r = confirm("¿Desea eliminar el elemento '"+n+"'?");
		if(r){return true;
		}else{return false;
		}
	}
	function confirmDelete(){
		var r = confirm("¿Desea eliminar el elemento?");
		if(r){return true;
		}else{return false;
		}
	}
    
	function confirmDeleteSelected(){
		var r = confirm('¿Desea eliminar los elementos seleccionados?');
		if(r){return true;
		}else{return false;
		}
	} 
	function 	makeExcellWrap(enlace){
		var div = document.getElementById('exportOptions');
		var elms = div.getElementsByTagName("a");
		var maxI=0;
		for(var i = 0, maxI = elms.length; i < maxI; ++i) {
			var a = elms[i];
			enlace.href=a.href.substring(a.href.indexOf("?",0));
		}
	}

	function makeExcell(enlace){
		var div = document.getElementById('exportOptions');
		var elms = div.getElementsByTagName("a");
		var maxI=0;
		for(var i = 0, maxI = elms.length; i < maxI; ++i) {
			var a = elms[i];
			enlace.href=a.href;
		}
	}
	

	function checkProductos(combo){
		
		var nombreProducto= document.getElementById("proveedorMisim.producto.nombre");
		var nombreProductoLabel= document.getElementById("proveedorMisim.nombreProductoLabel");

		var codigoProducto= document.getElementById("proveedorMisim.producto.codigo");
		var codigoProductoLabel= document.getElementById("proveedorMisim.codigoProductoLabel");
	
		if(combo.value=="-2" || combo.value==null){

			comboBck = combo.value;
			var nprodBck="";
			var cprodBck="";
			
			if (nombreProducto != null) {
				nprodBck=nombreProducto.value;
				nombreProducto.value=nprodBck;
				nombreProducto.disabled="";
				nombreProducto.style.visibility="visible";
				nombreProducto.style.display="block";
				nombreProductoLabel.value="";
				nombreProductoLabel.disabled="";
				nombreProductoLabel.style.visibility="visible";
				nombreProductoLabel.style.display="block";
			}

			if (codigoProducto != null) {
				cprodBck=codigoProducto.value;
				codigoProducto.value=cprodBck;
				codigoProducto.disabled="";
				codigoProducto.style.visibility="visible";
				codigoProducto.style.display="block";
				codigoProductoLabel.value="";
				codigoProductoLabel.disabled="";
				codigoProductoLabel.style.visibility="visible";
				codigoProductoLabel.style.display="block";
			}

		} else {

			var nprodBck="";
			var cprodBck="";
			
			if (nombreProducto != null) {
				nprodBck=nombreProducto.value;
				nombreProducto.value=nprodBck;
				nombreProducto.disabled="disabled";
				nombreProducto.style.visibility="visible";
				nombreProducto.style.display="block";
				nombreProductoLabel.value="";
				nombreProductoLabel.disabled="disabled";
				nombreProductoLabel.style.visibility="visible";
				nombreProductoLabel.style.display="block";
			}
	
			if (codigoProducto != null) {
				cprodBck=codigoProducto.value;
				codigoProducto.value=cprodBck;
				codigoProducto.disabled="disabled";
				codigoProducto.style.visibility="visible";
				codigoProducto.style.display="block";
				codigoProductoLabel.value="";
				codigoProductoLabel.disabled="disabled";
				codigoProductoLabel.style.visibility="visible";
				codigoProductoLabel.style.display="block";
			}			
		}
		if (combo.value == 3) {
				document.getElementById("proveedorCompany").style.display = "";
			}
		else{
				document.getElementById("proveedorCompany").style.display = "none";
				document.getElementById("proveedorMisim.company").selectedIndex = "0";
			}		
	}

	function checkEndpoints(combo){

		var Comunicacion= document.getElementById("proveedorMisim.endpoint.comunicacion.idComunicacion");
		var comboComunicacionLabel= document.getElementById("proveedorMisim.comboComunicacionLabel");

		var nombreEndpoint= document.getElementById("proveedorMisim.endpoint.nombre");
		var nombreEndpointLabel= document.getElementById("proveedorMisim.nombreEndpointLabel");

		var urlEndpoint= document.getElementById("proveedorMisim.endpoint.urlEndpoint");
		var urlEndpointEndpointLabel= document.getElementById("proveedorMisim.urlEndpointEndpointLabel");
		
		var serviceNameEndpoint= document.getElementById("proveedorMisim.endpoint.serviceName");
		var serviceNameEndpointLabel= document.getElementById("proveedorMisim.serviceNameEndpointLabel");

		var portNameEndpoint= document.getElementById("proveedorMisim.endpoint.portName");
		var portNameEndpointLabel= document.getElementById("proveedorMisim.portNameEndpointLabel");

		var targetNameEndpoint= document.getElementById("proveedorMisim.endpoint.targetName");
		var targetNameEndpointLabel= document.getElementById("proveedorMisim.targetNameEndpointLabel");

		var operationEndpoint= document.getElementById("proveedorMisim.endpoint.urlOperation");
		var operationEndpointLabel= document.getElementById("proveedorMisim.operationEndpointLabel");

		var timeoutEndpoint= document.getElementById("proveedorMisim.endpoint.timeout");
		var timeoutEndpointLabel= document.getElementById("proveedorMisim.timeoutEndpointLabel");
		
	
		if(combo.value=="-2" || combo.value==null){

			comboBck = combo.value;
			var comendpBck="";
			var nendpBck="";
			var urlendpBck="";
			var snendpBck="";
			var pnendpBck="";
			var tnendpBck="";
			var oendpBck="";
			var tendpBck="";
			
			if (Comunicacion != null) {
				comendpBck=Comunicacion.value;
				Comunicacion.value=comendpBck;
				Comunicacion.disabled="";
				Comunicacion.style.visibility="visible";
				Comunicacion.style.display="block";
				comboComunicacionLabel.value="";
				comboComunicacionLabel.disabled="";
				comboComunicacionLabel.style.visibility="visible";
				comboComunicacionLabel.style.display="block";
			}

			if (nombreEndpoint != null) {
				nendpBck=nombreEndpoint.value;
				nombreEndpoint.value=nendpBck;
				nombreEndpoint.disabled="";
				nombreEndpoint.style.visibility="visible";
				nombreEndpoint.style.display="block";
				nombreEndpointLabel.value="";
				nombreEndpointLabel.disabled="";
				nombreEndpointLabel.style.visibility="visible";
				nombreEndpointLabel.style.display="block";
			}

			if (urlEndpoint != null) {
				urlendpBck=urlEndpoint.value;
				urlEndpoint.value=urlendpBck;
				urlEndpoint.disabled="";
				urlEndpoint.style.visibility="visible";
				urlEndpoint.style.display="block";
				urlEndpointEndpointLabel.value="";
				urlEndpointEndpointLabel.disabled="";
				urlEndpointEndpointLabel.style.visibility="visible";
				urlEndpointEndpointLabel.style.display="block";
			}

			if (serviceNameEndpoint != null) {
				snendpBck=serviceNameEndpoint.value;
				serviceNameEndpoint.value=snendpBck;
				serviceNameEndpoint.disabled="";
				serviceNameEndpoint.style.visibility="visible";
				serviceNameEndpoint.style.display="block";
				serviceNameEndpointLabel.value="";
				serviceNameEndpointLabel.disabled="";
				serviceNameEndpointLabel.style.visibility="visible";
				serviceNameEndpointLabel.style.display="block";
			}

			if (portNameEndpoint != null) {
				pnendpBck=portNameEndpoint.value;
				portNameEndpoint.value=pnendpBck;
				portNameEndpoint.disabled="";
				portNameEndpoint.style.visibility="visible";
				portNameEndpoint.style.display="block";
				portNameEndpointLabel.value="";
				portNameEndpointLabel.disabled="";
				portNameEndpointLabel.style.visibility="visible";
				portNameEndpointLabel.style.display="block";
			}

			if (targetNameEndpoint != null) {
				tnendpBck=targetNameEndpoint.value;
				targetNameEndpoint.value=tnendpBck;
				targetNameEndpoint.disabled="";
				targetNameEndpoint.style.visibility="visible";
				targetNameEndpoint.style.display="block";
				targetNameEndpointLabel.value="";
				targetNameEndpointLabel.disabled="";
				targetNameEndpointLabel.style.visibility="visible";
				targetNameEndpointLabel.style.display="block";
			}

			if (operationEndpoint != null) {
				oendpBck=operationEndpoint.value;
				operationEndpoint.value=oendpBck;
				operationEndpoint.disabled="";
				operationEndpoint.style.visibility="visible";
				operationEndpoint.style.display="block";
				operationEndpointLabel.value="";
				operationEndpointLabel.disabled="";
				operationEndpointLabel.style.visibility="visible";
				operationEndpointLabel.style.display="block";
			}

			if (timeoutEndpoint != null) {
				tendpBck=timeoutEndpoint.value;
				timeoutEndpoint.value=tendpBck;
				timeoutEndpoint.disabled="";
				timeoutEndpoint.style.visibility="visible";
				timeoutEndpoint.style.display="block";
				timeoutEndpointLabel.value="";
				timeoutEndpointLabel.disabled="";
				timeoutEndpointLabel.style.visibility="visible";
				timeoutEndpointLabel.style.display="block";
			}

		} else {
			
			var comendpBck="";
			var nendpBck="";
			var urlendpBck="";
			var snendpBck="";
			var pnendpBck="";
			var tnendpBck="";
			var oendpBck="";
			var tendpBck="";

			if (Comunicacion != null) {
				comendpBck=Comunicacion.value;
				Comunicacion.value=comendpBck;
				Comunicacion.disabled="disabled";
				Comunicacion.style.visibility="visible";
				Comunicacion.style.display="block";
				comboComunicacionLabel.value="";
				comboComunicacionLabel.disabled="disabled";
				comboComunicacionLabel.style.visibility="visible";
				comboComunicacionLabel.style.display="block";
			}

			if (nombreEndpoint != null) {
				nendpBck=nombreEndpoint.value;
				nombreEndpoint.value=nendpBck;
				nombreEndpoint.disabled="disabled";
				nombreEndpoint.style.visibility="visible";
				nombreEndpoint.style.display="block";
				nombreEndpointLabel.value="";
				nombreEndpointLabel.disabled="disabled";
				nombreEndpointLabel.style.visibility="visible";
				nombreEndpointLabel.style.display="block";
			}

			if (urlEndpoint != null) {
				urlendpBck=urlEndpoint.value;
				urlEndpoint.value=urlendpBck;
				urlEndpoint.disabled="disabled";
				urlEndpoint.style.visibility="visible";
				urlEndpoint.style.display="block";
				urlEndpointEndpointLabel.value="";
				urlEndpointEndpointLabel.disabled="disabled";
				urlEndpointEndpointLabel.style.visibility="visible";
				urlEndpointEndpointLabel.style.display="block";
			}

			if (serviceNameEndpoint != null) {
				snendpBck=serviceNameEndpoint.value;
				serviceNameEndpoint.value=snendpBck;
				serviceNameEndpoint.disabled="disabled";
				serviceNameEndpoint.style.visibility="visible";
				serviceNameEndpoint.style.display="block";
				serviceNameEndpointLabel.value="";
				serviceNameEndpointLabel.disabled="disabled";
				serviceNameEndpointLabel.style.visibility="visible";
				serviceNameEndpointLabel.style.display="block";
			}

			if (portNameEndpoint != null) {
				pnendpBck=portNameEndpoint.value;
				portNameEndpoint.value=pnendpBck;
				portNameEndpoint.disabled="disabled";
				portNameEndpoint.style.visibility="visible";
				portNameEndpoint.style.display="block";
				portNameEndpointLabel.value="";
				portNameEndpointLabel.disabled="disabled";
				portNameEndpointLabel.style.visibility="visible";
				portNameEndpointLabel.style.display="block";
			}

			if (targetNameEndpoint != null) {
				tnendpBck=targetNameEndpoint.value;
				targetNameEndpoint.value=tnendpBck;
				targetNameEndpoint.disabled="disabled";
				targetNameEndpoint.style.visibility="visible";
				targetNameEndpoint.style.display="block";
				targetNameEndpointLabel.value="";
				targetNameEndpointLabel.disabled="disabled";
				targetNameEndpointLabel.style.visibility="visible";
				targetNameEndpointLabel.style.display="block";
			}

			if (operationEndpoint != null) {
				oendpBck=operationEndpoint.value;
				operationEndpoint.value=oendpBck;
				operationEndpoint.disabled="disabled";
				operationEndpoint.style.visibility="visible";
				operationEndpoint.style.display="block";
				operationEndpointLabel.value="";
				operationEndpointLabel.disabled="disabled";
				operationEndpointLabel.style.visibility="visible";
				operationEndpointLabel.style.display="block";
			}

			if (timeoutEndpoint != null) {
				tendpBck=timeoutEndpoint.value;
				timeoutEndpoint.value=tendpBck;
				timeoutEndpoint.disabled="disabled";
				timeoutEndpoint.style.visibility="visible";
				timeoutEndpoint.style.display="block";
				timeoutEndpointLabel.value="";
				timeoutEndpointLabel.disabled="disabled";
				timeoutEndpointLabel.style.visibility="visible";
				timeoutEndpointLabel.style.display="block";
			}			
			
		}
	}

	function checkTransformaciones(combo){

			var nombreTransformacion= document.getElementById("proveedorMisim.transformacion.nombre");
			var nombreTransformacionLabel= document.getElementById("proveedorMisim.nombreTransformacionLabel");
	
			var xslPeticion= document.getElementById("proveedorMisim.transformacion.xslPeticion");
			var xslPeticionLabel= document.getElementById("proveedorMisim.xslPeticionLabel");
			
			var xslRespuesta= document.getElementById("proveedorMisim.transformacion.xslRespuesta");
			var xslRespuestaLabel= document.getElementById("proveedorMisim.xslRespuestaLabel");
	
			var xslFault= document.getElementById("proveedorMisim.transformacion.xslFault");
			var xslFaultLabel= document.getElementById("proveedorMisim.xslFaultLabel");
	
			if(combo.value=="-2" || combo.value==null){
	
				comboBck = combo.value;
				var ntransBck="";
				var xslptransBck="";
				var xslrtransBck = "";
				var xslftransBck = "";
				
				if (nombreTransformacion != null) {
					ntransBck=nombreTransformacion.value;
					nombreTransformacion.value=ntransBck;
					nombreTransformacion.disabled="";
					nombreTransformacion.style.visibility="visible";
					nombreTransformacion.style.display="block";
					nombreTransformacionLabel.value="";
					nombreTransformacionLabel.disabled="";
					nombreTransformacionLabel.style.visibility="visible";
					nombreTransformacionLabel.style.display="block";
				}
	
				if (xslPeticion != null) {
					xslptransBck=xslPeticion.value;
					xslPeticion.value=xslptransBck;
					xslPeticion.disabled="";
					xslPeticion.style.visibility="visible";
					xslPeticion.style.display="block";
					xslPeticionLabel.value="";
					xslPeticionLabel.disabled="";
					xslPeticionLabel.style.visibility="visible";
					xslPeticionLabel.style.display="block";
				}
	
				if (xslRespuesta != null) {
					xslrtransBck=xslRespuesta.value;
					xslRespuesta.value=xslrtransBck;
					xslRespuesta.disabled="";
					xslRespuesta.style.visibility="visible";
					xslRespuesta.style.display="block";
					xslRespuestaLabel.value="";
					xslRespuestaLabel.disabled="";
					xslRespuestaLabel.style.visibility="visible";
					xslRespuestaLabel.style.display="block";
				}
	
				if (xslFault != null) {
					xslftransBck=xslFault.value;
					xslFault.value=xslftransBck;
					xslFault.disabled="";
					xslFault.style.visibility="visible";
					xslFault.style.display="block";
					xslFaultLabel.value="";
					xslFaultLabel.disabled="";
					xslFaultLabel.style.visibility="visible";
					xslFaultLabel.style.display="block";
				}
	
			} else {

				var ntransBck="";
				var xslptransBck="";
				var xslrtransBck = "";
				var xslftransBck = "";
	
				if (nombreTransformacion != null) {
					ntransBck=nombreTransformacion.value;
					nombreTransformacion.value=ntransBck;
					nombreTransformacion.disabled="disabled";
					nombreTransformacion.style.visibility="visible";
					nombreTransformacion.style.display="block";
					nombreTransformacionLabel.value="";
					nombreTransformacionLabel.disabled="disabled";
					nombreTransformacionLabel.style.visibility="visible";
					nombreTransformacionLabel.style.display="block";
				}
	
				if (xslPeticion != null) {
					xslptransBck=xslPeticion.value;
					xslPeticion.value=xslptransBck;
					xslPeticion.disabled="disabled";
					xslPeticion.style.visibility="visible";
					xslPeticion.style.display="block";
					xslPeticionLabel.value="";
					xslPeticionLabel.disabled="disabled";
					xslPeticionLabel.style.visibility="visible";
					xslPeticionLabel.style.display="block";
				}
	
				if (xslRespuesta != null) {
					xslrtransBck=xslRespuesta.value;
					xslRespuesta.value=xslrtransBck;
					xslRespuesta.disabled="disabled";
					xslRespuesta.style.visibility="visible";
					xslRespuesta.style.display="block";
					xslRespuestaLabel.value="";
					xslRespuestaLabel.disabled="disabled";
					xslRespuestaLabel.style.visibility="visible";
					xslRespuestaLabel.style.display="block";
				}
	
				if (xslFault != null) {
					xslftransBck=xslFault.value;
					xslFault.value=xslftransBck;
					xslFault.disabled="disabled";
					xslFault.style.visibility="visible";
					xslFault.style.display="block";
					xslFaultLabel.value="";
					xslFaultLabel.disabled="disabled";
					xslFaultLabel.style.visibility="visible";
					xslFaultLabel.style.display="block";
				}
			}
		}
	
	function numbersonly(myfield, e, dec)
	{
	var key;
	var keychar;

	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);

	// control keys
	if ((key==null) || (key==0) || (key==8) || 
	    (key==9) || (key==13) || (key==27) )
	   return true;

	// numbers
	else if ((("0123456789").indexOf(keychar) > -1))
	   return true;

	// decimal point jump
	else if (dec && (keychar == "."))
	   {
	   myfield.form.elements[dec].focus();
	   return false;
	   }
	else
	   return false;
	}

	
	

</script>
