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

import './Kits.css'

class Kits extends Component {
  render() {
    return (<div className="Kits">
      <Table selectable={false} onRowClick={this.handleClick}>
        <TableHeader displaySelectAll={false}>
          <TableRow>
            <TableHeaderColumn>ID</TableHeaderColumn>
            <TableHeaderColumn>Name</TableHeaderColumn>
            <TableHeaderColumn>Price</TableHeaderColumn>
            <TableHeaderColumn>Age Limit</TableHeaderColumn>
            <TableHeaderColumn>Category</TableHeaderColumn>
          </TableRow>
        </TableHeader>
        <TableBody displayRowCheckbox={false} showRowHover={true}>
          {
            this.props.kits.data.map((kit) => <TableRow key={kit.id}>
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
              <TableRowColumn>
                <Link to={'/kit/' + kit.id}>
                  {kit.category}
                </Link>
              </TableRowColumn>
            </TableRow>)
          }
        </TableBody>
      </Table>
      <Link to="/kit/create">
        <FloatingActionButton className="Kits-floating-button">
          <ContentAdd/>
        </FloatingActionButton>
      </Link>
    </div>)
  }
}

const mapStateToProps = store => {
  return {kits: store.kitsPage.kits}
}

export default connect(mapStateToProps)(Kits)
