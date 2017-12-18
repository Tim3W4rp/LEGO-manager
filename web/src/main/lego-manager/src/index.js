import React from 'react'
import { render } from 'react-dom'
import { Provider } from 'react-redux'
import { createStore, combineReducers, compose, applyMiddleware } from 'redux'
import { responsiveStoreEnhancer, responsiveStateReducer } from 'redux-responsive'
import promiseMiddleware from 'redux-promise-middleware'
import { responsiveDrawer } from 'material-ui-responsive-drawer'
import env from './env'

// material ui plugin needed
import injectTapEventPlugin from 'react-tap-event-plugin'

// redux forms
import { reducer as reduxFormReducer } from 'redux-form'

// router
import { Router, Route, IndexRoute, browserHistory } from 'react-router'
import { syncHistoryWithStore, routerReducer } from 'react-router-redux'

//components
import Index from './components/index/Index'
import Categories from './components/categories/Categories'
import CategoryCreate from './components/categoryCreate/CategoryCreate'
import CategoryUpdate from './components/categoryUpdate/CategoryUpdate'
import Category from './components/category/Category'
import Sets from './components/sets/Sets'
import SetCreate from './components/setCreate/SetCreate'
import Set from './components/set/Set'
import Kits from './components/kits/Kits'
import KitCreate from './components/kitCreate/KitCreate'
import Kit from './components/kit/Kit'
import SetUpdate from './components/setUpdate/SetUpdate'

// reducers
import categories from './components/categories/reducer'
import category from './components/category/reducer'
import sets from './components/sets/reducer'
import set from './components/set/reducer'
import setUpdate from './components/setUpdate/reducer'
import loading from './components/loading/reducer'
import errorToast from './components/errorToast/reducer'
import kits from './components/kits/reducer'
import kit from './components/kit/reducer'

// data loaders
import loadCategories from './components/categories/loader'
import loadCategory from './components/category/loader'
import loadSets from './components/sets/loader'
import loadSet from './components/set/loader'
import loadKit from './components/kit/loader'
import loadKits from './components/kits/loader'
import loadSetUpdate from './components/setUpdate/loader'


// elements
import NotFound from './elements/notFound/NotFound'
import App from './App'

// Needed for onTouchTap
// http://stackoverflow.com/a/34015469/988941
injectTapEventPlugin()

const composeEnhancers =
  window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

// create store
export const store = createStore(
  combineReducers({
    browser: responsiveStateReducer,
    responsiveDrawer: responsiveDrawer,
    routing: routerReducer,
    form: reduxFormReducer,
    loading: loading,
    error: errorToast,
    categoriesPage: combineReducers({
      categories,
    }),
    categoryPage: combineReducers({
      category,
    }),
    setsPage: combineReducers({
      sets,
    }),
    setPage: combineReducers({
      set,
    }),
    kitPage: combineReducers({
      kit,
    }),
    kitsPage: combineReducers({
      kits,
    }),
    setUpdatePage: combineReducers({
      setUpdate,
    })
  }),

  composeEnhancers(
    applyMiddleware(
      promiseMiddleware()
    ),
    responsiveStoreEnhancer
  )
)

// Create an enhanced history that syncs navigation events with the store
const history = syncHistoryWithStore(browserHistory, store)

// init react with store and router
render(
  <Provider store={store}>
    <Router history={history}>
      <Route path={env.PUBLIC_URL} component={App}>
        <IndexRoute component={Index}  />
        <Route path="categories" onEnter={loadCategories} component={Categories}/>
        <Route path="category/create" component={CategoryCreate} />
        <Route path="category/update/:id" onEnter={loadCategory} component={CategoryUpdate}/>
        <Route path="category/:id" onEnter={loadCategory} component={Category}/>
        <Route path="sets" onEnter={loadSets} component={Sets}/>
        <Route path="set/create" component={SetCreate}/>
        <Route path="set/:id" onEnter={loadSet} component={Set}/>
        <Route path="kits" onEnter={loadKits} component={Kits}/>
        <Route path="kit/create" component={KitCreate}/>
        <Route path="kit/:id" onEnter={loadKit} component={Kit}/>
        <Route path="set/update/:id" onEnter={loadSetUpdate} component={SetUpdate}/>
      </Route>
      <Route path="/" component={App}>
        <Route path="*" component={NotFound}/>
      </Route>
    </Router>
  </Provider>,
  document.getElementById('root')
)
