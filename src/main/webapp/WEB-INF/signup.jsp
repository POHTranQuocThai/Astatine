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
        <link rel="stylesheet" href="assets/css/signup.css"/>
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
            <div class="form-box register">
                <form action="Signup" method="post" class="form" id="form-signup">
                    <input type="hidden" name="action" value="signup">
                    <h1>Register</h1>
                    <div class="field fullname-field">
                        <div class="input-box">
                            <input type="text" placeholder="Full Name" id="fullname" name="fullname" class="fullname" value="${fullname != null ? fullname : ''}">
                            <i class='bx bxs-user'></i>
                        </div>
                        <span class="error email-error">
                            <i class="bx bx-error-circle error-icon"></i>
                            <p class="error-text">Please enter a valid name </p>
                        </span>
                    </div>

                    <div class="field email-field">
                        <div class="input-box">
                            <input type="email" placeholder="Email" id="email" name="email" class="email" value="${email != null ? email : ''}"/>
                            <i class='bx bxs-envelope'></i>
                        </div>
                        <span class="error email-error">
                            <i class="bx bx-error-circle error-icon"></i>
                            <p class="error-text">${existsEmail != null ? existsEmail : "Please enter a valid email"}</p>
                        </span>
                        <span class="error email-exist" style="display: ${existsEmail != null ? 'flex' : 'none'};">
                            <i class="bx bx-error-circle error-icon"></i>
                            <p class="error-text">${existsEmail != null ? existsEmail : "Please enter a valid email"}</p>
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
                                Please enter at least 6 character.
                            </p>
                        </span>
                    </div>
                    <div class="field cpassword-field">
                        <div class="input-box">
                            <input type="password" placeholder="Confirm Password" id="password_confirmation" name="password_confirmation" class="cPassword">
                            <i class="bx bxs-lock-alt"></i>
                            <i class="bx bxs-hide show-hide"></i>
                        </div>

                        <span class="error cPassword-error">
                            <i class="bx bx-error-circle error-icon"></i>
                            <p class="error-text">
                                Password don't match.
                            </p>
                        </span>
                    </div>

                    <button type="submit" class="btn">Register</button>
                </form>
            </div>
            <div class="toggle-box">
                <div class="toggle-panel toggle-right">
                    <h1>Welcome Back!</h1>
                    <p>Already have an account?</p>
                    <a class="btn login-btn" href="Login">Login</a>
                </div>
            </div>
        </div>

        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <script src="assets/js/Validator/validSignup.js"></script>
    </body>
</html>
