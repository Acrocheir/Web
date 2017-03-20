<%@page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
</head>
<link type="text/css" rel="stylesheet" href="/ImitateTest/css/LoginPage.css">

<script>
    function hide(thisz) {
    	document.getElementById('warning').style.display = 'none';
        if (thisz == 'le') {
        	document.getElementById('le').style.backgroundColor = "#9acfea";
        	document.getElementById('ri').style.backgroundColor = "white";
            document.getElementById('test').style.display = 'block';
            document.getElementById('Regist').style.display = 'none';
        }else {
        	document.getElementById('ri').style.backgroundColor = "#9acfea";
        	document.getElementById('le').style.backgroundColor = "white";
            document.getElementById('test').style.display = 'none';
            document.getElementById('Regist').style.display = 'block';
        }
     }
    </script>
<body>
<img src="/ImitateTest/img/bg_login.jpg" height="100%" width="100%">
<div id="container">
    <div id="login">
        <div id="login-head">
            <div id="le" class="login_head" onclick="hide('le')">
                <h2>登录</h2>
            </div>
            <div id="ri" class="login_head" onclick="hide('ri')">
                <h2>注册</h2>
            </div>
        </div>
        <form id="form1" method="post" action="/ImitateTest/login.do">
            <dl>
                <dt>一二模拟考试系统</dt>
                <!--<dt>登录页面</dt>-->
                <div dis name="Login" id="test">
                    <dd>
                        <input type="text" id="username" name="username"
                               placeholder="请输入用户名" required autofocus height="30" class="input">
                    </dd>
                    <dd>
                        <input type="password" name="password"
                               placeholder="请输入密码" required class="input">
                    </dd>
                    <dd>
                        <input type="submit" value="登录" class="btn btn-submmit">
                        <input type="reset" value="重置" class="btn btn-rsset">
                    </dd>
            </dl>
        </form>
        <div style="display:none" name="Regist" id="Regist" height="40px">
            <form id="form2" method="post" action="/ImitateTest/regist.do">
                <dl>
                    <dd>
                        <input type="text" name="registname"
                               placeholder="请输入用户名" required autofocus height="30" class="input">
                    </dd>
                    <dd>
                        <input type="password" name="password1"
                               placeholder="请输入密码" required class="input">
                    </dd>
                    <dd>
                        <input type="password" name="password2"
                               placeholder="请再次确认密码" required class="input">
                    </dd>
                    <dd>
                        <input type="submit" value="注册" class="btn btn-submmit">
                        <input type="reset" value="重置" class="btn btn-rsset">
                    </dd>
                </dl>
            </form>
        </div>
        <p>
			<strong id="warning">${message}</strong>
		</p>
    </div>
</body>
</html>