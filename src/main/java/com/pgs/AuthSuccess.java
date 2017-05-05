package com.pgs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthSuccess extends SimpleUrlAuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.setStatus(HttpServletResponse.SC_OK);
	}

	/*private AuthenticationSuccessHandler defaultHandler;

	public AuthSuccess(){};
	
    public AuthSuccess(AuthenticationSuccessHandler defaultHandler) {
        this.defaultHandler = defaultHandler;
    }

   @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

    	
    	   if ("true".equals(request.getHeader("X-Login-Ajax-call"))) {
            response.getWriter().print("ok");
            response.getWriter().flush();
        }
        else {
            defaultHandler.onAuthenticationSuccess(request, response, authentication);
        }

    }*/
	
}
