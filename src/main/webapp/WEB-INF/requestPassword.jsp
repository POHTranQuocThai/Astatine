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
            <p>Enter Your Mail Send Link Your Mail Please Check And Verify If Your Mail Before Account Create You Have A Link Inbox. Click Link & Your New Password</p>
            <form action="requestPassword" method="POST">
                <label for="email">Enter Your Email</label>
                <div class="field email-field">
                    <div class="input-group">
                        <i class='bx bxs-envelope'></i>
                        <input type="email" name="email" id="email" placeholder="Enter your email" required>
                    </div>
                    <p class="text-error" style="color: #EE4E4E;">${mess}</p>
                    <button class="btn" type="submit">Send Reset Email</button>
                </div>
            </form>
        </div>
    </body>
</html>