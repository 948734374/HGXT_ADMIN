const $tools = mcfish.Tools;
const $api = mcfish.API;

$(function(){
	//获取基础配置列表
	getBasicConfigList();
})


/******************************************************************************************
*************************           基础配置页面-start            **************************** 
******************************************************************************************/


/**
 * 获取基础配置列表
 */
function getBasicConfigList(){
	
	var ajaxParams = {
			api: 'shareSystemController/getConfigList.do',
			type: 'POST',
			searching:true,
			paging:false
	 }
	 var colData = [
	 				{"data" : "key_name",'sClass' : "text-left col-width-160"},
					{"data" : "value",'sClass' : "text-leftcol-min-width-140"},
					{"data" : "comment",'sClass' : "text-left col-min-width-140"},
					{"data" : "id",'sClass' : "text-center col-width-oper1",
						"render" : function(data, type, full, meta) {
							return "<span class='tab_text_blue pointer' onclick=showeEditBasicConfigView(" + data +");>编辑</span>";
						}
					},
				]
	basicTable = $api.getDataTable('#basicConfigList',ajaxParams, colData);
}


/**
 * 打开编辑基础配置弹窗
 * @param data 主键id
 * @returns
 */
function showeEditBasicConfigView(id){
	$("#editBasicConfigkey_name").val("");
	$("#editBasicConfigvalue").val("");
	$("#editBasicConfigcomment").val("");
	$api.asyncRequest("shareSystemController/getConfigById.do","GET",{id:id}).then(function(res){
		if(res.data){
			$("#editBasicConfigkey_name").val(res.data.key_name);
			$("#editBasicConfigvalue").val(res.data.value);
			$("#editBasicConfigcomment").val(res.data.comment);
			$("#editBasicConfigBtn").attr("onclick","comfirmEditBasicConfig("+ id +")");
			$("#editBasicConfigView").modal("toggle");
		}else{
			mizhu.toast("获取数据失败",1000);
		}
	});
}


/**
 * 保存基础配置
 * @param id
 * @returns
 */
function comfirmEditBasicConfig(id){
	var data = {
		id			:id,
		key_name	:$("#editBasicConfigkey_name").val(),
		value		:$("#editBasicConfigvalue").val(),
		comment		:$("#editBasicConfigcomment").val()
	}
	$api.asyncRequest("shareSystemController/updateSystemConfig.do","POST",data).then(function(res){
		mizhu.toast("保存成功",1000);
		basicTable.ajax.reload(null,false);
		$("#editBasicConfigView").modal("toggle");
	});
}


/******************************************************************************************
*************************           基础配置页面-end              **************************** 
******************************************************************************************/




/******************************************************************************************
*************************           充值配置页面-start            **************************** 
******************************************************************************************/
/**
 * 获取充值配置列表
 * @returns
 */
function getPayConfigList(){
	
	var ajaxParams = {
			api: 'shareSystemController/getAllChargeList.do',
			type: 'POST',
			searching:true,
			paging:false
	}
	var colData = [
		{"data" : "amount",'sClass' : "text-center col-width-160",
			"render" : function(data, type, full, meta) {
				return data/100;
			}
		},
		{"data" : "charge",'sClass' : "text-center col-min-width-140",
			"render" : function(data, type, full, meta) {
				return data/100;
			}
		},
		{"data" : "give",'sClass' : "text-center col-min-width-140",
			"render" : function(data, type, full, meta) {
				return data/100;
			}
		},
		{"data" : "comment",'sClass' : "text-center col-min-width-140"},
		{"data" : "id",'sClass' : "text-center col-width-oper1",
			"render" : function(data, type, full, meta) {
				return "<span class='tab_text_blue pointer' onclick=showePayConfigView(" + data +");>编辑</span>";
			}
		},
		]
	payTable = $api.getDataTable('#payConfigList',ajaxParams, colData);
}


/**
 * 打开编辑充值配置弹窗
 * @param data 主键id
 * @returns
 */
