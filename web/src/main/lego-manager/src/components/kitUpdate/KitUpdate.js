import React, { Component } from 'react'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton'

import { TextField } from 'redux-form-material-ui'
import { Field, reduxForm } from 'redux-form'

import * as actions from './actions'
import {loadKit} from '../kit/actions'
import {loadBricks} from "../bricks/actions";

import './KitUpdate.css'

import Link from '../../elements/link/Link'


class KitUpdate extends Component {

    componentWillMount() {
        this.props.loadKit(this.props.routeParams.id)
    }

    submit(values) {
        this.props.updateKit(values)
            .then(r => (
                Link.redirect('/kit/' + r.value.data.id)
            ))
    }

    render() {
        const { handleSubmit } = this.props
        return (
            <Paper className="KitUpdate" zDepth={1}>
              <div className="KitUpdate-label">Update kit</div>

              <Divider />

              <form className="KitUpdate-form" onSubmit={handleSubmit(vals => this.submit(vals))}>


                <RaisedButton type="submit" label="Update kit" primary={true} />
              </form>
            </Paper>
        )
    }
}

const required = value => value ? undefined : 'Required'

let component = reduxForm({
    form: 'kitUpdate',
    enableReinitialize: true,
})(KitUpdate)

component = connect(store => ({
    initialValues: store.kitPage.kit
}), dispatch => bindActionCreators({
    ...actions, loadKit
}, dispatch))(component)

export default component
