<%-- 
    Document   : LeftAdmin
    Created on : 19 thg 1, 2024, 15:38:40
    Author     : Nguyen Minh Khoa
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Sidebar -->
<nav id="sidebarMenu" class="collapse d-lg-block sidebar collapse bg-white" style="padding: 0px">
    <div class="position-sticky">
        <div class="list-group list-group-flush mx-3 mt-4">

            <a href="home" class="list-group-item list-group-item-action py-2 ripple">
                <i class="fas fa-home fa-fw me-3"></i><span>Trang chủ</span>
            </a>

            <a href="ManageAccount" class="list-group-item list-group-item-action py-2 ripple">
                <i class="fas fa-user-circle fa-fw me-3"></i><span>Quản lý tài khoản</span>
            </a>
            <a href="editcategory" class="list-group-item list-group-item-action py-2 ripple">
                <i class="fas fa-user-circle fa-fw me-3"></i><span>Quản lý danh mục</span>
            </a>
            <a href="ReportServlet" class="list-group-item list-group-item-action py-2 ripple">
                <i class="fas fa-exclamation-triangle fa-fw me-3"></i><span>Quản lý khiếu nại</span>
            </a>
            <a href="RevenueAdmin" class="list-group-item list-group-item-action py-2 ripple">
                <i class="fa fa-money fa-fw me-3"></i><span>Quản Lý Doanh Thu</span>
            </a>

            <a href="withdrawalprocessing" class="list-group-item list-group-item-action py-2 ripple">
                <i class="fas fa-money fa-fw me-3"></i><span>Yêu cầu rút tiền</span>
            </a>
        </div>
    </div>
</nav>
<!-- Sidebar -->
