package org.superweatherman.services;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.superweatherman.models.Forecast;
import org.superweatherman.models.xml.XMLForecastsData;
import org.superweatherman.repositories.ForecastRepository;

import java.util.List;

import static org.superweatherman.utils.ForecastUtils.mergePlaceListsToForecasts;

@Service
@Slf4j
public class ForecastService {

    @Autowired
    private ForecastRepository db;

    @Autowired
    private XmlMapper xmlMapper;

    public List<Forecast> findByLocationString(String location) {
        return db.findByLocation(location);
    }

    /**
     * Adds all the forecasts in the xml string to repository
     * @param xmlString - xml string (has to be in ilmateenistus format)
     * @return boolean indicating if the process was successful or not
     */
    public boolean addForecastsFromXML(String xmlString) {
        try {
            //parse forecast xml to XMlForecastData object
            XMLForecastsData rawXMLForecastsData = xmlMapper.readValue(xmlString, XMLForecastsData.class);

            //for each day, add all forecasts for places on that day to db
            rawXMLForecastsData.getForecasts().forEach((xmlForecast) -> {
                List<Forecast> forecasts = mergePlaceListsToForecasts(
                        xmlForecast.getForecastDay().getPlaces(),
                        xmlForecast.getForecastNight().getPlaces(),
                        xmlForecast.getDate());
                db.saveAll(forecasts);
            });

            return true;
        } catch (Exception e) {
            log.error("Failed to add XML forecasts, reason:", e);
            return false;
        }
    }

}
