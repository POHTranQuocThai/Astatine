<%-- 
    Document   : product
    Created on : Oct 14, 2024, 8:12:45 PM
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
        <link type="text/css" rel="stylesheet" href="assets/css/style.css"/>

        <!-- Swiper -->
        <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/toastify-js/src/toastify.min.css">

    </head>

    <!-- HEADER -->
    <header>
        <div id="fb-root"></div>
        <script async defer crossorigin="anonymous" src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v22.0&appId=1136618881088660"></script>
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
                    <ul class="breadcrumb-tree">
                        <li><a href="Home">Home</a></li>
                        <li><a href="Store">All Categories</a></li>
                            <c:choose>
                                <c:when test="${param.category != null}">
                                <li>${param.category}</li>
                                </c:when>
                                <c:when test="${param.brand != null}">
                                <li>${param.brand}</li>
                                </c:when>
                            </c:choose>
                            <c:if test="${prodDetails != null}">
                            <li class="active">${prodDetails.productName}</li>
                            </c:if>
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
                <!-- Product main img -->   
                <div class="col-md-5 col-md-push-2">
                    <div id="product-main-img">
                        <div class="product-preview">
                            <img id="mainImage" img src="${getImage[0]}" alt="">
                        </div>
                        <div class="product-preview">
                            <img id="mainImage" img src="${getImage[1]}" alt="">
                        </div>
                        <div class="product-preview">
                            <img id="mainImage" img src="${getImage[2]}" alt="">
                        </div>
                        <div class="product-preview">
                            <img id="mainImage" img src="${getImage[3]}" alt="">
                        </div>
                        <div class="product-preview">
                            <img id="mainImage" img src="${getImage[4]}" alt="">
                        </div>
                    </div>
                </div>
                <!-- /Product main img -->
                <!-- Product thumb imgs -->
                <div class="col-md-2  col-md-pull-5">
                    <div id="product-imgs">
                        <c:forEach items="${getImage}" var="image" varStatus="status">
                            <div class="product-preview">
                                <img src="${image}" alt="${status.index + 1}"
                                     onclick="changeMainImage('${image}')"
                                     style="cursor:pointer;">
                            </div>                             
                        </c:forEach>
                    </div>
                </div>
                <!-- /Product thumb imgs -->
                <script>
                    function changeMainImage(imageSrc) {
                        console.log("Hình ảnh được chọn: ", imageSrc); // Log hình ảnh được chọn
                        document.getElementById()("mainImage").src = imageSrc;
                    }
                </script>
                <style>
                    .product-preview {
                        cursor: pointer; /* Đổi con trỏ khi hover để báo hiệu có thể bấm vào */
                    }
                </style>

                <!-- Product details -->
                <div class="col-md-5">
                    <div class="product-details">
                        <h2 class="product-name">${prodDetails.productName}</h2>
                        <div>
                            <div class="product-rating">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>                       
                            </div>                      
                        </div>
                        <div>
                            <h3 class="product-price">$${prodDetails.price} <del class="product-old-price">$990.00</del></h3>
                            <span class="product-available">In Stock ${prodDetails.countInStock}</span>
                        </div>

                        <div class="add-to-cart">                           
                            <div class="qty-label">
                                Qty
                                <div class="input-number">
                                    <input style="padding: 0;" type="number" id="quantity" value="1" min="1" readonly max="${prodDetails.countInStock != 0 ? prodDetails.countInStock : 1}">
                                    <span class="qty-up">+</span>
                                    <span class="qty-down">-</span>
                                </div>
                            </div>                         
                            <button style="pointer-events: ${prodDetails.countInStock > 0 ? 'auto' : 'none'};" onclick="handleAddToCart(${prodDetails.productId}, '${email}')" class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i>${prodDetails.countInStock > 0 ? 'Add To Card':'Sold Out'}</button>
                        </div>

                        <ul class="product-links">
                            <li>Category:</li>
                            <li><a href="#">${prodDetails.type}</a></li>                         
                        </ul>
                    </div>
                </div>
            </div>

            <!-- /Product details -->

            <!-- Product tab -->
            <div class="col-md-12">
                <div id="product-tab">
                    <!-- product tab nav -->
                    <ul class="tab-nav">
                        <li class="active"><a data-toggle="tab" href="#tab1">Description</a></li>
                        <li><a data-toggle="tab" href="#tab2">Comments</a></li>
                    </ul>
                    <!-- /product tab nav -->

                    <!-- product tab content -->
                    <div class="tab-content">
                        <!-- tab1 -->
                        <div id="tab1" class="tab-pane fade in active">
                            <div class="row">
                                <div class="col-md-12">
                                    <p>${prodDetails.description}</p>
                                </div>
                            </div>
                        </div>
                        <!-- /tab1 -->
                        <style>
                            .comment-box {
                                width: 100%;
                                background: #fff;
                                padding: 15px;
                                border-radius: 8px;
                                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
                            }
                            .comment-header {
                                display: flex;
                                align-items: center;
                                gap: 10px;
                                margin-bottom: 10px;
                            }
                          
                            .comment-header input,
                            .comment-header textarea {
                                width: 100%;
                                padding: 8px;
                                border: 1px solid #ccc;
                                border-radius: 20px;
                                outline: none;
                            }
                            .comment-header textarea {
                                padding: 15px 15px;
                                height: 50px;
                                resize: none;
                            }
                            .comment-actions {
                                display: flex;
                                justify-content: flex-end;
                                margin-top: 10px;
                            }
                            .comment-actions button {
                                background: #007bff;
                                color: white;
                                border: none;
                                padding: 8px 15px;
                                border-radius: 20px;
                                cursor: pointer;
                                font-size: 14px;
                            }
                            .comments {
                                margin-top: 20px;
                            }
                            .comment {
                                display: flex;
                                align-items: flex-start;
                                gap: 10px;
                                background: #f8f9fa;
                                padding: 10px;
                                border-radius: 10px;
                                margin-top: 10px;
                                flex-direction: column;
                            }
                            .comment .avatar {
                                min-width: 40px;
                                height: 40px;
                                background-color: #ccc;
                                border-radius: 50%;
                                display: flex;
                                align-items: center;
                                justify-content: center;
                                font-size: 18px;
                                color: white;
                                font-weight: bold;
                                flex-shrink: 0;
                            }
                            .comment-wrapper {
                                display: flex;
                                flex-direction: column;
                                width: 100%;
                            }
                            .comment-name {
                                text-align: start;
                                font-size: 14px;
                                font-weight: bold;
                                margin-bottom: 3px;
                            }
                            .comment-content {
                                text-align: start;
                                background: #e9ecef;
                                padding: 5px 12px;
                                border-radius: 10px;
                                word-wrap: break-word;
                                white-space: pre-wrap;
                                max-width: fit-content;
                            }
                            .comment-actions-bar {
                                display: flex;
                                gap: 10px;
                                font-size: 14px;
                                cursor: pointer;
                                margin-top: 5px;
                                margin-left: 3px;
                                align-items: center;
                            }
                            .comment-actions-bar span:hover {
                                text-decoration: underline;
                            }
                            .replies {
                                margin-left: 50px;
                                margin-top: 5px;
                            }
                            .reply-box {
                                display: flex;
                                gap: 5px;
                                margin-top: 5px;
                            }
                            .reply-box input {
                                flex: 1;
                                padding: 5px;
                                border: 1px solid #ccc;
                                border-radius: 5px;
                            }
                            .reply-box button {
                                padding: 5px 10px;
                                border: none;
                                border-radius: 5px;
                                cursor: pointer;
                            }
                            .reply-box .send {
                                background: #28a745;
                                color: white;
                            }
                            .reply-box .cancel {
                                background: #dc3545;
                                color: white;
                            }
                            .comment-time {
                                padding-top: 3px;
                                font-size: 12px;
                                color: darkgray;
                            }
                        </style>

                        <!-- tab2 -->
                        <div id="tab2" class="tab-pane fade">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="comment-box">                      
                                        <div class="comment-header">
                                            <textarea id="commentInput" placeholder="Comment..."></textarea>
                                        </div>
                                        <div class="comment-actions">
                                            <button onclick="addComment()">Send</button>
                                        </div>
                                        <div class="comments" id="commentsList"></div>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <script>

                            // Lưu trạng thái like của từng người dùng (giả lập backend)
                            var likedComments = {}; // Định dạng: { commentId: [userId1, userId2] }
                            var userId = '${User.userId}'; // ✅ Gán giá trị từ backend vào biến

                            function addComment() {
                                if (!userId || userId === 'null' || userId === 'undefined') {
                                    showToast('Please login to use comments!', '');
                                    return;
                                }
                                var commentText = document.getElementById("commentInput").value.trim();
                                if (commentText === "") {
                                    showToast('Please enter the content!', '');
                                    return;
                                }

                                // Tạo commentId duy nhất (có thể dùng ID từ backend nếu có)
//                                var commentId = "cmt_" + new Date().getTime();

                                var commentDiv = createCommentElement('${User.fullname}', commentText, userId);
                                document.getElementById("commentsList").appendChild(commentDiv);
                                document.getElementById("commentInput").value = "";
                            }
                            function timeAgo(timestamp) {
                                const now = Date.now(); // Lấy timestamp hiện tại (milliseconds)
                                const past = new Date(timestamp).getTime(); // Chuyển timestamp đầu vào về milliseconds

                                if (isNaN(past))
                                    return "Không xác định"; // Kiểm tra nếu timestamp không hợp lệ

                                const diff = Math.floor((now - past) / 1000); // Chênh lệch thời gian tính theo giây

                                if (diff < 60) {
                                    return `${diff} giây trước`;
                                } else if (diff < 3600) {
                                    return `${Math.floor(diff / 60)} phút trước`;
                                } else if (diff < 86400) {
                                    return `${Math.floor(diff / 3600)} giờ trước`;
                                } else {
                                    return `${Math.floor(diff / 86400)} ngày trước`;
                                }
                            }




