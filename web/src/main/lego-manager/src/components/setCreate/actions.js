import client from '../../client'

export const addSet = (setData) => ({
  type: 'ADD_SET',
  payload: client({
    url: '/sets/create',
    method: 'POST',
    data: setData
  })
})
