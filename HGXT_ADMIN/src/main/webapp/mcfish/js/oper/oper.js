const $tools = mcfish.Tools;
const $api = mcfish.API;

$(function(){
	//获取BANNER列表
	getBannerList();
})



/******************************************************************************************
*************************           banner页面-start            **************************** 
******************************************************************************************/


/**
 * 获取BANNER列表
 */
function getBannerList(){
	
	var ajaxParams = {
			api			: 'shareOperController/getAllBanner.do',
			type		: 'POST',
			searching	:true,
			paging		:false
	 }
	 var colData = [
	 				{"data" : "image",'sClass' : "text-center col-width-340",
	 					"render" : function(data, type, full, meta) {
	 						if(full.url != null && full.url != ""){
	 							return "<a href='" + full.url + "' target='blank'><img src='"+ data +"' class='bannerImg' /></a>";
	 						}
							return "<img src='"+ data +"' class='bannerImg' />";
						}
	 				},
	 				{"data" : "title",'sClass' : "text-left"},
	 				{"data" : "pos",'sClass' : "text-center col-width-80",
	 					"render" : function(data, type, full, meta) {
							switch (data){
								case 0:
									return "首页";
								default:
									return "菜单栏";
							}
						}
	 				},
					{"data" : "begin",'sClass' : "text-center col-width-160",
						"render": function ( data, type, full, meta ) {
		                    return mcfish.Tools.getMyDate(data,1);
		                }
					},
					{"data" : "end",'sClass' : "text-center col-width-160",
						"render": function ( data, type, full, meta ) {
		                    return mcfish.Tools.getMyDate(data,1);
		                }
					},
					{"data" : "status",'sClass' : "text-center col-width-80",
	 					"render" : function(data, type, full, meta) {
							var str = "";
							if(data== 0){
								 str="<span class='label label-success'>在线</span> ";
							} 
							if(data == 1) {
								str="<span class='label label-danger'>下线</span>";
							}
                        	return str;
						}
	 				},
					{"data" : "id",'sClass' : "text-center col-width-oper3",
						"render" : function(data, type, full, meta) {
							var str = "<span class='tab_text_blue pointer' onclick=showeEditBannerView(" + data +");>编辑</span>&nbsp;&nbsp;";
							if(full.status == 0){
								str += "<span class='tab_text_blue pointer' onclick=updateBannerStatus(" + data +",1);>下线</span>&nbsp;&nbsp;";
							}else{
								str += "<span class='tab_text_blue pointer' onclick=updateBannerStatus(" + data +",0);>上线</span>&nbsp;&nbsp;";
							}
							str += "<span class='tab_text_blue pointer' onclick=delBanner(" + data +");>删除</span>"
							return str;
						}
					},
				]
	bannerTable = $api.getDataTable('#bannerList',ajaxParams, colData);
}



/**
 * 删除banner
 * @param {Object} id
 */
function delBanner(id){
	parent.window.openTipsDialog("确定删除","删除后数据将不存在",function(){
		$api.asyncRequest("shareOperController/deleteBanner.do","POST",{id:id}).then(function(res){
			mizhu.toast(res.resmsg,1000);
			bannerTable.ajax.reload(null,false);
		});
	})
}


/**
 * 修改banner状态
 * @param {Object} id banner ID
 * @param {Object} status 目标状态
 */
function updateBannerStatus(id, status){
	parent.window.openTipsDialog("提示","确定修改BANNER状态",function(){
		$api.asyncRequest("shareOperController/updateBanner.do","POST",{id:id,status:status}).then(function(res){
			mizhu.toast(res.resmsg,1000);
			bannerTable.ajax.reload(null,false);
		});
	})
}



/**
 * 打开添加BANNER弹窗
 */
function openAddBannerView(){
	
	//清除BANNER弹窗中的历史数据
	clearBannerView();
	$("#bannerBoxTitle").html("添加BANNER");
	$("#addBannerBtn").attr("onclick","comfirmAddBanner()");
	$("#addBannerView").modal("toggle");
}


