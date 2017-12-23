import client from '../../client'

export const loadSets = (route) => ({
  type: 'FETCH_SETS',
  payload: client({
    url: '/sets',
    method: 'GET',
  })
})
