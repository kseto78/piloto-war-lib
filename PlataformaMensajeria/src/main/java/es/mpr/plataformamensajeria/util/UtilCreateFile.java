package es.mpr.plataformamensajeria.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.apache.log4j.Logger;

/**
 * Clase UtilCreateFile.
 */
public class UtilCreateFile {
	
	protected static final String UTILCREATEFILEDOT = "UtilCreateFile.getAdjuntoMensaje";
	protected static final String UTILCREATEFILEDOT0 = "UtilCreateFile.getCuerpoMensajeFronFile";
	/**  logger. */
	private static Logger logger = Logger.getLogger(UtilCreateFile.class);
	
	/**
	 * Obtener cuerpo mensaje from file.
	 *
	 * @param ruta the ruta
	 * @return cuerpo mensaje from file
	 */
	public static String getCuerpoMensajeFromFile(String ruta) {
		BufferedReader br = null;
		StringBuilder sb = null;
		FileReader fr = null;
		try {
			try {
				fr = new FileReader(ruta);
			} catch (FileNotFoundException e) {
				logger.error(UTILCREATEFILEDOT0, e);
			}
			
			br = new BufferedReader(fr);
		    sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        line = br.readLine();
		    }
		
		} catch (IOException e) {
			logger.error(UTILCREATEFILEDOT0, e);

		} finally{
			if (null != br) {
				try {
					br.close();
				} catch (IOException e1) {
					logger.error(UTILCREATEFILEDOT0, e1);
				}
			}
			if (null != fr){
				try{
					fr.close();
				} catch (IOException e1) {
					logger.error(UTILCREATEFILEDOT0, e1);
				}
			}
			
		}

		return null != sb ? sb.toString() : "";
	}
	
	
	/**
	 * Obtener adjunto mensaje.
	 *
	 * @param ruta the ruta
	 * @return adjunto mensaje
	 */
	public static byte[] getAdjuntoMensaje(String ruta) {
		byte[] bytesArray = null;
		FileInputStream fis = null;
		try {
			File file = new File(ruta);
			bytesArray = new byte[(int) file.length()];			
			fis = new FileInputStream(file);
			fis.read(bytesArray); 
			//read file into bytes[]
		} catch (Exception e ) {
			logger.error(UTILCREATEFILEDOT, e);
		}finally{
			try{
				if (null != fis){
					fis.close();
				}
			} catch (IOException e1) {
				logger.error(UTILCREATEFILEDOT, e1);
			}
		}
		

		return bytesArray;
	}


	/**
	 * Eliminar adjunto.
	 *
	 * @param ruta the ruta
	 */
	public static void eliminarAdjunto(String ruta) {
		
		try {
			logger.info("UtilCreateFile -ELIMINAMOS FICHERO------->" + ruta);
			File file = new File(ruta);
			if (file.delete()){
				logger.info("SE HA ELIMINADO EL FICHERO------->" + ruta);
			}else{
				logger.info("NO SE HA ELIMINADO EL FICHERO------->" + ruta);
			}
			
			deleteDirectory(file.getParent());
			
		} catch (Exception e) {
			logger.error("UtilCreateFile.eliminarAdjunto", e);
			logger.info("NO SE HA ELIMINADO EL FICHERO hay Excepcion------->" + ruta, e);
		}
		
	}


	/**
	 * Delete directory.
	 *
	 * @param ruta the ruta
	 */
	private static void deleteDirectory(String ruta) {
		File file = new File(ruta);
		if (file.isDirectory() && file.list().length == 0) {
			if (file.delete()){
				logger.info("SE HA ELIMINADO EL DIRECTORIO------->" + ruta);
				deleteDirectory(file.getParent());
			}else{
				logger.info("NO SE HA ELIMINADO EL DIRECTORIO------->" + ruta);
			}
			
		}

	}
	
}
