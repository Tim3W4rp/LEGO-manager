import React, { Component } from 'react'
import {connect} from 'react-redux'
import {bindActionCreators} from 'redux'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton'

import {TextField, SelectField} from 'redux-form-material-ui'
import {Field, reduxForm} from 'redux-form'

import MenuItem from 'material-ui/MenuItem';

import * as actions from './actions'
import {loadShapes} from '../shapes/actions'
import {loadBrick} from '../brick/actions'
import {loadCategories} from '../categories/actions'
import './KitCreate.css'

import Link from '../../elements/link/Link'

class KitCreate extends Component {

    componentWillMount() {
    }

  submit(values) {
    this.props.addKit(values)
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
            hintText="Name"
            validate={[ required ]} />

          <Field
            className="KitCreate-item"
            name="price"
            component={TextField}
            hintText="Price"
            multiLine={true}
            validate={[ required ]} />

          <Field
            className="KitCreate-item"
            name="ageLimit"
            component={TextField}
            hintText="Age Limit"
            multiLine={true}
            validate={[ required ]} />

          <Field className="KitCreate-item" name="category" component={SelectField} hintText="Category" validate={[required]}>
          </Field>

          <RaisedButton type="submit" label="Add kit" primary={true} />
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
