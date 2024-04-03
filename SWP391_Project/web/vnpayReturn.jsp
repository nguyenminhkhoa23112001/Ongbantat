<%-- 
    Document   : vnpayReturn
    Created on : Mar 15, 2024, 10:30:54 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="css/https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"/>

        <link rel="stylesheet" href="css/https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"/>
        <link rel="stylesheet" href="css/Deposite.css"/>
        <style>
            #transaction-table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            #transaction-table th, #transaction-table td {
                padding: 8px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }
            #transaction-table th {
                background-color: #f2f2f2;
            }
            /* Style for alternating rows */
            #transaction-table tr:nth-child(even) {
                background-color: #f2f2f2;
            }
            /* Define styles for td1 and td2 */
            .td1 {
                font-weight: bold;
            }
            .td2 {
                color: #555;
            }
            /* Additional styling for the status */
            #status {
                font-weight: bold;
                color: green; /* You can change color based on status */
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="card p-3" id="form2">
                        <table id="transaction-table">
                            <tr>
                                <td class="td1">1. Mã giao dịch thanh toán:</td>
                                <td class="td2">${transactionCode}</td>
                            </tr>
                            <tr>
                                <td class="td1">2 Ngân hàng thanh toán:</td>
                                <td class="td2">${bank}</td>
                            </tr>
                            <tr>
                                <td class="td1">3. Số tiền:</td>
                                <td class="td2">${money}</td>
                            </tr>
                            <tr>
                                <td class="td1">4. Mô tả giao dịch:</td>
                                <td class="td2">${description}</td>
                            </tr>

                            <tr>
                                <td class="td1">5. Thời gian thanh toán:</td>
                                <td class="td2">${time}</td>
                            </tr>
                            <tr>
                                <td class="td1">6. Trạng thái giao dịch:</td>
                                <td class="td2" id="status">${status}</td>
                            </tr>
                        </table>
                        <div class="d-flex justify-content-center mt-3">
                            <button type="button" class="btn btn-primary mx-2" style="background-color: #008000; "><a href="home" style="text-decoration: none; color: white;">Trở về trang chủ</a></button>
                            <button type="button" class="btn btn-primary mx-2" style="background-color: blue;"><a href="withdrawal" style="text-decoration: none;  color: white;">Tiếp tục nạp tiền</a></button>
                        </div>
                    </div>

                </div>

            </div>
        </div>
    </body>
</html>
