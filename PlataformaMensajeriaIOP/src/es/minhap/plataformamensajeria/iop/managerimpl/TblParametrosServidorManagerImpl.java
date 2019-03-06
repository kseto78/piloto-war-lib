package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.entity.TextComparator;
import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.beans.PeticionActualizarPasswordCorreo;
import es.minhap.plataformamensajeria.iop.beans.ResponseStatusTypeActualizarPasswordCorreo;
import es.minhap.plataformamensajeria.iop.beans.RespuestaActualizarPasswordCorreo;
import es.minhap.plataformamensajeria.iop.manager.TblLogManager;
import es.minhap.plataformamensajeria.iop.manager.TblParametrosServidorManager;
import es.minhap.plataformamensajeria.iop.manager.TblServidoresManager;
import es.minhap.plataformamensajeria.iop.manager.TblTiposParametrosManager;
import es.minhap.sim.dao.TblParametrosServidorDAO;
import es.minhap.sim.model.TblLog;
import es.minhap.sim.model.TblParametrosServidor;
import es.minhap.sim.model.TblServidores;
import es.minhap.sim.query.TblParametrosServidorQuery;
import es.minhap.sim.query.TblServidoresQuery;
import es.minhap.sim.query.TblTiposParametrosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("tblParametrosServidorManagerImpl")
public class TblParametrosServidorManagerImpl implements TblParametrosServidorManager {

	private static final Logger LOG = LoggerFactory.getLogger(TblParametrosServidorManagerImpl.class);
	
	@Resource 
	private TblParametrosServidorDAO tblParametrosServidorDAO;
	
	@Resource 
	private TblServidoresManager tblServidoresManager;

	@Resource 
	private TblTiposParametrosManager tblTiposParametros;
	
	@Resource TblLogManager tblLogManager;
	
	@Override
	@Transactional
	public List<TblParametrosServidor> getAll() {
		TblParametrosServidorQuery query = new TblParametrosServidorQuery();
		
		return tblParametrosServidorDAO.search(query).getResults();
	}


	@Override
	@Transactional
	public Long insert(TblParametrosServidor parametroServidor, String source, String accion, Long accionId, String descripcion) {
		parametroServidor.setActivo(true);
		parametroServidor.setFechacreacion(new Date());
		
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setAdtusuario(parametroServidor.getCreadopor());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(descripcion + " " + parametroServidor.getTblTiposParametros().getNombre());
		log.setSourceid(parametroServidor.getTblServidores().getServidorid());
		log.setSourcename(source);
		tblLogManager.insertLog(log);
		
		TblServidores servidor = parametroServidor.getTblServidores();
		servidor.setModificadopor(parametroServidor.getCreadopor());
		servidor.setFechamodificacion(new Date());
		tblServidoresManager.update(servidor, null, null, null);
		
		return tblParametrosServidorDAO.insert(parametroServidor);
	}

	@Override
	@Transactional
	public void delete(Long parametroServidorId, String source, String accion, Long accionId, String descripcion) {
		TblParametrosServidor ps = tblParametrosServidorDAO.get(parametroServidorId);
		tblParametrosServidorDAO.delete(parametroServidorId);	
		
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setAdtusuario(ps.getCreadopor());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(descripcion + " " + ps.getTblTiposParametros().getNombre());
		log.setSourceid(ps.getTblServidores().getServidorid());
		log.setSourcename(source);
		tblLogManager.insertLog(log);
		
		TblServidores servidor = ps.getTblServidores();
		servidor.setModificadopor(ps.getCreadopor());
		servidor.setFechamodificacion(new Date());
		tblServidoresManager.update(servidor, null, null, null);
	}

	@Override
	
