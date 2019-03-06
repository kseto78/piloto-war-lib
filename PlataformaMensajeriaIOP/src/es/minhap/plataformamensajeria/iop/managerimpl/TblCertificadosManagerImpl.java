package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.common.entity.OrderType;
import es.minhap.common.entity.TextComparator;
import es.minhap.plataformamensajeria.iop.manager.TblCertificadosManager;
import es.minhap.plataformamensajeria.iop.manager.TblLogManager;
import es.minhap.plataformamensajeria.iop.manager.TblPdpDiputacionesManager;
import es.minhap.sim.dao.TblCertificadosDAO;
import es.minhap.sim.dao.TblOrganismosDAO;
import es.minhap.sim.model.TblCertificados;
import es.minhap.sim.model.TblLog;
import es.minhap.sim.model.TblOrganismos;
import es.minhap.sim.model.TblServicios;
import es.minhap.sim.query.TblCertificadosQuery;
import es.minhap.sim.query.TblOrganismosQuery;
import es.minhap.sim.query.TblServiciosQuery;

/**
 * 
 * @author everis
 *
 */
@Service("TblCertificadosManagerImpl")
public class TblCertificadosManagerImpl implements TblCertificadosManager {

	@Resource 
	private TblCertificadosDAO certificadosDAO;
	
	@Resource 
	private TblLogManager tblLogManager;
	
		

	@Override
	@Transactional
	public TblCertificados getCertificadosByServicio(Long servicioId) {
		TblCertificadosQuery query = new TblCertificadosQuery();
		
		query.setCaducado(false);
		TblServiciosQuery tblServicio = new TblServiciosQuery();
		tblServicio.setServicioid(servicioId);
		query.setTblServicios(tblServicio);
		
		return certificadosDAO.searchUnique(query);
	}
	



	
}
