
<head>
		

		<style type="text/css">
		textarea {
			width: 100%;
			height: 100px;
		}
		</style>
</head>
<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<plataforma:securityRedirect isAction="true" redirectTo="permisoDenegado"  allowedTo="ROL_ADMINISTRADOR">
	<script>
		document.location.href="permisoDenegado.action";
	</script>
</plataforma:securityRedirect>

<!-- <div class="menu_cabecera">
	<h1><framework:crumb/></h1><br>
</div>-->

<!-- ************************** -->
<!-- Criterios de la busqueda   -->
<!-- ************************** -->

        <div class="mainContent">	
		<%@include file="/WEB-INF/jsp/plataforma/validation/validadoresJS.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/errorForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/successForm.jsp" %>
		<%@include file="/WEB-INF/jsp/plataforma/validation/fieldErrorForm.jsp" %>
             <div class="criteria">
		<s:form id="frmEjecutarJob" method="POST" action="seleccionarJob" validate="false" theme="css_xhtml">            	
                    
                    <p class="criteria">
                 	 <span>
                       <label style="width: 100px;" class="fieldText">Job a Ejecutar:</label>
                       <s:select 
						id="jobBean.nombreJob" name="jobBean.nombreJob" 
						emptyOption="false" theme="simple"
						labelposition="left" onchange="cargarNuevoJob()"
						list="comboJobs" listKey="codigo" headerKey="" 
						listValue="descripcion" cssClass="W240" 
						value="%{jobBean.nombreJob}" disabled="false" />     	      	
           	      	</span>
                      
                    
           	      </p> 
            	 <p class="criteria">
                 	 <span>
                       <label style="width: 100px;" class="fieldText">Servicio:</label>
                       <s:select 
						id="jobBean.servicioId" name="jobBean.servicioId" 
						emptyOption="false" theme="simple" 
						labelposition="left"
						list="comboServicios" listKey="codigo" headerKey="" headerValue="Todos"
						listValue="descripcion" cssClass="W240" 
						value="%{jobBean.servicioId}" disabled="false" />     	      	
           	      	</span>
                    <span>
                     <label style="width: 100px;" class="fieldText"> Hasta Fecha:</label>
                      <s:textfield
								name="jobBean.fecha" value="%{jobBean.fecha}" id="jobBean.fecha"
								theme="simple"  style="width:60px;"
								size="10" maxlength="10"
								cssClass="datepickerEstadisticas">
								<s:param name="value">
    							<s:date name="jobBean.fecha" format="dd/MM/yyyy"/>
  						</s:param>
						</s:textfield>
           	      	</span>                    
                    
           	      </p> 
                
                <div class="footerCriteria">
                    <span class="leftSide"></span>
                    <span class="rightSide">
                       <s:submit  theme="simple" value="%{getText('buttons.text.ejecutar')}" cssClass="button"/>
                    </span>
                </div>
            </div>
           </s:form>
           		<textarea id="subscription"></textarea>
		<textarea id="message"></textarea>


   <script>
	function cargarNuevoJob() {              
        document.frmEjecutarJob.action="cargarServiciosConservacionEvent.action";
	    document.frmEjecutarJob.submit();

	}


</script>        