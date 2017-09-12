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
	href="${path}/css/admin-trend.css" />

<script src="${path}/js/laydate/laydate.js"></script>
<link href="${path}/js/myPagination/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${path}/js/myPagination/js/myPagination/page.css" />

<script src="${path}/js/myPagination/js/myPagination/jquery.myPagination6.0.js" type="text/javascript"></script>
<script src="${path}/js/layer/layer.js"></script>
<script src="${path}/js/tool.js"></script>
<script src="${path}/js/datecontrol.js"></script>
<link type="text/css" rel="stylesheet" href="${path}/css/jquery-ui.css" />
<script src="${path}/js/jquery-ui.js"></script>
</head>
<body>
	<div class="sdk-content">
		<ul class="breadcrumb">
			<li>您当前的位置：</li>
			<tags:breadcrumb />
			<li>渠道充值消费日报表</li>
		</ul>
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingchannelName">
				<h4 class="panel-title" id="-collapsible-group-item-#1-">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapsechannelName" aria-expanded="true"
						aria-controls="collapsechannelName" class=""> 渠道充值消费日报表 </a>
				</h4>
			</div>
			<input type="hidden" id="countryId" value="${countryId}">
			<div id="collapsechannelName" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingchannelName" aria-expanded="true">
					<div class="panel-body">
						<div class="admin-content partner-container">
								<div class="section-box">
									<div class="titleDiv" style="height:25px;">
										<div style="display: inline;width:500px;text-align: center;"></div>
									    <div class="select-fr" style="padding-right: 30px;float:left;">
									    	<table style="margin-left:5px;">
									    		<tr>
									    			<td class="tb1Td"><span class="span">渠道ID：</span></td>
									    			<td><input id="channelId" class="input" readonly="true" value="${param.channelId }"></td>
													<td class="tb1Td"><span class="span">月份：</span></td>
									    			<td><input id="rechargeMonth" class="input" readonly="true" value="${param.rechargeMonth }"></td>
													<td >
									    			<span class="span">查询时间：</span>
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
													<div style="padding-bottom:35px;" class="ui-widget-content resize">
														<div class="sortHandle div-sum">	
														</div>
														<div class="tablePanel">
															<table class="tableList1">
																<thead>
																	<tr>
																		<th>统计日期</th>
																		<th>渠道</th>
																		<!-- <th>下载量</th> -->
																		<th>注册用户数</th>
																		<th>激活用户数</th>
																		
																		<th>充值用户数</th>
																		<th>充值金额</th>
																		<th>德州投注用户数</th>
																		<th>德州投注金额</th>
																		<th>电竞投注用户数</th>
																		<th>电竞投注金额</th>
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
			
			$(function(){setDateRangeConfig("dateStart","dateEnd",null,true);
				//setDateType2Change("dateType","dateStart","dateEnd",dateValue.dateStart,dateValue.dateEnd,search);
				search();
				bindEnterEventOfAllEle();
				$( "#sortable" ).sortable({cursor: "move",handle: ".sortHandle"});
				$( ".resize" ).resizable({containment: ".admin-content",minHeight: 600,minWidth: 600});
			});
			
			var bindEnterEventOfAllEle = function(){
				bindEnterEvent("dateStart",search);
				bindEnterEvent("dateEnd",search);
				//bindEnterEvent("channelName",search);
			}
			
	   		var pageSize = 10;
			var addTbRow = function(data,isTotalRow){
				if(null != data && undefined != data && "" != data){
					var ele="<tr><td>" + data.rechargeDay + "</td><td>" + data.channelId + "</td><td>" + data.registUsers + "</td><td>" + data.actionUsers + "</td><td>" + data.rechargeUsers + "</td><td>" + data.rechargeAmount + "</td><td>" + data.dzBetingUsers + "</td><td>" + data.dzBetingAccSum + "</td><td>" + data.djBetingUsers + "</td><td>" + data.djBetingAccSum + "</td>";
					$("#data").append(ele);
				}
			}
			
			
			//显示统计列表
			var showData = function(pageNumber,pageSize){
				$("#data").empty();
				$.post("${path}/game/lawyerUserStatistics/listDay.do",{
					rechargeMonth:$("#rechargeMonth").val(),
					dateStart:$("#dateStart").val(),
					dateEnd:$("#dateEnd").val(),
					channelId:$("#channelId").val(),
					//channelName:$("#channelName").val(),
					pageNumber:pageNumber,
					pageSize:pageSize
				},function(data){
					var json = JSON.parse(data);
					if(null != json && undefined != json){
						$("#totalCount").html(json.total);
						$("#totalPage").html(json.pages);
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
							$("#data").append("<tr><td colspan=\"9\">没有数据</td></tr>");
						}else{
							for(var i=0;i < infoData.length;i++){
								var ele = {
										rechargeDay:infoData[i].rechargeDay,
										channelId:infoData[i].channelId,
										downloadTimes:infoData[i].downloadTimes,
										registUsers:infoData[i].registUsers,
										actionUsers:infoData[i].actionUsers,
										rechargeUsers:infoData[i].rechargeUsers,
										rechargeAmount:infoData[i].rechargeAmount,
										dzBetingUsers:infoData[i].dzBetingUsers,
										dzBetingAccSum:infoData[i].dzBetingAccSum,
										djBetingUsers:infoData[i].djBetingUsers,
										djBetingAccSum:infoData[i].djBetingAccSum
								}
								addTbRow(ele);
							}
						}
					}else{
						$("#data").append("<tr><td colspan=\"9\">没有数据</td></tr>");
					}
				});
			}
			
			var exportToExcel = function(){
			var index = layer.load();
			$.ajax({
			    url: "${path}/game/lawyerUserStatistics/exportDay.do",
			    type: "POST",
			    data: {
			    	rechargeMonth:$("#rechargeMonth").val(),
					dateStart:$("#dateStart").val(),
					dateEnd:$("#dateEnd").val(),
					channelId:$("#channelId").val()
				},
			    success:function(response, status, request){
			    	layer.close(index);
			    	if(!response.canExport){
			    		layer.msg("当前查询条件数据量过大，请缩小查询条件再导出！", {icon: 5}); 
			    		return;
			    	}
			        window.location = "${path}/game/lawyerUserStatistics/downloadDay.do?uuid=" + response.uuid;
		    }});
		}

			//重置
			var reset = function(){
				$("#startDay").val("");
				search();
			}
			
			var search = function(){
				pageSize = $("#pageSize").val() * 1;
				showData(1,pageSize);
			}
		</script>
</html>
