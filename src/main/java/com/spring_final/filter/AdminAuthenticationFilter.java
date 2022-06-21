package com.spring_final.filter;

import com.spring_final.model.Authority;
import com.spring_final.model.User;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AdminAuthenticationFilter implements Filter {

        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {

            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpSession session = httpRequest.getSession(false);

            User user = (User) session.getAttribute("authUser");

            boolean isLoggedIn = (session != null && user.getAuthorities().contains(Authority.AuthorityEnum.ADMIN));

            String loginURI = httpRequest.getContextPath() + "/login";

            //boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);

            boolean isLoginPage = httpRequest.getRequestURI().endsWith("login");

            if (isLoggedIn && /*(isLoginRequest ||*/ isLoginPage) {
                // the admin is already logged in and he's trying to login again
                // then forwards to the admin's homepage
                RequestDispatcher dispatcher = request.getRequestDispatcher("index");
                dispatcher.forward(request, response);

            } else if (isLoggedIn /*|| isLoginRequest*/) {
                // continues the filter chain
                // allows the request to reach the destination
                chain.doFilter(request, response);

            } else {
                // the admin is not logged in, so authentication is required
                // forwards to the Login page
                RequestDispatcher dispatcher = request.getRequestDispatcher("login");
                dispatcher.forward(request, response);

            }

        }
}
