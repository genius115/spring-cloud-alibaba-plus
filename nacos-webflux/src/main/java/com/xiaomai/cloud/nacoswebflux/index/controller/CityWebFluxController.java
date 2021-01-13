package com.xiaomai.cloud.nacoswebflux.index.controller;

/**
 * @author developer
 * @date 2021/1/12
 */
import com.xiaomai.cloud.nacoswebflux.index.domain.City;
import com.xiaomai.cloud.nacoswebflux.index.handler.CityHandler;
import com.xiaomai.cloud.nacoswebflux.index.handler.CityMongoDBHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/city")
public class CityWebFluxController {

    @Autowired
    private CityHandler cityHandler;

    @Autowired
    private CityMongoDBHandler cityMongoDBHandler;

    @GetMapping(value = "/{id}")
    public Mono<City> findCityById(@PathVariable("id") Long id) {
        return cityHandler.findCityById(id);
    }

    @GetMapping()
    public Flux<City> findAllCity() {
        return cityHandler.findAllCity();
    }

    @PostMapping()
    public Mono<Long> saveCity(@RequestBody City city) {
        return cityHandler.save(city);
    }

    @PutMapping()
    public Mono<Long> modifyCity(@RequestBody City city) {
        return cityHandler.modifyCity(city);
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Long> deleteCity(@PathVariable("id") Long id) {
        return cityHandler.deleteCity(id);
    }
}
