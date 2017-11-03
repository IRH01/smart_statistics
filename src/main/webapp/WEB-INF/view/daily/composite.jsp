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
    <link rel="stylesheet" href="/css/admin-trend.css"/>
    <script src="/lib/laydate/laydate.js"></script>
    <link rel="stylesheet" href="/lib/myPagination/css/style.css"/>
    <link rel="stylesheet" href="/lib/myPagination/js/myPagination/page.css"/>
    <link rel="stylesheet" href="/css/jquery-ui.css"/>
    <script src="/lib/myPagination/js/myPagination/jquery.myPagination6.0.js"></script>
    <script src="/lib/jquery-ui.js"></script>
    <script src="/lib/datecontrol.js"></script>
    <script src="/lib/echart/dist/echarts.js"></script>
    <script src="/lib/tool.js"></script>
<body>
<div class="wrap">
    <jsp:include page="../template/header.jsp"/>
    <jsp:include page="../template/menu.jsp"/>

    <div class="container-fluid content">
        <div class="row">
            <div id="content" class="col-lg-10 col-md-9">
                <div class="sdk-content">
                    <ul class="breadcrumb">
                        <li>您当前的位置：</li>
                        <tags:breadcrumb/>
                        <li>平台日报表</li>
                    </ul>
                </div>
                <!--body start-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title" id="-collapsible-group-item-#1-">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne" aria-expanded="true"
                               aria-controls="collapseOne" class="">综合数据</a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in" aria-expanded="true">
                        <div class="panel-body">
                            <div class="admin-content">
                                <div class="section-box">
                                    <div class="whiteDiv tab-content">
                                        <ul id="sortable" class="ui-widget-content sortable">
                                            <li class="ui-state-default">
                                                <div style="padding-bottom:60px;"
                                                     class="ui-widget-content resize resizePanel">

                                                    <div class="titleDiv" style="height:65px;">
                                                        <div style="display: inline;width:500px;text-align: center;"></div>
                                                        <div class="select-fr" style="padding-right: 30px;float:left;">
                                                            <table style="margin-left:5px;">
                                                                <tr>
                                                                    <td class="tb1Td">
                                                                        <span class="span">统计时间：</span>
                                                                    </td>
                                                                    <td><input placeholder="请选择日期" class="laydate-icon"
                                                                               style="height:25px;" id="dateStart"
                                                                               readonly></td>
                                                                    <td>
                                                                        <span class="span">&nbsp;至&nbsp;</span>
                                                                    </td>
                                                                    <td><input class="laydate-icon" id="dateEnd"
                                                                               style="height:25px;" placeholder="请选择日期"
                                                                               readonly></td>
                                                                    <td style="line-height:1;">
                                                                        <button type="button" id="search"
                                                                                class="btn btn-primary btn-sm"
                                                                                onclick="search();">
                                                                            <i class="icon-search icon-white"
                                                                               style="height: 24px;padding-top:0px;padding-bottom:0px;"></i>&nbsp;查&nbsp;&nbsp;询&nbsp;
                                                                        </button>
                                                                    </td>
                                                                    <td style="line-height:1;">
                                                                        <button type="button" id="reset"
                                                                                class="btn btn-primary btn-sm"
                                                                                onclick="reset();">
                                                                            <i class="icon-search icon-white"
                                                                               style="height: 24px;padding-top:0px;padding-bottom:0px;"></i>&nbsp;重&nbsp;&nbsp;置&nbsp;
                                                                        </button>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                    </div>
                                                    <div class="tablePanel">
                                                        <table class="tableList">
                                                            <tbody id="data">
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <table class="tablePage">
                                                        <tr>
                                                            <td>
                                                                <div class="divPage"><span
                                                                        class="spanPageSize">每页个数：</span><input
                                                                        id="pageSize" value="10" class="inputPageSize"
                                                                        onKeypress="return intInput(event);"
                                                                        onKeyup="value=pageSizeLimit(value);"
                                                                        onblur="value=pageSizeNotEmpty(value);"/></div>
                                                            </td>
                                                            <td><span class="spanPageSize">总记录数：</span><span
                                                                    id="totalCount" class="spanPageSize"></span></td>
                                                            <td><span class="spanPageSize">总页数：</span><span
                                                                    id="totalPage" class="spanPageSize"></span></td>
                                                            <td class="tablePageTd">
                                                                <div id="page"></div>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--body end-->
                </div>
            </div>
        </div>
    </div>
