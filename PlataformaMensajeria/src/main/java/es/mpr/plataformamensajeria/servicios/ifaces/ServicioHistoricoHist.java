package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.sim.model.TblHistoricosHist;

/**
 * <p>Interface que define los metodos para la gestion de historico</p>.
 *
 * @author jgonzvil
 */
public interface ServicioHistoricoHist {
	
	/**
	 * Obtener todos id historicos cons.
	 *
	 * @param listaMensajesHistoricosCons the lista mensajes historicos cons
	 * @return todos id historicos cons
	 * @throws BusinessException the business exception
	 */
	List<Long> getTodosIdHistoricosCons(List<Long> listaMensajesHistoricosCons) throws BusinessException;
	
	/**
	 * Obtener tbl historicos hist.
	 *
	 * @param subList the sub list
	 * @return tbl historicos hist
	 */
	List<List<TblHistoricosHist>> getTblHistoricosHist(List<Long> subList);

	/**
	 * Insert.
	 *
	 * @param historicosHistorico the historicos historico
	 * @return the long
	 */
	Long insert(TblHistoricosHist historicosHistorico);

	/**
	 * Detele.
	 *
	 * @param historicoid the historicoid
	 */
	void detele(Long historicoid);


}
