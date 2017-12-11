import React, { Component } from 'react'
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider'
import getMuiTheme from 'material-ui/styles/getMuiTheme'
import baseTheme from 'material-ui/styles/baseThemes/lightBaseTheme'
import * as Colors from 'material-ui/styles/colors'
import { fade } from 'material-ui/utils/colorManipulator'
import { ResponsiveDrawer,  BodyContainer, ResponsiveAppBar } from 'material-ui-responsive-drawer'

import ErrorToast from './components/errorToast/ErrorToast'
import Menu from './elements/menu/Menu'
import Brick from './elements/brick/Brick'
import Link from './elements/link/Link'
import './App.css'

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


class App extends Component {
  render() {
    return (
      <div className='App'>
        <MuiThemeProvider muiTheme={muiTheme}>
          <div>
            <ResponsiveDrawer>
              <Menu />
            </ResponsiveDrawer>
            <BodyContainer>
              <ResponsiveAppBar
                title={
                  <div className='App-header'>
                    <Link className='App-header' to='/'>
                      <div className='App-logo'><Brick size={44} color={muiTheme.palette.accent2Color} /></div>
                      <div className='App-title' style={{color: muiTheme.palette.accent2Color}}>Lego Manager</div>
                    </Link>
                  </div>
                }
              />

              <div className='App-content'>
                {this.props.children}
              </div>
            </BodyContainer>
            <ErrorToast />
          </div>
        </MuiThemeProvider>
      </div>
    )
  }
}

export default App;
