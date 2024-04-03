<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Giỏ hàng</title>
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>

        <!-- Slick -->
        <link type="text/css" rel="stylesheet" href="css/slick.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>

        <!-- nouislider -->
        <link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>

        <!-- Font Awesome Icon -->
        <link rel="stylesheet" href="css/font-awesome.min.css">

        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="css/style.css"/>
        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
            }

            .container {
                max-width: 800px;
                margin: 50px auto; /* Canh giữa khung lớn */
                padding: 20px;
                background-color: #fff; /* Màu nền cho khung lớn */
                border-radius: 10px; /* Bo tròn góc */
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Hiệu ứng bóng */
            }

            h2 {
                color: #333;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }

            th, td {
                border: 1px solid #ddd;
                padding: 10px;
                text-align: left;
            }

            th {
                background-color: #f2f2f2;
            }

            .delete-icon {
                color: #ff0000;
                cursor: pointer;
            }

            .buy-button {
                background-color: #008000;
                color: #ffffff;
                padding: 8px 15px;
                text-decoration: none;
                border-radius: 5px;
                display: inline-block;
            }

            .checkout-btn {
                background-color: #4CAF50;
                color: #ffffff;
                padding: 10px 15px;
                text-decoration: none;
                border-radius: 5px;
                display: inline-block;
                margin-top: 20px;
            }

            .container-2 {
                position: relative;
            }

            .cookiesContent {
                text-align: center;
                display: none;
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: #fff;
                padding: 20px;
                border: 1px solid #ccc;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                text-align: center;
                z-index: 9999; /* Đặt z-index để thẻ luôn hiển thị trên cùng */
            }

            .show {
                display: block !important;
            }

            .button-buy {
                position: relative;
                display: inline-block;
                margin: 15px;
                padding: 15px 15px;
                text-align: center;
                font-size: 18px;
                letter-spacing: 1px;
                text-decoration: none;
                color: #725AC1;
                background: transparent;
                cursor: pointer;
                transition: ease-out 0.5s;
                border: 2px solid #725AC1;
                border-radius: 10px;
                box-shadow: inset 0 0 0 0 #725AC1;
            }

            .button-buy:hover {
                color: white;
                box-shadow: inset 0 -100px 0 0 #725AC1;
            }

            .button-buy:active {
                transform: scale(0.9);
            }
            .delete-icon {
                display: inline-block;
                width: 30px;
                height: 30px;
                background-color: #ff0000; /* Màu đỏ */
                border-radius: 10px; /* Độ cong của góc */
                text-align: center;
                line-height: 30px;
                cursor: pointer;
            }

            .delete-icon i {
                color: #ffffff; /* Màu trắng cho biểu tượng */
            }
            .add-to-cart-btn {
                background-color: #008000; /* Màu nền xanh */
                color: #ffffff; /* Màu chữ màu trắng */
                border: none;
                border-radius: 10px; /* Bo góc */
                padding: 8px 15px;
                cursor: pointer;
                transition: ease-out 0.5s;
            }

            .add-to-cart-btn:hover {
                background-color: #725AC1; /* Màu nền khi di chuột qua */
            }

            .add-to-cart-btn:hover i {
                color: #ffffff; /* Màu biểu tượng màu trắng khi di chuột qua */
            }


        </style>
    </head>
    <body>


        <div style="max-width: 800px; margin: 0 auto;" >
            <div class="container">
                <h2>GIỎ HÀNG</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Tên sản phẩm</th>
                            <th>Hình ảnh</th>
                            <th>Giá sản phẩm</th>
                            <th>Xóa</th>
                            <th>Mua</th>
                        </tr>
                    </thead>
                    <tbody id="tableCart">
                        <c:forEach items="${comboX}" var="x"  varStatus="loop">
                            <c:if test="${dao.getProductByID(x.productID).isIs_delete() != true}">
                                <tr>
                                    <td>${dao.getProductByID(x.productID).getName()}</td>
                                    <td><img src="${dao.getProductByID(x.productID).getImage1()}" alt="" style="height: 50px;"/></td>
                                    <td>${dao.getProductByID(x.productID).getPrice()}</td>
                                    <td><span class="delete-icon" onclick="deleteProduct(${dao.getProductByID(x.productID).getId()})"><i class="fa fa-trash"></i></span></td>
                                    <td><button class="add-to-cart-btn" id="buyButton_${loop.index}" data-target="cookiesPopup_${loop.index}">
                                            <i class="fa fa-shopping-cart"></i> MUA
                                        </button></td>
                                </tr>
                            <div class="container-2">
                                <div class="cookiesContent" id="cookiesPopup_${loop.index}">
                                    <button class="close">✖</button>
                                    <img src="https://dichthuatmientrung.com.vn/wp-content/uploads/2022/06/important-sticky-note.jpg" alt="cookies-img" style="width: 50%;"/>
                                    <p style="color:red; margin-top: 5%;">Chúng tôi sẽ giữ tiền trung gian của bạn và đợi cho đến khi bạn xác nhận giao dịch hoàn toàn thành công</p>
                                    <button class="button-buyx" data-id="${dao.getProductByID(x.productID).getId()}"> Mua</button>
                                </div>
                            </div>   
                        </c:if>

                    </c:forEach>
                    </tbody>
                </table>

                <a href="home" class="checkout-btn">Trở về trang chủ</a>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

        <script>

                                        // Sử dụng event delegation:
                                        document.addEventListener('click', function (event) {
                                            var target = event.target;

                                            // Kiểm tra xem nút BUY được nhấp hay không
                                            if (target.classList.contains('add-to-cart-btn')) {
                                                var targetId = target.getAttribute('data-target');
                                                var popup = document.getElementById(targetId);
                                                if (popup) {
                                                    popup.style.display = 'block';
                                                }
                                            }

                                            // Kiểm tra xem nút đóng được nhấp hay không
                                            if (target.classList.contains('close')) {
                                                var popup = target.closest('.cookiesContent');
                                                if (popup) {
                                                    popup.style.display = 'none';
                                                }
                                            }

                                            // Kiểm tra xem nút BUY trong popup được nhấp hay không
                                            if (target.classList.contains('button-buyx')) {
                                                var productId = target.getAttribute('data-id');
                                                $.ajax({
                                                    type: 'post',
                                                    url: 'buy',
                                                    data: {id: productId},
                                                    success: function (response) {
                                                        alert(response);
                                                        window.location.href = 'AddToCartController';
                                                    },
                                                    error: function () {
                                                        alert('Đã xảy ra lỗi khi tải trang');
                                                    }
                                                });
                                            }
                                        });


                                        

                                        document.addEventListener('click', function (event) {
                                            var target = event.target;

                                            if (target.classList.contains('button-buy')) {
                                                var productId = target.getAttribute('data-id'); // Lấy ID của sản phẩm từ thuộc tính data-id của nút                                                
                                                // Xóa sản phẩm khỏi giỏ hàng bằng AJAX
                                                deleteProduct(productId);
                                            }
                                            // Các phần xử lý khác ở đây
                                        });

                                        function deleteProduct(deleteProductId) {

                                            $.ajax({
                                                type: "POST", // Hoặc "GET" tùy thuộc vào yêu cầu của bạn
                                                url: "EditReportStatus", // Thay thế bằng URL của servlet của bạn
                                                data: {deleteProductId: deleteProductId},
                                                success: function (response) {
                                                    // Xử lý kết quả từ servlet nếu cần
                                                    $("#tableCart").html(response);
                                                },
                                                error: function (error) {
                                                    console.log("Error:", error);
                                                }
                                            });
                                        }
        </script>
    </body>
</html>
