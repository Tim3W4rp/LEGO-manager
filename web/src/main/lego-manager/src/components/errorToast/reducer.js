const reducer = (state = {
  text: "",
  buttonText: "",
  opened: false,
}, action) => {
  switch (action.type) {
    case 'FETCH_ERROR':
      return {
        ...state,
        text: action.error.message + " " + (
          action.error.response
          ? "Status: " + action.error.response.status
          : ""),
        buttonText: 'close',
        opened: true,
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
