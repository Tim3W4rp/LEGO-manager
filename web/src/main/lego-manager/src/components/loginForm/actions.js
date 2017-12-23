import client from '../../client'

export const signIn = (userData) => ({
  type: 'SIGN_IN',
  payload: client({
    url: '/login',
    method: 'POST',
    data: userData
  })
})
