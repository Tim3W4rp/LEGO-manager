const reducer = (state = [], action) => {
  switch (action.type) {
    case 'FETCH_KIT_FULFILLED':
      return {
        ...state,
        ...action.payload.data
      }

    case 'FETCH_SIMILAR_KIT_FULFILLED':
      if (!action.payload.data._embedded) {
        return state
      }
      return {
        ...state,
        similarKits: action.payload.data._embedded.kits
      }

    default:
      return state
  }
}

export default reducer
