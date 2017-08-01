import React from 'react';
import {render} from 'react-dom';
import { Router, IndexRoute, Route, browserHistory } from 'react-router';
import routes from './Routes.jsx';
import { receivedData } from './actions/actions';

import rootReducer from './reducers/index.js';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';

class App extends React.Component {
  
	render () {
		return (
			<Provider store={this.props.store}>
				<Router history={browserHistory} routes={routes} />
			</Provider>
		)
	}
} 

const io = require('socket.io-client');  
const socket = io();

// const store = createStore(rootReducer, window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__(), applyMiddleware(thunk));
const store = createStore(rootReducer, applyMiddleware(thunk)); 

socket.on('trade message', (payload) => {   
			console.log('received some data', payload);
			let trade = JSON.parse(payload);
			store.dispatch(receivedData(trade.currencyTo, trade.timePlaced, trade.amountBuy));
});  

render(<App store={store} />, document.getElementById('app-container'));