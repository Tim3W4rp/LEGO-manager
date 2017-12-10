import React, { Component } from 'react';
import { connect } from 'react-redux'

import Paper from 'material-ui/Paper';
import Divider from 'material-ui/Divider';


import './Loading.css';

class Loading extends Component {
  render() {
    return (<div />);
  }
}

const mapStateToProps = store => {
}

export default connect(mapStateToProps)(Loading)
