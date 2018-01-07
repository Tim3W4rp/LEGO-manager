import client from '../../client'

export const updateKit = (kitData) => ({
    type: 'UPDATE_KIT',
    payload: client({
        url: '/kits/' + kitData.id,
        method: 'PUT',
        data: kitData
    })
})
