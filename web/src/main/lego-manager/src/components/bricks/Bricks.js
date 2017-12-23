import React, {Component} from 'react'
import {connect} from 'react-redux'
import {bindActionCreators} from 'redux'
import Link from '../../elements/link/Link'
import BrickElement from '../../elements/brick/Brick'
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

import './Bricks.css'
import * as actions from './actions'

class Bricks extends Component {

  componentWillMount() {
    this.props.loadBricks()
  }

  render() {
    return (<div className="Bricks">
      <Table selectable={false} onRowClick={this.handleClick}>
        <TableHeader displaySelectAll={false}>
          <TableRow>
            <TableHeaderColumn>ID</TableHeaderColumn>
            <TableHeaderColumn>Shape</TableHeaderColumn>
          </TableRow>
        </TableHeader>
        <TableBody displayRowCheckbox={false} showRowHover={true}>
          {
            this.props.bricks.data.map((brick) => <TableRow key={brick.id}>
              <TableRowColumn>
                <Link to={'/brick/' + brick.id}>
                  {brick.id}
                </Link>
              </TableRowColumn>
              <TableRowColumn>
                <Link to={'/brick/' + brick.id}>
                  <BrickElement size="40" color={'rgb(' + brick.dtoRed + ', ' + brick.dtoGreen + ', ' + brick.dtoBlue + ')'}/>
                </Link>
              </TableRowColumn>
            </TableRow>)
          }
        </TableBody>
      </Table>
      <Link to="/brick/create">
        <FloatingActionButton className="Bricks-floating-button">
          <ContentAdd/>
        </FloatingActionButton>
      </Link>
    </div>)
  }
}

export default connect(store => ({
  bricks: store.bricksPage.bricks
}), dispatch => (
  bindActionCreators({ ...actions }, dispatch)
))(Bricks)