/**
 * 打开编辑BANNER弹窗
 * @param {Object} id
 */
function showeEditBannerView(id){
	//清除BANNER弹窗中的历史数据
	clearBannerView();
	$("#bannerBoxTitle").html("编辑BANNER");
	$api.asyncRequest("shareOperController/getBanner.do","POST",{id:id}).then(function(res){
		
		var data = res.data;
		
		$("#addBannerTitle").val(data.title);
		$("#addBannerPos").val(data.pos);
		$tools.setImage("addBannerImg",data.image);
		$("#addBannerUrl").val(data.url);
		$("#addBannerEndData").val(data.data);
		$("#addBannerStartTime").val($tools.getMyDate(data.begin,1));
		$("#addBannerEndTime").val($tools.getMyDate(data.end,1));
		
		$("#addBannerBtn").attr("onclick","comfirmAddBanner("+ id +")");
		$("#addBannerView").modal("toggle");
	});
}


/**
 * 清除BANNER弹窗中的历史数据
 */
function clearBannerView(){
	$("#addBannerTitle").val("");
	$("#addBannerPos").val(0);
	$tools.setImage("addBannerImg",null);
	$("#addBannerUrl").val("");
	$("#addBannerEndData").val("");
	$("#addBannerStartTime").val("");
	$("#addBannerEndTime").val("");
}


/**
 * 保存BANNER
 * @param {Object} id
 */
function comfirmAddBanner(id){
	
	var title		= $("#addBannerTitle").val();
	var pos 		= $("#addBannerPos").val();
	var image		= $("#addBannerImg").attr("src");
	var url			= $("#addBannerUrl").val();
	var diyData		= $("#addBannerEndData").val();
	var startTime	= $("#addBannerStartTime").val();
	var endTime		= $("#addBannerEndTime").val();
	
	if(title == ""){
		mizhu.toast("请输入标题",1000);
		return false;
	}
	
	if(title.length > 20){
		mizhu.toast("标题过长，请限制在20个字符以内",1000);
		return false;
	}
	
	if(image == ""){
		mizhu.toast("请上传一张图片",1000);
		return false;
	}
	
	if(startTime == ""){
		mizhu.toast("输入开始时间",1000);
		return false;
	}
	
	if(endTime == ""){
		mizhu.toast("情输入结束时间",1000);
		return false;
	}
	
	
	var data = {
		title		: title,
		pos			: pos,
		image		: image,
		url			: url,
		diyData		: diyData,
		startTime	: startTime,
		endTime		: endTime 
	}
	
	var url = "shareOperController/addBanner.do";
	
	if(id){
		data.id = id;
		url 	= "shareOperController/updateBanner.do";
	}
	
	$api.asyncRequest(url,"POST",data).then(function(res){
		$("#addBannerView").modal("toggle");
		mizhu.toast(res.resmsg,1000);
		bannerTable.ajax.reload(null,false);
	});
}

/******************************************************************************************
*************************           banner页面-end              **************************** 
******************************************************************************************/




/******************************************************************************************
*************************           引导页面-start            **************************** 
******************************************************************************************/

/**
 * 获取所有引导页
 */
function getAllGuide(){
	$("#guidelist").empty();
	$api.asyncRequest("shareOperController/getAllGuide.do","GET",{}).then(function(res){
		$("#guidelist").empty();
		var data = res.data;
		var str = "";
		for(var i = 0 ; i< data.length ; i++){
			str += '<li>';
			if(data[i].status == 0){
				str += '<img src="'+ data[i].image +'">' +
						'<div class="btns">' +
							'<button class="btn btn-info" type="button" onclick="showGuide('+ data[i].id+',1)" >隐藏</button>';
			}else{
				str += '<img src="../dist/mcfish/image/whiteGuide.png">' +
						'<div class="btns">' +
							'<button class="btn btn-info" type="button" onclick="showGuide('+ data[i].id+',0)" >显示</button>';
			}
				str += 	'<button class="btn btn-info" type="button" onclick="openEditGuideView('+ data[i].id+')">编辑</button>' +
							'<button class="btn btn-info" type="button" onclick="deleteGuide('+ data[i].id+')" >删除</button>'+
						'</div>' +
					'</li>';	
							
		}
		
		$("#guidelist").append(str);
	});
}



