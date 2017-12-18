import React, { Component } from 'react'
import { connect } from 'react-redux'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton'

import { TextField } from 'redux-form-material-ui'
import { Field, reduxForm } from 'redux-form'

import './KitCreate.css'
import { addKit } from './actions'

import Link from '../../elements/link/Link'


class KitCreate extends Component {

  submit(values) {
    this.props.dispatch(addKit(values))
      .then(r => (
        Link.redirect('/kit/' + r.value.data.id)
      ))
  }

  render() {
    const { handleSubmit } = this.props
    return (
      <Paper className="KitCreate" zDepth={1}>
        <div className="KitCreate-label">Create new kit</div>

        <Divider />

        <form className="KitCreate-form" onSubmit={handleSubmit(vals => this.submit(vals))}>
          <Field
            className="KitCreate-item"
            name="description"
            component={TextField}
            hintText="Description"
            validate={[ required ]} />

          <Field
            className="KitCreate-item"
            name="price"
            component={TextField}
            hintText="price"
            multiLine={true} />

          <Field
            className="KitCreate-item"
            name="ageLimit"
            component={TextField}
            hintText="Age Limit"
            multiLine={true} />

          <Field
            className="KitCreate-item"
            name="category"
            component={TextField}
            hintText="category"
            multiLine={true} />

          <RaisedButton type="submit" label="Add kit" primary={true} />
        </form>
      </Paper>
    )
  }
}

const required = value => value ? undefined : 'Required'

let component = connect(store => ({

}), dispatch => ({
  dispatch,
}))(KitCreate)

component = reduxForm({
  form: 'kitCreate'
})(component)

export default component
