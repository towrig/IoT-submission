package org.superweatherman.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.superweatherman.models.Forecast;
import org.superweatherman.services.ForecastService;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    ForecastService forecastService;

    @GetMapping("/{location}")
    public List<Forecast> findByLocation(@PathVariable String location) {
        return forecastService.findByLocationString(location);
    }
}
