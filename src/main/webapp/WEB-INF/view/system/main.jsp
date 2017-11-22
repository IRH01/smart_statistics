<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>产品运营数据统计后台</title>

    <!-- Bootstrap -->
    <link href="../../../lib/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../../../css/style.css">
    <link rel="stylesheet" href="../../../css/zTreeStyle.css">
    <link rel="stylesheet" href="../../../css/dialogsdk.css">
    <link rel="stylesheet" href="../../../css/jquery-ui.css">
    <!-- The fav icon -->
    <link rel="shortcut icon" href="../../../img/favicon.ico">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../../../lib/jquery-1.11.2.min.js"></script>
    <script src="../../../lib/bootstrap.min.js"></script>
    <script src="../../../lib/jquery.ztree.all-3.5.min.js"></script>
    <script src="../../../lib/jquery.validate.js"></script>
    <script src="../../../lib/additional-methods.min.js" type="text/javascript"></script>
    <script src="../../../lib/jquery-validate.bootstrap-tooltip.js" type="text/javascript"></script>
    <script src="../../../lib/jquery.validate.custom.js" type="text/javascript"></script>
    <script src="../../../lib/My97DatePickerBeta/My97DatePicker/WdatePicker.js"></script>
    <script src="../../../lib/dialogsdk.js"></script>
    <script src="../../../lib/tools/tools.js"></script>
<body>
<div class="wrap">
    <jsp:include page="../template/header.jsp"/>
    <jsp:include page="../template/menu.jsp"/>
    <div class="container-fluid content">
        <div class="row">
            <div id="content" class="col-lg-10 col-md-9">
                <div class="panel-group" id="accordion" role="tablist"
                     aria-multiselectable="true">
                    <div class="panel panel-default">
                        <div id="collapseOne" class="panel-collapse collapse in"
                             role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
                            <div class="panel-body">
                                <div class="clm-box">
                                    <h4 class="clm-title">
                                        <img src="../../../img/ico-clm.png" alt=""/>选择您需要的栏目：
                                    </h4>
                                    <hr style="border-top: 1px solid #ddd"/>
                                    <dl class="clm-ctn" id="clm">
                                        <dt>系统管理</dt>
                                        <dd>
                                            <a href="#">用户管理</a>
                                            <a href="#">角色管理</a>
                                            <a href="#">菜单管理</a>
                                            <a href="#">权限管理</a>
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
                content += "<dt>" + item.menuName + "</dt>";
                content += "<dd>";
                $(item.children).each(function (j, data) {
                    if (data.isParent) {
                        $(data.children).each(function (j1, data1) {
                            content += ("<a href='" + data1.url + "'>" + data1.menuName + "</a>");
                        });
                    } else {
                        content += ("<a href='" + data.url + "'>" + data.menuName + "</a>");
                    }
                });
                content += "</dd>";
                $("#clm").append(content);
            } else {
                if (item.url) {
                    var content = "";
                    content += "<dt>" + item.menuName + "</dt>";
                    content += "<dd>";
                    content += ("<a href='" + item.url + "'>" + item.menuName + "</a>");
                    content += "</dd>";
                    $("#clm").append(content);
                }
            }
        });
    });
</script>
