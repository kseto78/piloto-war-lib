/* Spanish translation for the jQuery Timepicker Addon */
/* Written by Ianaré Sévi */
/* Modified by Carlos Martínez */
(function($) {
	$.timepicker.regional['es'] = {
		timeOnlyTitle: 'Elegir una hora',
		timeText: 'Hora',
		hourText: 'Horas',
		minuteText: 'Minutos',
		secondText: 'Segundos',
		millisecText: 'Milisegundos',
		microsecText: 'Microsegundos',
		timezoneText: 'Uso horario',
		currentText: 'Ahora',
		closeText: 'Cerrar',
		timeFormat: 'HH:mm',
		timeSuffix: '',
		monthNames:["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"],
		monthNamesShort:["Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"],
		dayNames:["Domingo","Lunes","Martes","Mi�rcoles","Jueves","Viernes","Sabado"],
		dayNamesShort:["Dom","Lun","Mar","Mie","Jue","Vie","Sab"],
		dayNamesMin:["Do","Lu","Ma","Mi","Ju","Vi","Sa"],
		dateFormat: "dd/mm/yy",
		amNames: ['a.m.', 'AM', 'A'],
		pmNames: ['p.m.', 'PM', 'P'],
		isRTL: false
	};
	$.timepicker.setDefaults($.timepicker.regional['es']);
})(jQuery);

(function($) {
	$.datepicker.regional['es2'] = {
		monthNames:["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"],
		monthNamesShort:["Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"],
		dayNames:["Domingo","Lunes","Martes","Mi�rcoles","Jueves","Viernes","Sabado"],
		dayNamesShort:["Dom","Lun","Mar","Mie","Jue","Vie","Sab"],
		dayNamesMin:["Do","Lu","Ma","Mi","Ju","Vi","Sa"],
		dateFormat: "dd/mm/yy",
		amNames: ['a.m.', 'AM', 'A'],
		pmNames: ['p.m.', 'PM', 'P'],
		isRTL: false
	};
	$.datepicker.setDefaults($.datepicker.regional['es2']);
})(jQuery);