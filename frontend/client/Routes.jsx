import React from 'react';
import { IndexRoute, Route } from 'react-router';
import Dashboard from './containers/Dashboard.jsx';
import AppShell from './containers/AppShell.jsx';


const Routes = (
    <Route path='/' component={AppShell}>
        <IndexRoute component={Dashboard}/>
    </Route>
);

export default Routes;