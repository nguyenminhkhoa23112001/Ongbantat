/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

/* global l1, files, Swal */

$(document).ready(function () {
    // Ẩn form khi trang được tải
    document.getElementById("ProductDisplay").style.display = 'block';
    document.getElementById("Filter").style.display = 'block';
    if ($.fn.DataTable.isDataTable('#orderBuy')) {
        // Nếu đã tồn tại, hủy DataTable hiện tại
        $('#orderBuy').DataTable().destroy();
    }
    if ($.fn.DataTable.isDataTable('#orderBuy-complete')) {
        // Nếu đã tồn tại, hủy DataTable hiện tại
        $('#orderBuy-complete').DataTable().destroy();
    }
    $('#ProductDisplay').DataTable();



    // Xử lý sự kiện khi nhấn nút "Add Product"
    $("#addProductButton").click(function () {
        document.getElementById("addProductForm").style.display = 'block';
        document.getElementById("ProductDisplay").style.display = 'none';
        document.getElementById("ProductCompleteDisplay").style.display = 'none';
        document.getElementById("ProductProcessingDisplay").style.display = 'none';
        document.getElementById("Filter").style.display = 'none';
        document.getElementById("orderBuy").style.display = 'none';
        document.getElementById("orderBuy-complete").style.display = 'none';
        if ($.fn.DataTable.isDataTable('#orderBuy')) {
            // Nếu đã tồn tại, hủy DataTable hiện tại
            $('#orderBuy').DataTable().destroy();
        }
        if ($.fn.DataTable.isDataTable('#orderBuy-complete')) {
            // Nếu đã tồn tại, hủy DataTable hiện tại
            $('#orderBuy-complete').DataTable().destroy();
        }
        $('.dataTable').each(function () {
            $(this).DataTable().destroy();
        });

    });
    $("#allProductButton").click(function () {
        document.getElementById("ProductDisplay").style.display = 'block';
        document.getElementById("Filter").style.display = 'block';
        document.getElementById("ProductCompleteDisplay").style.display = 'none';
        document.getElementById("ProductProcessingDisplay").style.display = 'none';
        document.getElementById("addProductForm").style.display = 'none';
        document.getElementById("orderBuy").style.display = 'none';
        document.getElementById("orderBuy-complete").style.display = 'none';
        if ($.fn.DataTable.isDataTable('#orderBuy')) {
            // Nếu đã tồn tại, hủy DataTable hiện tại
            $('#orderBuy').DataTable().destroy();
        }
        if ($.fn.DataTable.isDataTable('#orderBuy-complete')) {
            // Nếu đã tồn tại, hủy DataTable hiện tại
            $('#orderBuy-complete').DataTable().destroy();
        }
        $('.dataTable').each(function () {
            $(this).DataTable().destroy();
        });
        $('#ProductDisplay').DataTable();

    });
    $("#completedorder").click(function () {

        document.getElementById("Filter").style.display = 'block';
        document.getElementById("ProductCompleteDisplay").style.display = 'block';
        document.getElementById("ProductDisplay").style.display = 'none';
        document.getElementById("addProductForm").style.display = 'none';
        document.getElementById("orderBuy").style.display = 'none';
        document.getElementById("orderBuy-complete").style.display = 'none';
        document.getElementById("ProductProcessingDisplay").style.display = 'none';
        if ($.fn.DataTable.isDataTable('#orderBuy')) {
            // Nếu đã tồn tại, hủy DataTable hiện tại
            $('#orderBuy').DataTable().destroy();
        }
        if ($.fn.DataTable.isDataTable('#orderBuy-complete')) {
            // Nếu đã tồn tại, hủy DataTable hiện tại
            $('#orderBuy-complete').DataTable().destroy();
        }
        $('.dataTable').each(function () {
            $(this).DataTable().destroy();
        });
        $('#ProductCompleteDisplay').DataTable();

    });
    $("#processingorder").click(function () {
        document.getElementById("Filter").style.display = 'block';
        document.getElementById("ProductProcessingDisplay").style.display = 'block';
        document.getElementById("ProductDisplay").style.display = 'none';
        document.getElementById("ProductCompleteDisplay").style.display = 'none';
        document.getElementById("addProductForm").style.display = 'none';
        document.getElementById("orderBuy").style.display = 'none';
        document.getElementById("orderBuy-complete").style.display = 'none';
        if ($.fn.DataTable.isDataTable('#orderBuy')) {
            // Nếu đã tồn tại, hủy DataTable hiện tại
            $('#orderBuy').DataTable().destroy();
        }
        if ($.fn.DataTable.isDataTable('#orderBuy-complete')) {
            // Nếu đã tồn tại, hủy DataTable hiện tại
            $('#orderBuy-complete').DataTable().destroy();
        }
        $('.dataTable').each(function () {
            $(this).DataTable().destroy();
        });
        $('#ProductProcessingDisplay').DataTable();
    });
});
function toggleOptions(productId) {
    const options = document.getElementById(productId).querySelector('.options');
    options.style.display = options.style.display === 'block' ? 'none' : 'block';
}

