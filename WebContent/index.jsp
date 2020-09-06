<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath }/">
<meta charset="UTF-8">
<title>欢迎</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery-3.5.0.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript">
var t=120;
var interval;
	
	$(function(){
		$("#sendcode").click(function(){
			$.post("Verify_code_CodeSender",$("#codeform").serialize(),function(data){
				if (data=="true") {
					t=120;
					clearInterval(interval);
					interval = setInterval("refer()",1000);//启动1秒定时
					
				}else if (data=="limit") {
					clearInterval(interval);
					$("#countdown").text("单日发送超过次数！");
				}else {
					clearInterval(interval);
					$("#countdown").text("手机号有误！");
				}
			});
		});
		
		$("#verifyCode").click(function(){
			$.post("Verify_code_CodeVerify",$("#codeform").serialize(),function(data){
				if (data=="true") {
				     $("#result").attr("color","green");
				     $("#result").text("验证成功");
				     clearInterval(interval);
				     $("#countdown").text("");
				}else {
					$("#result").attr("color","red");
					$("#result").text("验证失败");
					
				}
			});
		});
	});

function refer(){
	$("#countdown").text("请于"+t+"秒内填写验证码");//显示倒计时
	t--;//计数器递减
	if (t<=0) {
		clearInterval(interval);
		$("#countdown").text("验证码已失效，请重新发送！");
	}
}
</script>
</head>
<body>
	<h1><a href="emps/1">展示员工信息</a></h1>
	<h1><a href="page/sekill.jsp">去秒杀</a></h1>
	<div class="container">
		<div class="row">
			<div id="alertdiv" class="col-md-12">
				<form action="navbar-form navbar-left" role="search" id="codeform">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="填写手机号"
							name="phone_no">
						<button type="button" class="btn btn-default" id="sendcode">发送验证码</button>
						<font id="countdown" color="red"></font> <br>
						<input
							type="text" class="form-control" placeholder="填写验证码"
							name="verify_code">
						<button type="button" class="btn btn-default" id="verifyCode">确定</button>
						<font id="result" color="green"></font>
						<font id="error"
							color="red"></font>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>