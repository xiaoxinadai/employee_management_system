$(function() {
	if (error) {
		layer.alert(error);
	} else {
		if (success) {
			layer.confirm("确认修改员工信息?", {
				title: "提示"
			}, function(handler) {
				location.href = ctx + "/staff/list";
				/*layer.close(handler);*/
				parent.location.reload();
			}, function(handler) {
				//继续留在本页
				layer.close(handler);
			});
		}
	}
	
	
	if (sex) {
		$("#staff-form [name=sex]").val(sex);
	}

	laydate.render({
		elem: "[name=entrytime]"
	});

	//点击提交修改按钮
	$("#submit-btn").click(function() {
		
		//执行参数校验
		let staffId = $(":input[name=staffId]").val();
		if (staffId.trim() === "") {
			layer.alert("员工号不可为空", function(handler) {
				$(":input[name=staffId]").focus();
				layer.close(handler);
			});
			return;
		}

		$("#staff-form").submit();
	});
	
});





