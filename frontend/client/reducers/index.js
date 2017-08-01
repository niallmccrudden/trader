import { combineReducers } from 'redux';
import chartReducer from './chart.js';

const rootReducer = combineReducers({
    chart: chartReducer
});

export default rootReducer;