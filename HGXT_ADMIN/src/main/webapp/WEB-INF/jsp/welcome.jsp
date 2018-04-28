<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>客户资料</title>
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<link rel="stylesheet" href="../dist/bootstrap/bootstrap.min.css">
		<link rel="stylesheet" href="../dist/adminlte/skin-mcblue.css">
		<link rel="stylesheet" href="../dist/adminlte/AdminLTE.css">
		<link rel="stylesheet" href="../mcfish/css/mcfish.css">

	</head>

	<body class="hold-transition skin-blue sidebar-mini mc_body">
		<div class="wrapper">
			<div class="content-wrapper" style="margin-left: 0;">
				<section class="content mc_content">
					<div style="margin-top:10%">
						<h2 align="center" style="color: gray;font-size: 40px;">欢迎来到<span id="project"></span>-共享管理平台</h2>
					</div>
				</section>
			</div>
		</div>
		
		<script src="../dist/jquery/jquery.min.js"></script>
		<script src="../dist/bootstrap/bootstrap.min.js"></script>
		<script src="../dist/adminlte/adminlte.min.js"></script>
		<script src="../dist/jquery/ui.js"></script>
		<script src="../mcfish/js/mcfish.js"></script>
		
		
		<script type="text/javascript">
			mcfish.API.asyncRequest("keyValue","POST",{}).then(function(result){
				var res = result.data
				 for(var i=0;i<res.length;i++){
		    		if(res[i].key=="project"){
		    			 $("#project").html(res[i].value);
		    			 $("#indexProjectName").html(res[i].value);
		    		}
		    	}
			});
		</script>
	</body>

</html>