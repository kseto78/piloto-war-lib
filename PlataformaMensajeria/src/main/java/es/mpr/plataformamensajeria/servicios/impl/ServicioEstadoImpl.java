package es.mpr.plataformamensajeria.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.plataformamensajeria.iop.manager.TblEstadosManager;
import es.minhap.sim.model.TblEstados;
import es.mpr.plataformamensajeria.beans.EstadoBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioEstado;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de estados a traves de JPA.
 * 
 * @author 
 * 
 */
@Service("servicioEstadoImpl")
public class ServicioEstadoImpl implements ServicioEstado{

	private static Logger logger = Logger.getLogger(ServicioServidorImpl.class);
	
	@Resource(name="TblEstadosManagerImpl")
	private TblEstadosManager tblEstadosManager;
	
	///MIGRADO
	@Override
	public List<EstadoBean> getEstados() throws BusinessException {
		List<TblEstados> list = null;
		try {			
			list= tblEstadosManager.getEstados().getResults();
			List<EstadoBean> result = getListEstadoBean(list);					
			return result;
		} 
		catch (Exception e){
			logger.error("ServicioEstadosImpl - getEstados:" + e);
			throw new BusinessException(e,"errors.organismo.getOrganismos");	
		}
	}

	/**
	 * <p>Convertirmos una lista de  a una lista de EstadoBean</p>
	 * 
	 * @param list
	 * 
	 * @return Lista de objetos EstadoBean
	 */
	///MIGRADO
	protected List<EstadoBean> getListEstadoBean(List<TblEstados> list) throws BusinessException
	{	
		List<EstadoBean> result = null;
		
		if (list!=null && !list.isEmpty())
		{
			result = new ArrayList<EstadoBean>();
		
			for (int indice=0;indice<list.size();indice++) {
					
				TblEstados estadoJPA = list.get(indice);
				EstadoBean estado =  new EstadoBean();
			
				estado.setEstadoId(estadoJPA.getEstadoid().intValue());
				estado.setNombre(estadoJPA.getNombre());
				estado.setDescripcion(estadoJPA.getDescripcion());
				if(estadoJPA.getActivo()){
					estado.setActivo(1);
				}
				else {
					estado.setActivo(0);
				}
				estado.setFechaCreacion(estadoJPA.getFechacreacion());
				estado.setCreadoPor(estadoJPA.getCreadopor());
				estado.setFechaModificacion(estadoJPA.getFechamodificacion());
				estado.setModificadoPor(estadoJPA.getModificadopor());
			
				result.add(estado);
			}
		}
			
		return result;
	}
}
