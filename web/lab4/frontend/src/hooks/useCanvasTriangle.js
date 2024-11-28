// hooks/useCanvasRectangle.js
import { useEffect, useRef } from 'react';

const useCanvasTriangle = () => {
    const canvasRef = useRef(null);

    useEffect(() => {
        const canvas = canvasRef.current;
        if (!canvas) {
            return;
        }

        const ctx = canvas.getContext("2d");
        if (!ctx) {
            return;
        }

        // Draw the rectangle
        ctx.beginPath();
        ctx.moveTo(150, 75);
        ctx.lineTo(300, 75);
        ctx.lineTo(300, 150);
        ctx.lineTo(150, 150);
        ctx.fillStyle = 'rgb(124, 58, 237)';
        ctx.fill();

        let hue = 10;
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

export default useCanvasTriangle;
