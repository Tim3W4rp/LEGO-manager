import client from '../../client'

export const addCategory = (categoryData) => ({
  type: 'ADD_CATEGORY',
  payload: client({
    url: '/categories/create',
    method: 'POST',
    data: categoryData
  })
})
