<%-- 
    Document   : editUser
    Created on : Oct 21, 2024, 9:50:51 PM
    Author     : dung0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <title>Sửa Thông tin người dùng</title>
        <link rel="stylesheet" href="css/login.css"/>
    <img src="images/hero-bg.jpg" alt="" class="background-image">
</head>
<body>
    <div class="container">
        <div class="registration form">
            <header>Đăng ký</header>
            <h3 style="color: red">${requestScope.error}</h3>
            <c:set var="u" value="${requestScope.userEdit}"/>
            <form action="staff?action=edit" method="post">
                <input type="text" name="user" placeholder="Nhập Username" value="${u.username}" required>
                <input type="text" name="pass" placeholder="Nhập Password" value="${u.password}" required>
                <input type="text" name="fullname" placeholder="Họ và tên" value="${u.fullname}" required>
                <select name="role" required>
                    <option value="admin" ${u.role == 'admin' ? 'selected' : ''}>Admin</option>
                    <option value="staff" ${u.role == 'staff' ? 'selected' : ''}>Staff</option>
                </select>
                <input type="email" name="email" placeholder="Địa chỉ email" value="${u.email}" required>
                <input type="text" name="phone" placeholder="Số điện thoại" value="${u.phone}" required>
                <input type="submit" class="button" value="Tạo Mới">
            </form>
        </div>
    </div>
</body>
</html>
