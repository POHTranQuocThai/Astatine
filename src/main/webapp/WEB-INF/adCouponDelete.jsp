<%-- 
    Document   : adCouponDelete
    Created on : Nov 5, 2024, 6:43:42 AM
    Author     : Ma Tan Loc - CE181795
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Tittle Web-->
        <title>Astatine 04 - Admin</title>
        <link rel="shortcut icon" type="image/png" href="assets/img/Tittle-web-icon/Logo_Dark.ico" />

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

        <!-- CSS Link -->
        <link rel="stylesheet" href="./assets/css/Admin.css">
        <link rel="stylesheet" href="./assets/css/adOrder.css"/>

        <!-- Icon New-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200&icon_names=arrow_drop_down" />

    </head>

    <body>
        <div id="editModal" class="modal-container">
            <div class="modal-box">
                <h1 style="text-align: center; padding: 2rem 0 0 0;">Delete Coupon</h1>
                <form action="Coupon?action=delete" method="post">
                    <input type="hidden" name="voucherId" value="${coupon.voucherId}">
                    <div>
                        <p style="font-size: 1.5rem;">Do you want to delete <span style="font-weight: 500; color: #21A691">${coupon.name}</span>?</p>
                    </div>

                    <div class="center-bottom-delete">                       
                        <!-- Save Button -->
                        <button type="submit" class="submit-btn" onclick="location.href = 'Coupon'">
                            Save Changes
                        </button>

                        <!-- Close Button -->
                        <button type="button" class="close-btn" onclick="location.href = 'Coupon'">
                            Close
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </body>

    <!-- JS Link-->
    <script type="text/javascript" src="./assets/js/JSRemake/adminTable.js"></script>
    <script type="text/javascript" src="./assets/js/JSRemake/adminJS.js"></script>
    <script type="text/javascript" src="./assets/js/JSRemake/adminDragDropImage.js"></script>

</html>