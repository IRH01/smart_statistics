<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/template/taglib.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
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
    <jsp:include page="../template/header.jsp"/>
    <jsp:include page="../template/menu.jsp"/>

    <div class="container-fluid content">
        <div class="row">
            <div id="content" class="col-lg-10 col-md-9">
                <div class="sdk-content">
                    <ul class="breadcrumb">
                        <li>您当前的位置：</li>
                        <tags:breadcrumb/>
                        <li>列表</li>
                    </ul>
                </div>
                <!--body start-->
                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingOne">
                            <h4 class="panel-title" id="-collapsible-group-item-#1-">
                                <a data-toggle="collapse" data-parent="#accordion"
                                   href="#collapseOne" aria-expanded="true"
                                   aria-controls="collapseOne" class=""> 用户管理 </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in"
                             role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
                            <div class="panel-body">
                                <form name="form" class="form-inline"
                                      action="<c:url value="/admin/admin/list.do"/>" method="post">
                                    <fieldset>
                                        <div class="form-group form-group-sm right20">
                                            <label class="control-label" for="username">用户名：</label> <input
                                                class="form-control" type="text" id="username" name="username"
                                                value="${condition.username}">
                                        </div>
                                        <div class="form-group form-group-sm right20">
                                            <label class="control-label" for="type">用户类型：</label> <select
                                                class="form-control" id="type" name="type">
                                            <option value="">请选择</option>
                                            <c:forEach items="${typeMap}" var="item">
                                                <option value="${item.key}"
                                                        <c:if test="${item.key == condition.type}">selected="selected" </c:if>>${item.value}</option>
                                            </c:forEach>
                                        </select>
                                        </div>
                                        <div class="form-group form-group-sm right20">
                                            <label class="control-label" for="userStatus">用户状态：</label> <select
                                                class="form-control" id="userStatus" name="userStatus">
                                            <option value="">全部</option>
                                            <c:forEach items="${statusMap}" var="item">
                                                <option value="${item.key}"
                                                        <c:if test="${item.key==condition.userStatus}">selected="selected" </c:if>>${item.value}</option>
                                            </c:forEach>
                                        </select>
                                        </div>
                                        <div class="form-group right20">
                                            <shiro:hasPermission name="admin_admin_list">
                                                <c:if test="${not empty condition.username||not empty condition.userStatus||not empty condition.type}"><input
                                                        type="button" class="btn btn-primary btn-sm"
                                                        onclick="clearSearch()" value='&nbsp;清&nbsp;&nbsp;空&nbsp;'>
                                                    <i class="icon-search icon-white"></i>
                                                    </input>&nbsp;&nbsp;</c:if>
                                                <button type="submit" class="btn btn-primary btn-sm">
                                                    <i class="icon-search icon-white"></i>&nbsp;查&nbsp;&nbsp;询&nbsp;
                                                </button>
                                                &nbsp;&nbsp;
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="admin_admin_add">
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
                                        <th>用户名</th>
                                        <th>姓名</th>
                                        <th>用户类型</th>
                                        <th>用户状态</th>
                                        <th>创建时间</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${null != adminList}">
                                        <c:forEach var="item" items="${adminList}">
                                            <tr>
                                                <td class="center">${item.id}</td>
                                                <td class="center">${item.username}</td>
                                                <td class="center">${item.name}</td>
                                                    <%-- <td class="center">${typeMap[item.type]}</td> --%>
                                                <td class="center">${typeMap[item.type]}</td>
                                                <td class="center">${statusMap[item.userStatus]}</td>
                                                <td class="center"><fmt:formatDate
                                                        value="${item.createTime}"
                                                        pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                <td><shiro:hasPermission name="admin_admin_edit">
                                                    <button type="submit" class="btn btn-primary btn-xs"
                                                            onclick="tijiao('edit.do?id=${item.id}')">
                                                        <i class="icon-edit icon-white"></i>编辑
                                                    </button>
                                                </shiro:hasPermission> <shiro:hasPermission
                                                        name="admin_admin_allocRole">
                                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                                    <button type="submit"
                                                            class="btn btn-primary btn-xs"
                                                            onclick="tijiao('showRoles.do?userId=${item.userId}')">
                                                        <i class="icon-edit icon-white"></i>分配角色
                                                    </button>
                                                </shiro:hasPermission> <shiro:hasPermission
                                                        name="admin_admin_initPwd">
                                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                                    <button type="submit"
                                                            class="btn btn-danger btn-xs"
                                                            onclick="initPwd(${item.userId})">
                                                        <i class="icon-warning-sign icon-white"></i>重置密码
                                                    </button>
                                                </shiro:hasPermission> <c:if test="${item.userStatus == '1'}">
                                                    <shiro:hasPermission name="admin_admin_on">
                                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                                        <button type="submit"
                                                                class="btn btn-success btn-xs"
                                                                onclick="tijiao('on.do?userId=${item.userId}')">
                                                            <i class="icon-off icon-white"></i>启用用户
                                                        </button>
                                                    </shiro:hasPermission>
                                                </c:if> <c:if test="${item.userStatus == '0'}">
                                                    <shiro:hasPermission name="admin_admin_off">
                                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                                        <button type="submit"
                                                                class="btn btn-danger btn-xs"
                                                                onclick="disable('off.do?userId=${item.userId}')">
                                                            <i class="icon-off icon-white"></i>禁用用户
                                                        </button>
                                                    </shiro:hasPermission>
                                                </c:if>
                                                    <shiro:hasPermission name="admin_admin_delete">
                                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                                        <button type="submit"
                                                                class="btn btn-danger btn-xs"
                                                                onclick="del(${item.userId})">
                                                            <i class="icon-off icon-white"></i>刪除
                                                        </button>
                                                    </shiro:hasPermission>
                                                </td>
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
        layer.confirm("确认要将密码重置为密码“123456”吗？", {
            btn: ["确认", "取消"] //可以无限个按钮
        }, function (index, layero) {
            $.post("<c:url value="/admin/admin/initPwd.do"/>", {userId: id}, function () {
                layer.alert("设置成功", {icon: 6});
            });
        });
    }

    function del(id) {
        layer.confirm("是否要刪除该用户？", {
            btn: ["确认", "取消"] //可以无限个按钮
        }, function (index, layero) {
            $.post("<c:url value="/admin/admin/del.do"/>", {
                userId: id
            }, function (data) {
                if ("SUCCESS" == data) {
                    layer.alert("删除成功", {
                        icon: 6
                    });
                    tijiao("<%=request.getContextPath()%>/admin/admin/list.do");
                } else {
                    layer.alert("删除失败", {
                        icon: 5
                    });
                }
            });
        });
    }

    function disable(url) {
        layer.confirm("是否要禁用该用户？禁用后，该用户将无法登陆", {
            btn: ["确认", "取消"]
        }, function (index, layero) {
            document.form.action = url;
            document.form.submit();
        });
    }

    function clearSearch() {
        jQuery("#username").attr("value", "");
        jQuery("#userStatus").attr("value", "");
        jQuery("#type").attr("value", "");
        document.form.action = 'list.do';
        document.form.submit();
    }

</script>