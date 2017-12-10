import React, {Component} from 'react';
import { connect } from 'react-redux'
import Link from '../../elements/link/Link'
import {
  Table,
  TableBody,
  TableFooter,
  TableHeader,
  TableHeaderColumn,
  TableRow,
  TableRowColumn
} from 'material-ui/Table';

import './Categories.css';

class Categories extends Component {
  render() {
    return (<div className="Categories">
      <Table
        selectable={false}
        onRowClick={this.handleClick}>
        <TableHeader displaySelectAll={false}>
          <TableRow>
            <TableHeaderColumn>ID</TableHeaderColumn>
            <TableHeaderColumn>Name</TableHeaderColumn>
            <TableHeaderColumn>Description</TableHeaderColumn>
          </TableRow>
        </TableHeader>
        <TableBody displayRowCheckbox={false} showRowHover={true}>
          {this.props.categories.data.map((category) =>
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
          )}
        </TableBody>
      </Table>
    </div>);
  }
}

const mapStateToProps = store => {
  return {categories: store.categoriesPage.categories}
}

export default connect(mapStateToProps)(Categories)
