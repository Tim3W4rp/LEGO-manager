import React, {Component} from 'react'
import {connect} from 'react-redux'
import {bindActionCreators} from 'redux'

import Link from '../../elements/link/Link'
import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import BrickElement from '../../elements/brick/Brick'
import RaisedButton from 'material-ui/RaisedButton';
import * as actions from './actions'

import './Brick.css'

class Brick extends Component {

  componentWillMount() {
    this.props.loadBrick(this.props.routeParams.id)
  }

  delete() {
    this.props.deleteBrick(this.props.brick.id).then(r => (Link.redirect('/bricks/')))
  }

  render() {
    let brick = this.props.brick
    return (<Paper className="Brick" zDepth={1}>
      <div className="Brick-label">Brick {brick.id}</div>
      <Divider/>
      <BrickElement size="40" color={'rgb(' + brick.dtoRed + ', ' + brick.dtoGreen + ', ' + brick.dtoBlue + ')'}/>
      <Link to={'/brick/update/' + this.props.brick.id}>
        <RaisedButton className="Brick-update">Update</RaisedButton>
      </Link>

      <RaisedButton className="Brick-delete" onClick={e => this.delete()}>Delete</RaisedButton>
    </Paper>)
  }
}

export default connect(store => ({
  brick: store.brickPage.brick
}), dispatch => (bindActionCreators({
  ...actions
}, dispatch)))(Brick)
