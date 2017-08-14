package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.Date;
import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.sim.model.TblLotesEnviosHist;
import es.minhap.sim.model.TblMensajesHist;


/**
 * <p>Interface que define los metodos para la gestion de mensajes historico</p>
 * 
 * @author jgonzvil
 *
 */

public interface ServicioMensajesHistoricos {
	
	List<TblMensajesHist> getTblMensajesHist(List<Long> subList, TblLotesEnviosHist loteHistorico);

	Long insert(TblMensajesHist mensajeHistorico);

	void delete(Long mensajeid);

	List<List<Long>> getTodosMensajesLoteHistorificar(Long loteEnvioID, Date fecha) throws BusinessException;
	
}
