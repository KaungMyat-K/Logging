import { createAsyncThunk, createSlice } from "@reduxjs/toolkit"
import axios from "axios"

const initialState = {
    loading : false,
    projectName : [],
    filterItem : [],
    error : ''
}

export const fetchProjectName = createAsyncThunk("fetchProjectName",async ()=>{
    const res = await axios.get("http://localhost:8081/log/getByFilter");
    return res.data;
})


export const fetchFilterItem = createAsyncThunk("fetchFilterItem",async (projectName)=>{
    const res = await axios.get("http://localhost:8081/log/getFilterItem/"+projectName);
    return res.data;
})

const LogSlice = createSlice({
    name : 'log',
    initialState,
    extraReducers: (builder)=>{

        // fetch_project_name
        builder.addCase(fetchProjectName.pending,(state)=>{
            state.loading = true
        })
        builder.addCase(fetchProjectName.pending,(state,action)=>{
            state.loading = false
            state.projectName = action.payload;
        })
        builder.addCase(fetchProjectName.pending,(state,action)=>{
            state.loading = false
            state.projectName = []
            state.error = action.error.message
        })

        //fetch_filter_item 
        builder.addCase(fetchFilterItem.pending,(state)=>{
            state.loading = true
        })
        builder.addCase(fetchFilterItem.pending,(state,action)=>{
            state.loading = false
            state.filterItem = action.payload;
        })
        builder.addCase(fetchFilterItem.pending,(state,action)=>{
            state.loading = false
            state.filterItem = []
            state.error = action.error.message
        })





    }
    
})

export default LogSlice.reducer;