export const get_user = () => {
  let data = window.localStorage.getItem('user')
  if (!data) {
    return {}
  }
  let user = {}
  try {
    user = JSON.parse(data)
  } catch (e) {
    remove_user()
  }

  if(!timestamp_valid(user.exp)) {
    // timestamp expired
    remove_user()
    return {}
  }
  return user
}

export const set_user = (user) => {
  window.localStorage.setItem('user', JSON.stringify(user))
}

export const remove_user = () => {
  window.localStorage.removeItem('user')
}

export const timestamp_valid = (timestamp) => {
  let exp = new Date(timestamp * 1000);
  if(exp && exp > new Date()) {
    return true
  } else {
    return false
  }
}
