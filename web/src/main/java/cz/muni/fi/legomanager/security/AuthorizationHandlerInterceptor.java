/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.legomanager.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Štěpán Granát
 */
public class AuthorizationHandlerInterceptor extends HandlerInterceptorAdapter {
    
    final static Logger logger = LoggerFactory.getLogger(AuthorizationHandlerInterceptor.class);

    private AuthorizationService getAuthService() {
        return new AnnotationConfigApplicationContext(SpringConfig.class).getBean(AuthorizationService.class);
    }
    
    private String getToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return null;
        }
  
        return authorization.substring(7);
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            
            ApplyAuthorizeFilter authorizeFilter = handlerMethod.getMethodAnnotation(ApplyAuthorizeFilter.class);
            
            if (authorizeFilter != null) {
                String token = this.getToken(request);
                
                if (token != null) {
                    boolean shouldBeAdmin = authorizeFilter.securityLevel() == SecurityLevel.ADMIN;
                
                    AuthorizationService authService = this.getAuthService();
                    Long userId = authService.authorizeUser(token, shouldBeAdmin);

                    if (userId != null) {
                        request.setAttribute("userId", userId);
                        return true;
                    }
                }

                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false; 
            }
        } 
        
        return true;
    }
}
