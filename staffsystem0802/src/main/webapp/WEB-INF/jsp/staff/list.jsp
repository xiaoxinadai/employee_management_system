<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />

<html>
<head>
<base href="${ctx }/">
<meta charset="utf-8">
<title>员工表单</title>
<link rel="stylesheet" href="static/common/css/list.css">
<script type="text/javascript">
	const ctx = "${ctx}";
	const page = parseInt("${pi.page}");//总页数
	const pageNo = parseInt("${pi.pageNo}");//当前页
	const pageSize = parseInt("${pi.pageSize}");//页面大小
	const sex = "${staffbean.staffSex}";
	const workingtime = "${staffbean.staffWorkingyears}";

</script>

<script type="text/javascript" src="static/common/lib/jquery/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="static/common/lib/laydate/laydate.js"></script>
<script type="text/javascript" src="static/common/lib/layer/layer.js"></script>
<script type="text/javascript" src="static/common/js/list.js"></script>

</head>
<body>
	<!-- 查询条件 -->
	<div class="search">
		<form id="staff-form" class="clear" action="staff/list" method="post">
			<div>
				<label for="">工号：</label> <input type="text" name="staffid" autocomplete="off" value="${staffbean.staffId}">
			</div>
			<div>
				<label for="">姓名：</label> <input type="text" name="name" autocomplete="off" value="${staffbean.staffName}">
			</div>
			<div>
				<label for="">性别：</label> <select name="sex">
					<option value=""></option>
					<option value="男">男</option>
					<option value="女">女</option>
				</select>
			</div>
			<div>
				<label for="">加入日期：</label> <input type="text" name="entrytime" autocomplete="off" value="${staffbean.staffEntrytime}">
			</div>
			<div>
				<label for="">邮箱：</label> <input type="text" name="email" autocomplete="off" value="${staffbean.staffEmail}">
			</div>
			<div>
				<label for="">工龄：</label> <select name="workingtime">
					<option value=""></option>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="8">9</option>
				</select>
			</div>

			<input type="hidden" name="pageNo" value="${pi.pageNo}" />
			 <input type="hidden" name="pageSize" value="${pi.pageSize }" />
		</form>
	</div>

	<!-- 操作按钮 -->
	<div class="op clear">
		<div>
			<button id="add-btn" type="button">添加</button>
		</div>
		<div>
			<button id="edit-btn" type="button">修改</button>
		</div>
		<div>
			<button id="search-btn" type="button">查询</button>
		</div>
		<div>
			<button id="del-btn" class="error" type="button">删除</button>
		</div>
	</div>

	<div class="main">
		<table class="tbl">
			<thead>
				<tr>
					<th><input id="checkall" type="checkbox"></th>
					<th><span>id</span></th>
					<th><span>工号</span></th>
					<th><span style="width: 120px;">姓名</span></th>
					<th><span style="width: 120px;">部门</span></th>
					<th><span>性别</span></th>
					<th><span>年龄</span></th>
					<th><span style="width: 120px;">邮箱</span></th>
					<th><span style="width: 120px;">加入日期</span></th>
					<th><span>工龄</span></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${staffs}" var="sfs">
					<tr>
						<td><input type="checkbox"></td>
						<td>${sfs.id }</td>
						<td>${sfs.staffId }</td>
						<td>${sfs.staffName }</td>
						<td>${sfs.staffJob }</td>
						<td>${sfs.staffSex }</td>
						<td>${sfs.staffAge }</td>
						<td>${sfs.staffEmail }</td>
						<td>${sfs.staffEntrytime}</td>
						<td>${sfs.staffWorkingyears}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<!-- 底部分页 -->
	<div class="footer clear">
		<div class="paginate">
			<span>总记录数：${pi.total }/总页数：${pi.page }</span> <a class="first" href="javascript:void(0)">首页</a> <a class="prev" href="javascript:void(0)">上一页</a>
			<ul class="clear">

				<c:forEach begin="${pi.navstartpage }" end="${pi.navendpage }" var="p">
					<c:if test="${p == pi.pageNo }">
						<li class="current"><a href="javascript:void(0)">${p }</a></li>
					</c:if>

					<c:if test="${p != pi.pageNo }">
						<li><a href="javascript:void(0)">${p }</a></li>
					</c:if>
				</c:forEach>

			</ul>
			<a class="next" href="javascript:void(0)">下一页</a>
			<a class="last" href="javascript:void(0)">尾页</a>
			<div>
				跳转到第&nbsp;<input type = "text" value = "${pi.pageNo}">&nbsp;页
				<button>确定</button>
			</div>
		</div>
	</div>
</body>
</html>