package es.mpr.plataformamensajeria.beans;

import java.util.ArrayList;
import java.util.List;

import es.mpr.plataformamensajeria.model.AdjuntoEmailHistoricosJPA;
import es.mpr.plataformamensajeria.model.DestinatarioHistoricosJPA;
import es.mpr.plataformamensajeria.model.GestionEnviosHistoricosJPA;
import es.mpr.plataformamensajeria.model.HistoricoHistJPA;
import es.mpr.plataformamensajeria.model.MensajesAdjuntosHistoricosJPA;

public class HistoricoDTO {

	private List<MensajesAdjuntosHistoricosJPA> listaMensajesAdjuntosHist;
	private List<DestinatarioHistoricosJPA> listaDestinatariosHist;
	private List<HistoricoHistJPA> listaHistoricoHist;
	private List<GestionEnviosHistoricosJPA> listaGestionEnviosHist;
	private List<AdjuntoEmailHistoricosJPA> listaAdjuntosEmailHist;

	public HistoricoDTO() {
		listaMensajesAdjuntosHist = new ArrayList<MensajesAdjuntosHistoricosJPA>();
		listaDestinatariosHist = new ArrayList<DestinatarioHistoricosJPA>();
		listaHistoricoHist = new ArrayList<HistoricoHistJPA>();
		listaGestionEnviosHist = new ArrayList<GestionEnviosHistoricosJPA>();
		listaAdjuntosEmailHist = new ArrayList<AdjuntoEmailHistoricosJPA>();
	}

	public List<MensajesAdjuntosHistoricosJPA> getListaMensajesAdjuntosHist() {
		return listaMensajesAdjuntosHist;
	}

	public void setListaMensajesAdjuntosHist(List<MensajesAdjuntosHistoricosJPA> listaMensajesAdjuntosHist) {
		this.listaMensajesAdjuntosHist = listaMensajesAdjuntosHist;
	}

	public List<DestinatarioHistoricosJPA> getListaDestinatariosHist() {
		return listaDestinatariosHist;
	}

	public void setListaDestinatariosHist(List<DestinatarioHistoricosJPA> listaDestinatariosHist) {
		this.listaDestinatariosHist = listaDestinatariosHist;
	}

	public List<HistoricoHistJPA> getListaHistoricoHist() {
		return listaHistoricoHist;
	}

	public void setListaHistoricoHist(List<HistoricoHistJPA> listaHistoricoHist) {
		this.listaHistoricoHist = listaHistoricoHist;
	}

	public List<GestionEnviosHistoricosJPA> getListaGestionEnviosHist() {
		return listaGestionEnviosHist;
	}

	public void setListaGestionEnviosHist(List<GestionEnviosHistoricosJPA> listaGestionEnviosHist) {
		this.listaGestionEnviosHist = listaGestionEnviosHist;
	}

	public List<AdjuntoEmailHistoricosJPA> getListaAdjuntosEmailHist() {
		return listaAdjuntosEmailHist;
	}

	public void setListaAdjuntosEmailHist(List<AdjuntoEmailHistoricosJPA> listaAdjuntosEmailHist) {
		this.listaAdjuntosEmailHist = listaAdjuntosEmailHist;
	}

}
