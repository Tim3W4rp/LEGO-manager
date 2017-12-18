import client from '../../client'

export const updateCategory = (categoryData) => ({
  type: 'UPDATE_CATEGORY',
  payload: client({
    url: '/categories/' + categoryData.id,
    method: 'PUT',
    data: categoryData
  })
})
