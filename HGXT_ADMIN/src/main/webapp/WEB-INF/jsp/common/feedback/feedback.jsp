<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>意见反馈管理</title>
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<link rel="stylesheet" href="../dist/jquery/dataTables.bootstrap.min.css">
		<link rel="stylesheet" href="../dist/bootstrap/bootstrap.min.css">
		<link rel="stylesheet" href="../dist/adminlte/AdminLTE.css">
		<link rel="stylesheet" href="../dist/adminlte/skin-mcblue.css">
		<link rel="stylesheet" href="../mcfish/css/mcfish.css">
	</head>

	<body class="hold-transition skin-blue sidebar-mini mc_body">
		<div class="wrapper">
			<div class="content-wrapper" style="margin-left: 0;">
				<section class="content no-padding">
					<div class="box box-default">
						<div class="box-header" style="line-height: 45px;">
							<h3 class="box-title text-blue box-header-text">意见反馈管理</h3>
							<div class="form-inline" style="margin: 10px 0 0 0;">
								<div class="form-group">
									<label class="text-color-999">状态:</label>
									<select id="feedbackStatus" class="form-control" onchange="getFeedbackList();">
										<option value="">全部</option>
										<option value="0">待处理</option>
										<option value="1">已采纳</option>
										<option value="2">已拒绝</option>
									</select>
								</div>
								<div class="form-group">
									<input type="text" id="feedbackFlag" class="form-control" placeholder="用户昵称、标题搜索" style="width: 240px;" oninput="search(this)" />
								</div>
								<div class="form-group">
									<button class="btn  btn-info" onclick="search('#feedbackFlag');">搜索</button>
								</div>
							</div>
						</div>
						<div class="box-body table-responsive">
							<table id="feedbackList" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>建议标题</th>
										<th>建议内容</th>
										<th>用户昵称</th>
										<th>用户电话</th>
										<th>处理意见</th>
										<th>提交时间</th>
										<th>处理状态</th>
										<th>操作</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</section>
			</div>
		</div>

		<!-- 拒绝弹窗 -->
		<div class="modal fade" id="editInfoView" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="margin-top: 10%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span>
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title text-center">提示</h4>
					</div>
					<div class="modal-body">
						<div class="itemOne">
							<p class="text-color-999" style="width:12%">处理意见</p>
							<textarea id="editInfoItemContent" class="form-control" rows="3" placeholder="" style="height: 120px; width:80%; resize: none;"></textarea>
						</div>
					</div>
					<div class="modal-footer" style="text-align: center;">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-info" id="comfirmEditInfo">确定</button>
					</div>
				</div>
			</div>
		</div>

		<!-- jQuery 3 -->
		<script src="../dist/jquery/jquery.min.js"></script>
		<script src="../dist/bootstrap/bootstrap.min.js"></script>
		<script src="../dist/adminlte/adminlte.min.js"></script>
		<script src="../dist/jquery/jquery.dataTables.js"></script>
		<script src="../dist/jquery/ui.js"></script>
		<script src="../mcfish/js/mcfish.js"></script>
		<script src="../mcfish/js/feedback/feedback.js"></script>
	</body>

</html>