	public List<TblParametrosServidor> getParametrosPorServidor(Long servidorId, Long tipoParametroId) {
		TblParametrosServidorQuery query = new TblParametrosServidorQuery();
		TblServidoresQuery q = new TblServidoresQuery();
		q.setServidorid(servidorId);
		query.setTblServidores(q);
		
		if (null != tipoParametroId){
			TblTiposParametrosQuery qt = new TblTiposParametrosQuery();
			qt.setTipoparametroid(tipoParametroId);
			query.setTblTiposParametros(qt);
		}
		for (TblParametrosServidor p : tblParametrosServidorDAO.search(query).getResults()) {
			p.setTblServidores(tblServidoresManager.getServidorById(p.getTblServidores().getServidorid()));
			p.setTblTiposParametros(tblTiposParametros.getTipoParametroById(p.getTblTiposParametros().getTipoparametroid()));
		}
		return tblParametrosServidorDAO.search(query).getResults();
		
		
	}


	
	private List<TblParametrosServidor> checkExisteEmail(String usuario, Integer tipoParametro) {
		
		TblParametrosServidorQuery query = new TblParametrosServidorQuery();
		TblTiposParametrosQuery tipoQuery = new TblTiposParametrosQuery();
		
		tipoQuery.setTipoparametroid(tipoParametro.longValue());
		
		query.setValor(usuario);
		query.setValorComparator(TextComparator.EQUALS);
		query.setTblTiposParametros(tipoQuery);
		return tblParametrosServidorDAO.search(query).getResults();
		
	}

