package es.mpr.plataformamensajeria.ws.envio;

import java.util.ArrayList;

import javax.jws.WebService;

import org.apache.log4j.Logger;

import es.mpr.plataformamensajeria.beans.AdjuntosXMLBean;
import es.mpr.plataformamensajeria.beans.DestinatarioXMLBean;
import es.mpr.plataformamensajeria.beans.EnvioEmailXMLBean;
import es.mpr.plataformamensajeria.beans.EnvioSMSXMLBean;
import es.mpr.plataformamensajeria.beans.ImagenXMLBean;
import es.mpr.plataformamensajeria.beans.MensajeSMSXMLBean;
import es.mpr.plataformamensajeria.beans.MensajesXMLBean;
import es.mpr.plataformamensajeria.jdbc.EnvioMensajesDAO;
import es.mpr.plataformamensajeria.response.RespuestaEnvioXMLBean;
import es.mpr.plataformamensajeria.util.WSPlataformaErrors;
import es.mpr.plataformamensajeria.ws.exceptions.PlataformaBusinessException;

@WebService(endpointInterface = "es.mpr.plataformamensajeria.ws.envio.IEnvioMensajesService")
public class EnvioMensajesImpl implements IEnvioMensajesService {
	static Logger logger = Logger.getLogger(EnvioMensajesImpl.class);
	
