import React, { Component } from 'react'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton'

import { TextField } from 'redux-form-material-ui'
import { Field, reduxForm } from 'redux-form'

import * as actions from './actions'
import {loadSet} from '../set/actions'
import './SetUpdate.css'

import Link from '../../elements/link/Link'


class SetUpdate extends Component {

  componentWillMount() {
    this.props.loadSet(this.props.routeParams.id)
  }

  submit(values) {
    this.props.updateSet(values)
      .then(r => (
        Link.redirect('/set/' + r.value.data.id)
      ))
  }

  render() {
    const { handleSubmit } = this.props
    return (
      <Paper className="SetUpdate" zDepth={1}>
        <div className="SetUpdate-label">Update set</div>

        <Divider />

        <form className="SetUpdate-form" onSubmit={handleSubmit(vals => this.submit(vals))}>
          <Field
            className="SetUpdate-item"
            name="description"
            component={TextField}
            hintText="desc"
            validate={[ required ]} />

          <Field
            className="SetUpdate-item"
            name="price"
            component={TextField}
            hintText="price"
            multiLine={true} />

          <RaisedButton type="submit" label="Update set" primary={true} />
        </form>
      </Paper>
    )
  }
}

const required = value => value ? undefined : 'Required'

let component = reduxForm({
  form: 'setUpdate',
  enableReinitialize: true,
})(SetUpdate)

component = connect(store => ({
  initialValues: store.setPage.set
}), dispatch => (
  bindActionCreators({ ...actions, loadSet}, dispatch)
))(component)

export default component
