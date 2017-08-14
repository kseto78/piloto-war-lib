
var isNN = (navigator.appName.indexOf("Netscape")!=-1);

// Funcion que ejecuta el submit del formulario pasado por parametro
function enviar(formulario) {
	document.getElementById(formulario).submit();
}

// Funcion que sustituye el literal comilla por barra y comilla  
 function parser(cadena) {

    tamano = 0;
    resultado = "";
    while (tamano <= cadena.length) {
      if (cadena.charAt(tamano) == "'") {
        resultado = resultado + "\\'";
      } else {
        resultado = resultado + cadena.charAt(tamano);
    }

    tamano = tamano +1;

    }
    return resultado;
  }



// Funcion que ubica el foco en el primer componente en el que se puedan entrar datos
function placeFocus() {
	var focus = false;

	if (document.forms.length > 0) {
		for (i = 0; i< document.forms.length; i++) {
			if (focus == true) break;
			var field = document.forms[i];
			for (j = 0; j < field.length; j++) {
				//if ((field.elements[j].type == "text") || (field.elements[j].type == "textarea") || (field.elements[j].type.toString().charAt(0) == "s")) {
				if (field.elements[j].type != "hidden") {
					if (field.elements[j].enabled) {
						document.forms[i].elements[j].focus();
						focus = true;
						break;
					}
				}
			}
		}
	}
}

//============= Funcion que pasa automaticament al siguiente campo =======================
// Ejemplo: <input onKeyUp="return autoTab(this, 3, event);" size="4" maxlength="3">
//========================================================================================

function autoTab(input,len, e) {
	var keyCode = (isNN) ? e.which : e.keyCode; 
	var filter = (isNN) ? [0,8,9] : [0,8,9,16,17,18,37,38,39,40,46];
	if(input.value.length >= len && !containsElement(filter,keyCode)) {
	input.value = input.value.slice(0, len);
	input.form[(getIndex(input)+1) % input.form.length].focus();
}



function containsElement(arr, ele) {
	var found = false, index = 0;
	while(!found && index < arr.length)
	if(arr[index] == ele)
	found = true;
	else
	index++;
	return found;
}

function getIndex(input) {
	var index = -1, i = 0, found = false;
	while (i < input.form.length && index == -1)
	if (input.form[i] == input)index = i;
	else i++;
	return index;
}
return true;
}




//========================================================================================
// UTILES CONVERSION
//========================================================================================


// Funcion que elimina los blancos a izquierda y derecha. Adem?s elimina los
// espacios consecutivos y deja solo uno

function trim(inputString) {
   if (typeof inputString != "string") { return inputString; }
   var retValue = inputString;
   var ch = retValue.substring(0, 1);
   while (ch == " ") { // Check for spaces at the beginning of the string
      retValue = retValue.substring(1, retValue.length);
      ch = retValue.substring(0, 1);
   }
   ch = retValue.substring(retValue.length-1, retValue.length);
   while (ch == " ") { // Check for spaces at the end of the string
      retValue = retValue.substring(0, retValue.length-1);
      ch = retValue.substring(retValue.length-1, retValue.length);
   }
   while (retValue.indexOf("  ") != -1) { // Note that there are two spaces in the string - look for multiple spaces within the string
      retValue = retValue.substring(0, retValue.indexOf("  ")) + retValue.substring(retValue.indexOf("  ")+1, retValue.length); // Again, there are two spaces in each of the strings
   }
   return retValue;
} 


