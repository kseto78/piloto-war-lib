<?xml version="1.0"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
	xmlns:ns1="https://administracionelectronica.gob.es/notifica/ws/notifica/1.0/"
	xmlns:pet3="http://intermediacion.redsara.es/scsp/esquemas/V3/peticion"
	xmlns:res3="http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta"
	xmlns:dat3="http://intermediacion.redsara.es/scsp/esquemas/datosespecificos"
	exclude-result-prefixes="ns1 pet3">

	<xsl:output indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />
	
	<xsl:template match="/">
		<soapenv:Envelope>
		<soapenv:Header />
			<soapenv:Body>
				<xsl:apply-templates />
			</soapenv:Body>
		</soapenv:Envelope>
	</xsl:template>
	
	<xsl:template match="pet3:Peticion">
		<res3:Respuesta>
			<xsl:apply-templates />
		</res3:Respuesta>
	</xsl:template>
	
	<!-- Plantillas elementos Atributos -->
	<xsl:template match="pet3:Atributos">
		<res3:Atributos>
			<xsl:apply-templates />
		</res3:Atributos>
	</xsl:template>
	
	<xsl:template match="pet3:IdPeticion">
		<res3:IdPeticion>
			<xsl:apply-templates />
		</res3:IdPeticion>
	</xsl:template>

	<xsl:template match="pet3:NumElementos">
		<res3:NumElementos>
			<xsl:value-of select="." />
		</res3:NumElementos>
	</xsl:template>

	<xsl:template match="pet3:TimeStamp">
		<res3:TimeStamp>
			<xsl:value-of select="." />
		</res3:TimeStamp>
	</xsl:template>
	
	<xsl:template match="pet3:Estado">
		<res3:Estado>
			<xsl:apply-templates />
		</res3:Estado>
	</xsl:template>

	<xsl:template match="pet3:Atributos/pet3:CodigoCertificado">
		<res3:CodigoCertificado>
			<xsl:value-of select="." />
		</res3:CodigoCertificado>
	</xsl:template>
	<!-- Plantillas elementos Atributos -->
	
	<!-- Plantilla elementos Estado -->	
	<xsl:template match="pet3:CodigoEstado">
		<xsl:apply-templates select="//ns1:infoEnvioResponse/return/codigo_respuesta"/>
	</xsl:template>
	
	<xsl:template match="//ns1:infoEnvioResponse/return/codigo_respuesta">
		<res3:CodigoEstado>
			<xsl:value-of select="." />
		</res3:CodigoEstado>
	</xsl:template>

	<xsl:template match="pet3:CodigoEstadoSecundario">
		<res3:CodigoEstadoSecundario>
			<xsl:value-of select="." />
		</res3:CodigoEstadoSecundario>
	</xsl:template>
	
	<xsl:template match="pet3:LiteralError">
			<xsl:apply-templates select="//ns1:infoEnvioResponse/return/descripcion_respuesta"/>
	</xsl:template>
	
	<xsl:template match="//ns1:infoEnvioResponse/return/descripcion_respuesta">
		<res3:LiteralError>
			<xsl:value-of select="." />
		</res3:LiteralError>
	</xsl:template>

	<xsl:template match="pet3:TiempoEstimadoRespuesta">
		<res3:TiempoEstimadoRespuesta>
			<xsl:value-of select="." />
		</res3:TiempoEstimadoRespuesta>
	</xsl:template>
	<!-- Plantilla Estado -->
	
	<!-- Plantilla Transmisiones -->
	<xsl:template match="pet3:Solicitudes">
		<res3:Transmisiones>
			<xsl:apply-templates />
		</res3:Transmisiones>
	</xsl:template>

	<!-- Plantilla TransmisionDatos -->
	<xsl:template match="pet3:SolicitudTransmision">
		<res3:TransmisionDatos>
			<xsl:apply-templates select="//pet3:DatosGenericos" />
			<xsl:apply-templates select="//ns1:infoEnvioResponse/return/infoEnvio" />
		</res3:TransmisionDatos>
	</xsl:template>
	
	<!-- Plantilla DatosGenericos -->
	<xsl:template match="//pet3:DatosGenericos">
		<res3:DatosGenericos>
			<xsl:apply-templates />
		</res3:DatosGenericos>
	</xsl:template>
	
	<!-- Plantilla elementos Emisor -->
	<xsl:template match="pet3:Emisor">
		<res3:Emisor>
			<xsl:apply-templates />
		</res3:Emisor>
	</xsl:template>

	<xsl:template match="pet3:NombreEmisor">
		<res3:NombreEmisor>
			<xsl:value-of select="." />
		</res3:NombreEmisor>
	</xsl:template>

	<xsl:template match="pet3:NifEmisor">
		<res3:NifEmisor>
			<xsl:value-of select="." />
		</res3:NifEmisor>
	</xsl:template>
	<!-- Plantillas elementos Emisor -->
	
	<!-- Plantilla elementos Solicitante -->
	<xsl:template match="pet3:Solicitante">
		<res3:Solicitante>
			<xsl:apply-templates />
		</res3:Solicitante>
	</xsl:template>
	
	<xsl:template match="pet3:IdentificadorSolicitante">
		<res3:IdentificadorSolicitante>
			<xsl:value-of select="." />
		</res3:IdentificadorSolicitante>
	</xsl:template>
	
	<xsl:template match="pet3:NombreSolicitante">
		<res3:NombreSolicitante>
			<xsl:value-of select="." />
		</res3:NombreSolicitante>
	</xsl:template>
	
	<xsl:template match="pet3:UnidadTramitadora">
		<res3:UnidadTramitadora>
			<xsl:value-of select="." />
		</res3:UnidadTramitadora>
	</xsl:template>
	
	<xsl:template match="pet3:Procedimiento">
		<res3:Procedimiento>
			<xsl:apply-templates />
		</res3:Procedimiento>
	</xsl:template>
	
	<xsl:template match="pet3:CodProcedimiento">
		<res3:CodProcedimiento>
			<xsl:value-of select="." />
		</res3:CodProcedimiento>
	</xsl:template>
	
	<xsl:template match="pet3:NombreProcedimiento">
		<res3:NombreProcedimiento>
			<xsl:value-of select="." />
		</res3:NombreProcedimiento>
	</xsl:template>
	
	<xsl:template match="pet3:Finalidad">
		<res3:Finalidad>
			<xsl:value-of select="." />
		</res3:Finalidad>
	</xsl:template>
	
	<xsl:template match="pet3:Consentimiento">
		<res3:Consentimiento>
			<xsl:value-of select="." />
		</res3:Consentimiento>
	</xsl:template>
	
	<xsl:template match="pet3:Funcionario">
		<res3:Funcionario>
			<xsl:apply-templates />
		</res3:Funcionario>
	</xsl:template>
	
	<xsl:template match="pet3:NombreCompletoFuncionario">
		<res3:NombreCompletoFuncionario>
			<xsl:value-of select="." />
		</res3:NombreCompletoFuncionario>
	</xsl:template>
	
	<xsl:template match="pet3:NifFuncionario">
		<res3:NifFuncionario>
			<xsl:value-of select="." />
		</res3:NifFuncionario>
	</xsl:template>
	
	<xsl:template match="pet3:IdExpediente">
		<res3:IdExpediente>
			<xsl:value-of select="." />
		</res3:IdExpediente>
	</xsl:template>
	<!-- Plantilla elementos Solicitante -->
	
	<!-- Plantillas elementos Titular -->
	<xsl:template match="pet3:Titular">
		<res3:Titular>
			<xsl:apply-templates />
		</res3:Titular>
	</xsl:template>
	<xsl:template match="pet3:TipoDocumentacion">
		<res3:TipoDocumentacion>
			<xsl:value-of select="." />
		</res3:TipoDocumentacion>
	</xsl:template>
	<xsl:template match="pet3:Documentacion">
		<res3:Documentacion>
			<xsl:value-of select="." />
		</res3:Documentacion>
	</xsl:template>
	<xsl:template match="pet3:NombreCompleto">
		<res3:NombreCompleto>
			<xsl:value-of select="." />
		</res3:NombreCompleto>
	</xsl:template>
	<xsl:template match="pet3:Nombre">
		<res3:Nombre>
			<xsl:value-of select="." />
		</res3:Nombre>
	</xsl:template>
	<xsl:template match="pet3:Apellido1">
		<res3:Apellido1>
			<xsl:value-of select="." />
		</res3:Apellido1>
	</xsl:template>
	<xsl:template match="pet3:Apellido2">
		<res3:Apellido2>
			<xsl:value-of select="." />
		</res3:Apellido2>
	</xsl:template>
	<!-- Plantillas elementos Titular -->

	<!-- Plantilla elementos Transmision -->
	<xsl:template match="pet3:Transmision">
		<res3:Transmision>
			<xsl:apply-templates />
		</res3:Transmision>
	</xsl:template>
	
	<xsl:template match="pet3:Transmision/pet3:CodigoCertificado">
		<res3:CodigoCertificado>
			<xsl:value-of select="." />
		</res3:CodigoCertificado>
	</xsl:template>

	<xsl:template match="pet3:IdSolicitud">
		<res3:IdSolicitud>
			<xsl:value-of select="." />
		</res3:IdSolicitud>
	</xsl:template>

	<xsl:template match="pet3:IdTransmision">
		<res3:IdTransmision>
			<xsl:value-of select="." />
		</res3:IdTransmision>
	</xsl:template>

	<xsl:template match="pet3:FechaGeneracion">
		<res3:FechaGeneracion>
			<xsl:value-of select="." />
		</res3:FechaGeneracion>
	</xsl:template>
	<!-- Plantilla elementos Transmision -->
	
	<!-- Plantilla Datos Especificos -->
	<xsl:template match="dat3:DatosEspecificos">
	</xsl:template>
	
	<xsl:template match="//ns1:infoEnvioResponse/return/infoEnvio">
		<dat3:DatosEspecificos xmlns:dat3="http://intermediacion.redsara.es/scsp/esquemas/datosespecificos">
			<ns1:infoEnvioResponse>
				<return>
					<infoEnvio>
						<xsl:apply-templates />
					</infoEnvio>
				</return>
			</ns1:infoEnvioResponse>
		</dat3:DatosEspecificos>
	</xsl:template>
	
	<!-- Plantilla organismo_emisor -->
	<xsl:template match="organismo_emisor">
		<organismo_emisor>
			<xsl:apply-templates/>
		</organismo_emisor>
	</xsl:template>
	
	<xsl:template match="organismo_emisor/codigo_dir3">
		<codigo_dir3>
			<xsl:apply-templates/>
		</codigo_dir3>
	</xsl:template>
	
	<xsl:template match="organismo_emisor/nombre">
		<nombre>
			<xsl:apply-templates/>
		</nombre>
	</xsl:template>
	<!-- Plantilla organismo_emisor -->
	
	<!-- Plantilla organismo_pagador -->
	<xsl:template match="organismo_pagador">
		<organismo_pagador>
			<xsl:apply-templates/>
		</organismo_pagador>
	</xsl:template>
	
	<xsl:template match="organismo_pagador/codigo_dir3">
		<codigo_dir3>
			<xsl:apply-templates/>
		</codigo_dir3>
	</xsl:template>
	
	<xsl:template match="organismo_pagador/numero_contrato_correos">
		<numero_contrato_correos>
			<xsl:apply-templates/>
		</numero_contrato_correos>
	</xsl:template>
	
	<xsl:template match="organismo_pagador/codigo_cliente_facturacion_correos">
		<codigo_cliente_facturacion_correos>
			<xsl:apply-templates/>
		</codigo_cliente_facturacion_correos>
	</xsl:template>
	<!-- Plantilla organismo_pagador -->
	
	<!-- Plantilla documento -->
	<xsl:template match="documento">
		<documento>
			<xsl:apply-templates/>
		</documento>
	</xsl:template>
	
	<xsl:template match="documento/contenido">
		<contenido>
			<xsl:apply-templates/>
		</contenido>
	</xsl:template>
	
	<xsl:template match="documento/hash_sha1">
		<hash_sha1>
			<xsl:apply-templates/>
		</hash_sha1>
	</xsl:template>
	
	<xsl:template match="documento/normalizado">
		<normalizado>
			<xsl:apply-templates/>
		</normalizado>
	</xsl:template>
	<!-- Plantilla documento -->
	
	<!-- Plantilla tipo_envio -->
	<xsl:template match="tipo_envio">
		<tipo_envio>
			<xsl:apply-templates/>
		</tipo_envio>
	</xsl:template>
	<!-- Plantilla tipo_envio -->
	
	<!-- Plantilla concepto -->
	<xsl:template match="concepto">
		<concepto>
			<xsl:apply-templates/>
		</concepto>
	</xsl:template>
	<!-- Plantilla concepto -->
	
	<!-- Plantilla destinatarios -->
	<xsl:template match="destinatarios">
		<destinatarios>
			<xsl:apply-templates/>
		</destinatarios>
	</xsl:template>
	
	<xsl:template match="item">
		<item>
			<xsl:apply-templates/>
		</item>
	</xsl:template>
	
	<xsl:template match="destinatarios/item/referencia_emisor">
		<referencia_emisor>
			<xsl:apply-templates/>
		</referencia_emisor>
	</xsl:template>
	
	<xsl:template match="destinatarios/item/titular">
		<titular>
			<xsl:apply-templates/>
		</titular>
	</xsl:template>
	
	<xsl:template match="titular/telefono">
		<telefono>
			<xsl:apply-templates/>
		</telefono>
	</xsl:template>
	
	<xsl:template match="titular/email">
		<email>
			<xsl:apply-templates/>
		</email>
	</xsl:template>
	
	<xsl:template match="titular/nif">
		<nif>
			<xsl:apply-templates/>
		</nif>
	</xsl:template>
	
	<xsl:template match="titular/nombre">
		<nombre>
			<xsl:apply-templates />
		</nombre>
	</xsl:template>
	
	<xsl:template match="titular/apellidos">
		<apellidos>
			<xsl:apply-templates/>
		</apellidos>
	</xsl:template>
	
	<xsl:template match="destinatarios/item/destinatario">
		<destinatario>
			<xsl:apply-templates/>
		</destinatario>
	</xsl:template>
	
	<xsl:template match="destinatario/telefono">
		<telefono>
			<xsl:apply-templates/>
		</telefono>
	</xsl:template>
	
	<xsl:template match="destinatario/email">
		<email>
			<xsl:apply-templates/>
		</email>
	</xsl:template>
	
	<xsl:template match="destinatario/nif">
		<nif>
			<xsl:apply-templates/>
		</nif>
	</xsl:template>
	
	<xsl:template match="destinatario/nombre">
		<nombre>
			<xsl:apply-templates/>
		</nombre>
	</xsl:template>
	
	<xsl:template match="destinatario/apellidos">
		<apellidos>
			<xsl:apply-templates/>
		</apellidos>
	</xsl:template>
	
	<xsl:template match="destinatarios/item/tipo_domicilio">
		<tipo_domicilio>
			<xsl:apply-templates/>
		</tipo_domicilio>
	</xsl:template>
	
	<xsl:template match="destinatarios/item/domicilio">
		<domicilio>
			<xsl:apply-templates/>
		</domicilio>
	</xsl:template>
	
	<xsl:template match="domicilio/tipo_domicilio_concreto">
		<tipo_domicilio_concreto>
			<xsl:apply-templates/>
		</tipo_domicilio_concreto>
	</xsl:template>
	
	<xsl:template match="domicilio/tipo_via">
		<tipo_via>
			<xsl:apply-templates/>
		</tipo_via>
	</xsl:template>
	
	<xsl:template match="domicilio/nombre_via">
		<nombre_via>
			<xsl:apply-templates/>
		</nombre_via>
	</xsl:template>
	
	<xsl:template match="domicilio/numero_casa">
		<numero_casa>
			<xsl:apply-templates/>
		</numero_casa>
	</xsl:template>
	
	<xsl:template match="domicilio/punto_kilometrico">
		<punto_kilometrico>
			<xsl:apply-templates/>
		</punto_kilometrico>
	</xsl:template>
	
	<xsl:template match="domicilio/apartado_correos">
		<apartado_correos>
			<xsl:apply-templates/>
		</apartado_correos>
	</xsl:template>
	
	<xsl:template match="domicilio/calificador_numero">
		<calificador_numero>
			<xsl:apply-templates/>
		</calificador_numero>
	</xsl:template>
	
	<xsl:template match="domicilio/bloque">
		<bloque>
			<xsl:apply-templates/>
		</bloque>
	</xsl:template>
	
	<xsl:template match="domicilio/portal">
		<portal>
			<xsl:apply-templates/>
		</portal>
	</xsl:template>
	
	<xsl:template match="domicilio/escalera">
		<escalera>
			<xsl:apply-templates/>
		</escalera>
	</xsl:template>
	
	<xsl:template match="domicilio/planta">
		<planta>
			<xsl:apply-templates/>
		</planta>
	</xsl:template>
	
	<xsl:template match="domicilio/puerta">
		<puerta>
			<xsl:apply-templates/>
		</puerta>
	</xsl:template>
	
	<xsl:template match="domicilio/complemento">
		<complemento>
			<xsl:apply-templates/>
		</complemento>
	</xsl:template>
	
	<xsl:template match="domicilio/poblacion">
		<poblacion>
			<xsl:apply-templates/>
		</poblacion>
	</xsl:template>
	
	<xsl:template match="domicilio/municipio">
		<municipio>
			<xsl:apply-templates/>
		</municipio>
	</xsl:template>
	
	<xsl:template match="municipio/codigo_ine">
		<codigo_ine>
			<xsl:apply-templates/>
		</codigo_ine>
	</xsl:template>
	
	<xsl:template match="municipio/nombre">
		<nombre>
			<xsl:apply-templates/>
		</nombre>
	</xsl:template>
	
	<xsl:template match="domicilio/codigo_postal">
		<codigo_postal>
			<xsl:apply-templates/>
		</codigo_postal>
	</xsl:template>
	
	<xsl:template match="domicilio/provincia">
		<provincia>
			<xsl:apply-templates/>
		</provincia>
	</xsl:template>
	
	<xsl:template match="provincia/codigo_provincia">
		<codigo_provincia>
			<xsl:apply-templates/>
		</codigo_provincia>
	</xsl:template>
	
	<xsl:template match="provincia/nombre">
		<nombre>
			<xsl:apply-templates/>
		</nombre>
	</xsl:template>
	
	
	<xsl:template match="domicilio/pais">
		<pais>
			<xsl:apply-templates/>
		</pais>
	</xsl:template>
	
	<xsl:template match="pais/codigo_iso3166">
		<codigo_iso3166>
			<xsl:apply-templates/>
		</codigo_iso3166>
	</xsl:template>
	
	<xsl:template match="pais/nombre">
		<nombre>
			<xsl:apply-templates/>
		</nombre>
	</xsl:template>
		
	<xsl:template match="domicilio/linea_1">
		<linea_1>
			<xsl:apply-templates/>
		</linea_1>
	</xsl:template>
	
	<xsl:template match="domicilio/linea_2">
		<linea_2>
			<xsl:apply-templates/>
		</linea_2>
	</xsl:template>
	
	<xsl:template match="destinatarios/item/servicio">
		<servicio>
			<xsl:apply-templates/>
		</servicio>
	</xsl:template>
	
	<xsl:template match="destinatarios/item/direccion_electronica">
		<direccion_electronica>
			<xsl:apply-templates/>
		</direccion_electronica>
	</xsl:template>
	
	<xsl:template match="direccion_electronica/obligado">
		<obligado>
			<xsl:apply-templates/>
		</obligado>
	</xsl:template>
	
	<xsl:template match="direccion_electronica/nif">
		<nif>
			<xsl:apply-templates/>
		</nif>
	</xsl:template>
	
