import client from '../../client'
import { store } from '../../index'

const load = (route) => {
    let id = route.params.id
    // load brick detail
    store.dispatch({
        type: 'FETCH_BRICK',
        payload: client({
            url: '/bricks/' + id,
            method: 'GET',
        })
    })
}


export default load
