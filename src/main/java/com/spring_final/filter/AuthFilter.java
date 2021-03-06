package com.spring_final.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {

            HttpServletRequest httpRequest = (HttpServletRequest) request;

            String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

            if (path.startsWith("/pages/admin/")) {
                chain.doFilter(request, response);
                return;
            }

            HttpSession session = httpRequest.getSession(false);

            boolean isLoggedIn = (session != null && session.getAttribute("authUser") != null);

            String loginURI = httpRequest.getContextPath() + "/pages/login.jsp";
            boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
            boolean isLoginPage = httpRequest.getRequestURI().endsWith("login");

            boolean isRegPage = httpRequest.getRequestURI().endsWith("registration");
            boolean isIndexPage = httpRequest.getRequestURI().endsWith("index");



            if (isLoggedIn && (isLoginRequest || isLoginPage)) {
                // the user is already logged in and he's trying to login again
                // then forward to the homepage
                httpRequest.getRequestDispatcher("/").forward(request, response);
            } else if (!isLoggedIn && !isRegPage && !isIndexPage) {
                // the user is not logged in, and the requested page requires
                // authentication, then forward to the login page
                String loginPage = "/pages/login";
                RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginPage);
                dispatcher.forward(request, response);
            } else {
                // for other requested pages that do not require authentication
                // or the user is already logged in, continue to the destination
                System.out.println("$2a$10$F56jK24TIV40n3byOd19nOkNGKleOr/4r.HXl86EgJZ19D3IiACBK");
                chain.doFilter(request, response);
            }
        }
}
