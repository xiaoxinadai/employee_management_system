$(function(){
	/*注销*/
	$("#logout-btn").click(function(){
		location.href = ctx + "/user/logout";
		
	});
	
	//显示表格	
	$(".menucontent>.secmenu>a").click(function() {
		let url = $(this).data("url");
		$(".rgtcontent>.rgt>iframe").attr("src", ctx + url);
	});
	
});