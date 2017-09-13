<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/template/taglib.jsp" %>
<!DOCTYPE html>
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
    <link rel="stylesheet" href="<c:url value="/css/dialogsdk.css"/>">
    <link rel="stylesheet" href="<c:url value="/css/jquery-ui.css"/>">
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
    <script src="<c:url value="/lib/dialogsdk.js"/>"></script>
    <script src="<c:url value="/lib/tools/tools.js"/>"></script>
<body>
<div class="wrap">
    <jsp:include page="../../../view/template/header.jsp"/>
    <div class="container-fluid content">
        <div class="row">
            <jsp:include page="../../../view/template/menu.jsp"/>
            <div id="content" class="col-lg-10 col-md-9">
                <div class="panel-group" id="accordion" role="tablist"
                     aria-multiselectable="true">
                    <div class="panel panel-default">
                        <div id="collapseOne" class="panel-collapse collapse in"
                             role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
                            <div class="panel-body">
                                <div class="clm-box">
                                    <h4 class="clm-title">
                                        <img src="img/ico-clm.png" alt=""/>选择您需要的栏目：
                                    </h4>
                                    <hr style="border-top: 1px solid #ddd"/>
                                    <dl class="clm-ctn" id="clm">
                                        <dt>系统管理</dt>
                                        <dd>
                                            <a href="#">用户管理</a> <a href="#">角色管理</a> <a href="#">菜单管理</a> <a
                                                href="#">权限管理</a>
                                        </dd>
                                    </dl>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr>
    </div>
    <jsp:include page="../../../view/template/footer.jsp"/>
</div>
</body>
</html>
<script>
    $(function () {
        zTree_Menu = $.fn.zTree.getZTreeObj("menu");
        $("#clm").empty();
        $(zTree_Menu.getNodes()).each(function (i, item) {

            if (item.children) {
                var content = "";
                content += "<dt>" + item.name + "</dt>";
                content += "<dd>";
                $(item.children).each(function (j, data) {
                    if (data.isParent) {
                        $(data.children).each(function (j1, data1) {
                            content += ("<a href='" + data1.url + "'>" + data1.name + "</a>");
                        });
                    } else {
                        content += ("<a href='" + data.url + "'>" + data.name + "</a>");
                    }
                });
                content += "</dd>";
                $("#clm").append(content);
            } else {
                if (item.url) {
                    var content = "";
                    content += "<dt>" + item.name + "</dt>";
                    content += "<dd>";
                    content += ("<a href='" + item.url + "'>" + item.name + "</a>");
                    content += "</dd>";
                    $("#clm").append(content);
                }
            }
        });
    });
</script>
