<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta charset="IUTF-8">
<title>新增页面</title>
<link href="<%= basePath%>resources/css/upinsert.css" rel="stylesheet" type="text/css"> 
</head>
<body>
<form action ="<%=basePath %>/Auto/FinalInsert" id="insertForm" method="post">
 <div class="bg">
	    <div class="inputBox">
	       <div class="toinput">
			名称
			<div >
			<input type="text"  name = "commandname" >
		     </div>
		      
			 描述
			 <div >
			<input type="text" name = "description" >
		     </div>
		
		      <input   type="submit"  value="提交">
		      </div>
        </div>
</div>
</form>
</body>
</html>