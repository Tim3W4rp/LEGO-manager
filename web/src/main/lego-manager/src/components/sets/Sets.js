import React, {Component} from 'react'
import {connect} from 'react-redux'
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

import './Sets.css'

class Sets extends Component {
  render() {
    return (<div className="Sets">
      <Table selectable={false} onRowClick={this.handleClick}>
        <TableHeader displaySelectAll={false}>
          <TableRow>
            <TableHeaderColumn>ID</TableHeaderColumn>
            <TableHeaderColumn>Description</TableHeaderColumn>
            <TableHeaderColumn>Price</TableHeaderColumn>
          </TableRow>
        </TableHeader>
        <TableBody displayRowCheckbox={false} showRowHover={true}>
          {
            this.props.sets.data.map((set) => <TableRow key={set.id}>
              <TableRowColumn>
                <Link to={'/set/' + set.id}>
                  {set.id}
                </Link>
              </TableRowColumn>
              <TableRowColumn>
                <Link to={'/set/' + set.id}>
                  {set.description}
                </Link>
              </TableRowColumn>
              <TableRowColumn>
                <Link to={'/set/' + set.id}>
                  {set.price}
                </Link>
              </TableRowColumn>
            </TableRow>)
          }
        </TableBody>
      </Table>
      <Link to="/set/create">
        <FloatingActionButton className="Sets-floating-button">
          <ContentAdd/>
        </FloatingActionButton>
      </Link>
    </div>)
  }
}

const mapStateToProps = store => {
  return {sets: store.setsPage.sets}
}

export default connect(mapStateToProps)(Sets)
