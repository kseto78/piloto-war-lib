package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TblErrorMensajeLog generated by hbm2java
 */
@Entity
@Table(name = "TBL_ERRORMENSAJE_LOG")
public class TblErrorMensajeLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 386550746786642302L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ERRORMENSAJELOGID_SEC")
    @SequenceGenerator(name="ERRORMENSAJELOGID_SEC", sequenceName="ERRORMENSAJELOGID_SEC", allocationSize=1)
	@Column(name = "ERRORMENSAJELOGID", unique = true, nullable = false, precision = 22, scale = 0)
	private Long errormensajelogid;
	
	@Column(name = "FECHA", nullable = false, length = 7)
	private Date fecha;

	@Column(name = "OPERACION", length = 128)
	private String operacion;

	@Column(name = "CODIGOERROR", precision = 22, scale = 0)
	private Long codigoerror;

	@Column(name = "DESCRIPCIONERROR", length = 1024)
	private String descripcionerror;

	public TblErrorMensajeLog() {
	}

	/**
	 * @return the errormensajelogid
	 */
	public Long getErrormensajelogid() {
		return errormensajelogid;
	}

	/**
	 * @param errormensajelogid
	 *            the errormensajelogid to set
	 */
	public void setErrormensajelogid(Long errormensajelogid) {
		this.errormensajelogid = errormensajelogid;
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
	 * @return the operacion
	 */
	public String getOperacion() {
		return operacion;
	}

	/**
	 * @param operacion
	 *            the operacion to set
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	/**
	 * @return the codigoerror
	 */
	public Long getCodigoerror() {
		return codigoerror;
	}

	/**
	 * @param codigoerror
	 *            the codigoerror to set
	 */
	public void setCodigoerror(Long codigoerror) {
		this.codigoerror = codigoerror;
	}

	/**
	 * @return the descripcionerror
	 */
	public String getDescripcionerror() {
		return descripcionerror;
	}

	/**
	 * @param descripcionerror
	 *            the descripcionerror to set
	 */
	public void setDescripcionerror(String descripcionerror) {
		this.descripcionerror = descripcionerror;
	}

}
