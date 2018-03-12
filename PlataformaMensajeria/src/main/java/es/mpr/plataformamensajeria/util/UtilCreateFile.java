package es.mpr.plataformamensajeria.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Blob;

import org.apache.log4j.Logger;

public class UtilCreateFile {
	
	private static Logger logger = Logger.getLogger(UtilCreateFile.class);
	
	public static String getCuerpoMensajeFromFile(String ruta) {
		BufferedReader br = null;
		StringBuilder sb = null;
		FileReader fr = null;
		try {
			try {
				fr = new FileReader(ruta);
			} catch (FileNotFoundException e) {
				logger.error("UtilCreateFile.getCuerpoMensajeFronFile", e);
			}
			
			br = new BufferedReader(fr);
		    sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        line = br.readLine();
		    }
		
		} catch (IOException e) {
			logger.error("UtilCreateFile.getCuerpoMensajeFronFile", e);

		} finally{
			if (null != br)
				try {
					br.close();
				} catch (IOException e1) {
					logger.error("UtilCreateFile.getCuerpoMensajeFronFile", e1);
				}
			if (null != fr){
				try{
					fr.close();
				} catch (IOException e1) {
					logger.error("UtilCreateFile.getCuerpoMensajeFronFile", e1);
				}
			}
			
		}

		return null != sb ? sb.toString() : "";
	}
	
	
	public static byte[] getAdjuntoMensaje(String ruta) {
		byte[] bytesArray = null;
		Blob blob = null;
		FileInputStream fis = null;
		try {
			File file = new File(ruta);
			bytesArray = new byte[(int) file.length()];			
			fis = new FileInputStream(file);
			fis.read(bytesArray); //read file into bytes[]
			blob = new javax.sql.rowset.serial.SerialBlob(bytesArray);
		} catch (Exception e ) {
			logger.error("UtilCreateFile.getAdjuntoMensaje", e);
		}finally{
			try{
				if (null != fis){
					fis.close();
				}
			} catch (IOException e1) {
				logger.error("UtilCreateFile.getAdjuntoMensaje", e1);
			}
		}
		

		return bytesArray;
	}


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
