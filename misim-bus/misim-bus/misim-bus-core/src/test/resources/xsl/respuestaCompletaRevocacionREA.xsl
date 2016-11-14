<?xml version="1.0"?>
<xsl:stylesheet version="2.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:pet3="http://intermediacion.redsara.es/scsp/esquemas/V3/peticion"
    xmlns:res3="http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta"
    xmlns:dat3="http://intermediacion.redsara.es/scsp/esquemas/datosespecificos"
    xmlns:ns1="http://ws.funcionario.map.es"
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
            <xsl:value-of select="." />
        </res3:IdPeticion>
    </xsl:template>

    <xsl:template match="pet3:NumElementos">
        <res3:NumElementos>
            <xsl:value-of select="." />
        </res3:NumElementos>
    </xsl:template>

    <xsl:template match="pet3:TimeStamp">
        <res3:TimeStamp>
            <xsl:value-of select="format-dateTime(current-dateTime(), '[Y0001]-[M01]-[D01]T[h1]:[m01]:[s01].[f001][Z]')"/>
        </res3:TimeStamp>
    </xsl:template>
    
    <xsl:template match="pet3:Estado">
        <res3:Estado>
            <xsl:apply-templates />
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
        <xsl:apply-templates select="//ns1:revocarApoderamientoWSResponse/Salida/Error/CodError"/> 
    </xsl:template>

    <xsl:template match="//ns1:revocarApoderamientoWSResponse/Salida/Error/CodError">
        <res3:CodigoEstado>
            <xsl:choose>
                <xsl:when test=". = '0'">000</xsl:when>
                <xsl:when test=". = '13'">000</xsl:when>
                <xsl:otherwise><xsl:value-of select="." /></xsl:otherwise>
            </xsl:choose>
        </res3:CodigoEstado>
    </xsl:template>

    <xsl:template match="pet3:CodigoEstadoSecundario">
        <res3:CodigoEstadoSecundario>
            <xsl:value-of select="." />
        </res3:CodigoEstadoSecundario>
    </xsl:template>
    
    <xsl:template match="pet3:LiteralError">
        <xsl:apply-templates select="//ns1:revocarApoderamientoWSResponse/Salida/Error/DesError"/>
    </xsl:template>
    
    <xsl:template match="//ns1:revocarApoderamientoWSResponse/Salida/Error/DesError">
        <res3:LiteralError>
            <xsl:value-of select="." />
        </res3:LiteralError>
    </xsl:template>
    
    <xsl:template match="pet3:TiempoEstimadoRespuesta">
        <res3:TiempoEstimadoRespuesta>
            <xsl:value-of select="." />
        </res3:TiempoEstimadoRespuesta>
    </xsl:template>
    <!-- Plantilla Estado -->
    
    <!-- Plantilla Transmisiones -->
    <xsl:template match="pet3:Solicitudes">
        <res3:Transmisiones>
            <xsl:apply-templates />
        </res3:Transmisiones>
    </xsl:template>

    <!-- Plantilla TransmisionDatos -->
    <xsl:template match="pet3:SolicitudTransmision">
        <res3:TransmisionDatos>
            <xsl:apply-templates select="//pet3:DatosGenericos" />
            <xsl:apply-templates select="//ns1:revocarApoderamientoWSResponse/Salida" />
        </res3:TransmisionDatos>
    </xsl:template>
    
        <!-- Plantilla DatosGenericos -->
    <xsl:template match="//pet3:DatosGenericos">
        <res3:DatosGenericos>
            <xsl:apply-templates />
        </res3:DatosGenericos>
    </xsl:template>
    
    <!-- Plantilla elementos Emisor -->
    <xsl:template match="pet3:Emisor">
        <res3:Emisor>
            <xsl:apply-templates />
        </res3:Emisor>
    </xsl:template>

    <xsl:template match="pet3:NombreEmisor">
        <res3:NombreEmisor>
            <xsl:value-of select="." />
        </res3:NombreEmisor>
    </xsl:template>

    <xsl:template match="pet3:NifEmisor">
        <res3:NifEmisor>
            <xsl:value-of select="." />
        </res3:NifEmisor>
    </xsl:template>
    <!-- Plantillas elementos Emisor -->
    
    <!-- Plantilla elementos Solicitante -->
    <xsl:template match="pet3:Solicitante">
        <res3:Solicitante>
            <xsl:apply-templates />
        </res3:Solicitante>
    </xsl:template>
    
    <xsl:template match="pet3:IdentificadorSolicitante">
        <res3:IdentificadorSolicitante>
            <xsl:value-of select="." />
        </res3:IdentificadorSolicitante>
    </xsl:template>
    
    <xsl:template match="pet3:NombreSolicitante">
        <res3:NombreSolicitante>
            <xsl:value-of select="." />
        </res3:NombreSolicitante>
    </xsl:template>
    
    <xsl:template match="pet3:UnidadTramitadora">
        <res3:UnidadTramitadora>
            <xsl:value-of select="." />
        </res3:UnidadTramitadora>
    </xsl:template>
    
    <xsl:template match="pet3:Procedimiento">
        <res3:Procedimiento>
            <xsl:apply-templates />
        </res3:Procedimiento>
    </xsl:template>
    
    <xsl:template match="pet3:CodProcedimiento">
        <res3:CodProcedimiento>
            <xsl:value-of select="." />
        </res3:CodProcedimiento>
    </xsl:template>
    
    <xsl:template match="pet3:NombreProcedimiento">
        <res3:NombreProcedimiento>
            <xsl:value-of select="." />
        </res3:NombreProcedimiento>
    </xsl:template>
    
    <xsl:template match="pet3:Finalidad">
        <res3:Finalidad>
            <xsl:value-of select="." />
        </res3:Finalidad>
    </xsl:template>
    
    <xsl:template match="pet3:Consentimiento">
        <res3:Consentimiento>
            <xsl:value-of select="." />
        </res3:Consentimiento>
    </xsl:template>
    
    <xsl:template match="pet3:Funcionario">
        <res3:Funcionario>
            <xsl:apply-templates />
        </res3:Funcionario>
    </xsl:template>
    
    <xsl:template match="pet3:NombreCompletoFuncionario">
        <res3:NombreCompletoFuncionario>
            <xsl:value-of select="." />
        </res3:NombreCompletoFuncionario>
    </xsl:template>
    
    <xsl:template match="pet3:NifFuncionario">
        <res3:NifFuncionario>
            <xsl:value-of select="." />
        </res3:NifFuncionario>
    </xsl:template>
    
    <xsl:template match="pet3:IdExpediente">
        <res3:IdExpediente>
            <xsl:value-of select="." />
        </res3:IdExpediente>
    </xsl:template>
    <!-- Plantilla elementos Solicitante -->
    
    <!-- Plantillas elementos Titular -->
    <xsl:template match="pet3:Titular">
        <res3:Titular>
            <xsl:apply-templates />
        </res3:Titular>
    </xsl:template>
    <xsl:template match="pet3:TipoDocumentacion">
        <res3:TipoDocumentacion>
            <xsl:value-of select="." />
        </res3:TipoDocumentacion>
    </xsl:template>
    <xsl:template match="pet3:Documentacion">
        <res3:Documentacion>
            <xsl:value-of select="." />
        </res3:Documentacion>
    </xsl:template>
    <xsl:template match="pet3:NombreCompleto">
        <res3:NombreCompleto>
            <xsl:value-of select="." />
        </res3:NombreCompleto>
    </xsl:template>
    <xsl:template match="pet3:Nombre">
        <res3:Nombre>
            <xsl:value-of select="." />
        </res3:Nombre>
    </xsl:template>
    <xsl:template match="pet3:Apellido1">
        <res3:Apellido1>
            <xsl:value-of select="." />
        </res3:Apellido1>
    </xsl:template>
    <xsl:template match="pet3:Apellido2">
        <res3:Apellido2>
            <xsl:value-of select="." />
        </res3:Apellido2>
    </xsl:template>
    <!-- Plantillas elementos Titular -->

    <!-- Plantilla elementos Transmision -->
    <xsl:template match="pet3:Transmision">
        <res3:Transmision>
            <xsl:apply-templates />
        </res3:Transmision>
    </xsl:template>
    
    <xsl:template match="pet3:Transmision/pet3:CodigoCertificado">
        <res3:CodigoCertificado>
            <xsl:value-of select="." />
        </res3:CodigoCertificado>
    </xsl:template>

    <xsl:template match="pet3:IdSolicitud">
        <res3:IdSolicitud>
            <xsl:value-of select="." />
        </res3:IdSolicitud>
    </xsl:template>

    <xsl:template match="pet3:IdTransmision">
        <res3:IdTransmision>
            <xsl:value-of select="." />
        </res3:IdTransmision>
    </xsl:template>

    <xsl:template match="pet3:FechaGeneracion">
        <res3:FechaGeneracion>
            <xsl:value-of select="." />
        </res3:FechaGeneracion>
    </xsl:template>
    <!-- Plantilla elementos Transmision -->
    
    <!-- Plantilla Datos Especificos -->
    <xsl:template match="dat3:DatosEspecificos">
    </xsl:template>
    
    <xsl:template match="//ns1:revocarApoderamientoWSResponse/Salida/Valido">
        <dat3:DatosEspecificos xmlns:dat3="http://intermediacion.redsara.es/scsp/esquemas/datosespecificos">
            <revocarApoderamientoWSResponse>
                <Salida>
                    <Valido>
                        <xsl:apply-templates />
                    </Valido>
                </Salida>
            </revocarApoderamientoWSResponse>
        </dat3:DatosEspecificos>
    </xsl:template>
    
    <xsl:template match="ns1:revocarApoderamientoWSResponse/Salida/Valido/NumRegistro">
        <NumRegistro>
            <xsl:apply-templates />
        </NumRegistro>
    </xsl:template>
    
    <xsl:template match="ns1:revocarApoderamientoWSResponse/Salida/Valido/DocumentoJustificante">
        <DocumentoJustificante>
            <xsl:apply-templates />
        </DocumentoJustificante>
    </xsl:template>
    
    <xsl:template match="ns1:revocarApoderamientoWSResponse/Salida/Valido/codOrganismo">
        <codOrganismo>
            <xsl:apply-templates />
        </codOrganismo>
    </xsl:template>
    
    <xsl:template match="ns1:revocarApoderamientoWSResponse/Salida/Valido/estado">
        <estado>
            <xsl:apply-templates />
        </estado>
    </xsl:template>
    
    <xsl:template match="ns1:revocarApoderamientoWSResponse/Salida/Valido/codApoderamiento">
        <codApoderamiento>
            <xsl:apply-templates />
        </codApoderamiento>
    </xsl:template>
    
    <xsl:template match="ns1:revocarApoderamientoWSResponse/Salida/Error">
    </xsl:template>
   
    <xsl:template match="ns1:revocarApoderamientoWSResponse">
    </xsl:template>
    
</xsl:stylesheet>