<?xml version="1.0"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
	xmlns:pet3="http://intermediacion.redsara.es/scsp/esquemas/V3/peticion"
	xmlns:res3="http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta"
	xmlns:dat3="http://intermediacion.redsara.es/scsp/esquemas/datosespecificos"
	xmlns:ns1="http://impl.manager.cct.map.es"
	xmlns:ns2="http://bean.cct.map.es"
	exclude-result-prefixes="ns1 ns2 pet3">

	<xsl:output indent="yes" omit-xml-declaration="yes" encoding="UTF-8" />
	
	<xsl:template match="/">
		<soapenv:Envelope>
		<soapenv:Header />
			<soapenv:Body>
				<xsl:apply-templates />
			</soapenv:Body>
		</soapenv:Envelope>
	</xsl:template>
	
	<xsl:template match="soapenv:Header">
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
        <xsl:if test="//ns1:recuperarTrazaResponse/ns1:resultTraza/ns2:error != ''">
        	<xsl:apply-templates select="//ns1:recuperarTrazaResponse/ns1:resultTraza/ns2:error/ns2:codigo"/>
        </xsl:if>
        <xsl:if test="//ns1:recuperarTrazaResponse/ns1:resultTraza/ns2:error = ''">
            <res3:CodigoEstado>000</res3:CodigoEstado>
        </xsl:if>
    </xsl:template>
	
	<xsl:template match="//ns1:recuperarTrazaResponse/ns1:resultTraza/ns2:error/ns2:codigo">
		<res3:CodigoEstado>
			<xsl:choose>
                <xsl:when test=". = '0201'">000</xsl:when>
                <xsl:otherwise><xsl:value-of select="." /></xsl:otherwise>
            </xsl:choose>
		</res3:CodigoEstado>
	</xsl:template>

	<xsl:template match="pet3:CodigoEstadoSecundario">
		<res3:CodigoEstadoSecundario>
			<xsl:value-of select="." />
		</res3:CodigoEstadoSecundario>
	</xsl:template>
	
	<xsl:template match="pet3:LiteralError">
		<xsl:if test="//ns1:recuperarTrazaResponse/ns1:resultTraza/ns2:error != ''">
			<xsl:apply-templates select="//ns1:recuperarTrazaResponse/ns1:resultTraza/ns2:error/ns2:descripcion"/>
		</xsl:if>
		<xsl:if test="//ns1:recuperarTrazaResponse/ns1:resultTraza/ns2:error = ''">
			<res3:LiteralError>OK</res3:LiteralError>
		</xsl:if>
	</xsl:template>
	
	<xsl:template match="//ns1:recuperarTrazaResponse/ns1:resultTraza/ns2:error/ns2:descripcion">
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
			<xsl:apply-templates select="//ns1:recuperarTrazaResponse/ns1:resultTraza" />
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
	
	<xsl:template match="//ns1:recuperarTrazaResponse/ns1:resultTraza">
		<dat3:DatosEspecificos xmlns:dat3="http://intermediacion.redsara.es/scsp/esquemas/datosespecificos">
			<recuperarTrazaResponse>
				<resultTraza>
					<xsl:apply-templates />
				</resultTraza>
			</recuperarTrazaResponse>
		</dat3:DatosEspecificos>
	</xsl:template>
	
	<xsl:template match="ns2:error">
		<ns2:error/>
	</xsl:template>
	
	<xsl:template match="ns2:asientos">
		<ns2:asientos>
			<xsl:apply-templates />
		</ns2:asientos>
	</xsl:template>
	
	<xsl:template match="ns2:asientos/ns2:numRegistro">
		<ns2:numRegistro>
			<xsl:value-of select="." />
		</ns2:numRegistro>
	</xsl:template>
	
	<xsl:template match="ns2:asientos/ns2:fechaRegistro">
		<ns2:fechaRegistro>
			<xsl:value-of select="." />
		</ns2:fechaRegistro>
	</xsl:template>
	
	<xsl:template match="ns2:asientos/ns2:cdOficinaRegistroInicio">
		<ns2:cdOficinaRegistroInicio>
			<xsl:value-of select="." />
		</ns2:cdOficinaRegistroInicio>
	</xsl:template>
	
	<xsl:template match="ns2:asientos/ns2:dsOficinaRegistroInicio">
		<ns2:dsOficinaRegistroInicio>
			<xsl:value-of select="." />
		</ns2:dsOficinaRegistroInicio>
	</xsl:template>
	
	<xsl:template match="ns2:asientos/ns2:cdIntercambio">
		<ns2:cdIntercambio>
			<xsl:value-of select="." />
		</ns2:cdIntercambio>
	</xsl:template>
	
	<xsl:template match="ns2:asientos/ns2:interesados">
		<ns2:interesados>
			<xsl:apply-templates />
		</ns2:interesados>
	</xsl:template>
	
	<xsl:template match="ns2:interesados/ns2:num_doc_ident_interesado">
		<ns2:num_doc_ident_interesado>
			<xsl:value-of select="." />
		</ns2:num_doc_ident_interesado>
	</xsl:template>
	
	<xsl:template match="ns2:interesados/ns2:tipo_doc_ident_interesado">
		<ns2:tipo_doc_ident_interesado>
			<xsl:value-of select="." />
		</ns2:tipo_doc_ident_interesado>
	</xsl:template>
	
	<xsl:template match="ns2:interesados/ns2:nombre_interesado">
		<ns2:nombre_interesado>
			<xsl:value-of select="." />
		</ns2:nombre_interesado>
	</xsl:template>
	
	<xsl:template match="ns2:interesados/ns2:apellido1_interesado">
		<ns2:apellido1_interesado>
			<xsl:value-of select="." />
		</ns2:apellido1_interesado>
	</xsl:template>
	
	<xsl:template match="ns2:interesados/ns2:apellido2_interesado">
		<ns2:apellido2_interesado>
			<xsl:value-of select="." />
		</ns2:apellido2_interesado>
	</xsl:template>
	
	<xsl:template match="ns2:interesados/ns2:razon_social_interesado">
		<ns2:razon_social_interesado>
			<xsl:value-of select="." />
		</ns2:razon_social_interesado>
	</xsl:template>
	
	<xsl:template match="ns2:interesados/ns2:num_doc_ident_representante">
		<ns2:num_doc_ident_representante>
			<xsl:value-of select="." />
		</ns2:num_doc_ident_representante>
	</xsl:template>
	
	<xsl:template match="ns2:interesados/ns2:tipo_doc_ident_representante">
		<ns2:tipo_doc_ident_representante>
			<xsl:value-of select="." />
		</ns2:tipo_doc_ident_representante>
	</xsl:template>
	
	<xsl:template match="ns2:interesados/ns2:nombre_representante">
		<ns2:nombre_representante>
			<xsl:value-of select="." />
		</ns2:nombre_representante>
	</xsl:template>
	
	<xsl:template match="ns2:interesados/ns2:apellido1_representante">
		<ns2:apellido1_representante>
			<xsl:value-of select="." />
		</ns2:apellido1_representante>
	</xsl:template>
	
	<xsl:template match="ns2:interesados/ns2:apellido2_representante">
		<ns2:apellido2_representante>
			<xsl:value-of select="." />
		</ns2:apellido2_representante>
	</xsl:template>
	
	<xsl:template match="ns2:interesados/ns2:razon_social_representante">
		<ns2:razon_social_representante>
			<xsl:value-of select="." />
		</ns2:razon_social_representante>
	</xsl:template>
	
	<xsl:template match="ns2:asientos/ns2:anotaciones">
		<ns2:anotaciones>
			<xsl:apply-templates />
		</ns2:anotaciones>
	</xsl:template>
	
	<xsl:template match="ns2:anotaciones/ns2:fechaAnotacion">
		<ns2:fechaAnotacion>
			<xsl:value-of select="." />
		</ns2:fechaAnotacion>
	</xsl:template>
	
	<xsl:template match="ns2:anotaciones/ns2:cdEstado">
		<ns2:cdEstado>
			<xsl:value-of select="." />
		</ns2:cdEstado>
	</xsl:template>
	
	<xsl:template match="ns2:anotaciones/ns2:dsEstado">
		<ns2:dsEstado>
			<xsl:value-of select="." />
		</ns2:dsEstado>
	</xsl:template>
	
	<xsl:template match="ns2:anotaciones/ns2:cdOrOrigen">
		<ns2:cdOrOrigen>
			<xsl:value-of select="." />
		</ns2:cdOrOrigen>
	</xsl:template>
	
	<xsl:template match="ns2:anotaciones/ns2:dsOrOrigen">
		<ns2:dsOrOrigen>
			<xsl:value-of select="." />
		</ns2:dsOrOrigen>
	</xsl:template>
	
	<xsl:template match="ns2:anotaciones/ns2:cdOrDestino">
		<ns2:cdOrDestino>
			<xsl:value-of select="." />
		</ns2:cdOrDestino>
	</xsl:template>
	
	<xsl:template match="ns2:anotaciones/ns2:dsOrDestino">
		<ns2:dsOrDestino>
			<xsl:value-of select="." />
		</ns2:dsOrDestino>
	</xsl:template>
	
	<xsl:template match="ns2:anotaciones/ns2:cdUgDestino">
		<ns2:cdUgDestino>
			<xsl:value-of select="." />
		</ns2:cdUgDestino>
	</xsl:template>
	
	<xsl:template match="ns2:anotaciones/ns2:dsUgDestino">
		<ns2:dsUgDestino>
			<xsl:value-of select="." />
		</ns2:dsUgDestino>
	</xsl:template>
	
	<xsl:template match="ns2:anotaciones/ns2:observaciones">
		<ns2:observaciones>
			<xsl:value-of select="." />
		</ns2:observaciones>
	</xsl:template>
	
	<xsl:template match="ns2:asientos/ns2:fechaModUltReg">
		<ns2:fechaModUltReg>
			<xsl:value-of select="." />
		</ns2:fechaModUltReg>
	</xsl:template>
	
	<xsl:template match="ns2:resultados">
		<ns2:resultados>
			<xsl:apply-templates />
		</ns2:resultados>
	</xsl:template>
	
	<xsl:template match="ns2:resultados/ns2:num_total_resultados">
		<ns2:num_total_resultados>
			<xsl:value-of select="." />
		</ns2:num_total_resultados>
	</xsl:template>
	
	<xsl:template match="ns2:resultados/ns2:posicion_inicial">
		<ns2:posicion_inicial>
			<xsl:value-of select="." />
		</ns2:posicion_inicial>
	</xsl:template>
	
	<xsl:template match="ns2:resultados/ns2:num_resultados">
		<ns2:num_resultados>
			<xsl:value-of select="." />
		</ns2:num_resultados>
	</xsl:template>
	
	<xsl:template match="ns1:recuperarTrazaResponse">
	</xsl:template>
	<!-- Plantilla Datos Especificos -->
		
</xsl:stylesheet>