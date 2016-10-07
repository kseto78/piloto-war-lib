package es.mpr.plataformamensajeria.autentica;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.ui.AuthenticationEntryPoint;
import org.springframework.security.util.PortMapper;
import org.springframework.security.util.PortMapperImpl;
import org.springframework.security.util.PortResolver;
import org.springframework.security.util.PortResolverImpl;
import org.springframework.security.util.RedirectUrlBuilder;
import org.springframework.util.Assert;

public class AutenticaAuthenticationEntryPoint implements AuthenticationEntryPoint, InitializingBean {
    
	 //~ Static fields/initializers =====================================================================================

    private static final Log logger = LogFactory.getLog(AutenticaAuthenticationEntryPoint.class);

    //~ Instance fields ================================================================================================

    private PortMapper portMapper = new PortMapperImpl();

    private PortResolver portResolver = new PortResolverImpl();

    private String loginFormUrl;

    private boolean forceHttps = false;

    private boolean serverSideRedirect = false;

    //~ Methods ========================================================================================================

    public void afterPropertiesSet() throws Exception {
        Assert.hasLength(loginFormUrl, "loginFormUrl must be specified");
        Assert.notNull(portMapper, "portMapper must be specified");
        Assert.notNull(portResolver, "portResolver must be specified");
    }

    /**
     * Allows subclasses to modify the login form URL that should be applicable for a given request.
     *
     * @param request   the request
     * @param response  the response
     * @param exception the exception
     * @return the URL (cannot be null or empty; defaults to {@link #getLoginFormUrl()})
     */
    protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response,
    		org.springframework.security.AuthenticationException exception) {

        return getLoginFormUrl();
    }

    /**
     * Performs the redirect (or forward) to the login form URL.
     */
    public void commence(ServletRequest request, ServletResponse response, org.springframework.security.AuthenticationException authException)
			throws IOException, ServletException {
    	
    	HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String redirectUrl = null;

        if (serverSideRedirect) {

            if (forceHttps && "http".equals(request.getScheme())) {
                redirectUrl = buildHttpsRedirectUrlForRequest(httpRequest);
            }

            if (redirectUrl == null) {
                String loginForm = determineUrlToUseForThisRequest(httpRequest, httpResponse, authException);
                
                if (logger.isDebugEnabled()) {
                    logger.debug("Server side forward to: " + loginForm);
                }

                RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginForm);

                dispatcher.forward(request, response);

                return;
            }
        } else {
            // redirect to login page. Use https if forceHttps true

            redirectUrl = buildRedirectUrlToLoginPage(httpRequest, httpResponse, authException);

        }

        httpResponse.sendRedirect(httpResponse.encodeRedirectURL(redirectUrl));
    	
    }

    protected String buildRedirectUrlToLoginPage(HttpServletRequest request, HttpServletResponse response,
    		org.springframework.security.AuthenticationException authException) {

        String loginForm = determineUrlToUseForThisRequest(request, response, authException);
        int serverPort = portResolver.getServerPort(request);
        String scheme = request.getScheme();

        RedirectUrlBuilder urlBuilder = new RedirectUrlBuilder();

        urlBuilder.setScheme(scheme);
        urlBuilder.setServerName(request.getServerName());
        urlBuilder.setPort(serverPort);
        urlBuilder.setContextPath(request.getContextPath());
        urlBuilder.setPathInfo(loginForm);

        if (forceHttps && "http".equals(scheme)) {
            Integer httpsPort = portMapper.lookupHttpsPort(new Integer(serverPort));

            if (httpsPort != null) {
                // Overwrite scheme and port in the redirect URL
                urlBuilder.setScheme("https");
                urlBuilder.setPort(httpsPort.intValue());
            } else {
                logger.warn("Unable to redirect to HTTPS as no port mapping found for HTTP port " + serverPort);
            }
        }

        return urlBuilder.getUrl();
    }

    /**
     * Builds a URL to redirect the supplied request to HTTPS.
     */
    protected String buildHttpsRedirectUrlForRequest(HttpServletRequest request)
            throws IOException, ServletException {

        int serverPort = portResolver.getServerPort(request);
        Integer httpsPort = portMapper.lookupHttpsPort(new Integer(serverPort));

        if (httpsPort != null) {
            RedirectUrlBuilder urlBuilder = new RedirectUrlBuilder();
            urlBuilder.setScheme("https");
            urlBuilder.setServerName(request.getServerName());
            urlBuilder.setPort(httpsPort.intValue());
            urlBuilder.setContextPath(request.getContextPath());
            urlBuilder.setServletPath(request.getServletPath());
            urlBuilder.setPathInfo(request.getPathInfo());
            urlBuilder.setQuery(request.getQueryString());

            return urlBuilder.getUrl();
        }

        // Fall through to server-side forward with warning message
        logger.warn("Unable to redirect to HTTPS as no port mapping found for HTTP port " + serverPort);

        return null;
    }

    /**
     * Set to true to force login form access to be via https. If this value is true (the default is false),
     * and the incoming request for the protected resource which triggered the interceptor was not already
     * <code>https</code>, then the client will first be redirected to an https URL, even if <tt>serverSideRedirect</tt>
     * is set to <tt>true</tt>.
     */
    public void setForceHttps(boolean forceHttps) {
        this.forceHttps = forceHttps;
    }

    protected boolean isForceHttps() {
        return forceHttps;
    }

    /**
     * The URL where the <code>AuthenticationProcessingFilter</code> login
     * page can be found. Should be relative to the web-app context path, and
     * include a leading <code>/</code>
     */
    public void setLoginFormUrl(String loginFormUrl) {
        this.loginFormUrl = loginFormUrl;
    }

    public String getLoginFormUrl() {
        return loginFormUrl;
    }

    public void setPortMapper(PortMapper portMapper) {
        this.portMapper = portMapper;
    }

    protected PortMapper getPortMapper() {
        return portMapper;
    }

    public void setPortResolver(PortResolver portResolver) {
        this.portResolver = portResolver;
    }

    protected PortResolver getPortResolver() {
        return portResolver;
    }

    /**
     * Tells if we are to do a server side include of the <code>loginFormUrl</code> instead of a 302 redirect.
     *
     * @param serverSideRedirect
     */
    public void setServerSideRedirect(boolean serverSideRedirect) {
        this.serverSideRedirect = serverSideRedirect;
	}

    protected boolean isServerSideRedirect() {
        return serverSideRedirect;
    }
    
}
