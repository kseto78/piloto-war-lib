package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.minhap.sim.model.TblPdpDiputaciones;
import es.minhap.sim.model.TblProcesos;




/**
 * 
 * @author everis
 *
 */
public interface QueryExecutorProcesos {

	
	/**
	 * Obtiene el listado según parametros
	 * 
	 * @param inicio
	 * @param pagesize
	 * @param order
	 * @param column
	 * @param criterio
	 * @return
	 */
	public List<TblProcesos> getProcesosPaginado(int size, String order, String column,
			es.minhap.plataformamensajeria.iop.beans.ProcesosBean ob);
	
	/**
	 * Obtiene el total según ob
	 * 
	 * @param ob
	 * @return
	 */
	public Integer countProcesosPaginado(es.minhap.plataformamensajeria.iop.beans.ProcesosBean ob);
	
	/**
	 * Obtiene el listado
	 * 
	 * @param term
	 * @return List<String>
	 */
	public List<String> getListAutocomplete(String term);


	

}