// Hàm tạo bình luận
                            // Hàm tạo bình luận
                            function createCommentElement(name, content, commentId) {
                                const now = new Date();
                                const dateString = now.toLocaleDateString("vi-VN");
                                const timeString = now.toLocaleTimeString("vi-VN", {
                                    hour: "2-digit",
                                    minute: "2-digit",
                                });

                                var commentDiv = document.createElement("div");
                                commentDiv.classList.add("comment");
                                commentDiv.dataset.commentId = commentId; // Gán ID cho comment

                                var avatarDiv = document.createElement("div");
                                avatarDiv.classList.add("avatar");
                                avatarDiv.textContent = "👤";
                                var commentWrapper = document.createElement("div");
                                commentWrapper.classList.add("comment-wrapper");
                                var nameDiv = document.createElement("div");
                                nameDiv.classList.add("comment-name");
                                nameDiv.textContent = name;
                                var contentDiv = document.createElement("div");
                                contentDiv.classList.add("comment-content");
                                contentDiv.textContent = content;
                                var actionsBar = document.createElement("div");
                                actionsBar.classList.add("comment-actions-bar");
                                var likeButton = document.createElement("span");
                                likeButton.textContent = "Like (0)";
                                likeButton.onclick = function () {
                                    toggleLike(commentId, likeButton);
                                };
                                var replyButton = document.createElement("span");
                                replyButton.textContent = "Reply";
                                replyButton.onclick = function () {
                                    showReplyBox('${User.fullname}', commentDiv);
                                };
                                actionsBar.appendChild(likeButton);
                                actionsBar.appendChild(replyButton);
                                commentWrapper.appendChild(nameDiv);
                                commentWrapper.appendChild(contentDiv);
                                commentWrapper.appendChild(actionsBar);
                                var repliesDiv = document.createElement("div");
                                repliesDiv.classList.add("replies");

                                var timeDiv = document.createElement("div");
                                timeDiv.classList.add("comment-time");
                                timeDiv.textContent = timeString + " " + dateString
                                // Cập nhật thời gian hiển thị mỗi phút
                                setInterval(() => {
                                    timeDiv.textContent = timeAgo(timestamp);
                                }, 60000);
                                actionsBar.appendChild(timeDiv);

                                commentDiv.appendChild(avatarDiv);
                                commentDiv.appendChild(commentWrapper);
                                commentDiv.appendChild(repliesDiv);
                                return commentDiv;
                            }
