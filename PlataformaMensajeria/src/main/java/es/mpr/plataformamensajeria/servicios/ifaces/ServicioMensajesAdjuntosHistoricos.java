package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.sim.model.TblLotesEnviosHist;
import es.minhap.sim.model.TblMensajesAdjuntosHist;

/**
 * <p>Interface que define los metodos para la gestion de adjuntos historico</p>
 * 
 * @author jgonzvil
 *
 */

public interface ServicioMensajesAdjuntosHistoricos {
	
	List<Long> getIdMensajesAdjuntosCons(List<Long> listaMensajes) throws BusinessException;
	
	List<List<TblMensajesAdjuntosHist>> getTblMensajesAdjuntosHist(List<Long> subList, TblLotesEnviosHist loteHistorico);

	Long insert(TblMensajesAdjuntosHist mensajeAdjuntoHistorico);

	void delete(Long idMensajeAdjunto);

}
