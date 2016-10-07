package es.mpr.plataformamensajeria.model.views;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.map.j2ee.base.jpa.AbstractBaseJPAEntity;

@Entity
@Table(name = "VIEW_SERVIDOR_ORGANISMO")
@NamedQueries({ @NamedQuery(name = "ViewSelectServidorOrganismoJPA", query = "SELECT e FROM ViewServidoresOrganismosJPA e WHERE to_char(e.organismoId) = ? "),
	@NamedQuery(name = "listViewServidoresPorOrganismos", query = "SELECT e FROM ViewServidoresOrganismosJPA e WHERE organismoId in (:listaOrganismos) ") })
public class ViewServidoresOrganismosJPA extends AbstractBaseJPAEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewServidoresOrganismosJPA() {
		super();
		this.servidorOrganismoId = null;
		this.organismoId = null;
		this.servidorId = null;
		this.nombreServidor = null;
		this.nombreOrganismo = null;
		this.DIR3Organismo = null;
		this.numIntentos = null;
		this.headerSMS = null;
		this.proveedorUsuarioSMS = null;
		this.proveedorPasswordSMS = null;

	}

	@Id
	@SequenceGenerator(name = "servidorOrganismo", sequenceName = "ORGANISMOSSERVIDORID_SEC", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "servidorOrganismo")
	protected Integer servidorOrganismoId;

	@Column(name = "ORGANISMOID")
	protected Integer organismoId;

	@Column(name = "SERVIDORID")
	protected String servidorId;

	@Column(name = "NOMBRESERVIDOR")
	protected String nombreServidor;

	@Column(name = "NOMBREORGANISMO")
	protected String nombreOrganismo;

	@Column(name = "DIR3ORGANISMO")
	protected String DIR3Organismo;

	@Column(name = "NUMINTENTOS")
	protected Integer numIntentos;

	@Column(name = "HEADERSMS")
	protected String headerSMS;

	@Column(name = "PROVEEDORUSUARIOSMS")
	protected String proveedorUsuarioSMS;

	@Column(name = "PROVEEDORPASSWORDSMS")
	protected String proveedorPasswordSMS;

	@Override
	public Object getId() {
		return this.servidorOrganismoId;
	}

	public Integer getServidorOrganismoId() {
		return servidorOrganismoId;
	}

	public void setServidorOrganismoId(Integer servidorOrganismoId) {
		this.servidorOrganismoId = servidorOrganismoId;
	}

	public Integer getOrganismoId() {
		return organismoId;
	}

	public void setOrganismoId(Integer organismoId) {
		this.organismoId = organismoId;
	}

	public String getServidorId() {
		return servidorId;
	}

	public void setServidorId(String servidorId) {
		this.servidorId = servidorId;
	}

	public String getNombreServidor() {
		return nombreServidor;
	}

	public void setNombreServidor(String nombreServidor) {
		this.nombreServidor = nombreServidor;
	}

	public String getNombreOrganismo() {
		return nombreOrganismo;
	}

	public void setNombreOrganismo(String nombreOrganismo) {
		this.nombreOrganismo = nombreOrganismo;
	}

	public String getDIR3Organismo() {
		return DIR3Organismo;
	}

	public void setDIR3Organismo(String dIR3Organismo) {
		DIR3Organismo = dIR3Organismo;
	}

	public Integer getNumIntentos() {
		return numIntentos;
	}

	public void setNumIntentos(Integer numIntentos) {
		this.numIntentos = numIntentos;
	}

	public String getHeaderSMS() {
		return headerSMS;
	}

	public void setHeaderSMS(String headerSMS) {
		this.headerSMS = headerSMS;
	}

	public String getProveedorUsuarioSMS() {
		return proveedorUsuarioSMS;
	}

	public void setProveedorUsuarioSMS(String proveedorUsuarioSMS) {
		this.proveedorUsuarioSMS = proveedorUsuarioSMS;
	}

	public String getProveedorPasswordSMS() {
		return proveedorPasswordSMS;
	}

	public void setProveedorPasswordSMS(String proveedorPasswordSMS) {
		this.proveedorPasswordSMS = proveedorPasswordSMS;
	}

}
