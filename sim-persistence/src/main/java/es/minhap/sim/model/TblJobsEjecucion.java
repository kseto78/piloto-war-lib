package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TblJobsEjecucion generated by hbm2java
 */
@Entity
@Table(name = "TBL_JOBS_EJECUCION")
public class TblJobsEjecucion implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7143679344082550380L;

	@Id
	@Column(name = "JOBEJECUCIONID", unique = true, nullable = false, precision = 22, scale = 0)
	private Long jobejecucionid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "JOBID")
	private TblJobs tblJobs;

	@Column(name = "FECHA", length = 7)
	private Date fecha;

	@Column(name = "SALIDA", length = 256)
	private String salida;

	@Column(name = "CODIGOERROR", length = 256)
	private String codigoerror;

	public TblJobsEjecucion() {
	}

	/**
	 * @return the jobejecucionid
	 */
	public Long getJobejecucionid() {
		return jobejecucionid;
	}

	/**
	 * @param jobejecucionid
	 *            the jobejecucionid to set
	 */
	public void setJobejecucionid(Long jobejecucionid) {
		this.jobejecucionid = jobejecucionid;
	}

	/**
	 * @return the tblJobs
	 */
	public TblJobs getTblJobs() {
		return tblJobs;
	}

	/**
	 * @param tblJobs
	 *            the tblJobs to set
	 */
	public void setTblJobs(TblJobs tblJobs) {
		this.tblJobs = tblJobs;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the salida
	 */
	public String getSalida() {
		return salida;
	}

	/**
	 * @param salida
	 *            the salida to set
	 */
	public void setSalida(String salida) {
		this.salida = salida;
	}

	/**
	 * @return the codigoerror
	 */
	public String getCodigoerror() {
		return codigoerror;
	}

	/**
	 * @param codigoerror
	 *            the codigoerror to set
	 */
	public void setCodigoerror(String codigoerror) {
		this.codigoerror = codigoerror;
	}

}
