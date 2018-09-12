package es.minhap.plataformamensajeria.iop.misim.manager;


public interface ErroresManager {

	/**
	 * recupera la aplicacion según datos pasados
	 * 
	 * @param TblAplicacionesQuery
	 * @return
	 */
	public boolean getEstadoMq();
	public void updateErrorMq(boolean estado);
	public boolean comprobarActiveMqActivo(boolean estado);
}
