<%-- 
    Document   : bill
    Created on : Oct 18, 2024, 2:08:20 PM
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
        <title> Restaurant</title>
        <link rel="shortcut icon" href="images/favicon.png" type="">
        <link rel="stylesheet" href="css/bill.css" />
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
                    <h1>Quản lí hóa đơn</h1>
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
<!--                <div class="main-skills">
                    <div class="card">
                        <i class="fa-solid fa-utensils"></i>
                        <h3>Hóa đơn chưa thanh toán</h3>
                        <p>10 hóa đơn</p>
                        <button>Chi tiết</button>
                    </div>
                    <div class="card">
                        <i class="fa-solid fa-utensils"></i>
                        <h3>Hóa đơn đã thanh toán</h3>
                        <p>20 hóa đơn</p>
                        <button>Chi tiết</button>
                    </div>
                    <div class="card">
                        <i class="fa-solid fa-utensils"></i>
                        <h3>Hóa đơn đã hủy</h3>
                        <p>5 hóa đơn</p>
                        <button>Chi tiết</button>
                    </div>
                </div>-->

                <section class="main-course">
<!--                    <h1>Chi tiết đơn hàng</h1>-->
                    <div class="course-box">
                        
                        <div class="course">
                            <c:forEach items="${requestScope.listBill}" var="b">
                            <div class="box unpaid"> <!-- Thêm class cho trạng thái chưa thanh toán -->
                                <h3>Hóa đơn ${b.orderId}</h3>
                                <p>${b.billTime}</p>
                                <p>Tổng: <fmt:formatNumber value="${b.totalAmount}" type="currency" currencySymbol="VND" /></p>
                                <button onclick="window.location.href='bill?action=view&oid=${b.orderId}'">Chi tiết </button>
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

// Lấy tất cả các mục li và các box
    const billFilterItems = document.querySelectorAll('.course-box ul li');
    const billBoxes = document.querySelectorAll('.course .box');

// Hiển thị các box có class 'unpaid' khi trang được tải
    billBoxes.forEach(box => {
        if (box.classList.contains('unpaid')) {
            box.classList.add('active');
        } else {
            box.classList.remove('active');
        }
    });

// Lặp qua từng mục li
    billFilterItems.forEach(item => {
        item.addEventListener('click', function () {
            // Bỏ active khỏi tất cả các li và thêm active vào mục đang được nhấp
            billFilterItems.forEach(i => i.classList.remove('active'));
            this.classList.add('active');

            // Lấy giá trị data-filter từ mục li được nhấp
            const filterValue = this.getAttribute('data-filter');

            // Lặp qua tất cả các box để hiển thị hoặc ẩn
            billBoxes.forEach(box => {
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
