import {ChangeEvent, PureComponent} from 'react';
import "./styles.scss";

interface Props {
    onSearch: (searchValue: string) => void,
}

interface State {
    inputValue?: string,
}

class SearchBar extends PureComponent<Props, State> {

    constructor(props: Props) {
        super(props);
        this.state = {
            inputValue: "",
        }
    }

    onChange = (e: ChangeEvent<HTMLInputElement>) => {
        const {onSearch} = this.props;
        const value = e.target.value;

        if(value.length > 2) {
            onSearch(value);
        }

        this.setState({
            inputValue: e.target.value
        })
    }

    render() {
        const { inputValue } = this.state;
        return (
            <input className="weather-searchbar" value={inputValue} onChange={this.onChange} placeholder={"Search for your location"}/>
        );
    }
}


export default SearchBar;