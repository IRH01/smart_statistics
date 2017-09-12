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
									    			<td class="tb1Td">
									    				<span class="span">起止月份：</span>
													</td>
													<td>
														<span class="laydateBox">
															<input type="text" id="monthStart" class="laydate-icon" style="width:140px;" readonly/>
														</span>
													</td>
													<td class="tb1Td">
									    				<span class="span">-</span>
													</td>
													<td>
													    <input type="text" id="monthEnd" class="laydate-icon" style="width:140px;" readonly/>
													</td>
									    			<td class="tb1Td"><span class="span">代理账号：</span></td>
									    			<td><input id="partnerUserId" class="input"></td>
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
													<td  style="line-height:1;">
														<button type="button" id="export" class="btn btn-primary btn-sm" onclick="exportToExcel();"
															<i class="icon-search icon-white" style="height: 24px;padding-top:0px;padding-bottom:0px;width:58px"></i>导出
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
																		<th>月份</th>
																		<th>代理ID</th>
																		<th>代理账号</th>
																		<th>直属会员</th>
																		<th>下级代理</th>
																		<th>直属金额</th>
																		<th>代理金额</th>
																		<th>合计</th>
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
			var setMonthConfig = function(){
				var date=new Date();
				var year=date.getFullYear(); 
				var years = new Array();
				var startYear = 2016;
				for(var i = 0;year >= startYear;year--,i++){
					years[i] = year;
				}
				$("#monthStart").monthpicker({ 
					years: years, 
					topOffset: 6, 
					width:"140px",
					onMonthSelect: function(m, y) { 
						search();//查询
						console.log('Month: ' + m + ', year: ' + y); 
				 }}); 
				$("#monthEnd").monthpicker({ 
					years: years, 
					topOffset: 6, 
					width:"140px",
					onMonthSelect: function(m, y) { 
						search();//查询
				 }}); 
			}
			$(function(){
				setMonthConfig();
				search();
				bindEnterEventOfAllEle();
				$( ".resize" ).resizable({containment: ".admin-content",minHeight: 600,minWidth: 600});
			});
			var bindEnterEventOfAllEle = function(){
				bindEnterEvent("partnerUserId",search);
			}
	   		var pageSize = 10;
			var addTbRow = function(data,isTotalRow){
				if(null != data && undefined != data && "" != data){
					var monthIdTitle = data.monthId;
					var monthId = data.monthId;
					if(data.monthId != undefined && data.monthId != null && 50 < data.monthId.length){
						monthId = data.monthId.substring(0,50) + "..";
					}
					var ele = "";
					if(data.memberAmount > 0){
						ele="<tr><td class='mId'>monthId</td><td>partnerNo</td><td class='pUserId'>partnerUserId</td><td>qualMemberTotalUCnt</td><td>subAgentUCnt</td><td><a onclick='showDetailInfo(this);' class='partner-link'>memberAmount</a></td><td>agentAmount</td><td>totalAmount</td></tr>";
						ele = ele.replace("monthId",monthId).replace("partnerNo",data.partnerNo).replace("partnerUserId",data.partnerUserId).replace("qualMemberTotalUCnt",data.qualMemberTotalUCnt).replace("subAgentUCnt",data.subAgentUCnt).replace("memberAmount",data.memberAmount).replace("agentAmount",data.agentAmount).replace("totalAmount",data.totalAmount);
					}else{
						ele="<tr><td class='mId'>monthId</td><td>partnerNo</td><td class='pUserId'>partnerUserId</td><td>qualMemberTotalUCnt</td><td>subAgentUCnt</td><td>memberAmount</td><td>agentAmount</td><td>totalAmount</td></tr>";
						ele = ele.replace("monthId",monthId).replace("partnerNo",data.partnerNo).replace("partnerUserId",data.partnerUserId).replace("qualMemberTotalUCnt",data.qualMemberTotalUCnt).replace("subAgentUCnt",data.subAgentUCnt).replace("memberAmount",data.memberAmount).replace("agentAmount",data.agentAmount).replace("totalAmount",data.totalAmount);
					}
					$("#data").append(ele);
				}
			}	
			
			//显示统计列表
			var showData = function(pageNumber,pageSize){
				$("#data").empty();
				$.post("${path}/partner/ptnmbblastat/list.do",{
					partnerUserId:$("#partnerUserId").val(),
					monthStart:$("#monthStart").val(),
					monthEnd:$("#monthEnd").val(),
					pageNumber:pageNumber,
					pageSize:pageSize
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
							$("#data").append("<tr><td colspan=\"8\">没有数据</td></tr>");
						}else{
							for(var i=0;i < infoData.length;i++){
								var ele = {
										monthId:formatMonth(infoData[i].monthId),
										partnerNo:infoData[i].partnerNo,
										partnerUserId:infoData[i].partnerUserId,
										qualMemberTotalUCnt:infoData[i].qualMemberTotalUCnt,
										subAgentUCnt:infoData[i].subAgentUCnt,
										memberAmount:infoData[i].memberAmount.toFixed(2),
										agentAmount:infoData[i].agentAmount.toFixed(2),
										totalAmount:(infoData[i].memberAmount + infoData[i].agentAmount).toFixed(2),
								}
								addTbRow(ele);
							}
						}
					}else{
						$("#data").append("<tr><td colspan=\"8\">没有数据</td></tr>");
					}
				});
			}
			
			//重置
			var reset = function(){
				$("#partnerUserId").val("");
				$("#monthStart").attr("value","");
				$("#monthEnd").attr("value","");
				search();
			}
			
			//查询显示
			var layerIndex;
			var search = function(){
				layerIndex = layer.load(0, {shade:[0.1,'#fff']}); //0代表加载的风格，支持0-2
				pageSize = $("#pageSize").val() * 1;
				showData(1,pageSize);
			}
			
			
			var exportToExcel = function(){
				var index = layer.load();
				$.ajax({
				    url: "${path}/partner/ptnmbblastat/export.do",
				    type: "POST",
				    data: {
				    	partnerUserId:$("#partnerUserId").val(),
						monthStart:$("#monthStart").val(),
						monthEnd:$("#monthEnd").val()
					},
				    success:function(response, status, request){
				    	layer.close(index);
				    	if(!response.canExport){
				    		layer.msg("当前查询条件数据量过大，请缩小查询条件再导出！", {icon: 5}); 
				    		return;
				    	}
				        window.location = "${path}/partner/ptnmbblastat/download.do?uuid=" + response.uuid;
			    }});
			}
			
			var showDetailInfo = function(ele){
				var parentObj = $(ele).parent().parent();
				var monthIdEle = $(parentObj).children(".mId")[0];
				var monthId = $(monthIdEle).text();
				var partnerUserIdEle = $(parentObj).children(".pUserId")[0];
				var partnerUserId = $(partnerUserIdEle).text();
				layer.open({
	                type: 2,
	                title: "佣金详细信息",
	                maxmin: true,
	                resize:true,
	                moveOut:true,
	                scrollbar:true,
	                shadeClose: false, //点击遮罩关闭层
	                skin: 'layui-layer-rim', //加上边框
	                area : ["1200px" , "700px"],
	                content: "${path}/partner/ptnmbbladetail/show.do?partnerUserId=" + partnerUserId + "&monthId=" + monthId
	            });
			}
		</script>
</body>
</html>
