import React, { Component } from 'react'
import { connect } from 'react-redux'
import {bindActionCreators} from 'redux'


import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'

import * as actions from './actions'
import './Kit.css'

class Kit extends Component {

  componentWillMount() {
    this.props.loadKit(this.props.routeParams.id)
  }

  render() {
    return (
      <Paper className="Kit" zDepth={1}>
        <div className="Kit-label">Kit {this.props.kit.id}</div>
        <Divider />
        <div className="Kit-title">{this.props.kit.description}</div>
        <div className="Kit-price">Price: {this.props.kit.price}</div>
        <div className="Kit-ageLimit">Age limit: {this.props.kit.ageLimit}</div>
        <div className="Kit-category">Category: {this.props.kit.category}</div>
      </Paper>
    )
  }
}

const mapStateToProps = store => {
  return {kit: store.kitPage.kit}
}

export default connect(store => ({
  kit: store.kitPage.kit
}), dispatch => bindActionCreators({
  ...actions
}, dispatch))(Kit)
