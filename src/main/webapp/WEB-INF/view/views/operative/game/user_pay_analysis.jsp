<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<%
	request.setAttribute("path", request.getContextPath());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台统计</title>
<link type="text/css" rel="stylesheet"
	href="${path}/css/admin-trend.css" />
<script src="${path}/js/laydate/laydate.js"></script>
<link href="${path}/js/myPagination/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${path}/js/myPagination/js/myPagination/page.css" />
<script src="${path}/js/myPagination/js/myPagination/jquery.myPagination6.0.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="${path}/css/jquery-ui.css" />
<script src="${path}/js/jquery-ui.js"></script>
<script src="${path}/js/datecontrol.js"></script>
</head>
<body>
	<div class="sdk-content">
		<ul class="breadcrumb">
			<li>您当前的位置：</li>
			<tags:breadcrumb />
			<li>统计图</li>
		</ul>
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingOne">
				<h4 class="panel-title" id="-collapsible-group-item-#1-">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne" aria-expanded="true"
						aria-controls="collapseOne" class="">统计图</a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in" aria-expanded="true">
					<div class="panel-body">
						<div class="admin-content">
								<div class="section-box">
									<div class="titleDiv">
											<input type="hidden" id="platformId" value="${platformId}">
										    <div class="select-fr" style="padding-right: 30px;">
												<span class="laydateBox"> 日期：<input value="选择日期"
													class="laydate-icon" id="dateStart"> 至 <input
													class="laydate-icon" id="dateEnd" value="选择日期">
												</span>
												<span id="type">
		                                            <select id="dateType" onchange="dateTypeChange('dateType','dateStart','dateEnd',search);" style="width:auto;" class="select">
		                                                 <option value="1">今日</option>
		                                                 <option value="2">昨日</option>
		                                                 <option value="3">近7日</option>
		                                                 <option value="4">近30日</option>
		                                                 <option value="5">近90日</option>
		                                                 <option value="0">自定义</option>
		                                            </select>
	                                            </span> 
										</div>
										<ul class="tabTit titslt">
											<li class="cur" style="width: 80px;">付费人数</li>
											<li style="width: 80px;">付费金额</li>
											<li style="width: 80px;">付费次数</li>
										</ul>
									</div>

									<div class="whiteDiv tab-content" style="width:100%;">
										<ul id="sortable" class="ui-widget-content sortable">
											<li class="ui-state-default">
	 											<div  class="ui-widget-content resize" style="height:300px;">
	 												<div class="sortHandle">趋势图</div>
													<div id="trendline" class="trendline"></div> 
												</div>
											</li>
											<li class="ui-state-default">
												<div class="ui-widget-content resize resizePanel">
													<div class="sortHandle">列表</div>
													<div class="tablePanel">
														<table class="tableList1">
															<colgroup>
																<col width="120" />
																<col width="90" />
																<col width="90" />
																<col width="90" />
																<col width="90" />
																<col width="90" />
															</colgroup>
															<thead>
																<tr>
																	<th>日期</th>
																	<th>新增玩家</th>
																	<th>活跃玩家</th>
																	<th>付费人数</th>
																	<th>付费金额（￥）</th>
																	<th>付费次数</th>
																</tr>
															</thead>
															<tbody id="data"">
															</tbody>
														</table>
													</div>
													<div id="page"></div>
												</div>
											</li>
										</ul>
								</div>
							</div>
					</div>
				</div>
		</div>

		<script src="${path}/js/echart/dist/echarts.js"></script>
		<script type="text/javascript">
			var dateChange = function(){
				$("#dateType").val(0);
				search();
			}
			var dateValue = setDateRangeConfig("dateStart","dateEnd",dateChange);
			var dateTypeChange = function(){
				setDateTypeChange("dateType","dateStart","dateEnd",dateValue.dateStart,dateValue.dateEnd,search);
			}
		
			var tabIndex=0;
			$(function(){
				var index=0;
				$(".tabTit li").click(function(){
					tabIndex=$(".tabTit li").index(this);
					$(this).addClass("cur").siblings().removeClass("cur");
					loadTrendLine(echartsCopy,tabIndex);
				});
				$( "#sortable" ).sortable({cursor: "move",handle: ".sortHandle"});
				$( ".resize" ).resizable({minHeight: 150,minWidth: 150});
				$("#trendline").resize(
					function(){
						if(undefined != trendline){
							trendline.resize();
						}
					}		
				);
				$(window).resize(
						function(){
							if(undefined != trendline){
								trendline.resize();
							}
					}
				);
			});
			
	   		var pageSize = 10;
			var addTbRow = function(data){
				if(null != data && undefined != data && "" != data){
					var ele="<tr><td class=\"date\">statDate</td><td>registerCount</td><td>activeCount</td><td>payCount</td><td >payAmount</td><td>payTimes</td></tr>";
					ele = ele.replace("statDate",data.statDate).replace("registerCount",data.registerCount).replace("activeCount",data.activeCount).replace("payCount", data.payCount).replace("payTimes",data.payTimes).replace("payAmount",data.payAmount);
					$("#data").append(ele);
				}
			}
			
			//显示统计列表
			var showData = function(pageNumber,pageSize){
				$("#data").empty();
				$.post("${path}/game/userpayals/list.do",{
					startDate:$("#dateStart").val(),
					endDate:$("#dateEnd").val(),
					platformId:$("#platformId").val(),
					pageNumber:pageNumber,
					pageSize:pageSize
				},function(data){
					var json = JSON.parse(data);
					if(null != json && undefined != json){
						$("#page").myPagination({
					          currPage: pageNumber,
					          pageCount: json.pages,
					          ajax:{on:false,  
				                    onClick:function(page){
				                    	showData(page,pageSize);
				                    }  
				        		}
					        });
						var infoData = json.list;
						if(null == infoData || undefined == infoData || 0 >= infoData.length){
							$("#data").append("<tr><td colspan=\"6\">没有数据</td></tr>");
						}else{
							for(var i=0;i < infoData.length;i++){
								var ele = {
										statDate:infoData[i].statDate,
										registerCount:infoData[i].registerCount,
										activeCount:infoData[i].activeCount,
										payCount:infoData[i].payCount,
										payTimes:infoData[i].payTimes,
										payAmount:infoData[i].payAmount
								}
								addTbRow(ele);
							}
						}
					}else{
						$("#data").append("<tr><td colspan=\"6\">没有数据</td></tr>");
					}
				});
			}

			//查询显示
			var search = function(){
				showData(1,pageSize);
				loadTrendLine(echartsCopy,tabIndex);
			}
			
			function getRegData(data,index){
				if(0 == index){//付费人数
					return data.regAndRechargeCountList
				}else if(1 == index){//付费金额
					return data.regAndRechargeAmountList
				}else{//付费次数
					return data.regAndRechargeTimesList
				}
			}
			
			function getActData(data,index){
				if(0 == index){//付费人数
					return data.actAndRechargeCountList
				}else if(1 == index){//付费金额
					return data.actAndRechargeAmountList
				}else{//付费次数
					return data.actAndRechargeTimesList
				}
			}
			
			function getYAxisLabelFormart(index){
				if(1 == index){//付费金额
					return "￥{value}";
				}else{//付费次数
					return "{value}";
				}
			}
			
			//加载付费人数折线图
			function loadTrendLine(echarts,index){
				var startDate =$("#dateStart").val();
				var endDate =$("#dateEnd").val();
				
				$.ajax({ 
					url: "${path}/game/userpayals/chart.do",
								data : {
									platformId:$("#platformId").val(),
									startDate:startDate,
									endDate:endDate
								},
								success : function(data) {
									var json = JSON.parse(data);
									showTrendLineData(echarts,json,index);
								}
							})
			}
			
			var trendline;
			function showTrendLineData(echarts,json,index){
				trendline = echarts.init(document
						.getElementById('trendline'));
				trendline.setOption({
					calculable :true,
					title : {
						x : '20',
						text : '',
						textStyle : {
							fontSize : '16',
							fontWeight : 'bold',
						}
					},
					tooltip : {
						trigger : 'axis'
					},
					color : [ '#2ec7c9', '#b6a2de', '#5ab1ef',
							'#ffb980', '#d87a80' ],
					legend : {
						show : true,
						orient : 'horizontal',
						data : [ {
							name : '新增玩家',
							textStyle : {
								fontSize : 12,
								color : '#666'
							},
							icon : 'roundRect'
						}, {
							name : '活跃玩家',
							textStyle : {
								fontSize : 12,
								color : '#666'
							},
							icon : 'roundRect'
						}]
					},
					grid : {
						borderWidth : 0,
						y : 50,
						y2 : 30,
						x : 50,
						x2 : 50
					},
					xAxis : [ {
						type : 'category',
						boundaryGap : false,
						data : json.scales,
						splitLine : {
							show : true,
							lineStyle : {
								color : '#f5f5f5',
								width : 1,
								type : 'solid'
							}
						},
						axisTick : {
							show : true,
							lineStyle : {
								color : '#ccc',
								width : 1,
								type : 'solid'
							}
						},
						axisLine : {
							lineStyle : {
								color : '#ccc',
								width : 1,
								type : 'solid'
							}
						},
						axisLabel : {
							textStyle : {
								color : "#999"
							}
						}
					} ],
					yAxis : [ {
						type : 'value',
						axisLabel : {
				                formatter: getYAxisLabelFormart(index),
				                margin:5
				        },
						axisLine : {
							show : true
						},
						axisTick : {
							show : true
						},
						splitArea : {
							show : false
						},
						splitLine : {
							show : true,
							lineStyle : {
								color : '#f5f5f5',
								width : 1,
								type : 'solid'
							}
						}
					} ],
		
					series : [
							{
								name : '新增玩家',
								type : 'line',
								data : getRegData(json,index)
							},
							{
								name : '活跃玩家',
								type : 'line',
								data : getActData(json,index)
							}]
				});
			}
	
	var echartsCopy;
    // 路径配置
    require.config({
       paths: {
       	 echarts: '${path}/js/echart/dist'
			}
	});

	// 使用
	require([ 'echarts','echarts/chart/line' ],
		function(ec) {
			echartsCopy = ec;
			search();
	});
	</script>
</body>
</html>
