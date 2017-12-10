import client from '../../client'
import { store } from '../../index'

const load = (route) => {
  // load categories
  store.dispatch({
    type: 'FETCH_CATEGORIES',
    payload: client({
      url: '/categories',
      method: 'GET',
    })
  })
}

export default load
