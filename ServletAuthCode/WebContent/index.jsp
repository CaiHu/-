<%@ page language="java" contentType="text/html; charset=utf-8"%>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="index.jsp" method="post">
		<img src="servlet/GetAuthCodeServlet" id="authImg" /><a href="#"
			onClick="window.location.reload()">看不清</a><br> <input
			type="text" name="inputCode">
		<%
			String inputCode = (String) request.getParameter("inputCode");
			String authCode = (String) session.getAttribute("authCode");
			if (inputCode != null) {
				if (authCode.equalsIgnoreCase(inputCode)) {
					out.print("验证码正确！");
				} else {
					out.print("验证码错误！请重新输入！");
				}
			}
		%>
		<br> <input type="submit" value="提交">
	</form>
</body>
</html>