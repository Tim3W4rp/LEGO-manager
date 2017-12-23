import React, { Component } from 'react';
import { connect } from 'react-redux'

class Loading extends Component {
  render() {
    return (<div />);
  }
}

export default connect(store => ({}))(Loading)
