<%-- 
    Document   : login
    Created on : Oct 14, 2024, 8:13:40 PM
    Author     : Tran Quoc Thai - CE181618 
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/css/login.css"/>
        <title>Astatine 04 | Welcome Back!</title>
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
            <div class="form-box login">
                <form action="Login" method="post" class="form" id="form-login">
                    <input type="hidden" name="action" value="login">
                    <h1>Login</h1>
                    <div class="field email-field">
                        <div class="input-box">
                            <input type="email" placeholder="Email" id="email" name="email" class="email" value="${email != null ? email:""}"/>
                            <i class='bx bxs-envelope'></i>
                        </div>
                        <span class="error email-error">
                            <i class="bx bx-error-circle error-icon"></i>
                            <p class="error-text">Please enter a valid email</p>
                        </span>
                    </div>

                    <div class="field password-field">
                        <div class="input-box">
                            <input type="password" placeholder="Password" id="password" name="password" class="password">
                            <i class="bx bxs-lock-alt"></i>
                            <i class="bx bxs-hide show-hide"></i>
                        </div>

                        <span class="error password-error">
                            <i class="bx bx-error-circle error-icon"></i>
                            <p class="error-text">
                                ${mess != null ? mess : "Please enter password"}
                            </p>
                        </span>

                        <span class="error password-error" style="display: ${mess != null ? 'flex' : 'none'};">
                            <i class="bx bx-error-circle error-icon"></i>
                            <p class="error-text">
                                ${mess != null ? mess : "Please enter password"}
                            </p>
                        </span>

                    </div>
                    <div class="forgot-link">
                        <a href="requestPassword">Forgot password?</a>
                    </div>
                    <button type="submit" class="btn">Login</button>
                    <p>or login with social platforms</p>
                    <div class="social-icons">
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/Login&response_type=code&client_id=713665687871-pqhbevvfk0h2vv7rnuu0id61qptou58f.apps.googleusercontent.com&approval_prompt=force">
                            <i class="bx bxl-google"></i>
                        </a>
                        <a href="#"><i class='bx bxl-facebook'></i></a>
                    </div>
                </form>
            </div>

            <div class="toggle-box">
                <div class="toggle-panel toggle-left">
                    <h1>Hello, Welcome!</h1>
                    <p>Don't have an account?</p>
                    <a class="btn register-btn" href="Signup">Register</a>
                </div>
            </div>
        </div>

        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <script src="assets/js/Validator/validLogin.js"></script>
    </body>
</html>
