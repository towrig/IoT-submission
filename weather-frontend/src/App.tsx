import {PureComponent} from 'react'
import './App.css'
import {connect, ConnectedProps} from "react-redux";
import {RootState} from "./store/store.ts";
import SearchBar from "./components/SearchBar";

class AppComponent extends PureComponent<Props> {

    onSearch = (searchValue: string) => {
        const { dispatch } = this.props;
        dispatch({type: "SEARCH_WEATHER_LOCATION", searchValue});
    }

    render() {

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
    console.log({state});
    return {};
}
const connector = connect(mapStateToProps);
type Props = ConnectedProps<typeof connector>;
const App = connector(AppComponent);
export default App;
