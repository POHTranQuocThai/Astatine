<%-- 
    Document   : blank
    Created on : Oct 14, 2024, 8:14:31 PM
    Author     : Tran Quoc Thai - CE181618 
--%>

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

        <!-- Swiper -->
        <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />

        <!-- Animation -->
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
                  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
                  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
                <![endif]-->

    </head>

    <!-- HEADER -->
    <header>
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

                                <div id="account-dropdown" style="<%= email != null && !email.isEmpty() ? "display: none;" : "display: inline-block;" %>">
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

        <!-- BREADCRUMB -->
        <div id="breadcrumb" class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <div class="col-md-12">
                        <h3 class="breadcrumb-header">Blank Page</h3>
                        <ul class="breadcrumb-tree">
                            <li><a href="#">Home</a></li>
                            <li class="active">Blank</li>
                        </ul>
                    </div>
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /BREADCRUMB -->

        <<main>
            <!-- SECTION -->
            <div class="section">
                <!-- container -->
                <div class="container">
                    <!-- row -->
                    <div class="row">
                        <div class="img-noproduct">
                            <img src="./assets/img/product_not_found.png">
                        </div>
                    </div>
                    <div class="button-container">
                        <a href="Home" class="btn">Back To HomePage</a>
                        <a href="Store?page=1" class="btn">View Store</a>
                    </div>

                    <style>
                        .button-container {
                            display: flex;
                            justify-content: space-between;
                            max-width: 600px;
                            margin: 0 auto;
                            padding: 20px;
                        }

                        .btn {
                            display: inline-block;
                            padding: 10px 20px;
                            font-size: 16px;
                            font-weight: bold;
                            color: white;
                            background-color: #21A691;
                            text-align: center;
                            text-decoration: none;
                            border-radius: 8px;
                            transition: background-color 0.3s;
                        }

                        .btn:hover {
                            background-color: #21A691;
                        }
                    </style>

                    <style>
                        .img-noproduct img {
                            display: block;
                            margin-top: -30px;
                            margin-left: auto;
                            margin-right: auto;
                            width: 600px;
                        }
                    </style>
                    <!-- /row -->
                </div>
                <!-- /container -->
            </div>
            <!-- /SECTION -->
        </main>

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

        <!-- jQuery Plugins -->
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/slick.min.js"></script>
        <script src="assets/js/nouislider.min.js"></script>
        <script src="assets/js/jquery.zoom.min.js"></script>
        <script src="assets/js/main.js"></script>

    </body>

</html>
