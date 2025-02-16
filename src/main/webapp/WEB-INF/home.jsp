<%-- 
    Document   : home
    Created on : Oct 22, 2024, 1:48:02 PM
    Author     : Tran Quoc Thai - CE181618 
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Astatine 04</title>
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
        <link type="text/css" rel="stylesheet" href="assets/css/chat.css" />

        <!-- Swiper -->
        <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />

        <!-- Animation -->
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">

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
                            <div class="dropdown" id="cart-dropdown" onsubmit="this.submit()">
                                <a class="dropdown-toggle" href="Checkout" id="navbarDropdownMenuLink" 
                                   aria-haspopup="true" aria-expanded="false">
                                    <i class="bi bi-bag-heart-fill" style="font-size: 24px;"></i>
                                    <div class="qty num-order">${SHOP.size() > 0 ? SHOP.size(): 0}</div>
                                </a>
                            </div>
                            <!-- /Cart -->
                            <!-- Account -->   
                            <%
                                String email = (String) session.getAttribute("email");
                            %>

                            <div id="account-dropdown" style="<%= email != null && !email.isEmpty() ? "display: none;" : "display: inline-block;"%>">
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
                                <div id="account-success" style="<%= email != null && !email.isEmpty() ? "display: block;" : "display: none;"%>">
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
            <!-- NAVIGATION -->
            <nav id="navigation">
                <!-- container -->
                <div class="container">

                    <!-- responsive-nav -->
                    <div id="responsive-nav">
                        <!-- NAV -->
                        <ul class="main-nav nav navbar-nav">
                            <li class="active"><a href="">Home</a></li>
                            <!-- Brands Dropdown -->
                            <li class="dropdown">
                                <a class="dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">           
                                    <span>Brands</span>
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                    <c:forEach items="${brand}" var="b">
                                        <a class="dropdown-item brand" href="Store?brand=${b.brandName}">${b.brandName}</a>
                                        <hr>                    
                                    </c:forEach>
                                </div>
                            </li>
                            <li class="dropdown">
                                <a class="dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">           
                                    <span>Categories</span>
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                                    <c:forEach items="${type}" var="t">
                                        <a class="dropdown-item brand" href="Store?category=${t.type}">${t.type}</a>
                                        <hr>                    
                                    </c:forEach>
                                </div>
                            </li>
                            <li class="dropdown">
                                <a class="dropdown-toggle" href="Store?page=1" id="navbarDropdownMenuLink" aria-haspopup="true" aria-expanded="false">           
                                    <span>All Categories</span>
                                </a>

                            </li>
                        </ul>
                        <!-- /NAV -->

                    </div>
                    <!-- /responsive-nav -->
                </div>
                <!-- /container -->
            </nav>
            <!-- /NAVIGATION -->

            <main>
                <!-- CAROUSEL-->

                <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" data-interval="3000">
                    <ol class="carousel-indicators">
                        <li data-target="#carouselExampleIndicators" data-slide-to="0"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="4"></li>
                        <li data-target="#carouselExampleIndicators" data-slide-to="5"></li>
                    </ol>
                    <div class="carousel-inner">
                        <div class="item active">
                            <img class="d-block w-100" src="assets/img/Banner/Lining01.png" alt="First slide">
                            <div class="overlay"></div>
                        </div>
                        <div class="item">
                            <img class="d-block w-100" src="assets/img/Banner/Mizuno01.png" alt="Second slide">
                            <div class="overlay"></div>
                        </div>
                        <div class="item">
                            <img class="d-block w-100" src="assets/img/Banner/Yonex01.png" alt="Third slide">
                            <div class="overlay"></div>
                        </div>
                        <div class="item">
                            <img class="d-block w-100"
                                 src="https://www.yonex.com/media/scandiweb/slider/y/n/ynx_nf800_banner_01_en_2880-1120.png"
                                 alt="Fourth slide">
                            <div class="overlay"></div>
                        </div>
                        <div class="item">
                            <img class="d-block w-100" src="assets/img/Banner/Yonex03.png" alt="Fifth slide">
                            <div class="overlay"></div>
                        </div>
                        <div class="item">
                            <img class="d-block w-100" src="assets/img/Banner/Yonex04.png" alt="Sixth slide">
                            <div class="overlay"></div>
                        </div>
                    </div>

                    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                        <i class="bi bi-caret-left-fill" aria-hidden="true"></i>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                        <i class="bi bi-caret-right-fill" aria-hidden="true"></i>
                        <span class="sr-only">Next</span>
                    </a>
                </div>


                <!-- /CAROUSEL-->

                <!-- SECTION -->
                <div class="section">
                    <!-- container -->
                    <div class="container">
                        <!-- row -->
                        <div class="row">

                            <!-- section title -->
                            <div class="col-md-12">
                                <div class="section-title">
                                    <h3 class="title">New Products</h3>
                                    <div class="section-nav">
                                    </div>
                                </div>
                            </div>
                            <!-- /section title -->

                            <!-- Products tab & slick -->
                            <div class="col-md-12">
                                <div class="row">
                                    <div class="products-tabs">
                                        <!-- tab -->
                                        <div id="tab1" class="tab-pane active">
                                            <div class="products-slick" data-nav="#slick-nav-1">
                                                <!-- product -->                      
                                                <c:forEach items="${products}" var="prod">
                                                    <div class="product" style="opacity: ${prod.countInStock > 0 ? '1': '.5'}">
                                                        <div class="product-img">
                                                            <img src="${prod.image}" alt="">
                                                            <div class="product-label">
                                                                <span class="sale">-30%</span>
                                                                <span class="new">NEW</span>
                                                            </div>
                                                        </div>
                                                        <div class="product-body">
                                                            <p class="product-category">Category</p>
                                                            <h3 class="product-name"><a href="Products?view=prod-details&id=${prod.productId}" target="_self">${prod.productName}</a></h3>
                                                            <h4 class="product-price">$${prod.price} <del class="product-old-price">$990.00</del>
                                                            </h4>
                                                            <div class="product-rating">
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                            </div>
                                                            <div class="product-btns">
                                                                <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span
                                                                        class="tooltipp">${prod.type}</span></button>
                                                                <button class="quick-view"><i class="bi bi-bag-heart"></i><span
                                                                        class="tooltipp">${prod.selled}</span></button>
                                                                <button class="quick-view"><i class="fa fa-eye"></i><span
                                                                        class="tooltipp">${prod.brand}</span></button>
                                                            </div>
                                                        </div>
                                                        <div class="add-to-cart">
                                                            <button class="add-to-cart-btn" 
                                                                    style="pointer-events: ${prod.countInStock > 0 ? 'auto' : 'none'};"
                                                                    onclick="handleAddToCart(${prod.productId}, '${email}')" >
                                                                <i class="fa fa-shopping-cart"></i> ${prod.countInStock > 0 ? 'Add To Card':'Sold Out'}
                                                            </button>                            
                                                        </div>                      
                                                    </div>                                                      
                                                </c:forEach>    

                                            </div>
                                            <div id="slick-nav-1" class="products-slick-nav"></div>
                                        </div>
                                        <!-- /tab -->
                                    </div>
                                </div>
                            </div>
                            <!-- Products tab & slick -->
                        </div>
                        <!-- /row -->
                    </div>
                    <!-- /container -->
                </div>
                <!-- /SECTION -->

                <!-- HOT DEAL SECTION -->
                <div id="hot-deal" class="section">
                    <!-- container -->
                    <div class="container">
                        <!-- row -->
                        <div class="row">
                            <div class="col-md-12">
                                <div class="hot-deal">
                                    <ul class="hot-deal-countdown">
                                        <li class="countdown-item">
                                            <div>
                                                <h3>00</h3>
                                                <span>Days</span>
                                            </div>
                                        </li>
                                        <li class="countdown-item">
                                            <div>
                                                <h3>00</h3>
                                                <span>Hours</span>
                                            </div>
                                        </li>
                                        <li class="countdown-item">
                                            <div>
                                                <h3>00</h3>
                                                <span>Mins</span>
                                            </div>
                                        </li>
                                        <li class="countdown-item">
                                            <div>
                                                <h3>00</h3>
                                                <span>Secs</span>
                                            </div>
                                        </li>
                                    </ul>
                                    <h2 class="text-uppercase">hot deal this week</h2>
                                    <p>New Collection Up to 50% OFF</p>
                                    <a type="submit" class="primary-btn cta-btn" href="Store">Shop now</a>
                                </div>
                            </div>
                        </div>
                        <!-- /row -->
                    </div>
                    <!-- /container -->
                </div>
                <!-- /HOT DEAL SECTION -->

                <!-- BADMINTON SLIDE-->

                <div class="badminton-cointainer">

                    <div class="badminton-slide row" id="slide-odd">

                        <div class="badminton-slide-odd--info col-md-5" data-aos="fade-up" data-aos-offset="500"
                             data-aos-duration="1500">

                            <div class="badminton-slide-odd--info--inner">
                                <span class="label">NEW BADMINTON</span>
                                <h1 class="title">NANOFLARE 1000</h1>
                                <p>Engineered to improve swing speed and maneuverability, the NANOFLARE is the only head-light racquet
                                    proven to provide unrivaled power and increased shuttle acceleration.</p>
                                <a href="#">
                                    <span role="button" class="action black">
                                        <span>Learn More</span>
                                    </span>
                                </a>
                            </div>

                        </div>

                        <div class="badminton-slide-odd--image col-md-7">
                            <img class="main-image" data-aos="fade-left" data-aos-offset="500" data-aos-duration="800"
                                 data-aos-easing="ease-in-out"
                                 src="https://www.yonex.com/media/wysiwyg/Home/badminton_racquet_img_2x_NF1000-pro.png"
                                 alt="NANOFLARE 1000" style="display: inline;">
                            <img class="shape" data-aos="fade-left" data-aos-offset="500" data-aos-duration="400"
                                 src="https://www.yonex.com/media/images/cms/homepage/shapes/badminton_bg_shape.svg" alt="Badminton"
                                 style="display: inline;">
                        </div>
                    </div>

                    <div class="badminton-slide row" id="slide-02">

                        <div class="badminton-slide--even--image col-md-7">
                            <img class="main-image" data-aos="fade-right" data-aos-offset="500" data-aos-duration="500"
                                 data-aos-easing="ease-in-out" 
                                 src="assets/img/giay-cau-long-yonex-65z3-c-90-wide.png"
                                 alt="NANOFLARE 1000" style="display: inline;">
                            <img class="shape" data-aos="fade-right" data-aos-offset="500" data-aos-duration="1000"
                                 src="https://www.yonex.com/media/images/cms/homepage/shapes/shoe_bg_shape.svg" alt="Badminton"
                                 style="display: inline;">
                        </div>

                        <div class="badminton-slide--even--info col-md-5" data-aos="fade-up" data-aos-offset="500"
                             data-aos-duration="1500">
                            <div class="badminton-slide-even--info--inner">
                                <span class="label">NEW SHOES</span>
                                <h1 class="title">65Z3 C-90</h1>
                                <p>The Yonex 65Z3 C-90 Men’s badminton shoes are high-end, popular among pro players for their stylish cream design and excellent grip, providing smooth movement and optimal energy transfer.</p>
                                <a href="https://www.yonex.com/badminton/racquets/nanoflare/">
                                    <span role="button" class="action black">
                                        <span>Learn More</span>
                                    </span>
                                </a>
                            </div>
                        </div>

                    </div>

                    <div class="badminton-slide row" id="slide-odd">

                        <div class="badminton-slide-odd--info col-md-5" data-aos="fade-up" data-aos-offset="500"
                             data-aos-duration="1500">

                            <div class="badminton-slide-odd--info--inner">
                                <span class="label">NEW BADMINTON</span>
                                <h1 class="title">ASTROX 100ZZ</h1>
                                <p>For advanced players seeking faster movements and precise shots, the Astrox 100 ZZ is an ideal
                                    racket. It requires strong technique and power to fully utilize its capabilities.</p>
                                <a href="Products?view=prod-details&id=1">
                                    <span role="button" class="action black">
                                        <span>Learn More</span>
                                    </span>
                                </a>
                            </div>

                        </div>

                        <div class="badminton-slide-odd--image col-md-7">
                            <style>
                                .badminton-slide-odd--image img {
                                    mix-blend-mode: multiply;
                                }
                            </style>
                            <img class="main-image" data-aos="fade-left" data-aos-offset="500" data-aos-duration="800"
                                 data-aos-easing="ease-in-out" src="assets/img/vot-cau-long-yonex-astrox-100zz-dark-navy.png"
                                 alt="NANOFLARE 1000" style="display: inline;">
                            <img class="shape" data-aos="fade-left" data-aos-offset="500" data-aos-duration="400"
                                 src="https://www.yonex.com/media/images/cms/homepage/shapes/badminton_bg_shape.svg" alt="Badminton"
                                 style="display: inline;">
                        </div>
                    </div>

                </div>


                <!-- SECTION -->
                <div class="section">
                    <!-- container -->
                    <div class="container">
                        <!-- row -->
                        <div class="row">

                            <!-- section title -->
                            <div class="col-md-12">
                                <div class="section-title">
                                    <h3 class="title">Top selling</h3>
                                    <div class="section-nav">
                                    </div>
                                </div>
                            </div>
                            <!-- /section title -->

                            <!-- Products tab & slick -->
                            <div class="col-md-12">
                                <div class="row">
                                    <div class="products-tabs">
                                        <!-- tab -->
                                        <div id="tab2" class="tab-pane fade in active">
                                            <div class="products-slick" data-nav="#slick-nav-2">  
                                                <!-- product -->                      
                                                <c:forEach items="${topSelled}" var="top">
                                                    <div class="product">
                                                        <div class="product-img">
                                                            <img src="${top.image}" alt="">
                                                            <div class="product-label">
                                                                <span class="sale">-30%</span>
                                                                <span class="new">NEW</span>
                                                            </div>
                                                        </div>
                                                        <div class="product-body">
                                                            <p class="product-category">Category</p>
                                                            <h3 class="product-name"><a href="Products?view=prod-details&id=${top.productId}" target="_self">${top.productName}</a></h3>
                                                            <h4 class="product-price">$${top.price} <del class="product-old-price">$990.00</del>
                                                            </h4>
                                                            <div class="product-rating">
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                                <i class="fa fa-star"></i>
                                                            </div>
                                                            <div class="product-btns">
                                                                <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span
                                                                        class="tooltipp">${top.type}</span></button>
                                                                <button class="quick-view"><i class="bi bi-bag-heart"></i><span
                                                                        class="tooltipp">${top.selled}</span></button>
                                                                <button class="quick-view"><i class="fa fa-eye"></i><span
                                                                        class="tooltipp">${top.brand}</span></button>
                                                            </div>
                                                        </div>
                                                        <div class="add-to-cart">
                                                            <button onclick="handleAddToCart(${top.productId}, '${email}')" class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add
                                                                to
                                                                cart</button>
                                                        </div>
                                                    </div>
                                                </c:forEach>    
                                                <!-- /product -->
                                            </div>
                                            <div id="slick-nav-2" class="products-slick-nav"></div>
                                        </div>
                                        <!-- /tab -->
                                    </div>
                                </div>
                            </div>
                            <!-- /Products tab & slick -->

                        </div>
                        <!-- /row -->

                    </div>

                    <!-- /container -->
                </div>
                <!-- /SECTION -->
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

                    <!--                    <div id='tawk_67b54113aa66771910dec480'></div>-->
                    <!--Start of Tawk.to Script-->
                    <!--Start of Tawk.to Script-->
                    <!--                    <script type="text/javascript">
                                            var Tawk_API = Tawk_API || {}, Tawk_LoadStart = new Date();
                                            (function () {
                                                var s1 = document.createElement("script"), s0 = document.getElementsByTagName("script")[0];
                                                s1.async = true;
                                                s1.src = 'https://embed.tawk.to/67b54113aa66771910dec480/1ike4ofrf';
                                                s1.charset = 'UTF-8';
                                                s1.setAttribute('crossorigin', '*');
                                                s0.parentNode.insertBefore(s1, s0);
                                            })();
                                        </script>-->


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
                    <!-- Icon Chat -->
                    <div id="chatIcon">
                        <svg viewBox="0 0 100 100">
                        <defs>
                        <!-- Định nghĩa đường tròn để chữ chạy quanh -->
                        <path id="circlePath" d="M 10,50 a 40,40 0 1,1 80,0 a 40,40 0 1,1 -80,0"/>
                        </defs>
                        <g>
                        <text>
                        <textPath href="#circlePath">Chat • Support •</textPath>
                        </text>
                        </g>
                        </svg>
                        <img src="../assets/img/logo.png" alt="alt"/>
                    </div>
                    <div id="chat-widget" style="display: block">
                        <div id="chat-header">Astatine Admin</div>
                        <div id="chat-box">
                            <h6 class="chat-date"></h6>
                            <div class="login-require" style="display: ${User != null ? 'none': 'block'}">
                                <h3 class="label-info">Please login to use Chat!</h3>
                                <div class="btn-chat-login">
                                    <a class="login" href="Login">Log In</a>

                                </div>
                            </div>
                        </div>
                        <div id="chat-input">
                            <input type="text" id="message" placeholder="Nhập tin nhắn..." />
                            <button class="btn-chat" onclick="sendMessage(${User.userId}, ${User.isAdmin})">➤</button>
                        </div>
                    </div>
                    <script>
                        document.addEventListener("DOMContentLoaded", () => {
                            const input = document.getElementById("message");
                            if (input) {
                                input.addEventListener("keypress", function (event) {
                                    if (event.key === "Enter")
                                        sendMessage(${User.userId},${User.isAdmin});
                                });
                            }
                        });
                    </script>
                </div>
                <!-- /container -->
            </div>
            <!-- /bottom footer -->
        </footer>
        <!-- /FOOTER -->
    </div>

    <script>
        window.onload = function () {
        <% if (session.getAttribute("toastMessage") != null) {%>
            showToast("<%= session.getAttribute("toastMessage")%>", "<%= session.getAttribute("toastType")%>");
        <% session.removeAttribute("toastMessage");
            session.removeAttribute("toastType"); %>
        <% }%>
        }
        ;
    </script>

    <!-- jQuery Plugins -->
    <script src="assets/js/JSDefault/jquery.min.js"></script>
    <script src="assets/js/JSDefault/bootstrap.min.js"></script>
    <script src="assets/js/JSDefault/slick.min.js"></script>
    <script src="assets/js/JSDefault/nouislider.min.js"></script>
    <script src="assets/js/JSDefault/jquery.zoom.min.js"></script>
    <script src="assets/js/JSDefault/main.js"></script>
    <script src="assets/js/utils/addtocart.js"></script>
    <script src="assets/js/utils/notification.js"></script>
    <script src="assets/js/utils/chatbox.js"></script>
    <!-- ToastyFy -->
    <script src="https://cdn.jsdelivr.net/npm/toastify-js"></script>

    <!-- Countdown -->
    <script src="assets/js/JSRemake/countdown.js"></script>

    <!-- Animated -->
    <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
    <script>
        AOS.init();
    </script>

    <script src="assets/js/JSRemake/SlideMoving.js"></script>
    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
</body>

</html> 