$(document).ready(function () {
    $('#addForm').submit(function (e) {
        e.preventDefault(); // Ngăn chặn hành động mặc định của form

        // Hiển thị hộp thoại xác nhận của SweetAlert2
        Swal.fire({
            title: "Are you sure?",
            text: "Posting a product will cost 500 VND for the posting fee !",
            icon: "question",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes, add it!",
            width: 600 // Độ rộng của hộp thoại


        }).then((result) => {
            // Nếu người dùng xác nhận
            if (result.isConfirmed) {
                var formData = new FormData(); // Khởi tạo đối tượng FormData

                // Lặp qua từng tệp trong trường input có id là "images"
                $.each($('#images')[0].files, function (i, file) {
                    formData.append('images[]', file); // Thêm từng tệp vào FormData
                });

                // Lặp qua các trường input khác trong biểu mẫu và thêm chúng vào FormData
                $('#addForm').find('input, select, textarea').each(function () {
                    var input = $(this);
                    var name = input.attr('name');
                    var value;
                    if (input.is(':radio')) {
                        value = $('input[name="' + name + '"]:checked').val(); // Lấy giá trị của radio button đã được chọn
                    } else if (input.is('select')) {
                        value = input.children("option:selected").val(); // Lấy giá trị của option được chọn trong thẻ select
                    } else {
                        value = input.val(); // Lấy giá trị của các phần tử input và textarea
                    }
                    formData.append(name, value);
                });

                $.ajax({
                    url: 'addProduct', // Đường dẫn tới file xử lý form
                    type: 'POST', // Phương thức POST
                    data: formData, // Dữ liệu được thu thập từ form  
                    contentType: false,
                    processData: false,
                    success: function (response) {
                        // Xử lý phản hồi từ máy chủ nếu cần
                        if (response === "success") {
                            Swal.fire({
                                title: "Success!",
                                text: "Sản phẩm đã thêm thành công !",
                                icon: "success"
                            }).then(() => {
                                window.location.href = 'manageMyOrder';
                            });
                        } else if (response === "Insufficient_balance") {
                            Swal.fire({
                                title: "Error!",
                                text: "Số dư không đủ !",
                                icon: "error"
                            }).then(() => {
                                window.location.href = 'manageMyOrder';
                            })
                                    ;
                        } else if (response === "price") {
                            Swal.fire({
                                title: "Error!",
                                text: "Vui lòng nhập lại giá tiền !",
                                icon: "error"
                            });

                        } else if (response === "null") {
                            alert("Không được để trống");
                        }
                    },
                    error: function (xhr, status, error) {
                        // Xử lý lỗi nếu có
                        console.error(xhr.responseText);
                    }
                });
            }
        });
    });
});


