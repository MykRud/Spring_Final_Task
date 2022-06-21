package com.spring_final.filter;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class LocalizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if(request.getParameter("lang") != null){

            request.getSession().setAttribute("lang", request.getParameter("lang"));


        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
