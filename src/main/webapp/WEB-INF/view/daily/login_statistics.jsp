<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="/css/zTreeStyle.css">
    <link rel="stylesheet" href="/css/dialogsdk.css">
    <link rel="stylesheet" href="/css/jquery-ui.css">
    <link rel="shortcut icon" href="/img/favicon.ico">
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="/lib/jquery-1.11.2.min.js"></script>
    <script src="/lib/bootstrap.min.js"></script>
    <script src="/lib/jquery.ztree.all-3.5.min.js"></script>
    <script src="/lib/jquery.validate.js"></script>
    <script src="/lib/additional-methods.min.js" type="text/javascript"></script>
    <script src="/lib/jquery-validate.bootstrap-tooltip.js" type="text/javascript"></script>
    <script src="/lib/jquery.validate.custom.js" type="text/javascript"></script>
    <script src="/lib/My97DatePickerBeta/My97DatePicker/WdatePicker.js"></script>
    <script src="/lib/dialogsdk.js"></script>
    <script src="/lib/tools/tools.js"></script>
    <script src="/lib/layer/layer.js"></script>
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
                        <li>平台综合时段数据统计</li>
                    </ul>
                </div>
                <!--body start-->

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
            $.post("<c:url value="/admin/admin/initPwd.do"/>", {userId: id}, function (result) {
                if (result.status == 1200) {
                    layer.alert("设置成功", {icon: 6});
                }
            });
        });
    }

    function del(id) {
        layer.confirm("是否要刪除该用户？", {
            btn: ["确认", "取消"] //可以无限个按钮
        }, function (index, layero) {
            $.post("<c:url value="/admin/admin/del.do"/>", {
                userId: id
            }, function (result) {
                if (result.status == 1200) {
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