const $tools = mcfish.Tools
const $api = mcfish.API


$(function () {
	
	//获取管理员列表
    getAdminList();
    
})


/**
 * 获取用户列表
 */
function getAdminList() {

    var status = $("#adminStatus").val();

    var ajaxParams = {
        api			: 'shareAdminController/getAdminList.do',
        type		: 'GET',
        searching	: true,
        paging		: false,
        data: {
	            status: status,
	        }
    }
    
    var colData = [
        {"data": "account", 'sClass': "text-left col-width-160"},
        {"data": "name", 'sClass': "text-left col-width-140"},
        {"data": "phone", 'sClass': "text-center col-width-140"},
        {"data": "comment", 'sClass': "text-left col-min-width-140"},
        {"data": "type", 'sClass': "text-center col-width-120",
            "render": function (data, type, full, meta) {
                return data == 1 ? "超级管理员":"普通管理";
            }
        },
        {"data": "status", 'sClass': "text-center col-width-100",
            "render": function (data, type, full, meta) {
                var str = "<span class='label label-success' >正常</span> ";
                if (data == 1) {
                    str = "<span class='label label-danger'>冻结</span>";
                }
                return str;
            }
        },
        {"data": "id", 'sClass': "text-center col-width-admin",
            "render": function (data, type, full, meta) {
                var str = "";
                str += '<div class="btn-group">';
                if (full.status === 1) {
                    str += '<button type="button" class="btn btn-sm btn-default" disabled="disabled">权限配置</button>';
                    str += '<button type="button" class="btn btn-sm btn-default" disabled="disabled">编辑</button>';
                    str += '<button type="button" class="btn btn-sm btn-default" data-toggle="dropdown" aria-expanded="false" disabled="disabled" style="height:30px"><span class="caret"></span></button>';
                    str += '<button type="button" class="btn btn-sm btn-default" onclick=updateStatus(' + data + ',0);>启用</button>';
                } else if (full.status === 0) {
                    str += '<button type="button" class="btn btn-sm btn-default" onclick=editAuthConfig(' + data + ');>权限配置</button>';
                    str += '<button type="button" class="btn btn-sm btn-default" onclick=openEditAdminView(' + data + ');>编辑</button>';
                    str += '<button type="button" class="btn btn-sm btn-default" data-toggle="dropdown" aria-expanded="false" style="height:30px"><span class="caret"></span></button>';
                    str += '<ul class="dropdown-menu" role="menu">';
                    str += '<li><a href="javascript:;" onclick=resetPwd(' + data + ');>重置密码</a></li>'
                    str += '</ul>'
                    str += '<button type="button" class="btn btn-sm btn-default" onclick=updateStatus(' + data + ',1);>停用</button>';
                }
                str += '<button type="button" class="btn btn-sm btn-default btn-danger" onclick=deleteAdmin(' + full.role_id + ');>删除</button>';
                str += '</div>';
                
                if (full.type == 1) {
                    str = "超管无法操作";
                }
                return str;
            }
        }
    ]
    
    table = $api.getDataTable('#adminList', ajaxParams, colData);
}


/**
 * 打开添加管理员弹窗
 */
function openAddAdminView() {
	
	$("#add-account").removeAttr("disabled");
	$("#info-title-o").show();
	$("#info-title-t").show();
	
	$("#addAdminViewTitle").html("新增管理员");
	
	$("#add-username").val("");
	$("#add-account").val("");
	$("#add-phone").val("");
	$("#add-comment").val("");
	
	$("#saveAddAdminBtn").attr("onclick","saveAddAdmin()");
    $("#addAdminView").modal("toggle");
}


/**
 * 打开编辑管理员弹窗
 * @param {Object} id
 */
function openEditAdminView(id){
	
	$api.asyncRequest("shareAdminController/getAdminById.do", "GET", {id:id}).then(function (res) {
		
		$("#add-account").attr("disabled","disabled");
		$("#info-title-o").hide();
		$("#info-title-t").hide();
		
		$("#addAdminViewTitle").html("编辑管理员");
		$("#add-username").val(res.data.name);
		$("#add-account").val(res.data.account);
		$("#add-phone").val(res.data.phone);
		$("#add-comment").val(res.data.comment);
		
		$("#saveAddAdminBtn").attr("onclick","saveAddAdmin("+id+")");
		$("#addAdminView").modal("toggle");
		
	})
}


/**
 * 保存管理员信息
 */
function saveAddAdmin(id) {
	
	var username	= $("#add-username").val();
    var account		= $("#add-account").val();
    var phone		= $("#add-phone").val();
    var comment 	= $("#add-comment").val();
    
    if(username == null){
    	mizhu.toast("请填写用户名称",1000);
    	return false;
    }
    if(account == null){
    	mizhu.toast("请填写账号",1000);
    	return false;
    }
    if(phone == null){
    	mizhu.toast("请填写手机号",1000);
    	return false;
    }
    if(comment == null){
    	mizhu.toast("请填写备注",1000);
    	return false;
    }
    
    if(!$tools.isTel(phone)){
    	mizhu.toast("请输入正确的手机号码",1000);
    	return false;
    }
    
    var data = {
    	username:username,
    	account	:account,
    	phone	:phone,
    	comment	:comment
    }
    
    var url = "shareAdminController/addAdmin.do";
    if(id){
    	url = "shareAdminController/updateById.do";
    	data.id = id;
    }
    
    $api.asyncRequest(url, "POST", data).then(function (res) {
    		mizhu.toast(res.resmsg,1000)
            table.ajax.reload(null,false);
            $("#addAdminView").modal("toggle");
    });
}


