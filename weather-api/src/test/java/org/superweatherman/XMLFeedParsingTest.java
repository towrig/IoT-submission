package org.superweatherman;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.superweatherman.models.Forecast;
import org.superweatherman.models.xml.XMLForecastsData;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.superweatherman.utils.ForecastUtils.mergePlaceListsToForecasts;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class XMLFeedParsingTest {

    @Autowired
    private XmlMapper xmlMapper;

    String testXML = """
            <forecasts>
                <forecast date="2023-11-03">
                    <night>
                        <phenomenon>Light shower</phenomenon>
                        <tempmin>0</tempmin>
                        <tempmax>6</tempmax>
                        <text>Cloudy with clear spells. Rain in many places, after midnight rain is gradually dying out and it is clearing up. East, southeast wind 2-7, on coast up to 10, in gusts 15, in the morning 17 m/s. Air temperature 0..+6°C.</text>
                        <place>
                            <name>Harku</name>
                            <phenomenon>Light shower</phenomenon>
                            <tempmin>3</tempmin>
                        </place>
                        <place>
                            <name>Jõhvi</name>
                            <phenomenon>Light shower</phenomenon>
                            <tempmin>0</tempmin>
                        </place>
                    </night>
                    <day>
                        <phenomenon>Variable clouds</phenomenon>
                        <tempmin>5</tempmin>
                        <tempmax>10</tempmax>
                        <text>Few or partly cloudy and mainly dry. In the afternoon on islands cloudiness is thickening and it is starting to rain. East, southeast wind 5-9, in gusts 13, on islands and coast 8-14, in gusts 18 m/s, towards evening some increasing. Air temperature 5..10°C.</text>
                        <place>
                            <name>Harku</name>
                            <phenomenon>Few clouds</phenomenon>
                            <tempmax>8</tempmax>
                        </place>
                        <place>
                            <name>Jõhvi</name>
                            <phenomenon>Few clouds</phenomenon>
                            <tempmax>6</tempmax>
                        </place>
                    </day>
                </forecast>
            </forecasts>
            """;

    @Test
    void forecastDataIsReadCorrectly() {
        assertDoesNotThrow(() -> xmlMapper.readValue(testXML, XMLForecastsData.class));
    }

    @Test
    void forecastDataIsMappedCorrectly() throws JsonProcessingException {
        XMLForecastsData forecastsData = xmlMapper.readValue(testXML, XMLForecastsData.class);

        assertThat(forecastsData.getForecasts().size()).isEqualTo(1);
        assertThat(forecastsData.getForecasts().get(0).getForecastDay().getTempmax()).isEqualTo(10L);
        assertThat(forecastsData.getForecasts().get(0).getForecastDay().getTempmin()).isEqualTo(5L);
        assertThat(forecastsData.getForecasts().get(0).getForecastDay().getPlaces().size()).isEqualTo(2);
    }

    @Test
    void forecastDataIsConvertedCorrectly() throws JsonProcessingException {
        XMLForecastsData forecastsData = xmlMapper.readValue(testXML, XMLForecastsData.class);

        XMLForecastsData.RawForecast rawForecast = forecastsData.getForecasts().get(0);
        List<Forecast> forecasts = mergePlaceListsToForecasts(
                rawForecast.getForecastDay().getPlaces(),
                rawForecast.getForecastNight().getPlaces(),
                rawForecast.getDate());

        assertThat(forecasts.size()).isEqualTo(2);
    }
}
