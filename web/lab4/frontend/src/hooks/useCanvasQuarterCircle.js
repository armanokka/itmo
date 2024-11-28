// hooks/useCanvas.js
import { useEffect, useRef } from 'react';

const useCanvasQuarterCircle = () => {
    const canvasRef = useRef(null);

    useEffect(() => {
        const canvas = canvasRef.current;
        if (!canvas) {
            return;
        }

        canvas.width = 500;
        canvas.height = 500;
        const ctx = canvas.getContext("2d");
        if (!ctx) {
            return;
        }

        ctx.beginPath();
        ctx.arc(250, 250, 125, 0, Math.PI * 1.5, true);
        ctx.lineTo(250, 250);
        ctx.fillStyle = 'rgb(124, 58, 237)';
        ctx.fill();

        let hue = 20;
        const draw = () => {
            ctx.fillStyle = `hsl(${hue}, 100%, 50%)`;
            ctx.fill();
            hue = (hue + 1) % 360; // Increment hue
            requestAnimationFrame(draw); // Request next frame
        };
        draw(); // Start the drawing

        // Cleanup function to stop the animation when the component unmounts
        return () => {
            cancelAnimationFrame(draw);
        };
    }, []);

    return canvasRef;
};

export default useCanvasQuarterCircle;
