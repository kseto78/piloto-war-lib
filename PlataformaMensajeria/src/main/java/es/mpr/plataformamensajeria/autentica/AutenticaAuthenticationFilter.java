package es.mpr.plataformamensajeria.autentica;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import es.mpr.plataformamensajeria.util.MapUser;

///MIGRADO
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
    			
				 SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(springUser, null, springUser.getAuthorities(),
						 springUser.getNombre(), springUser.getApellido1(), springUser.getApellido2(), springUser.getEmail(), springUser.getNif()));
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
