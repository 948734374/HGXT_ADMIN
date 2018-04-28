const $tools = mcfish.Tools;
const $api = mcfish.API;

$(function(){
	//获取优惠券列表
	getCouponList();
})


/**
 * 获取优惠券列表
 */
function getCouponList(){
	
	var status = $("#couponStatus").val();
	
	var ajaxParams = {
			api			: 'shareCouponController/getAllCoupon.do',
			type		: 'GET',
			searching	: true,
			data		: {
							status :status
						}
	 }
	 var colData = [
	 				{"data" : "image",'sClass' : "text-center col-width-120",
	 					"render" : function(data, type, full, meta) {
	 						if(full.url != null && full.url != ""){
	 							return "<a href='" + full.url + "' target='blank'><img src='"+ data +"' class='couponImg' /></a>";
	 						}
							return "<img src='"+ data +"' class='couponImg' />";
						}
	 				},
	 				{"data" : "logo",'sClass' : "text-center col-width-100",
	 					"render" : function(data, type, full, meta) {
							return "<img src='"+ data +"' class='couponLogo' />";
						}
	 				},
	 				{"data" : "code",'sClass' : "text-center col-width-120"},
	 				{"data" : "brief",'sClass' : "text-left"},
	 				{"data" : "number",'sClass' : "text-center col-width-80"},
	 				{"data" : "taked",'sClass' : "text-center col-width-80"},
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
							var str = "<span class='tab_text_blue pointer' onclick=openActionCouponView(" + data +");>编辑</span>&nbsp;&nbsp;";
							if(full.status == 0){
								str += "<span class='tab_text_blue pointer' onclick=updateCouponStatus(" + data +",1);>下线</span>&nbsp;&nbsp;";
							}else{
								str += "<span class='tab_text_blue pointer' onclick=updateCouponStatus(" + data +",0);>上线</span>&nbsp;&nbsp;";
							}
							str += "<span class='tab_text_blue pointer' onclick=delCoupon(" + data +");>删除</span>"
							return str;
						}
					},
				]
	couponTable = $api.getDataTable('#couponList',ajaxParams, colData);
}



/**
 * 删除coupon
 * @param {Object} id
 */
function delCoupon(id){
	parent.window.openTipsDialog("确定删除","删除后数据将不存在",function(){
		$api.asyncRequest("shareCouponController/deletCoupon.do","POST",{id:id}).then(function(res){
			mizhu.toast(res.resmsg,1000);
			couponTable.ajax.reload(null,false);
		});
	})
}


/**
 * 修改coupon状态
 * @param {Object} id banner ID
 * @param {Object} status 目标状态
 */
function updateCouponStatus(id, status){
	parent.window.openTipsDialog("提示","确定修改优惠券状态",function(){
		$api.asyncRequest("shareCouponController/updateCoupon.do","POST",{id:id,status:status}).then(function(res){
			mizhu.toast(res.resmsg,1000);
			couponTable.ajax.reload(null,false);
		});
	})
}



/**
 * 打开添加编辑/添加弹窗
 * @param {Object} id 存在编辑、不存在新增
 */
function openActionCouponView(id){
	
	//清除Coupon弹窗中的历史数据
	clearCouponView();
	
	if(id){
		$("#couponBoxTitle").html("编辑优惠券");
		$("#actionCouponBtn").attr("onclick","comfirmSaveCoupon("+ id +")");
		
		$api.asyncRequest("shareCouponController/getCouponInfo.do","POST",{id:id}).then(function(res){
			var data = res.data;
			
			$tools.setImage("actionCouponImg",data.image);
			$("#actionCouponBrief").val(data.brief);
			$tools.setImage("actionCouponLogoImg",data.logo);
			$("#actionCouponUrl").val(data.url);
			$("#actionCouponNumber").val(data.number);
			$("#actionCouponBegin").val($tools.getMyDate(data.begin,1));
			$("#actionCouponEnd").val($tools.getMyDate(data.end,1));
			$("#actionCouponStatus").val(data.status);
			
			$("#actionCouponView").modal("toggle");
		});
		
	}else{
		$("#couponBoxTitle").html("新增优惠券");
		$("#actionCouponBtn").attr("onclick","comfirmSaveCoupon()");
		$("#actionCouponView").modal("toggle");
	}
}


/**
 * 清除Coupon弹窗中的历史数据
 */
function clearCouponView(){
	$tools.setImage("actionCouponImg",null);
	$("#actionCouponBrief").val("");
	$tools.setImage("actionCouponLogoImg",null);
	$("#actionCouponUrl").val("");
	$("#actionCouponNumber").val("");
	$("#actionCouponBegin").val("");
	$("#actionCouponEnd").val("");
	$("#actionCouponStatus").val(0);
}


/**
 * 保存优惠券
 * @param {Object} id
 */
function comfirmSaveCoupon(id){
	
	var image		= $("#actionCouponImg").attr("src");
	var brief		= $("#actionCouponBrief").val();
	var logo		= $("#actionCouponLogoImg").attr("src");
	var url			= $("#actionCouponUrl").val();
	var number 		= $("#actionCouponNumber").val();
	var begin		= $("#actionCouponBegin").val();
	var end			= $("#actionCouponEnd").val();
	var status		= $("#actionCouponStatus").val();
	
	if(image == ""){
		mizhu.toast("请上传优惠券图片",1000);
		return false;
	}
	if(logo == ""){
		mizhu.toast("请上传优惠券LOGO",1000);
		return false;
	}
	
	if(brief == ""){
		mizhu.toast("请对优惠券进行摘要说明",1000);
		return false;
	}
	
	
	if(number == ""){
		mizhu.toast("请填写优惠券数量",1000);
		return false;
	}
	
	if(begin == ""){
		mizhu.toast("输入开始时间",1000);
		return false;
	}
	
	if(end == ""){
		mizhu.toast("情输入结束时间",1000);
		return false;
	}
	
	
	var data = {
		image	: image,
		brief	: brief,
		logo	: logo,
		url		: url,
		number	: number,
		begin	: begin,
		end		: end,
		status	: status 
	}
	
	var url = "shareCouponController/addCoupon.do";
	
	if(id){
		data.id = id;
		url 	= "shareCouponController/updateCoupon.do";
	}
	
	$api.asyncRequest(url,"POST",data).then(function(res){
		$("#actionCouponView").modal("toggle");
		mizhu.toast(res.resmsg,1000);
		couponTable.ajax.reload(null,false);
	});
}



//日期插件相关
$.datetimepicker.setLocale('zh');
$("#actionCouponBegin,#actionCouponEnd").datetimepicker({
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
			if(couponTable){
				couponTable.search(value).draw();
			}
			break;
		default:
			break;
	}
}