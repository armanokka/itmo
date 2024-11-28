'use client';
import {Provider, ProviderProps} from 'react-redux';
import {persistor, store} from './store'
import {PersistGate} from "redux-persist/integration/react";

export function PersistGates({ children }: any) {
    return (
        <PersistGate loading={null} persistor={persistor}>
            { children }
        </PersistGate>
    )
}
