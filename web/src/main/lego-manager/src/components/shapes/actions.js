import client from '../../client'

export const loadShapes = () => ({
  type: 'FETCH_SHAPES',
  payload: client({
    url: '/shapes',
    method: 'GET'
  })
})
