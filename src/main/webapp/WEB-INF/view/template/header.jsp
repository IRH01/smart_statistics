<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/template/taglib.jsp"%>
<!-- topbar starts -->
<div class="navbar navbar-sdk">
	<div class="navbar-inner">
		<div class="container-fluid">
			<!-- <a class="navbar-header" href="#"><img alt="Charisma"
				src="<c:url value="/img/logo.png"/>" width="40px"><span>体育资讯管理平台</span></a>
			 -->
				
			<a class="navbar-header" href="#"><span>产品运营数据统计后台</span></a>

			<!-- top right -->
			<%--<div class="btn-group pull-right" style="margin-top:7px">--%>
			<%--<button type="button" class="btn btn-sm sdk-user dropdown-toggle" data-toggle="dropdown" aria-expanded="false">--%>
			<%--&lt;%&ndash;${session_user.username} <span class="caret"></span>&ndash;%&gt;--%>
			<%--</button>--%>
			<%--<ul class="dropdown-menu" role="menu">--%>
			<%--<li><a href="<%=path%>/queryProfile.do">个人信息</a></li>--%>
			<%--<li class="divider"></li>--%>
			<%--<li><a href="<%=path%>/logout.do">退出</a></li>--%>
			<%--</ul>--%>
			<%--</div>--%>
			<div class="pull-right top-right">
				<span class="name">您好，${session_user.username}！</span> <a
					class="user-info" href="<c:url value="/sys/user/editPasswd.do"/>">修改密码</a>
				<a class="btn-out" href="<c:url value="/logout.do"/>">退出系统</a>
			</div>
		</div>
	</div>
</div>