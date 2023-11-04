import {PureComponent} from 'react'
import './App.css'
import {connect, ConnectedProps} from "react-redux";
import {RootState} from "./store/store.ts";
import SearchBar from "./components/SearchBar";
import {selectLocations} from "./store/weatherSlice.ts";
import WeatherCard from "./components/WeatherCard";

class AppComponent extends PureComponent<Props> {

    onSearch = (searchValue: string) => {
        const { dispatch } = this.props;
        dispatch({type: "SEARCH_WEATHER_LOCATION", searchValue});
    }

    renderCards = () => {
        const { locations } = this.props;

        if (!locations || locations.length === 0) {
            return (
                <div className="weather-cards__none">No data to show :(</div>
            );
        }

        return locations.map((location) => {
            return (
                <WeatherCard key={location.locationName} location={location}/>
            )
        })
    }

    render() {
        return (
            <div>
                <h1>Weather Forecast</h1>
                <h3>made by Karl Kuusik</h3>
                <p>Note: </p>
                <SearchBar onSearch={this.onSearch} />

                <div className="weather-cards">
                    {this.renderCards()}
                </div>
            </div>
        )
    }
}

const mapStateToProps = (state: RootState) => {
    return {
        locations: selectLocations(state)
    };
}
const connector = connect(mapStateToProps);
type Props = ConnectedProps<typeof connector>;
const App = connector(AppComponent);
export default App;
