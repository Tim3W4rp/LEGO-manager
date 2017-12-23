import { store } from './index'
import env from './env'
import axios from 'axios'

export const backendUrl =
  (env.NODE_ENV === 'development' ? 'http://localhost:8080' : '')
  + env.PUBLIC_URL + '/rest'

const client = axios.create({
  baseURL: backendUrl,
  headers: {
    'Accept': 'application/json, text/plain, */*',
    'Content-Type': 'application/json',
  },
})

client.interceptors.request.use(function(config) {
  try {
    let token = store.getState().user.token
    config.headers.Authorization = `Bearer ${token}`
    return config
  } catch (e) {
    return config
  }
}, null);

client.interceptors.response.use(null, function(err) {
  store.dispatch({
    type: 'FETCH_ERROR',
    error: err
  })
  throw err
})

export default client
