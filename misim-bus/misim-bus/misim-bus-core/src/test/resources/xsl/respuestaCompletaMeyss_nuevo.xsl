<?xml version="1.0"?>
<xsl:stylesheet version="2.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:pet3="http://intermediacion.redsara.es/scsp/esquemas/V3/peticion"
    xmlns:res3="http://intermediacion.redsara.es/scsp/esquemas/V3/respuesta"
    xmlns:dat3="http://intermediacion.redsara.es/scsp/esquemas/datosespecificos"
    xmlns:ns1="http://des-carpetaciudadana.redsara.es/micc-bus-webapp/"
    xmlns:ns2="http://des-carpetaciudadana.redsara.es/micc-bus-webapp/respuesta"
    exclude-result-prefixes="ns1 ns2 pet3">

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
    
    <xsl:template match="soapenv:Fault">
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
        <res3:CodigoEstado>000</res3:CodigoEstado>
        <res3:LiteralError>Peticion procesada correctamente.</res3:LiteralError>
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
        <dat3:DatosEspecificos xmlns:dat3="http://intermediacion.redsara.es/scsp/esquemas/datosespecificos">
            <ns2:consultaExpedientes xmlns:ns1="http://des-carpetaciudadana.redsara.es/micc-bus-webapp/" xmlns:ns2="http://des-carpetaciudadana.redsara.es/micc-bus-webapp/respuesta">
                <xsl:apply-templates select="//ns1:consultaExpedientes/ns2:expedientes" />
                <xsl:apply-templates select="//ns1:consultaExpedientes/ns2:hayMasResultados" />
                <xsl:apply-templates select="//ns1:consultaExpedientes/ns2:hayMasResultadosQaa" />
            </ns2:consultaExpedientes>
        </dat3:DatosEspecificos>
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
             <res3:IdTransmision>
                <xsl:value-of select="//pet3:Atributos/pet3:IdPeticion" />
            </res3:IdTransmision>
            <res3:FechaGeneracion>
                <xsl:value-of select="format-dateTime(current-dateTime(), '[Y0001]-[M01]-[D01]T[h1]:[m01]:[s01].[f001][Z]')"/>
            </res3:FechaGeneracion>
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
    </xsl:template>

    <xsl:template match="pet3:FechaGeneracion">
    </xsl:template>
    <!-- Plantilla elementos Transmision -->
    
    <!-- Plantilla Datos Especificos -->
    <xsl:template match="dat3:DatosEspecificos">
    </xsl:template>

     <xsl:template match="//ns1:consultaExpedientes/ns2:expedientes">
          <ns2:expedientes>
             <xsl:apply-templates />
         </ns2:expedientes>
     </xsl:template>
    
    <xsl:template match="ns1:consultaExpedientes/ns2:expedientes/ns2:expediente">
        <ns2:expediente>
            <xsl:apply-templates />
        </ns2:expediente>
    </xsl:template>
    
    <xsl:template match="ns1:consultaExpedientes/ns2:expedientes/ns2:expediente/ns2:identificador">
        <ns2:identificador>
            <xsl:value-of select="." />
        </ns2:identificador>
    </xsl:template>
    
    <xsl:template match="ns1:consultaExpedientes/ns2:expedientes/ns2:expediente/ns2:clasificacion">
        <ns2:clasificacion>
            <xsl:value-of select="." />
        </ns2:clasificacion>
    </xsl:template>
    
    <xsl:template match="ns1:consultaExpedientes/ns2:expedientes/ns2:expediente/ns2:organo">
        <ns2:organo>
            <xsl:value-of select="." />
        </ns2:organo>
    </xsl:template>
    
    <xsl:template match="ns1:consultaExpedientes/ns2:expedientes/ns2:expediente/ns2:fechaPresentacion">
        <ns2:fechaPresentacion>
            <xsl:value-of select="." />
        </ns2:fechaPresentacion>
    </xsl:template>
    
    <xsl:template match="ns1:consultaExpedientes/ns2:expedientes/ns2:expediente/ns2:ejercicio">
        <ns2:ejercicio>
            <xsl:value-of select="." />
        </ns2:ejercicio>
    </xsl:template>
    
    <xsl:template match="ns1:consultaExpedientes/ns2:expedientes/ns2:expediente/ns2:numRegistro">
        <ns2:numRegistro>
            <xsl:value-of select="." />
        </ns2:numRegistro>
    </xsl:template>
    
    <xsl:template match="ns1:consultaExpedientes/ns2:expedientes/ns2:expediente/ns2:fechaInicioExpediente">
        <ns2:fechaInicioExpediente>
            <xsl:value-of select="." />
        </ns2:fechaInicioExpediente>
    </xsl:template>
    
    <xsl:template match="ns1:consultaExpedientes/ns2:expedientes/ns2:expediente/ns2:asunto">
        <ns2:asunto>
            <xsl:value-of select="." />
        </ns2:asunto>
    </xsl:template>
    
    <xsl:template match="ns1:consultaExpedientes/ns2:expedientes/ns2:expediente/ns2:fechaUltimoEstado">
        <ns2:fechaUltimoEstado>
            <xsl:value-of select="." />
        </ns2:fechaUltimoEstado>
    </xsl:template>
    
    <xsl:template match="ns1:consultaExpedientes/ns2:expedientes/ns2:expediente/ns2:pendienteCiudadano">
        <ns2:pendienteCiudadano>
            <xsl:value-of select="." />
        </ns2:pendienteCiudadano>
    </xsl:template>
    
    <xsl:template match="ns1:consultaExpedientes/ns2:expedientes/ns2:expediente/ns2:estadoOrigen">
        <ns2:estadoOrigen>
            <xsl:value-of select="." />
        </ns2:estadoOrigen>
    </xsl:template>
    
    <xsl:template match="ns1:consultaExpedientes/ns2:expedientes/ns2:expediente/ns2:estado">
        <ns2:estado>
            <xsl:value-of select="." />
        </ns2:estado>
    </xsl:template>
    
    <xsl:template match="ns1:consultaExpedientes/ns2:expedientes/ns2:expediente/ns2:interesadosRepresentantes">
        <ns2:interesadosRepresentantes>
            <xsl:apply-templates />
        </ns2:interesadosRepresentantes>
    </xsl:template>
    
    <xsl:template match="ns1:consultaExpedientes/ns2:expedientes/ns2:expediente/ns2:interesadosRepresentantes/ns2:interesadoRepresentante">
        <ns2:interesadoRepresentante>
            <xsl:apply-templates />
        </ns2:interesadoRepresentante>
    </xsl:template>
    
    <xsl:template match="ns1:consultaExpedientes/ns2:expedientes/ns2:expediente/ns2:interesadosRepresentantes/ns2:interesadoRepresentante/ns2:interesado">
        <ns2:interesado>
            <xsl:apply-templates />
        </ns2:interesado>
    </xsl:template>
    
    <xsl:template match="ns1:consultaExpedientes/ns2:expedientes/ns2:expediente/ns2:interesadosRepresentantes/ns2:interesadoRepresentante/ns2:interesado/ns2:docIdentidad">
        <ns2:docIdentidad>
            <xsl:value-of select="." />
        </ns2:docIdentidad>
    </xsl:template>
    
    <xsl:template match="ns1:consultaExpedientes/ns2:expedientes/ns2:expediente/ns2:interesadosRepresentantes/ns2:interesadoRepresentante/ns2:interesado/ns2:nombreORazon">
        <ns2:nombreORazon>
            <xsl:value-of select="." />
        </ns2:nombreORazon>
    </xsl:template>
    
    <xsl:template match="ns1:consultaExpedientes/ns2:expedientes/ns2:expediente/ns2:interesadosRepresentantes/ns2:interesadoRepresentante/ns2:representante">
        <ns2:representante>
            <xsl:apply-templates />
        </ns2:representante>
    </xsl:template>
    
    <xsl:template match="ns1:consultaExpedientes/ns2:expedientes/ns2:expediente/ns2:interesadosRepresentantes/ns2:interesadoRepresentante/ns2:representante/ns2:docIdentidad">
        <ns2:docIdentidad>
            <xsl:value-of select="." />
        </ns2:docIdentidad>
    </xsl:template>
    
    <xsl:template match="ns1:consultaExpedientes/ns2:expedientes/ns2:expediente/ns2:interesadosRepresentantes/ns2:interesadoRepresentante/ns2:representante/ns2:nombreORazon">
        <ns2:nombreORazon>
            <xsl:value-of select="." />
        </ns2:nombreORazon>
    </xsl:template>
    
    <xsl:template match="ns1:consultaExpedientes/ns2:expedientes/ns2:expediente/ns2:observaciones">
        <ns2:observaciones>
            <xsl:value-of select="." />
        </ns2:observaciones>
    </xsl:template>
    
    <xsl:template match="ns1:consultaExpedientes/ns2:expedientes/ns2:expediente/ns2:enlace">
        <ns2:enlace>
            <xsl:value-of select="." />
        </ns2:enlace>
    </xsl:template>
    
    <xsl:template match="//ns1:consultaExpedientes/ns2:hayMasResultados">
        <ns2:hayMasResultados>
            <xsl:value-of select="." />
        </ns2:hayMasResultados>
    </xsl:template>
    
    <xsl:template match="//ns1:consultaExpedientes/ns2:hayMasResultadosQaa">
        <ns2:hayMasResultadosQaa>
            <xsl:value-of select="." />
        </ns2:hayMasResultadosQaa>
    </xsl:template>
        
    <xsl:template match="ns1:consultaExpedientes">
    </xsl:template>
    
</xsl:stylesheet>