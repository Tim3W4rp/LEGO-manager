import client from '../../client'

export const loadBrick = (id) => ({
  type: 'FETCH_BRICK',
  payload: client({
    url: '/bricks/' + id,
    method: 'GET'
  })
})

export const deleteBrick = (brickId) => ({
  type: 'ADD_BRICK',
  payload: client({
    url: '/bricks/' + brickId,
    method: 'DELETE'
  })
})