// Hàm xử lý like/unlike
                            function toggleLike(commentId, likeButton) {
                                if (!likedComments[commentId]) {
                                    likedComments[commentId] = new Set();
                                }
                                var userLikes = likedComments[commentId];
                                console.log(userLikes.size);
                                if (userLikes.has(commentId)) {
                                    // Nếu đã like => Unlike
                                    userLikes.delete(commentId);
                                } else {
                                    // Nếu chưa like => Like
                                    userLikes.add(commentId);
                                }
                                console.log(userLikes.size);
                                let num = userLikes.size
                                // Cập nhật số lượng like
                                likeButton.textContent = `Like (` + num + `)`;
                                likeButton.style.color = userLikes.has(commentId) ? '#007bff' : 'black';
                            }

// Hiển thị khung trả lời
                            function showReplyBox(name, commentDiv) {
                                var existingReplyBox = commentDiv.querySelector(".reply-box");
                                if (existingReplyBox) {
                                    existingReplyBox.remove();
                                    return;
                                }

                                var replyBox = document.createElement("div");
                                replyBox.classList.add("reply-box");
                                var replyInput = document.createElement("input");
                                replyInput.placeholder = "Reply...";
                                var sendButton = document.createElement("button");
                                sendButton.classList.add("send");
                                sendButton.textContent = "Send";
                                sendButton.onclick = function () {
                                    if (replyInput.value.trim() !== "") {
                                        var replyDiv = createCommentElement(name, replyInput.value.trim(), "reply_" + new Date().getTime());
                                        commentDiv.querySelector(".replies").appendChild(replyDiv);
                                        replyBox.remove();
                                    } else {
                                        showToast('Please enter reply!', '');
                                    }
                                };
                                var cancelButton = document.createElement("button");
                                cancelButton.classList.add("cancel");
                                cancelButton.textContent = "Cancel";
                                cancelButton.onclick = function () {
                                    replyBox.remove();
                                };
                                replyBox.appendChild(replyInput);
                                replyBox.appendChild(sendButton);
                                replyBox.appendChild(cancelButton);
                                commentDiv.appendChild(replyBox);
                            }

                        </script>
                        <!-- /tab2 -->
                    </div>
                    <!-- /product tab content -->
                </div>
            </div>

            <!-- /product tab -->
        </div>
        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /SECTION -->

