<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<%request.setAttribute("path", request.getContextPath());%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台统计</title>

<script src="${path}/js/datecontrol.js"></script>
<script src="${path}/js/laydate/laydate.js"></script>
<link type="text/css" rel="stylesheet" href="${path}/css/jquery-ui.css" />
<script src="${path}/js/datecontrol.js"></script>
<link type="text/css" rel="stylesheet" href="${path}/css/jquery-ui.css" />
<script src="${path}/js/jquery-ui.js"></script>
<link type="text/css" rel="stylesheet" href="${path}/css/admin-trend.css" />
<script src="${path}/js/tool.js"></script>
</head>
<body style="height:100%;">
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
						aria-controls="collapseOne" class=""> 统计图 </a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
				<div class="domainDiv">
					<label class="control-label" for="" style="font-size: 16px;float:left;line-height:2;">域名：</label>
					<select id="domain" class="form-control input-sm" onchange="searchByDomain();"
						style="display: initial; width: inherit;float:left;">
						<c:forEach items="${domains}" var="item">
							<option value="${item.tblId}">${item.positionName}</option>
						</c:forEach>
					</select>
					<button type="button" id="search" class="btn btn-primary btn-sm"
						onclick="searchByDomain();">
						<i class="icon-search icon-white"></i>&nbsp;查&nbsp;&nbsp;询&nbsp;
					</button>
				</div>
				<div class="panel-body">
					<div class="admin-content" style="overflow-x: auto;">
							<!--今日总览-->
							<div class="section-box">
								<div class="titleDiv">
									<div class="date-pos">
										<button type="button" id="exportToExcel" class="btn btn-primary btn-sm"
											onclick="exportToExcel();">
											<i class="icon-search icon-white"></i>导出为Excel
										</button>
										<span class="laydateBox"> 日期：<input value="选择日期"
											class="laydate-icon" id="overViewStart" readonly> 至 <input
											class="laydate-icon" id="overViewEnd" value="选择日期" readonly>
										</span>
									</div>
									<h2>总览</h2>
								</div>
								<div class="whiteDiv">
									<ul class="ui-widget-content sortable">
										<li class="ui-state-default">
 											<div  class="ui-widget-content resize" style="height:300px;">
 												<div class="sortHandle">日更新资讯数量</div>
 												<div style="height:90%;width:100%;">
 													<div class="barPanel">
														<div id="dailyInfoCountBar" class="barDiv"></div>
 													</div>
 													<table class="piePanel">
														<tr>
															<td style="width:50%;height:100%;">
																<div class="legendPanel">
																		<ul class="barColor" id="dailyInfoCountLegend"></ul>
																</div>
															</td >
															<td style="width:50%;height:100%;">
																<div id="dailyInfoCountPie" class="pieDiv"></div>
															</td>
														</tr>
													</table>	
 												</div>
											</div>
										</li>
										<li class="ui-state-default">
 											<div  class="ui-widget-content resize" style="height:310px;">
 												<div class="sortHandle">点击量</div>
 												<div style="height:90%;width:100%;">
 													<div class="barPanel">
														<div id="clickCountBar" class="barDiv"></div>
 													</div>
 													<table class="piePanel">
														<tr>
															<td style="width:50%;height:100%;">
																	<div class="legendPanel" id="legendPanel">
																		<ul class="barColor" id="clickCountLegend"></ul>
																	</div>
															</td >
															<td style="width:50%;height:100%;">
																<div id="clickCountPie" class="pieDiv"></div>
															</td>
														</tr>
													</table>	
 												</div>
											</div>
										</li>
										<li class="ui-state-default">
 											<div  class="ui-widget-content resize" style="height:310px;">
 												<div class="sortHandle">IP数量</div>
 												<div style="height:90%;width:100%;">
 													<div class="barPanel">
														<div id="ipCountBar" class="barDiv"></div>
 													</div>
 													<table class="piePanel">
														<tr>
															<td style="width:50%;height:100%;">
																<div class="legendPanel">
																	<ul class="barColor" id="ipCountLegend"></ul>
																</div>
															</td >
															<td style="width:50%;height:100%;">
																<div id="ipCountPie" class="pieDiv"></div>
															</td>
														</tr>
													</table>	
 												</div>
											</div>
										</li>
										<li class="ui-state-default">
 											<div  class="ui-widget-content resize" style="height:300px;">
 												<div class="sortHandle">停留时长</div>
 												<div style="height:90%;width:100%;">
 													<div class="barPanel">
														<div id="stayTimeBar" class="barDiv"></div>
 													</div>
 													<table class="piePanel">
														<tr>
															<td style="width:50%;height:100%;">
																<div class="legendPanel">
																	<ul class="barColor" id="stayTimeLegend"></ul>
																</div>
															</td >
															<td style="width:50%;height:100%;">
																<div id="stayTimePie" class="pieDiv"></div>
															</td>
														</tr>
													</table>	
 												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
							<!--今日总览end-->
							<!--历史对比-->
							<div class="section-box">
								<div class="titleDiv">
									<div class="date-pos">
									    <button type="button" id="exportToExcel" class="btn btn-primary btn-sm"
											onclick="exportToExcel2();">
											<i class="icon-search icon-white"></i>导出为Excel
										</button>
										<span class="laydateBox"> 日期：<input value="选择日期"
											class="laydate-icon" id="start2"> 至 <input
											class="laydate-icon" id="end2" value="选择日期">
										</span>
									</div>
									<h2>历史对比</h2>
								</div>

								<div class="whiteDiv">
									<ul class="ui-widget-content sortable">
										<li class="ui-state-default">
 											<div  class="ui-widget-content resize" style="height:300px;">
 												<div class="sortHandle">趋势图</div>
												<div id="historyTrendLine" class="trendline"></div> 
											</div>
										</li>
									</ul>
								</div>
							</div>
							<!--历史对比end-->
					</div>
				</div>
			</div>
		</div>


		<script src="${path}/js/echart/dist/echarts.js"></script>
		<script type="text/javascript">
		function resize(containId,chartEle){
			$("#" + containId).resize(
					function(){
						chartResize(chartEle);
					}		
			);
		}
		var historyDateValue;
		$(function(){
			setDateRangeConfig("overViewStart","overViewEnd",searchByDomain,null,true);
			historyDateValue = setDateRangeConfig("start2","end2",searchHistory,true,true);
			$( ".sortable" ).sortable({cursor: "move",handle: ".sortHandle"});
			$( ".resize" ).resizable({minHeight: 300,minWidth: 1150});
			$(window).resize(
					function(){
						chartResize(historyTrendLine);
						chartResize(dailyInfoCountBar);
						chartResize(dailyInfoCountPie);
						chartResize(clickCountBar);
						chartResize(clickCountPie);
						chartResize(ipCountBar);
						chartResize(ipCountPie);
						chartResize(stayTimeBar);
						chartResize(stayTimePie);
				}
			);
		});
		//日更新资讯图开始
		var colorList = [
                         '#ff525e','#4bb1fc','#adce1b','#960081','#ffdb03',
                         '#5867c3','#028f1c','#d30075','#00c5dc','#ffaa00',
                         '#9f4402','#086cbb','#7b68ee','#00fa9a','#ffd700',
                         '#6b8e23','#ff00ff','#3cb371','#b8860b','#30e0e0'
                       ];
		
		var showLegend = function(data,colorList,eleId){
			$("#"+eleId).empty();
			for(var i=0;i<data.length;i++){
				var item = "<li><i class=\"colorBar1\" style=background-color:"+colorList[i%colorList.length]+"></i><span>"+data[i]+"</span></li>";
				$("#"+eleId).append(item);
			}
		}
		
		//显示资讯数量图例
		var showInfoLegend = function(data,colorList){
			showLegend(data,colorList,"dailyInfoCountLegend");
		}
		var dailyInfoCountBar;
		var dailyInfoCountPie;
		//显示资讯数量柱状图
		var showInfoBarChart = function(data,echarts,colorList){
			var legendData = new Array();
			var xData = new Array();
			var yData = new Array();
			var pieData = new Array();
			for(var i=0;i<data.length;i++){
				if(undefined != data[i].infoTypeName && data[i].infoTypeName.length > 4){
					//只显示4个字长度，其它的用..分隔
					legendData[i] = data[i].infoTypeName.substring(0,4) + ".. " + (data[i].percent*100).toFixed(2) +"%";
				}else{
					legendData[i] = data[i].infoTypeName + (data[i].percent*100).toFixed(2) +"%";
				}
				
				
				xData[i] = data[i].infoTypeName;
				yData[i] = data[i].count;
				var ele = {
						value:data[i].count,
						name:data[i].infoTypeName
				}
				pieData[i]=ele;
			}
			showInfoLegend(legendData,colorList);
			
			//日更新资讯数量柱状图开始
			dailyInfoCountBar = echarts.init(document.getElementById('dailyInfoCountBar'));
			dailyInfoCountBar.setOption({
			    title: {
			        x: '20',
			        text: '日更新资讯数量',
			        textStyle : {
					    fontSize : '14',
					  	fontWeight : 'bold',
				  	}
			    },
			    toolbox: {
        	        show : false,
        	        feature : {
        	            mark : {show: false},
        	            dataView : {show: false, readOnly: false},
        	            magicType : {
        	                show: false, 
        	                type: ['pie', 'funnel'],
        	                option: {
        	                    funnel: {
        	                        x: '25%',
        	                        width: '50%',
        	                        funnelAlign: 'center',
        	                        max: 1548
        	                    }
        	                }
        	            },
        	            restore : {show: false},
        	            saveAsImage : {show: true}
        	        }
        	    },
			    calculable: false,
			    grid: {borderWidth:0,y:30,y2: 30, x:20,x2:20},
			    xAxis: [{
			            type: 'category',
			            data : xData,
			            splitLine: { show:false},
			           	axisTick: {show:false},
			           	axisLine: {	lineStyle: {color: '#ccc',width: 1,type: 'solid'}},
						axisLabel: {textStyle:{color:"#999"}}  
					}],
			    yAxis: [
			        {
			            type: 'value',
			            show: false
			        }
			    ],
			    series: [{
			            name: '日更新资讯数量',
			            type: 'bar',
			            barWidth : 24,
			            itemStyle: {
			                normal: {
			                    color: function(params) {
			                        return colorList[params.dataIndex%colorList.length]
			                    },
			                    label: {
			                        show: true,
			                        position: 'top',
			                        formatter: '{c}',
			                        textStyle:{
								 		color:"#999"
								 	}
			                    }
			                }
			            },
			            data:yData,
			        }],
			});
			//日更新资讯数量柱状图结束
				
			//日更新资讯数量饼图开始
			dailyInfoCountPie = echarts.init(document.getElementById('dailyInfoCountPie'));
				dailyInfoCountPie.setOption({
					tooltip: {
						trigger: 'item',
						formatter: "更新{c}次 <br/>占{d}%"
					},
					color:colorList,
					series : [
						{
							name:'',
							type:'pie',
							radius : ['65%', '90%'],
							itemStyle : {
								 normal : {
									  label : {
										  show : false
									  },
									  labelLine : {
										  show : false
									  }
								  },
								  emphasis : {
									  label : {
										  show : true,
										  position : 'center',
										  textStyle : {
											  fontSize : '14',
											  fontWeight : 'bold',
											  color:"#333"
										  }
									  }
								  }
							},
							data:pieData
						}
					]
				});
				//日更新资讯数量饼图结束
			}
			var getDailyInfoCount = function(startDate,endDate,domainId,echarts,colorList){
				$.post("${path}/overviewchart/dailyInfoCount.do",
						{
							startDate:startDate,
							endDate:endDate,
							domainId:domainId
						},
						function(data){
							showInfoBarChart(data.data,echarts,colorList);
					});
			}
			
			//查询显示日更新资讯图
			var showInfoChart = function(echarts){
				var startDate = $("#overViewStart").val();
				var endDate = $("#overViewEnd").val();
				var domainId = $("#domain").val();
				getDailyInfoCount(startDate,endDate,domainId,echarts,colorList);
			}
			//日更新资讯图开始结束
			
			
			var getShowEle = function(value,borderColor){
				var ele = {
				        value : value,
				        //自定义标签样式，仅对该数据项有效
				        label: {},
	        			//自定义特殊 itemStyle，仅对该数据项有效
	       				itemStyle:{
	       					normal:{
	       					 	barBorderColor: borderColor,
	       						label:{show: true,position:'top'}
	       					}}
				     };
				return ele;
			}
			var getShowAvgData = function(data,colorList){
				avgData = new Array();
				for(var i=0;i<data.length;i++){
					avgData[i]=getShowEle(data[i],colorList[i]);
				}
				return avgData;
			}
			
			//资讯行为图开始
			var showInfoActChart = function(data,echarts,colorList,chartDiscrip,exportToExcel,exportCondition){
				var legendData = new Array();
				var xData = new Array();
				var yTotalData = new Array();
				var yAvgData = new Array();
				var pieData = new Array();
				for(var i=0;i<data.length;i++){
					if(undefined!=data[i].infoTypeName&&data[i].infoTypeName.length>4){
						//只显示四位数，其他用“..”省略
						legendData[i] = data[i].infoTypeName.substring(0,4) + ".. " + (data[i].percent*100).toFixed(2) +"%";
					}else{
						legendData[i] = data[i].infoTypeName + " " + (data[i].percent*100).toFixed(2) +"%";
					}
					
					
					xData[i] = data[i].infoTypeName;
					yTotalData[i] = data[i].count;
					yAvgData[i] = data[i].avgCount;
					var ele = {
							value:data[i].count,
							name:data[i].infoTypeName
					}
					pieData[i]=ele;
				}
				
				//显示图例
				showLegend(legendData,colorList,chartDiscrip.legendDivId);
				
				//柱形图
				var infoActBar = echarts.init(document.getElementById(chartDiscrip.barChartDivId));
				infoActBar.setOption({
				title : {
				        x: '20',
				        text: chartDiscrip.barChartName,
				        textStyle : {
						    fontSize : '14',
						  	fontWeight : 'bold'
					  	}
				    },
				legend: {
					orient : 'horizontal',
		    	    x : 'right',
				    data:[
				          {
  							name : chartDiscrip.barS1Name,
  							textStyle : {color: 'black'},
  							icon :"image://${path}/img/avg.png"
						  },{
	  							name : chartDiscrip.barS2Name,
	  							icon :"image://${path}/img/total.png"
							 }]
				},
			    toolbox: {
        	        show : false,
        	        feature : {
        	            mark : {show: false},
        	            dataView : {show: false, readOnly: false},
        	            magicType : {
        	                show: false, 
        	                type: ['pie', 'funnel'],
        	                option: {
        	                    funnel: {
        	                        x: '25%',
        	                        width: '50%',
        	                        funnelAlign: 'center',
        	                        max: 1548
        	                    }
        	                }
        	            },
        	            restore : {show: false},
        	            saveAsImage : {show: true},
        	            myTool : {
        	                show : false,
        	                title : '导出为excel',
        	                icon : 'image:/img/excel.png',
        	                onclick : function (){
        	                	exportToExcel(exportCondition.startDate,exportCondition.endDate,exportCondition.domainId);
        	                }
        	            }
        	        }
        	    },
				tooltip : {
			        trigger: 'axis',
			        axisPointer: {
			        	type: 'bar',
			        },
			    },
			    calculable : true,
			    grid: { borderWidth:0,y:45,y2:45, x:20,x2:20},
			    xAxis : [
			        {
			        	
			        	type: 'category',
			            data : xData,
			            splitLine: { show:false},
			           	axisTick: {show:false},
			           	axisLine: {	lineStyle: {color: '#ccc',width: 1,type: 'solid'}},
						axisLabel: {textStyle:{color:"#999"}}  
			           
			        },
			        {
			            type : 'category',
			            data : xData,
			            axisLine: {show:false},
			            axisTick: {show:false},
			            axisLabel: {show:false},
			            splitArea: {show:false},
			            splitLine: {show:false}
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value',
			            axisLabel:{formatter:'{value} ms'},
			            show:false
			        }
			    ],
			    series: [
			     {
			            name:chartDiscrip.barS1Name,
			            type:'bar',
			            barWidth : 20,
			            itemStyle: {
			            	normal:{
			            		color: '#fff',
			                    barBorderColor: 'transparent',
			                    barBorderWidth:2,
					            label:{
					            	show:true,
					            	textStyle:{color:"#333"}
					            }
				            }
			        	},
			             data:getShowAvgData(yAvgData,colorList)//平均
			        },
			    	{
				        name: chartDiscrip.barS2Name,
				        type: 'bar',
				        barWidth : 24,
				        xAxisIndex:1,
				        itemStyle: {
				            normal: {
				                color: function(params) {
				                    return colorList[params.dataIndex]
				                },
				                label: {
				                    show: true,
				                    position: 'top',
				                    formatter: '{c}',
				                    textStyle:{
								 		color:"#999"
								 	}
				                }
				            }
				        },
				        data:yTotalData//总
				        
				    }],
				});
				//柱形图结束
				
				
				//饼图
				var infoActPie = echarts.init(document.getElementById(chartDiscrip.pieChartDivId));
				infoActPie.setOption({
					tooltip: {
						trigger: 'item',
						formatter: chartDiscrip.pieTipForamt
					},
					color:colorList,
					series : [
						{
							name:'',
							type:'pie',
							radius : ['65%', '90%'],
							itemStyle : {
								 normal : {
									  label : {
										  show : false
									  },
									  labelLine : {
										  show : false
									  }
								  },
								  emphasis : {
									  label : {
										  show : true,
										  position : 'center',
										  textStyle : {
											  fontSize : '14',
											  fontWeight : 'bold',
											  color:"#333"
										  }
									  }
								  }
							},
							data:pieData
						}
					]
				});
				//饼图结束
				var chartEle = {bar:infoActBar,pie:infoActPie};
				return chartEle;
			}
			
			var clickCountBar;
			var clickCountPie;
			//从服务器获取点击量数据
			var getClickCount = function(startDate,endDate,domainId,echarts,colorList){
				$.post("${path}/overviewchart/clickCount.do",
						{
							startDate:startDate,
							endDate:endDate,
							domainId:domainId
						},
						function(data){
							var chartDiscrip = {
									barChartDivId:"clickCountBar",
									barChartName:"点击量",
									barS1Name:"平均点击量",
									barS2Name:"总点击量",
									pieChartDivId:"clickCountPie",
									pieChartName:"点击",
									pieTipForamt:"点击{c}次 <br/>占{d}%",
									legendDivId:"clickCountLegend"
									
							}
							var exportCondition ={
									startDate:startDate,
									endDate:endDate,
									domainId:domainId
							}
							var chartInfo = showInfoActChart(data.data,echarts,colorList,chartDiscrip,exportClickCount,exportCondition);
							clickCountBar = chartInfo.bar;
							clickCountPie = chartInfo.pie;
					});
			}
			var exportClickCount = function(startDate,endDate,domainId){
				window.location = "${path}/overviewchart/exportClickCount.do?startDate="+startDate+"&endDate="+endDate+"&domainId="+domainId;
			}
			
			var ipCountBar;
			var ipCountPie;
			//从服务器获取IP数量数据
			var getIPCount = function(startDate,endDate,domainId,echarts,colorList){
				$.post("${path}/overviewchart/ipCount.do",
						{
							startDate:startDate,
							endDate:endDate,
							domainId:domainId
						},
						function(data){
							var chartDiscrip = {
									barChartDivId:"ipCountBar",
									barChartName:"IP数量",
									barS1Name:"平均IP数量",
									barS2Name:"总IP数量",
									pieChartDivId:"ipCountPie",
									pieChartName:"点击",
									pieTipForamt:"IP数{c}个<br/>占{d}%",
									legendDivId:"ipCountLegend"
									
							}
							var exportCondition ={
									startDate:startDate,
									endDate:endDate,
									domainId:domainId
							}
							var chartInfo = showInfoActChart(data.data,echarts,colorList,chartDiscrip,exportIPCount,exportCondition);
							ipCountBar = chartInfo.bar;
							ipCountPie = chartInfo.pie;
					});
			}
			var exportIPCount = function(startDate,endDate,domainId){
				window.location = "${path}/overviewchart/exportIPCount.do?startDate="+startDate+"&endDate="+endDate+"&domainId="+domainId;
			}
			
			var stayTimeBar;
			var stayTimePie;
			//从服务器获取停留时间数据
			var getStayTime = function(startDate,endDate,domainId,echarts,colorList){
				$.post("${path}/overviewchart/stayTime.do",
						{
							startDate:startDate,
							endDate:endDate,
							domainId:domainId
						},
						function(data){
							var chartDiscrip = {
									barChartDivId:"stayTimeBar",
									barChartName:"停留时长",
									barS1Name:"平均停留时长（秒）",
									barS2Name:"总停留时长（秒）",
									pieChartDivId:"stayTimePie",
									pieChartName:"停留时长",
									pieTipForamt:"停留时长{c}秒 <br/>占{d}%",
									legendDivId:"stayTimeLegend"
									
							}
							var exportCondition ={
									startDate:startDate,
									endDate:endDate,
									domainId:domainId
							}
							var chartInfo = showInfoActChart(data.data,echarts,colorList,chartDiscrip,exportStayTime,exportCondition);
							stayTimeBar = chartInfo.bar;
							stayTimePie = chartInfo.pie;
					});
			}
			var exportStayTime = function(startDate,endDate,domainId){
				window.location = "${path}/overviewchart/exportStayTime.do?startDate="+startDate+"&endDate="+endDate+"&domainId="+domainId;
			}
			
			//查询获取资讯行为图
			var  searchInfoActChart= function(echarts){
				var startDate = $("#overViewStart").val();
				var endDate = $("#overViewEnd").val();
				var domainId = $("#domain").val();
				getClickCount(startDate,endDate,domainId,echarts,colorList);
				getIPCount(startDate,endDate,domainId,echarts,colorList);
				getStayTime(startDate,endDate,domainId,echarts,colorList);
				
				historyChange(echarts);
			}
		
		//导出为Excel
		function exportToExcel(){
			var startDate = $("#overViewStart").val();
			var endDate = $("#overViewEnd").val();
			var domainId = $("#domain").val();
			window.location = "${path}/overviewchart/exportToExcel.do?startDate="+startDate+"&endDate="+endDate+"&domainId="+domainId;
		}
			
		//echarts 变量副本
		var echartsCopy;
		
		function searchByDomain(){
			showInfoChart(echartsCopy);
			searchInfoActChart(echartsCopy);
		}
		
		//导出历史记录
		function exportToExcel2(){
			var startDate = $("#start2").val();
			var endDate = $("#end2").val();
			var domain = $("#domain").val();
			window.location = "${path}/history/exportToExcel.do?startDate="+startDate+"&endDate="+endDate+"&domain="+domain;
		}
		
		var searchHistory = function(){
			historyChange(echartsCopy);
		}
		var historyTrendLine;
		//history
		function historyChange(echarts){
			
			var startDate = $("#start2").val();
			var endDate = $("#end2").val();
			var domain = $("#domain").val();
			
			//alert(domain+":"+startDate+":"+endDate);
			
			if(startDate == "选择日期") startDate = "";
			if(endDate == "选择日期") endDate = "";
			
			$.ajax({ 
				url: "${path}/history/count.do",
				data:{
				startDate: startDate,
				endDate : endDate,
				domain : domain
				},
				success: function(data){
								
	            var json = JSON.parse(data);
	            
	            $("#start2").val(json.startDate);
	            $("#end2").val(json.endDate);
	            if(undefined != historyDateValue){
	            	historyDateValue.dateStart.max = json.endDate;
	            	historyDateValue.dateEnd.min = json.startDate;
	            }
	            
	                      //历史对比折线图	
	            historyTrendLine = echarts.init(document.getElementById('historyTrendLine'));
	 			historyTrendLine.setOption(
	 					{		
	 						title: {
	 							x:'20',
	 							text: '',
	 							textStyle : {
	 							    fontSize : '16',
	 							  	fontWeight : 'bold',
	 					  		}
	 					        
	 					    },
	 					    tooltip : {
	 					        trigger: 'axis'
	 					    },
	 					   	toolbox: {
						        show : false,
						        showTitle: true,
						        feature : {
						            saveAsImage : {
						                show : true,
						                title : '保存为图片',
						                type : 'jpeg',
						                lang : ['点击本地保存'] 
						            }
						        }
						    },
	 					   	color: ['#2ec7c9','#b6a2de','#5ab1ef','#ffb980','#d87a80'],
	 					    legend: {
	 					        data:['停留时长（秒）','点击次数','IP数量','资讯数量']
	 					    },
	 					    grid: { borderWidth:0,y:30,y2: 30, x:20,x2:20},
	 					    xAxis : [
	 					        {
	 					            type : 'category',
	 					            boundaryGap : false,
	 					            data : json.dates,
	 					            splitLine: { show:true,lineStyle: {color: '#f5f5f5',width: 1,type: 'solid'}},
	 					           	axisTick: {show:true,lineStyle: {color: '#ccc',width: 1,type: 'solid'}},
	 					           	axisLine: {	lineStyle: {color: '#ccc',width: 1,type: 'solid'}},
	 								axisLabel: {textStyle:{color:"#999"}}  
	 					        }
	 					    ],
	 					    yAxis : [
	 					        {
	 					            type : 'value',
	 					            axisLine: {show:false},
	 					            axisTick: {show:false},
	 					            axisLabel: {show:false},
	 					            splitArea: {show:false},
	 					            splitLine: {show:true,lineStyle: {color: '#f5f5f5',width: 1,type: 'solid'}}
	 					        }
	 					    ],
	 					    
	 					     series : [
	 					        
	 					       {
	 					            name:'资讯数量',
	 					            type:'line',
	 					            itemStyle: {normal: {areaStyle: {type: 'default'}}},
	 					            data:json.consultList
	 					        },
	 					         {
	 					            name:'IP数量',
	 					            type:'line',
	 					            itemStyle: {normal: {areaStyle: {type: 'default'}}},
	 					            data:json.ipList
	 					        },
	 					        {
	 					            name:'点击次数',
	 					            type:'line',
	 					            itemStyle: {normal: {areaStyle: {type: 'default'}}},
	 					            data:json.clickNumberList
	 					        },
	 					        {
	 					            name:'停留时长（秒）',
	 					            type:'line',
	 					            itemStyle: {normal: {areaStyle: {type: 'default'}}},
	 					            data:json.stayTimeList
	 					        }
	 					    ]
	 					}
	 			);
	 						//历史对比折线图结束	
	            }})
			
		}
		
		
    	// 路径配置
	    require.config({
	        paths: {
	            echarts: '${path}/js/echart/dist'
	        }
	    });
    
	    // 使用
	    require(
	        [
	            'echarts',
				'echarts/chart/bar',
				'echarts/chart/pie',
				'echarts/chart/line'
	        ],
			function (echarts) {
					// 基于准备好的dom，初始化echarts图表
					echartsCopy = echarts;
					//查询显示日更新资讯数量图
					showInfoChart(echarts);
					//查询显示点击量图
					searchInfoActChart(echarts);
					
				
			
						
					function  loadHistory(){
						var startDate = $("#start2").val();
						var endDate =  $("#end2").val();
						var domain = $("#domain").val();
						
						if(startDate == "选择日期") startDate = "";
						if(endDate == "选择日期") endDate = "";
						$.ajax({ 
							url: "${path}/history/count.do",
										data : {
											startDate : startDate,
											endDate : endDate,
											domain: domain
										},
										success : function(data) {

											var json = JSON.parse(data);
											$("#start2").val(json.startDate);
											$("#end2").val(json.endDate);
										
											
											//历史对比折线图	
											historyTrendLine = echarts
													.init(document
															.getElementById('historyTrendLine'));
											historyTrendLine
													.setOption({
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
														 toolbox: {
														        show : false,
														        showTitle: true,
														        feature : {
														            saveAsImage : {
														                show : true,
														                title : '保存为图片',
														                type : 'jpeg',
														                lang : ['点击本地保存'] 
														            }
														        }
														    },
														color : [ '#2ec7c9',
																'#b6a2de',
																'#5ab1ef',
																'#ffb980',
																'#d87a80' ],
														legend : {
															data : [ '停留时长（秒）',
																	'点击次数',
																	'IP数量',
																	'资讯数量' ]
														},
														grid : {
															borderWidth : 0,
															y : 30,
															y2 : 30,
															x : 20,
															x2 : 20
														},
														xAxis : [ {
															type : 'category',
															boundaryGap : false,
															data : json.dates,
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
															axisLine : {
																show : false
															},
															axisTick : {
																show : false
															},
															axisLabel : {
																show : false
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
																	name : '资讯数量',
																	type : 'line',
																	itemStyle : {
																		normal : {
																			areaStyle : {
																				type : 'default'
																			}
																		}
																	},
																	data : json.consultList
																},
																{
																	name : 'IP数量',
																	type : 'line',
																	itemStyle : {
																		normal : {
																			areaStyle : {
																				type : 'default'
																			}
																		}
																	},
																	data : json.ipList
																},
																{
																	name : '点击次数',
																	type : 'line',
																	itemStyle : {
																		normal : {
																			areaStyle : {
																				type : 'default'
																			}
																		}
																	},
																	data : json.clickNumberList
																},
																{
																	name : '停留时长（秒）',
																	type : 'line',
																	itemStyle : {
																		normal : {
																			areaStyle : {
																				type : 'default'
																			}
																		}
																	},
																	data : json.stayTimeList
																} ]
													});
											//历史对比折线图结束	
										}
									})
						}

						loadHistory();

						//分类浏览资讯折线图	
						var trendline2 = echarts.init(document
								.getElementById('trendline2'));
						trendline2.setOption({
							title : {
								x : '20',
								text : '时间分布',
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
									name : '点击次数',
									textStyle : {
										fontSize : 12,
										color : '#666'
									},
									icon : 'roundRect'
								}, {
									name : 'IP数量',
									textStyle : {
										fontSize : 12,
										color : '#666'
									},
									icon : 'roundRect'
								}, {
									name : '平均停留时长（秒）',
									textStyle : {
										fontSize : 12,
										color : '#666'
									},
									icon : 'roundRect'
								}

								]

							},
							grid : {
								borderWidth : 0,
								y : 50,
								y2 : 30,
								x : 20,
								x2 : 20
							},
							xAxis : [ {
								type : 'category',
								boundaryGap : false,
								data : [ '0:00', '02:00', '04:00', '06:00',
										'08:00', '10:00', '12:00', '14:00',
										'16:00', '18:00', '20:00', '22:00' ],
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
								axisLine : {
									show : false
								},
								axisTick : {
									show : false
								},
								axisLabel : {
									show : false
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
										name : '点击次数',
										type : 'line',
										itemStyle : {
											normal : {
												areaStyle : {
													type : 'default'
												}
											}
										},
										data : [ 30, 182, 434, 791, 390, 30,
												10, 30, 182, 434, 791, 390 ],
									},
									{
										name : 'IP数量',
										type : 'line',
										itemStyle : {
											normal : {
												areaStyle : {
													type : 'default'
												}
											}
										},
										data : [ 10, 12, 21, 54, 260, 530, 610,
												10, 12, 21, 54, 260 ],
									},
									{
										name : '平均停留时长（秒）',
										type : 'line',
										itemStyle : {
											normal : {
												areaStyle : {
													type : 'default'
												}
											}
										},
										data : [ 300, 120, 440, 391, 390, 300,
												100, 300, 120, 440, 391, 390 ],
									},

							]
						});
						//分类浏览资讯折线图结束	

						//分类浏览位置折线图	
						var trendline3 = echarts.init(document
								.getElementById('trendline3'));
						trendline3.setOption({
							title : {
								x : '20',
								text : '时间分布',
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
									name : '点击次数',
									textStyle : {
										fontSize : 12,
										color : '#666'
									},
									icon : 'stack'
								}, {
									name : 'IP数量',
									textStyle : {
										fontSize : 12,
										color : '#666'
									},
									icon : 'stack'
								}, {
									name : '平均停留时长（秒）',
									textStyle : {
										fontSize : 12,
										color : '#666'
									},
									icon : 'stack'
								}

								]
							},
							grid : {
								borderWidth : 0,
								y : 50,
								y2 : 30,
								x : 20,
								x2 : 20
							},
							xAxis : [ {
								type : 'category',
								boundaryGap : false,
								data : [ '0:00', '02:00', '04:00', '06:00',
										'08:00', '10:00', '12:00', '14:00',
										'16:00', '18:00', '20:00', '22:00' ],
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
								axisLine : {
									show : false
								},
								axisTick : {
									show : false
								},
								axisLabel : {
									show : false
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
										name : '点击次数',
										type : 'line',
										itemStyle : {
											normal : {
												areaStyle : {
													type : 'default'
												}
											}
										},
										data : [ 30, 182, 434, 791, 390, 30,
												10, 434, 791, 390, 30, 10 ]
									},
									{
										name : 'IP数量',
										type : 'line',
										itemStyle : {
											normal : {
												areaStyle : {
													type : 'default'
												}
											}
										},
										data : [ 10, 12, 21, 54, 260, 530, 610,
												10, 12, 21, 54, 260, ]
									},
									{
										name : '平均停留时长（秒）',
										type : 'line',
										itemStyle : {
											normal : {
												areaStyle : {
													type : 'default'
												}
											}
										},
										data : [ 300, 120, 440, 391, 390, 300,
												100, 300, 120, 440, 391, 390, ]
									},

							]
						});
						//分类浏览位置折线图结束	
					});
		</script>
</body>
</html>
