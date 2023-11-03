package org.superweatherman.utils;

import org.superweatherman.models.Forecast;
import org.superweatherman.models.xml.XMLForecastsData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ForecastUtils {

    /**
     * Takes in the day and night forecasts for places on a specific date and merges them to a single list of forecasts.
     * @param dayPlaces - day forecasts for all the places
     * @param nightPlaces - night forecasts for all the places
     * @param date - date of forecasts
     * @return list of forecasts for that date
     */
    public static List<Forecast> mergePlaceListsToForecasts(List<XMLForecastsData.Place> dayPlaces, List<XMLForecastsData.Place> nightPlaces, String date) {
        List<Forecast> dayForecasts = dayPlaces == null ? new ArrayList<>() : dayPlaces.stream()
                .map(place -> placeToForecast(place, date,true))
                .toList();

        List<Forecast> nightForecasts = nightPlaces == null ? new ArrayList<>() : nightPlaces.stream()
                .map(place -> placeToForecast(place, date,false))
                .toList();

        List<Forecast> allForecasts = new ArrayList<>(dayForecasts);
        allForecasts.addAll(nightForecasts);

        Map<String, Forecast> forecasts =  allForecasts.stream()
                .collect(Collectors.toMap(Forecast::getLocationName, f -> f, ForecastUtils::mergeForecasts));

        return new ArrayList<>(forecasts.values());
    }

    public static Forecast placeToForecast(XMLForecastsData.Place place, String date, boolean isDay) {
        Forecast forecast = new Forecast();

        forecast.setLocationName(place.getName());
        forecast.setDate(date);

        if (isDay) {
            forecast.setDayWeather(place.getPhenomenon());
            forecast.setTempMax(place.getTempmax());
        } else {
            forecast.setNightWeather(place.getPhenomenon());
            forecast.setTempMin(place.getTempmin());
        }

        return forecast;
    }

    private static Forecast mergeForecasts(Forecast f1, Forecast f2) {
        if (f1.getDayWeather() == null) {
            f1.setDayWeather(f2.getDayWeather());
        }
        if (f1.getNightWeather() == null) {
            f1.setNightWeather(f2.getNightWeather());
        }
        if(f1.getTempMax() == null) {
            f1.setTempMax(f2.getTempMax());
        }
        if(f1.getTempMin() == null) {
            f1.setTempMin(f2.getTempMin());
        }
        return f1;
    }
}
