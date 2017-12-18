import client from '../../client'
import { store } from '../../index'

const load = (route) => {
    let id = route.params.id
    // load shape detail
    store.dispatch({
        type: 'FETCH_SHAPE',
        payload: client({
            url: '/shapes/' + id,
            method: 'GET',
        })
    })
}


export default load
