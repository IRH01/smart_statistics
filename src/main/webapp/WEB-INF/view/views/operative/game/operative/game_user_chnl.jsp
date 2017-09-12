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
<title>市场合作渠道数据</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/admin-trend.css" />
<script src="<%=request.getContextPath()%>/js/laydate/laydate.js"></script>
<link href="<%=request.getContextPath()%>/js/myPagination/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/myPagination/js/msgbox/msgbox.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/myPagination/js/myPagination/page.css" />
<link rel="stylesheet" type="text/css" href="${path}/js/monthpicker/skin/jquery.monthpicker.css" />

<script src="<%=request.getContextPath()%>/js/myPagination/js/myPagination/jquery.myPagination6.0.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
<script src="<%=request.getContextPath()%>/js/tool.js"></script>
<script src="${path}/js/datecontrol.js"></script>
<link type="text/css" rel="stylesheet" href="${path}/css/jquery-ui.css" />
<script src="${path}/js/jquery-ui.js"></script>
<script src="${path}/js/monthpicker/jquery.monthpicker.js"></script>
<script src="${path}/js/echart/dist/echarts.js"></script>

</head>
<body>
	<div class="sdk-content">
		<ul class="breadcrumb">
			<li>您当前的位置：</li>
			<tags:breadcrumb />
			<li>    </li>
		</ul>
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingchannelName">
				<h4 class="panel-title" id="-collapsible-group-item-#1-">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapsechannelName" aria-expanded="true"
						aria-controls="collapsechannelName" class="">     </a>
				</h4>
			</div>
			<input type="hidden" id="countryId" value="${countryId}">
			<div id="collapsechannelName" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingchannelName" aria-expanded="true">
					<div class="panel-body">
						<div class="admin-content partner-container">
								<div class="section-box">
									<div class="titleDiv" style="height:25px;">
										<div style="display: inline;width:600px;text-align: center;"></div>
									    <div class="select-fr" style="padding-right: 30px;float:left;">
									    	<table style="margin-left:5px;">
									    		<tr>
									    			<td class="tb1Td">用户指标：</td>
											    	<td>
											    		<select id="userType"  class="select" style="width:100px;">
			                                            	<option value="1">注册用户</option>
			                                            	<option value="2">充值用户</option>
			                                            	<option value="3">活跃用户</option>
			                                            	<option value="4">活跃率</option>
			                                        	</select>
			                                        </td>
									    			<td class="tb1Td"><span class="span">渠道ID：</span></td>
									    			<td><input id="channelId" class="input"></td>
									    			<td class="tb1Td">产品：</td>
											    	<td>
											    		<select id="platformId" class="select" style="width:100px;">
			                                        		<option value="-1">全部产品</option>
			                                            	<c:forEach items="${produces}" var="produce">
			                                            		<option value="${produce.produceId}">${produce.produceName}</option>
			                                            	</c:forEach>
			                                        	</select>
			                                        </td>
			                                        
			                                        
			                                        <td >
									    			<span class="span">时间：</span>
										    		</td>
										    		<td>
										    			<input placeholder="请选择日期" class="laydate-icon" style="height:25px;" id="dateStart" readonly>
										    		</td>
										    		<td>
										    			<span class="span">&nbsp;至&nbsp;</span>
										    		</td>
										    		<td>
										    			 <input class="laydate-icon" id="dateEnd" style="height:25px;" placeholder="请选择日期" readonly>
										    		</td>
										    		
									    			<td  style="line-height:1;">
														<button type="button" id="search" class="btn btn-primary btn-sm" onclick="search();"
															<i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;width:58px"></i>查询
														</button>
													<td/>
													<td  style="line-height:1;">
														<button type="button" id="search" class="btn btn-primary btn-sm" onclick="reset();"
															<i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;width:58px"></i>重置
														</button>
													<td/>
													<td colspan="2" style="line-height:1;">
													<button type="button" id="export" class="btn btn-primary btn-sm" onclick="exportToExcel();"
														<i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;width:124px"></i>导出为Excel
													</button>
												</td>
									    		</tr>
									    	</table>
										</div>
									</div>
									<div class="whiteDiv tab-content">
										<div>
											<ul id="sortable" class="ui-widget-content sortable">
												<li class="ui-state-default">
		 											<div  class="ui-widget-content resize" style="height:300px;">
		 												<div class="sortHandle">趋势图</div>
														<div style="width:100%;height:95%;">
															<!-- <div style="margin-left: -10px;">
																<span>客户端类型：</span>
																<input type="checkbox" id="cltTypeAll" onchange="deviceTypeChange(this);" checked>全部</input>
																<c:if test="${deviceTypes != null}">
																	<c:forEach items="${deviceTypes}" var="deviceType">
																		<input type="checkbox" class="deviceType" onchange="deviceTypeChange(this);" value="${deviceType.type}" checked>${deviceType.name}</input>
																	</c:forEach>												
																</c:if>
															</div> -->
															<div id="trendline" class="trendline"></div>
														</div> 
													</div>
												</li>
												<li class="ui-state-default">
													<div style="padding-bottom:35px;" class="ui-widget-content resize">
														<div class="sortHandle div-sum">	
														</div>
														<div class="tablePanel">
															<table class="tableList1">
																<thead>
																	<tr>
																		<th>时间</th>
																		<th>用户指标</th>
																		<th>渠道ID</th>
																		<th>平台</th>
																		<th>产品</th>
																	</tr>
																</thead>
																<tbody id="data">
																</tbody>
															</table>
														</div>
														<div>
															<table class="tablePage">
																<tr>
																	<td><div class="divPage">
																		<span class="spanPageSize">每页个数：
																			<select id="pageSize" onchange="search();">
																				<option value="10">10</option>
																				<option value="20">20</option>
																				<option value="50">50</option>
																				<option value="100">100</option>
																				<option value="200">200</option>
																			</select>
																		</span>
																	</td>
																	<td><span class="spanPageSize">总记录数：</span><span id="totalCount" class="spanPageSize"></span></td>
																	<td><span class="spanPageSize">总页数：</span><span id="totalPage" class="spanPageSize"></span></td>
																	<td class="tablePageTd"><div id="page"></div></td>
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
</body>

