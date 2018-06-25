<?xml version="1.0"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
	xmlns:ns1="https://administracionelectronica.gob.es/notifica/ws/notifica/1.0/"
	xmlns:pet3="http://intermediacion.redsara.es/scsp/esquemas/V3/peticion"
	xmlns:res3="http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta"
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
			<res3:CodigoEstado>243</res3:CodigoEstado>
			<res3:CodigoEstadoSecundario>
				<xsl:apply-templates select="//soapenv:Fault/faultcode"/> - <xsl:apply-templates select="//soapenv:Fault/faultstring"/>
			</res3:CodigoEstadoSecundario>
			<res3:LiteralError>Formato de la petición inválido</res3:LiteralError>
			<res3:TiempoEstimadoRespuesta/>
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
	</xsl:template>
	
	<xsl:template match="pet3:LiteralError">
	</xsl:template>

	<xsl:template match="pet3:CodigoEstadoSecundario">
	</xsl:template>
	
	<xsl:template match="//ns1:getEnviosResponse/return/codigo_respuesta">
		<xsl:value-of select="." />
	</xsl:template>
	
	<xsl:template match="//ns1:getEnviosResponse/return/descripcion_respuesta">
		<xsl:value-of select="." />
	</xsl:template>
	<!-- Plantilla Estado -->
	
	<!-- Plantilla Transmisiones -->
	<xsl:template match="pet3:Solicitudes">
		<res3:Transmisiones/>
	</xsl:template>
	<!-- Plantilla Transmisiones -->
	
	<!-- Plantilla FAULT -->
	<xsl:template match="//soapenv:Fault">
	</xsl:template>
	<!-- Plantilla FAULT -->

</xsl:stylesheet>