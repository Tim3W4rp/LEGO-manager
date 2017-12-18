import React, { Component } from 'react'
import { connect } from 'react-redux'
import Link from '../../elements/link/Link'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton'

import { removeSet } from './actions'

import './Set.css'

class Set extends Component {
  submit(id) {
    this.props.dispatch(removeSet(id))
      .then(r => (
        Link.redirect('/sets')
      ))
  }

  render() {
    const { handleSubmit } = this.props
    return (
      <Paper className="Set" zDepth={1}>

        <form className="SetCreate-form" onSubmit={vals => handleSubmit(this.submit(this.props.set.id))}>
          <Link to={'/set/update/' + this.props.set.id}>
            <RaisedButton primary={true}
              className="Set-update">Update set</RaisedButton>
          </Link>
          <RaisedButton type="submit" label="Remove set" primary={true} />
        </form>
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