	@Override
	public String enviarEmail(String xmlEnvio) { 
		logger.debug("[EnviarEmail] XML envioEmail recibido");
		logger.debug(xmlEnvio);
		String xmlRespues = null;
		EnvioEmailXMLBean envioEmail = new EnvioEmailXMLBean();
		EnvioMensajesDAO dao = new EnvioMensajesDAO();
		dao.beginTransaction();
		logger.debug("[EnviarEmail] Iniciando transaccion bbdd");
		ArrayList<String> listaErroresGenerales = new ArrayList<String>();
		ArrayList<String> listaErroresLote = new ArrayList<String>();
		ArrayList<MensajesXMLBean> listaMensajesProcesados = new ArrayList<MensajesXMLBean>();
		Integer idLote = null;
		try {
			
			envioEmail.loadObjectFromXML(xmlEnvio);
			logger.debug("[EnviarEmail] XML procesado correctamente");
			idLote = dao.crearLote(envioEmail.getServicio(), envioEmail.getNombreLote(), envioEmail.getUsuario(), envioEmail.getPassword());
			if(WSPlataformaErrors.getErrorCrearLote(idLote)!=null){
				listaErroresLote.add(WSPlataformaErrors.getErrorCrearLote(idLote));
				xmlRespues = RespuestaEnvioXMLBean.generarXMLRespuestaEmail(idLote, listaMensajesProcesados, listaErroresGenerales,listaErroresLote);
				dao.endTransaction(false);
				dao.closeAll();
				return xmlRespues;
			}
			logger.debug("[EnviarEmail] Lote creado correctamente con ID "+idLote);
			logger.debug("[EnviarEmail] Creando mensajes ");
			
			ArrayList<MensajesXMLBean> listaMensajes = envioEmail.getListadoMensajes();
			for(MensajesXMLBean mensaje : listaMensajes){
				if(mensaje.getModo().equals("1")){
					String to = mensaje.getDestinatarios(DestinatarioXMLBean.TIPO_TO);
					String cc = mensaje.getDestinatarios(DestinatarioXMLBean.TIPO_CC);
					String bcc = mensaje.getDestinatarios(DestinatarioXMLBean.TIPO_BCC);
					logger.debug("[EnviarEmail] MODO 1 - Antes de crearEmail() - Tam Cuerpo: "+mensaje.getCuerpo().length());
					Integer idMensaje = dao.crearEmail(idLote, mensaje.getAsunto(), mensaje.getCuerpo(), 
							mensaje.getDocUsuario(), mensaje.getCodSIA(), mensaje.getCodOrganismo(),
							mensaje.getIdExterno(),mensaje.getFormatoCuerpo(),mensaje.getCodificacion(),mensaje.getPrioridad(), cc, bcc, to, 
							envioEmail.getUsuario(), envioEmail.getPassword());
					logger.debug("[EnviarEmail] MODO 1 - Despues de crearEmail() ");
					mensaje.setIdMensaje(idMensaje.toString());
					
					listaMensajesProcesados.add(mensaje);
					if(WSPlataformaErrors.getErrorCrearEmail(idMensaje)!=null){
						mensaje.addErroresMensaje(idMensaje+"#"+WSPlataformaErrors.getErrorCrearEmail(idMensaje));
					}else{ //Si no hay errores se guardan los adjuntos
						for(AdjuntosXMLBean adjunto : mensaje.getListaAdjuntos()){
							Integer adjuntoId = dao.nuevoAnexo(idMensaje, adjunto.getNombre(), adjunto.getContenido(), 
									envioEmail.getUsuario(), envioEmail.getPassword());
							if(WSPlataformaErrors.getErrorCrearAnexo(adjuntoId)!=null){
								mensaje.addErroresMensaje(adjuntoId+"#"+WSPlataformaErrors.getErrorCrearAnexo(adjuntoId));
							}
						}
						for(AdjuntosXMLBean adjunto : envioEmail.getListadoAdjuntosGenerales()){
							if(adjunto.getIdAdjunto()==null){
								Integer adjuntoId = dao.nuevoAnexo(idMensaje, adjunto.getNombre(), adjunto.getContenido(),
										envioEmail.getUsuario(), envioEmail.getPassword());
								adjunto.setIdAdjunto(adjuntoId);
								if(WSPlataformaErrors.getErrorCrearAnexo(adjuntoId)!=null){
									mensaje.addErroresMensaje(adjuntoId+"#"+WSPlataformaErrors.getErrorCrearAnexo(adjuntoId));
								}

							}else{
								//Integer idAdjuntoAsociar = adjuntosProcesados.get(adjunto.getNombre());
								Integer idAdjuntoAsociar = adjunto.getIdAdjunto();
								Integer salida = dao.asociarAnexo(idMensaje, idAdjuntoAsociar, envioEmail.getUsuario(), envioEmail.getPassword());
								if(WSPlataformaErrors.getErrorAsociarAnexo(salida)!=null){
									mensaje.addErroresMensaje(salida+"#"+WSPlataformaErrors.getErrorAsociarAnexo(salida));
								}
							}						 
						}
						for(ImagenXMLBean imagen : mensaje.getListaImagenes()){
							if(imagen.getIdImagen()==null){
								Integer imagenId = dao.nuevaImagen(idMensaje, imagen.getCid(), imagen.getContenido(), 
										envioEmail.getUsuario(), envioEmail.getPassword());
								if(WSPlataformaErrors.getErrorCrearAnexo(imagenId)!=null){
									mensaje.addErroresMensaje(imagenId+"#"+WSPlataformaErrors.getErrorNuevaImagen(imagenId));
								}
							}else{
								Integer idImagen = imagen.getIdImagen();
								Integer salida = dao.asociarImagen(idMensaje, idImagen, envioEmail.getUsuario(), envioEmail.getPassword());
								if(WSPlataformaErrors.getErrorAsociarAnexo(salida)!=null){
									mensaje.addErroresMensaje(salida+"#"+WSPlataformaErrors.getErrorAsociarImagen(salida));
								}
							}
						}
						
					}
				}else{
					for(String destinatario : mensaje.getListaDestinatariosCompleta()){
						logger.debug("[EnviarEmail] Antes de crearEmail() - Tam Cuerpo: "+mensaje.getCuerpo().length());
						Integer idMensaje = dao.crearEmail(idLote, mensaje.getAsunto(), mensaje.getCuerpo(), 
							mensaje.getDocUsuario(), mensaje.getCodSIA(), mensaje.getCodOrganismo(),
							mensaje.getIdExterno(),mensaje.getFormatoCuerpo(),mensaje.getCodificacion(),mensaje.getPrioridad(), null, null, destinatario,
							envioEmail.getUsuario(), envioEmail.getPassword());
						logger.debug("[EnviarEmail] Despues de crearEmail()");
						mensaje.setIdMensaje(idMensaje.toString());
						MensajesXMLBean mensajeNuevo = new MensajesXMLBean();
						mensajeNuevo.setIdExterno(mensaje.getIdExterno());
						mensajeNuevo.setIdMensaje(mensaje.getIdMensaje());
						listaMensajesProcesados.add(mensajeNuevo);
						if(WSPlataformaErrors.getErrorCrearEmail(idMensaje)!=null){
							mensajeNuevo.addErroresMensaje(idMensaje+"#"+WSPlataformaErrors.getErrorCrearEmail(idMensaje));
						}else{
							for(AdjuntosXMLBean adjunto : mensaje.getListaAdjuntos()){
								if(adjunto.getIdAdjunto()==null){
									Integer adjuntoId = dao.nuevoAnexo(idMensaje, adjunto.getNombre(), adjunto.getContenido(),
											envioEmail.getUsuario(), envioEmail.getPassword());
									adjunto.setIdAdjunto(adjuntoId);
									if(WSPlataformaErrors.getErrorCrearAnexo(adjuntoId)!=null){
										mensajeNuevo.addErroresMensaje(adjuntoId+"#"+WSPlataformaErrors.getErrorCrearAnexo(adjuntoId));
									}
	
								}else{
									//Integer idAdjuntoAsociar = adjuntosProcesados.get(adjunto.getNombre());
									Integer idAdjuntoAsociar = adjunto.getIdAdjunto();
									Integer salida = dao.asociarAnexo(idMensaje, idAdjuntoAsociar, envioEmail.getUsuario(), envioEmail.getPassword());
									if(WSPlataformaErrors.getErrorAsociarAnexo(salida)!=null){
										mensajeNuevo.addErroresMensaje(salida+"#"+WSPlataformaErrors.getErrorAsociarAnexo(salida));
									}
								}
							}
							for(AdjuntosXMLBean adjunto : envioEmail.getListadoAdjuntosGenerales()){
								if(adjunto.getIdAdjunto()==null){
									Integer adjuntoId = dao.nuevoAnexo(idMensaje, adjunto.getNombre(), adjunto.getContenido(),
											envioEmail.getUsuario(), envioEmail.getPassword());
									adjunto.setIdAdjunto(adjuntoId);
									if(WSPlataformaErrors.getErrorCrearAnexo(adjuntoId)!=null){
										mensajeNuevo.addErroresMensaje(adjuntoId+"#"+WSPlataformaErrors.getErrorCrearAnexo(adjuntoId));
									}
	
								}else{
									//Integer idAdjuntoAsociar = adjuntosProcesados.get(adjunto.getNombre());
									Integer idAdjuntoAsociar = adjunto.getIdAdjunto();
									Integer salida = dao.asociarAnexo(idMensaje, idAdjuntoAsociar, envioEmail.getUsuario(), envioEmail.getPassword());
									if(WSPlataformaErrors.getErrorAsociarAnexo(salida)!=null){
										mensajeNuevo.addErroresMensaje(salida+"#"+WSPlataformaErrors.getErrorAsociarAnexo(salida));
									}
								}						
							}	
							for(ImagenXMLBean imagen : mensaje.getListaImagenes()){
								if(imagen.getIdImagen()==null){
									Integer imagenId = dao.nuevaImagen(idMensaje, imagen.getCid(), imagen.getContenido(), 
											envioEmail.getUsuario(), envioEmail.getPassword());
									if(WSPlataformaErrors.getErrorNuevaImagen(imagenId)!=null){
										mensaje.addErroresMensaje(imagenId+"#"+WSPlataformaErrors.getErrorNuevaImagen(imagenId));
									}
								}else{
									Integer idImagen = imagen.getIdImagen();
									Integer salida = dao.asociarImagen(idMensaje, idImagen, envioEmail.getUsuario(), envioEmail.getPassword());
									if(WSPlataformaErrors.getErrorAsociarImagen(salida)!=null){
										mensaje.addErroresMensaje(salida+"#"+WSPlataformaErrors.getErrorAsociarImagen(salida));
									}
								}
							}
							//PROCESAR ADJUNTOS GENERALES
							//TODO: 20120321 - ComprobarEmail
							//dao.comprobarEnvioEmail(idMensaje, envioEmail.getUsuario(), envioEmail.getPassword());
						}
					}
				}
			}
			logger.debug("[EnviarEmail] Creando XML de respuesta");
			xmlRespues = RespuestaEnvioXMLBean.generarXMLRespuestaEmail(idLote, listaMensajesProcesados, listaErroresGenerales,listaErroresLote);
			logger.debug("[EnviarEmail] XML de respuesta generado");
		} catch (Exception e) {
			logger.error("[EnviarEmail] - Se ha producido un error desconocido",e);
			listaErroresGenerales.add("Se ha producido un error desconocido procesando el envio");
			try {
				if(idLote==null){ 
					idLote = new Integer(-99);
				}
				xmlRespues = RespuestaEnvioXMLBean.generarXMLRespuestaEmail(idLote, listaMensajesProcesados, listaErroresGenerales,listaErroresLote);
			} catch (PlataformaBusinessException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			if(xmlRespues!=null&&xmlRespues.length()>0){
				dao.endTransaction(true);
			}else{
				dao.endTransaction(false);
			}
			dao.closeAll();
		}
		return xmlRespues;
	}

	@Override
	public String enviarMensaje(String xmlMensaje) {
		String xmlRespues = null;
		EnvioSMSXMLBean envioSMS = new EnvioSMSXMLBean();
		EnvioMensajesDAO dao = new EnvioMensajesDAO();
		ArrayList<MensajeSMSXMLBean> listaMensajesProcesados = new ArrayList<MensajeSMSXMLBean>();
		ArrayList<String> listaErroresGenerales = new ArrayList<String>();
		ArrayList<String> listaErroresLote = new ArrayList<String>();
		Integer idLote=null;
		dao.beginTransaction();
		try {
			
			envioSMS.loadObjectFromXML(xmlMensaje);
			idLote = dao.crearLote(envioSMS.getServicio(), envioSMS.getNombreLote(), envioSMS.getUsuario(), envioSMS.getPassword());
			if(WSPlataformaErrors.getErrorCrearLote(idLote)!=null){
				listaErroresLote.add(WSPlataformaErrors.getErrorCrearLote(idLote));
				xmlRespues = RespuestaEnvioXMLBean.generarXMLRespuestaSMS(idLote, listaMensajesProcesados, listaErroresGenerales,listaErroresLote);
				dao.endTransaction(false);
				dao.closeAll();
				return xmlRespues;
			}
			ArrayList<MensajeSMSXMLBean> listaMensajes = envioSMS.getListadoMensajes();
			for(MensajeSMSXMLBean mensaje : listaMensajes){
				for(String destinatario : mensaje.getListaDestinatarios()){
					Integer idMensaje = dao.crearSMS(idLote, mensaje.getCuerpo(), mensaje.getDocUsuario(), mensaje.getCodSIA(), 
							mensaje.getCodOrganismo(), mensaje.getCodOrganismoPagador(),mensaje.getIdExterno(), destinatario, 
							envioSMS.getUsuario(), envioSMS.getPassword());
					mensaje.setIdMensaje(idMensaje.toString());
					listaMensajesProcesados.add(mensaje);
					if(WSPlataformaErrors.getErrorCrearSMS(idMensaje)!=null){
						mensaje.addErroresMensaje(idMensaje+"#"+WSPlataformaErrors.getErrorCrearSMS(idMensaje));
					}
				}
			}
			xmlRespues = RespuestaEnvioXMLBean.generarXMLRespuestaSMS(idLote, listaMensajesProcesados, listaErroresGenerales,listaErroresLote);
			dao.connClose();
		} catch (PlataformaBusinessException e) {
			if(idLote==null){
				idLote = new Integer(-99);
			}
			listaErroresGenerales.add("Se ha producido un error procesando el envio");
			xmlRespues = RespuestaEnvioXMLBean.generarXMLRespuestaSMS(idLote, listaMensajesProcesados, listaErroresGenerales,listaErroresLote);
		}finally{
			if(xmlRespues!=null&&xmlRespues.length()>0){
				dao.endTransaction(true);
			}else{
				dao.endTransaction(false);
			}
			dao.closeAll();
		}
		return xmlRespues;
	}
}
