package es.mpr.plataformamensajeria.beans;

import java.util.ArrayList;
import java.util.List;

import es.minhap.sim.model.TblDestinatariosHist;
import es.minhap.sim.model.TblDestinatariosMensHist;
import es.minhap.sim.model.TblGestionEnviosHist;
import es.minhap.sim.model.TblHistoricosHist;
import es.minhap.sim.model.TblMensajesAdjuntosHist;
import es.minhap.sim.model.TblMensajesHist;

public class ProcesoHistorificacionBean {

	List<TblMensajesHist> listaMensajeHistorico;
	private List<List<TblDestinatariosHist>> listasDestinatariosHist;
	private List<List<TblMensajesAdjuntosHist>> listasMensajesAdjuntosHist;
	private List<List<TblHistoricosHist>> listasHistoricoHist;
	private List<TblGestionEnviosHist> listaGestionEnviosHist;
	private List<List<TblDestinatariosMensHist>> listasDestinatariosMensajesHist;

	public ProcesoHistorificacionBean() {
		listaMensajeHistorico = new ArrayList<>();
		listasDestinatariosHist = new ArrayList<>();
		listasMensajesAdjuntosHist = new ArrayList<>();
		listasHistoricoHist = new ArrayList<>();
		listaGestionEnviosHist = new ArrayList<>();
		listasDestinatariosMensajesHist = new ArrayList<>();
	}

	
	
	/**
	 * @return the listasDestinatariosHist
	 */
	public List<List<TblDestinatariosHist>> getListasDestinatariosHist() {
		return listasDestinatariosHist;
	}

	/**
	 * @param listasDestinatariosHist the listasDestinatariosHist to set
	 */
	public void setListasDestinatariosHist(List<List<TblDestinatariosHist>> listasDestinatariosHist) {
		this.listasDestinatariosHist = listasDestinatariosHist;
	}

	/**
	 * @return the listasMensajesAdjuntosHist
	 */
	public List<List<TblMensajesAdjuntosHist>> getListasMensajesAdjuntosHist() {
		return listasMensajesAdjuntosHist;
	}

	/**
	 * @param listasMensajesAdjuntosHist the listasMensajesAdjuntosHist to set
	 */
	public void setListasMensajesAdjuntosHist(List<List<TblMensajesAdjuntosHist>> listasMensajesAdjuntosHist) {
		this.listasMensajesAdjuntosHist = listasMensajesAdjuntosHist;
	}

	/**
	 * @return the listasHistoricoHist
	 */
	public List<List<TblHistoricosHist>> getListasHistoricoHist() {
		return listasHistoricoHist;
	}

	/**
	 * @param listasHistoricoHist the listasHistoricoHist to set
	 */
	public void setListasHistoricoHist(List<List<TblHistoricosHist>> listasHistoricoHist) {
		this.listasHistoricoHist = listasHistoricoHist;
	}

	/**
	 * @return the listasDestinatariosMensajesHist
	 */
	public List<List<TblDestinatariosMensHist>> getListasDestinatariosMensajesHist() {
		return listasDestinatariosMensajesHist;
	}

	/**
	 * @param listasDestinatariosMensajesHist the listasDestinatariosMensajesHist to set
	 */
	public void setListasDestinatariosMensajesHist(List<List<TblDestinatariosMensHist>> listasDestinatariosMensajesHist) {
		this.listasDestinatariosMensajesHist = listasDestinatariosMensajesHist;
	}



	/**
	 * @return the listaMensajeHistorico
	 */
	public List<TblMensajesHist> getListaMensajeHistorico() {
		return listaMensajeHistorico;
	}



	/**
	 * @param listaMensajeHistorico the listaMensajeHistorico to set
	 */
	public void setListaMensajeHistorico(List<TblMensajesHist> listaMensajeHistorico) {
		this.listaMensajeHistorico = listaMensajeHistorico;
	}



	/**
	 * @return the listaGestionEnviosHist
	 */
	public List<TblGestionEnviosHist> getListaGestionEnviosHist() {
		return listaGestionEnviosHist;
	}



	/**
	 * @param listaGestionEnviosHist the listaGestionEnviosHist to set
	 */
	public void setListaGestionEnviosHist(List<TblGestionEnviosHist> listaGestionEnviosHist) {
		this.listaGestionEnviosHist = listaGestionEnviosHist;
	}

}
