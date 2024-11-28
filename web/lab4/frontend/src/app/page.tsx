"use client";
import {redirect} from "next/navigation";
import {useSelector} from "react-redux";
import {RootState} from "@/store/store";

export default function Home() {
    const userState = useSelector((state: RootState) => state.user)

    if (userState != null) {
        redirect('/game')
        return
    }
    redirect('/auth')
    return
}
