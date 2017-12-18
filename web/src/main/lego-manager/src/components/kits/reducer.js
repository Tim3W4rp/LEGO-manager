const reducer = (state = {
  data: [],
  links: {}
}, action) => {
  switch (action.type) {
    case 'FETCH_KITS_FULFILLED':
      return {
        ...state,
        data: action.payload.data._embedded.categories,
        links: action.payload.data._links
      }

    default:
      return state
  }
}

export default reducer
