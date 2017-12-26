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

import * as actions from './actions'
import './Shapes.css'

class Shapes extends Component {

  componentWillMount() {
    this.props.loadShapes()
  }

  render() {
    return (<div className="Shapes">
      <Table selectable={false} onRowClick={this.handleClick}>
        <TableHeader displaySelectAll={false}>
          <TableRow>
            <TableHeaderColumn>ID</TableHeaderColumn>
            <TableHeaderColumn>Name</TableHeaderColumn>
          </TableRow>
        </TableHeader>
        <TableBody displayRowCheckbox={false} showRowHover={true}>
          {
            this.props.shapes.data.map((shape) => <TableRow key={shape.id}>
              <TableRowColumn>
                <Link to={'/shape/' + shape.id}>
                  {shape.id}
                </Link>
              </TableRowColumn>
              <TableRowColumn>
                <Link to={'/shape/' + shape.id}>
                  {shape.name}
                </Link>
              </TableRowColumn>
            </TableRow>)
          }
        </TableBody>
      </Table>
      <Link to="/shape/create">
        <FloatingActionButton className="Shapes-floating-button">
          <ContentAdd/>
        </FloatingActionButton>
      </Link>
    </div>)
  }
}

export default connect(store => ({
  shapes: store.shapesPage.shapes
}), dispatch => bindActionCreators({
  ...actions
}, dispatch))(Shapes)
