import client from '../../client'

export const loadKits = () => ({
  type: 'FETCH_KITS',
  payload: client({
    url: '/kits',
    method: 'GET',
  })
})
