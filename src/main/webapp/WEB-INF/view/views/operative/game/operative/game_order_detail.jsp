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
									    			<td class="tb1Td"><span class="span">订单号：</span></td>
									    			<td><input id="paymentDrderno" class="input"></td>
									    			<td class="tb1Td"><span class="span">渠道ID：</span></td>
									    			<td><input id="channelId" class="input"></td>
									    			<td class="tb1Td"><span class="span">用户ID：</span></td>
									    			<td><input id="userId1" class="input"></td>
									    			<td class="tb1Td">产品：</td>
											    	<td>
											    		<select id="platformId"  class="select" style="width:100px;">
			                                        		<option value="-999">全部产品</option>
			                                            	<c:forEach items="${produces}" var="produce">
			                                            		<option value="${produce.produceName}">${produce.produceName}</option>
			                                            	</c:forEach>
			                                        	</select>
			                                        </td>
			                                        <td class="tb1Td">订单状态：</td>
											    	<td>
											    		<select id="payStatus"  class="select" style="width:100px;">
			                                        		<option value="-999">全部状态</option>
			                                            	<c:forEach items="${states}" var="state">
			                                            		<option value="${state.statusName}">${state.statusName}</option>
			                                            	</c:forEach>
			                                        	</select>
			                                        </td>
			                                        
			                                        <td >
									    			<span class="span">下单时间：</span>
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
													<div style="padding-bottom:35px;" class="ui-widget-content resize">
														<div class="sortHandle div-sum">	
														</div>
														<div class="tablePanel">
															<table class="tableList1">
																<thead>
																	<tr>
																		<th>订单号</th>
																		<th>订单类型</th>
																		<th>产品</th>
																		<th>服务器</th>
																		<th>订单金额</th>
																		<th>下单时间</th>
																		<th>充值时间</th>
																		<th>订单状态</th>
																		<th>充值方式</th>
																		<th>用户ID</th>
																		<th>所属渠道ID</th>
																		<th>交易平台</th>
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
				search();
				bindEnterEventOfAllEle();
				$( "#sortable" ).sortable({cursor: "move",handle: ".sortHandle"});
				$( ".resize" ).resizable({containment: ".admin-content",minHeight: 600,minWidth: 600});
			});
			var bindEnterEventOfAllEle = function(){
				bindEnterEvent("paymentDrderno",search);
				bindEnterEvent("channelId",search);
				bindEnterEvent("platformId",search);
				bindEnterEvent("payStatus",search);
				bindEnterEvent("userId1",search);
			}
			
	   		var pageSize = 10;
			var addTbRow = function(data,isTotalRow){
				if(null != data && undefined != data && "" != data){
					
					var ele = "<tr><td>" + data.paymentDrderno + "</td><td>" + data.orderType
					+ "</td><td>" + data.platformId + "</td><td>"
					+ data.serverId + "</td><td>" + data.amount
					+ "</td><td>" + data.createTime + "</td><td>"
					+ data.payTime + "</td><td>" + data.payStatus
					+ "</td><td>" + data.rechargeWay + "</td><td>"
					+ data.userId + "</td><td>" + data.channelId
					+ "</td><td>" + data.name + "</td>";
					$("#data").append(ele);
		}
	}

	//显示统计列表
	var showData = function(pageNumber, pageSize) {
		$("#data").empty();
		$.post("${path}/game/operative/gameOrderDetail/list.do", {
			paymentDrderno : $("#paymentDrderno").val(),
			channelId : $("#channelId").val(),
			userId1 : $("#userId1").val(),
			platformId:$("#platformId").val() == -999 ? "" : $("#platformId").val(),
			payStatus:$("#payStatus").val() == -999 ? "" : $("#payStatus").val(),
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
							paymentDrderno : infoData[i].paymentDrderno,
							orderType : infoData[i].orderType,
							platformId : infoData[i].platformId,
							serverId : infoData[i].serverId,
							amount : infoData[i].amount,
							createTime : infoData[i].createTime,
							payTime : infoData[i].payTime,
							payStatus : infoData[i].payStatus,
							rechargeWay : infoData[i].rechargeWay,
							userId : infoData[i].userId,
							channelId : infoData[i].channelId,
							name : infoData[i].name
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
			url : "${path}/game/operative/gameOrderDetail/export.do",
			type : "POST",
			data : {
				paymentDrderno : $("#paymentDrderno").val(),
				channelId : $("#channelId").val(),
				userId1 : $("#userId1").val(),
				platformId:$("#platformId").val() == -999 ? "" : $("#platformId").val(),
				payStatus:$("#payStatus").val() == -999 ? "" : $("#payStatus").val(),
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
				window.location = "${path}/game/operative/gameOrderDetail/download.do?uuid="
						+ response.uuid;
			}
		});
	}

	//重置
	var reset = function() {
		$("#paymentDrderno").val("");
		$("#channelId").val("");
		$("#userId1").val("");
		$("#dateStart").val("");
		$("#dateEnd").val("");
		$("#platformId").val("-999");
		$("#payStatus").val("-999");
		search();
	}

	var search = function() {
		pageSize = $("#pageSize").val() * 1;
		showData(1, pageSize);
	}
</script>
</html>