$(document).ready(function () {
    $('.productInfo').click(function (event) {
        event.preventDefault();
        const popupContainer = document.getElementById('popupContainer');
        const tablePopup = document.getElementById('notificationTable');
        popupContainer.style.display = 'none';
        tablePopup.style.display = 'none';
        document.getElementById("overlay").style.display = "block";
        document.getElementById("modal").style.display = "block";

        var productId = $(this).data('product-id');
        var action = $(this).data('action');
        $.ajax({
            type: 'POST',
            url: 'manageMyOrder',
            data: {pid: productId, action: action},
            success: function (response) {
                var responseData = response.split(";");
                if(responseData[14] === "Sẵn sàng giao dịch") {
                    document.getElementById("requestAdmin").style.display = 'none';
                    document.getElementById("verifyOrder5").style.display = 'none';
                    document.getElementById("verifyOrder6").style.display = 'none';
                } else if(responseData[14] === "Người mua khiếu nại đơn hàng"){
                    document.getElementById("requestAdmin").style.display = 'none';
                    document.getElementById("verifyOrder5").style.display = 'block';
                    document.getElementById("verifyOrder6").style.display = 'block';
                } else if(responseData[14] === "Chờ người mua xác nhận"){
                    document.getElementById("requestAdmin").style.display = 'block';
                    document.getElementById("verifyOrder5").style.display = 'none';
                    document.getElementById("verifyOrder6").style.display = 'none';
                } else {
                    document.getElementById("requestAdmin").style.display = 'none';
                    document.getElementById("verifyOrder5").style.display = 'none';
                    document.getElementById("verifyOrder6").style.display = 'none';
                }
                document.getElementById("orderCode").value = responseData[0];
                document.getElementById("productName").value = responseData[1];
                document.getElementById("price").value = responseData[2];
                document.getElementById("intermediaryFee").value = responseData[3];
                document.getElementById("party").value = responseData[4];
                document.getElementById("receivedAmount").value = responseData[5];
                document.getElementById("paidAmount").value = responseData[6];
                document.getElementById("img1").src = responseData[7];
                document.getElementById("img2").src = responseData[8];
                document.getElementById("img3").src = responseData[9];
                document.getElementById("img4").src = responseData[10];
                document.getElementById("description").value = responseData[11];
                document.getElementById("hiddenContent_info").value = responseData[12];
                document.getElementById("contactMethod").value = responseData[13];
                document.getElementById("status").value = responseData[14];
                document.getElementById("buyer_info").value = responseData[15];
                document.getElementById("create_at").value = responseData[16];
                document.getElementById("update_at").value = responseData[17];
                
            },
            error: function (xhr, status, error) {
                console.error(xhr.responseText);
            }
        });
    });

    $('.updateproduct').click(function (event) {
        event.preventDefault();
        var productId = $(this).data('product-id');
        var action = $(this).data('action');
        $.ajax({
            type: 'POST',
            url: 'manageMyOrder',
            data: {pid: productId, action: action},
            success: function (response) {
                const popupContainer = document.getElementById('popupContainer');
                const tablePopup = document.getElementById('notificationTable');
                popupContainer.style.display = 'none';
                tablePopup.style.display = 'none';
                document.getElementById("overlay").style.display = "block";
                document.getElementById("modal2").style.display = "block";

                // Code xử lý dữ liệu phản hồi cho modal2
                var responseData = response.split(";");
                document.getElementById("orderCode_ud").value = responseData[0];
                document.getElementById("productName_ud").value = responseData[1];
                document.getElementById("price_ud").value = responseData[2];
                document.getElementById("intermediaryFee_ud").value = responseData[3];
                if (responseData[4] === 'Bên Bán') {
                    document.getElementById("partySeller_ud").checked = true;
                } else if (responseData[4] === 'Bên Mua') {
                    document.getElementById("partyBuyer_ud").checked = true;
                }
                document.getElementById("receivedAmount_ud").value = responseData[5];
                document.getElementById("paidAmount_ud").value = responseData[6];
                document.getElementById("img1_ud").src = responseData[7];
                document.getElementById("img2_ud").src = responseData[8];
                document.getElementById("img3_ud").src = responseData[9];
                document.getElementById("img4_ud").src = responseData[10];
                document.getElementById("description_ud").value = responseData[11];
                document.getElementById("hiddenContent_ud").value = responseData[12];
                document.getElementById("contactMethod_ud").value = responseData[13];
                document.getElementById("status_ud").value = responseData[14];
                document.getElementById("buyer_ud").value = responseData[15];
                document.getElementById("create_at_ud").value = responseData[16];
                document.getElementById("update_at_ud").value = responseData[17];
            }
        });
    });
});
$(document).ready(function () {
    $('#updateButton').click(function () {
        // Thu thập dữ liệu từ form
        var formData = {

            code: $('#orderCode_ud').val(),
            productName: $('#productName_ud').val(),
            price: $('#price_ud').val(),
            party: $('input[name=party_ud]:checked').val(),
            description: $('#description_ud').val(),
            hiddenContent: $('#hiddenContent_ud').val(),
            contactMethod: $('#contactMethod_ud').val()
        };

        // Gửi dữ liệu đến servlet bằng AJAX
        $.ajax({
            type: 'POST',
            url: 'updateOrder',
            data: formData,
            success: function (response) {
                if (response === 'success') {
                    alert("success");
                    window.location.href = 'manageMyOrder';
                } else {
                    alert(response);
                }
            },
            error: function (xhr, status, error) {
                // Xử lý lỗi (nếu có)
                alert("loi");
                console.error(xhr.responseText);
            }
        });
    });
});
const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
        confirmButton: "btn btn-success",
        cancelButton: "btn btn-danger"
    },
    buttonsStyling: false
});

