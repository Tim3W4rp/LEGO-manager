import React, { Component } from 'react'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton'

import { TextField } from 'redux-form-material-ui'
import { Field, reduxForm } from 'redux-form'

import './CategoryCreate.css'
import * as actions from './actions'

import Link from '../../elements/link/Link'


class CategoryCreate extends Component {

  submit(values) {
    this.props.addCategory(values)
      .then(r => (
        Link.redirect('/category/' + r.value.data.id)
      ))
  }

  render() {
    const { handleSubmit } = this.props
    return (
      <Paper className="CategoryCreate" zDepth={1}>
        <div className="CategoryCreate-label">Create new category</div>

        <Divider />

        <form className="CategoryCreate-form" onSubmit={handleSubmit(vals => this.submit(vals))}>
          <Field
            className="CategoryCreate-item"
            name="name"
            component={TextField}
            hintText="Name"
            validate={[ required ]} />

          <Field
            className="CategoryCreate-item"
            name="description"
            component={TextField}
            hintText="description"
            multiLine={true} />

          <RaisedButton type="submit" label="Add category" primary={true} />
        </form>
      </Paper>
    )
  }
}

const required = value => value ? undefined : 'Required'

let component = connect(store => ({

}), dispatch => bindActionCreators({
  ...actions
}, dispatch))(CategoryCreate)

component = reduxForm({
  form: 'categoryCreate'
})(component)

export default component
