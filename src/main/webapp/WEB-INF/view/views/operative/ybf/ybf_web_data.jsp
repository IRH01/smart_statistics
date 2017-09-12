<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<%
	request.setAttribute("path", request.getContextPath());
%>
<%
	String path = request.getContextPath();
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
<script src="${path}/js/tool.js"></script>
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
						aria-controls="collapseOne" class=""> 统计图 </a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
					<div class="domainDiv">
						<label class="control-label" for="" style="font-size: 16px;float:left;line-height:2;">域名：</label>
						<select id="domain" class="form-control input-sm" onchange="searchByDomianChange()"
							style="display: initial; width: inherit;float:left;">
							<c:forEach items="${domains}" var="item">
								<option value="${item}">${item}</option>
							</c:forEach>
						</select>
						<button type="button" id="search" class="btn btn-primary btn-sm"
							onclick="search();">
							<i class="icon-search icon-white"></i>&nbsp;查&nbsp;&nbsp;询&nbsp;
						</button>
					</div>
					<div class="panel-body">
						<div class="admin-content">
								<!--分类浏览-->
								<div class="section-box">
									<div class="titleDiv">
										    <div class="select-fr" style="padding-right: 5px;">
											    <button type="button" id="exportToExcel1" style="display:none;"
														class="btn btn-primary btn-sm" onclick="exportExcel();">
														<i class="icon-search icon-white"></i>导出为Excel
											    </button>
												<span class="laydateBox"> 日期：<input value="选择日期"
													class="laydate-icon" id="dateStart" class="laydate-icon" readonly> 至 <input
													class="laydate-icon" id="dateEnd" value="选择日期" class="laydate-icon" readonly>
												</span>
											    <!--选择类别-->
												<span id="type">
												&nbsp;&nbsp;
		                                            <select id="dateType" onchange="dateTypeChange();" class="select">
		                                                 <option value="1">今日</option>
		                                                 <option value="2">近一周</option>
		                                                 <option value="3">近一月</option>
		                                                  <option value="0">自定义</option>
		                                            </select>
	                                            </span> 
										</div>
										<h2>一比分WEB数据统计</h2>
									</div>

									<div class="whiteDiv tab-content">
										<div class="contentDiv">
											<ul id="sortable" class="ui-widget-content sortable">
												<li class="ui-state-default">
													<div class="ui-widget-content resize resizePanel">
														<div class="sortHandle">列表</div>
														<div class="tablePanel">
															<table class="tableList">
																<colgroup>
																	<col width="120" />
																	<col width="90" />
																	<col width="90" />
																	<col width="90" />
																	<col width="90" />
																	<col width="90" />
																	<col width="90" />
																	<col width="90" />
																</colgroup>
																<tbody id="ybfWebData">
																</tbody>
															</table>
														</div>
														<div id="page"></div>
													</div>
												</li>
												<li class="ui-state-default">
		 											<div  class="ui-widget-content resize" style="height:300px;">
		 												<div class="sortHandle">趋势图</div>
		 												<div style="padding-right: 20px;float:right;">
															<span >&nbsp;&nbsp;统计页面: 
					                                            <select id="urlName" onchange="urlNameChange();">
					                                            </select>
			                                            	</span>
			                                            </div>
														<div id="trendline" class="trendline" style="height:85%;"></div>
													</div>
												</li>
											</ul>
									</div>
								</div>
								<!--分类浏览end-->
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
			var dateTypeChange = function(){
				setDateType3Change("dateType","dateStart","dateEnd",dateValue.dateStart,dateValue.dateEnd,search);
			}
			var dateValue;
			$(function(){
				dateValue = setDateRangeConfig("dateStart","dateEnd",dateChange);
				$( "#sortable" ).sortable({cursor: "move",handle: ".sortHandle"});
				$( ".resize" ).resizable({minHeight: 150,minWidth: 150});
				$(window).resize(
						function(){
							chartResize(trendline);
					}
				);
			});
	   		var pageSize = 10;
	   		//设置URL名称
	    	var setUrlNames = function(){
		    	$.post("${path}/ybfwebdata/getUrlNames.do",{
					domainName:$('#domain').val()
				},function(data){
					var optionstring = ""; 
					if(null != data && undefined != data && ""!=data){
						for(var i=0;i < data.length;i++){
	                        optionstring += "<option value=\"" + data[i].urlId + "\" >" + data[i].urlName + "</option>";  
						}
					}
					$("#urlName").html(optionstring);
					//加载趋势图数据
					loadYbfDataTrendLine(echartsCopy);
				});
	    	}
	    	
	    	//获取当前月天数
	    	var getCurMonthDayCnt = function(){
	    		var now = new Date();
	    		var date = new Date(now.getYear(),now.getMonth() + 1,0);
	    		return date.getDate();
	    	}
	    	
	   		
	   		var searchByDomianChange = function(){
	   			showYbfWebData(1,pageSize);
				setUrlNames();
	   		}
	    	
			var showTbCl = function(){
				$("#ybfWebData").append("<tr><td>统计页面</td><td>点击量</td><td>访客数</td><td>访问次数</td><td>IP数</td><td>访问时长</td><td>平均访问时长</td><td>累计用户（占比）</td></tr>");
			}
			var addTbRow = function(data){
				if(null != data && undefined != data && "" != data){
					var ele="<tr><td>urlName</td><td>clickCnt</td><td>userCnt</td><td>viewCnt</td><td>ipCnt</td><td>stayCnt</td><td style=\'color: hsl(208, 95%, 55%);\'>avgStayCnt</td><td style=\'color: hsl(208, 95%, 55%);\'>dayTotalUserCnt(userPercent%)</td></tr>";
					ele = ele.replace("urlName",data.urlName).replace("clickCnt",data.clickCnt).replace("userCnt",data.userCnt).replace("viewCnt",data.viewCnt).replace("ipCnt",data.ipCnt).replace("stayCnt", data.stayCnt).replace("avgStayCnt",data.avgStayCnt).replace("dayTotalUserCnt",data.dayTotalUserCnt).replace("userPercent",(data.userPercent*100).toFixed(2));
					$("#ybfWebData").append(ele);
				}
			}
			
			//显示统计列表
			var showYbfWebData = function(pageNumber,pageSize){
				$("#ybfWebData").empty();
				showTbCl();
				$.post("${path}/ybfwebdata/list.do",{
					startDate:$('#dateStart').val(),
					endDate:$('#dateEnd').val(),
					domain:$('#domain').val(),
					pageNumber:pageNumber,
					pageSize:pageSize
				},function(data){
					var json = JSON.parse(data);
					if(null != json && undefined != json){
						$("#page").myPagination({
					          currPage: pageNumber,
					          pageCount: json.ybfWebStatDs.pages,
					          ajax:{on:false,  
				                    onClick:function(page){
				                    	showYbfWebData(page,pageSize);
				                    }  
				        		}
					        });
						var infoData = json.ybfWebStatDs.list;
						if(null == infoData || undefined == infoData || 0 >= infoData.length){
							$("#ybfWebData").append("<tr><td colspan=\"8\">没有数据</td></tr>");
						}else{
							for(var i=0;i < infoData.length;i++){
								var ele = {
										urlName:infoData[i].urlName,
										clickCnt:infoData[i].clickCnt,
										userCnt:infoData[i].userCnt,
										ipCnt:infoData[i].ipCnt,
										stayCnt:formatSeconds(infoData[i].stayCnt),
										dayTotalUserCnt:infoData[i].dayTotalUserCnt,
										viewCnt:infoData[i].viewCnt,
										avgStayCnt:formatSeconds(infoData[i].avgStayCnt),
										userPercent:infoData[i].userPercent
								}
								addTbRow(ele);
							}
						}
					}else{
						$("#ybfWebData").append("<tr><td colspan=\"8\">没有数据</td></tr>");
					}
				});
			}

			//查询显示
			var search = function(){
				showYbfWebData(1,pageSize);//查询显示资讯信息
				loadYbfDataTrendLine(echartsCopy);
			}
			
			function exportExcel(){
				var domain = $("#domain").val();
				var dateType =$("#dateType").val();
				var date = $("#currentDate").val();
				window.location = "${path}/category/exportInfoList.do?date="+date+"&domain="+domain+"&dateType="+dateType;
			}
			
			function urlNameChange(){
				loadYbfDataTrendLine(echartsCopy);
			}
			
			var trendline;
			function  loadYbfDataTrendLine(echarts){
				var urlId = $("#urlName").val();
				var startDate =$("#dateStart").val();
				var endDate =$("#dateEnd").val();
				
				$.ajax({ 
					url: "${path}/ybfwebdata/chart.do",
					data : {
						urlId: urlId,
						startDate:startDate,
						endDate:endDate
					},
					success : function(data) {

						var json = JSON.parse(data);
						
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
								trigger : 'axis'
							},
							color : [ '#2ec7c9', '#b6a2de', '#5ab1ef',
									'#ffb980', '#d87a80' ],
							legend : {
								show : true,
								orient : 'horizontal',
								data : [ {
									name : '点击量',
									textStyle : {
										fontSize : 12,
										color : '#666'
									},
									icon : 'roundRect'
								}, {
									name : '访客数',
									textStyle : {
										fontSize : 12,
										color : '#666'
									},
									icon : 'roundRect'
								}, {
									name : '访问次数',
									textStyle : {
										fontSize : 12,
										color : '#666'
									},
									icon : 'roundRect'
								}, {
									name : 'IP数',
									textStyle : {
										fontSize : 12,
										color : '#666'
									},
									icon : 'roundRect'
								}, {
									name : '停留时长（秒）',
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
								x2 : 35
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
										name : 'IP数',
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
										name : '访客数',
										type : 'line',
										itemStyle : {
											normal : {
												areaStyle : {
													type : 'default'
												}
											}
										},
										data : json.userList
									},
									{
										name : '访问次数',
										type : 'line',
										itemStyle : {
											normal : {
												areaStyle : {
													type : 'default'
												}
											}
										},
										data : json.viewList
									},
									{
										name : '点击量',
										type : 'line',
										itemStyle : {
											normal : {
												areaStyle : {
													type : 'default'
												}
											}
										},
										data :json.clickList
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
										data : json.stayList
									}
							]
						});
						
					}
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
	require([ 'echarts', 'echarts/chart/line' ],
		function(ec) {
			echartsCopy = ec;
			searchByDomianChange();
	});
	</script>
</body>
</html>
