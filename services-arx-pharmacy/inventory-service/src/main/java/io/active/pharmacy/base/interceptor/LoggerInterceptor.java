package io.active.pharmacy.base.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Slf4j
@Component
public class LoggerInterceptor implements HandlerInterceptor {

    public static final String CORRELATION_ID = "activeRx-pharmacy-trace-id";

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        String str = "[ I N V E N T O R Y ]  " + request.getMethod() + ":" + request.getRequestURI() + " Trace ID "+ request.getHeader(CORRELATION_ID);
        System.out.println(str);
        log.info(str);
        return true;
    }

}
