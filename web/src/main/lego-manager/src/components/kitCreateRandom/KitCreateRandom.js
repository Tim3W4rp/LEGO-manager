import React, { Component } from 'react'
import { connect } from 'react-redux'
import {bindActionCreators} from 'redux'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton'

import { TextField } from 'redux-form-material-ui'
import { Field, reduxForm } from 'redux-form'

import './KitCreateRandom.css'
import * as actions from './actions'

import Link from '../../elements/link/Link'


class KitCreate extends Component {
  submit(values) {
    values.min = Number.parseInt(values.min, 10);
    values.max = Number.parseInt(values.max, 10);
    values.bricks = [{id: 1}, {id: 2}, {id: 3}];
    this.props.addKit(values)
      .then(r => (
        Link.redirect('/kit/' + r.value.data.id)
      ))
  }

  render() {
    const { handleSubmit } = this.props
    return (
      <Paper className="KitCreate" zDepth={1}>
        <div className="KitCreate-label">Create new kit with random bricks</div>

        <Divider />

        <form className="KitCreate-form" onSubmit={handleSubmit(vals => this.submit(vals))}>
          <Field
            className="KitCreate-item"
            name="min"
            component={TextField}
            hintText="Minimum value"
            validate={[ required ]} />

          <Field
            className="KitCreate-item"
            name="max"
            component={TextField}
            hintText="Maximum value"
            multiLine={true} />

          <Field
            className="KitCreate-item"
            name="bricks"
            component={TextField}
            hintText="List of bricks"
            multiLine={true} />

          <RaisedButton type="submit" label="Generate kit" primary={true} />
        </form>
      </Paper>
    )
  }
}

const required = value => value ? undefined : 'Required'

let component = connect(store => ({

}), dispatch => bindActionCreators({
  ...actions
}, dispatch))(KitCreate)

component = reduxForm({
  form: 'kitCreate'
})(component)

export default component