$('.deleteProductButton').click(function () {
    // Thu thập dữ liệu từ form
    var orderID = $(this).data('product-id');

    // Hiển thị hộp thoại xác nhận
    swalWithBootstrapButtons.fire({
        title: "Are you sure?",
        text: "You won't be able to revert this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Yes, delete it!",
        cancelButtonText: "No, cancel!",
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            // Nếu người dùng chấp nhận xóa, thực hiện AJAX
            $.ajax({
                type: 'POST',
                url: 'deleteProduct',
                data: {oid: orderID},
                success: function (response) {
                    if (response === "success") {
                        swalWithBootstrapButtons.fire({
                            title: "Deleted!",
                            text: "Your file has been deleted.",
                            icon: "success"
                        }).then(() => {
                            window.location.href = 'manageMyOrder';
                        });
                    } else {
                        swalWithBootstrapButtons.fire({
                            title: "Fail!",
                            text: "Can't delete product!!!",
                            icon: "error"
                        });
                    }
                },
                error: function (xhr, status, error) {
                    // Xử lý lỗi (nếu có)
                    swalWithBootstrapButtons.fire({
                        title: "Error!",
                        text: "An error occurred while deleting the product.",
                        icon: "error"
                    });
                }
            });
        } else if (result.dismiss === Swal.DismissReason.cancel) {
            // Nếu người dùng hủy bỏ xóa
            swalWithBootstrapButtons.fire({
                title: "Cancelled",
                text: "Your imaginary file is safe :)",
                icon: "error"
            });
        }
    });
});
function hideProductModal() {
    event.preventDefault();
    document.getElementById("overlay").style.display = "none";
    document.getElementById("modal").style.display = "none";
    document.getElementById("modal2").style.display = "none";
}

