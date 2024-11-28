'use client'
import React, {useEffect} from "react";
import Header from "@/app/components/UI/Header/header";
import {Axis} from "@/app/components/UI/Axis/Axis";
import useWebsocket from "@/hooks/useWebsocket";
import getCoordinatesFromClick from "@/utils/getCoordinatesFromClick";
import {useDispatch, useSelector} from "react-redux";
import {RootState} from "@/store/store";
import Logs from "@/app/components/UI/Logs/Logs";
import style from './game.module.css'
import {addLog, LogsState, removeLogByID} from "@/store/features/logs/logsSlice";
import drawDotFromCoordinates from "@/utils/drawDotFromCoordinates";
import Footer from "@/app/components/UI/Footer/Footer";

export default function Game() {
    const userState = useSelector((state: RootState) => state.user);
    const logsState = useSelector((state: RootState) => state.logs);
    const dispatch = useDispatch();

    console.log('user state from redux:', userState)

    useEffect(()=>{
        logsState.entries().forEach(([key, value], index) => {
            drawDotFromCoordinates()
            setTimeout(()=>{
                dispatch(removeLogByID(value.id))
            }, 10_000+index*1000)
        })
    }, [])


    const handleNewClickCallback = (data: any) => {
        const log: LogsState = {
            firstName: data.first_name,
            lastName: data.last_name,
            photoUrl: data.photo_url,
            username: data.username,
            hit: data.hit,
            x: data.x,
            y: data.y,
            id: data.id,
            timestamp: Date.now(),
        }
        dispatch(addLog(log))
        setTimeout(()=>{
            dispatch(removeLogByID(data.id));
        }, 10_000)
    }

    const {sendMessage} = useWebsocket('ws://localhost:8080/api/ws?token=' + userState.authToken, handleNewClickCallback);

    function handleAxisClick(e: React.MouseEvent<HTMLDivElement>) {
        sendMessage(JSON.stringify(getCoordinatesFromClick(e)))
    }

    return (
        <>
            <Header/>
            <div className={style.container}>
                <Axis handleClick={handleAxisClick}/>
                <Logs/>
            </div>
            <Footer/>
        </>
    )
}
