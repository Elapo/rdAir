package com.realdolmen.rdAir.filters;

import com.realdolmen.rdAir.controllers.LoginBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AirlineAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LoginBean login = (LoginBean) ((HttpServletRequest) request).getSession().getAttribute("loginBean");
        if (login.getUser() == null || !login.getUser().getClass().getSimpleName().equals("Airline")) {
            String contextPath = ((HttpServletRequest) request).getContextPath();
            ((HttpServletResponse) response).sendRedirect(contextPath + "/login.xhtml");
            return;
        }
        System.out.println("User " + login.getUser().getFirstName() + " is of type " + login.getUser().getClass().getSimpleName());
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
