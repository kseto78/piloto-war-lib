<?xml version="1.0"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
	xmlns:pet3="http://intermediacion.redsara.es/scsp/esquemas/V3/peticion"
	xmlns:pet2="http://www.map.es/scsp/esquemas/V2/peticion" xmlns:dat2="http://www.map.es/scsp/esquemas/datosespecificos"
	xmlns:dat3="http://intermediacion.redsara.es/scsp/esquemas/datosespecificos">

	<xsl:output indent="yes" omit-xml-declaration="no" encoding="UTF-8" />
	
	<xsl:template match="/">
		<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
			<xsl:apply-templates />
		</soapenv:Envelope>
	</xsl:template>

	<!-- Plantilla Header -->
	<xsl:template match="soapenv:Header">
		<soapenv:Header />
	</xsl:template>

	<!-- Plantilla Body -->
	<xsl:template match="soapenv:Body">
		<soapenv:Body Id="MsgBody">
			<xsl:apply-templates />
		</soapenv:Body>
	</xsl:template>

	<!-- Plantilla Atributos -->
	<xsl:template match="pet3:Atributos">
		<pet2:Atributos>
			<xsl:apply-templates />
		</pet2:Atributos>
	</xsl:template>

	<!-- Plantillas elementos Atributos -->
	<xsl:template match="pet3:IdPeticion">
		<pet2:IdPeticion>
			<xsl:value-of select="." />
		</pet2:IdPeticion>
	</xsl:template>

	<xsl:template match="pet3:NumElementos">
		<pet2:NumElementos>
			<xsl:value-of select="." />
		</pet2:NumElementos>
	</xsl:template>

	<xsl:template match="pet3:TimeStamp">
		<pet2:TimeStamp>
			<xsl:value-of select="." />
		</pet2:TimeStamp>
	</xsl:template>

	<xsl:template match="pet3:CodigoCertificado">
		<pet2:CodigoCertificado>
			<xsl:value-of select="." />
		</pet2:CodigoCertificado>
	</xsl:template>

	<!-- Plantilla Solicitudes -->
	<xsl:template match="pet3:Solicitudes">
		<pet2:Solicitudes>
			<xsl:apply-templates />
		</pet2:Solicitudes>
	</xsl:template>

	<!-- Plantilla SolicitudTransmision -->
	<xsl:template match="pet3:SolicitudTransmision">
		<pet2:SolicitudTransmision>
			<xsl:apply-templates />
		</pet2:SolicitudTransmision>
	</xsl:template>

	<!-- Plantilla DatosGenericos -->
	<xsl:template match="pet3:DatosGenericos">
		<pet2:DatosGenericos>
			<xsl:apply-templates />
		</pet2:DatosGenericos>
	</xsl:template>

	<!-- Plantilla DatosEspecificos -->
	<xsl:template match="dat3:DatosEspecificos">
		<dat2:DatosEspecificos>
			<xsl:call-template name="hijosDatosEspecificos" />
		</dat2:DatosEspecificos>
	</xsl:template>

	<!-- Plantilla Emisor -->
	<xsl:template match="pet3:Emisor">
		<pet2:Emisor>
			<xsl:apply-templates />
		</pet2:Emisor>
	</xsl:template>

	<!-- Plantillas elementos Emisor -->

	<xsl:template match="pet3:NombreEmisor">
		<pet2:NombreEmisor>
			<xsl:value-of select="." />
		</pet2:NombreEmisor>
	</xsl:template>

	<xsl:template match="pet3:NifEmisor">
		<pet2:NifEmisor>
			<xsl:value-of select="." />
		</pet2:NifEmisor>
	</xsl:template>

	<!-- Plantilla Atributos -->
	<xsl:template match="pet3:Titular">
		<pet2:Titular>
			<xsl:apply-templates />
		</pet2:Titular>
	</xsl:template>

	<!-- Plantillas elementos Titular -->
	<xsl:template match="pet3:TipoDocumentacion">
		<pet2:TipoDocumentacion>
			<xsl:value-of select="." />
		</pet2:TipoDocumentacion>
	</xsl:template>
	<xsl:template match="pet3:Documentacion">
		<pet2:Documentacion>
			<xsl:value-of select="." />
		</pet2:Documentacion>
	</xsl:template>
	<xsl:template match="pet3:NombreCompleto">
		<pet2:NombreCompleto>
			<xsl:value-of select="." />
		</pet2:NombreCompleto>
	</xsl:template>
	<xsl:template match="pet3:Nombre">
		<pet2:Nombre>
			<xsl:value-of select="." />
		</pet2:Nombre>
	</xsl:template>
	<xsl:template match="pet3:Apellido1">
		<pet2:Apellido1>
			<xsl:value-of select="." />
		</pet2:Apellido1>
	</xsl:template>
	<xsl:template match="pet3:Apellido2">
		<pet2:Apellido2>
			<xsl:value-of select="." />
		</pet2:Apellido2>
	</xsl:template>

	<!-- Plantilla Transmision -->
	<xsl:template match="pet3:Transmision">
		<pet2:Transmision>
			<xsl:apply-templates />
		</pet2:Transmision>
	</xsl:template>

	<xsl:template match="pet3:IdSolicitud">
		<pet2:IdSolicitud>
			<xsl:value-of select="." />
		</pet2:IdSolicitud>
	</xsl:template>

	<xsl:template match="pet3:IdTransmision">
		<pet2:IdTransmision>
			<xsl:value-of select="." />
		</pet2:IdTransmision>
	</xsl:template>

	<xsl:template match="pet3:FechaGeneracion">
		<pet2:FechaGeneracion>
			<xsl:value-of select="." />
		</pet2:FechaGeneracion>
	</xsl:template>


	<!-- Plantilla Peticion -->
	<xsl:template match="pet3:Peticion">
		<pet2:Peticion>
			<xsl:apply-templates />
		</pet2:Peticion>
	</xsl:template>

	<!-- Plantilla Solicitante -->
	<xsl:template match="pet3:Solicitante">
		<pet2:Solicitante>
			<pet2:IdentificadorSolicitante>
				<xsl:value-of select="pet3:IdentificadorSolicitante" />
			</pet2:IdentificadorSolicitante>
			<pet2:NombreSolicitante>
				<xsl:value-of select="pet3:NombreSolicitante" />
			</pet2:NombreSolicitante>
			<xsl:call-template name="putFinalidad">
				<xsl:with-param name="CodProcedimiento"
					select="pet3:Procedimiento/pet3:CodProcedimiento" />
				<xsl:with-param name="IdExpediente" select="pet3:IdExpediente" />
				<xsl:with-param name="Finalidad" select="pet3:Finalidad" />
			</xsl:call-template>
			<pet2:Consentimiento>
				<xsl:value-of select="pet3:Consentimiento" />
			</pet2:Consentimiento>
			<xsl:apply-templates select="pet3:Funcionario" />
		</pet2:Solicitante>
	</xsl:template>

	<!-- Plantilla Funcionario -->
	<xsl:template match="pet3:Funcionario">
		<pet2:Funcionario>
			<xsl:apply-templates />
		</pet2:Funcionario>
	</xsl:template>

	<!-- Plantillas elementos Funcionario -->
	<xsl:template match="pet3:NombreCompletoFuncionario">
		<pet2:NombreCompletoFuncionario>
			<xsl:value-of select="." />
		</pet2:NombreCompletoFuncionario>
	</xsl:template>
	<xsl:template match="pet3:NifFuncionario">
		<pet2:NifFuncionario>
			<xsl:value-of select="." />
		</pet2:NifFuncionario>
	</xsl:template>

	<!-- Plantilla Estado -->
	<xsl:template match="pet3:Estado">
		<pet2:Estado>
			<xsl:apply-templates />
		</pet2:Estado>
	</xsl:template>

	<xsl:template match="pet3:CodigoEstado">
		<pet2:CodigoEstado>
			<xsl:value-of select="." />
		</pet2:CodigoEstado>
	</xsl:template>

	<xsl:template match="pet3:CodigoEstadoSecundario">
		<pet2:CodigoEstadoSecundario>
			<xsl:value-of select="." />
		</pet2:CodigoEstadoSecundario>
	</xsl:template>

	<xsl:template match="pet3:LiteralError">
		<pet2:LiteralError>
			<xsl:value-of select="." />
		</pet2:LiteralError>
	</xsl:template>

	<xsl:template match="pet3:TiempoEstimadoRespuesta">
		<pet2:TiempoEstimadoRespuesta>
			<xsl:value-of select="." />
		</pet2:TiempoEstimadoRespuesta>
	</xsl:template>

	<!-- FUNCIONES -->

	<!-- finalidad -->
	<xsl:template name="putFinalidad">
		<xsl:param name="CodProcedimiento" />
		<xsl:param name="IdExpediente" />
		<xsl:param name="Finalidad" />
		<pet2:Finalidad>
			<xsl:value-of select="$CodProcedimiento" />
			#::#
			<xsl:value-of select="$IdExpediente" />
			#::#
			<xsl:value-of select="$Finalidad" />
		</pet2:Finalidad>
	</xsl:template>

	<!-- Elementos hijos de datos especificos -->
	<xsl:template name="hijosDatosEspecificos">
		<xsl:for-each select="child::*">
			<xsl:choose>
				<!-- elemento con hijos -->
				<xsl:when test="count(child::*)!=0">
					<xsl:element name="{local-name(.)}">
						<xsl:call-template name="hijosDatosEspecificos" />
					</xsl:element>
				</xsl:when>
				<!-- elemento de texto -->
				<xsl:otherwise>
					<xsl:element name="{local-name(.)}">
						<xsl:value-of select="." />
					</xsl:element>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>