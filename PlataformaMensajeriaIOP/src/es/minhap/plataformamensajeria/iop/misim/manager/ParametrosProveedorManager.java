package es.minhap.plataformamensajeria.iop.misim.manager;

import java.util.List;

import es.minhap.misim.bus.model.ParametrosProveedor;

public interface ParametrosProveedorManager {

	List<ParametrosProveedor> getParametrosPorProveedorMisim(Long idProveedor);

	ParametrosProveedor getParametroPorIdParametrosProveedor(Long idParametrosProveedor);

	Long insert(ParametrosProveedor parametroProveedor, String source, String accion, Long accionId, String descripcion);

	void delete(Long idParametrosProveedor, String source, String accion, Long accionId, String descripcion);

}
