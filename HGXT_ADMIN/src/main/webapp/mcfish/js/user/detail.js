const $tools = mcfish.Tools
const $api = mcfish.API
const myurl = $tools.parseURL(window.location.href)
const uid = parseInt(myurl.params['id'])

$(function() {
	//获取用户详细信息
	getUserInfo();
})


/*************************************************************************************************************/
/******************************             基本资料页-start            ***************************************/
/*************************************************************************************************************/

/**
 * 获取用户详细信息
 * @returns
 */
function getUserInfo(){
	$("#editUserInfoBtn").show();
	$api.asyncRequest("shareUserController/getUserById.do","GET",{uid:uid}).then(function(res){
		//渲染用户信息到页面
		drawUserInfo(res.data);
	});
}


/**
 * 渲染用户信息到页面
 * @param {Object} data
 */
function drawUserInfo(data){
	$("#info-uid").html(data.id);
	
	$("#info-header").find("img").attr("src",data.head);
	
	$("#info-username").html(data.name);
	$("#user-sex").html(data.sex == 1?"女":"男");
	$("#info-phone").html(data.phone);
	$("#info-email").html(data.email);
	$("#info-father_id").html(data.father_id);
	
	switch (data.identity){
		case 0:
			$("#info-identity").html("普通用户");
			break;
		case 1:
			$("#info-identity").html("我是商家");
			break;
		case 2:
			$("#info-identity").html("我是小二");
			break;
		default:
			$("#info-identity").html("");
			break;
	}
	
	$("#info-birthday").html($tools.getMyDate(data.birthday,2));
	$("#info-openid_facebook").html(data.openid_facebook == ""?"否":"是");
	$("#info-openid_google").html(data.openid_google == ""?"否":"是");
	
	switch (data.status){
		case 0:
			$("#info-status").html("正常");
			break;
		case 1:
			$("#info-status").html("冻结");
			break;
		default:
			break;
	}
	
	$("#info-create_time").html($tools.getMyDate(data.create_time,1));
}



/**
 * 打开用户编辑弹窗
 */
function openEditUserInfoView(){
	//编辑用户信息时获取用户信息
	getNewUserInfo();
	$("#editUserInfoView").modal("toggle");
}


/**
 * 编辑用户信息时获取用户信息
 */
function getNewUserInfo(){
	$api.asyncRequest("shareUserController/getUserById.do","GET",{uid:uid}).then(function(res){
		var data = res.data;
		//渲染用户信息到页面
		$("#edit-info-userId").html(uid);
		
		$("#edit-info-header").attr("src",data.head)
		$("#edit-info-userName").val(data.name)
		$("#edit-info-status").val(data.status)
		$("#edit-info-fatherId").val(data.father_id)
		$("#edit-info-money").val($tools.toMoney(data.money))
		$("#edit-info-emaiil").val(data.email)
		$("#edit-info-birthday").val($tools.getMyDate(data.birthday,2))
		$("#edit-info-sex").val(data.sex)
		$("#edit-info-phone").val(data.phone)
		$("#edit-info-deposit").val($tools.toMoney(data.deposit))
		$("#edit-info-identity").val(data.identity)
		$("#edit-info-point").val(data.point)
		
	});
}


/**
 * 保存新的用户信息
 */
function saveEditInfo(){
	
	var head		= $("#edit-info-header").attr("src")
	var name		= $("#edit-info-userName").val()
	var status		= $("#edit-info-status").val()
	var father_id	= $("#edit-info-fatherId").val()
	//var money		= $("#edit-info-money").val()*100
	var email		= $("#edit-info-emaiil").val()
	var birthday	= $("#edit-info-birthday").val()
	var sex			= $("#edit-info-sex").val()
	var phone		= $("#edit-info-phone").val()
	//var deposit		= $("#edit-info-deposit").val()*100
	var identity	= $("#edit-info-identity").val()
	var point		= $("#edit-info-point").val()
	
	if(!$tools.isEmail(email)){
		mizhu.toast("请输入正确的邮箱",1000);
		return false;
	}
	
	if(!$tools.isTel(phone)){
		mizhu.toast("请输入正确的手机号",1000);
		return false;
	}
	
	var data = {
		id			:uid,
		head		:head,
		name		:name,
		status		:status,
		father_id	:father_id,
		email		:email,
		birthday	:birthday,
		sex			:sex,
		phone		:phone,
		identity	:identity,
		point		:point
	}
	
	$api.asyncRequest("shareUserController/updateUserInfo.do","POST", data).then(function(res){
		//获取用户详细信息
		getUserInfo();
		mizhu.toast(res.resmsg,1000);
		$("#editUserInfoView").modal("toggle");
	});
}

