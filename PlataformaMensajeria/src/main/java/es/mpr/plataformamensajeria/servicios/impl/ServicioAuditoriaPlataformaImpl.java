package es.mpr.plataformamensajeria.servicios.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.minhap.plataformamensajeria.iop.manager.TblLogManager;
import es.minhap.sim.model.TblLog;
import es.mpr.plataformamensajeria.beans.AuditoriaPlataformaBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioAuditoriaPlataforma;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;

/**
 * <p>
 * Maneja la persistencia y b&uacute;squeda de auditorias a traves de JPA.
 * 
 * @author
 * 
 */
@Service("servicioAuditoriaPlataformaImpl")
public class ServicioAuditoriaPlataformaImpl implements ServicioAuditoriaPlataforma {

	/**  logger. */
	private static Logger logger = Logger.getLogger(ServicioAuditoriaPlataformaImpl.class);

	
	/**  tbl log manager. */
	@Resource(name = "tblLogManagerImpl")
	private TblLogManager tblLogManager;

	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private PlataformaMensajeriaProperties properties;

	

	/* (non-Javadoc)
	 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioAuditoriaPlataforma#getAuditoriasPlataforma(int, int, java.lang.String, java.lang.String, es.mpr.plataformamensajeria.beans.AuditoriaPlataformaBean, boolean)
	 */
	// ///MIGRADO
	@Override
	public PaginatedList<AuditoriaPlataformaBean> getAuditoriasPlataforma(int start, int size, String order,
			String columnSort, AuditoriaPlataformaBean criterio, boolean isExport) throws BusinessException {
		String nombre = null;
		String recursosFiltro = properties.getProperty("recursos.filtro.AUDITORIA", null);

		try {
			// Columna para ordenar
			Hashtable<String, String> columns = new Hashtable<String, String>();
			columns.put("0", "id");
			columns.put("1", "logdescripcion");
			columns.put("2", "sourcename");
			columns.put("3", "sourceid");
			columns.put("5", "adtfecha");
			columns.put("6", "adtusuario");
			if (columnSort == null) {
				columnSort = "5"; // Id
			}
			String column = columns.get(columnSort);
			if (column == null) {
				column = "adtfecha";
			}

			es.minhap.plataformamensajeria.iop.beans.AuditoriaPlataformaBean ap = new es.minhap.plataformamensajeria.iop.beans.AuditoriaPlataformaBean();
			if (null != criterio) {
				ap = createAuditoriaPlataformaBean(ap, criterio);
			}
			List<TblLog> lista = tblLogManager.getAuditoriasPaginadas(start, size, order, column, nombre, ap, false,
					recursosFiltro);
			List<AuditoriaPlataformaBean> pageList = getListAuditoriaPlataformaBean(lista);

			// Total de organismos
			Integer rowcount = tblLogManager.getAuditoriasPaginadas(start, size, order, column, nombre, ap, true,
					recursosFiltro).size();

			PaginatedList<AuditoriaPlataformaBean> result = new PaginatedList<>();
			result.setPageList(pageList);
			result.setTotalList(rowcount);

			return result;
		} catch (Exception e) {
			logger.error("ServicioAuditoriaPlataformaImpl - getAuditoriasPlataforma:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");

		}
	}


	/**
	 * <p>
	 * Convertirmos una lista de TblLog a una lista de
	 * AuditoriasBean
	 * </p>.
	 *
	 * @param lista the lista
	 * @return Lista de objetos AuditoriasBean
	 * @throws BusinessException the business exception
	 */
	/////MIGRADO
	protected List<AuditoriaPlataformaBean> getListAuditoriaPlataformaBean(List<TblLog> lista)
			throws BusinessException {
		List<AuditoriaPlataformaBean> result = new ArrayList<>();

		if (lista != null && !lista.isEmpty()) {
			for (TblLog l : lista) {
				AuditoriaPlataformaBean auditoriaPlataforma = new AuditoriaPlataformaBean();
				auditoriaPlataforma.setAdtFecha(l.getAdtfecha());
				auditoriaPlataforma.setAdtUsuario(l.getAdtusuario());
				auditoriaPlataforma.setLogAccion((null != l.getLogaccion())? l.getLogaccion().intValue() : null);
				auditoriaPlataforma.setLogDescripcion(l.getLogdescripcion());
				auditoriaPlataforma.setLogId(l.getLogid().intValue());
				auditoriaPlataforma.setSourceDescription(l.getSourcedescription());
				auditoriaPlataforma.setSourceId((null != l.getSourceid())? l.getSourceid().intValue() : null);
				auditoriaPlataforma.setSourceName(l.getSourcename());
				result.add(auditoriaPlataforma);
			}
		}
			return result;
	}

	/**
	 * Creates the auditoria plataforma bean.
	 *
	 * @param pb the pb
	 * @param criterio the criterio
	 * @return the es.minhap.plataformamensajeria.iop.beans. auditoria plataforma bean
	 */
	// //MIGRADO
	private es.minhap.plataformamensajeria.iop.beans.AuditoriaPlataformaBean createAuditoriaPlataformaBean(
			es.minhap.plataformamensajeria.iop.beans.AuditoriaPlataformaBean pb, AuditoriaPlataformaBean criterio) {
		
			pb.setAdtFecha(criterio.getAdtFecha());
			pb.setAdtUsuario(criterio.getAdtUsuario());
			pb.setFechaDesde(criterio.getFechaDesde());
			pb.setFechaHasta(criterio.getFechaHasta());
			pb.setLogAccion(criterio.getLogAccion());
			pb.setLogDescripcion(criterio.getLogDescripcion());
			pb.setLogId(criterio.getLogId());
			pb.setSourceDescription(criterio.getSourceDescription());
			pb.setSourceId(criterio.getSourceId());
			pb.setSourceName(criterio.getSourceName());
		return pb;
	}
}