function showePayConfigView(id){
	$("#editPayConfigAmount").val("");
	$("#editPayConfigCharge").val("");
	$("#editPayConfigGive").val("");
	$("#editPayConfigComment").val("");
	$api.asyncRequest("shareSystemController/getChargeById.do","GET",{id:id}).then(function(res){
		if(res.data){
			$("#editPayConfigAmount").val(res.data.amount/100);
			$("#editPayConfigCharge").val(res.data.charge/100);
			$("#editPayConfigGive").val(res.data.give/100);
			$("#editPayConfigComment").val(res.data.comment);
			$("#editPayConfigBtn").attr("onclick","comfirmEditPayConfig("+ id +")");
			$("#editPayConfigView").modal("toggle");
		}else{
			mizhu.toast("获取数据失败",1000);
		}
	});
}


/**
 * 保存充值配置
 * @param id
 * @returns
 */
function comfirmEditPayConfig(id){
	var data = {
		id			:id,
		amount		:$("#editPayConfigAmount").val()*100,
		charge		:$("#editPayConfigCharge").val()*100,
		give		:$("#editPayConfigGive").val()*100,
		comment		:$("#editPayConfigComment").val()
	}
	
	if(data.charge <= 0){
		mizhu.toast("实际到账金额必须大于0元！",1000);
		return false;
	}
	
	if(data.amount == null || data.amount === ""){
		mizhu.toast("请填写充值金额",1000);
		return false;
	}
	if(data.charge == null || data.charge === ""){
		mizhu.toast("请填写实际到帐金额",1000);
		return false;
	}
	if(data.give == null || data.give === ""){
		mizhu.toast("请填写赠送金额",1000);
		return false;
	}
	
	$api.asyncRequest("shareSystemController/updateSystemCharge.do","POST",data).then(function(res){
		mizhu.toast("保存成功",1000);
		payTable.ajax.reload(null,false);
		$("#editPayConfigView").modal("toggle");
	});
}
/******************************************************************************************
*************************           充值配置页面-end              **************************** 
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
			api: 'shareSystemController/getAppVersionList.do',
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
		{"data" : "comment",'sClass' : "text-left col-min-width-100"},
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
		{"data" : "download",'sClass' : "text-left"},
		{"data" : "id",'sClass' : "text-center col-width-oper1",
			"render" : function(data, type, full, meta) {
				var str = "";
				if(full.is_debug == 0){
					str = "<span class='tab_text_blue pointer' onclick=tochangeDebug(" + data +");>正式</span>&nbsp;&nbsp;";
				}else{
					str = "<span class='tab_text_blue pointer' onclick=tochangeDebug(" + data +");>调试</span>&nbsp;&nbsp;";
				}
				str += "<span class='tab_text_blue pointer' onclick=showePayConfigView(" + data +");>固件升级</span>";
				return str;
			}
		},
	]
	versionTable = $api.getDataTable('#versionList',ajaxParams, colData);
}



/******************************************************************************************
 *************************           版本管理页面-end              **************************** 
 ******************************************************************************************/



/******************************************************************************************
 *************************           关于我们页面-start            **************************** 
 ******************************************************************************************/

/**
 *关于我们列表
 * @returns
 */
function getAboutList(){
	
	var ajaxParams = {
			api: 'shareSystemController/getAllAboutList.do',
			type: 'POST',
			searching:true
	}
	var colData = [
		{"data" : "code",'sClass' : "text-center col-min-width-100"},
		{"data" : "title",'sClass' : "text-left col-min-width-100"},
		{"data" : "content",'sClass' : "text-left col-min-width-220",
			"render" : function(data, type, full, meta) {
				var str = "";
    			data = data.replace(/<\/?.+?>/g,"");//去掉所有html标签
    			data = data.replace(/\s/g,""); //去掉所有空格
    			
				str = data.substring(0,30);
				if (data.length > 30) {
					str = str + "..."
				}
				return str;
			}
		},
		{"data" : "id",'sClass' : "text-center col-width-oper1",
			"render" : function(data, type, full, meta) {
				return "<span class='tab_text_blue pointer' onclick=openEditAbout(" + data +");>编辑</span>";
			}
		},
	]
	aboutTable = $api.getDataTable('#aboutList',ajaxParams, colData);
}

//打开新增页面
function openAddAbout(){
	window.location.href = $tools.getBasicUrl() + "shareSystemController/toAddAboutPage.do";
}


//打开编辑页面
function openEditAbout(id){
	window.location.href = $tools.getBasicUrl() + "shareSystemController/toAddAboutPage.do?id=" + id;
}

/******************************************************************************************
 *************************           关于我们页面-end              **************************** 
 ******************************************************************************************/


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