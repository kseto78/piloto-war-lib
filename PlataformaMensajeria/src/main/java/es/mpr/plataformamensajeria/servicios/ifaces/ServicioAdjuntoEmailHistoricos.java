package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.minhap.sim.model.TblAdjuntosHist;

/**
 * <p>Interface que define los metodos para la gestion de adjuntos</p>
 * 
 * @author jgonzvil
 *
 */
@Service
public interface ServicioAdjuntoEmailHistoricos {
	
	public List<Long> getTodosIdAdjuntosCons(List<Long> listaMensajes) throws BusinessException;
	
	Long insert(TblAdjuntosHist adjuntoHistorico);

	void delete(Long adjuntoid);
	
}
