package es.minhap.misim.bus.model.servicios;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import es.minhap.misim.bus.dao.ParametrosProveedorDAO;
import es.minhap.misim.bus.model.ParametrosProveedor;
import es.minhap.misim.bus.query.ParametrosProveedorQuery;

@Service("parametrosProveedorManager")
public class ParametrosProveedorManagerImpl implements ParametrosProveedorManager{

		
	@Resource
	ParametrosProveedorDAO parametrosProveedorDAO;
	
	@Override
	public List<ParametrosProveedor> getParametrosProveedor(ParametrosProveedorQuery query) {
		return parametrosProveedorDAO.search(query).getResults();
	}
	
}
