<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="utils.NumberUtils" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="results" scope="session" class="beans.ResultsBean"/>
<core:forEach var="result" items="${results.results}">
  <tr>
    <th>${NumberUtils.truncate(result.x, 4)}</th>
    <th>${NumberUtils.truncate(result.y, 4)}</th>
    <th>${result.r}</th>
    <th>${result.hit ? "true" : "false"}</th>
  </tr>
</core:forEach>
