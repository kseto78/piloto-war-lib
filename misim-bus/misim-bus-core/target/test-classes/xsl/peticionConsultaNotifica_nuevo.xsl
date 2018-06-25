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
		<xsl:for-each select="child::*">
			<ns:getEnvios>
				<get_envios>
					<xsl:apply-templates/>
				</get_envios>
			</ns:getEnvios>
		</xsl:for-each>
	</xsl:template>
	
	<xsl:template match="dat3:Object/dat3:Property">
		<xsl:if test="@*='nif'">
		  <nif>
		  	<xsl:value-of select="dat3:SimpleValue/dat3:Value"/>
		  </nif>
		</xsl:if>
		
		<xsl:if test="@*='estado'">
		  <estado>
		  	<xsl:value-of select="dat3:SimpleValue/dat3:Value"/>
		  </estado>
		</xsl:if>
		
		<xsl:if test="@*='organismo_remisor'">
		  <organismo_remisor>
		  	<xsl:value-of select="dat3:SimpleValue/dat3:Value"/>
		  </organismo_remisor>
		</xsl:if>
		
		<xsl:if test="@*='codigo_sia'">
		  <codigo_sia>
		  	<xsl:value-of select="dat3:SimpleValue/dat3:Value"/>
		  </codigo_sia>
		</xsl:if>
		
		<xsl:if test="@*='fecha_desde'">
		  <fecha_desde>
		  	<xsl:value-of select="dat3:SimpleValue/dat3:Value"/>
		  </fecha_desde>
		</xsl:if>
		
		<xsl:if test="@*='fecha_hasta'">
		  <fecha_hasta>
		  	<xsl:value-of select="dat3:SimpleValue/dat3:Value"/>
		  </fecha_hasta>
		</xsl:if>
	</xsl:template>
</xsl:stylesheet>
