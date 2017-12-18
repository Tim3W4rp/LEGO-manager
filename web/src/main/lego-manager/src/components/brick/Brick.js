import React, { Component } from 'react'
import { connect } from 'react-redux'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'


import './Brick.css'

class Brick extends Component {
    render() {
        return (
            <Paper className="Brick" zDepth={1}>
                <div className="Brick-label">Brick {this.props.brick.id}</div>
                <Divider />
                <div className="Brick-title">{this.props.brick.shape}</div>
                <div className="Brick-description">{this.props.brick.red}</div>
                <div className="Brick-description">{this.props.brick.green}</div>
                <div className="Brick-description">{this.props.brick.blue}</div>
            </Paper>
        )
    }
}

const mapStateToProps = store => {
    return {brick: store.brickPage.brick}
}

export default connect(mapStateToProps)(Brick)
