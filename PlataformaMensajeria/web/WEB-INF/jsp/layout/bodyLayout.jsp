<%@include file="/WEB-INF/jsp/utils/taglibs.jsp"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%@ page errorPage="errorpage.jsp"%>
<%
	String openerForm = request.getParameter("openerForm");
	boolean windowPopup = (openerForm != null)
			&& (!openerForm.trim().equals(""));
%>

<%-- Tenplate Basico
  Template en el que sea crea una cabecera, un menu a la izquierda, el
  cuerpo y un pie

  @param title Titulo de la pagina
  @param header Cabecera
  @param menu Menu
  @param body Body
  @param footer Pie
--%>

<body>

	<table border="0" width="100%" cellspacing="5">
		<%
			if (!windowPopup) {
		%>
		<tr>
			<td colspan="2"><tiles:insert attribute="header" /></td>
		</tr>
		<%
			}
		%>

		<tr>
			<%
				if (!windowPopup) {
			%>
			<td valign="top"><tiles:insert attribute='menu' /></td>
			<%
				}
			%>
			<td valign="top" align="left">
			<table width="100%">
				<tr>
					<td><html:errors /></td>
				</tr>
				<tr>
					<!-- Links enlace de soporte a la navegacion usuario -->
					<td> 
						<framework:crumb style="cuerpo_central_tituloNivel1"/>
					</td>
				</tr>
				<tr>
					<td><tiles:insert attribute='body' /></td>
				</tr>
			</table>
			</td>
		</tr>

		<%
			if (!windowPopup) {
		%>
		<tr>
			<td colspan="2"><tiles:insert attribute="footer" /></td>
		</tr>
		<%
			}
		%>
	</table>

</body>
