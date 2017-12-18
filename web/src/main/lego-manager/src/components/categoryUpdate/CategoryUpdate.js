import React, { Component } from 'react'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton'

import { TextField } from 'redux-form-material-ui'
import { Field, reduxForm } from 'redux-form'

import * as UpdateActions from './actions'
import './CategoryUpdate.css'

import Link from '../../elements/link/Link'


class CategoryUpdate extends Component {

  submit(values) {
    this.props.updateCategory(values)
      .then(r => (
        Link.redirect('/category/' + r.value.data.id)
      ))
  }

  render() {
    const { handleSubmit } = this.props
    return (
      <Paper className="CategoryUpdate" zDepth={1}>
        <div className="CategoryUpdate-label">Update category</div>

        <Divider />

        <form className="CategoryUpdate-form" onSubmit={handleSubmit(vals => this.submit(vals))}>
          <Field
            className="CategoryUpdate-item"
            name="name"
            component={TextField}
            hintText="Name"
            validate={[ required ]} />

          <Field
            className="CategoryUpdate-item"
            name="description"
            component={TextField}
            hintText="description"
            multiLine={true} />

          <RaisedButton type="submit" label="Update category" primary={true} />
        </form>
      </Paper>
    )
  }
}

const required = value => value ? undefined : 'Required'

let component = reduxForm({
  form: 'categoryUpdate',
  enableReinitialize: true,
})(CategoryUpdate)

component = connect(store => ({
  initialValues: store.categoryPage.category
}), dispatch => (
  bindActionCreators({ ...UpdateActions }, dispatch)
))(component)

export default component
