<?xml version="1.0"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
 	xmlns:pet3="http://intermediacion.redsara.es/scsp/esquemas/V3/peticion"
	xmlns:dat3="http://intermediacion.redsara.es/scsp/esquemas/datosespecificos"
	xmlns:ws="http://ws.funcionario.map.es"
	xmlns:bean="http://bean.cct.map.es"
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
		<xsl:apply-templates/>
	</xsl:template>
	
	<xsl:template match="ws:revocarApoderamientoWS">
		<ws:revocarApoderamientoWS>
			<xsl:apply-templates/>
		</ws:revocarApoderamientoWS>
	</xsl:template>
	
	<xsl:template match="ws:revocarApoderamientoWS/Organismo">
  		<Organismo>
  			<xsl:value-of select="."/>
  		</Organismo>
	</xsl:template>
	
	<xsl:template match="ws:revocarApoderamientoWS/NumRegistro">
  		<NumRegistro>
  			<xsl:value-of select="."/>
  		</NumRegistro>
	</xsl:template>
	
	<xsl:template match="ws:revocarApoderamientoWS/CodApoderamiento">
  		<CodApoderamiento>
  			<xsl:value-of select="."/>
  		</CodApoderamiento>
	</xsl:template>
	
	<xsl:template match="ws:revocarApoderamientoWS/NIF_NIE__Poderdante">
  		<NIF_NIE__Poderdante>
  			<xsl:value-of select="."/>
  		</NIF_NIE__Poderdante>
	</xsl:template>
	
	<xsl:template match="ws:revocarApoderamientoWS/CIF_Poderdante">
  		<CIF_Poderdante>
  			<xsl:value-of select="."/>
  		</CIF_Poderdante>
	</xsl:template>
	
	<xsl:template match="ws:revocarApoderamientoWS/anexos">
  		<anexos>
  			<xsl:apply-templates/>
  		</anexos>
	</xsl:template>
	
	<xsl:template match="ws:revocarApoderamientoWS/anexos/anexo">
  		<anexo>
  			<xsl:apply-templates/>
  		</anexo>
	</xsl:template>
	
	<xsl:template match="ws:revocarApoderamientoWS/anexos/anexo/nombre">
  		<nombre>
  			<xsl:value-of select="."/>
  		</nombre>
	</xsl:template>
	
	<xsl:template match="ws:revocarApoderamientoWS/anexos/anexo/contenido">
  		<contenido>
  			<xsl:value-of select="."/>
  		</contenido>
	</xsl:template>
	
	<xsl:template match="ws:revocarApoderamientoWS/anexos/anexo/firma">
  		<firma>
  			<xsl:value-of select="."/>
  		</firma>
	</xsl:template>
	
</xsl:stylesheet>
