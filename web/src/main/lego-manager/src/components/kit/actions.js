import client from '../../client'

export const loadKit = (id) => ({
  type: 'FETCH_KIT',
  payload: client({
    url: '/kits/' + id,
    method: 'GET',
  })
})

export const loadSimilarKits = (id) => ({
    type: 'FETCH_KIT',
    payload: client({
      url: '/kits/' + id,
      method: 'GET',
    })
})

export const deleteKit = (id) => ({
  type: 'ADD_KIT',
  payload: client({
    url: '/kits/' + id,
    method: 'DELETE',
  })
})
