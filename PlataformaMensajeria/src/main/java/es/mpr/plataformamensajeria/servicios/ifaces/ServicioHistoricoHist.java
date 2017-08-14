package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.sim.model.TblHistoricosHist;

/**
 * <p>Interface que define los metodos para la gestion de historico</p>
 * 
 * @author jgonzvil
 *
 */
public interface ServicioHistoricoHist {
	
	List<Long> getTodosIdHistoricosCons(List<Long> listaMensajesHistoricosCons) throws BusinessException;
	
	List<List<TblHistoricosHist>> getTblHistoricosHist(List<Long> subList);

	Long insert(TblHistoricosHist historicosHistorico);

	void detele(Long historicoid);


}
