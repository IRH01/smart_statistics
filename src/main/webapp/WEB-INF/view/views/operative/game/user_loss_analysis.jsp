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
<script src="${path}/js/datecontrol.js"></script>
<link type="text/css" rel="stylesheet" href="${path}/css/jquery-ui.css" />
<script src="${path}/js/jquery-ui.js"></script>

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
			<div id="collapseOne" class="panel-collapse collapse in"
				role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
					<div class="panel-body">
						<div class="admin-content">
								<div class="section-box">
									<div class="titleDiv">
											 <input type="hidden" id="platformId" value="${platformId}">
										    <div class="select-fr" style="padding-right: 30px;">
												<span class="laydateBox"> 日期：<input value="选择日期"
													class="laydate-icon" id="dateStart" readonly> 至 <input
													class="laydate-icon" id="dateEnd" value="选择日期" readonly>
												</span>
											    <!--选择类别-->
												<span id="type">
												&nbsp;&nbsp;
		                                            <select id="dateType" onchange="dateTypeChange();" style="width:auto;" class="select">
		                                                 <option value="1">近7日</option>
		                                                 <option value="2">近14日</option>
		                                                 <option value="3">近30日</option>
		                                                 <option value="4">近90日</option>
		                                                 <option value="0">自定义</option>
		                                            </select>
	                                            </span> 
										</div>
										<ul class="tabTit titslt">
											<li class="cur" style="width: 80px;">活跃玩家</li>
											<li style="width: 80px;">付费玩家</li>
										</ul>
									</div>

									<div class="whiteDiv tab-content">
										<ul id="sortable" class="ui-widget-content sortable">
											<li class="ui-state-default">
	 											<div  class="ui-widget-content resize" style="height:300px;">
	 												<div class="sortHandle">趋势图</div>
													<div id="trendline" class="trendline"></div>
												</div>
											</li>
											<li class="ui-state-default">
												<div style="padding-bottom:60px;" class="ui-widget-content resize resizePanel">
													<div class="sortHandle">列表</div>
													<div class="tablePanel">
														<table class="tableList1" >
															<colgroup>
																<col width="25%" />
																<col width="25%" />
																<col width="25%" />
																<col width="25%" />
															</colgroup>
															<thead>
																<tr>
																	<th>日期</th>
																	<th>7日流失数（率）</th>
																	<th>14日流失数（率）</th>
																	<th>30日流失数（率）</th>
																</tr>
															</thead>
															<tbody id="data"></tbody>
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
				setDateType2Change("dateType","dateStart","dateEnd",dateValue.dateStart,dateValue.dateEnd,search);
			}
			
			var tabIndex=0;
			$(function(){
				var index=0;
				$(".tabTit li").click(function(){
					tabIndex=$(".tabTit li").index(this);
					$(this).addClass("cur").siblings().removeClass("cur");
					search();
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
	    	
	    	//获取当前月天数
	    	var getCurMonthDayCnt = function(){
	    		var now = new Date();
	    		var date = new Date(now.getYear(),now.getMonth() + 1,0);
	    		return date.getDate();
	    	}
	    	
	    	//保留两位小数
			var getTwoFixedDouble = function(value){
				return (value*100).toFixed(2);
			}
	    	
			var addTbRow = function(data){
				if(null != data && undefined != data && "" != data){
					var ele="<tr><td class=\"date\">statDate</td><td>seven（sePercent）</td><td>fourteen（foteenPercent）</td><td>thirty（thPercent）</td></tr>";
					ele = ele.replace("statDate",data.statDate).replace("sePercent",data.sevenPercent).replace("seven",data.seven).replace("foteenPercent",data.fourteenPercent).replace("fourteen",data.fourteen).replace("thPercent", data.thirtyPercent).replace("thirty",data.thirty);
					$("#data").append(ele);
				}
			}
			
			//显示统计列表
			var showData = function(pageNumber,pageSize,index){
				$("#data").empty();
				$.post("${path}/game/userlossals/list.do",{
					type:index,
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
				                    	showData(page,pageSize,index);
				                    }  
				        		}
					        });
						var infoData = json.list;
						if(null == infoData || undefined == infoData || 0 >= infoData.length){
							$("#data").append("<tr><td colspan=\"4\">没有数据</td></tr>");
						}else{
							for(var i=0;i < infoData.length;i++){
								var ele = {
										statDate:infoData[i].statDate,
										seven:infoData[i].seven,
										sevenPercent:getTwoFixedDouble(infoData[i].sevenPercent) + "%",
										fourteen:infoData[i].fourteen,
										fourteenPercent:getTwoFixedDouble(infoData[i].fourteenPercent) + "%",
										thirty:infoData[i].thirty,
										thirtyPercent:getTwoFixedDouble(infoData[i].thirtyPercent) + "%"
								}
								addTbRow(ele);
							}
						}
					}else{
						$("#data").append("<tr><td colspan=\"4\">没有数据</td></tr>");
					}
				});
			}

			//查询显示
			var search = function(){
				showData(1,pageSize,tabIndex);
				loadTrendLine(echartsCopy,tabIndex);
			}
			
			//加载付费人数折线图
			function  loadTrendLine(echarts,index){
				var startDate =$("#dateStart").val();
				var endDate =$("#dateEnd").val();
				
				$.ajax({ 
					url: "${path}/game/userlossals/chart.do",
								data : {
									platformId:$("#platformId").val(),
									startDate:startDate,
									endDate:endDate,
									type:index
								},
								success : function(data) {
									var json = JSON.parse(data);
									showTrendLineData(echarts,json,index);
								}
							})
			}
			
			function formatDoubleData(data){
				if(undefined != data && undefined != data.length){
					for(var i = 0;i < data.length;i++){
						data[i] = getTwoFixedDouble(data[i]);
					}
				}
				return data;
			}
			var trendline;
			function showTrendLineData(echarts,json,index){
				trendline = echarts.init(document
						.getElementById('trendline'));
				trendline.setOption({
					title : {
						x : '20',
						text : '',
						textStyle : {
							fontSize : '16',
							fontWeight : 'bold',
						}
					},
					tooltip : {
						trigger : 'axis',
						formatter : '{b}<br/>{a0}:{c0}%<br/>{a1}:{c1}%<br/>{a2}:{c2}%'
					},
					color : [ '#2ec7c9', '#b6a2de', '#5ab1ef',
							'#ffb980', '#d87a80' ],
					legend : {
						show : true,
						orient : 'horizontal',
						data : [ {
							name : '7日流失率',
							textStyle : {
								fontSize : 12,
								color : '#666'
							},
							icon : 'roundRect'
						}, {
							name : '14日流失率',
							textStyle : {
								fontSize : 12,
								color : '#666'
							},
							icon : 'roundRect'
						}, {
							name : '30日流失率',
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
							show : true,
							formatter : '{value}%'
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
								name : '7日流失率',
								type : 'line',
								data : formatDoubleData(json.sevenLossPercentList)
							},
							{
								name : '14日流失率',
								type : 'line',
								data : formatDoubleData(json.fourteenLossPercentList)
							},
							{
								name : '30日流失率',
								type : 'line',
								data : formatDoubleData(json.thirtyLossPercentList)
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
			setDateType2Change("dateType","dateStart","dateEnd",dateValue.dateStart,dateValue.dateEnd,search);
	});
	</script>
</body>
</html>
