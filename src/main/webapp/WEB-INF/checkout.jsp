<%-- 
Document   : checkout
Created on : Oct 14, 2024, 8:11:49 PM
Author     : Tran Quoc Thai - CE181618 
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title> Astatine04 - Check Out </title>
        <link rel="shortcut icon" type="image/png" href="assets/img/Tittle-web-icon/Logo_Dark.ico" />

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="assets/css/bootstrap.min.css" />

        <!-- Slick -->
        <link type="text/css" rel="stylesheet" href="assets/css/slick.css" />
        <link type="text/css" rel="stylesheet" href="assets/css/slick-theme.css" />

        <!-- nouislider -->
        <link type="text/css" rel="stylesheet" href="assets/css/nouislider.min.css" />

        <!-- Font Awesome Icon -->
        <link rel="stylesheet" href="assets/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <!-- Icon New-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="assets/css/style.css" />
        <link rel="stylesheet" href="assets/css/modal.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->


    </head>

    <!-- HEADER -->
    <header>
        <!-- TOP HEADER -->
        <div id="top-header">
            <div class="container">
                <ul class="header-links pull-left">
                    <li><a href="#"><i class="fa fa-envelope-o"></i> Astatine04@info.com</a></li>
                    <li><a href="#"><i class="fa fa-map-marker"></i> Can Tho, Viet Nam</a></li>
                </ul>
                <ul class="header-links pull-right">
                    <!-- <li><a href="#"><i class="fa fa-dollar"></i> USD</a></li> -->
                    <li><a href="#"><i class="fa fa-user-o"></i> About us</a></li>
                </ul>
            </div>
        </div>
        <!-- /TOP HEADER -->

        <!-- MAIN HEADER -->
        <div id="header">
            <!-- container -->
            <div class="container">

                <!-- row -->
                <div class="row" style="display: flex;
                         justify-content: center;
                         align-items: center;">
                    <!-- LOGO -->
                    <div class="col-md-3">
                        <div class="header-logo">
                            <a href="Home" class="logo">
                                <img src="assets/img/logo.png" alt="">
                            </a>
                        </div>
                    </div>
                    <!-- /LOGO -->

                    <!-- SEARCH BAR -->
                    <div class="col-md-6">
                        <div class="header-search">
                            <form action="Store?action=search" method="get">
                                <input class="input" name="search" placeholder="Search here">
                                <button type="submit" class="search-btn"><i class="fa fa-search"></i></button>
                            </form>
                        </div>
                    </div>
                    <!-- /SEARCH BAR -->
                    <!--              
                    <!-- ACCOUNT -->

                    <div class="col-md-3 clearfix">
                        <div class="header-ctn">
                            <!-- Cart -->
