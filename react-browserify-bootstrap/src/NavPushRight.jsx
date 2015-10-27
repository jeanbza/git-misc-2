import React, { Component, PropTypes } from 'react'
import 'jasny-bootstrap'

export default class NavPushRight extends Component {
    render() {
        return (
            <nav className="navbar navbar-default">
                <div className="container-fluid">
                    <div className="navbar-header">
                        <button type="button" className="navbar-toggle" data-toggle="offcanvas"
                                data-target=".navmenu"
                                data-canvas="body">
                            <span className="icon-bar"></span>
                            <span className="icon-bar"></span>
                            <span className="icon-bar"></span>
                        </button>
                        <a className="navbar-brand" href="#">Brand</a>
                    </div>

                    <div className="navmenu navmenu-default navmenu-fixed-left offcanvas">
                        <a className="navmenu-brand" href="#">Project name</a>
                        <ul className="nav navmenu-nav">
                            <li><a href="#">Link</a></li>
                            <li><a href="#">Link</a></li>
                            <li><a href="#">Foo!</a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        )
    }
}
