import types from '../constants/';

export function receivedData(currency, dateProcessed, amountBought) {
    return {
        type: types.TRADE_RECEIVED,
        payload: {
            currency,
            dateProcessed,
            amountBought
        }
    }
}