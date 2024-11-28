'use client';
import {create} from "domain";
import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {UserState} from "@/store/features/user/user.types";


const initialState: UserState = {
    firstName: "",
    lastName: "",
    username: "",
    photoUrl: "",
    authToken: "",
}

export const userSlice = createSlice({
    name: 'user',
    initialState,
    reducers: {
        update: (state, action: PayloadAction<UserState>) => {
            Object.assign(state, action.payload)
        }
    }
})


export const { update } = userSlice.actions;
export default userSlice.reducer;
