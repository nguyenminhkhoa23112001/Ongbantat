<%-- 
    Document   : Home
    Created on : Jan 15, 2024, 9:39:31 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

        <title>Electro - HTML Ecommerce Template</title>

        <!-- Google font -->
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


        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <style>
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



        </style>

    </head>

    <body>
        <!-- HEADER -->
        <%@include file="components/navBar.jsp" %>
        <!-- /NAVIGATION -->
        <!-- /NAVIGATION -->
        <!-- SECTION -->
        <div class="section" >
            <!-- container -->
            <div  class="container">
                <!-- row -->
                <div class="row">

                    <!-- section title -->
                    <div class="col-md-12"  >
                        <div class="section-title">
                            <h3 class="title">Product</h3>
                            <div class="section-nav">
                                <ul class="section-tab-nav tab-nav">
                                    <!-- Correctly setting 'All' category selection -->
                                    <li><a href="javascript:void(0);" onclick="redirectToController('all')">All</a></li>
                                        <c:forEach items="${listCategory}" var="category">

                                        <li><a href="javascript:void(0);" onclick="redirectToController('${category.id}')">${category.name}</a></li>
                                        </c:forEach>
                                </ul>
                            </div>
                        </div>

                    </div>
                    <div class="col-md-12">
                        <div class="row">
                            <div class="products-tabs">

                                <!-- tab -->
                                <div id="Listproduct" class="tab-pane fade in active">
                                    <c:forEach items="${listProductPage}" var="p" >

                                        <div class="col-md-3">
                                            <!-- product -->

                                            <div class="product">
                                                <div class="product-img">
                                                    <img src="imagesUpload/${p.image1}" alt="" style="height: 150px;">

                                                </div>
                                                <div class="product-body">

                                                    <p class="product-category">Danh mục</p>
                                                    <h3 class="product-name"><a href="ProductDetail?id=${p.id}">${p.name}</a></h3>
                                                    <h4 class="product-price"><c:out value="${String.format('%,.0f', p.price)}" /></h4> 

                                                    <div class="product-btns">
                                                        <button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span
                                                                class="tooltipp">add to wishlist</span></button>
                                                        <button class="add-to-compare"><i class="fa fa-exchange"></i><span
                                                                class="tooltipp">add to compare</span></button>
                                                        <button class="quick-view"><i class="fa fa-eye"></i><span
                                                                class="tooltipp">quick view</span></button>
                                                    </div>
                                                </div>
                                                <div class="add-to-cart">
                                                    <button class="add-to-cart-btn buy-button1" data-target="cookiesPopup">
                                                        <i class="fa fa-shopping-cart"></i>Mua
                                                    </button>
                                                    <button class="add-to-cart-btn buy-button" onclick="addToCart(${p.id})">
                                                        <i class="fa fa-shopping-cart"></i>Thêm
                                                    </button>

                                                </div>
                                            </div>
                                            <div class="container-2">
                                                <div class="cookiesContent cookiesPopup">
                                                    <button class="close">✖</button>
                                                    <img src="https://dichthuatmientrung.com.vn/wp-content/uploads/2022/06/important-sticky-note.jpg" alt="cookies-img" style="width: 50%;"/>
                                                    <p style="color:red; margin-top: 5%;">Bạn sẽ phải trả tổng số tiền là: <c:out value="${String.format('%,.0f',dao.getOrderByProductID(p.id).getTotal_paid_amount())}" /> cho sản phẩm này!</p>
                                                    <p style="color:red;">Bấm mua nếu bạn chấp nhận hệ thống giữ tiền trung gian !!!</p>
     
                                                    <button class="button-buy" data-id="${p.id}">BUY</button>
                                                </div>
                                            </div>               
                                            <!-- /product -->
                                        </div>
                                    </c:forEach>

                                </div>

                            </div>

                        </div>
                    </div>
                    <!-- /tab -->
                </div>
            </div>
        </div>
        <div style="display: flex; justify-content: center; align-items: center;">
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <li class="page-item">
                        <c:forEach var="i" begin="1" end="${requestScope.Count}">
                            <a onclick="loadPage('${i}')" class="page-link" style="${requestScope.page == i ? "  color: black; border:1px solid #000;padding-right : 10px; padding-left : 10px" : ""}" adding-right : 25px href="javascript:void(0);" >
                                ${i}
                            </a>
                        </c:forEach>
                    </li>                   
                </ul>
            </nav>
        </div>
        <!-- /SECTION -->

        <!-- NEWSLETTER -->
        <div id="newsletter" class="section">
            <!-- container -->
            <div class="container">
                <!-- row -->
                <div class="row">
                    <div class="col-md-12">
                        <div class="newsletter">
                            <p>Sign Up for the <strong>NEWSLETTER</strong></p>
                            <form>
                                <input class="input" type="email" placeholder="Enter Your Email">
                                <button class="newsletter-btn"><i class="fa fa-envelope"></i> Subscribe</button>
                            </form>

                        </div>
                    </div>
                </div>
                <!-- /row -->
            </div>
            <!-- /container -->
        </div>
        <!-- /NEWSLETTER -->



        <script>
            document.addEventListener("DOMContentLoaded", function () {
                var buyButtons = document.querySelectorAll('.buy-button');
                buyButtons.forEach(function (buyButton) {
                    buyButton.onclick = function () {
                        var cookiesPopup = this.closest('.col-md-3').querySelector('.cookiesPopup');
                        cookiesPopup.style.display = 'block';
                    };
                });

                var closeButtons = document.querySelectorAll('.close');
                closeButtons.forEach(function (closeButton) {
                    closeButton.onclick = function () {
                        var popup = this.closest('.cookiesContent');
                        popup.style.display = 'none';
                    };
                });
            });

            document.addEventListener("click", function (event) {
                if (event.target.matches('.buy-button1')) {
                    var cookiesPopup = event.target.closest('.col-md-3').querySelector('.cookiesPopup');
                    cookiesPopup.style.display = 'block';
                }

                if (event.target.matches('.close')) {
                    var popup = event.target.closest('.cookiesContent');
                    popup.style.display = 'none';
                }
            });


        </script>
        <script>
            function addToCart(productId) {
                // Gửi giá trị productId đến servlet bằng Ajax
                $.ajax({
                    type: "POST", // Hoặc "GET" tùy thuộc vào yêu cầu của bạn
                    url: "AddToCartController", // Thay thế bằng URL của servlet của bạn
                    data: {productId: productId},
                    success: function (response) {
                        // Xử lý kết quả từ servlet nếu cần
                        alert(response);
                        window.location.href = "home";
                    },
                    error: function (error) {
                        console.log("Error:", error);
                    }
                });
            }

            // Lấy tham chiếu đến button và popup container
            const showPopupButton = document.getElementById('notification-Button');
            const popupContainer = document.getElementById('popupContainer');
            const tablePopup = document.getElementById('notificationTable');
