<%-- 
    Document   : createUser
    Created on : Oct 21, 2024, 8:19:57 PM
    Author     : dung0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    // Kiểm tra nếu người dùng chưa đăng nhập
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login");
        return;
    }
%>
<c:if test="${sessionScope.user.role != 'admin'}">
    <c:redirect url="notfound"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tạo Người Dùng Mới</title>
        <link rel="stylesheet" href="css/login.css"/>
        <img src="images/hero-bg.jpg" alt="" class="background-image">
    </head>
    <body>
        <div class="container">
            <div class="registration form">
                <header>Tạo người dùng mới</header>
                <h3 style="color: red">${requestScope.error}</h3>
                <form action="staff?action=add" method="post">
                    <input type="text" name="user" placeholder="Nhập Username" required>
                    <input type="password" name="pass" placeholder="Nhập Password" required>
                    <input type="text" name="fullname" placeholder="Họ và tên" required>
                    <select name="role" required>
                        <option selected="">--Role--</option>
                        <option value="admin">Admin</option>
                        <option value="staff">Staff</option>
                    </select>
                    <input type="email" name="email" placeholder="Địa chỉ email" required>
                    <input type="text" name="phone" placeholder="Số điện thoại" required>
                    <input type="submit" class="button" value="Tạo Mới">
                </form>
            </div>
        </div>
    </body>
</html>
