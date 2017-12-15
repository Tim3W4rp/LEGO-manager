import React, { Component } from 'react'
import { connect } from 'react-redux'

import Paper from 'material-ui/Paper'
import Divider from 'material-ui/Divider'


import './Category.css'

class Category extends Component {
  render() {
    return (
      <Paper className="Category" zDepth={1}>
        <div className="Category-label">Category {this.props.category.id}</div>
        <Divider />
        <div className="Category-title">{this.props.category.name}</div>
        <div className="Category-description">{this.props.category.description}</div>
      </Paper>
    )
  }
}

const mapStateToProps = store => {
  return {category: store.categoryPage.category}
}

export default connect(mapStateToProps)(Category)
