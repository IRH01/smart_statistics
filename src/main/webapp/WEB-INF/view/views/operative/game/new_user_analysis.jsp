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
<link type="text/css" rel="stylesheet" href="${path}/css/jquery-ui.css" />
<script src="${path}/js/myPagination/js/myPagination/jquery.myPagination6.0.js" type="text/javascript"></script>
<script src="${path}/js/jquery-ui.js"></script>
<script src="${path}/js/datecontrol.js"></script>

</head>
<body style="background:#efefef;">
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
										<div style="margin-left:8px;">
											<input type="hidden" id="platformId" value="${platformId}">
											<span class="laydateBox"> 日期：<input value="选择日期"
												class="laydate-icon" id="dateStart"> 至 <input
												class="laydate-icon" id="dateEnd" value="选择日期">
											</span>
											<span id="type">
	                                            <select id="dateType" onchange="dateTypeChange();" style="width:auto;" class="select">
	                                                 <option value="1">今日</option>
	                                                 <option value="2">昨日</option>
	                                                 <option value="3">近7日</option>
	                                                 <option value="4">近30日</option>
	                                                 <option value="5">近90日</option>
	                                                 <option value="0">自定义</option>
	                                            </select>
                                            </span> 
										</div>
									</div>
									<div class="whiteDiv tab-content">
										<ul id="sortable" class="ui-widget-content sortable">
											<li class="ui-state-default">
	 											<div  class="ui-widget-content resize" style="height:300px;">
	 												<div class="sortHandle">趋势图</div>
													<div style="width:100%;height:95%;">
														<div style="margin-left: -10px;">
															<span>客户端类型：</span>
															<input type="checkbox" id="cltTypeAll" onchange="deviceTypeChange(this);" checked>全部</input>
															<c:if test="${deviceTypes != null}">
																<c:forEach items="${deviceTypes}" var="deviceType">
																	<input type="checkbox" class="deviceType" onchange="deviceTypeChange(this);" value="${deviceType.type}" checked>${deviceType.name}</input>
																</c:forEach>												
															</c:if>
														</div>
														<div id="trendline" class="trendline"></div>
													</div> 
												</div>
											</li>
											<li class="ui-state-default">
												<div style="padding-bottom:60px;" class="ui-widget-content resize resizePanel">
													<div class="sortHandle">列表</div>
													<div class="tablePanel">
														<table class="tableList1" >
															<colgroup>
																<col width="120" />
																<col width="90" />
																<col width="100" />
																<col width="100" />
																<col width="100" />
																<col width="80" />
																<col width="80" />
																<col width="100" />
																<col width="80" />
																<col width="50" />
															</colgroup>
															<thead>
																<tr>
																	<th>日期</th>
																	<th>新增玩家</th>
																	<th>注册玩家转化率</th>
																	<th>活跃玩家</th>
																	<th>注册充值转化率</th>
																	<th>付费人数</th>
																	<th>付费次数</th>
																	<th>付费金额（￥）</th>
																	<th>ARPU</th>
																	<th>操作</th>
																</tr>
															</thead>
															<tbody id="newUserData"></tbody>
														</table>
													</div>
													<div id="page"></div>
												</div>
											</li>
										</ul>
								</div>
								<!--新增玩家详情-->
								<div class="xq-box" style="width:auto;">
									<table class="xq-table" style="width:990px;">
										<caption>&nbsp;&nbsp;&nbsp;<span class="close" style="color: #fff;opacity: inherit;margin-top:-3px;"></span></caption>
										<colgroup>
											<col width="50" />
											<col width="50" />
											<col width="50" />
											<col width="50" />
											<col width="50" />
											<col width="50" />
											<col width="50" />
											<col width="50" />
											<col width="50" />
										</colgroup>
										<thead>
											<tr>
												<th>类型</th>
												<th>新增玩家</th>
												<th>注册玩家转化率</th>
												<th>活跃玩家</th>
												<th>注册充值转化率</th>
												<th>付费人数</th>
												<th>付费次数</th>
												<th>付费金额（￥）</th>
												<th>ARPU</th>
											</tr>
										</thead>
										<tbody id="detailData" style="overflow:auto;">
										</tbody>
									</table>
								</div>
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
			
			$(function(){
				$( "#sortable" ).sortable({cursor: "move",handle: ".sortHandle"});
				$( ".resize" ).resizable({minHeight: 200,minWidth: 300});
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
	    	var deviceTypeChange = function(ele){
	    		if(ele.id == "cltTypeAll"){
    				$(".deviceType").prop("checked",ele.checked);
	    		}else{
	    			if(!ele.checked){
	    				$("#cltTypeAll").prop("checked",false);
	    			}
	    		}
	    		loadNewUserDataTrendLine(echartsCopy);
	    	}
	    	
	    	var getDeviceType = function(){
	    		var devices = $(".deviceType");
	    		var deviceTypes = "";
	    		for(i=0;i<devices.length;i++)
	    		{
	    			if(devices[i].checked){
	    				if("" != deviceTypes){
	    					deviceTypes += ",";
	    				}
	    				deviceTypes += devices.eq(i).val();
	    			}
	    		}
	    		return deviceTypes;
	    	}
	   		
			var addDetailDataTbRow = function(data){
				if(null != data && undefined != data && "" != data){
					var ele="<tr><td>deviceName</td><td>registerCount</td><td>regConversionPercent</td><td>activeCount</td><td>regAndRechargeCvsPercent</td><td>payCount</td><td>payTimes</td><td>payAmount</td><td>ARPU</td></tr>";
					ele = ele.replace("deviceName",data.deviceName).replace("registerCount",data.registerCount).replace("regConversionPercent",data.regConversionPercent).replace("activeCount",data.activeCount).replace("regAndRechargeCvsPercent",data.regAndRechargeCvsPercent).replace("payCount", data.payCount).replace("payTimes",data.payTimes).replace("payAmount",data.payAmount).replace("ARPU",data.arpu);
					$("#detailData").append(ele);
				}
			}
			
			var showDetailData = function(date){
				$("#detailData").empty();
				$.post("${path}/game/newuserals/detail.do",{
					date:date,
					platformId:$("#platformId").val()
				},function(data){
					var json = JSON.parse(data);
					if(null == json || undefined == json || 0 >= json.length){
						$("#detailData").append("<tr><td colspan=\"9\">没有数据</td></tr>");
					}else{
						for(var i=0;i < json.length;i++){
							var ele = {
									deviceName:json[i].deviceName,
									registerCount:json[i].registerCount,
									regConversionPercent:(json[i].regConversionPercent*100).toFixed(2) +"%",
									activeCount:json[i].activeCount,
									regAndRechargeCvsPercent:(json[i].regAndRechargeCvsPercent*100).toFixed(2) +"%",
									payCount:json[i].payCount,
									payTimes:json[i].payTimes,
									payAmount:json[i].payAmount,
									arpu:json[i].arpu.toFixed(2)
							}
							addDetailDataTbRow(ele);
						}
					}
				});
			}
			
			var onDetailClick = function(ele){
				$(".xq-box").css('left', $(ele).offset().left-1269).css('top', $(ele).offset().top-55).show();
				var parentObj = $(ele).parent().parent();
				var infoIdEle = $(parentObj).children(".date")[0];
				showDetailData($(infoIdEle).text());
			}
			
			var hideDetailTableClick = function(){
				$(".close").bind("mousedown",function(event){
					$(".xq-box").hide();
				});
			}
	   		
	    	
			var addTbRow = function(data){
				if(null != data && undefined != data && "" != data){
					var ele="<tr><td class=\"date\">statDate</td><td>registerCount</td><td>regConversionPercent</td><td>activeCount</td><td>regAndRechargeCvsPercent</td><td>payCount</td><td>payTimes</td><td >payAmount</td><td>ARPU</td><td><a class=\"xiangqing\" onclick=\"onDetailClick(this);\"></a></td></tr>";
					ele = ele.replace("statDate",data.statDate).replace("registerCount",data.registerCount).replace("regConversionPercent",data.regConversionPercent).replace("activeCount",data.activeCount).replace("regAndRechargeCvsPercent",data.regAndRechargeCvsPercent).replace("payCount", data.payCount).replace("payTimes",data.payTimes).replace("payAmount",data.payAmount).replace("ARPU",data.arpu);
					$("#newUserData").append(ele);
				}
			}
			
			//显示统计列表
			var showNewUserData = function(pageNumber,pageSize){
				$("#newUserData").empty();
				$.post("${path}/game/newuserals/list.do",{
					startDate:$('#dateStart').val(),
					endDate:$('#dateEnd').val(),
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
				                    	showNewUserData(page,pageSize);
				                    }  
				        		}
					        });
						var infoData = json.list;
						if(null == infoData || undefined == infoData || 0 >= infoData.length){
							$("#newUserData").append("<tr><td colspan=\"10\">没有数据</td></tr>");
						}else{
							for(var i=0;i < infoData.length;i++){
								var ele = {
										statDate:infoData[i].statDate,
										registerCount:infoData[i].registerCount,
										regConversionPercent:(infoData[i].regConversionPercent*100).toFixed(2) +"%",
										activeCount:infoData[i].activeCount,
										regAndRechargeCvsPercent:(infoData[i].regAndRechargeCvsPercent*100).toFixed(2) +"%",
										payCount:infoData[i].payCount,
										payTimes:infoData[i].payTimes,
										payAmount:infoData[i].payAmount,
										arpu:infoData[i].arpu.toFixed(2)
								}
								addTbRow(ele);
							}
						}
					}else{
						$("#newUserData").append("<tr><td colspan=\"10\">没有数据</td></tr>");
					}
				});
			}

			//查询显示
			var search = function(){
				showNewUserData(1,pageSize);
				loadNewUserDataTrendLine(echartsCopy);
				$(".xq-box").hide();//隐藏详情框
			}
			
			var trendline;
			function  loadNewUserDataTrendLine(echarts){
				var startDate =$("#dateStart").val();
				var endDate =$("#dateEnd").val();
				
				$.ajax({ 
					url: "${path}/game/newuserals/chart.do",
								data : {
									startDate:startDate,
									endDate:endDate,
									platformId:$("#platformId").val(),
									deviceTypes:getDeviceType()
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
											}, {
												name : '付费人数',
												textStyle : {
													fontSize : 12,
													color : '#666'
												},
												icon : 'roundRect'
											}, {
												name : '付费次数',
												textStyle : {
													fontSize : 12,
													color : '#666'
												},
												icon : 'roundRect'
											}, {
												name : '付费金额（￥）',
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
													name : '新增玩家',
													type : 'line',
													data : json.registerCountList
												},
												{
													name : '活跃玩家',
													type : 'line',
													data : json.activeCountList
												},
												{
													name : '付费人数',
													type : 'line',
													data : json.payCountList
												},
												{
													name : '付费次数',
													type : 'line',
													data :json.payTimesList
												},
												{
													name : '付费金额（￥）',
													type : 'line',
													data :json.payAmountList
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
       	 echarts: '${path}/js/echart/dist'
			}
	});

	// 使用
	require([ 'echarts', 'echarts/chart/line' ],
		function(ec) {
			echartsCopy = ec;
			search();
			hideDetailTableClick();
	});
	</script>
</body>
</html>
