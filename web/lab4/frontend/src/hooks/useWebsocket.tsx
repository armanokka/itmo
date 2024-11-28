import { useEffect, useRef } from 'react';
import Cookies from 'js-cookie';
import getClickFromCoordinates from '../utils/getClickFromCoordinates';
import drawDotFromCoordinates from '@/utils/drawDotFromCoordinates';
import { useDispatch } from 'react-redux';
import { addLog } from '@/store/features/logs/logsSlice';

interface ServerMessageClickEvent {
    id: number;
    x: number;
    y: number;
    first_name: string;
    last_name: string;
    photo_url: string;
    username: string;
    hit: boolean;
}

const useWebSocket = (url: string, handleClickCallback: (data:any) => void) => {
    const socketRef = useRef<WebSocket | null>(null);

    useEffect(() => {
        socketRef.current = new WebSocket(url);

        socketRef.current.onopen = () => {
            console.log('WebSocket connected');
        };

        socketRef.current.onmessage = (event: MessageEvent) => {
            handleClickCallback(JSON.parse(event.data));
        };

        socketRef.current.onclose = () => {
            console.log('WebSocket disconnected');
        };

        // Cleanup on unmount
        return () => {
            if (socketRef.current) {
                socketRef.current.close();
            }
        };
    });

    const sendMessage = (message: string) => {
        if (socketRef.current) {
            socketRef.current.send(message);
        }
    };

    return { sendMessage };
};

export default useWebSocket;
