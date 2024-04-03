<%-- 
    Document   : verify
    Created on : Jan 12, 2024, 9:56:40 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Verify OTP</title>
        <link rel="stylesheet" href="css/verify.css">
    </head>
    <body>
        <div class="otp-box">
            <form id="loginForm">
                <div class="img">
                    <img src="https://apps.uk/wp-content/uploads/2022/11/verification-codes.png" alt="" width="300px">
                </div>
              
                <div class="inputs">
                    <input type="text" maxlength="5" id="Otp_code" required="">
                    <div class="verify-button">
                        <button type="submit" onclick="verifyButton">Verify</button></br></br>
                        <button type="button"><a href="home.jsp" target="target" style="text-decoration: none; color: white;">Home</a></button>
                    </div>
                </div>
            </form>
        </div>

    </div>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#loginForm').submit(function (e) {
                e.preventDefault(); // Prevents the default form submission

                var formData = {
                    otp_code: $('#Otp_code').val()
                };

                $.ajax({
                    type: 'POST',
                    url: 'VerifyCodeUpdate',
                    data: formData,
                    success: function (response) {
                        if (response === "success") {
                            alert("Update success !");

                        } else {
                            alert("OTP error!!!\nPlease input again!");
                        }
                    },
                    error: function (error) {
                        console.log(error);
                    }
                });
            });
        });
    </script>
</body>
</html>
