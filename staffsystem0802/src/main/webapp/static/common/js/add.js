$(function() {
	if (error) {
		layer.alert(error);
	} else {
		if (success) {
			layer.confirm("添加信息成功，是否继续添加?", {
				title: "提示"
			}, function(handler) {
				//继续留在本页
				layer.close(handler);
			}, function(handler) {
				location.href = ctx + "/staff/list";
				/*layer.close(handler);*/
				parent.location.reload();
			});
		}
	}

	laydate.render({
		elem: "[name=entrytime]"
	});

	//点击提交按钮
	$("#submit-btn").click(function() {
		//执行参数校验
		let staffId = $(":input[name=staffId]").val();
		if (staffId.trim() === "") {
			layer.alert("员工工号不可为空", function(handler) {
				$(":input[name=staffId]").focus();
				layer.close(handler);
			});
			return;
		}

		$("#staff-form").submit();
	});
});