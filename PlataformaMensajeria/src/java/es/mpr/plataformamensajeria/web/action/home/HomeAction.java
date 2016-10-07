package es.mpr.plataformamensajeria.web.action.home;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.map.j2ee.exceptions.BaseException;
import com.map.j2ee.pagination.PaginatedList;
import com.map.j2ee.util.KeyValueObject;
import com.opensymphony.xwork2.Preparable;

import es.mpr.plataformamensajeria.beans.EnviosPendientesCanalBean;
import es.mpr.plataformamensajeria.beans.EstadoLotesEnviosBean;
import es.mpr.plataformamensajeria.beans.UsoServidoresBean;
import es.mpr.plataformamensajeria.impl.PlataformaPaginationAction;
import es.mpr.plataformamensajeria.servicios.ifaces.ServicioListadosHome;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaProperties;
import es.mpr.plataformamensajeria.util.PlataformaMensajeriaUtil;
import es.mpr.plataformamensajeria.util.TituloEstadisticasParser;
 
/**
 * <p>Clase Action de Struts2 para la gesti&oacute;n de los Auditorias.
 * 
 * <p>
 * Proporciona m&eacute;todos para la creaci&oacute;n, modificaci&oacute;n, borrado y listado de los Usuarios
 * 
 * @author i-nercya
 *
 */
public class HomeAction extends PlataformaPaginationAction implements ServletRequestAware, Preparable{
	
	private static final long serialVersionUID = 1L;
	static PlataformaMensajeriaProperties props = new PlataformaMensajeriaProperties();
	private static String START_YEAR = props.getProperty("home.startyear", "2012");
	private static String END_YEAR = props.getProperty("home.endyear", "");
	private static final String REQUEST_ATTRIBUTE_TOTALSIZE = "totalSize";
	private static final String REQUEST_ATTRIBUTE_PAGESIZE = "pageSize";
	private static final Integer PAGESIZE = new Integer(10); //Elementos por pagina
	//Parametros Filtro Uso servidores
	private String filtroAnyo;
	private String filtroMes;
	//Parametros Filtro Estados Lotes
	private String filtroAnyoLotes;
	private String filtroMesLotes;

	public String getFiltroAnyoLotes() {
		return filtroAnyoLotes;
	}



	public void setFiltroAnyoLotes(String filtroAnyoLotes) {
		this.filtroAnyoLotes = filtroAnyoLotes;
	}



	public String getFiltroMesLotes() {
		return filtroMesLotes;
	}



	public void setFiltroMesLotes(String filtroMesLotes) {
		this.filtroMesLotes = filtroMesLotes;
	}


	//Combos
	List<KeyValueObject> comboAnyos = new ArrayList<KeyValueObject>();
	//List<KeyValueObject> comboMeses = new ArrayList<KeyValueObject>();
	
	//Listados
	public List<EnviosPendientesCanalBean> listaEnviosPendientesCanal;
	public List<UsoServidoresBean>   listaUso;
	public List<UsoServidoresBean>   listaUsoServidores;
	public List<UsoServidoresBean>   listaUsoProveedores;
	public List<UsoServidoresBean>   listaUsoReceptores;
	public List<UsoServidoresBean>   listaUsoServidoresPush;
	public List<EstadoLotesEnviosBean> 		   listadoEstadosLotesEnvios;
	



	public ServicioListadosHome serviciosListadoHome;

	public Integer totalesEmail = 0;
	public Integer totalesSMS = 0;
	public Integer totalesRecepcionSMS = 0;
	public Integer totalesPush = 0;
	
	
	
