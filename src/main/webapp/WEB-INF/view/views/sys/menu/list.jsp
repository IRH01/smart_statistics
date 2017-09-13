<%@ page language="java" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/view/template/taglib.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<script src="<%=path%>/lib/layer/layer.js"></script>
<div class="sdk-content">
    <ul class="breadcrumb">
        <li>您当前的位置：</li>
        <tags:breadcrumb/>
        <li>列表</li>
    </ul>
    <div class="panel-group" id="accordion" role="tablist"
         aria-multiselectable="true">
        <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingOne">
                <h4 class="panel-title" id="-collapsible-group-item-#1-">
                    <a data-toggle="collapse" data-parent="#accordion"
                       href="#collapseOne" aria-expanded="true"
                       aria-controls="collapseOne" class=""> 菜单管理 </a>
                </h4>
            </div>
            <div id="collapseOne" class="panel-collapse collapse in"
                 role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
                <div class="panel-body">
                    <div class="col-md-4">
                        <div class="col-md-10">
                            <div class="main-menu">
                                <ul id="menuTree" class="ztree">
                                </ul>
                                <shiro:hasPermission name="sys_menu_sort">
                                    <div style="margin: 10px 0 15px 10px">
                                        <input type="button" value="保存" class='btn btn-primary'
                                               onclick="sortMenu()"/>
                                    </div>
                                </shiro:hasPermission>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-8">
                        <form id="form" class="form-horizontal">

                            <input type="hidden" name="id" id="menuId"/>

                            <div class="form-group form-group-sm">
                                <label class="col-sm-2 control-label">权限:</label>
                                <div class="col-sm-6">
                                    <input type="text" name="permission" id="permission" class="form-control"
                                           value="" onclick="onShowPermissionTree()"/>
                                    <div id="permissionContent" class="permissionContent"
                                         style="display: none; position: absolute;width:480px;border: 1px solid #ccc;left:15px;position: absolute; z-index: 10;">
                                        <ul id="treePermission" class="ztree"
                                            style="margin-top: 0; width: 478px;background-color: white;"></ul>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label class="col-sm-2 control-label">菜单名称:</label>
                                <div class="col-sm-6">
                                    <input type="text" name="name" class="form-control" value=""/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label class="col-sm-2 control-label">菜单url:</label>
                                <div class="col-sm-6">
                                    <input type="text" name="url" class="form-control" value=""/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <div class="col-sm-2"></div>
                                <div class="col-sm-6">
                                    <shiro:hasPermission name="!sys_menu_update">
                                        <input type="button" value="保存" class='btn btn-primary'
                                               onclick="updateMenu()"/>
                                    </shiro:hasPermission>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<SCRIPT type="text/javascript">

    var menuTree, rMenu;
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
            beforeDrag: beforeDrag,
            beforeDrop: beforeDrop,
            onRightClick: OnRightClick,
            onClick: showMenuDetail
        }
    };

    function updateMenu() {
        var selectedNode = menuTree.getSelectedNodes()[0];
        if (!selectedNode) {
            layer.alert("请选择菜单", {
                icon: 6
            });
            return false;
        }
        var menuId = selectedNode.id;
        if (!menuId || menuId == "") {
            layer.alert("请先保存菜单", {
                icon: 6
            });
            return false
        }
        $("#menuId").val(menuId);
        var url = "<%=path%>/sys/menu/update.do"
        $.post(url, $("#form").serialize(), function (text) {
            if (text == "SUCCESS") {
                selectedNode.name = $("#form").find("input[name='name']").val();
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

    function showMenuDetail(event, treeId, treeNode, clickFlag) {
        //清空全部数据
        $("#form").find("input[type!='button']").val("");
        var menuId = treeNode.id;
        //存在则查询展示 否则只展示菜单名称
        if (menuId) {
            $("#menuId").val(menuId);
            var url = "<%=path%>/sys/menu/menuDetail.do"
            $.post(url, {id: menuId}, function (json) {
                $("#form").find("input[name='name']").val(json.name);
                $("#form").find("input[name='permission']").val(json.permission);
                $("#form").find("input[name='url']").val(json.url);
                setPermissionContentValue(json.permission);
            }, "json");
        } else {
            $("#form").find("input[name='name']").val(treeNode.name);
        }
    }

    function OnRightClick(event, treeId, treeNode) {
        if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
            menuTree.cancelSelectedNode();
            showRMenu("root", event.clientX, event.clientY);
        } else if (treeNode && !treeNode.noR) {
            menuTree.selectNode(treeNode);
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
        rMenu.css({"top": (y - sdkContent.top) + "px", "left": (x - sdkContent.left) + "px", "visibility": "visible"});

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
        if (menuTree.getSelectedNodes()[0]) {
            newNode.checked = menuTree.getSelectedNodes()[0].checked;
            menuTree.addNodes(menuTree.getSelectedNodes()[0], newNode);
        } else {
            menuTree.addNodes(null, newNode);
        }
    }

    function sortMenu() {
        var url = "<%=path%>/sys/menu/sort.do"
        var data = {menuTree: JSON.stringify(menuTree.getNodes())};
        $.post(url,
            data,
            function (json) {
                $.each(json, function (tId, id) {
                    menuTree.getNodeByTId(tId).id = id;
                })
                layer.alert("保存成功", {
                    icon: 6
                });
            },
            "json");
    }


    function removeTreeNode() {
        hideRMenu();
        var nodes = menuTree.getSelectedNodes();
        if (nodes && nodes.length > 0) {
            layer.confirm("是否要刪除该菜单？", {
                btn: ["确认", "取消"] //可以无限个按钮
            }, function (index, layero) {
                menuTree.removeNode(nodes[0]);
                layer.close(index);
            });
        }
    }


    $(document).ready(function () {
        $.post("<%=path%>/sys/menu/menuList.do",
            function (json) {
                $.fn.zTree.init($("#menuTree"), funcSetting, json);
                menuTree = $.fn.zTree.getZTreeObj("menuTree");
                <shiro:hasPermission name="!sys_menu_update">
                menuTree.setting.edit.showRenameBtn = true;
                </shiro:hasPermission>
                <shiro:hasPermission name="!sys_menu_delete">
                menuTree.setting.edit.showRemoveBtn = true;
                </shiro:hasPermission>
                rMenu = $("#rMenu");
            },
            "json");
    });


    var PermissionSetting = {
        check: {
            enable: true,
            chkStyle: "radio",
            radioType: "all"
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

        var zTree = $.fn.zTree.getZTreeObj("treePermission");
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
        $("#permission").val(v);
    }

    function onShowPermissionTree() {
        var compamyinput = $("#permission");
        var companyinputOffset = $("#permission").offset();
        $("#permissionContent").css({top: compamyinput.outerHeight() + "px"}).slideDown("fast");
        $("body").bind("mousedown", onPermissionBodyDown);
    }


    function hidePermissionContent() {
        $("#permissionContent").fadeOut("fast");
        $("body").unbind("mousedown", onPermissionBodyDown);
    }


    function onPermissionBodyDown(event) {
        if (!(event.target.id == "permission" || $(event.target).parents("#permissionContent").length > 0)) {
            hidePermissionContent();
        }
    }

    function setPermissionContentValue(permissionId) {
        $.ajax({
            url: "<%=path%>/sys/role/" + Math.random() + "/tree.do",
            dataType: "json",
            success: function (data) {
                if (null != data && data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        //非叶子节点
                        if (!data[i].leaf) {
                            data[i].chkDisabled = true;
                            data[i].nocheck = true;
                        } else {
                            data[i].chkDisabled = false;
                            data[i].nocheck = false;
                            if (data[i].id == permissionId) {
                                data[i].checked = true;
                            }
                        }
                    }
                }
                $.fn.zTree.init($("#treePermission"), PermissionSetting, data);
                zTree_Menu = $.fn.zTree.getZTreeObj("treePermission");
                var node = zTree_Menu.getNodeByParam("id", permissionId);
                if (null != node && undefined != node) {
                    zTree_Menu.selectNode(node);//指定选中ID节点展开
                }
            }
        });
    }

    //-->
</SCRIPT>
<div id="rMenu">
    <ul>
        <shiro:hasPermission name="!sys_menu_add">
            <li id="m_add" onclick="addTreeNode();">增加节点</li>
        </shiro:hasPermission>
        <shiro:hasPermission name="!sys_menu_delete">
            <li id="m_del" onclick="removeTreeNode();">删除节点</li>
        </shiro:hasPermission>
    </ul>
</div>