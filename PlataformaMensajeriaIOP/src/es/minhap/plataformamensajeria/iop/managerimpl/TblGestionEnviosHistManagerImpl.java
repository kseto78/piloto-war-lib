package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.manager.TblGestionEnviosHistManager;
import es.minhap.sim.dao.TblGestionEnviosDAO;
import es.minhap.sim.dao.TblGestionEnviosHistDAO;
import es.minhap.sim.model.TblGestionEnvios;
import es.minhap.sim.model.TblGestionEnviosHist;
import es.minhap.sim.query.TblGestionEnviosHistQuery;
import es.minhap.sim.query.TblGestionEnviosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblGestionEnviosHistManagerImpl")
public class TblGestionEnviosHistManagerImpl implements TblGestionEnviosHistManager {

	@Resource 
	private TblGestionEnviosHistDAO tblGestionEnviosHistDAO;
	
	@Resource 
	private TblGestionEnviosDAO tblGestionEnviosDAO;

	/**
	 * @see es.minhap.TblGestionEnviosManager.insertarGestionEnvios
	 */
	@Override
	@Transactional
	public Integer insertarGestionEnvios(TblGestionEnviosHist ge) {
		Integer res;

		res = tblGestionEnviosHistDAO.insert(ge).intValue();

		return res;
	}

	@Override
	@Transactional
	public List<TblGestionEnviosHist> getGestionEnviosByQuery(TblGestionEnviosHistQuery queryGE) {
		return tblGestionEnviosHistDAO.search(queryGE).getResults();
	}
	
	@Override
	@Transactional
	public List<TblGestionEnviosHist> convertGestionEnviosTOGestionEnviosHist(Long idMensaje) {
		List<TblGestionEnviosHist> res = new ArrayList<>();
		TblGestionEnviosQuery query = new TblGestionEnviosQuery();
		query.setMensajeid(idMensaje);
		
		List<TblGestionEnvios> lista = tblGestionEnviosDAO.search(query).getResults();
		
		for (TblGestionEnvios ge : lista) {
			res.add(convertTOHist(ge));
		}
		return res;	
	}
	
	@Override
	@Transactional
	public TblGestionEnviosHist getGestionEnviosById(Long idMensaje) {
		return tblGestionEnviosHistDAO.get(idMensaje);
	}
	
	@Override
	@Transactional
	public void eliminar(Long idGestionEnvioHist) {
		tblGestionEnviosHistDAO.delete(idGestionEnvioHist);
	}
	
	private TblGestionEnviosHist convertTOHist(TblGestionEnvios gestion) {
		TblGestionEnviosHist ge = null;
		if (null != gestion){
			ge = new TblGestionEnviosHist();
			ge.setAnio(gestion.getAnio());
			ge.setAplicacion(gestion.getAplicacion());
			ge.setAplicacionid(gestion.getAplicacionid());
			ge.setCanal(gestion.getCanal());
			ge.setCanalid(gestion.getCanalid());
			ge.setCodigoexterno(gestion.getCodigoexterno());
			ge.setCodorganismo(gestion.getCodorganismo());
			ge.setCodorganismopagador(gestion.getCodorganismopagador());
			ge.setCodsia(gestion.getCodsia());
			ge.setDestinatario(gestion.getDestinatario());
			ge.setDocusuario(gestion.getDocusuario());
			ge.setEstado(gestion.getEstado());
			ge.setEstadoid(gestion.getEstadoid());
			ge.setEstadolote(gestion.getEstadolote());
			ge.setIcono(gestion.getIcono());
			ge.setLoteenvioid(gestion.getLoteenvioid());
			ge.setMensajeid(gestion.getMensajeid());
			ge.setMes(gestion.getMes());
			ge.setNombre(gestion.getNombre());
			ge.setNombreusuario(gestion.getNombreusuario());
			ge.setServicio(gestion.getServicio());
			ge.setServicioid(gestion.getServicioid());
			ge.setServidorid(gestion.getServidorid());
			ge.setSonido(gestion.getSonido());
			ge.setUltimoenvio(gestion.getUltimoenvio());
		}
		return ge;
	}



}
