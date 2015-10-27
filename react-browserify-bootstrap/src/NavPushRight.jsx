import React, { Component, PropTypes } from 'react'
import 'jasny-bootstrap'

export default class NavPushRight extends Component {
    render() {
        return (
            <div>
                <div className="navmenu navmenu-default navmenu-fixed-left offcanvas">
                    <a className="navmenu-brand" href="#">Project name</a>
                    <ul className="nav navmenu-nav">
                        <li><a href="#">Link</a></li>
                        <li><a href="#">Link</a></li>
                        <li><a href="#">Foo!</a></li>
                    </ul>
                </div>

                <div className="navbar navbar-default navbar-fixed-top">
                    <button type="button" className="navbar-toggle" data-toggle="offcanvas" data-target=".navmenu"
                            data-canvas="body">
                        <span className="icon-bar"></span>
                        <span className="icon-bar"></span>
                        <span className="icon-bar"></span>
                    </button>
                </div>
            </div>
        )
    }
}
