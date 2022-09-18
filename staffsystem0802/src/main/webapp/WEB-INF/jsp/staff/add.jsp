<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<html>
<head>
<base href="${ctx }/">
<meta charset="utf-8">
<title>员工表单</title>
<link rel="stylesheet" href="static/common/css/add.css">
<script type="text/javascript">
	const ctx = "${ctx}";
	const error = "${error}";
	const success = "${success}";

</script>

<script type="text/javascript" src="static/common/lib/jquery/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="static/common/lib/laydate/laydate.js"></script>
<script type="text/javascript" src="static/common/lib/layer/layer.js"></script>
<script type="text/javascript" src="static/common/js/add.js"></script>

</head>
<body>
	<h1>添加信息</h1>
	<div class="main">
		<form id="staff-form" action="staff/add" method="post">
			<div>
				<label>工号：(<i class="warning">*</i>)：
				</label> <input type="text" name="staffId" placeholder="请输入员工工号" autocomplete="off"> <span class="error">${error }</span>
			</div>

			<div>
				<label>姓名(<i class="warning">*</i>)：
				</label> <input type="text" name="name" placeholder="请输入姓名" autocomplete="off">
			</div>

			<div>
				<label>部门(<i class="warning">*</i>)：
				</label> <input type="text" name="job">
			</div>

			<div>
				<label>性别(<i class="warning">*</i>)：
				</label> <select name="sex">
					<option value="男">男</option>
					<option value="女">女</option>
				</select>
			</div>
			<div>
				<label>年龄：</label> <input type="text" name="age">
			</div>

			<div>
				<label>入职日期：</label> <input type="text" name="entrytime" autocomplete="off" readonly="readonly">
			</div>

			<div>
				<label>邮箱：</label> <input type="text" name="email">
			</div>

			<div>
				<label>工龄：</label> <input type="text" name="workingyears">
			</div>
			

			<div>
				<label></label>
				<button id="submit-btn" type="button">提交</button>
				<button id="reset-btn" type="reset">重置</button>
			</div>

		</form>
	</div>

	
	
	
</body>
</html>