/**
 * 删除管理员
 * 因数据库设置，当前输出role表
 * @param {Object} id role_id
 */
function deleteAdmin(id) {
    parent.window.openTipsDialog("确定删除", "删除后数据将不存在", function () {
        $api.asyncRequest("shareAdminController/deleteAdmin.do", "POST", {id: id}).then(function (res) {
        	mizhu.toast(res.resmsg,1000)
            table.ajax.reload(null,false);
        });
    })
}


/**
 * 重置密码
 */
function resetPwd(id) {
    parent.window.openTipsDialog("确定充值为初始密码", "123456", function () {
        $api.asyncRequest("shareAdminController/updatePwd.do", "POST", {id: id}).then(function (res) {
        	mizhu.toast(res.resmsg,1000)
            table.ajax.reload(null,false);
        });
    })
}


/**
 * 停用/启用
 * @param {Object} id 管理员id
 * @param {Object} status 目标状态  0-启用 1-停用
 */
function updateStatus(id, status){
	
	var info = "停用后不可登录";
	if(status == 0){
		info = "确定启用？";	
	}
	
	 parent.window.openTipsDialog("提示", info, function () {
        $api.asyncRequest("shareAdminController/updateStatus.do", "POST", {id:id,status:status}).then(function (res) {
        	mizhu.toast(res.resmsg,1000)
            table.ajax.reload(null,false);
        });
    })
}


/**
 * 打开修改admin权限弹窗
 * @param id
 */
//TODO 权限
function editAuthConfig(id) {
	$("input[name='read']").prop("checked",false);
	$("input[name='write']").prop("checked",false);
    
    $api.asyncRequest("shareAdminController/getAdminById.do","GET",{id:id}).then(function(result){
        if (result.data.views === "all") {
            $("input[name='read']").prop("checked", true);
        }else if(result.data.views == null || result.data.views == ""){
        	 $("input[name='read']").prop("checked", false);
        } else {
            var views = result.data.views;
            //拆分为字符串数组
            var viewArray = views.split(",");
            //获得所有的复选框对象
            var readBoxAll = $("input[name='read']");
            for (var i = 0; i < viewArray.length; i++) {
                //遍历所有复选框对象的value属性，然后，用array[i]和他们匹配，如果有，则说明他应被选中
                $.each(readBoxAll, function (j, checkbox) {
                    //获取复选框的value属性
                    var checkValue = $(checkbox).val();
                    if (viewArray[i] === checkValue) {
                        $(checkbox).prop("checked", true);
                    }
                })
            }
        }
        if (result.data.edits === "all") {
            $("input[name='write']").prop("checked", true);
        }else if(result.data.edits == null || result.data.edits == ""){
        	 $("input[name='write']").prop("checked", false);
        } else {
            var edits = result.data.edits;
            var editArray = edits.split(",");
            var writeBoxAll = $("input[name='write']");
            for (var i = 0; i < editArray.length; i++) {
                $.each(writeBoxAll, function (j, checkbox) {
                    var checkValue = $(checkbox).val();
                    if (editArray[i] == checkValue) {
                        $(checkbox).prop("checked", true);
                    }
                })
            }
        }
        
        $("#confirmEditAuthBtn").attr("onclick","confirmEditAuth("+id+")");
        $("#authModal").modal("toggle");
    });

}


//保存账号权限
function confirmEditAuth(id) {
    var reads = "";
    var writes = "";
    $("input:checkbox[name='read']:checked").each(function() {
        reads += $(this).val() + ",";
    });
    reads = reads.substring(0, reads.lastIndexOf(','));
    
    $("input:checkbox[name='write']:checked").each(function() {
        writes += $(this).val() + ",";
    });
    writes = writes.substring(0, writes.lastIndexOf(','));
    
    $api.asyncRequest("shareAdminController/updateAuth.do", "POST", {id:id,views:reads,edits:writes}).then(function (res) {
    	$("#authModal").modal("toggle");
        mizhu.toast(res.resmsg,1000)
        table.ajax.reload(null,false);
    });
}


//读--全选/全部选
function checkReadAll(obj) {
    var isChecked = obj.checked;
    if (isChecked) {
        $("input[name='read']").prop("checked",true);
    } else {
        $("input[name='read']").prop("checked",false);
    }
}


//写--全选/全部选
function checkWriteAll(obj) {
    var isChecked = obj.checked;
    if (isChecked) {
        $("input[name='write']").prop("checked",true);
    } else {
        $("input[name='write']").prop("checked",false);
    }
}


/**
 * 表格搜索
 */
function search(obj) {
    if (table) {
        var value = $(obj).val();
        table.search(value).draw();
    }
}