$(document).ready(function () {
    $("#order-checking").click(function () {
        $('.dataTable').each(function () {
            $(this).DataTable().destroy();
        });
        if ($.fn.DataTable.isDataTable('#orderBuy-complete')) {
            // Nếu đã tồn tại, hủy DataTable hiện tại
            $('#orderBuy-complete').DataTable().destroy();
        }
        document.getElementById("orderBuy").style.display = 'block';
        document.getElementById("orderBuy-complete").style.display = 'none';
        document.getElementById("ProductProcessingDisplay").style.display = 'none';
        document.getElementById("ProductDisplay").style.display = 'none';
        document.getElementById("ProductCompleteDisplay").style.display = 'none';
        document.getElementById("addProductForm").style.display = 'none';
        document.getElementById("Filter").style.display = 'none';
        // Kiểm tra xem DataTable đã được khởi tạo trên bảng chưa
        if ($.fn.DataTable.isDataTable('#orderBuy')) {
            // Nếu đã tồn tại, hủy DataTable hiện tại
            $('#orderBuy').DataTable().destroy();
        }

        // Gửi yêu cầu AJAX để nhận dữ liệu từ servlet
        $.ajax({
            type: 'GET',
            url: "orderChecking",
            success: function (response) {
                // Khởi tạo DataTables với dữ liệu nhận được từ servlet
                $('#orderBuy').DataTable({
                    "paging": true, // Cho phép phân trang
                    "pageLength": 5,
                    "searching": true,
                    "data": response, // Dữ liệu từ servlet
                    "columns": [
                        {"data": "orderCode", "searchable": true}, // Cho phép tìm kiếm trong cột "orderCode"
                        {"data": "orderStatus", "searchable": true}, // Cho phép tìm kiếm trong cột "orderStatus"
                        {"data": "sellerName", "searchable": true}, // Cho phép tìm kiếm trong cột "sellerName"
                        {"data": "categoryName", "searchable": true}, // Cho phép tìm kiếm trong cột "categoryName"
                        {"data": "contactMethod", "searchable": true}, // Cho phép tìm kiếm trong cột "contactMethod"
                        {"data": "Price", "searchable": true}, // Cho phép tìm kiếm trong cột "Price"
                        {"data": "Intermediary_fee", "searchable": true}, // Cho phép tìm kiếm trong cột "Intermediary_fee"
                        {"data": "isTransaction_fee", "searchable": true}, // Cho phép tìm kiếm trong cột "isTransaction_fee"
                        {
                            "data": null,
                            "render": function (data, type, row) {
                                return '<button class="btn btn-info btn-detail" data-ordercode="' + row.orderCode + '">Detail</button>';
                            }
                        },
                        {
                            "data": null,
                            "render": function (data, type, row) {
                                return '<a class="historyOrder" href="orderHistory?idor=' + row.orderCode + '"><i style="color: #FFFFFF" class="fa fa-calendar"></i> Lịch sử</a>';
                            }
                        }
                        // Thêm các cột khác tại đây
                    ]
                });
            },
            error: function () {
                // Xử lý lỗi nếu có
                alert("Đã xảy ra lỗi khi tải dữ liệu");
            }
        });
    });

    $(document).on('click', '.btn-detail', function () {
        var formData = {
            code: $(this).data("ordercode")
        };
        document.getElementById("myModalComplain").style.display = 'block';
        $.ajax({
            type: 'GET',
            url: 'orderBuyerDetail',
            data: formData,
            success: function (response) {
                $("#complaintForm").html(response);
            },
            error: function (xhr, status, error) {
                console.error(xhr.responseText);
            }
        });
        // Thực hiện các thao tác tiếp theo khi nút chi tiết được click
    });
});






