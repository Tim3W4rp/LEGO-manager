const reducer = (state = {
  text: "",
  buttonText: "",
  opened: false
}, action) => {
  switch (action.type) {
    case 'FETCH_ERROR':
      return {
        ...state,
        text: (
          action.error.response &&
          action.error.response.data &&
          action.error.response.data.code
            ? action.error.response.data.message
            : action.error.message),
        buttonText: 'close',
        opened: true
      }

    case 'ERROR_OPENED':
      return {
        ...state,
        opened: action.value
      }

    default:
      return state
  }
}

export default reducer
