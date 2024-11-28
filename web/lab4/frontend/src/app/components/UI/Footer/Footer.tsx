import style from './Footer.module.css';
export default function Footer() {
    return (
        <footer className={style.footer}>
            <div className={style.scroll}>
                <div className={style.scrollContainer}>
                    <div className={`${style.scrollElement} ${style.color1}`}>сделано</div>
                    <div className={`${style.scrollElement} ${style.color2}`}>в</div>
                    <div className={`${style.scrollElement} ${style.color3}`}>ИТМО</div>
                    <div className={`${style.scrollElement} ${style.color1}`}>с</div>
                    <div className={`${style.scrollElement} ${style.color2}`}>помощью</div>
                    <div className={`${style.scrollElement} ${style.color3}`}>Next.JS</div>
                    <div className={`${style.scrollElement} ${style.color1}`}>Golang</div>
                    <div className={`${style.scrollElement} ${style.color2}`}>React</div>
                    <div className={`${style.scrollElement} ${style.color3}`}>RTK Query</div>
                    <div className={`${style.scrollElement} ${style.color1}`}>Websockets</div>
                    <div className={`${style.scrollElement} ${style.color2}`}>Docker-compose</div>
                    <div className={`${style.scrollElement} ${style.color3}`}>Websocket</div>
                    <div className={`${style.scrollElement} ${style.color1}`}>специально</div>
                    <div className={`${style.scrollElement} ${style.color2}`}>для</div>
                    <div className={`${style.scrollElement} ${style.color3}`}>Миху</div>
                    <div className={`${style.scrollElement} ${style.color1}`}>Вадима</div>
                    <div className={`${style.scrollElement} ${style.color2}`}>Дмитриевича</div>
                    <div className={`${style.scrollElement} ${style.color3}`}>2024</div>
                </div>
            </div>
        </footer>
    )
}
