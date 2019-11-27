package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import es.minhap.sim.model.TblPdpDiputaciones;
import es.minhap.sim.model.TblProcesos;
import es.minhap.sim.model.ViewProcesosManuales;




/**
 * 
 * @author everis
 *
 */
public interface QueryExecutorProcesosManuales {

	
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
	List<ViewProcesosManuales> getProcesosManualesPaginado(int inicio, int size, String order, String column,
			es.minhap.plataformamensajeria.iop.beans.ProcesosManualesBean ob);
	
	/**
	 * Obtiene el total según ob
	 * 
	 * @param ob
	 * @return
	 */
	Integer countProcesosManualesPaginado(es.minhap.plataformamensajeria.iop.beans.ProcesosManualesBean ob);
	
	

}
