<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>后台管理系统</title>
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<link rel="stylesheet" href="dist/fonts/font-awesome.min.css">
		<link rel="stylesheet" href="dist/fonts/ionicons.min.css">
		<link rel="stylesheet" href="dist/bootstrap/bootstrap.min.css">
		<link rel="stylesheet" href="dist/select2/select2.min.css">
		<link rel="stylesheet" href="dist/adminlte/AdminLTE.css">
		<link rel="stylesheet" href="dist/adminlte/skin-mcblue.css">
		<link rel="stylesheet" href="mcfish/css/mcfish.css">

		<style>
			#menuFrame {
				width: 100%;
			}
		</style>
		<!-- jQuery 3 -->
		<script src="dist/jquery/jquery.min.js"></script>
		<script>
			function iframeHight() {
				var ifm = document.getElementById("menuFrame");
				ifm.height = document.documentElement.clientHeight
				var bodyHeight = document.body.clientHeight
				$('#leftMenu').css({
					'height': bodyHeight,
					'overflow-y': 'auto'
				}) // 这里会碰***隐藏显示
			}
			window.onresize = function() {
				iframeHight()
			}
		</script>
	</head>

	<body class="hold-transition skin-blue sidebar-mini" style="overflow: hidden;">
		<div class="wrapper">
			<header class="main-header">
				<a href="${pageContext.request.contextPath}/shareBasicController/toWelcomePage.do" target="menuFrame" class="logo" >
					<span class="logo-mini" style="color: #ff9751;"><b>M</b>c</span>
					<span class="logo-lg"><b id="indexProjectName"></b></span>
				</a>
				
				<nav class="navbar navbar-static-top" role="navigation">
					<div class="container pull-left" style="width: 100%; padding-left: 0; padding-right: 13px;">
					
						<a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
					    	<span class="sr-only">Toggle navigation</span>
					    </a>
					
						<!-- 顶部菜单 -->
						<div class="navbar-header pad-left15">
							<a href="javascript:;" data-id='0' target="menuFrame" class="navbar-brand toggle-nav-line" style="font-size: 14px;" onclick="">测试菜单1</a>
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
								<i class="fa fa-bars"></i>
							</button>
						</div>
						<div class="collapse navbar-collapse pull-left" id="navbar-collapse">
							<ul class="nav navbar-nav">
								<li><a class="toggle-nav-line" href="javascript:;" target="menuFrame" id="showDevMenu" onclick="" data-id='1'>测试菜单2 </a></li>
								<li><a class="toggle-nav-line" href="javascript:;" target="menuFrame" onclick="" data-id='2'>测试菜单3</a></li>
								<li class="visi-settings"><a href="javascript:;" target="menuFrame" onclick="" data-id='3' class="toggle-nav-line" >测试菜单4</a></li>
							</ul>
						</div>
					
						<!-- 头部导航栏右侧菜单（消息菜单、用户菜单） -->
						<div class="navbar-custom-menu">
							<ul class="nav navbar-nav">
								<!-- 用户按钮 -->
								<li class="dropdown user user-menu">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown">
										<img id="userImg" src="http://p.qlogo.cn/bizmail/EYpKfaTucnc9cmico9IQALvL628bCoZgtubJ6RoqGhl8ndXdtnZ6OYA/0" class="user-image">
										<span class="hidden-xs" id="user_Name">...</span>
									</a>
									<ul class="dropdown-menu systemConfig">
										<li class="visi-settings">
											<a href="javascript:;" target="menuFrame" onclick="openEditPwdView()">修改密码</a>
										</li>
										<li>
											<a href="javascript:;" onclick="loginOut()">注销</a>
										</li>
									</ul>
								</li>
							</ul>
						</div>
					</div>
				</nav>
			</header>
			<aside class="main-sidebar" id="leftMenu">
				<!-- 左侧菜单 -->
				<section class="sidebar" id="developLeftMenu">
					<ul class="sidebar-menu mc-menu" data-widget="tree" id="leftMenu">
			 			<!-- 菜单管理 -->
				    	<c:forEach items="${MyLookAuth}" var="menu">
							 <li class="treeview" id="${menu.id}"  > 
								 <a href="${pageContext.request.contextPath}${menu.link}" target="menuFrame"> 
								 	<i class="${menu.icon}">
								    </i>   
									<span>${menu.name}</span>
								 </a>
							 </li>
						</c:forEach> 
					</ul>
				</section>

			</aside>
			<div class="content-wrapper" id="leftContenWrapper">
				<section class="content" style="padding: 0;">
					<iframe id="menuFrame" class="embed-responsive-item" name="menuFrame" src="${pageContext.request.contextPath}/shareBasicController/toWelcomePage.do" frameborder="0" onload="iframeHight()" allowfullscreen></iframe>
				</section>
			</div>
		</div>
		
		
		<!-- 通用弹窗 sm-->
		<div class="modal fade" id="tips_dialog_sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog mc-dialog-sm">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span>
					<span class="sr-only">Close</span>
				</button>
						<h4 class="modal-title" align="center" id="tips_dialog_title"></h4>
					</div>
					<div class="modal-body">
						<div id="tips_dialog_content" class="text-center" style="color: #666666"></div>
					</div>
					<div class="modal-footer" style="text-align: center;">
						<button type="button" class="btn btn-info tips_dialog_button" data-dismiss="modal">确定</button>
					</div>
				</div>
			</div>
		</div>
		
		
		<!-- 修改密码弹窗 -->
		<div class="modal fade" id="editPwdView" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="margin-top: 10%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span>
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title text-center">修改密码</h4>
					</div>
					<div class="modal-body">
						<div class="addInputs" style="width: 100%;">
							<div class="inputs">
								<span class="text-color-999">原始密码</span>
								<input id="oldPassword" type="password" class="form-control" placeholder="请输入原始密码">
							</div>
							<div class="inputs">
								<span class="text-color-999">新密码</span>
								<input id="newPassword" type="password" class="form-control" placeholder="请输入6~16位与原密码不同的新密码">
							</div>
							<div class="inputs">
								<span class="text-color-999">确认新密码</span>
								<input id="newPassword2" type="password" class="form-control" placeholder="请再次输入新密码">
							</div>
						</div>
					</div>
					<div class="modal-footer" style="text-align: center;">
						<button type="button" data-dismiss="modal" class="btn btn-default">取消</button>
						<button type="button" class="btn btn-info" onclick="savePwd()">保存</button>
					</div>
				</div>
			</div>
		</div>


		<script src="dist/jquery/ui.js"></script>
		<script src="dist/bootstrap/bootstrap.min.js"></script>
		<script src="dist/bootstrap/checkbix.min.js"></script>
		<script src="dist/jquery/jquery.combo.select.js"></script>
		<script src="dist/adminlte/adminlte.min.js"></script>
		<script src="mcfish/js/mcfish.js"></script>
		<script src="mcfish/js/index.js"></script>
	</body>

</html>