	@Override
	@Transactional
	public RespuestaActualizarPasswordCorreo checkActualizarPassword(PeticionActualizarPasswordCorreo peticion,
			PropertiesServices ps) {
		String statusTextKO = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.KO", null);
		String statusTextOK = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.OK", null);
		
		RespuestaActualizarPasswordCorreo respuesta = null;
		List<TblParametrosServidor> listaParametros = checkExisteEmail(peticion.getUsuario(), 
				Integer.parseInt(ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.TIPO_PARAMETRO_USUARIO", null)));
		
		//Comprobamos si existe el email
		if (listaParametros.isEmpty()){
			String statusCodeNoExiste = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.COD_ERROR_NO_EXISTE_EMAIL", null);
			String detailsNoExiste = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.DES_ERROR_NO_EXISTE_EMAIL", null);
			return generarSalida(respuesta, statusTextKO, statusCodeNoExiste, detailsNoExiste);
		}else {
			for (TblParametrosServidor parametro : listaParametros) {
				TblParametrosServidorQuery query = new TblParametrosServidorQuery();
				TblServidoresQuery servidoresQuery = new TblServidoresQuery();
				servidoresQuery.setServidorid(parametro.getTblServidores().getServidorid());
				TblTiposParametrosQuery tipoQuery = new TblTiposParametrosQuery();
				tipoQuery.setTipoparametroid(Long.parseLong(ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.TIPO_PARAMETRO_PASSWORD", null)));
						
				//auditamos
				auditar(parametro.getTblServidores().getServidorid(), peticion.getUsuario(), ps);
				
				//comprobamos la conexion
//				query = new TblParametrosServidorQuery();
//				servidoresQuery = new TblServidoresQuery();
//				servidoresQuery.setServidorid(parametro.getTblServidores().getServidorid());
				query.setTblServidores(servidoresQuery);
//				query.setTblTiposParametros(tipoQuery);
				
				listaParametros = getByQuery(query);
				
				int contador = 0;
				int num = 0 ;
				for (TblParametrosServidor tblParametrosServidor : listaParametros) {
					if(tblParametrosServidor.getTblTiposParametros().getTipoparametroid() == 3){ //El tipo parametro contraseña es el 3
						//Tenemos la posicion de la contrasena
						num = contador;
						LOG.debug("Numero: "+num);
						break;
					}
					contador++;
				}
				
				//cambiamos el password por el nuevo
				String passAntiguo = listaParametros.get(num).getValor();
				listaParametros.get(num).setValor(peticion.getPassword_new());
				try{				
					if (checkConnection(listaParametros)){
						String statusCodeOK = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.COD_CORRECTO", null);
						String detailsOK = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.DES_CORRECTO", null);
						
						
						System.out.println("Contrasena nueva: "+peticion.getPassword_new());
						parametro = listaParametros.get(num);
	//					parametro.setValor(peticion.getPassword_new());
						tblParametrosServidorDAO.update(parametro);
						
						respuesta = generarSalida( respuesta, statusTextOK, statusCodeOK, detailsOK );
					}else{
	//					String statusTextOK = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.KO", null);
						listaParametros.get(num).setValor(passAntiguo);
						String statusCodeNoConnection = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.COD_ERROR_CONEXION", null);
						String detailsNoConnection = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.DES_ERROR_CONEXION", null);
						respuesta = generarSalida(respuesta, statusTextKO, statusCodeNoConnection, detailsNoConnection );
					}
				}catch(AuthenticationFailedException ex){
					String detailsUserPassError = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.DES_ERROR_USERPASS", null);
					String statusCodeUserPass = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.COD_ERROR_USERPASS", null);
					respuesta = generarSalida(respuesta, statusTextKO, statusCodeUserPass, detailsUserPassError);
				}
					
			}	
		}
		
		
		
		return respuesta;
	}
	
	@Override
	public List<TblParametrosServidor> getByQuery(TblParametrosServidorQuery query) {
		return tblParametrosServidorDAO.search(query).getResults();
	}
	
	private RespuestaActualizarPasswordCorreo generarSalida(RespuestaActualizarPasswordCorreo respuesta, String statusTextKO,
			String statusCodeCamposNulos, String detailsCamposNulos) {
		if (null == respuesta){
			RespuestaActualizarPasswordCorreo res = new RespuestaActualizarPasswordCorreo();
			ResponseStatusTypeActualizarPasswordCorreo status = new ResponseStatusTypeActualizarPasswordCorreo();
			
			status.setDetails(detailsCamposNulos);
			status.setStatusCode(statusCodeCamposNulos);
			status.setStatusText(statusTextKO);
			res.setStatus(status);
			return res;
		}else{
			respuesta.getStatus().setDetails(respuesta.getStatus().getDetails() + " | " + detailsCamposNulos);
			respuesta.getStatus().setStatusCode(respuesta.getStatus().getStatusCode() + " | " + statusCodeCamposNulos);
			respuesta.getStatus().setStatusText(respuesta.getStatus().getStatusText() + " | " + statusTextKO);
			return respuesta;
		}
		
	}


	private boolean checkConnection(List<TblParametrosServidor> listaParametros) throws AuthenticationFailedException{
	String port = null;
	String host = null;
	String user = null;
	String pwd = null;
	String conSeg = null;
	String reqAut = null;
	for (TblParametrosServidor p : listaParametros) {
		switch (p.getTblTiposParametros().getTipoparametroid().intValue()) {
		case 1:
			host =p.getValor();
			break;
		case 2:
			user = p.getValor();
			break;
		case 3:
			pwd = p.getValor();
			break;
		case 4:
			conSeg = p.getValor();
			break;
		case 10:
			port = p.getValor();
			break;
		case 11:
			reqAut = p.getValor();
			break;
		default:
			continue;
		}
	}
	
	
	return checkConnectionClassic(host, user, pwd, conSeg, port, reqAut);

	}
	
	
	public Boolean checkConnectionClassic(String ip, String user, String password, String secure, String port,String reqAuth) throws javax.mail.AuthenticationFailedException{
		Boolean res = false;
		Properties props = new Properties();
	     props.put("mail.smtp.host", ip);
	     if(user!=null){
	    	 props.put("mail.stmp.user", user);
	     }	
	     props.put("mail.smtp.auth", (reqAuth==null)?"false":reqAuth); 
	     props.put("mail.smtp.starttls.enable", (secure==null)?"false":secure);
	     if(password!=null){
	    	 props.put("mail.smtp.password",password);
	     }
	     props.put("mail.smtp.port", port);
	     props.put("mail.smtp.timeout", 5000);

	     Session session = Session.getInstance(props);
	     
	     	
	         try {
	           Transport transport = session.getTransport("smtp");

	           transport.connect(ip,new Integer(port),user,password);

	           if(transport.isConnected()){
	        	  return true;
	           }
	         }catch(javax.mail.AuthenticationFailedException exc) {
	        	 throw new javax.mail.AuthenticationFailedException();
//	        	   LOG.info("---Usuario o password incorrectos---");
	         }catch (NumberFormatException e) {
	        	 LOG.info("---Puerto introducido incorrecto---");
	         } catch (MessagingException e) {
	        	 LOG.info("---Mensaje del servidor: " + e.getMessage());
			}
		return res;
	}
	
	
	private void auditar(Long servidorId, String cuenta, PropertiesServices ps){
		String accion = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.auditar.ACCION", null);
		Long accionId = Long.parseLong(ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.auditar.ACCIONID", null));
		String source = ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.auditar.SOURCE", null);
		
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setAdtusuario(ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.auditar.USUARIO", null));
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(ps.getMessage("plataformaErrores.ActualizarPasswordCorreo.auditar.SOURCEID", null)+" "+ cuenta);
		log.setSourceid(servidorId);
		log.setSourcename(source);
		tblLogManager.insertLog(log);
	
	}
}
