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
    <link href="../../../css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../../../css/style.css">
    <link rel="stylesheet" href="../../../css/zTreeStyle.css">
    <link rel="stylesheet" href="../../../css/dialogsdk.css">
    <link rel="shortcut icon" href="../../../img/favicon.ico">
    <link rel="stylesheet" type="text/css" href="../../../lib/myPagination/css/style.css"/>
    <link type="text/css" rel="stylesheet" href="../../../css/admin-trend.css"/>
    <link type="text/css" rel="stylesheet" href="../../../css/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css" href="../../../lib/monthpicker/skin/jquery.monthpicker.css"/>
    <link rel="stylesheet" type="text/css" href="../../../lib/myPagination/js/myPagination/page.css"/>
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="../../../lib/jquery-1.11.2.min.js"></script>
    <script src="../../../lib/bootstrap.min.js"></script>
    <script src="../../../lib/jquery.ztree.all-3.5.min.js"></script>
    <script src="../../../lib/jquery.validate.js"></script>
    <script src="../../../lib/additional-methods.min.js" type="text/javascript"></script>
    <script src="../../../lib/jquery-validate.bootstrap-tooltip.js" type="text/javascript"></script>
    <script src="../../../lib/jquery.validate.custom.js" type="text/javascript"></script>
    <script src="../../../lib/My97DatePickerBeta/My97DatePicker/WdatePicker.js"></script>
    <script src="../../../lib/dialogsdk.js"></script>
    <script src="../../../lib/tools/tools.js"></script>
    <script src="../../../lib/layer/layer.js"></script>
    <script src="../../../lib/laydate/laydate.js"></script>
    <script src="../../../lib/myPagination/js/myPagination/jquery.myPagination6.0.js" type="text/javascript"></script>
    <script src="../../../lib/layer/layer.js"></script>
    <script src="../../../lib/tool.js"></script>
    <script src="../../../lib/datecontrol.js"></script>
    <script src="../../../lib/jquery-ui.js"></script>
    <script src="../../../lib/layer/layer.js"></script>
    <script src="../../../lib/monthpicker/jquery.monthpicker.js"></script>
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
                        <li>充值来源统计</li>
                    </ul>
                </div>
                <!--body start-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title" id="-collapsible-group-item-#1-">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne" aria-expanded="true"
                               aria-controls="collapseOne">充值来源统计</a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel"
                         aria-labelledby="headingOne" aria-expanded="true">
                        <div class="panel-body">
                            <div class="admin-content" style="overflow-x:auto;overflow-y:hidden;">
                                <div class="section-box">
                                    <div class="titleDiv">
                                        <div class="notice-div">
                                            昨日充值总金额：<span id="last_daily_amount">*</span>
                                        </div>
                                        <div class="select-fr" style="padding-right: 30px;">
											<span class="laydateBox">
                                                日期区间：<input type="text" id="dateStart" class="laydate-icon"
                                                            style="width:140px;" title="" readonly/>
                                                至 &nbsp;<input type="text" id="dateEnd" class="laydate-icon"
                                                               style="width:140px;" title="" readonly/>
											</span>
                                            <button type="button" id="search" class="btn btn-primary btn-sm">
                                                查询
                                            </button>
                                        </div>
                                    </div>
                                    <div class="whiteDiv">
                                        <ul id="sortable" class="ui-widget-content sortable">
                                            <li class="ui-state-default">
                                                <div style="padding-bottom:35px;" class="ui-widget-content resize">
                                                    <div class="sortHandle">
                                                    </div>
                                                    <div class="tablePanel">
                                                        <table class="tableListGame">
                                                            <thead>
                                                            <tr>
                                                                <th style="min-width:90px;">日期</th>
                                                                <th style="min-width:100px;">PC-充值人数</th>
                                                                <th style="min-width:100px;">PC-充值金额</th>
                                                                <th style="min-width:100px;">PC-充值次数</th>
                                                                <th style="min-width:120px;">PC-新用户充值</th>
                                                                <th style="min-width:120px;">PC-老用户充值</th>
                                                                <th style="min-width:90px;">PC-ARPU</th>
                                                                <th style="min-width:130px;">android-充值人数</th>
                                                                <th style="min-width:130px;">android-充值金额</th>
                                                                <th style="min-width:130px;">android-充值次数</th>
                                                                <th style="min-width:140px;">android-新用户充值</th>
                                                                <th style="min-width:140px;">android-老用户充值</th>
                                                                <th style="min-width:120px;">android-ARPU</th>
                                                                <th style="min-width:120px;">IOS-充值人数</th>
                                                                <th style="min-width:120px;">IOS-充值金额</th>
                                                                <th style="min-width:100px;">IOS-充值次数</th>
                                                                <th style="min-width:120px;">IOS-新用户充值</th>
                                                                <th style="min-width:120px;">IOS-老用户充值</th>
                                                                <th style="min-width:120px;">IOS-ARPU</th>
                                                                <th style="min-width:120px;">H5-充值人数</th>
                                                                <th style="min-width:120px;">H5-充值金额</th>
                                                                <th style="min-width:120px;">H5-充值次数</th>
                                                                <th style="min-width:120px;">H5-新用户充值</th>
                                                                <th style="min-width:120px;">H5-老用户充值</th>
                                                                <th style="min-width:90px;">H5-ARPU</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody id="data">
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <div class="pagePanel">
                                                        <table class="tablePage">
                                                            <tr>
                                                                <td>
                                                                    <div class="divPage"><span class="spanPageSize">每页记录数：</span>
                                                                        <select id="pageSize" class="inputPageSize"
                                                                                title="页记录数">
                                                                            <option value="10" aria-checked="true">10
                                                                            </option>
                                                                            <option value="20">20</option>
                                                                            <option value="30">30</option>
                                                                            <option value="40">40</option>
                                                                            <option value="50">50</option>
                                                                        </select>
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <span class="spanPageSize">总记录数：</span>
                                                                    <span id="totalCount" class="spanPageSize"></span>
                                                                </td>
                                                                <td>
                                                                    <span class="spanPageSize">总页数：</span>
                                                                    <span id="totalPage" class="spanPageSize"></span>
                                                                </td>
                                                                <td class="tablePageTd">
                                                                    <div id="page"></div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div>
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
    var pageSize = 10;

    $("#pageSize").val(pageSize);

    setDateRangeConfig("dateStart", "dateEnd", null, true);

    var addTbRow = function (data) {
        if (null !== data && undefined !== data && "" !== data) {
            var ele = "<tr><td>statisticsDay</td>" +
                "<td>pc_rechargePopulation</td>" +
                "<td>pc_rechargeAmount</td>" +
                "<td>pc_rechargeCount</td>" +
                "<td>pc_newRechargePopulation</td>" +
                "<td>pc_oldRechargePopulation</td>" +
                "<td>pc_ARPU</td>" +
                "<td>android_rechargePopulation</td>" +
                "<td>android_rechargeAmount</td>" +
                "<td>android_rechargeCount</td>" +
                "<td>android_newRechargePopulation</td>" +
                "<td>android_oldRechargePopulation</td>" +
                "<td>android_ARPU</td>" +
                "<td>ios_rechargePopulation</td>" +
                "<td>ios_rechargeAmount</td>" +
                "<td>ios_rechargeCount</td>" +
                "<td>ios_newRechargePopulation</td>" +
                "<td>ios_oldRechargePopulation</td>" +
                "<td>ios_ARPU</td>" +
                "<td>h5_rechargePopulation</td>" +
                "<td>h5_rechargeAmount</td>" +
                "<td>h5_rechargeCount</td>" +
                "<td>h5_newRechargePopulation</td>" +
                "<td>h5_oldRechargePopulation</td>" +
                "<td>h5_ARPU</td>";
            ele = ele.replace("statisticsDay", data.statisticsDay)
                .replace("pc_rechargePopulation", data.pc_rechargePopulation)
                .replace("pc_rechargeAmount", data.pc_rechargeAmount)
                .replace("pc_rechargeCount", data.pc_rechargeCount)
                .replace("pc_newRechargePopulation", data.pc_newRechargePopulation)
                .replace("pc_oldRechargePopulation", data.pc_oldRechargePopulation)
                .replace("pc_ARPU", data.pc_ARPU)
                .replace("android_rechargePopulation", data.android_rechargePopulation)
                .replace("android_rechargeAmount", data.android_rechargeAmount)
                .replace("android_rechargeCount", data.android_rechargeCount)
                .replace("android_newRechargePopulation", data.android_newRechargePopulation)
                .replace("android_oldRechargePopulation", data.android_oldRechargePopulation)
                .replace("android_ARPU", data.android_ARPU)
                .replace("ios_rechargePopulation", data.ios_rechargePopulation)
                .replace("ios_rechargeAmount", data.ios_rechargeAmount)
                .replace("ios_rechargeCount", data.ios_rechargeCount)
                .replace("ios_newRechargePopulation", data.ios_newRechargePopulation)
                .replace("ios_oldRechargePopulation", data.ios_oldRechargePopulation)
                .replace("ios_ARPU", data.ios_ARPU)
                .replace("h5_rechargePopulation", data.h5_rechargePopulation)
                .replace("h5_rechargeAmount", data.h5_rechargeAmount)
                .replace("h5_rechargeCount", data.h5_rechargeCount)
                .replace("h5_newRechargePopulation", data.h5_newRechargePopulation)
                .replace("h5_oldRechargePopulation", data.h5_oldRechargePopulation)
                .replace("h5_ARPU", data.h5_ARPU);
            $("#data").append(ele);
        }
    };

    //显示统计列表
    var showNewUserData = function (pageNumber, pageSize) {
        $("#data").empty();
        $.get("/daily/recharge/statistics/list.do", {
            timeStart: $('#dateStart').val(),
            timeEnd: $('#dateEnd').val(),
            pageNo: pageNumber,
            pageSize: pageSize
        }, function (result) {
            var pagination = result.data;
            if (pagination !== null && pagination !== undefined) {
                $("#totalCount").html(pagination.total);
                $("#totalPage").html(parseInt((pagination.total + pageSize - 1) / pageSize));
                $("#page").myPagination({
                    currPage: pageNumber,
                    pageCount: parseInt((pagination.total + pageSize - 1) / pageSize),
                    ajax: {
                        on: false,
                        onClick: function (page) {
                            showNewUserData(page, pageSize);
                        }
                    }
                });
                var infoData = pagination.data;
                if (null === infoData || undefined === infoData || 0 >= infoData.length) {
                    $("#totalCount").html(0);
                    $("#totalPage").html(0);
                    $("#data").append("<tr><td colspan=\"25\">没有数据</td></tr>");
                } else {
                    var info = {};
                    var totalInfo = {};
                    totalInfo.statisticsDay = "总计";
                    totalInfo.pc_rechargePopulation = 0;
                    totalInfo.pc_rechargeAmount = 0;
                    totalInfo.pc_rechargeCount = 0;
                    totalInfo.pc_newRechargePopulation = 0;
                    totalInfo.pc_oldRechargePopulation = 0;
                    totalInfo.pc_ARPU = "-";
                    totalInfo.h5_rechargePopulation = 0;
                    totalInfo.h5_rechargeAmount = 0;
                    totalInfo.h5_rechargeCount = 0;
                    totalInfo.h5_newRechargePopulation = 0;
                    totalInfo.h5_oldRechargePopulation = 0;
                    totalInfo.h5_ARPU = "-";
                    totalInfo.ios_rechargePopulation = 0;
                    totalInfo.ios_rechargeAmount = 0;
                    totalInfo.ios_rechargeCount = 0;
                    totalInfo.ios_newRechargePopulation = 0;
                    totalInfo.ios_oldRechargePopulation = 0;
                    totalInfo.ios_ARPU = "-";
                    totalInfo.android_rechargePopulation = 0;
                    totalInfo.android_rechargeAmount = 0;
                    totalInfo.android_rechargeCount = 0;
                    totalInfo.android_newRechargePopulation = 0;
                    totalInfo.android_oldRechargePopulation = 0;
                    totalInfo.android_ARPU = "-";
                    //1、PC 2.android 3.IOS 4.H5
                    for (var i = 0; i < infoData.length; i++) {
                        if (infoData[i].sourceType == 1) {
                            info.pc_rechargePopulation = infoData[i].rechargePopulation;
                            info.pc_rechargeAmount = infoData[i].rechargeAmount;
                            info.pc_rechargeCount = infoData[i].rechargeCount;
                            info.pc_newRechargePopulation = infoData[i].newRechargePopulation;
                            info.pc_oldRechargePopulation = infoData[i].oldRechargePopulation;
                            info.pc_ARPU = infoData[i].arpu;
                            totalInfo.pc_rechargePopulation = accAdd(totalInfo.pc_rechargePopulation, parseFloat(infoData[i].rechargePopulation));
                            totalInfo.pc_rechargeAmount = accAdd(totalInfo.pc_rechargeAmount, parseFloat(infoData[i].rechargeAmount));
                            totalInfo.pc_rechargeCount = accAdd(totalInfo.pc_rechargeCount, parseFloat(infoData[i].rechargeCount));
                            totalInfo.pc_newRechargePopulation = accAdd(totalInfo.pc_newRechargePopulation, parseFloat(infoData[i].newRechargePopulation));
                            totalInfo.pc_oldRechargePopulation = accAdd(totalInfo.pc_oldRechargePopulation, parseFloat(infoData[i].oldRechargePopulation));
                        }
                        if (infoData[i].sourceType == 2) {
                            info.android_rechargePopulation = infoData[i].rechargePopulation;
                            info.android_rechargeAmount = infoData[i].rechargeAmount;
                            info.android_rechargeCount = infoData[i].rechargeCount;
                            info.android_newRechargePopulation = infoData[i].newRechargePopulation;
                            info.android_oldRechargePopulation = infoData[i].oldRechargePopulation;
                            info.android_ARPU = infoData[i].arpu;
                            totalInfo.android_rechargePopulation = accAdd(totalInfo.android_rechargePopulation, parseFloat(infoData[i].rechargePopulation));
                            totalInfo.android_rechargeAmount = accAdd(totalInfo.android_rechargeAmount, parseFloat(infoData[i].rechargeAmount));
                            totalInfo.android_rechargeCount = accAdd(totalInfo.android_rechargeCount, parseFloat(infoData[i].rechargeCount));
                            totalInfo.android_newRechargePopulation = accAdd(totalInfo.android_newRechargePopulation, parseFloat(infoData[i].newRechargePopulation));
                            totalInfo.android_oldRechargePopulation = accAdd(totalInfo.android_oldRechargePopulation, parseFloat(infoData[i].oldRechargePopulation));
                        }
                        if (infoData[i].sourceType == 3) {
                            info.ios_rechargePopulation = infoData[i].rechargePopulation;
                            info.ios_rechargeAmount = infoData[i].rechargeAmount;
                            info.ios_rechargeCount = infoData[i].rechargeCount;
                            info.ios_newRechargePopulation = infoData[i].newRechargePopulation;
                            info.ios_oldRechargePopulation = infoData[i].oldRechargePopulation;
                            info.ios_ARPU = infoData[i].arpu;
                            totalInfo.ios_rechargePopulation = accAdd(totalInfo.ios_rechargePopulation, parseFloat(infoData[i].rechargePopulation));
                            totalInfo.ios_rechargeAmount = accAdd(totalInfo.ios_rechargeAmount, parseFloat(infoData[i].rechargeAmount));
                            totalInfo.ios_rechargeCount = accAdd(totalInfo.ios_rechargeCount, parseFloat(infoData[i].rechargeCount));
                            totalInfo.ios_newRechargePopulation = accAdd(totalInfo.ios_newRechargePopulation, parseFloat(infoData[i].newRechargePopulation));
                            totalInfo.ios_oldRechargePopulation = accAdd(totalInfo.ios_oldRechargePopulation, parseFloat(infoData[i].oldRechargePopulation));
                        }
                        if (infoData[i].sourceType == 4) {
                            info.h5_rechargePopulation = infoData[i].rechargePopulation;
                            info.h5_rechargeAmount = infoData[i].rechargeAmount;
                            info.h5_rechargeCount = infoData[i].rechargeCount;
                            info.h5_newRechargePopulation = infoData[i].newRechargePopulation;
                            info.h5_oldRechargePopulation = infoData[i].oldRechargePopulation;
                            info.h5_ARPU = infoData[i].arpu;
                            totalInfo.h5_rechargePopulation = accAdd(totalInfo.h5_rechargePopulation, parseFloat(infoData[i].rechargePopulation));
                            totalInfo.h5_rechargeAmount = accAdd(totalInfo.h5_rechargeAmount, parseFloat(infoData[i].rechargeAmount));
                            totalInfo.h5_rechargeCount = accAdd(totalInfo.h5_rechargeCount, parseFloat(infoData[i].rechargeCount));
                            totalInfo.h5_newRechargePopulation = accAdd(totalInfo.h5_newRechargePopulation, parseFloat(infoData[i].newRechargePopulation));
                            totalInfo.h5_oldRechargePopulation = accAdd(totalInfo.h5_oldRechargePopulation, parseFloat(infoData[i].oldRechargePopulation));
                        }
                        if ((i % 4) == 3) {
                            info.statisticsDay = infoData[i].statisticsDay
                            addTbRow(info);
                            info = {};
                        }
                    }
                    addTbRow(totalInfo);
                }
            } else {
                $("#data").append("<tr><td colspan=\"25\">没有数据</td></tr>");
            }
        });
    };

    var getLastMonthAmount = function () {
        $.get("/daily/recharge/statistics/last/recharge/total.do", {}, function (result) {
            $("#last_daily_amount").html(result.data);
        });
    };

    //查询显示
    var search = function () {
        showNewUserData(1, pageSize);
    };

    getLastMonthAmount();
    search();

    $("#search").click(function () {
        search();
    });

    $("#pageSize").change(function () {
        pageSize = parseInt($("#pageSize").val());
        search();
    });
</script>