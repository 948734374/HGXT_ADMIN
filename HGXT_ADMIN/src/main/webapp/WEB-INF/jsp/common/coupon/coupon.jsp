<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>优惠券管理</title>
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
							<h3 class="box-title text-blue box-header-text">优惠券管理</h3>
							<div class="form-inline" style="margin: 10px 0 0 0;">
								<div class="form-group">
									<label class="text-color-999">状态:</label>
									<select id="couponStatus" class="form-control" onchange="getCouponList();">
										<option value="">全部</option>
										<option value="0">上线</option>
										<option value="1">下线</option>
									</select>
								</div>
								<div class="form-group">
									<input type="text" id="couponFlag" class="form-control" placeholder="商家名称搜索" style="width: 240px;" oninput="search(1,this)" />
								</div>
								<div class="form-group">
									<button class="btn  btn-info" onclick="search('#couponFlag');">搜索</button>
								</div>
								<div class="form-group pull-right">
									<button class="btn  btn-info" onclick="openActionCouponView();">发布优惠券</button>
								</div>
							</div>
						</div>
						<div class="box-body table-responsive">
							<table id="couponList" class="table table-bordered table-hover">
								<thead>
									<tr>
										<th>优惠券图片</th>
										<th>优惠券LOGO</th>
										<th>领取码</th>
										<th>简介</th>
										<th>发布数量</th>
										<th>领取数量</th>
										<th>开始时间</th>
										<th>结束时间</th>
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

		<!-- 编辑/添加弹窗 -->
		<div class="modal fade" id="actionCouponView" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="margin-top: 10%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
						<span class="sr-only">Close</span>
					</button>
						<h4 class="modal-title text-center" id="couponBoxTitle"></h4>
					</div>
					<div class="modal-body">
						<div class="addInputs" style="width: 100%;">
							
							<div class="inputs">
								<span class="text-color-999">优惠券图片</span>
								<div>
									<div class="uploadImg">
										<img id="actionCouponImg" src="http://wx.qlogo.cn/mmopen/vi_32/nrZwlTIgnN95WrbOdcJHYfGR6PqAd3NEVNevWIlADOibM8ojK51moJcgUyia8lcUmOVk8PkliaA3V9jBfemicdJQOA/0">
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
								<span class="text-color-999">摘要说明</span>
								<input id="actionCouponBrief" class="form-control" placeholder="请对优惠券进行摘要说明">
							</div>
							<div class="inputs">
								<span class="text-color-999">优惠券LOGO</span>
								<div>
									<div class="uploadImg">
										<img id="actionCouponLogoImg" src="http://wx.qlogo.cn/mmopen/vi_32/nrZwlTIgnN95WrbOdcJHYfGR6PqAd3NEVNevWIlADOibM8ojK51moJcgUyia8lcUmOVk8PkliaA3V9jBfemicdJQOA/0">
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
								<span class="text-color-999">跳转地址</span>
								<input id="actionCouponUrl" class="form-control" placeholder="点击优惠券页面会进入跳转页面		可选">
							</div>
							<div class="inputs">
								<span class="text-color-999">优惠券数量</span>
								<input id="actionCouponNumber" class="form-control" placeholder="请输入优惠券数量 	0表示无限量">
							</div>
							<div class="inputs">
								<span class="text-color-999">开始时间</span>
								<input id="actionCouponBegin" class="form-control" placeholder="开始时间">
							</div>
							<div class="inputs">
								<span class="text-color-999">结束时间</span>
								<input id="actionCouponEnd" class="form-control" placeholder="结束时间">
							</div>
							<div class="inputs">
								<span class="text-color-999">状态</span>
								<select class="form-control" id="actionCouponStatus">
									<option value="0">上线</option>
									<option value="1">下线</option>
								</select>
							</div>
						</div>
					</div>
					<div class="modal-footer" style="text-align: center;">
						<button type="button" data-dismiss="modal" class="btn btn-default">取消</button>
						<button type="button" id="actionCouponBtn" class="btn btn-info">保存</button>
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
		<script src="../mcfish/js/coupon/coupon.js"></script>
	</body>

</html>