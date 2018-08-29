package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;

import es.minhap.plataformamensajeria.iop.dao.QueryExecutorGestionEnvios;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorServidores;
import es.minhap.plataformamensajeria.iop.dao.QueryExecutorViewEnviosPendientesPorCanal;
import es.minhap.sim.model.ViewEnviosPendientesPorCanal;
import es.minhap.sim.model.ViewEstadoLotesEnvios;
import es.mpr.plataformamensajeria.beans.EnviosPendientesCanalBean;
import es.mpr.plataformamensajeria.beans.EstadoLotesEnviosBean;
import es.mpr.plataformamensajeria.beans.UsoServidoresBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioListadosHome;

/**
 * <p>Maneja la persistencia y b&uacute;squeda de canales a traves de JPA.
 * 
 * @author 
 * 
 */
@Service("servicioListadosHomeImpl")
public class ServicioListadosHomeImpl implements ServicioListadosHome{
		
	/**  query executor view envios pendientes por canal. */
	@Autowired
	private QueryExecutorViewEnviosPendientesPorCanal queryExecutorViewEnviosPendientesPorCanal;

	/**  query executor servidores. */
	@Autowired
	private QueryExecutorServidores queryExecutorServidores;
	
	/**  query executor gestion envios. */
	@Autowired
	private QueryExecutorGestionEnvios queryExecutorGestionEnvios;
	

	
	
	
/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioListadosHome#getEnviosPendientesCanal()
 */
//////Migrado
	@Override
	public List<EnviosPendientesCanalBean> getEnviosPendientesCanal() throws BusinessException {
		List<ViewEnviosPendientesPorCanal> lista= null;
		try {
			lista = queryExecutorViewEnviosPendientesPorCanal.getAll();

			return getListEnviosPendientesBeam(lista);						
		}catch (Exception e){
			throw new BusinessException(e,"errors.organismo.getEnviosPendientesCanal");	
		}
	}

	
	
/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioListadosHome#getUsoServidoresBean(java.lang.String, java.lang.String)
 */
//////Migrado
	@Override
	public List<UsoServidoresBean> getUsoServidoresBean(String anyo, String mes)
			throws BusinessException {
	
		List<UsoServidoresBean> listaUsoServidores = new ArrayList<UsoServidoresBean>();
		try{
			
			List<es.minhap.plataformamensajeria.iop.beans.UsoServidoresBean> lista = queryExecutorServidores.getUsoServidores(anyo, mes);
			for (es.minhap.plataformamensajeria.iop.beans.UsoServidoresBean usb : lista) {
				UsoServidoresBean uso = new UsoServidoresBean();
				uso.setNenvios(usb.getnEnvios());
				uso.setServidor(usb.getNombre());
				uso.setTipoServidor(usb.getTipoServidor().intValue());
				listaUsoServidores.add(uso);
			}
						
		}catch(Exception e){
			throw new BusinessException(e,"errors.organismo.getUsoServidoresBean");	
		}
		return listaUsoServidores;
	}
	
/* (non-Javadoc)
 * @see es.mpr.plataformamensajeria.servicios.ifaces.ServicioListadosHome#getEstadosLotesEnvios(int, int, java.lang.String, java.lang.String)
 */
//////Migrado
	@Override
	public PaginatedList<EstadoLotesEnviosBean> getEstadosLotesEnvios(int start, int size,String anyo, String mes) throws BusinessException {
		List<ViewEstadoLotesEnvios> lista = queryExecutorGestionEnvios.getUltimosEstadoEnviosLotes();
	 
	 	List<EstadoLotesEnviosBean> pageList = getListViewEstadoEnviosLotesBean(lista);

		
		PaginatedList<EstadoLotesEnviosBean> result = new PaginatedList<EstadoLotesEnviosBean>();
		result.setPageList(pageList);
		result.setTotalList(10);
	 	return result;
	}	
	
	
	
	/**
	 * <p>Convertirmos una lista de ViewServidoresJPA a una lista de ServidoresBean</p>.
	 *
	 * @param lista the lista
	 * @return Lista de objetos OrganismoBean
	 * @throws BusinessException the business exception
	 */
//////Migrado
	protected List<EstadoLotesEnviosBean> getListViewEstadoEnviosLotesBean(List<ViewEstadoLotesEnvios> lista) throws BusinessException
	{	
		List<EstadoLotesEnviosBean> result = null;
		
		if (lista!=null && !lista.isEmpty())
		{
			result = new ArrayList<EstadoLotesEnviosBean>();
		
			for (int indice=0;indice<lista.size();indice++) {
					
				ViewEstadoLotesEnvios servidorJPA = lista.get(indice);
				EstadoLotesEnviosBean servidor =  new EstadoLotesEnviosBean();
			
				try {
					BeanUtils.copyProperties(servidor, servidorJPA);
				} catch (IllegalAccessException e) {
					throw new BusinessException(e);
				} catch (InvocationTargetException e) {
					throw new BusinessException(e);
				}
			
				result.add(servidor);
			}
		}
			
		return result;
	}
	
	/**
	 * <p>Convertirmos una lista de ViewEnviosPendientesPorCanal a una lista de ServidoresBean</p>.
	 *
	 * @param lista the lista
	 * @return Lista de objetos EnviosPendientesCanalBean
	 * @throws BusinessException the business exception
	 */
//////Migrado
	protected List<EnviosPendientesCanalBean> getListEnviosPendientesBeam
		(List<ViewEnviosPendientesPorCanal> lista) throws BusinessException
	{	
		List<EnviosPendientesCanalBean> result = null;
		
		if (lista!=null && !lista.isEmpty())
		{
			result = new ArrayList<EnviosPendientesCanalBean>();
			for (ViewEnviosPendientesPorCanal l : lista) {
				EnviosPendientesCanalBean envioPendiente =  new EnviosPendientesCanalBean();
				envioPendiente.setAplicacion(l.getId().getNombre());
				envioPendiente.setAplicacionId(l.getId().getAplicacionid().intValue());
				envioPendiente.setEmail(l.getId().getEmails());
				envioPendiente.setPush(l.getId().getPush());
				envioPendiente.setRecepcionSMS(l.getId().getRecepcionsms());
				envioPendiente.setSms(l.getId().getSms());
				result.add(envioPendiente);
			}
			
		}
			
		return result;
	}
	
	/**
	 * Obtener query executor view envios pendientes por canal.
	 *
	 * @return the queryExecutorViewEnviosPendientesPorCanal
	 */
	public QueryExecutorViewEnviosPendientesPorCanal getQueryExecutorViewEnviosPendientesPorCanal() {
		return queryExecutorViewEnviosPendientesPorCanal;
	}
	
	/**
	 * Modificar query executor view envios pendientes por canal.
	 *
	 * @param queryExecutorViewEnviosPendientesPorCanal the queryExecutorViewEnviosPendientesPorCanal to set
	 */
	public void setQueryExecutorViewEnviosPendientesPorCanal(
			QueryExecutorViewEnviosPendientesPorCanal queryExecutorViewEnviosPendientesPorCanal) {
		this.queryExecutorViewEnviosPendientesPorCanal = queryExecutorViewEnviosPendientesPorCanal;
	}

	
}
