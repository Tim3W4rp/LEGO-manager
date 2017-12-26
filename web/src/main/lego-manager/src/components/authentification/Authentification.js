import React, { Component } from 'react'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'
import * as actions from './actions'

import { timestamp_valid } from './storedUser'

import LoginForm from '../../components/loginForm/LoginForm'

class Authentification extends Component {

  isAuthentificated() {
    return timestamp_valid(this.props.user.exp)
  }

  render() {
    return (
      <div>
        {this.isAuthentificated() ? (
          this.props.children
        ) : (
          <LoginForm />
        )}
      </div>
    )
  }
}

export default connect(store => ({
  user: store.user
}), dispatch => (
  bindActionCreators({ ...actions }, dispatch)
))(Authentification)
