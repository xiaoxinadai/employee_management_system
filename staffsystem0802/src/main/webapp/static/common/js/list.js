$(function(){
	
	laydate.render({
		elem: '[name=entrytime]'
		, range: true
	});
	if (sex != null && sex != undefined) {
		$("#staff-form :input[name=sex]").val(sex);
	}
	if (workingtime != null && workingtime != undefined) {
		$("#staff-form :input[name=workingtime]").val(workingtime);
	}
	$(".paginate a").click(function(){
		
		if($(this).is(".first")){
			$("#staff-form :input[name=pageNo]").val(1);
			$("#staff-form").submit();
			
		}else if($(this).is(".last")){
			$("#staff-form :input[name=pageNo]").val(page);
			$("#staff-form").submit();
		}else if ($(this).is(".next")) {
			let p = pageNo + 1;
			if (p > page) {
				p = page;
			}
			$("#staff-form :input[name=pageNo]").val(p);
			$("#staff-form").submit();
		} else if ($(this).is(".prev")) {
			let p = pageNo - 1;
			if (p < 1) {
				p = 1;
			}
			$("#staff-form :input[name=pageNo]").val(p);
			$("#staff-form").submit();
		}else if ($(this).parent().is("li")) {//数字页码
			let p = parseInt($(this).text());//获取
			$("#staff-form :input[name=pageNo]").val(p);
			$("#staff-form").submit();
		}
	});
	
	$("#search-btn").click(function(){
		$("#staff-form :input[name=pageNo]").val(1);
		$("#staff-form").submit();
		
	});
	
	$(".paginate button").click(function(){
		
		let p = $(this).parent().children(":input").val();
		p = parseInt(p);
		
		$("#staff-form :input[name=pageNo]").val(p);
		$("#staff-form").submit();
		
	});
	//选择
	$("#checkall").click(function() {
		let checked = $(this).prop("checked");
		$(".tbl tr>td:first-child>:checkbox").prop("checked", checked);
	});
	
	$(".tbl tr").on("click", "td:not(:first-child)", function() {
		let $td = $(this);
		let $ckx = $td.parent().children("td:first-child").children(":checkbox");
		$ckx.prop("checked", !$ckx.prop("checked"));
	});
	
	//删除操作
	$("#del-btn").click(function(){
		
		let $checked = $(".tbl tr>td:first-child>:checked")
		let ids = [];
		
		$checked.each(function(idx,item){
			
			let $td = $(item).closest("tr").children("td:nth-child(2)");
			ids.push($td.text());
			
		});
		
		if (ids.length === 0) {
			layer.alert("请选择您要删除的行");
			return;
		}

		layer.confirm("是否确认删除", {
			title: "警告"
		}, function(index) {

			//异步无刷新提交ajax。json对象就是没有方法的js对象
			let url = ctx + "/staff/delete";
			$.ajax({
				url: url,
				method: "post",
				dataType: "json",//响应的值的类型
				data: {
					ids: ids
				},
				success: function(resp) {//callback回调函数
					if (resp.success) {
						//当前地址刷新，刷新页面
						layer.msg("删除操作成功");
						location.reload();
					}
				},
				error: function(resp) {
					layer.alert("删除失败");
				},
				traditional: true
			});
		});
	});
	
	//添加操作
	$("#add-btn").click(function(){
		//页面层
		layer.open({
			title:'添加',
  			type: 2,
  			skin: 'layui-layer-rim', //加上边框
  			area: ['600px', '820px'], //宽高
  			content:['http://127.0.0.1:8080/staffsystem0802/staff/add','no']
		});
		
	});
	
	
	//修改操作
	$("#edit-btn").click(function(){
		
		let $checked = $(".tbl tr>td:first-child>:checked");
		let ids = [];//存储所有待修改的编号

		$checked.each(function(idx, item) {
			let $td = $(item).closest("tr").children("td:nth-child(2)");
			ids.push($td.text());
		});

		if (ids.length === 0) {
			layer.msg("请选中您要修改的记录");
			return;
		}

		if (ids.length > 1) {
			layer.msg("您一次只能修改一次记录");
			return;
		}
		
		/*location.href = ctx + "/staff/edit?id=" + ids[0];*/
		
		
		//页面层
		layer.open({
			title:'修改',
  			type: 2,
  			zIndex:100,
  			skin: 'layui-layer-rim', //加上边框
  			area: ['600px', '820px'], //宽高
  			content:'http://127.0.0.1:8080/staffsystem0802/staff/edit?id='+ids[0]
		});
		
	});
	
	

	
});









