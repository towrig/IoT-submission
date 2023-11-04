import {all, takeLatest} from "redux-saga/effects";
import {searchWeatherLocation} from "./searchWeatherLocation.ts";

export function* weatherSaga() {
    yield all([
        takeLatest("SEARCH_WEATHER_LOCATION", searchWeatherLocation)
    ])
}