package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

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
	public Long obtenerServidorByIdMensaje(Long idMensaje);
	
	/**
	 * Obtiene un listado de proveedores multiorganismo a partir del id de mensaje
	 * 
	 * @param idMensaje
	 * @return
	 */
	public List<ParametrosProveedor> getProveedoresMultiorganismo(Long idMensaje);
	
	/**
	 * Obtiene un listado de proveedores  a partir del id de mensaje
	 * 
	 * @param idMensaje
	 * @return
	 */
	public List<ParametrosProveedor> getProveedores(Long idMensaje); 

	
	/**
	 * Se recupera el id servidor a partir del mensaje
	 * 
	 * @param idMensaje
	 * @return
	 */
	public Long getIdServicioByIdMensaje(Long idMensaje);

	
	/**
	 * Se recupera el id lote a partir del mensaje
	 * 
	 * @param idMensaje
	 * @return
	 */
	public Long getIdLoteByIdMensaje(Long idMensaje);
	
	
}
