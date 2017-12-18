import React, { Component } from 'react'
import { connect } from 'react-redux'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'


import './Set.css'

class Set extends Component {
  render() {
    return (
      <Paper className="Set" zDepth={1}>
        <div className="Set-label">Set {this.props.set.id}</div>
        <Divider />
        <div className="Set-title">{this.props.set.description}</div>
        <div className="Set-description">{this.props.set.price}</div>
      </Paper>
    )
  }
}

const mapStateToProps = store => {
  return {set: store.setPage.set}
}

export default connect(mapStateToProps)(Set)
