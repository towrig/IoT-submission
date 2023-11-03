package org.superweatherman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.superweatherman.models.Forecast;
import org.superweatherman.repositories.ForecastRepository;

import java.util.List;

@SpringBootTest
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop"})
@ActiveProfiles("test")
public class ForecastRepositoryTest {

    @Autowired
    ForecastRepository forecastRepository;

    @BeforeEach
    void setUp() {
        forecastRepository.deleteAll();

        Forecast f1 = new Forecast(1L, "2023-11-03", "Tartu", "Must", "Kaunis", 0L, 3L);
        Forecast f2 = new Forecast(2L,"2023-11-03", "Tallinn", "Pime", "Hall", -20L, -10L);
        Forecast f3 = new Forecast(3L,"2023-11-03", "Metsatarguse", "Pime", "Tume", 0L, 10L);

        forecastRepository.saveAll(List.of(f1, f2, f3));
    }

    @Test
    void databaseHasData() {
        List<Forecast> forecasts = forecastRepository.findAll();
        assertThat(forecasts.size()).isEqualTo(3);
    }

    @Test
    void findByLocation_usesStartsWith() {
        List<Forecast> forecastsWithTa = forecastRepository.findByLocation("Ta"); //Tartu & Tallinn
        assertThat(forecastsWithTa.size()).isEqualTo(2);

        List<Forecast> forecastsWithTar = forecastRepository.findByLocation("Tar"); //Tartu
        assertThat(forecastsWithTar.size()).isEqualTo(1);
    }

    @Test
    void findByLocation_isCaseInsensitive() {
        List<Forecast> forecasts1 = forecastRepository.findByLocation("Tar");
        List<Forecast> forecasts2 = forecastRepository.findByLocation("tAr");
        List<Forecast> forecasts3 = forecastRepository.findByLocation("tar");

        assertThat(forecasts1.size()).isEqualTo(forecasts2.size());
        assertThat(forecasts2.size()).isEqualTo(forecasts3.size());
    }
}
