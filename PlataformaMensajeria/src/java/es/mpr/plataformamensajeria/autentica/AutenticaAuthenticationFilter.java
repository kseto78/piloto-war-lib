package es.mpr.plataformamensajeria.autentica;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import com.map.j2ee.security.auth.MapUser;


public class AutenticaAuthenticationFilter extends GenericFilterBean {
	
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
    	

    	HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        if (logger.isDebugEnabled()) {
        	logger.debug("AutenticaAuthenticationFilter - Method: " + request.getMethod());
        }
        
        if(null!=request.getSession().getAttribute("infoUser")){
//        	if(null==SecurityContextHolder.getContext() || null==SecurityContextHolder.getContext().getAuthentication()
//            		|| null==SecurityContextHolder.getContext().getAuthentication().getPrincipal()){
    			
    			MapUser springUser = (MapUser) request.getSession().getAttribute("infoUser");
    			
    			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(springUser, null));
//    		} 
        }
        
        if(!request.getRequestURI().equals("/sim/logout")){
        	filterChain.doFilter(request, response);    
        }else{
            request.getSession().invalidate();
            response.sendRedirect(response.encodeRedirectURL("/sim/logon.jsp"));
        }
        
        return;
			
    }
   
	
}
