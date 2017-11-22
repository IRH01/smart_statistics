<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>产品运营数据统计后台</title>
    <link href="../../../lib/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../../../css/style.css">
    <link rel="stylesheet" href="../../../css/zTreeStyle.css">
    <link rel="stylesheet" href="../../../css/dialogsdk.css">
    <link rel="shortcut icon" href="../../../img/favicon.ico">
    <link rel="stylesheet" type="text/css" href="../../../lib/myPagination/css/style.css"/>
    <link type="text/css" rel="stylesheet" href="../../../css/admin-trend.css"/>
    <link type="text/css" rel="stylesheet" href="../../../css/jquery-ui.css"/>
    <link rel="stylesheet" type="text/css" href="../../../lib/monthpicker/skin/jquery.monthpicker.css"/>
    <link rel="stylesheet" type="text/css" href="../../../lib/myPagination/js/myPagination/page.css"/>
    <link rel="stylesheet" type="text/css" href="../../../lib/datepicker/css/bootstrap-datepicker3.css"/>
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
    <script src="../../../lib/datepicker/js/bootstrap-datepicker.min.js"></script>
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
                        <li>平台月数据-综合数据</li>
                    </ul>
                </div>
                <!--body start-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title" id="-collapsible-group-item-#1-">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne" aria-expanded="true"
                               aria-controls="collapseOne">综合数据</a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel"
                         aria-labelledby="headingOne" aria-expanded="true">
                        <div class="panel-body">
                            <div class="admin-content" style="overflow-x:auto;overflow-y:hidden;">
                                <div class="section-box">
                                    <div class="titleDiv">
                                        <div class="select-fr" style="padding-right: 30px;">
											<span class="laydateBox">
                                                月份区间：<input type="text" id="monthStart" class="laydate-icon"
                                                            style="width:140px;" title="" readonly/>
                                                至 &nbsp;<input type="text" id="monthEnd" class="laydate-icon"
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
                                                <div style="padding-bottom:35px;" class="ui-widget-content">
                                                    <div class="tablePanel resize">
                                                        <table class="tableListGame">
                                                            <thead>
                                                            <tr>
                                                                <th style="min-width:70px;" rowspan="2">月份</th>
                                                                <th style="min-width:90px;" rowspan="2">注册用户数</th>
                                                                <th style="min-width:90px;" rowspan="2">注册体验量</th>
                                                                <th style="min-width:100px;" rowspan="2">注册体验率(%)</th>
                                                                <th style="min-width:80px;" rowspan="2">真体验</th>
                                                                <th style="min-width:80px;" rowspan="2">假体验</th>
                                                                <th colspan="9">新用户</th>
                                                                <th colspan="9">老用户</th>
                                                                <th style="min-width:140px;" rowspan="2">平均次日留存率(%)</th>
                                                            </tr>
                                                            <tr>
                                                                <th style="min-width:130px;">新用户充值人数</th>
                                                                <th style="min-width:130px;">新用户充值次数</th>
                                                                <th style="min-width:130px;">新用户充值金额</th>
                                                                <th style="min-width:130px;">新用户充值转化率(%)</th>
                                                                <th style="min-width:120px;">新用户ARPU</th>
                                                                <th style="min-width:120px;">新用户ARPPU</th>
                                                                <th style="min-width:130px;">新用户登录人数</th>
                                                                <th style="min-width:130px;">新用户玩游戏数</th>
                                                                <th style="min-width:140px;">新用户登录转化率(%)</th>
                                                                <th style="min-width:130px;">老用户充值次数</th>
                                                                <th style="min-width:130px;">老用户充值人数</th>
                                                                <th style="min-width:130px;">老用户充值金额</th>
                                                                <th style="min-width:130px;">老用户充值转化率(%)</th>
                                                                <th style="min-width:120px;">老用户ARPU</th>
                                                                <th style="min-width:120px;">老用户ARPPU</th>
                                                                <th style="min-width:130px;">老用户登录人数</th>
                                                                <th style="min-width:130px;">老用户玩游戏数</th>
                                                                <th style="min-width:150px;">老用户登录转化率(%)</th>
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

    var setMonthConfig = function () {
        var date = new Date();
        var year = date.getFullYear();
        var years = new Array();
        var startYear = 2010;
        for (var i = 0; year >= startYear; year--, i++) {
            years[i] = year;
        }
        $("#monthStart").monthpicker({
            years: years,
            topOffset: 6,
            width: "140px",
            onMonthSelect: function (m, y) {
            }
        });
        $("#monthEnd").monthpicker({
            years: years,
            topOffset: 6,
            width: "140px",
            onMonthSelect: function (m, y) {
            }
        });
    };
    setMonthConfig();

    $('#monthStart').change(function () {

    });

    var addTbRow = function (data) {
        if (null !== data && undefined !== data && "" !== data) {
            var ele = "<tr><td>statisticsMonth</td>" +
                "<td>registerPopulation</td>" +
                "<td>registerExpCount</td>" +
                "<td>registerExpRate</td>" +
                "<td>realExpCount</td>" +
                "<td>virtualExpCount</td>" +
                "<td>newUserRechargePopulation</td>" +
                "<td>newUserRechargeCount</td>" +
                "<td>newUserRechargeAmount</td>" +
                "<td>newUserRechargeRate</td>" +
                "<td>newUserARPU</td>" +
                "<td>newUserARPPU</td>" +
                "<td>newUserLoginCount</td>" +
                "<td>newUserPlayCount</td>" +
                "<td>newUserLoginTransformRate</td>" +
                "<td>oldUserRechargeCount</td>" +
                "<td>oldUserRechargePopulation</td>" +
                "<td>oldUserRechargeAmount</td>" +
                "<td>oldUserRechargeRate</td>" +
                "<td>oldUserARPU</td>" +
                "<td>oldUserARPPU</td>" +
                "<td>oldUserLoginCount</td>" +
                "<td>oldUserPlayCount</td>" +
                "<td>oldUserLoginTransformRate</td>" +
                "<td>nextDayKeepRate</td>";
            ele = ele.replace("statisticsMonth", data.statisticsMonth)
                .replace("registerPopulation", data.registerPopulation)
                .replace("registerExpCount", data.registerExpCount)
                .replace("registerExpRate", data.registerExpRate)
                .replace("realExpCount", data.realExpCount)
                .replace("virtualExpCount", data.virtualExpCount)
                .replace("newUserRechargePopulation", data.newUserRechargePopulation)
                .replace("newUserRechargeCount", data.newUserRechargeCount)
                .replace("newUserRechargeAmount", data.newUserRechargeAmount)
                .replace("newUserRechargeRate", data.newUserRechargeRate)
                .replace("newUserARPU", data.newUserARPU)
                .replace("newUserARPPU", data.newUserARPPU)
                .replace("newUserLoginCount", data.newUserLoginCount)
                .replace("newUserPlayCount", data.newUserPlayCount)
                .replace("newUserLoginTransformRate", data.newUserLoginTransformRate)
                .replace("oldUserRechargeCount", data.oldUserRechargeCount)
                .replace("oldUserRechargePopulation", data.oldUserRechargePopulation)
                .replace("oldUserRechargeAmount", data.oldUserRechargeAmount)
                .replace("oldUserRechargeRate", data.oldUserRechargeRate)
                .replace("oldUserARPU", data.oldUserARPU)
                .replace("oldUserARPPU", data.oldUserARPPU)
                .replace("oldUserLoginCount", data.oldUserLoginCount)
                .replace("oldUserPlayCount", data.oldUserPlayCount)
                .replace("oldUserLoginTransformRate", data.oldUserLoginTransformRate)
                .replace("nextDayKeepRate", data.nextDayKeepRate);
            $("#data").append(ele);
        }
    };

    //显示统计列表
    var showNewUserData = function (pageNumber, pageSize) {
        $("#data").empty();
        $.post("/month/composite/list.do", {
            timeStart: $('#monthStart').val(),
            timeEnd: $('#monthEnd').val(),
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
                    $("#data").append("<tr><td colspan=\"23\">没有数据</td></tr>");
                } else {
                    var totalInfo = {};
                    totalInfo.statisticsMonth = "总计";
                    totalInfo.registerPopulation = 0;
                    totalInfo.registerExpCount = 0;
                    totalInfo.registerExpRate = "-";
                    totalInfo.realExpCount = 0;
                    totalInfo.virtualExpCount = 0;
                    totalInfo.newUserRechargePopulation = 0;
                    totalInfo.newUserRechargeCount = 0;
                    totalInfo.newUserRechargeAmount = 0;
                    totalInfo.newUserRechargeRate = "-";
                    totalInfo.newUserARPU = "-";
                    totalInfo.newUserARPPU = "-";
                    totalInfo.newUserLoginCount = 0;
                    totalInfo.newUserPlayCount = 0;
                    totalInfo.newUserLoginTransformRate = "-";
                    totalInfo.oldUserRechargeCount = 0;
                    totalInfo.oldUserRechargePopulation = 0;
                    totalInfo.oldUserRechargeAmount = 0;
                    totalInfo.oldUserRechargeRate = "-";
                    totalInfo.oldUserARPU = "-";
                    totalInfo.oldUserARPPU = "-";
                    totalInfo.oldUserLoginCount = 0;
                    totalInfo.oldUserPlayCount = 0;
                    totalInfo.oldUserLoginTransformRate = "-";
                    totalInfo.nextDayKeepRate = "-";
                    for (var i = 0; i < infoData.length; i++) {
                        addTbRow(infoData[i]);
                        totalInfo.registerPopulation = accAdd(totalInfo.registerPopulation, parseFloat(infoData[i].registerPopulation));
                        totalInfo.registerExpCount = accAdd(totalInfo.registerExpCount, parseFloat(infoData[i].registerExpCount));
                        totalInfo.realExpCount = accAdd(totalInfo.realExpCount, parseFloat(infoData[i].realExpCount));
                        totalInfo.virtualExpCount = accAdd(totalInfo.virtualExpCount, parseFloat(infoData[i].virtualExpCount));
                        totalInfo.newUserRechargePopulation = accAdd(totalInfo.newUserRechargePopulation, parseFloat(infoData[i].newUserRechargePopulation));
                        totalInfo.newUserRechargeCount = accAdd(totalInfo.newUserRechargeCount, parseFloat(infoData[i].newUserRechargeCount));
                        totalInfo.newUserRechargeAmount = accAdd(totalInfo.newUserRechargeAmount, parseFloat(infoData[i].newUserRechargeAmount));
                        totalInfo.newUserLoginCount = accAdd(totalInfo.newUserLoginCount, parseFloat(infoData[i].newUserLoginCount));
                        totalInfo.newUserPlayCount = accAdd(totalInfo.newUserPlayCount, parseFloat(infoData[i].newUserPlayCount));
                        totalInfo.oldUserRechargeCount = accAdd(totalInfo.oldUserRechargeCount, parseFloat(infoData[i].oldUserRechargeCount));
                        totalInfo.oldUserRechargePopulation = accAdd(totalInfo.oldUserRechargePopulation, parseFloat(infoData[i].oldUserRechargePopulation));
                        totalInfo.oldUserRechargeAmount = accAdd(totalInfo.oldUserRechargeAmount, parseFloat(infoData[i].oldUserRechargeAmount));
                        totalInfo.oldUserLoginCount = accAdd(totalInfo.oldUserLoginCount, parseFloat(infoData[i].oldUserLoginCount));
                        totalInfo.oldUserPlayCount = accAdd(totalInfo.oldUserPlayCount, parseFloat(infoData[i].oldUserPlayCount));
                    }
                    addTbRow(totalInfo);
                }
            } else {
                $("#data").append("<tr><td colspan=\"23\">没有数据</td></tr>");
            }
        });
    };

    //查询显示
    var search = function () {
        showNewUserData(1, pageSize);
    };

    search();

    $("#search").click(function () {
        search();
    });

    $("#pageSize").change(function () {
        pageSize = parseInt($("#pageSize").val());
        search();
    });
</script>