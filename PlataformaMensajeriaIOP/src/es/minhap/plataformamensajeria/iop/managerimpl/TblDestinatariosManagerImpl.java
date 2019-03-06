package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.properties.PropertiesServices;
import es.minhap.plataformamensajeria.iop.manager.TblDestinatariosManager;
import es.minhap.sim.dao.TblDestinatariosDAO;
import es.minhap.sim.model.TblDestinatarios;
import es.minhap.sim.query.TblDestinatariosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblDestinatariosManagerImpl")
public class TblDestinatariosManagerImpl implements TblDestinatariosManager {

	@Resource
	private TblDestinatariosDAO destinatariosDAO;

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;
	
	/**
	 * @see es.minhap.TblDestinatariosManager.getEstadoByName
	 */
	@Override
	@Transactional
	public List<Long> setDestinatarios(Long mensajeId, String to, String cc, String bcc, String usuario) {
		PropertiesServices ps = new PropertiesServices(getReloadableResourceBundleMessageSource());
		
		ArrayList<Long> listaIds = new ArrayList<>();
		List<String> lista;
		if (null != to){
		// analizamos destinatarios TO
		lista = Arrays.asList(to.split(";"));
		for (String email : lista) {
			TblDestinatarios d = createDestinatario(mensajeId, usuario, email, ps.getMessage("constantes.email.tipoDestinatarioTO",null));
			listaIds.add(getDestinatariosDAO().insert(d));
		}
		}

		// analizamos destinatarios CC
		if (null != cc){
		lista = Arrays.asList(cc.split(";"));
		for (String email : lista) {
			TblDestinatarios d = createDestinatario(mensajeId, usuario, email, ps.getMessage("constantes.email.tipoDestinatarioCC",null));
			listaIds.add(getDestinatariosDAO().insert(d));
		}
		}

		// analizamos destinatarios BCC
		if (null != bcc){
		lista = Arrays.asList(bcc.split(";"));
		for (String email : lista) {
			TblDestinatarios d = createDestinatario(mensajeId, usuario, email, ps.getMessage("constantes.email.tipoDestinatarioBCC",null));
			listaIds.add(getDestinatariosDAO().insert(d));
		}
		}
		return listaIds;
	}
	
	@Override
	@Transactional
	public List<String> getDestinatarios(Long mensajeId) {
		List<String> res = new ArrayList<>();
		TblDestinatariosQuery query = new TblDestinatariosQuery();
		query.setMensajeid(mensajeId);
		
		for (TblDestinatarios d : getDestinatariosDAO().search(query).getResults()) {
			if (!res.contains(d.getEmail()))
				res.add(d.getEmail());
		}
		
		return res;
	}

	@Override
	@Transactional
	public void delete(Long destinatarioid) {
		destinatariosDAO.delete(destinatarioid);
	}
	
	@Override
	public TblDestinatarios getDestinatario(Long destinatarioId) {
		return destinatariosDAO.get(destinatarioId);
	}

	/**
	 * @param mensajeId
	 * @param usuario
	 * @param email
	 * @return
	 */
	private TblDestinatarios createDestinatario(Long mensajeId, String usuario, String email, String tipo) {
		TblDestinatarios d = new TblDestinatarios();
		d.setCreadopor(usuario);
		d.setFechacreacion(new Date());
		d.setMensajeid(mensajeId);
		d.setEmail(email);
		d.setTipodestinatario(tipo);
		return d;
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

	/**
	 * @return the destinatariosDAO
	 */
	public TblDestinatariosDAO getDestinatariosDAO() {
		return destinatariosDAO;
	}

	/**
	 * @param destinatariosDAO the destinatariosDAO to set
	 */
	public void setDestinatariosDAO(TblDestinatariosDAO destinatariosDAO) {
		this.destinatariosDAO = destinatariosDAO;
	}



}
