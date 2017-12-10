import React from 'react'
import { render } from 'react-dom'
import { Provider } from 'react-redux'
import { createStore, combineReducers, compose, applyMiddleware } from 'redux'
import { responsiveStoreEnhancer, responsiveStateReducer } from 'redux-responsive';
import promiseMiddleware from 'redux-promise-middleware'
import { responsiveDrawer } from 'material-ui-responsive-drawer';
// material ui plugin needed
import injectTapEventPlugin from 'react-tap-event-plugin';

import { Router, Route, IndexRoute, browserHistory } from 'react-router'
import { syncHistoryWithStore, routerReducer } from 'react-router-redux'

import App from './App'

//components
import Index from './components/index/Index'
import Categories from './components/categories/Categories'
import Category from './components/category/Category'

// reducers
import categories from './components/categories/reducer'
import category from './components/category/reducer'

// data loaders
import loadCategories from './components/categories/loader'
import loadCategory from './components/category/loader'

// elements
import NotFound from './elements/notFound/NotFound'

// Needed for onTouchTap
// http://stackoverflow.com/a/34015469/988941
injectTapEventPlugin();

// create store
export const store = createStore(
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

  compose(
    applyMiddleware(
      promiseMiddleware()
    ),
    responsiveStoreEnhancer,
    // redux browser web extension support
    window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
  )
)

// Create an enhanced history that syncs navigation events with the store
const history = syncHistoryWithStore(browserHistory, store)

// init react with store and router
render(
  <Provider store={store}>
    <Router history={history}>
      <Route path={process.env.PUBLIC_URL} component={App}>
        <IndexRoute component={Index}  />
        <Route path="categories" onEnter={loadCategories} component={Categories}/>
        <Route path="category/:id" onEnter={loadCategory} component={Category}/>
      </Route>
      <Route path="/" component={App}>
        <Route path="*" component={NotFound}/>
      </Route>
    </Router>
  </Provider>,
  document.getElementById('root')
)
