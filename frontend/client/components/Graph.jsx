import { Link } from 'react-router';
import React, { PropTypes } from 'react';
import { Line } from 'react-chartjs-2';

class Graph extends React.Component {

  render() {
    return (  
        <div>
          <p>
            <strong>Realtime Trading</strong>
            <Line data={this.props.data} />
          </p>
        </div>
    );
  }
}

export default Graph;