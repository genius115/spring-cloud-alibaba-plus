package com.xiaomai.cloud.nacoswebflux.index.handler;

import com.xiaomai.cloud.nacoswebflux.index.dao.CityMongoDBRepository;
import com.xiaomai.cloud.nacoswebflux.index.domain.City;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * @author developer
 * @date 2021/1/12
 */
@Component
public class CityMongoDBHandler {
    private final CityMongoDBRepository repository;

    public CityMongoDBHandler(CityMongoDBRepository repository) {
        this.repository = repository;
    }

    //http://localhost:8888/saveCity
    public Mono<ServerResponse> saveCity(ServerRequest request) {
        Mono<City> city = request.bodyToMono(City.class);
        return ServerResponse.ok().build(repository.insert(city).then());
    }

    //http://localhost:8888/deleteCity/1
    public Mono<ServerResponse> deleteCity(ServerRequest request) {
        Long cityId = Long.valueOf(request.pathVariable("id"));
        return ServerResponse.ok().build(repository.deleteById(cityId).then());
    }

    //http://localhost:8888/city/1
    public Mono<ServerResponse> getCity(ServerRequest request) {
        Long cityId = Long.valueOf(request.pathVariable("id"));
        Mono<City> City = repository.findById(cityId);
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(City, City.class);
    }

    //http://localhost:8888/listCity
    public Mono<ServerResponse> listCity(ServerRequest request) {
        Flux<City> cityList = repository.findAll();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(cityList, City.class);
    }
}
