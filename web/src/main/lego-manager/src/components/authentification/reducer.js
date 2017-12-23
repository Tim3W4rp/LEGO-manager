import { get_user, set_user, remove_user } from './storedUser'

const reducer = (state = get_user(), action) => {
  switch (action.type) {
    case 'SIGN_IN_FULFILLED':
      return {
        ...state,
        ...decodeUser(action.payload.data.token)
      }
    case 'FETCH_ERROR':
      try {
        if (action.error.response.status === 401) {
          // authentification error - token is not valid
          remove_user()
          return {}
        } else {
          return state
        }
      } catch (e) {
        return state
      }
    default:
      return state
  }
}

const decodeUser = (token) => {
  let base64Url = token.split('.')[1];
  let base64 = base64Url.replace('-', '+').replace('_', '/');
  let data = JSON.parse(window.atob(base64));
  let user = {
    ...data,
    token: token
  }
  set_user(user)
  return user
}

export default reducer
