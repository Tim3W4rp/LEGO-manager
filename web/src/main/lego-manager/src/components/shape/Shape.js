import React, {Component} from 'react'
import {connect} from 'react-redux'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'


import './Shape.css'

class Shape extends Component {
    render() {
        return (
            <Paper className="Shape" zDepth={1}>
                <div className="Shape-label">Shape {this.props.shape.id}</div>
                <Divider/>
                <div className="Shape-title">{this.props.shape.name}</div>
            </Paper>
        )
    }
}

const mapStateToProps = store => {
    return {shape: store.shapePage.shape}
}

export default connect(mapStateToProps)(Shape)
