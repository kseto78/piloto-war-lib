package es.mpr.template.pool;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.pool.BasePoolableObjectFactory;

import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;


/**
 *  implementamos la factoria basica
 * solo se exige el metodo makeObject; debe de devolver un objeto.
 *
 * @author juanantonio.caro
 */

@SuppressWarnings("restriction")
public class factoryPoolParser extends BasePoolableObjectFactory {

	
	/* (non-Javadoc)
	 * @see org.apache.commons.pool.BasePoolableObjectFactory#makeObject()
	 */
	public Object makeObject() throws Exception {
	
		// la factoria retorna objetos DocumentBuilder
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactoryImpl.newInstance();   
        return dbFactory.newDocumentBuilder();
	}
}
