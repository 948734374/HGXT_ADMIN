<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>系统设置</title>
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
							<h3 class="box-title text-blue box-header-text">系统设置</h3>
						</div>
						<div class="box-body table-responsive no-padding">
							<div class="nav-tabs-custom no-shadow">
								<ul class="nav nav-tabs">
									<li class="active" onclick="getBasicConfigList();">
										<a href="#tab_1" data-toggle="tab" aria-expanded="false" class="pad10">基础配置</a>
									</li>
									<li class="" onclick="getPayConfigList();">
										<a href="#tab_2" data-toggle="tab" aria-expanded="false" class="pad10">充值配置</a>
									</li>
									<li class="" onclick="getVersionList();">
										<a href="#tab_3" data-toggle="tab" aria-expanded="false" class="pad10">硬件版本</a>
									</li>
									<li class="" onclick="getAboutList();">
										<a href="#tab_4" data-toggle="tab" aria-expanded="false" class="pad10">关于我们</a>
									</li>
								</ul>
								<div class="tab-content">
									<!-- 基础配置 -->
									<div class="tab-pane active" id="tab_1">
										<div class="box no-border no-shadow no-padding">
											<div class="box-header" style="line-height: 45px;">
												<div class="form-inline">
													<div class="form-group">
								                    	<label class="text-color-999">查询:</label>      
														<input type="text" id="basicFlag" class="form-control" placeholder="输入关键词信息进行模糊搜索" style="width: 240px;" oninput="search(1,this)"/>
													</div>
													<div class="form-group">
														<button class="btn  btn-info" onclick="search(1,'#basicFlag');">搜索</button>
													</div>
												</div>
											</div>
											<div class="box-body table-responsive no-padding">
												<table id="basicConfigList" class="table table-bordered table-hover">
													<thead>
														<tr>
															<th>配置项</th>
															<th>配置内容</th>
															<th>配置说明</th>
															<th>操作</th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
									</div>
		
									<!-- 充值配置 -->
									<div class="tab-pane" id="tab_2">
										<div class="box no-border no-shadow no-padding">
											<div class="box-body table-responsive no-padding">
												<table id="payConfigList" class="table table-bordered table-hover">
													<thead>
														<tr>
															<th>充值金额（元）</th>
															<th>设计到账（元）</th>
															<th>赠送金额（元）</th>
															<th>备注</th>
															<th>操作</th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
									</div>
		
									<!-- 版本管理 -->
									<div class="tab-pane" id="tab_3">
										<div class="box no-border no-shadow no-padding">
											<div class="box-header" style="line-height: 45px;">
												<div class="form-inline">
													<div class="form-group">
								                    	<label class="text-color-999">版本列表</label>      
													</div>
													<div class="form-group pull-right">
								                    	<button type="button" class="btn btn-info" onclick="openAddVersionView()">添加版本</button>   
													</div>
												</div>
											</div>
											<div class="box-body table-responsive no-padding">
												<table id="versionList" class="table table-bordered table-hover">
													<thead>
														<tr>
															<th>APP类型</th>
															<th>版本信息</th>
															<th>备注信息</th>
															<th>试调开关</th>
															<th>是否强制更新</th>
															<th>下载地址</th>
															<th>操作</th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
									</div>
		
									<!-- 关于我们 -->
									<div class="tab-pane" id="tab_4">
										<div class="box no-border no-shadow no-padding">
											<div class="box-header" style="line-height: 45px;">
												<div class="form-inline">
													<div class="form-group">
								                    	<label class="text-color-999">查询:</label>      
														<input type="text" id="aboutFlag" class="form-control" placeholder="输入关键词信息进行模糊搜索" style="width: 240px;" oninput="search(2,this)"/>
													</div>
													<div class="form-group">
														<button class="btn  btn-info" onclick="search(2,'#aboutFlag');">搜索</button>
													</div>
													<div class="form-group pull-right">
														<button type="button" onclick="openAddAbout();" class="btn btn-block btn-info ">添加</button>
													</div>
												</div>
											</div>
											<div class="box-body table-responsive no-padding">
												<table id="aboutList" class="table table-bordered table-hover">
													<thead>
														<tr>
															<th>关键字</th>
															<th>内容标题</th>
															<th>内容</th>
															<th>操作</th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
									</div>
		
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
		</div>
		
		<!-- 编辑基础项配置弹窗 -->
		<div class="modal fade" id="editBasicConfigView" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="margin-top: 10%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
						<span class="sr-only">Close</span>
					</button>
						<h4 class="modal-title text-center">编辑</h4>
					</div>
					<div class="modal-body">
						<div class="addInputs" style="width: 100%;">
							<div class="inputs">
								<span class="text-color-999">配置项</span>
								<input id="editBasicConfigkey_name" class="form-control" placeholder="请输入配置项名称">
							</div>
							<div class="inputs">
								<span class="text-color-999">配置内容</span>
								<textarea id="editBasicConfigvalue" class="form-control" rows="3" placeholder="请输入配置项内容"></textarea>
							</div>
							<div class="inputs">
								<span class="text-color-999">配置说明</span>
								<textarea id="editBasicConfigcomment" class="form-control" rows="3" placeholder="请输入配置项说明"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer" style="text-align: center;">
						<button type="button" data-dismiss="modal" class="btn btn-default">取消</button>
						<button type="button" id="editBasicConfigBtn" class="btn btn-info">保存</button>
					</div>
				</div>
			</div>
		</div>
		
		
		<!-- 编辑充值项配置弹窗 -->
		<div class="modal fade" id="editPayConfigView" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="margin-top: 10%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
						<span class="sr-only">Close</span>
					</button>
						<h4 class="modal-title text-center">编辑充值配置</h4>
					</div>
					<div class="modal-body">
						<div class="addInputs">
							<div class="inputs">
								<span class="text-color-red" style="width: 25%;">充值金额（元）</span>
								<input id="editPayConfigAmount" class="form-control" placeholder="请输入充值金额">
							</div>
							
							<div class="inputs">
								<span class="text-color-red" style="width: 25%;">实际到账（元）</span>
								<input id="editPayConfigCharge" class="form-control" placeholder="请输入实际到账金额">
							</div>
							
							<div class="inputs">
								<span class="text-color-red" style="width: 25%;">赠送金额（元）</span>
								<input id="editPayConfigGive" class="form-control" placeholder="请输入赠送金额">
							</div>
							
							<div class="inputs">
								<span class="text-color-999" style="width: 25%;">备注</span>
								<input id="editPayConfigComment" class="form-control" placeholder="请输入备注">
							</div>
							
						</div>
					</div>
					<div class="modal-footer" style="text-align: center;">
						<button type="button" data-dismiss="modal" class="btn btn-default">取消</button>
						<button type="button" id="editPayConfigBtn" class="btn btn-info">保存</button>
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
		<script src="../mcfish/js/system/system.js"></script>
	</body>

</html>