<!--                            <div class="dropdown" id="cart-dropdown" onsubmit="this.submit()">
                                <a class="dropdown-toggle" href="Checkout" id="navbarDropdownMenuLink" 
                                   aria-haspopup="true" aria-expanded="false">
                                    <i class="bi bi-bag-heart-fill" style="font-size: 24px;"></i>
                                    <div class="qty num-order">${counted != null ? counted: 0}</div>
                                </a>
                            </div>-->
                            <!-- /Cart -->
                                <!-- Account -->   
                            <%
                               String email = (String) session.getAttribute("email");
                            %>

                            <div id="account-dropdown" style="<%= email != null && !email.isEmpty() ? "display: none;" : "display: inline-block;" %>;">
                                <a class="dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                                   aria-haspopup="true" aria-expanded="false" style="display: flex; align-items: center; gap: 20px;">
                                    <i class="bi bi-person-fill" style="font-size: 24px;"></i>
                                    <p style="margin: 0; font-size: 16px">Account</p>
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                    <a class="dropdown-item" href="Login">Log In</a>
                                    <hr>
                                    <a class="dropdown-item" href="Signup">Sign up</a>
                                </div>
                            </div>                                                           
                            <div>           
                                <div id="account-success" style="<%= email != null && !email.isEmpty() ? "display: block;" : "display: none;" %>">
                                    <div class="dropdown">
                                        <div style="display: flex; align-items: center; gap: 20px;">
                                            <div>
                                                <img src="../assets/img/default-avatar.png" width="35px" height="35px" alt="Avatar User"/>
                                            </div> 
                                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                                <div class="dropdown__menu--items">
                                                    <a class="dropdown-item" href="Profile?action=edit">Profile</a>
                                                    <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#5f6368"><path d="M480-480q-66 0-113-47t-47-113q0-66 47-113t113-47q66 0 113 47t47 113q0 66-47 113t-113 47ZM160-240v-32q0-34 17.5-62.5T224-378q62-31 126-46.5T480-440q66 0 130 15.5T736-378q29 15 46.5 43.5T800-272v32q0 33-23.5 56.5T720-160H240q-33 0-56.5-23.5T160-240Zm80 0h480v-32q0-11-5.5-20T700-306q-54-27-109-40.5T480-360q-56 0-111 13.5T260-306q-9 5-14.5 14t-5.5 20v32Zm240-320q33 0 56.5-23.5T560-640q0-33-23.5-56.5T480-720q-33 0-56.5 23.5T400-640q0 33 23.5 56.5T480-560Zm0-80Zm0 400Z"/></svg>                                                </div>                   
                                                    <c:if test="${isAdmin == true}">
                                                    <div class="dropdown__menu--items">
                                                        <a class="dropdown-item" href="User">Admin</a>
                                                        <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#5f6368"><path d="M480-440q-59 0-99.5-40.5T340-580q0-59 40.5-99.5T480-720q59 0 99.5 40.5T620-580q0 59-40.5 99.5T480-440Zm0-80q26 0 43-17t17-43q0-26-17-43t-43-17q-26 0-43 17t-17 43q0 26 17 43t43 17Zm0 440q-139-35-229.5-159.5T160-516v-244l320-120 320 120v244q0 152-90.5 276.5T480-80Zm0-400Zm0-315-240 90v189q0 54 15 105t41 96q42-21 88-33t96-12q50 0 96 12t88 33q26-45 41-96t15-105v-189l-240-90Zm0 515q-36 0-70 8t-65 22q29 30 63 52t72 34q38-12 72-34t63-52q-31-14-65-22t-70-8Z"/></svg>
                                                    </div>
                                                </c:if>
                                                <hr>
                                                <div class="dropdown__menu--items">
                                                    <a class="dropdown-item" href="Logout?view=logout">Log out</a>
                                                    <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#5f6368"><path d="M200-120q-33 0-56.5-23.5T120-200v-560q0-33 23.5-56.5T200-840h280v80H200v560h280v80H200Zm440-160-55-58 102-102H360v-80h327L585-622l55-58 200 200-200 200Z"/></svg>
                                                </div>
                                            </div>
                                            <div class="email-container dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown">
                                                <p>${userName}</p>
                                            </div>
                                        </div>                                 
                                    </div>
                                </div>
                            </div>
                            <!-- /Account -->
                            <c:set var="shop" value="${SHOP}"></c:set>
                            <c:set var="totalPrice" value="0" />
                            <c:set var="count" value="0"/>
                            <c:forEach items="${shop}" var="s">
                                <c:set var="count" value="${count + 1}"/>
                                <c:set var="counted" value="${count}"/>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- /ACCOUNT -->
                </div>
                <!-- row -->
            </div>
            <!-- container -->
        </div>
        <!-- /MAIN HEADER -->
    </header>
    <!-- /HEADER -->

    <div id="wrapper">
        <div id="content">
            <!-- BREADCRUMB -->
            <div id="breadcrumb" class="section">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">
                        <div class="col-md-12">
                            <h3 class="breadcrumb-header">Checkout</h3>
                            <ul class="breadcrumb-tree">
                                <li><a href="Home">Home</a></li>
                                <li class="active">Checkout</li>
                            </ul>
                        </div>
                    </div>
                    <!-- /row -->
                </div>
                <!-- /container -->
            </div>
            <!-- /BREADCRUMB -->

            <!-- SECTION -->
            <div class="section">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">
                        <c:choose>
                            <c:when test="${not empty messOrder}"> 
                                <div class="modal processing" id="orderProcessingModal">
                                    <img src="assets/img/Gif/brain.gif" alt="alt" style="width: 100px; height: 100px; margin: auto 0;"/>
                                    <div class="modal-header" style="border: none; margin: 0;">Out of Product!</div>
                                    <div class="modal-body" style="border: none; margin: 0;">${messOrder}</div>
                                    <div class="modal-footer" style="border: none; margin: 0;">
                                        <button class="btn btn-cancel" onclick="window.location.href = 'Checkout'">Ok</button>
                                    </div>
                                </div>    
                            </c:when>
                            <c:when test="${not empty messOrderSuccess}">
                                <div class="modal processing" id="orderProcessingModal">
                                    <img src="assets/img/Gif/shopping.gif" alt="alt" style="width: 100px; height: 100px; margin: auto 0;"/>
                                    <div class="modal-header" style="border: none; margin: 0;">Order Successful!</div>
                                    <div class="modal-footer" style="border: none; margin: 0;">
                                        <button class="btn btn-cancel" onclick="window.location.href = 'Home'">Home</button>
                                        <button class="btn btn-ok" onclick="window.location.href = 'Profile?action=order'">Order</button>
                                    </div>
                                </div>                            
                            </c:when>
                        </c:choose>
                        <div class="col-md-7">
                            <!-- Billing Details -->
                            <div class="billing-details">
                                <div class="section-title">
                                    <h3 class="title">Billing address</h3>
                                </div>
                                <div class="row cart-title"
                                     style="display: flex;align-items: center;justify-content: center;margin-bottom: 10px;">
                                    <div style="padding: 0;" class="col-md-1 billing-details--item">Image</div>
                                    <div class="col-md-4 billing-details--item">Name</div>
                                    <div class="col-md-2 billing-details--item">Price</div>
                                    <div style="width: 80px;" class="col-md-2 billing-details--item">Quantity</div>
                                    <div style="padding-left: 30px;" class="col-md-3 billing-details--item">Total Price</div>
                                    <div class=" billing-details--item">
                                    </div>
                                </div>
                                <div class="cart-container">
                                    <c:set value="${discount}" var="dis"/>                    
                                    <c:set var="shop" value="${SHOP}"></c:set>
                                    <c:set var="totalPrice" value="0" />
                                    <c:set var="count" value="0"/>
                                    <c:forEach items="${shop}" var="s">
                                        <c:set var="count" value="${count + 1}"/>
                                        <c:set var="counted" value="${count}"/>
                                        <div class="row cart-item align-items-center" style="margin-bottom: 10px;">                             
                                            <a href="Products?view=prod-details&id=${s.value.prod.productId}" class="col-md-1 billing-details--item text-center" style="width: 80px; height: fit-content;">
                                                <img style="width: 100%; object-fit: contain;" src="${s.value.prod.image}" alt="${s.value.prod.productName}">
                                            </a>
                                            <a href="Products?view=prod-details&id=${s.value.prod.productId}" class="col-md-4 billing-details--item">${s.value.prod.productName}</a>
                                            <div class="col-md-2 billing-details--item text-center">${s.value.prod.price}</div>
                                            <div class="input-number col-md-2 billing-details--item text-center" style="display: flex; align-items: center; justify-content: center;">
                                                <input id="qty-${s.value.prod.productId}" style="height: 35px; width: 40px;padding: 0; text-align: center;" class="qty" type="number" value="${s.value.quantity}" min="1" max="${s.value.prod.countInStock}" readonly>
                                                <div class="d-flex flex-column" style="margin-left: 5px;">
                                                    <a href="Quantity?action=inc&id=${s.value.prod.productId}" class="qty-up" style="cursor: pointer; padding: 0;">+</a>
                                                    <a href="Quantity?action=dec&id=${s.value.prod.productId}"  class="qty-down" style="cursor: pointer; padding: 0;">-</a>
                                                </div>
                                            </div>                     
                                            <div class="col-md-2 billing-details--item text-center">${(s.value.prod.price * s.value.quantity)}</div>
                                            <a href="Checkout?action=remove&id=${s.value.prod.productId}" class="col-md-1 billing-details--item text-center">
                                                <i class="bi bi-trash3-fill" title="Remove item" style="cursor: pointer;"></i>
                                            </a>
                                        </div> 
                                        <c:set value="${(s.value.prod.price * s.value.quantity)}" var="total"/>
                                        <c:set value="${total + totalPrice}" var="totalPrice"/>
                                    </c:forEach> 
                                    <c:set value="${totalPrice * (dis/100)}" var="discounted"/>
                                </div>
                            </div>
                        </div>   
                        <!-- Order Details -->
                        <div class="col-md-5 order-details">
                            <div class="section-title text-center">
                                <h3 class="title">Your Order</h3>
                            </div>
                            <div class="order-summary">
                                <div class="order-col">
                                    <div><strong>PRODUCT</strong></div>
                                    <div><strong>TOTAL</strong></div>
                                </div>
                                <div class="order-products">
                                    <div class="order-col">
                                        <div>Provisional</div>
                                        <div id="totalPriceDisplay">$${totalPrice}</div>
                                    </div>
                                    <div class="order-col">
                                        <div>Shipping</div>
                                        <div>
                                            <c:choose>
                                                <c:when test="${totalPrice == 0}">
                                                    <c:set value="${0}" var="ship"/>
                                                    <strong>+$0</strong>
                                                </c:when>
                                                <c:when test="${totalPrice <= 50}">
                                                    <c:set value="${100}" var="ship"/>
                                                    <strong>+$30</strong>
                                                </c:when>
                                                <c:when test="${totalPrice <= 100}">
                                                    <c:set value="${50}" var="ship"/>
                                                    <strong>+$25</strong>
                                                </c:when>
                                                <c:when test="${totalPrice <= 200}">
                                                    <c:set value="${30}" var="ship"/>
                                                    <strong>+$20</strong>
                                                </c:when>
                                                <c:when test="${totalPrice <= 300}">
                                                    <c:set value="${10}" var="ship"/>
                                                    <strong>+$10</strong>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:set value="${0}" var="ship"/>
                                                    <strong>FREE</strong>
                                                </c:otherwise>
                                            </c:choose>                      
                                        </div>
                                    </div>
                                    <div class="dropdown" id="couponDropdown"  style="color:#00a089; padding: 10px 0;">
                                        <a href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <span  style="color:#00a089">Voucher</span>
                                        </a>
                                        <c:if test="${not empty discount}">${applied}</c:if>
                                            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                                <div class="coupon-dropdown-content"> 
                                                <c:forEach items="${vouchers}" var="voucher">
                                                    <div class="coupon">
                                                        <div class="coupon-left">
                                                            <img alt="Astatine logo" height="40" src="assets/img/logo.png" width="40" />
                                                        </div>
                                                        <div class="coupon-mid">
                                                            <div style="font-weight: 600;color: #00a089;"><span>Get ${voucher.discount}% off your total bill</span></div>
                                                            <div style="font-size: 13px; color: gray"> Expired: ${voucher.expiry}</div>
                                                        </div>
                                                        <div class="coupon-right">
                                                            <div class="expired" style="background: #00a089 ;padding: 6px;border-radius: 5px; color: white;">
                                                                <a href="Checkout?voucherId=${voucher.voucherId}&dis=${voucher.discount}">Apply</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>                              
                                            </div>
                                        </div>
                                    </div>

                                    <style>
                                        .dropdown-menu {
                                            min-width: 300px;
                                            padding: 15px;
                                            max-height: 200px; /* Giảm chiều cao tối đa xuống */
                                            overflow-y: auto; /* Đổi lại thành auto để chỉ hiện thanh cuộn khi cần */
                                        }

                                        /* Khi có 2 coupon trở lên */
                                        .coupon-dropdown-content:has.coupon:nth-child(2) {
                                            max-height: 200px; /* Chiều cao cho 2 coupon */
                                            overflow-y: scroll;
                                        }

                                        /* Style cho thanh cuộn */
                                        .dropdown-menu::-webkit-scrollbar {
                                            width: 8px;
                                        }

                                        .dropdown-menu::-webkit-scrollbar-track {
                                            background: #f1f1f1;
                                            border-radius: 10px;
                                        }

                                        .dropdown-menu::-webkit-scrollbar-thumb {
                                            background: #00bfa5;
                                            border-radius: 10px;
                                            border: 2px solid #f1f1f1;
                                        }

                                        .dropdown-menu::-webkit-scrollbar-thumb:hover {
                                            background: #00a089;
                                        }

                                        /* Đảm bảo mỗi coupon có chiều cao cố định */
                                        .coupon {
                                            height: 80px; /* Hoặc một giá trị phù hợp */
                                            margin-bottom: 10px;
                                        }

                                        .coupon-mid {
                                            display: flex;
                                            flex-direction: column;
                                        }

                                        .coupon-right a{
                                            color: white;
                                        }

                                        /* Phần CSS còn lại giữ nguyên */
                                        .coupon-dropdown-content .coupon {
                                            display: flex;
                                            padding: 10px;
                                            border: 1px solid #ddd;
                                            border-radius: 8px;
                                            background: #fff;
                                            gap: 8px;
                                            justify-content: center;
                                            align-items: center;
                                        }

                                        .coupon-left {
                                            display: flex;
                                            align-items: center;
                                            gap: 10px;
                                        }

                                        .coupon-left img {
                                            border-radius: 5px;
                                            background-color: #00bfa5;
                                        }

                                        .coupon-right {
                                            display: flex;
                                            flex-direction: column;
                                            align-items: flex-end;
                                            gap: 8px;
                                        }

                                        .coupon-right-numbers span {
                                            font-weight: bold;
                                            color: #ff4444;
                                        }

                                        .coupon-right .btn-primary {
                                            padding: 5px 15px;
                                            font-size: 14px;
                                        }

                                        .view-all-coupons {
                                            display: block;
                                            text-align: center;
                                            margin-top: 15px;
                                            padding: 8px;
                                            background: #f8f9fa;
                                            border-radius: 5px;
                                            text-decoration: none;
                                            color: #333;
                                        }

                                        .view-all-coupons:hover {
                                            background: #e9ecef;
                                            text-decoration: none;
                                        }

                                        .expired button {
                                            background-color: #00bfa5;
                                            color: white;
                                            border: none;
                                            padding: 5px 15px;
                                            border-radius: 4px;
                                            cursor: pointer;
                                        }

                                        .expired button:hover {
                                            background-color: #00a089;
                                        }

                                        .coupon:hover {
                                            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
                                            transform: translateY(-1px);
                                            transition: all 0.2s ease;
                                        }
                                    </style>

                                    <div class="order-col">
                                        <div><strong>TOTAL</strong></div>
                                        <div><strong style="display: flex; justify-content: flex-end" class="order-total">$<span id="orderTotal">${totalPrice != 0 ? (totalPrice - discounted + ship) : 0}</span></strong></div>
                                        <script>
                                            const total = parseFloat(document.getElementById("orderTotal").innerText);
                                            document.getElementById("orderTotal").innerText = total.toFixed(2);
                                        </script>
                                    </div>
                                    <div class="payment-method">
                                        <div class="input-radio">
                                            <input type="radio" name="payment" id="payment-1">
                                            <label for="payment-1">
                                                <span></span>
                                                Cash Payment
                                            </label>
                                        </div>
                                        <div class="input-radio">
                                            <input type="radio" name="payment" id="payment-2">
                                            <label for="payment-2">
                                                <span></span>
                                                Banking Payment
                                            </label>

                                        </div>
                                    </div>
                                    <a style="cursor: ${shop.size() > 0 ? 'pointer' : 'not-allowed'};pointer-events: ${shop.size() > 0 ? 'auto' : 'none'};"  onclick="checkSubmitOrder(${totalPrice - discounted + ship})" class="primary-btn order-submit">Place order</a>
                                    <div class="modal" id="orderConfirmModal">
                                        <img src="assets/img/Gif/groundhog-day.gif" alt="alt" style="width: 100px; height: 100px; margin: auto 0;"/>
                                        <div class="modal-header" style="border: none; margin: 0;">Are you sure?</div>
                                        <div class="modal-footer" style="border: none; margin: 0;">
                                            <button class="btn btn-cancel" id="cancelOrder">Cancel</button>
                                            <button class="btn btn-ok" id="confirmOrder">OK</button>
                                        </div>
                                    </div>

                                </div>
                                <!-- /Order Details -->            
                            </div>
                            <!-- /row -->
                        </div>
                        <!-- /container -->
                    </div>
                    <!-- /SECTION -->
                </div>
            </div>

            <!-- FOOTER -->
            <footer id="footer">
                <!-- top footer -->
                <div class="section">
                    <!-- container -->
                    <div class="container">
                        <!-- row -->
                        <div class="row">
                            <div class="col-md-3 col-xs-6">
                                <div class="footer">
                                    <h3 class="footer-title">About Us</h3>
                                    <p>Astatine04 specializes in providing high quality badminton products, committed to prestige.</p>
                                    <ul class="footer-links">
                                        <li><a href="#"><i class="fa fa-map-marker"></i>Can Tho, Viet Nam</a></li>
                                        <li><a href="#"><i class="fa fa-phone"></i>0912345678</a></li>
                                        <li><a href="#"><i class="fa fa-envelope-o"></i>Astatine04@info.com</a></li>
                                    </ul>
                                </div>
                            </div>

                            <div class="col-md-3 col-xs-6">
                                <div class="footer">
                                    <h3 class="footer-title">Categories</h3>
                                    <ul class="footer-links">
                                        <li><a href="#">Hot deals</a></li>
                                        <li><a href="#">Racquet</a></li>
                                        <li><a href="#">Shoes</a></li>
                                        <li><a href="#">Apparel</a></li>
                                        <li><a href="#">Accessories</a></li>
                                    </ul>
                                </div>
                            </div>

                            <div class="clearfix visible-xs"></div>

                            <div class="col-md-3 col-xs-6">
                                <div class="footer">
                                    <h3 class="footer-title">Information</h3>
                                    <ul class="footer-links">
                                        <li><a href="#">About Us</a></li>
                                        <li><a href="#">Contact Us</a></li>
                                        <li><a href="#">Privacy Policy</a></li>
                                        <li><a href="#">Orders and Returns</a></li>
                                        <li><a href="#">Terms & Conditions</a></li>
                                    </ul>
                                </div>
                            </div>

                            <div class="col-md-3 col-xs-6">
                                <div class="footer">
                                    <h3 class="footer-title">Service</h3>
                                    <ul class="footer-links">
                                        <li><a href="#">My Account</a></li>
                                        <li><a href="#">View Cart</a></li>
                                        <li><a href="#">Wishlist</a></li>
                                        <li><a href="#">Track My Order</a></li>
                                        <li><a href="#">Help</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- /row -->
                    </div>
                    <!-- /container -->
                </div>
                <!-- /top footer -->

                <!-- bottom footer -->
                <div id="bottom-footer" class="section">
                    <div class="container">
                        <!-- row -->
                        <div class="row">
                            <div class="col-md-12 text-center">
                                <span class="copyright">
                                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                                    Copyright &copy;
                                    <script>document.write(new Date().getFullYear());</script> All rights reserved | This
                                    template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a
                                        href="https://colorlib.com" target="_blank">Astatine04</a>
                                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                                </span>
                            </div>
                        </div>
                        <!-- /row -->
                    </div>
                    <!-- /container -->
                </div>
                <!-- /bottom footer -->
            </footer>
            <!-- /FOOTER -->
        </div>

        <!-- jQuery Plugins -->
        <script src="assets/js/JSDefault/jquery.min.js"></script>
        <script src="assets/js/JSDefault/bootstrap.min.js"></script>
        <script src="assets/js/JSDefault/slick.min.js"></script>
        <script src="assets/js/JSDefault/nouislider.min.js"></script>
        <script src="assets/js/JSDefault/jquery.zoom.min.js"></script>
        <script src="assets/js/JSDefault/main.js"></script>
        <script src="assets/js/Voucher/coupon.js"></script>
        <script src="assets/js/utils/addtocart.js"></script>
        <script src="assets/js/utils/submitorder.js"></script>
        <script src="assets/js/utils/notification.js"></script>
        <!-- ToastyFy -->
        <script src="https://cdn.jsdelivr.net/npm/toastify-js"></script>

    </body>

</html>