<!-- 	<xsl:template match="destinatarios/item/comparecencia"> -->
<!-- 		<comparecencia> -->
<!-- 			<xsl:apply-templates/> -->
<!-- 		</comparecencia> -->
<!-- 	</xsl:template> -->
	
<!-- 	<xsl:template match="comparecencia/url"> -->
<!-- 		<url> -->
<!-- 			<xsl:apply-templates/> -->
<!-- 		</url> -->
<!-- 	</xsl:template> -->
	
<!-- 	<xsl:template match="comparecencia/enviar"> -->
<!-- 		<enviar> -->
<!-- 			<xsl:apply-templates/> -->
<!-- 		</enviar> -->
<!-- 	</xsl:template> -->
	
	<xsl:template match="destinatarios/item/opciones_emision">
		<opciones_emision>
			<xsl:apply-templates/>
		</opciones_emision>
	</xsl:template>
	
	<xsl:template match="opciones_emision/retardo_postal_deh">
		<retardo_postal_deh>
			<xsl:apply-templates/>
		</retardo_postal_deh>
	</xsl:template>
	
<!-- 	<xsl:template match="opciones_emision/retardo_comparecencia"> -->
<!-- 		<retardo_comparecencia> -->
<!-- 			<xsl:apply-templates/> -->
<!-- 		</retardo_comparecencia> -->
<!-- 	</xsl:template> -->
	<!-- Plantilla destinatarios -->
	
	<!-- Plantilla procedimiento -->
	<xsl:template match="procedimiento">
		<procedimiento>
			<xsl:apply-templates/>
		</procedimiento>
	</xsl:template>
	
	<xsl:template match="procedimiento/codigo_sia">
		<codigo_sia>
			<xsl:apply-templates/>
		</codigo_sia>
	</xsl:template>
	
	<xsl:template match="procedimiento/descripcion_sia">
		<descripcion_sia>
			<xsl:apply-templates/>
		</descripcion_sia>
	</xsl:template>
	<!-- Plantilla procedimiento -->
	
	<xsl:template match="ns1:infoEnvioResponse/return">
	</xsl:template>
	<!-- Plantilla Datos Especificos -->
		
</xsl:stylesheet>