function replaceSubstring(inputString, fromString, toString) {
   // Goes through the inputString and replaces every occurrence of fromString with toString
   var temp = inputString;
   if (fromString == "") {
      return inputString;
   }
   if (toString.indexOf(fromString) == -1) { // If the string being replaced is not a part of the replacement string (normal situation)
      while (temp.indexOf(fromString) != -1) {
         var toTheLeft = temp.substring(0, temp.indexOf(fromString));
         var toTheRight = temp.substring(temp.indexOf(fromString)+fromString.length, temp.length);
         temp = toTheLeft + toString + toTheRight;
      }
   } else { // String being replaced is part of replacement string (like "+" being replaced with "++") - prevent an infinite loop
      var midStrings = new Array("~", "`", "_", "^", "#");
      var midStringLen = 1;
      var midString = "";
      // Find a string that doesn't exist in the inputString to be used
      // as an "inbetween" string
      while (midString == "") {
         for (var i=0; i < midStrings.length; i++) {
            var tempMidString = "";
            for (var j=0; j < midStringLen; j++) { tempMidString += midStrings[i]; }
            if (fromString.indexOf(tempMidString) == -1) {
               midString = tempMidString;
               i = midStrings.length + 1;
            }
         }
      } // Keep on going until we build an "inbetween" string that doesn't exist
      // Now go through and do two replaces - first, replace the "fromString" with the "inbetween" string
      while (temp.indexOf(fromString) != -1) {
         var toTheLeft = temp.substring(0, temp.indexOf(fromString));
         var toTheRight = temp.substring(temp.indexOf(fromString)+fromString.length, temp.length);
         temp = toTheLeft + midString + toTheRight;
      }
      // Next, replace the "inbetween" string with the "toString"
      while (temp.indexOf(midString) != -1) {
         var toTheLeft = temp.substring(0, temp.indexOf(midString));
         var toTheRight = temp.substring(temp.indexOf(midString)+midString.length, temp.length);
         temp = toTheLeft + toString + toTheRight;
      }
   } // Ends the check to see if the string being replaced is part of the replacement string or not
   return temp; // Send the updated string back to the user
} // Ends the "replaceSubstring" function



function numberOfOcurrences(inputString,pattern) {		
	var matches = inputString.match(pattern);	
	if (matches!=null) return matches.length; 
	else  return 0;
}


function convert2Double(inputString,decimalPoint,decimalCount) {
	var replaced = false;
	var replacedString = inputString;
	// Esta variable almacena el valor con decimal de javascript
	var decimalPointFound;
	
	var patternDecimalPointArray = new Array(new RegExp(",","g"),new RegExp("\\.","g"));
	var decimalPointArray = new Array(",",".");
	for (var iCnt=0;iCnt<2;iCnt++) {	
		if (numberOfOcurrences(inputString,patternDecimalPointArray[iCnt])==1) {
			decimalPointFound = decimalPointArray[iCnt];
			replacedString = replaceSubstring(inputString,decimalPointArray[iCnt],decimalPoint);
			replaced = true;
		} 
	}
	
	if (replaced==true) {
		
	
		// De nuevo hay que convertir el replacedString en uno en el que el decimal
		// se sustituya por '.'
		replacedString = replaceSubstring(replacedString,decimalPoint,".");						
		var decimalPow = Math.pow(10,decimalCount);		
		var decimalNumber = parseFloat(replacedString);
		
		decimalNumber=Math.round(decimalNumber*decimalPow)/decimalPow;
		var decimalNumberStr = decimalNumber + '';
		replacedString = replaceSubstring(decimalNumberStr,".",decimalPoint);				
		return replacedString;
	} else {
		return inputString;
	}	
}


//========================================================================================
// Contador de caracteres (uso en textarea)
//========================================================================================

function checkTextAreaLines(textArea,minRows) {
 var content = textArea.value;
 var lines = content.split('\n');
 

 if (lines.length>minRows) {
 	textArea.rows = lines;
 }
}


function textCounter(field,cntfield,maxlimit) {	
	if (field.value.length > maxlimit) { // si demasiado largo, cortarlo
		field.value = field.value.substring(0, maxlimit);
	} // en caso contrario actualizar el contador de caracteres restantes
	else {
		cntfield.value = maxlimit - field.value.length;
	}
}


// Funcion equivalente a la anterior, pero no actualiza el contador
function textCounterWithoutChange(field,maxlimit) {
	if (field.value.length > maxlimit) { // si demasiado largo, cortarlo
		field.value = field.value.substring(0, maxlimit);
	}
}




