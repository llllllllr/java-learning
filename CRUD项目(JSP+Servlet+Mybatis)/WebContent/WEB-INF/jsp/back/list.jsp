<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="X-UA-Compatible"content="IE=9; IE=8; IE=7; IE=EDGE" />
		<title>内容列表页面</title>
		<link href="<%= basePath %>resources/css/all.css"  rel="stylesheet" type="text/css" />
		<script src="<%= basePath %>resources/js/common/jquery-1.8.0.min.js"></script>
		<script src="<%= basePath %>resources/js/back/list.js"></script>
	</head>
	<body style="background: #e1e9eb;">
	<form action="<%= basePath %>/Auto/ListServlet" id="mainForm" method="post">
		<input type="hidden" name="currentPage" id="currentPage" value="${page.currentPage}"/>
		
		<div class="right">
			<div class="current">当前位置：<a href="javascript:void(0)" style="color:#6E6E6E;">内容管理</a> &gt; 内容列表</div>
			<div class="rightCont">
				<p class="g_title fix">内容列表 <a class="btn03" href="javascript:insertOne('<%= basePath%>');">新 增</a>&nbsp;&nbsp;&nbsp;&nbsp;<a class="btn03" href="javascript:deleteBatch('<%=basePath%>');">删 除</a></p>
				<table class="tab1">
					<tbody>
						<tr>
							<td width="90" align="right">指令名称：</td>
							<td>
								<input name="name" type="text" class="allInput" value="${name}"/>
							</td>
							<td width="90" align="right">描述：</td>
							<td>
								<input name="description" type="text" class="allInput" value="${description}"/>
							</td>
                            <td width="85" align="right"><input type="submit" class="tabSub" value="查 询" /></td>
       					</tr>
					</tbody>
				</table>
				<div class="zixun fix">
					<table class="tab2" width="100%">
						<tbody>
							<tr>
							    <th><input type="checkbox" id="all" onclick="#"/></th>
							    <th>序号</th>
							    <th>指令名称</th>
							    <th>描述</th>
							    <th>操作</th>
							</tr>
							<c:forEach items="${messageList}" var="message" varStatus="status">
							<tr   <c:if test="${status.index%2 !=0 }">style='background-color:#ECF6EE;'</c:if>>
							         <td><input type="checkbox" name="id" value="${message.id }"></input></td>
									<td>${status.index+1}</td>
									<td>${message.name }</td>
									<td>${message.description }</td>
							     <td>
							        <a href="javascript:updateCommand('<%= basePath%>');">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
							     </td>
							     </tr>
							 </c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
    </form>
	</body>
</html>