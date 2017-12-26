import client from '../../client'

export const loadShape = (id) => ({
  type: 'FETCH_SHAPE',
  payload: client({
    url: '/shapes/' + id,
    method: 'GET',
  })
})

export const deleteShape = (id) => ({
  type: 'ADD_SHAPE',
  payload: client({
    url: '/shapes/' + id,
    method: 'DELETE',
  })
})
