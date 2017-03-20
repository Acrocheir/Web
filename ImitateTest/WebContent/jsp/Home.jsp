<%@page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>首页</title>
</head>
<link rel="stylesheet" type="text/css" href="/ImitateTest/css/HomePage.css">

<body>
	<header>
        <h1>一二模拟考试系统</h1>
    </header>
    <section>
        <menu>
            <ul>
                <li><a href="/ImitateTest/index.do?username=${username}" target="main">首页</a></li>
                <li><a target="main">模拟考试</a></li>
                <li><a href="/ImitateTest/choose.do?username=${username}" target="main">考试记录</a></li>
                <li><a href="/ImitateTest/loadusers.do?username=${username}&pageNo=1" target="main">个人中心</a></li>
                <li><a href="/ImitateTest/jsp/Login.jsp">退出</a></li>
            </ul>
        </menu>
        <main>
            <iframe name="main" src="/ImitateTest/index.do?username=${username}" width="100%" height="100%" frameborder="no"></iframe>
        </main>
    </section>
    <footer>不努力，你永远不知道自己有多强大！</footer>
</body>
</html>