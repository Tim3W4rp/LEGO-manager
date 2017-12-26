import React, {Component} from 'react'
import {connect} from 'react-redux'
import {bindActionCreators} from 'redux'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton'

import {TextField} from 'redux-form-material-ui'
import {Field, reduxForm} from 'redux-form'

import * as actions from './actions'
import {loadShape} from '../shape/actions'
import './ShapeUpdate.css'

import Link from '../../elements/link/Link'

class ShapeUpdate extends Component {

  componentWillMount() {
    this.props.loadShape(this.props.routeParams.id)
  }

  submit(values) {
    this.props.updateShape(values).then(r => (Link.redirect('/shape/' + r.value.data.id)))
  }

  render() {
    const {handleSubmit} = this.props
    return (<Paper className="ShapeUpdate" zDepth={1}>
      <div className="ShapeUpdate-label">Update shape</div>

      <Divider/>

      <form className="ShapeUpdate-form" onSubmit={handleSubmit(vals => this.submit(vals))}>
        <Field className="ShapeUpdate-item" name="name" component={TextField} hintText="Name" validate={[required]}/>

        <RaisedButton type="submit" label="Update shape" primary={true}/>
      </form>
    </Paper>)
  }
}

const required = value => value
  ? undefined
  : 'Required'

let component = reduxForm({form: 'shapeUpdate', enableReinitialize: true})(ShapeUpdate)

component = connect(store => ({
  initialValues: store.shapePage.shape
}), dispatch => (bindActionCreators({
  ...actions, loadShape
}, dispatch)))(component)

export default component