/**
 * 删除引导页
 * @param {Object} id
 */
function deleteGuide(id){
	parent.window.openTipsDialog("提示","确定删除吗？",function(){
		$api.asyncRequest("shareOperController/deleteGuide.do","POST",{id:id}).then(function(res){
			mizhu.toast(res.resmsg,1000);
			getAllGuide();
		});
	})
}



/**
 * 显示和隐藏 引导页
 * @param {Object} id id
 * @param {Object} status 目标状态
 */
function showGuide(id, status){
	parent.window.openTipsDialog("提示","确定要进行操作吗？",function(){
		$api.asyncRequest("shareOperController/updateGuide.do","POST",{id:id,status:status}).then(function(res){
			mizhu.toast(res.resmsg,1000);
			getAllGuide();
		});
	})
}


/**
 * 打开编辑引导页弹窗
 */
function openEditGuideView(id){
	
	$tools.setImage("addGuideImg",null);
	$("#addGuideStatus").val(0);
	$("#addGuideComment").val("");
	
	$api.asyncRequest("shareOperController/getGuide.do","POST",{id:id}).then(function(res){
		$tools.setImage("addGuideImg",res.data.image);
		$("#addGuideStatus").val(res.data.status);
		$("#addGuideComment").val(res.data.comment);
		
		$("#addGuideBtn").attr("onclick","comfirmEditGuide("+ id +");");
		$("#editGuideView").modal("toggle");
	});
}


/**
 * 保存引导页
 * @param id
 * @returns
 */
function comfirmEditGuide(id){
	
	var data = {
		id			:id,
		status		:$("#addGuideStatus").val(),
		comment		:$("#addGuideComment").val()
	}
	
	$api.asyncRequest("shareOperController/updateGuide.do","POST",data).then(function(res){
		getAllGuide();
		mizhu.toast(res.resmsg,1000);
		$("#editGuideView").modal("toggle");
	});
}

/******************************************************************************************
*************************           引导页面-end              **************************** 
******************************************************************************************/




/******************************************************************************************
 *************************           版本管理页面-start            **************************** 
 ******************************************************************************************/
/**
 * 获取版本列表
 * @returns
 */
function getVersionList(){
	
	var ajaxParams = {
			api: 'shareOperController/getAppVersionList.do',
			type: 'POST',
			searching:true
	}
	var colData = [
		{"data" : "type",'sClass' : "text-center col-width-120",
			"render" : function(data, type, full, meta) {
				var str = "";
				switch (data) {
				case 0:
					str = "Android";
					break;
				case 1:
					str = "IOS";
					break;
				default:
					break;
				}
				return str;
			}
		},
		{"data" : "version",'sClass' : "text-center col-width-120"},
		{"data" : "is_debug",'sClass' : "text-center col-width-80",
			"render" : function(data, type, full, meta) {
				return data == 1?"调试":"正式";
			}
		},
		{"data" : "is_force",'sClass' : "text-center col-width-100",
			"render" : function(data, type, full, meta) {
				return data == 1?"是":"否";
			}
		},
		{"data" : "comment",'sClass' : "text-left col-min-width-100"},
		{"data" : "download",'sClass' : "text-left"},
		{"data" : "id",'sClass' : "text-center col-width-oper1",
			"render" : function(data, type, full, meta) {
				var str = "";
				if(full.is_debug == 0){
					str = "<span class='tab_text_blue pointer' onclick=tochangeDebug(" + data +",1);>调试</span>&nbsp;&nbsp;";
				}else{
					str = "<span class='tab_text_blue pointer' onclick=tochangeDebug(" + data +",0);>正式</span>&nbsp;&nbsp;";
				}
				str += "<span class='tab_text_blue pointer' onclick=openActionAppView(" + data +");>编辑</span>";
				return str;
			}
		},
	]
	versionTable = $api.getDataTable('#versionList',ajaxParams, colData);
}


