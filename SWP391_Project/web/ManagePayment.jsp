<%-- 
    Document   : ManagePayment
    Created on : Mar 16, 2024, 3:28:55 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--
=========================================================
* Argon Dashboard 2 - v2.0.4
=========================================================

* Product Page: https://www.creative-tim.com/product/argon-dashboard
* Copyright 2022 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://www.creative-tim.com/license)
* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
-->
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="apple-touch-icon" sizes="76x76" href="./assets/img/apple-icon.png">
        <link rel="icon" type="image/png" href="./assets/img/favicon.png">
        <title>
            Quản lí thanh toán
        </title>
        <!--     Fonts and icons     -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet" />
        <!-- Nucleo Icons -->
        <link href="./assets/css/nucleo-icons.css" rel="stylesheet" />
        <link href="./assets/css/nucleo-svg.css" rel="stylesheet" />
        <!-- Font Awesome Icons -->
        <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
        <link href="./assets/css/nucleo-svg.css" rel="stylesheet" />
        <!-- CSS Files -->
        <link id="pagestyle" href="./assets/css/argon-dashboard.css?v=2.0.4" rel="stylesheet" />
        <style>
            .main-content {
                min-height: 60vh; /* Thay đổi chiều cao để nâng cao lên */
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .custom-form {
                background-color: #ffffff;
                border-radius: 10px;
                padding: 20px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            }

            .custom-form form {
                margin-bottom: 0;
            }

            .custom-form label {
                font-weight: bold;
            }

            .custom-form .form-control {
                border-radius: 8px;
            }

            .custom-form .custom-btn {
                width: 100%;
            }

            .ftco-section {
                display: block; /* Ensure the section is displayed */
                margin: auto; /* Center the section horizontally */
                width: 80%; /* Adjust the width as needed */
            }

            .table-wrap {
                overflow-x: auto; /* Allow horizontal scrolling if necessary */
            }

            .table {
                width: 100%; /* Ensure the table takes up full width */
                margin-bottom: 1rem; /* Add spacing between the table and other elements */
            }

            /* Center the modal */
            .modal-dialog {
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                z-index: 1050; /* Ensure the modal is above other elements */
                max-width: 800px; /* Set maximum width for the modal */
                width: 90%; /* Adjust width as needed */
                padding: 20px;
                border-radius: 10px;


            }

            /* Semi-transparent backdrop */
            .modal-backdrop {
                position: fixed;
                top: 0;
                right: 0;
                bottom: 0;
                left: 0;
                background-color: rgba(0, 0, 0, 0.5); /* Semi-transparent background */
                z-index: 1040; /* Ensure the backdrop is behind the modal */
            }

            


        </style>
    </head>

    <body class="g-sidenav-show   bg-gray-100">
        <aside class="sidenav bg-white navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-4 " id="sidenav-main">
            <div class="sidenav-header">
                <a class="navbar-brand m-0" target="_blank">
                    <span class="ms-1 font-weight-bold">Quản lí thanh toán</span>
                </a>
            </div>
            <hr class="horizontal dark mt-0">
            <div class="collapse navbar-collapse  w-auto " id="sidenav-collapse-main">
                <ul class="navbar-nav">
                    <li class="nav-item" id="depositeButton">
                        <a class="nav-link active">
                            <div class="icon icon-shape icon-sm border-radius-md text-center me-2 d-flex align-items-center justify-content-center">
                                <i class="ni ni-tv-2 text-primary text-sm opacity-10"></i>
                            </div>
                            <span class="nav-link-text ms-1" id="formDeposite">Nạp tiền</span>
                        </a>
                    </li>
                    <li class="nav-item" id="withdrawlButton">
                        <a class="nav-link " >
                            <div class="icon icon-shape icon-sm border-radius-md text-center me-2 d-flex align-items-center justify-content-center">
                                <i class="ni ni-calendar-grid-58 text-warning text-sm opacity-10"></i>
                            </div>
                            <span class="nav-link-text ms-1" id="">Rút tiền</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="transaction">
                            <div class="icon icon-shape icon-sm border-radius-md text-center me-2 d-flex align-items-center justify-content-center">
                                <i class="ni ni-credit-card text-success text-sm opacity-10"></i>
                            </div>
                            <span class="nav-link-text ms-1">Lịch sử giao dịch</span>
                        </a>
                    </li>
                </ul>

                    <div class="sidenav-footer mx-3 ">
                        <a class="btn btn-primary btn-sm mb-0 w-100" href="home" type="button">Trang chủ</a>
                    </div>
                    </aside>
                    <main class="main-content position-relative border-radius-lg d-flex justify-content-center align-items-center">
                        <div class="row" style="display: flex;" id="depositeForm">
                            <div class="col-lg-5 mb-lg-0 mb-3">
                                <p class="mb-0"><span class="fw-bold">Phương thức</span><span class="c-green">: Cổng thanh toán VNPAY</span></p>
                                <p class="mb-0">Thu thêm 3000đ cho những đơn hàng dưới 100.000đ. Quý khách vui lòng nhập đúng thông tin tránh sai sót không đáng có !</p>
                                <div class="text-center mt-4">
                                    <a href="home" class="btn btn-success">Chú ý</a>
                                </div>
                            </div>
                            <div class="col-lg-7">
                                <div class="custom-form">
                                    <form class="form" id="vnpayForm">
                                        <div class="mb-3">
                                            <label for="amountVnpay" class="form-label">Số tiền:</label>
                                            <input name="amount" id="amountVnpay" type="text" class="form-control" placeholder="">
                                        </div>
                                        <div class="mb-3">
                                            <label for="desVnpay" class="form-label">Mô tả khoản nạp:</label>
                                            <input name="description" id="desVnpay" type="text" class="form-control" placeholder="">
                                        </div>
                                        <div class="mb-3">
                                            <input type="submit" class="btn btn-primary custom-btn" value="Nạp tiền">
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <section class="ftco-section" style="display: none;" id="withdrawlRequest">
                            <button id="showModalButton" class="mr-1 btn btn-success">
                                <i class="fa fa-plus"></i> Tạo yêu cầu mới 
                            </button>
                            <div class="container">
                                <div class="row justify-content-center">				
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="table-wrap">
                                            <table class="table table-striped">
                                                <thead>
                                                    <tr>
                                                        <th>Mã yêu cầu</th>
                                                        <th>Trạng thái xử lý</th>
                                                        <th>Số tiền rút</th>
                                                        <th>Số tài khoản</th>
                                                        <th>Chủ tài khoản</th>
                                                        <th>Tên ngân hàng</th>
                                                        <th>Chi nhánh ngân hàng</th>
                                                        <th>Phản hồi</th>
                                                        <th>Thời gian tạo</th>
                                                        <th>Cập nhật</th>                                                      
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${Listwithdrawal}" var="w">
                                                        <tr>
                                                            <th scope="row">${w.getCode()}</th>
                                                            <th>
                                                                <c:choose>
                                                                    <c:when test="${w.getStatus() eq 'Hoàn thành'}">
                                                                        <a href="#" class="btn btn-success">${w.getStatus()}</a>
                                                                    </c:when>
                                                                    <c:when test="${w.getStatus() eq 'Mới tạo'}">
                                                                        <a href="#" class="btn btn-info">${w.getStatus()}</a>
                                                                    </c:when>
                                                                    <c:when test="${w.getStatus() eq 'Bị từ chối'}">
                                                                        <a href="#" class="btn btn-danger">${w.getStatus()}</a>
                                                                    </c:when>                                                                                                                          
                                                                    <c:when test="${w.getStatus() eq 'Bị lỗi'}">
                                                                        <a href="#" class="btn btn-warning">${w.getStatus()}</a>
                                                                    </c:when>
                                                                    <c:when test="${w.getStatus() eq 'Chờ chuyển khoản'}">
                                                                        <a href="#" class="btn btn-color">${w.getStatus()}</a>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <a href="#" class="btn btn-secondary">${w.getStatus()}</a>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </th>
                                                            <th><c:out value="${String.format('%,.0f', w.getAmount())}" /></th>
                                                            <th>${w.getAccount_number()}</th>
                                                            <th>${w.getAccount_holder()}</th>
                                                            <th>${w.getBankname()}</th>
                                                            <th>${w.getBankbranch()}</th>
                                                            <th>${w.getResponse()}</th>
                                                            <th>${w.getCreated_at()}</th>
                                                            <th>${w.getUpdated_at()}</th>

                                                        </tr>
                                                    </c:forEach>                            
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>

                        <div class="modal-dialog modal-lg" role="document">
                            <div id="withdrawalModal" class="modal-content" style="display: none;">
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="card-group">
                                                <div class="card">
                                                    <div class="card-header">
                                                        <h3>Yêu cầu rút tiền</h3>
                                                    </div>
                                                    <div class="card-body">
                                                        <div autocomplete="off">
                                                            <form id="withdrawalForm">
                                                                <div class="position-relative row form-group">
                                                                    <div class="form-label-horizontal col-md-3">
                                                                        <label class=""><b>Số tiền rút (*)</b></label>
                                                                    </div>
                                                                    <div class="col-md-9">
                                                                        <input placeholder="Nhập vào số tiền cần rút" name="amount" id="amountInput" type="text" class="form-control" value="" inputmode="numeric">
                                                                        <div id="textOutput" class="text"></div>
                                                                    </div>
                                                                </div>
                                                                <div class="position-relative row form-group">
                                                                    <div class="form-label-horizontal col-md-3">
                                                                        <label class=""><b>Số tài khoản (*)</b></label>
                                                                    </div>
                                                                    <div class="col-md-9">
                                                                        <input id="accountNumberInput" name="accountNumber" placeholder="" type="text" class="form-control" value="">
                                                                    </div>
                                                                </div>
                                                                <div class="position-relative row form-group">
                                                                    <div class="form-label-horizontal col-md-3">
                                                                        <label class=""><b>Chủ tài khoản (*)</b></label>
                                                                    </div>
                                                                    <div class="col-md-9">
                                                                        <input id="accountHolderInput" name="accountHolder" placeholder="" type="text" class="form-control" value="">
                                                                    </div>
                                                                </div>
                                                                <div class="position-relative row form-group">
                                                                    <div class="form-label-horizontal col-md-3">
                                                                        <label class=""><b>Tên ngân hàng (*)</b></label>
                                                                    </div>
                                                                    <div class="col-md-9">
                                                                        <input id="bankNameInput" name="bankName" placeholder="VD: Tiên Phong Bank (TPB) , Vietcombank (VCB)" type="text" class="form-control" value="">
                                                                    </div>
                                                                </div>
                                                                <div class="position-relative row form-group">
                                                                    <div class="form-label-horizontal col-md-3">
                                                                        <label class=""><b>Chi nhánh </b></label>
                                                                    </div>
                                                                    <div class="col-md-9">
                                                                        <input id="bankBranchInput" name="bankBranch" placeholder="VD: Chi nhánh Phạm Hùng (Có thể bỏ trống)" type="text" class="form-control" value="">
                                                                    </div>
                                                                </div>
                                                                <div style="display: flex; justify-content: center; align-items: center;">
                                                                    <button id="withdrawal_Button" type="submit" class="mr-1 btn-white-space btn btn-success">
                                                                        <i class="fa fa-plus"></i> Gửi yêu cầu
                                                                    </button>
                                                                    <div style="margin-left: 10px;"> <!-- Add margin to create space between buttons -->
                                                                        <button id="closeRqForm" type="button" class="btn btn-secondary">Close</button>
                                                                    </div>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">


                                </div>
                            </div>
                        </div>
                    </main>
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
                    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
                    <script src="jscript/vnpay.js"></script>
                    <!--   Core JS Files   -->
                    <script src="./assets/js/core/popper.min.js"></script>
                    <script src="./assets/js/core/bootstrap.min.js"></script>
                    <script src="./assets/js/plugins/perfect-scrollbar.min.js"></script>
                    <script src="./assets/js/plugins/smooth-scrollbar.min.js"></script>
                    <script src="./assets/js/plugins/chartjs.min.js"></script>
                    <script>
                        $(document).ready(function () {
                            // Thêm sự kiện click cho nút "Gửi yêu cầu"
                            $('#showModalButton').click(function () {
                                // Loại bỏ lớp d-none từ phần modal khi nút được nhấn
                                $('#withdrawalModal').show();
                            });
                        });
                        $(document).ready(function () {
                            // Thêm sự kiện click cho nút "Gửi yêu cầu"
                            $('#closeRqForm').click(function () {
                                // Loại bỏ lớp d-none từ phần modal khi nút được nhấn
                                $('#withdrawalModal').hide();
                            });
                            $('#withdrawlButton').click(function () {
                                // Loại bỏ lớp d-none từ phần modal khi nút được nhấn
                                
                                $('#depositeForm').hide();
                                
                                $('#withdrawlRequest').show();
                            });
                            
                             $('#depositeButton').click(function () {
                                // Loại bỏ lớp d-none từ phần modal khi nút được nhấn
                                
                                $('#depositeForm').show();
                                
                                $('#withdrawlRequest').hide();
                            });
                        });
                        $(document).ready(function () {
                            $('#withdrawalForm').submit(function (e) {
                                e.preventDefault();
                                var confirmation = confirm("Xác nhận đúng thông tin TK Ngân hàng? Nếu thông tin bị sai thì bạn sẽ mất 10% khoản tiền yêu cầu rút");

                                // Nếu người dùng xác nhận
                                if (confirmation) {
                                    var formData = $(this).serialize();
                                    $.ajax({
                                        url: 'withdrawal',
                                        type: 'POST',
                                        data: formData,
                                        success: function (response) {
                                            // Xử lý phản hồi từ máy chủ nếu cần
                                            if (response === "success") {
                                                alert("Thành công!");
                                                window.location.href = 'withdrawal';
                                            } else if (response === "less than 100000") {
                                                alert("Số tiền rút không được nhỏ hơn 100,000 VND!");
                                            } else if (response === "blank") {
                                                alert("Không được để trống (Số tài khoản,Chủ tài khoản,Tên ngân hàng)!");
                                            } else if (response === "Insufficient balance") {
                                                alert("Số dư không đủ!");
                                            } else {
                                                alert("Vui lòng kiểm tra lại số tiền!");
                                            }
                                        },
                                        error: function (xhr, status, error) {

                                            console.error(xhr.responseText);
                                        }
                                    });
                                }
                            });
                        });
                        
              $(document).ready(function() {
    // Áp dụng mask cho ô input giá
    $('#amountInput').mask('000,000,000,000', {reverse: true});
});
                    </script>
                    
                    </body>

                    </html>
