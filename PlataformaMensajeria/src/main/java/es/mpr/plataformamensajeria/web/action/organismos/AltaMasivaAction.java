package es.mpr.plataformamensajeria.web.action.organismos;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Preparable;

import es.minhap.plataformamensajeria.iop.beans.OrganismosServicioBean;
import es.minhap.plataformamensajeria.iop.beans.PdpDiputacionesBean;
import es.minhap.sim.model.TblOrganismos;
import es.mpr.plataformamensajeria.beans.AplicacionBean;
import es.mpr.plataformamensajeria.beans.DetalleAplicacionBean;
import es.mpr.plataformamensajeria.beans.OrganismoBean;
import es.mpr.plataformamensajeria.beans.PlanificacionBean;
import es.mpr.plataformamensajeria.beans.ResultOptions;
import es.mpr.plataformamensajeria.beans.SelectOption;
import es.mpr.plataformamensajeria.beans.ServicioBean;
import es.mpr.plataformamensajeria.beans.ServicioOrganismosBean;
import es.mpr.plataformamensajeria.beans.ServidorBean;
import es.mpr.plataformamensajeria.beans.ServidoresOrganismosBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioOrganismo;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioPdpDiputaciones;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServicio;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioServidor;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioUsuarioAplicacion;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;

/**
 * <p>
 * Clase Action de Struts2 para la gesti&oacute;n de los organismos.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n, borrado y listado de los Organismos
 * 
 * @author Altran
 * 
 */
@Controller("altaMasivaAction")
@Scope("prototype")
public class AltaMasivaAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable {

	private static final String ALTA_MASIVA_ACTION_CARGAR_COMBO_SERVICIO_ORGANISMOS = "AltaMasivaAction - cargarComboServicioOrganismos:";

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**  logger. */
	private static Logger logger = Logger.getLogger(AltaMasivaAction.class);
	
	
	/**  servicio organismo. */
	@Resource(name="servicioOrganismoImpl")
	private transient ServicioOrganismo servicioOrganismo;
	
	/**  servicio organismo pdp. */
	@Resource(name="servicioPdpDiputacionesImpl")
	private transient ServicioPdpDiputaciones servicioOrganismoPdp;
	
	/**  servicio servicio. */
	@Resource(name="servicioServicioImpl")
	private transient ServicioServicio servicioServicio;
		
	/**  servicio usuario aplicacion. */
	@Resource(name="servicioUsuarioAplicacionImpl")
	private transient ServicioUsuarioAplicacion servicioUsuarioAplicacion;
	
	/**  servicio servidor. */
	@Resource(name="servicioServidorImpl")
	private transient ServicioServidor servicioServidor;
	
	/**  properties. */
	@Resource(name = "plataformaMensajeriaProperties")
	private transient PlataformaMensajeriaProperties properties;	
	
	/**  organismo. */
	private OrganismoBean organismo; 
	
	/**  servidor organismo. */
	private ServidoresOrganismosBean servidorOrganismo;
	
	/**  aplicacion. */
	private AplicacionBean aplicacion;
	
	/**  servicio organismos. */
	transient ServicioOrganismosBean servicioOrganismos;
	
	/**  servicio. */
	private ServicioBean servicio;
	
	/**  servidor. */
	private ServidorBean servidor;	
		
	/**  lista organismos. */
	transient List<OrganismoBean> listaOrganismos = null;
	
	/**  lista servicio organismos. */
	transient List<ServicioBean> listaServicioOrganismos = null;
	
	/**  lista servidores organismos. */
	transient List<ServidoresOrganismosBean> listaServidoresOrganismos = null;
	
	/**  lista planificaciones servicio. */
	transient List<PlanificacionBean> listaPlanificacionesServicio = null;
	
	/**  combo servicio organismos. */
	transient List<KeyValueObject> comboServicioOrganismos = new ArrayList<>();
	
	/**  combo organismos pdp. */
	transient List<KeyValueObject> comboOrganismosPdp = new ArrayList<>();
	
	/**  combo organismos hijos */
	transient List<KeyValueObject> comboOrganismosHijos = new ArrayList<>();
	
	/**  combo servidores organismos. */
	transient List<KeyValueObject> comboServidoresOrganismos = new ArrayList<>();
	
	/**  combo servidores plan. */
	transient List<KeyValueObject> comboServidoresPlan = new ArrayList<>();
	
	/**  combo servicios plan. */
	transient List<KeyValueObject> comboServiciosPlan = new ArrayList<>();
	
	/**  combo servidores. */
	transient List<KeyValueObject> comboServidores = new ArrayList<>();
	
	/**  combo tipos estados. */
	transient List<KeyValueObject> comboTiposEstados = new ArrayList<>();
	
	/**  check del list. */
	private String[] checkDelList;
	
	/**  check del list organismos servicios. */
	private String[] checkDelListOrganismosServicios;
	
	/**  check del list servidor organismos. */
	private String[] checkDelListServidorOrganismos;
	
	/**  check del list planificaciones organismos. */
	private String[] checkDelListPlanificacionesOrganismos;
	
	/**  id organismo. */
	private String idOrganismo;
	
	/**  id servicio organismo. */
	private String idServicioOrganismo;
	
	/**  servidor organismo id. */
	private String servidorOrganismoId;
	
	/**  id planificacion. */
	private String idPlanificacion;
	
	/**  id servicio. */
	private String idServicio;
	
	/**  id servidor. */
	private String idServidor;
	
	/**  id proveedor SMS. */
	private String idProveedorSMS;
	
