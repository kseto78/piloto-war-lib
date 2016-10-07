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
	function verifyEmail(mail){
	var status = false;     
	var emailRegEx = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;
		 if(mail.value!=""){
		     if (mail.value.search(emailRegEx) == -1) {
		          alert("Por favor introduce una dirección correcta");
		          mail.value="";
		          mail.focus();
		     }
		     else {
		          status = true;
		     }
		 }else{
			 status = true;
		 }
	     return status;
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
	var hBck="";
	var fmBck="";
	var fmnBck="";
	var nleBck="";
	var endPointBck="";
	var userBck="";
	var passwordBck="";
	var rePasswordBck="";
	var proUsuarioBck="";
	var proPassBck="";
	var comboBck="";

	function checkCanalHeader(combo){
		var headerSMS= document.getElementById("servidorServicio.headerSMS");
		var headerSMSLabel= document.getElementById("headerSMSLabel");
		var fromMail= document.getElementById("servicio.fromMail");
		var fromMailLabel= document.getElementById("fromMailLabel");
		var fromMailName= document.getElementById("servicio.fromMailName");
		var fromMailNameLabel= document.getElementById("fromMailNameLabel");	
		var nombreLoteEnvio= document.getElementById("servicio.nombreLoteEnvio");
		var nombreLoteEnvioLabel= document.getElementById("nombreLoteEnvioLabel");
		var proveedorUsuarioSMSLabel = document.getElementById("proveedorUsuarioSMSLabel");
		var proveedorUsuarioSMS = document.getElementById("servidorServicio.proveedorUsuarioSMS");
		var proveedorPassSMSLabel = document.getElementById("proveedorPassSMSLabel");
		var proveedorPassSMS = document.getElementById("servidorServicio.proveedorPasswordSMS");
		
		var nombreEndPoint= document.getElementById("servicio.endPoint");
		var nombreEndPointLabel= document.getElementById("endPointLabel");

		var plataforma = document.getElementById("plataformaId");
		var newPlataformaAndroid = document.getElementById("newPlataformaAndroid");
		var newPlataformaiOS = document.getElementById("newPlataformaiOS");
		var badge= document.getElementById("servicio.badge");
		var badgeLabel= document.getElementById("badgeLabel");
		var gcmProjectKey= document.getElementById("servicio.gcmProjectKey");
		var gcmProjectKeyLabel= document.getElementById("gcmProjectKeyLabel");
		var apnsRutaCertificado= document.getElementById("servicio.apnsRutaCertificado");
		var apnsRutaCertificadoLabel= document.getElementById("apnsRutaCertificadoLabel");
		var apnsPasswordCertificado= document.getElementById("servicio.apnsPasswordCertificado");
		var apnsPasswordCertificadoLabel= document.getElementById("apnsPasswordCertificadoLabel");

		var agrupacionCodOrg= document.getElementById("servicio.agrupacionCodOrg");
		var agrupacionCodOrgLabel= document.getElementById("agrupacionCodOrg");
		var agrupacionCodSia= document.getElementById("servicio.agrupacionCodSia");
		var agrupacionCodSiaLabel= document.getElementById("agrupacionCodSia");
		var agrupacionCodOrgPagador= document.getElementById("servicio.agrupacionCodOrgPagador");
		var agrupacionCodOrgPagadorLabel= document.getElementById("agrupacionCodOrgPagador");

		var auna= document.getElementById("auna");
		var aune= document.getElementById("aune");
		var auni= document.getElementById("auni");
		var aunaValue= document.getElementById("aunaValue1");
		var auneValue= document.getElementById("auneValue1");
		var auniValue= document.getElementById("auneValui1");
		//var colspan1 =document.getElementById("colspan1");

		var containerMultiorganismo = document.getElementById("containerMultiorganismo");
		


		if(combo.value=="1"){

			if (auna!=null)
				auna.innerHTML="";
			if (aune!=null)
				aune.innerHTML="";
			if (auni!=null)
				auni.innerHTML="";
			if (aunaValue!=null)
				aunaValue.style.visibility="hidden";
			if (auneValue!=null)
				auneValue.style.visibility="hidden";
			if (auniValue!=null)
				auniValue.style.visibility="hidden";
			
			if(containerMultiorganismo != null)
				containerMultiorganismo.style.display="none";
			
			comboBck = combo.value;
			if (headerSMS != null){
				hBck=headerSMS.value;
				headerSMS.value="";
				headerSMS.disabled="disabled";
				headerSMS.style.visibility="hidden";
				headerSMS.style.display="none";
				headerSMSLabel.value="";
				headerSMSLabel.disabled="disabled";
				headerSMSLabel.style.visibility="hidden";
				headerSMSLabel.style.display="none";
				
				proUsuarioBck = proveedorUsuarioSMS.value;
				proveedorUsuarioSMS.disabled = "disabled";
				proveedorUsuarioSMS.style.visibility = "hidden";
				proveedorUsuarioSMS.style.display ="none";
				proveedorUsuarioSMS.value="";
				proPassBck = proveedorPassSMS.value;
				proveedorPassSMS.disabled = "disabled";
				proveedorPassSMS.style.visibility = "hidden";
				proveedorPassSMS.style.display ="none";
				proveedorPassSMS.value = "";
				proveedorUsuarioSMSLabel.disabled = "disabled";
				proveedorUsuarioSMSLabel.style.visibility = "hidden";
				proveedorUsuarioSMSLabel.style.display ="none";
				proveedorUsuarioSMSLabel.value = "";
				proveedorPassSMSLabel.disabled = "disabled";
				proveedorPassSMSLabel.style.visibility = "hidden";
				proveedorPassSMSLabel.style.display ="none";
				proveedorPassSMSLabel.value = "";		
			}
			plataforma.style.visibility="hidden";
			plataforma.style.display="none";
			if(newPlataformaAndroid.checked==true){
				newPlataformaAndroid.click();
			}
			if(newPlataformaiOS.checked==true){
				newPlataformaiOS.click();
			}

			nombreEndPoint.value="";
			nombreEndPoint.disabled="disabled";
			nombreEndPoint.style.visibility="hidden";
			nombreEndPoint.style.display="none";

			nombreEndPointLabel.value="";
			nombreEndPointLabel.disabled="disabled";
			nombreEndPointLabel.style.visibility="hidden";
			nombreEndPointLabel.style.display="none";

			badge.value="";
			badge.disabled="disabled";
			badge.style.visibility="hidden";
			badge.style.display="none";

			badgeLabel.value="";
			badgeLabel.disabled="disabled";
			badgeLabel.style.visibility="hidden";
			badgeLabel.style.display="none";

			gcmProjectKey.value="";
			gcmProjectKey.disabled="disabled";
			gcmProjectKey.style.visibility="hidden";
			gcmProjectKey.style.display="none";

			gcmProjectKeyLabel.value="";
			gcmProjectKeyLabel.disabled="disabled";
			gcmProjectKeyLabel.style.visibility="hidden";
			gcmProjectKeyLabel.style.display="none";

			apnsRutaCertificado.value="";
			apnsRutaCertificado.disabled="disabled";
			apnsRutaCertificado.style.visibility="hidden";
			apnsRutaCertificado.style.display="none";

			apnsRutaCertificadoLabel.value="";
			apnsRutaCertificadoLabel.disabled="disabled";
			apnsRutaCertificadoLabel.style.visibility="hidden";
			apnsRutaCertificadoLabel.style.display="none";

			apnsPasswordCertificado.value="";
			apnsPasswordCertificado.disabled="disabled";
			apnsPasswordCertificado.style.visibility="hidden";
			apnsPasswordCertificado.style.display="none";
			

			apnsPasswordCertificadoLabel.value="";
			apnsPasswordCertificadoLabel.disabled="disabled";
			apnsPasswordCertificadoLabel.style.visibility="hidden";
			apnsPasswordCertificadoLabel.style.display="none";
			
			nleBck=nombreLoteEnvio.value;
			nombreLoteEnvio.value="";
			nombreLoteEnvio.disabled="disabled";
			nombreLoteEnvio.style.visibility="hidden";
			nombreLoteEnvio.style.display="none";
			nombreLoteEnvioLabel.value="";
			nombreLoteEnvioLabel.disabled="disabled";
			nombreLoteEnvioLabel.style.visibility="hidden";
			nombreLoteEnvioLabel.style.display="none";
			
			if(fmBck!=""){
				fromMail.value=fmBck;
			}
			fromMail.disabled="";
			fromMail.style.display="block";
			fromMail.style.visibility="visible";
			fromMailLabel.disabled="";
			fromMailLabel.style.display="block";
			fromMailLabel.style.visibility="visible";
			
			
			if(fmnBck!=""){
				fromMailName.value=fmnBck;
			}
			fromMailName.disabled="";
			fromMailName.style.visibility="visible";
			fromMailName.style.display="block";
			fromMailNameLabel.disabled="";
			fromMailNameLabel.style.visibility="visible";
			fromMailNameLabel.style.display="block";

			
			agrupacionCodOrgLabel.style.visibility="";
			agrupacionCodSiaLabel.style.visibility="";
			agrupacionCodOrgPagadorLabel.style.visibility="hidden";
			
			
		}
		if(combo.value=="2"){
			if (auna!=null)
				auna.innerHTML="Header";
			if (aune!=null)
				aune.innerHTML="Usuario";
			if (auni!=null)
				auni.innerHTML="Organismo";
			if (aunaValue!=null)
				aunaValue.style.visibility="visible";
			if (auneValue!=null)
				auneValue.style.visibility="visible";
			if (auniValue!=null)
				auniValue.style.visibility="visible";
			
			if(containerMultiorganismo != null)
				containerMultiorganismo.style.display="block";

			comboBck = combo.value;
			
			if (headerSMS != null){
				if(hBck!=""){
					headerSMS.value=hBck;
				}
				if (proUsuarioBck != ""){
					proveedorUsuarioSMS.value = proUsuarioBck;
				}
				if (proPassBck != ""){
					proveedorPassSMS.value = proPassBck;
				}
				headerSMS.disabled="";
				headerSMS.style.visibility="visible";
				headerSMS.style.display="block";
				
				
				headerSMSLabel.disabled="";
				headerSMSLabel.style.visibility="visible";
				headerSMSLabel.style.display="block";
				
				proveedorUsuarioSMS.disabled = "";
				proveedorUsuarioSMS.style.visibility = "visible";
				proveedorUsuarioSMS.style.display ="block";
				proveedorPassSMS.disabled = "";
				proveedorPassSMS.style.visibility = "visible";
				proveedorPassSMS.style.display ="block";
				proveedorUsuarioSMSLabel.disabled = "";
				proveedorUsuarioSMSLabel.style.visibility = "visible";
				proveedorUsuarioSMSLabel.style.display ="block";
				proveedorPassSMSLabel.disabled = "";
				proveedorPassSMSLabel.style.visibility = "visible";	
				proveedorPassSMSLabel.style.display = "block";	
			}
			fmBck=fromMail.value;
			fromMail.value="";
			fromMail.disabled="disabled";
			fromMail.style.visibility="hidden";
			fromMail.style.display="none";
			fromMailLabel.value="";
			fromMailLabel.disabled="disabled";
			fromMailLabel.style.visibility="hidden";
			fromMailLabel.style.display="none";

			fmnBck=fromMailName.value;
			fromMailName.value="";
			fromMailName.disabled="disabled";
			fromMailName.style.visibility="hidden";
			fromMailName.style.display="none";
			fromMailNameLabel.value="";
			fromMailNameLabel.disabled="disabled";
			fromMailNameLabel.style.visibility="hidden";
			fromMailNameLabel.style.display="none";

			nleBck=nombreLoteEnvio.value;
			nombreLoteEnvio.value="";
			nombreLoteEnvio.disabled="disabled";
			nombreLoteEnvio.style.visibility="hidden";
			nombreLoteEnvio.style.display="none";
			nombreLoteEnvioLabel.value="";
			nombreLoteEnvioLabel.disabled="disabled";
			nombreLoteEnvioLabel.style.visibility="hidden";
			nombreLoteEnvioLabel.style.display="none";

			plataforma.style.visibility="hidden";
			plataforma.style.display="none";
			if(newPlataformaAndroid.checked==true){
				newPlataformaAndroid.click();
			}
			if(newPlataformaiOS.checked==true){
				newPlataformaiOS.click();
			}

			nombreEndPoint.value="";
			nombreEndPoint.disabled="disabled";
			nombreEndPoint.style.visibility="hidden";
			nombreEndPoint.style.display="none";

			nombreEndPointLabel.value="";
			nombreEndPointLabel.disabled="disabled";
			nombreEndPointLabel.style.visibility="hidden";
			nombreEndPointLabel.style.display="none";

			badge.value="";
			badge.disabled="disabled";
			badge.style.visibility="hidden";
			badge.style.display="none";

			badgeLabel.value="";
			badgeLabel.disabled="disabled";
			badgeLabel.style.visibility="hidden";
			badgeLabel.style.display="none";

			gcmProjectKey.value="";
			gcmProjectKey.disabled="disabled";
			gcmProjectKey.style.visibility="hidden";
			gcmProjectKey.style.display="none";

			gcmProjectKeyLabel.value="";
			gcmProjectKeyLabel.disabled="disabled";
			gcmProjectKeyLabel.style.visibility="hidden";
			gcmProjectKeyLabel.style.display="none";

			apnsRutaCertificado.value="";
			apnsRutaCertificado.disabled="disabled";
			apnsRutaCertificado.style.visibility="hidden";
			apnsRutaCertificado.style.display="none";

			apnsRutaCertificadoLabel.value="";
			apnsRutaCertificadoLabel.disabled="disabled";
			apnsRutaCertificadoLabel.style.visibility="hidden";
			apnsRutaCertificadoLabel.style.display="none";

			apnsPasswordCertificado.value="";
			apnsPasswordCertificado.disabled="disabled";
			apnsPasswordCertificado.style.visibility="hidden";
			apnsPasswordCertificado.style.display="none";

			apnsPasswordCertificadoLabel.value="";
			apnsPasswordCertificadoLabel.disabled="disabled";
			apnsPasswordCertificadoLabel.style.visibility="hidden";
			apnsPasswordCertificadoLabel.style.display="none";

			agrupacionCodOrgLabel.style.visibility="";
			agrupacionCodSiaLabel.style.visibility="";
			agrupacionCodOrgPagadorLabel.style.visibility="";
			
		}
		if(combo.value=="3"){
			if (auna!=null)
				auna.innerHTML="Header";
			if (aune!=null)
				aune.innerHTML="";
			if (auni!=null)
				auni.innerHTML="";
			if (aunaValue!=null)
				aunaValue.visibility="hidden";
			if (auneValue!=null)
				auneValue.visibility="hidden";
			if (auniValue!=null)
				auniValue.visibility="hidden";
			
			if(containerMultiorganismo != null)
				containerMultiorganismo.style.display="none";
			
			comboBck = combo.value;
			
			if (headerSMS != null){
				if(userBck!=""){
					headerSMS.value=hBck;
				}
				headerSMS.disabled="";
				headerSMS.style.visibility="visible";
				headerSMS.style.display ="block";
				headerSMSLabel.disabled="";
				headerSMSLabel.style.visibility="visible";
				headerSMSLabel.style.display ="block";
	
				proUsuarioBck = proveedorUsuarioSMS.value;
				proPassBck = proveedorPassSMS.value;
				
				proveedorUsuarioSMS.disabled = "disabled";
				proveedorUsuarioSMS.style.visibility = "hidden";
				proveedorUsuarioSMS.style.display ="none";
				proveedorUsuarioSMS.value="";
				proveedorPassSMS.disabled = "disabled";
				proveedorPassSMS.style.visibility = "hidden";
				proveedorPassSMS.style.display ="none";
				proveedorPassSMS.value = "";
				proveedorUsuarioSMSLabel.disabled = "disabled";
				proveedorUsuarioSMSLabel.style.visibility = "hidden";
				proveedorUsuarioSMSLabel.style.display ="none";
				proveedorUsuarioSMSLabel.value = "";
				proveedorPassSMSLabel.disabled = "disabled";
				proveedorPassSMSLabel.style.visibility = "hidden";
				proveedorPassSMSLabel.style.display ="none";
				proveedorPassSMSLabel.value = "";	
			}	
			
			if(nleBck!=""){
				nombreLoteEnvio.value=nleBck;
			}
			nombreLoteEnvio.disabled="";
			nombreLoteEnvio.style.visibility="visible";
			nombreLoteEnvio.style.display="block";
			
			nombreLoteEnvioLabel.disabled="";
			nombreLoteEnvioLabel.style.visibility="visible";
			nombreLoteEnvioLabel.style.display="block";

			if(endPointBck!=""){
				nombreEndPoint.value=endPointBck;
			}
			nombreEndPoint.disabled="";
			nombreEndPoint.style.visibility="visible";
			nombreEndPoint.style.display="block";
			
			nombreEndPointLabel.disabled="";
			nombreEndPointLabel.style.visibility="visible";
			nombreEndPointLabel.style.display="block";
			
			fmBck=fromMail.value;
			fromMail.value="";
			fromMail.disabled="disabled";
			fromMail.style.visibility="hidden";
			fromMail.style.display="none";
			fromMailLabel.value="";
			fromMailLabel.disabled="disabled";
			fromMailLabel.style.visibility="hidden";
			fromMailLabel.style.display="none";

			fmnBck=fromMailName.value;
			fromMailName.value="";
			fromMailName.disabled="disabled";
			fromMailName.style.visibility="hidden";
			fromMailName.style.display="none";
			fromMailNameLabel.value="";
			fromMailNameLabel.disabled="disabled";
			fromMailNameLabel.style.visibility="hidden";
			fromMailNameLabel.style.display="none";

			plataforma.style.visibility="hidden";
			plataforma.style.display="none";
			if(newPlataformaAndroid.checked==true){
				newPlataformaAndroid.click();
			}
			if(newPlataformaiOS.checked==true){
				newPlataformaiOS.click();
			}
			

			badge.value="";
			badge.disabled="disabled";
			badge.style.visibility="hidden";
			badge.style.display="none";

			badgeLabel.value="";
			badgeLabel.disabled="disabled";
			badgeLabel.style.visibility="hidden";
			badgeLabel.style.display="none";

			gcmProjectKey.value="";
			gcmProjectKey.disabled="disabled";
			gcmProjectKey.style.visibility="hidden";
			gcmProjectKey.style.display="none";

			gcmProjectKeyLabel.value="";
			gcmProjectKeyLabel.disabled="disabled";
			gcmProjectKeyLabel.style.visibility="hidden";
			gcmProjectKeyLabel.style.display="none";

			apnsRutaCertificado.value="";
			apnsRutaCertificado.disabled="disabled";
			apnsRutaCertificado.style.visibility="hidden";
			apnsRutaCertificado.style.display="none";

			apnsRutaCertificadoLabel.value="";
			apnsRutaCertificadoLabel.disabled="disabled";
			apnsRutaCertificadoLabel.style.visibility="hidden";
			apnsRutaCertificadoLabel.style.display="none";

			apnsPasswordCertificado.value="";
			apnsPasswordCertificado.disabled="disabled";
			apnsPasswordCertificado.style.visibility="hidden";
			apnsPasswordCertificado.style.display="none";

			apnsPasswordCertificadoLabel.value="";
			apnsPasswordCertificadoLabel.disabled="disabled";
			apnsPasswordCertificadoLabel.style.visibility="hidden";
			apnsPasswordCertificadoLabel.style.display="none";

			agrupacionCodOrgLabel.style.visibility="";
			agrupacionCodSiaLabel.style.visibility="";
			agrupacionCodOrgPagadorLabel.style.visibility="hidden";
		}
		if(combo.value=="4"){

			if (auna!=null)
				auna.innerHTML="";
			if (aune!=null)
				aune.innerHTML="";
			if (auni!=null)
				auni.innerHTML="";
			if (aunaValue!=null)
				aunaValue.style.visibility="hidden";
			if (auneValue!=null)
				auneValue.style.visibility="hidden";
			if (auniValue!=null)
				auniValue.style.visibility="hidden";
			
			if(containerMultiorganismo != null)
				containerMultiorganismo.style.display="none";
			
			comboBck = combo.value;
			plataforma.style.visibility="visible";
			plataforma.style.display="block";

			badge.disabled="";
			badge.style.visibility="visible";
			badge.style.display="block";

			badgeLabel.disabled="";
			badgeLabel.style.visibility="visible";
			badgeLabel.style.display="block";

			gcmProjectKey.disabled="";
			gcmProjectKey.style.visibility="visible";
			gcmProjectKey.style.display="block";

			gcmProjectKeyLabel.disabled="";
			gcmProjectKeyLabel.style.visibility="visible";
			gcmProjectKeyLabel.style.display="block";

			apnsRutaCertificado.disabled="";
			apnsRutaCertificado.style.visibility="visible";
			apnsRutaCertificado.style.display="block";

			apnsRutaCertificadoLabel.disabled="";
			apnsRutaCertificadoLabel.style.visibility="visible";
			apnsRutaCertificadoLabel.style.display="block";

			apnsPasswordCertificado.disabled="";
			apnsPasswordCertificado.style.visibility="visible";
			apnsPasswordCertificado.style.display="block";

			apnsPasswordCertificadoLabel.disabled="";
			apnsPasswordCertificadoLabel.style.visibility="visible";
			apnsPasswordCertificadoLabel.style.display="block";

			
			if (headerSMS != null){
				hBck=headerSMS.value;
				headerSMS.value="";
				headerSMS.disabled="disabled";
				headerSMS.style.visibility="hidden";
				headerSMS.style.display="none";
				headerSMSLabel.value="";
				headerSMSLabel.disabled="disabled";
				headerSMSLabel.style.visibility="hidden";
				headerSMSLabel.style.display="none";
			
				proUsuarioBck = proveedorUsuarioSMS.value;
				proPassBck = proveedorPassSMS.value;
			
				proveedorUsuarioSMS.disabled = "disabled";
				proveedorUsuarioSMS.style.visibility = "hidden";
				proveedorUsuarioSMS.style.display ="none";
				proveedorUsuarioSMS.value="";
				proveedorPassSMS.disabled = "disabled";
				proveedorPassSMS.style.visibility = "hidden";
				proveedorPassSMS.style.display ="none";
				proveedorPassSMS.value = "";
				proveedorUsuarioSMSLabel.disabled = "disabled";
				proveedorUsuarioSMSLabel.style.visibility = "hidden";
				proveedorUsuarioSMSLabel.style.display ="none";
				proveedorUsuarioSMSLabel.value = "";
				proveedorPassSMSLabel.disabled = "disabled";
				proveedorPassSMSLabel.style.visibility = "hidden";
				proveedorPassSMSLabel.style.display ="none";
				proveedorPassSMSLabel.value = "";		
			}
			nombreEndPoint.value="";
			nombreEndPoint.disabled="disabled";
			nombreEndPoint.style.visibility="hidden";
			nombreEndPoint.style.display="none";

			nombreEndPointLabel.value="";
			nombreEndPointLabel.disabled="disabled";
			nombreEndPointLabel.style.visibility="hidden";
			nombreEndPointLabel.style.display="none";

			nombreLoteEnvio.value="";
			nombreLoteEnvio.disabled="disabled";
			nombreLoteEnvio.style.visibility="hidden";
			nombreLoteEnvio.style.display="none";

			nombreLoteEnvioLabel.value="";
			nombreLoteEnvioLabel.disabled="disabled";
			nombreLoteEnvioLabel.style.visibility="hidden";
			nombreLoteEnvioLabel.style.display="none";
			
			fmBck=fromMail.value;
			fromMail.value="";
			fromMail.disabled="disabled";
			fromMail.style.visibility="hidden";
			fromMail.style.display="none";
			fromMailLabel.value="";
			fromMailLabel.disabled="disabled";
			fromMailLabel.style.visibility="hidden";
			fromMailLabel.style.display="none";

			fmnBck=fromMailName.value;
			fromMailName.value="";
			fromMailName.disabled="disabled";
			fromMailName.style.visibility="hidden";
			fromMailName.style.display="none";
			fromMailNameLabel.value="";
			fromMailNameLabel.disabled="disabled";
			fromMailNameLabel.style.visibility="hidden";
			fromMailNameLabel.style.display="none";

			agrupacionCodOrgLabel.style.visibility="";
			agrupacionCodSiaLabel.style.visibility="";
			agrupacionCodOrgPagadorLabel.style.visibility="hidden";
			

		}
	}
	function chkViewValue(boton){
		var field = document.getElementById('estadisticaBean.vistaId');
		if(boton.id=="btndias"){
			field.value="3";
			boton.className="buttonSelected";
			document.getElementById('btnmeses').className="buttonNoSelected";
			document.getElementById('btnannos').className="buttonNoSelected";
		}else if(boton.id=="btnmeses"){
			field.value="2";
			boton.className="buttonSelected";
			document.getElementById('btndias').className="buttonNoSelected";
			document.getElementById('btnannos').className="buttonNoSelected";

		}else if(boton.id="btnannos"){
			field.value="1";
			boton.className="buttonSelected";
			document.getElementById('btnmeses').className="buttonNoSelected";
			document.getElementById('btndias').className="buttonNoSelected";

		}
	}

	function chkViewEnviosValue(boton){
		var field = document.getElementById('gestionEnvioBean.vistaEnviosId');
		if(boton.id=="btnmensajes"){
			field.value="1";
			boton.className="buttonSelected";
			document.getElementById('btnlotes').className="buttonNoSelected";
			document.getElementById('btndestinatarios').className="buttonNoSelected";
		}else if(boton.id=="btnlotes"){
			field.value="2";
			boton.className="buttonSelected";
			document.getElementById('btnmensajes').className="buttonNoSelected";
			document.getElementById('btndestinatarios').className="buttonNoSelected";
		}else if(boton.id=="btndestinatarios"){
			field.value="3";
			boton.className="buttonSelected";
			document.getElementById('btnmensajes').className="buttonNoSelected";
			document.getElementById('btnlotes').className="buttonNoSelected";
		}
	}

	function chkViewEnviosHistoricosValue(boton){
		var field = document.getElementById('gestionEnvioHistoricoBean.vistaEnviosId');
		if(boton.id=="btnmensajesHistorico"){
			field.value="1";
			boton.className="buttonSelected";
			document.getElementById('btnlotesHistorico').className="buttonNoSelected";
			document.getElementById('btndestinatariosHistorico').className="buttonNoSelected";
		}else if(boton.id=="btnlotesHistorico"){
			field.value="2";
			boton.className="buttonSelected";
			document.getElementById('btnmensajesHistorico').className="buttonNoSelected";
			document.getElementById('btndestinatariosHistorico').className="buttonNoSelected";
		}else if(boton.id=="btndestinatariosHistorico"){
			field.value="3";
			boton.className="buttonSelected";
			document.getElementById('btnmensajesHistorico').className="buttonNoSelected";
			document.getElementById('btnlotesHistorico').className="buttonNoSelected";
		}
		
	}


	function showApplicationsDiv(combo){
		if(combo.value=="1"&&document.getElementById("applicationsDiv")!=null){
			document.getElementById("applicationsDiv").style.visibility="hidden";
			document.getElementById("applicationsDiv").style.display="none";
			document.getElementById("addItem").disabled="disabled";
		}else if(document.getElementById("applicationsDiv")!=null){
			document.getElementById("applicationsDiv").style.visibility="visible";
			document.getElementById("applicationsDiv").style.display="";
			document.getElementById("addItem").disabled="";
		}
	}
	function validaPlanJS(formName,varNameStr){
		if(!document.getElementById(varNameStr+".lunes").checked&&!document.getElementById(varNameStr+".martes").checked&&!document.getElementById(varNameStr+".miercoles").checked
				&&!document.getElementById(varNameStr+".jueves").checked&&!document.getElementById(varNameStr+".viernes").checked&&!document.getElementById(varNameStr+".sabado").checked
				&&!document.getElementById(varNameStr+".domingo").checked){
			alert('Debe seleccionar al menos un día para asociar una planificación al servidor');
			return false;
		}
		
		var horaDesde = document.getElementById(varNameStr+".horaDesde").value
	    var horaHasta = document.getElementById(varNameStr+".horaHasta").value
	    //if(horaDesde==""&&horaHasta==""){
		//	alert('Debe introducir valores para los campos Hora Inicio y Hora Fin');
		//	return false;
		//}
		//if(horaDesde!=""&&horaHasta==""){
		//	alert('Debe introducir un valor para el campo Hora Fin');
		//	return false;
		//}
		//if(horaDesde==""&&horaHasta!=""){
		//	alert('Debe introducir un valor para el campo Hora Inicio');
		//	return false;
		//}	
		var regExpHoras = new RegExp("([01]?[0-9]|2[0-3]):[0-5][0-9]");
		if(!regExpHoras.test(horaDesde)){
			alert('El formato de Hora Inicio es incorrecto (HH:MM)');
			return false;
		}
		if(!regExpHoras.test(horaHasta)){
			alert('El formato de Hora Fin es incorrecto (HH:MM)');
			return false;
		}		
		
		return true;
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
	function checkUrlPlataforma(combo){
		var urlFeedbackLabel= document.getElementById("nombreUrlFeedbackLabel");
		var urlFeedback= document.getElementById("servidorPush.urlFeedback");
		if(combo.value=="1"){
			urlFeedbackLabel.disabled="disabled";
			urlFeedbackLabel.style.visibility="hidden";

			urlFeedback.value="";
			urlFeedback.disabled="disabled";
			urlFeedback.style.visibility="hidden";
		}
		if(combo.value=="2"){
			urlFeedbackLabel.disabled="";
			urlFeedbackLabel.style.visibility="visible";

			urlFeedback.disabled="";
			urlFeedback.style.visibility="visible";
		}
	}
	

</script>