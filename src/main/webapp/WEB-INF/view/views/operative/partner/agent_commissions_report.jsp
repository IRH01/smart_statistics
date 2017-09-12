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
<style>
html,body{
    height: 100%;
    width: 100%;
    backgound:white;
}
</style>
<meta charset="UTF-8">
<title>后台统计</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/admin-trend.css" />
<script src="<%=request.getContextPath()%>/js/jquery-1.11.2.min.js"></script>
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
	<div class="admin-content partner-container" style="height:100%;">
				<input type="hidden" id="userId" value='${userId}'/>
				<input type="hidden" id="statMonth" value='${statMonth}'/>
				<div class="whiteDiv tab-content">
					<div>
						<ul id="sortable" class="ui-widget-content sortable">
							<li class="ui-state-default">
								<div style="padding-bottom:35px;" class="ui-widget-content resize">
									<div class="sortHandle div-sum">
										<span>&nbsp;&nbsp;&nbsp;&nbsp;总返佣(元)：
											<span>
												<c:choose>
													<c:when test="${not empty sum}"><fmt:formatNumber type="number" value="${sum.totalAgentMemberCommissions}" pattern="#0.00"/></c:when>
       												<c:otherwise>0</c:otherwise>
												</c:choose>
											</span>
										</span>
										<span>&nbsp;&nbsp;&nbsp;&nbsp;总奖励(元)：
											<span>
												<c:choose>
													<c:when test="${not empty sum}"><fmt:formatNumber type="number" value="${sum.totalCommissions}" pattern="#0.00"/></c:when>
       												<c:otherwise>0</c:otherwise>
												</c:choose>
											</span>
										</span>
										
									</div>
									<div class="tablePanel">
										<table class="tableList1">
											<thead>
												<tr>
													<th style="display:none;">AGENTID</th>
													<th>层级</th>
													<th>推广编码</th>
													<th>代理姓名</th>
													<th>代理合集</th>
													<th>返佣</th>
													<th>比例</th>
													<th>奖励</th>
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
	<script type="text/javascript">
		
		$(function(){
			search();
			bindEnterEventOfAllEle();
			$( "#sortable" ).sortable({cursor: "move",handle: ".sortHandle"});
		});
		var bindEnterEventOfAllEle = function(){
			bindEnterEvent("realName",search);
		}
   		var pageSize = 10;
		var addTbRow = function(data){
			if(null != data && undefined != data && "" != data){
					if("B" == data.agentLevel || "b" == data.agentLevel || "C" == data.agentLevel){
						var ele="<tr><td style='display:none;' class='atId'>agentId</td><td>agentLevel</td><td>agentPartnerNo</td><td>agentName</td><td><a onclick='showAgentsInfo(this)' class='partner-link'>agentsNum</a></td><td>agentMemberCommissions</td><td>commissionsRate</td><td>commissions</td></tr>";
						ele = ele.replace("agentId",data.agentId).replace("agentLevel",data.agentLevel).replace("agentPartnerNo",data.agentPartnerNo).replace("agentName",data.agentName).replace("agentsNum",data.agentsNum).replace("agentMemberCommissions",data.agentMemberCommissions).replace("commissionsRate",data.commissionsRate).replace("commissions",data.commissions);
						$("#data").append(ele);
					}else{
						var ele="<tr><td style='display:none;' class='atId'>agentId</td><td>agentLevel</td><td>agentPartnerNo</td><td>agentName</td><td>agentsNum</td><td>agentMemberCommissions</td><td>commissionsRate</td><td>commissions</td></tr>";
						ele = ele.replace("agentId",data.agentId).replace("agentLevel",data.agentLevel).replace("agentPartnerNo",data.agentPartnerNo).replace("agentName",data.agentName).replace("agentsNum",data.agentsNum).replace("agentMemberCommissions",data.agentMemberCommissions).replace("commissionsRate",data.commissionsRate).replace("commissions",data.commissions);
						$("#data").append(ele);
					}
			}
		}
		
		//显示统计列表
		var showData = function(pageNumber,pageSize){
			$("#data").empty();
			$.post("${path}/partner/agentcmsreport/list.do",{
				pageNumber:pageNumber,
				pageSize:pageSize,
				userId:$("#userId").val(),
				statMonth:$("#statMonth").val()
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
					var infoData = json.agentCmsSumMList;
					if(null == infoData || undefined == infoData || 0 >= infoData.length){
						$("#data").append("<tr><td colspan=\"10\">没有数据</td></tr>");
					}else{
						var agentMemberCommissionsSum = 0;
						var commissionsSum = 0;
						for(var i=0;i < infoData.length;i++){
							agentMemberCommissionsSum = accAdd(agentMemberCommissionsSum,infoData[i].agentMemberCommissions);
							commissionsSum = accAdd(commissionsSum,infoData[i].commissions);
							var ele = {
									agentId:infoData[i].agentId,
									agentLevel:infoData[i].agentLevel == 1 ? "B" : "C",
									agentPartnerNo:infoData[i].agentPartnerNo,
									agentName:infoData[i].agentName,
									agentsNum:infoData[i].agentsNum,
									agentMemberCommissions:infoData[i].agentMemberCommissions.toFixed(2),
									commissionsRate:convertToPercentFormat(infoData[i].commissionsRate),
									commissions:infoData[i].commissions.toFixed(2)
							}
							addTbRow(ele);
						}
						var ele1 = {
								agentId:"",
								agentLevel:"",
								agentPartnerNo:"",
								agentName:"",
								agentsNum:"总计",
								agentMemberCommissions:agentMemberCommissionsSum.toFixed(2),
								commissionsRate:"-",
								commissions:commissionsSum.toFixed(2)
						}
						addTbRow(ele1);
					}
				}else{
					$("#totalCount").html(0);
					$("#totalPage").html(0);
					$("#data").append("<tr><td colspan=\"10\">没有数据</td></tr>");
				}
			});
		}
		
		//显示代理合集信息
		var showAgentsInfo = function(ele){
			var parentObj = $(ele).parent().parent();
			var agentIdEle = $(parentObj).children(".atId")[0];
			var agentId = $(agentIdEle).text();
			layer.open({
                type: 2,
                title: "代理佣金",
                maxmin: true,
                resize:true,
                shadeClose: false, //点击遮罩关闭层
                moveOut:true,
                scrollbar:true,
                area : ["500px" , "350px"],
                content: "${path}/partner/agentsinforeport/show.do?userId=" + agentId
            });
		}
		
		//重置
		var reset = function(){
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
