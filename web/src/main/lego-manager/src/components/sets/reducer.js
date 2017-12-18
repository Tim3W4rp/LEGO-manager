const reducer = (state = {
  data: [],
  links: {}
}, action) => {
  switch (action.type) {
    case 'FETCH_SETS_FULFILLED':
      return {
        ...state,
        data: action.payload.data._embedded.sets,
        links: action.payload.data._links
      }

    default:
      return state
  }
}

export default reducer
