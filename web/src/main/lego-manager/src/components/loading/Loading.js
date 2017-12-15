import React, { Component } from 'react';
import { connect } from 'react-redux'

import './Loading.css';

class Loading extends Component {
  render() {
    return (<div />);
  }
}

const mapStateToProps = store => {
}

export default connect(mapStateToProps)(Loading)
