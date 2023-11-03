package org.superweatherman.models.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class XMLForecastsData {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "forecast")
    private List<RawForecast> forecasts;

    @NoArgsConstructor
    @Data
    public static class RawForecast {
        @JacksonXmlProperty(isAttribute = true)
        private String date;

        @JacksonXmlProperty(localName = "night")
        private ForecastNight forecastNight;

        @JacksonXmlProperty(localName = "day")
        private ForecastDay forecastDay;

    }

    @Data
    public static class ForecastDay {
        private String phenomenon;

        private Long tempmin;

        private Long tempmax;

        private String text;

        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "place")
        private List<Place> places;
    }

    @Data
    public static class ForecastNight {
        private String phenomenon;

        private Long tempmin;

        private Long tempmax;

        private String text;

        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "place")
        private List<Place> places;
    }

    @Data
    public static class Place {
        private String name;

        private String phenomenon;

        private Long tempmin;

        private Long tempmax;
    }
}

