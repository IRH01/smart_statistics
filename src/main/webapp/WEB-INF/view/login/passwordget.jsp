<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>产品运营数据统计后台</title>

    <!-- Bootstrap -->
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>">
    <!-- The fav icon -->
    <link rel="shortcut icon" href="<c:url value="/img/favicon.ico"/>">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<c:url value="/lib/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/lib/jquery-1.11.2.min.js"/>"></script>
    <script src="<c:url value="/lib/jquery.ztree.all-3.5.min.js"/>"></script>
    <script src="<c:url value="/lib/jquery.validate.js"/>"></script>
    <script src="<c:url value="/lib/additional-methods.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/lib/jquery-validate.bootstrap-tooltip.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/lib/jquery.validate.custom.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/lib/My97DatePickerBeta/My97DatePicker/WdatePicker.js"/>"></script>
</head>
<body>
<div class="navbar navbar-sdk">
    <div class="navbar-inner">
        <div class="container">
            <a class="navbar-header" href="#">
                <img alt="Charisma" src="img/logo.png" width="40px"><span>产品运营数据统计后台丨重设密码</span></a>
        </div>
    </div>
</div>
<div class="container re-password">
    <div class="re-password-ctn">
        <p>
            <strong>亲爱的朋友：</strong>
        </p>
        <p>1. 如果您是渠道用户，请联系CP重置密码；
        <p>2. 如果您是开发者(CP)，请按以下步骤重置密码：
        <p class="top20">
            <strong>开发者(CP)重置密码流程如下：</strong>
        </p>
        <p>第一步:请准确输入您的注册账号(Email地址)；
        <p>
        <div class="re-form">
            <form action="<c:url value="./emailgetPassWord.do"/>"
                  name="forGetPassWord" id="forGetPassWord" method="post"
                  class="form-horizontal">
                <div class="form-group form-group-sm">
                    <div class="col-sm-4">
                        <input class="form-control" name="username" type="text" id="username" maxlength="50"
                               placeholder="请输入注册账号">
                    </div>
                    <div class="col-sm-2">
                        <button type="submit" class="btn btn-success btn-submit">&nbsp;&nbsp;&nbsp;找回密码&nbsp;&nbsp;&nbsp;</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    $().ready(function () {
        $("#forGetPassWord").validate({
            rules: {
                username: {
                    required: true,
                    mail: true,
                    remote: {                                          //验证用户名是否存在
                        type: "POST",
                        url: "validateUserNameEx.do?#username"            //servlet
                    }
                }
            },
            messages: {
                username: {
                    required: "请输入注册账号",
                    mail: "邮箱格式不对",
                    remote: "此邮箱未注册"
                }
            }
        });
    });
</script>