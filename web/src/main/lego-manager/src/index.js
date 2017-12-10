import React from 'react'
import { render } from 'react-dom'
import { Provider } from 'react-redux'
import { createStore, combineReducers, compose } from 'redux'
import { responsiveStoreEnhancer, responsiveStateReducer } from 'redux-responsive';
import {responsiveDrawer} from 'material-ui-responsive-drawer';
// material ui plugin needed
import injectTapEventPlugin from 'react-tap-event-plugin';

import { Router, Route, browserHistory } from 'react-router'
import { syncHistoryWithStore, routerReducer } from 'react-router-redux'

import App from './App'

//pages
import Categories from './pages/categories/Categories'
import Category from './pages/category/Category'

// reducers
import categories from './pages/categories/reducer.js'
import category from './pages/category/reducer.js'

// Needed for onTouchTap
// http://stackoverflow.com/a/34015469/988941
injectTapEventPlugin();


// create store
let store = createStore(
  combineReducers({
    browser: responsiveStateReducer,
    responsiveDrawer: responsiveDrawer,
    routing: routerReducer,
    categoriesPage: combineReducers({
      categories,
    }),
    categoryPage: combineReducers({
      category,
    })
  }),
  // redux browser web extension support
  compose(
    responsiveStoreEnhancer,
    window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
  )
)

// Create an enhanced history that syncs navigation events with the store
const history = syncHistoryWithStore(browserHistory, store)

// init react with store and router
render(
  <Provider store={store}>
    <Router history={history}>
      <Route path="/pa165/" component={App}>
        <Route path="categories" component={Categories}/>
        <Route path="category/:id" component={Category}/>
      </Route>
    </Router>
  </Provider>,
  document.getElementById('root')
)
