import React, { Component } from 'react'
import { connect } from 'react-redux'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton'

import { TextField } from 'redux-form-material-ui'
import { Field, reduxForm } from 'redux-form'

import './ShapeCreate.css'
import { addShape } from './actions'

import Link from '../../elements/link/Link'


class ShapeCreate extends Component {

    submit(values) {
        this.props.dispatch(addShape(values))
            .then(r => (
                Link.redirect('/shape/' + r.value.data.id)
            ))
    }

    render() {
        const { handleSubmit } = this.props
        return (
            <Paper className="ShapeCreate" zDepth={1}>
                <div className="ShapeCreate-label">Create new shape</div>

                <Divider />

                <form className="ShapeCreate-form" onSubmit={handleSubmit(vals => this.submit(vals))}>
                    <Field
                        className="ShapeCreate-item"
                        name="name"
                        component={TextField}
                        hintText="Name"
                        validate={[ required ]} />

                    <RaisedButton type="submit" label="Add shape" primary={true} />
                </form>
            </Paper>
        )
    }
}

const required = value => value ? undefined : 'Required'

let component = connect(store => ({

}), dispatch => ({
    dispatch,
}))(ShapeCreate)

component = reduxForm({
    form: 'shapeCreate'
})(component)

export default component
