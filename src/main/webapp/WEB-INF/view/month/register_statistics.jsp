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
                        <li>综合数据</li>
                    </ul>
                </div>
                <!--body start-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title" id="-collapsible-group-item-#1-">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne" aria-expanded="true"
                               aria-controls="collapseOne">平台月数据</a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel"
                         aria-labelledby="headingOne" aria-expanded="true">
                        <div class="panel-body">
                            <div class="admin-content" style="overflow-x:auto;overflow-y:hidden;">
                                <div class="section-box">
                                    <div class="titleDiv">
                                        <div class="notice-div">
                                            月新增注册用户：<span id="last_month_total">*</span>
                                        </div>
                                        <div class="select-fr" style="padding-right: 30px;">
											<span class="laydateBox">
                                                月份区间：<input type="text" id="monthStart" class="laydate-icon"
                                                            style="width:140px;" title=""/>
                                                至 &nbsp;<input type="text" id="monthEnd" class="laydate-icon"
                                                               style="width:140px;" title=""/>
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
                                                                <th style="min-width:70px;">月份</th>
                                                                <th style="min-width:90px;">PC(人)</th>
                                                                <th style="min-width:90px;">PC(PV)</th>
                                                                <th style="min-width:100px;">PC(UV)</th>
                                                                <th style="min-width:90px;">H5(人)</th>
                                                                <th style="min-width:90px;">H5(PV)</th>
                                                                <th style="min-width:100px;">H5(UV)</th>
                                                                <th style="min-width:90px;">IOS(人)</th>
                                                                <th style="min-width:90px;">IOS(安装量)</th>
                                                                <th style="min-width:90px;">Android(人)</th>
                                                                <th style="min-width:90px;">Android(安装量)</th>
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
                                                                        <input id="pageSize" value="12"
                                                                               class="inputPageSize" title="页数"/>
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
    }

    var addTbRow = function (data) {
        if (null !== data && undefined !== data && "" !== data) {
            var ele = "<tr><td>statisticsMonth</td>" +
                "<td>pcPopulation</td>" +
                "<td>pcPageView</td>" +
                "<td>pcUserView</td>" +
                "<td>h5Population</td>" +
                "<td>h5PageView</td>" +
                "<td>h5UserView</td>" +
                "<td>iosPopulation</td>" +
                "<td>iosInstallCount</td>" +
                "<td>androidPopulation</td>" +
                "<td>androidInstallCount</td>";
            ele = ele.replace("statisticsMonth", data.statisticsMonth)
                .replace("pcPopulation", data.pcPopulation)
                .replace("pcPageView", data.pcPageView)
                .replace("pcUserView", data.pcUserView)
                .replace("h5Population", data.h5Population)
                .replace("h5PageView", data.h5PageView)
                .replace("h5UserView", data.h5UserView)
                .replace("iosPopulation", data.iosPopulation)
                .replace("iosInstallCount", data.iosInstallCount)
                .replace("androidPopulation", data.androidPopulation)
                .replace("androidInstallCount", data.androidInstallCount);
            $("#data").append(ele);
        }
    };

    //显示统计列表
    var showNewUserData = function (pageNumber, pageSize) {
        $("#data").empty();
        $.get("/month/register/statistics/list.do", {
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
                    totalInfo.pcPopulation = 0;
                    totalInfo.pcPageView = 0;
                    totalInfo.pcUserView = 0;
                    totalInfo.h5Population = 0;
                    totalInfo.h5PageView = 0;
                    totalInfo.h5UserView = 0;
                    totalInfo.iosPopulation = 0;
                    totalInfo.iosInstallCount = 0;
                    totalInfo.androidPopulation = 0;
                    totalInfo.androidInstallCount = 0;
                    for (var i = 0; i < infoData.length; i++) {
                        addTbRow(infoData[i]);
                        totalInfo.pcPopulation = accAdd(totalInfo.pcPopulation, parseFloat(infoData[i].pcPopulation));
                        totalInfo.pcPageView = accAdd(totalInfo.pcPageView, parseFloat(infoData[i].pcPageView));
                        totalInfo.pcUserView = accAdd(totalInfo.pcUserView, parseFloat(infoData[i].pcUserView));
                        totalInfo.h5Population = accAdd(totalInfo.h5Population, parseFloat(infoData[i].h5Population));
                        totalInfo.h5PageView = accAdd(totalInfo.h5PageView, parseFloat(infoData[i].h5PageView));
                        totalInfo.h5UserView = accAdd(totalInfo.h5UserView, parseFloat(infoData[i].h5UserView));
                        totalInfo.iosPopulation = accAdd(totalInfo.iosPopulation, parseFloat(infoData[i].iosPopulation));
                        totalInfo.iosInstallCount = accAdd(totalInfo.iosInstallCount, parseFloat(infoData[i].iosInstallCount));
                        totalInfo.androidPopulation = accAdd(totalInfo.androidPopulation, parseFloat(infoData[i].androidPopulation));
                        totalInfo.androidInstallCount = accAdd(totalInfo.androidInstallCount, parseFloat(infoData[i].androidInstallCount));
                    }
                    addTbRow(totalInfo);
                }
            } else {
                $("#data").append("<tr><td colspan=\"23\">没有数据</td></tr>");
            }
        });
    };

    var getLastMonthTotal = function () {
        $.get("/month/register/statistics/last/register/total.do", {}, function (result) {
            $("#last_month_total").html(result.data);
        });
    };

    //查询显示
    var search = function () {
        showNewUserData(1, pageSize);
    };

    getLastMonthTotal();
    setMonthConfig();
    search();

    $("#search").click(function () {
        search();
    });

    $("#pageSize").change(function () {
        pageSize = parseInt($("#pageSize").val());
        search();
    });
</script>