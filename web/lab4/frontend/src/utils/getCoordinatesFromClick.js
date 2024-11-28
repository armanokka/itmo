import styles from '../app/components/UI/Axis/Axis.module.css'

export default function getCoordinatesFromClick(event) {
    const rect = document.querySelector("."+styles.canvasBlock).getBoundingClientRect()

    // Calculate mouse position relative to the div
    let x = event.clientX - rect.left;
    let y = event.clientY - rect.top;

    x -= 200
    y = -y + 200

    x /= 200
    y /= 200

    return {x, y}
}
