import {PureComponent} from 'react'
import './App.css'
import {connect, ConnectedProps} from "react-redux";
import {RootState} from "./store/store.ts";
import SearchBar from "./components/SearchBar";
import {selectLocations} from "./store/weatherSlice.ts";

class AppComponent extends PureComponent<Props> {

    onSearch = (searchValue: string) => {
        const { dispatch } = this.props;
        dispatch({type: "SEARCH_WEATHER_LOCATION", searchValue});
    }

    render() {
        const { locations } = this.props;
        console.log("RENDER LOCATIONS:", locations);

        return (
            <div>
                <h1>Weather Forecast</h1>
                <h3>made by Karl Kuusik</h3>
                <SearchBar onSearch={this.onSearch} />
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
