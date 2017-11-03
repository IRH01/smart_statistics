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
    <!-- [if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif] -->
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
                        <li>接口统计</li>
                    </ul>
                </div>
                <!--body start-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title" id="-collapsible-group-item-#1-">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne" aria-expanded="true"
                               aria-controls="collapseOne" class="">接口统计</a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in" aria-expanded="true">
                        <div class="panel-body">
                            <div class="admin-content">
                                <div class="section-box">
                                    <div class="whiteDiv tab-content">
                                        <ul id="sortable" class="ui-widget-content sortable">
                                            <li class="ui-state-default">
                                                <div class="ui-widget-content resize" style="height:80px;">
                                                    <div class="sortHandle">截止目前实时数据</div>
                                                    <div style="width:100%;height:5%;text-align: center;margin:0 auto;">
                                                        <table class="tableList1">
                                                            <colgroup>
                                                                <col width="100"/>
                                                                <col width="100"/>
                                                                <col width="100"/>
                                                                <col width="100"/>
                                                            </colgroup>
                                                            <thead>
                                                            <tr>
                                                                <th style="border:1px solid black">
                                                                    注册请求数<br/>
                                                                    <label id="registerRequestNum"></label>
                                                                </th>
                                                                <th style="border:1px solid black">
                                                                    注册完成数<br/>
                                                                    <lable id="registerCompleteNum"></lable>
                                                                </th>
                                                                <th style="border:1px solid black">
                                                                    充值请求数<br/>
                                                                    <label id="rechargeRequestNum"></label>
                                                                </th>
                                                                <th style="border:1px solid black">
                                                                    充值完成数<br/>
                                                                    <label id="rechargeCompleteNum"></label>
                                                                </th>
                                                            </tr>
                                                            </thead>
                                                        </table>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="ui-state-default">
                                                <div class="ui-widget-content resize" style="height:300px;">
                                                    <div class="sortHandle">注册分时段曲线图</div>
                                                    <div style="width:100%;height:95%;">
                                                        <div id="registerTrendLine" class="trendline"></div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li class="ui-state-default">
                                                <div class="ui-widget-content resize" style="height:300px;">
                                                    <div class="sortHandle">充值分时段曲线图</div>
                                                    <div style="width:100%;height:95%;">
                                                        <div id="rechargeTrendLine" class="trendline"></div>
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
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }

    var intervalNum = function () {
        $("#registerRequestNum").empty();
        $("#registerCompleteNum").empty();
        $("#rechargeRequestNum").empty();
        $("#rechargeCompleteNum").empty();
        $.post("/interval/interface/intervalNum.do", {
            startDate: $('#dateStarts').val(),
            endDate: $('#dateEnds').val()
        }, function (result) {
            var json = result.data;
            if (null != json && undefined != json) {
                var infoData = json.list;
                for (var i = 0; i < infoData.length; i++) {
                    if (infoData[i].interfaceCode == 1) { // 注册
                        if (infoData[i].operateType == 1) { // 请求
                            $("#registerRequestNum").html(infoData[i].operateCount);
                        } else if (infoData[i].operateType == 2) { // 完成
                            $("#registerCompleteNum").html(infoData[i].operateCount);
                        }
                    } else if (infoData[i].interfaceCode == 2) {// 充值
                        if (infoData[i].operateType == 1) { // 请求
                            $("#rechargeRequestNum").html(infoData[i].operateCount);
                        } else if (infoData[i].operateType == 2) { // 完成
                            $("#rechargeCompleteNum").html(infoData[i].operateCount);
                        }
                    }
                }
            } else {
                $("#registerRequestNum").empty();
                $("#registerCompleteNum").empty();
                $("#rechargeRequestNum").empty();
                $("#rechargeCompleteNum").empty();
            }
        });
    };

    $(function () {
        $("#sortable").sortable({cursor: "move", handle: ".sortHandle"});
        $(".resize").resizable({minHeight: 200, minWidth: 300});
        $("#rechargeTrendLine").resize(
            function () {
                if (undefined != rechargeTrendLine) {
                    rechargeTrendLine.resize();
                }
            }
        );
        $(window).resize(
            function () {
                if (undefined != rechargeTrendLine) {
                    rechargeTrendLine.resize();
                }
            }
        );
        $("#registerTrendLine").resize(
            function () {
                if (undefined != registerTrendLine) {
                    registerTrendLine.resize();
                }
            }
        );
        $(window).resize(
            function () {
                if (undefined != registerTrendLine) {
                    registerTrendLine.resize();
                }
            }
        );
    });

    //查询显示
    var search = function () {
        loadRechargeDataTrendLine(echartsCopy);
        loadRegisterDataTrendLine(echartsCopy);
        intervalNum();
    };

    var registerTrendLine;

    function loadRegisterDataTrendLine(echarts) {
        var startDate = $("#dateStarts").val();
        var endDate = $("#dateEnds").val();

        $.ajax({
            url: "/interval/interface/chart.do",
            data: {
                startDate: startDate,
                endDate: endDate,
                interfaceCode: 1
            },
            success: function (result) {
                var json = result.data;
                registerTrendLine = echarts.init(document.getElementById("registerTrendLine"));
                registerTrendLine.setOption({
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
                            name: '注册请求',
                            textStyle: {
                                fontSize: 12,
                                color: '#666'
                            },
                            icon: 'roundRect'
                        }, {
                            name: '注册完成',
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
//                            interval: 0.5,
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
                            name: '注册请求',
                            type: 'line',
                            data: json.requestList
                        },
                        {
                            name: '注册完成',
                            type: 'line',
                            data: json.completeList
                        }
                    ]
                });

            }
        })
    }

    var rechargeTrendLine;

    function loadRechargeDataTrendLine(echarts) {
        var startDate = $("#dateStarts").val();
        var endDate = $("#dateEnds").val();

        $.ajax({
            url: "/interval/interface/chart.do",
            data: {
                startDate: startDate,
                endDate: endDate,
                interfaceCode: 2
            },
            success: function (result) {
                var json = result.data;
                rechargeTrendLine = echarts.init(document.getElementById("rechargeTrendLine"));
                rechargeTrendLine.setOption({
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
                    color: ['#2ec7c9', '#b6a2de', '#5ab1ef',
                        '#ffb980', '#d87a80'],
                    legend: {
                        show: true,
                        orient: 'horizontal',
                        data: [{
                            name: '充值请求',
                            textStyle: {
                                fontSize: 12,
                                color: '#666'
                            },
                            icon: 'roundRect'
                        }, {
                            name: '充值完成',
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
                        data: initSearchDate,
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
                            name: '充值请求',
                            type: 'line',
                            data: json.requestList
                        },
                        {
                            name: '充值完成',
                            type: 'line',
                            data: json.completeList
                        }
                    ]
                });
            }
        })
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