	/**  id receptor SMS. */
	private String idReceptorSMS;
	
	/**  id servidor push. */
	private String idServidorPush;
	
	/**  result count. */
	private String resultCount;
	
	/**  check password. */
	private String checkPassword;
	
	/**  recovery. */
	private String recovery = "";
	
	/**  json. */
	private String json;
	
	/** archivo excel */
	private File archivoExcel;
	
	/** archivo excel */
	private String formatoArchivoExcel;
	
	/** datos servicios */
	private String datosServicios;
	
	/** datos servicios */
	private String datosServiciosTabla;	
	
	/** datos servidor */
	private String datosServidor;
	
	/**  session. */
	@SuppressWarnings("rawtypes")
	transient Map session;
	
	/** Constante INFO_USER. */
	private static final String INFO_USER = "infoUser";

	/** Constante NO_USER. */
	private static final String NO_USER = "noUser";

	/** Constante GENERALES_REQUEST_ATTRIBUTE_TOTALSIZE. */
	private static final String GENERALES_REQUEST_ATTRIBUTE_TOTALSIZE = "generales.REQUEST_ATTRIBUTE_TOTALSIZE";

	/** Constante LOG_ACCIONID_ACTUALIZAR. */
	private static final String LOG_ACCIONID_ACTUALIZAR = "log.ACCIONID_ACTUALIZAR";

	/** Constante LOG_ACCION_ACTUALIZAR. */
	private static final String LOG_ACCION_ACTUALIZAR = "log.ACCION_ACTUALIZAR";


	/** Constante LOG_SOURCE_ALTASMASIVAS. */
	private static final String LOG_SOURCE_ALTASMASIVAS = "log.SOURCE_ALTASMASIVAS";
	
	/** Constante RECOVERY. */
	private static final String RECOVERY = "recovery";

	/**
	 * New search.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String newSearch() {
		if (getRequest().getSession().getAttribute(AltaMasivaAction.INFO_USER) == null)
			return AltaMasivaAction.NO_USER;
		
		organismo = (OrganismoBean) getRequest().getSession().getAttribute("organismo");
		
		
		Integer totalSize = 0;
		getRequest().setAttribute(properties.getProperty(AltaMasivaAction.GENERALES_REQUEST_ATTRIBUTE_TOTALSIZE, null), totalSize);
		listaOrganismos =new ArrayList<>();
		
		return SUCCESS;
	}
	
	/**
	 * Arbol Organismos.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 */
	public String arbolOrganismos() throws BaseException {
		try {
			SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error("OrganismosAction - load:" + e);
			return AltaMasivaAction.NO_USER;
		}
		if (idOrganismo == null)
			throw new BusinessException("EL idOrganismo recibido es nulo");
		try {
			organismo = new OrganismoBean();
			organismo.setOrganismoId(new Integer(idOrganismo));
			organismo = servicioOrganismo.loadOrganismo(organismo);
			comboOrganismosHijos = cargarComboOrganismosHijos(organismo.getDir3());

			return SUCCESS;
		} catch (NumberFormatException | BusinessException e) {
			String mensg = this.getText("errors.action.organismo.loadOrganismo", new String[] { organismo.getOrganismoId().toString() });
			logger.error("OrganismosAction - load:" + e);
			throw new BusinessException(mensg);
		} 
	}
	
	
    /* (non-Javadoc)
     * @see es.mpr.plataformamensajeria.impl.PlataformaPaginationAction#prepare()
     */
    ///MIGRADO
	@SuppressWarnings("rawtypes")
	@Override
	public void prepare() throws Exception {
		session = (Map) ActionContext.getContext().get("session");
		recovery = (String) session.get(RECOVERY);
		
		comboOrganismosPdp = cargarComboOrganismosPdp();
		comboServicioOrganismos = cargarComboServicioOrganismos();
		comboServidoresOrganismos = cargarComboServidoresOrganismos();	
	}
	
