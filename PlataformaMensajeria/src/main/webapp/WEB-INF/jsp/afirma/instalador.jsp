<html>
	<head>
	<link rel="stylesheet" href="../css/afirma.css" type="text/css">
		<script type="text/javascript" language="javascript" src="js/afirma/common-js/deployJava.js"></script>
		<script type="text/javascript" language="javascript" src="js/afirma/common-js/instalador.js"></script>
		<script type="text/javascript" language="javascript" src="js/afirma/common-js/time.js"></script>
		<script type="text/javascript" language="javascript" src="js/afirma/constantes.js"></script>
		<script language="javascript">

			function instalarCliente(build) {
				if(!instalar(build))
					alert('Ocurrio un error durante el proceso de instalacion');
			}

		</script>
	</head>

<body>
		<script type="text/javascript">
			cargarAppletInstalador();
		</script>
<table align="center">
	<tr>
		<td>
		
		<table>
			<tr>
				<td>
				<fieldset><legend>Operaciones del instalador</legend>
				<a href="#" onclick="alert(isInstalado()); return false;">Comprobar construccion por defecto</a><br>
				<a href="#" onclick="alert(isInstalado('LITE')); return false;">Comprobar instalación (Construcción LITE)</a><br>
				<a href="#" onclick="alert(isInstalado('MEDIA')); return false;">Comprobar instalación (Construcción MEDIA)</a><br>
				<a href="#" onclick="alert(isInstalado('COMPLETA')); return false;">Comprobar instalación (Construcción COMPLETA)</a><br>
				<a href="#" onclick="instalarCliente(); return false;">Instalar construcción por defecto</a><br>
				<a href="#" onclick="instalarCliente('LITE'); return false;">Instalar construcción LITE del applet de firma</a><br>
				<a href="#" onclick="instalarCliente('MEDIA'); return false;">Instalar construcción MEDIA del applet de firma</a><br>
				<a href="#" onclick="instalarCliente('COMPLETA'); return false;">Instalar construcción COMPLETA del applet de firma</a><br>
				<a href="#" onclick="desinstalar(); return false;">Desinstalar applet de firma</a><br>
				<a href="#" onclick="alert(isActualizado()); return false;">Comprobar si el cliente esta actualizado</a><br>
				<a href="#" onclick="actualizar(); return false;">Actualizar applet de firma (mantener construcción)</a><br>
				<a href="#" onclick="alert(getVersionCliente()); return false;">Versión instalada del cliente</a><br>
				<a href="#" onclick="alert(getVersion()); return false;">Versión del instalador</a><br>
				<a href="#" onclick="alert(getDirectorioInstalacion()); return false;">Directorio de instalación</a><br>
				<br>
				</fieldset>
				</td>
			</tr>
		</table>
		
		</td>
	</tr>
</table>

</body>
</html>