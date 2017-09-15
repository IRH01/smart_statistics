<%@ page language="java" pageEncoding="UTF-8" %>
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
    <link rel="stylesheet" href="<c:url value="/css/zTreeStyle.css"/>">
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
    <script src="<c:url value="/lib/jquery-1.11.2.min.js"/>"></script>
    <script src="<c:url value="/lib/bootstrap.min.js"/>"></script>
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
        <h3>
            <strong>请输入新密码:</strong>
        </h3>
        <hr/>
        <form action="<c:url value="./resetPassWord.do"/>" method="post"
              id="reset" name="reset" class="form-horizontal">
            <input type="hidden" name="username" value="${username}">
            <div class="form-group form-group-sm">
                <label class="control-label col-sm-2">新密码:</label>
                <div class="col-sm-4">
                    <input class="form-control" name="password" id="password"
                           type="password">
                </div>
            </div>
            <div class="form-group form-group-sm">
                <label class="control-label col-sm-2">重新输入新密码:</label>
                <div class="col-sm-4">
                    <input class="form-control" name="repassword" id="repassword"
                           type="password">
                </div>
            </div>
            <div class="form-group form-group-sm top30">
                <label class="control-label col-sm-2"></label>
                <div class="col-sm-4 text-center">
                    <button type="submit" class="btn btn-success">确认修改</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    $().ready(function () {
        $("#reset").validate({
            rules: {
                password: {
                    required: true,
                    minlength: 3
                },
                repassword: {
                    required: true,
                    equalTo: "#password"
                }

            },
            messages: {
                password: {
                    required: "请输入密码",
                    minlength: "请输入至少三位数密码"
                },
                repassword: {
                    required: "请输入确认密码",
                    equalTo: "两次输入密码不一致"
                }

            }
        });

    });
</script>