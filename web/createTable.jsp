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
        <title>Tạo Bàn Mới</title>
        <link rel="stylesheet" href="css/login.css"/>
    <img src="images/hero-bg.jpg" alt="" class="background-image">
</head>
<body>
    <div class="container">
        <div class="registration form">
            <header>Tạo Bàn Mới</header>
            <h3 style="color: red">${requestScope.err}</h3>
            <form action="createtable" method="post">
                <input type="number" name="tnum" placeholder="Số Bàn" required>
                <input type="capacity" name="cnum" placeholder="Số người" required>                
                <input type="submit" class="button" value="Tạo Mới">
            </form>
        </div>
    </div>
</body>
</html>