<!-- Section -->
<div class="section">
    <!-- container -->
    <div class="container">
        <!-- row -->
        <div class="row">

            <div class="col-md-12">
                <div class="section-title text-center">
                    <h3 class="title">Related Products</h3>
                </div>
            </div>

            <!-- product -->
            <c:forEach items="${prodType}" var="type">
                <div class="col-md-3 col-xs-6">        
                    <div class="product" style="opacity: ${type.countInStock > 0 ? '1': '.5'}">
                        <div class="product-img">
                            <img src="${type.image}" alt="">                             
                        </div>
                        <div class="product-body">
                            <p class="product-category">Category</p>
                            <h3 class="product-name"><a href="Products?view=prod-details&id=${type.productId}">${type.productName}</a></h3>
                            <h4 class="product-price">$${type.price} <del class="product-old-price">$990.00</del></h4>
                            <div class="product-rating">
                            </div>
                            <div class="product-btns">
                                <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">${type.type}</span></button>
                                <button class="add-to-compare"><i class="bi bi-bag-heart"></i><span class="tooltipp">${type.selled}</span></button>
                                <button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">${type.brand}</span></button>
                            </div>
                        </div>
                        <div class="add-to-cart">
                            <button class="add-to-cart-btn" 
                                    style="pointer-events: ${type.countInStock > 0 ? 'auto' : 'none'};"
                                    onclick="handleAddToCart(${type.productId}, '${email}')" >
                                <i class="fa fa-shopping-cart"></i> ${type.countInStock > 0 ? 'Add To Card':'Sold Out'}
                            </button>       
                        </div>
                    </div>
                </div>
            </c:forEach>
            <!-- /product -->
        </div>
        <style>
            body {
                font-family: Arial, sans-serif;
                text-align: center;
            }
            #chat-box {
                width: 50%;
                height: 300px;
                border: 1px solid #ccc;
                overflow-y: auto;
                margin: 10px auto;
                padding: 10px;
            }
            input, button {
                padding: 10px;
                margin: 5px;
            }
        </style>

        <!-- /row -->
    </div>
    <!-- /container -->
</div>
<!-- /Section -->

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
                    <ul class="footer-payments">
                        <li><a href="#"><i class="fa fa-cc-visa"></i></a></li>
                        <li><a href="#"><i class="fa fa-credit-card"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-paypal"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-mastercard"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-discover"></i></a></li>
                        <li><a href="#"><i class="fa fa-cc-amex"></i></a></li>
                    </ul>
                    <span class="copyright">
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        Copyright &copy;
                        <script>document.write(new Date().getFullYear());</script> All rights reserved | This
                        template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a
                            href="https://colorlib.com" target="_blank">Colorlib</a>
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
<script src="assets/js/JSDefault/jquery.min.js"></script>
<script src="assets/js/JSDefault/bootstrap.min.js"></script>
<script src="assets/js/JSDefault/slick.min.js"></script>
<script src="assets/js/JSDefault/nouislider.min.js"></script>
<script src="assets/js/JSDefault/jquery.zoom.min.js"></script>
<script src="assets/js/JSDefault/main.js"></script>
<script src="assets/js/Coupon/coupon.js"></script>
<script src="assets/js/utils/addtocart.js"></script>
<script src="assets/js/utils/notification.js"></script>
<!-- ToastyFy -->
<script src="https://cdn.jsdelivr.net/npm/toastify-js"></script>

</body>

</html>
