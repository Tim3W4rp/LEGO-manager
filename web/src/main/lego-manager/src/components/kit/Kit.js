import React, {Component} from 'react'
import {connect} from 'react-redux'
import {bindActionCreators} from 'redux'

import Link from '../../elements/link/Link'
import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton';
import * as KitActions from './actions'
import BrickElement from '../../elements/brick/Brick'
import {
    Table,
    TableBody,
    TableHeader,
    TableHeaderColumn,
    TableRow,
    TableRowColumn
} from 'material-ui/Table'
import './Kit.css'

class Kit extends Component {

    componentWillMount() {
        this.props.loadKit(this.props.routeParams.id)
        this.props.loadSimilarKits(this.props.routeParams.id)
    }

    reload() {
      setTimeout(() => this.componentWillMount(), 0)
    }

    delete() {
        this.props.deleteKit(this.props.kit.id)
            .then(r => (
                Link.redirect('/kits/')
            ))
    }

    render() {
        let kitBricks = this.props.kit.kitBricks ? this.props.kit.kitBricks : []
        let similarKits = this.props.kit.similarKits ? this.props.kit.similarKits: []
        return (
            <Paper className="Kit" zDepth={1}>
                <div className="Kit-label">Kit {this.props.kit.id}</div>
                <Divider/>
                <div className="Kit-title">{this.props.kit.description}</div>
                <div className="Kit-price">Price: {this.props.kit.price},-</div>
                <div className="Kit-ageLimit">Age limit: {this.props.kit.ageLimit}</div>
                <div className="Kit-category">Category: {
                    this.props.kit.category ?
                      <Link to={'/category/' +  this.props.kit.categoryId}>{this.props.kit.category.name}</Link>
                      : ""
                    }
                </div><br></br>
                <Link to={'/kit/update/' + this.props.kit.id}>
                    <RaisedButton
                        className="Kit-update">Update</RaisedButton>
                </Link>

                <RaisedButton
                    className="Kit-delete"
                    onClick={e => this.delete()}>Delete</RaisedButton>

                <Divider/>

                <div className="Kit-bricks">Bricks in this kit</div>

                <Table className="Bricks-table" selectable={false} onRowClick={this.handleClick}>
                    <TableHeader displaySelectAll={false}>
                        <TableRow>
                            <TableHeaderColumn>ID</TableHeaderColumn>
                            <TableHeaderColumn></TableHeaderColumn>
                            <TableHeaderColumn>Shape</TableHeaderColumn>
                            <TableHeaderColumn>Count</TableHeaderColumn>
                        </TableRow>
                    </TableHeader>
                    <TableBody displayRowCheckbox={false} showRowHover={true}>
                        {
                            kitBricks.map((kitBrick) => {
                                return <TableRow key={kitBrick.brick.id + 'kit'}>
                                    <TableRowColumn>
                                        <Link to={'/brick/' + kitBrick.brick.id}>
                                            {kitBrick.brick.id}
                                        </Link>
                                    </TableRowColumn>
                                    <TableRowColumn>
                                      <Link to={'/brick/' + kitBrick.brick.id}>
                                        <BrickElement size="40" color={'rgb(' + kitBrick.brick.red + ', ' + kitBrick.brick.green + ', ' + kitBrick.brick.blue + ')'}/>
                                      </Link>
                                    </TableRowColumn>
                                    <TableHeaderColumn>
                                      <Link to={'/brick/' + kitBrick.brick.id}>
                                        {kitBrick.brick.shape.name}
                                      </Link>
                                    </TableHeaderColumn>
                                    <TableRowColumn>
                                      <Link to={'/brick/' + kitBrick.brick.id}>
                                        {kitBrick.count}
                                      </Link>
                                    </TableRowColumn>
                                </TableRow>
                            })}
                    </TableBody>
                </Table>

                <div className="Kit-bricks">Kits similar to this kit</div>

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
                          similarKits.map((kit) => {
                            return (<TableRow key={kit.id}>
                              <TableRowColumn>
                                <Link onClick={e => this.reload()} to={'/kit/' + kit.id}>
                                  {kit.id}
                                </Link>
                              </TableRowColumn>
                              <TableRowColumn>
                                <Link onClick={e => this.reload()} to={'/kit/' + kit.id}>
                                  {kit.description}
                                </Link>
                              </TableRowColumn>
                              <TableRowColumn>
                                <Link onClick={e => this.reload()} to={'/kit/' + kit.id}>
                                  {kit.price}
                                </Link>
                              </TableRowColumn>
                              <TableRowColumn>
                                <Link onClick={e => this.reload()} to={'/kit/' + kit.id}>
                                  {kit.ageLimit}
                                </Link>
                              </TableRowColumn>
                              <TableRowColumn>
                                <Link onClick={e => this.reload()} to={'/category/' + kit.category.id}>
                                  {kit.category.name}
                                </Link>
                              </TableRowColumn>
                            </TableRow>)
                          })
                        }
                    </TableBody>
                </Table>

            </Paper>
        )
    }
}

const mapStateToProps = store => {
    return {kit: store.kitPage.kit}
}

export default connect(store => ({
    kit: store.kitPage.kit
}), dispatch => bindActionCreators({
    ...KitActions
}, dispatch))(Kit)
