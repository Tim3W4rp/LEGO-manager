const reducer = (state = [], action) => {
    switch (action.type) {
        case 'FETCH_SHAPE_FULFILLED':
            return {
                ...action.payload.data
            }

        default:
            return state
    }
}

export default reducer
