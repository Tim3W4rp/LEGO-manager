import React, { Component } from 'react'
import { connect } from 'react-redux'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton'

import { TextField } from 'redux-form-material-ui'
import { Field, reduxForm } from 'redux-form'

import './SetCreate.css'
import { addSet } from './actions'

import Link from '../../elements/link/Link'


class SetCreate extends Component {

  submit(values) {
    this.props.dispatch(addSet(values))
      .then(r => (
        Link.redirect('/set/' + r.value.data.id)
      ))
  }

  render() {
    const { handleSubmit } = this.props
    return (
      <Paper className="SetCreate" zDepth={1}>
        <div className="SetCreate-label">Create new set</div>

        <Divider />

        <form className="SetCreate-form" onSubmit={handleSubmit(vals => this.submit(vals))}>
          <Field
            className="SetCreate-item"
            name="description"
            component={TextField}
            hintText="Description"
            validate={[ required ]} />

          <Field
            className="SetCreate-item"
            name="price"
            component={TextField}
            hintText="Price"
            multiLine={true} />

          <RaisedButton type="submit" label="Add set" primary={true} />
        </form>
      </Paper>
    )
  }
}

const required = value => value ? undefined : 'Required'

let component = connect(store => ({

}), dispatch => ({
  dispatch,
}))(SetCreate)

component = reduxForm({
  form: 'setCreate'
})(component)

export default component
