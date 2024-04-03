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
            <form id="VerifyForm">
                <div class="img">
                    <img src="https://apps.uk/wp-content/uploads/2022/11/verification-codes.png" alt="" width="300px"/>
                </div>

                <div class="inputs">
                    <input type="text" maxlength="5" id="Otp_code" required="">
                    <div class="verify-button">
                        <button type="submit">Verify</button></br></br>
                        <button type="button" id="loginButton">Login</button>
                    </div></br>
                    <div
                        
                    <button onclick="resendMail()" style=""> Resend Email </button>
                    
                </div>
            </form>
        </div>

    </div>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script>
                        $(document).ready(function () {
                            $('#VerifyForm').submit(function (e) {
                                e.preventDefault(); // Prevents the default form submission

                                var formData = {
                                    otp_code: $('#Otp_code').val()
                                };

                                $.ajax({
                                    type: 'POST',
                                    url: 'VerifyCode',
                                    data: formData,
                                    success: function (response) {
                                        if (response === "success") {
                                            Swal.fire({
                                                position: "bot-end",
                                                icon: "success",
                                                title: "Verify success!",
                                                showConfirmButton: false,
                                                timer: 1000
                                            });
                                            setTimeout(function () {
                                                window.location.href = "login";
                                            }, 1000);

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
                        
                        $("#loginButton").click(function (){
                           window.location.href = "login"; 
                        });

                        function resendMail() {
                            var formData = {
                                otp_code: $('#Otp_code').val()
                            };

                            $.ajax({
                                type: 'GET',
                                url: "VerifyCode",
                                data: formData,
                                success: function (response) {
                                    if (response === "success") {
                                        alert("Resend email success");
                                        Swal.fire({
                                            position: "bot-end",
                                            icon: "success",
                                            title: "Verify success!",
                                            showConfirmButton: false,
                                            timer: 1000
                                        });
                                        setTimeout(function () {
                                            window.location.href = "login";
                                        }, 1000);

                                    } else {
                                        alert(response);
                                    }
                                },
                                error: function (error) {
                                    console.log(error);
                                }

                            });
                        }
                        
                        
    </script>

</body>
</html>
