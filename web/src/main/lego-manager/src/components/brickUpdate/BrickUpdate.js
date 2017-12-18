import React, { Component } from 'react'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton'

import { TextField, SelectField } from 'redux-form-material-ui'
import { Field, reduxForm } from 'redux-form'

import MenuItem from 'material-ui/MenuItem';

import * as UpdateActions from './actions'
import './BrickUpdate.css'

import Link from '../../elements/link/Link'


class BrickUpdate extends Component {

    submit(values) {
        this.props.updateBrick(values)
            .then(r => (
                Link.redirect('/brick/' + r.value.data.id)
            ))
    }

    render() {
        const { handleSubmit } = this.props
        return (
            <Paper className="BrickUpdate" zDepth={1}>
                <div className="BrickUpdate-label">Update brick</div>

                <Divider />

                <form className="BrickUpdate-form" onSubmit={handleSubmit(vals => this.submit(vals))}>
                    <Field
                        className="BrickUpdate-item"
                        name="shape"
                        component={SelectField}
                        hintText="Shape"
                        validate={[ required ]}>
                        {this.props.shapes.data.map((shape) => (
                            <MenuItem value={shape} primaryText={shape.name} />
                        ))}
                    </Field>

                    <Field
                        className="BrickUpdate-item"
                        name="red"
                        component={TextField}
                        hintText="Red"
                        validate={[
                            required,
                            number255
                        ]}/>

                    <Field
                        className="BrickUpdate-item"
                        name="green"
                        component={TextField}
                        hintText="Green"
                        validate={[
                            required,
                            number255
                        ]}/>

                    <Field
                        className="BrickUpdate-item"
                        name="blue"
                        component={TextField}
                        hintText="Blue"
                        validate={[
                            required,
                            number255
                        ]}/>

                    <RaisedButton type="submit" label="Update brick" primary={true} />
                </form>
            </Paper>
        )
    }
}

const required = value => value ? undefined : 'Required'
const number255 = value => (/^\d+$/).test(value) && value >= 0 && value <= 255 ? undefined : 'Number 0 - 255'


let component = reduxForm({
    form: 'brickUpdate',
    enableReinitialize: true,
})(BrickUpdate)

component = connect(store => ({
    initialValues: store.brickPage.brick,
    shapes: store.shapesPage.shapes
}), dispatch => (
    bindActionCreators({ ...UpdateActions }, dispatch)
))(component)

export default component