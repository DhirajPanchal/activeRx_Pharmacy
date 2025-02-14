package io.active.pharmacy.gateway.risi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(1)
@Component
public class RISIRequestFilter implements GlobalFilter {

    private static final Logger logger = LoggerFactory.getLogger(RISIRequestFilter.class);

    @Autowired
    FilterUtility filterUtility;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();

        if (isCorrelationIdPresent(requestHeaders)) {

            String str = "[ G A T E W A Y     ] REQUEST : " + exchange.getRequest().getMethod() + ":" + exchange.getRequest().getURI() + " RISI(P): " + filterUtility.getCorrelationId(requestHeaders);

            System.out.println(str);

        } else {

            String correlationId = generateCorrelationId();
            exchange = filterUtility.setCorrelationId(exchange, correlationId);
            String str = "[ G A T E W A Y     ] REQUEST : " + exchange.getRequest().getMethod() + ":" + exchange.getRequest().getURI() + " RISI(G): " + correlationId;

            System.out.println(str);

        }
        return chain.filter(exchange);
    }

    private boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
        if (filterUtility.getCorrelationId(requestHeaders) != null) {
            return true;
        } else {
            return false;
        }
    }

    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }

}
