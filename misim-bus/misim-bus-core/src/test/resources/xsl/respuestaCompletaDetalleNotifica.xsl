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
			<dat3:Object>
				<xsl:apply-templates />
			</dat3:Object>
		</dat3:DatosEspecificos>
	</xsl:template>
	
	<!-- Plantilla organismo_emisor -->
	<xsl:template match="organismo_emisor">
		<dat3:Property name ="organismo_emisor">
			<dat3:ValueType>
				<xsl:apply-templates/>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="organismo_emisor/codigo_dir3">
		<dat3:Property name ="codigo_dir3">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="organismo_emisor/nombre">
		<dat3:Property name ="nombre">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	<!-- Plantilla organismo_emisor -->
	
	<!-- Plantilla organismo_pagador -->
	<xsl:template match="organismo_pagador">
		<dat3:Property name ="organismo_pagador">
			<dat3:ValueType>
				<xsl:apply-templates/>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="organismo_pagador/codigo_dir3">
		<dat3:Property name ="codigo_dir3">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="organismo_pagador/numero_contrato_correos">
		<dat3:Property name ="numero_contrato_correos">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="organismo_pagador/codigo_cliente_facturacion_correos">
		<dat3:Property name ="codigo_cliente_facturacion_correos">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	<!-- Plantilla organismo_pagador -->
	
	<!-- Plantilla documento -->
	<xsl:template match="documento">
		<dat3:Property name ="documento">
			<dat3:ValueType>
				<xsl:apply-templates/>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="documento/contenido">
		<dat3:Property name ="contenido">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="documento/hash_sha1">
		<dat3:Property name ="hash_sha1">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="documento/normalizado">
		<dat3:Property name ="normalizado">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	<!-- Plantilla documento -->
	
	<!-- Plantilla tipo_envio -->
	<xsl:template match="tipo_envio">
		<dat3:Property name ="tipo_envio">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	<!-- Plantilla tipo_envio -->
	
	<!-- Plantilla concepto -->
	<xsl:template match="concepto">
		<dat3:Property name ="concepto">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	<!-- Plantilla concepto -->
	
	<!-- Plantilla destinatarios -->
	<xsl:template match="destinatarios/item">
		<dat3:Property name ="destinatarios">
			<dat3:ValueType>
				<xsl:apply-templates/>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="destinatarios/item/referencia_emisor">
		<dat3:Property name ="referencia_emisor">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="destinatarios/item/titular">
		<dat3:Property name ="titular">
			<dat3:ValueType>
				<xsl:apply-templates/>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="titular/telefono">
		<dat3:Property name ="telefono">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="titular/email">
		<dat3:Property name ="email">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="titular/nif">
		<dat3:Property name ="nif">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="titular/nombre">
		<dat3:Property name ="nombre">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="titular/apellidos">
		<dat3:Property name ="apellidos">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="destinatarios/item/destinatario">
		<dat3:Property name ="destinatario">
			<dat3:ValueType>
				<xsl:apply-templates/>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="destinatario/telefono">
		<dat3:Property name ="telefono">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="destinatario/email">
		<dat3:Property name ="email">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="destinatario/nif">
		<dat3:Property name ="nif">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="destinatario/nombre">
		<dat3:Property name ="nombre">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="destinatario/apellidos">
		<dat3:Property name ="apellidos">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="destinatarios/item/tipo_domicilio">
		<dat3:Property name ="tipo_domicilio">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="destinatarios/item/domicilio">
		<dat3:Property name ="domicilio">
			<dat3:ValueType>
				<xsl:apply-templates/>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="domicilio/tipo_domicilio_concreto">
		<dat3:Property name ="tipo_domicilio_concreto">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="domicilio/tipo_via">
		<dat3:Property name ="tipo_via">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="domicilio/nombre_via">
		<dat3:Property name ="nombre_via">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="domicilio/numero_casa">
		<dat3:Property name ="numero_casa">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="domicilio/punto_kilometrico">
		<dat3:Property name ="punto_kilometrico">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="domicilio/apartado_correos">
		<dat3:Property name ="apartado_correos">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="domicilio/calificador_numero">
		<dat3:Property name ="calificador_numero">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="domicilio/bloque">
		<dat3:Property name ="bloque">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="domicilio/portal">
		<dat3:Property name ="portal">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="domicilio/escalera">
		<dat3:Property name ="escalera">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="domicilio/planta">
		<dat3:Property name ="planta">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="domicilio/puerta">
		<dat3:Property name ="puerta">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="domicilio/complemento">
		<dat3:Property name ="complemento">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="domicilio/poblacion">
		<dat3:Property name ="poblacion">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="domicilio/municipio">
		<dat3:Property name ="municipio">
			<dat3:ValueType>
				<xsl:apply-templates/>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="municipio/codigo_ine">
		<dat3:Property name ="codigo_ine">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="municipio/nombre">
		<dat3:Property name ="nombre">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="domicilio/codigo_postal">
		<dat3:Property name ="codigo_postal">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="domicilio/provincia">
		<dat3:Property name ="provincia">
			<dat3:ValueType>
				<xsl:apply-templates/>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="provincia/codigo_provincia">
		<dat3:Property name ="codigo_provincia">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="provincia/nombre">
		<dat3:Property name ="nombre">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	
	<xsl:template match="domicilio/pais">
		<dat3:Property name ="pais">
			<dat3:ValueType>
				<xsl:apply-templates/>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="pais/codigo_iso3166">
		<dat3:Property name ="codigo_iso3166">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="pais/nombre">
		<dat3:Property name ="nombre">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
		
	<xsl:template match="domicilio/linea_1">
		<dat3:Property name ="linea_1">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="domicilio/linea_2">
		<dat3:Property name ="linea_2">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="destinatarios/item/servicio">
		<dat3:Property name ="servicio">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="destinatarios/item/direccion_electronica">
		<dat3:Property name ="direccion_electronica">
			<dat3:ValueType>
				<xsl:apply-templates/>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="direccion_electronica/obligado">
		<dat3:Property name ="obligado">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="direccion_electronica/nif">
		<dat3:Property name ="nif">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="destinatarios/item/comparecencia">
		<dat3:Property name ="comparecencia">
			<dat3:ValueType>
				<xsl:apply-templates/>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="comparecencia/url">
		<dat3:Property name ="url">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="comparecencia/enviar">
		<dat3:Property name ="enviar">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="destinatarios/item/opciones_emision">
		<dat3:Property name ="opciones_emision">
			<dat3:ValueType>
				<xsl:apply-templates/>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="opciones_emision/retardo_postal_deh">
		<dat3:Property name ="retardo_postal_deh">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="opciones_emision/retardo_comparecencia">
		<dat3:Property name ="retardo_comparecencia">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	<!-- Plantilla destinatarios -->
	
	<!-- Plantilla procedimiento -->
	<xsl:template match="procedimiento">
		<dat3:Property name ="procedimiento">
			<dat3:ValueType>
				<xsl:apply-templates/>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="procedimiento/codigo_sia">
		<dat3:Property name ="codigo_sia">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	
	<xsl:template match="procedimiento/descripcion_sia">
		<dat3:Property name ="descripcion_sia">
			<dat3:ValueType>
				<dat3:Value>
					<xsl:apply-templates/>
				</dat3:Value>
			</dat3:ValueType>
		</dat3:Property>
	</xsl:template>
	<!-- Plantilla procedimiento -->
	
	<xsl:template match="ns1:infoEnvioResponse/return">
	</xsl:template>
	<!-- Plantilla Datos Especificos -->
		
</xsl:stylesheet>