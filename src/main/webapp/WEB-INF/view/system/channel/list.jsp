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
                                   aria-controls="collapseOne" class=""> CP渠道管理 </a>
                            </h4>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in"
                             role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
                            <div class="panel-body">
                                <c:if test="${null != msg}">
                                    <div class="alert alert-info">
                                        <i class="icon-info-sign"></i>${msg}
                                    </div>
                                </c:if>
                                <form name="form" class="form-inline"
                                      action="/sys/channel/list.do" method="post">
                                    <fieldset>
                                        <div class="form-group form-group-sm right20">
                                            <label class="control-label">CP名称：</label> <input
                                                class="form-control" type="text" id="companyName" name="cpName"
                                                value="${channel.cpName}"> <input type="hidden"
                                                                                  id="cpId"/>
                                        </div>
                                        <div class="form-group form-group-sm right20">
                                            <label class="control-label">渠道名称：</label> <input
                                                class="form-control" type="text" id="channel" name="channel"
                                                value="${channel.channel}">
                                        </div>
                                        <div class="form-group ">
                                            <shiro:hasPermission name="sys_channel_list">
                                                <button type="submit" class="btn btn-primary btn-sm">
                                                    <i class="icon-search icon-white"></i>查询
                                                </button>
                                            </shiro:hasPermission>
                                        </div>
                                    </fieldset>
                                </form>
                                <table class="table table-striped table-bordered top20">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>CP全称</th>
                                        <th>渠道标识</th>
                                        <th>渠道账号</th>
                                        <th>登录权限</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${null != channelList}">
                                        <c:forEach var="channel" items="${channelList}"
                                                   varStatus="status">
                                            <tr>
                                                <td class="center">${status.index+1}</td>
                                                <td class="center">${channel.cpName}</td>
                                                <td class="center">${channel.channel}</td>
                                                <td class="center">${channel.channelUser}</td>
                                                <td class="center"><c:choose>
                                                    <c:when
                                                            test="${channel.userId !=null  and channel.userStatus == 1}">禁止</c:when>
                                                    <c:otherwise>允许</c:otherwise>
                                                </c:choose></td>
                                                <td><shiro:hasPermission name="sys_channel_edit">
                                                    <c:if
                                                            test="${channel.userId !=null  and channel.userStatus == 1}">
                                                        <button type="submit" class="btn btn-success btn-xs"
                                                                onclick="changeAction('/sys/channel/changeuser.do?userid=${channel.userId}')">
                                                            <i class="icon-off icon-white"></i>允许登陆
                                                        </button>
                                                    </c:if>
                                                    <c:if
                                                            test="${channel.userId !=null  and channel.userStatus == 0}">
                                                        <button type="submit" class="btn btn-danger btn-xs"
                                                                onclick="changeAction('/sys/channel/changeuser.do?userid=${channel.userId}')">
                                                            <i class="icon-off icon-white"></i>禁止登陆
                                                        </button>
                                                    </c:if>
                                                    <c:if
                                                            test="${channel.userId == null or channel.userId == ''}">
                                                        <button type="button" class="btn btn-danger btn-xs"
                                                                disabled>
                                                            <i class="icon-off icon-white"></i>允许登陆
                                                        </button>
                                                    </c:if>
                                                </shiro:hasPermission> <shiro:hasPermission name="sys_channel_edit">
                                                    <c:if
                                                            test="${channel.channelUser ==null or channel.channelUser ==''}">
                                                        <button type="submit" class="btn btn-primary btn-xs"
                                                                onclick="changeAction('/sys/channel/adduser.do?channelId=${channel.id}')">
                                                            <i class="icon-edit icon-white"></i>账号设置
                                                        </button>
                                                    </c:if>
                                                    <c:if
                                                            test="${channel.channelUser !=null and channel.channelUser !=''}">
                                                        <button type="submit" class="btn btn-primary btn-xs"
                                                                disabled>
                                                            <i class="icon-edit icon-white"></i>账号已设置
                                                        </button>

                                                    </c:if>
                                                    <c:if
                                                            test="${channel.userId !=null  and  channel.userId !='' }">
                                                        <button type="submit" class="btn btn-primary btn-xs"
                                                                onclick="confirmUser('${channel.userId}')">
                                                            <i class="icon-edit icon-white"></i>重置密码
                                                        </button>
                                                    </c:if>
                                                    <c:if test="${channel.userId ==null or channel.userId ==''}">
                                                        <button type="submit" class="btn btn-primary btn-xs"
                                                                disabled>
                                                            </i>重置密码
                                                        </button>
                                                    </c:if>
                                                </shiro:hasPermission></td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                    </tbody>
                                </table>
                                <div style="text-align: right">
                                    <tags:page page1="${page}"/>
                                </div>
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
    function changeAction(url) {
        document.form.action = url;
        document.form.submit();
    }

    function confirmUser(channelId) {
        var msg = "请确认是否重置为初始密码6个8(888888)";
        if (confirm(msg) == true) {
            changeAction('/sys/channel/resetuser.do?userid=' + channelId);
        }
    }

    $(function () {
        //添加cp名称自动完成
        $("#companyName").autocomplete({
            source: "/admin/cp/cpCompanyNameAutocomplete.do",//数据来源
            minLength: 1, //最小输入多少个字符触发查询
            select: function (event, ui) {//选择该记录时执行
                $("#companyName").val(ui.item.item.companyName);
                $("#cpId").val(ui.item.item.id);
                return false;
            }
        });

        //添加渠道名称自动完成(带参数)
        $("#channel").autocomplete({
            source: function (request, response) {
                var term = request.term;
                var cpId = $("#cpId").val();
                $.getJSON("/sys/channel/autocomplete.do?cpId=" + cpId, request, function (data, status, xhr) {
                    response(data);
                });
                noclean = false;
            },//数据来源
            minLength: 1, //最小输入多少个字符触发查询
            select: function (event, ui) {//选择该记录时执行
                $("#channel").val(ui.item.item.channel);
                noclean = true;
                event.preventDefault();
                return false;
            }
        });
    });
</script>