$(document).ready(function () {
    $("#order-complete").click(function () {
        $('.dataTable').each(function () {
            $(this).DataTable().destroy();
        });
        if ($.fn.DataTable.isDataTable('#orderBuy')) {
            // Nếu đã tồn tại, hủy DataTable hiện tại
            $('#orderBuy').DataTable().destroy();
        }
        document.getElementById("orderBuy").style.display = 'none';
        document.getElementById("orderBuy-complete").style.display = 'block';
        document.getElementById("ProductProcessingDisplay").style.display = 'none';
        document.getElementById("ProductDisplay").style.display = 'none';
        document.getElementById("ProductCompleteDisplay").style.display = 'none';
        document.getElementById("addProductForm").style.display = 'none';
        document.getElementById("Filter").style.display = 'none';
        // Kiểm tra xem DataTable đã được khởi tạo trên bảng chưa
        if ($.fn.DataTable.isDataTable('#orderBuy-complete')) {
            // Nếu đã tồn tại, hủy DataTable hiện tại
            $('#orderBuy-complete').DataTable().destroy();
        }

        // Gửi yêu cầu AJAX để nhận dữ liệu từ servlet
        $.ajax({
            type: 'POST',
            url: "orderChecking",
            success: function (response) {
                // Khởi tạo DataTables với dữ liệu nhận được từ servlet
                $('#orderBuy-complete').DataTable({
                    "paging": true, // Cho phép phân trang
                    "pageLength": 5,
                    "data": response, // Dữ liệu từ servlet
                    "columns": [
                        {"data": "orderCode"},
                        {"data": "orderStatus"},
                        {"data": "sellerName"},
                        {"data": "categoryName"},
                        {"data": "contactMethod"},
                        {"data": "Price"},
                        {"data": "Intermediary_fee"},
                        {"data": "isTransaction_fee"},
                        {
                            "data": null,
                            "render": function (data, type, row) {
                                return '<button class="btn btn-info btn-detail" data-ordercode="' + row.orderCode + '">Detail</button>';
                            }
                        },
                        {
                            "data": null,
                            "render": function (data, type, row) {
                                return '<a class="historyOrder" href="orderHistory?idor="'+row.orderCode+'"}"><i style="color: #FFFFFF" class="fa fa-calendar"></i> Lịch sử</a>';
                            }
                        }

                        // Thêm các cột khác tại đây
                    ]
                });
            },
            error: function () {
                // Xử lý lỗi nếu có
                alert("Đã xảy ra lỗi khi tải dữ liệu");
            }
        });
    });
});

$(document).ready(function () {
    // Khi người dùng nhấn vào button, mở modal
    $(document).on("click", ".reportButton", function (e) {
        e.preventDefault();
        var orderId = $(this).data("orderid");
        var orderCode = $(this).data("ordercode");
        var productName = $(this).data("productname");
        var Price = $(this).data("price");
        var Imtermediary = $(this).data("inter");
        var party = $(this).data("party");
        var totalPaid = $(this).data("totalpaids");
        var productImg = $(this).data("proimg");
        var des = $(this).data("des");
        var hiddenInfo = $(this).data("hiddeninfo");
        var contact = $(this).data("contact");
        var status = $(this).data("status1");
        var buyer = $(this).data("buyers");
        var createTime = $(this).data("create");


        $("#complaintOrderButton").attr("data-order-id", orderId);

        $("#verifyOrderButton").attr("data-order-id", orderId);


        $("#order_id").val(orderId); // Cập nhật giá trị của trường input
        $("#order_code").val(orderCode); // Cập nhật giá trị của trường input
        $("#productName1").val(productName);
        $("#Price").val(Price);
        $("#inter").val(Imtermediary);
        $("#party1").val(party);
        $("#totalPaid1").val(totalPaid);
        $("#img1").val(productImg);
        $("#des").val(des);
        $("#hidden_info").val(hiddenInfo);
        $("#contact").val(contact);
        $("#status1").val(status);
        $("#buyer1").val(buyer);
        $("#create").val(createTime);

        document.getElementById("myModalComplain").style.display = 'block';
    });

    // Khi người dùng nhấn vào nút đóng (×), đóng modal
    $(".close").click(function () {
        document.getElementById("myModalComplain").style.display = 'none';

    });

    $("#verifyOrderButton1").click(function () {
        document.getElementById("exampleModalCreate").style.display = 'block';
    });


    $(".close1").click(function () {

        document.getElementById("exampleModalCreate").style.display = 'none';
        document.getElementById("myModalVerify").style.display = 'none';
        window.location.href = "manageMyOrder";
    });
    // Khi người dùng nhấp bất kỳ đâu ngoài modal, đóng modal
    $(window).click(function (event) {
        if (event.target === $("#myModalComplain")[0]) {
            document.getElementById("myModalComplain").style.display = 'none';
        }
    });
});

