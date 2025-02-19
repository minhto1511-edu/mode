<%-- 
    Document   : staff
    Created on : Oct 18, 2024, 1:50:52 PM
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
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Group 4 Restaurant</title>
        <link rel="shortcut icon" href="images/favicon.png" type="">
        <link rel="stylesheet" href="css/for_dashboard.css" />
        <!-- Font Awesome Cdn Link -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <script>
            function changeStatus(numberAction, otid) {
                window.location = 'orderdashboard?action='+numberAction+'&item='+otid;
            }

        </script>
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
                        <span class="nav-item">Request </span>
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
                    <h1>Chi Tiết Đơn Hàng</h1>
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
                <br>

                
                </br>


                <table border="1">
                    <thead>
                        <tr>
                            <th>Bàn</th>
                            <th>Tên món</th>
                            <th>Số lượng</th>
                            <th>Thời gian</th>
                            <th>Trạng Thái</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.list}" var="i">
                            <tr>
                                <td>${i.tableNumber}</td>
                                <td>${i.itemName}</td>
                                <td>${i.quantity}</td>
                                <td>${i.requestTime}</td>
                                <td class="action-buttons">
                                    <button class="btn btn-finish" ${i.status == 'pending'?'disabled' : ''} onclick="window.location.href='request?rid=' + ${i.requestId}">Accept</button>
                                </td>
                            </tr>
                        </c:forEach>


                        <!-- Add more rows as needed -->
                    </tbody>
                </table>


            </section>
        </section>
    </div>
</body>
</html></span>


<style>
    .add-data {
        display: flex;
        justify-content: flex-end; /* Align the button to the right */
        margin: 20px 50px 20px 20px;
    }

    .add-data button {
        background-color: #60a5d3; /* Green background color */
        color: white; /* White text */
        padding: 10px 20px; /* Padding for a comfortable size */
        border: none; /* Remove default border */
        border-radius: 5px; /* Rounded corners */
        cursor: pointer; /* Pointer cursor on hover */
        font-size: 16px; /* Font size */
        transition: background-color 0.3s ease; /* Smooth background color transition */
        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2); /* Subtle shadow for a 3D effect */
    }

    .add-data button:hover {
        background-color: #60a5d3; /* Slightly darker green on hover */
    }

    .add-data button:active {
        background-color: #60a5d3; /* Even darker green when clicked */
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); /* Adjusted shadow on click */
        transform: translateY(2px); /* Button press effect */
    }

    table {
        width: 80%;
        margin: 20px auto;
        border-collapse: separate; /* Use separate borders to apply border-radius */
        border-spacing: 0;
        border-radius: 10px; /* Rounded corners */
        overflow: hidden; /* Ensure content respects the rounded corners */
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2);
    }

    th, td {
        padding: 12px;
        text-align: left;
        border-bottom: 1px solid #ddd;
    }

    th {
        background-color: #60a5d3;
        color: white;
        font-weight: bold;
        text-align: center;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    tr:hover {
        background-color: #ddd;
    }

    td {
        color: #333;
        text-align: center;
    }

    /* Align action buttons to the right */
    .action-buttons {
        display: flex;
        justify-content: center;
        gap: 8px;
    }

    .btn {
        padding: 8px 12px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-size: 14px;
        transition: background-color 0.3s;
    }

    .btn-edit {
        background-color: #60a5d3;
        color: white;
    }

    .btn-edit:hover {
        background-color: #60a5d3;
    }

    .btn-delete {
        background-color: #f44336;
        color: white;
    }

    .btn-delete:hover {
        background-color: #d32f2f;
    }
    
    .btn-finish {
        background-color: #ffc107;
        color: white;
    }

    .btn-finish:hover {
        background-color: #d32f2f;
    }

    .btn-finish:disabled {
        background-color: gray;
    }
    .action-buttons button:disabled{
        background-color: gray;
    }
</style>