	private List<KeyValueObject> cargarComboOrganismosPdp() {
		List<KeyValueObject> result = new ArrayList<>();

		KeyValueObject option;

		ArrayList<PdpDiputacionesBean> keys = null;
		try {
			keys = (ArrayList<PdpDiputacionesBean>) servicioOrganismoPdp.getPdpDiputaciones();
		} catch (BusinessException e) {
			logger.error(ALTA_MASIVA_ACTION_CARGAR_COMBO_SERVICIO_ORGANISMOS + e);
		}

		if (keys != null && !keys.isEmpty())
			for (PdpDiputacionesBean key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getPdpDiputacionesId().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		return result;
	}

	/**
	 * Ajax load servicios.
	 *
	 * @return the string
	 */
	///MIGRADO
	public String ajaxLoadServicios() {
		if (getRequest().getSession().getAttribute("infoUser") == null)
			return "noUser";
		if (idServicio == null) {
			addFieldErrorSession("Datos incorrectos");
		} else {
			ResultOptions rOptions = new ResultOptions();
			try {
				if (idServicio.length() > 0) {
					ServicioBean sBean = new ServicioBean();
					sBean.setServicioId(Integer.valueOf(idServicio));
					ServicioBean servBean = servicioServicio.loadServicio(sBean);
					rOptions.getItems()
					.add(new SelectOption(String.valueOf(servBean.getServicioId()),"id"));
					rOptions.getItems()
					.add(new SelectOption(String.valueOf(servBean.getNombre()),"nombre"));
					rOptions.getItems()
					.add(new SelectOption(String.valueOf(servBean.getDescripcion()),"descripcion"));				
					
				} else {
					String rolUsuario = PlataformaMensajeriaUtil.getRolFromSession(request);
					Integer idUsuario = PlataformaMensajeriaUtil.getIdUsuarioFromSession(request);
//					listado = (ArrayList<ServicioBean>) servicioServicio.getServicios(rolUsuario, idUsuario);
				}
				
			} catch (Exception e) {
				logger.error("ServicioAction - ajaxLoadServicios:" + e);
			}
			setJson(new Gson().toJson(rOptions));
		}
		return SUCCESS;

	}
	
	/**
	 * Ejecucion alta masiva.
	 *
	 * @return the string
	 * @throws BaseException the base exception
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 * @throws EncryptedDocumentException 
	 */
	///MIGRADO
	public String ejecucionAltaMasiva() throws BaseException, IOException, InvalidFormatException {
	
	String accion = properties.getProperty(AltaMasivaAction.LOG_ACCION_ACTUALIZAR, null);
	Long accionId = Long.parseLong(properties.getProperty(AltaMasivaAction.LOG_ACCIONID_ACTUALIZAR, null));
	String source = properties.getProperty(AltaMasivaAction.LOG_SOURCE_ALTASMASIVAS, null);
	String descripcion = properties.getProperty("log.ACCION_DESCRIPCION_ANADIR_SERVIDOR", null);

	String[] idServicios = null;
	String[] infoServidor = null;
		
	//Comprobaciones iniciales
	/////////////////////////
	if(archivoExcel == null){
		addActionErrorSession(this.getText("plataforma.organismos.altasmasivas.error.seleccion.archivo"));		
	}
	if(datosServicios.equals("")){
		addActionErrorSession(this.getText("plataforma.organismos.altasmasivas.error.seleccion.servicio"));	
	}else{
		idServicios = datosServicios.split(",");
		List<String> listaServicios = Arrays.asList(idServicios); 		
		listaServicioOrganismos = new ArrayList<>();	
		for(String serv:listaServicios){	
			ServicioBean sBean = new ServicioBean();
			sBean.setServicioId(Integer.valueOf(serv));
			ServicioBean servBean = servicioServicio.loadServicio(sBean);
			listaServicioOrganismos.add(servBean);
		}
	}
	if(datosServidor.equals("")){
		addActionErrorSession(this.getText("plataforma.organismos.altasmasivas.error.seleccion.servidor"));	
	}else{
		listaServidoresOrganismos = new ArrayList<>();
		ServidoresOrganismosBean servBean = new ServidoresOrganismosBean();
		infoServidor = datosServidor.split(",");	
		servBean.setServidorId(Long.valueOf(infoServidor[0]));
		servBean.setHeaderSMS(infoServidor[1]);
		servBean.setProveedorUsuarioSMS(infoServidor[2]);
		servBean.setProveedorPasswordSMS(infoServidor[3]);
		servBean.setNombreServidor(infoServidor[4]);
		listaServidoresOrganismos.add(servBean);
	}
	
	if(archivoExcel == null || datosServicios.equals("") || datosServidor.equals("")){
		return SUCCESS;
	}
	/////////////////////////////

	ArrayList<String> organismos = null;
	if(formatoArchivoExcel.equals("xls")){
		organismos = leerArchivoExcel();
	}else{
		organismos = leerArchivoExcelXlsx();
	}
	
	if(organismos==null || organismos.isEmpty()){
		return SUCCESS;
	}
			
	
	//Comprueba si no existen organismos o existe pero tiene el campo Eliminado = 'S'
	ArrayList<String> existenOrganismos = comprobarOrganismos(organismos);
  
	StringBuilder listaOrg = new StringBuilder("");
	if(!existenOrganismos.isEmpty()){
		
		for(String org: existenOrganismos){
			listaOrg.append(org + "\t");		
		}
		addActionErrorSession(this.getText("plataforma.organismos.altasmasivas.error.organismos")+" "+listaOrg);		
		return SUCCESS;
	}
	///////////////////////////////////////////////////////////////////
	
	ArrayList<TblOrganismos> listadoOrganismos = obtenerOrganismos(organismos);
	
	//Comprueba que los organismos no tienen configurados un servidor/proveedor	
	for(TblOrganismos org:listadoOrganismos){
		if(!servicioServidor.getServidorOrganismo(String.valueOf(org.getOrganismoid())).isEmpty()){
			listaOrg.append(org.getDir3() + "\t");	
		}
	}
	if(!listaOrg.toString().equals("")){
		addActionErrorSession(this.getText("plataforma.organismos.altasmasivas.error.servidor")+" "+listaOrg);				
		return SUCCESS;
	}
	/////////////////////////////////////////
	
	//Comprueba que cada uno de los organismos del excel no tienen ya uno de los servicios que selecciona el usuario
	for(TblOrganismos orga:listadoOrganismos){		
		
		//Obtenemos la lista ServicioOrganismo para cada organismo del excel
		List<OrganismosServicioBean> listaServiciosOrganismo = servicioServicio.getServicioByOrganismo(orga.getOrganismoid());
		
		//Comparamos los servicios de la lista ServicioOrganismo de cada organismo con la lista de servicios que selecciona el usuario
		for(OrganismosServicioBean servOrg:listaServiciosOrganismo){
			for(ServicioBean serv:listaServicioOrganismos){
				if(serv.getServicioId().equals(servOrg.getServicioId())){					
					listaOrg.append("<p>"+ "Organismo DIR3: "+orga.getDir3() + " con el servicio ID: "+serv.getServicioId() + "\t"+"</p>");
				}
			}
		}	
	}
	
	if(!listaOrg.toString().equals("")){
		addActionErrorSession(this.getText("plataforma.organismos.altasmasivas.error.servicio")+listaOrg);					
		return SUCCESS;
	}	
	///////////////////////////////////////////////////
	//Comprobaciones terminadas	
	/////////////////////////////////////////////////
	
	StringBuilder orgModificados = new StringBuilder();
	//////////Alta de datos
	for(TblOrganismos orga:listadoOrganismos){
		
		///////Marcamos organismo como activo
		OrganismoBean orgBean = new OrganismoBean();
		orgBean.setOrganismoId(orga.getOrganismoid().intValue());
		orgBean = servicioOrganismo.loadOrganismo(orgBean);
		orgBean.setActivo(true);
		if(organismo.getIdPdpDiputaciones()!=null){
			orgBean.setIdPdpDiputaciones(organismo.getIdPdpDiputaciones());
		}		
		servicioOrganismo.updateOrganismo(orgBean, source, accion, accionId);		
		//////////////////////////////////////
		
		
		/////Damos de alta la relacion organismo-servidor
		ServidoresOrganismosBean servidorOrg = new ServidoresOrganismosBean();
		servidorOrg.setOrganismoId(orga.getOrganismoid());
		servidorOrg.setServidorId(listaServidoresOrganismos.get(0).getServidorId());
		servidorOrg.setHeaderSMS(listaServidoresOrganismos.get(0).getHeaderSMS());
		servidorOrg.setProveedorUsuarioSMS(listaServidoresOrganismos.get(0).getProveedorUsuarioSMS());
		servidorOrg.setProveedorPasswordSMS(listaServidoresOrganismos.get(0).getProveedorPasswordSMS());
		servicioServidor.newServidoresOrganismo(servidorOrg, source, accion, accionId, descripcion);
		//////////////////////////////////////////////
		
		////Damos de alta la relacion organismo-servicios
		for(ServicioBean servicioActual:listaServicioOrganismos){
			ServicioOrganismosBean orgServicios = new ServicioOrganismosBean();
			orgServicios.setOrganismoId(orga.getOrganismoid().intValue());
			orgServicios.setServicioId(servicioActual.getServicioId());
			servicioServicio.newServicioOrganismo(orgServicios, source, accion, accionId, descripcion);
		}

		orgModificados.append(orga.getDir3());
		orgModificados.append("\t");
		}	
	
		//Para borrar datos de servicios, servidores y la seleccion del organismo pdp si hubiera
		listaServicioOrganismos = null; 
		listaServidoresOrganismos = null;
		organismo = null;	
	
		addActionMessageSession(this.getText("plataforma.organismos.altasmasivas.update.ok"));
		addActionMessageSession("Organismos modificados: "+orgModificados);
		return SUCCESS;
	}

	private ArrayList<String> leerArchivoExcelXlsx() throws IOException, InvalidFormatException {
		ArrayList<String> organismos = new ArrayList<>();

	    FileInputStream fis = new FileInputStream(archivoExcel);	   
	   
	    XSSFWorkbook workbook = new XSSFWorkbook(fis);
	 
	    XSSFSheet sheet = workbook.getSheetAt(0);
	
	    Iterator<Row> rowIt = sheet.iterator();

	    while(rowIt.hasNext()) {
	      Row row = rowIt.next();

	      Iterator<Cell> cellIterator = row.cellIterator();

	      while (cellIterator.hasNext()) {
	        Cell cell = cellIterator.next();
	        if(cell.getRowIndex() != 0 && !cell.toString().equals("")){
	        	organismos.add(cell.toString());
	        }
	      }
	     
	    }

	    fis.close();
	    if(organismos.isEmpty()){
	    	addActionErrorSession(this.getText("plataforma.organismos.altasmasivas.error.existencia"));
	    }
		return organismos;
	}

	private ArrayList<TblOrganismos> obtenerOrganismos(ArrayList<String> organismos) {
		ArrayList<TblOrganismos> organismosValidos = new ArrayList<>();
		
		for(String orgActual: organismos){			
			 
			List<TblOrganismos> orgn = servicioOrganismo.getOrganismoById(orgActual);
			
			for(TblOrganismos orgnTemp : orgn){
				organismosValidos.add((orgnTemp));
			}
		}
		
		return organismosValidos;
	}
	
	private ArrayList<String> comprobarOrganismos(ArrayList<String> organismos) {
		ArrayList<String> organismosNoValidos = new ArrayList<>();
		
		for(String orgActual: organismos){			
			int id;
			id = servicioOrganismo.getOrganismoIdByDir3SoloEliminado(orgActual);
			
			if (id==0){
				organismosNoValidos.add(orgActual);					
			}
		}
		
		return organismosNoValidos;
	}

	private ArrayList<String> leerArchivoExcel() throws IOException, InvalidFormatException {
		
		InputStream excelFileToRead = new FileInputStream(archivoExcel);
		ArrayList<String> organismos = new ArrayList<>();
		HSSFWorkbook wb;		
		wb = new HSSFWorkbook(excelFileToRead);
		
	    HSSFSheet sheet = wb.getSheetAt(0);
	    HSSFRow row;
	    HSSFCell cell;

	    int rows; // No of rows
	    rows = sheet.getPhysicalNumberOfRows();

	    int cols = 0; // No of columns	    
	    int tmp = 0;
	    
	    for(int i = 0; i < 10 || i < rows; i++) {
	        row = sheet.getRow(i);
	        if(row != null) {
	            tmp = sheet.getRow(i).getPhysicalNumberOfCells();
	            if(tmp > cols) cols = tmp;
	        }
	    }

	    for(int r = 1; r < rows; r++) {
	        row = sheet.getRow(r);
	        if(row != null) {
	            for(int c = 0; c < cols; c++) {
	                cell = row.getCell(c);
	                if(cell != null) {	                	
	                	if(cell.getCellType() == 0 ){
	                		cell.setCellType(1);
	                		if(!cell.getStringCellValue().equals("")){
	                			organismos.add(cell.getStringCellValue());
	                		}
	                	}else{
	                		if(!cell.getStringCellValue().equals("")){
	                			organismos.add(cell.getStringCellValue());
	                		}	                		
	                	}	                	
	                }
	            }
	        }
	    }
	    if(organismos.isEmpty()){
	    	addActionErrorSession(this.getText("plataforma.organismos.altasmasivas.error.existencia"));
	    }	    	
	    return organismos;
	}

	/**
	 * Cargar combo servicio organismos.
	 *
	 * @return the list
	 */
	///MIGRADO
	private List<KeyValueObject> cargarComboServicioOrganismos() {
		List<KeyValueObject> result = new ArrayList<>();

		KeyValueObject option;
		String listaServ = properties.getProperty("altasmasivas.comboservicios.serviciosAeatGiss", null);
		List<String> serviciosAeatGiss = new ArrayList<String>(Arrays.asList(listaServ.split(",")));
		ArrayList<ServicioBean> keys = null;
		try {
			keys = (ArrayList<ServicioBean>) servicioServicio.getServiciosMultiorganismo();
		} catch (BusinessException e) {
			logger.error("OrganismosAction - cargarComboServicioOrganismos:" + e);
		}

		if (keys != null && !keys.isEmpty())
			for (ServicioBean key : keys) {
				for(String idServ : serviciosAeatGiss){
					if(idServ.equals(key.getServicioId().toString())){
						option = new KeyValueObject();
						option.setCodigo(key.getServicioId().toString());
						option.setDescripcion(key.getNombre());
						result.add(option);
					}
				}
			}
		return result;
	}

	/**
	 * Cargar combo servidores organismos.
	 *
	 * @return the list
	 */
	///MIGRADO
	private List<KeyValueObject> cargarComboServidoresOrganismos() {
		List<KeyValueObject> result = new ArrayList<>();

		KeyValueObject option;

		ArrayList<ServidorBean> keys = null;
		try {
			keys = (ArrayList<ServidorBean>) servicioServidor.getServidoresYProveedores(properties.getProperty("generales.TIPO_SERVIDOR_SMS", null));
		} catch (BusinessException e) {
			logger.error("OrganismosAction - cargarComboServicioOrganismos:" + e);
		}

		if (keys != null && !keys.isEmpty())
			for (ServidorBean key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getServidorid().toString());
				option.setDescripcion(key.getNombre());
				result.add(option);
			}
		return result;
	}

