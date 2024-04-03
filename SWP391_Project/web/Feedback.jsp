<%-- 
    Document   : Feedback
    Created on : Feb 9, 2024, 1:49:45 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
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
        <script src="https://cdn.ckeditor.com/4.20.2/standard/ckeditor.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        
    </head>

    <body>
         <%@include file="components/navBar.jsp" %>
         <div class="container-scroller" style="height: 80vh;padding-bottom: 20px">
            <div class="container-fluid page-body-wrapper">
                <!-- HEADER -->
               
                <div class="main-panel">
                    <div class="content-wrapper">
                        <div class="row">
                            <!-- ============================================================== -->
                            <!-- basic table  -->
                            <!-- ============================================================== -->
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="card">
                                    <h3 class="card-header">Phản Hồi </h3>
                                    <div class="card-body">           
                                        <div class="table-responsive">
                                            <table class="table table-striped table-bordered first">
                                                <thead>
                                                    <tr>
                                                        <th>#</th>
                                                        <th>Tiêu Đề</th>
                                                       
                                                        <th>Nội Dung</th>
                                                        <th>Ngày Tạo</th>
                                                         <th>Tên Người Dùng</th>
                                                        <th>Tính Năng</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${listF}" var="listF" varStatus="loop">
                                                        <tr>
                                                            
                                                            <td>${(currentPage - 1) * itemsPerPage + loop.index + 1}</td>
                                                            <td>${listF.title}</td>
                                                                 <td>${listF.content}</td>
                                                            <td>${listF.create_at}</td>
                                                            <td>${listF.username}</td>
                                                            <td class="text-center d-flex justify-content-center">
                                                                 <c:if test="${user.id eq listF.user_id}">
                                                                <button type="button" class="fa fa-pencil" data-toggle="modal" data-target="#exampleModal${listF.id}">               
                                                                </button>
                                                                <form action="" method="post" onsubmit="if(!confirm('Do you really want to delete?')){return false;}">
                                                                    <input type="text" name="action" value="delete" hidden="">
                                                                    <input type="text"  name="id" value="${listF.id}" hidden="">
                                                                    
                                                                        <button type="button" class="fa fa-trash" onclick="deleteItem(${listF.id})"></button>
                                                                </form>
                                                                

                                                            </td>
                                                             </c:if>
                                                        </tr>
                                                    </c:forEach>
                                                        


                                                </tbody>
                                               
                                            </table>
                                            <!-- Add these lines after your table -->
                                            <div class="pagination-container" style="text-align: center;">
    <ul class="pagination">
        <c:forEach begin="1" end="${totalPages}" var="page">
            <li class="page-item ${currentPage == page ? 'active' : ''}">
                <a class="page-link" href="Feedback?page=${page}">${page}</a>
            </li>
        </c:forEach>
    </ul>
</div>
                                               
                                        </div>
                                        
                                    </div>
                                   
                                    <!-- Modal -->
                                    <c:forEach items="${listF}" var="listF">
                                        <div class="modal fade" id="exampleModal${listF.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                            <div class="modal-dialog" style="max-width: 700px" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="exampleModalLabel">Feedback</h5>
                                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">

                                                        <form action="Feedback" method="post"> 
                                                            <input type="text" name="id" value="${listF.id}" hidden="">
                                                            <div class="row">

                                                                <div class="card">
                                                                    <div class="card-body">

                                                                        <input type="text" name="action" value="update" hidden="">

                                                                        <h2 class="card-title">Title</h2>
                                                                        <input type="text" name="title" value="${listF.title}" required="" class="form-control" ><br/>
                                                                        <h2 class="card-title">Content</h2>

                                                                               <input type="text" name="content" value="${listF.content}" required="" class="form-control" ><br/>
                                                     
                                                                       
                                                                     
                                                                       
                                                                       
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="modal-footer">

                                                                <button type="submit" class="btn btn btn-primary">Save Change</button>
                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                                <!-- ============================================================== -->
                                            </div>
                                        </div>


                                    </c:forEach>


                                    <!-- ============================================================== -->
                                    <!-- content-wrapper ends -->
                                    <!-- partial:partials/_footer.html -->
                                    <!-- partial -->
                                </div>
                                <!-- main-panel ends -->
                            </div>
                        </div>
                        <!-- main-panel ends -->
                    </div>
                </div>
                <!-- main-panel ends -->
            </div>
            <!-- page
            <!-- page-body-wrapper ends -->
        </div>
         <!-- FOOTER -->
        <%@include file="components/footer.jsp" %>
        <!-- /FOOTER -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="jscript/signin_captcha.js"></script>
        <!-- jQuery Plugins -->
        <script src="js1/jquery.min.js"></script>
        <script src="js1/bootstrap.min.js"></script>
        <script src="js1/slick.min.js"></script>
        <script src="js1/nouislider.min.js"></script>
        <script src="js1/jquery.zoom.min.js"></script>
        <script src="js1/main.js"></script>
        <script>
        // Hàm xóa mục sử dụng AJAX
        function deleteItem(itemId) {
             if (confirm('Do you really want to delete?')) {
            $.ajax({
                type: 'POST',
                url: 'Feedback',
                data: {
                    action: 'delete',
                    id: itemId
                },
                success: function (data) {
                    if (data.success) {
                        // Reload table data using AJAX
                        $.ajax({
                            type: 'GET',
                            url: 'Feedback',
                            success: function (html) {
                                // Replace the existing table with the updated one
                                $('.table-responsive').html($(html).find('.table-responsive').html());
                            }
                        });
                    }
                }
            });
        }
        }
    </script>
    </body>
</html>
