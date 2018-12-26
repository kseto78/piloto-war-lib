<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<%@page import="es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties" %>

<div class="mainContent">            
            <h3 class="pageNameButtons">
                <label>USUARIO NO REGISTRADO EN LA PLATAFORMA DE MENSAJERÍA
                </label>
            </h3>
	            <div class="editContainer">
	            	<div class="nameDescriptionError">
	            		<label>&nbsp;</label>
	            	</div>
					            	
			            	<div class="editContentError">
			 					 <span style="font-size:8pt;">	
							<ul class="actionError" id="">
							
		                		<li><span><i><div class="validation Error">
									<p>Ha intentado acceder a Plataforma de Mensajería pero su usuario no está registrado en el sistema o esta inactivo. Por favor, contacte con el administrador.</p>
								</br>
								<p>								
									<a href="<%=PlataformaMensajeriaProperties.getInstance().getProperty("url.caid", "")%>">Incidencias</a> |  <a href="logout">Salir</a></div></i></span></li>
								</p>
							</ul>
							      </span>
			            	</div>
			    
	            </div>
</div>