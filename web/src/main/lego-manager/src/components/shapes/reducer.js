const reducer = (state = {
    data: [],
    links: {}
}, action) => {
    switch (action.type) {
        case 'FETCH_SHAPES_FULFILLED':
            return {
                ...state,
                data: action.payload.data._embedded.shapes,
                links: action.payload.data._links
            }

        default:
            return state
    }
}

export default reducer
