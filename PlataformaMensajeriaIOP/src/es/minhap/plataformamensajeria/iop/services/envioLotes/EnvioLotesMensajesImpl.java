package es.minhap.plataformamensajeria.iop.services.envioLotes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import de.brendamour.jpasskit.PKField;
import de.brendamour.jpasskit.PassbookGenerator;
import de.brendamour.jpasskit.enums.PKTextAlignment;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.AdjuntosXMLBean;
import es.minhap.plataformamensajeria.iop.beans.EnvioEmailXMLBean;
import es.minhap.plataformamensajeria.iop.beans.EnvioPushXMLBean;
import es.minhap.plataformamensajeria.iop.beans.EnvioSMSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.MensajeSMSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.MensajesXMLBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.AdjuntoPeticionLotesXMLBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.DestinatarioPeticionLotesMailXMLBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.DestinatarioPeticionLotesSMSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.MensajePeticionLotesEmailXMLBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.MensajePeticionLotesPushXMLBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.MensajePeticionLotesSMSXMLBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.PassbookXMLBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.PeticionXMLBean;
import es.minhap.plataformamensajeria.iop.beans.lotes.PkFieldsXMLBean;
import es.minhap.plataformamensajeria.iop.services.envio.IEnvioMensajesService;


@Service("envioLotesMensajesImpl")
public class EnvioLotesMensajesImpl implements IEnvioLotesMensajesService {
	
	private static Logger logger = Logger.getLogger(EnvioLotesMensajesImpl.class);

	private static final String ERROR_GENERACION_PASSBOOK = "Se ha producido un error en la generacion del fichero passbook";
	 
	@Resource(name="envioMensajesImpl")
	private IEnvioMensajesService envioMensajes;
	 
	@Override
	public String enviarLotesEmail(PeticionXMLBean peticionXML, PropertiesServices ps) {
		String res = "";
		
		if (!peticionXML.getMensajes().getMensajeEmail().isEmpty()){
			EnvioEmailXMLBean envioEmail = new EnvioEmailXMLBean();
			
			envioEmail.setUsuario(peticionXML.getUsuario());
			envioEmail.setServicio(peticionXML.getServicio());
			envioEmail.setPassword(peticionXML.getPassword());
			envioEmail.setNombreLote(peticionXML.getNombreLote());
			List<String> erroresGeneracionPassbook = new ArrayList<>();
			for (MensajePeticionLotesEmailXMLBean mensajePeticionLotes : peticionXML.getMensajes().getMensajeEmail()){
				
						
				if (mensajePeticionLotes.getDestinatariosMail()!=null && mensajePeticionLotes.getDestinatariosMail().getDestinatarioMail()!=null && !mensajePeticionLotes.getDestinatariosMail().getDestinatarioMail().isEmpty()){
					MensajesXMLBean mensaje = new MensajesXMLBean();
					for (int i = 0; i< mensajePeticionLotes.getDestinatariosMail().getDestinatarioMail().size(); i++){
						setGenericAttributes(peticionXML, mensajePeticionLotes, mensaje);
						attachedFilesProcess(mensajePeticionLotes, mensaje);
						try {
							attachedPassbookProcess(mensajePeticionLotes, mensaje, ps);
						} catch (Exception e) {
							logger.error(ERROR_GENERACION_PASSBOOK, e);
							erroresGeneracionPassbook.add(ERROR_GENERACION_PASSBOOK);
						}
						DestinatarioPeticionLotesMailXMLBean dest = mensajePeticionLotes.getDestinatariosMail().getDestinatarioMail().get(i);
						mensaje.addDestinatario(dest);
					}
					envioEmail.addMensaje(mensaje);
				}else{
				
					MensajesXMLBean mensaje = new MensajesXMLBean();
					setGenericAttributes(peticionXML, mensajePeticionLotes, mensaje);
					attachedFilesProcess(mensajePeticionLotes, mensaje);
					try {
						attachedPassbookProcess(mensajePeticionLotes, mensaje, ps);
					} catch (Exception e) {
						logger.error(ERROR_GENERACION_PASSBOOK, e);
						erroresGeneracionPassbook.add(ERROR_GENERACION_PASSBOOK);
					}
					envioEmail.addMensaje(mensaje);
				}
				
			}
				
			
			res =  getEnvioMensajes().enviarEmail(envioEmail, erroresGeneracionPassbook);
		}
		
		return res;
	}
	
