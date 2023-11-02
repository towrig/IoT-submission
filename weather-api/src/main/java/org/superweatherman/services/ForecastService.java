package org.superweatherman.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.superweatherman.models.Forecast;
import org.superweatherman.repositories.ForecastRepository;

import java.util.List;

@Service
public class ForecastService {

    @Autowired
    private ForecastRepository db;

    public List<Forecast> findByLocationString(String location) {
        return db.findByLocation(location);
    }

}
