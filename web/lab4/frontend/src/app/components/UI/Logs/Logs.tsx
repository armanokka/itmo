import style from './Logs.module.css'
import {useSelector} from "react-redux";
import {RootState} from "@/store/store";
import {log} from "util";
import {useEffect} from "react";
import formatTimestamp from "@/utils/formatTimestamp";
export default function Logs() {
    const logsState = useSelector((state: RootState) => state.logs)

    return (
        <div className={style.list}>
            <ul className={style.list__inner}>
                <li className={style.list__header}>логи</li>
                {
                    logsState.map(elem => (
                        <li className={`${style.list__item} ${style.slideIn}`} key={elem.id} id={elem.id.toString()}>
                            <div className={style.list__avatar_block}>
                                <img src={elem.photoUrl} className={style.avatar}/>
                                <p className={style.p}>{elem.firstName} {elem.lastName}</p>
                            </div>
                            <p className={`${style.p} ${style.coords}`}>{elem.x.toFixed(4)} {elem.y.toFixed(4)}</p>
                            <p className={style.p}>{formatTimestamp(elem.timestamp)}</p>
                        </li>
                    ))
                }
            </ul>
        </div>
    )
}
