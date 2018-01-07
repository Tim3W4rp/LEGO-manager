import React, { Component } from 'react'
import { connect } from 'react-redux'
import {bindActionCreators} from 'redux'

import Link from '../../elements/link/Link'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton'

import {
    Table,
    TableBody,
    TableHeader,
    TableHeaderColumn,
    TableRow,
    TableRowColumn
} from 'material-ui/Table'

import * as actions from './actions'
import './Set.css'

class Set extends Component {

  componentWillMount() {
    this.props.loadSet(this.props.routeParams.id)
  }

  delete() {
      this.props.deleteSet(this.props.set.id).then(r => (Link.redirect('/sets/')))
  }

  render() {
    const { handleSubmit } = this.props
    let kits = this.props.set.kits ? this.props.set.kits : []
    return (
      <Paper className="Set" zDepth={1}>

        <form className="SetCreate-form" onSubmit={vals => handleSubmit(this.submit(this.props.set.id))}>
          <Link to={'/set/update/' + this.props.set.id}>
            <RaisedButton className="Set-update">Update</RaisedButton>
          </Link>
            <RaisedButton className="Set-delete" onClick={e => this.delete()}>Delete</RaisedButton>
        </form>
        <div className="Set-label">Set {this.props.set.id}</div>
        <Divider />
        <div className="Set-title">{this.props.set.description}</div>
        <div className="Set-description">Price: {this.props.set.price}</div>

        <Divider />
        <div className="Set-kits">Kits in this set</div>

        <Table className="Set-table" selectable={false} onRowClick={this.handleClick}>
          <TableHeader displaySelectAll={false}>
            <TableRow>
                      <TableHeaderColumn>ID</TableHeaderColumn>
                      <TableHeaderColumn>Name</TableHeaderColumn>
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
  set: store.setPage.set
}), dispatch => bindActionCreators({
  ...actions
}, dispatch))(Set)
