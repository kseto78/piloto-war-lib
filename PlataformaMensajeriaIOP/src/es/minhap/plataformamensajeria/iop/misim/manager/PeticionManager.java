package es.minhap.plataformamensajeria.iop.misim.manager;

import es.minhap.misim.bus.model.Peticion;
import es.minhap.misim.bus.query.PeticionQuery;


public interface PeticionManager {

	Peticion getPeticionByQuery(PeticionQuery query);

}