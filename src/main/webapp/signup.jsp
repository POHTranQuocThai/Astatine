<%-- 
    Document   : signup
    Created on : Oct 14, 2024, 8:14:06 PM
    Author     : Tran Quoc Thai - CE181618 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="assets/css/login.css"/>

        <title>Astatine 04 | Hello Welcome!</title>
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
                <form method="POST" class="form" action="Signup" id="form-1">
                    <h1>Register</h1>
                    <div class="form-group input-box">
                        <input type="text" placeholder="Full Name" id="fullname" name="fullname" class="form-control" required value="${fullname}">
                        <i class='bx bxs-user'></i>
                        <span class="form-message">${existsFullname != null ? existsFullname : ""}</span>
                    </div>
                    <div class="form-group input-box">
                        <input type="text" placeholder="Email" id="email" name="email" class="form-control" required value="${email}">
                        <i class='bx bxs-envelope'></i>
                        <span class="form-message">${existsEmail != null ? existsEmail : ""}</span>
                    </div>
                    <div class="form-group input-box">
                        <input type="password" placeholder="Password" id="password" name="password" class="form-control" required>
                        <i class="bx bxs-lock-alt"></i>
                        <span class="form-message"></span>
                    </div>
                    <div class="form-group input-box">
                        <input type="password" placeholder="Confirm Password" id="password_confirmation" name="password_confirmation" class="form-control" required>
                        <i class="bx bxs-lock-alt"></i>
                        <span class="form-message"></span>
                    </div>
                    <button type="submit" class="btn">Register</button>
                    <p>or register with social platforms</p>
                    <div class="social-icons">
                        <a href="#"><i class="bx bxl-google"></i></a>
                        <a href="#"><i class='bx bxl-facebook'></i></a>
                    </div>
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

        <script>
            document.addEventListener("DOMContentLoaded", function () {
                let isLoginActive = localStorage.getItem("loginActive");

                if (isLoginActive === "true") {
                    document.querySelector(".container").classList.add("active");
                } else {
                    document.querySelector(".container").classList.remove("active");
                }

                document.querySelector(".login-btn").addEventListener("click", function (event) {
                    event.preventDefault();
                    localStorage.setItem("loginActive", "false");
                    window.location.href = "Login";
                });
            });

            Validator({
                form: '#form-1',
                formGroupSelector: '.form-group',
                errorSelector: '.form-message',
                rules: [
                    Validator.isRequired('#fullname', 'Please enter your full name'),
                    Validator.isEmail('#email', 'Please enter a valid email'),
                    Validator.minLength('#password', 6, 'Password must be at least 6 characters'),
                    Validator.isConfirmed('#password_confirmation', () => {
                        return document.querySelector('#form-1 #password').value;
                    }, 'Passwords do not match'),
                ],
                onsubmit: (data) => {
                    let fullname = document.getElementById('fullname').value;
                    let email = document.getElementById('email').value;
                    let password = document.getElementById('password').value;

                    let formData = new FormData();
                    formData.append('fullname', fullname);
                    formData.append('email', email);
                    formData.append('password', password);

                    fetch('SignupServlet', {
                        method: 'POST',
                        body: formData
                    })
                            .then(response => response.text())
                            .then(data => {
                                console.log(data);
                                if (data.includes("success")) {
                                    alert("Registration successful!");
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
    </body>
</html>
