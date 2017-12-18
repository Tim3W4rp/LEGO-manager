import client from '../../client'

export const deleteBrick = (brickId) => ({
    type: 'ADD_BRICK',
    payload: client({
        url: '/bricks/' + brickId,
        method: 'DELETE',
    })
})