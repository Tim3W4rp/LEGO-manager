import React, { Component } from 'react'
import { connect } from 'react-redux'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'
import BrickElement from '../../elements/brick/Brick'


import './Brick.css'

class Brick extends Component {
    render() {
        let brick = this.props.brick
        return (
            <Paper className="Brick" zDepth={1}>
                <div className="Brick-label">Brick {brick.id}</div>
                <Divider />
                <BrickElement size="40" color={'rgb(' + brick.dtoRed + ', ' + brick.dtoGreen + ', ' + brick.dtoBlue + ')'}/>
            </Paper>
        )
    }
}

const mapStateToProps = store => {
    return {brick: store.brickPage.brick}
}

export default connect(mapStateToProps)(Brick)
