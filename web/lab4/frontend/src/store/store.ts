'use client';

import {combineReducers, configureStore} from "@reduxjs/toolkit";
import { persistStore, persistReducer } from "redux-persist";
import userReducer from "@/store/features/user/userSlice"
import logsReducer from "@/store/features/logs/logsSlice"
import storage from "redux-persist/lib/storage"
import {userApi} from "@/store/features/user/user.api";
import {setupListeners} from "@reduxjs/toolkit/query";

const rootReducer = combineReducers({
    user: userReducer,
    logs: logsReducer,
    [userApi.reducerPath]: userApi.reducer,
})

const persistConfig = {
    key: "root",
    // middleware: getDefaultMiddleware =>
    //     getDefaultMiddleware().concat(userApi.middleware)
    storage,
}

const persistedReducer = persistReducer(persistConfig, rootReducer);

export const store = configureStore({
    reducer: persistedReducer,
    devTools: process.env.NODE_ENV !== "production",
    middleware: (getDefaultMiddleware) =>
        getDefaultMiddleware({
            serializableCheck: {
                ignoredActions: ["persist/PERSIST", "persist/REHYDRATE"],
            },
    }).concat(userApi.middleware),
})

setupListeners(store.dispatch)

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
export const persistor = persistStore(store)
