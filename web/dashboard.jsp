
<%-- 
    Document   : dashboard
    Created on : Oct 18, 2024, 1:29:19 PM
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
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Group 4 Restaurant</title>
        <link rel="shortcut icon" href="images/favicon_3.png" type="">
        <link rel="stylesheet" href="css/for_dashboard.css" />
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
                        <i class="fas fa-bell"><c:if test="${sessionScope.n != 0}">
                            <span style="color:red">${sessionScope.n}</span>
                            </c:if></i>
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
                    <h1>Trang chủ</h1>
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
                        <i class="fas fa-file-invoice-dollar"></i>
                        <h3>Tổng số hóa đơn trong ngày</h3>
                        <p>${requestScope.billQuantity}</p>
                        <button onclick="window.location.href = 'bill'">Chi tiết</button>
                    </div>
                    <div class="card">
                        <i class="fas fa-receipt"></i>
                        <h3>Tổng số đơn hàng trong ngày</h3>
                        <p>${requestScope.orderQuantity}</p>
                        <button onclick="window.location.href = 'orderdashboard'">Chi tiết</button>
                    </div>
                    <div class="card">
                        <i class="fas fa-tasks"></i>
                        <h3>Tổng số món được gọi trong ngày</h3>
                        <p>${requestScope.itemQuantity}</p>
                    </div>
                </div>

                <section class="main-course">
                    <h1>Thống kê</h1>
                    <div class="course-box">
                        <br>
                        <div class="course">
                            <div class="box">
                                <h3>Tổng thu nhập của nhà hàng</h3>
                                <p><fmt:formatNumber value="${requestScope.total}" type="currency" currencySymbol="VND" /></p>
                                <i class="fa-solid fa-utensils"></i>
                            </div>
                            <div class="box">
                                <h3>Tổng thu nhập ngày hôm nay</h3>
                                <p><fmt:formatNumber value="${requestScope.totalToday}" type="currency" currencySymbol="VND" /></p>
                                <i class="fa-solid fa-utensils"></i>
                            </div>
                        </div>
                    </div>
                </section>
            </section>
        </div>
    </body>
</html>
