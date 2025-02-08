package io.active.pharmacy.base.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;


//@Component
public class FirstFilter implements Filter {

    public static final String REQUEST_INTER_SERVICE_ID = "activerx_pharmacy_risi";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String str = "Filter [ S T O R E ]          " + req.getMethod() + ":" + req.getRequestURI() + " RISI: " + req.getHeader(REQUEST_INTER_SERVICE_ID) + " / " + res.getHeader(REQUEST_INTER_SERVICE_ID);
        System.out.println(str);

        ((HttpServletResponse) response).setHeader("STORE", "STORE_RESPONSE");

        chain.doFilter(request, response);
    }
}
