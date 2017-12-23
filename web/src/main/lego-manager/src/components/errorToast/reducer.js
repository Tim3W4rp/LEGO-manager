const reducer = (state = {
  text: "",
  buttonText: "",
  opened: false
}, action) => {
  switch (action.type) {
    case 'FETCH_ERROR':
      if (action.error.response && action.error.response.status === 401) {
        return {
          ...state,
          text: 'User authentifaction expired',
          buttonText: 'close',
          opened: true,
        }
      }
      return {
        ...state,
        text: action.error.message + " " + (
          action.error.response
          ? "Status: " + action.error.response.status
          : ""),
        buttonText: 'close',
        opened: true,
        buttonAction: () => {}
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
