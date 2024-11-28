import useCanvasTriangle from "@/hooks/useCanvasTriangle";
import useCanvasRectangle from "@/hooks/useCanvasRectangle";
import useCanvasQuarterCircle from "@/hooks/useCanvasQuarterCircle";
import style from './Axis.module.css'
import React from "react";

interface AxisProps {
    handleClick: (event: React.MouseEvent<HTMLDivElement>) => void; // Define the onClick prop
}

export const Axis: React.FC<AxisProps> = ({handleClick}) => {
    const canvasRef1 = useCanvasTriangle()
    const canvasRef2 = useCanvasRectangle()
    const canvasRef3 = useCanvasQuarterCircle()

    return <div className={style.axisBox} onClick={(e)=>handleClick(e)} >
        <div className={style.canvasBlock} >
            <canvas className={style.canvas} ref={canvasRef3}/>
            <canvas className={style.canvas} ref={canvasRef1}/>
            <canvas className={style.canvas} ref={canvasRef2}/>
        </div>
        <div className={style.axisBlock}>
            <div className={style.axis}>
                <div className={`${style.stick} ${style.invisible}`}></div>
                <div className={style.stick}>
                    <p className={style.mark}>R</p>
                </div>
                <div className={style.stick}>
                    <p className={style.mark}>R/2</p>
                </div>
                <div className={style.stick}></div>
                <div className={style.stick}>
                    <p className={style.mark}>-R/2</p>
                </div>
                <div className={style.stick}>
                    <p className={style.mark}>-R</p>
                </div>
                <div className={`${style.stick} ${style.invisible}`}></div>
            </div>
            <div className={`${style.axis} ${style.y}`}>
                <div className={`${style.stick} ${style.invisible}`}></div>
                <div className={style.stick}>
                    <p className={`${style.mark} ${style.rotated}`}>R</p>
                </div>
                <div className={style.stick}>
                    <p className={style.mark}>R/2</p>
                </div>
                <div className={style.stick}></div>
                <div className={style.stick}>
                    <p className={style.mark}>-R/2</p>
                </div>
                <div className={style.stick}>
                    <p className={style.mark}>-R</p>
                </div>
                <div className={`${style.stick} ${style.invisible}`}></div>
            </div>
        </div>
    </div>
}
