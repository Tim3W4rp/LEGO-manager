import client from '../../client'

export const deleteCategory = (categoryId) => ({
  type: 'ADD_CATEGORY',
  payload: client({
    url: '/categories/' + categoryId,
    method: 'DELETE',
  })
})
