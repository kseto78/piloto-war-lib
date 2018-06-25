package es.minhap.plataformamensajeria.iop.misim.manager;

import java.util.List;

import es.minhap.misim.bus.model.ViewMisim;
import es.minhap.misim.bus.query.ViewMisimQuery;

public interface ViewMisimManager {

	/**
	 * obtiene si existe ya una entrada con para ese mensaje
	 * 
	 * @param ViewMisimQuery
	 * @return ViewMisim
	 */
	ViewMisim getViewMisim(ViewMisimQuery query);

	List<ViewMisim> getIntercambiosMisimByQuery(ViewMisimQuery query, int start, int size);

}