</body>
</html>
<script type="text/javascript">

    $(function () {
        $("#sortable").sortable({cursor: "move", handle: ".sortHandle"});
        $(".resize").resizable({minHeight: 200, minWidth: 300});
    });

    var pageSize = 30;

    setDateRangeConfig("dateStart", "dateEnd", null, true);

    var showTbCl = function () {
        $("#data").append("<tr><td rowspan=\"2\" >日期</td><td rowspan=\"2\">注册人数</td>\n" +
            "<td rowspan=\"2\">注册体验量</td><td rowspan=\"2\">注册体验率(%)</td><td rowspan=\"2\">真体验</td><td rowspan=\"2\">假体验</td><td colspan=\"9\">新用户</td><td colspan=\"9\">老用户</td><td rowspan=\"2\">次日留存</td><td rowspan=\"2\">次日留存率(%)</td></tr><tr><td>充值人数</td><td>充值次数</td><td>充值金额</td><td>充值转化率(%)</td><td>ARPU</td><td>ARPPU</td><td>登录人数</td><td>玩游戏人数</td><td>登录转化率(%)</td><td>充值人数</td><td>充值次数</td><td>充值金额</td><td>充值转化率(%)</td><td>ARPU</td><td>ARPPU</td><td>登录人数</td><td>玩游戏人数</td><td>登录转化率(%)</td></tr>");
    };

    var addTbRow = function (data) {
        if (null != data && undefined != data && "" != data) {
            var ele = "<tr><td class=\"date\" style=\"width:100px;\">statisticsDay</td><td>registerPopulation</td><td>registerExpCount</td><td>registerExpCountRate</td><td>realExpCount</td><td>virtualExpCount</td><td>newUserRechargePopulation</td><td>newUserRechargeCount</td><td>newUserRechargeAmount</td><td>newUserRechargeRate</td><td>newUserARPU</td>" +
                "<td>newUserARPPU</td><td>newUserLoginCount</td><td>newUserPlayCount</td><td>newUserLoginTransformRate</td><td>oldUserRechargePopulation</td><td>oldUserRechargeCount</td><td>oldUserRechargeAmount</td><td>oldUserRechargeRate</td><td>oldUserARPU</td><td>oldUserARPPU</td><td>oldUserLoginCount</td><td>oldUserPlayCount</td><td>oldUserLoginTransformRate</td>" +
                "<td>nextDayStayCount</td><td>nextDayStayRate</td></tr>";
            ele = ele.replace("statisticsDay", data.statisticsDay).replace("registerPopulation", data.registerPopulation).replace("registerExpCount", data.registerExpCount).replace("registerExpCountRate", data.registerExpCountRate).replace("realExpCount", data.realExpCount).replace("virtualExpCount", data.virtualExpCount).replace("newUserRechargePopulation", data.newUserRechargePopulation).replace("newUserRechargeCount", data.newUserRechargeCount)
                .replace("newUserRechargeAmount", data.newUserRechargeAmount).replace("newUserRechargeRate", data.newUserRechargeRate).replace("newUserARPU", data.newUserARPU).replace("newUserARPPU", data.newUserARPPU)
                .replace("newUserLoginCount", data.newUserLoginCount).replace("newUserPlayCount", data.newUserPlayCount).replace("newUserLoginTransformRate", data.newUserLoginTransformRate).replace("oldUserRechargePopulation", data.oldUserRechargePopulation)
                .replace("oldUserRechargeCount", data.oldUserRechargeCount).replace("oldUserRechargeAmount", data.oldUserRechargeAmount).replace("oldUserRechargeRate", data.oldUserRechargeRate).replace("oldUserARPU", data.oldUserARPU).replace("oldUserARPPU", data.oldUserARPPU)
                .replace("oldUserLoginCount", data.oldUserLoginCount).replace("oldUserPlayCount", data.oldUserPlayCount).replace("oldUserLoginTransformRate", data.oldUserLoginTransformRate).replace("nextDayStayCount", data.nextDayStayCount).replace("nextDayStayRate", data.nextDayStayRate);
            $("#data").append(ele);
        }
    };

    //显示统计列表
    var showNewUserData = function (pageNumber, pageSize) {
        $("#data").empty();
        showTbCl();
        $.post("/daily/composite/list.do", {
            startDate: $('#dateStart').val(),
            endDate: $('#dateEnd').val(),
            pageNumber: pageNumber,
            pageSize: pageSize
        }, function (result) {
            var json = result.data;
            if (null != json && undefined != json) {
                $("#totalCount").html(json.total);
                $("#totalPage").html(json.pages);
                $("#page").myPagination({
                    currPage: pageNumber,
                    pageCount: json.pages,
                    ajax: {
                        on: false,
                        onClick: function (page) {
                            showNewUserData(page, pageSize);
                        }
                    }
                });
                var infoData = json.list;
                if (null == infoData || undefined == infoData || 0 >= infoData.length) {
                    $("#totalCount").html(0);
                    $("#totalPage").html(0);
                    $("#data").append("<tr><td colspan=\"26\">没有数据</td></tr>");
                } else {
                    var registerPopulationNum = 0;
                    var registerExpCountNum = 0;
                    var registerExpCountRateNum = 0;
                    var realExpCountNum = 0;
                    var virtualExpCountNum = 0;
                    var newUserRechargePopulationNum = 0;
                    var newUserRechargeCountNum = 0;
                    var newUserRechargeAmountNum = 0;
                    var newUserRechargeRateNum = 0;
                    var newUserARPUNum = 0;
                    var newUserARPPUNum = 0;
                    var newUserLoginCountNum = 0;
                    var newUserPlayCountNum = 0;
                    var newUserLoginTransformRateNum = 0;
                    var oldUserRechargePopulationNum = 0;
                    var oldUserRechargeCountNum = 0;
                    var oldUserRechargeAmountNum = 0;
                    var oldUserRechargeRateNum = 0;
                    var oldUserARPUNum = 0;
                    var oldUserARPPUNum = 0;
                    var oldUserLoginCountNum = 0;
                    var oldUserPlayCountNum = 0;
                    var oldUserLoginTransformRateNum = 0;
                    var nextDayStayCountNum = 0;
                    var nextDayStayRateNums = 0;
                    var count = infoData.length;
                    for (var i = 0; i < infoData.length; i++) {
                        registerPopulationNum = accAdd(registerPopulationNum, infoData[i].registerPopulation);
                        registerExpCountNum = accAdd(registerExpCountNum, accDiv(infoData[i].registerExpCount, infoData[i].registerPopulation));
                        registerExpCountRateNum = accAdd(registerExpCountRateNum, infoData[i].registerExpCountRate);
                        realExpCountNum = accAdd(realExpCountNum, infoData[i].realExpCount);
                        virtualExpCountNum = accAdd(virtualExpCountNum, infoData[i].virtualExpCount);

                        newUserRechargePopulationNum = accAdd(newUserRechargePopulationNum, infoData[i].newUserRechargePopulation);
                        newUserRechargeCountNum = accAdd(newUserRechargeCountNum, infoData[i].newUserRechargeCount);
                        newUserRechargeAmountNum = accAdd(newUserRechargeAmountNum, infoData[i].newUserRechargeAmount);
                        newUserRechargeRateNum = accAdd(newUserRechargeRateNum, accDiv(infoData[i].newUserRechargePopulation, infoData[i].registerPopulation));
                        newUserARPUNum = accAdd(newUserARPUNum, infoData[i].newUserARPU);
                        newUserARPPUNum = accAdd(newUserARPPUNum, infoData[i].newUserARPPU);
                        newUserLoginCountNum = accAdd(newUserLoginCountNum, infoData[i].newUserLoginCount);
                        newUserPlayCountNum = accAdd(newUserPlayCountNum, infoData[i].newUserPlayCount);
                        newUserLoginTransformRateNum = accAdd(newUserLoginTransformRateNum, accDiv(infoData[i].newUserLoginCount, infoData[i].registerPopulation));

                        oldUserRechargePopulationNum = accAdd(oldUserRechargePopulationNum, infoData[i].oldUserRechargePopulation);
                        oldUserRechargeCountNum = accAdd(oldUserRechargeCountNum, infoData[i].oldUserRechargeCount);
                        oldUserRechargeAmountNum = accAdd(oldUserRechargeAmountNum, infoData[i].oldUserRechargeAmount);
                        oldUserRechargeRateNum = accAdd(oldUserRechargeRateNum, accDiv(infoData[i].oldUserRechargePopulation, infoData[i].registerPopulation));
                        oldUserARPUNum = accAdd(oldUserARPUNum, infoData[i].oldUserARPU);
                        oldUserARPPUNum = accAdd(oldUserARPPUNum, infoData[i].oldUserARPPU);
                        oldUserLoginCountNum = accAdd(oldUserLoginCountNum, infoData[i].oldUserLoginCount);
                        oldUserPlayCountNum = accAdd(oldUserPlayCountNum, infoData[i].oldUserPlayCount);
                        oldUserLoginTransformRateNum = accAdd(oldUserLoginTransformRateNum, accDiv(infoData[i].oldUserLoginCount, infoData[i].registerPopulation));

                        nextDayStayCountNum = accAdd(nextDayStayCountNum, infoData[i].nextDayStayCount);
                        nextDayStayRateNums = accAdd(nextDayStayRateNums, accDiv(infoData[i].oldUserLoginCount, infoData[i].registerPopulation));
                        var nextDayStayRateNum = 0;
                        if ((i + 1) < infoData.length) {
                            nextDayStayRateNum = accDiv(accAdd(infoData[i].oldUserLoginCount, infoData[i].newUserLoginCount), infoData[i + 1].statisticsDay);
                        } else {
                            nextDayStayRateNum = 0;
                        }

                        var ele = {
                            statisticsDay: infoData[i].statisticsDay,
                            registerPopulation: infoData[i].registerPopulation,
                            registerExpCount: infoData[i].registerExpCount,
                            registerExpCountRate: infoData[i].registerExpCountRate,
                            realExpCount: infoData[i].realExpCount,
                            virtualExpCount: infoData[i].virtualExpCount,
                            newUserRechargePopulation: infoData[i].newUserRechargePopulation,
                            newUserRechargeCount: infoData[i].newUserRechargeCount,
                            newUserRechargeAmount: infoData[i].newUserRechargeAmount,
                            newUserRechargeRate: infoData[i].newUserRechargeRate,
                            newUserARPU: infoData[i].newUserARPU,
                            newUserARPPU: infoData[i].newUserARPPU,
                            newUserLoginCount: infoData[i].newUserLoginCount,
                            newUserPlayCount: infoData[i].newUserPlayCount,
                            newUserLoginTransformRate: infoData[i].newUserLoginTransformRate,
                            oldUserRechargePopulation: infoData[i].oldUserRechargePopulation,
                            oldUserRechargeCount: infoData[i].oldUserRechargeCount,
                            oldUserRechargeAmount: infoData[i].oldUserRechargeAmount,
                            oldUserRechargeRate: infoData[i].oldUserRechargeRate,
                            oldUserARPU: infoData[i].oldUserARPU,
                            oldUserARPPU: infoData[i].oldUserARPPU,
                            oldUserLoginCount: infoData[i].oldUserLoginCount,
                            oldUserPlayCount: infoData[i].oldUserPlayCount,
                            oldUserLoginTransformRate: infoData[i].oldUserLoginTransformRate,
                            nextDayStayCount: infoData[i].nextDayStayCount,
                            nextDayStayRate: infoData[i].nextDayStayRate
                        };
                        addTbRow(ele);
                    }

                    var ele1 = {
                        statisticsDay: "总计",
                        registerPopulation: registerPopulationNum,
                        registerExpCount: registerExpCountNum,
                        registerExpCountRate: accDiv(registerExpCountRateNum, count),
                        realExpCount: realExpCountNum,
                        virtualExpCount: virtualExpCountNum,
                        newUserRechargePopulation: newUserRechargePopulationNum,
                        newUserRechargeCount: newUserRechargeCountNum,
                        newUserRechargeAmount: newUserRechargeAmountNum,
                        newUserRechargeRate: accDiv(newUserRechargeRateNum, count),
                        newUserARPU: newUserARPUNum,
                        newUserARPPU: newUserARPPUNum,
                        newUserLoginCount: newUserLoginCountNum,
                        newUserPlayCount: newUserPlayCountNum,
                        newUserLoginTransformRate: accDiv(newUserLoginTransformRateNum, count),
                        oldUserRechargePopulation: oldUserRechargePopulationNum,
                        oldUserRechargeCount: oldUserRechargeCountNum,
                        oldUserRechargeAmount: oldUserRechargeAmountNum,
                        oldUserRechargeRate: accDiv(oldUserRechargeRateNum, count),
                        oldUserARPU: oldUserARPUNum,
                        oldUserARPPU: oldUserARPPUNum,
                        oldUserLoginCount: oldUserLoginCountNum,
                        oldUserPlayCount: oldUserPlayCountNum,
                        oldUserLoginTransformRate: accDiv(oldUserLoginTransformRateNum, count),
                        nextDayStayCount: nextDayStayCountNum,
                        nextDayStayRate: accDiv(nextDayStayRateNums, count)
                    };
                    addTbRow(ele1);
                }
            } else {
                $("#totalCount").html(0);
                $("#totalPage").html(0);
                $("#data").append("<tr><td colspan=\"26\">没有数据</td></tr>");
            }
        });
    };

    //查询显示
    var search = function () {
        pageSize = $("#pageSize").val();
        showNewUserData(1, pageSize);
    };

    var reset = function () {
        $("#dateStart").val("");
        $("#dateEnd").val("");
    };

    search();

</script>