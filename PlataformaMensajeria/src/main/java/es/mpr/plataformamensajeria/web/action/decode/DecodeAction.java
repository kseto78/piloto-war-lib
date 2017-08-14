package es.mpr.plataformamensajeria.web.action.decode;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.w3c.dom.Document;

import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.Preparable;

import es.mpr.plataformamensajeria.beans.CertificadoBean;
import es.mpr.plataformamensajeria.beans.DecodeBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.CifradoService;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.XMLUtils;

/**
 * <p>
 * Clase Action de Struts2 para la gesti&oacute;n del decodificador.
 * 
 * <p>
 * 
 * @author Everis
 * 
 */

@Controller("decodeAction")
@Scope("prototype")
public class DecodeAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = Logger.getLogger(DecodeAction.class);
	
	@Resource
	private CifradoService cifradoService;
	
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties props;
	
	private DecodeBean decodeBean;
	
	private CertificadoBean certificadoBean;
	
	List<KeyValueObject> comboCertificados = new ArrayList<KeyValueObject>();

	
	@Override
	public void prepare() throws Exception {
		
		comboCertificados = cifradoService.getCertificados();
		
	}

	public String decode() {
		
		if (getRequest().getSession().getAttribute("infoUser") == null) 
			return "noUser";
		
		LOGGER.debug("[DecodeAction] - decode: Inicio");
		
		if (decodeBean.getXmlCifrado()!=null && !("").equals(decodeBean.getXmlCifrado())){
		
			try {
				
				String certificado;
				if(decodeBean.getCertificado()!=null && !"".equals(decodeBean.getCertificado())){
					certificado = decodeBean.getCertificado();
				}else{
					certificado = props.getProperty("decode.keystore.alias.defecto",null);
				}
			
				decodeBean=descifradoFichero(decodeBean, certificado);
				
			}catch (Exception e){
				LOGGER.error("[DecodeAction] Error al descifrar el XML: ",e);
				addActionErrorSession(this.getText("plataforma.decodificador.decodeDescifrar.error"));
			}
			
		}else{
			addActionErrorSession(this.getText("plataforma.decodificador.decodeNoXML.error"));
		}
		
		return SUCCESS;
		
	}


	/**
	 * Método de descifrado de una peticion
	 * @param decodeBean
	 * @param ps
	 * @param certificado
	 * @return
	 * @throws FactoryConfigurationError
	 * @throws Exception
	 */
	public DecodeBean descifradoFichero(DecodeBean decodeBean,
			String certificado) throws FactoryConfigurationError, Exception {
		String xmlCifrado = decodeBean.getXmlCifrado();

		final DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		
		final Document docOriginal = XMLUtils.xml2doc(xmlCifrado, Charset.forName("UTF-8"));
		
		LOGGER.debug("[DecodeAction] - decode: XML a decodificar: " + xmlCifrado);

		String keystoreType = props.getProperty("decode.keystore.type", null);
		String keystore = props.getProperty("decode.keystore.path", null);
		String keystorePassword = props.getProperty("decode.keystore.password", null);
		String alias = props.getProperty("decode.keystore.alias." + certificado, null);
		String aliasPassword = props.getProperty("decode.keystore.alias.password." + certificado, null);
		
		// Desciframos el documento cifrado
		Document docDescifrado = cifradoService.descifrarKey(
				docOriginal,
				keystoreType,
				keystorePassword,
				alias,
				aliasPassword,
				keystore);

		docDescifrado = cifradoService.descifrar(
				docDescifrado,
				keystoreType,
				keystorePassword,
				alias,
				aliasPassword,
				keystore);
		
		String xmlDescifrado = XMLUtils.dom2xml(docDescifrado);
		decodeBean.setXmlDescifrado(xmlDescifrado);
		
		LOGGER.debug("[DecodeController] - decode: XML decodificado: " + xmlDescifrado);
		return decodeBean;
	}


	/**
	 * Metodo para la gestion de la pagina de Consulta de Consumidores
	 * @return
	 */
	public String limpiar() {
		
		if (getRequest().getSession().getAttribute("infoUser") == null) return "noUser";
		
		LOGGER.debug("[DecodeAction] - limpiar: Inicio");
		
		if ((decodeBean.getXmlCifrado()!=null && !("").equals(decodeBean.getXmlCifrado())) || 
				(decodeBean.getXmlDescifrado()!=null && !("").equals(decodeBean.getXmlDescifrado()))){
			
			try {
				decodeBean.setXmlCifrado(null);
				decodeBean.setXmlDescifrado(null);
			}
			catch (Exception e){
				LOGGER.error("[DecodeAction] Error al limpiar el XML: ",e);
				addActionErrorSession(this.getText("plataforma.decodificador.decodeLimpiar.error"));
			}
		}
		LOGGER.debug("[DecodeAction] - limpiar: Fin");
		
		return SUCCESS;
		
	}
	
	public DecodeBean getDecodeBean() {
		return decodeBean;
	}

	public void setDecodeBean(DecodeBean decodeBean) {
		this.decodeBean = decodeBean;
	}
	
	public CertificadoBean getCertificadoBean() {
		return certificadoBean;
	}

	public void setCertificadoBean(CertificadoBean certificadoBean) {
		this.certificadoBean = certificadoBean;
	}

	public List<KeyValueObject> getComboCertificados() {
		return comboCertificados;
	}

	public void setComboCertificados(List<KeyValueObject> comboCertificados) {
		this.comboCertificados = comboCertificados;
	}
	
	public CifradoService getCifradoService() {
		return cifradoService;
	}


	public void setCifradoService(CifradoService cifradoService) {
		this.cifradoService = cifradoService;
	}

}