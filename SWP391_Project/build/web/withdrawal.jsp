<%-- 
    Document   : withdrawal
    Created on : Mar 13, 2024, 7:04:13 AM
    Author     : tudo7
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Yêu cầu rút tiền</title>
        <link rel="stylesheet" href="css/font-awesome.min.css">

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">
        <!-- Bootstrap -->
        <link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>
        <!-- Slick -->

        <!-- nouislider -->
        <link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>
        <!-- Font Awesome Icon -->
        <link rel="stylesheet" href="css/font-awesome.min.css"/>

   
        <!--Table-->
    </head>
    <body>
        <section class="ftco-section">
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
        <button id="showModalButton" class="mr-1 btn btn-success">
            <i class="fa fa-plus"></i> Gửi yêu cầu
        </button>
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
                                                        <input placeholder="Nhập vào số tiền cần rút" name="amount" id="amountInput" type="text" class="form-control">
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
                    <button type="button" class="btn btn-secondary" style="left: 2%"><a href="home"> Back to home</a></button>
                    <button id="closeRqForm" type="button" class="btn btn-secondary">Close</button>
                </div>
            </div>
        </div>
           <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
        <script>
          $(document).ready(function () {
    $('#amountInput').mask('000,000,000,000', {reverse: true});
});
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
                                }
                            },
                            error: function (xhr, status, error) {

                                console.error(xhr.responseText);
                            }
                        });
                    }
                });
            });

        </script>

    </body>


</html>
