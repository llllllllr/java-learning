<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新界面</title>
<link href="<%=basePath %>resources/css/upinsert.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<%
   String sendID= (String) request.getAttribute("id");
%>
<form  action="UpdateServlet" method="post">
<input type="hidden" name ="sendid" value=<%=sendID %>>
	
	<div class="bg">
	    <div class="inputBox">
	       <div class="toinput">
			名称
			<div >
			<input type="text"  name = "newname" >
		     </div>
		      
			 描述
			 <div >
			<input type="text" name = "newdes" >
		     </div>
		
		      <input   type="submit"  value="提交">
		      </div>
        </div>
</div>






</form>
</body>
</html>