	@Override
	public String enviarLotesEmail(PeticionXMLBean peticionXML) {
		return enviarLotesEmail(peticionXML, null);
	}
	 
	private void attachedFilesProcess(MensajePeticionLotesEmailXMLBean mensajePeticionLotes, MensajesXMLBean mensaje) {
		if (mensajePeticionLotes.getAdjuntos() != null) {
			for (AdjuntoPeticionLotesXMLBean adjunto : mensajePeticionLotes
					.getAdjuntos().getAdjunto()) {
				AdjuntosXMLBean adjunto1 = new AdjuntosXMLBean();
				if (null != adjunto.getNombre()
						&& adjunto.getNombre().length() > 0
						&& null != adjunto.getContenido()
						&& adjunto.getContenido().length() > 0) {
					adjunto1.setNombre(adjunto.getNombre());
					String contenido = adjunto.getContenido();
					try {
						adjunto1.setContenido(decodeFile(contenido));
					} catch (IOException e) {
						logger.error("Se ha producido un error al convertir el fichero adjunto:" + adjunto.getNombre(), e);
					}
					mensaje.addAdjunto(adjunto1);
				}
			}
		}
	}
	
	private void attachedPassbookProcess(MensajePeticionLotesEmailXMLBean mensajePeticionLotes, MensajesXMLBean mensaje, PropertiesServices ps) throws Exception {
		
		if (mensajePeticionLotes.getPassbook() != null) {
			if (mensaje.getListaAdjuntos() != null) {
				PassbookXMLBean  passbook = mensajePeticionLotes.getPassbook();
				
				String url = passbook.getURL();
				String logoText= passbook.getLogoText();
				String description=passbook.getDescription();
				
				String serialNumber = String.valueOf(System.currentTimeMillis());
				String autheticationToken = Base64.encodeBase64String(serialNumber.getBytes());
				
				String teamIdentifier = ps.getMessage("passbook.teamIdentifier", null, null, null);
				String organizationName = ps.getMessage("passbook.organizationName", null, null, null);
				String passTypeIdentifier = ps.getMessage("passbook.passTypeIdentifier", null, null, null);
				
				String templatePath = ps.getMessage("passbook.templatePath", null, null, null);
				String tempPath = ps.getMessage("passbook.tempPath", null, null, null);
				
				String appleWWDRCA = ps.getMessage("passbook.appleWWDRCA", null, null, null);
				String keyStorePath = ps.getMessage("passbook.keyStorePath", null, null, null);
				String keyStorePassword = ps.getMessage("passbook.keyStorePassword", null, null, null);
				
				String foregroundColor=ps.getMessage("passbook.foregroundColor", null, null, null);
				String backgroundColor=ps.getMessage("passbook.backgroundColor", null, null, null);
				String labelColor=ps.getMessage("passbook.labelColor", null, null, null);
				
				String nombrePassbook=ps.getMessage("passbook.nombreAdjunto", null, null, null);
				
				logger.info("Passbook - Team identifier " + teamIdentifier);
				logger.info("Passbook - Organization name " +organizationName);
				logger.info("Passbook - PassTypeIdentifier " +passTypeIdentifier);
				logger.info("Passbook - Template path " +templatePath);
				logger.info("Passbook - Tmp path " +tempPath);
				logger.info("Passbook - Apple WWDRCA " +appleWWDRCA);
				logger.info("Passbook - KeyStore " +keyStorePath);

				List<PKField> camposPrincipales = getPkFieldsListAlign((passbook.getCamposPrincipales() != null)  ?passbook.getCamposPrincipales().getPkFieldsList() : new ArrayList<PkFieldsXMLBean>());
				List<PKField> camposSecundarios = getPkFieldsListAlign((passbook.getCamposSecundarios()!= null) ? passbook.getCamposSecundarios().getPkFields(): new ArrayList<PkFieldsXMLBean>());
				List<PKField> camposAuxiliares = getPkFieldsListAlign((passbook.getCamposAuxiliares()!= null) ? passbook.getCamposAuxiliares().getPkFields(): new ArrayList<PkFieldsXMLBean>());
				List<PKField> camposCabecera = getPkFieldsList((passbook.getCamposCabecera() != null) ? passbook.getCamposCabecera().getPkFields(): new ArrayList<PkFieldsXMLBean>() );
				List<PKField> camposDetalleTrasera = getPkFieldsList((passbook.getCamposDetalleTrasero()!= null) ? passbook.getCamposDetalleTrasero().getPkFields(): new ArrayList<PkFieldsXMLBean>());
				String pkpassFile = null;
				pkpassFile = PassbookGenerator.generate(camposPrincipales, camposSecundarios, camposAuxiliares, camposCabecera, 
						camposDetalleTrasera, url, logoText, description, backgroundColor, foregroundColor, labelColor,passTypeIdentifier, 
						serialNumber, autheticationToken, teamIdentifier, organizationName, templatePath,appleWWDRCA,keyStorePath,keyStorePassword, tempPath);				AdjuntosXMLBean adjunto = new AdjuntosXMLBean();
				adjunto.setNombre(nombrePassbook);
				File fichero = new File(pkpassFile);
				adjunto.setContenido(getBytesFromFile(fichero));
				mensaje.addAdjunto(adjunto);
				if (fichero != null) {
					fichero.delete();
				}
			}
		}
	}
	
