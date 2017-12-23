import client from '../../client'

export const loadCategory = (categoryId) => ({
  type: 'FETCH_CATEGORY',
  payload: client({
    url: '/categories/' + categoryId,
    method: 'GET',
  })
})

export const deleteCategory = (categoryId) => ({
  type: 'ADD_CATEGORY',
  payload: client({
    url: '/categories/' + categoryId,
    method: 'DELETE',
  })
})
