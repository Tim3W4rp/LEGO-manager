import { store } from './index'
import axios from 'axios'

export const backendUrl = (process.env.NODE_ENV === 'development' ? 'http://localhost:8080' : '') + process.env.PUBLIC_URL + '/api/v1'

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
});

export default client
