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
    var date = new Date();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }

    $(function () {
        $("#sortable").sortable({cursor: "move", handle: ".sortHandle"});
        $(".resize").resizable({minHeight: 200, minWidth: 300});
    });

    var pageSize = 30;

    setDateRangeConfig("dateStart", "dateEnd", null, true);

    var showTbCl = function () {
        $("#data").append("<tr><td rowspan=\"2\">日期</td><td rowspan=\"2\">新增用户</td>\n" +
            "<td colspan=\"9\">留存率</td></tr><tr><td>1天后</td><td>2天后</td><td>3天后</td><td>4天后</td>" +
            "<td>5天后</td><td>6天后</td><td>7天后</td><td>14天后</td><td>30天后</td></tr>");
    };

    var addTbRow = function (data) {
        if (null != data && undefined != data && "" != data) {
            var ele = "<tr><td class=\"date\">statisticsDay</td><td>registerCount</td><td>oneRemain</td><td>twoRemain</td>" +
                "<td>threeRemain</td><td>fourRemain</td><td>fiveRemain</td><td>sixRemain</td><td>sevenRemain</td><td>fourteenRemain</td><td>thirtyRemain</td></tr>";
            ele = ele.replace("statisticsDay", data.statisticsDay).replace("registerCount", data.registerCount)
                .replace("oneRemain", data.oneRemain).replace("twoRemain", data.twoRemain).replace("threeRemain", data.threeRemain)
                .replace("fourRemain", data.fourRemain).replace("fiveRemain", data.fiveRemain).replace("sixRemain", data.sixRemain)
                .replace("sevenRemain", data.sevenRemain).replace("fourteenRemain", data.fourteenRemain).replace("thirtyRemain", data.thirtyRemain);
            $("#data").append(ele);
        }
    };

    //显示统计列表
    var showNewUserData = function (pageNumber, pageSize) {
        $("#data").empty();
        showTbCl();
        $.post("/daily/keep/record/list.do", {
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
                    var registerCountNum = 0;
                    var oneNum = 0;
                    var twoNum = 0;
                    var threeNum = 0;
                    var fourNum = 0;
                    var fiveNum = 0;
                    var sixNum = 0;
                    var sevenNum = 0;
                    var fourteenNum = 0;
                    var thirtyNum = 0;
                    var count = infoData.length;
                    for (var i = 0; i < infoData.length; i++) {
                        registerCountNum = accAdd(registerCountNum, infoData[i].registerCount);
                        oneNum = accDiv(accAdd(oneNum, infoData[i].onePercent), count);
                        twoNum = accDiv(accAdd(twoNum, infoData[i].twoPercent), count);
                        threeNum = accDiv(accAdd(threeNum, infoData[i].threePercent), count);
                        fourNum = accDiv(accAdd(fourNum, infoData[i].fourPercent), count);
                        fiveNum = accDiv(accAdd(fiveNum, infoData[i].fivePercent), count);
                        sixNum = accDiv(accAdd(sixNum, infoData[i].sixPercent), count);
                        sevenNum = accDiv(accAdd(sevenNum, infoData[i].sevenPercent), count);
                        fourteenNum = accDiv(accAdd(fourteenNum, infoData[i].fourteenPercent), count);
                        thirtyNum = accDiv(accAdd(thirtyNum, infoData[i].thirtyPercent), count);
                        var ele = {
                            statisticsDay: infoData[i].statisticsDay,
                            registerCount: infoData[i].registerCount,
                            oneRemain: infoData[i].oneRemain + "(" + convertToPercentFormat(infoData[i].onePercent) + ")",
                            twoRemain: infoData[i].twoRemain + "(" + convertToPercentFormat(infoData[i].twoPercent) + ")",
                            threeRemain: infoData[i].threeRemain + "(" + convertToPercentFormat(infoData[i].threePercent) + ")",
                            fourRemain: infoData[i].fourRemain + "(" + convertToPercentFormat(infoData[i].fourPercent) + ")",
                            fiveRemain: infoData[i].fiveRemain + "(" + convertToPercentFormat(infoData[i].fivePercent) + ")",
                            sixRemain: infoData[i].sixRemain + "(" + convertToPercentFormat(infoData[i].sixPercent) + ")",
                            sevenRemain: infoData[i].sevenRemain + "(" + convertToPercentFormat(infoData[i].sevenPercent) + ")",
                            fourteenRemain: infoData[i].sevenRemain + "(" + convertToPercentFormat(infoData[i].fourteenPercent) + ")",
                            thirtyRemain: infoData[i].thirtyRemain + "(" + convertToPercentFormat(infoData[i].thirtyPercent) + ")"
                        };
                        addTbRow(ele);
                    }

                    var ele1 = {
                        statisticsDay: "总计",
                        registerCount: registerCountNum,
                        oneRemain: oneNum,
                        twoRemain: twoNum,
                        threeRemain: threeNum,
                        fourRemain: fourNum,
                        fiveRemain: fiveNum,
                        sixRemain: sixNum,
                        sevenRemain: sevenNum,
                        fourteenRemain: sevenNum,
                        thirtyRemain: thirtyNum

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