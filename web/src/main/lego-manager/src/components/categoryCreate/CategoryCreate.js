import React, { Component } from 'react';

import Paper from 'material-ui/Paper';
import Divider from 'material-ui/Divider';

import { TextField } from 'redux-form-material-ui'
import { Field, reduxForm } from 'redux-form'

import './CategoryCreate.css';

class CategoryCreate extends Component {
  render() {
    return (
      <Paper className="CategoryCreate" zDepth={1}>
        <div className="CategoryCreate-label">Create new category</div>

        <Divider />

        <form className="CategoryCreate-form">
          <Field className="CategoryCreate-item" name="name" component={TextField} hintText="Name"/>
          <Field className="CategoryCreate-item" name="description" component={TextField} hintText="description" multiLine={true}/>
        </form>
      </Paper>
    );
  }
}

CategoryCreate = reduxForm({
  form: 'categoryCreate'
})(CategoryCreate)

export default CategoryCreate
