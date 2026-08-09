package api_gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        long startTime= System.currentTimeMillis();

        String method=exchange.getRequest()
                .getMethod()
                .name();

        String path= exchange.getRequest()
                .getURI()
                .getPath();

        log.info("Incoming request: {} {}", method, path);

        return chain.filter(exchange)
                .doFinally(signalType -> {
                    long duration=System.currentTimeMillis() - startTime;

                    int statusCode= exchange.getResponse()
                            .getStatusCode()!=null
                            ? exchange.getResponse()
                            .getStatusCode()
                            .value()
                            :0;

                    log.info(
                      "Completed request: {} {} | Status: {} | Time : {}ms",
                      method,
                      path,
                      statusCode,
                      duration
                    );
                });
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
