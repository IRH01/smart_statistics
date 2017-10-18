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
                                                                    <td><input placeholder="请选择日期" class="laydate-icon" style="height:25px;" id="dateStart" readonly> </td>
                                                                    <td>
                                                                        <span class="span">&nbsp;至&nbsp;</span>
                                                                    </td>
                                                                    <td><input class="laydate-icon" id="dateEnd" style="height:25px;" placeholder="请选择日期" readonly></td>
                                                                    <td style="line-height:1;">
                                                                        <button type="button" id="search" class="btn btn-primary btn-sm" onclick="search();">
                                                                            <i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;"></i>&nbsp;查&nbsp;&nbsp;询&nbsp;
                                                                        </button>
                                                                    </td>
                                                                    <td style="line-height:1;">
                                                                        <button type="button" id="reset" class="btn btn-primary btn-sm" onclick="reset();">
                                                                            <i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;"></i>&nbsp;重&nbsp;&nbsp;置&nbsp;
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
                                                            <td><div class="divPage"><span class="spanPageSize">每页个数：</span><input id="pageSize" value="10" class="inputPageSize" onKeypress="return intInput(event);" onKeyup="value=pageSizeLimit(value);" onblur="value=pageSizeNotEmpty(value);"/></div></td>
                                                            <td><span class="spanPageSize">总记录数：</span><span id="totalCount" class="spanPageSize"></span></td>
                                                            <td><span class="spanPageSize">总页数：</span><span id="totalPage" class="spanPageSize"></span></td>
                                                            <td class="tablePageTd"><div id="page"></div></td>
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
    var date = new Date();
    var seperator1 = "-";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " ;

    // 统计
    var dateChange = function () {
        $("#dateType").val(0);
        search();
    }


    $(function () {
        $("#sortable").sortable({cursor: "move", handle: ".sortHandle"});
        $(".resize").resizable({minHeight: 200, minWidth: 300});
    });


    var pageSize = 30;
    var deviceTypeChange = function (ele) {
        if (ele.id == "cltTypeAll") {
            $(".deviceType").prop("checked", ele.checked);
        } else {
            if (!ele.checked) {
                $("#cltTypeAll").prop("checked", false);
            }
        }
        loadNewUserDataTrendLine(echartsCopy);
    }

    var getDeviceType = function () {
        var devices = $(".deviceType");
        var deviceTypes = "";
        for (i = 0; i < devices.length; i++) {
            if (devices[i].checked) {
                if ("" != deviceTypes) {
                    deviceTypes += ",";
                }
                deviceTypes += devices.eq(i).val();
            }
        }
        return deviceTypes;
    }
    setDateRangeConfig("dateStart","dateEnd",null,true);
    //    var dateValue = setDateRangeConfig("dateStart", "dateEnd", dateChange);
    var dateTypeChange = function () {
        setDateTypeChange("dateType", "dateStart", "dateEnd", dateValue.dateStart, dateValue.dateEnd, search);
    }
    var showTbCl = function(){
        $("#data").append("<tr><td rowspan=\"2\">日期</td><td rowspan=\"2\">新增用户</td>\n" +
            "<td colspan=\"9\">留存率</td></tr><tr><td>1天后</td><td>2天后</td><td>3天后</td><td>4天后</td><td>5天后</td><td>6天后</td><td>7天后</td><td>14天后</td><td>30天后</td></tr>");
    }

    var addTbRow = function (data) {
        if (null != data && undefined != data && "" != data) {
            var ele = "<tr><td class=\"date\">statisticsDay</td><td>registerCount</td><td>one</td><td>two</td><td>three</td><td>four</td><td>five</td><td>six</td><td>seven</td><td>fourteen</td><td>thirty</td></tr>";
            ele = ele.replace("statisticsDay", data.statisticsDay).replace("registerCount", data.registerCount).replace("one", data.one).replace("two", data.two).
            replace("three", data.three).replace("four", data.four).replace("five", data.five).replace("six", data.six)
                .replace("seven", data.seven).replace("fourteen", data.fourteen).replace("thirty", data.thirty);
            $("#data").append(ele);
        }
    }

    //显示统计列表
    var showNewUserData = function (pageNumber, pageSize) {
        $("#data").empty()
        showTbCl();
        $.post("/daily/dailyKeepRecord/list.do", {
            startDate: $('#dateStart').val(),
            endDate: $('#dateEnd').val(),
            pageNumber: pageNumber,
            pageSize: pageSize
        }, function (data) {
            var json = JSON.parse(data);
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
                    var registerPopulationNum=0;
                    var registerExpCountNum=0;
                    var registerExpCountRateNum=0;
                    var realExpCountNum=0;
                    var virtualExpCountNum=0;

                    var newUserRechargePopulationNum=0;
                    var newUserRechargeCountNum=0;
                    var newUserRechargeAmountNum=0;
                    var newUserRechargeRateNum=0;
                    var newUserARPUNum=0;
                    var newUserARPPUNum=0;
                    var newUserLoginCountNum=0;
                    var newUserPlayCountNum=0;
                    var newUserPlayRateNum=0;

                    var newUserRechargePopulationNum=0;
                    var newUserRechargeCountNum=0;
                    var newUserRechargeAmountNum=0;
                    var newUserRechargeRateNum=0;
                    var newUserARPUNum=0;
                    var newUserARPPUNum=0;
                    var newUserLoginCountNum=0;
                    var newUserPlayCountNum=0;
                    var newUserPlayRateNum=0;

                    var nextDayStayCountNum=0;
                    var nextDayStayRateNums=0

                    for (var i = 0; i < infoData.length; i++) {
//                        pcPopulationNum=accAdd(pcPopulationNum,infoData[i].pcPopulation);
//                        pcPageViewNum=accAdd(pcPageViewNum,infoData[i].pcPageView);
//                        pcUserViewNum=accAdd(pcUserViewNum,infoData[i].pcUserView);
//                        h5PopulationNum=accAdd(h5PopulationNum,infoData[i].h5Population);
//                        h5PageViewNum=accAdd(h5PageViewNum,infoData[i].h5PageView);
//                        h5UserViewNum=accAdd(h5UserViewNum,infoData[i].h5UserView);
//                        iosPopulationNum=accAdd(iosPopulationNum,infoData[i].iosPopulation);
//                        iosInstallCountNum=accAdd(iosInstallCountNum,infoData[i].iosInstallCount);
//                        androidPopulationNum=accAdd(androidPopulationNum,infoData[i].androidPopulation);
//                        androidInstallCountNum=accAdd(androidInstallCountNum,infoData[i].androidInstallCount);

//                        var nextDayStayRateNum = 0;
//                        if ((i+1)<infoData.length) {
//                            nextDayStayRateNum = accDiv(accAdd(infoData[i].oldUserLoginCount,infoData[i].newUserLoginCount),infoData[i+1].statisticsDay);
//                        } else {
//                            nextDayStayRateNum = 0;
//                        }

                        var ele = {
                            statisticsDay: infoData[i].statisticsDay,
                            registerCount: infoData[i].registerCount,
                            one: infoData[i].one+"("+convertToPercentFormat(infoData[i].onePercent)+")",
                            two: infoData[i].two+"("+convertToPercentFormat(infoData[i].twoPercent)+")",
                            three: infoData[i].three+"("+convertToPercentFormat(infoData[i].threePercent)+")",
                            four: infoData[i].four+"("+convertToPercentFormat(infoData[i].fourPercent)+")",

                            five: infoData[i].five+"("+convertToPercentFormat(infoData[i].fivePercent)+")",
                            six: infoData[i].six+"("+convertToPercentFormat(infoData[i].sixPercent)+")",
                            seven: infoData[i].seven+"("+convertToPercentFormat(infoData[i].sevenPercent)+")",
                            fourteen: infoData[i].seven+"("+convertToPercentFormat(infoData[i].fourteenPercent)+")",
                            thirty: infoData[i].thirty+"("+convertToPercentFormat(infoData[i].thirtyPercent)+")"
                        }
                        addTbRow(ele);

//                        var ele1 = {
//                            statisticsTime:"总计",
//                            registerPopulation:sumRegisterPopulation,
//                            loginPopulation:sumLoginPopulation,
//                            rechargePopulation:sumRechargePopulation,
//                            rechargeCount:sumRechargeCount,
//                            rechargeAmount:sumRechargeAmount.toFixed(2)
//                        }
//                        addTbRow(ele1);
                    }
                }
            } else {
                $("#totalCount").html(0);
                $("#totalPage").html(0);
                $("#data").append("<tr><td colspan=\"26\">没有数据</td></tr>");
            }
        });
    }

    //查询显示
    var search = function () {
        pageSize = $("#pageSize").val();
        showNewUserData(1, pageSize);
    }

    var reset = function() {
        $("#dateStart").val("");
        $("#dateEnd").val("");
    }



    var echartsCopy;
    // 路径配置
    require.config({
        paths: {
            echarts: '/lib/echart/dist'
        }
    });

    // 使用
    require(['echarts', 'echarts/chart/line'],
        function (ec) {
            echartsCopy = ec;
            search();
        });
</script>