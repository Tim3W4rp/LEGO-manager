import React, { Component } from 'react'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'

import muiThemeable from 'material-ui/styles/muiThemeable';
import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton'

import { TextField } from 'redux-form-material-ui'
import { Field, reduxForm } from 'redux-form'

import * as actions from './actions'
import './LoginForm.css'

import ErrorToast from '../errorToast/ErrorToast'
import Brick from '../../elements/brick/Brick'

class LoginForm extends Component {

  submit(vals) {
    this.props.signIn(vals)
  }

  render() {
    const { handleSubmit } = this.props
    return (
      <div className="LoginForm-container">
        <Paper className="LoginForm" zDepth={2}>
          <div className="LoginForm-title">
            <Brick size="50" color={this.props.muiTheme.palette.primary1Color} />
            <div>
              Lego <br />
              Manager
            </div>
          </div>
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
              type="password"
              validate={[ required ]} />

            <RaisedButton
              className="LoginForm-login"
              type="submit"
              label="Log in"
              primary={true} />
          </form>
        </Paper>
        <ErrorToast />
      </div>
    )
  }
}

const required = value => value ? undefined : 'Required'

let component = muiThemeable()(LoginForm)

component = connect(store => ({

}), dispatch => (
  bindActionCreators({ ...actions }, dispatch)
))(component)

component = reduxForm({
  form: 'categoryCreate'
})(component)

export default component