<script type="text/javascript">
		


			$(function(){
				//起始时间根据：上线时间变化
				setDateRangeConfig("dateStart","dateEnd",null,true,false);
				//search();
				bindEnterEventOfAllEle();
				$( "#sortable" ).sortable({cursor: "move",handle: ".sortHandle"});
				$( ".resize" ).resizable({containment: ".admin-content",minHeight: 600,minWidth: 600});
			});
			var bindEnterEventOfAllEle = function(){
				bindEnterEvent("userType",search);
				bindEnterEvent("channelId",search);
				bindEnterEvent("platformId",search);
			}
			
	   		var pageSize = 10;
			var addTbRow = function(data,isTotalRow){
				if(null != data && undefined != data && "" != data){
					var userNum = "";
					if($("#userType").val() == '1') {
						userNum = data.regUcnt;
					}if($("#userType").val() == '2') {
						userNum = data.rechargeUcnt;
					}if($("#userType").val() == '3') {
						userNum = data.activeUcnt;
					}if($("#userType").val() == '4') {
						userNum = data.activeRate;
					}
					
					var ele = "<tr><td>" + data.statDate + "</td><td>" + userNum
					+ "</td><td>" + data.channelId + "</td><td>"
					+ data.osName + "</td><td>" + data.platformId
					+ "</td>";
					$("#data").append(ele);
		}
	}

	//显示统计列表
	var showData = function(pageNumber, pageSize) {
		$("#data").empty();
		$.post("${path}/game/operative/gameUserChnl/list.do", {
			channelId : $("#channelId").val(),
			userType : $("#userType").val(),
			platformId:$("#platformId").val(),
			dateStart:$("#dateStart").val(),
			dateEnd:$("#dateEnd").val(),
			pageNumber : pageNumber,
			pageSize : pageSize
		}, function(data) {
			var json = JSON.parse(data);
			if (null != json && undefined != json) {
				$("#totalCount").html(json.total);
				$("#totalPage").html(json.pages);
				$("#page").myPagination({
					currPage : pageNumber,
					pageCount : json.pages,
					ajax : {
						on : false,
						onClick : function(page) {
							showData(page, pageSize);
						}
					}
				});
				var infoData = json.list;
				if (null == infoData || undefined == infoData
						|| 0 >= infoData.length) {
					$("#data").append("<tr><td colspan=\"12\">没有数据</td></tr>");
				} else {
					for (var i = 0; i < infoData.length; i++) {
						
						var ele = {
								statDate : infoData[i].statDate,
								channelId : infoData[i].channelId,
								platformId : infoData[i].platformId,
								regUcnt : infoData[i].regUcnt,
								rechargeUcnt : infoData[i].rechargeUcnt,
								rechargeAmount : infoData[i].rechargeAmount,
								activeUcnt : infoData[i].activeUcnt,
								totalActiveUcnt : infoData[i].totalActiveUcnt,
								activeRate : infoData[i].activeRate,
								osName : infoData[i].osName
						}
						addTbRow(ele);
					}
				}
			} else {
				$("#data").append("<tr><td colspan=\"12\">没有数据</td></tr>");
			}
		});
	}

	var exportToExcel = function() {
		var index = layer.load();
		$.ajax({
			url : "${path}/game/operative/gameUserChnl/export.do",
			type : "POST",
			data : {
				channelId : $("#channelId").val(),
				userType : $("#userType").val(),
				platformId:$("#platformId").val(),
				dateStart:$("#dateStart").val(),
				dateEnd:$("#dateEnd").val()
			},
			success : function(response, status, request) {
				layer.close(index);
				if (!response.canExport) {
					layer.msg("当前查询条件数据量过大，请缩小查询条件再导出！", {
						icon : 5
					});
					return;
				}
				window.location = "${path}/game/operative/gameUserChnl/download.do?uuid="
						+ response.uuid;
			}
		});
	}

	//重置
	var reset = function() {
		$("#userType").val("1");
		$("#channelId").val("");
		$("#dateStart").val("");
		$("#dateEnd").val("");
		$("#platformId").val("-1");
		search();
	}

	var search = function() {
		if($("#dateStart").val() != '' || $("#dateEnd").val() != '') {
			if($("#dateStart").val() == '') {
				alert("选择结束时间必须选择开始时间");
				return ;
			}if($("#dateEnd").val() == '') {
				alert("选择开始时间必须选择结束时间");
				return ;
			}
		}
		pageSize = $("#pageSize").val() * 1;
		showData(1, pageSize);
		loadNewUserDataTrendLine(echartsCopy);
	}
		
	
	
	var trendline;
	function loadNewUserDataTrendLine(echarts) {
		$.ajax({
			url : "${path}/game/operative/gameUserChnl/chart.do",
			data : {
				channelId : $("#channelId").val(),
				userType : $("#userType").val(),
				platformId:$("#platformId").val(),
				dateStart:$("#dateStart").val(),
				dateEnd:$("#dateEnd").val()
			},
			success : function(data) {
				var json = JSON.parse(data);
				var userName = "";
				if(json.userType == '1') {
					userName = "注册用户";
				} else if(json.userType == '2') {
					userName = "充值用户";
				} else if(json.userType == '3') {
					userName = "活跃用户";
				} else if(json.userType == '4') {
					userName = "活跃率";
				}
				trendline = echarts.init(document.getElementById('trendline'));
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
					color : [ '#2ec7c9' ],
					legend : {
						show : true,
						orient : 'horizontal',
						data : [ {
							name : userName,
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
						axisLine : {
							show : true
						},
						axisTick : {
							show : true
						},
						axisLabel : {
							show : true,
							formatter:function(val){
							    return val;
							}
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
								name : userName,
								type : 'line',
								data : json.datas
							}
					]
				});
			}
		})
	}
	
	var echartsCopy;
	// 路径配置
	require.config({
		paths : {
			echarts : '${path}/js/echart/dist'
		}
	});
	
	// 使用
	require([ 'echarts', 'echarts/chart/line' ], function(ec) {
		echartsCopy = ec;
		search();
		//hideDetailTableClick();
	});
</script>
</html>
