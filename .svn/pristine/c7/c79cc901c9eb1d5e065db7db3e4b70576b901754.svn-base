<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath }/">
<meta charset="UTF-8">
<title>我要秒杀</title>
<script type="text/javascript" src="js/jquery-3.5.0.js"></script>
<script type="text/javascript">
      $(function(){
    	 $("#miaosha_btn").click(function(){
    		 var url = $("#msform").attr("action");
    		 $.post(url,$("#msform").serialize(),function(data){
    			 if (data=="false") {
					alert("你所在的地区暂时无货");
					$("#miaosha_btn").attr("disabled",true);					
				}
    		 });
    	 });
      })
</script>
</head>
<body>
	<h1>红米30pro至尊版</h1>
	<form id="msform" action="dosekill"
		enctype="application/x-www-form-urlencoded">
		<input type="hidden" id="prodid" name="prodid" value="0101">
		<input
			type="button" id="miaosha_btn" name="sekill_btn" value="秒杀点我">
	</form>

</body>
</html>