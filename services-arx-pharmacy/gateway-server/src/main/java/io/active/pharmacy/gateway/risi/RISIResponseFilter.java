package io.active.pharmacy.gateway.risi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

@Configuration
public class RISIResponseFilter {

    private static final Logger logger = LoggerFactory.getLogger(RISIResponseFilter.class);

    @Autowired
    FilterUtility filterUtility;

    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
                String correlationId = filterUtility.getCorrelationId(requestHeaders);
                String str = "[ G A T E W A Y     ] RESPONSE RISI(U): " + correlationId;
                System.out.println(str);
                exchange.getResponse().getHeaders().add(filterUtility.REQUEST_INTER_SERVICE_ID, correlationId);
            }));
        };
    }
}