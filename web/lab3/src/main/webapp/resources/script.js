(function(window, document) {

    window.onload = init;

    function drawCanvas1() {
        const canvas = document.getElementById('canvas1')
        if (canvas.getContext) {
            const ctx = canvas.getContext("2d");
            ctx.beginPath();
            ctx.lineTo(150, 0);
            ctx.lineTo(0, 75);
            ctx.lineTo(0, 75);
            ctx.lineTo(150, 75);
            ctx.fill();

            let hue = 10;
            function draw() {
                ctx.fillStyle = `hsl(`+hue+`, 100%, 50%)`;
                ctx.fill()
                hue = (hue + 1) % 360; // Increment hue
                requestAnimationFrame(draw); // Request next frame
            }

            draw(); // Start the drawing
        }
    }
    function drawCanvas2() {
        const canvas = document.getElementById('canvas2')
        if (canvas.getContext) {
            const ctx = canvas.getContext("2d");
            ctx.beginPath();
            ctx.moveTo(150, 75)
            ctx.lineTo(300, 75)
            ctx.lineTo(300, 150)
            ctx.lineTo(150, 150)
            ctx.fillStyle = 'rgb(124, 58, 237)'
            ctx.fill();

            let hue = 20;
            function draw() {
                ctx.fillStyle = `hsl(`+hue+`, 100%, 50%)`;
                ctx.fill()
                hue = (hue + 1) % 360; // Increment hue
                requestAnimationFrame(draw); // Request next frame
            }

            draw(); // Start the drawing
        }
    }

    function drawCanvas3() {
        const canvas = document.getElementById('canvas3')
        canvas.width = 100
        canvas.height = 100
        if (canvas.getContext) {
            const ctx = canvas.getContext("2d");
            ctx.beginPath();
            ctx.arc(50, 50, 25, 0, Math.PI*1.5,true);
            ctx.lineTo(50, 50)
            ctx.fillStyle = 'rgb(124, 58, 237)'
            ctx.fill();

            let hue = 30;
            function draw() {
                ctx.fillStyle = `hsl(`+hue+`, 100%, 50%)`;
                ctx.fill()
                hue = (hue + 1) % 360; // Increment hue
                requestAnimationFrame(draw); // Request next frame
            }

            draw(); // Start the drawing
        }
    }

    function sendRequest(x, y, r, clientX, clientY) {
        document.querySelector("input[id$=\":x\"]").value = x;
        document.querySelector("input[id$=\":y\"]").value = y;
        document.querySelector("input[id$=\":submit-btn\"]").click();
    }

    function showResponse(response, clientX, clientY) {
        let tbody = document.querySelector("tbody") // table

        const tr = document.createElement("tr")
        tr.setAttribute("class", "move-in")

        const th1 = document.createElement("th")
        th1.setAttribute("class", "row")
        th1.appendChild(document.createTextNode(Number(response.x).toFixed(4)))

        const th2 = document.createElement("th")
        th2.setAttribute("class", "row")
        th2.appendChild(document.createTextNode(Number(response.y).toFixed(4)))

        const th3 = document.createElement("th")
        th3.setAttribute("class", "row")
        th3.appendChild(document.createTextNode(Number(response.r).toFixed(0)))


        const th4 = document.createElement("th")
        th4.setAttribute("class", "row")
        if (response.isHit) th4.setAttribute("class", "row text-gradient");
        th4.appendChild(document.createTextNode(response.isHit))

        tr.appendChild(th1)
        tr.appendChild(th2)
        tr.appendChild(th3)
        tr.appendChild(th4)

        tbody.insertBefore(tr, tbody.lastElementChild)
        drawDot(clientX, clientY, response.isHit)
    }

    function init(){
        drawCanvas1();
        drawCanvas2();
        drawCanvas3()
        drawDotsFromBeanTableData();
        document.querySelector(".axis-box").addEventListener('click', handleAxisBoxClick);
    }

    function handleAxisBoxClick(event) {
        const axisBox = document.querySelector(".axis-box");

        const rect = axisBox.getBoundingClientRect()

        // Calculate mouse position relative to the div
        let x = event.clientX - rect.left;
        let y = event.clientY - rect.top;
        let r = Number(document.querySelector('.checkboxes input:checked').value);

        x -= 150
        y = -y + 150
        x /= 100
        y /= 100
        x *= r
        y *= r

        sendRequest(x, y, r, event.clientX, event.clientY)
    }

    function drawDot(clientX, clientY, hit) {
        const dot = document.createElement('dot');
        dot.style.left = clientX+`px`; // Center the dot
        dot.style.top = clientY+`px`;  // Center the dot
        if (hit) dot.classList.add("hit");
        document.body.appendChild(dot)
    }

    function drawDotsFromBeanTableData() {
        const tbody = document.querySelector("table#results-table > tbody")
        let output = []
        tbody.childNodes.forEach(function (tr) {
            let data = []
            tr.childNodes.forEach(function (th) {
                if (th.textContent.trim() !== "") data.push(th.textContent.trim());
            })
            if (data.length !== 0) output.push(data);
        });
        console.log(output)

        const rect = document.querySelector(".axis-box").getBoundingClientRect();
        for (var i = 0; i < output.length; i++) {
            let x = Number(output[i][0]);
            let y = Number(output[i][1]);
            let r = Number(output[i][2]);
            let isHit = String(output[i][3]) === 'true' ? true : false;

            x /= r
            y /= r
            x *= 100
            y *= 100

            y = 150 - y
            x += 150

            x += rect.left;
            y += rect.top;

            drawDot(x, y, isHit)
        }
    }
})(window, document);



