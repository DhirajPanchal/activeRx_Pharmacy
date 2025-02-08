package io.active.pharmacy.gateway.risi;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import org.springframework.http.HttpHeaders;
import java.util.List;

@Component
public class FilterUtility {

    public static final String REQUEST_INTER_SERVICE_ID = "activerx_pharmacy_risi";

    public String getCorrelationId(HttpHeaders requestHeaders) {
        if (requestHeaders.get(REQUEST_INTER_SERVICE_ID) != null) {
            List<String> requestHeaderList = requestHeaders.get(REQUEST_INTER_SERVICE_ID);
            return requestHeaderList.stream().findFirst().get();
        } else {
            return null;
        }
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
        return exchange.mutate().request(exchange.getRequest().mutate().header(name, value).build()).build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return this.setRequestHeader(exchange, REQUEST_INTER_SERVICE_ID, correlationId);
    }

}
