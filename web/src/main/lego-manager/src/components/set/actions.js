import client from '../../client'

export const removeSet = (id) => ({
  type: 'REMOVE_SET',
  payload: client({
    url: '/sets/' + id,
    method: 'DELETE'
  })
})