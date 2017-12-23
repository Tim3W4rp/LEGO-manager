import client from '../../client'

export const addShape = (shapeData) => ({
  type: 'ADD_SHAPE',
  payload: client({
    url: '/shapes/create',
    method: 'POST',
    data: shapeData
  })
})
