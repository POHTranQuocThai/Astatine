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
                <form action="Login" method="POST" class="form" id="form-login">
                    <h1>Login</h1>
                    <div class="form-group input-box">
                        <input type="text" placeholder="Email" id="email" name="email" class="form-control" value="${email != null ? email : ""}"/>
                        <i class='bx bxs-envelope'></i>
                        <span class="form-message"></span>
                    </div>
                    <div class="form-group input-box">
                        <input type="password" placeholder="Password" id="password" name="password" class="form-control"/>
                        <i class="bx bxs-lock-alt"></i>
                    </div>
                    <div class="forgot-link">
                        <span class="form-message">${mess != null ? mess : ""}</span>
                        <a href="#">Forgot password?</a>
                    </div>
                    <button type="submit" class="btn">Login</button>
                    <p>or login with social platforms</p>
                    <div class="social-icons">
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/Astatine/Login&response_type=code&client_id=713665687871-pqhbevvfk0h2vv7rnuu0id61qptou58f.apps.googleusercontent.com&approval_prompt=force">
                            <i class="bx bxl-google"></i>
                        </a>
                        <a href="#"><i class='bx bxl-facebook'></i></a>
                    </div>
                </form>
            </div>

            <div class="form-box register">
                <form  class="form" id="form-signup">
                    <h1>Register</h1>
                    <div class="form-group input-box">
                        <input type="text" placeholder="Full Name" id="fullname" name="fullname" class="form-control">
                        <i class='bx bxs-user'></i>
                        <span class="form-message">${existsFullname != null ? existsFullname : ""}</span>
                    </div>
                    <div class="form-group input-box">
                        <input type="text" placeholder="Email" id="email" name="email" class="form-control">
                        <i class='bx bxs-envelope'></i>
                        <span class="form-message">${existsEmail != null ? existsEmail : ""}</span>
                    </div>
                    <div class="form-group input-box">
                        <input type="password" placeholder="Password" id="password" name="password" class="form-control">
                        <i class="bx bxs-lock-alt"></i>
                        <span class="form-message"></span>
                    </div>
                    <div class="form-group input-box">
                        <input type="password" placeholder="Confirm Password" id="password_confirmation" name="password_confirmation" class="form-control">
                        <i class="bx bxs-lock-alt"></i>
                        <span class="form-message"></span>
                    </div>
                    <button type="submit" class="btn">Register</button>
                </form>
            </div>
            <div class="toggle-box">
                <div class="toggle-panel toggle-left">
                    <h1>Hello, Welcome!</h1>
                    <p>Don't have an account?</p>
                    <button class="btn register-btn">Register</button>
                </div>

                <div class="toggle-panel toggle-right">
                    <h1>Welcome Back!</h1>
                    <p>Already have an account?</p>
                    <button class="btn login-btn">Login</button>
                </div>
            </div>
        </div>

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var alertMessage = document.querySelector(".form-message.alert");

                if (!alertMessage.textContent.trim()) {
                    alertMessage.style.display = "none";
                } else {
                    alertMessage.style.display = "block";
                }
            });

            Validator({
                form: '#form-login',
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

                    // Gửi dữ liệu đến  bằng fetch
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

            Validator({
                form: '#form-signup',
                formGroupSelector: '.form-group',
                errorSelector: '.form-message',
                rules: [
                    Validator.isRequired('#fullname', 'Vui lòng nhập tên đầy đủ của bạn'),
                    Validator.minLength('#password', 6),
                    Validator.isRequired('#password_confirmation'),
                    Validator.isEmail('#email'),
                    Validator.isConfirmed('#password_confirmation', () => {
                        return document.querySelector('#form-signup #password').value;
                    }, 'Mật khẩu nhập lại không chính xác'),
                ],
                onsubmit: (data) => {
                    // Lấy dữ liệu từ các input
                    let fullname = document.getElementById('#form-signup #fullname').value;
                    let email = document.getElementById('#form-signup #email').value;
                    let password = document.getElementById('#form-signup #password').value;

                    // Tạo FormData để gửi dữ liệu tới Servlet
                    let formData = new FormData();
                    formData.append('fullname', fullname);
                    formData.append('email', email);
                    formData.append('password', password);

                    // Gửi dữ liệu đến Servlet bằng fetch
                    fetch('SignupServlet', {
                        method: 'POST',
                        body: formData
                    })
                            .then(response => response.text())
                            .then(data => {
                                // Xử lý phản hồi từ server
                                console.log(data);
                                if (data.includes("success")) {
                                    alert("Đăng ký thành công!");
                                }
                            })
                            .catch(error => {
                                console.error('Error:', error);
                            });
                }
            });
        </script>

        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <script src="assets/js/Validator/validator.js"></script>
        <script src="assets/js/JSRemake/login.js"></script>
    </body>
</html>
