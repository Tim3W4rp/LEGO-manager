import React, { Component } from 'react'
import { connect } from 'react-redux'
import {bindActionCreators} from 'redux'

import Link from '../../elements/link/Link'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton'

import * as actions from './actions'
import './Set.css'

class Set extends Component {

  componentWillMount() {
    this.props.loadSet(this.props.routeParams.id)
  }

  delete() {
      this.props.deleteSet(this.props.set.id).then(r => (Link.redirect('/sets/')))
  }

  render() {
    const { handleSubmit } = this.props
    return (
      <Paper className="Set" zDepth={1}>

        <form className="SetCreate-form" onSubmit={vals => handleSubmit(this.submit(this.props.set.id))}>
          <Link to={'/set/update/' + this.props.set.id}>
            <RaisedButton className="Set-update">Update</RaisedButton>
          </Link>
            <RaisedButton className="Brick-delete" onClick={e => this.delete()}>Delete</RaisedButton>
        </form>
        <div className="Set-label">Set {this.props.set.id}</div>
        <Divider />
        <div className="Set-title">{this.props.set.description}</div>
        <div className="Set-description">Price: {this.props.set.price}</div>
      </Paper>
    )
  }
}

export default connect(store => ({
  set: store.setPage.set
}), dispatch => bindActionCreators({
  ...actions
}, dispatch))(Set)
