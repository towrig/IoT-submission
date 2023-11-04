import { all } from 'redux-saga/effects';
import {weatherSaga} from "./weatherSaga.ts";

export default function* rootSaga() {
    yield all([
        weatherSaga(),
    ]);
}