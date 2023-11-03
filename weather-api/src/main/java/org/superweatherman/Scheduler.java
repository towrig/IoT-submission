package org.superweatherman;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import org.superweatherman.services.ForecastService;

@Slf4j
@Configuration
@Profile("!test")
public class Scheduler {

    @Autowired
    private ForecastService forecastService;

    @Scheduled(fixedDelay = 1000 * 60 * 30) //runs every 30 minutes after last completion
    public void scheduleForecastDataUpdate() {
        final String uri = "https://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng";
        RestTemplate restTemplate = new RestTemplate();
        String resultXML = restTemplate.getForObject(uri, String.class);
        boolean success = forecastService.addForecastsFromXML(resultXML);
        log.info(success ? "Imported XML data successfully!" : "Failed to import XML data.");
    }
}
