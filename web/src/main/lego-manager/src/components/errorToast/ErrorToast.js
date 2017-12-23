import React, { Component } from 'react';
import { connect } from 'react-redux'
import {bindActionCreators} from 'redux'

import Snackbar from 'material-ui/Snackbar';

import  * as actions from './actions'

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
    this.props.toggleToast(false)
  }
}

export default connect(store => ({
  error: store.error
}), dispatch => bindActionCreators({
  ...actions
}, dispatch))(ErrorToast)
