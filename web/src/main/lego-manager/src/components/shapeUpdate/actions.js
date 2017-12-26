import client from '../../client'

export const updateShape = (shapeData) => ({
    type: 'UPDATE_SHAPE',
    payload: client({
        url: '/shapes/' + shapeData.id,
        method: 'PUT',
        data: shapeData
    })
})
