import { store } from './index'
import env from './env'
import axios from 'axios'

export const backendUrl =
  (env.NODE_ENV === 'development' ? 'http://localhost:8080' : '')
  + env.PUBLIC_URL + '/rest'

const client = axios.create({
  baseURL: backendUrl,
  withCredentials: true,
  headers: {
    headers: {
      'Accept': 'application/json, text/plain, */*',
      'Content-Type': 'application/json'
    }
  },
})

client.interceptors.response.use(null, function(err) {
  store.dispatch({
    type: 'FETCH_ERROR',
    error: err
  })
  throw err
})

export default client
