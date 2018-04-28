<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>消息推送</title>
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
							<h3 class="box-title text-blue box-header-text">消息推送</h3>
							<div class="form-inline" style="margin: 10px 0 0 0;">
								<div class="form-group">
									<label class="text-color-999">状态:</label>
									<select id="messageStatus" class="form-control" onchange="getMessageList();">
										<option value="">全部</option>
										<option value="0">未发送</option>
										<option value="1">已发送</option>
									</select>
								</div>
								<div class="form-group">
									<input type="text" id="messageFlag" class="form-control" placeholder="标题搜索" style="width: 240px;" oninput="search(this)" />
								</div>
								<div class="form-group">
									<button class="btn  btn-info" onclick="search('#messageFlag');">搜索</button>
								</div>
								<div class="form-group pull-right">
									<button class="btn  btn-info" onclick="openAddMessage();">新建推送</button>
								</div>
							</div>
						</div>
						<div class="box-body table-responsive">
							<table id="messageList" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>消息标题</th>
										<th>消息内容</th>
										<th>消息来源</th>
										<th>状态</th>
										<th>提交时间</th>
										<th>操作</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</section>
			</div>
		</div>

		<!-- 编辑/添加弹窗 -->
		<div class="modal fade" id="actionMessageView" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="margin-top: 10%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
						<span class="sr-only">Close</span>
					</button>
						<h4 class="modal-title text-center" id="messageBoxTitle"></h4>
					</div>
					<div class="modal-body">
						<div class="addInputs" style="width: 100%;">
							<div class="inputs">
								<span class="text-color-999">消息标题</span>
								<input id="actionMsgTitle" class="form-control" placeholder="请输入消息标题">
							</div>
							<div class="inputs">
								<span class="text-color-999">消息图片</span>
								<div>
									<div class="uploadImg">
										<img id="actionMsgImg" src="http://wx.qlogo.cn/mmopen/vi_32/nrZwlTIgnN95WrbOdcJHYfGR6PqAd3NEVNevWIlADOibM8ojK51moJcgUyia8lcUmOVk8PkliaA3V9jBfemicdJQOA/0">
										<p id="actionMsgImgTool">
											<span>
												<a href="javascript:;" id="pickfiles" class="file">
													选择文件
						 							<input type="file" name="picUrl" id="picUrl" class="btn btn-primary">
						                        </a>
					 							<button class="btn btn-default">删除</button>
											</span>
											<font>仅限png、jpg格式图片</font>
										</p>
									</div>
								</div>
							</div>
							<div class="inputs">
								<span class="text-color-999">消息内容</span>
								<textarea id="actionMsgContent" class="form-control" rows="3" placeholder="请输入消息内容"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer" style="text-align: center;">
						<button type="button" data-dismiss="modal" class="btn btn-default">取消</button>
						<button type="button" id="actionMsgBtn" class="btn btn-info">保存</button>
					</div>
				</div>
			</div>
		</div>


		<!-- 消息详情弹窗 -->
		<div class="modal fade" id="msgInfoView" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="margin-top: 10%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
						<span class="sr-only">Close</span>
					</button>
						<h4 class="modal-title text-center">消息详情</h4>
					</div>
					<div class="modal-body">
						<div class="addInputs" style="width: 100%;">
							<div class="inputs">
								<span class="text-color-999">消息标题</span>
								<input id="showMsgTitle" class="form-control" disabled="disabled">
							</div>
							<div class="inputs">
								<span class="text-color-999">消息图片</span>
								<div>
									<div class="uploadImg">
										<img id="showMsgImg" src="http://wx.qlogo.cn/mmopen/vi_32/nrZwlTIgnN95WrbOdcJHYfGR6PqAd3NEVNevWIlADOibM8ojK51moJcgUyia8lcUmOVk8PkliaA3V9jBfemicdJQOA/0">
									</div>
								</div>
							</div>
							<div class="inputs">
								<span class="text-color-999">消息内容</span>
								<textarea id="showMsgContent" class="form-control" rows="3" disabled="disabled" style="height: 140px;"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer" style="text-align: center;">
						<button type="button" data-dismiss="modal" class="btn btn-default">确定</button>
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
		<script src="../mcfish/js/message/message.js"></script>
	</body>

</html>