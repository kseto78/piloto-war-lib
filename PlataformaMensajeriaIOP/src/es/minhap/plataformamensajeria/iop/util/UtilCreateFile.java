package es.minhap.plataformamensajeria.iop.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.PosixFilePermission;
import java.sql.Blob;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import es.minhap.common.properties.PropertiesServices;

/**
 * 
 * @author everis
 * 
 */
@Service("UtilCreateFile")
public class UtilCreateFile {

	private static final Logger LOG = LoggerFactory.getLogger(UtilCreateFile.class);

	@Resource(name = "reloadableResourceBundleMessageSource")
	private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

	private static final  String SEPARATOR = "/";

	public String createFileMensaje(Long idMensaje, String cuerpo, Long idServicio, Integer conservacion, Date fechaCreacion) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String pathBase = ps.getMessage("filesystem.pathBase", null);
		String prefijo =  ps.getMessage("filesystem.prefijoMensaje", null);
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaCreacion);
		Path path = null;
		try {
			String sistem = System.getProperty("os.name");
			Set<PosixFilePermission> perms = createPermisos();
						
			path = Paths.get(pathBase + SEPARATOR + conservacion + SEPARATOR + idServicio+SEPARATOR + cal.get(Calendar.YEAR) 
					+SEPARATOR +(cal.get(Calendar.MONTH)+1) +SEPARATOR + (cal.get(Calendar.DAY_OF_MONTH)) + SEPARATOR + prefijo + idMensaje);
		
			Path base = Paths.get(pathBase);
			
			//crea la ruta total con todos los directorios y le pone permisos a todo
			Files.createDirectories(path.getParent());
			if (!sistem.toLowerCase().contains("windows")){
				Files.setPosixFilePermissions(path.getParent(), perms);
			}
			
			//actualizamos todos los demas directorios
			Path aux = path.getParent();
			while (!Files.isSameFile(base, aux)){
				if (!sistem.toLowerCase().contains("windows")){
					Files.setPosixFilePermissions(aux, perms);
				}
				aux = aux.getParent();
			}
			
			
			Files.write(path, Arrays.asList(cuerpo), Charset.forName("UTF-8"), StandardOpenOption.CREATE);
			if (!sistem.toLowerCase().contains("windows")){
				Files.setPosixFilePermissions(path, perms);	
			}
		} catch (IOException e) {
			LOG.error("UtilCreateFile.createFileMensaje: IOException", e);
		}catch (Exception e) {
			LOG.error("UtilCreateFile.createFileMensaje: Error general", e);
		}

		return null != path ? path.normalize().toString() : null;
	}

	
	public String createFileAdjunto(Long adjuntoid, byte[] contenido, Long idServicio, Integer conservacion,
			Date fechaCreacion) {
		PropertiesServices ps = new PropertiesServices(reloadableResourceBundleMessageSource);
		String pathBase = ps.getMessage("filesystem.pathBase", null);
		String prefijo =  ps.getMessage("filesystem.prefijoAdjunto", null);
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaCreacion);
		Path path = null;
		
		try {
			String sistem = System.getProperty("os.name");
			Set<PosixFilePermission> perms = createPermisos();
			
			path = Paths.get(pathBase + SEPARATOR + conservacion + SEPARATOR + idServicio+SEPARATOR + cal.get(Calendar.YEAR) 
					+SEPARATOR +(cal.get(Calendar.MONTH)+1) +SEPARATOR + cal.get(Calendar.DAY_OF_MONTH) + SEPARATOR + prefijo + adjuntoid);
			
			Path base = Paths.get(pathBase);
			
			//crea la ruta total con todos los directorios y le pone permisos a todo
			Files.createDirectories(path.getParent());
			if (!sistem.toLowerCase().contains("windows")){
				Files.setPosixFilePermissions(path.getParent(), perms);
			}
			
			//actualizamos todos los demás directorios
			Path aux = path.getParent();
			while (!Files.isSameFile(base, aux)){
				if (!sistem.toLowerCase().contains("windows")){
					Files.setPosixFilePermissions(aux, perms);
				}
				aux = aux.getParent();
			}
			Files.write(path, contenido);
			if (!sistem.toLowerCase().contains("windows")){
				Files.setPosixFilePermissions(path, perms);
			}
						
		} catch (IOException e) {
			LOG.error("UtilCreateFile.createFileMensaje: IOException", e);
		}catch (Exception e) {
			LOG.error("UtilCreateFile.createFileMensaje: Error general", e);
		}
		
		return null != path ? path.normalize().toString() : null;
	}

	
	public String getCuerpoMensajeFromFile(String ruta) {
		Path path = null;
		StringBuilder sb = null;
		try {
			path = Paths.get(ruta);
			
			List<String> listaLineas = Files.readAllLines(path, Charset.forName("UTF-8"));
			sb = new StringBuilder();
			for (String string : listaLineas) {
				sb.append(string);
			}
			
						
		} catch (IOException e) {
			LOG.error("UtilCreateFile.getCuerpoMensajeFromFile: IOException", e);
		}catch (Exception e) {
			LOG.error("UtilCreateFile.getCuerpoMensajeFromFile: Error general", e);
		}

		return null != sb ? sb.toString() : "";
	}
	
	
	public Blob getAdjuntoMensaje(String ruta) {
		Path path = null;
		Blob blob = null;
		try {
			path = Paths.get(ruta);
			
			blob = new javax.sql.rowset.serial.SerialBlob(Files.readAllBytes(path));
						
						
		} catch (IOException e) {
			LOG.error("UtilCreateFile.getCuerpoMensajeFromFile: IOException", e);
		}catch (Exception e) {
			LOG.error("UtilCreateFile.getCuerpoMensajeFromFile: Error general", e);
		}

		return blob;
	}
	
	private Set<PosixFilePermission> createPermisos() {
		Set<PosixFilePermission> res = new HashSet<>();
		
		//add owners permission
		res.add(PosixFilePermission.OWNER_READ);
		res.add(PosixFilePermission.OWNER_WRITE);
		res.add(PosixFilePermission.OWNER_EXECUTE);
		//add group permissions
		res.add(PosixFilePermission.GROUP_READ);
		res.add(PosixFilePermission.GROUP_WRITE);
		res.add(PosixFilePermission.GROUP_EXECUTE);
		//add others permissions
		res.add(PosixFilePermission.OTHERS_READ);
//		res.add(PosixFilePermission.OTHERS_WRITE);
//		res.add(PosixFilePermission.OTHERS_EXECUTE);
		
		return res;
	}

	
}
