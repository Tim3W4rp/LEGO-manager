import client from '../../client'

export const loadKit = (id) => ({
  type: 'FETCH_KIT',
  payload: client({
    url: '/kits/' + id,
    method: 'GET',
  })
})
