<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>管理员配置</title>
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<link rel="stylesheet" href="../dist/jquery/dataTables.bootstrap.min.css">
		<link rel="stylesheet" href="../dist/bootstrap/bootstrap.min.css">
		<link rel="stylesheet" href="../dist/adminlte/AdminLTE.css">
		<link rel="stylesheet" href="../dist/adminlte/skin-mcblue.css">
		<link rel="stylesheet" href="../mcfish/css/mcfish.css">
	</head>

	<body class="hold-transition skin-blue sidebar-mini mc_body">
		<div class="wrapper">
			<div class="content-wrapper" style="margin-left: 0;padding-bottom: 10px">
				<section class="content no-padding">
					<div class="box box-default">
						<div class="box-header" style="line-height: 45px;">
							<h3 class="box-title text-blue box-header-text">管理员配置</h3>
							<div class="form-inline" style="margin: 10px 0 0 0;">
								<div class="form-group">
									<label class="text-color-999">状态:</label>
									<select id="adminStatus" class="form-control" onchange="getAdminList();">
										<option value="">全部</option>
										<option value="0">正常</option>
										<option value="1">冻结</option>
									</select>
								</div>
								<div class="form-group">
									<input type="text" id="adminFlag" class="form-control" placeholder="用户ID、名称、手机号搜索" style="width: 240px;" oninput="search(this)" />
								</div>
								<div class="form-group">
									<button class="btn  btn-info" onclick="search('#adminFlag');">搜索</button>
								</div>
								<div class="form-group pull-right">
									<button type="button" onclick="openAddAdminView();" class="btn btn-block btn-info ">添加管理员</button>
								</div>
							</div>
						</div>
						<div class="box-body table-responsive">
							<table id="adminList" class="table  table-bordered table-hover">
								<thead>
									<tr>
										<th>账号</th>
										<th>姓名</th>
										<th>手机号</th>
										<th>备列注</th>
										<th>账号类型</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</section>
			</div>
		</div>
		
		
		<!-- 【编辑权限】弹出窗 -->
		<div class="modal" id="authModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="margin-top: 10%;">
				<div class="modal-content">
					<div class="box" style="margin-bottom: 0px;">
						<div class="box-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">×</span>
								<span class="sr-only">Close</span>
							</button>
							<h3 class="box-title">权限配置</h3>
							<span style="margin-left: 100px;">
								<input type="checkbox" name = "readAll" onchange="checkReadAll(this)">全部（查看）
							</span>
							<span style="margin-left: 145px;">
								<input type="checkbox" name = "writeAll" onchange="checkWriteAll(this)">全部（编辑）
							</span>
						</div>
						<div class="box-body no-padding">
							<table class="table">
								<tbody>
									<tr>
										<th align="center">序号</th>
										<th>模块名称</th>
										<th align="center">查看</th>
										<th align="center">编辑</th>
										<th align="center">序号</th>
										<th>模块名称</th>
										<th align="center">查看</th>
										<th align="center">编辑</th>
									</tr>
									<c:forEach items="${allMenu}" var="menu" varStatus="status">
										<c:if test="${status.count%2==1}">
											<tr>
										</c:if>
											<td>${menu.id}</td>
											<td><span>${menu.name}</span></td>
											<td><input type="checkbox" name="read" value="${menu.id}"></td>
											<td><input type="checkbox" name="write" value="${menu.id}"></td>
										<c:if test="${status.count%2==0}">
											</tr>
										</c:if>
									</c:forEach> 
								</tbody>
								<input type="hidden" id="adminID">

							</table>
						</div>
					</div>
					<div class="modal-footer" style="text-align: center;padding: 10px 15px;">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary" id="confirmEditAuthBtn" onclick="confirmEditAuth();">确定</button>
					</div>
				</div>
			</div>
		</div>


		<!-- 【新增管理员】弹出窗内容 -->
		<div class="modal fade" id="addAdminView" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="margin-top: 10%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
		                    <span aria-hidden="true">×</span>
		                    <span class="sr-only">Close</span>
		                </button>
						<h4 class="modal-title" align="center" id="addAdminViewTitle"></h4>
					</div>
					<div class="modal-body">
						<div class="addInputs">
							<div class="inputs">
								<span class="text-color-999">*名称/姓名:</span>
								<input type="text" id="add-username" maxlength="16" class="form-control" placeholder="显示的名字" />
							</div>
							<div class="inputs">
								<span class="text-color-999">*账号:</span>
								<input type="text" id="add-account" class="form-control" placeholder="请输入账号" />
							</div>
							<div class="inputs">
								<span class="text-color-999">*手机:</span>
								<input type="text" id="add-phone" class="form-control" placeholder="请输入绑定手机" />
							</div>
							<div class="inputs">
								<span class="text-color-999">*备注:</span>
								<input type="text" id="add-comment" class="form-control" placeholder="请输入账号备注说明" />
							</div>
						</div>
					</div>
					<h5 id="info-title-o" class="modal-title text-red text-center" hidden="hidden"> 初始密码统一为123456，请务必让员工登录后修改密码 </h5>
					<h5 id="info-title-t" class="modal-title text-red text-center mar-bottom10" hidden="hidden"> 若遗忘密码，请为其重置为初始密码 </h5>
					<div class="modal-footer" style="text-align: center;padding: 10px 15px;">
						<button type="button" class="btn btn-info" id="saveAddAdminBtn">保存</button>
					</div>
				</div>
			</div>
		</div>


		<script src="../dist/jquery/jquery.min.js"></script>
		<script src="../dist/bootstrap/bootstrap.min.js"></script>
		<script src="../dist/adminlte/adminlte.min.js"></script>
		<script src="../dist/jquery/jquery.dataTables.js"></script>
		<script src="../dist/jquery/ui.js"></script>
		<script src="../mcfish/js/mcfish.js"></script>
		<script src="../mcfish/js/admin/admin.js"></script>
	</body>

</html>