	private byte[] decodeFile(String fichero) throws IOException {
		return Base64.decodeBase64(fichero);
	}

	private void setGenericAttributes(PeticionXMLBean peticionXML, MensajePeticionLotesEmailXMLBean mensajePeticionLotes, MensajesXMLBean mensaje) {
		mensaje.setAsunto(mensajePeticionLotes.getAsunto());
		mensaje.setCuerpo(mensajePeticionLotes.getCuerpo());
		mensaje.setModo(mensajePeticionLotes.getModo());			
		mensaje.setOrigen(mensajePeticionLotes.getOrigen());
		
		mensaje.setCodOrganismo(peticionXML.getCodOrganismo());
		mensaje.setCodSIA(peticionXML.getCodSia());
	}

	private List<PKField> getPkFieldsList(List<PkFieldsXMLBean> pkFieldsList) {
		List<PKField> camposPrincipales = new ArrayList<PKField>();
		for (PkFieldsXMLBean pkFieldXMLField : pkFieldsList) {
			PKField field = new PKField();
			field.setKey(pkFieldXMLField.getKey());
			field.setLabel(pkFieldXMLField.getLabel());
			field.setValue(pkFieldXMLField.getValue());
			camposPrincipales.add(field);
		}
		return camposPrincipales;
	}
	
	private List<PKField> getPkFieldsListAlign(List<PkFieldsXMLBean> pkFieldsList) {
        List<PKField> camposPrincipales = new ArrayList<PKField>();
        for (int i=0; i<pkFieldsList.size();i++) {
            
            PkFieldsXMLBean pkFieldXMLField = pkFieldsList.get(i);
            
            PKField field = new PKField();
            field.setKey(pkFieldXMLField.getKey());
            field.setLabel(pkFieldXMLField.getLabel());
            field.setValue(pkFieldXMLField.getValue());
            
            if(i%2==0){
                field.setTextAlignment(PKTextAlignment.PKTextAlignmentLeft);
            }else{
                field.setTextAlignment(PKTextAlignment.PKTextAlignmentRight);
            }
            
            camposPrincipales.add(field);
        }
        
        return camposPrincipales;
    }

