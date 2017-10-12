<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!-- topbar starts -->
<div class="navbar navbar-sdk">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="navbar-header" href="#"><span>产品运营数据统计后台</span></a>
            <div class="pull-right top-right">
                <span class="name">您好，${session_user.username}！</span> <a
                    class="user-info" href="/sys/user/editPasswd.do">修改密码</a>
                <a class="btn-out" href="/logout.do">退出系统</a>
            </div>
        </div>
    </div>
</div>