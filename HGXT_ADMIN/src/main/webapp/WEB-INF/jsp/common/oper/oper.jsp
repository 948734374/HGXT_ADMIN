<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>运营管理</title>
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<link rel="stylesheet" href="../dist/jquery/dataTables.bootstrap.min.css">
		<link rel="stylesheet" href="../dist/bootstrap/bootstrap.min.css">
		<link rel="stylesheet" href="../dist/adminlte/AdminLTE.css">
		<link rel="stylesheet" href="../dist/adminlte/skin-mcblue.css">
		<link rel="stylesheet" href="../dist/datetimepicker/jquery.datetimepicker.css">
		<link rel="stylesheet" href="../mcfish/css/mcfish.css">
	</head>

	<body class="hold-transition skin-blue sidebar-mini mc_body">
		<div class="wrapper">
			<div class="content-wrapper" style="margin-left: 0;">
				<section class="content no-padding">
					<div class="box box-default">
						<div class="box-header" style="line-height: 45px;">
							<h3 class="box-title text-blue box-header-text">运营管理</h3>
						</div>
						<div class="box-body table-responsive no-padding">
							<div class="nav-tabs-custom no-shadow">
								<ul class="nav nav-tabs">
									<li class="active" onclick="getBannerList();">
										<a href="#tab_1" data-toggle="tab" aria-expanded="false" class="pad10">BANNER管理</a>
									</li>
									<li class="" onclick="getAllGuide();">
										<a href="#tab_2" data-toggle="tab" aria-expanded="false" class="pad10">引导页管理</a>
									</li>
									<li class="" onclick="getVersionList();">
										<a href="#tab_3" data-toggle="tab" aria-expanded="false" class="pad10">版本管理</a>
									</li>
								</ul>
								<div class="tab-content">
									<!-- BANNER管理 -->
									<div class="tab-pane active" id="tab_1">
										<div class="box no-border no-shadow no-padding">
											<div class="box-header" style="line-height: 45px;">
												<div class="form-inline">
													<div class="form-group pull-right">
								                    	<button type="button" class="btn btn-info" onclick="openAddBannerView();">添加BANNER</button>   
													</div>
												</div>
											</div>
											<div class="box-body table-responsive no-padding">
												<table id="bannerList" class="table table-bordered table-hover">
													<thead>
														<tr>
															<th>图片</th>
															<th>标题</th>
															<th>展位</th>
															<th>开始时间</th>
															<th>结束时间</th>
															<th>状态</th>
															<th>操作</th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
									</div>
		
									<!-- 引导也管理 -->
									<div class="tab-pane" id="tab_2">
										<div class="box no-border no-shadow no-padding">
											<div class="box-body table-responsive no-padding">
												<ul class="guidelist" id="guidelist">
													
												</ul>
											</div>
										</div>
									</div>
		
									<!-- 版本管理 -->
									<div class="tab-pane" id="tab_3">
										<div class="box no-border no-shadow no-padding">
											<div class="box-header" style="line-height: 45px;">
												<div class="form-inline">
													<div class="form-group pull-right">
								                    	<button type="button" class="btn btn-info" onclick="openActionAppView();">添加版本</button>   
													</div>
												</div>
											</div>
											<div class="box-body table-responsive no-padding">
												<table id="versionList" class="table table-bordered table-hover">
													<thead>
														<tr>
															<th>APP类型</th>
															<th>版本信息</th>
															<th>试调开关</th>
															<th>是否强制更新</th>
															<th>备注信息</th>
															<th>下载地址</th>
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
		
		<!-- 编辑/添加BANNER弹窗 -->
		<div class="modal fade" id="addBannerView" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="margin-top: 10%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
						<span class="sr-only">Close</span>
					</button>
						<h4 class="modal-title text-center" id="bannerBoxTitle"></h4>
					</div>
					<div class="modal-body">
						<div class="addInputs" style="width: 100%;">
							<div class="inputs">
								<span class="text-color-999">标题</span>
								<input id="addBannerTitle" class="form-control" placeholder="请输入标题，最长20个字符">
							</div>
							<div class="inputs">
								<span class="text-color-999">展位</span>
								<select class="form-control" id="addBannerPos">
									<option value="0">首页</option>
									<option value="1">菜单栏</option>
								</select>
							</div>
							<div class="inputs">
								<span class="text-color-999">BANNER图片</span>
								<div>
									<div class="uploadImg">
										<img id="addBannerImg" src="http://wx.qlogo.cn/mmopen/vi_32/nrZwlTIgnN95WrbOdcJHYfGR6PqAd3NEVNevWIlADOibM8ojK51moJcgUyia8lcUmOVk8PkliaA3V9jBfemicdJQOA/0">
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
								<span class="text-color-999">开始时间</span>
								<input id="addBannerStartTime" class="form-control" placeholder="请输入开始时间">
							</div>
							<div class="inputs">
								<span class="text-color-999">结束时间</span>
								<input id="addBannerEndTime" class="form-control" placeholder="请输入结束时间">
							</div>
							<div class="inputs">
								<span class="text-color-999">跳转</span>
								<input id="addBannerUrl" class="form-control" placeholder="请输入跳转地址，最长50个字符，	可选、优先">
							</div>
							<div class="inputs">
								<span class="text-color-999">扩展</span>
								<input id="addBannerEndData" class="form-control" placeholder="请输入扩展字段，最长50个字符，	可选，如key:value">
							</div>
						</div>
					</div>
					<div class="modal-footer" style="text-align: center;">
						<button type="button" data-dismiss="modal" class="btn btn-default">取消</button>
						<button type="button" id="addBannerBtn" class="btn btn-info">保存</button>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 编辑Guide 弹窗 -->
		<div class="modal fade" id="editGuideView" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="margin-top: 10%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
						<span class="sr-only">Close</span>
					</button>
						<h4 class="modal-title text-center" id="guideBoxTitle">编辑Guide</h4>
					</div>
					<div class="modal-body">
						<div class="addInputs" style="width: 100%;">
							<div class="inputs">
								<span class="text-color-999">BANNER图片</span>
								<div>
									<div class="uploadImg">
										<img id="addGuideImg" src="http://wx.qlogo.cn/mmopen/vi_32/nrZwlTIgnN95WrbOdcJHYfGR6PqAd3NEVNevWIlADOibM8ojK51moJcgUyia8lcUmOVk8PkliaA3V9jBfemicdJQOA/0">
										<p id="actionMsgImgTool">
											<span>
												<a href="javascript:;" id="pickfiles" class="file">
													选择文件
						 							<input type="file" name="picUrl" id="picUrl" class="btn btn-primary">
						                        </a>
					 							<button class="btn btn-default">删除</button>
											</span>
											<font>仅支持jpg、png格式图片，图片比例为750px*400px,不得超过1M</font>
										</p>
									</div>
								</div>
							</div>
							<div class="inputs">
								<span class="text-color-999">状态</span>
								<select class="form-control" id="addGuideStatus">
									<option value="0">显示</option>
									<option value="1">隐藏</option>
								</select>
							</div>
							<div class="inputs">
								<span class="text-color-999">图片描述</span>
								<input id="addGuideComment" class="form-control" placeholder="图片描述">
							</div>
						</div>
					</div>
					<div class="modal-footer" style="text-align: center;">
						<button type="button" data-dismiss="modal" class="btn btn-default">取消</button>
						<button type="button" id="addGuideBtn" class="btn btn-info">保存</button>
					</div>
				</div>
			</div>
		</div>
		
		
		<!-- 编辑APP 弹窗 -->
		<div class="modal fade" id="actionAppView" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="margin-top: 10%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
						<span class="sr-only">Close</span>
					</button>
						<h4 class="modal-title text-center" id="appBoxTitle"></h4>
					</div>
					<div class="modal-body">
						<div class="addInputs" style="width: 100%;">
							
							<div class="inputs">
								<span class="text-color-999">APP类型</span>
								<select class="form-control" id="actionAppType">
									<option value="0">Android</option>
									<option value="1">IOS</option>
								</select>
							</div>
							
							<div class="inputs">
								<span class="text-color-999">调试开关</span>
								<select class="form-control" id="actionAppisDebug">
									<option value="0">正式</option>
									<option value="1">调试</option>
								</select>
							</div>
							
							<div class="inputs">
								<span class="text-color-999">强制更新</span>
								<select class="form-control" id="actionAppisForce">
									<option value="0">否</option>
									<option value="1">是</option>
								</select>
							</div>
							
							<div class="inputs">
								<span class="text-color-999">版本信息</span>
								<input id="actionAppVersion" class="form-control" placeholder="请输入版本信息">
							</div>
							
							<div class="inputs">
								<span class="text-color-999">备注</span>
								<textarea id="actionAppComment" class="form-control" placeholder="请输入备注信息"></textarea>
							</div>
							
							<div class="inputs">
								<span class="text-color-999">下载地址</span>
								<input id="actionAppDownload" class="form-control" placeholder="请输入下载地址">
							</div>
							
						</div>
					</div>
					<div class="modal-footer" style="text-align: center;">
						<button type="button" data-dismiss="modal" class="btn btn-default">取消</button>
						<button type="button" id="actionAppBtn" class="btn btn-info">保存</button>
					</div>
				</div>
			</div>
		</div>
		
		
		<script src="../dist/jquery/jquery.min.js"></script>
		<script src="../dist/bootstrap/bootstrap.min.js"></script>
		<script src="../dist/adminlte/adminlte.min.js"></script>
		<script src="../dist/jquery/jquery.dataTables.js"></script>
		<script src="../dist/datetimepicker/jquery.datetimepicker.full.js"></script>
		<script src="../dist/jquery/ui.js"></script>
		<script src="../mcfish/js/mcfish.js"></script>
		<script src="../mcfish/js/oper/oper.js"></script>
	</body>

</html>