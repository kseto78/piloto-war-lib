package es.minhap.plataformamensajeria.iop.misim.manager;


public interface ErroresManager {

	/**
	 * recupera la aplicacion según datos pasados
	 * 
	 * @param TblAplicacionesQuery
	 * @return
	 */
	boolean getEstadoMq();
	void updateErrorMq(boolean estado);
	boolean comprobarActiveMqActivo(boolean estado);
}
