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
        <!--        <link rel="stylesheet" href="assets/css/login.css"/>-->
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
                <form id="form-signup">
                    <input type="hidden" name="action" value="signup">
                    <h1>Register</h1>
                    <div class="field fullname-field">
                        <div class="input-box">
                            <input type="text" placeholder="Full Name" id="fullname" name="fullname" class="fullname">
                            <i class='bx bxs-user'></i>
                        </div>
                        <span class="error email-error">
                            <i class="bx bx-error-circle error-icon"></i>
                            <p class="error-text">Please enter a valid name </p>
                        </span>
                    </div>

                    <div class="field email-field">
                        <div class="input-box">
                            <input type="email" placeholder="Email" id="email" name="email" class="email"/>
                            <i class='bx bxs-envelope'></i>
                        </div>
                        <span class="error email-error">
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
            const signupForm = document.querySelector("#form-signup"),
                    signupFullnameField = signupForm.querySelector(".fullname-field"),
                    signupFullnameInput = signupFullnameField.querySelector(".fullname"),
                    signupEmailField = signupForm.querySelector(".email-field"),
                    signupEmailInput = signupEmailField.querySelector(".email"),
                    signupPassField = signupForm.querySelector(".password-field"),
                    signupPassInput = signupPassField.querySelector(".password"),
                    signupCPassField = signupForm.querySelector(".cpassword-field"),
                    signupCPassInput = signupCPassField.querySelector(".cPassword");

            function checkFullnameSignup() {
                if (signupFullnameInput.value === "") {
                    return signupFullnameField.classList.add("invalid");
                }
                signupFullnameField.classList.remove("invalid");
            }

            function checkEmailSignup() {
                const emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
                if (!signupEmailInput.value.match(emailPattern)) {
                    return signupEmailField.classList.add("invalid");
                }
                signupEmailField.classList.remove("invalid");
            }

            function createPassSignup() {
                if (signupPassInput.value.length < 6) {
                    return signupPassField.classList.add("invalid");
                }
                signupPassField.classList.remove("invalid");
            }

            function confirmPassSignup() {
                if (signupPassInput.value !== signupCPassInput.value || signupCPassInput.value === "") {
                    return signupCPassField.classList.add("invalid");
                }
                signupCPassField.classList.remove("invalid");
            }

            signupForm.addEventListener("submit", (e) => {
                e.preventDefault();
                checkFullnameSignup();
                checkEmailSignup();
                createPassSignup();
                confirmPassSignup();
                signupFullnameInput.addEventListener("input", checkFullnameSignup);
                signupEmailInput.addEventListener("keyup", checkEmailSignup);
                signupPassInput.addEventListener("input", createPassSignup);
                signupCPassInput.addEventListener("input", confirmPassSignup);

                // Nếu tất cả đều hợp lệ thì submit
                if (
                        !signupFullnameField.classList.contains("invalid") &&
                        !signupEmailField.classList.contains("invalid") &&
                        !signupPassField.classList.contains("invalid") &&
                        !signupCPassField.classList.contains("invalid")
                        ) {
                    let formData = new FormData(signupForm);

                    fetch("Signup", {
                        method: "POST",
                        body: formData
                    })
                            .then(response => response.text())
                            .then(data => {
                                console.log(data); // In ra console để kiểm tra phản hồi từ servlet
                            })
                            .catch(error => console.error("Lỗi:", error));
                }
            });

        </script>
        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <!--        <script src="assets/js/Validator/validLogin.js"></script>-->
        <script src="assets/js/Validator/validSignup.js"></script>
        <!--        <script src="assets/js/JSRemake/login.js"></script>-->
    </body>
</html>
