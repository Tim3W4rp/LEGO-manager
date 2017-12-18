import client from '../../client'

export const updateBrick = (brickData) => ({
    type: 'UPDATE_BRICK',
    payload: client({
        url: '/bricks/' + brickData.id,
        method: 'PUT',
        data: brickData
    })
})