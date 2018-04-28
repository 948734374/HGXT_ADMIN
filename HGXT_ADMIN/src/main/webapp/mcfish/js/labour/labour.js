const $tools = mcfish.Tools
const $api = mcfish.API


$(function(){
	//获取劳务公司数据列表
	getLabourList();
})

/**
 * 获取劳务公司数据列表
 */
function getLabourList(){
	
	var status = $("#labourStatus").val();
	
	var ajaxParams = {
			api: 'shareLabourController/getLabour.do',
			type: 'GET',
			searching:true,
			data: {
				status	:status,
			}
	 }
	 var colData = [
					{"data" : "id",'sClass' : "text-center col-width-80"},
					{"data" : "name",'sClass' : "text-center col-width-80",
						"render": function ( data, type, full, meta ) {
                           return data;
						} 
					},
					{"data" : "logo",'sClass' : "text-center col-width-80",
						"render": function ( data, type, full, meta ) {
						   return data;
						} 
					},
					{"data" : "phone",'sClass' : "text-center col-width-80",
						"render": function ( data, type, full, meta ) {
						   return data;
						} 
					},
					{"data" : "password",'sClass' : "text-center col-width-80",
						"render": function ( data, type, full, meta ) {
						   return data;
						} 
					},
					{"data" : "prov",'sClass' : "text-center col-width-100",
						"render": function ( data, type, full, meta ) {
							return data;
						} 
					},
					{"data" : "contractor",'sClass' : "text-center col-width-100",
						"render": function ( data, type, full, meta ) {
							return data;
						} 
					},
					{"data" : "status",'sClass' : "text-center col-width-80",
						"render": function ( data, type, full, meta ) {
							return data;
						} 
					},
					{"data" : "create_time",'sClass' : "text-center col-width-80",
						"render": function ( data, type, full, meta ) {
							return data;
                  	    } 
					},
				]
	table = $api.getDataTable('#labourList',ajaxParams, colData);
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
