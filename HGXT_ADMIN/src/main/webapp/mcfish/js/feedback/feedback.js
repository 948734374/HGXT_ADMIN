const $tools = mcfish.Tools
const $api = mcfish.API

$(function(){
	//获取意见反馈列表
	getFeedbackList();
})

/**
 * 获取用户列表
 */
function getFeedbackList(){
	
	var status = $("#feedbackStatus").val();
	
	var ajaxParams = {
			api: 'shareFeedbackController/getAllFeedbackList.do',
			type: 'GET',
			searching:true,
			data: {
				status:status
			}
	 }
	 var colData = [
	 				{"data" : "title",'sClass' : "text-left col-width-160"},
					{"data" : "content",'sClass' : "text-left col-min-width-200"},
					{"data" : "name",'sClass' : "text-center col-width-120"},
					{"data" : "phone",'sClass' : "text-center col-width-100"},
					{"data" : "comment",'sClass' : "text-left col-min-width-200"},
					{"data" : "create_time",'sClass' : "text-center col-width-160",
						"render": function ( data, type, full, meta ) {
		                    return mcfish.Tools.getMyDate(data,1);
		                } 
					},
					{"data" : "status",'sClass' : "text-center col-width-80",
						"render": function ( data, type, full, meta ) {
							var str = "";
							if(data== 0){
								 str="<span class='label label-warning'>待处理</span> ";
							} 
							if(data == 1) {
								str="<span class='label label-success'>已采纳</span>";
							}
							if(data == 2) {
								str="<span class='label label-danger'>已拒绝</span>";
							}
                        	return str;
                  	     } 
					},
					{"data" : "id",'sClass' : "text-center col-width-oper3",
						"render" : function(data, type, full, meta) {
								var str = "";
								if(full.status == 0){
									str += "<span class='tab_text_blue pointer' onclick=showDisagreeFeedback(" + data +",2);>拒绝</span>&nbsp;&nbsp;";
									str += "<span class='tab_text_blue pointer' onclick=agreeFeedback(" + data +",1);>采纳</span>&nbsp;&nbsp;";
									str += "<span class='tab_text_blue pointer' onclick=deleteFeed(" + data +");>删除</span>&nbsp;&nbsp;";
								}else{
									str += "<span class='tab_text_gray' >拒绝</span>&nbsp;&nbsp;";
									str += "<span class='tab_text_gray' >采纳</span>&nbsp;&nbsp;";
									str += "<span class='tab_text_blue pointer' onclick=deleteFeed(" + data +");>删除</span>&nbsp;&nbsp;";
								}
	                        	return str;
							}
					},
				]
	table = $api.getDataTable('#feedbackList',ajaxParams, colData);
}


/**
 * 打开拒绝弹窗
 * @param id 反馈id
 * @param status 目标状态
 * @returns
 */
function showDisagreeFeedback(id,status){
	$("#editInfoItemContent").val("");
	$("#comfirmEditInfo").attr("onclick","disagreeFeedback("+ id +","+ status +")");
	$("#editInfoView").modal("toggle");
}

//拒绝
function disagreeFeedback(id,status){
	var comment = $("#editInfoItemContent").val();
	$api.asyncRequest("shareFeedbackController/updateFeedStatus.do","POST",{id:id,status:status,comment:comment}).then(function(res){
		mizhu.toast(res.resmsg,1000);
		$("#editInfoView").modal("toggle");
		table.ajax.reload(null,false);
	});
}


/**
 * 采纳
 * @param id 反馈id
 * @param status 目标状态
 * @returns
 */
function agreeFeedback(id,status){
	parent.window.openTipsDialog("提示","确定要采纳吗？",function(){
		$api.asyncRequest("shareFeedbackController/updateFeedStatus.do","POST",{id:id,status:status}).then(function(res){
			mizhu.toast(res.resmsg,1000);
			table.ajax.reload(null,false);
		});
	})
}


/**
 * 删除意见反馈
 * @param id 反馈id
 * @returns
 */
function deleteFeed(id){
	parent.window.openTipsDialog("提示","确定要删除吗？",function(){
		$api.asyncRequest("shareFeedbackController/deleteFeed.do","POST",{id:id}).then(function(res){
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
