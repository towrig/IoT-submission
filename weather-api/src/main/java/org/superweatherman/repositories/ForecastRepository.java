package org.superweatherman.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.superweatherman.models.Forecast;

import java.util.List;

public interface ForecastRepository extends JpaRepository<Forecast, Long> {

    @Query("SELECT f FROM Forecast f WHERE lower(f.locationName) LIKE concat(lower(:location),'%')")
    List<Forecast> findByLocation(String location);

}