	@Override
	public String enviarLotesSMS(PeticionXMLBean peticionXML) {
		String res ="";
		if (!peticionXML.getMensajes().getMensajeSMS().isEmpty()){
			EnvioSMSXMLBean envioSMS = new EnvioSMSXMLBean();
			
			envioSMS.setUsuario(peticionXML.getUsuario());
			envioSMS.setServicio(peticionXML.getServicio());
			envioSMS.setPassword(peticionXML.getPassword());
			envioSMS.setNombreLote(peticionXML.getNombreLote());
			envioSMS.setOrganismoPagador(peticionXML.getCodOrganismoPagadorSMS());
			for (MensajePeticionLotesSMSXMLBean mensajePeticionLotes : peticionXML.getMensajes().getMensajeSMS()){
			
				MensajeSMSXMLBean mensaje = new MensajeSMSXMLBean();
				mensaje.setCodOrganismo(peticionXML.getCodOrganismo());
				mensaje.setCodSIA(peticionXML.getCodSia());
				mensaje.setCodOrganismoPagador(peticionXML.getCodOrganismoPagadorSMS());
				
				if (mensajePeticionLotes.getDestinatariosSMS()!=null && mensajePeticionLotes.getDestinatariosSMS().getDestinatarioSMS()!=null && !mensajePeticionLotes.getDestinatariosSMS().getDestinatarioSMS().isEmpty()){
					mensaje.setCuerpo(mensajePeticionLotes.getCuerpo());
					
					for (int i = 0; i< mensajePeticionLotes.getDestinatariosSMS().getDestinatarioSMS().size(); i++){
						DestinatarioPeticionLotesSMSXMLBean dest =mensajePeticionLotes.getDestinatariosSMS().getDestinatarioSMS().get(i);
						
						mensaje.addDestinatario(dest);
					}
				}
				
				envioSMS.addMensaje(mensaje);
				
				
			}
			res =  getEnvioMensajes().enviarSMS(envioSMS);
		}
		return res;
	}

	@Override
	public String enviarLotesNotificacion(PeticionXMLBean peticionXML) {
		String res ="";
		
		if (!peticionXML.getMensajes().getMensajePush().isEmpty()){
			EnvioPushXMLBean envioPush = new EnvioPushXMLBean();
			
			envioPush.setUsuario(peticionXML.getUsuario());
			envioPush.setServicio(peticionXML.getServicio());
			envioPush.setPassword(peticionXML.getPassword());
			envioPush.setNombreLote(peticionXML.getNombreLote());
			envioPush.setCodSIA(peticionXML.getCodSia());
			envioPush.setCodOrganismo(peticionXML.getCodOrganismo());
			
			
			for (MensajePeticionLotesPushXMLBean mensajePeticionLotes : peticionXML.getMensajes().getMensajePush()){
				mensajePeticionLotes.setCuerpo(mensajePeticionLotes.getCuerpo().replace("\"", "\\\""));
					envioPush.addMensaje(mensajePeticionLotes);
				
			}
			res =  getEnvioMensajes().enviarNotificacion(envioPush);
		}
		
		return res;
	}
	
	public static byte[] getBytesFromFile(File file) throws IOException {
		long length = file.length();

		byte[] bytes = new byte[(int) length];
		int offset = 0;
		int numRead = 0;

		InputStream is = new FileInputStream(file);
		try {
			while (offset < bytes.length
					&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}
		} finally {
			is.close();
		}

		if (offset < bytes.length) {
			throw new IOException("" + file.getName());
		}
		return bytes;
	}

	/**
	 * @return the envioMensajes
	 */
	public IEnvioMensajesService getEnvioMensajes() {
		return envioMensajes;
	}

	/**
	 * @param envioMensajes the envioMensajes to set
	 */
	public void setEnvioMensajes(IEnvioMensajesService envioMensajes) {
		this.envioMensajes = envioMensajes;
	}
}
