package io.active.pharmacy.base.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class LoggerInterceptor implements HandlerInterceptor {
    public static final String REQUEST_INTER_SERVICE_ID = "activerx_pharmacy_risi";

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {

        String str = "[ S T O R E ]          " + request.getMethod() + ":" + request.getRequestURI() + " RISI: " + request.getHeader(REQUEST_INTER_SERVICE_ID);

        System.out.println(str);

        return true;
    }

}
