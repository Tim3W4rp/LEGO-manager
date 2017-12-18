import client from '../../client'
import { store } from '../../index'

const load = (route) => {
    // load shapes
    store.dispatch({
        type: 'FETCH_SHAPES',
        payload: client({
            url: '/shapes',
            method: 'GET',
        })
    })
}

export default load
