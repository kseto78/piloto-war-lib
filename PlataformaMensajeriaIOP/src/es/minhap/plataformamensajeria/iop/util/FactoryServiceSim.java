package es.minhap.plataformamensajeria.iop.util;

import es.minhap.plataformamensajeria.iop.services.ayuda.GestionAyudaImpl;
import es.minhap.plataformamensajeria.iop.services.ayuda.IGestionAyudaService;
import es.minhap.plataformamensajeria.iop.services.envio.EnvioMensajesImpl;
import es.minhap.plataformamensajeria.iop.services.envio.IEnvioMensajesService;
import es.minhap.plataformamensajeria.iop.services.envioLotes.EnvioLotesMensajesImpl;
import es.minhap.plataformamensajeria.iop.services.envioLotes.IEnvioLotesMensajesService;
import es.minhap.plataformamensajeria.iop.services.envioPremium.EnvioPremiumGISSImpl;
import es.minhap.plataformamensajeria.iop.services.envioPremium.EnvioPremiumImpl;
import es.minhap.plataformamensajeria.iop.services.envioPremium.IEnvioPremiumGISSService;
import es.minhap.plataformamensajeria.iop.services.envioPremium.IEnvioPremiumService;
import es.minhap.plataformamensajeria.iop.services.gestionNotificacionesPush.GestionNotificacionesPushImpl;
import es.minhap.plataformamensajeria.iop.services.gestionNotificacionesPush.IGestionNotificacionesPushService;
import es.minhap.plataformamensajeria.iop.services.gestionServiciosPush.GestionServiciosPushServiceImpl;
import es.minhap.plataformamensajeria.iop.services.gestionServiciosPush.IGestionServiciosPushService;
import es.minhap.plataformamensajeria.iop.services.operaciones.IOperacionesMensajesService;
import es.minhap.plataformamensajeria.iop.services.operaciones.OperacionesMensajesImpl;
import es.minhap.plataformamensajeria.iop.services.procesarSAMLResponse.GestionSAMLRequestServiceImpl;
import es.minhap.plataformamensajeria.iop.services.procesarSAMLResponse.GestionSAMLResponseServiceImpl;
import es.minhap.plataformamensajeria.iop.services.procesarSAMLResponse.GetAuthServiceImpl;
import es.minhap.plataformamensajeria.iop.services.procesarSAMLResponse.IGestionSAMLRequestService;
import es.minhap.plataformamensajeria.iop.services.procesarSAMLResponse.IGestionSAMLResponseService;
import es.minhap.plataformamensajeria.iop.services.procesarSAMLResponse.IGetAuthService;
import es.minhap.plataformamensajeria.iop.services.recepcion.IRecepcionMensajesService;
import es.minhap.plataformamensajeria.iop.services.recepcion.IRecepcionMensajesServiceImpl;
import es.minhap.plataformamensajeria.iop.services.recepcionEstadoSMS.IRecepcionEstadoSMSService;
import es.minhap.plataformamensajeria.iop.services.recepcionEstadoSMS.RecepcionEstadoSMSImpl;
import es.minhap.plataformamensajeria.iop.services.seguimiento.ISeguimientoMensajesService;
import es.minhap.plataformamensajeria.iop.services.seguimiento.SeguimientoMensajesImpl;
import es.minhap.plataformamensajeria.iop.services.usuariosplataformas.IRegistroUsuarioPushService;
import es.minhap.plataformamensajeria.iop.services.usuariosplataformas.RegistroUsuarioPushServiceImpl;

public class FactoryServiceSim {
	
    private IEnvioMensajesService instanceMensajes = new EnvioMensajesImpl();
    private ISeguimientoMensajesService instanceSeguimiento = new SeguimientoMensajesImpl();
    private IOperacionesMensajesService instanceOperaciones = new OperacionesMensajesImpl();
    private IRegistroUsuarioPushService instanceUsuario = new RegistroUsuarioPushServiceImpl();
    private IRecepcionMensajesService instanceRecepcionMensajes = new IRecepcionMensajesServiceImpl();
    private IRecepcionEstadoSMSService instanceRecepcionEstado = new RecepcionEstadoSMSImpl();
    private IEnvioLotesMensajesService instanceLotes = new EnvioLotesMensajesImpl();
    private IGestionAyudaService instanceAyuda = new GestionAyudaImpl();
    private IGestionServiciosPushService instanceServiciosPush = new GestionServiciosPushServiceImpl();
    private IGestionNotificacionesPushService instanceNotificacionesPush = new GestionNotificacionesPushImpl();
    private IEnvioPremiumService instancePremium = new EnvioPremiumImpl();
    private IEnvioPremiumGISSService instanceGISS = new EnvioPremiumGISSImpl();
    private IGestionSAMLResponseService instanceSAML = new GestionSAMLResponseServiceImpl();
    private IGestionSAMLRequestService instanceSAMLRequest = new GestionSAMLRequestServiceImpl();
    private IGetAuthService instanceGetAuth = new GetAuthServiceImpl();
    
    public static FactoryServiceSim instance = new  FactoryServiceSim();
    
    public static FactoryServiceSim getInstance()  {
    	return instance;
    }

	public IEnvioMensajesService getInstanceMensajes() {
		return instanceMensajes;
	}

	public ISeguimientoMensajesService getInstanceSeguimiento() {
		return instanceSeguimiento;
	}

	public IOperacionesMensajesService getInstanceOperaciones() {
		return instanceOperaciones;
	}

	public IRegistroUsuarioPushService getInstanceUsuario() {
		return instanceUsuario;
	}

	public IRecepcionMensajesService getInstanceRecepcionMensajes(){
		return instanceRecepcionMensajes;
	}
	
	public IRecepcionEstadoSMSService getInstanceRecepcionEstado(){
		return instanceRecepcionEstado;
	}

	public IEnvioLotesMensajesService getInstanceLotes() {
		return instanceLotes;
	}

	public IGestionAyudaService getInstanceAyuda() {
		return instanceAyuda;
	}

	public IGestionServiciosPushService getInstanceServiciosPush() {
		return instanceServiciosPush;
	}

	public IGestionNotificacionesPushService getInstanceNotificacionesPush() {
		return instanceNotificacionesPush;
	}
	public IEnvioPremiumService getInstancePremium() {
		return instancePremium;
	}
	
	public IEnvioPremiumGISSService getInstanceGISS() {
		return instanceGISS;
	}
	
	public IGestionSAMLResponseService getInstanceSAML() {
		return instanceSAML;
	}
	
	public IGestionSAMLRequestService getInstanceSAMLRequest() {
		return instanceSAMLRequest;
	}
	
	public IGetAuthService getInstanceGetAuth(){
		return instanceGetAuth;
	}
}
