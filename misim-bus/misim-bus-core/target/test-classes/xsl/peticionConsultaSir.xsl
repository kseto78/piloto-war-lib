<?xml version="1.0"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
 	xmlns:pet3="http://intermediacion.redsara.es/scsp/esquemas/V3/peticion"
	xmlns:dat3="http://intermediacion.redsara.es/scsp/esquemas/datosespecificos"
	xmlns:impl="http://impl.manager.cct.map.es" 
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
	
	<xsl:template match="impl:recuperarTraza">
		<impl:recuperarTraza>
			<xsl:apply-templates/>
		</impl:recuperarTraza>
	</xsl:template>
	
	<xsl:template match="impl:recuperarTraza/impl:autentication">
		<impl:autentication>
			<xsl:apply-templates/>
		</impl:autentication>
	</xsl:template>
	
	<xsl:template match="impl:autentication/impl:usuario">
  		<bean:usuario>
  			<xsl:value-of select="."/>
  		</bean:usuario>
	</xsl:template>
	
	<xsl:template match="impl:autentication/impl:password">
  		<bean:password>
  			<xsl:value-of select="."/>
  		</bean:password>
	</xsl:template>
	
	<xsl:template match="impl:criterios">
		<impl:criterios>
			<xsl:apply-templates/>
		</impl:criterios>
	</xsl:template>
	
	<xsl:template match="impl:criterios/impl:cdIntercambio">
		<xsl:if test=". != ''">
	  		<bean:cdIntercambio>
	  			<xsl:value-of select="."/>
	  		</bean:cdIntercambio>
	  	</xsl:if>
	</xsl:template>
	
	<xsl:template match="impl:criterios/impl:numRegistro">
		<xsl:if test=". != ''">
	  		<bean:numRegistro>
	  			<xsl:value-of select="."/>
	  		</bean:numRegistro>
	  	</xsl:if>
	</xsl:template>
	
	<xsl:template match="impl:criterios/impl:oficinaRegistroByCdOrOrigen">
		<xsl:if test=". != ''">
	  		<bean:oficinaRegistroByCdOrOrigen>
	  			<xsl:value-of select="."/>
	  		</bean:oficinaRegistroByCdOrOrigen>
	  	</xsl:if>
	</xsl:template>
	
	<xsl:template match="impl:criterios/impl:tipoEstado">
		<xsl:if test=". != ''">
	  		<bean:tipoEstado>
	  			<xsl:value-of select="."/>
	  		</bean:tipoEstado>
	  	</xsl:if>
	</xsl:template>
	
	<xsl:template match="impl:criterios/impl:num_doc_ident">
		<xsl:if test=". != ''">
	  		<bean:num_doc_ident>
	  			<xsl:value-of select="."/>
	  		</bean:num_doc_ident>
	  	</xsl:if>
	</xsl:template>
	
	<xsl:template match="impl:criterios/impl:tipoTraza">
  		<bean:tipoTraza>
  			<xsl:value-of select="."/>
  		</bean:tipoTraza>
	</xsl:template>
	
	<xsl:template match="impl:criterios/impl:fechaInicial">
		<xsl:if test=". != ''">
	  		<bean:fechaInicial>
	  			<xsl:value-of select="."/>
	  		</bean:fechaInicial>
	  	</xsl:if>
	</xsl:template>
	
	<xsl:template match="impl:criterios/impl:fechaFinal">
		<xsl:if test=". != ''">
	  		<bean:fechaFinal>
	  			<xsl:value-of select="."/>
	  		</bean:fechaFinal>
	  	</xsl:if>
	</xsl:template>
	
	<xsl:template match="impl:criterios/impl:historico">
		<xsl:if test=". != ''">
	  		<bean:historico>
	  			<xsl:value-of select="."/>
	  		</bean:historico>
	  	</xsl:if>
	</xsl:template>
	
	<xsl:template match="impl:paginacion">
		<impl:paginacion>
			<xsl:apply-templates/>
		</impl:paginacion>
	</xsl:template>
	
	<xsl:template match="impl:paginacion/impl:posicion_inicial">
  		<bean:posicion_inicial>
  			<xsl:value-of select="."/>
  		</bean:posicion_inicial>
	</xsl:template>
	
	<xsl:template match="impl:paginacion/impl:num_resultados">
  		<bean:num_resultados>
  			<xsl:value-of select="."/>
  		</bean:num_resultados>
	</xsl:template>
	
</xsl:stylesheet>
