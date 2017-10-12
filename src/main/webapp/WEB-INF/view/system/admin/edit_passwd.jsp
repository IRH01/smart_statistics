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
    <link rel="stylesheet" href="../../../../css/bootstrap.min.css">
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
                        <li>修改密码</li>
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
                                   aria-controls="collapseOne" class="">
                                    修改密码
                                </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in"
                             role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
                            <div class="panel-body">
                                <form class="form-horizontal" name="form" id="form"
                                      action="/sys/user/modifyPasswd.do" method="post">
                                    <input type="hidden" name="userId" id="userId" value="${session_user.userId}">
                                    <fieldset>
                                        <div class="form-group form-group-sm">
                                            <label class="control-label col-sm-2">用户名:</label>
                                            <div class="col-sm-3">
                                                <span class="form-span">${session_user.username}</span>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="control-label col-sm-2" for="password"><em
                                                    class="red-star">*</em>原始密码:</label>
                                            <div class="col-sm-3">
                                                <input class="form-control" type="password" id="password"
                                                       name="password">
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="control-label col-sm-2" for="newPassword"><em
                                                    class="red-star">*</em>新密码:</label>
                                            <div class="col-sm-3">
                                                <input class="form-control" type="password" id="newPassword"
                                                       name="newPassword">
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="control-label col-sm-2" for="confirmPassword"><em
                                                    class="red-star">*</em>确认新密码:</label>
                                            <div class="col-sm-3">
                                                <input class="form-control" type="password"
                                                       id="confirmPassword" name="confirmPassword">
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="control-label col-sm-2"></label>
                                            <div class="col-sm-3">
                                                <button type="submit" class="btn btn-primary"
                                                        onclick="return checkPassword();">保存
                                                </button>
                                                <button type="button" class="btn btn-primary"
                                                        onclick="history.go(-1)">返回
                                                </button>
                                            </div>
                                        </div>
                                    </fieldset>
                                </form>
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

        $("#form").validate({
            rules: {
                password: {
                    required: true,
                    remote: {
                        url: "/sys/user/verifyPasswd.do",
                        type: "post",
                        data: {
                            password: function () {
                                return $("#password").val()
                            },
                            userId: $("#userId").val()
                        }
                    }
                },
                newPassword: {
                    required: true,
                    rangelength: [3, 15]
                },
                confirmPassword: {
                    required: true,
                    rangelength: [3, 15],
                    equalTo: "#newPassword"
                }
            },
            messages: {
                password: {
                    required: "密码不能为空!",
                    remote: "密码输入错误！"
                },
                newPassword: {
                    required: "新密码不能为空!",
                    rangelength: "新密码必须在3-15个字符之间！"
                },
                confirmPassword: {
                    required: "确认密码不能为空！",
                    rangelength: "确认密码必须在3-15个字符之间！",
                    equalTo: "确认密码必须与新密码一致！"
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

    function checkPassword() {
        var password = $("#password").val();

        var newPassword = $("#newPassword").val();

        if (password != null && password != "" && password == newPassword) {
            layer.alert("新密码不能与旧密码一致，请重新输入", {
                icon: 5
            });
            return false;
        }
        return true;
    }

</script>