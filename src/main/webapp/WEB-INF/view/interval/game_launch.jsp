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
                        <li>游戏启动统计列表</li>
                    </ul>
                </div>
                <!--body start-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title" id="-collapsible-group-item-#1-">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne" aria-expanded="true"
                               aria-controls="collapseOne" class="" >游戏启动统计列表</a>
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
                                                    <div class="sortHandle">分时段列表</div>
                                                    <div class="tablePanel">
                                                        <table class="tableList1">
                                                            <colgroup>
                                                                <col width="120"/>
                                                                <col width="90"/>
                                                                <col width="100"/>
                                                                <col width="100"/>
                                                            </colgroup>
                                                            <thead>
                                                            <tr>
                                                                <th>日期</th>
                                                                <th>撩妹德州</th>
                                                                <th>乐盈电竞</th>
                                                                <th>一比分</th>
                                                            </tr>
                                                            </thead>
                                                            <tbody id="terminalsIntervalData"></tbody>
                                                        </table>
                                                    </div>
                                                    <%--<div id="page"></div>--%>
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
                                            <li class="ui-state-default">
                                                <div class="ui-widget-content resize" style="height:300px;">
                                                    <div class="sortHandle">分时段曲线图</div>
                                                    <div style="width:100%;height:95%;">
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
    function tijiao(url) {
        document.form.action = url;
        document.form.submit();
    }

    function initPwd(id) {
        layer.confirm("确认要将密码重置为密码“123456”吗？", {
            btn: ["确认", "取消"] //可以无限个按钮
        }, function (index, layero) {
            $.post("<c:url value="/admin/admin/initPwd.do"/>", {userId: id}, function (result) {
                if (result.status == 1200) {
                    layer.alert("设置成功", {icon: 6});
                }
            });
        });
    }

    function del(id) {
        layer.confirm("是否要刪除该用户？", {
            btn: ["确认", "取消"] //可以无限个按钮
        }, function (index, layero) {
            $.post("<c:url value="/admin/admin/del.do"/>", {
                userId: id
            }, function (result) {
                if (result.status == 1200) {
                    layer.alert("删除成功", {
                        icon: 6
                    });
                    tijiao("<%=request.getContextPath()%>/admin/admin/list.do");
                } else {
                    layer.alert("删除失败", {
                        icon: 5
                    });
                }
            });
        });
    }

    function disable(url) {
        layer.confirm("是否要禁用该用户？禁用后，该用户将无法登陆", {
            btn: ["确认", "取消"]
        }, function (index, layero) {
            document.form.action = url;
            document.form.submit();
        });
    }

    function clearSearch() {
        jQuery("#username").attr("value", "");
        jQuery("#userStatus").attr("value", "");
        jQuery("#type").attr("value", "");
        document.form.action = 'list.do';
        document.form.submit();
    }
    var initSearchDate = [ "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30","07:00", "07:30",
        "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30","12:00", "12:30","13:00", "13:30", "14:00", "14:30", "15:00", "15:30",
        "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30","24:00" ];

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

    var dateChange1 = function () {
        $("#dateType").val(0);
        showNewUserData(1, pageSize);
    }
    //    var dateValue = setDateRangeConfig("dateStart", "dateEnd", dateChange);
    //    var dateTypeChange = function () {
    //        setDateTypeChange("dateType", "dateStart", "dateEnd", dateValue.dateStart, dateValue.dateEnd, search);
    //    }

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

    // 各端实时数据列表展示
    var addTbRow1 = function (data) {
        if (null != data && undefined != data && "" != data) {
            var ele = "<tr><td class=\"date\">statisticsTime</td><td>ybflaunchCount</td><td>lmdzlaunchCount</td><td>lydjlaunchCount</td></tr>";
            ele = ele.replace("statisticsTime", data.statisticsTime).replace("ybflaunchCount", data.ybflaunchCount).replace("lmdzlaunchCount", data.lmdzlaunchCount).replace("lydjlaunchCount", data.lydjlaunchCount);
            $("#terminalsIntervalData").append(ele);
        }
    }

    var showTerminalsIntervalData = function (pageNumber, pageSize) {
        for(var i=0;i<initSearchDate.length;i++){
            $("#dateStarts").append("<option value='"+currentdate+initSearchDate[i]+"'>"+initSearchDate[i]+"</option>");
            $("#dateEnds").append("<option value='"+currentdate+initSearchDate[i]+"'>"+initSearchDate[i]+"</option>");
        }
        $("#terminalsIntervalData").empty();
        $.post("/interval/IntervalGameLaunch/list.do", {
            startDate: $('#dateStarts').val(),
            endDate: $('#dateEnds').val(),
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
                            showTerminalsIntervalData(page, pageSize);
                        }
                    }
                });
                var infoData = json.list;
                if (null == infoData || undefined == infoData || 0 >= infoData.length) {
                    $("#totalCount").html(0);
                    $("#totalPage").html(0);
                    $("#terminalsIntervalData").append("<tr><td colspan=\"10\">没有数据</td></tr>");
                } else {
                    var sumlydj=0;
                    var sumybf=0;
                    var sumlmdz=0;
                    for (var i = 0; i < infoData.length; i++) {
                            sumlmdz = accAdd(sumlmdz,infoData[i].lmdzlaunchCount);
                            sumybf = accAdd(sumybf,infoData[i].ybflaunchCount);
                            sumlydj = accAdd(sumlydj,infoData[i].lydjlaunchCount);

                        var ele = {
                            statisticsTime: infoData[i].statisticsTime,
                            ybflaunchCount: infoData[i].ybflaunchCount,
                            lmdzlaunchCount: infoData[i].lmdzlaunchCount,
                            lydjlaunchCount: infoData[i].lydjlaunchCount
                        }
                        addTbRow1(ele);
                    }

                    var ele1 = {
                        statisticsTime:"总计",
                        ybflaunchCount:sumybf,
                        lmdzlaunchCount:sumlmdz,
                        lydjlaunchCount:sumlydj
                    }
                    addTbRow1(ele1);
                }
            } else {
                $("#totalCount").html(0);
                $("#totalPage").html(0);
                $("#terminalsIntervalData").append("<tr><td colspan=\"10\">没有数据</td></tr>");
            }
        });
    };




    //查询显示
    var search = function () {
        pageSize = $("#pageSize").val();
        loadNewUserDataTrendLine(echartsCopy);
        showTerminalsIntervalData(1, pageSize);
    }

    var trendline;

    function loadNewUserDataTrendLine(echarts) {
        var startDate = $("#dateStarts").val();
        var endDate = $("#dateEnds").val();

        $.ajax({
            url: "/interval/IntervalGameLaunch/chart.do",
            data: {
                startDate: startDate,
                endDate: endDate,
                deviceTypes: getDeviceType()
            },
            success: function (data) {

                var json = JSON.parse(data);
                trendline = echarts.init(document
                    .getElementById('trendline'));
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
                    color: ['#2ec7c9', '#b6a2de', '#5ab1ef',
                        '#ffb980', '#d87a80'],
                    legend: {
                        show: true,
                        orient: 'horizontal',
                        data: [{
                            name: '撩妹德州',
                            textStyle: {
                                fontSize: 12,
                                color: '#666'
                            },
                            icon: 'roundRect'
                        }, {
                            name: '乐盈电竞',
                            textStyle: {
                                fontSize: 12,
                                color: '#666'
                            },
                            icon: 'roundRect'
                        }, {
                            name: '一比分',
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
                            name: '撩妹德州',
                            type: 'line',
                            data: json.lmdzList
                        },
                        {
                            name: '乐盈电竞',
                            type: 'line',
                            data: json.yydjList
                        },
                        {
                            name: '一比分',
                            type: 'line',
                            data: json.ybfList
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