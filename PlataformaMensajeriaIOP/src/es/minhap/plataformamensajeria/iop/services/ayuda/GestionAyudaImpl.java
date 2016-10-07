package es.minhap.plataformamensajeria.iop.services.ayuda;

import org.apache.log4j.Logger;

import es.minhap.plataformamensajeria.iop.beans.respuestasServiciosMoviles.Respuesta;
import es.minhap.plataformamensajeria.iop.jdbc.AplicacionDAO;
import es.minhap.plataformamensajeria.iop.jdbc.GestionAyudaDAO;
import es.minhap.plataformamensajeria.iop.services.exceptions.PlataformaBusinessException;


public class GestionAyudaImpl implements IGestionAyudaService {
	static Logger logger = Logger.getLogger(GestionAyudaImpl.class);
	private static final Integer USUARIO_CORRECTO = 1;

	@Override
	public String gestionAyuda(String username, String password) {
		logger.debug("[GestionAyuda] Consultando la ayuda de la app movil");
		String xmlResultado = "";
		Respuesta respuesta = new Respuesta(); 
		AplicacionDAO aplicacionDao = new AplicacionDAO();
		GestionAyudaDAO dao = new GestionAyudaDAO();
		try {
			aplicacionDao.beginTransaction();
			Integer existeUsuario = aplicacionDao.loginUsuario(username, password);
			if(USUARIO_CORRECTO == existeUsuario) {
		    	dao.beginTransaction();
				String ayuda = dao.consultarAyuda();
				dao.endTransaction(true);
				logger.debug("[GestionAyuda] Generando XML de respuesta");
				xmlResultado = respuesta.toXML(ayuda);
				logger.trace(xmlResultado);
			} else{
				xmlResultado = respuesta.loginIncorrectotoXML();
			}
			logger.trace(xmlResultado);
			logger.debug("[GestionAyuda] XML de respuesta generado");
		} catch (PlataformaBusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			aplicacionDao.endTransaction(false);
		} finally{
			dao.closeAll();
			aplicacionDao.closeAll();
		}
		return xmlResultado;
	}
}
