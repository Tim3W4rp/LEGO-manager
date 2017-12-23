import React, {Component} from 'react'
import {connect} from 'react-redux'
import {bindActionCreators} from 'redux'

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
import * as actions from './actions'


class Sets extends Component {

  componentWillMount() {
    this.props.loadSets()
  }

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


export default connect(store => ({
  sets: store.setsPage.sets
}), dispatch => bindActionCreators({
  ...actions
}, dispatch))(Sets)
