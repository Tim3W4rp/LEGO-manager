import client from '../../client'

export const loadSet = (id) => ({
  type: 'FETCH_SET',
  payload: client({
    url: '/sets/' + id,
    method: 'GET',
  })
})

export const removeSet = (id) => ({
  type: 'REMOVE_SET',
  payload: client({
    url: '/sets/' + id,
    method: 'DELETE'
  })
})
