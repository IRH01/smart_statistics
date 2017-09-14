<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/view/template/taglib.jsp" %>
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
    <script src="/lib/layer/layer.js"></script>
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
                        <li>创建角色</li>
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
                                   aria-controls="collapseOne" class=""> 创建角色 </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in"
                             role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
                            <div class="panel-body">
                                <form id="form" name="form" class="form-horizontal"
                                      action="/sys/role/save.do" method="post">
                                    <fieldset>
                                        <div class="form-group form-group-sm">
                                            <label class="control-label col-sm-2" for="name">角色名:</label>
                                            <div class="col-sm-3">
                                                <input class="form-control" type="text" id="name" name="name"
                                                       value="${role.name}">
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="control-label col-sm-2" for="permission">权限:</label>
                                            <div class="col-sm-3">
                                                <input class="form-control" type="text" id="permission"
                                                       name="permission" value="${role.permission}"
                                                       onclick="onShowCompanyTree()" readonly> <input
                                                    class="form-control" type="hidden" id="permissionIds"
                                                    name="permissionIds" value="${role.permissionIds}">
                                                <div id="menuContent" class="menuContent"
                                                     style="display: none; position: absolute;width:360px;border: 1px solid #ccc;left:15px;position: absolute; z-index: 10;">
                                                    <ul id="treeDemo" class="ztree"
                                                        style="margin-top: 0; width: 160px;width: 358px;background-color: white;"></ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group form-group-sm">
                                            <label class="control-label col-sm-2"></label>
                                            <div class="col-sm-3">
                                                <shiro:hasPermission name="sys_role_add">
                                                    <button type="submit" class="btn btn-primary"
                                                            onclick="return checkForm();">
                                                        <i class="icon-ok icon-white"></i>保存
                                                    </button>
                                                </shiro:hasPermission>
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
                name: {
                    required: true,
                    remote: "<c:url value="/sys/role/validateRoleName.do"/>",
                    maxlength: 32
                },
                permission: {
                    required: true
                }
            },
            messages: {
                name: {
                    remote: "角色名已经存在！"
                }
            }
        });
    });

    var CompanySetting = {
        check: {
            enable: true,
            chkStyle: "checkbox"
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onCheck: onCompanyClick,
        }
    };

    function onCompanyClick() {

        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        var n = "", v = "";
        var nodes = zTree.getCheckedNodes(true);
        nodes.sort(function compare(a, b) {
            return a.id - b.id;
        });
        for (var i = 0, l = nodes.length; i < l; i++) {
            if (nodes[i].isParent == false) {
                n += nodes[i].name + ",";
                v += nodes[i].id + ",";
            }
        }
        if (n.length > 0) {
            n = n.substring(0, n.length - 1) , v = v.substring(0, v.length - 1)
        }
        var permission = $("#permission");
        var companyValue = $("#permissionIds");
        permission.attr("value", n);
        companyValue.attr("value", v);
        //hideCompanyMenu();
    }

    function onShowCompanyTree() {
        var compamyinput = $("#permission");
        var companyinputOffset = $("#permission").offset();
        $("#menuContent").css({top: compamyinput.outerHeight() + "px"}).slideDown("fast");
        $("body").bind("mousedown", onCompanyBodyDown);
    }


    function hideCompanyMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onCompanyBodyDown);
    }


    function onCompanyBodyDown(event) {
        if (!(event.target.id == "permission" || $(event.target).parents("#menuContent").length > 0)) {
            hideCompanyMenu();
        }
    }

    $().ready(function () {
        $.ajax({
            url: "/sys/role/" + Math.random() + "/tree.do",
            dataType: "json",
            success: function (date) {
                $.fn.zTree.init($("#treeDemo"), CompanySetting, date);
            }
        });
    });

    function checkForm() {
        var permissionIds = $('#permissionIds').val();
        var roleName = $('#name').val();
        if (roleName == null || roleName.trim() == "") {
            layer.alert("角色名不能为空", {
                icon: 5
            });
            $('#name').focus();
            return false;
        }
        if (permissionIds == null || permissionIds.trim() == "") {
            layer.alert("权限不能为空", {
                icon: 5
            });
            $('#permissionIds').focus();
            return false;
        }
        return true;
    }

</script>