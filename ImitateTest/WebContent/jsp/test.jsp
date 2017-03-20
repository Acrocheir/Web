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
	<form id="qryForm" action="/ImitateTest/choose.do" method="POST">
		<input name="pageNo" value="1" type="hidden"> 
	</form>
	</header>
	<section>
		<table class="data">
			<thead>
				<tr>
					<th>序号</th>
					<th>选课编号</th>
					<th>课程名称</th>
					<th>选课时间</th>
					<th>操作</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="g" items="${pageData}" varStatus="vs" >
				<tr>
					<td>${vs.count}</td>
					<td>${g.xkID}</td>
					<td>${g.kc.name}</td>
					<td>${g.xksj}</td>
					<td><a href="/ImitateTest/exam.do?chooseid=${g.xkID}&PageNo=1&name=${g.kc.name}">考试记录</a></td>
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
				form.submit();
		}
	</script>
</body>