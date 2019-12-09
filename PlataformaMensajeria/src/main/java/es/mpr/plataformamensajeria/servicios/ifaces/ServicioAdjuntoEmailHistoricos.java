package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.sim.model.TblAdjuntosHist;

/**
 * <p>Interface que define los metodos para la gestion de adjuntos</p>.
 *
 * @author jgonzvil
 */
@Service
public interface ServicioAdjuntoEmailHistoricos {
	
	/**
	 * Obtener todos id adjuntos cons.
	 *
	 * @param listaMensajes the lista mensajes
	 * @return todos id adjuntos cons
	 * @throws BusinessException the business exception
	 */
	List<Long> getTodosIdAdjuntosCons(List<Long> listaMensajes) throws BusinessException;
	
	/**
	 * Insert.
	 *
	 * @param adjuntoHistorico the adjunto historico
	 * @return the long
	 */
	Long insert(TblAdjuntosHist adjuntoHistorico);

	/**
	 * Delete.
	 *
	 * @param adjuntoid the adjuntoid
	 */
	void delete(Long adjuntoid);
	
}
