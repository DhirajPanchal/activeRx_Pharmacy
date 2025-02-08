package io.active.pharmacy.base.filter;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;


//@Component
public class FirstFilter implements Filter {

    public static final String REQUEST_INTER_SERVICE_ID = "activerx_pharmacy_risi";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String str = "Filter [ I N V E N T O R Y ]  " + req.getMethod() + ":" + req.getRequestURI() + " RISI: " + req.getHeader(REQUEST_INTER_SERVICE_ID) + " / " + res.getHeader(REQUEST_INTER_SERVICE_ID);
        System.out.println(str);

        ((HttpServletResponse) response).setHeader("INV", "INV");

        chain.doFilter(request, response);
    }
}
