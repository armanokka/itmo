(function(window, document, undefined) {

    // code that should be taken care of right away

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
            ctx.fillStyle = 'deepskyblue'
            ctx.fill();
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
            ctx.fillStyle = 'deepskyblue'
            ctx.fill();
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
            ctx.fillStyle = 'deepskyblue'
            ctx.fill();
        }
    }

    function sendRequest() {
        // Create a new XMLHttpRequest object
        var xhr = new XMLHttpRequest();

        // Configure it: POST-request to the URL
        xhr.open('POST', 'http://localhost:8080/fcgi-bin/server.jar', true); // Replace with your server URL

        // Set the request header
        xhr.setRequestHeader('Content-Type', 'multipart/form-data');

        let x = document.querySelector('input[name="x"]:checked').value;
        let y = document.querySelector('input[name="y"]').value;
        let r = document.querySelector('input[name="r"]:checked').value;

        // Set up a function to handle the response
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    // Parse and log the response
                    const response = JSON.parse(xhr.responseText)
                    console.log('Response:', response);
                    showResponse(response)
                    saveResponseToLocalStorage(response)
                } else {
                    console.error('Error:', xhr.status, xhr.statusText);
                }
            }
        };

        // Send the request with the data
        xhr.send(`x=${x}&y=${y}&r=${r}`);
    }

    function showResponse(response) {
        let rows = document.querySelector(".rows")

        const tr = document.createElement("tr")
        tr.setAttribute("class", "move-in")

        const th1 = document.createElement("th")
        th1.setAttribute("class", "row")
        th1.appendChild(document.createTextNode(response.current_time))

        const th2 = document.createElement("th")
        th2.setAttribute("class", "row")
        th2.appendChild(document.createTextNode(response.result ? "да" : "нет"))

        const th3 = document.createElement("th")
        th3.setAttribute("class", "row")
        th3.appendChild(document.createTextNode(response.execution_time_ns))

        tr.appendChild(th1)
        tr.appendChild(th2)
        tr.appendChild(th3)
        if (response.result) {
            th2.style.background = 'lightgreen'
        } else {
            th2.style.background = 'red'
        }
        rows.appendChild(tr)
    }

    function getResponsesFromLocalStorage() {
        let data = localStorage.getItem("data")
        if (data == null) {
            data = '[]'
        }
        const obj = JSON.parse(data)
        return Object.keys(obj).map((key) => obj[key]);
    }

    function saveResponseToLocalStorage(response) {
        let responses = getResponsesFromLocalStorage()
        responses.push(response)
        localStorage.setItem("data", JSON.stringify(responses))
    }

    function init(){
        let data = getResponsesFromLocalStorage()

        for (let i = 0; i < data.length; i++) {
            console.log("loaded from local storage", data[i])
            showResponse(data[i])
        }

        drawCanvas1()
        drawCanvas2()
        drawCanvas3()
        document.getElementById('submit-btn').addEventListener('click', (e)=>{
            e.preventDefault();
            sendRequest()
        })
    }

})(window, document, undefined);



