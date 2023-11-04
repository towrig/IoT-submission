export function* fetchWeatherByLocation(location: string) {
    const response: Response = yield fetch(`/weather/${location}`);
    console.log({json: response.json()});
}