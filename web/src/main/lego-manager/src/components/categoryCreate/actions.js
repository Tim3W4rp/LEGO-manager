import client from '../../client'

export const addCategory = (categoryData) => ({
  type: 'ADD_CATEGORY',
  payload: client({
    url: '/categories/',
    method: 'POST',
    data: categoryData
  })
})
