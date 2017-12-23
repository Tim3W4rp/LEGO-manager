import React, { Component } from 'react'
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider'
import getMuiTheme from 'material-ui/styles/getMuiTheme'
import baseTheme from 'material-ui/styles/baseThemes/lightBaseTheme'
import * as Colors from 'material-ui/styles/colors'
import { fade } from 'material-ui/utils/colorManipulator'

const muiTheme = getMuiTheme(baseTheme, {
  palette: {
    'primary1Color': fade(Colors.red600, 0.9),
    'primary2Color': fade(Colors.red500, 0.8),
    'primary3Color': Colors.grey700,
    'accent1Color': Colors.cyanA700
  },
  'textField': {
    'errorColor': Colors.red900,
    'focusColor': fade(Colors.red500, 0.9)
  }
})


class Theme extends Component {
  render() {
    return (
      <MuiThemeProvider muiTheme={muiTheme}>
        <div>
          {this.props.children}
        </div>
      </MuiThemeProvider>
    )
  }
}

export default Theme;
