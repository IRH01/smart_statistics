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
                        <li><a href="#">系统管理</a></li>
                        <li><a href="#">全部用户管理</a></li>
                        <li><a href="#">分配角色</a></li>
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
                                   aria-controls="collapseOne" class=""> 分配角色 </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in"
                             role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
                            <div class="panel-body">
                                <form name="form" class="form-horizontal"
                                      action="/sys/user/allocRole.do" method="post">
                                    <div class="control-group">
                                        <input type="hidden" name="id" value="${id}"> <label
                                            class="control-label">角色名</label>
                                        <div class="controls form-inline top20 ">
                                            <c:forEach items="${roleList}" var="role" varStatus="status">
                                                <input type="checkbox" name="role" value="${role.id}"
                                                       <c:if test="${role.owned}">checked="checked" </c:if> />${role.name}&nbsp;&nbsp;
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <shiro:hasPermission name="sys_user_add">
                                        <button type="submit" class="btn btn-primary top20">
                                            <i class="icon-search icon-white"></i>保存
                                        </button>
                                    </shiro:hasPermission>
                                    <button type="button" class="btn btn-primary top20" onclick="history.go(-1)"><i
                                            class="icon-search icon-white"></i>返回
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="menuContent" class="menuContent"
                 style="display: none; position: absolute;">
                <ul id="treeDemo" class="ztree" style="margin-top: 0; width: 160px;"></ul>
            </div>
            <!--/span-->
        </div>

        <!--body end-->
    </div>
</div>
<hr>
</body>
</html>
<script type="text/javascript">
    function changeAction(url) {
        document.form.action = url;
        document.form.submit();
    }

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
            onClick: onCompanyClick
        }
    };

    function onCompanyClick(e, treeId, treeNode) {
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
        hideCompanyMenu();
    }

    function onShowCompanyTree() {
        var compamyinput = $("#permission");
        var companyinputOffset = $("#permission").offset();
        $("#menuContent").css({
            left: companyinputOffset.left + "px",
            top: companyinputOffset.top + compamyinput.outerHeight() + "px"
        }).slideDown("fast");
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

    $(document).ready(function () {
        $.ajax({
            url: "/sys/role/" + Math.random() + "/tree.do",
            dataType: "json",
            success: function (date) {
                $.fn.zTree.init($("#treeDemo"), CompanySetting, date);
            }
        });
    });

</script>
