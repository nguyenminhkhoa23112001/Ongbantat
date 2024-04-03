<%-- 
    Document   : withdrawalProcessing
    Created on : Mar 15, 2024, 9:22:06 PM
    Author     : tudo7
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Withdrawal Processing</title>
        <link rel="stylesheet" type="text/css" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/styles.css" rel="stylesheet" type="text/css"/> 
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">

        <style>
            #drawalProcessing tr:nth-child(odd) {
                font-weight: bold;
            }

            #drawalProcessing tr:nth-child(even) {
                font-weight: lighter;
            }

            #drawalProcessing th {
                text-align: center;
            }
            #drawalProcessing th,
            #drawalProcessing td {
                padding: 8px; /* Điều chỉnh lề nội dung trong thẻ */
            }

            #drawalProcessing th {
                text-align: center; /* Căn giữa nội dung của các thẻ th */
                font-weight: bold; /* Làm đậm cho tiêu đề */
            }

            #drawalProcessing td {
                text-align: left; /* Căn trái cho nội dung của các ô dữ liệu */
            }
            #drawalProcessing tr:hover {
                background-color: #f5f5f5; /* Màu nền khi di chuột qua */
            }
        </style>
    </head>
    <body>       
        <div class="row">
            <div class="col-md-2">
                <jsp:include page="LeftAdmin.jsp"></jsp:include>
                </div>
                <div class="col-md-9">
                    <table id="drawalProcessing" class="display" style="width:100%">
                        <thead>
                            <tr>
                                <th>Mã yêu cầu</th>
                                <th>Người yêu cầu</th>
                                <th>Trạng thái</th>
                                <th>Số tiền rút</th>
                                <th>Số tài khoản</th>
                                <th>Chủ tài khoản</th>
                                <th>Tên ngân hàng</th>
                                <th>Chi nhánh</th>
                                <th>Phản hồi</th>
                                <th>Thời gian tạo</th>
                                <th>Cập nhật</th>
                                <th>Hành động</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listWithdrawal}" var="lw">
                            <tr>
                                <th scope="row">${lw.getCode()}</th>
                                <th>${dao.getUserById(lw.getCreated_by()).getDisplay_name()}</th>
                                <th>
                                    <c:choose>
                                        <c:when test="${lw.getStatus() eq 'Hoàn thành'}">
                                            <a href="#" class="btn btn-success">${lw.getStatus()}</a>
                                        </c:when>
                                        <c:when test="${lw.getStatus() eq 'Mới tạo'}">
                                            <a href="#" class="btn btn-info">${lw.getStatus()}</a>
                                        </c:when>
                                        <c:when test="${lw.getStatus() eq 'Bị từ chối'}">
                                            <a href="#" class="btn btn-danger">${lw.getStatus()}</a>
                                        </c:when>                                                                                                                          
                                        <c:when test="${lw.getStatus() eq 'Bị lỗi'}">
                                            <a href="#" class="btn btn-warning">${lw.getStatus()}</a>
                                        </c:when>
                                        <c:when test="${lw.getStatus() eq 'Chờ chuyển khoản'}">
                                            <a href="#" class="btn btn-dark">${lw.getStatus()}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="#" class="btn btn-secondary">${lw.getStatus()}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </th>
                                <th><c:out value="${String.format('%,.0f', lw.getAmount())}" /></th>
                                <th>${lw.getAccount_number()}</th>
                                <th>${lw.getAccount_holder()}</th>
                                <th>${lw.getBankname()}</th>
                                <th>${lw.getBankbranch()}</th>
                                <th>${lw.getResponse()}</th>
                                <th>${lw.getCreated_at()}</th>
                                <th>${lw.getUpdated_at()}</th>
                                <th style="display: flex"> 
                                    <c:choose>
                                        <c:when test="${lw.getStatus() eq 'Hoàn thành'}">
                                            <button  href="#" class="btn btn-success">Hoàn thành</button>
                                        </c:when>
                                        <c:when test="${lw.getStatus() eq 'Mới tạo'}">                                        
                                            <button name="action2" value="accept" data-id="${lw.getId()}" href="#" class="action btn btn-info">Xác nhận</button>                                   
                                        </c:when>
                                        <c:when test="${lw.getStatus() eq 'Bị từ chối'}">
                                            <button  href="#" class="btn btn-danger">Đã từ chối</button>
                                        </c:when>                                                                                                                          
                                        <c:when test="${lw.getStatus() eq 'Bị lỗi'}">
                                            <button href="#" class="btn btn-danger">Sai thông tin</button>
                                        </c:when>
                                        <c:when test="${lw.getStatus() eq 'Chờ chuyển khoản'}">
                                            <button name="action2" value="error" data-id="${lw.getId()}" class="action btn btn-danger">Bị lỗi</button>
                                            <button name="action2" value="complete" data-id="${lw.getId()}" class="action btn btn-success">Đã xong</button>
                                        </c:when>
                                        <c:otherwise>

                                        </c:otherwise>
                                    </c:choose>
                                </th>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table> 
            </div>
        </div>
        <script>
            $(document).ready(function () {
                // Khởi tạo DataTable
                // var table = $('#drawalProcessing').DataTable();

                // Gọi sự kiện click của button
                $('.action').click(function (e) {
                    e.preventDefault();
                    var action = $(this).attr('value');
                    var lwid = $(this).attr('data-id');
                    $.ajax({
                        type: "POST",
                        url: "withdrawalprocessing",
                        data: {actions: action, lwids: lwid},
                        success: function (response) {
                            // Xử lý phản hồi từ server nếu cần
                            alert(response);
                            window.location.href = "withdrawalprocessing";
                        },
                        error: function (xhr, status, error) {
                            // Xử lý lỗi nếu có
                            console.error(xhr.responseText);
                        }
                    });
                });
            });

        </script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
