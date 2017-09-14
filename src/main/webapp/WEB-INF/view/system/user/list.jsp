<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/template/taglib.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>产品运营数据统计后台</title>
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/zTreeStyle.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/dialogsdk.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/jquery-ui.css"/>">
    <link rel="shortcut icon" href="<c:url value="/img/favicon.ico"/>">
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="<c:url value="/lib/jquery-1.11.2.min.js"/>"></script>
    <script src="<c:url value="/lib/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/lib/jquery.ztree.all-3.5.min.js"/>"></script>
    <script src="<c:url value="/lib/jquery.validate.js"/>"></script>
    <script src="<c:url value="/lib/additional-methods.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/lib/jquery-validate.bootstrap-tooltip.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/lib/jquery.validate.custom.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/lib/My97DatePickerBeta/My97DatePicker/WdatePicker.js"/>"></script>
    <script src="<c:url value="/lib/dialogsdk.js"/>"></script>
    <script src="<c:url value="/lib/tools/tools.js"/>"></script>
    <script src="<c:url value="/lib/layer/layer.js"/>"></script>
<body>
<div class="wrap">
    <jsp:include page="../../../view/template/header.jsp"/>
    <jsp:include page="../../../view/template/menu.jsp"/>

    <div class="container-fluid content">
        <div class="row">
            <div id="content" class="col-lg-10 col-md-9">
                <div class="sdk-content">
                    <ul class="breadcrumb">
                        <li>您当前的位置：</li>
                        <tags:breadcrumb/>
                        <li><a href="#">全部用户管理</a></li>
                    </ul>
                </div>
                <!--body start-->
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingOne">
                            <h4 class="panel-title" id="-collapsible-group-item-#1-">
                                <a data-toggle="collapse" data-parent="#accordion"
                                   href="#collapseOne" aria-expanded="true"
                                   aria-controls="collapseOne" class=""> 全部用户管理 </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in"
                             role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
                            <div class="panel-body">
                                <c:if test="${null != msg}">
                                    <div class="alert alert-info">
                                        <i class="icon-info-sign"></i>${msg}
                                    </div>
                                </c:if>
                                <form name="form" class="form-inline"
                                      action="/sys/user/list.do" method="post">
                                    <fieldset>
                                        <div class="form-group right20">
                                            <label class="control-label" for="username">用户名：</label> <input
                                                class="form-control input-sm" type="text" id="username"
                                                name="username" placeholder="username" value="${user.username}">
                                        </div>
                                        <div class="form-group right20">
                                            <label class="control-label" for="juese">角色名称：</label> <select
                                                id="juese" class="form-control input-sm">
                                            <option>全部角色</option>
                                            <option>系统管理员</option>
                                            <option>运营-CP</option>
                                        </select>
                                        </div>
                                        <div class="form-group right20">
                                            <label class="control-label">用户状态：</label> <select
                                                class="form-control input-sm">
                                            <option>全部</option>
                                            <option>启用</option>
                                            <option>停用</option>
                                        </select>
                                        </div>
                                        <div class="form-group right20">
                                            <shiro:hasPermission name="sys_user_list">
                                                <button type="submit" class="btn btn-primary btn-sm">
                                                    <i class="icon-search icon-white"></i>&nbsp;查&nbsp;&nbsp;询&nbsp;
                                                </button>
                                                &nbsp;&nbsp;
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="sys_user_add">
                                                <button type="submit" class="btn btn-primary btn-sm"
                                                        onclick="tijiao('add.do')">
                                                    <i class="icon-plus icon-white"></i>创建用户
                                                </button>
                                            </shiro:hasPermission>
                                        </div>
                                    </fieldset>
                                </form>
                                <table class="table table-striped table-bordered top20">
                                    <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>账号</th>
                                        <th>用户名称</th>
                                        <th>用户角色</th>
                                        <th>用户状态</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${null != userList}">
                                        <c:forEach var="user" items="${userList}">
                                            <tr>
                                                <td class="center">${user.userId}</td>
                                                <td class="center">${user.username}</td>
                                                <td class="center"><fmt:formatDate
                                                        value="${user.createTime}" pattern="yyyy-MM-dd"/></td>
                                                <td>角色名称</td>
                                                <td><c:if test="${user.userStatus == '1'}">
                                                    <shiro:hasPermission name="sys_user_on">
                                                        <button type="submit" class="btn btn-success btn-xs"
                                                                onclick="tijiao('on.do?id=${user.id}')">
                                                            <i class="icon-off icon-white"></i>启用
                                                        </button>
                                                    </shiro:hasPermission>
                                                </c:if> <c:if test="${user.userStatus == '0'}">
                                                    <shiro:hasPermission name="sys_user_off">
                                                        <button type="submit" class="btn btn-danger btn-xs"
                                                                onclick="tijiao('off.do?id=${user.id}')">
                                                            <i class="icon-off icon-white"></i>禁用
                                                        </button>
                                                    </shiro:hasPermission>
                                                </c:if></td>
                                                <td><shiro:hasPermission name="sys_user_initPwd">
                                                    <button type="submit" class="btn btn-danger btn-xs"
                                                            onclick="initPwd(${user.userId})">
                                                        <i class="icon-warning-sign icon-white"></i>重置密码
                                                    </button>
                                                </shiro:hasPermission>&nbsp;&nbsp;&nbsp;&nbsp; <shiro:hasPermission
                                                        name="sys_user_allocRole">
                                                    <button type="submit" class="btn btn-primary btn-xs"
                                                            onclick="tijiao('showRoles.do?id=${user.userId}')">
                                                        <i class="icon-edit icon-white"></i>分配角色
                                                    </button>
                                                </shiro:hasPermission></td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                    </tbody>
                                </table>
                                <div style="text-align: right">
                                    <tags:page page1="${page}"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--body end-->
        </div>
    </div>
    <hr>
</div>
</body>
</html>
<script type="text/javascript">
    function tijiao(url) {
        document.form.action = url;
        document.form.submit();
    }

    function initPwd(id) {
        if (confirm("确认要将密码重置为“123456”吗？") == true) {
            $.post("<c:url value="/sys/user/initPwd.do"/>", {id: id}, function () {
                alert("设置成功！");
            });
        }
    }
</script>