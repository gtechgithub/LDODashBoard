package com.security;

import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.User;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import java.io.IOException;

public class UrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private static String L1USER = "l1";
	private static String L2USER = "l2";

	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication)

	throws IOException, ServletException {

		// TODO Auto-generated method stub

		System.out.println("inside UrlAuthenticationSuccessHandler");

		HttpSession session = httpServletRequest.getSession();

		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		session.setAttribute("username", authUser.getUsername());

		session.setAttribute("authorities", authentication.getAuthorities());

		// set our response to OK status

		httpServletResponse.setStatus(HttpServletResponse.SC_OK);

		// since we have created our custom success handler, its up to us to
		// where

		// we will redirect the user after successfully login

		if (authUser.getUsername().equalsIgnoreCase(L1USER)) {
			httpServletResponse.sendRedirect("showMessage");
			session.setAttribute("urlName", "showMessage");
		} else if (authUser.getUsername().equalsIgnoreCase(L2USER)) {
			httpServletResponse.sendRedirect("L2LandingPage");
			session.setAttribute("urlName", "L2LandingPage");
		}

	}

	public void onLogout(HttpSession session) {
		System.out.println("setting authentication  null");
		// session.invalidate();
		SecurityContextHolder.getContext().setAuthentication(null);
	}

}