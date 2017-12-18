const reducer = (state = [], action) => {
  switch (action.type) {
    case 'FETCH_KIT_FULFILLED':
      return {
        ...action.payload.data
      }

    default:
      return state
  }
}

export default reducer
