package es.minhap.plataformamensajeria.iop.managerimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.minhap.plataformamensajeria.iop.beans.OrganismosServicioBean;
import es.minhap.plataformamensajeria.iop.manager.TblLogManager;
import es.minhap.plataformamensajeria.iop.manager.TblOrganismosServicioManager;
import es.minhap.sim.dao.TblOrganismosServicioDAO;
import es.minhap.sim.model.TblLog;
import es.minhap.sim.model.TblOrganismosServicio;
import es.minhap.sim.query.TblOrganismosServicioQuery;

/**
 * 
 * @author everis
 *
 */
@Service("tblOrganismosServicioManagerImpl")
public class TblOrganismosServicioManagerImpl implements TblOrganismosServicioManager {

	@Resource
	private TblOrganismosServicioDAO tblOrganismosServicioDAO;
	
	
	@Resource 
	private TblLogManager tblLogManager;
	
	@Override
	@Transactional
	public List<OrganismosServicioBean> getOrganismosServicioByQuery(TblOrganismosServicioQuery query) {
		List<OrganismosServicioBean> res = new ArrayList<>();
				
		List<TblOrganismosServicio> lista = tblOrganismosServicioDAO.search(query).getResults();
		for (TblOrganismosServicio osTO : lista) {
			OrganismosServicioBean osBean = createOsBean(osTO);
			res.add(osBean);
		}
		return res;
	}

	@Override
	@Transactional
	public Long insert(TblOrganismosServicio servicioOrganismosTO, String source, String accion, Long accionId, String descripcion) {
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setAdtusuario(servicioOrganismosTO.getCreadopor());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(descripcion + " " + servicioOrganismosTO.getTblOrganismos().getOrganismoid());
		log.setSourceid(servicioOrganismosTO.getTblServicios().getServicioid());
		log.setSourcename(source);
		tblLogManager.insertLog(log);
		
		return tblOrganismosServicioDAO.insert(servicioOrganismosTO);
	}
	
	@Override
	@Transactional
	public OrganismosServicioBean getOrganismoServicioById(Integer servicioOrganismoId) {
		return createOsBean(tblOrganismosServicioDAO.get(servicioOrganismoId.longValue()));
	}

	@Override
	@Transactional
	public void delete(Integer servicioOrganismoId, String source, String accion, Long accionId, String descripcion ) {
		TblOrganismosServicio os = tblOrganismosServicioDAO.get(servicioOrganismoId.longValue());
		tblOrganismosServicioDAO.delete(servicioOrganismoId.longValue());
		
		TblLog log = new TblLog();
		log.setAdtfecha(new Date());
		log.setAdtusuario(os.getCreadopor());
		log.setLogaccion(accionId);
		log.setLogdescripcion(accion);
		log.setSourcedescription(descripcion + " " + os.getTblOrganismos().getOrganismoid());
		log.setSourceid(os.getTblServicios().getServicioid());
		log.setSourcename(source);
		tblLogManager.insertLog(log);		
	}

	
	private OrganismosServicioBean createOsBean(TblOrganismosServicio osTO) {
		OrganismosServicioBean res = new OrganismosServicioBean();
		res.setCreadoPor(osTO.getCreadopor());
		res.setDescripcionOrganismo(osTO.getTblOrganismos().getDescripcion());
		res.setDescripcionServicio(osTO.getTblServicios().getDescripcion());
		res.setDIR3Organismo(osTO.getTblOrganismos().getDir3());
		res.setFechaCreacion(osTO.getFechacreacion());
		res.setFechaModificacion(osTO.getFechamodificacion());
		res.setModificadoPor(osTO.getModificadopor());
		res.setNombreOrganismo(osTO.getTblOrganismos().getNombre());
		res.setNombreServicio(osTO.getTblServicios().getNombre());
		res.setOrganismoId(osTO.getTblOrganismos().getOrganismoid().intValue());
		res.setServicioId(osTO.getTblServicios().getServicioid().intValue());
		res.setServicioOrganismoId(osTO.getServicioorganismoid().intValue());
		
		return res;
	}

	
	


	


}
