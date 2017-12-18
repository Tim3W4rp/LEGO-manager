import client from '../../client'
import { store } from '../../index'

const load = (route) => {
  // load sets
  store.dispatch({
    type: 'FETCH_SETS',
    payload: client({
      url: '/sets',
      method: 'GET',
    })
  })
}

export default load
