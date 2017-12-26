import client from '../../client'
import {store} from '../../index'

export const loadBricks = () => ({
  type: 'FETCH_BRICKS',
  payload: client({
    url: '/bricks',
    method: 'GET'
  })
})
