package es.mpr.plataformamensajeria.servicios.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.beanutils.converters.DateConverter;

import es.minhap.plataformamensajeria.iop.manager.TblContactosManager;
import es.minhap.plataformamensajeria.iop.manager.TblServiciosManager;
import es.minhap.sim.model.TblContactos;
import es.minhap.sim.model.ViewContactos;
import es.mpr.plataformamensajeria.beans.ContactoBean;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioContacto;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>
 * Maneja la persistencia y b&uacute;squeda de usuarios a traves de JPA.
 * 
 * @author
 * 
 */
@Service("servicioContactosImpl")
public class ServicioContactosImpl implements ServicioContacto {

	protected static final String NOMBRE = "nombre";

	/** logger. */
	private static Logger logger = Logger.getLogger(ServicioContactosImpl.class);

	/** tbl ContactosManager. */
	@Resource(name = "tblContactosManagerImpl")
	private TblContactosManager contactosManager;
	
	/**  tbl servicios manager. */
	@Resource(name = "TblServiciosManagerImpl")
	private TblServiciosManager serviciosManager;
	
	
	protected List<ContactoBean> getListContactosBean(List<ViewContactos> lista) throws BusinessException {

		// pasar del objeto entry de bbdd al objeto normal de desarrollo.
		List<ContactoBean> result = null;

		if (lista != null && !lista.isEmpty()) {
			result = new ArrayList<>();

			for (ViewContactos o : lista) {
				ContactoBean contacto = new ContactoBean();

				try {
					Date defaultValue = null;
					DateConverter converter = new DateConverter(defaultValue);
					Long defaultLongValue = null;
					LongConverter longConverter = new LongConverter(
							defaultLongValue);
					Integer defaultIntegerValue = null;
					IntegerConverter integerConverter = new IntegerConverter(
							defaultIntegerValue);
					ConvertUtils.register(longConverter, java.lang.Long.class);
					ConvertUtils.register(integerConverter,
							java.lang.Integer.class);
					ConvertUtils.register(converter, java.util.Date.class);
					BeanUtils.copyProperties(contacto, o);
					contacto.setContactoId(o.getContactoid());		
				} catch (IllegalAccessException | InvocationTargetException e) {
					throw new BusinessException(e);
				}

				result.add(contacto);
			}
		}
		return result;
	}

	@Override
	public PaginatedList<ContactoBean> getContactos(int start, int size, String order, String columnSort,
			ContactoBean criterio) throws BusinessException {

		try {
			// Columna para ordenar
			 HashMap<String, String> columns = new HashMap<>();
			 columns.put("2","aplicacion");
			 columns.put("3","servicio");
			 columns.put("4",NOMBRE);
			 columns.put("5","apellidos");
			 columns.put("6","email");
			 columns.put("7","telefono");
		
			 String column = columns.get(columnSort);
			 if (column==null){
			 column = NOMBRE;
			 }


			es.minhap.plataformamensajeria.iop.beans.ContactoBean ob = new es.minhap.plataformamensajeria.iop.beans.ContactoBean();
			ob = createContactoBean(criterio, ob);
			List<ViewContactos> lista = contactosManager.getContactosPaginado(start, size, order, column, ob);			
			List<ContactoBean> pageList = getListContactosBean(lista);

			if(null == pageList){
				pageList = new ArrayList<>();
			}
			PaginatedList<ContactoBean> result = new PaginatedList<>();
			result.setPageList(pageList);
			
			return result;
		} catch (Exception e) {
			logger.error("ServicioContactoImpl - getUsuarios:" + e);
			throw new BusinessException(e, "errors.organismo.getOrganismos");
		}

	}

	private es.minhap.plataformamensajeria.iop.beans.ContactoBean createContactoBean(
			ContactoBean criterio,
			es.minhap.plataformamensajeria.iop.beans.ContactoBean ob) {
		try {
			if (null != criterio) {
				Date defaultValue = null;
				DateConverter converter = new DateConverter(defaultValue);
				Long defaultLongValue = null;
				LongConverter longConverter = new LongConverter(
						defaultLongValue);
				Integer defaultIntegerValue = null;
				IntegerConverter integerConverter = new IntegerConverter(
						defaultIntegerValue);
				ConvertUtils.register(longConverter, java.lang.Long.class);
				ConvertUtils
						.register(integerConverter, java.lang.Integer.class);
				ConvertUtils.register(converter, java.util.Date.class);
				BeanUtils.copyProperties(ob, criterio);			
			}

		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("ServicioOrganismoImpl - getOrganismoBean:" + e);
		}
		return ob;
	}

