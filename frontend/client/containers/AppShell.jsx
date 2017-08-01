import React from 'react';
import {render} from 'react-dom';
import { Link } from 'react-router';
import { connect } from 'react-redux'


class AppShell extends React.Component {

	render () {
		return (
			<div className="container col-md-12">
				{ this.props.children }
			</div>
		);
	}
}

var mapStateToProps = function(state){
    return { state };
}

export default connect(mapStateToProps)(AppShell);

