import React, { Component } from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import getMuiTheme from 'material-ui/styles/getMuiTheme';
import { blue500 } from 'material-ui/styles/colors';
import { ResponsiveDrawer,  BodyContainer, ResponsiveAppBar } from 'material-ui-responsive-drawer'

import Menu from './elements/menu/Menu'
import './App.css';

const muiTheme = getMuiTheme({
  palette: {
    primary1Color: blue500,
  },
});

class App extends Component {
  render() {
    return (
      <div className="App">
        <MuiThemeProvider muiTheme={muiTheme}>
          <div>
            <ResponsiveDrawer>
              <Menu />
            </ResponsiveDrawer>
            <BodyContainer>
              <ResponsiveAppBar
                  title={'Lego Manager'} />
              <div className="App-content">
                {this.props.children}
              </div>
            </BodyContainer>
          </div>
        </MuiThemeProvider>
      </div>
    )
  }
}

export default App;
