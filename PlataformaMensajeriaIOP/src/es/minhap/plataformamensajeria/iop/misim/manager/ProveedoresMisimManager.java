package es.minhap.plataformamensajeria.iop.misim.manager;

import java.util.List;

import es.minhap.misim.bus.model.Proveedor;
import es.minhap.plataformamensajeria.iop.beans.FiltroProveedorMisimBean;


public interface ProveedoresMisimManager {

	List<Proveedor> getProveedoresMisimPaginado(int start, int size,
			String order, String columnSort, FiltroProveedorMisimBean criterio, boolean count);

	Long insert(Proveedor proveedor, String source, String accion, Long accionId);

	void update(Proveedor proveedor, String source, String accion, Long accionId);

	Proveedor getProveedor(Long idProveedor);

	void delete(Long idProveedor, String source, String accion, Long accionId);

}
