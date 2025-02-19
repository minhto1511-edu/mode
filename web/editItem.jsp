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
            <header>Sửa thông tin món</header>
            
            <c:set var="o" value="${requestScope.item}"/>
            <form action="menudashboard?action=edit&item=${o.id}" method="post">
                <input type="text" name="itemName" placeholder="Nhập tên món" value="${o.itemName}" required>
                <textarea style="min-height: 100px; width: 100%; border: 1px solid grey; outline: none; resize: none" type="text" name="itemDescription" placeholder="Nhập mô tả" value="${o.itemDescription}" required>${o.itemDescription}</textarea>
                <input type="number" name="price" placeholder="Nhập giá" value="${o.price}" required>
                <select name="category" required>
                    <c:forEach var="cate" items="${requestScope.categoryList}">
                        <option value="${cate.id}" ${cate.id == o.categoryId ? 'selected' : ''} name="category">${cate.name}</option>
                    </c:forEach>
                </select>
                <input type="text" name="image" placeholder="URL ảnh" value="${o.imageUrl}" required>
                <input type="submit" class="button" value="Tạo Mới">
            </form>
        </div>
    </div>
</body>
</html>
