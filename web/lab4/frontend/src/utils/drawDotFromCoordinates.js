import styles from "@/app/components/UI/Axis/Axis.module.css";

export default function drawDotFromCoordinates(id, clientX, clientY, hit) {
    const canvasBlock = document.querySelector("."+styles.canvasBlock);
    const rect = canvasBlock.getBoundingClientRect();

    clientX -= rect.left
    clientY -= rect.top

    const dot = document.createElement('dot');
    dot.setAttribute('id', id)

    dot.style.left = clientX+`px`;
    dot.style.top = clientY+`px`;
    if (hit) dot.classList.add("hit");
    canvasBlock.appendChild(dot)
}
