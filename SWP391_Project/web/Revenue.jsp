<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <style>
    .text-center {
        text-align: center;
    }

    @media (min-width: 768px) {
        .text-center {
            margin-left: auto;
            width: 66.66666667%; /* 2/3 of the screen */
        }
    }
</style>
    </head>
    </head>

    <body>
        <%@include file="components/navBar.jsp" %>
        <div class="container-scroller" style="height: 80vh;padding-bottom: 20px">
            <div class="container-fluid page-body-wrapper">
                <!-- HEADER -->

                <div class="main-panel">
                    <div class="content-wrapper">
                        <div class="row">
                            <div class="col-xl-5 col-lg-5 col-md-5 col-sm-6 col-5" style="height: 803px;">
                                <canvas id="revenueBarChart" width="100" height="150"></canvas>
                                <h3 class="mb-0 text-center"><strong>Doanh thu theo thang</strong></h3>
                                
                            </div>
                       
                              <div class="col-xl-5 col-lg-5 col-md-5 col-sm-6 col-5">
                                            <canvas class="my-4 w-100" id="pieChart" width="90"height="90"></canvas>
                                           
                             </div>
                        </div>    
       
                         <h3 class="mb-0 text-center"><strong>Doanh thu theo thu</strong></h3>
                        <div class="row">
    <div class="col-xl-6 col-lg-3 col-md-6 col-sm-12 col-12">
        <div class="card border-3 border-top border-top-primary">
            <div class="card-body">
                <h5 class="text-muted">Doanh Thu</h5>
                <div class="metric-value d-inline-block">
                    <h1 class="mb-1">${revenueData}</h1>
                </div>
            </div>
        </div>
    </div>
</div>
        <div class="row">
    <div class="col-xl-6 col-lg-3 col-md-6 col-sm-12 col-12">
        <div class="card border-3 border-top border-top-primary">
            <div class="card-body">
                <h5 class="text-muted">So Luong San Pham </h5>
                <div class="metric-value d-inline-block">
                    <h1 class="mb-1">${quantityAll}</h1>
                </div>
            </div>
        </div>
    </div>
</div>

                                         
                        <!-- Your existing content -->
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

            <script>// Graph
                            //pie
                            
                            var ctxP = document.getElementById("pieChart").getContext('2d');

                            var myPieChart = new Chart(ctxP, {
                                type: 'pie',
                                data: {
                                    labels: ["Chu Nhat", "Thu 7", "Thu 6", "Thu 5", "Thu 4", "Thu 3", "Thu 2"],
                                    datasets: [{
                                            data: [${totalMoney1}, ${totalMoney7}, ${totalMoney6}, ${totalMoney5}, ${totalMoney4}, ${totalMoney3}, ${totalMoney2}],
                                            backgroundColor: ["#F7464A", "#46BFBD", "#FDB45C", "#949FB1", "#4D5360", "#1874CD", "#CDB5CD"],
                                            hoverBackgroundColor: ["#FF5A5E", "#5AD3D1", "#FFC870", "#A8B3C5", "#616774", "#1E90FF", "#FFE1FF"]
                                        }]
                                },
                                options: {
                                    responsive: true
                                }
                            });

                        </script>
                        <script>
                            // Get chart data from the Java servlet
                            var chartData = ${chartData.values()};

                            // Transform the Java HashMap into JavaScript arrays for Chart.js
                            var labels = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
                            var data = [];

                            // Iterate over the entries in the HashMap

                            labels = labels.map(Number);

                            // Chart configuration
                            var options = {
                                title: {
                                    display: true,
                                    text: 'Monthly Revenue'
                                },
                                legend: {
                                    display: false
                                }
                            };

                            // Get the canvas element
                            var ctx = document.getElementById('revenueBarChart').getContext('2d');

                            // Create a bar chart
                            var myBarChart = new Chart(ctx, {
type: 'bar',
                                data: {
                                    labels: labels,
                                    datasets: [{
                                            label: "Doanh Thu",
                                            backgroundColor: "#3e95cd",
                                            data: chartData
                                        }]
                                },
                                options: options
                            });
                        </script>
                        
    </body>
</html>