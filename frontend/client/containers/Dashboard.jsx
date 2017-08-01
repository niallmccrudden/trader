import { Link } from 'react-router';
import React, { PropTypes } from 'react';
import { connect } from 'react-redux'
import { receivedData } from '../actions/actions';
import Graph from '../components/Graph.jsx';

class Dashboard extends React.Component {

  constructor(props) {
    	super(props); 
  }
  
  render() {
    
    let data = this.props.state.chart;

    return (

      <div className="jumbotron">
          <Graph data={data} />
      </div>
    );
  }
}

var mapStateToProps = function(state){
    return { state };
}

export default connect(mapStateToProps)(Dashboard);