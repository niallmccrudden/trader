import types from '../constants/';

const init = {
    labels: [],
    datasets: chartDatasetFactory()
};

export default function(state=init, action) {
  switch(action.type) {
    case types.TRADE_RECEIVED:
      let clonedDatasets = state.datasets.slice();
      let datasetIndex = getDatasetForCurrency(action.payload.currency, clonedDatasets);
      
      if (datasetIndex !== false) {
        clonedDatasets[datasetIndex].data.push({x: action.payload.dateProcessed, y: action.payload.amountBought});

        // TODO - performance improvements required for a large volume of trades 
        
        return Object.assign({}, state, 
          { 
            labels: (state.labels.indexOf(action.payload.dateProcessed) > -1) ? state.labels : [...state.labels, action.payload.dateProcessed],  
            datasets: clonedDatasets
          }
        );
      }

    default:
      return state;
  }
};


function getDatasetForCurrency(currency, datasets) {
  for (var i = 0; i < datasets.length; i++) {
    if (datasets[i].currency == currency) {
      return i;
    }
  }

  return false;
}

function chartDatasetFactory() {
  let datasets = [];

  datasets[0] = {
    currency: 'EUR',
    label: 'EUR Bought',
    fill: false,
    lineTension: 0.1,
    backgroundColor: 'rgba(75,192,192,0.4)',
    borderColor: 'rgba(75,192,192,1)',
    borderCapStyle: 'butt',
    borderDash: [],
    borderDashOffset: 0.0,
    borderJoinStyle: 'miter',
    pointBorderColor: 'rgba(75,192,192,1)',
    pointBackgroundColor: '#fff',
    pointBorderWidth: 1,
    pointHoverRadius: 5,
    pointHoverBackgroundColor: 'rgba(75,192,192,1)',
    pointHoverBorderColor: 'rgba(220,220,220,1)',
    pointHoverBorderWidth: 2,
    pointRadius: 1,
    pointHitRadius: 10,
    data: []
  };

  datasets[1] = {
    currency: 'GBP',
    label: 'GBP Bought',
    fill: false,
    lineTension: 0.1,
    backgroundColor: 'rgba(216,113,113,1)',
    borderColor: 'rgba(216,113,113,1)',
    borderCapStyle: 'butt',
    borderDash: [],
    borderDashOffset: 0.0,
    borderJoinStyle: 'miter',
    pointBorderColor: 'rgba(216,113,113,1)',
    pointBackgroundColor: '#fff',
    pointBorderWidth: 1,
    pointHoverRadius: 5,
    pointHoverBackgroundColor: 'rgba(216,113,113,1)',
    pointHoverBorderColor: 'rgba(220,220,220,1)',
    pointHoverBorderWidth: 2,
    pointRadius: 1,
    pointHitRadius: 10,
    data: []
  };

  return datasets;
}