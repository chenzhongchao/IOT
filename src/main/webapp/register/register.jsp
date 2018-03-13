<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>注册</title>
<link rel="stylesheet" href="css/style.css" />
<body>

<div class="register-container">
	<h1>IOT</h1>
	
	<div class="connect">
		<p>用户注册</p>
	</div>
	
	<form action="${ctx}/register/register" method="post" >
		<div>
			<input type="text" id="username" name="username" class="username" placeholder="您的用户名" autocomplete="off"/>
		</div>
		<div>
			<input type="password" id="password" name="password" class="password" placeholder="输入密码" oncontextmenu="return false" onpaste="return false" />
		</div>
		<div>
			<input type="password" id="confirm_password" name="confirm_password" class="confirm_password" placeholder="再次输入密码" oncontextmenu="return false" onpaste="return false" />
		</div>
<!-- 		<div>
			<input type="text" name="phone_number" class="phone_number" placeholder="输入手机号码" autocomplete="off" id="number"/>
		</div>
		<div>
			<input type="email" name="email" class="email" placeholder="输入邮箱地址" oncontextmenu="return false" onpaste="return false" />
		</div> -->

		<button id="user" type="submit">注 册</button>
	</form>

</div>

<script>var _urlPath = "http://localhost:8080/iot/";</script>
<script src="js/jquery-1.7.2.min.js"></script>
<script src="js/common.js"></script>
<script src="js/md5.js"></script>
<!--背景图片自动更换-->
<script src="js/supersized.3.2.7.min.js"></script>
<script src="js/supersized-init.js"></script>
<!--表单验证-->
<script src="js/jquery.validate.min.js?var1.14.0"></script>

<script type="text/javascript">
$(document).ready(function(){
	  
	  $("form").submit(function(e){
		  var password = $("#password").val();
		  var confirm_password = $("#confirm_password").val();
		  if(password.length <= 20){
			  password = hex_md5(password);
			  confirm_password = hex_md5(confirm_password);
		  }
		  $("#password").val(password);
		  $("#confirm_password").val(confirm_password);
		  
	  });
	});
</script>

</body>
</html>
