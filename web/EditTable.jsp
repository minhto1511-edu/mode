<%-- 
    Document   : createTable
    Created on : Oct 22, 2024, 9:12:24 AM
    Author     : dung0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login");
        return;
    }
%>
<c:if test="${sessionScope.user.role != 'admin'}">
    <%
        response.sendRedirect("notfound");
    %>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sửa Thông Tin Bàn</title>
        <link rel="stylesheet" href="css/login.css"/>
    <img src="images/hero-bg.jpg" alt="" class="background-image">
</head>
<body>
    <div class="container">
        <div class="registration form">
            <header>Sửa Thông Tin Bàn</header>
            <h3 style="color: red">${requestScope.err}</h3>
            <c:set var="t" value="${requestScope.tableInfo}"/>
            <form action="createtable?action=2" method="post"> 
                <label>Số Bàn:</label><br>
                <input type="number" name="tnum" placeholder="Số Bàn" value="${t.tableNumber}" readonly>
                <label>Số ghế:</label><br>
                <input type="capacity" name="cnum" placeholder="Số người" value="${t.capacity}" required>                
                <input type="submit" class="button" value="Sửa">
            </form>
        </div>
    </div>
</body>
</html>
