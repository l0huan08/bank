<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>闫老板大鱼塘</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.1/themes/hot-sneaks/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script language="JavaScript">
	var now, hours, minutes, seconds, timeValue;
	function showtime() {
		now = new Date();
		hours = now.getHours();
		minutes = now.getMinutes();
		seconds = now.getSeconds();
		timeValue = (hours >= 12) ? "下午 " : "上午 ";
		timeValue += ((hours > 12) ? hours - 12 : hours) + " 點";
		timeValue += ((minutes < 10) ? " 0" : " ") + minutes + " 分";
		timeValue += ((seconds < 10) ? " 0" : " ") + seconds + " 秒";
		clock.innerHTML = timeValue;
		setTimeout("showtime()", 1000);
	}
	
	$(document).ready(function() {
		showtime();
	});
	
</script>
</head>
<body>
	<div id="blinkSubject">
		<h1>闫老板大银行(Our Online Bank)</h1>
		<img src="yutang/yutang.jpg" />
		<h2>
			<a href="login.jsp">点击进入</a>
		</h2>
		<h2>热烈欢迎昝老板大驾光临！！！(Welcome All of You!!!)</h2>
		<h2 align="right">昝老板威武！(You are always our best client!)</h2>
		<h2 align="right">昝老板霸气！(Our bank always give You Best Service!)</h2>

		<h1>
			闫老板世界标准时间(Our Standard Time): <span id='clock'></span>
		</h1>
	</div>
</body>
</html>