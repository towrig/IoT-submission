import {fetchWeatherByLocation} from "../api/fetchWeatherByLocation.ts";

interface SearchWeatherLocationAction {
   type: "SEARCH_WEATHER_LOCATION",
   searchValue: string,
}

export function* searchWeatherLocation(action: SearchWeatherLocationAction) {
    yield fetchWeatherByLocation(action.searchValue);
}