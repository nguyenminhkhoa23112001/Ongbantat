

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://unpkg.com/bootstrap@5.3.2/dist/css/bootstrap.min.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Verify OTP</title>
        <link rel="stylesheet" href="css/verify.css">
    </head>
    <body>

        <c:if test="${flag == 1}">
            <form action="forgot" method="post">
                <!-- Password Reset 8 - Bootstrap Brain Component -->
                <section class="bg-light p-3 p-md-4 p-xl-5">
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-12 col-xxl-11">
                                <div class="card border-light-subtle shadow-sm">
                                    <div class="row g-0">
                                        <div class="col-12 col-md-6">
                                            <img class="img-fluid rounded-start w-100 h-100 object-fit-cover" loading="lazy" src="img/checkmail1.png" alt="Welcome back you've been missed!">
                                        </div>
                                        <div class="col-12 col-md-6 d-flex align-items-center justify-content-center">
                                            <div class="col-12 col-lg-11 col-xl-10">
                                                <div class="card-body p-3 p-md-4 p-xl-5">
                                                    <div class="row">
                                                        <div class="col-12">
                                                            <div class="mb-5">
                                                                <div class="text-center mb-4">
                                                                    <a href="#!">
                                                                        <img src="img/checkmail.jpg"   width="200" height="100">
                                                                    </a>
                                                                </div>
                                                                <h2 class="h4 text-center">Password Reset</h2>
                                                                <h3 class="fs-6 fw-normal text-secondary text-center m-0">Provide the email address associated with your account to recover your password.</h3>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <form action="#!">
                                                        <div class="row gy-3 overflow-hidden">
                                                            <div class="col-12">
                                                                <div class="form-floating mb-3">
                                                                    <input type="email" class="form-control" name="email" id="email" placeholder="name@example.com" required>
                                                                    <label for="email" class="form-label">Email</label>
                                                                </div>
                                                            </div>
                                                            <div class="col-12">
                                                                <div class="d-grid">
                                                                    <button class="btn btn-dark btn-lg" id="resetButton" type="submit">Reset Password</button>
                                                                </div>
                                                                <p style="color: red;text-align: center">${requestScope.msg}</p>
                                                            </div>
                                                        </div>
                                                    </form>
                                                    <div class="row">
                                                        <div class="col-12">
                                                            <div class="d-flex gap-2 gap-md-4 flex-column flex-md-row justify-content-md-center mt-5">
                                                                <a href="signin.jsp" class="link-secondary text-decoration-none">Login</a>

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </form> 
        </c:if>

        <c:if test="${flag == 2}">
            <form action="reset" method="get">
                <div class="otp-box">           
                    <div class="img">
                        <img src="https://t.ly/7CuFm" alt="" width="100px">
                    </div>
                    <div class="content-box">
                        <h2>Verification code</h2>
                        <p>We just send a Verification code to your email</p>
                    </div>
                    <div class="inputs">
                        <input type="text" maxlength="5" name="otp" required="">
                    </div>
                    <div class="verify-button">
                        <button type="submit">Verify</button>
                    </div>           
                </div>
            </form> 
        </c:if>

        <c:if test="${flag == 3}">
            <form action="reset" method="post">
                <!-- Registration 8 - Bootstrap Brain Component -->
                <section class="bg-light p-3 p-md-4 p-xl-5">
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-12 col-xxl-11">
                                <div class="card border-light-subtle shadow-sm">
                                    <div class="row g-0">
                                        <div class="col-12 col-md-6">
                                            <img class="img-fluid rounded-start w-100 h-100 object-fit-cover" loading="lazy" src="img/resetpass.jpg " alt="Welcome back you've been missed!">
                                        </div>
                                        <div class="col-12 col-md-6 d-flex align-items-center justify-content-center">
                                            <div class="col-12 col-lg-11 col-xl-10">
                                                <div class="card-body p-3 p-md-4 p-xl-5">
                                                    <div class="row">
                                                        <div class="col-12">
                                                            <div class="mb-5">
                                                                <div class="text-center mb-4">
                                                                    <a href="#!">
                                                                        <img src="img/forfot.jpg"  width="200" height="100">
                                                                    </a>
                                                                </div>
                                                                <h2 class="h4 text-center">New Password</h2>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="row">                              
                                                    </div>
                                                    <form action="#!">
                                                        <div class="row gy-3 overflow-hidden">
                                                            <div class="col-12">
                                                                <div class="form-floating mb-3">
                                                                    <input type="password" class="form-control" name="password" >
                                                                    <label for="firstName" class="form-label">Password</label>
                                                                </div>
                                                            </div>
                                                            <div class="col-12">
                                                                <div class="form-floating mb-3">
                                                                    <input type="password" class="form-control" name="cpassword">
                                                                    <label for="lastName" class="form-label">Confirm Password </label>
                                                                </div>
                                                            </div>
                                                            <p style="color: red;text-align: center">${requestScope.error}</p>
                                                        </div>
                                                        <div class="col-12">
                                                            <div class="d-grid">
                                                                <button class="btn btn-dark btn-lg" type="submit">Reset</button>
                                                                <p style="color: red;text-align: center">${requestScope.msg}</p>
                                                                <div class="row">
                                                                    <div class="col-12">
                                                                        <p class="mb-0 mt-5 text-secondary text-center">Already have an account? <a href="signin.jsp" class="link-primary text-decoration-none">Sign in</a></p>

                                                                    </div>
                                                                </div>
                                                            </div>
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

                </section>
            </form> 
        </c:if>


    </div>
    <script>
        let isButtonClicked = false;

        document.getElementById('resetButton').addEventListener('click', function (event) {
            if (!isButtonClicked) {
                // Thực hiện hành động
                isButtonClicked = true;

                // Tùy chọn: Thêm loader hoặc thông báo
            } else {
                // Ngăn không cho hành động xảy ra lần nữa
                event.preventDefault();
            }
        });
    </script>
</body>
</html>