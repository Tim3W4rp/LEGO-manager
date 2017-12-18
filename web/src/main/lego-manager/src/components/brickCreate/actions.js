import client from '../../client'

export const addBrick = (brickData) => ({
    type: 'ADD_BRICK',
    payload: client({
        url: '/bricks/create',
        method: 'POST',
        data: brickData
    })
})