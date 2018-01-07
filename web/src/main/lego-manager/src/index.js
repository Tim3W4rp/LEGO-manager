import React from 'react'
import {render} from 'react-dom'
import {Provider} from 'react-redux'
import {createStore, combineReducers, compose, applyMiddleware} from 'redux'
import {responsiveStoreEnhancer, responsiveStateReducer} from 'redux-responsive'
import promiseMiddleware from 'redux-promise-middleware'
import {responsiveDrawer} from 'material-ui-responsive-drawer'
import env from './env'

// material ui plugin needed
import injectTapEventPlugin from 'react-tap-event-plugin'

// redux forms
import {reducer as reduxFormReducer} from 'redux-form'

// router
import {Router, Route, IndexRoute, browserHistory} from 'react-router'
import {syncHistoryWithStore, routerReducer} from 'react-router-redux'

//components
import Authentification from './components/authentification/Authentification'
import Index from './components/index/Index'
import Categories from './components/categories/Categories'
import CategoryCreate from './components/categoryCreate/CategoryCreate'
import CategoryUpdate from './components/categoryUpdate/CategoryUpdate'
import Category from './components/category/Category'
import Sets from './components/sets/Sets'
import SetCreate from './components/setCreate/SetCreate'
import SetUpdate from './components/setUpdate/SetUpdate'
import Set from './components/set/Set'
import Bricks from './components/bricks/Bricks'
import BrickCreate from './components/brickCreate/BrickCreate'
import BrickUpdate from './components/brickUpdate/BrickUpdate'
import Brick from './components/brick/Brick'
import Shapes from './components/shapes/Shapes'
import ShapeCreate from './components/shapeCreate/ShapeCreate'
import ShapeUpdate from './components/shapeUpdate/ShapeUpdate'
import Shape from './components/shape/Shape'
import Kits from './components/kits/Kits'
import KitCreate from './components/kitCreate/KitCreate'
import KitCreateRandom from './components/kitCreateRandom/KitCreateRandom'
import Kit from './components/kit/Kit'

// reducers
import authentification from './components/authentification/reducer'
import categories from './components/categories/reducer'
import category from './components/category/reducer'
import sets from './components/sets/reducer'
import setReducer from './components/set/reducer'
import bricks from './components/bricks/reducer'
import brick from './components/brick/reducer'
import shapes from './components/shapes/reducer'
import shape from './components/shape/reducer'
import setUpdate from './components/setUpdate/reducer'
import loading from './components/loading/reducer'
import errorToast from './components/errorToast/reducer'
import kits from './components/kits/reducer'
import kit from './components/kit/reducer'

// elements
import NotFound from './elements/notFound/NotFound'
import App from './App'
import Theme from './Theme'

// Needed for onTouchTap
// http://stackoverflow.com/a/34015469/988941
injectTapEventPlugin()

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

// create store
export const store = createStore(combineReducers({
  browser: responsiveStateReducer,
  responsiveDrawer: responsiveDrawer,
  routing: routerReducer,
  form: reduxFormReducer,
  loading: loading,
  error: errorToast,
  user: authentification,
  categoriesPage: combineReducers({categories}),
  categoryPage: combineReducers({category}),
  setsPage: combineReducers({sets}),
  setPage: combineReducers({set: setReducer}),
  bricksPage: combineReducers({bricks}),
  brickPage: combineReducers({brick}),
  shapesPage: combineReducers({shapes}),
  shapePage: combineReducers({shape}),
  kitPage: combineReducers({kit}),
  kitsPage: combineReducers({kits}),
  setUpdatePage: combineReducers({setUpdate})
}), composeEnhancers(applyMiddleware(promiseMiddleware()), responsiveStoreEnhancer))

// Create an enhanced history that syncs navigation events with the store
const history = syncHistoryWithStore(browserHistory, store)

// init react with store and router
render(<Theme>
  <Provider store={store}>
    <Router history={history}>
      <Route component={Authentification}>
        <Route path={env.PUBLIC_URL} component={App}>
          <IndexRoute component={Index}/>
          <Route path="categories" component={Categories}/>
          <Route path="category/create" component={CategoryCreate}/>
          <Route path="category/update/:id" component={CategoryUpdate}/>
          <Route path="category/:id" component={Category}/>
          <Route path="sets" component={Sets}/>
          <Route path="set/create" component={SetCreate}/>
          <Route path="set/:id" component={Set}/>
          <Route path="set/update/:id" component={SetUpdate}/>
          <Route path="bricks" component={Bricks}/>
          <Route path="brick/create" component={BrickCreate}/>
          <Route path="brick/update/:id" component={BrickUpdate}/>
          <Route path="brick/:id" component={Brick}/>
          <Route path="shapes" component={Shapes}/>
          <Route path="shape/create" component={ShapeCreate}/>
          <Route path="shape/update/:id" component={ShapeUpdate}/>
          <Route path="shape/:id" component={Shape}/>
          <Route path="kits" component={Kits}/>
          <Route path="kit/create" component={KitCreate}/>
          <Route path="kit/createrandom" component={KitCreateRandom}/>
          <Route path="kit/:id" component={Kit}/>
        </Route>
        <Route path="/" component={App}>
          <Route path="*" component={NotFound}/>
        </Route>
      </Route>
    </Router>
  </Provider>
</Theme>, document.getElementById('root'))
