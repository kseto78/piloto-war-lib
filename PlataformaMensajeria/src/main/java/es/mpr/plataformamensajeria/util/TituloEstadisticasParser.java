package es.mpr.plataformamensajeria.util;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase TituloEstadisticasParser.
 */
public class TituloEstadisticasParser {
	
	protected static final String R_CONST_REF = "10";

	protected static final String SLASH = "_";

	protected static final String R_CONST_0 = "11";

	protected static final String R_CONST_1 = "12";

	protected static final String R_CONST_2 = "'";

	protected static final String HYPHEN = "-";

	protected static final String R_CONST_3 = "1";

	protected static final String R_CONST_4 = "2";

	protected static final String R_CONST_5 = "3";

	protected static final String R_CONST_6 = "4";

	protected static final String R_CONST_7 = "5";

	protected static final String R_CONST_8 = "6";

	protected static final String R_CONST_9 = "7";

	protected static final String R_CONST_10 = "8";

	protected static final String R_CONST_11 = "9";

	/** Constante ENERO_FULL. */
	private static final String ENERO_FULL = "Enero";
	
	/** Constante FEBRERO_FULL. */
	private static final String FEBRERO_FULL ="Febrero";
	
	/** Constante MARZO_FULL. */
	private static final String MARZO_FULL = "Marzo";
	
	/** Constante ABRIL_FULL. */
	private static final String ABRIL_FULL = "Abril";
	
	/** Constante MAYO_FULL. */
	private static final String MAYO_FULL = "Mayo"; 
	
	/** Constante JUNIO_FULL. */
	private static final String JUNIO_FULL = "Junio";
	
	/** Constante JULIO_FULL. */
	private static final String JULIO_FULL = "Julio";
	
	/** Constante AGOSTO_FULL. */
	private static final String AGOSTO_FULL = "Agosto";
	
	/** Constante SEPTIEMBRE_FULL. */
	private static final String SEPTIEMBRE_FULL = "Septiembre";
	
	/** Constante OCTUBRE_FULL. */
	private static final String OCTUBRE_FULL = "Octubre";
	
	/** Constante NOVIEMBRE_FULL. */
	private static final String NOVIEMBRE_FULL = "Noviembre";
	
	/** Constante DICIEMBRE_FULL. */
	private static final String DICIEMBRE_FULL = "Diciembre";
	
	/** Constante ENERO_SHORT. */
	private static final String ENERO_SHORT = "Ene";
	
	/** Constante FEBRERO_SHORT. */
	private static final String FEBRERO_SHORT ="Feb";
	
	/** Constante MARZO_SHORT. */
	private static final String MARZO_SHORT = "Mar";
	
	/** Constante ABRIL_SHORT. */
	private static final String ABRIL_SHORT = "Abr";
	
	/** Constante MAYO_SHORT. */
	private static final String MAYO_SHORT = "May"; 
	
	/** Constante JUNIO_SHORT. */
	private static final String JUNIO_SHORT = "Jun";
	
	/** Constante JULIO_SHORT. */
	private static final String JULIO_SHORT = "Jul";
	
	/** Constante AGOSTO_SHORT. */
	private static final String AGOSTO_SHORT = "Ago";
	
	/** Constante SEPTIEMBRE_SHORT. */
	private static final String SEPTIEMBRE_SHORT = "Sep";
	
	/** Constante OCTUBRE_SHORT. */
	private static final String OCTUBRE_SHORT = "Oct";
	
	/** Constante NOVIEMBRE_SHORT. */
	private static final String NOVIEMBRE_SHORT = "Nov";
	
	/** Constante DICIEMBRE_SHORT. */
	private static final String DICIEMBRE_SHORT = "Dic";
	
