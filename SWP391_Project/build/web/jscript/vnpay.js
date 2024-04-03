/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


$(document).ready(function () {
    $('#vnpayForm').submit(function (e) {
    e.preventDefault(); // Ngăn chặn gửi yêu cầu mặc định

    // Lấy giá trị của trường "amount"
    var amount = parseFloat($('#amountVnpay').val());

    // Kiểm tra nếu giá trị amount nhỏ hơn 10000
    if (amount < 10000) {
        alert("Số tiền phải lớn hơn 10.000đ");
        return; // Dừng xử lý tiếp theo
    }

    // Nếu amount hợp lệ, tiến hành gửi yêu cầu AJAX
    var formData = {
        amount: amount,
        description: $('#desVnpay').val()
    };

    $.ajax({
        type: 'post',
        url: 'ajaxServlet',
        data: formData,
        success: function (response) {
            window.location.href = response;
        },
        error: function (xhr) {
            refreshCaptcha();
            console.error(xhr.responseText);
        }
    });
});

});

