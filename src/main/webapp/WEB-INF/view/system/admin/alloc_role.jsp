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
    <jsp:include page="../../template/header.jsp"/>
    <jsp:include page="../../template/menu.jsp"/>

    <div class="container-fluid content">
        <div class="row">
            <div id="content" class="col-lg-10 col-md-9">
                <div class="sdk-content">
                    <ul class="breadcrumb">
                        <li>您当前的位置：</li>
                        <tags:breadcrumb/>
                        <li>分配角色</li>
                    </ul>
                </div>
                <!--body start-->
                <div class="panel-group" id="accordion" role="tablist"
                     aria-multiselectable="true">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingOne">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion"
                                   href="#collapseOne" aria-expanded="true"
                                   aria-controls="collapseOne" class=""> 分配角色 </a>
                            </h4>
                        </div>
                        <div class="panel-collapse collapse in"
                             role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
                            <div class="panel-body">
                                <form id="_form" name="form" class="form-horizontal"
                                      action="/admin/admin/allocRole.do" method="post">
                                    <div class="control-group">
                                        <input type="hidden" name="userId" value="${userId}"> <label
                                            class="control-label">角色名</label>
                                        <div class="controls checkbox top10">
                                            <c:forEach items="${roleList}" var="role" varStatus="status">
                                                <label><input id="role" type="checkbox" onclick="checkMove()"
                                                              name="role" value="${role.id}"
                                                              <c:if test="${role.owned}">checked="checked" </c:if> />${role.name}
                                                </label>&nbsp;&nbsp;
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <button type="submit" class="btn btn-primary top20">
                                        <i class="icon-search icon-white"></i>保存
                                    </button>
                                    <button type="button" class="btn btn-primary top20" onclick="history.go(-1)"><i
                                            class="icon-search icon-white"></i>返回
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingTwo">
                            <h4 class="panel-title" id="-collapsible-group-item-#1-">
                                <a data-toggle="collapse" data-parent="#accordion"
                                   href="#collapseOne" aria-expanded="true"
                                   aria-controls="collapseOne" class=""> 菜单列表 </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in"
                             role="tabpanel" aria-labelledby="headingtne" aria-expanded="true">
                            <div class="panel-body">

                                <div class="col-md-4">

                                    <div class="col-md-10">
                                        <div class="main-menu">
                                            <ul id="menuTree" class="ztree">
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-8">
                                    <form id="form" class="form-horizontal"></form>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div id="menuContent" class="menuContent"
                     style="display: none; position: absolute;">
                    <ul id="treeDemo" class="ztree" style="margin-top: 0; width: 160px;"></ul>
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
    function showMenuDetail(event, treeId, treeNode, clickFlag) {
        //清空全部数据
        $("#form").find("input[type!='button']").val("");
        var menuId = treeNode.id;
        //存在则查询展示 否则只展示菜单名称
        if (menuId) {
            $("#menuId").val(menuId);
            var url = "/sys/menu/menuDetail.do";
            $.post(url, {id: menuId}, function (result) {
                var json = result.data;
                $("#form").find("input[name='name']").val(json.name);
                $("#form").find("input[name='permission']").val(json.permission);
                $("#form").find("input[name='url']").val(json.url);
            }, "json");
        } else {
            $("#form").find("input[name='name']").val(treeNode.name);
        }
        ;
    }

    function leafFilter(node) {
        return !node.isParent && !node.url;
    }

    function checkMove() {
        var objs = document.getElementsByName("role");
        var ids = "";
        if (objs.length) {
            for (var i = 0; i < objs.length; i++) {
                if (objs[i].checked) {
                    value = objs[i].value;//
                    ids = value + "," + ids;
                }

            }
        }
        if (ids != "") {
            ids = ids.substring(0, ids.lastIndexOf(","));
            $.post("/sys/menu/menuListByRole.do?", {'roleIds': ids},
                function (result) {
                    $.fn.zTree.init($("#menuTree"), funcSetting, result.data);
                    menuTree = $.fn.zTree.getZTreeObj("menuTree");
                    $(menuTree.getNodesByFilter(leafFilter)).each(function (i, item) {
                        menuTree.removeNode(item);
                    });
                },
                "json");
        } else {
            $.fn.zTree.init($("#menuTree"), funcSetting, null);
            menuTree = $.fn.zTree.getZTreeObj("menuTree");

        }

    }

    var menuTree;
    var funcSetting = {
        view: {
            showLine: true,
            selectedMulti: false
        },
        check: {
            enable: false
        },
        edit: {
            enable: true,
            showRemoveBtn: false,
            showRenameBtn: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: showMenuDetail
        }
    };

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
        $("#_form").validate({
            rules: {
                role: {
                    required: true
                }
            },
            messages: {
                role: {
                    required: "请选择角色",
                }
            }
        });

        $.ajax({
            url: "/sys/role/" + Math.random() + "/tree.do",
            dataType: "json",
            success: function (result) {
                $.fn.zTree.init($("#treeDemo"), CompanySetting, result.date);
            }
        });
        checkMove();
    });

</script>
