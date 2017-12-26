
import client from '../../client'
import { store } from '../../index'

export const loadCategories = (route) => ({
    type: 'FETCH_CATEGORIES',
    payload: client({
      url: '/categories/',
      method: 'GET',
    })
  })