/**
 * 打开添加/编辑 版本
 */
function openActionAppView(id){
	
	//清除历史数据
	clearActionAppView();
	
	if(id){
		$("#appBoxTitle").html("编辑版本");
		$api.asyncRequest("shareOperController/getAppVersion.do","POST",{id:id}).then(function(res){
			
			var data = res.data;
			
			$("#actionAppType").val(data.type);
			$("#actionAppisDebug").val(data.is_debug);
			$("#actionAppisForce").val(data.is_force);
			$("#actionAppVersion").val(data.version);
			$("#actionAppComment").val(data.comment);
			$("#actionAppDownload").val(data.download);
			
			$("#actionAppBtn").attr("onclick","saveAppVersion("+ id +")");
			$("#actionAppView").modal("toggle");
		});
	}else{
		
		$("#actionAppBtn").attr("onclick","saveAppVersion()");
		$("#appBoxTitle").html("添加版本");
		$("#actionAppView").modal("toggle");
	}
}


/**
 * 保存版本信息
 * @param {Object} id
 */
function saveAppVersion(id){
	
	var type 	 = $("#actionAppType").val();
	var is_debug = $("#actionAppisDebug").val();
	var is_force = $("#actionAppisForce").val();
	var version  = $("#actionAppVersion").val();
	var comment  = $("#actionAppComment").val();
	var download = $("#actionAppDownload").val();
	
	var data = {
		type	: type,
		is_debug: is_debug,
		is_force: is_force,
		version	: version,
		comment	: comment,
		download: download
	}
	
	if(version == "" || version == null){
		mizhu.toast("请输入版本信息",1000)
		return false;
	}
	
	var url = "shareOperController/addAppVersion.do";
	if(id){
		data.id = id;
		url = "shareOperController/updateAppVersion.do"
	}
	
	$api.asyncRequest(url, "POST", data).then(function(res){
		versionTable.ajax.reload(null,false);
		mizhu.toast(res.resmsg,1000);
		$("#actionAppView").modal("toggle");
	});
}


/**
 * 清除历史数据
 */
function clearActionAppView(){
	
	$("#actionAppType").val(0);
	$("#actionAppisDebug").val(0);
	$("#actionAppisForce").val(0);
	$("#actionAppVersion").val("");
	$("#actionAppComment").val("");
	$("#actionAppDownload").val("");
	$("#appBoxTitle").html("");
	$("#actionAppBtn").attr("onclick","");
}


/**
 * 修改APP的调试状态
 * @param {Object} id id
 * @param {Object} is_debug 目标状态
 */
function tochangeDebug(id,is_debug){
	parent.window.openTipsDialog("提示","确定要进行操作吗？",function(){
		$api.asyncRequest("shareOperController/updateAppVersion.do","POST",{id:id,is_debug:is_debug}).then(function(res){
			mizhu.toast(res.resmsg,1000);
			versionTable.ajax.reload(null,false);
		});
	})
}

/******************************************************************************************
 *************************           版本管理页面-end              **************************** 
 ******************************************************************************************/


//日期插件相关
$.datetimepicker.setLocale('zh');
$("#addBannerStartTime,#addBannerEndTime").datetimepicker({
    	inline:false,	//true-显示，false - 隐藏
        format: 'Y-m-d H:i:s',
        timepicker: true, //true-显示时间，false - 不显示时间
        height : "50px"
});


/**
 * 表格搜索
 */
function search(flag,obj){
	var value = $(obj).val();
	switch (flag){
		case 1:
			if(basicTable){
				basicTable.search(value).draw();
			}
			break;
		case 2:
			if(aboutTable){
				aboutTable.search(value).draw();
			}
			break;
		default:
			break;
	}
}