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
			<li>  渠道详情  </li>
		</ul>
		<div class="panel panel-default">
			<div class="panel-heading" role="tab" id="headingchannelName">
				<h4 class="panel-title" id="-collapsible-group-item-#1-">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapsechannelName" aria-expanded="true"
						aria-controls="collapsechannelName" class="">     </a>
				</h4>
			</div>
			<div id="collapsechannelName" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingchannelName" aria-expanded="true">
					<div class="panel-body">
						<div class="admin-content partner-container">
								<div class="section-box">
									<div class="titleDiv" style="height:60px;">
										<div style="display: inline;width:500px;text-align: center;"></div>
									    <div class="select-fr" style="padding-right: 30px;float:left;">
									    	<table style="margin-left:5px;">
									    		<tr>
									    			<td class="tb1Td"><span class="span">用户名：</span></td>
									    			<td><input id="userName" class="input"></td>
													<td class="tb1Td"><span class="span">平台名称：</span></td>
									    			<td><input id="platformId" class="input"></td>
									    			<td class="tb1Td"><span class="span">支付类型：</span></td>
									    			<td><input id="payType" class="input"></td>
									    			
												</td>
									    		</tr>
									    		
									    		<tr>
									    			<td class="tb1Td"><span class="span">投入金额：</span></td>
									    			<td><input id="costAmountStart" class="input">~<input id="costAmountEnd" class="input"></td>
									    			<td >
									    			<span class="span">统计时间：</span>
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
													<td style="line-height:1;">
														<button type="button" id="search" class="btn btn-primary btn-sm" onclick="search();"
															<i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;width:58px"></i>查询
														</button>
														<button type="button" id="search" class="btn btn-primary btn-sm" onclick="reset();"
															<i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;width:58px"></i>重置
														</button>
														<button type="button" id="export" class="btn btn-primary btn-sm" onclick="exportToExcel();"
															<i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;width:124px"></i>导出为Excel
														</button>
														<button type="button" id="export" class="btn btn-primary btn-sm" onclick="window.history.go(-1);"
															<i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;width:124px"></i>返回
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
																		<th>订单号</th>
																		<th>用户名</th>
																		<th>用户ID</th>
																		<th>投入金额</th>
																		<th>平台名称</th>
																		<th>支付类型</th>
																		<th>时间</th>
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
				bindEnterEvent("userName",search);
				bindEnterEvent("platformId",search);
				bindEnterEvent("payType",search);
				bindEnterEvent("costAmountStart",search);
				bindEnterEvent("costAmountEnd",search);
				bindEnterEvent("dateStart",search);
				bindEnterEvent("dateEnd",search);
			}
			
	   		var pageSize = 10;
			var addTbRow = function(data,isTotalRow){
				if(null != data && undefined != data && "" != data){
					var ele="<tr><td>" + data.orderNo + "</td><td>" + data.userName + "</td><td>" + data.userId + "</td><td>" + data.costAmount + "</td><td>" + data.platformId + "</td><td>" + data.payType + "</td><td>" + data.costTime + "</td>";
					$("#data").append(ele);
				}
			}
			
			//显示统计列表
			var showData = function(pageNumber,pageSize){
				$("#data").empty();
				$.post("${path}/agent/channel/listChannelCostDetails.do",{
					promotNo:${param.promotNo},
					accountNo:${param.accountNo},
					channelId:${param.channelId},
					userName:$("#userName").val(),
					platformId:$("#platformId").val(),
					payType:$("#payType").val(),
					costAmountStart:$("#costAmountStart").val(),
					costAmountEnd:$("#costAmountEnd").val(),
					dateStart:$("#dateStart").val(),
					dateEnd:$("#dateEnd").val(),
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
							$("#data").append("<tr><td colspan=\"7\">没有数据</td></tr>");
						}else{
							for(var i=0;i < infoData.length;i++){
								var ele = {
										orderNo:infoData[i].orderNo,
										userName:infoData[i].userName,
										userId:infoData[i].userId,
										costAmount:infoData[i].costAmount,
										platformId:infoData[i].platformId,
										payType:infoData[i].payType,
										costTime:infoData[i].costTime
								}
								addTbRow(ele);
							}
						}
					}else{
						$("#data").append("<tr><td colspan=\"7\">没有数据</td></tr>");
					}
				});
			}
			
			var exportToExcel = function(){
				var index = layer.load();
				$.ajax({
				    url: "${path}/agent/channel/exportCost.do",
				    type: "POST",
				    data: {
				    	promotNo:${param.promotNo},
						accountNo:${param.accountNo},
						channelId:${param.channelId},
						userName:$("#userName").val(),
						platformId:$("#platformId").val(),
						payType:$("#payType").val(),
						costAmountStart:$("#costAmountStart").val(),
						costAmountEnd:$("#costAmountEnd").val(),
						dateStart:$("#dateStart").val(),
						dateEnd:$("#dateEnd").val()
					},
				    success:function(response, status, request){
				    	layer.close(index);
				    	if(!response.canExport){
				    		layer.msg("当前查询条件数据量过大，请缩小查询条件再导出！", {icon: 5}); 
				    		return;
				    	} 
				        window.location = "${path}/agent/channel/downloadCost.do?uuid=" + response.uuid;
			    }});
			}
			
			//重置
			var reset = function(){
				$("#userName").val("");
				$("#platformId").val("");
				$("#payType").val("");
				$("#costAmountStart").val("");
				$("#costAmountEnd").val("");
				$("#dateStart").val("");
				$("#dateEnd").val("");
				search();
			}
			
			var search = function(){
				pageSize = $("#pageSize").val() * 1;
				showData(1,pageSize);
			}
		</script>
</html>
