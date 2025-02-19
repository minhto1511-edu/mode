<%-- 
    Document   : order_dashboard
    Created on : Oct 18, 2024, 1:56:25 PM
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
        <link rel="shortcut icon" href="images/favicon.png" type="">
        <link rel="stylesheet" href="css/order_1.css" />
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
                    <h1>Quản lí đơn hàng</h1>
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


                <section class="main-course">
                    <div class="course-box">

                        <ul>
                            <!--sửa cái này-->
                            <li data-filter=".pay" class="active">Thanh Toán</li>
                            <li data-filter=".unpaid">Chưa Thanh Toán</li>
                        </ul>

                        <div class="course">
                            <c:forEach items="${requestScope.dataOrder}" var="i">
                                <div class="box ${i.orderStatus}">
                                    <h3>Đơn số: ${i.orderId}</h3>
                                    <c:forEach items="${sessionScope.listTable}" var="t">
                                        <c:if test="${i.tableId == t.tableId}">
                                            <p>Số Bàn: ${t.tableNumber}</p>
                                        </c:if>
                                    </c:forEach>
                                    <p>Thời gian: ${i.createdAt}</p>
                                    <p>Tổng: <fmt:formatNumber value="${i.totalAmount}" type="currency" currencySymbol="VND" /></p>
                                    <form style="display: inline-block" action="orderdashboard" method="get">
                                        <input name="oid" value ="${i.orderId}" hidden>
                                        <button>Chi tiết</button>
                                    </form>

                                    <i class="fa-solid fa-utensils"></i>
                                </div>
                            </c:forEach>
                        </div>

                    </div>            
                </section>
            </section>
        </div>
    </body>
</html>

<script>
    // JavaScript code here
    // Lấy tất cả các mục li và các box
// Lấy tất cả các mục li và các box
    const filterItems = document.querySelectorAll('.course-box ul li');
    const boxes = document.querySelectorAll('.course .box');

// Hiển thị các box có class 'waiting' khi trang được tải
    boxes.forEach(box => {
        if (box.classList.contains('pay')) {
            box.classList.add('active');
        } else {
            box.classList.remove('active');
        }
    });

// Lặp qua từng mục li
    filterItems.forEach(item => {
        item.addEventListener('click', function () {
            // Bỏ active khỏi tất cả các li và thêm active vào mục đang được nhấp
            filterItems.forEach(i => i.classList.remove('active'));
            this.classList.add('active');

            // Lấy giá trị data-filter từ mục li được nhấp
            const filterValue = this.getAttribute('data-filter');

            // Lặp qua tất cả các box để hiển thị hoặc ẩn
            boxes.forEach(box => {
                // Nếu box có class tương ứng với filterValue, hiển thị nó, ngược lại ẩn đi
                if (box.classList.contains(filterValue.substring(1))) {
                    box.classList.add('active');
                } else {
                    box.classList.remove('active');
                }
            });
        });
    });


</script>
