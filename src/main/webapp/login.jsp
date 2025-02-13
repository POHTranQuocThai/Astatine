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
                <form method="POST" class="form" action="Login" id="form-1">
                    <h1>Login</h1>
                    <div class="form-group input-box email">
                        <input type="text" placeholder="Email" id="email" name="email" class="form-control" value="${email != null ? email : ""}"/>
                        <i class='bx bxs-envelope'></i>
                        <span class="form-message"></span>
                    </div>
                    <div class="form-group input-box password">
                        <input type="password" placeholder="Password" id="password" name="password" class="form-control"/>
                        <i class="bx bxs-lock-alt"></i>
                        <span class="alert alert-danger form-message" role="alert" style="${mess != null && !mess.isEmpty() ? 'display:block;' : 'display:none;'}">
                            ${mess}
                        </span>
                    </div>
                    <div class="forgot-link">
                        <a href="#">Forgot password?</a>
                    </div>
                    <button type="submit" class="btn">Login</button>
                    <p>or login with social platforms</p>
                    <div class="social-icons">
                        <a href="#"><i class="bx bxl-google"></i></a>
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

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                let isLoginActive = localStorage.getItem("loginActive");

                if (isLoginActive === "true") {
                    document.querySelector(".container").classList.add("active");
                } else {
                    document.querySelector(".container").classList.remove("active");
                }

                document.querySelector(".register-btn").addEventListener("click", function (event) {
                    event.preventDefault();
                    localStorage.setItem("loginActive", "true");
                    window.location.href = "Signup";
                });
            });

            document.addEventListener("DOMContentLoaded", function () {
                var alertMessage = document.querySelector(".form-message.alert");

                if (!alertMessage.textContent.trim()) {
                    alertMessage.style.display = "none";
                } else {
                    alertMessage.style.display = "block";
                }
            });

            Validator({
                form: '#form-1',
                formGroupSelector: '.form-group',
                errorSelector: '.form-message',
                rules: [
                    Validator.isEmail('#email'),
                    Validator.isRequired('#password')
                ],
                onsubmit: (data) => {
                    let email = document.getElementById('email').value;
                    let password = document.getElementById('password').value;

                    let formData = new FormData();
                    formData.append('email', email);
                    formData.append('password', password);

                    fetch('LoginServlet', {
                        method: 'POST',
                        body: formData
                    })
                            .then(response => response.text())
                            .then(data => {
                                console.log(data);
                            })
                            .catch(error => {
                                console.error('Error:', error);
                            });
                }
            });
        </script>

        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
    </body>
</html>
