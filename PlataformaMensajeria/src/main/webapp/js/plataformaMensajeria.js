                function nuevoAjax()
                { 
                	/* Crea el objeto AJAX. Esta funcion es generica para cualquier utilidad de este tipo, por
                	lo que se puede copiar tal como esta aqui */
                	var xmlhttp=false;
                	try
                	{
                		// Creacion del objeto AJAX para navegadores no IE
                		xmlhttp=new ActiveXObject("Msxml2.XMLHTTP");
                	}
                	catch(e)
                	{
                		try
                		{
                			// Creacion del objet AJAX para IE
                			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
                		}
                		catch(E)
                		{
                			if (!xmlhttp && typeof XMLHttpRequest!='undefined') xmlhttp=new XMLHttpRequest();
                		}
                	}
                	return xmlhttp; 
                }

                // Declaro los selects que componen el documento HTML. Su atributo ID debe figurar aqui.
                var listadoSelects=new Array();
                listadoSelects[0]="planificacion.servicioId";
                listadoSelects[1]="planificacion.tipoPlanificacionIdSelect";
                listadoSelects[2]="planificacion.servidorIdSelect";

                function buscarEnArray(array, dato)
                {
                	// Retorna el indice de la posicion donde se encuentra el elemento en el array o null si no se encuentra
                	var x=0;
                	while(array[x])
                	{
                		if(array[x]==dato) return x;
                		x++;
                	}
                	return null;
                }

                function cargaContenido(idSelectOrigen)
                {
                	var posicionSelectDestino=buscarEnArray(listadoSelects, idSelectOrigen)+1;
                	var selectOrigen=document.getElementById(idSelectOrigen);
                	var opcionSeleccionada=selectOrigen.options[selectOrigen.selectedIndex].value;
                	if(opcionSeleccionada==-1)
                	{
                		var x=posicionSelectDestino, selectActual=null;
                		while(listadoSelects[x])
                		{
                			selectActual=document.getElementById(listadoSelects[x]);
                			selectActual.length=0;
                			
                			var nuevaOpcion=document.createElement("option"); nuevaOpcion.value=0; nuevaOpcion.innerHTML="Selecciona Opci&oacute;n...";
                			selectActual.appendChild(nuevaOpcion);	selectActual.disabled=true;
                			x++;
                		}
                	}
                	else if(idSelectOrigen!=listadoSelects[listadoSelects.length-1])
                	{
                		var idSelectDestino=listadoSelects[posicionSelectDestino];
                		var selectDestino=document.getElementById(idSelectDestino);
                		var ajax=nuevoAjax();
                		if(idSelectOrigen=="planificacion.tipoPlanificacionIdSelect"){
                			ajax.open("GEST", "loadServiroresByTipoPlan.action?tipoPlanificacionId="+document.getElementById("planificacion.tipoPlanificacionIdSelect").value, true);
                			
                		}else if(idSelectOrigen=="planificacion.servicioId"){
                			ajax.open("GEST", "loadTipoPlanificacionByServicioCanal.action?servicioId="+document.getElementById("planificacion.servicioId").value, true);
                		}
                		ajax.onreadystatechange=function() 
                		{ 
                			if (ajax.readyState==1)
                			{
                				selectDestino.length=0;
                				if(idSelectDestino=="planificacion.tipoPlanificacionIdSelect"){
                					var selectDestinoFi = document.getElementById("planificacion.servidorIdSelect");
                					selectDestinoFi.length=0;
                					var nuevaOpcion=document.createElement("option"); nuevaOpcion.value=0; nuevaOpcion.innerHTML="Seleccione un tipo...";
                					selectDestinoFi.appendChild(nuevaOpcion); selectDestinoFi.disabled=true;	
                				}
                				var nuevaOpcion=document.createElement("option"); nuevaOpcion.value=0; nuevaOpcion.innerHTML="Cargando...";
                				selectDestino.appendChild(nuevaOpcion); selectDestino.disabled=true;	
                			}
                			if (ajax.readyState==4)
                			{
                				selectDestino.parentNode.innerHTML=ajax.responseText;
                			} 
                		}
                		ajax.send(null);
                	}
                }
                
                
                function loadValueServidorId(){
                	var valor = document.getElementById("planificacion.servidorIdSelect").value;
                	var var2 = document.getElementById("planificacion.servidorId");
                	var2.value=valor;
                	var valor2 = document.getElementById("planificacion.tipoPlanificacionId").value;
                	var varTipoPlan = document.getElementById("planificacion.tipoPlanificacionId");
                	varTipoPlan.value=valor2;

                	return true;
                }