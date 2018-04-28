const $tools = mcfish.Tools
const $api = mcfish.API

$(function(){
	//获取消息列表数据
	getMessageList();
})

/**
 * 获取消息列表数据
 */
function getMessageList(){
	
	var status = $("#messageStatus").val();
	
	var ajaxParams = {
			api: 'shareMessageController/getAllMessage.do',
			type: 'GET',
			searching:true,
			data: {
				status:status
			}
	 }
	 var colData = [
	 				{"data" : "title",'sClass' : "text-left col-width-160",
	 					"render":function(data, type, full){
	 						return "<span class='tab_text_blue pointer' onclick=showEditMessage(" + full.id +");>" + data + "</span>";
	 					}
	 				},
					{"data" : "content",'sClass' : "text-left col-min-width-200",
						"render" : function(data, type, full, meta) {
							var str = "";
			    			data = data.replace(/<\/?.+?>/g,"");//去掉所有html标签
			    			data = data.replace(/\s/g,""); //去掉所有空格
			    			
							str = data.substring(0,60);
							if (data.length > 60) {
								str = str + "..."
							}
							return str;
						}
					},
					{"data" : "from",'sClass' : "text-center col-width-80",
						"render": function ( data, type, full, meta ) {
                        	return data == 0 ? "系统消息":"其它消息";
                  	     } 
					},
					{"data" : "status",'sClass' : "text-center col-width-80",
						"render": function ( data, type, full, meta ) {
							var str = "";
							if(data== 0){
								 str="<span class='label label-danger'>未发送</span> ";
							} 
							if(data == 1) {
								str="<span class='label label-success'>已发送</span>";
							}
                        	return str;
                  	     } 
					},
					{"data" : "create_time",'sClass' : "text-center col-width-160",
						"render": function ( data, type, full, meta ) {
		                    return mcfish.Tools.getMyDate(data,1);
		                } 
					},
					{"data" : "id",'sClass' : "text-center col-width-oper1",
						"render" : function(data, type, full, meta) {
								var str = "<span class='tab_text_blue pointer' onclick=openEditMessage(" + data +");>编辑</span>&nbsp;&nbsp;"
								return str += "<span class='tab_text_blue pointer' onclick=deleteMessage(" + data +");>删除</span>";
							}
					},
				]
	table = $api.getDataTable('#messageList',ajaxParams, colData);
}



/**
 * 删除
 * @param id 反馈id
 * @returns
 */
function deleteMessage(id){
	parent.window.openTipsDialog("提示","确定要删除吗？",function(){
		$api.asyncRequest("shareMessageController/deleteMessage.do","POST",{id:id}).then(function(res){
			mizhu.toast(res.resmsg,1000);
			table.ajax.reload(null,false);
		});
	})
}


/**
 * 打开新增项目弹窗
 */
function openAddMessage(){
	
	//清除原有数据
	clearActionMesView();
	
	$("#messageBoxTitle").html("新建消息");
	$("#actionMsgBtn").attr("onclick","addMessage()");
	$("#actionMsgBtn").html("保存");
	$("#actionMessageView").modal("toggle");
}


/**
 * 打开编辑弹窗
 * @param {Object} id
 */
function openEditMessage(id){
	
	//清除原有数据
	clearActionMesView();
	
	$api.asyncRequest("shareMessageController/getMessage.do","POST",{id:id}).then(function(res){
		var data = res.data;
		
		$("#actionMsgTitle").val(data.title);
		$("#actionMsgImg").attr("src",data.image);
		$("#actionMsgContent").val(data.content);
		
		
		$("#messageBoxTitle").html("编辑消息");
		$("#actionMsgBtn").attr("onclick","addMessage("+id+")");
		$("#actionMsgBtn").html("保存");
		$("#actionMessageView").modal("toggle");
	});
	
}


/**
 * 展示消息详情
 * @param {Object} id
 */
function showEditMessage(id){
	
	//清除原有数据
	$("#showMsgTitle").val("");
	$tools.setImage("showMsgImg","");
	$("#showMsgContent").val("");
	
	$api.asyncRequest("shareMessageController/getMessage.do","POST",{id:id}).then(function(res){
		var data = res.data;
		
		$("#showMsgTitle").val(data.title);
		$tools.setImage("showMsgImg",data.image);
		$("#showMsgContent").val(data.content);
		
		$("#msgInfoView").modal("toggle");
	});
}

/**
 * 清除数据
 */
function clearActionMesView(){
	$("#messageBoxTitle").html("");
	$("#actionMsgTitle").val("");
	$tools.setImage("actionMsgImg",null);
	$("#actionMsgContent").val("");
}

/**
 * 保存消息推送
 * @param {Object} id
 */
function addMessage(id){
	
	var title = $("#actionMsgTitle").val();
	var image = $("#actionMsgImg").attr("src");
	var content = $("#actionMsgContent").val();
	
	var data = {
		title	: title,
		image	: image,
		content	: content
	}
	
	var url = "shareMessageController/addMessage.do";
	
	if(id){
		data.id = id;
		url = "shareMessageController/updatMessage.do";
	}
	
	$api.asyncRequest(url,"POST",data).then(function(res){
		$("#actionMessageView").modal("toggle");
		mizhu.toast(res.resmsg,1000);
		table.ajax.reload(null,false);
	});
	
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
