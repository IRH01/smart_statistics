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
	href="<%=request.getContextPath()%>/css/admin-trend.css" />
<script src="<%=request.getContextPath()%>/js/laydate/laydate.js"></script>
<link href="<%=request.getContextPath()%>/js/myPagination/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/myPagination/js/msgbox/msgbox.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/js/myPagination/js/myPagination/page.css" />

<script src="<%=request.getContextPath()%>/js/myPagination/js/myPagination/jquery.myPagination6.0.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/layer/layer.js"></script>
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
			<div class="panel-heading" role="tab" id="headingpartnerNo">
				<h4 class="panel-title" id="-collapsible-group-item-#1-">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapsepartnerNo" aria-expanded="true"
						aria-controls="collapsepartnerNo" class=""> 统计图 </a>
				</h4>
			</div>
			<div id="collapsepartnerNo" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingpartnerNo" aria-expanded="true">
					<div class="panel-body">
						<div class="admin-content partner-container">
								<div class="section-box">
									<div class="titleDiv" style="height:25px;">
										<div style="display: inline;width:500px;text-align: center;"></div>
									    <div class="select-fr" style="padding-right: 30px;float:left;">
									    	<table style="margin-left:5px;">
									    		<tr>
									    			<td class="tb1Td"><span class="span">代理账号：</span></td>
									    			<td><input id="userId" class="input"></td>
									    			<td class="tb1Td"><span class="span">代理姓名：</span></td>
									    			<td><input id="name" class="input"></td>
									    			<td class="tb1Td">
									    				<span class="span">日期：
														</span>
													</td>
													<td><input placeholder="请选择日期" class="laydate-icon" style="height:25px;" id="dateStart" readonly> </td>
													<td>
														<span class="span">&nbsp;-&nbsp;</span>
													</td>
													<td><input placeholder="请选择日期" class="laydate-icon" style="height:25px;" id="dateEnd" readonly>
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
																		<th style="max-width:200px;">来源网址</th>
																		<th style="max-width:200px;">推广链接</th>
																		<th>推广编码</th>
																		<th>代理姓名</th>
																		<th>代理账号</th>
																		<th>PV<img class="order dailyDataOrder" src="${path}/img/arrow_down.png" onclick="orderChange(this);" order=1 name="pv_count"/></th>
																		<th>UV<img class="order dailyDataOrder" src="${path}/img/arrow_disable.png" onclick="orderChange(this);" order=0 name="uv_count"/></th>
																		<th>IP<img class="order dailyDataOrder" src="${path}/img/arrow_disable.png" onclick="orderChange(this);" order=0 name="ip_count"/></th>
																		<th>跳出率</th>
																		<th>平均访问时长</th>
																		<th>注册用户数</th>
																		<th>付费用户数</th>
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
				setDateRangeConfig("dateStart","dateEnd",null,true,false);
				search();
				bindEnterEventOfAllEle();
				$( ".resize" ).resizable({containment: ".admin-content",minHeight: 600,minWidth: 600});
			});
			var bindEnterEventOfAllEle = function(){
				bindEnterEvent("userId",search);
				bindEnterEvent("name",search);
			}
	   		var pageSize = 10;
			var addTbRow = function(data,isTotalRow){
				if(null != data && undefined != data && "" != data){
					var referrerWebsiteUrlTitle = data.referrerWebsiteUrl;
					var referrerWebsiteUrl = data.referrerWebsiteUrl;
					if(data.referrerWebsiteUrl != undefined && data.referrerWebsiteUrl != null && 50 < data.referrerWebsiteUrl.length){
						referrerWebsiteUrl = data.referrerWebsiteUrl.substring(0,50) + "..";
					}
					var ele="<tr><td class='channel' title='referrerWebsiteUrlTitle'>referrerWebsiteUrl</td><td>promoteUrl</td><td>partnerNo</td><td>realName</td><td>userId</td><td>uvCount</td><td>pvCount</td><td>ipCount</td><td>outRate</td><td>avgDuration</td><td>registCount</td><td>payCount</td></tr>";
					ele = ele.replace("referrerWebsiteUrlTitle",referrerWebsiteUrlTitle).replace("referrerWebsiteUrl",referrerWebsiteUrl).replace("promoteUrl",data.promoteUrl).replace("partnerNo",data.partnerNo).replace("realName",data.realName).replace("userId",data.userId).replace("pvCount",data.pvCount).replace("uvCount",data.uvCount).replace("ipCount",data.ipCount).replace("outRate",data.outRate).replace("avgDuration",data.avgDuration).replace("registCount",data.registCount).replace("payCount",data.payCount);
					$("#data").append(ele);
				}
			}	
			
			//显示统计列表
			var showData = function(pageNumber,pageSize){
				$("#data").empty();
				var orderInfo = getOrder("order");
				$.post("${path}/partner/ptnsitesum/list.do",{
					userId:$("#userId").val(),
					name:$("#name").val(),
					dateStart:$("#dateStart").val(),
					dateEnd:$("#dateEnd").val(),
					pageNumber:pageNumber,
					pageSize:pageSize,
					order:orderInfo.order,
					orderField:orderInfo.orderField
				},function(data){
					layer.close(layerIndex);
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
							$("#data").append("<tr><td colspan=\"12\">没有数据</td></tr>");
						}else{
							for(var i=0;i < infoData.length;i++){
								var ele = {
										referrerWebsiteUrl:"EMPTY" == infoData[i].referrerWebsiteUrl ? "直接访问" : infoData[i].referrerWebsiteUrl,
										promoteUrl:infoData[i].promoteUrl,
										partnerNo:infoData[i].partnerNo,
										realName:infoData[i].realName,
										userId:infoData[i].userId,
										pvCount:infoData[i].pvCount,
										uvCount:infoData[i].uvCount,
										ipCount:infoData[i].ipCount,
										outRate:convertToPercentFormat(infoData[i].outRate.toFixed(2)),
										avgDuration:infoData[i].avgDuration,
										registCount:infoData[i].registCount,
										payCount:infoData[i].payCount
								}
								addTbRow(ele);
							}
						}
					}else{
						$("#data").append("<tr><td colspan=\"12\">没有数据</td></tr>");
					}
				});
			}
			
			var orderChange = function(value){
				var tempValue = $(value).attr("order");
				resetOrder("order");
				value.order = tempValue;
				if("1" == value.order){
					$(value).attr("order",2);
					value.src = "${path}/img/arrow_up.png";
				}else if("2" == value.order){
					$(value).attr("order",0);
					value.src = "${path}/img/arrow_disable.png";
				}else if("0" == value.order){
					$(value).attr("order",1);
					value.src = "${path}/img/arrow_down.png";
				}
				search();
			}
			

			//重置
			var reset = function(){
				$("#userId").val("");
				$("#name").val("");
				$("#dateStart").val("");
				$("#dateEnd").val("");
				search();
			}
			
			//查询显示
			var layerIndex;
			var search = function(){
				layerIndex = layer.load(0, {shade:[0.1,'#fff']}); //0代表加载的风格，支持0-2
				pageSize = $("#pageSize").val() * 1;
				showData(1,pageSize);
			}
		</script>
</body>
</html>
