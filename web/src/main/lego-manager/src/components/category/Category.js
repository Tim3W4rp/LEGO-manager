import React, { Component } from 'react'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'

import Link from '../../elements/link/Link'
import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton';
import * as CategoryActions from './actions'
import {
  Table,
  TableBody,
  TableHeader,
  TableHeaderColumn,
  TableRow,
  TableRowColumn
} from 'material-ui/Table'

import './Category.css'

class Category extends Component {

  componentWillMount() {
    this.props.loadCategory(this.props.routeParams.id)
  }

  delete() {
    this.props.deleteCategory(this.props.category.id)
      .then(r => (
        Link.redirect('/categories/')
      ))
  }

  render() {
    let kits = this.props.category.kits ? this.props.category.kits : []
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

        <Divider />

        <div className="Category-kits">Kits in this category</div>

        <Table className="Category-table" selectable={false} onRowClick={this.handleClick}>
          <TableHeader displaySelectAll={false}>
            <TableRow>
              <TableHeaderColumn>ID</TableHeaderColumn>
              <TableHeaderColumn>Name</TableHeaderColumn>
              <TableHeaderColumn>Price</TableHeaderColumn>
              <TableHeaderColumn>Age Limit</TableHeaderColumn>
            </TableRow>
          </TableHeader>
          <TableBody displayRowCheckbox={false} showRowHover={true}>
            {
              kits.map((kit) => (
                <TableRow key={kit.id}>
                  <TableRowColumn>
                    <Link to={'/kit/' + kit.id}>
                      {kit.id}
                    </Link>
                  </TableRowColumn>
                  <TableRowColumn>
                    <Link to={'/kit/' + kit.id}>
                      {kit.description}
                    </Link>
                  </TableRowColumn>
                  <TableRowColumn>
                    <Link to={'/kit/' + kit.id}>
                      {kit.price}
                    </Link>
                  </TableRowColumn>
                  <TableRowColumn>
                    <Link to={'/kit/' + kit.id}>
                      {kit.ageLimit}
                    </Link>
                  </TableRowColumn>
                </TableRow>
              ))}
          </TableBody>
        </Table>
      </Paper>
    )
  }
}

export default connect(store => ({
  category: store.categoryPage.category
}), dispatch => (
  bindActionCreators({ ...CategoryActions }, dispatch)
))(Category)
