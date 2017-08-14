package es.mpr.plataformamensajeria.util;


import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MapUser
  extends User
{
  private static final long serialVersionUID = -6797275485901397834L;
  private Object userInfo;
  
  public MapUser(String username, String password, boolean isEnabled, List<GrantedAuthority> authorities, Object user)
  {
	  
    super(username, password, isEnabled, null, null, null, null, null, true, true, true, authorities);
    setUserInfo(user);
  }
  
  public MapUser(String username, String password, boolean isEnabled, List<GrantedAuthority> arrayAuths)
  {
    super(username, password, isEnabled, null, null, null, null, null, true, true, true, arrayAuths);
  }
  
  public Object getUserInfo()
  {
    return this.userInfo;
  }
  
  public void setUserInfo(Object userInfo)
  {
    this.userInfo = userInfo;
  }
}
