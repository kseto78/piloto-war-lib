<?xml version="1.0"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
	xmlns:ns="https://administracionelectronica.gob.es/notifica/ws/notifica/1.0/"
 	xmlns:pet3="http://intermediacion.redsara.es/scsp/esquemas/V3/peticion"
	xmlns:dat3="http://intermediacion.redsara.es/scsp/esquemas/datosespecificos"
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
		<ns:certificacionSede>
			<certificacion_sede>
				<xsl:apply-templates/>
		  	</certificacion_sede>
		</ns:certificacionSede>
	</xsl:template>
	
	<xsl:template match="ns:certificacion_sede/organismo_remisor">
  		<organismo_remisor>
  			<xsl:value-of select="."/>
  		</organismo_remisor>
	</xsl:template>
	
	<xsl:template match="ns:certificacion_sede/documento">
  		<documento>
  			<xsl:value-of select="."/>
  		</documento>
	</xsl:template>
	
	<xsl:template match="ns:certificacion_sede/hash_documento">
  		<hash_documento>
  			<xsl:value-of select="."/>
  		</hash_documento>
	</xsl:template>
	
	<xsl:template match="ns:certificacion_sede/envio_destinatario">
  		<envio_destinatario>
  			<xsl:value-of select="."/>
  		</envio_destinatario>
	</xsl:template>
	
</xsl:stylesheet>
