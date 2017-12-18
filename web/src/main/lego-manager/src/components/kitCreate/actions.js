import client from '../../client'

export const addKit = (kitData) => ({
  type: 'ADD_KIT',
  payload: client({
    url: '/kits/create',
    method: 'POST',
    data: kitData
  })
})
