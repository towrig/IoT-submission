import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {WeatherLocation} from "../api/types.ts";
import {RootState} from "./store.ts";

interface WeatherState {
    locations: WeatherLocation[],
}

export const initialState: WeatherState = {
    locations: [],
}

export const weatherSlice = createSlice({
    name: 'weather',
    initialState,
    reducers: {
        setLocations: (state, action: PayloadAction<WeatherLocation[]>) => {
            state.locations = action.payload;
        }
    }
})
export const {
    setLocations
} = weatherSlice.actions;


/* Selectors */
export const selectLocations = (state: RootState) => state.weather.locations;