package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

import es.minhap.plataformamensajeria.iop.beans.UsoServidoresBean;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosProveedor;

/**
 * 
 * 
 * @author everis
 *
 */
public interface QueryExecutorServidores {
	
	/**
	 * Obtiene el servidor a partir del id mensaje
	 * @param recipient
	 * @param canalId
	 * @return Integer
	 */
	Long obtenerServidorByIdMensaje(Long idMensaje);
	
	/**
	 * Obtiene un listado de proveedores multiorganismo a partir del id de mensaje
	 * 
	 * @param idMensaje
	 * @return
	 */
	List<ParametrosProveedor> getProveedoresMultiorganismo(Long idMensaje);
	
	/**
	 * Obtiene un listado de proveedores  a partir del id de mensaje
	 * 
	 * @param idMensaje
	 * @return
	 */
	List<ParametrosProveedor> getProveedores(Long idMensaje); 

	
	/**
	 * Se recupera el id servidor a partir del mensaje
	 * 
	 * @param idMensaje
	 * @return
	 */
	Long getIdServicioByIdMensaje(Long idMensaje);


	/**
	 * Se recupera el id lote a partir del mensaje
	 * 
	 * @param idMensaje
	 * @return
	 */
	Long getIdLoteByIdMensaje(Long idMensaje);

	/**
	 * Se recupera el id servidor a partir del mensaje
	 * 
	 * @param idMensaje
	 * @return
	 */
	Long getIdServicioByIdMensajeHist(Long idMensaje);

	/**
	 * Se recupera el uso de cada servidor
	 * 
	 * @param anoActual
	 * @param mesActual
	 * @return
	 */
	List<UsoServidoresBean> getUsoServidores(String anoActual, String mesActual);
	

	

}
