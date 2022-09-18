$(function(){
	
	/*登录错误提示*/
	if(error){
		layer.alert(error)
	}
	
	/*实现因密码输入错误或其他原因，导致无法登录，希望保留用户名在输入框*/
	if(loginUsername){
		$("#username").val(loginUsername);
	}
	
	$("#login-btn").click(function(){
		/*取得用户名与密码*/
		let username = $("#username").val();
		let password = $("#password").val();
		
		/*用户名为空时，提示用户输入*/
		if(username.trim() === ""){
			layer.alert("账号不可为空");
			return;
		}else if(username.trim().length < 6){ /*用户名不可少于6位*/
			layer.alert("用户名不可少于6位");
			return;
		}
		/*对输入的密码进行检验*/
		if(password.trim() === ""){  /*不可为空*/
			layer.alert("密码不可为空");
			return;
		}else if(password.trim().length < 6){ /*密码不能少于6位*/
			layer.alert("密码不能少于6位");
			return;
		}
		
		/*表单提交*/
		/*表单提交时，以name的值为键key，输入的值为value*/
		$("#login-form").submit();
	});
	//失去焦点
	$("#username").blur(function(){
		let username = $(this).val();
		
		$.ajax({
			url:ctx + "/user/exists",
			method:"post",
			dataType:"json",
			data:{
				username:username
			},
			success:function(resp){
				if (resp.error) {
					$("#login-form h2.error").text(resp.error);
				}
			}
		});
	});
	
});