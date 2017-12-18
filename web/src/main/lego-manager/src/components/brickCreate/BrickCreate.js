import React, {Component} from 'react'
import {connect} from 'react-redux'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton'

import { TextField, SelectField } from 'redux-form-material-ui'
import {Field, reduxForm} from 'redux-form'

import MenuItem from 'material-ui/MenuItem';

import './BrickCreate.css'
import {addBrick} from './actions'

import Link from '../../elements/link/Link'


class BrickCreate extends Component {

    submit(values) {
        this.props.dispatch(addBrick(values))
            .then(r => (
                Link.redirect('/brick/' + r.value.data.id)
            ))
    }

    render() {
        const {handleSubmit} = this.props
        return (
            <Paper className="BrickCreate" zDepth={1}>
                <div className="BrickCreate-label">Create new brick</div>

                <Divider/>

                <form className="BrickCreate-form" onSubmit={handleSubmit(vals => this.submit(vals))}>

                    <Field
                        className="BrickCreate-item"
                        name="shape"
                        component={SelectField}
                        hintText="Shape"
                        validate={[ required ]}>
                        {this.props.shapes.data.map((shape) => (
                          <MenuItem value={shape} primaryText={shape.name} />
                        ))}
                    </Field>

                    <Field
                        className="BrickCreate-item"
                        name="red"
                        component={TextField}
                        hintText="Red"
                        validate={[
                           required,
                           number255
                         ]}/>

                    <Field
                        className="BrickCreate-item"
                        name="green"
                        component={TextField}
                        hintText="Green"
                        validate={[
                          required,
                          number255
                        ]}/>

                    <Field
                        className="BrickCreate-item"
                        name="blue"
                        component={TextField}
                        hintText="Blue"
                        validate={[
                           required,
                           number255
                        ]}/>

                    <RaisedButton type="submit" label="Add brick" primary={true}/>
                </form>
            </Paper>
        )
    }
}

const required = value => value ? undefined : 'Required'
const number255 = value => (/^\d+$/).test(value) && value >= 0 && value <= 255 ? undefined : 'Number 0 - 255'

let component = connect(store => ({
  shapes: store.shapesPage.shapes
}), dispatch => ({
    dispatch,
}))(BrickCreate)

component = reduxForm({
    form: 'brickCreate'
})(component)

export default component
