import client from '../../client'
import { store } from '../../index'

const load = (route) => {
  let id = route.params.id
  // load category detail
  store.dispatch({
    type: 'FETCH_CATEGORY',
    payload: client({
      url: '/categories/' + id,
      method: 'GET',
    })
  })
}


export default load
