<?xml version="1.0"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
	xmlns:ns1="https://administracionelectronica.gob.es/notifica/ws/notifica/1.0/"
 	xmlns:res3="http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta"
	xmlns:dat3="http://intermediacion.redsara.es/scsp/esquemas/datosespecificos"
	exclude-result-prefixes="ns1">
	
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
			<Respuesta xmlns="http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta">
				<xsl:apply-templates/>
			</Respuesta>
		</soapenv:Body>
	</xsl:template>
	
	<xsl:template match="ns1:getEnviosResponse/return">
		<res3:Atributos>
<!-- 			<res3:IdPeticion></res3:IdPeticion> -->
<!-- 			<res3:NumElementos></res3:NumElementos> -->
<!-- 			<res3:TimeStamp></res3:TimeStamp> -->
			<res3:Estado>
				<res3:CodigoEstado>
					<xsl:apply-templates select="codigo_respuesta"/>
				</res3:CodigoEstado>
				<res3:LiteralError>
					<xsl:apply-templates select="descripcion_respuesta"/>
				</res3:LiteralError>
			</res3:Estado>
<!-- 			<res3:CodigoCertificado></res3:CodigoCertificado> -->
		</res3:Atributos>
	</xsl:template>
	
	<xsl:template match="ns1:getEnviosResponse/return/resultadoGetEnvios">
		<res3:Transmisiones>
			<res3:TransmisionDatos>
<!-- 				<res3:DatosGenericos> -->
<!-- 					<res3:Emisor> -->
<!-- 						<res3:NifEmisor></res3:NifEmisor> -->
<!-- 						<res3:NombreEmisor></res3:NombreEmisor> -->
<!-- 					</res3:Emisor> -->
<!-- 					<res3:Solicitante> -->
<!-- 						<res3:IdentificadorSolicitante></res3:IdentificadorSolicitante> -->
<!-- 						<res3:NombreSolicitante></res3:NombreSolicitante> -->
<!-- 						<res3:UnidadTramitadora></res3:UnidadTramitadora> -->
<!-- 						<res3:Procedimiento> -->
<!-- 							<res3:CodProcedimiento></res3:CodProcedimiento> -->
<!-- 							<res3:NombreProcedimiento></res3:NombreProcedimiento> -->
<!-- 						</res3:Procedimiento> -->
<!-- 						<res3:Finalidad></res3:Finalidad> -->
<!-- 						<res3:Consentimiento></res3:Consentimiento> -->
<!-- 						<res3:Funcionario> -->
<!-- 							<res3:NombreCompletoFuncionario></res3:NombreCompletoFuncionario> -->
<!-- 							<res3:NifFuncionario></res3:NifFuncionario> -->
<!-- 						</res3:Funcionario> -->
<!-- 						<res3:IdExpediente></res3:IdExpediente> -->
<!-- 					</res3:Solicitante> -->
<!-- 					<res3:Titular> -->
<!-- 						<res3:TipoDocumentacion></res3:TipoDocumentacion> -->
<!-- 						<res3:Documentacion></res3:Documentacion> -->
<!-- 						<res3:NombreCompleto></res3:NombreCompleto> -->
<!-- 						<res3:Nombre></res3:Nombre> -->
<!-- 						<res3:Apellido1></res3:Apellido1> -->
<!-- 						<res3:Apellido2></res3:Apellido2> -->
<!-- 					</res3:Titular> -->
<!-- 					<res3:Transmision> -->
<!-- 						<res3:CodigoCertificado></res3:CodigoCertificado> -->
<!-- 						<res3:IdSolicitud></res3:IdSolicitud> -->
<!-- 						<res3:IdTransmision></res3:IdTransmision> -->
<!-- 						<res3:FechaGeneracion></res3:FechaGeneracion> -->
<!-- 					</res3:Transmision> -->
<!-- 				</res3:DatosGenericos> -->
				<xsl:for-each select="child::*">
					<dat3:DatosEspecificos
						xmlns:dat3="http://intermediacion.redsara.es/scsp/esquemas/datosespecificos">
						<dat3:Object>
							<xsl:apply-templates />
						</dat3:Object>
					</dat3:DatosEspecificos>
				</xsl:for-each>
			</res3:TransmisionDatos>
		</res3:Transmisiones>
  	</xsl:template>
	
	<xsl:template match="item/envio_destinatario">
		<dat3:Property name ="envio_destinatario">
			<dat3:Value>
				<xsl:apply-templates/>
			</dat3:Value>
		</dat3:Property>
	</xsl:template>
	<xsl:template match="item/estado">
		<dat3:Property name ="estado">
			<dat3:Value>
				<xsl:apply-templates/>
			</dat3:Value>
		</dat3:Property>
	</xsl:template>
	<xsl:template match="item/concepto">
		<dat3:Property name ="concepto">
			<dat3:Value>
				<xsl:apply-templates/>
			</dat3:Value>
		</dat3:Property>
	</xsl:template>
	<xsl:template match="item/organismo_remisor">
		<dat3:Property name ="organismo_remisor">
			<dat3:Value>
				<xsl:apply-templates/>
			</dat3:Value>
		</dat3:Property>
	</xsl:template>
	<xsl:template match="item/fecha_notificacion">
		<dat3:Property name ="fecha_notificacion">
			<dat3:Value>
				<xsl:apply-templates/>
			</dat3:Value>
		</dat3:Property>
	</xsl:template>

</xsl:stylesheet>
