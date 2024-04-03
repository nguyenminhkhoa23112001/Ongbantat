<%-- 
    Document   : Historytransaction
    Created on : Mar 7, 2024, 4:24:30 PM
    Author     : My pc
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Transaction</title>
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">
        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>

        <!-- Slick -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">
        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>

        <!-- Slick -->
        <link type="text/css" rel="stylesheet" href="css/slick.css"/>
        <link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>

        <!-- nouislider -->
        <link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>

        <!-- Font Awesome Icon -->
        <link rel="stylesheet" href="css/font-awesome.min.css"/>
        <!-- Custom stlylesheet -->

        <link type="text/css" rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/myorder.css"/>
        <style>


            /* Modal */
            /* Modal */
            .modal3 {
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0, 0, 0, 0.6); /* Màu nền mờ */
                display: none; /* Ẩn modal ban đầu */
            }

            /* Modal Content */
            .modal-content3 {
                background-color: #fefefe;
                margin: 10% auto; /* Độ cao từ trên xuống modal */
                padding: 20px;
                border: 1px solid #888;
                width: 80%; /* Độ rộng của modal */
                border-radius: 10px; /* Bo tròn các góc */
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Hiệu ứng đổ bóng */
                display: flex; /* Sử dụng flexbox để sắp xếp các cột hàng dọc */
                flex-direction: column; /* Sắp xếp các phần tử thành cột hàng dọc */
            }

            /* Close Button */
            .close {
                color: #aaa;
                align-self: flex-end; /* Đẩy nút đóng về phía cuối cùng của modal */
                font-size: 28px;
                font-weight: bold;
                margin-bottom: 10px; /* Khoảng cách giữa nút đóng và các phần tử khác */
            }

            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }

            /* Các phần tử trong modal */
            .modal-content3 label,
            .modal-content3 input,
            .modal-content3 textarea {
                margin-bottom: 10px; /* Khoảng cách giữa các phần tử */
            }

            .modal-content3 input,
            .modal-content3 textarea {
                width: calc(100% - 40px); /* Độ rộng của input và textarea */
                padding: 10px; /* Khoảng cách giữa nội dung và viền */
                border-radius: 5px; /* Bo tròn các góc */
                border: 1px solid #ccc; /* Viền */
            }

        </style>  
        <style>


            html,
            dt-length{
                margin-top: 15px;
            }
            .form-control-sm{
                width: 50%;
                margin-bottom:15px;
            }
            .row {
                margin-right: 0px;
                margin-left: 0px;

            }
            .intro {
                padding-left: 15px;
            }

            table td,
            table th {
                text-overflow: ellipsis;
                white-space: nowrap;
                overflow: hidden;
            }

            thead th {
                color: #fff;
            }

            .card {
                border-radius: .5rem;
            }

            .table-scroll {
                border-radius: .5rem;
            }

            .table-scroll table thead th {
                font-size: 1.25rem;
            }
            thead {
                top: 0;
                position: sticky;
            }
        </style>
    </head>
    <body>      
        <section class="intro">
            <div class="bg-image h-100" style="background-color: #f5f7fa;">
                <div class="mask d-flex align-items-center h-100">
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-body p-0">
                                        <div class="table-responsive table-scroll" data-mdb-perfect-scrollbar="true" style="position: relative; padding-left: 15px; height: 650px;width: 105%; ">
                                            <table id="example" class="table table-striped">
                                                <thead style="background-color: #002d72;">
                                                    <tr>
                                                        <th  scope="col">Mã Giao dịch</th>
                                                        <th  scope="col">Số Tiền</th>
                                                        <th  scope="col">Loại Giao dịch</th>                        
                                                        <th  scope="col">Trạng Thái</th>
                                                        <th  scope="col">Ghi Chú</th>
                                                        <th  scope="col">Người tạo giao dịch</th>
                                                        <th  scope="col">Thời gian Tạo</th>
                                                        <th  scope="col">Cập nhật cuối</th>
                                                        <th  scope="col">Hành Động</th>
                                                    </tr>
                                                </thead>
                                                <tbody>

                                                    <c:forEach items="${historytransaction}" var="o">
                                                        <tr style="text-align: center;">
                                                            <td>${o.getID()}</td>
                                                            <td>${o.getMoney_Transaction()}</td>
                                                            <td>${o.getTransaction_Type()}</td>
                                                            <td>${o.isStatus()? "Đã xử lý" : "Chưa Xử Lý"}</td>
                                                            <td>${o.getNote()}</td>                       
                                                            <td>${dao.getUserById(o.getCreated_by()).getDisplay_name()}</td>
                                                            <td>${o.getCreate_at()}</td>
                                                            <td>${o.getUpdate_at()}</td>
                                                            <td> 
                                                                <a class="historytransactioninfo" data-action="view"  href="#" data-product-id="${o.getID()}">
                                                                    <i  class="bi bi-exclamation-circle-fill"></i>
                                                                </a>                                          
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <div class="overlay" id="overlay"></div>
        <div style="height: 700px;overflow: auto;max-height: 800px" class="modal" id="modal">
            <div class="modal-content3">                      
                <div class="container-complain">
                    <h2>Transaction Information</h2>                   
                    <label for="TransactionCode">Mã giao dịch</label>
                    <input type="text" id="TransactionCode1" value="" readonly><br>

                    <label for="Money">Số tiền</label>
                    <input type="text" id="Money1" value="" readonly><br>

                    <label for="Transaction_Type">Loại Giao dịch</label>
                    <input type="text" id="Transaction_Type1" value="" readonly><br>

                    <label for="Status">Trạng Thái</label>
                    <input type="text" id="Status1" value="" readonly><br>

                    <label for="Note">Ghi Chú:</label>
                    <input type="text" id="Note1" value="" readonly><br>

                    <label for="Created_by">Người tạo giao dịch:</label>
                    <input type="text" id="Created_by1" value="" readonly><br>

                    <label for="Create_at">Thời gian Tạo:</label>
                    <input type="text" id="Create_at1" value="" readonly><br>
                    <label for="Update_at">Cập nhật cuối:</label>
                    <input type="text" id="Update_at1" value=""  readonly><br>
                </div>
                <div style="margin-top: auto; text-align: center;">
                    <button onclick="hideProductModal()">Close</button>
                </div>
            </div>              
        </div>
        <script src="https://code.jquery.com/jquery-3.7.1.js"></script> 
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>

        <script src="https://cdn.datatables.net/2.0.2/js/dataTables.js"></script>  
        <script src="https://cdn.datatables.net/2.0.2/js/dataTables.bootstrap5.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css"></script>
        <script src="https://cdn.datatables.net/2.0.2/css/dataTables.bootstrap5.css"></script>





        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
                        $(document).ready(function () {
                            // Sử dụng event delegation để gắn sự kiện cho các phần tử con của bảng
                            $('#example').on('click', '.historytransactioninfo', function (event) {
                                event.preventDefault();
                                var historyid = $(this).data('product-id');
                                var action = $(this).data('action');
                                $.ajax({
                                    type: 'post',
                                    url: 'transaction',
                                    data: {hid: historyid, action: action},
                                    success: function (response) {
                                        document.getElementById("overlay").style.display = "block";
                                        document.getElementById("modal").style.display = "block";
                                        var responseData = response.split(";");
                                        document.getElementById("TransactionCode1").value = responseData[0];
                                        document.getElementById("Money1").value = responseData[1];
                                        document.getElementById("Transaction_Type1").value = responseData[2];
                                        document.getElementById("Status1").value = responseData[3];
                                        document.getElementById("Note1").value = responseData[4];
                                        document.getElementById("Created_by1").value = responseData[5];
                                        document.getElementById("Create_at1").value = responseData[6];
                                        document.getElementById("Update_at1").value = responseData[7];
                                    },
                                    error: function (xhr, status, error) {
                                        console.error(xhr.responseText);
                                    }
                                });
                            });
                        });
                        function hideProductModal() {
                            event.preventDefault();
                            document.getElementById("overlay").style.display = "none";
                            document.getElementById("modal").style.display = "none";
                            document.getElementById("modal2").style.display = "none";
                        }
                        ;

        </script>
        <script>
            new DataTable('#example');
        </script>
    </body>
</html>
