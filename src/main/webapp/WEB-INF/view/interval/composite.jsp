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
    <link rel="shortcut icon" href="../../../img/favicon.ico">
    <link rel="stylesheet" href="../../../css/bootstrap.min.css">
    <link rel="stylesheet" href="../../../css/style.css">
    <link rel="stylesheet" href="../../../css/zTreeStyle.css">
    <link rel="stylesheet" href="../../../css/dialogsdk.css">
    <link rel="stylesheet" href="../../../css/jquery-ui.css">
    <link rel="stylesheet" href="../../../css/admin-trend.css"/>
    <link rel="stylesheet" href="../../../lib/myPagination/css/style.css"/>
    <link rel="stylesheet" href="../../../lib/myPagination/js/myPagination/page.css"/>
    <link rel="stylesheet" href="../../../css/jquery-ui.css"/>
    <link rel="stylesheet" href="../../../css/interval/tipso.css"/>
    <link rel="stylesheet" href="../../../css/interval/tipso.min.css"/>
    <!-- [if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif] -->
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
    <script src="../../../lib/myPagination/js/myPagination/jquery.myPagination6.0.js"></script>
    <script src="../../../lib/jquery-ui.js"></script>
    <script src="../../../lib/datecontrol.js"></script>
    <script src="../../../lib/echart/dist/echarts.js"></script>
    <script src="../../../lib/tool.js"></script>
    <script src="../../../lib/tool.js"></script>
    <script src="../../../js/interval/tipso.js"></script>
    <script src="../../../js/interval/tipso.min.js"></script>
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
                        <li>平台综合时段数据统计</li>
                    </ul>
                </div>
                <!--body start-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title" id="-collapsible-group-item-#1-">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne" aria-expanded="true"
                               aria-controls="collapseOne" class="">平台数据实时统计</a>
                            <span class="inner"><span id="tip2" data-tipso="平台数据实时统计：统计当日数据，每30分钟请求一次数据。00:00-23:59为一日。">？</span></span>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in" aria-expanded="true">
                        <div class="panel-body">
                            <div class="admin-content">
                                <div class="section-box">
                                    <div class="titleDiv">
                                        <div style="margin-left:8px;">
                                            <span> 时间段选择：
                                                <select id="dateStarts" onchange="dateChange()">
                                                    <option value="-999" selected>请选择</option>
                                                </select>
                                                至
                                                <select id="dateEnds" onchange="dateChange()">
                                                     <option value="-999" selected>请选择</option>
                                                </select>
                                                <button type="button" id="search" class="btn btn-primary btn-sm"
                                                        onclick="search();">
                                                    <i class="icon-search icon-white"
                                                       style="height: 24px;padding-top:0px;padding-bottom:0px;"></i>&nbsp;查&nbsp;&nbsp;询&nbsp;
                                                </button>
                                                <button type="button" id="reset" class="btn btn-primary btn-sm"
                                                        onclick="reset();">
                                                    <i class="icon-search icon-white"
                                                       style="height: 24px;padding-top:0px;padding-bottom:0px;"></i>&nbsp;重&nbsp;&nbsp;置&nbsp;
                                                </button>
											</span>
                                        </div>
                                    </div>
                                    <div class="whiteDiv tab-content">
                                        <ul id="sortable" class="ui-widget-content sortable">
                                            <li class="ui-state-default">
                                                <div class="ui-widget-content" style="height:80px;">
                                                    <div class="sortHandle">截止目前实时数据</div>
                                                    <div style="width:100%;height:5%;text-align: center;margin:0 auto;">
                                                        <table class="tableList1">
                                                            <colgroup>
                                                                <col width="100"/>
                                                                <col width="100"/>
                                                                <col width="100"/>
                                                                <col width="100"/>
                                                                <col width="100"/>
                                                            </colgroup>
                                                            <thead>
                                                            <tr>
                                                                <th style="border:1px solid black">
                                                                    注册人数<br/>
                                                                    <label id="registerPopulationNum"></label>
                                                                </th>
                                                                <th style="border:1px solid black">
                                                                    登录人数<br/>
                                                                    <label id="loginPopulationNum"></label>
                                                                </th>
                                                                <th style="border:1px solid black">
                                                                    充值人数<br/>
                                                                    <label id="rechargePopulationNum"></label>
                                                                </th>
                                                                <th style="border:1px solid black">
                                                                    充值次数<br/>
                                                                    <label id="rechargeCountNum"></label>
                                                                </th>
                                                                <th style="border:1px solid black">
                                                                    充值金额<br/>
                                                                    <label id="rechargeAmountNum"></label>
                                                                </th>
                                                            </tr>
                                                            </thead>
                                                        </table>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="ui-state-default">
                                                <div style="padding-bottom:60px;"
                                                     class="ui-widget-content resize resizePanel">
                                                    <div class="sortHandle">分时段列表</div>
                                                    <div class="tablePanel">
                                                        <table class="tableList1">
                                                            <colgroup>
                                                                <col width="120"/>
                                                                <col width="90"/>
                                                                <col width="100"/>
                                                                <col width="100"/>
                                                                <col width="100"/>
                                                                <col width="80"/>
                                                            </colgroup>
                                                            <thead>
                                                            <tr>
                                                                <th>时间段尾</th>
                                                                <th>注册人数</th>
                                                                <th>登陆人数</th>
                                                                <th>充值人数</th>
                                                                <th>充值次数</th>
                                                                <th>充值金额</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody id="newUserData"></tbody>
                                                        </table>
                                                    </div>
                                                    <%--<div id="page"></div>--%>
                                                    <table class="tablePage">
                                                        <tr>
                                                            <td>
                                                                <div class="divPage">
                                                                    <span class="spanPageSize">每页个数：</span>
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
                                            </li>
                                            <li class="ui-state-default">
                                                <div class="ui-widget-content resize" style="height:300px;">
                                                    <div class="sortHandle">分时段曲线图</div>
                                                    <div style="width:100%;height:95%;">
                                                        <div style="margin-left: -10px;">
                                                            <span>客户端类型：</span>
                                                            <input type="radio" name="sourceType" value="0"
                                                                   class="sourceType"
                                                                   onchange="deviceTypeChange(this);" checked title=""/>
                                                            全部
                                                            <input type="radio" name="sourceType" value="1"
                                                                   class="sourceType"
                                                                   onchange="deviceTypeChange(this);" title=""/>
                                                            PC
                                                            <input type="radio" name="sourceType" value="2"
                                                                   class="sourceType"
                                                                   onchange="deviceTypeChange(this);" title=""/>
                                                            Android
                                                            <input type="radio" name="sourceType" value="3"
                                                                   class="sourceType"
                                                                   onchange="deviceTypeChange(this);" title=""/>
                                                            IOS
                                                            <input type="radio" name="sourceType" value="4"
                                                                   class="sourceType"
                                                                   onchange="deviceTypeChange(this);" title=""/>
                                                            H5
                                                        </div>
                                                        <div id="trendline" class="trendline"></div>
                                                    </div>
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
    var initSearchDate = ["00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30",
        "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
        "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30", "24:00"];

    var date = new Date();
    var separator = "-";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentDate = date.getFullYear() + separator + month + separator + strDate + " ";

    // js定时器
    window.setInterval("search()", 30 * 60 * 1000);

    // 加载tips
    $('#tip2').tipso({
        useTitle: false,
        position: 'bottom'
    });



    // 统计
    var dateChange = function () {
        if ("-999" != $("#dateStarts").val() && "-999" != $("#dateEnds").val()) {
            var dateEnds = new Date($("#dateEnds").val()).getTime();
            var dateStarts = new Date($("#dateStarts").val()).getTime();
            if (dateEnds < dateStarts) {
                layer.alert("起始时间必须小于等于结束时间", {
                    icon: 5
                });
                $("#dateStarts").val("-999");
                $("#dateEnds").val("-999");
            }
        }
    };


    window.onload = function () {
        for (var i = 0; i < initSearchDate.length; i++) {
            $("#dateStarts").append("<option value='" + currentDate + initSearchDate[i] + "'>" + initSearchDate[i] + "</option>");
            $("#dateEnds").append("<option value='" + currentDate + initSearchDate[i] + "'>" + initSearchDate[i] + "</option>");
        }
    }

    var reset = function () {
        $("#dateStarts").val("-999");
        $("#dateEnds").val("-999");
    };

    var intervalNum = function () {
        $("#registerPopulationNum").empty();
        $("#loginPopulationNum").empty();
        $("#rechargePopulationNum").empty();
        $("#rechargeCountNum").empty();
        $("#rechargeAmountNum").empty();
        $.post("/interval/platform/intervalNum.do", {
            startDate: $("#dateStarts").val() != -999 ? $("#dateStarts").val() : null,
            endDate: $("#dateEnds").val() != -999 ? $("#dateEnds").val() : null
        }, function (result) {
            var json = result.data;
            if (null != json && undefined != json) {
                $("#registerPopulationNum").html(json.registerPopulation);
                $("#loginPopulationNum").html(json.loginPopulation);
                $("#rechargePopulationNum").html(json.rechargePopulation);
                $("#rechargeCountNum").html(json.rechargeCount);
                $("#rechargeAmountNum").html(json.rechargeAmount.toFixed(2));
            } else {
                $("#registerPopulationNum").empty();
                $("#loginPopulationNum").empty();
                $("#rechargePopulationNum").empty();
                $("#rechargeCountNum").empty();
                $("#rechargeAmountNum").empty();
            }
        });
    };

    $(function () {
        $("#sortable").sortable({cursor: "move", handle: ".sortHandle"});
        $(".resize").resizable({minHeight: 200, minWidth: 300});
        $("#trendline").resize(
            function () {
                if (undefined != trendline) {
                    trendline.resize();
                }
            }
        );
        $(window).resize(
            function () {
                if (undefined != trendline) {
                    trendline.resize();
                }
            }
        );
    });

    var pageSizeLimit = function (value) {
        if ("" != value && value * 1 > 200) {
            layer.alert("每页个数最多只能为50", {
                icon: 5
            });
        }
        return value;
    };

    var pageSizeNotEmpty = function (value) {
        if ("" == value) {
            layer.alert("每页个数不能为空", {
                icon: 5
            });
            value = 10;
        }
        if (value >= 30) {
            value = 30
        }
        showNewUserData(1,value);
        return value;
    };

    var pageSize = 10;
    var deviceTypeChange = function (ele) {
        if (ele.id == "cltTypeAll") {
            $(".deviceType").prop("checked", ele.checked);
        } else {
            if (!ele.checked) {
                $("#cltTypeAll").prop("checked", false);
            }
        }
        loadNewUserDataTrendLine(echartsCopy);
    };

    var getDeviceType = function () {
        var devices = $(".deviceType");
        var deviceTypes = "";
        for (var i = 0; i < devices.length; i++) {
            if (devices[i].checked) {
                if ("" != deviceTypes) {
                    deviceTypes += ",";
                }
                deviceTypes += devices.eq(i).val();
            }
        }
        return deviceTypes;
    };

    var addTbRow = function (data) {
        if (null != data && undefined != data && "" != data) {
            var ele = "<tr><td class=\"date\">statisticsTime</td><td>registerPopulation</td><td>loginPopulation</td><td>rechargePopulation</td><td>rechargeCount</td><td>rechargeAmount</td></tr>";
            ele = ele.replace("statisticsTime", data.statisticsTime).replace("registerPopulation", data.registerPopulation).replace("loginPopulation", data.loginPopulation).replace("rechargePopulation", data.rechargePopulation).replace("rechargeCount", data.rechargeCount).replace("rechargeAmount", data.rechargeAmount);
            $("#newUserData").append(ele);
        }
    };

    //显示统计列表
    var showNewUserData = function (pageNumber, pageSize) {
        $("#newUserData").empty();
        $.post("/interval/platform/list.do", {
            startDate: $("#dateStarts").val() != -999 ? $("#dateStarts").val() : null,
            endDate: $("#dateEnds").val() != -999 ? $("#dateEnds").val() : null,
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
                    $("#newUserData").append("<tr><td colspan=\"10\">没有数据</td></tr>");
                } else {
                    var sumRechargeAmount = 0;
                    var sumRegisterPopulation = 0;
                    var sumLoginPopulation = 0;
                    var sumRechargePopulation = 0;
                    var sumRechargeCount = 0;
                    for (var i = 0; i < infoData.length; i++) {
                        sumRegisterPopulation = accAdd(sumRegisterPopulation, infoData[i].registerPopulation);
                        sumLoginPopulation = accAdd(sumLoginPopulation, infoData[i].loginPopulation);
                        sumRechargePopulation = accAdd(sumRechargePopulation, infoData[i].rechargePopulation);
                        sumRechargeCount = accAdd(sumRechargeCount, infoData[i].rechargeCount);
                        sumRechargeAmount = accAdd(sumRechargeAmount, infoData[i].rechargeAmount);
                        var ele = {
                            statisticsTime: infoData[i].statisticsTime.slice(10, 16),
                            registerPopulation: infoData[i].registerPopulation,
                            loginPopulation: infoData[i].loginPopulation,
                            rechargePopulation: infoData[i].rechargePopulation,
                            rechargeCount: infoData[i].rechargeCount,
                            rechargeAmount: infoData[i].rechargeAmount.toFixed(2)
                        };
                        addTbRow(ele);
                    }
                    var ele1 = {
                        statisticsTime: "总计",
                        registerPopulation: sumRegisterPopulation,
                        loginPopulation: sumLoginPopulation,
                        rechargePopulation: sumRechargePopulation,
                        rechargeCount: sumRechargeCount,
                        rechargeAmount: sumRechargeAmount.toFixed(2)
                    };
                    addTbRow(ele1);
                }
            } else {
                $("#newUserData").append("<tr><td colspan=\"10\">没有数据</td></tr>");
            }
        });
    };

    //查询显示
    var search = function () {
        pageSize = $("#pageSize").val();
        showNewUserData(1, pageSize);
        loadNewUserDataTrendLine(echartsCopy);
        intervalNum();
    };

    var trendline;

    function loadNewUserDataTrendLine(echarts) {
        var startDate = $("#dateStarts").val() != -999 ? $("#dateStarts").val() : null;
        var endDate = $("#dateEnds").val() != -999 ? $("#dateEnds").val() : null;

        $.ajax({
            url: "/interval/platform/chart.do",
            data: {
                startDate: startDate,
                endDate: endDate,
                sourceType: $('.sourceType:radio:checked').val()
            },
            success: function (result) {
                var json = result.data;
                trendline = echarts.init(document.getElementById('trendline'));
                trendline.setOption({
                    title: {
                        x: '20',
                        text: '',
                        textStyle: {
                            fontSize: '16',
                            fontWeight: 'bold',
                        }
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    color: ['#2ec7c9', '#b6a2de', '#5ab1ef', '#ffb980', '#d87a80'],
                    legend: {
                        show: true,
                        orient: 'horizontal',
                        data: [{
                            name: '注册人数',
                            textStyle: {
                                fontSize: 12,
                                color: '#666'
                            },
                            icon: 'roundRect'
                        }, {
                            name: '登录人数',
                            textStyle: {
                                fontSize: 12,
                                color: '#666'
                            },
                            icon: 'roundRect'
                        }, {
                            name: '充值人数',
                            textStyle: {
                                fontSize: 12,
                                color: '#666'
                            },
                            icon: 'roundRect'
                        }, {
                            name: '充值次数',
                            textStyle: {
                                fontSize: 12,
                                color: '#666'
                            },
                            icon: 'roundRect'
                        }, {
                            name: '充值金额（￥）',
                            textStyle: {
                                fontSize: 12,
                                color: '#666'
                            },
                            icon: 'roundRect'
                        }
                        ]

                    },
                    grid: {
                        borderWidth: 0,
                        y: 50,
                        y2: 30,
                        x: 50,
                        x2: 50
                    },
                    xAxis: [{
                        type: 'category',
                        boundaryGap: false,
                        data: json.scales,
                        splitLine: {
                            show: true,
                            lineStyle: {
                                color: '#f5f5f5',
                                width: 1,
                                type: 'solid'
                            }
                        },
                        axisTick: {
                            show: true,
                            lineStyle: {
                                color: '#ccc',
                                width: 1,
                                type: 'solid'
                            }
                        },
                        axisLine: {
                            lineStyle: {
                                color: '#ccc',
                                width: 1,
                                type: 'solid'
                            }
                        },
                        axisLabel: {
                            textStyle: {
                                color: "#999"
                            }
                        }
                    }],
                    yAxis: [{
                        type: 'value',
                        axisLine: {
                            show: true
                        },
                        axisTick: {
                            show: true
                        },
                        axisLabel: {
                            show: true,
                            formatter: function (val) {
                                return val;
                            }
                        },
                        splitArea: {
                            show: false
                        },
                        splitLine: {
                            show: true,
                            lineStyle: {
                                color: '#f5f5f5',
                                width: 1,
                                type: 'solid'
                            }
                        }
                    }],
                    series: [
                        {
                            name: '注册人数',
                            type: 'line',
                            data: json.registerPopulationList
                        },
                        {
                            name: '登录人数',
                            type: 'line',
                            data: json.loginCountList
                        },
                        {
                            name: '充值人数',
                            type: 'line',
                            data: json.rechargePopulationList
                        },
                        {
                            name: '充值次数',
                            type: 'line',
                            data: json.rechargeCountList
                        },
                        {
                            name: '充值金额（￥）',
                            type: 'line',
                            data: json.rechargeAmountList
                        }
                    ]
                });

            }
        })
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