import React, { Component } from 'react'
import { ResponsiveDrawer,  BodyContainer, ResponsiveAppBar } from 'material-ui-responsive-drawer'
import muiThemeable from 'material-ui/styles/muiThemeable';

import ErrorToast from './components/errorToast/ErrorToast'
import Menu from './elements/menu/Menu'
import Brick from './elements/brick/Brick'
import Link from './elements/link/Link'
import './App.css'


class App extends Component {
  render() {
    return (
      <div className='App'>
        <ResponsiveDrawer>
          <Menu />
        </ResponsiveDrawer>
        <BodyContainer>
          <ResponsiveAppBar
            title={
              <div className='App-header'>
                <Link className='App-header' to='/'>
                  <div className='App-logo'><Brick size={44} color={this.props.muiTheme.palette.accent2Color} /></div>
                  <div className='App-title' style={{color: this.props.muiTheme.palette.accent2Color}}>Lego Manager</div>
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
    )
  }
}

export default muiThemeable()(App);
