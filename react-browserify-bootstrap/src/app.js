import React from 'react';
import ReactDOM from 'react-dom'

import NavDropDown from './NavDropDown'
import NavPushRight from './NavPushRight'

if(document.getElementById('nav-dropdown')) {
    ReactDOM.render(
        <NavDropDown />,
        document.getElementById('nav-dropdown')
    )
}

if(document.getElementById('nav-pushright')) {
    ReactDOM.render(
        <NavPushRight />,
        document.getElementById('nav-pushright')
    )
}
