import 'bootstrap'

import React from 'react';
import { Provider } from 'react-redux'
import { bindActionCreators } from 'redux'
import { connect } from 'react-redux'
import ReactDOM from 'react-dom'

import configureStore from './configureStore'
import HelloWorld from './components/HelloWorld'
import * as AllActions from './actions'

const store = configureStore()

function mapStateToProps(state) {
  return {
    searchResults: Array.isArray(state.search) ? state.search : []
  }
}

function mapDispatchToProps(dispatch) {
  return bindActionCreators(AllActions, dispatch)
}

const ConnectedApp = connect(mapStateToProps, mapDispatchToProps)(HelloWorld)

ReactDOM.render(
  <Provider store={store}>
    <ConnectedApp />
  </Provider>,
  document.getElementById('root')
)
