package es.mpr.template.web.action.afirma;

import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import es.mpr.j2ee.afirma.dss.ResponseAfirmaVerify;
import es.mpr.j2ee.afirma.services.IVerifyService;
import es.mpr.j2ee.afirma.services.SignServiceImpl;

/**
 * <p>Clase controladora del registro de la firma de un formulario</p>
 * 
 * @author Altran
 *
 */
@Controller
public class FirmaWebAction extends ActionSupport {
	
	private static Log logger = LogFactory.getLog(FirmaWebAction.class);
	
	 /** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	//Datos del formulario
	private String nombre;	
	private String apellidos;
	private String firmaUsuario;
	private String resultado;
	
	//Servicio de validar firmas
	protected IVerifyService servicio;

	/**
	 * Realiza la verificaci&oacute;n de una firma electr&oacute;nica
	 * 
	 * Devuelve la propiedad <i>resultado</i> en formato JSON
	 * 
	 */
	public String execute()
	{
		//if(request.getSession().getAttribute("infoUser")==null) return "noUser"; 
		HashMap<String,Object> input = new HashMap<String,Object>();
		input.put(SignServiceImpl.PARAMS_IDAPLICACION,"mpr.dgiae.fwkv3");
		input.put(SignServiceImpl.PARAMS_ALIASFIRMANTE,"mpr.dgiae.fwkv3.firmante2");
		input.put(SignServiceImpl.PARAMS_REQUESTID, "1111");
		input.put(SignServiceImpl.PARAMS_DOCUMENT_BASE64,firmaUsuario.getBytes());
		input.put(SignServiceImpl.PARAMS_DOCUMENT_TYPE,SignServiceImpl.DOCUMENT_TYPE_XML); //Se puede obtener a partir del parametro PARAMS_SIGNATURETYPE
		input.put(SignServiceImpl.PARAMS_SIGNATURE_TYPE,SignServiceImpl.SIGNATURE_TYPE_XADES);
				
		try {
			ResponseAfirmaVerify response = (ResponseAfirmaVerify) servicio.verificaFirma(input);		
			
			logger.info("Resultado correcto: " + response.getResultadoCorrecto());
			
			if (response.getResultObject()!=null && response.getResultObject().getResultMessage()!=null)
			{
				resultado = response.getResultObject().getResultMessage().getValue();
			}
		} 
		catch (Exception e) {
			
			logger.error(e);
			
			if (e!=null && e.getCause()!=null)
			{
				resultado = e.getCause().getMessage();
			}
			else
				resultado = e.getMessage();
			
			return ERROR;
		}
				
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return Campo nombre del formulario
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el campo nombre del formulario
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * 
	 * @return Campo Apellidos del formulario
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Establece el campo Apellidos del formulario
	 * 
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * @return La firma elect&oacute;nica realizada por el usuario con el applet cliente de @Firma
	 */
	public String getFirmaUsuario() {
		return firmaUsuario;
	}

	/**
	 * Establece la propiedad firmaUsuario
	 * 
	 * @param firmaUsuario
	 */
	public void setFirmaUsuario(String firmaUsuario) {
		this.firmaUsuario = firmaUsuario;
	}

	/**
	 * 
	 * @return Servicio de verificaci&oacute;n de firma electr&oacute;nica
	 */
	public IVerifyService getServicio() {
		return servicio;
	}

	/**
	 * Establece la propiedad servicio
	 * 
	 * @param servicio
	 */
	public void setServicio(IVerifyService servicio) {
		this.servicio = servicio;
	}

	/**
	 * 
	 * @return Resultado de la verificaci&oacute;n de la firma
	 */
	public String getResultado() {
		return resultado;
	}

	/**
	 * Establece la propiedad resultado
	 * 
	 * @param resultado
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
			
}
