<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>产品运营数据统计后台</title>
    <link rel="stylesheet" href="../../../../lib/bootstrap.min.css">
    <link rel="stylesheet" href="../../../../css/style.css">
    <link rel="stylesheet" href="../../../../css/zTreeStyle.css">
    <link rel="stylesheet" href="../../../../css/dialogsdk.css">
    <link rel="stylesheet" href="../../../../css/jquery-ui.css">
    <link rel="shortcut icon" href="../../../../img/favicon.ico">
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="../../../../lib/jquery-1.11.2.min.js"></script>
    <script src="../../../../lib/bootstrap.min.js"></script>
    <script src="../../../../lib/jquery.ztree.all-3.5.min.js"></script>
    <script src="../../../../lib/jquery.validate.js"></script>
    <script src="../../../../lib/additional-methods.min.js" type="text/javascript"></script>
    <script src="../../../../lib/jquery-validate.bootstrap-tooltip.js" type="text/javascript"></script>
    <script src="../../../../lib/jquery.validate.custom.js" type="text/javascript"></script>
    <script src="../../../../lib/My97DatePickerBeta/My97DatePicker/WdatePicker.js"></script>
    <script src="../../../../lib/dialogsdk.js"></script>
    <script src="../../../../lib/tools/tools.js"></script>
    <script src="../../../../lib/layer/layer.js"></script>
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
                        <li>列表</li>
                    </ul>
                </div>
                <!--body start-->
                <div class="panel-group" id="accordion" role="tablist"
                     aria-multiselectable="true">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingOne">
                            <h4 class="panel-title" id="-collapsible-group-item-#1-">
                                <a data-toggle="collapse" data-parent="#accordion"
                                   href="#collapseOne" aria-expanded="true"
                                   aria-controls="collapseOne" class="">角色管理 </a>
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
                                      action="/sys/role/list.do" method="post">
                                    <fieldset>
                                        <div class="form-group form-group-sm right20">
                                            <label class="control-label" for="name">角色名：</label> <input
                                                class="form-control" type="text" id="name" name="name"
                                                value="${role.roleName}">
                                        </div>
                                        <div class="form-group form-group-sm right20">
                                            <label class="control-label" for="id">角色编号：</label> <input
                                                class="form-control" type="text"
                                                onkeyup="this.value=this.value.replace(/\D/g,'')"
                                                onafterpaste="this.value=this.value.replace(/\D/g,'')" id="id"
                                                name="id" value="${role.id}">
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <button type="submit" class="btn btn-primary btn-sm">
                                                <i class="icon-search icon-white"></i>查&nbsp;&nbsp;询
                                            </button>
                                            &nbsp;&nbsp;
                                            <button type="submit" class="btn btn-primary btn-sm"
                                                    onclick="changeAction('/sys/role/add.do')">
                                                <i class="icon-plus icon-white"></i>创建角色
                                            </button>
                                        </div>
                                    </fieldset>
                                </form>
                                <table class="table table-striped table-bordered top20">
                                    <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>角色名</th>
                                        <th>创建时间</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${null != roleList}">
                                        <c:forEach var="role" items="${roleList}">
                                            <tr>
                                                <td class="center">${role.id}</td>
                                                <td class="center">${role.roleName}</td>
                                                <td class="center"><fmt:formatDate
                                                        value="${role.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                                </td>
                                                <td>
                                                    <button type="submit" class="btn btn-primary btn-xs"
                                                            onclick="confirmAction('/sys/role/${role.id}/delete.do')">
                                                        <i class="icon-remove icon-white"></i>删除
                                                    </button>
                                                    <button type="submit" class="btn btn-primary btn-xs"
                                                            onclick="changeAction('/sys/role/${role.id}/preupdate.do')">
                                                        <i class="icon-edit icon-white"></i>分配权限
                                                    </button>
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
                <!--body end-->
            </div>
        </div>
        <hr>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    function changeAction(url) {
        document.form.action = url;
        document.form.submit();
    }

    function confirmAction(url) {
        layer.confirm("确定删除角色？", {
            btn: ["确认", "取消"] //可以无限个按钮
        }, function (index, layero) {
            document.form.action = url;
            document.form.submit();
        });
    }
</script>