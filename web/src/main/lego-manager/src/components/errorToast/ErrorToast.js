import React, { Component } from 'react';
import { connect } from 'react-redux'

import Snackbar from 'material-ui/Snackbar';

import { toggleToast } from './actions'

import './ErrorToast.css';

class ErrorToast extends Component {
  render() {
    let error = this.props.error
    return (
      <Snackbar
        open={error.opened}
        message={error.text}
        action={error.buttonText}
        onActionClick={e => this.closeToast(e)}
        onRequestClose={e => this.closeToast(e)} />
    );
  }

  closeToast() {
    this.props.dispatch(toggleToast(false))
  }
}

export default connect(store => ({
  error: store.error
}), dispatch => ({
  dispatch
}))(ErrorToast)
