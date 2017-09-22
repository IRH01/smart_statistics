<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                        <tags:breadcrumb/>
                        <li>列表</li>
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
                                   aria-controls="collapseOne" class=""> 权限管理 </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in"
                             role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
                            <div class="panel-body">
                                <div class="col-md-4">
                                    <div class="col-md-10">
                                        <div class="main-menu">
                                            <ul id="funcTree" class="ztree">
                                            </ul>
                                            <div style="margin: 10px 0 15px 10px">
                                                <input type="button" value="保存" class="btn btn-primary"
                                                       onclick="updateFunc()"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-8">
                                    <form id="permForm">
                                        <input onclick="addPerm()" type="button" value="增加权限"
                                               class="btn btn-primary"/> <input type="hidden" name="funcId"
                                                                                id="funcId"/>
                                        <table id="permTable" class="table table-striped table-bordered "
                                               style="margin-top: 20px">
                                            <thead>
                                            <tr>
                                                <th>权限</th>
                                                <th>名称</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                        </table>
                                        <input type="button" class="btn btn-primary" value="保存"
                                               onclick="updatePerms()"/>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="rMenu">
                <ul>
                    <li id="m_add" onclick="addTreeNode();">增加节点</li>
                    <li id="m_del" onclick="removeTreeNode();">删除节点</li>
                </ul>
            </div>
            <!--body end-->
        </div>
    </div>
    <hr>
</div>
</body>
</html>
<script type="text/javascript">
    function deletePerm($this) {
        $($this).parent().parent().remove();
    }

    function addPerm($this) {
        var curNode = funcTree.getSelectedNodes()[0];
        if (!curNode) {
            layer.alert("请选择功能", {
                icon: 6
            });
            return
        }
        if (!curNode.id) {
            layer.alert("请先保存功能", {
                icon: 6
            });
            return
        }
        var groupId = curNode.id;
        var tr = "";
        tr += "<tr group='" + groupId + "' >";
        tr += "<td><input class='form-control input-sm' type='text' name='permission' /></td>";
        tr += "<td><input class='form-control input-sm' type='text' name='name' /></td>";
        tr += "<td><button  onclick='deletePerm(this)' class='btn btn-primary btn-sm'>删除</button></td>";
        tr += "</tr>";
        $("#permTable").append(tr);
    }

    var funcTree, rMenu;
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
            showRemoveBtn: true,
            showRenameBtn: true
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeDrag: beforeDrag,
            beforeDrop: beforeDrop,
            onRightClick: OnRightClick,
            onClick: showPerms
        }
    };

    function updatePerms() {
        var funcId = $("#funcId").val();
        if (funcId == "") {
            layer.alert("请选择功能", {
                icon: 6
            });
            return false
        }
        var perms = [];
        $.each($("tr[group]"), function (i, item) {
            var name = $(item).find("input[name='name']").val();
            var permission = $(item).find("input[name='permission']").val();
            if (permission.trim() != "") {
                perms.push({permission: permission, name: name});
            }
        });

        var data = {funcId: funcId, permsJson: JSON.stringify(perms)};

        var url = "/sys/perm/updatePerms.do"
        $.post(url, data, function (text) {
            if (text == "SUCCESS") {
                layer.alert("保存成功", {
                    icon: 6
                });
            } else {
                layer.alert("保存失败", {
                    icon: 5
                });
            }
        }, "text");

    }

    function showPerms(event, treeId, treeNode, clickFlag) {
        //先全部删除
        $("tr[group]").remove();
        var funcId = treeNode.id;
        if (funcId) {
            $("#funcId").val(funcId);
            var url = "/sys/perm/searchPerms.do"
            $.post(url, {functionId: funcId}, function (json) {
                $.each(json, function (i, perm) {
                    var tr = "";
                    tr += "<tr group='" + perm.functionId + "'>";
                    tr += "<td><input class='form-control input-sm' type='text' name='permission' value='" + perm.permission + "' /></td>";
                    tr += "<td><input class='form-control input-sm' type='text' name='name' value='" + perm.name + "' /></td>";
                    tr += "<td><button onclick='deletePerm(this)' class='btn btn-primary btn-sm' >删除</button></td>";
                    tr += "</tr>";
                    $("#permTable").append(tr);
                })
            }, "json");
        }
    }

    function OnRightClick(event, treeId, treeNode) {
        if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
            funcTree.cancelSelectedNode();
            showRMenu("root", event.clientX, event.clientY);
        } else if (treeNode && !treeNode.noR) {
            funcTree.selectNode(treeNode);
            showRMenu("node", event.clientX, event.clientY);
        }
    }

    function beforeDrag(treeId, treeNodes) {
        for (var i = 0, l = treeNodes.length; i < l; i++) {
            if (treeNodes[i].drag === false) {
                return false;
            }
        }
        return true;
    }

    function beforeDrop(treeId, treeNodes, targetNode, moveType) {
        return targetNode ? targetNode.drop !== false : true;
    }


    function showRMenu(type, x, y) {
        var sdkContent = $(".sdk-content").offset();
        $("#rMenu ul").show();
        if (type == "root") {
            $("#m_del").hide();
        } else {
            $("#m_del").show();
        }
        rMenu.css({
            "top": (y - sdkContent.top) + "px",
            "left": (x - sdkContent.left) + "px",
            "visibility": "visible"
        });

        $("body").bind("mousedown", onBodyMouseDown);
    }

    function hideRMenu() {
        if (rMenu) rMenu.css({"visibility": "hidden"});
        $("body").unbind("mousedown", onBodyMouseDown);
    }

    function onBodyMouseDown(event) {
        if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length > 0)) {
            rMenu.css({"visibility": "hidden"});
        }
    }

    var addCount = 1;

    function addTreeNode() {
        hideRMenu();
        var newNode = {name: "增加" + (addCount++)};
        if (funcTree.getSelectedNodes()[0]) {
            newNode.checked = funcTree.getSelectedNodes()[0].checked;
            funcTree.addNodes(funcTree.getSelectedNodes()[0], newNode);
        } else {
            funcTree.addNodes(null, newNode);
        }
    }

    function updateFunc() {
        var url = "/sys/func/update.do"
        var data = {funcTree: JSON.stringify(funcTree.getNodes())};
        $.post(url, data,
            function (result) {
                layer.alert("保存成功", {
                    icon: 6
                });
                $.each(result.data, function (tId, id) {
                    funcTree.getNodeByTId(tId).id = id;
                })

            },
            "json");
    }


    function removeTreeNode() {
        hideRMenu();
        var nodes = funcTree.getSelectedNodes();
        if (nodes && nodes.length > 0) {
            /**var msg = "确认要删除吗，功能下的权限将会一起删除。\n\n请确认！";
             if (confirm(msg) == true) {
                             funcTree.removeNode(nodes[0]);
                             }**/
            layer.confirm("确认要删除吗，功能下的权限将会一起删除。\n\n请确认！", {
                btn: ["确认", "取消"] //可以无限个按钮
            }, function (index, layero) {
                funcTree.removeNode(nodes[0]);
            });
        }
    }

    var funcNodes = [
        <c:forEach items="${funcList}" varStatus="status" var="func">
        {
            id: ${func.id},
            pId: ${func.parentId},
            name: "${func.name}"
        }<c:if test="${!status.last}">, </c:if>
        </c:forEach>

    ];
    $(document).ready(function () {
        $.fn.zTree.init($("#funcTree"), funcSetting, funcNodes);
        funcTree = $.fn.zTree.getZTreeObj("funcTree");
        rMenu = $("#rMenu");
    });
    //-->
</script>