	/** Constante MESES_FULL. */
	private static final HashMap<String,String> MESES_FULL = new HashMap<>();
 	static {
 		MESES_FULL.put(R_CONST_3, ENERO_FULL);
 		MESES_FULL.put(R_CONST_4, FEBRERO_FULL);
 		MESES_FULL.put(R_CONST_5, MARZO_FULL);
 		MESES_FULL.put(R_CONST_6, ABRIL_FULL);
 		MESES_FULL.put(R_CONST_7, MAYO_FULL);
 		MESES_FULL.put(R_CONST_8, JUNIO_FULL);
 		MESES_FULL.put(R_CONST_9, JULIO_FULL);
 		MESES_FULL.put(R_CONST_10, AGOSTO_FULL);
 		MESES_FULL.put(R_CONST_11, SEPTIEMBRE_FULL);
 		MESES_FULL.put(R_CONST_REF, OCTUBRE_FULL);
 		MESES_FULL.put(R_CONST_0, NOVIEMBRE_FULL);
 		MESES_FULL.put(R_CONST_1, DICIEMBRE_FULL);
 	}	
	
	/** Constante MESES_SHORT. */
	private static final HashMap<String,String> MESES_SHORT = new HashMap<>();
 	static {
 		MESES_SHORT.put(R_CONST_3, ENERO_SHORT);
 		MESES_SHORT.put(R_CONST_4, FEBRERO_SHORT);
 		MESES_SHORT.put(R_CONST_5, MARZO_SHORT);
 		MESES_SHORT.put(R_CONST_6, ABRIL_SHORT);
 		MESES_SHORT.put(R_CONST_7, MAYO_SHORT);
 		MESES_SHORT.put(R_CONST_8, JUNIO_SHORT);
 		MESES_SHORT.put(R_CONST_9, JULIO_SHORT);
 		MESES_SHORT.put(R_CONST_10, AGOSTO_SHORT);
 		MESES_SHORT.put(R_CONST_11, SEPTIEMBRE_SHORT);
 		MESES_SHORT.put(R_CONST_REF, OCTUBRE_SHORT);
 		MESES_SHORT.put(R_CONST_0, NOVIEMBRE_SHORT);
 		MESES_SHORT.put(R_CONST_1, DICIEMBRE_SHORT);
 	}	
 	
 	
 	/**
	  * Parses the mes anno.
	  *
	  * @param mesAnno the mes anno
	  * @return the string
	  */
	 public static String parseMesAnno(String mesAnno){
 		StringBuffer sbf = new StringBuffer();
 		if(mesAnno!=null&&!mesAnno.isEmpty()){
 			mesAnno = mesAnno.replaceAll(R_CONST_2, "");
 			String[] auxMesAnno = mesAnno.split(SLASH);
 			sbf.append(MESES_SHORT.get(auxMesAnno[0])).append(HYPHEN).append(auxMesAnno[1]);
 		}
 		return sbf.toString();
 	}
 	
	 /**
	  * Parses the dia mes anno.
	  *
	  * @param diaMesAnno the dia mes anno
	  * @return the string
	  */
	 public static  String parseDiaMesAnno(String diaMesAnno){
 		StringBuffer sbf = new StringBuffer();
 		if(diaMesAnno!=null&&!diaMesAnno.isEmpty()){
 			diaMesAnno=diaMesAnno.replaceAll(R_CONST_2, "");
 			String[] auxDiaMesAnno = diaMesAnno.split(SLASH);
 			sbf.append(auxDiaMesAnno[0]).append(HYPHEN).append(MESES_SHORT.get(auxDiaMesAnno[1])).append(HYPHEN).append(auxDiaMesAnno[2]);
 			
 		}
 		return sbf.toString();
 	}
 	
	 /**
	  * Obtener meses full name.
	  *
	  * @return meses full name
	  */
 	public static ArrayList<String> getMesesFullName(){
 		ArrayList<String> listadoMesesFullName = new ArrayList<>();
 		for (int i= 1; i<=12; i++) {
			listadoMesesFullName.add(i+SLASH+MESES_FULL.get(String.valueOf(i)));
		}
 		
 		return listadoMesesFullName;
 	}
 	
	
	
}