	/**
	 * Cargar combo organismos hijos.
	 * @param dir3 
	 *
	 * @return the list
	 */
	///MIGRADO
	private List<KeyValueObject> cargarComboOrganismosHijos(String dir3) {
		List<KeyValueObject> result = new ArrayList<>();

		KeyValueObject option;

		ArrayList<TblOrganismos> keys = null;
		keys = (ArrayList<TblOrganismos>) servicioOrganismo.getOrganismosHijos(dir3);

		if (keys != null && !keys.isEmpty())
			for (TblOrganismos key : keys) {
				option = new KeyValueObject();
				option.setCodigo(key.getDir3());				
				result.add(option);
			}
		return result;
	}
	
	
	/**
	 * Modificar servidor.
	 *
	 * @param aplicacion new servidor
	 */
	public void setServidor(AplicacionBean aplicacion) {
		this.aplicacion = aplicacion;
	}

	/**
	 * Obtener id servidor.
	 *
	 * @return id servidor
	 */
	public String getIdServidor() {
		return idServidor;
	}

	/**
	 * Obtener result count.
	 *
	 * @return result count
	 */
	public String getResultCount() {
		return resultCount;
	}

	/**
	 * Modificar result count.
	 *
	 * @param resultCount new result count
	 */
	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}

	/**
	 * Obtener check del list.
	 *
	 * @return check del list
	 */
	public String[] getCheckDelList() {
		return checkDelList;
	}

	/**
	 * Modificar check del list.
	 *
	 * @param checkDelList new check del list
	 */
	public void setCheckDelList(String[] checkDelList) {
		this.checkDelList = checkDelList;
	}

	/**
	 * Obtener servicio usuario aplicacion.
	 *
	 * @return servicio usuario aplicacion
	 */
	public ServicioUsuarioAplicacion getServicioUsuarioAplicacion() {
		return servicioUsuarioAplicacion;
	}

	/**
	 * Modificar servicio usuario aplicacion.
	 *
	 * @param servicioUsuarioAplicacion new servicio usuario aplicacion
	 */
	public void setServicioUsuarioAplicacion(ServicioUsuarioAplicacion servicioUsuarioAplicacion) {
		this.servicioUsuarioAplicacion = servicioUsuarioAplicacion;
	}

	/**  detalle aplicacion. */
	transient DetalleAplicacionBean detalleAplicacion;

	/**
	 * Obtener detalle aplicacion.
	 *
	 * @return detalle aplicacion
	 */
	public DetalleAplicacionBean getDetalleAplicacion() {
		return detalleAplicacion;
	}

	/**
	 * Modificar detalle aplicacion.
	 *
	 * @param detalleAplicacion new detalle aplicacion
	 */
	public void setDetalleAplicacion(DetalleAplicacionBean detalleAplicacion) {
		this.detalleAplicacion = detalleAplicacion;
	}


	/**
	 * Obtener servicio servicio.
	 *
	 * @return servicio servicio
	 */
	public ServicioServicio getServicioServicio() {
		return servicioServicio;
	}

	/**
	 * Modificar servicio servicio.
	 *
	 * @param servicioServicio new servicio servicio
	 */
	public void setServicioServicio(ServicioServicio servicioServicio) {
		this.servicioServicio = servicioServicio;
	}

	/**
	 * Modificar servicio servidor.
	 *
	 * @param servicioServidor new servicio servidor
	 */
	public void setServicioServidor(ServicioServidor servicioServidor) {
		this.servicioServidor = servicioServidor;
	}

	/**
	 * Obtener servicio servidor.
	 *
	 * @return servicio servidor
	 */
	public ServicioServidor getServicioServidor() {
		return servicioServidor;
	}

	/**
	 * Obtener id servidor push.
	 *
	 * @return id servidor push
	 */
	public String getIdServidorPush() {
		return idServidorPush;
	}

	/**
	 * Modificar id servidor push.
	 *
	 * @param idServidorPush new id servidor push
	 */
	public void setIdServidorPush(String idServidorPush) {
		this.idServidorPush = idServidorPush;
	}

	/**
	 * Obtener servicio organismo.
	 *
	 * @return servicio organismo
	 */
	public ServicioOrganismo getServicioOrganismo() {
		return servicioOrganismo;
	}

	/**
	 * Modificar servicio organismo.
	 *
	 * @param servicioOrganismo new servicio organismo
	 */
	public void setServicioOrganismo(ServicioOrganismo servicioOrganismo) {
		this.servicioOrganismo = servicioOrganismo;
	}

	/**
	 * Obtener lista servicio organismos.
	 *
	 * @return lista servicio organismos
	 */
	public List<ServicioBean> getListaServicioOrganismos() {
		return listaServicioOrganismos;
	}

	/**
	 * Modificar lista servicio organismos.
	 *
	 * @param listaServicioOrganismos new lista servicio organismos
	 */
	public void setListaServicioOrganismos(List<ServicioBean> listaServicioOrganismos) {
		this.listaServicioOrganismos = listaServicioOrganismos;
	}

	/**
	 * Obtener servicio organismos.
	 *
	 * @return servicio organismos
	 */
	public ServicioOrganismosBean getServicioOrganismos() {
		return servicioOrganismos;
	}

	/**
	 * Modificar servicio organismos.
	 *
	 * @param servicioOrganismos new servicio organismos
	 */
	public void setServicioOrganismos(ServicioOrganismosBean servicioOrganismos) {
		this.servicioOrganismos = servicioOrganismos;
	}

	/**
	 * Obtener combo servicio organismos.
	 *
	 * @return combo servicio organismos
	 */
	public List<KeyValueObject> getComboServicioOrganismos() {
		return comboServicioOrganismos;
	}

	/**
	 * Modificar combo servicio organismos.
	 *
	 * @param comboServicioOrganismos new combo servicio organismos
	 */
	public void setComboServicioOrganismos(List<KeyValueObject> comboServicioOrganismos) {
		this.comboServicioOrganismos = comboServicioOrganismos;
	}

	/**
	 * Obtener servicio.
	 *
	 * @return servicio
	 */
	public ServicioBean getServicio() {
		return servicio;
	}

	/**
	 * Modificar servicio.
	 *
	 * @param servicio new servicio
	 */
	public void setServicio(ServicioBean servicio) {
		this.servicio = servicio;
	}

	/**
	 * Modificar combo servidores.
	 *
	 * @param comboServidores new combo servidores
	 */
	public void setComboServidores(List<KeyValueObject> comboServidores) {
		this.comboServidores = comboServidores;
	}

	/**
	 * Obtener id organismo.
	 *
	 * @return id organismo
	 */
	public String getIdOrganismo() {
		return idOrganismo;
	}

	/**
	 * Modificar id organismo.
	 *
	 * @param idOrganismo new id organismo
	 */
	public void setIdOrganismo(String idOrganismo) {
		this.idOrganismo = idOrganismo;
	}

	/**
	 * Obtener id servicio organismo.
	 *
	 * @return id servicio organismo
	 */
	public String getIdServicioOrganismo() {
		return idServicioOrganismo;
	}

	/**
	 * Modificar id servicio organismo.
	 *
	 * @param idServicioOrganismo new id servicio organismo
	 */
	public void setIdServicioOrganismo(String idServicioOrganismo) {
		this.idServicioOrganismo = idServicioOrganismo;
	}

	/**
	 * Obtener servidor organismo id.
	 *
	 * @return servidor organismo id
	 */
	public String getServidorOrganismoId() {
		return servidorOrganismoId;
	}

	/**
	 * Modificar servidor organismo id.
	 *
	 * @param servidorOrganismoId new servidor organismo id
	 */
	public void setServidorOrganismoId(String servidorOrganismoId) {
		this.servidorOrganismoId = servidorOrganismoId;
	}

	/**
	 * Obtener servidor organismo.
	 *
	 * @return servidor organismo
	 */
	public ServidoresOrganismosBean getServidorOrganismo() {
		return servidorOrganismo;
	}

	/**
	 * Modificar servidor organismo.
	 *
	 * @param servidorOrganismo new servidor organismo
	 */
	public void setServidorOrganismo(ServidoresOrganismosBean servidorOrganismo) {
		this.servidorOrganismo = servidorOrganismo;
	}

	/**
	 * Obtener lista servidores organismos.
	 *
	 * @return lista servidores organismos
	 */
	public List<ServidoresOrganismosBean> getListaServidoresOrganismos() {
		return listaServidoresOrganismos;
	}

	/**
	 * Modificar lista servidores organismos.
	 *
	 * @param listaServidoresOrganismos new lista servidores organismos
	 */
	public void setListaServidoresOrganismos(List<ServidoresOrganismosBean> listaServidoresOrganismos) {
		this.listaServidoresOrganismos = listaServidoresOrganismos;
	}

	/**
	 * Obtener combo servidores organismos.
	 *
	 * @return combo servidores organismos
	 */
	public List<KeyValueObject> getComboServidoresOrganismos() {
		return comboServidoresOrganismos;
	}

	/**
	 * Modificar combo servidores organismos.
	 *
	 * @param comboServidoresOrganismos new combo servidores organismos
	 */
	public void setComboServidoresOrganismos(List<KeyValueObject> comboServidoresOrganismos) {
		this.comboServidoresOrganismos = comboServidoresOrganismos;
	}

	/**
	 * Obtener lista planificaciones servicio.
	 *
	 * @return lista planificaciones servicio
	 */
	public List<PlanificacionBean> getListaPlanificacionesServicio() {
		return listaPlanificacionesServicio;
	}

	/**
	 * Modificar lista planificaciones servicio.
	 *
	 * @param listaPlanificacionesServicio new lista planificaciones servicio
	 */
	public void setListaPlanificacionesServicio(List<PlanificacionBean> listaPlanificacionesServicio) {
		this.listaPlanificacionesServicio = listaPlanificacionesServicio;
	}

	/**
	 * Obtener combo servidores plan.
	 *
	 * @return combo servidores plan
	 */
	public List<KeyValueObject> getComboServidoresPlan() {
		return comboServidoresPlan;
	}

	/**
	 * Modificar combo servidores plan.
	 *
	 * @param comboServidoresPlan new combo servidores plan
	 */
	public void setComboServidoresPlan(List<KeyValueObject> comboServidoresPlan) {
		this.comboServidoresPlan = comboServidoresPlan;
	}

	/**
	 * Obtener combo servicios plan.
	 *
	 * @return combo servicios plan
	 */
	public List<KeyValueObject> getComboServiciosPlan() {
		return comboServiciosPlan;
	}

	/**
	 * Modificar combo servicios plan.
	 *
	 * @param comboServiciosPlan new combo servicios plan
	 */
	public void setComboServiciosPlan(List<KeyValueObject> comboServiciosPlan) {
		this.comboServiciosPlan = comboServiciosPlan;
	}

	/**
	 * Obtener servidor.
	 *
	 * @return servidor
	 */
	public ServidorBean getServidor() {
		return servidor;
	}

	/**
	 * Modificar servidor.
	 *
	 * @param servidor new servidor
	 */
	public void setServidor(ServidorBean servidor) {
		this.servidor = servidor;
	}

	/**
	 * Obtener check del list organismos servicios.
	 *
	 * @return check del list organismos servicios
	 */
	public String[] getCheckDelListOrganismosServicios() {
		return checkDelListOrganismosServicios;
	}

	/**
	 * Modificar check del list organismos servicios.
	 *
	 * @param checkDelListOrganismosServicios new check del list organismos servicios
	 */
	public void setCheckDelListOrganismosServicios(String[] checkDelListOrganismosServicios) {
		this.checkDelListOrganismosServicios = checkDelListOrganismosServicios;
	}

	/**
	 * Obtener check del list servidor organismos.
	 *
	 * @return check del list servidor organismos
	 */
	public String[] getCheckDelListServidorOrganismos() {
		return checkDelListServidorOrganismos;
	}

	/**
	 * Modificar check del list servidor organismos.
	 *
	 * @param checkDelListServidorOrganismos new check del list servidor organismos
	 */
	public void setCheckDelListServidorOrganismos(String[] checkDelListServidorOrganismos) {
		this.checkDelListServidorOrganismos = checkDelListServidorOrganismos;
	}

	/**
	 * Obtener check del list planificaciones organismos.
	 *
	 * @return check del list planificaciones organismos
	 */
	public String[] getCheckDelListPlanificacionesOrganismos() {
		return checkDelListPlanificacionesOrganismos;
	}

	/**
	 * Modificar check del list planificaciones organismos.
	 *
	 * @param checkDelListPlanificacionesOrganismos new check del list planificaciones organismos
	 */
	public void setCheckDelListPlanificacionesOrganismos(String[] checkDelListPlanificacionesOrganismos) {
		this.checkDelListPlanificacionesOrganismos = checkDelListPlanificacionesOrganismos;
	}
	
	/**
	 * Obtener check password.
	 *
	 * @return check password
	 */
	public String getCheckPassword() {
		return checkPassword;
	}

	/**
	 * Modificar check password.
	 *
	 * @param checkPassword new check password
	 */
	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}
	
	/**
	 * Obtener id proveedor SMS.
	 *
	 * @return id proveedor SMS
	 */
	public String getIdProveedorSMS() {
		return idProveedorSMS;
	}

	/**
	 * Modificar id proveedor SMS.
	 *
	 * @param idProveedorSMS new id proveedor SMS
	 */
	public void setIdProveedorSMS(String idProveedorSMS) {
		this.idProveedorSMS = idProveedorSMS;
	}

	/**
	 * Obtener id receptor SMS.
	 *
	 * @return id receptor SMS
	 */
	public String getIdReceptorSMS() {
		return idReceptorSMS;
	}

	/**
	 * Modificar id receptor SMS.
	 *
	 * @param idReceptorSMS new id receptor SMS
	 */
	public void setIdReceptorSMS(String idReceptorSMS) {
		this.idReceptorSMS = idReceptorSMS;
	}

	/**
	 * Modificar id servidor.
	 *
	 * @param idServidor new id servidor
	 */
	public void setIdServidor(String idServidor) {
		this.idServidor = idServidor;
	}

	/**
	 * Obtener id servicio.
	 *
	 * @return id servicio
	 */
	public String getIdServicio() {
		return idServicio;
	}

	/**
	 * Modificar id servicio.
	 *
	 * @param idServicio new id servicio
	 */
	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}

	/**
	 * Obtener lista organismos.
	 *
	 * @return lista organismos
	 */
	public List<OrganismoBean> getListaOrganismos() {
		return listaOrganismos;
	}

	/**
	 * Modificar lista organismos.
	 *
	 * @param listaOrganismos new lista organismos
	 */
	public void setListaOrganismos(List<OrganismoBean> listaOrganismos) {
		this.listaOrganismos = listaOrganismos;
	}

	/**
	 * Obtener organismo.
	 *
	 * @return organismo
	 */
	public OrganismoBean getOrganismo() {
		return organismo;
	}

	/**
	 * Modificar organismo.
	 *
	 * @param organismo new organismo
	 */
	public void setOrganismo(OrganismoBean organismo) {
		this.organismo = organismo;
	}

	/**
	 * Obtener aplicacion.
	 *
	 * @return aplicacion
	 */
	public AplicacionBean getAplicacion() {
		return aplicacion;
	}

	/**
	 * Modificar aplicacion.
	 *
	 * @param aplicacion new aplicacion
	 */
	public void setAplicacion(AplicacionBean aplicacion) {
		this.aplicacion = aplicacion;
	}
	
	/**
	 * Obtener combo servidores.
	 *
	 * @return combo servidores
	 */
	public List<KeyValueObject> getComboServidores() {
		return comboServidores;
	}

	/**
	 * Obtener id planificacion.
	 *
	 * @return id planificacion
	 */
	public String getIdPlanificacion() {
		return idPlanificacion;
	}

	/**
	 * Modificar id planificacion.
	 *
	 * @param idPlanificacion new id planificacion
	 */
	public void setIdPlanificacion(String idPlanificacion) {
		this.idPlanificacion = idPlanificacion;
	}

	/**
	 * Obtener combo tipos estados.
	 *
	 * @return the comboTiposEstados
	 */
	public List<KeyValueObject> getComboTiposEstados() {
		return comboTiposEstados;
	}

	/**
	 * Modificar combo tipos estados.
	 *
	 * @param comboTiposEstados the comboTiposEstados to set
	 */
	public void setComboTiposEstados(List<KeyValueObject> comboTiposEstados) {
		this.comboTiposEstados = comboTiposEstados;
	}


	/**
	 * Obtener recovery.
	 *
	 * @return recovery
	 */
	public String getRecovery() {
		return recovery;
	}


	/**
	 * Modificar recovery.
	 *
	 * @param recovery new recovery
	 */
	public void setRecovery(String recovery) {
		this.recovery = recovery;
	}	

	/**
	 * @return the comboOrganismosHijos
	 */
	public List<KeyValueObject> getComboOrganismosHijos() {
		return comboOrganismosHijos;
	}

	/**
	 * @param comboOrganismosHijos the comboOrganismosHijos to set
	 */
	public void setComboOrganismosHijos(List<KeyValueObject> comboOrganismosHijos) {
		this.comboOrganismosHijos = comboOrganismosHijos;
	}
	
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
	/**
	 * @return the archivoExcel
	 */
	public File getArchivoExcel() {
		return archivoExcel;
	}

	/**
	 * @param archivoExcel the archivoExcel to set
	 */
	public void setArchivoExcel(File archivoExcel) {
		this.archivoExcel = archivoExcel;
	}

	public String getDatosServicios() {
		return datosServicios;
	}

	public void setDatosServicios(String datosServicios) {
		this.datosServicios = datosServicios;
	}

	public String getDatosServidor() {
		return datosServidor;
	}

	public void setDatosServidor(String datosServidor) {
		this.datosServidor = datosServidor;
	}

	/**
	 * @return the comboOrganismosPdp
	 */
	public List<KeyValueObject> getComboOrganismosPdp() {
		return comboOrganismosPdp;
	}

	/**
	 * @param comboOrganismosPdp the comboOrganismosPdp to set
	 */
	public void setComboOrganismosPdp(List<KeyValueObject> comboOrganismosPdp) {
		this.comboOrganismosPdp = comboOrganismosPdp;
	}

	/**
	 * @return the datosServiciosTabla
	 */
	public String getDatosServiciosTabla() {
		return datosServiciosTabla;
	}

	/**
	 * @param datosServiciosTabla the datosServiciosTabla to set
	 */
	public void setDatosServiciosTabla(String datosServiciosTabla) {
		this.datosServiciosTabla = datosServiciosTabla;
	}

	/**
	 * @return the formatArchivoExcel
	 */
	public String getFormatoArchivoExcel() {
		return formatoArchivoExcel;
	}

	/**
	 * @param formatoArchivoExcel the nombreArchivoExcel to set
	 */
	public void setFormatoArchivoExcel(String nombreArchivoExcel) {
		this.formatoArchivoExcel = nombreArchivoExcel;
	}

}
