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
    
    <xsl:template match="ws:consultaApoderamientoWS">
        <ws:consultaApoderamientoWS>
            <xsl:apply-templates/>
        </ws:consultaApoderamientoWS>
    </xsl:template>
    
    <xsl:template match="ws:consultaApoderamientoWS/CodApoderamiento">
          <CodApoderamiento>
              <xsl:value-of select="."/>
          </CodApoderamiento>
    </xsl:template>
    
    <xsl:template match="ws:consultaApoderamientoWS/NIFPoderdante">
          <NIFPoderdante>
              <xsl:value-of select="."/>
          </NIFPoderdante>
    </xsl:template>
    
    <xsl:template match="ws:consultaApoderamientoWS/CIFPoderdante">
          <CIFPoderdante>
              <xsl:value-of select="."/>
          </CIFPoderdante>
    </xsl:template>
    
    <xsl:template match="ws:consultaApoderamientoWS/NIFApoderado">
          <NIFApoderado>
              <xsl:value-of select="."/>
          </NIFApoderado>
    </xsl:template>
    
    <xsl:template match="ws:consultaApoderamientoWS/CIFApoderado">
          <CIFApoderado>
              <xsl:value-of select="."/>
          </CIFApoderado>
    </xsl:template>
            
            
    <xsl:template match="ws:consultaApoderamientoWS/idOrganismo">
          <idOrganismo>
              <xsl:value-of select="."/>
          </idOrganismo>
    </xsl:template>
    
    <xsl:template match="ws:consultaApoderamientoWS/idTramiteREA">
          <idTramiteREA>
              <xsl:value-of select="."/>
          </idTramiteREA>
    </xsl:template>
    
    <xsl:template match="ws:consultaApoderamientoWS/idTramiteOrganismo">
          <idTramiteOrganismo>
              <xsl:value-of select="."/>
          </idTramiteOrganismo>
    </xsl:template>
        
    <xsl:template match="ws:consultaApoderamientoWS/idCategoriaREA">
          <idCategoriaREA>
              <xsl:value-of select="."/>
          </idCategoriaREA>
    </xsl:template>
    
    <xsl:template match="ws:consultaApoderamientoWS/estado">
          <estado>
              <xsl:value-of select="."/>
          </estado>
    </xsl:template>
    
    <xsl:template match="ws:consultaApoderamientoWS/fechaInicio">
          <fechaInicio>
              <xsl:value-of select="."/>
          </fechaInicio>
    </xsl:template>
    
    <xsl:template match="ws:consultaApoderamientoWS/fechaFin">
          <fechaFin>
              <xsl:value-of select="."/>
          </fechaFin>
    </xsl:template>
    
</xsl:stylesheet>
