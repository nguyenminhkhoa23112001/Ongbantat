<!doctype html>
<html lang="en">
    <head>
        <title>ODITS</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <link rel="stylesheet" href="css/Login.css">
        <style>
            body {
                background: url("https://cdn.pixabay.com/photo/2018/02/23/04/38/computer-3174729_1280.jpg") no-repeat center center fixed;
                background-size: cover;
            }
        </style>
    </head>
    <body class="img js-fullheight">

        <section class="ftco-section">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-6 text-center mb-5">
                        <h2 class="heading-section">Online Digital Information Transactions System</h2>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <div class="col-md-6 col-lg-4" style="background-color: rgba(0,0,0,0.7); padding: 3%;border-radius: 20px" id="signin1">
                        <div class="login-wrap p-0">
                            <div style="display: flex;text-align: center;margin-bottom: 3%;">
                                <a style="height: 40px" href="home">
                                    <button style="border-radius: 100%;width:40px;height: 40px" id="backHome">
                                        <i class="fas fa-home"></i>
                                    </button>
                                </a>
                                <h3 style="font-weight: bold; margin-left: 25%">Sign in</h3>
                            </div>
                            <form>
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Username" name ="user" id="user-Signin">
                                </div>
                                <div class="form-group">
                                    <input id="password-field" type="password" class="form-control" placeholder="Password" name="password">
                                    <span toggle="#password-field" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                                </div>
                                <div class="form-group" style="display: flex">
                                    <img style="border-radius: 40px" id="captchaImage" src="captchaimage" alt="CAPTCHA image">
                                    <input type="text" class="form-control" placeholder="Captcha" name="capchaRespone" id="captcha">
                                    <button type="button" onclick="refreshCaptcha()" style="width: 100px; background-color: white; border-radius:100%">
                                        <i class="fa fa-refresh" style="color: black;"></i>
                                    </button>
                                </div>
                                <div class="form-group">
                                    <button onclick="SignIn()" type="button" class="form-control btn btn-primary submit px-3">Sign In</button>
                                </div>
                                <div class="form-group d-md-flex">
                                    <div class="w-50 text-md-center">
                                        <a href="forgot" style="color: #fff">Forgot Password</a>
                                    </div>
                                </div>
                            </form>
                            <p class="w-100 text-center">&mdash; Do not have an account ? &mdash;</p>

                            <div class="social d-flex text-center" style="justify-content:center;">
                                <button id="signupButton1" class="w-100 px-2 py-2 mr-md-1" style="border-radius: 40px;">SIGN UP</a></button>
                            </div>
                        </div>
                    </div>
                    
                    
                    
                    <div class="col-md-6 col-lg-4" style="background-color: rgba(0,0,0,0.7); padding: 3%;border-radius: 20px; display: none;" id="signup1">
                        <div class="login-wrap p-0">
                            <div style="display: flex;text-align: center;margin-bottom: 3%;">
                                <a style="height: 40px" href="home">
                                    <button style="border-radius: 100%;width:40px;height: 40px" id="backHome">
                                        <i class="fas fa-home"></i>
                                    </button>
                                </a>
                                <h3 style="font-weight: bold; margin-left: 25%">Sign Up</h3>
                            </div>
                            <form id="signupForm">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="username" placeholder="Username" required>
                                </div>
                                <div class="form-group">
                                    <input id="password-signup" type="password" class="form-control" placeholder="Password"
                                           required>
                                    <span toggle="#password-signup" class="fa fa-fw fa-eye field-icon toggle-password"></span>

                                </div>
                                <div class="form-group">
                                    <input id="confirm-Pass" type="password" class="form-control"
                                           placeholder="Confirm password" required>
                                    <span toggle="#confirm-Pass" class="fa fa-fw fa-eye field-icon toggle-password"></span>
                                </div>

                                <div class="form-group">
                                    <input id="email" type="text" class="form-control"
                                           placeholder="Email" required>

                                </div>
                                <div class="form-group" style="display: flex">
                                    <img style="border-radius: 40px" id="captchaImage2" src="captchaimage" alt="CAPTCHA image">
                                    <input type="text" class="form-control" placeholder="Captcha" name="capchaRespone" id="captcha2">
                                    <button type="button" onclick="refreshCaptcha2()" style="width: 100px; background-color: white; border-radius:100%">
                                        <i class="fa fa-refresh" style="color: black;"></i>
                                    </button>
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="form-control btn btn-primary submit px-3" id="signup-button2">Sign Up</button>
                                </div>
                            </form>
                            <p class="w-100 text-center">&mdash; You have an account ? &mdash;</p>
                            <div class="social d-flex text-center" style="justify-content:center">
                                <a class="px-2 py-2 mr-md-1" style="border-radius: 40px" id="signin-Button">SIGN IN</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
        <script src="jscript/signin_captcha.js"></script>
        <script src="jscript/signup.js"></script>
        
    </body>
</html>