// Thêm sự kiện click cho nút
            showPopupButton.addEventListener('click', function () {
                // Hiện popup container
                popupContainer.style.display = 'block';
                tablePopup.style.display = 'block';

                // Khóa cuộn trang
                document.body.style.overflow = 'hidden';
            });
        </script>
        <!-- FOOTER -->
        <%@include file="components/footer.jsp" %>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="jscript/signin_captcha.js"></script>

        <script src="js1/jquery.min.js"></script>
        <script src="js1/bootstrap.min.js"></script>
        <script src="js1/slick.min.js"></script>
        <script src="js1/nouislider.min.js"></script>
        <script src="js1/jquery.zoom.min.js"></script>
        <script src="js1/main.js"></script>
        <script src="jscript/myorder.js"></script>
        <script>
            $(document).ready(function () {
                $(document).on('click', '.button-buy', function () {
                    var productId = $(this).data("id");
                    $.ajax({
                        type: 'post',
                        url: "buy",
                        data: {id: productId},
                        success: function (response) {
                            alert(response);
                            window.location.href = "home";
                        },
                        error: function (xhr) {
                            // Xử lý lỗi nếu có
                            console.error(xhr.responseText);


                        }
                    });
                });
            });

            function redirectToController(categoryId) {
                // Construct the URL based on whether a categoryId is provided
                $.ajax({
                    url: "home", // Update this URL to your server endpoint that handles the category request
                    type: "GET",
                    data: {
                        categoryId: categoryId
                    },
                    success: function (response) {
                        // Assuming 'response' contains the HTML or data to update the product list
                        updateProductList(response);
                    },
                    error: function (xhr, status, error) {
                        // Handle any errors here
                        console.error("An error occurred while fetching the category data:", error);
                    }
                });
            }

            function updateProductList(data) {
                // Assuming 'data' is the HTML content to be placed inside the product list container
                // Find the container in your DOM and update its content
                $('#Listproduct').html(data);
            }
            $(document).ready(function () {
                loadPage(1); // Load trang đầu tiên khi trang được tải lên
            });

            function loadPage(page) {
                $.ajax({
                    url: 'home',
                    type: 'GET',
                    data: {page: page}, // Truyền số trang qua cho Servlet

                    success: function (response) {
                        updateProductList(response);
                    },
                    error: function (xhr, status, error) {
                        alert('Error while loading data');
                    }
                });
            }
            $(document).ready(function () {
                // Event listener for form submission
                $('#ajaxSearchForm').on('submit', function (e) {
                    e.preventDefault(); // Prevent default form submission

                    var formData = $(this).serialize(); // Serialize form data

                    $.ajax({
                        type: "GET", // or "POST", depending on your requirement
                        url: "home", // the URL to submit the form data
                        data: formData,
                        success: function (response) {
                            // Success action
                            updateProductList(response);
                            // Example: update your page with the response
                            // $('#responseContainer').html(response);
                        },
                        error: function (xhr, status, error) {
                            // Error action
                            console.log("Error submitting form: " + error);
                        }
                    });
                });
            });

            
        </script>
    </body>
</html>

