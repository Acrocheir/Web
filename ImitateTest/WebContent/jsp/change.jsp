<%@page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改密码</title>
</head>
<body>
	<form action="/ImitateTest/change.do" method="post">
		<p>
			 用户名:<input name="id" value="${vo.id}" >
		</p>
		<p>
		 	原密码:<input name="password" value="${vo.mm }">
		</p>
		<p>
		 	新密码:<input name="newpassword" value="${vo.mm }">
		</p>
		<p>
			<button>确定</button>
		</p>
	</form>
</body>
</html>