import React, { Component } from 'react'
import { connect } from 'react-redux'
import {bindActionCreators} from 'redux'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton'
import MenuItem from 'material-ui/MenuItem';

import BrickElement from '../../elements/brick/Brick'

import {TextField, SelectField} from 'redux-form-material-ui'
import { Field, reduxForm, formValueSelector } from 'redux-form'

import './KitCreateRandom.css'
import * as actions from './actions'

import { loadBricks } from '../bricks/actions'

import Link from '../../elements/link/Link'


class KitCreate extends Component {

  constructor() {
    super()
    this.state = {
      values: [],
    };
  }

  submit(values) {
    values.min = Number.parseInt(values.min, 10);
    values.max = Number.parseInt(values.max, 10);
    this.props.addKit(values)
      .then(r => (
        Link.redirect('/kit/' + r.value.data.id)
      ))
  }

  componentWillMount() {
    this.props.loadBricks()
  }

  render() {
    const { handleSubmit } = this.props
    let { form } = this.props

    return (
      <Paper className="KitCreate" zDepth={1}>
        <div className="KitCreate-label">Create new kit with random bricks</div>

        <Divider />

        <form className="KitCreate-form" onSubmit={handleSubmit(vals => this.submit(vals))}>

          <Field
            className="KitCreate-item"
            name="min"
            component={TextField}
            hintText="Minimum bricks"
            type="number"
            validate={[ required, greaterThenZero ]} />

          <Field
            className="KitCreate-item"
            name="max"
            component={TextField}
            hintText="Maximum bricks"
            type="number"
            validate={[ required, greaterThenZero, maxIsGreater ]} />

          <Field
            className="KitCreate-item"
            name="bricks"
            component={SelectField}
            hintText="Bricks"
            validate={[required]}
            selectionRenderer={bricks => bricks.length ? `${String(bricks.length)} selected` : ''}
            multiple={true}>
            {this.props.bricks.data.map((brick) => (
              <MenuItem
                key={brick.id}
                value={brick}
                checked={form && form.bricks && form.bricks.find(b => b.id === brick.id) !== undefined}
                primaryText={
                  <div className="KitCreate-brick-item">
                    <div>{brick.id}</div>
                    <BrickElement size="15" color={'rgb(' + brick.dtoRed + ', ' + brick.dtoGreen + ', ' + brick.dtoBlue + ')'}/>
                  </div>
                }
                insetChildren={true}/>
            ))}
          </Field>

          <RaisedButton type="submit" label="Generate kit" primary={true} />
        </form>
      </Paper>
    )
  }
}

const required = value => value ? undefined : 'Required'

const greaterThenZero = value => parseInt(value, 10) > 0 ? undefined : 'Must be > 0'

const maxIsGreater = (value, values) => {
  if(values.min && values.max && parseInt(values.min, 10) > parseInt(values.max, 10)) {
    return 'Min is greater then max'
  }
  return undefined
}

const selector = formValueSelector('kitCreate')
let component = connect(store => ({
  bricks: store.bricksPage.bricks,
  form: {
    bricks: selector(store, 'bricks'),
  }
}), dispatch => bindActionCreators({
  ...actions,
  loadBricks
}, dispatch))(KitCreate)

component = reduxForm({
  form: 'kitCreate',
})(component)

export default component
