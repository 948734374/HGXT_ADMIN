<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="mcfish/css/login.css">
</head>

<body>
    <div class="header">
        <div class="content">
            <img src="" alt="Logo" id="logo">
            <p id="project"></p>
        </div>
    </div>
    <div class="login-content">
        <p style="text-align: left;color:#fff;font-size:20px;">管理员登录</p>
        <div  id="Msg" align="center"  style="color: red;height: 20px;">${Msg }</div>
        <div class="input-login">
        	<form action="${pageContext.request.contextPath}/login" method="post">
	            <input type="text"    name="account" id="account" placeholder="请输入账号" autocomplete = 'new-password'/>
	            <input type="password" name="password" id="password" placeholder="请输入密码"/>
	            <input type="hidden" name="types" id="types" value="1"/>
	            <div class="rem-scout">
	                <div>
	                	<input type="checkbox" name="remenber" onclick="remember2();" id="remFlag" value="" />
	                	<span style="color:#ffffff;">记住账号</span>
	                </div>
	                <div>
	                	<input type="checkbox" name="agent" onclick="checkType();" id="agentLogin" value="" />
	                	<span style="color:#ffffff;">渠道商登录</span>
	                </div>
	            </div>
	            <button class="login-submit" id="loginBtn" onclick="remember()">登&nbsp;录</button>
	        </from>
        </div>
    </div>
    <div class="login-copyright">
		<p id="copyright" style="color: #ffffff;">-</p>
	</div>
    <script src="dist/jquery/jquery.min.js"></script>
    <script src="dist/jquery/ui.js"></script>
    <script src="dist/jquery/md5.js"></script>
    <script src="mcfish/js/mcfish.js"></script>
    <script src="mcfish/js/login.js"></script>
    <script>
	    $(".login-submit").click(function(){
			if($("#account").val()=="" ){
				$("#Msg").text("请输入账号");
				return false;
			}
			if($("#password").val()=="" ){
				$("#Msg").text("请输入密码");
				return false;
			}
		});
    
        if (window != top){
			top.location.href = "${pageContext.request.contextPath}/"
		}
    </script>
</body>
