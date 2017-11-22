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
    <jsp:include page="../../template/header.jsp"/>
    <jsp:include page="../../template/menu.jsp"/>

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
                <div class="panel-group" id="accordion" role="tablist"
                     aria-multiselectable="true">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingOne">
                            <h4 class="panel-title" id="-collapsible-group-item-#1-">
                                <a data-toggle="collapse" data-parent="#accordion"
                                   href="#collapseOne" aria-expanded="true"
                                   aria-controls="collapseOne" class=""> 创建新用户 </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in"
                             role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
                            <div class="panel-body">
                                <form class="form-horizontal" name="form" id="form"
                                      action="/admin/admin/save.do" method="post">
                                    <fieldset>
                                        <div class="form-group form-group-sm">
                                            <label class="control-label col-sm-2" for="username"><em
                                                    class="red-star">*</em>用户名:</label>
                                            <div class="col-sm-3">
                                                <input class="form-control" name="username" type="text"
                                                       id="username"/>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="control-label col-sm-2" for="password"><em
                                                    class="red-star">*</em>密码:</label>
                                            <div class="col-sm-3">
                                                <input class="form-control" type="password" id="password"
                                                       name="password">
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="control-label col-sm-2" for="name"><em
                                                    class="red-star">*</em>姓名:</label>
                                            <div class="col-sm-3">
                                                <input class="form-control" type="text" maxlength="20"
                                                       id="name" name="name">
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="control-label col-sm-2" for="type">类型:</label>
                                            <div class="col-sm-3">
                                                <select class="form-control" name="type" id="type">
                                                    <c:forEach items="${typeMap}" var="item">
                                                        <option value="${item.key}">${item.value}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="control-label col-sm-2" for="email">邮箱:</label>
                                            <div class="col-sm-3">
                                                <input class="form-control" name="email"
                                                       type="text" id="email"/>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="control-label col-sm-2" for="tel">电话:</label>
                                            <div class="col-sm-3">
                                                <input class="form-control" name="tel" maxlength="15"
                                                       type="text" id="tel"/>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="control-label col-sm-2" for="jobNo">工号:</label>
                                            <div class="col-sm-3">
                                                <input class="form-control" name="jobNo" maxlength="15"
                                                       type="text" id="jobNo"/>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="control-label col-sm-2" for="channel">渠道:</label>
                                            <div class="col-sm-3">
                                                <input class="form-control" type="text" id="channel"
                                                       name="channel"
                                                       onclick="onShowCompanyTree();" readonly> <input
                                                    class="form-control" type="hidden" id="channelIds"
                                                    name="channelIds">
                                                <div id="menuContent" class="menuContent"
                                                     style="display: none; position: absolute;width:360px;border: 1px solid #ccc;left:15px;position: absolute; z-index: 10;">
                                                    <ul id="treeDemo" class="ztree"
                                                        style="margin-top: 0; width: 160px;width: 358px;background-color: white;"></ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="control-label col-sm-2" for="channelName">渠道名称:</label>
                                            <div class="col-sm-3">
                                                <span id="channelName"></span>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="control-label col-sm-2" for="channelName">关联游戏:</label>
                                            <div class="col-sm-3">
                                                <span id="platformName"></span>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="control-label col-sm-2"></label>
                                            <div class="col-sm-3">
                                                <button type="submit" class="btn btn-primary">保存</button>
                                                <button type="button" class="btn btn-primary" onclick="history.go(-1)">
                                                    返回
                                                </button>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
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
    $().ready(function () {
        $("#form").validate({
            rules: {
                username: {
                    required: true,
                    remote: "/validateUserName.do",
                    maxlength: 32
                },
                password: {
                    required: true,
                    rangelength: [3, 15]
                },
                name: {
                    required: true,
                    maxlength: 20
                },
                email: {
                    email: true,
                    maxlength: 50
                },
                tel: {
                    maxlength: 15
                },
                jobNo: {
                    maxlength: 15
                }
            },
            messages: {
                username: {
                    remote: "用户名已经存在！"
                },
                email: {
                    email: "请输入正确的邮箱格式！"
                }
            }
        });
    });

    function onShowCompanyTree() {
        var compamyinput = $("#channel");
        $("#menuContent").css({top: compamyinput.outerHeight() + "px"}).slideDown("fast");
        $("body").bind("mousedown", onCompanyBodyDown);
    }

    function hideCompanyMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onCompanyBodyDown);
    }

    function onCompanyBodyDown(event) {
        if (!(event.target.id == "channel" || $(event.target).parents("#menuContent").length > 0)) {
            hideCompanyMenu();
        }
    }
</script>
