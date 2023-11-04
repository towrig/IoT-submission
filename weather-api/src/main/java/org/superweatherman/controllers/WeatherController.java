package org.superweatherman.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.superweatherman.models.ResultDTO;
import org.superweatherman.services.ForecastService;

@RestController
public class WeatherController {

    @Autowired
    ForecastService forecastService;

    @GetMapping(path ="/weather/{location}", produces="application/json" )
    public @ResponseBody ResultDTO findByLocation(@PathVariable String location) {
        return new ResultDTO(forecastService.findByLocationString(location));
    }
}
