package es.minhap.plataformamensajeria.iop.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import es.map.sim.negocio.modelo.MensajeJMS;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;

/**
 * 
 * @author everis
 *
 */
public class SIMDLQMessageDispatcher {
	
	private static Logger LOGG = Logger.getLogger(SIMDLQMessageDispatcher.class);
	
	@Resource
	private TblMensajesManager tblMensajesManager;
	
	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	
	/**
	 * Metodo que desencola de la cola de mensajes a anular
	 * @param msg
	 */
	public void dispatchMessage(MensajeJMS msg){
		LOGG.info("Procesando mensaje de la DLQ: " + msg.getIdMensaje());
		try {
			PropertiesServices ps = new PropertiesServices(getReloadableResourceBundleMessageSource());
			String estado = ps.getMessage("constantes.ESTADO_ANULADO", null);
			String descripcion =  ps.getMessage("constantes.descripcionAnularMensaje", null);
			String usuario =  ps.getMessage("constantes.usuarioActiveMQ", null);
			Long mensajeId = Long.parseLong(msg.getIdMensaje());
			List<String> listaDestinatariosMensajes = new ArrayList<>();
			if (null != msg.getDestinatarioMensajeId()){
				listaDestinatariosMensajes = Arrays.asList(msg.getDestinatarioMensajeId().split(";"));
			}
			Integer codEstado;
			if (listaDestinatariosMensajes.size() == 1){
				codEstado = getTblMensajesManager().setEstadoMensaje(mensajeId, estado, descripcion, false, Long.parseLong(listaDestinatariosMensajes.get(0)), null, usuario, null);
			}else{
				codEstado = getTblMensajesManager().setEstadoMensaje(mensajeId, estado, descripcion, false, null, null, usuario, null);
			}
			if (codEstado <= 0)
				throw new Exception();
			
			//elimina los mensajes que estén en esta cola
			LOGG.info("Mensaje DLQ procesado ---->: " + msg.getIdMensaje());
		} catch (Exception e) {
			//Se lanza una excepción para volver a encolar el mensaje.
			LOGG.error("[SIMDLQMessageDispatcher] Error procesando el mensaje de la DLQ", e);
			throw new RuntimeException("");
		}
	}

	/**
	 * @return the tblMensajesManager
	 */
	public TblMensajesManager getTblMensajesManager() {
		return tblMensajesManager;
	}

	/**
	 * @param tblMensajesManager the tblMensajesManager to set
	 */
	public void setTblMensajesManager(TblMensajesManager tblMensajesManager) {
		this.tblMensajesManager = tblMensajesManager;
	}

	/**
	 * @return the reloadableResourceBundleMessageSource
	 */
	public ReloadableResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
		return reloadableResourceBundleMessageSource;
	}

	/**
	 * @param reloadableResourceBundleMessageSource the reloadableResourceBundleMessageSource to set
	 */
	public void setReloadableResourceBundleMessageSource(ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource) {
		this.reloadableResourceBundleMessageSource = reloadableResourceBundleMessageSource;
	}
	
}
