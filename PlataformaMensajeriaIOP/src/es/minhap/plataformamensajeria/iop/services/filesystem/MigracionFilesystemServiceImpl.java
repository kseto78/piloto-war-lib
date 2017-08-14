package es.minhap.plataformamensajeria.iop.services.filesystem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.manager.MigracionFilesystemManager;
import es.minhap.plataformamensajeria.iop.manager.TblAdjuntosHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblAdjuntosManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesAdjuntosHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesAdjuntosManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesHistManager;
import es.minhap.plataformamensajeria.iop.manager.TblMensajesManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.plataformamensajeria.iop.util.UtilCreateFile;
import es.minhap.sim.model.TblAdjuntos;
import es.minhap.sim.model.TblAdjuntosHist;
import es.minhap.sim.model.TblMensajes;
import es.minhap.sim.model.TblMensajesAdjuntos;
import es.minhap.sim.model.TblMensajesAdjuntosHist;
import es.minhap.sim.model.TblMensajesHist;
import es.minhap.sim.model.TblServicios;

/**
 * 
 * @author everis
 * 
 */
@Service("MigracionFilesystemServiceImpl")
public class MigracionFilesystemServiceImpl implements IMigracionFilesystemService {

	private static final Logger LOG = LoggerFactory.getLogger(MigracionFilesystemServiceImpl.class);

	@Resource(name = "MigracionFilesystemManagerImpl")
	private MigracionFilesystemManager filesystemManager;

	@Resource(name = "TblServiciosManagerImpl")
	private TblServiciosManager tblServiciosManager;

	@Resource(name = "TblMensajesManagerImpl")
	private TblMensajesManager tblMensajesManager;

	@Resource(name = "TblMensajesHistManagerImpl")
	private TblMensajesHistManager tblMensajesHistManager;

	@Resource(name = "TblMensajesAdjuntosManagerImpl")
	private TblMensajesAdjuntosManager tblMensajesAdjuntosManager;

	@Resource(name = "TblMensajesAdjuntosHistManagerImpl")
	private TblMensajesAdjuntosHistManager tblMensajesAdjuntosHistManager;

	@Resource(name = "TblAdjuntosManagerImpl")
	private TblAdjuntosManager tblAdjuntosManager;

	@Resource(name = "TblAdjuntosHistManagerImpl")
	private TblAdjuntosHistManager tblAdjuntosHistManager;
	
	@Resource(name = "TblServiciosManagerImpl")
	private TblServiciosManager serviciosManager;

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	@Resource(name = "UtilCreateFile")
	private UtilCreateFile utilFile;
	
	private static final Integer MAX = 1000;

	@Override
	public Boolean modificarMensajes(Long idMensaje, Date fechaInicio, Date fechaFin, Boolean historicos) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String tipoMensajeEmail = ps.getMessage("constantes.TIPO_MENSAJE_EMAIL", null);
		Integer maxLimitCaracterer = Integer.parseInt(ps.getMessage("filesystem.maxTamMensajeBBDD", null));
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

