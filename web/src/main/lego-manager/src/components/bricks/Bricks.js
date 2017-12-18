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

import './Bricks.css'

class Bricks extends Component {
    render() {
        return (<div className="Bricks">
            <Table selectable={false} onRowClick={this.handleClick}>
                <TableHeader displaySelectAll={false}>
                    <TableRow>
                        <TableHeaderColumn>ID</TableHeaderColumn>
                        <TableHeaderColumn>Shape</TableHeaderColumn>
                        <TableHeaderColumn>Red</TableHeaderColumn>
                        <TableHeaderColumn>Green</TableHeaderColumn>
                        <TableHeaderColumn>Blue</TableHeaderColumn>
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
                                    {brick.shape}
                                </Link>
                            </TableRowColumn>
                            <TableRowColumn>
                                <Link to={'/brick/' + brick.id}>
                                    {brick.red}
                                </Link>
                            </TableRowColumn>
                            <TableRowColumn>
                                <Link to={'/brick/' + brick.id}>
                                    {brick.green}
                                </Link>
                            </TableRowColumn>
                            <TableRowColumn>
                                <Link to={'/brick/' + brick.id}>
                                    {brick.blue}
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

const mapStateToProps = store => {
    return {bricks: store.bricksPage.bricks}
}

export default connect(mapStateToProps)(Bricks)