	public String infoHome() throws BaseException {
		SimpleDateFormat sdfInicio = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		int page = getPage("tableId");
		int inicio = (page - 1) * PAGESIZE;
		/////////////////////////////////////////////
		//	Obtenemos el listado de Mensajes Pendientes
		/////////////////////////////////////////////
		System.out.println("### INFO HOME ### INICIO EnviosPendientesCanal: " + sdfInicio.format(new Date()));
		listaEnviosPendientesCanal = serviciosListadoHome.getEnviosPendientesCanal();
		System.out.println("### INFO HOME ### FIN    EnviosPendientesCanal: " + sdfInicio.format(new Date()));
		if(listaEnviosPendientesCanal!=null){
			for (EnviosPendientesCanalBean envioPendiente: listaEnviosPendientesCanal) {
				totalesEmail+=envioPendiente.getEmail();
				totalesSMS+=envioPendiente.getSms();
				totalesRecepcionSMS+=envioPendiente.getRecepcionSMS();
				totalesPush+=envioPendiente.getPush();
			}
		}
		if(listaEnviosPendientesCanal!=null&&listaEnviosPendientesCanal.size()<4){
			int size = listaEnviosPendientesCanal.size();
			for(int i=size;i<4;i++){
				EnviosPendientesCanalBean envioPendienteVacio = new EnviosPendientesCanalBean();
				envioPendienteVacio.setAplicacion("");
				envioPendienteVacio.setEmail(0);
				envioPendienteVacio.setSms(0);
				envioPendienteVacio.setRecepcionSMS(0);
				envioPendienteVacio.setPush(0);
				listaEnviosPendientesCanal.add(envioPendienteVacio);
			}
		}
		/////////////////////////////////////////////
		//	Obtenemos el listado de Uso Servidores
		/////////////////////////////////////////////
		if(null==filtroAnyo||filtroAnyo.isEmpty()){
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy");
			String anyoActual = sdf.format(new Date());
			this.filtroAnyo =  anyoActual;
		}
		if(null==filtroMes||filtroMes.isEmpty()){
			SimpleDateFormat sdf =new SimpleDateFormat("M");
			String mesActual = sdf.format(new Date());
			this.filtroMes =  mesActual;
		}	
		/////////////////////////////////////////////
		//	Obtenemos el listado de Uso Servidores
		/////////////////////////////////////////////
		if(null==filtroAnyoLotes||filtroAnyoLotes.isEmpty()){
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy");
			String anyoActual = sdf.format(new Date());
			this.filtroAnyoLotes =  anyoActual;
		}
		if(null==filtroMesLotes||filtroMesLotes.isEmpty()){
			SimpleDateFormat sdf =new SimpleDateFormat("M");
			String mesActual = sdf.format(new Date());
			this.filtroMesLotes =  mesActual;
		}				
		System.out.println("### INFO HOME ### INICIO UsoServidores: " + sdfInicio.format(new Date()));

		listaUso = serviciosListadoHome.getUsoServidoresBean(filtroAnyo,filtroMes);
		System.out.println("### INFO HOME ### FIN    UsoServidores: " + sdfInicio.format(new Date()));
		//Caluclar listados usos servidores, proveedores, receptores y servidores push.
		listaUsoServidores = new ArrayList<UsoServidoresBean>();
		listaUsoProveedores = new ArrayList<UsoServidoresBean>();
		listaUsoReceptores = new ArrayList<UsoServidoresBean>();
		listaUsoServidoresPush = new ArrayList<UsoServidoresBean>();
		for(UsoServidoresBean uso : listaUso){
			if(uso.getTipoServidor() == 1){
				listaUsoServidores.add(uso);
			}
			if(uso.getTipoServidor() == 2){
				listaUsoProveedores.add(uso);
			}
			if(uso.getTipoServidor() == 3){
				listaUsoReceptores.add(uso);
			}
			if(uso.getTipoServidor() == 4){
				listaUsoServidoresPush.add(uso);
			}
		}
		/////////////////////////////////////////////
		//	Obtenemos el listado de Alertas
		/////////////////////////////////////////////
		System.out.println("### INFO HOME ### INICIO EstadoLotesEnvios: " + sdfInicio.format(new Date()));
		PaginatedList<EstadoLotesEnviosBean> result = serviciosListadoHome.getEstadosLotesEnvios(inicio, PAGESIZE, filtroAnyoLotes,filtroMesLotes);
		System.out.println("### INFO HOME ### FIN EstadoLotesEnvios: " + sdfInicio.format(new Date()));
    	    Integer totalSize = result.getTotalList();

    	    listadoEstadosLotesEnvios = result.getPageList();
    	    if(listadoEstadosLotesEnvios!=null&&listadoEstadosLotesEnvios.size()<10){
    	    	int i=listadoEstadosLotesEnvios.size();
    	    	for(int j=i;j<=10;j++){
    	    		EstadoLotesEnviosBean estadoBean = new EstadoLotesEnviosBean();
    	    		listadoEstadosLotesEnvios.add(estadoBean);
    	    	}
    	    }else if(listadoEstadosLotesEnvios==null){
    	    	listadoEstadosLotesEnvios = new ArrayList<EstadoLotesEnviosBean>();
    	    	for(int j=0;j<10;j++){
    	    		EstadoLotesEnviosBean estadoBean = new EstadoLotesEnviosBean();
    	    		listadoEstadosLotesEnvios.add(estadoBean);
    	    	}    	    	
    	    }

    	    getRequest().setAttribute(REQUEST_ATTRIBUTE_TOTALSIZE, totalSize);
    		boolean export = PlataformaMensajeriaUtil.isExport(getRequest());
    		getRequest().setAttribute(REQUEST_ATTRIBUTE_PAGESIZE, PAGESIZE);
    		
    		System.out.println("### INFO HOME ### FIN: " + sdfInicio.format(new Date()));
        return SUCCESS;
	}



	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<KeyValueObject> getComboAnyos() {
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        ArrayList<String> listaAnyos = PlataformaMensajeriaUtil.getYears(START_YEAR,END_YEAR);
        for(String anyo : listaAnyos){
			option = new KeyValueObject();
			option.setCodigo(anyo);
			option.setDescripcion(anyo);
			result.add(option);
        }
		return result;
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public List<KeyValueObject> getComboMeses(){
        List<KeyValueObject> result = new ArrayList<KeyValueObject>();
        KeyValueObject option = null;
        ArrayList<String> listaMeses = TituloEstadisticasParser.getMesesFullName();
        for (String string : listaMeses) {
			String[] splitMes = string.split("_");
			option = new KeyValueObject();
			option.setCodigo(splitMes[0]);
			option.setDescripcion(splitMes[1]);
			result.add(option);
		}
		
		//getMesesFullName
        return result;
	}
	
	public String getFiltroAnyo() {
		return filtroAnyo;
	}



	public void setFiltroAnyo(String filtroAnyo) {
		this.filtroAnyo = filtroAnyo;
	}



	public String getFiltroMes() {
		return filtroMes;
	}



	public void setFiltroMes(String filtroMes) {
		this.filtroMes = filtroMes;
	}



	public List<EnviosPendientesCanalBean> getListaEnviosPendientesCanal() {
		return listaEnviosPendientesCanal;
	}



	public void setListaEnviosPendientesCanal(
			List<EnviosPendientesCanalBean> listaEnviosPendientesCanal) {
		this.listaEnviosPendientesCanal = listaEnviosPendientesCanal;
	}



	public ServicioListadosHome getServiciosListadoHome() {
		return serviciosListadoHome;
	}



	public void setServiciosListadoHome(ServicioListadosHome serviciosListadoHome) {
		this.serviciosListadoHome = serviciosListadoHome;
	}



	public String getTotalesEmail() {
		DecimalFormat formateador = new DecimalFormat("###,###.##"); 
		return formateador.format(totalesEmail);
	}



	public void setTotalesEmail(Integer totalesEmail) {
		this.totalesEmail = totalesEmail;
	}



	public String getTotalesSMS() {
		DecimalFormat formateador = new DecimalFormat("###,###.##"); 
		return formateador.format(totalesSMS);
		
	}



	public void setTotalesSMS(Integer totalesSMS) {
		this.totalesSMS = totalesSMS;
	}

	public List<EstadoLotesEnviosBean> getListadoEstadosLotesEnvios() {
		return listadoEstadosLotesEnvios;
	}
	
	
	public String getTotalesRecepcionSMS() {
		DecimalFormat formateador = new DecimalFormat("###,###.##"); 
		return formateador.format(totalesRecepcionSMS);
	}



	public void setTotalesRecepcionSMS(Integer totalesRecepcionSMS) {
		this.totalesRecepcionSMS = totalesRecepcionSMS;
	}



	public void setListadoEstadosLotesEnvios(
			List<EstadoLotesEnviosBean> listadoEstadosLotesEnvios) {
		this.listadoEstadosLotesEnvios = listadoEstadosLotesEnvios;
	}


	@Override
	public void prepare() throws Exception {
//			comboAplicacionesNoAsignadas=getComboAplicacionesNoAsignadas(idUsuario);
//			listaUsuarioAplicaciones = servicioUsuarioAplicacion.getUsuarioAplicacionesByUsuarioId(new Integer(idUsuario));
	}
}
