const reducer = (state = [], action) => {
  switch (action.type) {
    case 'FETCH_SET_FULFILLED':
      return {
        ...action.payload.data
      }

    default:
      return state
  }
}

export default reducer
