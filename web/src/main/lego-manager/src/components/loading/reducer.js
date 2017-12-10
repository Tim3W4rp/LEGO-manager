const reducer = (state = [], action) => {
  // reveal if action is one of async actions
  let async_action = parse_async_action(action.type)

  if (!async_action) {
    return state
  }

  let process_count = state[async_action.name] ? state[async_action.name] : 0

  let tmpObject = {}
  tmpObject[async_action.name] = process_count

  // now change state of loading

  switch (async_action.action) {
    case 'PENDING':
      tmpObject[async_action.name] += 1
      return {...state, ...tmpObject}

    case 'FULFILLED':
    case 'REJECTED':
      tmpObject[async_action.name] -= 1
      return {...state, tmpObject}

    default:
      return state
  }
}

const parse_async_action = (name) => {
  if(name.lastIndexOf("_") === -1) {
    return undefined
  }

  let action_original = name.substr(0, name.lastIndexOf("_"))
  let action_type = name.substr(name.lastIndexOf("_") + 1)

  return {
    name: action_original,
    action: action_type,
  }
}

export default reducer
