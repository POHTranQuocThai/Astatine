<%-- 
    Document   : login.jsp
    Created on : 4 Jun, 2024, 4:48:55 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/css/resetPassword.css"/>
        <title>Astatine 04 | Reset Password</title>
        <link rel="shortcut icon" type="image/png" href="assets/img/Tittle-web-icon/Logo_Dark.ico" />

        <!-- Google font -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

        <!-- Icon New-->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link
            href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
            rel="stylesheet"
            />
    </head>
    <body>
        <div class="container">
            <h2>Reset Password</h2>
            <form action="resetPassword" method="POST">
                <label for="email">Email</label>
                <div class="input-group">
                    <i class='bx bxs-envelope'></i>
                    <input type="email" name="email" id="email" value="${email}" placeholder="Enter your email" readonly    >
                </div>
                <label for="password">Password</label>
                <div class="input-group">
                    <i class="bx bxs-lock-alt"></i>
                    <i class="bx bxs-hide show-hide"></i>
                    <input type="password" class="form-control" name="password" id="password" value="" placeholder="Password" required>
                </div>
                <label for="confirm_password">Confirm Password</label>
                <div class="input-group">
                    <i class="bx bxs-lock-alt"></i>
                    <i class="bx bxs-hide show-hide"></i>
                    <input type="password" class="form-control" name="confirm_password" id="confirm_password" value="" placeholder="Password" required>
                </div>

                <button class="btn" type="submit">Reset password</button>
                <p class="text-error" style="color: #EE4E4E;">${mess}</p>            
            </form>
        </div>
    </body>
    <script>
        const eyeIcons = document.querySelectorAll(".show-hide");
        eyeIcons.forEach((eyeIcon) => {
            eyeIcon.addEventListener("click", () => {
                const pInput = eyeIcon.parentElement.querySelector("input"); //getting parent element of eye icon and selecting the password input
                if (pInput.type === "password") {
                    eyeIcon.classList.replace("bxs-hide", "bxs-show");
                    return (pInput.type = "text");
                }
                eyeIcon.classList.replace("bxs-show", "bxs-hide");
                pInput.type = "password";
            });
        });
    </script>
</html>

