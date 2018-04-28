<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>平台首页</title>
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<link rel="stylesheet" href="../dist/fonts/font-awesome.min.css">
		<link rel="stylesheet" href="../dist/fonts/ionicons.min.css">
		<link rel="stylesheet" href="../dist/jquery/dataTables.bootstrap.min.css">
		<link rel="stylesheet" href="../dist/bootstrap/bootstrap.min.css">
		<link rel="stylesheet" href="../dist/adminlte/AdminLTE.css">
		<link rel="stylesheet" href="../dist/adminlte/skin-mcblue.css">
		<link rel="stylesheet" href="../dist/morris/morris.css">
		<link rel="stylesheet" href="../mcfish/css/mcfish.css">
	</head>

	<body class="hold-transition skin-blue sidebar-mini mc_body">
		<div class="wrapper">
			<div class="content-wrapper" style="margin-left: 0;padding-bottom: 10px">
				<section class="content no-padding">
					<div class="box box-default">
						<div class="box-header" style="line-height: 45px;">
							<h3 class="box-title text-blue box-header-text">平台首页</h3>
						</div>
						<div class="box-body table-responsive home-body" style="padding-top: 2%;">
							
							<div class="col-md-3 col-sm-6 col-xs-12">
								<div class="info-box">
									<span class="info-box-icon bg-aqua">
										<i class="fa fa-cubes"></i>
									</span>
									<div class="info-box-content">
										<span class="info-box-text">平台总收益（元）</span>
										<a href="${pageContext.request.contextPath}/shareCabinetController/toCabinetPage.do">
											<span class="info-box-number" id="totalIncome" style="font-size: 22px;"></span>
										</a>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-6 col-xs-12">
								<div class="info-box">
									<span class="info-box-icon bg-green">
										<i class="fa fa-mail-forward"></i>
									</span>
									<div class="info-box-content">
										<span class="info-box-text">平台余额（元）</span>
										<a href="${pageContext.request.contextPath}/shareMoneyController/list.do?flag=2">
											<span class="info-box-number" id="totalBalance" style="font-size: 22px;"></span>
										</a>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-6 col-xs-12">
								<div class="info-box">
									<span class="info-box-icon bg-yellow">
										<i class="fa fa-google-wallet"></i>
									</span>
									<div class="info-box-content">
										<span class="info-box-text">平台总押金（元）</span>
										<a href="${pageContext.request.contextPath}/shareMoneyController/list.do?flag=3">
											<span class="info-box-number" id="totalDeposit" style="font-size: 22px;"></span>
										</a>
									</div>
								</div>
							</div>

							<div class="col-md-3 col-sm-6 col-xs-12">
								<div class="info-box">
									<span class="info-box-icon bg-red">
										<i class="fa  fa-paypal"></i>
									</span>
									<div class="info-box-content">
										<span class="info-box-text">平台总订单（元）</span>
										<a href="${pageContext.request.contextPath}/shareMoneyController/list.do?flag=4">
											<span class="info-box-number" id="totalOrder" style="font-size: 22px;"></span>
										</a>
									</div>
								</div>
							</div>


							<div class="col-md-6 col-lg-6 col-xs-12">
								<div class="home-title">
									<font size="5px" color="red">用户总数:</font>
									<font id="totalUsers" size="5px;"></font>
								</div>
								
								<div class="box box-info">
									<div class="box-header with-border">
										<h3 class="box-title">每月用户数</h3>&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
									<div class="box-body chart-responsive">
										<div class="chart" id="user-chart" style="height: 400px;"></div>
									</div>
								</div>
								
							</div>
							
							<div class="col-md-6 col-lg-6 col-xs-12">
								<div class="home-title">
									<font size="5px" color="red">订单总数</font>
								</div>
								
								<div class="box box-info">
									<div class="box-header with-border">
										<h3 class="box-title">每月订单数</h3>
									</div>
									<div class="box-body chart-responsive">
										<div class="chart" id="line-chart1" style="height: 400px;"></div>
									</div>
								</div>
								
							</div>
							
						</div>
					</div>
				</section>
			</div>
		</div>

		<script src="../dist/jquery/jquery.min.js"></script>
		<script src="../dist/bootstrap/bootstrap.min.js"></script>
		<script src="../dist/adminlte/adminlte.min.js"></script>
		<script src="../dist/morris/morris.min.js"></script>
		<script src="../dist/echarts/echarts.min.js"></script>
		<script src="../dist/jquery/ui.js"></script>
		<script src="../mcfish/js/mcfish.js"></script>
		<script src="../mcfish/js/home/home.js"></script>
	</body>

</html>