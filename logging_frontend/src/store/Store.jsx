import { configureStore } from "@reduxjs/toolkit"
import logReducer from '../slices/LogSlice'

const store = configureStore({
    reducer:{
        log : logReducer
    }
})

export default store;