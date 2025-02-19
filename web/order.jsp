<%-- 
    Document   : order
    Created on : Oct 17, 2024, 8:35:36 AM
    Author     : dung0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- Basic -->
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- Mobile Metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <!-- Site Metas -->
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <link rel="shortcut icon" href="images/favicon.png" type="">

        <title> Restaurant - Cart </title>

        <!-- bootstrap core css -->
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
        <!--FontAwesome-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <!--owl slider stylesheet -->
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" />
        <!-- nice select  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/css/nice-select.min.css" integrity="sha512-CruCP+TD3yXzlvvijET8wV5WxxEh5H8P4cmz0RFbKK6FlZ2sYl3AEsKlLPHbniXKSrDdFewhbmBK5skbdsASbQ==" crossorigin="anonymous" />
        <!-- font awesome style -->
        <link href="css/font-awesome.min.css" rel="stylesheet" />

        <!-- Custom styles for this template -->
        <link href="css/style.css" rel="stylesheet" />
        <!-- responsive style -->
        <link href="css/responsive.css" rel="stylesheet" />
        <!-- Cart page CSS -->
        <link href="css/order.css" rel="stylesheet" />
    </head>

    <body class="sub_page">

        <div class="hero_area">
            <div class="bg-box">
                <img src="images/hero-bg.jpg" alt="">
            </div>
            <!-- header section starts -->
            <header class="header_section">
                <div class="container">
                    <nav class="navbar navbar-expand-lg custom_nav-container ">
                        <a class="navbar-brand" href="home">
                            <span>
                                Restaurant
                            </span>
                        </a>

                        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class=""> </span>
                        </button>

                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav  mx-auto ">
                                <li class="nav-item">
                                    <a class="nav-link" href="home">Trang Chủ</a>
                                </li>
                                <c:if test="${sessionScope.tid!=null}">
                                <li class="nav-item active" action="menu?cid=0" method="post">
                                    <a class="nav-link" href="menu?cid=0">Thực Đơn<span class="sr-only">(current)</span></a>
                                </li>
                                </c:if>
                                
                            </ul>
                            <div class="user_option">
                                <c:if test="${sessionScope.tid==null}">
                                <button class="btn  my-2 my-sm-0 nav_search-btn" type="submit" onclick="location.href = 'choosetable'">
                                        <i class="fa-solid fa-table-list"></i>
                                    </button>
                                </c:if>
                                <c:if test="${sessionScope.tid!=null}">
                                <a class="cart_link" href="cart">
                                    <svg version="1.1" id="Capa_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 456.029 456.029" style="enable-background:new 0 0 456.029 456.029;" xml:space="preserve">
                                    <g>
                                    <g>
                                    <path d="M345.6,338.862c-29.184,0-53.248,23.552-53.248,53.248c0,29.184,23.552,53.248,53.248,53.248
                                          c29.184,0,53.248-23.552,53.248-53.248C398.336,362.926,374.784,338.862,345.6,338.862z" />
                                    </g>
                                    </g>
                                    <g>
                                    <g>
                                    <path d="M439.296,84.91c-1.024,0-2.56-0.512-4.096-0.512H112.64l-5.12-34.304C104.448,27.566,84.992,10.67,61.952,10.67H20.48
                                          C9.216,10.67,0,19.886,0,31.15c0,11.264,9.216,20.48,20.48,20.48h41.472c2.56,0,4.608,2.048,5.12,4.608l31.744,216.064
                                          c4.096,27.136,27.648,47.616,55.296,47.616h212.992c26.624,0,49.664-18.944,55.296-45.056l33.28-166.4
                                          C457.728,97.71,450.56,86.958,439.296,84.91z" />
                                    </g>
                                    </g>
                                    <g>
                                    <g>
                                    <path d="M215.04,389.55c-1.024-28.16-24.576-50.688-52.736-50.688c-29.696,1.536-52.224,26.112-51.2,55.296
                                          c1.024,28.16,24.064,50.688,52.224,50.688h1.024C193.536,443.31,216.576,418.734,215.04,389.55z" />
                                    </g>
                                    </g>
                                    <g>
                                    </g>
                                    <g>
                                    </g>
                                    <g>
                                    </g>
                                    <g>
                                    </g>
                                    <g>
                                    </g>
                                    <g>
                                    </g>
                                    <g>
                                    </g>
                                    <g>
                                    </g>
                                    <g>
                                    </g>
                                    <g>
                                    </g>
                                    <g>
                                    </g>
                                    <g>
                                    </g>
                                    <g>
                                    </g>
                                    <g>
                                    </g>
                                    <g>
                                    </g>              
                                    </svg>
                                </a>
                                <form class="form-inline" action="order" method="post" >
                                    <input type="text" name="choose" value="2" hidden/>
                                    <input type="text" name="tid" value="${sessionScope.tid}" hidden>
                                    <button class="btn  my-2 my-sm-0 nav_search-btn" type="submit" >
                                        <i class="fa-solid fa-eye" aria-hidden="true"></i>
                                    </button>
                                </form>
                                </c:if>
                            </div>
                        </div>
                    </nav>
                </div>
            </header>
            <!-- end header section -->
        </div>

        <!-- Cart content section -->
        <div class="container cart-container">
            <c:set var="it" value="${requestScope.tableInfo}"/>
            <c:set var="se" value="${requestScope.orderInfo}"/>
            <h2>Theo dõi đơn hàng</h2>
            <h3>Số Bàn: ${it.tableNumber}</h3>
            <h3 hidden>Mã Đơn:${se.orderId} </h3>
            <table id="cart-table">
                <thead>
                    <tr>
                        <th>Sản phẩm</th>
                        <th>Số lượng</th>
                        <th>Tình trạng món</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody id="cart-items">
                    <!-- Các sản phẩm trong giỏ hàng sẽ được hiển thị tại đây -->
                    <c:forEach items="${requestScope.dataAccept}" var="i">
                        <tr>
                            <td>${i.itemInMenu.itemName}</td>
                            <td>${i.quantity}</td>
                            <td>${i.item_status}</td>
                            <td>
                                <c:if test="${i.item_status != 'served'}">
                                <c:if test="${i.item_status != 'unaccepted' && (map[i.orderItemId] == 'noRequest' || map[i.orderItemId] == 'pending')}">
                                    <button onclick="window.location.href = 'order?action=request&orderItemId=' + ${i.orderItemId}" id="checkout-button">Giục ra món</button>
                                </c:if>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            
            <div id="total-price">Tổng cộng: <fmt:formatNumber value="${requestScope.totalOrder}" type="currency" currencySymbol="VND" /></div>

            
        </div>

        <!-- footer section -->
        <footer class="footer_section">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 footer-col">
                        <div class="footer_contact">
                            <h4>Liên hệ với chúng tôi</h4>
                            <div class="contact_link_box">
                                <a href="">
                                    <i class="fa fa-map-marker" aria-hidden="true"></i>
                                    <span>Đại học FPT - Khu Công nghệ cao Hòa Lạc, Hà Nội</span>
                                </a>
                                <a href="">
                                    <i class="fa fa-phone" aria-hidden="true"></i>
                                    <span>Call 024 7300 1866</span>
                                </a>
                                <a href="">
                                    <i class="fa fa-envelope" aria-hidden="true"></i>
                                    <span>daihocfpt@fpt.edu.vn</span>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 footer-col">
                        <div class="footer_detail">
                            <a href="menu?cid=0" class="footer-logo">Group 4 - Restaurant</a>
                            <p>Chỉ cần vài bước đơn giản, bạn có thể đặt món trực tuyến một cách dễ dàng.</p>
                            <div class="footer_social">
                                <a href="">
                                    <i class="fa fa-facebook" aria-hidden="true"></i>
                                </a>
                                <a href="">
                                    <i class="fa fa-twitter" aria-hidden="true"></i>
                                </a>
                                <a href="">
                                    <i class="fa fa-linkedin" aria-hidden="true"></i>
                                </a>
                                <a href="">
                                    <i class="fa fa-instagram" aria-hidden="true"></i>
                                </a>
                                <a href="">
                                    <i class="fa fa-pinterest" aria-hidden="true"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 footer-col">
                        <h4>Giờ mở cửa</h4>
                        <p>Mỗi ngày: 10.00 AM - 10.00 PM</p>
                    </div>
                </div>
                <div class="footer-info">
                    <p>&copy; <span id="displayYear"></span> All Rights Reserved By <a href="https://www.facebook.com/heizxje/">Heizxje</a></p>
                </div>
            </div>
        </footer>
        <!-- end footer section -->
        <!-- Menu Javascript -->
<!--        <script src="js/menu.js"></script>-->
        <!-- jQery -->
        <script src="js/jquery-3.4.1.min.js"></script>
        <!-- popper js -->
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <!-- bootstrap js -->
        <script src="js/bootstrap.js"></script>
        <!-- owl slider -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
        <!-- isotope js -->
        <script src="https://unpkg.com/isotope-layout@3.0.4/dist/isotope.pkgd.min.js"></script>
        <!-- nice select -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/js/jquery.nice-select.min.js"></script>
        <!-- custom js -->
        <script src="js/custom.js"></script>
        <!-- Google Map -->
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCh39n5U-4IoWpsVGUHWdqB6puEkhRLdmI&callback=myMap"></script>
        <!-- End Google Map -->

    </body>
</html>
