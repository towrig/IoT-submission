import {PureComponent} from 'react';
import {WeatherLocation} from "../../api/types.ts";
import "./styles.scss";
import { format } from 'date-fns';

interface Props {
    location?: WeatherLocation;
}

class WeatherCard extends PureComponent<Props> {
    render() {
        const {location} = this.props;
        if (!location) return null;

        const { date, locationName, dayWeather, nightWeather, tempMax, tempMin } = location;
        const formattedDate = format(new Date(date), "dd. LLL yyyy");

        return (
            <div className="weather-card">
                <div className="weather-card__date">
                    {locationName} <span className="weather-card__date-text">{formattedDate}</span>
                </div>
                <div className="weather-card__data">
                    <div className="weather-card__data__day">
                        <div className="weather-card__data__temperature">{`${tempMax}°C`}</div>
                        <div>{dayWeather}</div>
                    </div>
                    <div className="weather-card__data__night">
                        <div className="weather-card__data__temperature">{`${tempMin}°C`}</div>
                        <div>{nightWeather}</div>
                    </div>
                    <div className="weather-card__data__graphic">
                        <svg xmlns="http://www.w3.org/2000/svg" width="150" height="150" viewBox="0 0 200 200">
                            <circle cx="100" cy="100" r="90" fill="#777777" />
                            <path d="M100 10 A90 90 0 0 0 100 190" fill="#FFD700" />
                        </svg>
                    </div>
                </div>
            </div>
        );
    }
}

export default WeatherCard;