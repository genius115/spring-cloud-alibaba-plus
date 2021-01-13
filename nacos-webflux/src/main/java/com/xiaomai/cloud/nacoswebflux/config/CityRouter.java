package com.xiaomai.cloud.nacoswebflux.config;

import com.xiaomai.cloud.nacoswebflux.index.handler.CityMongoDBHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author developer
 * @date 2021/1/12
 */
@Configuration
public class CityRouter {

    @Bean
    public RouterFunction<ServerResponse> routeCity(CityMongoDBHandler cityMongoDBHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/listCity")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        cityMongoDBHandler::listCity)
                .andRoute(RequestPredicates.GET("/city/{id}")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        cityMongoDBHandler::getCity)
                .andRoute(RequestPredicates.GET("/deleteCity/{id}")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        cityMongoDBHandler::deleteCity)
                .andRoute(RequestPredicates.POST("/saveCity")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        cityMongoDBHandler::saveCity);
    }
}
