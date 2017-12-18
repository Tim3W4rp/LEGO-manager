import client from '../../client'
import { store } from '../../index'

const load = (route) => {
    // load bricks
    store.dispatch({
        type: 'FETCH_BRICKS',
        payload: client({
            url: '/bricks',
            method: 'GET',
        })
    })
}

export default load
