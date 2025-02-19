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
        <title>Xóa danh mục món ăn</title>
        <link rel="stylesheet" href="css/login.css"/>
    <img src="images/hero-bg.jpg" alt="" class="background-image">
</head>
<script>
    function confirmDeletion() {
        return confirm("Xóa danh mục món ăn này sẽ xóa tất cả những món thuộc danh mục này? Bạn có chắc chắc muốn xóa không?");
    }
</script>
<body>
    <div class="container">
        <div class="registration form">
            <header>Xóa danh mục món ăn</header>
            <h3 style="color: red">${requestScope.error}</h3>
            <form action="menudashboard" method="post" onsubmit="return confirmDeletion()">
                <input name="action" value="deleteCate" hidden>
                <select name="cate">
                    <c:forEach items = "${requestScope.list}" var="list">
                        <option value="${list.id}">${list.name}</option>
                    </c:forEach>
                </select>            
                <input type="submit" class="button" value="Xóa">
            </form>
        </div>
    </div>
</body>
</html>
