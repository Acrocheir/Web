<%@page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>正在模拟考试...</title>
</head>
<link type="text/css" rel="stylesheet" href="/ImitateTest/css/ExamPage.css">

<script type="text/javascript">
	/* 获得用户的选择 */
	function userChecked() {
		for(var i=1;i<=10;i++) {
			var radiosName = document.getElementsByName("rad"+i);
			for(var j=0;j<radiosName.length;j++) {
				if(radiosName[j].checked) {
					switch (j) {
						case 0:
							document.getElementById("userchoose"+i).innerHTML="A";
						break;
					
						case 1:
							document.getElementById("userchoose"+i).innerHTML="B";
						break;
						
						case 2:
							document.getElementById("userchoose"+i).innerHTML="C";
						break;
						
						case 3:
							document.getElementById("userchoose"+i).innerHTML="D";
						break;

						default:
						break;
					}
				}
			}
		}
		return useranswer;
	}
	
	/* 用户开始考试的系统时间 */
	function examStartTime() {
		var date = new Date();
        var h = date.getHours(); //获取小时
        var m = date.getMinutes(); //获得分钟
        var s = date.getSeconds(); //获得秒钟
        //为了均为两位数字
        h = checkTime(h);
        m = checkTime(m);
        s = checkTime(s);
        return h+":"+m+":"+s;
	}
	/* 时分秒均显示两位数字 */
	 function checkTime(t) {
         if(t<10){
             t = "0"+t;
         }
         return t;
     }
	
	/* 计时 */
	var h=0;
	var m=0;
	var s=0;
	function timeing() {
		s++;
		if(s>=60) {
			s=0;
			m++;
		}else if(m>=60) {
			m=0;
			h++;
		}
		document.getElementById("timeing").innerHTML = h+":"+m+":"+s;
        //递归调用，没1000毫秒加载一次，及1秒
        t = setTimeout(function () {
            timeing();
        },1000);
	}
	
	/* 显示时间 */
	function time() {
		var myDate = new Date();
		//打印日期+时间
		document.getElementById("starttime").innerText=myDate.toLocaleDateString()+" "+examStartTime();//获取日期与时间
		timeing();
	}
	
	/* 提示用户是否提交 */
	var allanswer = new Array();
	var rightAnswer = new Array();
	var th = new Array();
	function isConfirm() {
		var con;
		con = confirm("确认提交？");
		if(con==true) {
			for(var i=1;i<=10;i++) {
				var answerss = document.getElementById("rightanswer"+i).innerText;
				rightAnswer = rightAnswer + answerss;
				var answerTh = document.getElementById("th"+i).innerText;
				th[i-1] = answerTh;
				var radName = document.getElementsByName("rad"+i);
				for(var j=0;j<radName.length;j++) {
					if(radName[j].checked) {
						switch (j) {
							case 0:
								allanswer[i-1] = "A";
							break;
							
							case 1:
								allanswer[i-1] = "B";
							break;
								
							case 2:
								allanswer[i-1] = "C";
							break;
								
							case 3:
								allanswer[i-1] = "D";
							break;
							default:
							break;
						}
					}
				}
				if(allanswer[i-1]==null){
					allanswer[i-1] = "0";
					alert("你第"+i+"题还没选择，确认提交？");
				}
			}
			document.getElementById("score").innerHTML = calScore();
			var form=document.querySelector("#qryform");
			form.action="/ImitateTest/examrecords.do?starttime="+document.getElementById("starttime").innerText
					+"&timeing="+document.getElementById("timeing").innerText+"&score="+document.getElementById("score").innerHTML
					+"&chooseid="+document.getElementById("chooseid").innerText;
			form.submit();
			alert("提交成功！");
		}else {
			alert("提交失败！");
		}
	}
	
	/* 计算分数 */
	var score = 0;
	function calScore() {
		for(var i=0;i<10;i++) {
			if(allanswer[i]==rightAnswer[i]) {
				score = score + 10;
			}
		}
		return score;
	}
	
	/* 点击下一题时 以此存放答案 */
	var allanswer = new Array(); //全局变量,存放用户选的答案
	function nextAnswer() {
		if(userChecked()!=null){
			allanswer = allanswer+userChecked(); //答案
			var radios = document.getElementsByName("rad2");
			for(var i=0;i<radios.length;i++) {
				radios[i].checked=false;
			}
		 }else {
			var con;
			con = confirm("你还没选择答案，确认下一题？");
			if(con==true) {
				allanswer = allanswer+" ";
				document.getElementById("i").innerHTML=++queNumber;
			}else {
				alert("没有下一题！");
			}
		}
	}
	
</script>

<body onload="time()">
	<div>
		<form id="qryform" action="" method="post">
		<h4>
			考试时间：<label id="starttime" name="starttime"></label>&nbsp&nbsp&nbsp
			考试用时：<label id="timeing" name="timeing"></label>
			<label id="score" name="score" type="hidden"></label>
			<label id="chooseid" name="chooseid" style="display: none">${xkid}</label>
		</h4>
		</form>
	<c:forEach var="q" items="${vo}" varStatus="vs" >
        <div> <!--题面模块-->
            <h3>第<label>${vs.count}</label>题</h3>
            <h4 id="stem">${q.stem}</h4>
            <ol class="options">
                <li id="A">
                	<input type="radio" name="rad${vs.count}" onclick="userChecked()">${q.sec1}
                </li>
                <li id="B">
                	<input type="radio" name="rad${vs.count}" onclick="userChecked()">${q.sec2}
                </li>
                <li id="C">
                	<input type="radio" name="rad${vs.count}" onclick="userChecked()">${q.sec3}
                </li>
                <li id="D">
                	<input type="radio" name="rad${vs.count}" onclick="userChecked()">${q.sec4}
                </li>
            </ol>
            <h5>
            	你的答案：<label id="userchoose${vs.count}"></label>
            </h5>
            <h5 style="display: none">
            	正确答案：<label id="rightanswer${vs.count}" style="display: none">${q.answer}</label>
            	<label id="th${vs.count}" name="th" style="display: none">${q.id}</label>
            </h5>
        </div>
        </c:forEach>
        <p>
            <input class="sub_submit" type="submit" value="提交考试结果" onclick="isConfirm()">
        	<!-- <button class="sub_up" type="submit">上一页</button>
            <button class="sub_down" type="submit" onclick="nextAnswer()">下一页</button> -->
        </p>
    </div>
</body>
</html>