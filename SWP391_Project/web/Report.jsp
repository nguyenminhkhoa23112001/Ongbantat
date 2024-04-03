

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Quản Lý report</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ------>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/styles.css" rel="stylesheet" type="text/css"/> 

        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">


        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/bootstrap.min.css">
        <!-- Material Design Bootstrap -->
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb-pro.min.css">
        <!-- Material Design Bootstrap Ecommerce -->
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb.ecommerce.min.css">
        <!-- Your custom styles (optional) -->
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script> 
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ------>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/styles.css" rel="stylesheet" type="text/css"/> 
        <link href="css/manager.css" rel="stylesheet" type="text/css"/>

        <!--           <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round"> -->
        <!--         <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"> -->
        <!--       <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
        <!--     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">  -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <style>
            body {
                margin: 0;
                padding: 0;
            }
        </style>
        <link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css"><link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&amp;display=swap"><link rel="stylesheet" type="text/css" href="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/css/mdb5/3.8.1/compiled.min.css"><link rel="stylesheet" type="text/css" href="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/css/mdb-plugins-gathered.min.css"><style>
            body {
                background-color: #fbfbfb;
            }
            @media (min-width: 991.98px) {
                main {
                    padding-left: 240px;
                }
            }

            /* Sidebar */
            .sidebar {
                position: fixed;
                top: 0;
                bottom: 0;
                left: 0;
                padding: 58px 0 0; /* Height of navbar */
                box-shadow: 0 2px 5px 0 rgb(0 0 0 / 5%), 0 2px 10px 0 rgb(0 0 0 / 5%);
                width: 240px;
                z-index: 600;
            }

            @media (max-width: 991.98px) {
                .sidebar {
                    width: 100%;
                }
            }
            .sidebar .active {
                border-radius: 5px;
                box-shadow: 0 2px 5px 0 rgb(0 0 0 / 16%), 0 2px 10px 0 rgb(0 0 0 / 12%);
            }



            .sidebar-sticky {
                position: relative;
                top: 0;
                height: calc(100vh - 48px);
                padding-top: 0.5rem;
                overflow-x: hidden;
                overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
            }

            /* Modal Content */
            .modal-content3 {
                background-color: #fefefe;
                margin: 5% auto; /* Độ cao từ trên xuống modal */
                padding: 20px;
                border: 1px solid #888;
                width: 90%; /* Độ rộng của modal */
                border-radius: 10px; /* Bo tròn các góc */
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Hiệu ứng đổ bóng */
                display: flex; /* Sử dụng flexbox để sắp xếp các cột hàng dọc */
                flex-direction: column; /* Sắp xếp các phần tử thành cột hàng dọc */
            }
            .form-row {
                display: flex;
                align-items: baseline; /* Đảm bảo các input thẳng hàng theo baseline của label */
                margin-bottom: 10px; /* Khoảng cách giữa các cặp label và input */
            }

            .form-row label {
                width: 30%; /* Độ rộng của label */
                margin-right: 10px; /* Khoảng cách giữa label và input */
            }

            .form-row input {
                flex: 1; /* Input mở rộng để lấp đầy phần còn lại của container */
            }
            .modal-content3 label,
            .modal-content3 input,
            .modal-content3 textarea {
                margin-bottom: 10px; /* Khoảng cách giữa các phần tử */
            }

            .modal-content3 input,
            .modal-content3 textarea {

                width: calc(50% - 40px); /* Độ rộng của input và textarea */
                padding: 10px; /* Khoảng cách giữa nội dung và viền */
                border-radius: 5px; /* Bo tròn các góc */
                border: 1px solid #ccc; /* Viền */
            }

            .modal-content3 textarea {
                height: 150px; /* Độ cao của textarea */
            }

        </style>
    </head>
    <body>

        <!--Main Navigation-->
        <header>
            <jsp:include page="LeftAdmin.jsp"></jsp:include>
            </header>
            <!--Main Navigation-->

            <!--Main layout-->
            <!-- Modal -->




            <!-- QUẢN LÝ  -->
            <main>



                <div class="container pt-4">

                    <!--Section: Quan Ly -->
                    <section class="mb-4">
                        <div class="card">
                            <div class="card-header py-3 row">
                                <div class="col-sm-5">
                                    <h5 class="mb-0 text-left" id="">
                                        <strong>QUẢN LÝ KHIẾU NẠI YÊU CẦU XỬ LÝ</strong>
                                    </h5>
                                </div>

                            </div>

                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-hover text-nowrap">
                                        <thead>
                                            <tr>
                                                <th scope="col">Phân loại</th>
                                                <th scope="col">Trạng thái</th>
                                                <th scope="col">Mô tả</th>
                                                <th scope="col">Tạo bởi</th>                                                
                                                <th scope="col">Thời gian</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                        <c:forEach items="${combo3}" var="c" varStatus="loop">
                                            <c:if test="${c.getReport().getType_report() == 7}">
                                                <tr>
                                                    <td>${c.getReport().getType_report()}</td>
                                                    <td>${c.getReport().isStatus()}</td>
                                                    <td>${c.getReport().getDescription()}</td>
                                                    <td>${c.getReport().getCreate_by()}</td>
                                                    <td>${c.getReport().getCreate_At()}</td>

                                                    <td>

                                                        <c:choose>
                                                            <c:when test="${c.getReport().isStatus() == true}">
                                                                <a class="btn btn-success edit-btn" data-toggle="modal" >
                                                                    Đã xử lý
                                                                </a>
                                                            </c:when>
                                                            <c:when test="${c.getReport().isStatus() != true}">
                                                                <a class= "btn btn-danger edit-btn" data-toggle="modal" data-target="#confirmationModal_${loop.index}">
                                                                    Đang chờ
                                                                </a>
                                                            </c:when>
                                                        </c:choose>

                                                    </td>
                                                    <td>
                                                        <span class="info-icon" data-toggle="modal" data-target="#detailModal_${loop.index}">&#8505;</span>
                                                    </td>
                                                </tr>

                                            <div class="modal fade" id="confirmationModal_${loop.index}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalLabel">Quản lý khiếu nại</h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>

                                                        <div class="modal-body">
                                                            <p>Trong đơn hàng khiếu nại, bên đúng là:</p>                                                            
                                                        </div>

                                                        <div class="form-check">
                                                            <input type="radio" class="form-check-input" id="sellerRadio_${loop.index}" name="selectAction" value="${c.getReport().getId()},${c.getOrder().getCreate_by()},${c.getOrder().getTotal_received_amount()},${c.getReport().getCreate_by()}">
                                                            <label class="form-check-label" for="sellerRadio_${loop.index}">Người bán</label>
                                                        </div>
                                                        <div class="form-check">
                                                            <input type="radio" class="form-check-input" id="buyerRadio_${loop.index}" name="selectAction" value="${c.getReport().getId()},${c.getOrder().getBuyer_id()},${c.getOrder().getTotal_paid_amount()},${c.getReport().getCreate_by()}">
                                                            <label class="form-check-label" for="buyerRadio_${loop.index}">Người mua</label>
                                                        </div>
                                                            
                                                        

                                                        <div class="modal-footer">
                                                            <div class="modal-body">
                                                                <p>Xác nhận xử lý đơn khiếu nại:</p>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <a href="#" class="btn btn-secondary" data-dismiss="modal">Xác nhận</a>
                                                            </div>
                                                        </div>


                                                    </div>
                                                </div>
                                            </div>

                                            <!-- Modal for displaying details -->
                                            <div class="modal fade" id="detailModal_${loop.index}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div class="modal-dialog modal-lg" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalLabel">Thông tin chi tiết</h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-content3">


                                                            <!-- Add your input fields for Name, Price, Image, Content here -->
                                                            <div class="form-row">
                                                                <label for="orderCode">Mã giao dịch:</label>
                                                                <input type="text" id="orderCode" value="${c.getOrder().getCode()}" readonly><br>
                                                            </div>

                                                            <div class="form-row">
                                                                <label for="productName">Tên sản phẩm:</label>
                                                                <input type="text" id="productName" value="${c.getProduct().getName()}" readonly><br>
                                                            </div>
                                                            <div class="form-row">
                                                                <label for="price">Giá:</label>
                                                                <input type="text" id="price" value="${c.getProduct().getPrice()}" readonly><br>
                                                            </div>

                                                            <label for="productImage">Hình ảnh:</label>
                                                            <img style="max-width: 150px; max-height: 150px" id="img1" src="" >
                                                            <img style="max-width: 150px; max-height: 150px" id="img2" src="" >
                                                            <img style="max-width: 150px; max-height: 150px" id="img3" src="" >
                                                            <img style="max-width: 150px; max-height: 150px" id="img4" src="" ><br>
                                                            <div class="form-row">
                                                                <label for="description">Mô tả:</label>
                                                                <input id="description" value="${c.getProduct().getDescription()}" readonly></input><br>
                                                            </div>
                                                            <div class="form-row">
                                                                <label for="hiddenContent">Nội dung ẩn:</label>
                                                                <input type="text" id="hiddenContent" value="${c.getProduct().getHidden_content()}" readonly><br>
                                                            </div>
                                                            <div class="form-row">
                                                                <label for="party">Người mua:</label>                          
                                                                <input type="text" id="partyBuyer" name="party" value="${c.getOrder().getBuyer_id()}" readonly><br>
                                                            </div>
                                                            <div class="form-row">
                                                                <label for="party">Người bán:</label>                          
                                                                <input type="text" id="partySeller" name="party" value="${c.getOrder().getCreate_by()}" readonly><br>
                                                            </div>
                                                            <div class="form-row">
                                                                <label for="contactMethod">Thông tin liên hệ:</label><br>
                                                                <input type="text" id="contactMethod" value="${c.getProduct().getContact_Method()}"  readonly><br>
                                                            </div>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- End of Modal -->
                                        </c:if>


                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </section>
                <!--Section: Quan Ly -->
            </div>


        </main>





        <script src="js/manager.js" type="text/javascript"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!--Main layout-->
        <!-- SCRIPTS -->
        <!-- JQuery -->
        <script src="https://mdbootstrap.com/previews/ecommerce-demo/js/jquery-3.4.1.min.js"></script>
        <!-- Bootstrap tooltips -->
        <script type="text/javascript" src="https://mdbootstrap.com/previews/ecommerce-demo/js/popper.min.js"></script>
        <!-- Bootstrap core JavaScript -->
        <script type="text/javascript" src="https://mdbootstrap.com/previews/ecommerce-demo/js/bootstrap.js"></script>
        <!-- MDB core JavaScript -->
        <script type="text/javascript" src="https://mdbootstrap.com/previews/ecommerce-demo/js/mdb.min.js"></script>
        <!-- MDB Ecommerce JavaScript -->
        <script type="text/javascript" src="https://mdbootstrap.com/previews/ecommerce-demo/js/mdb.ecommerce.min.js"></script>
        <!-- MDB -->
        <script type="text/javascript" src="js/mdb.min.js"></script>
        <!-- Custom scripts -->
        <script type="text/javascript" src="js/script.js"></script>
        <script src="https://mdbootstrap.com/api/snippets/static/download/MDB5-Free_3.8.1/js/mdb.min.js"></script><script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script>


        <script type="text/javascript" src="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/js/plugins/mdb-plugins-gathered.min.js"></script>
        <!-- MDB -->
        <script type="text/javascript" src="js/mdb.min.js"></script>
        <!-- Custom scripts -->
        <script type="text/javascript" src="js/script.js"></script>  
        <script>
            $(document).ready(function () {
                // Lắng nghe sự kiện click của nút "Xác nhận"
                $('.btn-secondary').click(function (e) {
                    // Ngăn chặn hành vi mặc định của form (tránh load lại trang)
                    e.preventDefault();
                    // Kiểm tra xem có radio button nào được chọn không
                    var selectedValue = $('input[name=selectAction]:checked').val();
                    if (!selectedValue) {
                        // Hiển thị thông báo nếu không có radio button nào được chọn
                        alert("Yêu cầu chọn bên đúng trong khiếu nại");
                        return;
                    }

                    // Sử dụng AJAX để gửi giá trị của radio button đến servlet
                    $.ajax({
                        url: 'EditReportStatus',
                        type: 'GET',
                        data: {selectedValue: selectedValue},
                        success: function (response) {
                            alert(response);
                            window.location.href = "ReportServlet";
                        },
                        error: function (xhr, status, error) {
                            // Xử lý lỗi nếu có
                            console.error(responseText);
                        }
                    });
                });
            });

        </script>
    </body>
</html>
