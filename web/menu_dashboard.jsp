<%-- 
    Document   : menu_dashboard
    Created on : Oct 18, 2024, 2:05:43 PM
    Author     : dung0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <meta charset="UTF-8" />
        <title>Group 4 Restaurant</title>
        <link rel="shortcut icon" href="images/favicon.png" type="">
        <link rel="stylesheet" href="css/menu.css" />
        <!-- Font Awesome Cdn Link -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    </head>
    <body>
        <div class="container">
            <nav>
                <ul>
                    <li><a href="dashboard" class="logo">
                            <img src="images/favicon.png" alt="">
                            <span class="nav-item">Restaurant</span>
                        </a></li>
                    <li><a href="dashboard">
                            <i class="fas fa-home"></i>
                            <span class="nav-item">Home</span>
                        </a></li>
                        <c:if test="${sessionScope.user.role == 'admin'}">
                        <li><a href="staff">
                                <i class="fas fa-user"></i>
                                <span class="nav-item">Staff</span>
                            </a></li>
                        </c:if>
                    <li><a href="orderdashboard">
                            <i class="fas fa-receipt"></i>
                            <span class="nav-item">Order</span>
                        </a></li>
                    <li><a href="request">
                            <i class="fas fa-bell">
                                <c:if test="${sessionScope.n != 0}">
                            <span style="color:red">${sessionScope.n}</span>
                            </c:if>
                            </i>
                        <span class="nav-item">Request</span>
                    </a></li>
                    <li><a href="bill" class="active">
                            <i class="fas fa-file-invoice-dollar"></i>
                            <span class="nav-item">Bills</span>
                        </a></li>
                        <c:if test="${sessionScope.user.role == 'admin'}">
                        <li><a href="menudashboard?cid=0">

                                <i class="fas fa-tasks"></i>
                                <span class="nav-item">Menu</span>
                            </a></li>
                        </c:if>
                    <li><a href="managetable">

                            <i class="fas fa-table"></i>
                            <span class="nav-item">Manage Table</span>
                        </a></li>
                    <li><a href="logout" class="logout">
                            <i class="fas fa-sign-out-alt"></i>
                            <span class="nav-item">Log out</span>
                        </a></li>
                </ul>
            </nav>

            <section class="main">
                <div class="main-top">
                    <h1>Quản lí thực đơn</h1>
                    <div class="profile-container">
                        <div class="profile-text">
                            <h2>Hey, <strong>${sessionScope.user.fullname}</strong></h2>
                            <p>${sessionScope.user.role}</p>
                        </div>
                        <div class="profile-image">
                            <img src="images/avatar.png" alt="User">
                        </div>
                    </div>
                </div>
                <div class="main-skills">
                    <div class="card">
                        <i class="fa-solid fa-utensils"></i>
                        <h3>Thêm món</h3>
                        <form action="menudashboard" method="post">
                            <div class="form-group">
                                <input name="cid" value="0" hidden>
                                <label for="food-name">Tên món:</label>
                                <input type="text" id="food-name" name="food-name" required>

                                <label for="food-detail">Mô tả món ăn:</label>
                                <input type="text" id="food-detail" name="food-detail" required>

                                <label for="food-price">Giá:</label>
                                <input type="number" id="food-price" name="food-price" required>

                                <label for="food-image">URL hình ảnh:</label>
                                <input type="text" id="food-image" name="food-image" required>

                                <label for="food-category">Loại món:</label>
                                <select name="food-category" id="cars">
                                    <c:forEach var="cate" items="${requestScope.categoryList}">
                                        <option value="${cate.id}" name="food-category">${cate.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button type="submit" style="background-color:green">Thêm món</button>
                            <button type="submit" onclick="location.href = 'menudashboard?action=createCate' ">Thêm danh mục món ăn</button>
                            <button type="submit" onclick="location.href = 'menudashboard?action=deleteCate' ">Xóa danh mục món ăn</button>
                        </form>
                        <c:if test="${not empty message && mode==1}">
                            <h5 style="color: green">${message}</h3>
                            </c:if> 
                            <c:if test="${not empty message && mode==0}">
                                <h5 style="color: red">${message}</h3>
                                </c:if>
                                </div>
                                </div>

                                <section class="main-course">
                                    <h1>Thực đơn</h1>
                                    <div class="course-box">
                                        <c:set var="id" value="${requestScope.cid}"/>
                                        <ul class="filters_menu">
                                            <li class="${id == 0 ?'active' : ''}" data-filter="*"><a href="menudashboard?cid=0">Tất cả</a></li>
                                                <c:forEach items="${requestScope.categoryList}" var="ca">
                                                <li class="${id == ca.id ? 'active' : ''}" data-filter="category${ca.id}"><a href="menudashboard?cid=${ca.id}">${ca.name}</a></li>
                                                </c:forEach>
                                        </ul>
                                        <div class="course">
                                            <c:forEach items="${requestScope.dataM}" var="dish">
                                                <div class="box category+${dish.categoryId}">
                                                    <h3>${dish.itemName}</h3>
                                                    <p>${dish.itemDescription}</p>
                                                    <p>Giá: <fmt:formatNumber value="${dish.price}" type="currency" currencySymbol="VND" /></p>
                                                    <button onclick="location.href = 'menudashboard?action=edit&item=${dish.id}'">Sửa</button>
                                                    <button onclick="deleteItem('${dish.id}')">Xóa</button>
                                                </div>
                                            </c:forEach>
                                        </div>
                                </section>
                                </section>
                                </div>
                                </body>
                                </html>

                                <style>
                                    .form-group {
                                        display: flex; /* Use Flexbox to arrange items in a row */
                                        align-items: center; /* Center align items vertically */
                                        border: 2px solid #ccc; /* Box border */
                                        padding: 10px; /* Inner spacing */
                                        margin: 5px 0; /* Space between groups */
                                        border-radius: 10px; /* Rounded corners */
                                    }

                                    label {
                                        margin-right: 5px; /* Space between label and input */
                                    }

                                    input[type="text"],
                                    input[type="number"],
                                    select {
                                        flex-grow: 1; /* Allow input fields to grow and fill available space */
                                        padding: 5px; /* Inner spacing */
                                    }
                                </style>


                                <script>
                                    const filterItems = document.querySelectorAll('[data-filter]');
                                    const boxes = document.querySelectorAll('.box');
                                    
                                    
                                    filterItems.forEach(item => {
                                        item.addEventListener('click', function () {
                                            // Remove active class from all filter items
                                            filterItems.forEach(i => i.classList.remove('active'));
                                            // Add active class to the clicked filter item
                                            this.classList.add('active');

                                            const filter = this.getAttribute('data-filter');

                                            boxes.forEach(box => {
                                                // Check if the box should be displayed or hidden
                                                if (filter === '*' || box.classList.contains(filter.substring(1))) {
                                                    box.classList.remove('hidden');
                                                } else {
                                                    box.classList.add('hidden');
                                                }
                                            });
                                        });
                                    });
                                    function deleteItem(itemId) {
                                        if (confirm("Bạn có muốn xóa món này không?")) {
                                            window.location.href = 'menudashboard?action=delete&item=' + itemId;
                                        }

                                    }
                                </script>