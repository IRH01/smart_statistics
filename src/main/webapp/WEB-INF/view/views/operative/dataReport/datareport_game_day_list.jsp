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
										<div style="display: inline;width:500px;text-align: center;"></div>
									    <div class="select-fr" style="padding-right: 30px;float:left;">
									    	<table style="margin-left:5px;">
									    		<tr>
									    			<td class="tb1Td"><span class="span">游戏名称：</span></td>
									    			<td><input id="gameName" class="input" readonly="true" value=""></td>
									    			<td class="tb1Td"><span class="span">开始日期：</span></td>
									    			<td><input id="dateStart" class="input" readonly="true" value="${param.dateStart }"></td>
									    			<td class="tb1Td"><span class="span">结束日期：</span></td>
									    			<td><input id="dateEnd" class="input" readonly="true" value="${param.dateEnd }"></td>
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
																		<th>游戏名称</th>
																		<th>游戏充值</th>
																		<th>乐盈币充值</th>
																		<th>游戏划账</th>
																		<th>总充值</th>
																		<th>充值人数</th>
																		<th>充值次数</th>
																		<th>新增注册</th>
																		<th>ARPU</th>
																		<th>DAU</th>
																		<th>WAU</th>
																		<th>MAU</th>
																		<th>留存率</th>
																		<th>付费率</th>
																		<th>留存用户</th>
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
			$("#gameName").val(decodeURI("${param.gameName }"));
			$(function(){
				//起始时间根据：上线时间变化
				//setDateRangeConfig("dateStart","dateEnd",null,true,false);
				search();
				bindEnterEventOfAllEle();
				$( "#sortable" ).sortable({cursor: "move",handle: ".sortHandle"});
				$( ".resize" ).resizable({containment: ".admin-content",minHeight: 600,minWidth: 600});
			});
			var bindEnterEventOfAllEle = function(){
				bindEnterEvent("gameName",search);
				bindEnterEvent("dateStart",search);
				bindEnterEvent("dateEnd",search);
			}
			
	   		var pageSize = 10;
			var addTbRow = function(data,isTotalRow){
				if(null != data && undefined != data && "" != data){
					
	var ele = "<tr><td>" + data.dataDate + "</td><td>" + data.gameName
					+ "</td><td>" + data.gameRecharge + "</td><td>"
					+ data.lybRecharge + "</td><td>" + data.yxExchange
					+ "</td><td>" + data.allRecharge + "</td><td>"
					+ data.rechargePeople + "</td><td>" + data.rechargeTimes
					+ "</td><td>" + data.newRegister + "</td><td>"
					+ data.arpu
					+ "</td><td>"
					+ data.dau
					+ "</td><td>"
					+ data.wau
					+ "</td><td>"
					+ data.mau
					+ "</td><td>"
					+ data.remainRate
					+ "</td><td>"
					+ data.rechargeRate
					+ "</td><td>"
					+ data.remainUsers
					+ "</td>";
			$("#data").append(ele);
		}
	}

	//显示统计列表
	var showData = function(pageNumber, pageSize) {
		$("#data").empty();
		$.post("${path}/dataReport/game/day/daylist.do", {
			gameName : $("#gameName").val(),
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
					$("#data").append("<tr><td colspan=\"9\">没有数据</td></tr>");
				} else {
					for (var i = 0; i < infoData.length; i++) {
						var ele = {
							dataDate : infoData[i].dataDate,
							gameName : infoData[i].gameName,
							gameRecharge : infoData[i].gameRecharge,
							lybRecharge : infoData[i].lybRecharge,
							yxExchange : infoData[i].yxExchange,
							allRecharge : infoData[i].allRecharge,
							rechargePeople : infoData[i].rechargePeople,
							rechargeTimes : infoData[i].rechargeTimes,
							newRegister : infoData[i].newRegister,
							arpu : infoData[i].arpu,
							dau : infoData[i].dau,
							wau : infoData[i].wau,
							mau : infoData[i].mau,
							remainRate : infoData[i].remainRate,
							rechargeRate : infoData[i].rechargeRate,
							remainUsers : infoData[i].remainUsers
						}
						addTbRow(ele);
					}
				}
			} else {
				$("#data").append("<tr><td colspan=\"9\">没有数据</td></tr>");
			}
		});
	}

	//重置
	var reset = function() {
		$("#gameName").val("");
		$("#dateStart").val("");
		$("#dateEnd").val("");;
		search();
	}

	var search = function() {
		pageSize = $("#pageSize").val() * 1;
		showData(1, pageSize);
	}
</script>
</html>