		try {
			Integer count = filesystemManager.countMensajesMigracion(idMensaje, fechaInicio, fechaFin, historicos);
			int indice = 0;
			if (null != count && count > 0){
				while (indice < Math.ceil((double)count / (double)MAX )){
					List<Object> listaMensajes = filesystemManager.obtenerMensajesMigracion(idMensaje, fechaInicio,
							fechaFin, historicos, MAX, indice);
					indice++;
		
					for (Object o : listaMensajes) {
						tratarObjeto(tipoMensajeEmail, maxLimitCaracterer, o);
					}
					LOG.info(
							"[MigracionFilesystemServiceImpl.modificarMensajes] Migrandos " + (indice)*MAX +" Mensajes de " + count);
				}
			}
			LOG.info(
					"[MigracionFilesystemServiceImpl.modificarMensajes] Migrados todos los mensajes ");
			return true;
		} catch (Exception e) {
			LOG.error(
					"[MigracionFilesystemServiceImpl.modificarMensajes] Migrando mensajes. Error Migrando Mensajes ",e);				
			return false;
		}

	}

	private Long tratarObjeto(String tipoMensajeEmail, Integer maxLimitCaracterer, Object o) {
		Long mensajeId;
		if (o instanceof TblMensajes){
			TblMensajes mensaje = (TblMensajes) o;
			TblServicios servicio = serviciosManager.getServicio(tblMensajesManager.getIdServicioByIdMensaje(mensaje.getMensajeid()));
			mensajeId = tratarMensaje(tipoMensajeEmail, maxLimitCaracterer, servicio,mensaje);
		}else{
			TblMensajesHist mensaje = (TblMensajesHist) o;
			TblServicios servicio = tblMensajesHistManager.getServicioByIdMensaje(mensaje.getMensajeid());
			mensajeId = tratarMensajeHist(tipoMensajeEmail, maxLimitCaracterer, servicio, mensaje);
		}
		return mensajeId;
	}

	private Long tratarMensajeHist(String tipoMensajeEmail, Integer maxLimitCaracterer, TblServicios s,
			TblMensajesHist mensaje) {
		Long mensajeId;
		
		mensajeId = mensaje.getMensajeid();
		
		if (null  != mensaje.getCuerpo_clob() &&mensaje.getCuerpo_clob().length() > maxLimitCaracterer) {
			mensaje.setCuerpofile(utilFile.createFileMensaje(mensajeId, mensaje.getCuerpo_clob(), s.getServicioid(),
					s.getConservacion(), mensaje.getFechacreacion()));
			mensaje.setCuerpo(null);
			migrarAdjuntosMensajeHist(tipoMensajeEmail, mensajeId, s.getServicioid(), mensaje, s);
			tblMensajesHistManager.update(mensaje);
		} else {
			mensaje.setCuerpo(mensaje.getCuerpo_clob());
			mensaje.setCuerpofile(null);
			migrarAdjuntosMensajeHist(tipoMensajeEmail, mensajeId, s.getServicioid(), mensaje, s);
			tblMensajesHistManager.update(mensaje);
		}
		return mensajeId;
	}

	private Long tratarMensaje(String tipoMensajeEmail, Integer maxLimitCaracterer, TblServicios s,
			TblMensajes mensaje) {
		Long mensajeId;
		
		mensajeId = mensaje.getMensajeid();
		if (null != mensaje.getCuerpo_clob() && mensaje.getCuerpo_clob().length() > maxLimitCaracterer) {
			mensaje.setCuerpofile(utilFile.createFileMensaje(mensajeId, mensaje.getCuerpo_clob(), s.getServicioid(),
					s.getConservacion(), mensaje.getFechacreacion()));
			mensaje.setCuerpo(null);
			migrarAdjuntosMensaje(tipoMensajeEmail, mensajeId, s.getServicioid(), mensaje, s);
			tblMensajesManager.update(mensaje);
		} else {
			mensaje.setCuerpo(mensaje.getCuerpo_clob());
			mensaje.setCuerpofile(null);
			migrarAdjuntosMensaje(tipoMensajeEmail, mensajeId, s.getServicioid(), mensaje, s);
			tblMensajesManager.update(mensaje);
		}
		return mensajeId;
	}

	private void migrarAdjuntosMensaje(String tipoMensajeEmail, Long mensajeId, Long servicio, TblMensajes mensaje,
			TblServicios s) {
		// si email y adjunto migro adjunto
		if (tipoMensajeEmail.equals(mensaje.getTipomensaje())
				&& tblMensajesAdjuntosManager.countAdjuntosByMensaje(mensaje.getMensajeid()) > 0) {
			for (TblMensajesAdjuntos ma : tblMensajesAdjuntosManager.listaAdjuntosByMensaje(mensajeId)) {
				TblAdjuntos adjunto = tblAdjuntosManager.getAdjuntoById(ma.getTblAdjuntos().getAdjuntoid());
				adjunto.setContenidofile(utilFile.createFileAdjunto(adjunto.getAdjuntoid(), adjunto.getContenido(),
						servicio, s.getConservacion(), adjunto.getFechacreacion()));
				tblAdjuntosManager.update(adjunto);
			}
		}
	}

	private void migrarAdjuntosMensajeHist(String tipoMensajeEmail, Long mensajeId, Long servicio,
			TblMensajesHist mensaje, TblServicios s) {
		// si email y adjunto migro adjunto
		if (tipoMensajeEmail.equals(mensaje.getTipomensaje())
				&& tblMensajesAdjuntosHistManager.countAdjuntosByMensaje(mensaje.getMensajeid()) > 0) {
			for (TblMensajesAdjuntosHist ma : tblMensajesAdjuntosHistManager.listaAdjuntosByMensaje(mensajeId)) {
				TblAdjuntosHist adjunto = tblAdjuntosHistManager.getAdjuntoById(ma.getTblAdjuntosHist().getAdjuntoid());
				adjunto.setContenidofile(utilFile.createFileAdjunto(adjunto.getAdjuntoid(), adjunto.getContenido(),
						servicio, s.getConservacion(), adjunto.getFechacreacion()));
				tblAdjuntosHistManager.update(adjunto);
			}
		}
	}

}
