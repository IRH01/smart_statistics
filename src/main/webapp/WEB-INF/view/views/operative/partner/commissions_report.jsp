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
			<div class="panel-heading" role="tab" id="headingOne">
				<h4 class="panel-title" id="-collapsible-group-item-#1-">
					<a data-toggle="collapse" data-parent="#accordion"
						href="#collapseOne" aria-expanded="true"
						aria-controls="collapseOne" class=""> 统计图 </a>
				</h4>
			</div>
			<div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne" aria-expanded="true">
					<div class="panel-body">
						<div class="admin-content partner-container">
								<div class="section-box">
									<div class="titleDiv" style="height:25px;">
										<div style="display: inline;width:500px;text-align: center;"></div>
									    <div class="select-fr" style="padding-right: 30px;float:left;">
									    	<table style="margin-left:5px;">
									    		<tr>
									    			<td class="tb1Td"><span class="span">代理姓名：</span></td>
									    			<td><input id="realName" class="input"></td>
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
															<span>总直属会员佣金(元)：
																<span id="gameRcgSummaryAmount">
																	<c:choose>
																		<c:when test="${not empty ptnsCmsSum}"><fmt:formatNumber type="number" value="${ptnsCmsSum.directMemberCommissions}" pattern="#0.00"/></c:when>
					       												<c:otherwise>0</c:otherwise>
																	</c:choose>
																</span>
															</span>
															<span>&nbsp;&nbsp;&nbsp;&nbsp;总发展代理佣金(元)：
																<span id="lybRcgSummaryAmount">
																	<c:choose>
																		<c:when test="${not empty ptnsCmsSum}"><fmt:formatNumber type="number" value="${ptnsCmsSum.agentCommissions}" pattern="#0.00"/></c:when>
					       												<c:otherwise>0</c:otherwise>
																	</c:choose>
																</span>
															</span>
															<span>&nbsp;&nbsp;&nbsp;&nbsp;总佣金(元)：
																<span id="remainLybSummaryAmount">
																	<c:choose>
																		<c:when test="${not empty ptnsCmsSum}"><fmt:formatNumber type="number" value="${ptnsCmsSum.totalCommissions}" pattern="#0.00"/></c:when>
					       												<c:otherwise>0</c:otherwise>
																	</c:choose>
																</span>
															</span>
															
														</div>
														<div class="tablePanel">
															<table class="tableList1">
																<thead>
																	<tr>
																		<th style="display:none;">USERID</th>
																		<th>代理编码</th>
																		<th>代理姓名</th>
																		<th>直属会员佣金</th>
																		<th>发展代理佣金</th>
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
			$(function(){
				search();
				bindEnterEventOfAllEle();
				$( "#sortable" ).sortable({cursor: "move",handle: ".sortHandle"});
				$( ".resize" ).resizable({containment: ".admin-content",minHeight: 600,minWidth: 600});
			});
			var bindEnterEventOfAllEle = function(){
				bindEnterEvent("realName",search);
			}
	   		var pageSize = 10;
			var addTbRow = function(data,isTotalRow){
				if(null != data && undefined != data && "" != data){
					if(true != isTotalRow){
						var ele="<tr><td style='display:none' class='user'>userId</td><td><a onclick='showDetailCms(this)' class='partner-link' >partnerNo</a></td><td><a onclick='showDetailCms(this)' class='partner-link' class='name'>realName</a></td><td>directMemberCommissions</td><td>agentCommissions</td><td>totalCommissions</td></tr>";
						ele = ele.replace("userId",data.userId).replace("partnerNo",data.partnerNo).replace("realName",data.realName).replace("directMemberCommissions",data.directMemberCommissions).replace("agentCommissions",data.agentCommissions).replace("totalCommissions",data.totalCommissions);
						$("#data").append(ele);
					}else{
						var ele="<tr><td style='display:none' class='user'>userId</td><td style='color:#0066ff;'>partnerNo</td><td>realName</td><td>directMemberCommissions</td><td>agentCommissions</td><td>totalCommissions</td></tr>";
						ele = ele.replace("userId",data.userId).replace("partnerNo",data.partnerNo).replace("realName",data.realName).replace("directMemberCommissions",data.directMemberCommissions).replace("agentCommissions",data.agentCommissions).replace("totalCommissions",data.totalCommissions);
						$("#data").append(ele);
					}
				}
			}
			
			var showDetailCms = function(ele){
				var parentObj = $(ele).parent().parent();
				var userIdEle = $(parentObj).children(".user")[0];
				layer.open({
	                type: 2,
	                title: "佣金月汇总",
	                maxmin: true,
	                resize:true,
	                shadeClose: false, //点击遮罩关闭层
	                moveOut:true,
	                scrollbar:true,
	                area : ["1100px" , "550px"],
	                scrollbar:true,
	                content: "${path}/partner/cmsmreport/show.do?userId=" + $(userIdEle).text()
	            });
			}
			
			
			
			//显示统计列表
			var showData = function(pageNumber,pageSize){
				$("#data").empty();
				$.post("${path}/partner/cmsreport/getPtnCmsList.do",{
					pageNumber:pageNumber,
					pageSize:pageSize,
					realName:$("#realName").val()
				},function(data){
					var json = JSON.parse(data);
					if(null != json && undefined != json){
						$("#totalCount").html(json.page.total);
						$("#totalPage").html(json.page.pages);
						$("#page").myPagination({
					          currPage: pageNumber,
					          pageCount: json.page.pages,
					          ajax:{on:false,  
				                    onClick:function(page){
				                    	showData(page,pageSize);
				                    }  
				        		}
					        });
						var infoData = json.partnerCmsInfos;
						if(null == infoData || undefined == infoData || 0 >= infoData.length){
							$("#data").append("<tr><td colspan=\"10\">没有数据</td></tr>");
						}else{
							var directMemberCommissionsSum = 0;
							var agentCommissionsSum = 0;
							var totalCommissionsSum = 0;
							for(var i=0;i < infoData.length;i++){
								directMemberCommissionsSum = accAdd(directMemberCommissionsSum,infoData[i].directMemberCommissions);
								agentCommissionsSum = accAdd(agentCommissionsSum,infoData[i].agentCommissions);
								totalCommissionsSum = accAdd(totalCommissionsSum,infoData[i].totalCommissions);
								var ele = {
										userId:infoData[i].userId,
										partnerNo:infoData[i].partnerNo,
										realName:infoData[i].realName,
										directMemberCommissions:infoData[i].directMemberCommissions.toFixed(2),
										agentCommissions:infoData[i].agentCommissions.toFixed(2),
										totalCommissions:infoData[i].totalCommissions.toFixed(2)
								}
								addTbRow(ele);
							}
							var ele1 = {
									userId:"",
									partnerNo:"",
									realName:"累计",
									directMemberCommissions:directMemberCommissionsSum.toFixed(2),
									agentCommissions:agentCommissionsSum.toFixed(2),
									totalCommissions:totalCommissionsSum.toFixed(2)
							}
							addTbRow(ele1,true);
						}
					}else{
						$("#totalCount").html(0);
						$("#totalPage").html(0);
						$("#data").append("<tr><td colspan=\"10\">没有数据</td></tr>");
					}
				});
			}
			

			//重置
			var reset = function(){
				$("#realName").val("");
				search();
			}
			
			//查询显示
			var search = function(){
				pageSize = $("#pageSize").val() * 1;
				showData(1,pageSize);
			}
		</script>
</body>
</html>
