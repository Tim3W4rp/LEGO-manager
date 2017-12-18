import React, { Component } from 'react'
import { connect } from 'react-redux'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'


import './Kit.css'

class Kit extends Component {
  render() {
    return (
      <Paper className="Kit" zDepth={1}>
        <div className="Kit-label">Category {this.props.kit.id}</div>
        <Divider />
        <div className="Kit-title">{this.props.kit.description}</div>
        <div className="Kit-price">{this.props.kit.price}</div>
        <div className="Kit-ageLimit">{this.props.kit.ageLimit}</div>
        <div className="Kit-category">{this.props.kit.category}</div>
      </Paper>
    )
  }
}

const mapStateToProps = store => {
  return {kit: store.kitPage.kit}
}

export default connect(mapStateToProps)(Kit)