//========================================================================================
// Funciones ventana popup
//========================================================================================
function openPopupWindow (form,inputName,popup,width,height,e) {        
	var xx, yy;
	xx = e.screenX;
	yy = e.screenY;
	
	var openerForm,openerInput;
	
	if (form.openerForm) { 
	alert('1'+form.openerForm.value);
		// Guardamos los valores anteriores
		openerForm = form.openerForm.value;
		openerInput = form.openerInput.value;
	alert('2'+form.name);		
		form.openerForm.value= form.name;
	 	form.openerInput.value= inputName; 
	}

	//No le pasamos el top y el left, mejor que salga centrada la ventana
	//window.open('about:blank', 'popup', 'directories=0, location=0, menubar=0, status=0, toolbar=0, width=' + width + ', height=' + height + ', top=' + yy + ', left=' + xx);
	window.open('about:blank', 'popup', 'directories=0, location=0, menubar=0, status=0, toolbar=0, width=' + width + ', height=' + height);
	var action = form.action;
	var target = form.target;
	
	if (popup == null || popup == "") {
		popup = action;
	}
	
	form.target='popup';
	
        
	form.action = popup;
	form.submit();
        
    // reinicializar el target y action a los iniciales
	form.target = target;
	form.action = action;
	if (form.openerForm) { 
		form.openerForm.value= openerForm;
	 	form.openerInput.value= openerInput; 
	}
	
	return false;
}
        
function closePopupWindow(formName,inputName,value) {
	var openerForm;
	var openerInput;		
	
	openerForm = window.opener.document.forms[formName];
	openerInput = openerForm.elements[inputName];
	openerInput.value = value;
	window.close();
}


//========================================================================================
// Funciones para b?squeda autoincremental en un select
//========================================================================================

w = "";

function keyp(e,input) {
	if (document.getElementById&&!document.all) {
		k = e.which;
	} else if (document.all) {
		k = window.event.keyCode;
	}
	
	if (k==46) {
		if (w.length!=0) {
			w = w.substring(0,w.length-1);
		}
	} else{
		w += String.fromCharCode(k).toLowerCase();
	}
	
	for (x=0; x<input.options.length; x++) {
		z = input.options[x].text.substring(0,w.length).toLowerCase();
		if (w==z) {
			input.options[x].selected=true;
			q = false;
			break;
		} else{
			q = true;
		}
	}
	if (q) {
		w=w.substring(0,w.length-1);
	}
}


//========================================================================================
// Funciones para validaci?n de tipos de ficheros
//========================================================================================

// Comprobacion de qu? ficheros se pueden descargar
function TestFileType( fileName, fileTypes) {
	if (!fileName) return;

	dots = fileName.split(".")
	//get the part AFTER the LAST period.
	fileType = "." + dots[dots.length-1];

	return (fileTypes.join(".").indexOf(fileType) != -1) ?
	alert('That file is OK!') : 
	alert("Please only upload files that end in types: \n\n" + (fileTypes.join(" .")) + "\n\nPlease select a new file and try again.");
}


// Funcion que devuelve el nombre del fichero sin el path
function extract(what) {
    if (what.indexOf('/') > -1)
        answer = what.substring(what.lastIndexOf('/')+1,what.length);
    else
        answer = what.substring(what.lastIndexOf('\\')+1,what.length);
    alert(answer);
}



function isInteger(sText)
{
   var ValidChars = "0123456789";
   var numberOK=true;
   var Char;

 
   for (i = 0; i < sText.length && numberOK == true; i++) 
      { 
      Char = sText.charAt(i); 
      if (ValidChars.indexOf(Char) == -1) 
         {
         numberOK = false;
         }
      }
   return numberOK;
   
}


//========================================================================================
// Funcion para agregar scripts din?micamente al onload
//========================================================================================

function addLoadEvent(func) {
  var oldonload = window.onload;
  if (typeof window.onload != 'function') {
    window.onload = func;
  } else {
    window.onload = function() {
      oldonload();
      func();
    }
  }
}