$(document).ready(function () {
    $(".filterBtn").click(function (e) {
        e.preventDefault(); // Ngăn chặn hành động mặc định của nút submit
        sendData();
    });

    $("select").change(function () {
        sendData();
    });

    function sendData() {
        var formData = {
            filter_code: $("#filter_code").val(),
            filter_name: $("#filter_name").val(),
            filter_price: $("#filter_price").val(),
            filter_status: $("#filter_status").val(),
            filter_party: $("#filter_party").val()
        };

        $.ajax({
            type: "POST",
            url: "filtermyorder",
            data: formData,
            success: function (response) {
                // Xử lý dữ liệu trả về nếu cần
                console.log(response);
            },
            error: function (xhr, status, error) {
                console.error(xhr.responseText);
            }
        });
    }


});

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



$("#complaintForm").submit(function (e) {
    e.preventDefault(); // Ngăn chặn hành vi gửi form mặc định

    // Lấy giá trị của data-orderi từ nút submit đã được nhấn
    var orderiValue = $(this).find("button[type='submit']:focus").attr("data-orderi");

    // Hàm thực hiện AJAX
    function performAjaxRequest() {
        var formData = {
            id: $("#order_id").val(),
            datax: orderiValue
        };
        $.ajax({
            type: 'POST',
            url: 'report', // URL của servlet xử lý
            data: formData, // Gửi dữ liệu form tới servlet
            success: function (response) {
                alert(response); // Hiển thị thông báo từ server
                if (response === "Bạn đã xác thực đơn hàng thành công. Xin cảm ơn!") {
                    document.getElementById("myModalComplain").style.display = 'none';
                    document.getElementById("exampleModalCreate").style.display = 'block';
                    return;
                }

                window.location.href = 'manageMyOrder'; // Chuyển hướng đến trang manageMyOrder sau khi gửi thành công
            },
            error: function (xhr, status, error) {
                console.error(error); // Ghi log lỗi ra console
            }
        });
    }

    // Sử dụng SweetAlert2 để xác nhận trước khi thực hiện AJAX
    Swal.fire({
        title: "Bạn có chắc chắn xác nhận không?",
        icon: "question",
        iconHtml: "",
        showCancelButton: true,
        confirmButtonText: "Có",
        cancelButtonText: "Không",
        showCloseButton: true
    }).then((result) => {
        if (result.isConfirmed) {
            performAjaxRequest(); // Nếu người dùng ấn đồng ý, thực hiện AJAX
        }
    });
});

$("#sellerForm").submit(function (e) {
    e.preventDefault(); // Ngăn chặn hành vi gửi form mặc định

    // Lấy giá trị của data-orderi từ nút submit đã được nhấn
    var orderiValue = $(this).find("button[type='submit']:focus").attr("data-orderi");

    // Hàm thực hiện AJAX
    function performAjaxRequest() {
        var formData = {
            code: $("#orderCode").val(),
            datax: orderiValue
        };
        $.ajax({
            type: 'GET',
            url: 'report', // URL của servlet xử lý
            data: formData, // Gửi dữ liệu form tới servlet
            success: function (response) {
                alert(response); // Hiển thị thông báo từ server
                window.location.href = 'manageMyOrder'; // Chuyển hướng đến trang manageMyOrder sau khi gửi thành công
            },
            error: function (xhr, status, error) {
                console.error(xhr.responseText); // Ghi log lỗi ra console
            }
        });
    }

    // Sử dụng SweetAlert2 để xác nhận trước khi thực hiện AJAX
    Swal.fire({
        title: "Bạn có chắc chắn xác nhận không?",
        icon: "question",
        iconHtml: "",
        showCancelButton: true,
        confirmButtonText: "Có",
        cancelButtonText: "Không",
        showCloseButton: true
    }).then((result) => {
        if (result.isConfirmed) {
            performAjaxRequest(); // Nếu người dùng ấn đồng ý, thực hiện AJAX
        }
    });
});