/*************************************************************************************************************/
/******************************             基本资料页-end          ******************************************/
/*************************************************************************************************************/



/*************************************************************************************************************/
/******************************             使用记录页-start            ***************************************/
/*************************************************************************************************************/


/**
 * 使用记录列表
 */
function getUsageRecordList(){
	
	var status = $("#userStatus").val();
	var identity = $("#userIdentity").val();
	
	var ajaxParams = {
			api: 'shareUserController/getAllUserList.do',
			type: 'GET',
			searching:true,
			data: {
				status	:status,
				identity:identity,
			}
	 }
	 var colData = [
					{"data" : "id",'sClass' : "text-center col-width-80"},
					{"data" : "name",'sClass' : "text-left col-min-width-120",
						"render": function ( data, type, full, meta ) {
                        	var href = $tools.getBasicUrl() + "shareUserController/toUserDetail.do?id=" + full.id;
                        	return "<a href='"+ href +"' target='menuFrame' class='tab_text_blue pointer'>"+ data +"</span>";
                  	     } 
					},
					{"data" : "sex",'sClass' : "text-center col-width-80",
						"render": function ( data, type, full, meta ) {
                        	return data == 0?"男":"女";
                  	     } 
					},
					{"data" : "phone",'sClass' : "text-center col-min-phone"},
					{"data" : "money",'sClass' : "text-center col-width-80"},
					{"data" : "deposit",'sClass' : "text-center col-width-80"},
					{"data" : "point",'sClass' : "text-center col-width-80"},
					{"data" : "openid_wx",'sClass' : "text-center col-width-status",
						"render": function ( data, type, full, meta ) {
		                    return (data == null || data == "")?"未绑定":"已绑定";
		                } 
					},
					{"data" : "identity",'sClass' : "text-center col-width-status",
						"render": function ( data, type, full, meta ) {
							var str = "";
							if(data==0){
								str = "用户";
							} 
							if(data == 1) {
								str = "商家";
							}
							if(data == 2){
								str = "小二";
							}
							if(data == 3){
								str = "员工";
							}
                        	return str;
                  	    } 
					},
					{"data" : "status",'sClass' : "text-center col-width-80",
						"render": function ( data, type, full, meta ) {
							var str = "";
							if(data== 0){
								 str="<span class='label label-success'>正常</span> ";
							} 
							if(data == 1) {
								str="<span class='label label-danger'>冻结</span>";
							}
                        	return str;
                  	     } 
					},
					{"data" : "id",'sClass' : "text-center col-width-oper2",
						"render" : function(data, type, full, meta) {
								var str = "";
								if(full.status == 0){
									str += "<span class='tab_text_blue pointer' onclick=changeUserStatus(" + data +",1);>冻结</span>&nbsp;&nbsp;";
								}
								if(full.status == 1){
									str += "<span class='tab_text_blue pointer' onclick=changeUserStatus(" + data +",0);>启用</span>&nbsp;&nbsp;";
								}
								str += "<span class='tab_text_blue pointer' onclick=deleteUserId(" + data +");>删除</font>&nbsp;";
	                        	return str;
							}
					},
				]
	table = $api.getDataTable('#userList',ajaxParams, colData);
}


/**
 * 表格搜索
 */
function search(flag,obj){
	var value = $(obj).val();
	switch (flag){
		case 1:
			if(table){
				table.search(value).draw();
			}
			break;
		default:
			break;
	}
}


/*************************************************************************************************************/
/******************************             使用记录页-end          ******************************************/
/*************************************************************************************************************/