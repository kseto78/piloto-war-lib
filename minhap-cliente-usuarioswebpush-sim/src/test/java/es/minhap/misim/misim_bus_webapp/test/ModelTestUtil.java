package es.minhap.misim.misim_bus_webapp.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class ModelTestUtil {

	/**
	 * Spring context
	 */
	protected static final String SPRING_CONTEXT_LOCATION = "spring-context.xml";

	/**
	 * Devuelve un array de bytes. 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static byte[] getBytesFromFile(File file) throws IOException {
		long length = file.length();
        byte[] bytes = new byte[(int)length];
        int offset = 0;
        int numRead = 0;
        InputStream is = new FileInputStream(file);
        try {
            while (offset < bytes.length
                   && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                offset += numRead;
            }
        } finally {
            is.close();
        }
        if (offset < bytes.length) {
            throw new IOException("No se puede leer el fichero "+file.getName());
        }
        return bytes;
    }
}
