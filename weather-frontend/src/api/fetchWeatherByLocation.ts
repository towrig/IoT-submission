import { put } from "redux-saga/effects";
import {ajax} from "./ajax.ts";
import {WeatherLocation} from "./types.ts";
import {setLocations} from "../store/weatherSlice.ts";

interface FetchWeatherByLocationResponse {
    data: WeatherLocation[]
}

export function* fetchWeatherByLocation(location: string) {
    const response: FetchWeatherByLocationResponse = yield ajax(`/weather/${location}`);
    yield(put(setLocations(response.data)));
}