	@Override
	public Long newContacto(ContactoBean contactos, String source, String accion, Long accionId)
			throws BusinessException {
		try {
			
			TblContactos contactoTO = getContactoTO(contactos);
			contactoTO.setFechacreacion(new Date());
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			contactoTO.setCreadopor(modificador);
			Long idContacto = contactosManager.insert(contactoTO, source, accion, accionId);

			contactos.setContactoId(idContacto);

			contactos.setFechacreacion(contactoTO.getFechacreacion());
			contactos.setCreadopor(contactoTO.getCreadopor());
			return contactos.getContactoId();

		} catch (Exception e) {
			logger.error("ServicioAplicaciones - newAplicacion:" + e);
			BusinessException exc = new BusinessException(e, "errors.organismo.newOrganismo");
			exc.mRechargeInput();
			throw exc;
		}

	}

	protected TblContactos getContactoTO(ContactoBean contactos) {
		TblContactos contactoTO = new TblContactos();		

		try {
			Date defaultValue = null;
			DateConverter converter = new DateConverter(defaultValue);
			Long defaultLongValue = null;
			LongConverter longConverter = new LongConverter(defaultLongValue);
			Integer defaultIntegerValue = null;
			IntegerConverter integerConverter = new IntegerConverter(
					defaultIntegerValue);
			ConvertUtils.register(longConverter, java.lang.Long.class);
			ConvertUtils.register(integerConverter, java.lang.Integer.class);
			ConvertUtils.register(converter, java.util.Date.class);
			BeanUtils.copyProperties(contactoTO, contactos);
			contactoTO.setContactoid((null != contactos.getContactoId()) ? contactos
							.getContactoId().longValue() : null);
			contactoTO.setFechacreacion(contactos.getFechacreacion());
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("ServicioProcesosManualesImpl - getProcesosManualesTO:" + e);
		}
		return contactoTO;
	}

	@Override
	public void updateContacto(ContactoBean contactos, String source, String accion, Long accionId)
			throws BusinessException {
		try {
			TblContactos contactosTO = getContactoTO(contactos);
			String modificador = PlataformaMensajeriaUtil.getUsuarioLogueado().getNombreCompleto();
			contactosTO.setModificadopor(modificador);
			contactosTO.setFechamodificacion(new Date());
			contactosManager.update(contactosTO, source, accion, accionId);
		} catch (Exception e) {
			logger.error("ServicioContacto - updateContacto:" + e);
			throw new BusinessException(e, "plataforma.contacto.delete.error");
		}

	}

	@Override
	public ContactoBean loadContacto(ContactoBean contactos) throws BusinessException {

	try {
		TblContactos contactoTO = contactosManager.getById(contactos.getContactoId());
		return getContactosBean(contactoTO);
	} catch (Exception e) {
		logger.error("ServicioContactos - loadContactos: " + e);
		throw new BusinessException(e, "plataforma.contacto.update.error");
	}

	}

	@Override
	public void deleteContacto(Long contactoId, String source, String accion, Long accionId)
			throws BusinessException {
		try {
			TblContactos o = contactosManager.getById(contactoId);

			o.setModificadopor(PlataformaMensajeriaUtil.getUsuarioLogueado()
					.getNombreCompleto());
			o.setFechamodificacion(new Date());
			o.setEliminado("S");	
			contactosManager.update(o, source, accion, accionId);			
		} catch (Exception e) {
			logger.error("ServicioContactosImpl - deleteContacto:" + e);
			throw new BusinessException(e, "plataforma.contactos.delete.error");
		}
	}

	protected ContactoBean getContactosBean(TblContactos contactosTO){


		ContactoBean contacto = new ContactoBean();
		try{
		
			Date defaultValue = null;
			DateConverter converter = new DateConverter(defaultValue);
			Long defaultLongValue = null;
			LongConverter longConverter = new LongConverter(defaultLongValue);
			Integer defaultIntegerValue = null;
			IntegerConverter integerConverter = new IntegerConverter(
					defaultIntegerValue);
			ConvertUtils.register(longConverter, java.lang.Long.class);
			ConvertUtils.register(integerConverter, java.lang.Integer.class);
			ConvertUtils.register(converter, java.util.Date.class);
			BeanUtils.copyProperties(contacto, contactosTO);
			
			contacto.setContactoId(contactosTO.getContactoid());

		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("ServicioContactosImpl - getContactosBean:" + e);
		}
		return contacto;
	}


}
