import React, {Component} from 'react'
import {connect} from 'react-redux'
import {bindActionCreators} from 'redux'

import Link from '../../elements/link/Link'
import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import RaisedButton from 'material-ui/RaisedButton';
import * as KitActions from './actions'
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
    }

    delete() {
        this.props.deleteKit(this.props.kit.id)
            .then(r => (
                Link.redirect('/kits/')
            ))
    }

    render() {
        let bricks = this.props.bricks ? this.props.bricks : []
        return (
            <Paper className="Kit" zDepth={1}>
                <div className="Kit-label">Kit {this.props.kit.id}</div>
                <Divider/>
                <div className="Kit-title">{this.props.kit.description}</div>
                <div className="Kit-price">Price: {this.props.kit.price},-</div>
                <div className="Kit-ageLimit">Age limit: {this.props.kit.ageLimit}</div>
                <div className="Kit-category">Category: {this.props.kit.categoryId}</div><br></br>
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
                            <TableHeaderColumn>Shape</TableHeaderColumn>
                        </TableRow>
                    </TableHeader>
                    <TableBody displayRowCheckbox={false} showRowHover={true}>
                        {
                            bricks.map((brick) => (
                                <TableRow key={brick.id}>
                                    <TableRowColumn>
                                        <Link to={'/brick/' + brick.id}>
                                            {brick.id}
                                        </Link>
                                    </TableRowColumn>
                                    <TableRowColumn>
                                        <Link to={'/brick/' + brick.id}>
                                            {brick.shape}
                                        </Link>
                                    </TableRowColumn>
                                </TableRow>
                            ))}
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
