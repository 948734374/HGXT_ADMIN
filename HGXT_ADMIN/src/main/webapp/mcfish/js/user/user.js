const $tools = mcfish.Tools;
const $api = mcfish.API;


$(function(){
	//获取用户列表
	getLabourList();
})

/**
 * 获取用户列表
 */
function getUserList(){
	
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
					{"data" : "head",'sClass' : "text-center col-width-80",
						"render": function ( data, type, full, meta ) {
                        	return "<img src='" + $tools.toHeadImage(data)+ "' class='userImg' />";
                  	     } 
					},
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
					{"data" : "money",'sClass' : "text-center col-width-100",
						"render": function ( data, type, full, meta ) {
							return $tools.toMoney(data);
                  	     } 
					},
					{"data" : "deposit",'sClass' : "text-center col-width-100",
						"render": function ( data, type, full, meta ) {
                        	return $tools.toMoney(data);
                  	     } 
					},
					{"data" : "point",'sClass' : "text-center col-width-80"},
					{"data" : "openid_wx",'sClass' : "text-center col-width-80",
						"render": function ( data, type, full, meta ) {
		                    return (data == null || data == "")?"未绑定":"已绑定";
		                } 
					},
					{"data" : "identity",'sClass' : "text-center col-width-80",
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
 * 删除用户
 * @param id 用户id
 * @returns
 */
function deleteUserId(id){
	parent.window.openTipsDialog("确定删除","删除后数据将不存在",function(){
		$api.asyncRequest("shareUserController/deleteUser.do","POST",{userId:id}).then(function(res){
			mizhu.toast(res.resmsg,1000);
			table.ajax.reload(null,false);
		});
	})
}


/**
 * 修改用户状态
 * @param id 用户id
 * @param status 目标状态
 * @returns
 */
function changeUserStatus(id,status){
	parent.window.openTipsDialog("提示","确定更改用户状态吗？",function(){
		$api.asyncRequest("shareUserController/updateUserStatus.do","POST",{id:id,status:status}).then(function(res){
			mizhu.toast(res.resmsg,1000);
			table.ajax.reload(null,false);
		});
	})
}


/**
 * 表格搜索
 */
function search(obj){
	if(table){
		var value = $(obj).val();
		table.search(value).draw();
	}
}
