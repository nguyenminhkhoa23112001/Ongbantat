<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop Homepage - Start Bootstrap Template</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
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
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <!-- Custom stlylesheet -->
        <link type="text/css" rel="stylesheet" href="css/style.css" />
        <style>
            .formedit {

                max-width: 900px;
                margin: 0 auto;
                background-color: #fff;
                padding: 20px;
                border: 1px solid #ddd;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                margin-bottom: 11em;
                margin-top: 5em;
            }

            label {
                display: block;
                margin-bottom: 5px;
            }


            input {
                width: 100%;
                padding: 8px;
                margin-bottom: 10px;
                box-sizing: border-box;
            }

            input[type="submit"] {
                background-color: #808080;
                color: #fff;
                cursor: pointer;
                text-align: center;

                max-width: 250px;
            }

            input[type="submit"]:hover {
                background-color: #808080;
                max-width: 250px;
                text-align: center;
            }

            .error-message {
                color: red;
                margin-top: -10px;
                margin-bottom: 10px;
            }

            .info-message {
                color: blue;
                margin-top: -10px;
                margin-bottom: 10px;
            }

            .nav-borders .nav-link.active {
                color: #0061f2;
                border-bottom-color: #0061f2;
            }
            .nav-borders .nav-link {
                color: #69707a;
                border-bottom-width: 0.125rem;
                border-bottom-style: solid;
                border-bottom-color: transparent;
                padding-top: 0.5rem;
                padding-bottom: 0.5rem;
                padding-left: 0;
                padding-right: 0;
                margin-left: 1rem;
                margin-right: 1rem;
            }

        </style>
    </head>
    <body>
        <!-- Navigation-->
        <%@include file="components/navBar.jsp" %>

        <div class="container px-4 px-lg-5 mt-5">
            <nav style="margin-top: 5em;">
                <ul style="display: flex;">
                    
                </ul>


            </nav>
            <form action="EditProfile" method="post" class="formedit" >
                <h1 style="text-align: center;">Information</h1>

                <div style="display: flex; justify-content: space-between; max-width: 1000px;">
                    <!-- First Column -->
                    <div style="width: 48%;">
                        <!-- ID Field -->
                        <label>ID:</label>
                        <input type="number" name="id" value="${user.id}" readonly="">
                        <br>

                        <!-- Display Name Field -->
                        <label>Display Name:</label>
                        <input type="text" name="display" value="${user.display_name}">
                        <c:if test="${not empty errorMsg3}">
                            <p class="error-message">${errorMsg3}</p>
                        </c:if>
                        <br>
                    </div>

                    <!-- Second Column -->
                    <div style="width: 48%;">
                        <!-- Username Field -->
                        <label>Username:</label>
                        <input type="text" name="username" value="${user.username}" readonly="">
                        <c:if test="${not empty errorMsg1}">
                            <p class="error-message">${errorMsg1}</p>
                        </c:if>
                        <br>

                        <!-- Email Field -->
                        <label>Email:</label>
                        <input type="text" name="email" value="${user.email}">
                        <c:if test="${not empty errorMsg4}">
                            <p class="error-message">${errorMsg4}</p>
                        </c:if>
                        <br>
                    </div>
                </div>
                <!-- Submit Button -->
                <div style="text-align: center;">
                    <input type="submit" value="Update" style="border-radius: 20px; margin-top: 1em;">
                </div>
                <label style="color: green">${requestScope.done}</label><br/>
            </form>


            <!-- form 2-->
            <form action="ChangePassword" method="post" class="formedit">
                <h1 style="text-align: center;">Change Password</h1>
                <label for="username">Username: </label>
                <input type="text" name="username" value="${user.username}" readonly=""><br>
                <label for="oldPassword">Old Password:</label>
                <input type="password" name="oldPassword" required><br>

                <label for="newPassword">New Password:</label>
                <input type="password" name="newPassword" required><br>

                <label for="confirmPassword">Confirm New Password:</label>
                <input type="password" name="confirmPassword" required><br>
                <label style="color: red">${requestScope.fail}</label><br/>
                <label style="color: green">${requestScope.done1}</label><br/>
                <div style="text-align: center;">
                    <input type="submit" value="Update" style="border-radius: 20px; margin-top: 1em;">
                </div>
            </form>
        </div>
        <%@include file="components/footer.jsp" %>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="jscript/login_logout.js">
        </script>
    </body>
</html>
