<?xml version="1.0"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:ns1="http://registrar.ws2.rec2are.map.es"
	xmlns:ns2="http://type.ws2.rec2are.map.es"
	xmlns:pet3="http://intermediacion.redsara.es/scsp/esquemas/V3/peticion"
	xmlns:res3="http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta"
	xmlns:dat3="http://intermediacion.redsara.es/scsp/esquemas/datosespecificos"
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
		<xsl:apply-templates select="//multiRef/cdRespuesta"/>
	</xsl:template>
	
	<xsl:template match="//multiRef/cdRespuesta">
		<res3:CodigoEstado>
			<xsl:choose>
                <xsl:when test=". = '00'">000</xsl:when>
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
			<xsl:apply-templates select="//multiRef/dsRespuesta"/>
	</xsl:template>
	
	<xsl:template match="//multiRef/dsRespuesta">
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
			<xsl:call-template name="templateRec"/>
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
	

	<xsl:template match="ns1:registrarResponse">
	</xsl:template>
	
	<xsl:template match="multiRef">
	</xsl:template>
	
	<xsl:template name="templateRec">
		<dat3:DatosEspecificos xmlns:dat3="http://intermediacion.redsara.es/scsp/esquemas/datosespecificos">
			<JustificanteType>
  				<xsl:call-template name="templateMultiRef"/>
			</JustificanteType>
		</dat3:DatosEspecificos>
	</xsl:template>
	
	<xsl:template name="templateMultiRef">

		<nmRegistro><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/nmRegistro" /></nmRegistro>
		<feRegistro><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/feRegistro" /></feRegistro>
		<blTimeStamp><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/blTimeStamp" /></blTimeStamp>
		<idIntercambio><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/idIntercambio" /></idIntercambio>
		<cdOficinaOrigen><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/cdOficinaOrigen" /></cdOficinaOrigen>
		<dsOficinaOrigen><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/dsOficinaOrigen" /></dsOficinaOrigen>
		<cdTipoRegistro><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/cdTipoRegistro" /></cdTipoRegistro>
		<cdOficinaDestino><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/cdOficinaDestino" /></cdOficinaDestino>
		<dsOficinaDestino><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/dsOficinaDestino" /></dsOficinaDestino>
		<cdUnidadDestino><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/cdUnidadDestino" /></cdUnidadDestino>
		<dsUnidadDestino><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/dsUnidadDestino" /></dsUnidadDestino>
		<cdUnidadOrigen><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/cdUnidadOrigen" /></cdUnidadOrigen>
		<dsUnidadOrigen><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/dsUnidadOrigen" /></dsUnidadOrigen>
		
		<!-- INTERESADOS -->
		<xsl:for-each select="//multiRef[contains(@xsi:type,'InteresadoJustificanteType')]">
			<interesados>
				<cdTipoDocIndentificativoInteresado><xsl:value-of select="cdTipoDocIndentificativoInteresado" /></cdTipoDocIndentificativoInteresado>
				<tlNumDocIdentificativoInteresado><xsl:value-of select="tlNumDocIdentificativoInteresado" /></tlNumDocIdentificativoInteresado>
				<tlNombreInteresado><xsl:value-of select="tlNombreInteresado" /></tlNombreInteresado>
				<tlApellido1Interesado><xsl:value-of select="tlApellido1Interesado" /></tlApellido1Interesado>
				<tlApellido2Interesado><xsl:value-of select="tlApellido2Interesado" /></tlApellido2Interesado>
				<tlRazonSocialInteresado><xsl:value-of select="tlRazonSocialInteresado" /></tlRazonSocialInteresado>
				<cdPaisInteresado><xsl:value-of select="cdPaisInteresado" /></cdPaisInteresado>
				<cdProvinciaInteresado><xsl:value-of select="cdProvinciaInteresado" /></cdProvinciaInteresado>
				<cdMunicipioInteresado><xsl:value-of select="cdMunicipioInteresado" /></cdMunicipioInteresado>
				<tlDireccionInteresado><xsl:value-of select="tlDireccionInteresado" /></tlDireccionInteresado>
				<cdCodigoPostalInteresado><xsl:value-of select="cdCodigoPostalInteresado" /></cdCodigoPostalInteresado>
				<tlDEHInteresado><xsl:value-of select="tlDEHInteresado" /></tlDEHInteresado>
				<tlTelefonoContactoInteresado><xsl:value-of select="tlTelefonoContactoInteresado" /></tlTelefonoContactoInteresado>
				<tlCorreoElectronicoInteresado><xsl:value-of select="tlCorreoElectronicoInteresado" /></tlCorreoElectronicoInteresado>
				<cdCanalPreferenteInteresado><xsl:value-of select="cdCanalPreferenteInteresado" /></cdCanalPreferenteInteresado>
				<cdTipoDocIndentificativoRepresentante><xsl:value-of select="cdTipoDocIndentificativoRepresentante" /></cdTipoDocIndentificativoRepresentante>
				<tlNumDocIdentificativoRepresentante><xsl:value-of select="tlNumDocIdentificativoRepresentante" /></tlNumDocIdentificativoRepresentante>
				<tlNombreRepresentante><xsl:value-of select="tlNombreRepresentante" /></tlNombreRepresentante>
				<tlApellido1Representante><xsl:value-of select="tlApellido1Representante" /></tlApellido1Representante>
				<tlApellido2Representante><xsl:value-of select="tlApellido2Representante" /></tlApellido2Representante>
				<tlRazonSocialRepresentante><xsl:value-of select="tlRazonSocialRepresentante" /></tlRazonSocialRepresentante>
				<cdPaisRepresentante><xsl:value-of select="cdPaisRepresentante" /></cdPaisRepresentante>
				<cdProvinciaRepresentante><xsl:value-of select="cdProvinciaRepresentante" /></cdProvinciaRepresentante>
				<cdMunicipioRepresentante><xsl:value-of select="cdMunicipioRepresentante" /></cdMunicipioRepresentante>
				<tlDireccionRepresentante><xsl:value-of select="tlDireccionRepresentante" /></tlDireccionRepresentante>
				<cdCodigoPostalRepresentante><xsl:value-of select="cdCodigoPostalRepresentante" /></cdCodigoPostalRepresentante>
				<tlDEHRepresentante><xsl:value-of select="tlDEHRepresentante" /></tlDEHRepresentante>
				<tlTelefonoContactoRepresentante><xsl:value-of select="tlTelefonoContactoRepresentante" /></tlTelefonoContactoRepresentante>
				<tlCorreoElectronicoRepresentante><xsl:value-of select="tlCorreoElectronicoRepresentante" /></tlCorreoElectronicoRepresentante>
				<cdCanalPreferenteRepresentante><xsl:value-of select="cdCanalPreferenteRepresentante" /></cdCanalPreferenteRepresentante>
				<tlObservaciones><xsl:value-of select="tlObservaciones" /></tlObservaciones>
			</interesados>
		</xsl:for-each>
		
		<!-- DOCUMENTOS -->
		<xsl:for-each select="//multiRef[contains(@xsi:type,'DocumentoJustificanteType')]">
			<documentos>
				<dsNombre><xsl:value-of select="dsNombre" /></dsNombre>
				<cdValidez><xsl:value-of select="cdValidez" /></cdValidez>
				<cdTipo><xsl:value-of select="cdTipo" /></cdTipo>
				<itFirmado><xsl:value-of select="itFirmado" /></itFirmado>
				<blPKCertificado><xsl:value-of select="blPKCertificado" /></blPKCertificado>
				<blTimeStamp><xsl:value-of select="blTimeStamp" /></blTimeStamp>
				<blValidacionOCSP><xsl:value-of select="blValidacionOCSP" /></blValidacionOCSP>
				<blHash><xsl:value-of select="blHash" /></blHash>
				<dsTipoMIME><xsl:value-of select="dsTipoMIME" /></dsTipoMIME>
				<idDocumento><xsl:value-of select="idDocumento" /></idDocumento>
				<tlObservaciones><xsl:value-of select="tlObservaciones" /></tlObservaciones>
			</documentos>
		</xsl:for-each>
		
		<tlResumen><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/tlResumen" /></tlResumen>
		<cdAsunto><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/cdAsunto" /></cdAsunto>
		<dsAsunto><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/dsAsunto" /></dsAsunto>
		<tlReferenciaExterna><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/tlReferenciaExterna" /></tlReferenciaExterna>
		<tlNumeroExpediente><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/tlNumeroExpediente" /></tlNumeroExpediente>
		<cdTipoTransporte><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/cdTipoTransporte" /></cdTipoTransporte>
		<tlNumeroTransporte><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/tlNumeroTransporte" /></tlNumeroTransporte>
		<tlNombreUsuario><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/tlNombreUsuario" /></tlNombreUsuario>
		<tlContactoUsuario><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/tlContactoUsuario" /></tlContactoUsuario>
		<cdDocumentacionFisicaSoportes><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/cdDocumentacionFisicaSoportes" /></cdDocumentacionFisicaSoportes>
		<tlExpone><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/tlExpone" /></tlExpone>
		<tlSolicita><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/tlSolicita" /></tlSolicita>
		<tlAplicacion><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/tlAplicacion" /></tlAplicacion>
		<tlObservaciones><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/tlObservaciones" /></tlObservaciones>
		<blJustificante><xsl:value-of select="//multiRef[contains(@xsi:type,'JustificanteType')]/blJustificante" /></blJustificante>
		
		<!-- RESPUESTA -->
		<respuesta>
			<cdRespuesta><xsl:value-of select="//multiRef[contains(@xsi:type,'RespuestaType')]/cdRespuesta" /></cdRespuesta>
			<dsRespuesta><xsl:value-of select="//multiRef[contains(@xsi:type,'RespuestaType')]/dsRespuesta" /></dsRespuesta>
		</respuesta>
		
	</xsl:template>
	<!-- Plantilla Datos Especificos -->
		
</xsl:stylesheet>