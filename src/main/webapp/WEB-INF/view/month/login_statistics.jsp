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
                                            月登录总数：<span id="last_month_login">*</span>
                                        </div>
                                        <div class="notice-div">
                                            月玩游戏总数：<span id="last_month_play">*</span>
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
                                        <div class="select-fr" style="padding-right: 30px;">
                                            指标筛选：
                                            <select id="dateType" style="width:60px;" class="select">
                                                <option value="1">PC</option>
                                                <option value="2">android</option>
                                                <option value="3">IOS</option>
                                                <option value="4">H5</option>
                                            </select>
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
                                                                <th style="min-width:70px;">PC-登录人数</th>
                                                                <th style="min-width:70px;">PC-玩游戏人数</th>
                                                                <th style="min-width:70px;">PC-撩妹德州</th>
                                                                <th style="min-width:70px;">PC-乐盈电竞</th>
                                                                <th style="min-width:70px;">PC-一比分</th>
                                                                <th style="min-width:90px;">H5-登录人数</th>
                                                                <th style="min-width:90px;">H5-玩游戏人数</th>
                                                                <th style="min-width:100px;">H5-撩妹德州</th>
                                                                <th style="min-width:80px;">H5-乐盈电竞</th>
                                                                <th style="min-width:80px;">H5-一比分</th>
                                                                <th style="min-width:90px;">ios-登录人数</th>
                                                                <th style="min-width:90px;">ios-玩游戏人数</th>
                                                                <th style="min-width:100px;">ios-撩妹德州</th>
                                                                <th style="min-width:80px;">ios-乐盈电竞</th>
                                                                <th style="min-width:80px;">ios-一比分</th>
                                                                <th style="min-width:90px;">android-登录人数</th>
                                                                <th style="min-width:90px;">android-玩游戏人数</th>
                                                                <th style="min-width:100px;">android-撩妹德州</th>
                                                                <th style="min-width:80px;">android-乐盈电竞</th>
                                                                <th style="min-width:80px;">android-一比分</th>
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
    var pageSize = 12;

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

    var addTbRow = function (data) {
        if (null !== data && undefined !== data && "" !== data) {
            var ele = "<tr><td>statisticsMonth</td>" +
                "<td>PC_loginPopulation</td>" +
                "<td>PC_playPopulation</td>" +
                "<td>PC_liaoMeiDeZhou_loginPopulation</td>" +
                "<td>PC_leYinDianJing_loginPopulation</td>" +
                "<td>PC_yiBiFen_loginPopulation</td>" +
                "<td>H5_loginPopulation</td>" +
                "<td>H5_playPopulation</td>" +
                "<td>H5_liaoMeiDeZhou_loginPopulation</td>" +
                "<td>H5_leYinDianJing_loginPopulation</td>" +
                "<td>H5_yiBiFen_loginPopulation</td>" +
                "<td>ios_loginPopulation</td>" +
                "<td>ios_playPopulation</td>" +
                "<td>ios_liaoMeiDeZhou_loginPopulation</td>" +
                "<td>ios_leYinDianJing_loginPopulation</td>" +
                "<td>ios_yiBiFen_loginPopulation</td>" +
                "<td>android_loginPopulation</td>" +
                "<td>android_playPopulation</td>" +
                "<td>android_liaoMeiDeZhou_loginPopulation</td>" +
                "<td>android_leYinDianJing_loginPopulation</td>" +
                "<td>android_yiBiFen_loginPopulation</td>";
            ele = ele.replace("statisticsMonth", data.statisticsMonth)
                .replace("PC_loginPopulation", data.PC_loginPopulation)
                .replace("PC_playPopulation", data.PC_playPopulation)
                .replace("PC_liaoMeiDeZhou_loginPopulation", data.PC_liaoMeiDeZhou_loginPopulation)
                .replace("PC_leYinDianJing_loginPopulation", data.PC_leYinDianJing_loginPopulation)
                .replace("PC_yiBiFen_loginPopulation", data.PC_yiBiFen_loginPopulation)
                .replace("H5_loginPopulation", data.H5_loginPopulation)
                .replace("H5_playPopulation", data.H5_playPopulation)
                .replace("H5_liaoMeiDeZhou_loginPopulation", data.H5_liaoMeiDeZhou_loginPopulation)
                .replace("H5_leYinDianJing_loginPopulation", data.H5_leYinDianJing_loginPopulation)
                .replace("H5_yiBiFen_loginPopulation", data.H5_yiBiFen_loginPopulation)
                .replace("ios_loginPopulation", data.ios_loginPopulation)
                .replace("ios_playPopulation", data.ios_playPopulation)
                .replace("ios_liaoMeiDeZhou_loginPopulation", data.ios_liaoMeiDeZhou_loginPopulation)
                .replace("ios_leYinDianJing_loginPopulation", data.ios_leYinDianJing_loginPopulation)
                .replace("ios_yiBiFen_loginPopulation", data.ios_yiBiFen_loginPopulation)
                .replace("android_loginPopulation", data.android_loginPopulation)
                .replace("android_playPopulation", data.android_playPopulation)
                .replace("android_liaoMeiDeZhou_loginPopulation", data.android_liaoMeiDeZhou_loginPopulation)
                .replace("android_leYinDianJing_loginPopulation", data.android_leYinDianJing_loginPopulation)
                .replace("android_yiBiFen_loginPopulation", data.android_yiBiFen_loginPopulation);
            $("#data").append(ele);
        }
    };

    //显示统计列表
    var showNewUserData = function (pageNumber, pageSize) {
        $("#data").empty();
        $.post("/month/login/statistics/list.do", {
            monthStart: $('#monthStart').val(),
            monthEnd: $('#monthEnd').val(),
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
                    var info = {};
                    for (var i = 0; i < infoData.length; i++) {
                        for (var i = 0; i < infoData.length; i++) {
                            if (infoData[i].sourceType == 1) {

                            }
                            if (infoData[i].sourceType == 2) {

                            }
                            if (infoData[i].sourceType == 3) {

                            }
                            if (infoData[i].sourceType == 4) {

                            }
                            if ((i % 4) == 3) {
                                info.statisticsMonth = infoData[i].statisticsMonth
                                addTbRow(info);
                                info = {};
                            }
                        }
                    }
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
    setMonthConfig();

    $("#search").click(function () {
        search();
    });

    $("#pageSize").change(function () {
        pageSize = parseInt($("#pageSize").val());
        search();
    });
</script>