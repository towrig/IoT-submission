import { configureStore } from "@reduxjs/toolkit";
import createSagaMiddleware from "redux-saga";
import {weatherSlice} from "./weatherSlice.ts";
import rootSaga from "../sagas/rootSaga.ts";

const sagaMiddleware = createSagaMiddleware();

export const store = configureStore({
    reducer: {
        weather: weatherSlice.reducer,
    },
    middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat([
        sagaMiddleware
    ])
})

sagaMiddleware.run(rootSaga);

export type RootState = ReturnType<typeof store.getState>;