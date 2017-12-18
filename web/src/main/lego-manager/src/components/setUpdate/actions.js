import client from '../../client'

export const updateSet = (setData) => ({
  type: 'UPDATE_SET',
  payload: client({
    url: '/sets/' + setData.id,
    method: 'PUT',
    data: setData
  })
})