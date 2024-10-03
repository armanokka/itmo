<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<style><%@include file="style.css"%></style>
<meta charset="utf-8">
<body>
<header class="header">
    <p class="text-gradient header_text">Торениязов Арман Ажинияз улы, Р3223, вариант 23474678</p>
</header>
<!--TODO:
- сохранять историю респонсов на бэкенде и показывать при первой загрузке [+]
- определять координаты x и y по клику по координатной плоскости на фронте
- отображать точки на фронте на координатной плоскости
-->
<main class="main">
    <div class="section">
        <form method="POST" action="http://localhost:8080/fcgi-bin/server.jar">
            <div class="selector">
                <div class="selector_inner">
                    <p>R</p>
                    <div class="checkboxes">
                        <label class="text-gradient">
                            1
                            <input name="r" value="1" checked="checked" type="radio" required>
                        </label>
                        <label class="text-gradient">
                            2
                            <input name="r" value="2" type="radio">
                        </label>
                        <label class="text-gradient">
                            3
                            <input name="r" value="3" type="radio">
                        </label>
                        <label class="text-gradient">
                            4
                            <input name="r" value="4" type="radio">
                        </label>
                        <label class="text-gradient">
                            5
                            <input name="r" value="5" type="radio">
                        </label>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="section">
        <div class="axis-box">
            <div class="canvas-block">
                <canvas id="canvas1"></canvas>
                <canvas id="canvas2"></canvas>
                <canvas id="canvas3"></canvas>
            </div>
            <div class="axis-block">
                <div class="axis x">
                    <div class="stick invisible"></div>
                    <div class="stick">
                        <p class="mark">R</p>
                    </div>
                    <div class="stick">
                        <p class="mark">R/2</p>
                    </div>
                    <div class="stick"></div>
                    <div class="stick">
                        <p class="mark">-R/2</p>
                    </div>
                    <div class="stick">
                        <p class="mark">-R</p>
                    </div>
                    <div class="stick invisible"></div>
                </div>
                <div class="axis y">
                    <div class="stick invisible"></div>
                    <div class="stick">
                        <p class="mark rotated">R</p>
                    </div>
                    <div class="stick">
                        <p class="mark">R/2</p>
                    </div>
                    <div class="stick"></div>
                    <div class="stick">
                        <p class="mark">-R/2</p>
                    </div>
                    <div class="stick">
                        <p class="mark">-R</p>
                    </div>
                    <div class="stick invisible"></div>
                </div>
            </div>
        </div>

    </div>
    <div class="section">
        <table class="rows">
            <tr class="columns">
                <th class="col">X</th>
                <th class="col">Y</th>
                <th class="col">R</th>
                <th class="col">Попал</th>
            </tr>
            <jsp:include page="table.jsp"/>
            <tr class="loading-circle-block">
                <th class="loading-circle-th"><div class="loading-circle"></div></th>
                <th class="loading-circle-th"><div class="loading-circle"></div></th>
                <th class="loading-circle-th"><div class="loading-circle"></div></th>
            </tr>
        </table>
    </div>
</main>
<footer class="footer">
    <div class="scroll">
        <div class="scroll-container">
                <div class="scroll-element color1">сделано</div>
                <div class="scroll-element color2">в</div>
                <div class="scroll-element color3 background-gradient">ИТМО</div>
                <div class="scroll-element color1">специально</div>
                <div class="scroll-element color2">для</div>
                <div class="scroll-element color3">Миху</div>
                <div class="scroll-element color1">Вадима</div>
                <div class="scroll-element color2">Дмитриевича</div>
                <div class="scroll-element color3">2024</div>
                <div class="scroll-element color1">сделано</div>
                <div class="scroll-element color2">в</div>
                <div class="scroll-element color3 background-gradient">ИТМО</div>
                <div class="scroll-element color1">специально</div>
                <div class="scroll-element color2">для</div>
                <div class="scroll-element color3">Миху</div>
                <div class="scroll-element color1">Вадима</div>
                <div class="scroll-element color2">Дмитриевича</div>
                <div class="scroll-element color3">2024</div>
        </div>
    </div>
<!--    <p>сделано с ♥</p>-->
<!--    <img class="avatar" src="https://i.ibb.co/dpYPXMC/avatar.jpg">-->
<!--    <p>в ИТМО</p>-->
<!--    <p>2024</p>-->
</footer>
<script><%@include file="script.js"%></script>
</body>
</html>
