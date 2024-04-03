/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


$(document).ready(function () {
    $("#signup-Button").click(function () {
        $.ajax({
            type: 'GET',
            url: "VerifyUser",
            success: function (response) {
                window.location.href = ""
            },
            error: function () {
                // Xử lý lỗi nếu có
                alert("Đã xảy ra lỗi khi tải trang");
            }
        });
    });
});

$(document).ready(function () {
    $("#signupButton1").click(function () {
        document.getElementById("signin1").style.display = 'none';
        document.getElementById("signup1").style.display = 'block';
        refreshCaptcha2();
    });
});

$(document).ready(function () {
    $('#signupForm').submit(function (e) {
        e.preventDefault(); // 
        $("signup-button2").prop("disable", true);
        var formData = {
            user: $('#username').val(),
            pass: $('#password-signup').val(),
            confirmPass: $("#confirm-Pass").val(),
            email: $('#email').val(),
            capchaRespone: $("#captcha2").val()
        };

        $.ajax({
            type: 'POST',
            url: 'VerifyUser',
            data: formData,

            success: function (response) {
                if (response === "success") {
                    window.location.href = "buy";
                    $("signup-button2").prop("disable", false);
                    refreshCaptcha2();
                } else {
                    refreshCaptcha2();
                    $("signup-button2").prop("disable", false);
                    alert(response);
                }
            },
            error: function (error) {
                refreshCaptcha2();
                console.log(error);
            }
        });
    });
});

function refreshCaptcha2()
{
    // Sử dụng AJAX để làm mới ảnh CAPTCHA
    $.get('refreshcaptcha', function () {
        // Thay đổi src của ảnh để làm mới
        $('#captchaImage2').attr('src', 'captchaimage?' + new Date().getTime());
        document.getElementById("captcha2").value = "";
    });
}
;




