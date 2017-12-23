import React, { Component } from 'react'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton'

import { TextField } from 'redux-form-material-ui'
import { Field, reduxForm } from 'redux-form'

import * as actions from './actions'
import './LoginForm.css'

import Link from '../../elements/link/Link'


class LoginForm extends Component {

  submit(vals) {
    this.props.signIn(vals)
  }

  render() {
    const { handleSubmit } = this.props
    return (
      <Paper className="LoginForm" zDepth={1}>
        <div className="LoginForm-label">You need to sign in to see this section</div>

        <Divider />

        <form className="LoginForm-form" onSubmit={handleSubmit(vals => this.submit(vals))}>
          <Field
            className="LoginForm-item"
            name="name"
            component={TextField}
            hintText="Username"
            validate={[ required ]} />

          <Field
            className="LoginForm-item"
            name="password"
            component={TextField}
            hintText="Password"
            validate={[ required ]} />

          <RaisedButton type="submit" label="SIGN IN" primary={true} />
        </form>
      </Paper>
    )
  }
}

const required = value => value ? undefined : 'Required'

let component = connect(store => ({

}), dispatch => (
  bindActionCreators({ ...actions }, dispatch)
))(LoginForm)

component = reduxForm({
  form: 'categoryCreate'
})(component)

export default component
