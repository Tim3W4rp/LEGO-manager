import React, {Component} from 'react'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'
import Link from '../../elements/link/Link'
import {
  Table,
  TableBody,
  TableHeader,
  TableHeaderColumn,
  TableRow,
  TableRowColumn
} from 'material-ui/Table'
import FloatingActionButton from 'material-ui/FloatingActionButton'
import ContentAdd from 'material-ui/svg-icons/content/add'

import './Categories.css'
import * as actions from './actions'

class Categories extends Component {
  componentWillMount() {
    this.props.loadCategories()
  }

  render() {
    return (
      <div className="Categories">
        <Table selectable={false} onRowClick={this.handleClick}>
          <TableHeader displaySelectAll={false}>
            <TableRow>
              <TableHeaderColumn>ID</TableHeaderColumn>
              <TableHeaderColumn>Name</TableHeaderColumn>
              <TableHeaderColumn>Description</TableHeaderColumn>
            </TableRow>
          </TableHeader>
          <TableBody displayRowCheckbox={false} showRowHover={true}>
            {this.props.categories.data.map((category) => (
              <TableRow key={category.id}>
                <TableRowColumn>
                  <Link to={'/category/' + category.id}>
                    {category.id}
                  </Link>
                </TableRowColumn>
                <TableRowColumn>
                  <Link to={'/category/' + category.id}>
                    {category.name}
                  </Link>
                </TableRowColumn>
                <TableRowColumn>
                  <Link to={'/category/' + category.id}>
                    {category.description}
                  </Link>
                </TableRowColumn>
              </TableRow>
            ))}
          </TableBody>
        </Table>
        <Link to="/category/create">
          <FloatingActionButton className="Categories-floating-button">
            <ContentAdd/>
          </FloatingActionButton>
        </Link>
      </div>
    )
  }
}

export default connect(store => ({
  categories: store.categoriesPage.categories
}), dispatch => (
  bindActionCreators({ ...actions }, dispatch)
))(Categories)
