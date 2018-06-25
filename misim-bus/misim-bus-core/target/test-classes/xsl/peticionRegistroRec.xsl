<?xml version="1.0"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
	xmlns:ns="https://administracionelectronica.gob.es/notifica/ws/notifica/1.0/"
 	xmlns:pet3="http://intermediacion.redsara.es/scsp/esquemas/V3/peticion"
	xmlns:dat3="http://intermediacion.redsara.es/scsp/esquemas/datosespecificos"
	xmlns:reg="http://registrar.ws2.rec2are.map.es"
	exclude-result-prefixes="pet3 dat3">
	
	<xsl:output indent="yes" omit-xml-declaration="no" encoding="UTF-8"/>
	
	<xsl:template match="/">
		<soapenv:Envelope>
			<xsl:apply-templates/>
		</soapenv:Envelope>
	</xsl:template>
	
	<!-- Plantilla Header -->
	<xsl:template match="soapenv:Header">
		<soapenv:Header/>
	</xsl:template>

	<!-- Plantilla Body -->
	<xsl:template match="soapenv:Body">
		<soapenv:Body>
			<xsl:apply-templates/>
		</soapenv:Body>
	</xsl:template>
	
	<!-- Plantilla Atributos -->
	<xsl:template match="pet3:Atributos">
	</xsl:template>
	
	<!-- Plantilla DatosGenericos -->
	<xsl:template match="pet3:DatosGenericos">
	</xsl:template>
	
	<xsl:template match="dat3:DatosEspecificos">
		<reg:registrar>
			<xsl:apply-templates/>
		</reg:registrar>
	</xsl:template>
	
	<xsl:template match="autenticacion">
		<autenticacion>
			<xsl:apply-templates/>
		</autenticacion>
	</xsl:template>
	
	<xsl:template match="autenticacion/idAplicacion">
  		<idAplicacion>
  			<xsl:value-of select="."/>
  		</idAplicacion>
	</xsl:template>
	
	<xsl:template match="autenticacion/password">
  		<password>
  			<xsl:value-of select="."/>
  		</password>
	</xsl:template>
	
	<xsl:template match="registro">
		<registro>
			<xsl:apply-templates/>
		</registro>
	</xsl:template>
	
	<xsl:template match="registro/cdOficinaOrigen">
  		<cdOficinaOrigen>
  			<xsl:value-of select="."/>
  		</cdOficinaOrigen>
	</xsl:template>
	
	<xsl:template match="registro/cdTipoRegistro">
  		<cdTipoRegistro>
  			<xsl:value-of select="."/>
  		</cdTipoRegistro>
	</xsl:template>
	
	<xsl:template match="registro/feRegistro">
  		<feRegistro>
  			<xsl:value-of select="."/>
  		</feRegistro>
	</xsl:template>
	
	<xsl:template match="registro/cdUnidadDestino">
  		<cdUnidadDestino>
  			<xsl:value-of select="."/>
  		</cdUnidadDestino>
	</xsl:template>
	
	<xsl:template match="registro/cdUnidadOrigen">
  		<cdUnidadOrigen>
  			<xsl:value-of select="."/>
  		</cdUnidadOrigen>
	</xsl:template>
	
	<xsl:template match="registro/interesados">
		<interesados>
			<xsl:apply-templates/>
		</interesados>
	</xsl:template>
	
	<xsl:template match="interesados/cdTipoDocIndentificativoInteresado">
  		<cdTipoDocIndentificativoInteresado>
  			<xsl:value-of select="."/>
  		</cdTipoDocIndentificativoInteresado>
	</xsl:template>
	
	<xsl:template match="interesados/tlNumDocIdentificativoInteresado">
  		<tlNumDocIdentificativoInteresado>
  			<xsl:value-of select="."/>
  		</tlNumDocIdentificativoInteresado>
	</xsl:template>
	
	<xsl:template match="interesados/tlNombreInteresado">
  		<tlNombreInteresado>
  			<xsl:value-of select="."/>
  		</tlNombreInteresado>
	</xsl:template>
	
	<xsl:template match="interesados/tlApellido1Interesado">
  		<tlApellido1Interesado>
  			<xsl:value-of select="."/>
  		</tlApellido1Interesado>
	</xsl:template>
	
	<xsl:template match="interesados/tlApellido2Interesado">
  		<tlApellido2Interesado>
  			<xsl:value-of select="."/>
  		</tlApellido2Interesado>
	</xsl:template>
	
	<xsl:template match="interesados/tlRazonSocialInteresado">
  		<tlRazonSocialInteresado>
  			<xsl:value-of select="."/>
  		</tlRazonSocialInteresado>
	</xsl:template>
	
	<xsl:template match="interesados/cdPaisInteresado">
  		<cdPaisInteresado>
  			<xsl:value-of select="."/>
  		</cdPaisInteresado>
	</xsl:template>
	
	<xsl:template match="interesados/cdProvinciaInteresado">
  		<cdProvinciaInteresado>
  			<xsl:value-of select="."/>
  		</cdProvinciaInteresado>
	</xsl:template>
	
	<xsl:template match="interesados/cdMunicipioInteresado">
  		<cdMunicipioInteresado>
  			<xsl:value-of select="."/>
  		</cdMunicipioInteresado>
	</xsl:template>
	
	<xsl:template match="interesados/tlDireccionInteresado">
  		<tlDireccionInteresado>
  			<xsl:value-of select="."/>
  		</tlDireccionInteresado>
	</xsl:template>
	
	<xsl:template match="interesados/cdCodigoPostalInteresado">
  		<cdCodigoPostalInteresado>
  			<xsl:value-of select="."/>
  		</cdCodigoPostalInteresado>
	</xsl:template>

	<xsl:template match="interesados/tlDEHInteresado">
  		<tlDEHInteresado>
  			<xsl:value-of select="."/>
  		</tlDEHInteresado>
	</xsl:template>
	
	<xsl:template match="interesados/tlTelefonoContactoInteresado">
  		<tlTelefonoContactoInteresado>
  			<xsl:value-of select="."/>
  		</tlTelefonoContactoInteresado>
	</xsl:template>
	
	<xsl:template match="interesados/tlCorreoElectronicoInteresado">
  		<tlCorreoElectronicoInteresado>
  			<xsl:value-of select="."/>
  		</tlCorreoElectronicoInteresado>
	</xsl:template>
	
	<xsl:template match="interesados/cdCanalPreferenteInteresado">
  		<cdCanalPreferenteInteresado>
  			<xsl:value-of select="."/>
  		</cdCanalPreferenteInteresado>
	</xsl:template>
	
	<xsl:template match="interesados/cdTipoDocIndentificativoRepresentante">
  		<cdTipoDocIndentificativoRepresentante>
  			<xsl:value-of select="."/>
  		</cdTipoDocIndentificativoRepresentante>
	</xsl:template>
	
	<xsl:template match="interesados/tlNumDocIdentificativoRepresentante">
  		<tlNumDocIdentificativoRepresentante>
  			<xsl:value-of select="."/>
  		</tlNumDocIdentificativoRepresentante>
	</xsl:template>

	<xsl:template match="interesados/tlNombreRepresentante">
  		<tlNombreRepresentante>
  			<xsl:value-of select="."/>
  		</tlNombreRepresentante>
	</xsl:template>
	
	<xsl:template match="interesados/tlApellido1Representante">
  		<tlApellido1Representante>
  			<xsl:value-of select="."/>
  		</tlApellido1Representante>
	</xsl:template>
	
	<xsl:template match="interesados/tlApellido2Representante">
  		<tlApellido2Representante>
  			<xsl:value-of select="."/>
  		</tlApellido2Representante>
	</xsl:template>

	<xsl:template match="interesados/tlRazonSocialRepresentante">
  		<tlRazonSocialRepresentante>
  			<xsl:value-of select="."/>
  		</tlRazonSocialRepresentante>
	</xsl:template>
	
	<xsl:template match="interesados/cdPaisRepresentante">
  		<cdPaisRepresentante>
  			<xsl:value-of select="."/>
  		</cdPaisRepresentante>
	</xsl:template>
	
	<xsl:template match="interesados/cdProvinciaRepresentante">
  		<cdProvinciaRepresentante>
  			<xsl:value-of select="."/>
  		</cdProvinciaRepresentante>
	</xsl:template>
	
	<xsl:template match="interesados/cdMunicipioRepresentante">
  		<cdMunicipioRepresentante>
  			<xsl:value-of select="."/>
  		</cdMunicipioRepresentante>
	</xsl:template>
	
	<xsl:template match="interesados/tlDireccionRepresentante">
  		<tlDireccionRepresentante>
  			<xsl:value-of select="."/>
  		</tlDireccionRepresentante>
	</xsl:template>
	
	<xsl:template match="interesados/cdCodigoPostalRepresentante">
  		<cdCodigoPostalRepresentante>
  			<xsl:value-of select="."/>
  		</cdCodigoPostalRepresentante>
	</xsl:template>
	
	<xsl:template match="interesados/tlDEHRepresentante">
  		<tlDEHRepresentante>
  			<xsl:value-of select="."/>
  		</tlDEHRepresentante>
	</xsl:template>
	
	<xsl:template match="interesados/tlTelefonoContactoRepresentante">
  		<tlTelefonoContactoRepresentante>
  			<xsl:value-of select="."/>
  		</tlTelefonoContactoRepresentante>
	</xsl:template>
	
	<xsl:template match="interesados/tlCorreoElectronicoRepresentante">
  		<tlCorreoElectronicoRepresentante>
  			<xsl:value-of select="."/>
  		</tlCorreoElectronicoRepresentante>
	</xsl:template>
	
	<xsl:template match="interesados/cdCanalPreferenteRepresentante">
  		<cdCanalPreferenteRepresentante>
  			<xsl:value-of select="."/>
  		</cdCanalPreferenteRepresentante>
	</xsl:template>
	
	<xsl:template match="interesados/tlObservaciones">
  		<tlObservaciones>
  			<xsl:value-of select="."/>
  		</tlObservaciones>
	</xsl:template>

	<xsl:template match="registro/documentos">
		<documentos>
			<xsl:apply-templates/>
		</documentos>
	</xsl:template>
	
	<xsl:template match="documentos/dsNombre">
  		<dsNombre>
  			<xsl:value-of select="."/>
  		</dsNombre>
	</xsl:template>
	
	<xsl:template match="documentos/cdValidez">
  		<cdValidez>
  			<xsl:value-of select="."/>
  		</cdValidez>
	</xsl:template>
	
	<xsl:template match="documentos/cdTipo">
  		<cdTipo>
  			<xsl:value-of select="."/>
  		</cdTipo>
	</xsl:template>
	
	<xsl:template match="documentos/blDocumento">
  		<blDocumento>
  			<xsl:value-of select="."/>
  		</blDocumento>
	</xsl:template>
	
	<xsl:template match="documentos/blFirma">
  		<blFirma>
  			<xsl:value-of select="."/>
  		</blFirma>
	</xsl:template>
	
	<xsl:template match="documentos/cdFirmado">
  		<cdFirmado>
  			<xsl:value-of select="."/>
  		</cdFirmado>
	</xsl:template>
	
	<xsl:template match="documentos/blPKCertificado">
  		<blPKCertificado>
  			<xsl:value-of select="."/>
  		</blPKCertificado>
	</xsl:template>
	
	<xsl:template match="documentos/blTimeStamp">
  		<blTimeStamp>
  			<xsl:value-of select="."/>
  		</blTimeStamp>
	</xsl:template>
	
	<xsl:template match="documentos/blValidacionOCSP">
  		<blValidacionOCSP>
  			<xsl:value-of select="."/>
  		</blValidacionOCSP>
	</xsl:template>
	
	<xsl:template match="documentos/blHash">
  		<blHash>
  			<xsl:value-of select="."/>
  		</blHash>
	</xsl:template>
	
	<xsl:template match="documentos/dsTipoMIME">
  		<dsTipoMIME>
  			<xsl:value-of select="."/>
  		</dsTipoMIME>
	</xsl:template>
	
	<xsl:template match="documentos/tlObservaciones">
  		<tlObservaciones>
  			<xsl:value-of select="."/>
  		</tlObservaciones>
	</xsl:template>

	<xsl:template match="registro/tlResumen">
  		<tlResumen>
  			<xsl:value-of select="."/>
  		</tlResumen>
	</xsl:template>
	
	<xsl:template match="registro/cdAsunto">
  		<cdAsunto>
  			<xsl:value-of select="."/>
  		</cdAsunto>
	</xsl:template>
	
	<xsl:template match="registro/tlReferenciaExterna">
  		<tlReferenciaExterna>
  			<xsl:value-of select="."/>
  		</tlReferenciaExterna>
	</xsl:template>
	
	<xsl:template match="registro/tlNumeroExpediente">
  		<tlNumeroExpediente>
  			<xsl:value-of select="."/>
  		</tlNumeroExpediente>
	</xsl:template>
	
	<xsl:template match="registro/cdTipoTransporte">
  		<cdTipoTransporte>
  			<xsl:value-of select="."/>
  		</cdTipoTransporte>
	</xsl:template>
	
	<xsl:template match="registro/tlNumeroTransporte">
  		<tlNumeroTransporte>
  			<xsl:value-of select="."/>
  		</tlNumeroTransporte>
	</xsl:template>
	
	<xsl:template match="registro/tlNombreUsuario">
  		<tlNombreUsuario>
  			<xsl:value-of select="."/>
  		</tlNombreUsuario>
	</xsl:template>
	
	<xsl:template match="registro/tlContactoUsuario">
  		<tlContactoUsuario>
  			<xsl:value-of select="."/>
  		</tlContactoUsuario>
	</xsl:template>

	<xsl:template match="registro/cdDocumentacionFisicaSoportes">
  		<cdDocumentacionFisicaSoportes>
  			<xsl:value-of select="."/>
  		</cdDocumentacionFisicaSoportes>
	</xsl:template>
	
	<xsl:template match="registro/tlExpone">
  		<tlExpone>
  			<xsl:value-of select="."/>
  		</tlExpone>
	</xsl:template>

	<xsl:template match="registro/tlSolicita">
  		<tlSolicita>
  			<xsl:value-of select="."/>
  		</tlSolicita>
	</xsl:template>

	<xsl:template match="registro/tlObservaciones">
  		<tlObservaciones>
  			<xsl:value-of select="."/>
  		</tlObservaciones>
	</xsl:template>
	
	<xsl:template match="registro/flJustificante">
  		<flJustificante>
  			<xsl:value-of select="."/>
  		</flJustificante>
	</xsl:template>
	
</xsl:stylesheet>
