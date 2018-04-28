const $tools = mcfish.Tools
const myurl = $tools.parseURL(window.location.href)
const mode = parseInt(myurl.params['mode'])
const id = parseInt(myurl.params['id'])

$(function() {
	/*var userinfo = $tools.is_Session('userinfo')
	$("#companyCname").html(userinfo.cname);*/
	
	//mode 0:岗位职责        1:研发会议        2:研发规范        3:研发制度        4:技术分享
 	
	switch(mode) {
		case 0:
			$tools.asyncRequest("api/research/getPositionDetail","GET",{id:id}).then(function(res){
				var data = res.data
				if(data){
					//回显数据
					$("#companyCname").html(data.creator);
					$("#headline").html(data.name + "岗位职责描述");
					$("#itemDate").html($tools.getMyDate(data.createTime,1));
					$("#entity").html(data.entity == 0?"虚拟岗位":"实体岗位");
					$("#leader").html(data.leader == 0?"普通岗位":"管理岗位");
					$("#content").html(data.content);
				}
			});
			$('#position').show()
			break
		case 1:
			$tools.asyncRequest("api/research/getMeetingDetail","GET",{id:id}).then(function(res){
				var data = res.data
				if(data){
					//回显数据
					$("#companyCname").html(data.creator);
					$("#headline").html(data.name + "描述");
					$("#itemDate").html($tools.getMyDate(data.createTime,1));
					$("#sponsor").html(data.sponsor?data.sponsor:"");
					$("#participants").html(data.participants?data.participants:"");
					$("#freq").html(data.freq?data.freq:"");
					$("#content").html(data.content);
				}
			});
			$('#meeting').show()
			break
		case 2:
			$tools.asyncRequest("api/research/getSpecificationDetail","GET",{id:id}).then(function(res){
				var data = res.data
				if(data){
					//回显数据
					$("#companyCname").html(data.creator);
					$("#headline").html(data.title + "描述");
					$("#itemDate").html($tools.getMyDate(data.createTime,1));
					$("#content").html(data.content);
				}
			});
			$('#standard').show()
			break
		case 3:
			$tools.asyncRequest("api/research/getRegulationDetail","GET",{id:id}).then(function(res){
				var data = res.data
				if(data){
					//回显数据
					$("#companyCname").html(data.creator);
					$("#headline").html(data.name + "描述");
					$("#itemDate").html($tools.getMyDate(data.createTime,1));
					$("#content").html(data.content);
				}
			});
			$('#regulation').show()
			break
		case 4:
			$tools.asyncRequest("api/research/getShareDetail","GET",{id:id}).then(function(res){
				var data = res.data
				if(data){
					//回显数据
					$("#companyCname").html(data.creator);
					$("#headline").html(data.name + "描述");
					$("#itemDate").html($tools.getMyDate(data.createTime,1));
					$("#content").html(data.brief);
				}
			});
			$('#share').show()
			break
		default:
			break
	}
})


/**
 * 关闭详情页
 */
function closeDetail(){
	window.history.back();
}
