package es.mpr.plataformamensajeria.servicios.ifaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;

import es.mpr.plataformamensajeria.beans.PlataformaBean;

/**
 * <p>Interface que define los m&eacute;todos para la gesti&oacute;n de plataformas</p>
 * 
 * @author jgonzvil
 *
 */
@Service
public interface ServicioPlataforma {
	
	List<PlataformaBean> getPlataformas() throws BusinessException;

}
