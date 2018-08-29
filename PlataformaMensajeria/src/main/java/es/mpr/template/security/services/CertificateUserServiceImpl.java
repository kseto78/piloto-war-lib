package es.mpr.template.security.services;

import java.security.cert.X509Certificate;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.map.j2ee.exceptions.BusinessException;
import com.map.j2ee.security.perm.UserException;
import com.map.j2ee.security.perm.manager.UserManager;
import com.map.j2ee.security.perm.model.UserVO;

import es.mpr.j2ee.security.services.ICertificateUserService;

/**
 * <p>Clase que proporciona m&eacute;todos de acceso a los datos de un usuario identificado 
 * con certificado en un repositorio LDAP de usuarios.
 * 
 * La propiedad <i>certAttribute</i> define el atributo del certificado de usuario
 * 
 * La propiedad <i>attributeName</i> define el atributo de una entrada de usuario en el repositorio LDAP
 * 
 * <ul>
 * <li>El atributo del certificado de usuarios puede proceder de una Hashtable<String,String> de atributos, 
 * o bien de el objeto <i>X509Certificate</i> que representa el certificado del usuario.</li>
 * <li>El atributo del usuario se recupera de un directorio LDAP o una base de datos, seg&uacute;n la configuraci&oacute;n escogida.  
 * </ul> 
 * 
 * Se verifica que el atributo de certificado de usuario coincide con el atributo del repositorio LDAP y se recuperan los 
 * datos del usuario en el objeto UserVO.
 * 
 * </p>
 * 
 * @author Altran
 *
 */
public class CertificateUserServiceImpl implements ICertificateUserService{

	/**  logger. */
	private static Logger logger = Logger.getLogger(CertificateUserServiceImpl.class);
	
	/**  manager. */
	private UserManager manager = null;
		
	/**  cert attribute. */
	private String certAttribute = null;
	
	/**  attribute name. */
	private String attributeName = null;

	/* (non-Javadoc)
	 * @see es.mpr.j2ee.security.services.ICertificateUserService#getUser(java.util.Hashtable)
	 */
	public UserVO getUser(Hashtable<String,String> attributes) throws UserException  {
		
		UserVO usuario = null;
		if (attributes!=null && !attributes.isEmpty())
		{
			String value = attributes.get(certAttribute);		
			logger.debug("certAttribute value: " + value);
            UserVO aCriteria = new UserVO();
			aCriteria.setAttribute(attributeName,value);
			
            try {
				usuario = (UserVO) manager.getObject(aCriteria);
			} 
            catch (BusinessException e) {
					throw new UserException(e);
				}
                     
		}
		
		return usuario;
	}

	
	/* (non-Javadoc)
	 * @see es.mpr.j2ee.security.services.ICertificateUserService#getUser(java.security.cert.X509Certificate)
	 */
	public UserVO getUser(X509Certificate cert) throws UserException  {
		
		UserVO usuario = null;
		if (cert!=null && cert.getSubjectDN()!=null)
		{
			String subjectDN = cert.getSubjectDN().getName();
			
			logger.debug("SubjectDN:  " + subjectDN);
		
            UserVO aCriteria = new UserVO();

			try {
				aCriteria.setAttribute(attributeName,subjectDN);
				usuario = (UserVO) manager.getObject(aCriteria);
                                       
			} 
			catch (BusinessException e) {
				throw new UserException(e);
			}
		}
		
		return usuario;
	}
		
	/**
	 * Obtener cert attribute.
	 *
	 * @return Atributo del certificado de usuario
	 */
	public String getCertAttribute() {
		return certAttribute;
	}
	
	/**
	 * Modificar cert attribute.
	 *
	 * @param certAttribute new cert attribute
	 */
	public void setCertAttribute(String certAttribute) {
		this.certAttribute = certAttribute;
	}
	
	/**
	 * Obtener attribute name.
	 *
	 * @return Atributo del repositorio de usuarios
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * Modificar attribute name.
	 *
	 * @param attribute new attribute name
	 */
	public void setAttributeName(String attribute) {
		this.attributeName = attribute;
	}

    /**
     * Obtener user manager.
     *
     * @return Servicio que gestiona el acceso al repositorio de usuarios
     */
	public UserManager getUserManager() {
		return manager;
	}

    /**
     * Modificar user manager.
     *
     * @param userManager Servicio que gestiona el acceso al repositorio de usuarios
     */
	public void setUserManager(UserManager userManager) {
		this.manager = userManager;
	}

}
