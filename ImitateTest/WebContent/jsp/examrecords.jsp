<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	header{
		text-align: center;
		padding:1em;
	}
	table.data{
		border: 1px solid black;
		border-collapse:collapse;
		width:100%;
	}
	table.data td,table.data th{
		border: 1px solid black;
	}
</style>
</head>
<body>
	<header>
	<form id="qryForm" method="POST">
		<input name="pageNo" value="1" type="hidden"> 
		<label id="chooseid" name="chooseid" style="display: none">${chooseid}</label>
	</form>
	</header>
	<section>
		<table class="data">
			<thead>
				<tr>
					<th>序号</th>
					<th>操作</th>
					<th>课程名</th>
					<th>考试时间</th>
					<th>用时</th>
					<th>成绩</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="g" items="${pageData}" varStatus="vs" >
				<tr>
					<td>${vs.count}</td>
					<td><a href="/Servlet/answer.do?id=${g.cjID}&PageNo=1">考试记录</a></td>
					<td>${name}</td>
					<td>${g.kssj}</td>
					<td>${g.ksys}</td>
					<td>${g.fs}</td>
					
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<span>共查到${totalRows}条记录 每页${pageSize}条 共${totalPage}页 </span>
			<span>				
				<c:if test="${currentPage==1}">
					<a>首页</a>
					<a>上一页</a>
				</c:if>
				<c:if test="${currentPage>1}">
					<a href="javascript:goPage(1)">首页</a>
					<a href="javascript:goPage(${currentPage-1})">上一页</a>
				</c:if>
				<b>${currentPage}</b>
				<c:if test="${currentPage==totalPage}">
					<a>下一页</a>
					<a>末页</a>
				</c:if>
				<c:if test="${currentPage<totalPage}">
					<a href="javascript:goPage(${currentPage+1})">下一页</a>
					<a href="javascript:goPage(${totalPage})">末页</a>
				</c:if>				
			</span>
		</div>
	</section>
	<script>
		//跳转到指定页面
		var goPage=function(no){
				var form=document.querySelector("#qryForm");
				form.pageNo.value=String(no);
				form.action="/ImitateTest/exam.do?chooseid="+document.getElementById("chooseid").innerText;
				form.submit();
		}
	</script>
</body>