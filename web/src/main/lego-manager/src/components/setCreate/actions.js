import client from '../../client'

export const addSet = (setData) => ({
  type: 'ADD_CATEGORY',
  payload: client({
    url: '/sets/create',
    method: 'POST',
    data: setData
  })
})
