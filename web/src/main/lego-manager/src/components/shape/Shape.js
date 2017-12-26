import React, {Component} from 'react'
import {connect} from 'react-redux'
import {bindActionCreators} from 'redux'

import Link from '../../elements/link/Link'
import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton';

import * as actions from './actions'
import './Shape.css'

class Shape extends Component {

  componentWillMount() {
    this.props.loadShape(this.props.routeParams.id)
  }

  delete() {
    this.props.deleteShape(this.props.shape.id)
      .then(r => (Link.redirect('/shapes/')))
  }

  render() {
    return (<Paper className="Shape" zDepth={1}>
      <div className="Shape-label">Shape {this.props.shape.id}</div>
      <Divider/>
      <div className="Shape-title">{this.props.shape.name}</div>
      <Link to={'/shape/update/' + this.props.shape.id}>
        <RaisedButton className="Shape-update">Update</RaisedButton>
      </Link>

      <RaisedButton className="Shape-delete" onClick={e => this.delete()}>Delete</RaisedButton>
    </Paper>)
  }
}

export default connect(store => ({shape: store.shapePage.shape}), dispatch => (bindActionCreators({
  ...actions
}, dispatch)))(Shape)
