// hooks/useCanvasTriangle.js
import { useEffect, useRef } from 'react';

const useCanvasRectangle = () => {
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

        // Draw the triangle
        ctx.beginPath();
        ctx.moveTo(150, 0);
        ctx.lineTo(0, 75);
        ctx.lineTo(150, 75);
        ctx.closePath(); // Close the path to create a triangle
        ctx.fillStyle = 'rgb(124, 58, 237)';
        ctx.fill();

        let hue = 0;
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

export default useCanvasRectangle;
