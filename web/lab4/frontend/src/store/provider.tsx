'use client';
import {Provider, ProviderProps} from 'react-redux';
import {store} from './store'

export function Providers({ children }: any) {
    return (
        <Provider store={store}>
            { children }
        </Provider>
    )
}
