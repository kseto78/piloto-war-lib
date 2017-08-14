package es.mpr.plataformamensajeria.util;

import java.util.ArrayList;
import java.util.HashMap;

public class TituloEstadisticasParser {
	private static final String ENERO_FULL = "Enero";
	private static final String FEBRERO_FULL ="Febrero";
	private static final String MARZO_FULL = "Marzo";
	private static final String ABRIL_FULL = "Abril";
	private static final String MAYO_FULL = "Mayo"; 
	private static final String JUNIO_FULL = "Junio";
	private static final String JULIO_FULL = "Julio";
	private static final String AGOSTO_FULL = "Agosto";
	private static final String SEPTIEMBRE_FULL = "Septiembre";
	private static final String OCTUBRE_FULL = "Octubre";
	private static final String NOVIEMBRE_FULL = "Noviembre";
	private static final String DICIEMBRE_FULL = "Diciembre";
	
	private static final String ENERO_SHORT = "Ene";
	private static final String FEBRERO_SHORT ="Feb";
	private static final String MARZO_SHORT = "Mar";
	private static final String ABRIL_SHORT = "Abr";
	private static final String MAYO_SHORT = "May"; 
	private static final String JUNIO_SHORT = "Jun";
	private static final String JULIO_SHORT = "Jul";
	private static final String AGOSTO_SHORT = "Ago";
	private static final String SEPTIEMBRE_SHORT = "Sep";
	private static final String OCTUBRE_SHORT = "Oct";
	private static final String NOVIEMBRE_SHORT = "Nov";
	private static final String DICIEMBRE_SHORT = "Dic";
	
	private final static HashMap<String,String> MESES_FULL = new HashMap<String,String>();
 	static
 	{
 		MESES_FULL.put("1", ENERO_FULL);
 		MESES_FULL.put("2", FEBRERO_FULL);
 		MESES_FULL.put("3", MARZO_FULL);
 		MESES_FULL.put("4", ABRIL_FULL);
 		MESES_FULL.put("5", MAYO_FULL);
 		MESES_FULL.put("6", JUNIO_FULL);
 		MESES_FULL.put("7", JULIO_FULL);
 		MESES_FULL.put("8", AGOSTO_FULL);
 		MESES_FULL.put("9", SEPTIEMBRE_FULL);
 		MESES_FULL.put("10", OCTUBRE_FULL);
 		MESES_FULL.put("11", NOVIEMBRE_FULL);
 		MESES_FULL.put("12", DICIEMBRE_FULL);
 	}	
	private final static HashMap<String,String> MESES_SHORT = new HashMap<String,String>();
 	static
 	{
 		MESES_SHORT.put("1", ENERO_SHORT);
 		MESES_SHORT.put("2", FEBRERO_SHORT);
 		MESES_SHORT.put("3", MARZO_SHORT);
 		MESES_SHORT.put("4", ABRIL_SHORT);
 		MESES_SHORT.put("5", MAYO_SHORT);
 		MESES_SHORT.put("6", JUNIO_SHORT);
 		MESES_SHORT.put("7", JULIO_SHORT);
 		MESES_SHORT.put("8", AGOSTO_SHORT);
 		MESES_SHORT.put("9", SEPTIEMBRE_SHORT);
 		MESES_SHORT.put("10", OCTUBRE_SHORT);
 		MESES_SHORT.put("11", NOVIEMBRE_SHORT);
 		MESES_SHORT.put("12", DICIEMBRE_SHORT);
 	}	
 	
 	
 	public static String parseMesAnno(String mesAnno){
 		StringBuffer sbf = new StringBuffer();
 		if(mesAnno!=null&&mesAnno.length()>0){
 			mesAnno = mesAnno.replaceAll("'", "");
 			String[] auxMesAnno = mesAnno.split("_");
 			sbf.append(MESES_SHORT.get(auxMesAnno[0])).append("-").append(auxMesAnno[1]);
 		}
 		return sbf.toString();
 	}
 	public static  String parseDiaMesAnno(String diaMesAnno){
 		StringBuffer sbf = new StringBuffer();
 		if(diaMesAnno!=null&&diaMesAnno.length()>0){
 			diaMesAnno=diaMesAnno.replaceAll("'", "");
 			String[] auxDiaMesAnno = diaMesAnno.split("_");
 			sbf.append(auxDiaMesAnno[0]).append("-").append(MESES_SHORT.get(auxDiaMesAnno[1])).append("-").append(auxDiaMesAnno[2]);
 			
 		}
 		return sbf.toString();
 	}
 	/**
 	 * 
 	 * @return
 	 */
 	public static ArrayList<String> getMesesFullName(){
 		ArrayList<String> listadoMesesFullName = new ArrayList<String>();
 		for (int i= 1; i<=12; i++) {
			listadoMesesFullName.add(String.valueOf(i)+"_"+MESES_FULL.get(String.valueOf(i)));
		}
 		
 		return listadoMesesFullName;
 	}
 	
	
	
}
