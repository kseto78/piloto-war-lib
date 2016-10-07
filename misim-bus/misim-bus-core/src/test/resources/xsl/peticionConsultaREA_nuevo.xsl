<?xml version="1.0"?>
<xsl:stylesheet version="2.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
     xmlns:pet3="http://intermediacion.redsara.es/scsp/esquemas/V3/peticion"
    xmlns:dat3="http://intermediacion.redsara.es/scsp/esquemas/datosespecificos"
    xmlns:ws="http://ws.funcionario.map.es/"
    exclude-result-prefixes="pet3 dat3 ws">
    
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
    
    <xsl:template match="ws:consultaApoderamiento">
        <ws:consultaApoderamiento xmlns:ws="http://ws.funcionario.map.es/">
            <xsl:apply-templates/>
        </ws:consultaApoderamiento>
    </xsl:template>
    
    <xsl:template match="ws:consultaApoderamiento/CodApoderamiento">
          <CodApoderamiento>
              <xsl:value-of select="."/>
          </CodApoderamiento>
    </xsl:template>
    
    <xsl:template match="ws:consultaApoderamiento/NIFPoderdante">
          <NIFPoderdante>
              <xsl:value-of select="."/>
          </NIFPoderdante>
    </xsl:template>
    
    <xsl:template match="ws:consultaApoderamiento/CIFPoderdante">
          <CIFPoderdante>
              <xsl:value-of select="."/>
          </CIFPoderdante>
    </xsl:template>
    
    <xsl:template match="ws:consultaApoderamiento/NIFApoderado">
          <NIFApoderado>
              <xsl:value-of select="."/>
          </NIFApoderado>
    </xsl:template>
    
    <xsl:template match="ws:consultaApoderamiento/CIFApoderado">
          <CIFApoderado>
              <xsl:value-of select="."/>
          </CIFApoderado>
    </xsl:template>
            
    <xsl:template match="ws:consultaApoderamiento/idOrganismo">
          <idOrganismo>
              <xsl:value-of select="."/>
          </idOrganismo>
    </xsl:template>
    
    <xsl:template match="ws:consultaApoderamiento/idTramiteREA">
          <idTramiteREA>
              <xsl:value-of select="."/>
          </idTramiteREA>
    </xsl:template>
    
    <xsl:template match="ws:consultaApoderamiento/idTramiteOrganismo">
          <idTramiteOrganismo>
              <xsl:value-of select="."/>
          </idTramiteOrganismo>
    </xsl:template>
        
    <xsl:template match="ws:consultaApoderamiento/idCategoriaREA">
          <idCategoriaREA>
              <xsl:value-of select="."/>
          </idCategoriaREA>
    </xsl:template>
    
    <xsl:template match="ws:consultaApoderamiento/Estado">
          <Estado>
              <xsl:value-of select="."/>
          </Estado>
    </xsl:template>
    
    <xsl:template match="ws:consultaApoderamiento/fechaInicio">
          <fechaInicio>
              <xsl:value-of select="."/>
          </fechaInicio>
    </xsl:template>
    
    <xsl:template match="ws:consultaApoderamiento/fechaFin">
          <fechaFin>
              <xsl:value-of select="."/>
          </fechaFin>
    </xsl:template>
    
</xsl:stylesheet>
