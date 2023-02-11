package com.delivery.gatewayapi;

import com.delivery.gatewayapi.Exception.ApiRequestException;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements GatewayFilter {
    @Autowired
    private RouterValidator routerValidator;

    private final WebClient.Builder webClientBuilder;

    public AuthenticationFilter(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    try{
        ServerHttpRequest request = exchange.getRequest();
        UserDto userDto;
        //Si la route nécessite une authentification (non exclu de la liste des routes sans vérification)
        String[] parts = new String[0];
        if (routerValidator.isSecured.test(request)) {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new ApiRequestException("Missing auth information");
            }
            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            parts = authHeader.split(" ");
            if (parts.length != 2 || !"Bearer".equals(parts[0])) {
                throw new ApiRequestException("Incorrect auth structure");
            }

            userDto = UserDto.getUserDto(parts[1]);

            if (userDto != null && userDto.getEmail() != null) {
                exchange.getResponse().getHeaders().set("emailUser", userDto.getEmail());
                exchange.getResponse().getHeaders().set("idUser", String.valueOf(userDto.getIdUser()));
                return chain.filter(exchange);
            } else {
                throw new ApiRequestException("Token error ! User may not be found or Token may be expired !");
            }

            }
            return chain.filter(exchange);
        }catch (Exception e){
            throw new ApiRequestException(e.getMessage());
        }
    }


}
