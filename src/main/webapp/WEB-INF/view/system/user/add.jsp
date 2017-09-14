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
    <jsp:include page="../../../view/template/header.jsp"/>
    <jsp:include page="../../../view/template/menu.jsp"/>

    <div class="container-fluid content">
        <div class="row">
            <div id="content" class="col-lg-10 col-md-9">
                <div class="sdk-content">
                    <ul class="breadcrumb">
                        <li>您当前的位置：</li>
                        <tags:breadcrumb/>
                        <li>创建新用户</li>
                    </ul>
                </div>
                <!--body start-->
                <div class="row-fluid sortable">
                    <div class="box span12">
                        <div class="box-header well" data-original-title>
                            <h2></h2>
                            <div class="box-icon">
                                <a href="#" class="btn btn-minimize btn-round">
                                    <i class="icon-chevron-up"></i>
                                </a>
                                <a href="#" class="btn btn-close btn-round">
                                    <i class="icon-remove"></i>
                                </a>
                            </div>
                        </div>

                        <div class="box-content">
                            <h4>创建新用户：</h4>
                            <hr/>
                            <form id="form" name="form" class="form-horizontal"
                                  action="/sys/user/save.do" method="post">
                                <div class="control-group">
                                    <p class="clearfix">
                                        <label class="control-label" for="username" style="width: 80px">用户名：</label>
                                        <input name="username" type="text" id="username"/>
                                    </p>
                                    <p class="clearfix" style="margin: 20px 0;">
                                        <label class="control-label" for="password" style="width: 80px">密码：</label>
                                        <input name="password" type="password" id="password"/>
                                    </p>
                                    <p class="clearfix" style="margin: 20px 0;">
                                        <label class="control-label" for="password" style="width: 80px">确认密码：</label>
                                        <input name="confirmPassword" type="password" id="confirmPassword"/>
                                    </p>
                                    <shiro:hasPermission name="sys_user_add">
                                        <button type="submit" class="btn btn-primary">保存</button>
                                    </shiro:hasPermission>
                                    <button type="button" class="btn btn-primary" onclick="history.go(-1)">返回</button>
                                </div>
                            </form>
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
    $().ready(function () {
        $("#form").validate({
            rules: {
                "username": {
                    required: true
                },
                "password": {
                    required: true
                },
                "confirmPassword": {
                    required: true,
                    equalTo: "#password"
                }
            },
            messages: {
                "username": {
                    required: "用户名不能为空!"
                },
                "password": {
                    required: "密码不能为空!"
                },
                "confirmPassword": {
                    required: "确认密码不能为空！",
                    equalTo: "确认密码必须与密码填写一致！"
                }
            },
            tooltip_options: {
                "username": {trigger: 'focus'},
                "password": {trigger: 'focus'}
            }
        });
    });

</script>