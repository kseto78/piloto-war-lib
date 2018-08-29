package es.mpr.plataformamensajeria.beans;

import java.util.ArrayList;
import java.util.List;

import es.minhap.sim.model.TblDestinatariosHist;
import es.minhap.sim.model.TblDestinatariosMensHist;
import es.minhap.sim.model.TblGestionEnviosHist;
import es.minhap.sim.model.TblHistoricosHist;
import es.minhap.sim.model.TblMensajesAdjuntosHist;
import es.minhap.sim.model.TblMensajesHist;

/**
 * Clase ProcesoHistorificacionBean.
 */
public class ProcesoHistorificacionBean {

	/**  lista mensaje historico. */
	List<TblMensajesHist> listaMensajeHistorico;
	
	/**  listas destinatarios hist. */
	private List<List<TblDestinatariosHist>> listasDestinatariosHist;
	
	/**  listas mensajes adjuntos hist. */
	private List<List<TblMensajesAdjuntosHist>> listasMensajesAdjuntosHist;
	
	/**  listas historico hist. */
	private List<List<TblHistoricosHist>> listasHistoricoHist;
	
	/**  lista gestion envios hist. */
	private List<TblGestionEnviosHist> listaGestionEnviosHist;
	
	/**  listas destinatarios mensajes hist. */
	private List<List<TblDestinatariosMensHist>> listasDestinatariosMensajesHist;

	/**
	 * Constructor de proceso historificacion bean.
	 */
	public ProcesoHistorificacionBean() {
		listaMensajeHistorico = new ArrayList<>();
		listasDestinatariosHist = new ArrayList<>();
		listasMensajesAdjuntosHist = new ArrayList<>();
		listasHistoricoHist = new ArrayList<>();
		listaGestionEnviosHist = new ArrayList<>();
		listasDestinatariosMensajesHist = new ArrayList<>();
	}

	
	
	/**
	 * Obtener listas destinatarios hist.
	 *
	 * @return the listasDestinatariosHist
	 */
	public List<List<TblDestinatariosHist>> getListasDestinatariosHist() {
		return listasDestinatariosHist;
	}

	/**
	 * Modificar listas destinatarios hist.
	 *
	 * @param listasDestinatariosHist the listasDestinatariosHist to set
	 */
	public void setListasDestinatariosHist(List<List<TblDestinatariosHist>> listasDestinatariosHist) {
		this.listasDestinatariosHist = listasDestinatariosHist;
	}

	/**
	 * Obtener listas mensajes adjuntos hist.
	 *
	 * @return the listasMensajesAdjuntosHist
	 */
	public List<List<TblMensajesAdjuntosHist>> getListasMensajesAdjuntosHist() {
		return listasMensajesAdjuntosHist;
	}

	/**
	 * Modificar listas mensajes adjuntos hist.
	 *
	 * @param listasMensajesAdjuntosHist the listasMensajesAdjuntosHist to set
	 */
	public void setListasMensajesAdjuntosHist(List<List<TblMensajesAdjuntosHist>> listasMensajesAdjuntosHist) {
		this.listasMensajesAdjuntosHist = listasMensajesAdjuntosHist;
	}

	/**
	 * Obtener listas historico hist.
	 *
	 * @return the listasHistoricoHist
	 */
	public List<List<TblHistoricosHist>> getListasHistoricoHist() {
		return listasHistoricoHist;
	}

	/**
	 * Modificar listas historico hist.
	 *
	 * @param listasHistoricoHist the listasHistoricoHist to set
	 */
	public void setListasHistoricoHist(List<List<TblHistoricosHist>> listasHistoricoHist) {
		this.listasHistoricoHist = listasHistoricoHist;
	}

	/**
	 * Obtener listas destinatarios mensajes hist.
	 *
	 * @return the listasDestinatariosMensajesHist
	 */
	public List<List<TblDestinatariosMensHist>> getListasDestinatariosMensajesHist() {
		return listasDestinatariosMensajesHist;
	}

	/**
	 * Modificar listas destinatarios mensajes hist.
	 *
	 * @param listasDestinatariosMensajesHist the listasDestinatariosMensajesHist to set
	 */
	public void setListasDestinatariosMensajesHist(List<List<TblDestinatariosMensHist>> listasDestinatariosMensajesHist) {
		this.listasDestinatariosMensajesHist = listasDestinatariosMensajesHist;
	}



	/**
	 * Obtener lista mensaje historico.
	 *
	 * @return the listaMensajeHistorico
	 */
	public List<TblMensajesHist> getListaMensajeHistorico() {
		return listaMensajeHistorico;
	}



	/**
	 * Modificar lista mensaje historico.
	 *
	 * @param listaMensajeHistorico the listaMensajeHistorico to set
	 */
	public void setListaMensajeHistorico(List<TblMensajesHist> listaMensajeHistorico) {
		this.listaMensajeHistorico = listaMensajeHistorico;
	}



	/**
	 * Obtener lista gestion envios hist.
	 *
	 * @return the listaGestionEnviosHist
	 */
	public List<TblGestionEnviosHist> getListaGestionEnviosHist() {
		return listaGestionEnviosHist;
	}



	/**
	 * Modificar lista gestion envios hist.
	 *
	 * @param listaGestionEnviosHist the listaGestionEnviosHist to set
	 */
	public void setListaGestionEnviosHist(List<TblGestionEnviosHist> listaGestionEnviosHist) {
		this.listaGestionEnviosHist = listaGestionEnviosHist;
	}

}
