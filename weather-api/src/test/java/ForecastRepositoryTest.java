import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.superweatherman.Application;
import org.superweatherman.models.Forecast;
import org.superweatherman.repositories.ForecastRepository;

import java.util.List;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@TestPropertySource(properties = {"spring.jpa.hibernate.ddl-auto=create-drop"})
public class ForecastRepositoryTest {

    @Autowired
    ForecastRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();

        Forecast f1 = new Forecast(1L, "Tartu", "Must", "Kaunis", 0L, 3L);
        Forecast f2 = new Forecast(2L, "Tallinn", "Pime", "Hall", -20L, -10L);
        Forecast f3 = new Forecast(3L, "Metsatarguse", "Pime", "Tume", 0L, 10L);

        bookRepository.saveAll(List.of(f1, f2, f3));
    }

    @Test
    void databaseHasData() {
        List<Forecast> forecasts = bookRepository.findAll();
        assertThat(forecasts.size()).isEqualTo(3);
    }

    @Test
    void findByLocation_usesStartsWith() {
        List<Forecast> forecastsWithTa = bookRepository.findByLocation("Ta"); //Tartu & Tallinn
        assertThat(forecastsWithTa.size()).isEqualTo(2);

        List<Forecast> forecastsWithTar = bookRepository.findByLocation("Tar"); //Tartu
        assertThat(forecastsWithTar.size()).isEqualTo(1);
    }

    @Test
    void findByLocation_isCaseInsensitive() {
        List<Forecast> forecasts1 = bookRepository.findByLocation("Tar");
        List<Forecast> forecasts2 = bookRepository.findByLocation("tAr");
        List<Forecast> forecasts3 = bookRepository.findByLocation("tar");

        assertThat(forecasts1.size()).isEqualTo(forecasts2.size());
        assertThat(forecasts2.size()).isEqualTo(forecasts3.size());
    }
}
