<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>用户详情</title>
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<link rel="stylesheet" href="../dist/jquery/dataTables.bootstrap.min.css">
		<link rel="stylesheet" href="../dist/bootstrap/bootstrap.min.css">
		<link rel="stylesheet" href="../dist/adminlte/AdminLTE.css">
		<link rel="stylesheet" href="../dist/adminlte/skin-mcblue.css">
		<link rel="stylesheet" href="../dist/jquery/caldate/caldate.css">
		<link rel="stylesheet" href="../mcfish/css/mcfish.css">
	</head>

	<body class="hold-transition skin-blue sidebar-mini mc_body">
		<div class="wrapper">
			<div class="content-wrapper" style="margin-left: 0;padding-bottom: 10px">
				<section class="content-header bg-color-white">
			      <ol class="mcbreadcrumb breadcrumb">
			        <li><a href="${pageContext.request.contextPath}/shareUserController/UserPage.do" target="menuFrame"><i class="fa fa-dashboard"></i>用户管理</a></li>
			        <li class="active">用户详情</li>
			      </ol>
			    </section>
				<section class="content no-padding">
					<div class="nav-tabs-custom">
						<ul class="nav nav-tabs">
							<li class="active" onclick="getUserInfo();">
								<a href="#tab_1" data-toggle="tab" aria-expanded="false">基本资料</a>
							</li>
							<li class="" onclick="getUsageRecordList();">
								<a href="#tab_2" data-toggle="tab" aria-expanded="false">使用记录</a>
							</li>
						</ul>
						<div class="tab-content">
							<!-- 基本资料 -->
							<div class="tab-pane active" id="tab_1">
								<div class="box no-border no-shadow">
									<div class="box-body table-responsive no-padding">
										<div class="info-container">
											<div class="info-container_left">
			
												<div class="item">
													<div class="item_left">
														<span>用户id</span>
													</div>
													<div class="item_right">
														<div id="info-uid"></div>
													</div>
												</div>
			
												<div class="item">
													<div class="item_left">
														<span>头像</span>
													</div>
													<div class="item_right">
														<div id="info-header">
															<img />
														</div>
													</div>
												</div>
												<div class="item">
													<div class="item_left">
														<span>昵称</span>
													</div>
													<div class="item_right">
														<div id="info-username"></div>
													</div>
												</div>
												<div class="item">
													<div class="item_left">
														<span>性别</span>
													</div>
													<div class="item_right">
														<div id="user-sex"></div>
													</div>
												</div>
												<div class="item">
													<div class="item_left">
														<span>绑定手机号</span>
													</div>
													<div class="item_right">
														<div id="info-phone"></div>
													</div>
												</div>
												<div class="item">
													<div class="item_left">
														<span>用户邮箱</span>
													</div>
													<div class="item_right">
														<div id="info-email"></div>
													</div>
												</div>
												<div class="item">
													<div class="item_left">
														<span>邀请者id</span>
													</div>
													<div class="item_right">
														<div id="info-father_id"></div>
													</div>
												</div>
												<div class="item">
													<div class="item_left">
														<span>用户身份位</span>
													</div>
													<div class="item_right">
														<div id="info-identity"></div>
													</div>
												</div>
												<div class="item">
													<div class="item_left">
														<span>用户生日</span>
													</div>
													<div class="item_right">
														<div id="info-birthday"></div>
													</div>
												</div>
											</div>
			
											<div class="info-container_right">
												<div class="item">
													<div class="item_left">
														<span>绑定Facebook</span>
													</div>
													<div class="item_right">
														<div id="info-openid_facebook"></div>
													</div>
												</div>
												<div class="item">
													<div class="item_left">
														<span>绑定Google</span>
													</div>
													<div class="item_right">
														<div id="info-openid_google"></div>
													</div>
												</div>
												<div class="item">
													<div class="item_left">
														<span>用户状态</span>
													</div>
													<div class="item_right">
														<div id="info-status"></div>
													</div>
												</div>
												<div class="item">
													<div class="item_left">
														<span>注册时间</span>
													</div>
													<div class="item_right">
														<div id="info-create_time"></div>
													</div>
												</div>
												<div class="item">
													<div class="item_left">
														<span class="tab_text_blue pointer" onclick="openEditUserInfoView()">编辑资料</span>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>

							<!-- 使用记录 -->
							<div class="tab-pane" id="tab_2">
								<div class="box box-default no-border">
									<div class="box-header" style="line-height: 45px;">
										<div class="form-inline">
											<div class="form-group">
						                      <label class="text-color-999">状态:</label>      
						                      <select id="userStatus" class="form-control" onchange="getUserList();">
						                        <option value="">全部</option>
						                        <option value="0">正常</option>
						                        <option value="1">冻结</option>
						                      </select>     
						                      <label class="text-color-999">身份:</label>      
						                      <select id="userIdentity" class="form-control" onchange="getUserList();">
						                        <option value="">全部</option>
						                        <option value="0">用户</option>
						                        <option value="1">商家</option>
						                        <option value="3">员工</option>
						                      </select>              
						                   </div>
											<div class="form-group">
												<input type="text" id="deviceFlag" class="form-control" placeholder="用户ID、名称、手机号搜索" style="width: 240px;" oninput="search(this)"/>
											</div>
											<div class="form-group">
												<button class="btn  btn-info" onclick="search('#deviceFlag');">搜索</button>
											</div>
										</div>
									</div>
									<div class="box-body table-responsive">
										<table id="userList" class="table table-bordered table-hover">
											<thead>
												<tr>
													<th>用户ID</th>
													<th>昵称</th>
													<th>性别</th>
													<th>手机号</th>
													<th>余额</th>
													<th>押金</th>
													<th>积分</th>
													<th>绑定微信</th>
													<th>身份位</th>
													<th>状态</th>
													<th>操作</th>
												</tr>
											</thead>
										</table>
									</div>
								</div>
							</div>

						</div>
					</div>
				</section>
			</div>
		</div>

		<!-- 编辑用户详情弹窗 -->
		<div class="modal fade" id="editUserInfoView" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="margin-top: 10%;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">×</span>
							<span class="sr-only">Close</span>
						</button>
						<h4 class="modal-title text-center">编辑用户资料</h4>
					</div>
					<div class="modal-body">
						<div class="editUserinfo">
							<div class="item-head">
								<div class="key">
									<span>用户ID</span>
								</div>
								<div class="value">
									<p id="edit-info-userId"></p>
								</div>
							</div>
							<div class="item-head img">
								<div class="key">
									<span >头像</span>
								</div>
								<div class="value">
									<div class="uploadImg">
										<img id="edit-info-header" src="http://p.qlogo.cn/bizmail/EYpKfaTucnc9cmico9IQALvL628bCoZgtubJ6RoqGhl8ndXdtnZ6OYA/0">
										<p>
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
							<div class="item">
								<div class="left">
									<div class="subitem">
										<div class="key">
											<span>昵称</span>
										</div>			
										<div class="value">
											<input id="edit-info-userName" class="form-control" placeholder="用户昵称"/>
										</div>
									</div>
									<div class="subitem">
										<div class="key">
											<span>状态</span>
										</div>			
										<div class="value">
											<select id="edit-info-status" class="form-control">
												<option value="0">正常</option>
												<option value="1">冻结</option>
											</select>
										</div>
									</div>
									<div class="subitem">
										<div class="key">
											<span>邀请者id</span>
										</div>			
										<div class="value">
											<input id="edit-info-fatherId" class="form-control" placeholder="邀请者id"/>
										</div>
									</div>
									<div class="subitem">
										<div class="key">
											<span>余额</span>
										</div>			
										<div class="value">
											<input id="edit-info-money" class="form-control" disabled="disabled" placeholder="余额（元）"/>
										</div>
									</div>
									<div class="subitem">
										<div class="key">
											<span>邮箱</span>
										</div>			
										<div class="value">
											<input id="edit-info-emaiil" class="form-control" placeholder="用户邮箱"/>
										</div>
									</div>
									<div class="subitem">
										<div class="key">
											<span>用户生日</span>
										</div>			
										<div class="value date">
											<input id="edit-info-birthday" class="form-control date-check" placeholder="用户生日"/>
										</div>
									</div>
								</div>
								
								<div class="right">
									<div class="subitem">
										<div class="key">
											<span>性别</span>
										</div>			
										<div class="value">
											<select id="edit-info-sex" class="form-control">
												<option value="0">男</option>
												<option value="1">女</option>
											</select>
										</div>
									</div>
									<div class="subitem">
										<div class="key">
											<span>手机号</span>
										</div>			
										<div class="value">
											<input id="edit-info-phone" class="form-control" placeholder="用户手机号"/>
										</div>
									</div>
									<!--<div class="subitem">
										<div class="key">
											<span>会员有效期至</span>
										</div>			
										<div class="value">
											<input id="edit-info-vipTime" class="form-control" placeholder="会员有效期至"/>
										</div>
									</div>-->
									<div class="subitem">
										<div class="key">
											<span>押金</span>
										</div>			
										<div class="value">
											<input id="edit-info-deposit" class="form-control" disabled="disabled" placeholder="用户押金"/>
										</div>
									</div>
									<div class="subitem">
										<div class="key">
											<span>身份位</span>
										</div>			
										<div class="value">
											<select id="edit-info-identity" class="form-control">
												<option value="0">用户</option>
												<option value="1">商家</option>
												<option value="2">小二</option>
												<option value="3">员工</option>
											</select>
										</div>
									</div>
									<div class="subitem">
										<div class="key">
											<span>积分</span>
										</div>			
										<div class="value">
											<input id="edit-info-point" class="form-control" placeholder="用户积分"/>
										</div>
									</div>
								</div>
								
							</div>
						</div>
					</div>
					<div class="modal-footer" style="text-align: center;">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-info" onclick="saveEditInfo();">保存</button>
					</div>
				</div>
			</div>
		</div>


		<script src="../dist/jquery/jquery.min.js"></script>
		<script src="../dist/bootstrap/bootstrap.min.js"></script>
		<script src="../dist/adminlte/adminlte.min.js"></script>
		<script src="../dist/jquery/jquery.dataTables.js"></script>
		<script src="../dist/jquery/ui.js"></script>
		<script src="../dist/jquery/caldate/caldate.js"></script>
		<script src="../mcfish/js/mcfish.js"></script>
		<script src="../mcfish/js/user/detail.js"></script>
	</body>
</html>