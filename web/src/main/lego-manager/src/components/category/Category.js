import React, { Component } from 'react'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'

import Link from '../../elements/link/Link'
import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton';
import * as CategoryActions from './actions'

import './Category.css'

class Category extends Component {
  delete() {
    this.props.deleteCategory(this.props.category.id)
      .then(r => (
        Link.redirect('/categories/')
      ))
  }

  render() {
    return (
      <Paper className="Category" zDepth={1}>
        <div className="Category-label">Category {this.props.category.id}</div>
        <Divider />
        <div className="Category-title">{this.props.category.name}</div>
        <div className="Category-description">{this.props.category.description}</div>
        <Link to={'/category/update/' + this.props.category.id}>
          <RaisedButton
            className="Category-update">Update</RaisedButton>
        </Link>

        <RaisedButton
          className="Category-delete"
          onClick={e => this.delete()}>Delete</RaisedButton>
      </Paper>
    )
  }
}

export default connect(store => ({
  category: store.categoryPage.category
}), dispatch => (
  bindActionCreators({ ...CategoryActions }, dispatch)
))(Category)
