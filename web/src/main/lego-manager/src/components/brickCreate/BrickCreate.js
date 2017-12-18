import React, {Component} from 'react'
import {connect} from 'react-redux'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton'

import {TextField} from 'redux-form-material-ui'
import {Field, reduxForm} from 'redux-form'

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
                        component={TextField}
                        hintText="Shape"
                        validate={[ required ]}/>

                    <Field
                        className="BrickCreate-item"
                        name="red"
                        component={TextField}
                        hintText="Red"
                        validate={[ required ]}/>

                    <Field
                        className="BrickCreate-item"
                        name="green"
                        component={TextField}
                        hintText="Green"
                        validate={[ required ]}/>

                    <Field
                        className="BrickCreate-item"
                        name="blue"
                        component={TextField}
                        hintText="Blue"
                        validate={[ required ]}/>

                    <RaisedButton type="submit" label="Add brick" primary={true}/>
                </form>
            </Paper>
        )
    }
}

const required = value => value ? undefined : 'Required'

let component = connect(store => ({}), dispatch => ({
    dispatch,
}))(BrickCreate)

component = reduxForm({
    form: 'brickCreate'
})(component)

export default component
