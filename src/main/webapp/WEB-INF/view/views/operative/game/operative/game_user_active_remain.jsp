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
									    			<td class="tb1Td"><span class="span">渠道ID：</span></td>
									    			<td><input id="channelId" class="input"></td>
									    			
									    			<td class="tb1Td">产品：</td>
											    	<td>
											    		<select id="platformId"  class="select" style="width:100px;">
			                                        		<option value="-999">全部产品</option>
			                                            	<c:forEach items="${produces}" var="produce">
			                                            		<option value="${produce.produceId }">${produce.produceName}</option>
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
													<!-- <td colspan="2" style="line-height:1;">
													<button type="button" id="export" class="btn btn-primary btn-sm" onclick="exportToExcel();"
														<i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;width:124px"></i>导出为Excel
													</button>
												</td> -->
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
																		<th rowspan="2">时间</th>
																		<th rowspan="2">新增访客</th>
																		
																		<th colspan="18">留存用户/留存率</th>
																	</tr><tr>
																		<th bgcolor="#e1e7e9" colspan="2">1天后</th>
																		<th bgcolor="#e1e7e9" colspan="2">2天后</th>
																		<th bgcolor="#e1e7e9" colspan="2">3天后</th>
																		<th bgcolor="#e1e7e9" colspan="2">4天后</th>
																		<th bgcolor="#e1e7e9" colspan="2">5天后</th>
																		<th bgcolor="#e1e7e9" colspan="2">6天后</th>
																		<th bgcolor="#e1e7e9" colspan="2">7天后</th>
																		<th bgcolor="#e1e7e9" colspan="2">14天后</th>
																		<th bgcolor="#e1e7e9" colspan="2">30天后</th>
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
				bindEnterEvent("channelId",search);
				bindEnterEvent("platformId",search);
			}
			
	   		var pageSize = 10;
			var addTbRow = function(data,isTotalRow){
				if(null != data && undefined != data && "" != data){
					
					
					var ele = "<tr><td>" + data.stat_date + "</td><td>" + data.add_uv
					+ "</td><td>" + data.remain_1_ucnt
					+ "</td><td>" + data.remain_1_rate
					+ "</td><td>" + data.remain_2_ucnt
					+ "</td><td>" + data.remain_2_rate
					+ "</td><td>" + data.remain_3_ucnt
					+ "</td><td>" + data.remain_3_rate
					+ "</td><td>" + data.remain_4_ucnt
					+ "</td><td>" + data.remain_4_rate
					+ "</td><td>" + data.remain_5_ucnt
					+ "</td><td>" + data.remain_5_rate
					+ "</td><td>" + data.remain_6_ucnt
					+ "</td><td>" + data.remain_6_rate
					+ "</td><td>" + data.remain_7_ucnt
					+ "</td><td>" + data.remain_7_rate
					+ "</td><td>" + data.remain_14_ucnt
					+ "</td><td>" + data.remain_14_rate
					+ "</td><td>" + data.remain_30_ucnt
					+ "</td><td>" + data.remain_30_rate
					+ "</td>";
			$("#data").append(ele);
		}
	}

	//显示统计列表
	var showData = function(pageNumber, pageSize) {
		$("#data").empty();
		$.post("${path}/game/operative/gameUserActiveRemain/list.do", {
			channelId : $("#channelId").val(),
			platformId : $("#platformId").val() == -999 ? "" : $("#platformId").val(),
			dateStart : $("#dateStart").val(),
			dateEnd : $("#dateEnd").val(),
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
							stat_date : infoData[i].statDate,
							channel_id : infoData[i].channelId,
							platform_id : infoData[i].platformId,
							add_uv : infoData[i].addUv,
							remain_1_ucnt : infoData[i].remain1Ucnt,
							remain_1_rate : infoData[i].remain1Rate,
							remain_2_ucnt : infoData[i].remain2Ucnt,
							remain_2_rate : infoData[i].remain2Rate,
							remain_3_ucnt : infoData[i].remain3Ucnt,
							remain_3_rate : infoData[i].remain3Rate,
							remain_4_ucnt : infoData[i].remain4Ucnt,
							remain_4_rate : infoData[i].remain4Rate,
							remain_5_ucnt : infoData[i].remain5Ucnt,
							remain_5_rate : infoData[i].remain5Rate,
							remain_6_ucnt : infoData[i].remain6Ucnt,
							remain_6_rate : infoData[i].remain6Rate,
							remain_7_ucnt : infoData[i].remain7Ucnt,
							remain_7_rate : infoData[i].remain7Rate,
							remain_14_ucnt : infoData[i].remain14Ucnt,
							remain_14_rate : infoData[i].remain14Rate,
							remain_30_ucnt : infoData[i].remain30Ucnt,
							remain_30_rate : infoData[i].remain30Rate,
							create_time : infoData[i].createTime,
							update_time : infoData[i].updateTime
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
		$
				.ajax({
					url : "${path}/game/operative/gameUserActiveRemain/export.do",
					type : "POST",
					data : {
						channelId : $("#channelId").val(),
						platformId : $("#platformId").val() == -999 ? "" : $("#platformId").val(),
						dateStart : $("#dateStart").val(),
						dateEnd : $("#dateEnd").val()
					},
					success : function(response, status, request) {
						layer.close(index);
						if (!response.canExport) {
							layer.msg("当前查询条件数据量过大，请缩小查询条件再导出！", {
								icon : 5
							});
							return;
						}
						window.location = "${path}/game/operative/gameUserActiveRemain/download.do?uuid="
								+ response.uuid;
					}
				});
	}

	//重置
	var reset = function() {
		$("#channelId").val("");
		$("#dateStart").val("");
		$("#dateEnd").val("");
		$("#platformId").val("-999");
		search();
	}

	var search = function() {
		pageSize = $("#pageSize").val() * 1;
		showData(1, pageSize);
	}
</script>
</html>
