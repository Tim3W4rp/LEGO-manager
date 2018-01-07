import client from '../../client'

export const addKit = (kitData) => ({
  type: 'ADD_KIT_RANDOM',
  payload: client({
    url: '/kits/createrandom',
    method: 'POST',
    data: kitData
  })
})
