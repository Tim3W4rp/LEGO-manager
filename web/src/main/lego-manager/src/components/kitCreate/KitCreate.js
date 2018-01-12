import React, {Component} from 'react'
import {connect} from 'react-redux'
import {bindActionCreators} from 'redux'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton'

import BrickElement from '../../elements/brick/Brick'

import {TextField, SelectField} from 'redux-form-material-ui'
import {Field, reduxForm, formValueSelector} from 'redux-form'

import MenuItem from 'material-ui/MenuItem';

import * as actions from './actions'
import {loadCategories} from '../categories/actions'
import { loadBricks } from '../bricks/actions'
import './KitCreate.css'

import Link from '../../elements/link/Link'

class KitCreate extends Component {

    componentWillMount() {
        this.props.loadCategories()
        this.props.loadBricks()
    }

    submit(values) {
        this.props.addKit(values)
            .then(r => (
                Link.redirect('/kit/' + r.value.data.id)
            ))
    }

    render() {
        const {handleSubmit} = this.props
        let { form } = this.props
        return (
            <Paper className="KitCreate" zDepth={1}>
                <div className="KitCreate-label">Create new kit</div>

                <Divider/>

                <form className="KitCreate-form" onSubmit={handleSubmit(vals => this.submit(vals))}>
                    <Field
                        className="KitCreate-item"
                        name="description"
                        component={TextField}
                        hintText="Name"
                        validate={[required]}/>

                    <Field
                        className="KitCreate-item"
                        name="price"
                        component={TextField}
                        hintText="Price"
                        multiLine={true}
                        validate={[required]}/>

                    <Field
                        className="KitCreate-item"
                        name="ageLimit"
                        component={TextField}
                        hintText="Age Limit"
                        multiLine={true}
                        validate={[required]}/>

                    <Field className="KitCreate-item" name="category" component={SelectField} hintText="Category"
                           validate={[required]}>
                        {this.props.categories.data.map((category) => (
                            <MenuItem key={category.id} value={category} primaryText={category.name}/>))}
                    </Field>

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


                    <RaisedButton type="submit" label="Add kit" primary={true}/>
                </form>
            </Paper>
        )
    }
}

const required = value => value ? undefined : 'Required'

const selector = formValueSelector('kitCreate')
let component = connect(store => ({
        categories: store.categoriesPage.categories,
        bricks: store.bricksPage.bricks,
        form: {
            bricks: selector(store, 'bricks'),
        }
    }),
    dispatch => (
        bindActionCreators({...actions, loadCategories, loadBricks}, dispatch)
    ))(KitCreate)

component = reduxForm({
    form: 'kitCreate'
})(component)

export default component
