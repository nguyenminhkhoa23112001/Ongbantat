/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */



/* global Swal */

$(document).ready(function () {
    $("#signin-Button2").click(function () {
        window.location.href = "login";
        refreshCaptcha();
    });
});

$(document).ready(function () {
    $("#signin-Button").click(function () {
        document.getElementById("signup1").style.display = 'none';
        document.getElementById("signin1").style.display = 'block';
        refreshCaptcha();
    });
});

$(document).ready(function () {
    $("#user-Signin").keypress(function (event) {
        if (event.keyCode === 13) {
            event.preventDefault();
            SignIn();
        }
    });

    $("#password-field").keypress(function (event) {
        if (event.keyCode === 13) {
            event.preventDefault();
            SignIn();
        }
    });

    $("#captcha").keypress(function (event) {
        if (event.keyCode === 13) {
            event.preventDefault();
            SignIn();
        }
    });
});


$(document).ready(function () {
    $("#user-Signin").keypress(function (event) {
        if (event.keyCode === 13) {
            event.preventDefault();
            SignIn();
        }
    });

    $("#password-field").keypress(function (event) {
        if (event.keyCode === 13) {
            event.preventDefault();
            SignIn();
        }
    });

    $("#captcha").keypress(function (event) {
        if (event.keyCode === 13) {
            event.preventDefault();
            SignIn();
        }
    });
});

function SignIn()
{
    var username = $("#user-Signin").val();
    var password = $("#password-field").val();
    var captcha = $("#captcha").val();
    $.ajax({
        type: "POST",
        url: "login",
        data: {
            username: username,
            password: password,
            capchaRespone: captcha
        },
        success: function (response) {
            if (response === "success" || response === "admin") {
                Swal.fire({
                    position: "bot-end",
                    icon: "success",
                    title: "Login success!",
                    showConfirmButton: false,
                    timer: 1000
                });
                setTimeout(function () {
                    window.location.href = "home";
                }, 1000);

            } else if (response === "verify") {
                window.location.href = "buy";
            } else {

                refreshCaptcha();

                Swal.fire({
                    icon: "error",
                    title: "Error...",
                    text: response

                });
            }
        }
    });
}
;

function refreshCaptcha()
{
    // Sử dụng AJAX để làm mới ảnh CAPTCHA
    $.get('refreshcaptcha', function () {
        // Thay đổi src của ảnh để làm mới
        $('#captchaImage').attr('src', 'captchaimage?' + new Date().getTime());
        document.getElementById("captcha").value = "";
    });
}
;






