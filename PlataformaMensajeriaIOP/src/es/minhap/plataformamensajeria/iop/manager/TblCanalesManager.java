package es.minhap.plataformamensajeria.iop.manager;

import java.util.List;

import es.minhap.sim.model.TblCanales;


public interface TblCanalesManager {

	/**
	 * obtiene el canal por id
	 * 
	 * @param canalId
	 * @return 
	 */
	TblCanales getCanalById(Long canalId );
	
	/**
	 * recupera todas las canales ordenadas por nombre
	 * @return
	 */
	List<TblCanales> getCanalesOrdenados();
}
