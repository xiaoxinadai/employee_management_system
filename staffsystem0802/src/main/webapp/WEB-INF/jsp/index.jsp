<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<base href="${ctx }/">
<meta charset="UTF-8">
<title>首页</title>
<link rel="stylesheet" href="static/common/css/index.css">
<!-- 定义一个常量 -->
<script type="text/javascript">
	const ctx = "${ctx}";
	const username = "${username}";
</script>

<script type="text/javascript"
	src="static/common/lib/jquery/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="static/common/js/index.js"></script>


<script type="text/javascript">
	$(function() {
		// js实现伸缩式菜单栏
		// 隐藏所有的子标题区
		$(".menucontent").each(function() {
			$(this).children(".secmenu").hide();
		});
		//给所有的主标题添加单击事件
		$(".fstmenu").each(
				function() {
					$(this).click(
							function() {
								var navEle = $(this).parents(".menucontent")
										.children(".secmenu");
								if (navEle.css("display") != "none") {
									//收起
									navEle.slideUp(200)
								} else {
									//向下展开
									navEle.slideDown(200);
								}
							});
				});

		//注销栏隐藏
		$(".rightarea").each(function() {
			$(this).children(".loginrootout").hide();
		});
		//添加点击事件
		$(".loginrootpic").each(
				function() {
					$(this).click(
							function() {
								var navEle = $(this).parents(".loginroot")
										.next(".loginrootout");
								if (navEle.css("display") != "none") {
									//收起
									navEle.slideUp(200)
								} else {
									//向下展开
									navEle.slideDown(200);
								}
							});
				});

	});
</script>






</head>
<body>
	<header>
		<h5>${username} 您好，欢迎使用百思特员工管理系统</h5>

	</header>



	<!-- 首页进行左右布局
		左侧菜单栏，菜单狼顶部放公司标志
		右侧为内容部分，内容顶部的右边显示当前用户 -->

	<div class="main">

		<!-- 左侧 -->
		<div class="leftarea clear">

			<!-- 菜单栏内容为:人事管理（员工管理）、财务管理（员工工资）、公司管理（项目管理）、系统管理 -->
			<!-- 左侧区域 -->

			<!-- 菜单栏顶部的公司标志 -->
			<div class="cmplogopic"></div>

			<!-- 菜单栏 -->
			<div class="lfmenu">

				<div class="menucontent">
					<!-- 主菜单 -->
					<div class="fstmenu">人事管理</div>
					<!-- 子菜单 -->
					<div class="secmenu">
						<a data-url="/staff/list">员工管理</a>
					</div>
				</div>
				<div class="menucontent">
					<!-- 主菜单 -->
					<div class="fstmenu">财务管理</div>
					<!-- 子菜单 -->
					<div class="secmenu">
						<a href="#">员工工资</a>
					</div>
				</div>
				<div class="menucontent">
					<!-- 主菜单 -->
					<div class="fstmenu">公司管理</div>
					<!-- 子菜单 -->
					<div class="secmenu">
						<a href="#">项目管理</a>
					</div>
				</div>
				<div class="menucontent">
					<!-- 主菜单 -->
					<div class="fstmenu">系统管理</div>
				</div>

			</div>




		</div>

		<!-- 右侧 -->
		<div class="rightarea clear">

			<!-- 顶部账号信息栏 -->
			<div class="loginroot clear">

				<!-- 头像 -->
				<div class="loginrootpic"></div>


				<!-- 标题 -->
				<div class="top-title">
					<div class="top-titlec">百思特员工管理系统</div>
				</div>

			</div>
			<div class="loginrootout"><a id = "logout-btn">注销</a></div>

			<!-- 右侧内容区域 -->
			<div class="rgtcontent">
				<div class = "rgt">
					<iframe src="${ctx}/welcome"></iframe>
				</div>
				
			</div>


		</div>

	</div>
</body>
</html>