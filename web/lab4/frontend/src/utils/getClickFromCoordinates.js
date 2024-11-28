import styles from "../app/components/UI/Axis/Axis.module.css";

// getCoordinatesFromClick returns clientX, clientY needed to draw a dot on the screen
export default function getClickFromCoordinates(x, y) {
    const rect = document.querySelector("."+styles.canvasBlock).getBoundingClientRect();

    x *= 200
    y *= 200

    y = 200 - y
    x += 200

    x += rect.left;
    y += rect.top;
    return {clientX: x, clientY: y}
}
