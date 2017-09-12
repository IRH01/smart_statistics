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

<script src="<%=request.getContextPath()%>/js/myPagination/js/myPagination/jquery.myPagination6.0.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/tool.js"></script>
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
			<div class="panel-heading" role="tab" id="headingchannelName">
				<h4 class="panel-title" id="-collapsible-group-item-#1-">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapsechannelName" aria-expanded="true"
						aria-controls="collapsechannelName" class=""> 统计图 </a>
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
									    			<td><input id="channelId" class="input"></td>
									    			<td class="tb1Td"><span class="span">渠道名称：</span></td>
									    			<td><input id="channelName" class="input"></td>
									    			<td class="tb1Td">
									    				<span class="span">日期：
														</span>
													</td>
													<td><input placeholder="请选择日期" class="laydate-icon" style="height:25px;" id="dateStart" readonly> </td>
													<td>
														<span class="span">&nbsp;-&nbsp;</span>
													</td>
													<td><input placeholder="请选择日期" class="laydate-icon" style="height:25px;" id="dateEnd" readonly> </td>
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
																		<th>日期</th>
																		<th>渠道ID</th>
																		<th>渠道名称</th>
																		<th>游戏名称</th>
																		<th>注册会员数</th>
																		<th>付费会员数</th>
																		<th>会员充值总额</th>
																		<th>游戏消耗金额</th>
																		<th>游戏账号余额</th>
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
				bindEnterEvent("channelName",search);
			}
			
	   		var pageSize = 10;
			var addTbRow = function(data,isTotalRow){
				if(null != data && undefined != data && "" != data){
					var ele="<tr><td>statDate</td><td>channelId</td><td>channelName</td><td>platformName</td><td>regUCnt</td><td>payUCnt</td><td>payAmount</a></td><td>consumeAmount</td><td>accountBalance</td></tr>";
					ele = ele.replace("statDate",data.statDate).replace("channelId",data.channelId).replace("channelName",data.channelName).replace("platformName",data.platformName).replace("payAmount",data.payAmount).replace("payUCnt",data.payUCnt).replace("regUCnt",data.regUCnt).replace("accountBalance",data.accountBalance).replace("consumeAmount",data.consumeAmount).replace("consumeAmount",data.consumeAmount);
					$("#data").append(ele);
				}
			}
			
			
			//显示统计列表
			var showData = function(pageNumber,pageSize){
				$("#data").empty();
				$.post("${path}/game/operative/cprtchnstat/list.do",{
					dateStart:$("#dateStart").val(),
					dateEnd:$("#dateEnd").val(),
					channelId:$("#channelId").val(),
					channelName:$("#channelName").val(),
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
										statDate:formatDay(infoData[i].statDate),
										channelId:infoData[i].channelId,
										channelName:infoData[i].channelName,
										platformName:infoData[i].platformName,
										regUCnt:infoData[i].regUCnt,
										payUCnt:infoData[i].payUCnt,
										payAmount:infoData[i].payAmount.toFixed(2),
										consumeAmount:infoData[i].consumeAmount.toFixed(2),
										accountBalance:infoData[i].accountBalance.toFixed(2)
								}
								addTbRow(ele);
							}
						}
					}else{
						$("#data").append("<tr><td colspan=\"9\">没有数据</td></tr>");
					}
				});
			}
			
			

			//重置
			var reset = function(){
				$("#channelId").val("");
				$("#channelName").val("");
				$("#dateStart").val("");
				$("#dateEnd").val("");
				search();
			}
			
			var search = function(){
				pageSize = $("#pageSize").val() * 1;
				showData(1,pageSize);
			}
		</script>
</body>
</html>
