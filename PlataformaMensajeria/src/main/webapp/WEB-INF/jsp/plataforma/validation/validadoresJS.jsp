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

	function confirmDeleteImagenPassbook(imagen, enlace, idServicioOrganismo, idServicio, idOrganismo){
		var r = confirm("¿Desea eliminar el elemento?");
		if(r){
			if(imagen == "imagenLogo"){
				return deleteImagenLogo(enlace, idServicioOrganismo, idServicio, idOrganismo);
				}
			else if(imagen == "imagenBackground"){
				return deleteImagenBackground(enlace, idServicioOrganismo, idServicio, idOrganismo);
			}
			else{
				return deleteImagenIcon(enlace, idServicioOrganismo, idServicio, idOrganismo);
				}
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
	var pBck="";
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
	var nmaxreBck = "";

	function checkPassbook(){
		var passbook = document.getElementById("passbook");
		var passbookCheck =  document.getElementById("envioMensajesAplicacionBean.passbook");
		
		var passbookLabel =  document.getElementById("passbookLabel");
		
		var url = document.getElementById("envioMensajesAplicacionBean.url");
		var urlLabel = document.getElementById("urlLabel");
		var logoPassbook = document.getElementById("envioMensajesAplicacionBean.logoPassbook");
		var logoPassbookLabel = document.getElementById("logoPassbookLabel");
		var descripcionPassbook = document.getElementById("envioMensajesAplicacionBean.descripcionPassbook");
		var descripcionPassbookLabel = document.getElementById("descripcionPassbookLabel");
		
		var camposPrincLabel =  document.getElementById("camposPrincLabel");
			
		var keyPrinc = document.getElementById("envioMensajesAplicacionBean.keyPrinc");
		var keyPrincLabel = document.getElementById("keyPrincLabel");
		var labelPrinc = document.getElementById("envioMensajesAplicacionBean.labelPrinc");
		var labelPrincLabel = document.getElementById("labelPrincLabel");
		var valuePrinc = document.getElementById("envioMensajesAplicacionBean.valuePrinc");
		var valuePrincLabel = document.getElementById("valuePrincLabel");

		var camposSecLabel =  document.getElementById("campoSecLabel");
			
		var keySec = document.getElementById("envioMensajesAplicacionBean.keySec");
		var keySecLabel = document.getElementById("keySecLabel");
		var labelSec = document.getElementById("envioMensajesAplicacionBean.labelSec");
		var labelSecLabel = document.getElementById("labelSecLabel");
		var valueSec = document.getElementById("envioMensajesAplicacionBean.valueSec");
		var valueSecLabel = document.getElementById("valueSecLabel");

		var camposAuxLabel =  document.getElementById("campoAuxLabel");

		var keyAux = document.getElementById("envioMensajesAplicacionBean.keyAux");
		var keyAuxLabel = document.getElementById("keyAuxLabel");
		var labelAux = document.getElementById("envioMensajesAplicacionBean.labelAux");
		var labelAuxLabel = document.getElementById("labelAuxLabel");
		var valueAux = document.getElementById("envioMensajesAplicacionBean.valueAux");
		var valueAuxLabel = document.getElementById("valueAuxLabel");

		var camposTrasLabel =  document.getElementById("campoTrasLabel");
		
		var keyTras = document.getElementById("envioMensajesAplicacionBean.keyTras");
		var keyTrasLabel = document.getElementById("keyTrasLabel");
		var labelTras = document.getElementById("envioMensajesAplicacionBean.labelTras");
		var labelTrasLabel = document.getElementById("labelTrasLabel");
		var valueTras = document.getElementById("envioMensajesAplicacionBean.valueTras");
		var valueTrasLabel = document.getElementById("valueTrasLabel");


		 passbook.disabled="";
		 passbook.style.display="block";
		 passbook.style.visibility="visible";

	if(passbookCheck.checked == true){
		 passbookLabel.disabled="";
		 passbookLabel.style.display="block";
		 passbookLabel.style.visibility="visible";
// 		 url.value="";
		 url.disabled="";
		 url.style.display="block";
		 url.style.visibility="visible";
		 urlLabel.disabled="";
		 urlLabel.style.display="block";
		 urlLabel.style.visibility="visible";
// 		 logoPassbook.value="";
		 logoPassbook.disabled="";
		 logoPassbook.style.display="block";
		 logoPassbook.style.visibility="visible";
		 logoPassbookLabel.disabled="";
		 logoPassbookLabel.style.display="block";
		 logoPassbookLabel.style.visibility="visible";
// 		 descripcionPassbook.value="";
		 descripcionPassbook.disabled="";
		 descripcionPassbook.style.display="block";
		 descripcionPassbook.style.visibility="visible";
		 descripcionPassbookLabel.disabled="";
		 descripcionPassbookLabel.style.display="block";
		 descripcionPassbookLabel.style.visibility="visible";

		 camposPrincLabel.disabled="";
		 camposPrincLabel.style.display="block";
		 camposPrincLabel.style.visibility="visible";
// 		 keyPrinc.value="";
		 keyPrinc.disabled="";
		 keyPrinc.style.display="block";
		 keyPrinc.style.visibility="visible";
		 keyPrincLabel.disabled="";
		 keyPrincLabel.style.display="block";
		 keyPrincLabel.style.visibility="visible";
// 		 labelPrinc.value="";
		 labelPrinc.disabled="";
		 labelPrinc.style.display="block";
		 labelPrinc.style.visibility="visible";
		 labelPrincLabel.disabled="";
		 labelPrincLabel.style.display="block";
		 labelPrincLabel.style.visibility="visible";		 
// 		 valuePrinc.value="";
		 valuePrinc.disabled="";
		 valuePrinc.style.display="block";
		 valuePrinc.style.visibility="visible";
		 valuePrincLabel.disabled="";
		 valuePrincLabel.style.display="block";
		 valuePrincLabel.style.visibility="visible";

		 camposSecLabel.disabled="";
		 camposSecLabel.style.display="block";
		 camposSecLabel.style.visibility="visible";
// 		 keySec.value="";
		 keySec.disabled="";
		 keySec.style.display="block";
		 keySec.style.visibility="visible";
		 keySecLabel.disabled="";
		 keySecLabel.style.display="block";
		 keySecLabel.style.visibility="visible";
// 		 labelSec.value="";
		 labelSec.disabled="";
		 labelSec.style.display="block";
		 labelSec.style.visibility="visible";
		 labelSecLabel.disabled="";
		 labelSecLabel.style.display="block";
		 labelSecLabel.style.visibility="visible";		 
// 		 valueSec.value="";
		 valueSec.disabled="";
		 valueSec.style.display="block";
		 valueSec.style.visibility="visible";
		 valueSecLabel.disabled="";
		 valueSecLabel.style.display="block";
		 valueSecLabel.style.visibility="visible";

		 camposAuxLabel.disabled="";
		 camposAuxLabel.style.display="block";
		 camposAuxLabel.style.visibility="visible";
// 		 keyAux.value="";
		 keyAux.disabled="";
		 keyAux.style.display="block";
		 keyAux.style.visibility="visible";
		 keyAuxLabel.disabled="";
		 keyAuxLabel.style.display="block";
		 keyAuxLabel.style.visibility="visible";
// 		 labelAux.value="";
		 labelAux.disabled="";
		 labelAux.style.display="block";
		 labelAux.style.visibility="visible";
		 labelAuxLabel.disabled="";
		 labelAuxLabel.style.display="block";
		 labelAuxLabel.style.visibility="visible";		 
// 		 valueAux.value="";
		 valueAux.disabled="";
		 valueAux.style.display="block";
		 valueAux.style.visibility="visible";
		 valueAuxLabel.disabled="";
		 valueAuxLabel.style.display="block";
		 valueAuxLabel.style.visibility="visible";

		 camposTrasLabel.disabled="";
		 camposTrasLabel.style.display="block";
		 camposTrasLabel.style.visibility="visible";
// 		 keyTras.value="";
		 keyTras.disabled="";
		 keyTras.style.display="block";
		 keyTras.style.visibility="visible";
		 keyTrasLabel.disabled="";
		 keyTrasLabel.style.display="block";
		 keyTrasLabel.style.visibility="visible";
// 		 labelTras.value="";
		 labelTras.disabled="";
		 labelTras.style.display="block";
		 labelTras.style.visibility="visible";
		 labelTrasLabel.disabled="";
		 labelTrasLabel.style.display="block";
		 labelTrasLabel.style.visibility="visible";		 
// 		 valueTras.value="";
		 valueTras.disabled="";
		 valueTras.style.display="block";
		 valueTras.style.visibility="visible";
		 valueTrasLabel.disabled="";
		 valueTrasLabel.style.display="block";
		 valueTrasLabel.style.visibility="visible";
	}else{
		passbookLabel.disabled="disabled";
		 passbookLabel.style.display="none";
		 passbookLabel.style.visibility="hidden";
		 url.value="";
		 url.disabled="disabled";
		 url.style.display="none";
		 url.style.visibility="hidden";
		 urlLabel.disabled="disabled";
		 urlLabel.style.display="none";
		 urlLabel.style.visibility="hidden";
		 logoPassbook.value="";
		 logoPassbook.disabled="disabled";
		 logoPassbook.style.display="none";
		 logoPassbook.style.visibility="hidden";
		 logoPassbookLabel.disabled="disabled";
		 logoPassbookLabel.style.display="none";
		 logoPassbookLabel.style.visibility="hidden";
		 descripcionPassbook.value="";
		 descripcionPassbook.disabled="disabled";
		 descripcionPassbook.style.display="none";
		 descripcionPassbook.style.visibility="hidden";
		 descripcionPassbookLabel.disabled="disabled";
		 descripcionPassbookLabel.style.display="none";
		 descripcionPassbookLabel.style.visibility="hidden";

		 camposPrincLabel.disabled="disabled";
		 camposPrincLabel.style.display="none";
		 camposPrincLabel.style.visibility="hidden";
		 keyPrinc.value="";
		 keyPrinc.disabled="disabled";
		 keyPrinc.style.display="none";
		 keyPrinc.style.visibility="hidden";
		 keyPrincLabel.disabled="disabled";
		 keyPrincLabel.style.display="none";
		 keyPrincLabel.style.visibility="hidden";
		 labelPrinc.value="";
		 labelPrinc.disabled="disabled";
		 labelPrinc.style.display="none";
		 labelPrinc.style.visibility="hidden";
		 labelPrincLabel.disabled="disabled";
		 labelPrincLabel.style.display="none";
		 labelPrincLabel.style.visibility="hidden";		 
		 valuePrinc.value="";
		 valuePrinc.disabled="disabled";
		 valuePrinc.style.display="block";
		 valuePrinc.style.visibility="hidden";
		 valuePrincLabel.disabled="disabled";
		 valuePrincLabel.style.display="none";
		 valuePrincLabel.style.visibility="hidden";

		 camposSecLabel.disabled="disabled";
		 camposSecLabel.style.display="none";
		 camposSecLabel.style.visibility="hidden";
		 keySec.value="";
		 keySec.disabled="disabled";
		 keySec.style.display="none";
		 keySec.style.visibility="hidden";
		 keySecLabel.disabled="disabled";
		 keySecLabel.style.display="none";
		 keySecLabel.style.visibility="hidden";
		 labelSec.value="";
		 labelSec.disabled="disabled";
		 labelSec.style.display="none";
		 labelSec.style.visibility="hidden";
		 labelSecLabel.disabled="disabled";
		 labelSecLabel.style.display="none";
		 labelSecLabel.style.visibility="hidden";		 
		 valueSec.value="";
		 valueSec.disabled="disabled";
		 valueSec.style.display="none";
		 valueSec.style.visibility="hidden";
		 valueSecLabel.disabled="disabled";
		 valueSecLabel.style.display="none";
		 valueSecLabel.style.visibility="hidden";

		 camposAuxLabel.disabled="disabled";
		 camposAuxLabel.style.display="none";
		 camposAuxLabel.style.visibility="hidden";
		 keyAux.value="";
		 keyAux.disabled="disabled";
		 keyAux.style.display="none";
		 keyAux.style.visibility="hidden";
		 keyAuxLabel.disabled="disabled";
		 keyAuxLabel.style.display="none";
		 keyAuxLabel.style.visibility="hidden";
		 labelAux.value="";
		 labelAux.disabled="disabled";
		 labelAux.style.display="none";
		 labelAux.style.visibility="hidden";
		 labelAuxLabel.disabled="disabled";
		 labelAuxLabel.style.display="none";
		 labelAuxLabel.style.visibility="hidden";		 
		 valueAux.value="";
		 valueAux.disabled="disabled";
		 valueAux.style.display="none";
		 valueAux.style.visibility="hidden";
		 valueAuxLabel.disabled="disabled";
		 valueAuxLabel.style.display="none";
		 valueAuxLabel.style.visibility="hidden";

		 camposTrasLabel.disabled="disabled";
		 camposTrasLabel.style.display="none";
		 camposTrasLabel.style.visibility="hidden";
		 keyTras.value="";
		 keyTras.disabled="disabled";
		 keyTras.style.display="none";
		 keyTras.style.visibility="hidden";
		 keyTrasLabel.disabled="disabled";
		 keyTrasLabel.style.display="none";
		 keyTrasLabel.style.visibility="hidden";
		 labelTras.value="";
		 labelTras.disabled="disabled";
		 labelTras.style.display="none";
		 labelTras.style.visibility="hidden";
		 labelTrasLabel.disabled="disabled";
		 labelTrasLabel.style.display="none";
		 labelTrasLabel.style.visibility="hidden";		 
		 valueTras.value="";
		 valueTras.disabled="disabled";
		 valueTras.style.display="none";
		 valueTras.style.visibility="hidden";
		 valueTrasLabel.disabled="disabled";
		 valueTrasLabel.style.display="none";
		 valueTrasLabel.style.visibility="hidden";
		}
		
	}

	function checkCanalEnvio(combo){


		var mensajeD= document.getElementById("divMensaje");
		var generalesD= document.getElementById("divGeneral");		
		var destinatariosD= document.getElementById("divDestinatarios");
		var button= document.getElementById("button");
// 		nat -- Envio Mensajes Aplicación

		var externoLabelSMS= document.getElementById("idExternoLabelSMS");
		var externoLabel= document.getElementById("idExternoLabel");
		var organismo= document.getElementById("search");
		var organismoLabel= document.getElementById("organismoLabel");		
// 		var organismoPagador= document.getElementById("envioMensajesAplicacionBean.organismoPagador");
// 		var organismoPagadorLabel= document.getElementById("organismoPagadorLabel");			
		var asunto= document.getElementById("envioMensajesAplicacionBean.asunto");
		var asuntoLabel= document.getElementById("asuntoLabel");		
		var titulo= document.getElementById("envioMensajesAplicacionBean.titulo");
		var tituloLabel= document.getElementById("tituloLabel");		
		var mensaje= document.getElementById("envioMensajesAplicacionBean.mensaje");
		var mensajeLabel= document.getElementById("mensajeLabel");
		var cuerpo= document.getElementById("envioMensajesAplicacionBean.cuerpo");
		var cuerpoLabel= document.getElementById("cuerpoLabel");
// 		var origen = document.getElementById("envioMensajesAplicacionBean.origen");
// 		var origenLabel = document.getElementById("origenLabel");		
// 		var modo = document.getElementById("envioMensajesAplicacionBean.modo");
// 		var modoLabel = document.getElementById("modoLabel");
		var adjunto= document.getElementById("envioMensajesAplicacionBean.adjunto");
		var adjuntoLabel= document.getElementById("adjuntoLabel");				
// 		var icono= document.getElementById("envioMensajesAplicacionBean.icono");
// 		var iconoLabel= document.getElementById("iconoLabel");					
// 		var sonido= document.getElementById("envioMensajesAplicacionBean.sonido");
// 		var sonidoLabel= document.getElementById("sonidoLabel");				
		var idUsuario= document.getElementById("envioMensajesAplicacionBean.idUsuario");
		var idUsuarioLabel= document.getElementById("idUsuarioLabel");				
		var movil = document.getElementById("envioMensajesAplicacionBean.movil");
		var movilLabel = document.getElementById("movilLabel");				
		var emailTo = document.getElementById("envioMensajesAplicacionBean.to");
		var emailToLabel = document.getElementById("toLabel");
		var emailCc= document.getElementById("envioMensajesAplicacionBean.cc");		
		var emailCcLabel= document.getElementById("ccLabel");						
		var emailCco= document.getElementById("envioMensajesAplicacionBean.cco");
		var emailCcoLabel= document.getElementById("ccoLabel");	

		var passbook = document.getElementById("passbook");
		
		var passbookLabel =  document.getElementById("passbookLabel");
		
		var url = document.getElementById("envioMensajesAplicacionBean.url");
		var urlLabel = document.getElementById("urlLabel");
		var logoPassbook = document.getElementById("envioMensajesAplicacionBean.logoPassbook");
		var logoPassbookLabel = document.getElementById("logoPassbookLabel");
		var descripcionPassbook = document.getElementById("envioMensajesAplicacionBean.descripcionPassbook");
		var descripcionPassbookLabel = document.getElementById("descripcionPassbookLabel");
		
		var camposPrincLabel =  document.getElementById("camposPrincLabel");
			
		var keyPrinc = document.getElementById("envioMensajesAplicacionBean.keyPrinc");
		var keyPrincLabel = document.getElementById("keyPrincLabel");
		var labelPrinc = document.getElementById("envioMensajesAplicacionBean.labelPrinc");
		var labelPrincLabel = document.getElementById("labelPrincLabel");
		var valuePrinc = document.getElementById("envioMensajesAplicacionBean.valuePrinc");
		var valuePrincLabel = document.getElementById("valuePrincLabel");

		var camposSecLabel =  document.getElementById("campoSecLabel");
			
		var keySec = document.getElementById("envioMensajesAplicacionBean.keySec");
		var keySecLabel = document.getElementById("keySecLabel");
		var labelSec = document.getElementById("envioMensajesAplicacionBean.labelSec");
		var labelSecLabel = document.getElementById("labelSecLabel");
		var valueSec = document.getElementById("envioMensajesAplicacionBean.valueSec");
		var valueSecLabel = document.getElementById("valueSecLabel");

		var camposAuxLabel =  document.getElementById("campoAuxLabel");

		var keyAux = document.getElementById("envioMensajesAplicacionBean.keyAux");
		var keyAuxLabel = document.getElementById("keyAuxLabel");
		var labelAux = document.getElementById("envioMensajesAplicacionBean.labelAux");
		var labelAuxLabel = document.getElementById("labelAuxLabel");
		var valueAux = document.getElementById("envioMensajesAplicacionBean.valueAux");
		var valueAuxLabel = document.getElementById("valueAuxLabel");

		var camposTrasLabel =  document.getElementById("campoTrasLabel");
		
		var keyTras = document.getElementById("envioMensajesAplicacionBean.keyTras");
		var keyTrasLabel = document.getElementById("keyTrasLabel");
		var labelTras = document.getElementById("envioMensajesAplicacionBean.labelTras");
		var labelTrasLabel = document.getElementById("labelTrasLabel");
		var valueTras = document.getElementById("envioMensajesAplicacionBean.valueTras");
		var valueTrasLabel = document.getElementById("valueTrasLabel");
		
// 		nat

		if(combo.value=="1"){//email

			 externoLabelSMS.disabled="disabled";
			 externoLabelSMS.style.display="none";
			 externoLabelSMS.style.visibility="hidden";

			 externoLabel.disabled="";
			 externoLabel.style.display="block";
			 externoLabel.style.visibility="visible";

			 generalesD.disabled="";
			 generalesD.style.display="block";
			 generalesD.style.visibility="visible";

			 mensajeD.disabled="";
			 mensajeD.style.display="block";
			 mensajeD.style.visibility="visible";

			 destinatariosD.disabled="";
			 destinatariosD.style.display="block";
			 destinatariosD.style.visibility="visible";

			 button.disabled="";
			 button.style.display="block";
			 button.style.visibility="visible";

// 			nat
			 organismo.value="";
			 organismo.disabled="";
			 organismo.style.display="block";
			 organismo.style.visibility="visible";		 
			 organismoLabel.disabled="";
			 organismoLabel.style.display="block";
			 organismoLabel.style.visibility="visible";		 
// 			 organismoPagador.value="";
// 			 organismoPagador.disabled="disabled";
// 			 organismoPagador.style.display="none";
// 			 organismoPagador.style.visibility="hidden";
// 			 organismoPagadorLabel.disabled="disabled";
// 			 organismoPagadorLabel.style.display="none";
// 			 organismoPagadorLabel.style.visibility="hidden";
// 			 asunto.value="";
			 asunto.disabled="";
			 asunto.style.display="block";
			 asunto.style.visibility="visible";		 
			 asuntoLabel.disabled="";
			 asuntoLabel.style.display="block";
			 asuntoLabel.style.visibility="visible";		 
			 titulo.value="";
			 titulo.disabled="disabled";
			 titulo.style.display="none";
			 titulo.style.visibility="hidden";
			 tituloLabel.disabled="disabled";
			 tituloLabel.style.display="none";
			 tituloLabel.style.visibility="hidden";
// 			 mensaje.value="";
			 mensaje.disabled="";
			 mensaje.style.display="block";
			 mensaje.style.visibility="visible";		 
			 mensajeLabel.disabled="";
			 mensajeLabel.style.display="block";
			 mensajeLabel.style.visibility="visible";	
			 cuerpo.value="";
			 cuerpo.disabled="disabled";
			 cuerpo.style.display="none";
			 cuerpo.style.visibility="hidden";
			 cuerpoLabel.disabled="disabled";
			 cuerpoLabel.style.display="none";
			 cuerpoLabel.style.visibility="hidden";
// 			 origen.value="";
// 			 origen.disabled="";
// 			 origen.style.display="block";
// 			 origen.style.visibility="visible";
// 			 origenLabel.disabled="";
// 			 origenLabel.style.display="block";
// 			 origenLabel.style.visibility="visible";
// 			 modo.value="";
// 			 modo.disabled="";
// 			 modo.style.display="block";
// 			 modo.style.visibility="visible";
// 			 modoLabel.disabled="";
// 			 modoLabel.style.display="block";
// 			 modoLabel.style.visibility="visible";
			 adjunto.value="";
			 adjunto.disabled="";
			 adjunto.style.display="block";
			 adjunto.style.visibility="visible";
			 adjuntoLabel.disabled="";
			 adjuntoLabel.style.display="block";
			 adjuntoLabel.style.visibility="visible";
// 			 icono.value="";
// 			 icono.disabled="disabled";
// 			 icono.style.display="none";
// 			 icono.style.visibility="hidden";
// 			 iconoLabel.disabled="disabled";
// 			 iconoLabel.style.display="none";
// 			 iconoLabel.style.visibility="hidden";
// 			 sonido.value="";
// 			 sonido.disabled="disabled";
// 			 sonido.style.display="none";
// 			 sonido.style.visibility="hidden";
// 			 sonidoLabel.disabled="disabled";
// 			 sonidoLabel.style.display="none";
// 			 sonidoLabel.style.visibility="hidden";
			 idUsuario.value="";
			 idUsuario.disabled="disabled";
			 idUsuario.style.display="none";
			 idUsuario.style.visibility="hidden";
			 idUsuarioLabel.disabled="disabled";
			 idUsuarioLabel.style.display="none";
			 idUsuarioLabel.style.visibility="hidden";
			 movil.value="";
			 movil.disabled="disabled";
			 movil.style.display="none";
			 movil.style.visibility="hidden";
			 movilLabel.disabled="disabled";
			 movilLabel.style.display="none";
			 movilLabel.style.visibility="hidden";
// 			 emailTo.value="";
			 emailTo.disabled="";
			 emailTo.style.display="block";
			 emailTo.style.visibility="visible";
			 emailToLabel.disabled="";
			 emailToLabel.style.display="block";
			 emailToLabel.style.visibility="visible";
// 			 emailCc.value="";
			 emailCc.disabled="";
			 emailCc.style.display="block";
			 emailCc.style.visibility="visible";
			 emailCcLabel.disabled="";
			 emailCcLabel.style.display="block";
			 emailCcLabel.style.visibility="visible";
// 			 emailCco.value="";
			 emailCco.disabled="";
			 emailCco.style.display="block";
			 emailCco.style.visibility="visible";
			 emailCcoLabel.disabled="";
			 emailCcoLabel.style.display="block";
			 emailCcoLabel.style.visibility="visible";

			 passbook.disabled="";
			 passbook.style.display="block";
			 passbook.style.visibility="visible";

		if(passbook.checked == true){
			 passbookLabel.disabled="";
			 passbookLabel.style.display="block";
			 passbookLabel.style.visibility="visible";
			 url.value="";
			 url.disabled="";
			 url.style.display="block";
			 url.style.visibility="visible";
			 urlLabel.disabled="";
			 urlLabel.style.display="block";
			 urlLabel.style.visibility="visible";
			 logoPassbook.value="";
			 logoPassbook.disabled="";
			 logoPassbook.style.display="block";
			 logoPassbook.style.visibility="visible";
			 logoPassbookLabel.disabled="";
			 logoPassbookLabel.style.display="block";
			 logoPassbookLabel.style.visibility="visible";
			 descripcionPassbook.value="";
			 descripcionPassbook.disabled="";
			 descripcionPassbook.style.display="block";
			 descripcionPassbook.style.visibility="visible";
			 descripcionPassbookLabel.disabled="";
			 descripcionPassbookLabel.style.display="block";
			 descripcionPassbookLabel.style.visibility="visible";

			 camposPrincLabel.disabled="";
			 camposPrincLabel.style.display="block";
			 camposPrincLabel.style.visibility="visible";
			 keyPrinc.value="";
			 keyPrinc.disabled="";
			 keyPrinc.style.display="block";
			 keyPrinc.style.visibility="visible";
			 keyPrincLabel.disabled="";
			 keyPrincLabel.style.display="block";
			 keyPrincLabel.style.visibility="visible";
			 labelPrinc.value="";
			 labelPrinc.disabled="";
			 labelPrinc.style.display="block";
			 labelPrinc.style.visibility="visible";
			 labelPrincLabel.disabled="";
			 labelPrincLabel.style.display="block";
			 labelPrincLabel.style.visibility="visible";		 
			 valuePrinc.value="";
			 valuePrinc.disabled="";
			 valuePrinc.style.display="block";
			 valuePrinc.style.visibility="visible";
			 valuePrincLabel.disabled="";
			 valuePrincLabel.style.display="block";
			 valuePrincLabel.style.visibility="visible";

			 camposSecLabel.disabled="";
			 camposSecLabel.style.display="block";
			 camposSecLabel.style.visibility="visible";
			 keySec.value="";
			 keySec.disabled="";
			 keySec.style.display="block";
			 keySec.style.visibility="visible";
			 keySecLabel.disabled="";
			 keySecLabel.style.display="block";
			 keySecLabel.style.visibility="visible";
			 labelSec.value="";
			 labelSec.disabled="";
			 labelSec.style.display="block";
			 labelSec.style.visibility="visible";
			 labelSecLabel.disabled="";
			 labelSecLabel.style.display="block";
			 labelSecLabel.style.visibility="visible";		 
			 valueSec.value="";
			 valueSec.disabled="";
			 valueSec.style.display="block";
			 valueSec.style.visibility="visible";
			 valueSecLabel.disabled="";
			 valueSecLabel.style.display="block";
			 valueSecLabel.style.visibility="visible";

			 camposAuxLabel.disabled="";
			 camposAuxLabel.style.display="block";
			 camposAuxLabel.style.visibility="visible";
			 keyAux.value="";
			 keyAux.disabled="";
			 keyAux.style.display="block";
			 keyAux.style.visibility="visible";
			 keyAuxLabel.disabled="";
			 keyAuxLabel.style.display="block";
			 keyAuxLabel.style.visibility="visible";
			 labelAux.value="";
			 labelAux.disabled="";
			 labelAux.style.display="block";
			 labelAux.style.visibility="visible";
			 labelAuxLabel.disabled="";
			 labelAuxLabel.style.display="block";
			 labelAuxLabel.style.visibility="visible";		 
			 valueAux.value="";
			 valueAux.disabled="";
			 valueAux.style.display="block";
			 valueAux.style.visibility="visible";
			 valueAuxLabel.disabled="";
			 valueAuxLabel.style.display="block";
			 valueAuxLabel.style.visibility="visible";

			 camposTrasLabel.disabled="";
			 camposTrasLabel.style.display="block";
			 camposTrasLabel.style.visibility="visible";
			 keyTras.value="";
			 keyTras.disabled="";
			 keyTras.style.display="block";
			 keyTras.style.visibility="visible";
			 keyTrasLabel.disabled="";
			 keyTrasLabel.style.display="block";
			 keyTrasLabel.style.visibility="visible";
			 labelTras.value="";
			 labelTras.disabled="";
			 labelTras.style.display="block";
			 labelTras.style.visibility="visible";
			 labelTrasLabel.disabled="";
			 labelTrasLabel.style.display="block";
			 labelTrasLabel.style.visibility="visible";		 
			 valueTras.value="";
			 valueTras.disabled="";
			 valueTras.style.display="block";
			 valueTras.style.visibility="visible";
			 valueTrasLabel.disabled="";
			 valueTrasLabel.style.display="block";
			 valueTrasLabel.style.visibility="visible";
		}else{
			passbookLabel.disabled="disabled";
			 passbookLabel.style.display="none";
			 passbookLabel.style.visibility="hidden";
			 url.value="";
			 url.disabled="disabled";
			 url.style.display="none";
			 url.style.visibility="hidden";
			 urlLabel.disabled="disabled";
			 urlLabel.style.display="none";
			 urlLabel.style.visibility="hidden";
			 logoPassbook.value="";
			 logoPassbook.disabled="disabled";
			 logoPassbook.style.display="none";
			 logoPassbook.style.visibility="hidden";
			 logoPassbookLabel.disabled="disabled";
			 logoPassbookLabel.style.display="none";
			 logoPassbookLabel.style.visibility="hidden";
			 descripcionPassbook.value="";
			 descripcionPassbook.disabled="disabled";
			 descripcionPassbook.style.display="none";
			 descripcionPassbook.style.visibility="hidden";
			 descripcionPassbookLabel.disabled="disabled";
			 descripcionPassbookLabel.style.display="none";
			 descripcionPassbookLabel.style.visibility="hidden";

			 camposPrincLabel.disabled="disabled";
			 camposPrincLabel.style.display="none";
			 camposPrincLabel.style.visibility="hidden";
			 keyPrinc.value="";
			 keyPrinc.disabled="disabled";
			 keyPrinc.style.display="none";
			 keyPrinc.style.visibility="hidden";
			 keyPrincLabel.disabled="disabled";
			 keyPrincLabel.style.display="none";
			 keyPrincLabel.style.visibility="hidden";
			 labelPrinc.value="";
			 labelPrinc.disabled="disabled";
			 labelPrinc.style.display="none";
			 labelPrinc.style.visibility="hidden";
			 labelPrincLabel.disabled="disabled";
			 labelPrincLabel.style.display="none";
			 labelPrincLabel.style.visibility="hidden";		 
			 valuePrinc.value="";
			 valuePrinc.disabled="disabled";
			 valuePrinc.style.display="block";
			 valuePrinc.style.visibility="hidden";
			 valuePrincLabel.disabled="disabled";
			 valuePrincLabel.style.display="none";
			 valuePrincLabel.style.visibility="hidden";

			 camposSecLabel.disabled="disabled";
			 camposSecLabel.style.display="none";
			 camposSecLabel.style.visibility="hidden";
			 keySec.value="";
			 keySec.disabled="disabled";
			 keySec.style.display="none";
			 keySec.style.visibility="hidden";
			 keySecLabel.disabled="disabled";
			 keySecLabel.style.display="none";
			 keySecLabel.style.visibility="hidden";
			 labelSec.value="";
			 labelSec.disabled="disabled";
			 labelSec.style.display="none";
			 labelSec.style.visibility="hidden";
			 labelSecLabel.disabled="disabled";
			 labelSecLabel.style.display="none";
			 labelSecLabel.style.visibility="hidden";		 
			 valueSec.value="";
			 valueSec.disabled="disabled";
			 valueSec.style.display="none";
			 valueSec.style.visibility="hidden";
			 valueSecLabel.disabled="disabled";
			 valueSecLabel.style.display="none";
			 valueSecLabel.style.visibility="hidden";

			 camposAuxLabel.disabled="disabled";
			 camposAuxLabel.style.display="none";
			 camposAuxLabel.style.visibility="hidden";
			 keyAux.value="";
			 keyAux.disabled="disabled";
			 keyAux.style.display="none";
			 keyAux.style.visibility="hidden";
			 keyAuxLabel.disabled="disabled";
			 keyAuxLabel.style.display="none";
			 keyAuxLabel.style.visibility="hidden";
			 labelAux.value="";
			 labelAux.disabled="disabled";
			 labelAux.style.display="none";
			 labelAux.style.visibility="hidden";
			 labelAuxLabel.disabled="disabled";
			 labelAuxLabel.style.display="none";
			 labelAuxLabel.style.visibility="hidden";		 
			 valueAux.value="";
			 valueAux.disabled="disabled";
			 valueAux.style.display="none";
			 valueAux.style.visibility="hidden";
			 valueAuxLabel.disabled="disabled";
			 valueAuxLabel.style.display="none";
			 valueAuxLabel.style.visibility="hidden";

			 camposTrasLabel.disabled="disabled";
			 camposTrasLabel.style.display="none";
			 camposTrasLabel.style.visibility="hidden";
			 keyTras.value="";
			 keyTras.disabled="disabled";
			 keyTras.style.display="none";
			 keyTras.style.visibility="hidden";
			 keyTrasLabel.disabled="disabled";
			 keyTrasLabel.style.display="none";
			 keyTrasLabel.style.visibility="hidden";
			 labelTras.value="";
			 labelTras.disabled="disabled";
			 labelTras.style.display="none";
			 labelTras.style.visibility="hidden";
			 labelTrasLabel.disabled="disabled";
			 labelTrasLabel.style.display="none";
			 labelTrasLabel.style.visibility="hidden";		 
			 valueTras.value="";
			 valueTras.disabled="disabled";
			 valueTras.style.display="none";
			 valueTras.style.visibility="hidden";
			 valueTrasLabel.disabled="disabled";
			 valueTrasLabel.style.display="none";
			 valueTrasLabel.style.visibility="hidden";
			}
//			nat

		}
		if(combo.value=="2"){//SMS

			 externoLabelSMS.disabled="";
			 externoLabelSMS.style.display="block";
			 externoLabelSMS.style.visibility="visible";

			 externoLabel.disabled="disabled";
			 externoLabel.style.display="none";
			 externoLabel.style.visibility="hidden";

			 generalesD.disabled="";
			 generalesD.style.display="block";
			 generalesD.style.visibility="visible";

			 mensajeD.disabled="";
			 mensajeD.style.display="block";
			 mensajeD.style.visibility="visible";

			 destinatariosD.disabled="";
			 destinatariosD.style.display="block";
			 destinatariosD.style.visibility="visible";

			 button.disabled="";
			 button.style.display="block";
			 button.style.visibility="visible";
			
// 			nat
			 organismo.value="";
			 organismo.disabled="";
			 organismo.style.display="block";
			 organismo.style.visibility="visible";		 
			 organismoLabel.disabled="";
			 organismoLabel.style.display="block";
			 organismoLabel.style.visibility="visible";		 
// 			 organismoPagador.value="";
// 			 organismoPagador.disabled="";
// 			 organismoPagador.style.display="block";
// 			 organismoPagador.style.visibility="visible";
// 			 organismoPagadorLabel.disabled="";
// 			 organismoPagadorLabel.style.display="block";
// 			 organismoPagadorLabel.style.visibility="visible";
			 asunto.value="";
			 asunto.disabled="disabled";
			 asunto.style.display="none";
			 asunto.style.visibility="hidden";		 
			 asuntoLabel.disabled="disabled";
			 asuntoLabel.style.display="none";
			 asuntoLabel.style.visibility="hidden";		 
			 titulo.value="";
			 titulo.disabled="disabled";
			 titulo.style.display="none";
			 titulo.style.visibility="hidden";
			 tituloLabel.disabled="disabled";
			 tituloLabel.style.display="none";
			 tituloLabel.style.visibility="hidden";
			 mensaje.value="";
			 mensaje.disabled="";
			 mensaje.style.display="block";
			 mensaje.style.visibility="visible";		 
			 mensajeLabel.disabled="";
			 mensajeLabel.style.display="block";
			 mensajeLabel.style.visibility="visible";	
			 cuerpo.value="";
			 cuerpo.disabled="disabled";
			 cuerpo.style.display="none";
			 cuerpo.style.visibility="hidden";
			 cuerpoLabel.disabled="disabled";
			 cuerpoLabel.style.display="none";
			 cuerpoLabel.style.visibility="hidden";
// 			 origen.value="";
// 			 origen.disabled="disabled";
// 			 origen.style.display="none";
// 			 origen.style.visibility="hidden";
// 			 origenLabel.disabled="disabled";
// 			 origenLabel.style.display="none";
// 			 origenLabel.style.visibility="hidden";
// 			 modo.value="";
// 			 modo.disabled="disabled";
// 			 modo.style.display="none";
// 			 modo.style.visibility="hidden";
// 			 modoLabel.disabled="disabled";
// 			 modoLabel.style.display="none";
// 			 modoLabel.style.visibility="hidden";
			 adjunto.value="";
			 adjunto.disabled="disabled";
			 adjunto.style.display="none";
			 adjunto.style.visibility="hidden";
			 adjuntoLabel.disabled="disabled";
			 adjuntoLabel.style.display="none";
			 adjuntoLabel.style.visibility="hidden";
// 			 icono.value="";
// 			 icono.disabled="disabled";
// 			 icono.style.display="none";
// 			 icono.style.visibility="hidden";
// 			 iconoLabel.disabled="disabled";
// 			 iconoLabel.style.display="none";
// 			 iconoLabel.style.visibility="hidden";
// 			 sonido.value="";
// 			 sonido.disabled="disabled";
// 			 sonido.style.display="none";
// 			 sonido.style.visibility="hidden";
// 			 sonidoLabel.disabled="disabled";
// 			 sonidoLabel.style.display="none";
// 			 sonidoLabel.style.visibility="hidden";
			 idUsuario.value="";
			 idUsuario.disabled="disabled";
			 idUsuario.style.display="none";
			 idUsuario.style.visibility="hidden";
			 idUsuarioLabel.disabled="disabled";
			 idUsuarioLabel.style.display="none";
			 idUsuarioLabel.style.visibility="hidden";
			 movil.value="";
			 movil.disabled="";
			 movil.style.display="block";
			 movil.style.visibility="visible";
			 movilLabel.disabled="";
			 movilLabel.style.display="block";
			 movilLabel.style.visibility="visible";
			 emailTo.value="";
			 emailTo.disabled="disabled";
			 emailTo.style.display="none";
			 emailTo.style.visibility="hidden";
			 emailToLabel.disabled="disabled";
			 emailToLabel.style.display="none";
			 emailToLabel.style.visibility="hidden";
			 emailCc.value="";
			 emailCc.disabled="disabled";
			 emailCc.style.display="none";
			 emailCc.style.visibility="hidden";
			 emailCcLabel.disabled="disabled";
			 emailCcLabel.style.display="none";
			 emailCcLabel.style.visibility="hidden";
			 emailCco.value="";
			 emailCco.disabled="disabled";
			 emailCco.style.display="none";
			 emailCco.style.visibility="hidden";
			 emailCcoLabel.disabled="disabled";
			 emailCcoLabel.style.display="none";
			 emailCcoLabel.style.visibility="hidden";

			 passbook.disabled="disabled";
			 passbook.style.display="none";
			 passbook.style.visibility="hidden";
			 
			 passbookLabel.disabled="disabled";
			 passbookLabel.style.display="none";
			 passbookLabel.style.visibility="hidden";
			 url.value="";
			 url.disabled="disabled";
			 url.style.display="none";
			 url.style.visibility="hidden";
			 urlLabel.disabled="disabled";
			 urlLabel.style.display="none";
			 urlLabel.style.visibility="hidden";
			 logoPassbook.value="";
			 logoPassbook.disabled="disabled";
			 logoPassbook.style.display="none";
			 logoPassbook.style.visibility="hidden";
			 logoPassbookLabel.disabled="disabled";
			 logoPassbookLabel.style.display="none";
			 logoPassbookLabel.style.visibility="hidden";
			 descripcionPassbook.value="";
			 descripcionPassbook.disabled="disabled";
			 descripcionPassbook.style.display="none";
			 descripcionPassbook.style.visibility="hidden";
			 descripcionPassbookLabel.disabled="disabled";
			 descripcionPassbookLabel.style.display="none";
			 descripcionPassbookLabel.style.visibility="hidden";

			 camposPrincLabel.disabled="disabled";
			 camposPrincLabel.style.display="none";
			 camposPrincLabel.style.visibility="hidden";
			 keyPrinc.value="";
			 keyPrinc.disabled="disabled";
			 keyPrinc.style.display="none";
			 keyPrinc.style.visibility="hidden";
			 keyPrincLabel.disabled="disabled";
			 keyPrincLabel.style.display="none";
			 keyPrincLabel.style.visibility="hidden";
			 labelPrinc.value="";
			 labelPrinc.disabled="disabled";
			 labelPrinc.style.display="none";
			 labelPrinc.style.visibility="hidden";
			 labelPrincLabel.disabled="disabled";
			 labelPrincLabel.style.display="none";
			 labelPrincLabel.style.visibility="hidden";		 
			 valuePrinc.value="";
			 valuePrinc.disabled="disabled";
			 valuePrinc.style.display="block";
			 valuePrinc.style.visibility="hidden";
			 valuePrincLabel.disabled="disabled";
			 valuePrincLabel.style.display="none";
			 valuePrincLabel.style.visibility="hidden";

			 camposSecLabel.disabled="disabled";
			 camposSecLabel.style.display="none";
			 camposSecLabel.style.visibility="hidden";
			 keySec.value="";
			 keySec.disabled="disabled";
			 keySec.style.display="none";
			 keySec.style.visibility="hidden";
			 keySecLabel.disabled="disabled";
			 keySecLabel.style.display="none";
			 keySecLabel.style.visibility="hidden";
			 labelSec.value="";
			 labelSec.disabled="disabled";
			 labelSec.style.display="none";
			 labelSec.style.visibility="hidden";
			 labelSecLabel.disabled="disabled";
			 labelSecLabel.style.display="none";
			 labelSecLabel.style.visibility="hidden";		 
			 valueSec.value="";
			 valueSec.disabled="disabled";
			 valueSec.style.display="none";
			 valueSec.style.visibility="hidden";
			 valueSecLabel.disabled="disabled";
			 valueSecLabel.style.display="none";
			 valueSecLabel.style.visibility="hidden";

			 camposAuxLabel.disabled="disabled";
			 camposAuxLabel.style.display="none";
			 camposAuxLabel.style.visibility="hidden";
			 keyAux.value="";
			 keyAux.disabled="disabled";
			 keyAux.style.display="none";
			 keyAux.style.visibility="hidden";
			 keyAuxLabel.disabled="disabled";
			 keyAuxLabel.style.display="none";
			 keyAuxLabel.style.visibility="hidden";
			 labelAux.value="";
			 labelAux.disabled="disabled";
			 labelAux.style.display="none";
			 labelAux.style.visibility="hidden";
			 labelAuxLabel.disabled="disabled";
			 labelAuxLabel.style.display="none";
			 labelAuxLabel.style.visibility="hidden";		 
			 valueAux.value="";
			 valueAux.disabled="disabled";
			 valueAux.style.display="none";
			 valueAux.style.visibility="hidden";
			 valueAuxLabel.disabled="disabled";
			 valueAuxLabel.style.display="none";
			 valueAuxLabel.style.visibility="hidden";

			 camposTrasLabel.disabled="disabled";
			 camposTrasLabel.style.display="none";
			 camposTrasLabel.style.visibility="hidden";
			 keyTras.value="";
			 keyTras.disabled="disabled";
			 keyTras.style.display="none";
			 keyTras.style.visibility="hidden";
			 keyTrasLabel.disabled="disabled";
			 keyTrasLabel.style.display="none";
			 keyTrasLabel.style.visibility="hidden";
			 labelTras.value="";
			 labelTras.disabled="disabled";
			 labelTras.style.display="none";
			 labelTras.style.visibility="hidden";
			 labelTrasLabel.disabled="disabled";
			 labelTrasLabel.style.display="none";
			 labelTrasLabel.style.visibility="hidden";		 
			 valueTras.value="";
			 valueTras.disabled="disabled";
			 valueTras.style.display="none";
			 valueTras.style.visibility="hidden";
			 valueTrasLabel.disabled="disabled";
			 valueTrasLabel.style.display="none";
			 valueTrasLabel.style.visibility="hidden";

		}
		if(combo.value=="3"){//Recepcion SMS

			 generalesD.disabled="disabled";
			 generalesD.style.display="none";
			 generalesD.style.visibility="hidden";

			 mensajeD.disabled="disabled";
			 mensajeD.style.display="none";
			 mensajeD.style.visibility="hidden";

			 destinatariosD.disabled="disabled";
			 destinatariosD.style.display="none";
			 destinatariosD.style.visibility="hidden";

			 button.disabled="disabled";
			 button.style.display="none";
			 button.style.visibility="hidden";
		}
		if(combo.value=="4"){//Notificaciones Push

			 externoLabel.disabled="";
			 externoLabel.style.display="block";
			 externoLabel.style.visibility="visible";

			 externoLabelSMS.disabled="disabled";
			 externoLabelSMS.style.display="none";
			 externoLabelSMS.style.visibility="hidden";

			 generalesD.disabled="";
			 generalesD.style.display="block";
			 generalesD.style.visibility="visible";

			 mensajeD.disabled="";
			 mensajeD.style.display="block";
			 mensajeD.style.visibility="visible";

			 destinatariosD.disabled="";
			 destinatariosD.style.display="block";
			 destinatariosD.style.visibility="visible";

			 button.disabled="";
			 button.style.display="block";
			 button.style.visibility="visible";
			 
			 organismo.value="";
			 organismo.disabled="disabled";
			 organismo.style.display="none";
			 organismo.style.visibility="hidden";		 
			 organismoLabel.disabled="disabled";
			 organismoLabel.style.display="none";
			 organismoLabel.style.visibility="hidden";		 
// 			 organismoPagador.value="";
// 			 organismoPagador.disabled="disabled";
// 			 organismoPagador.style.display="none";
// 			 organismoPagador.style.visibility="hidden";
// 			 organismoPagadorLabel.disabled="disabled";
// 			 organismoPagadorLabel.style.display="none";
// 			 organismoPagadorLabel.style.visibility="hidden";
			 asunto.value="";
			 asunto.disabled="disabled";
			 asunto.style.display="none";
			 asunto.style.visibility="hidden";		 
			 asuntoLabel.disabled="disabled";
			 asuntoLabel.style.display="none";
			 asuntoLabel.style.visibility="hidden";		 
			 titulo.value="";
			 titulo.disabled="";
			 titulo.style.display="block";
			 titulo.style.visibility="visible";
			 tituloLabel.disabled="";
			 tituloLabel.style.display="block";
			 tituloLabel.style.visibility="visible";
			 mensaje.value="";
			 mensaje.disabled="";
			 mensaje.style.display="block";
			 mensaje.style.visibility="visible";		 
			 mensajeLabel.disabled="";
			 mensajeLabel.style.display="block";
			 mensajeLabel.style.visibility="visible";	
			 cuerpo.value="";
			 cuerpo.disabled="disabled";
			 cuerpo.style.display="none";
			 cuerpo.style.visibility="hidden";
			 cuerpoLabel.disabled="disabled";
			 cuerpoLabel.style.display="none";
			 cuerpoLabel.style.visibility="hidden";
// 			 origen.value="";
// 			 origen.disabled="disabled";
// 			 origen.style.display="none";
// 			 origen.style.visibility="hidden";
// 			 origenLabel.disabled="disabled";
// 			 origenLabel.style.display="none";
// 			 origenLabel.style.visibility="hidden";
// 			 modo.value="";
// 			 modo.disabled="disabled";
// 			 modo.style.display="none";
// 			 modo.style.visibility="hidden";
// 			 modoLabel.disabled="disabled";
// 			 modoLabel.style.display="none";
// 			 modoLabel.style.visibility="hidden";
			 adjunto.value="";
			 adjunto.disabled="disabled";
			 adjunto.style.display="none";
			 adjunto.style.visibility="hidden";
			 adjuntoLabel.disabled="disabled";
			 adjuntoLabel.style.display="none";
			 adjuntoLabel.style.visibility="hidden";
// 			 icono.value="";
// 			 icono.disabled="";
// 			 icono.style.display="block";
// 			 icono.style.visibility="visible";
// 			 iconoLabel.disabled="";
// 			 iconoLabel.style.display="block";
// 			 iconoLabel.style.visibility="visible";
// 			 sonido.value="";
// 			 sonido.disabled="";
// 			 sonido.style.display="block";
// 			 sonido.style.visibility="visible";
// 			 sonidoLabel.disabled="";
// 			 sonidoLabel.style.display="block";
// 			 sonidoLabel.style.visibility="visible";
			 idUsuario.value="";
			 idUsuario.disabled="";
			 idUsuario.style.display="block";
			 idUsuario.style.visibility="visible";
			 idUsuarioLabel.disabled="";
			 idUsuarioLabel.style.display="block";
			 idUsuarioLabel.style.visibility="visible";
			 movil.value="";
			 movil.disabled="disabled";
			 movil.style.display="none";
			 movil.style.visibility="hidden";
			 movilLabel.disabled="disabled";
			 movilLabel.style.display="none";
			 movilLabel.style.visibility="hidden";
			 emailTo.value="";
			 emailTo.disabled="disabled";
			 emailTo.style.display="none";
			 emailTo.style.visibility="hidden";
			 emailToLabel.disabled="disabled";
			 emailToLabel.style.display="none";
			 emailToLabel.style.visibility="hidden";
			 emailCc.value="";
			 emailCc.disabled="disabled";
			 emailCc.style.display="none";
			 emailCc.style.visibility="hidden";
			 emailCcLabel.disabled="disabled";
			 emailCcLabel.style.display="none";
			 emailCcLabel.style.visibility="hidden";
			 emailCco.value="";
			 emailCco.disabled="disabled";
			 emailCco.style.display="none";
			 emailCco.style.visibility="hidden";
			 emailCcoLabel.disabled="disabled";
			 emailCcoLabel.style.display="none";
			 emailCcoLabel.style.visibility="hidden";

			 passbook.disabled="disabled";
			 passbook.style.display="none";
			 passbook.style.visibility="hidden";
			 
			 passbookLabel.disabled="disabled";
			 passbookLabel.style.display="none";
			 passbookLabel.style.visibility="hidden";
			 url.value="";
			 url.disabled="disabled";
			 url.style.display="none";
			 url.style.visibility="hidden";
			 urlLabel.disabled="disabled";
			 urlLabel.style.display="none";
			 urlLabel.style.visibility="hidden";
			 logoPassbook.value="";
			 logoPassbook.disabled="disabled";
			 logoPassbook.style.display="none";
			 logoPassbook.style.visibility="hidden";
			 logoPassbookLabel.disabled="disabled";
			 logoPassbookLabel.style.display="none";
			 logoPassbookLabel.style.visibility="hidden";
			 descripcionPassbook.value="";
			 descripcionPassbook.disabled="disabled";
			 descripcionPassbook.style.display="none";
			 descripcionPassbook.style.visibility="hidden";
			 descripcionPassbookLabel.disabled="disabled";
			 descripcionPassbookLabel.style.display="none";
			 descripcionPassbookLabel.style.visibility="hidden";

			 camposPrincLabel.disabled="disabled";
			 camposPrincLabel.style.display="none";
			 camposPrincLabel.style.visibility="hidden";
			 keyPrinc.value="";
			 keyPrinc.disabled="disabled";
			 keyPrinc.style.display="none";
			 keyPrinc.style.visibility="hidden";
			 keyPrincLabel.disabled="disabled";
			 keyPrincLabel.style.display="none";
			 keyPrincLabel.style.visibility="hidden";
			 labelPrinc.value="";
			 labelPrinc.disabled="disabled";
			 labelPrinc.style.display="none";
			 labelPrinc.style.visibility="hidden";
			 labelPrincLabel.disabled="disabled";
			 labelPrincLabel.style.display="none";
			 labelPrincLabel.style.visibility="hidden";		 
			 valuePrinc.value="";
			 valuePrinc.disabled="disabled";
			 valuePrinc.style.display="block";
			 valuePrinc.style.visibility="hidden";
			 valuePrincLabel.disabled="disabled";
			 valuePrincLabel.style.display="none";
			 valuePrincLabel.style.visibility="hidden";

			 camposSecLabel.disabled="disabled";
			 camposSecLabel.style.display="none";
			 camposSecLabel.style.visibility="hidden";
			 keySec.value="";
			 keySec.disabled="disabled";
			 keySec.style.display="none";
			 keySec.style.visibility="hidden";
			 keySecLabel.disabled="disabled";
			 keySecLabel.style.display="none";
			 keySecLabel.style.visibility="hidden";
			 labelSec.value="";
			 labelSec.disabled="disabled";
			 labelSec.style.display="none";
			 labelSec.style.visibility="hidden";
			 labelSecLabel.disabled="disabled";
			 labelSecLabel.style.display="none";
			 labelSecLabel.style.visibility="hidden";		 
			 valueSec.value="";
			 valueSec.disabled="disabled";
			 valueSec.style.display="none";
			 valueSec.style.visibility="hidden";
			 valueSecLabel.disabled="disabled";
			 valueSecLabel.style.display="none";
			 valueSecLabel.style.visibility="hidden";

			 camposAuxLabel.disabled="disabled";
			 camposAuxLabel.style.display="none";
			 camposAuxLabel.style.visibility="hidden";
			 keyAux.value="";
			 keyAux.disabled="disabled";
			 keyAux.style.display="none";
			 keyAux.style.visibility="hidden";
			 keyAuxLabel.disabled="disabled";
			 keyAuxLabel.style.display="none";
			 keyAuxLabel.style.visibility="hidden";
			 labelAux.value="";
			 labelAux.disabled="disabled";
			 labelAux.style.display="none";
			 labelAux.style.visibility="hidden";
			 labelAuxLabel.disabled="disabled";
			 labelAuxLabel.style.display="none";
			 labelAuxLabel.style.visibility="hidden";		 
			 valueAux.value="";
			 valueAux.disabled="disabled";
			 valueAux.style.display="none";
			 valueAux.style.visibility="hidden";
			 valueAuxLabel.disabled="disabled";
			 valueAuxLabel.style.display="none";
			 valueAuxLabel.style.visibility="hidden";

			 camposTrasLabel.disabled="disabled";
			 camposTrasLabel.style.display="none";
			 camposTrasLabel.style.visibility="hidden";
			 keyTras.value="";
			 keyTras.disabled="disabled";
			 keyTras.style.display="none";
			 keyTras.style.visibility="hidden";
			 keyTrasLabel.disabled="disabled";
			 keyTrasLabel.style.display="none";
			 keyTrasLabel.style.visibility="hidden";
			 labelTras.value="";
			 labelTras.disabled="disabled";
			 labelTras.style.display="none";
			 labelTras.style.visibility="hidden";
			 labelTrasLabel.disabled="disabled";
			 labelTrasLabel.style.display="none";
			 labelTrasLabel.style.visibility="hidden";		 
			 valueTras.value="";
			 valueTras.disabled="disabled";
			 valueTras.style.display="none";
			 valueTras.style.visibility="hidden";
			 valueTrasLabel.disabled="disabled";
			 valueTrasLabel.style.display="none";
			 valueTrasLabel.style.visibility="hidden";
//			nat			
		}if(combo.value=="5"){//Web Push

			 externoLabel.disabled="";
			 externoLabel.style.display="block";
			 externoLabel.style.visibility="visible";

			 externoLabelSMS.disabled="disabled";
			 externoLabelSMS.style.display="none";
			 externoLabelSMS.style.visibility="hidden";

			 generalesD.disabled="";
			 generalesD.style.display="block";
			 generalesD.style.visibility="visible";

			 mensajeD.disabled="";
			 mensajeD.style.display="block";
			 mensajeD.style.visibility="visible";

			 destinatariosD.disabled="";
			 destinatariosD.style.display="block";
			 destinatariosD.style.visibility="visible";

			 button.disabled="";
			 button.style.display="block";
			 button.style.visibility="visible";
			
			 organismo.value="";
			 organismo.disabled="disabled";
			 organismo.style.display="none";
			 organismo.style.visibility="hidden";		 
			 organismoLabel.disabled="disabled";
			 organismoLabel.style.display="none";
			 organismoLabel.style.visibility="hidden";		 
// 			 organismoPagador.value="";
// 			 organismoPagador.disabled="disabled";
// 			 organismoPagador.style.display="none";
// 			 organismoPagador.style.visibility="hidden";
// 			 organismoPagadorLabel.disabled="disabled";
// 			 organismoPagadorLabel.style.display="none";
// 			 organismoPagadorLabel.style.visibility="hidden";
			 asunto.value="";
			 asunto.disabled="disabled";
			 asunto.style.display="none";
			 asunto.style.visibility="hidden";		 
			 asuntoLabel.disabled="disabled";
			 asuntoLabel.style.display="none";
			 asuntoLabel.style.visibility="hidden";		 
			 titulo.value="";
			 titulo.disabled="";
			 titulo.style.display="block";
			 titulo.style.visibility="visible";
			 tituloLabel.disabled="";
			 tituloLabel.style.display="block";
			 tituloLabel.style.visibility="visible";
			 mensaje.value="";
			 mensaje.disabled="disabled";
			 mensaje.style.display="none";
			 mensaje.style.visibility="hidden";		 
			 mensajeLabel.disabled="disabled";
			 mensajeLabel.style.display="none";
			 mensajeLabel.style.visibility="hidden";	
			 cuerpo.value="";
			 cuerpo.disabled="";
			 cuerpo.style.display="block";
			 cuerpo.style.visibility="visible";
			 cuerpoLabel.disabled="";
			 cuerpoLabel.style.display="block";
			 cuerpoLabel.style.visibility="visible";
// 			 origen.value="";
// 			 origen.disabled="disabled";
// 			 origen.style.display="none";
// 			 origen.style.visibility="hidden";
// 			 origenLabel.disabled="disabled";
// 			 origenLabel.style.display="none";
// 			 origenLabel.style.visibility="hidden";
// 			 modo.value="";
// 			 modo.disabled="disabled";
// 			 modo.style.display="none";
// 			 modo.style.visibility="hidden";
// 			 modoLabel.disabled="disabled";
// 			 modoLabel.style.display="none";
// 			 modoLabel.style.visibility="hidden";
			 adjunto.value="";
			 adjunto.disabled="disabled";
			 adjunto.style.display="none";
			 adjunto.style.visibility="hidden";
			 adjuntoLabel.disabled="disabled";
			 adjuntoLabel.style.display="none";
			 adjuntoLabel.style.visibility="hidden";
// 			 icono.value="";
// 			 icono.disabled="";
// 			 icono.style.display="block";
// 			 icono.style.visibility="visible";
// 			 iconoLabel.disabled="";
// 			 iconoLabel.style.display="block";
// 			 iconoLabel.style.visibility="visible";
// 			 sonido.value="";
// 			 sonido.disabled="";
// 			 sonido.style.display="block";
// 			 sonido.style.visibility="visible";
// 			 sonidoLabel.disabled="";
// 			 sonidoLabel.style.display="block";
// 			 sonidoLabel.style.visibility="visible";
			 idUsuario.value="";
			 idUsuario.disabled="";
			 idUsuario.style.display="block";
			 idUsuario.style.visibility="visible";
			 idUsuarioLabel.disabled="";
			 idUsuarioLabel.style.display="block";
			 idUsuarioLabel.style.visibility="visible";
			 movil.value="";
			 movil.disabled="disabled";
			 movil.style.display="none";
			 movil.style.visibility="hidden";
			 movilLabel.disabled="disabled";
			 movilLabel.style.display="none";
			 movilLabel.style.visibility="hidden";
			 emailTo.value="";
			 emailTo.disabled="disabled";
			 emailTo.style.display="none";
			 emailTo.style.visibility="hidden";
			 emailToLabel.disabled="disabled";
			 emailToLabel.style.display="none";
			 emailToLabel.style.visibility="hidden";
			 emailCc.value="";
			 emailCc.disabled="disabled";
			 emailCc.style.display="none";
			 emailCc.style.visibility="hidden";
			 emailCcLabel.disabled="disabled";
			 emailCcLabel.style.display="none";
			 emailCcLabel.style.visibility="hidden";
			 emailCco.value="";
			 emailCco.disabled="disabled";
			 emailCco.style.display="none";
			 emailCco.style.visibility="hidden";
			 emailCcoLabel.disabled="disabled";
			 emailCcoLabel.style.display="none";
			 emailCcoLabel.style.visibility="hidden";

			 passbook.disabled="disabled";
			 passbook.style.display="none";
			 passbook.style.visibility="hidden";
			 
			 passbookLabel.disabled="disabled";
			 passbookLabel.style.display="none";
			 passbookLabel.style.visibility="hidden";
			 url.value="";
			 url.disabled="disabled";
			 url.style.display="none";
			 url.style.visibility="hidden";
			 urlLabel.disabled="disabled";
			 urlLabel.style.display="none";
			 urlLabel.style.visibility="hidden";
			 logoPassbook.value="";
			 logoPassbook.disabled="disabled";
			 logoPassbook.style.display="none";
			 logoPassbook.style.visibility="hidden";
			 logoPassbookLabel.disabled="disabled";
			 logoPassbookLabel.style.display="none";
			 logoPassbookLabel.style.visibility="hidden";
			 descripcionPassbook.value="";
			 descripcionPassbook.disabled="disabled";
			 descripcionPassbook.style.display="none";
			 descripcionPassbook.style.visibility="hidden";
			 descripcionPassbookLabel.disabled="disabled";
			 descripcionPassbookLabel.style.display="none";
			 descripcionPassbookLabel.style.visibility="hidden";

			 camposPrincLabel.disabled="disabled";
			 camposPrincLabel.style.display="none";
			 camposPrincLabel.style.visibility="hidden";
			 keyPrinc.value="";
			 keyPrinc.disabled="disabled";
			 keyPrinc.style.display="none";
			 keyPrinc.style.visibility="hidden";
			 keyPrincLabel.disabled="disabled";
			 keyPrincLabel.style.display="none";
			 keyPrincLabel.style.visibility="hidden";
			 labelPrinc.value="";
			 labelPrinc.disabled="disabled";
			 labelPrinc.style.display="none";
			 labelPrinc.style.visibility="hidden";
			 labelPrincLabel.disabled="disabled";
			 labelPrincLabel.style.display="none";
			 labelPrincLabel.style.visibility="hidden";		 
			 valuePrinc.value="";
			 valuePrinc.disabled="disabled";
			 valuePrinc.style.display="block";
			 valuePrinc.style.visibility="hidden";
			 valuePrincLabel.disabled="disabled";
			 valuePrincLabel.style.display="none";
			 valuePrincLabel.style.visibility="hidden";

			 camposSecLabel.disabled="disabled";
			 camposSecLabel.style.display="none";
			 camposSecLabel.style.visibility="hidden";
			 keySec.value="";
			 keySec.disabled="disabled";
			 keySec.style.display="none";
			 keySec.style.visibility="hidden";
			 keySecLabel.disabled="disabled";
			 keySecLabel.style.display="none";
			 keySecLabel.style.visibility="hidden";
			 labelSec.value="";
			 labelSec.disabled="disabled";
			 labelSec.style.display="none";
			 labelSec.style.visibility="hidden";
			 labelSecLabel.disabled="disabled";
			 labelSecLabel.style.display="none";
			 labelSecLabel.style.visibility="hidden";		 
			 valueSec.value="";
			 valueSec.disabled="disabled";
			 valueSec.style.display="none";
			 valueSec.style.visibility="hidden";
			 valueSecLabel.disabled="disabled";
			 valueSecLabel.style.display="none";
			 valueSecLabel.style.visibility="hidden";

			 camposAuxLabel.disabled="disabled";
			 camposAuxLabel.style.display="none";
			 camposAuxLabel.style.visibility="hidden";
			 keyAux.value="";
			 keyAux.disabled="disabled";
			 keyAux.style.display="none";
			 keyAux.style.visibility="hidden";
			 keyAuxLabel.disabled="disabled";
			 keyAuxLabel.style.display="none";
			 keyAuxLabel.style.visibility="hidden";
			 labelAux.value="";
			 labelAux.disabled="disabled";
			 labelAux.style.display="none";
			 labelAux.style.visibility="hidden";
			 labelAuxLabel.disabled="disabled";
			 labelAuxLabel.style.display="none";
			 labelAuxLabel.style.visibility="hidden";		 
			 valueAux.value="";
			 valueAux.disabled="disabled";
			 valueAux.style.display="none";
			 valueAux.style.visibility="hidden";
			 valueAuxLabel.disabled="disabled";
			 valueAuxLabel.style.display="none";
			 valueAuxLabel.style.visibility="hidden";

			 camposTrasLabel.disabled="disabled";
			 camposTrasLabel.style.display="none";
			 camposTrasLabel.style.visibility="hidden";
			 keyTras.value="";
			 keyTras.disabled="disabled";
			 keyTras.style.display="none";
			 keyTras.style.visibility="hidden";
			 keyTrasLabel.disabled="disabled";
			 keyTrasLabel.style.display="none";
			 keyTrasLabel.style.visibility="hidden";
			 labelTras.value="";
			 labelTras.disabled="disabled";
			 labelTras.style.display="none";
			 labelTras.style.visibility="hidden";
			 labelTrasLabel.disabled="disabled";
			 labelTrasLabel.style.display="none";
			 labelTrasLabel.style.visibility="hidden";		 
			 valueTras.value="";
			 valueTras.disabled="disabled";
			 valueTras.style.display="none";
			 valueTras.style.visibility="hidden";
			 valueTrasLabel.disabled="disabled";
			 valueTrasLabel.style.display="none";
			 valueTrasLabel.style.visibility="hidden";
//			nat			
		}
	}
	function checkCanalHeader(combo){
		var headerSMS= document.getElementById("servidorServicio.headerSMS");
		var headerSMSLabel= document.getElementById("headerSMSLabel");
	
		var prefijoSMS = document.getElementById("servidorServicio.prefijoSMS");
		var prefijoSMSLabel= document.getElementById("prefijoSMSLabel");
	
		var fromMail= document.getElementById("servicio.frommail");
		var fromMailLabel= document.getElementById("frommailLabel");
		var fromMailName= document.getElementById("servicio.frommailname");
		var fromMailNameLabel= document.getElementById("frommailnameLabel");	
		var nombreLoteEnvio= document.getElementById("servicio.nombreloteenvio");
		var nombreLoteEnvioLabel= document.getElementById("nombreloteenvioLabel");
		var nombreEndPoint= document.getElementById("servicio.endpoint");
		var nombreEndPointLabel= document.getElementById("endpointLabel");
		var proveedorUsuarioSMSLabel = document.getElementById("proveedorUsuarioSMSLabel");
		var proveedorUsuarioSMS = document.getElementById("servidorServicio.proveedorUsuarioSMS");
		var proveedorPassSMSLabel = document.getElementById("proveedorPassSMSLabel");
		var proveedorPassSMS = document.getElementById("servidorServicio.proveedorPasswordSMS");
		
		var plataforma = document.getElementById("plataformaid");
		var newPlataformaAndroid = document.getElementById("newPlataformaAndroid");
		var newPlataformaiOS = document.getElementById("newPlataformaiOS");
		var badge= document.getElementById("servicio.badge");
		var badgeLabel= document.getElementById("badgeLabel");
		var fcmProjectKey= document.getElementById("servicio.fcmprojectkey");
		var fcmProjectKeyLabel= document.getElementById("fcmprojectkeyLabel");
		var apnsRutaCertificado= document.getElementById("servicio.apnsrutacertificado");
		var apnsRutaCertificadoLabel= document.getElementById("apnsrutacertificadoLabel");
		var apnsPasswordCertificado= document.getElementById("servicio.apnspasswordcertificado");
		var apnsPasswordCertificadoLabel= document.getElementById("apnspasswordcertificadoLabel");

		var agrupacionCodOrg= document.getElementById("servicio.agrupacioncodorg");
		var agrupacionCodOrgLabel= document.getElementById("agrupacioncodorg");
		var agrupacionCodSia= document.getElementById("servicio.agrupacioncodsia");
		var agrupacionCodSiaLabel= document.getElementById("agrupacioncodsia");
		var agrupacionCodOrgPagador= document.getElementById("servicio.agrupacioncodorgpagador");
		var agrupacionCodOrgPagadorLabel= document.getElementById("agrupacioncodorgpagador");

		var auna= document.getElementById("auna");
		var aune= document.getElementById("aune");
		var auni= document.getElementById("auni");
		var auno= document.getElementById("auno");
		var aunip= document.getElementById("aunip");
		var aunaValue= document.getElementById("aunaValue1");
		var auneValue= document.getElementById("auneValue1");
		var auniValue= document.getElementById("auniValue1");
		var aunoValue= document.getElementById("aunoValue1");
		var aunipValue= document.getElementById("aunipValue1");
		//var colspan1 =document.getElementById("colspan1");

		var containerMultiorganismo = document.getElementById("containerMultiorganismo");
		var vapidPublicKey = document.getElementById("servicio.vapidPublicKey");
		var vapidPublicKeyLabel = document.getElementById("vapidPublicKeyLabel");
		var vapidPrivateKey = document.getElementById("servicio.vapidPrivateKey");
		var vapidPrivateKeyLabel = document.getElementById("vapidPrivateKeyLabel");
		var caducidadWebPushLabel = document.getElementById("caducidadWebPush");
		var caducidadWebPush = document.getElementById("servicio.caducidadWebPush");
		var botonClaves = document.getElementById("botonclaves"); 

		if(combo.value=="1"){//email

			if (auna!=null)
				auna.innerHTML="";
			if (aune!=null)
				aune.innerHTML="";
			if (auni!=null)
				auni.innerHTML="";
			if (auno!=null)
				auno.innerHTML="";
			if (aunip!=null)
				aunip.innerHTML="";
			
			
			
			if (aunipValue!=null)
				aunipValue.style.visibility="hidden";
			
			if(containerMultiorganismo != null)
				containerMultiorganismo.style.display="block";
			
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

			fcmProjectKey.value="";
			fcmProjectKey.disabled="disabled";
			fcmProjectKey.style.visibility="hidden";
			fcmProjectKey.style.display="none";

			fcmProjectKeyLabel.value="";
			fcmProjectKeyLabel.disabled="disabled";
			fcmProjectKeyLabel.style.visibility="hidden";
			fcmProjectKeyLabel.style.display="none";

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

			vapidPublicKey.disabled= "";
			vapidPublicKey.style.visibility="hidden";
			vapidPublicKey.style.display="none";
			vapidPublicKeyLabel.disabled= "";
			vapidPublicKeyLabel.style.visibility="hidden";
			vapidPublicKeyLabel.style.display="none";

			vapidPrivateKey.disabled= "";
			vapidPrivateKey.style.visibility="hidden";
			vapidPrivateKey.style.display="none";
			vapidPrivateKeyLabel.disabled= "";
			vapidPrivateKeyLabel.style.visibility="hidden";
			vapidPrivateKeyLabel.style.display="none";

			caducidadWebPushLabel.disabled= "";
			caducidadWebPushLabel.style.visibility="hidden";
			caducidadWebPushLabel.style.display="none";
			caducidadWebPush.disabled="";
			caducidadWebPush.style.visibility="hidden";
			caducidadWebPush.style.display="none";
			
			botonClaves.disabled= "";
			botonClaves.style.visibility="hidden";
			botonClaves.style.display="none";

			exclusivoLabel.style.display="";

			if (document.getElementById("servicio.isPremium") != null){
				document.getElementById("servicio.isPremium").disabled = false;
				}
			if (document.getElementById("servicio.isPremium") != null){
				document.getElementById("servicio.isExclusivo").disabled = false;
			}			

		}
		if(combo.value=="2"){//SMS
			if (auna!=null)
				auna.innerHTML="Header";
			if (aune!=null)
				aune.innerHTML="Usuario";
			if (auni!=null)
				auni.innerHTML="Organismo";
			if (auno!=null)
				auno.innerHTML="";
			if (aunip!=null)
				aunip.innerHTML="Organismo";
			if (aunipValue!=null)
				aunipValue.style.visibility="visible";
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

				pBck=prefijoSMS.value;
				prefijoSMS.value="";
				prefijoSMS.disabled="disabled";
				prefijoSMS.style.visibility="hidden";
				prefijoSMS.style.display="none";
				prefijoSMSLabel.value="";
				prefijoSMSLabel.disabled="disabled";
				prefijoSMSLabel.style.visibility="hidden";
				prefijoSMSLabel.style.display="none";	
				
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

			if(nleBck!=""){
				nombreLoteEnvio.value=nleBck;
			}
			nombreLoteEnvio.disabled="";
			nombreLoteEnvio.style.visibility="hidden";
			nombreLoteEnvio.style.display="none";
			
			nombreLoteEnvioLabel.disabled="";
			nombreLoteEnvioLabel.style.visibility="hidden";
			nombreLoteEnvioLabel.style.display="none";

			if(endPointBck!=""){
				nombreEndPoint.value=endPointBck;
			}
			nombreEndPoint.disabled="";
			nombreEndPoint.style.visibility="hidden";
			nombreEndPoint.style.display="none";
			
			nombreEndPointLabel.disabled="";
			nombreEndPointLabel.style.visibility="hidden";
			nombreEndPointLabel.style.display="none";

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

			fcmProjectKey.value="";
			fcmProjectKey.disabled="disabled";
			fcmProjectKey.style.visibility="hidden";
			fcmProjectKey.style.display="none";

			fcmProjectKeyLabel.value="";
			fcmProjectKeyLabel.disabled="disabled";
			fcmProjectKeyLabel.style.visibility="hidden";
			fcmProjectKeyLabel.style.display="none";

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

			vapidPublicKey.disabled= "";
			vapidPublicKey.style.visibility="hidden";
			vapidPublicKey.style.display="none";
			vapidPublicKeyLabel.disabled= "";
			vapidPublicKeyLabel.style.visibility="hidden";
			vapidPublicKeyLabel.style.display="none";

			vapidPrivateKey.disabled= "";
			vapidPrivateKey.style.visibility="hidden";
			vapidPrivateKey.style.display="none";
			vapidPrivateKeyLabel.disabled= "";
			vapidPrivateKeyLabel.style.visibility="hidden";
			vapidPrivateKeyLabel.style.display="none";

			caducidadWebPushLabel.disabled= "";
			caducidadWebPushLabel.style.visibility="hidden";
			caducidadWebPushLabel.style.display="none";
			caducidadWebPush.disabled="";
			caducidadWebPush.style.visibility="hidden";
			caducidadWebPush.style.display="none";
			
			botonClaves.disabled= "";
			botonClaves.style.visibility="hidden";
			botonClaves.style.display="none";

			exclusivoLabel.style.display="";
			
			if (document.getElementById("servicio.isPremium") != null){
				document.getElementById("servicio.isPremium").disabled = false;
				}
			if (document.getElementById("servicio.isPremium") != null){
				document.getElementById("servicio.isExclusivo").disabled = false;
			}			
		}
		if(combo.value=="3"){//Recepcion SMS
			if (auna!=null)
				auna.innerHTML="Header";
			if (aune!=null)
				aune.innerHTML="";
			if (auni!=null)
				auni.innerHTML="";
			if (auno!=null)
				auno.innerHTML="Prefijo SMS";
			if (aunip!=null)
				aunip.innerHTML="";
			if (aunaValue!=null)
				aunaValue.style.visibility="visible";
			if (aunoValue!=null)
				aunoValue.style.visibility="visible";
			if (aunipValue!=null)
				aunipValue.style.visibility="hidden";
			
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

				pBck = prefijoSMS.value;
				prefijoSMS.disabled="";
				prefijoSMS.style.visibility="visible";
				prefijoSMS.style.display ="block";
				prefijoSMSLabel.disabled="";
				prefijoSMSLabel.style.visibility="visible";
				prefijoSMSLabel.style.display ="block";
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

			fcmProjectKey.value="";
			fcmProjectKey.disabled="disabled";
			fcmProjectKey.style.visibility="hidden";
			fcmProjectKey.style.display="none";

			fcmProjectKeyLabel.value="";
			fcmProjectKeyLabel.disabled="disabled";
			fcmProjectKeyLabel.style.visibility="hidden";
			fcmProjectKeyLabel.style.display="none";

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

			vapidPublicKey.disabled= "";
			vapidPublicKey.style.visibility="hidden";
			vapidPublicKey.style.display="none";
			vapidPublicKeyLabel.disabled= "";
			vapidPublicKeyLabel.style.visibility="hidden";
			vapidPublicKeyLabel.style.display="none";

			vapidPrivateKey.disabled= "";
			vapidPrivateKey.style.visibility="hidden";
			vapidPrivateKey.style.display="none";
			vapidPrivateKeyLabel.disabled= "";
			vapidPrivateKeyLabel.style.visibility="hidden";
			vapidPrivateKeyLabel.style.display="none";

			caducidadWebPushLabel.disabled= "";
			caducidadWebPushLabel.style.visibility="hidden";
			caducidadWebPushLabel.style.display="none";
			caducidadWebPush.disabled="";
			caducidadWebPush.style.visibility="hidden";
			caducidadWebPush.style.display="none";
			
			botonClaves.disabled= "";
			botonClaves.style.visibility="hidden";
			botonClaves.style.display="none";

			exclusivoLabel.style.display="none";
			if (document.getElementById("servicio.isPremium") != null){
				document.getElementById("servicio.isPremium").disabled = true;
				}
			if (document.getElementById("servicio.isPremium") != null){
				document.getElementById("servicio.isExclusivo").disabled = true;
			}
			
		}
		if(combo.value=="4"){//Notificaciones Push

			if (auna!=null)
				auna.innerHTML="";
			if (aune!=null)
				aune.innerHTML="";
			if (auni!=null)
				auni.innerHTML="";
			if (auno!=null)
				auno.innerHTML="";
			if (aunip!=null)
				aunip.innerHTML="";

			if (aunipValue!=null)
				aunipValue.style.visibility="hidden";
			
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

			fcmProjectKey.disabled="";
			fcmProjectKey.style.visibility="visible";
			fcmProjectKey.style.display="block";

			fcmProjectKeyLabel.disabled="";
			fcmProjectKeyLabel.style.visibility="visible";
			fcmProjectKeyLabel.style.display="block";

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

				pBck=prefijoSMS.value;
				prefijoSMS.value="";
				prefijoSMS.disabled="disabled";
				prefijoSMS.style.visibility="hidden";
				prefijoSMS.style.display="none";
				prefijoSMSLabel.value="";
				prefijoSMSLabel.disabled="disabled";
				prefijoSMSLabel.style.visibility="hidden";
				prefijoSMSLabel.style.display="none";	
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

			vapidPublicKey.disabled= "";
			vapidPublicKey.style.visibility="hidden";
			vapidPublicKey.style.display="none";
			vapidPublicKeyLabel.disabled= "";
			vapidPublicKeyLabel.style.visibility="hidden";
			vapidPublicKeyLabel.style.display="none";

			vapidPrivateKey.disabled= "";
			vapidPrivateKey.style.visibility="hidden";
			vapidPrivateKey.style.display="none";
			vapidPrivateKeyLabel.disabled= "";
			vapidPrivateKeyLabel.style.visibility="hidden";
			vapidPrivateKeyLabel.style.display="none";

			caducidadWebPushLabel.disabled= "";
			caducidadWebPushLabel.style.visibility="hidden";
			caducidadWebPushLabel.style.display="none";
			caducidadWebPush.disabled="";
			caducidadWebPush.style.visibility="hidden";
			caducidadWebPush.style.display="none";
			
			botonClaves.disabled= "";
			botonClaves.style.visibility="hidden";
			botonClaves.style.display="none";

			exclusivoLabel.style.display="none";
			if (document.getElementById("servicio.isPremium") != null){
				document.getElementById("servicio.isPremium").disabled = true;
				}
			if (document.getElementById("servicio.isPremium") != null){
				document.getElementById("servicio.isExclusivo").disabled = true;
			}			

		}
		if(combo.value=="5"){//web push

			if (auna!=null)
				auna.innerHTML="";
			if (aune!=null)
				aune.innerHTML="";
			if (auni!=null)
				auni.innerHTML="";
			if (auno!=null)
				auno.innerHTML="";
			if (aunip!=null)
				aunip.innerHTML="";
			
			
			
			if (aunipValue!=null)
				aunipValue.style.visibility="hidden";
			
			if(containerMultiorganismo != null)
				containerMultiorganismo.style.display="block";
			
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

			fcmProjectKey.value="";
			fcmProjectKey.disabled="disabled";
			fcmProjectKey.style.visibility="hidden";
			fcmProjectKey.style.display="none";

			fcmProjectKeyLabel.value="";
			fcmProjectKeyLabel.disabled="disabled";
			fcmProjectKeyLabel.style.visibility="hidden";
			fcmProjectKeyLabel.style.display="none";

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
			fromMail.style.display="none";
			fromMail.style.visibility="hidden";
			fromMailLabel.disabled="";
			fromMailLabel.style.display="hidden";
			fromMailLabel.style.visibility="none";
			
			
			if(fmnBck!=""){
				fromMailName.value=fmnBck;
			}
			fromMailName.disabled="";
			fromMailName.style.visibility="hidden";
			fromMailName.style.display="none";
			fromMailNameLabel.disabled="";
			fromMailNameLabel.style.visibility="hidden";
			fromMailNameLabel.style.display="none";

			
			agrupacionCodOrgLabel.style.visibility="";
			agrupacionCodSiaLabel.style.visibility="";
			agrupacionCodOrgPagadorLabel.style.visibility="hidden";

			vapidPublicKey.disabled= "true";
			vapidPublicKey.style.visibility="visible";
			vapidPublicKey.style.display="block";
			vapidPublicKeyLabel.disabled= "";
			vapidPublicKeyLabel.style.visibility="visible";
			vapidPublicKeyLabel.style.display="block";

			vapidPrivateKey.disabled= "true";
			vapidPrivateKey.style.visibility="visible";
			vapidPrivateKey.style.display="block";
			vapidPrivateKeyLabel.disabled= "";
			vapidPrivateKeyLabel.style.visibility="visible";
			vapidPrivateKeyLabel.style.display="block";

			caducidadWebPushLabel.disabled= "";
			caducidadWebPushLabel.style.visibility="visible";
			caducidadWebPushLabel.style.display="block";
			caducidadWebPush.disabled="";
			caducidadWebPush.style.visibility="visible";
			caducidadWebPush.style.display="block";
			
			botonClaves.disabled= "";
			botonClaves.style.visibility="visible";
			botonClaves.style.display="block";

			exclusivoLabel.style.display="none";

			if (document.getElementById("servicio.isPremium") != null){
				document.getElementById("servicio.isPremium").disabled = true;
				}
			if (document.getElementById("servicio.isPremium") != null){
				document.getElementById("servicio.isExclusivo").disabled = true;
			}			
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
		var urlFeedback= document.getElementById("servidorPush.urlfeedback");
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


	function fncDesplegarMenu(intIndiceMenu) {
		
		var deplegable= document.getElementById("desplegable"+intIndiceMenu+"");
		
		if(deplegable.style.display == ""){
			deplegable.style.display='none';
		}else if(deplegable.style.display == "block"){
			deplegable.style.display='none';
		}else{
			deplegable.style.display='block';
		}

	}

</script>
