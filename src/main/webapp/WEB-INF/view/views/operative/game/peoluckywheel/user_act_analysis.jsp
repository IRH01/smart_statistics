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
<link rel="stylesheet" type="text/css" href="${path}/js/myPagination/js/myPagination/page.css" />
<script src="${path}/js/myPagination/js/myPagination/jquery.myPagination6.0.js" type="text/javascript"></script>
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
						aria-controls="collapseOne" class=""> 统计图 </a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
					<div class="domainDiv">
						<label class="control-label" for="" style="font-size: 16px;float:left;line-height:2;">域名：</label>
						<select id="domain" class="form-control input-sm" onchange="searchOnDomainChange()"
							style="display: initial; width: inherit;float:left;">
							<c:forEach items="${domains}" var="item">
								<option value="${item.tblId}">${item.domainName}</option>
							</c:forEach>
						</select>
						<label class="control-label" for="" style="font-size: 16px;float:left;padding-left:15px;line-height:2;">渠道：</label>
						<select id="channel" class="form-control input-sm" onchange="search()"
							style="display: initial; width: inherit;float:left;">
						</select>
						<button type="button" id="search" class="btn btn-primary btn-sm"
							onclick="search();">
							<i class="icon-search icon-white"></i>&nbsp;查&nbsp;&nbsp;询&nbsp;
						</button>
					</div>
					<div class="panel-body">
						<div class="admin-content">
								<div class="section-box">
									<div class="titleDiv">
									         <div style="display: inline;width:500px;text-align: center;">
											 </div>
										    <div class="select-fr" style="padding-right: 30px;">
												<span class="laydateBox"> 日期：<input value="选择日期"
													class="laydate-icon" id="dateStart"> 至 <input
													class="laydate-icon" id="dateEnd" value="选择日期">
												</span>
											    <!--选择类别-->
												<span id="type">
												&nbsp;&nbsp;
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
										<ul class="tabTit titslt">
											<c:forEach items="${gamePages}" var="item">
												<li style="width: 80px;" id="${item.tblId}">${item.gameName}</li>
											</c:forEach>
										</ul>
									</div>

									<div class="whiteDiv tab-content">
										<ul id="sortable" class="ui-widget-content sortable">
											<li class="ui-state-default">
												<div style="padding-bottom:60px;" class="ui-widget-content resize">
													<div class="sortHandle">列表</div>
													<div class="tablePanel">
														<table class="tableList1">
															<colgroup>
																<col width="25%" />
																<col width="25%" />
																<col width="25%" />
																<col width="25%" />
															</colgroup>
															<thead>
																<tr>
																	<th>日期</th>
																	<th>浏览量（PV）</th>
																	<th>访客数（UV）</th>
																	<th>平均访问时长</th>
																</tr>
															</thead>
															<tbody id="data"">
															</tbody>
														</table>
													</div>
													<div id="page"></div>
												</div>
											</li>
											<li class="ui-state-default">
	 											<div  class="ui-widget-content resize" style="height:300px;">
	 												<div class="sortHandle">趋势图</div>
	 												<div>
														<input type="radio" id="typePVCnt" name="type" onchange="radioChange();" checked>浏览量（PV）</input>
														<input type="radio" id="typeUVCnt" name="type" onchange="radioChange();">访客数（UV）</input>
														<input type="radio" id="typeAvgStayCnt" name="type" onchange="radioChange();">平均访问时长</input>
													</div>
													<div id="trendline" class="trendline"></div> 
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
			var gameId = "0";
			$(function(){
				initGamePages();
				$(".tabTit li").click(function(){
					tabIndex=$(".tabTit li").index(this);
					gameId = $(this).attr("id");
					$(this).addClass("cur").siblings().removeClass("cur");
					search();
				});
				$( "#sortable" ).sortable({cursor: "move",handle: ".sortHandle"});
				$( ".resize" ).resizable({minHeight: 300,minWidth: 300});
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
	    	
			var initGamePages = function(){
				if(undefined = $(".tabTit li:first") && null != $(".tabTit li:first")){
					$(".tabTit li:first").addClass("cur");
					gameId = $(".tabTit li:first").attr("id");
				}
			}
			
			var searchOnDomainChange = function(){
				$.post("${path}/game/peoluckywheel/useractals/getChannels.do",{
					domainId:$("#domain").val()
				},function(data){
					$("#channel").empty();
					var json = JSON.parse(data);
					if(undefined != json && undefined != json.length){
						for(var i = 0;i < json.length;i++){
							$("#channel").append("<option value="+ json[i].tblId + ">" + json[i].channelName + "</option>");
						}
					}
					search();
				});
			}
			
			var secondsToMinute = function(value) {
				debugger
	   			var result = 0;
	   			result = value/60;
	   			result = result.toFixed(2);
	   			return result;
	   		} 
			
	    	//获取当前月天数
	    	var getCurMonthDayCnt = function(){
	    		var now = new Date();
	    		var date = new Date(now.getYear(),now.getMonth() + 1,0);
	    		return date.getDate();
	    	}
	   		
			var addTbRow = function(data){
				if(null != data && undefined != data && "" != data){
					var ele="<tr><td class=\"date\">etlDate</td><td>pvCnt</td><td>uvCnt</td><td>avgStayCnt</td></tr>";
					ele = ele.replace("etlDate",data.etlDate).replace("pvCnt",data.pvCnt).replace("uvCnt",data.uvCnt).replace("avgStayCnt", data.avgStayCnt);
					$("#data").append(ele);
				}
			}
			
			//显示统计列表
			var showData = function(pageNumber,pageSize,gameId){
				$("#data").empty();
				$.post("${path}/game/peoluckywheel/useractals/list.do",{
					startDate:$("#dateStart").val(),
					endDate:$("#dateEnd").val(),
					domainId:$("#domain").val(),
					channelId:$("#channel").val(),
					gameId:gameId,
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
				                    	showData(page,pageSize,gameId);
				                    }  
				        		}
					        });
						var infoData = json.list;
						if(null == infoData || undefined == infoData || 0 >= infoData.length){
							$("#data").append("<tr><td colspan=\"6\">没有数据</td></tr>");
						}else{
							for(var i=0;i < infoData.length;i++){
								var ele = {
										etlDate:infoData[i].etlDate,
										uvCnt:infoData[i].uvCnt,
										pvCnt:infoData[i].pvCnt,
										avgStayCnt:formatSeconds(infoData[i].avgStayCnt)
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
				showData(1,pageSize,gameId);
				loadTrendLine(echartsCopy,gameId);
			}
			var radioChange = function(){
				loadTrendLine(echartsCopy,gameId);
			}
			
			function formartAvgStayCnt(data){
				if(undefined != data && undefined != data.length){
					for(var i = 0;i < data.length; i++){
						data[i] = secondsToMinute(data[i]);
					}
				}
				return data;
			}
			
			function getData(data){
				if($("#typeAvgStayCnt").prop("checked") == true){//平均停留时长
					return formartAvgStayCnt(data.avgStayCntList);
				}else if($("#typePVCnt").prop("checked") == true){//Pv数量
					return data.pvCntList;
				}else{//付费次数
					return data.uvCntList;
				}
			}
			
			function getSeriesName(){
				if($("#typeAvgStayCnt").prop("checked") == true){//平均停留时长
					return "平均访问时长";
				}else if($("#typePVCnt").prop("checked") == true){//Pv数量
					return "浏览量（PV）";
				}else{//付费次数
					return "访客数（UV）";
				}
			}
			
			function getToolTip(){
				if($("#typeAvgStayCnt").prop("checked") == true){//平均停留时长
					return "{b}<br/>{a0}:{c0}m";
				}else{//付费次数
					return "{b}<br/>{a0}:{c0}";
				}
			}
			
			function getYAxisLabelFormart(){
				if($("#typeAvgStayCnt").prop("checked") == true){//平均停留时长单位为m
					return "{value}m";
				}else{
					return "{value}";
				}
			}
			
			//加载付费人数折线图
			function loadTrendLine(echarts,gameId){
				var startDate =$("#dateStart").val();
				var endDate =$("#dateEnd").val();
				
				$.ajax({ 
					url: "${path}/game/peoluckywheel/useractals/chart.do",
								data : {
									startDate:$("#dateStart").val(),
									endDate:$("#dateEnd").val(),
									domainId:$("#domain").val(),
									channelId:$("#channel").val(),
									gameId:gameId,
									startDate:startDate,
									endDate:endDate
								},
								success : function(data) {
									var json = JSON.parse(data);
									showTrendLineData(echarts,json,gameId);
								}
							});
			}
			
			var trendline;
			function showTrendLineData(echarts,json){
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
						trigger : 'axis',
						formatter :  getToolTip()
					},
					color : [ '#2ec7c9', '#b6a2de', '#5ab1ef',
							'#ffb980', '#d87a80' ],
					legend : {
						show : true,
						orient : 'horizontal',
						data : [ {
							name : getSeriesName(),
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
				                formatter: getYAxisLabelFormart(),
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
								name : getSeriesName(),
								type : 'line',
								data : getData(json)
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
					searchOnDomainChange();
			});
		</script>
</body>
</html>
