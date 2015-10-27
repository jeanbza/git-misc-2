import React, { Component, PropTypes } from 'react'
import 'jasny-offcanvas'

export default class NavPushRight extends Component {
    render() {
        const links = [
            {
                active: true,
                url: '#',
                text: 'Hello world!'
            },
            {
                active: false,
                url: '#',
                text: 'Foo!'
            },
            {
                active: false,
                url: '#',
                text: 'Bar!'
            }
        ]

        return (
            <nav className="navbar navbar-default">
                <div className="container-fluid">
                    <div className="navbar-header">
                        <button type="button" className="navbar-toggle"
                                data-toggle="offcanvas" data-target=".navmenu" data-canvas="body">
                            <span className="sr-only">Toggle navigation</span>
                            <span className="icon-bar"></span>
                            <span className="icon-bar"></span>
                            <span className="icon-bar"></span>
                        </button>
                        <a className="navbar-brand" href="#">Brand</a>
                        <ul className="nav navbar-nav hidden-xs">
                            {this._listItems(links)}
                        </ul>
                    </div>

                    <div className="offcanvas navmenu navmenu-default navmenu-fixed-left">
                        <a className="navmenu-brand" href="#">Project name</a>
                        <ul className="nav navmenu-nav">
                            {this._listItems(links)}
                        </ul>
                    </div>
                </div>
            </nav>
        )
    }

    _listItems(links) {
        return links.map(function(link, index) {
            if (link.active) {
                return (
                    <li key={index} className="active"><a href={link.url}>{link.text}<span className="sr-only">(current)</span></a></li>
                )
            } else {
                return (
                    <li key={index}><a href={link.url}>{link.text}</a></li>
                )
            }
        })
    }
}
