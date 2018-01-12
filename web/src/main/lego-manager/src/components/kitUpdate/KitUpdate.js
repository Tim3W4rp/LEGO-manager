import React, {Component} from 'react'
import {connect} from 'react-redux'
import {bindActionCreators} from 'redux'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton'

import BrickElement from '../../elements/brick/Brick'

import {TextField, SelectField} from 'redux-form-material-ui'
import {Field, formValueSelector, reduxForm} from 'redux-form'

import MenuItem from 'material-ui/MenuItem';

import * as actions from './actions'
import {loadKit} from '../kit/actions'
import {loadCategories} from "../categories/actions"
import { loadBricks } from '../bricks/actions'

import './KitUpdate.css'

import Link from '../../elements/link/Link'


class KitUpdate extends Component {

    componentWillMount() {
        this.props.loadKit(this.props.routeParams.id)
        this.props.loadCategories()
        this.props.loadBricks()
    }

    submit(values) {
        this.props.updateKit(values)
            .then(r => (
                Link.redirect('/kit/' + r.value.data.id)
            ))
    }

    render() {
        const {handleSubmit} = this.props
        let { form } = this.props
        return (
            <Paper className="KitUpdate" zDepth={1}>
                <div className="KitUpdate-label">Update kit</div>

                <Divider/>

                <form className="KitUpdate-form" onSubmit={handleSubmit(vals => this.submit(vals))}>

                    <Field className="KitUpdate-item" name="description" component={TextField} hintText="Description"
                           validate={[required]}/>

                    <Field className="KitUpdate-item" name="price" component={TextField} hintText="Price"
                           validate={[required]}/>

                    <Field className="KitUpdate-item" name="ageLimit" component={TextField} hintText="Age limit"
                           validate={[required]}/>

                    <Field className="KitUpdate-item" name="category" component={SelectField} hintText="Category"
                           validate={[required]}>
                        {this.props.categories.data.map((category) => (
                            <MenuItem key={category.id} value={category} primaryText={category.name}/>))}
                    </Field>

                    <Field
                        className="KitUpdate-item"
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

                    <RaisedButton type="submit" label="Update kit" primary={true}/>
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

const selector = formValueSelector('kitUpdate')
component = connect(store => ({
    initialValues: {
        description: store.kitPage.kit.description,
        price: store.kitPage.kit.price,
        ageLimit: store.kitPage.kit.ageLimit,
        category: store.kitPage.kit.category,
        bricks: store.kitPage.kit.bricks,
        id: store.kitPage.kit.id
    },
    categories: store.categoriesPage.categories,
    bricks: store.bricksPage.bricks,
    form: {
    bricks: selector(store, 'bricks'),
}
}), dispatch => bindActionCreators({
    ...actions, loadKit, loadCategories, loadBricks
}, dispatch))(component)

export default component
