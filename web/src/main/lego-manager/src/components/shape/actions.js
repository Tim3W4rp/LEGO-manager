import client from '../../client'

export const deleteShape = (shapeId) => ({
    type: 'ADD_SHAPE',
    payload: client({
        url: '/shapes/' + shapeId,
        method: 'DELETE',
    })
})