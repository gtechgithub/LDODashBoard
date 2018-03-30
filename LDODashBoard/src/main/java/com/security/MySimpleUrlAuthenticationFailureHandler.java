package com.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MySimpleUrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override    
	public void onAuthenticationFailure(HttpServletRequest request,
													HttpServletResponse response, 
													AuthenticationException exception)
													throws IOException, ServletException {
		//super.onAuthenticationFailure(request, response, exception);
		System.out.println("ïnside onAuthenticationFailure");
		//getRedirectStrategy().sendRedirect(request, response, "fail2LoginPage");
		response.sendRedirect("/LDODashBoard/welcome?error=true");
	}
}
