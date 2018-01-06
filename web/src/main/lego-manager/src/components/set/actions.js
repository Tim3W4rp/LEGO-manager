import client from '../../client'

export const loadSet = (id) => ({
  type: 'FETCH_SET',
  payload: client({
    url: '/sets/' + id,
    method: 'GET',
  })
})

export const deleteSet = (id) => ({
  type: 'ADD_SET',
  payload: client({
    url: '/sets/' + id,
    method: 'DELETE'
  })
})
