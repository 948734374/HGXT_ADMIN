const $tools= mcfish.Tools
const $api	= mcfish.API
const myurl = $tools.parseURL(window.location.href)
const id = parseInt(myurl.params['id'])
const um = UM.getEditor('editor')

$(function() {
	
	um.setContent("")
	
	if(id){
		$api.asyncRequest("shareSystemController/getAboutById.do","GET",{id:id}).then(function(res){
			var data = res.data
			if(data){
				$('.box-title').text('编辑关于我们')
				$("#publish").attr("onclick", "saveItem("+ data.id +")");
				$("#publish").html("保存");
				
				//回显数据
				$("#bulit-code").val(data.code);
				$("#built-title").val(data.title);
				
				um.setContent(data.content || "");
			}
		});
	}else{
		$("#publish").attr("onclick", "saveItem(null)");
		$("#publish").html("发布");
		$('.box-title').text('新建关于我们')
	}
})


/**
 * 发布
 * @param {Object} id 当前项目id（如果存在则为编辑，否则为新增）
 */
function saveItem(id) {
	
	var code = $("#bulit-code").val();
	var title = $("#built-title").val();
	var content = um.getContent();

	if(code == null || code == "") {
		mizhu.toast("请填关键字", 1000);
		return false;
	}
	if(title == null || title == "") {
		mizhu.toast("请填写标题", 1000);
		return false;
	}
	if(content == null || content == "") {
		mizhu.toast("请填写描述", 1000);
		return false;
	}

	var data = {
		code	: code,
		title	: title,
		content	: content
	}
	
	var url = "shareSystemController/addSystemAbout.do";
	var info = "发布成功";
	if(id){
		data.id = id;
		url = "shareSystemController/updateSystemAbout.do";
		info = "保存成功"
	}

	$api.asyncRequest(url, "POST", data).then(function(res) {
		parent.childToast(info,3000);
		window.history.back();
		//window.location.href = $tools.getBasicUrl() + "shareSystemController/SystemPage.do#tab_4";
	});
}

// 文件上传
function mcupFileFunc(obj){
	var pid  = sessionStorage.getItem('nowProjectId') 
	var file = $(obj)[0].files[0] // 是否有值
	if (!file) {
		mizhu.toast('上传文件不能为空')
		return
	}
	//文件类型判断
	//fileType(file, ["word","doc","excel","xlsx"])
	/*
	if (file.type.indexOf("word")<0){  //  是否有word标签
		console.log('请上传word文档')
		return
	}*/
	var form = new FormData();    // FormData 对象
	form.append("file", file);    // 文件对象
	form.append("bath", '/user') 
	
	mcfish.Tools.processRequest('api/system/uploadFile','POST',form).then(function(res){
		$(obj).parent().find("span").show();
		var furl = res.data.fileUrl;
		$(obj).parent().find("b").html(furl);
	})
}

/**
 * 删除当前上传的文件
 * @param {Object} obj
 */
function delUpFile(obj){
	$(obj).parent().find("input").val("");
	$(obj).parent().find("b").html("");
	$(obj).hide();
	if($(".new-bulit-item.addFile").length >= 2){
		$(obj).parent().remove();
	}
}


/**
 * 添加一项
 */
function addFileTool(obj){
	var str = '<div class="new-bulit-item addFile">' +
					'<p class="bulit-small"></p>' +
					'<input type="file" onchange="mcupFileFunc(this)">' +
					'<b style="display: none;"></b>' +
					'<span class="tab_text_blue pointer" onclick="delUpFile(this)">删除</span>' +
				'</div>';
	$(obj).before(str);
}

/**
 * 获取上传的文件的URL
 */
function getFilesUrl(){
	var filesUrl = "";
	var fileDom = $(".new-bulit-item.addFile").find("b");
	for (var i = 0 ; i < fileDom.length ; i++) {
		var fileUrl = fileDom.eq(i).html();
		if( fileUrl != undefined && fileUrl != null && fileUrl != ""){
			if(filesUrl == ""){
				filesUrl += fileUrl;
			}else{
				filesUrl += ";" + fileUrl;
			}
		}
	}
	console.log(filesUrl)
	return filesUrl;
}


/**
 * 限制文件大小，返回 boolean
 * @param {Object} file 文件对象
 * @param {Object} limitSize 限制大小---例如："50MB"
 */
function limitFileSize(file, limitSize){
	var arr = ["KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"]
    var limit = limitSize.toUpperCase();
    var limitNum = 0;
    for (var i = 0; i < arr.length; i++) {
        var leval = limit.indexOf(arr[i]);
        if (leval > -1) {
            limitNum = parseInt(limit.substr(0, leval)) * Math.pow(1024, (i + 1))
            break
        }
    }
    if (file.size > limitNum) {
        return false
    }
    return true
}



/**
 * 格式化文件大小显示
 * @param {Object} value 文件的大小值
 */
function formateSize(value){
	if (null == value || value == '') {
        return "0 Bytes";
    }
    var unitArr = new Array("Bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB");
    var index = 0;
    var srcsize = parseFloat(value);
    index = Math.floor(Math.log(srcsize) / Math.log(1024));
    var size = srcsize / Math.pow(1024, index);
    size = size.toFixed(2);//保留的小数位数
    return size + unitArr[index];
}