'use client'
import {useEffect} from "react";
import {redirect} from "next/navigation";
import style from "./auth.module.css"
import {useDispatch} from "react-redux";
import {UserState} from "@/store/features/user/user.types";
import {update} from "@/store/features/user/userSlice";
import {useAuthorizeMutation} from "@/store/features/user/user.api";
export default function Auth() {
    const [authorize, { isLoading, error }] = useAuthorizeMutation();
    const dispatch = useDispatch();

    // Fake auth to test code on localhost
    // async function onTelegramAuth(event: any) {
    //     let payload: TelegramUserPayload = {
    //         id: 579515224,
    //         first_name: "Арман",
    //         last_name: "Торениязов",
    //         username: "armanokka",
    //         photo_url: "https://t.me/i/userpic/320/5YBXXglUkEctZOv0wR5aox5lNajN4xiYZ97UYZpeG-g.jpg",
    //         auth_date: 1732621732,
    //         hash: "df56e019c3425302a8c5d0bfa08db97c8e5f469e7e5610038928dd5e55c259f0"
    //     }
    //     const data = await authorize(payload).unwrap()
    //
    //     console.log(data);
    //
    //     let user: UserState = {
    //         firstName: payload.first_name,
    //         lastName: payload.last_name,
    //         username: payload.username,
    //         photoUrl: payload.photo_url,
    //         authToken: data.token,
    //     }
    //     dispatch(update(user))
    //     redirect('/game')
    // }
    //
    // return (
    //     <button onClick={onTelegramAuth}>Click me</button>
    // )


    // Real auth
    async function onTelegramAuth(payload: TelegramUserPayload) {
        console.log('received callback from Telegram login widget:', payload)

        const data = await authorize(payload).unwrap()

        console.log('response from auth server:', data)

        let user: UserState = {
            firstName: payload.first_name,
            lastName: payload.last_name,
            username: payload.username,
            photoUrl: payload.photo_url,
            authToken: data.token,
        }
        dispatch(update(user))
        redirect('/game')
    }
    useEffect(() => {
        // Create script element
        const script = document.createElement('script');
        script.src = "https://telegram.org/js/telegram-widget.js?22";
        script.setAttribute('data-telegram-login', 'web_lr4_mikhu_bot');
        script.setAttribute('data-size', 'large');
        script.setAttribute('data-radius', '5');
        script.setAttribute('data-onauth', 'onTelegramAuth(user)');

        // Append the script to the document
        document.getElementById('telegram-widget')?.appendChild(script);

        // Define the callback function
        (window as any).onTelegramAuth = onTelegramAuth;

        // Cleanup function to remove the script on component unmount
        return () => {
            document.getElementById('telegram-widget')?.removeChild(script);
            delete (window as any).onTelegramAuth; // Cleanup the callback
        };
    }, []);
    return (
        <div className={style.main}>
            <div id="telegram-widget"/>
        </div>
    );
}
