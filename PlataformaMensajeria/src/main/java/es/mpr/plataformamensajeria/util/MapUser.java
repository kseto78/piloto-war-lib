package es.mpr.plataformamensajeria.util;


import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Clase MapUser.
 */
public class MapUser
  extends User
{
  
  /** Constante serialVersionUID. */
  private static final long serialVersionUID = -6797275485901397834L;
  
  /**  user info. */
  private Object userInfo;
  
  /**
   * Constructor de map user.
   *
   * @param username the username
   * @param password the password
   * @param isEnabled the is enabled
   * @param authorities the authorities
   * @param user the user
   */
  public MapUser(String username, String password, boolean isEnabled, List<GrantedAuthority> authorities, Object user) {
	  
    super(username, password, isEnabled, null, null, null, null, null, true, true, true, authorities);
    setUserInfo(user);
  }
  
  /**
   * Constructor de map user.
   *
   * @param username the username
   * @param password the password
   * @param isEnabled the is enabled
   * @param arrayAuths the array auths
   */
  public MapUser(String username, String password, boolean isEnabled, List<GrantedAuthority> arrayAuths) {
    super(username, password, isEnabled, null, null, null, null, null, true, true, true, arrayAuths);
  }
  
  /**
   * Obtener user info.
   *
   * @return user info
   */
  public Object getUserInfo() {
    return this.userInfo;
  }
  
  /**
   * Modificar user info.
   *
   * @param userInfo new user info
   */
  public void setUserInfo(Object userInfo) {
    this.userInfo = userInfo;
  }
}
