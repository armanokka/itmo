import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import drawDotFromCoordinates from "@/utils/drawDotFromCoordinates";
import getClickFromCoordinates from "@/utils/getClickFromCoordinates";

export interface LogsState {
    id: number
    firstName: string
    lastName: string
    photoUrl: string
    username: string
    x: number
    y: number
    hit: boolean
    timestamp: number
}

const initialState: LogsState[] = [];

export const logsSlice = createSlice({
    name: 'logs',
    initialState,
    reducers: {
        addLog(state, action: PayloadAction<LogsState>) {
            const {clientX, clientY} = getClickFromCoordinates(action.payload.x, action.payload.y)
            drawDotFromCoordinates(action.payload.id, clientX, clientY, action.payload.hit)
            state.push(action.payload)
        },
        removeLogByID(state, action: PayloadAction<number>) {
            document.querySelector(`dot[id="${action.payload.toString()}"]`)?.remove()
            return [...state].filter(log => log.id !== action.payload)
        },
    }
})

export const { addLog, removeLogByID } = logsSlice.actions;
export default logsSlice.reducer;
