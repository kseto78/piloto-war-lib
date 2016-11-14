package es.minhap.sim.model;

// Generated 18-jul-2016 10:57:14 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TblQueryLog generated by hbm2java
 */
@Entity
@Table(name = "TBL_QUERYLOG")
public class TblQueryLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7954311699437826729L;

	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	private Long id;

	@Column(name = "QUERYSTRING", nullable = false, length = 2000)
	private String querystring;

	@Column(name = "FECHA", nullable = false, length = 7)
	private Date fecha;

	public TblQueryLog() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the querystring
	 */
	public String getQuerystring() {
		return querystring;
	}

	/**
	 * @param querystring
	 *            the querystring to set
	 */
	public void setQuerystring(String querystring) {
		this.querystring = querystring;
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

}
