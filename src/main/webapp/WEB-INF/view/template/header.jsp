<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/template/taglib.jsp"%>
<!-- topbar starts -->
<div class="navbar navbar-sdk">
	<div class="navbar-inner">
		<div class="container-fluid">
			<!-- <a class="navbar-header" href="#"><img alt="Charisma"
				src="<c:url value="/img/logo.png"/>" width="40px"><span>体育资讯管理平台</span></a>
			 -->
				
			<a class="navbar-header" href="#"><span>产品运营数据统计后台</span></a>
			<div class="pull-right top-right">
				<span class="name">您好，${session_user.username}！</span> <a
					class="user-info" href="/sys/user/editPasswd.do">修改密码</a>
				<a class="btn-out" href="/logout.do">退出系统</a>
			</div>
		</div>
	</div>
</div>