import client from '../../client'
import { store } from '../../index'

const load = (route) => {
  let id = route.params.id
  // load set detail
  store.dispatch({
    type: 'FETCH_SET',
    payload: client({
      url: '/sets/' + id,
      method: 'GET',
    })
  })
}


export default load
