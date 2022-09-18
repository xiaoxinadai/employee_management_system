<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 需要特别注意空格 浏览器会将空格解析为一系列的%20 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<base href="${ctx}/ ">
<meta charset="utf-8" />
<title>登录</title>
<!-- 引入css -->
<link rel="stylesheet" href="static/common/css/login.css">
<script type="text/javascript"
	src="static/common/lib/jquery/jquery-3.6.0.min.js"> ></script>
<!-- 注意必须要放在login.js前面,要不然按照顺序会用不了 -->
<script type="text/javascript" src="static/common/lib/layer/layer.js"> ></script>
<!-- 登录时提示错误信息 -->
<script type="text/javascript">
	const ctx = "${ctx}";
	let error = "${error}";
	let loginUsername = "${username}"
</script>

<script type="text/javascript" src="static/common/js/login.js"> ></script>
</head>
<body>
	<!-- 填写框上的logo -->
	<div class="logo_box">
		<img class="logo_img" src="static/common/image/login_logo.png">
	</div>

	<!-- 登录框设计 -->
	<div class="login_box">

		<!-- 欢迎文字部分 -->
		<h1 class="title">Welcome</h1>

		<form id="login-form" action="user/login" method="post">
			<input class="input_box" id="username" name="username" type="text"
				placeholder="用户名" autocomplete="off" value="admin0"> <input
				class="input_box" id="password" type="password" name="password"
				placeholder="密码" autocomplete="off" value="123456">

			<!-- 登录按钮 -->
			<input class="button_box" type="button" value="登录" id="login-btn">
			<div class="errortip">
				<!-- 显示错误信息 -->
				<h2 class="error"></h2>

			</div>


		</form>
	</div>

</body>
</html>