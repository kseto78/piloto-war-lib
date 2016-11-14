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
		<ns:getEnvios>
			<get_envios>
				<xsl:apply-templates/>
		  	</get_envios>
		</ns:getEnvios>
	</xsl:template>
	
	<xsl:template match="ns:getEnvios/nif">
  		<nif>
  			<xsl:value-of select="."/>
  		</nif>
	</xsl:template>
	
	<xsl:template match="ns:getEnvios/estado">
  		<estado>
  			<xsl:value-of select="."/>
  		</estado>
	</xsl:template>
	
	<xsl:template match="ns:getEnvios/organismo_remisor">
  		<organismo_remisor>
  			<xsl:value-of select="."/>
  		</organismo_remisor>
	</xsl:template>
	
	<xsl:template match="ns:getEnvios/codigo_sia">
  		<codigo_sia>
  			<xsl:value-of select="."/>
  		</codigo_sia>
	</xsl:template>
	
	<xsl:template match="ns:getEnvios/fecha_desde">
  		<fecha_desde>
  			<xsl:value-of select="."/>
  		</fecha_desde>
	</xsl:template>
	
	<xsl:template match="ns:getEnvios/fecha_hasta">
  		<fecha_hasta>
  			<xsl:value-of select="."/>
  		</fecha_hasta>
	</xsl:template>
	
</xsl:stylesheet>
