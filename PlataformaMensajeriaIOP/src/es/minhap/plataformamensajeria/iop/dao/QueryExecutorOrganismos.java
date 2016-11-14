package es.minhap.plataformamensajeria.iop.dao;

import org.springframework.transaction.annotation.Transactional;
/**
 * 
 * @author everis
 *
 */
public interface QueryExecutorOrganismos {

	/**
	 * Obtiene si el organismo esta activo en el servicio
	 * @param servicioId
	 * @param organismoPagador
	 * @return
	 */
	@Transactional
	public boolean organismoActivoEnServicio(Integer servicioId,String organismoPagador);
	
	/**
	 * Comprueba si para ese servicio esta activa la aplicacion
	 * @param servicioId
	 * @return
	 */
	@Transactional
	public Integer checkActiveApplication(Integer servicioId);

	/**
	 * Obtiene si el organismo esta asociado al servicio
	 * @param servicioId
	 * @param organismoPagador
	 * @return
	 */
	boolean asociadoOrganismoServicio(Integer servicioId, String organismoPagador);
}
