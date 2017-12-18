import client from '../../client'
import { store } from '../../index'

const load = (route) => {
  // load kits
  store.dispatch({
    type: 'FETCH_KITS',
    payload: client({
      url: '/kits',
      method: 'GET',
    })
  })
}

export default load
