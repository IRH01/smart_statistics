<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.hhly.smartdata.model.authentication.Menu" %>
<%@ page import="java.util.Map" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
    String menuId = request.getParameter("menuId");
    if (menuId != null) {
        Map<Integer, Menu> menuMap = (Map) session.getAttribute("session_user_menu_map");
        if (menuMap.keySet().contains(Integer.valueOf(menuId))) {
            session.setAttribute("menuId", menuId);
        } else {
            session.setAttribute("menuId", 0);
        }
    }
%>
<%--1为管理员--%>
<c:if test="${sessionScope.session_user.userType==1}">
    <div class="col-lg-2 col-md-3  sider-box">
        <div class="panel panel-default" id="menuPanel">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a data-toggle="collapse" data-parent="#menuPanel"
                       href="#collapseMenu" aria-expanded="true"
                       aria-controls="collapseOne" class=""> 菜单</a>
                </h4>
            </div>
            <div class="panel-collapse collapse in" id="collapseMenu"
                 role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
                <div class="main-menu">
                    <ul id="menu" class="ztree">
                    </ul>
                </div>
            </div>
        </div>
        <script>
            var curMenu = null;
            var menuId = null;
            var zTree_Menu = null;
            <c:if test="${null != sessionScope.menuId}">
            menuId = "${sessionScope.menuId}";
            </c:if>
            var setting = {
                view: {
                    showLine: true,
                    selectedMulti: false,
                    dblClickExpand: false
                },
                data: {
                    key: {
                        name: "menuName",
                        url: "url"
                    },
                    simpleData: {
                        enable: true,
                        idKey: "id",
                        pIdKey: "pId",
                        rootPId: null
                    }
                }
            };

            var zNodes = [
                <c:forEach items="${sessionScope.session_user_menu}" varStatus="status" var="menu">
                {
                    id: ${menu.id},
                    pId: ${menu.parentId},
                    menuName: "${menu.menuName}"
                    <c:if test="${null != menu.url && !fn:contains(menu.url, '?')}">,
                    url: "<c:url value="/${menu.url}?menuId=${menu.id}"/>",
                    target: "_self"
                    </c:if>
                    <%--<c:if test="${null != menu.url && fn:contains(menu.url, '?')}">,
                        url: "<c:url value="/${menu.url}&menuId=${menu.id}"/>",
                        target: "_self"
                    </c:if>--%>
                }
                <c:if test="${!status.last}">, </c:if>
                </c:forEach>
            ];

            function leafFilter(node) {
                return !node.isParent && !node.url
            }

            $().ready(function () {
                $.fn.zTree.init($("#menu"), setting, zNodes);
                zTree_Menu = $.fn.zTree.getZTreeObj("menu");
                $(zTree_Menu.getNodesByFilter(leafFilter)).each(function (i, item) {
                    zTree_Menu.removeNode(item);
                });
                if (menuId != null) {
                    curMenu = zTree_Menu.getNodeByParam("id", menuId);
                } else {
                    curMenu = zTree_Menu.getNodes()[0];
                }
                zTree_Menu.selectNode(curMenu);
            });
        </script>
    </div>
</c:if>


<%--其他用户使用不同的菜单--%>
<c:if test="${sessionScope.session_user.userType!=1}">
    <ul class="ztree" style="display: none"></ul>
    <div class="col-lg-2 col-md-3  sider-box">
        <!-- 侧边栏 -->
        <div class="sider-bar">
            <ul class="sider-menu" id="siderMenu">

            </ul>
        </div>
    </div>
    <script>

        $(function () {
            $("#siderMenu").empty();
            $.fn.zTree.init($("#menu"), setting, zNodes);
            zTree_Menu = $.fn.zTree.getZTreeObj("menu");

            $(zTree_Menu.getNodes()).each(function (i, item) {
                if (item.children) {
                    var content = "";
                    content += "<li><a href='javascript:'><span><i class='arrow'></i>" + item.menuName + "</span></a>";
                    content += "<ul class='sub-menu'>";
                    $(item.children).each(function (j, data) {
                        content += ("<li><a href='" + data.url + "'>" + data.menuName + "</a></li>");
                    });
                    content += "</ul>";
                    content += "</li>";
                    $("#siderMenu").append(content);
                } else {
                    if (item.url) {
                        $("#siderMenu").append("<li><a href='" + item.url + "'><span>" + item.menuName + "</span></a></li>");
                    }
                }
            });
            $("#siderMenu li ul").hide();
            $('#siderMenu a').click(function () {
                $(this).find(".arrow").toggleClass("cur").parent().parent().siblings('.sub-menu').slideToggle("fast");
            });
            // 一级菜单最后一个li去掉下划线
            $('#siderMenu>li:last').css('border', 'none');
            // 二级菜单最后一个li去掉下划线
            $('#siderMenu .sub-menu').each(function () {
                $(this).find('li:last').css('border', 'none')
            });
            if (menuId != null) {
                curMenu = zTree_Menu.getNodeByParam("id", menuId);
                $("a[href='" + curMenu.url + "']").toggleClass('on');
                if (curMenu.getParentNode() && curMenu.getParentNode().id != 0) {
                    $("a[href='" + curMenu.url + "']").parent().parent().siblings().addClass('on');
                    $("a[href='" + curMenu.url + "']").parent().parent().slideToggle("fast");
                    $("a[href='" + curMenu.url + "']").parent().parent().parent().find('.arrow').addClass('select');
                }
            }
        });
    </script>
</c:if>