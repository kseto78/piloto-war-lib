package es.minhap.plataformamensajeria.iop.dao;

import java.util.List;

/**
 * 
 * 
 * @author everis
 *
 */
public interface QueryExecutorServidoresOrganismos {
	

	/**
	 * Obtiene el usuario a partir del codigo de organismos y el proveedor
	 * 
	 * @param codigoOrganismo
	 * @param proveedorId
	 * @return
	 */
	String getUsuario(String codigoOrganismo, Integer proveedorId);
	
	/**
	 * Obtiene el password a partir del codigo de organismo y el proveedor
	 * 
	 * @param codigoOrganismo
	 * @param proveedorId
	 * @return
	 */
	String getPassword(String codigoOrganismo, Integer proveedorId);

	List<Long> obtenerOrganismosNoActivos(Long idProveedorSMS);
	
	
}
